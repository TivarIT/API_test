package tests;

import POJO.user.UserInfo;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RequestUtil;

public class GetAllUsersTest {
    private static final ISettingsFile ENDPOINTS = new JsonSettingsFile("endPoints.json");
    private static final String USERS_ENDPOINT = ENDPOINTS.getValue("/usersEndpoint").toString();
    private static final ISettingsFile USER_DATA = new JsonSettingsFile("userData.json");
    private static final int USER_ID = Integer.parseInt(USER_DATA.getValue("/userId").toString());

    RequestUtil requestUtil = new RequestUtil();

    @Test
    public void allUsersTest(){
        UserInfo[] listOfUsersModels = requestUtil.parseModels(requestUtil.getStringResponse(USERS_ENDPOINT, HttpStatus.SC_OK),
                UserInfo[].class);
        Assert.assertTrue(requestUtil.checkUserResponse(listOfUsersModels, USER_ID), "Wrong user data!");
    }
}
