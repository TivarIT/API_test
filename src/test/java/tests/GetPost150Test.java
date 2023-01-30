package tests;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RequestUtil;
import utils.Utils;

public class GetPost150Test {
    private static final ISettingsFile ENDPOINTS = new JsonSettingsFile("endPoints.json");
    private static final String POST_150_ENDPOINT = ENDPOINTS.getValue("/post150Endpoint").toString();

    Utils utils = new Utils();
    RequestUtil requestUtil = new RequestUtil();

    @Test
    public void post150Test(){
        Assert.assertEquals(utils.getResponseCode(POST_150_ENDPOINT), HttpStatus.SC_NOT_FOUND, "ERROR! Wrong status code!");
        requestUtil.isDataEmpty(POST_150_ENDPOINT);
    }
}
