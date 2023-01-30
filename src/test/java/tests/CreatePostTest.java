package tests;

import POJO.post.Post;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PostRequestUtil;
import utils.RequestUtil;


public class CreatePostTest {
    private static final ISettingsFile ENDPOINTS = new JsonSettingsFile("endPoints.json");
    private static final String POSTS_ENDPOINT = ENDPOINTS.getValue("/postsEndpoint").toString();
    private static final ISettingsFile POST_DATA = new JsonSettingsFile("createPostData.json");
    private static final int POST_USER_ID = Integer.parseInt(POST_DATA.getValue("/postUserID").toString());
    private static final int POST_ID = Integer.parseInt(POST_DATA.getValue("/postId101").toString());
    private static final String TITLE = POST_DATA.getValue("/title").toString();
    private static final String BODY = POST_DATA.getValue("/body").toString();

    RequestUtil requestUtil = new RequestUtil();

    @Test
    public void createPostTest(){
        Post postModel = requestUtil.postReq(POSTS_ENDPOINT, PostRequestUtil.getPostModel(), Post.class, HttpStatus.SC_CREATED);

        Assert.assertEquals(postModel.getTitle(), TITLE, "Title doesn't match!");
        Assert.assertEquals(postModel.getBody(), BODY, "Body doesn't match!");
        Assert.assertEquals(postModel.getUserId(), POST_USER_ID, "UserId doesn't match!");
        Assert.assertEquals(postModel.getId(), POST_ID, "Id doesn't match!");
    }
}
