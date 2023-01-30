package tests;

import POJO.user.UserInfo;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ModelsUtil;
import utils.RequestUtil;
import java.util.List;

public class GetUserTest {
    private static final ISettingsFile ENDPOINTS = new JsonSettingsFile("endPoints.json");
    private static final String USER_ENDPOINT = ENDPOINTS.getValue("/userEndpoint").toString();

    RequestUtil requestUtil = new RequestUtil();

    @Test
    public void getUserTest(){
        List<UserInfo> userInfoList = requestUtil.getReq(USER_ENDPOINT, UserInfo.class, HttpStatus.SC_OK);

        UserInfo userFromList = new UserInfo(userInfoList.get(0).getId(), userInfoList.get(0).getName(), userInfoList.get(0).getUsername()
                , userInfoList.get(0).getEmail(), userInfoList.get(0).getAddress(), userInfoList.get(0).getPhone(), userInfoList.get(0)
                .getWebsite(), userInfoList.get(0).getCompany());
        Assert.assertTrue(userFromList.equalsObj(ModelsUtil.getTestUser()));
    }
}
