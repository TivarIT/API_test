package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import static io.restassured.RestAssured.given;

public class Utils {
    private static final ISettingsFile CONFIG = new JsonSettingsFile("config.json");
    private static final String URI = CONFIG.getValue("/baseUri").toString();
    private static final String DATA_TYPE = CONFIG.getValue("/dataType").toString();


    public int getResponseCode(String endpoint){
        Specifications.setSpecification(Specifications.requestSpec(URI));
        return  given().when().get(endpoint).then().extract().response().getStatusCode();
    }

    public boolean isJson(String endpoint){
        Specifications.setSpecification(Specifications.requestSpec(URI));
        return  given().when().get(endpoint).then().extract().response().contentType().contains(DATA_TYPE);
    }
}
