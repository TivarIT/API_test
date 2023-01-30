package tests;

import POJO.post.Post;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ModelsUtil;
import utils.RequestUtil;

import java.util.List;

public class GetPost99Test {
    private static final ISettingsFile ENDPOINTS = new JsonSettingsFile("endPoints.json");
    private static final String POST_99_ENDPOINT = ENDPOINTS.getValue("/post99Endpoint").toString();

    RequestUtil requestUtil = new RequestUtil();

    @Test
    public void post99Test(){
        List<Post> postList = requestUtil.getReq(POST_99_ENDPOINT, Post.class, HttpStatus.SC_OK);

        Post postFromList = new Post(postList.get(0).getUserId(), postList.get(0).getId(), postList.get(0).getTitle()
        , postList.get(0).getBody());
        Assert.assertTrue(postFromList.equalsObj(ModelsUtil.getTestPost()), "Wrong post data!");
    }
}
