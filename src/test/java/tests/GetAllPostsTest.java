package tests;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RequestUtil;
import utils.Utils;

public class GetAllPostsTest {
    private static final ISettingsFile ENDPOINTS = new JsonSettingsFile("endPoints.json");
    private static final String POSTS_ENDPOINT = ENDPOINTS.getValue("/postsEndpoint").toString();

    Utils utils = new Utils();
    RequestUtil requestUtil = new RequestUtil();

    @Test
    public void allPostsTest(){
        Assert.assertEquals(utils.getResponseCode(POSTS_ENDPOINT), HttpStatus.SC_OK, "ERROR! Wrong status code!");
        Assert.assertTrue(utils.isJson(POSTS_ENDPOINT));
        Assert.assertTrue(requestUtil.isPostsInAscendingSort());
    }
}
