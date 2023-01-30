package utils;

import POJO.post.Post;
import POJO.user.UserInfo;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.google.gson.Gson;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestUtil {
    private static final ISettingsFile ENDPOINTS = new JsonSettingsFile("endPoints.json");
    private static final String POSTS_ENDPOINT = ENDPOINTS.getValue("/postsEndpoint").toString();
    private static final ISettingsFile CONFIG = new JsonSettingsFile("config.json");
    private static final String URI = CONFIG.getValue("/baseUri").toString();
    private static final String CONTENT_TYPE = CONFIG.getValue("/dataType").toString();

    Gson parser = new Gson();

    public String getStringResponse(String endPoint, int statusCode){
        Specifications.setSpecification(Specifications.requestSpec(URI));
        Response response = given().when().get(endPoint);
        if (response.getStatusCode() != statusCode && !response.contentType().contains(CONTENT_TYPE)){
            System.out.println("Wrong status code/content type of response!");
        }
        return response.then().extract().body().asString();
    }

    public <T> T postReq(String endpoint, Object model, Class<T> returnedObject, int statusCode){
        Specifications.setSpecification(Specifications.requestSpec(URI));
        return given()
            .body(model)
            .post(endpoint)
            .then()
            .log().body().statusCode(statusCode)
            .extract().body().as(returnedObject, ObjectMapperType.GSON);
    }

    @SneakyThrows
    public <T> List<T> getReq(String endPoint, Class<T> returnedObject, int statusCode){
        Specifications.setSpecification(Specifications.requestSpec(URI));
        Response response = given().when().get(endPoint);
        if (response.getStatusCode() != statusCode && !response.contentType().contains("json")){
            System.out.println("Wrong status code and content type!");
        }
        return List.of(response.getBody().as(returnedObject));
    }

    public <T> T[] parseModels(String jsonString, Class<T[]> tClass) {
        return (T[]) this.parser.fromJson(jsonString, tClass);
    }

    public Boolean isPostsInAscendingSort(){
        Specifications.setSpecification(Specifications.requestSpec(URI));
        List<Post> posts = given()
                .when()
                .get(POSTS_ENDPOINT)
                .then().log().body()
                .extract().body().jsonPath().getList(".", Post.class);

        List<Integer> id = posts.stream().map(Post::getId).collect(Collectors.toList());
        List<Integer> sortedId = id.stream().sorted().collect(Collectors.toList());

        return id.equals(sortedId);
    }

    public void isDataEmpty(String endpoint){
        Specifications.setSpecification(Specifications.requestSpec(URI));
        given().when().get(endpoint).then().log().body().assertThat()
                .body(equalTo("{}"));
    }

    public boolean checkUserResponse(UserInfo[] listOfUsersModels, int idOfUser) {
        List<UserInfo> listOfUsers = Arrays.stream(listOfUsersModels).filter(p -> p.getId() == idOfUser).collect(Collectors.toList());

        UserInfo userFromList = new UserInfo(listOfUsers.get(0).getId(), listOfUsers.get(0).getName(), listOfUsers.get(0).getUsername()
        , listOfUsers.get(0).getEmail(), listOfUsers.get(0).getAddress(), listOfUsers.get(0).getPhone(), listOfUsers.get(0)
        .getWebsite(), listOfUsers.get(0).getCompany());

        return userFromList.equalsObj(ModelsUtil.getTestUser());
    }
}