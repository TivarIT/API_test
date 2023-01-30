package utils;

import POJO.post.Post;
import POJO.user.Address;
import POJO.user.Company;
import POJO.user.Geo;
import POJO.user.UserInfo;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class ModelsUtil {
    private static final ISettingsFile USER_DATA = new JsonSettingsFile("userData.json");
    private static final int USER_ID = Integer.parseInt(USER_DATA.getValue("/userId").toString());
    private static final String USER_NAME = USER_DATA.getValue("/userName").toString();
    private static final String USER_USERNAME = USER_DATA.getValue("/userUsername").toString();
    private static final String USER_EMAIL = USER_DATA.getValue("/userEmail").toString();
    private static final String USER_ADDRESS_STREET = USER_DATA.getValue("/userAddressStreet").toString();
    private static final String USER_ADDRESS_SUITE = USER_DATA.getValue("/userAddressSuite").toString();
    private static final String USER_ADDRESS_CITY = USER_DATA.getValue("/userAddressCity").toString();
    private static final String USER_ADDRESS_ZIPCODE = USER_DATA.getValue("/userAddressZipcode").toString();
    private static final String USER_GEO_LAT = USER_DATA.getValue("/userGeoLat").toString();
    private static final String USER_GEO_LNG = USER_DATA.getValue("/userGeoLng").toString();
    private static final String USER_PHONE = USER_DATA.getValue("/userPhone").toString();
    private static final String USER_WEBSITE = USER_DATA.getValue("/userWebsite").toString();
    private static final String USER_COMPANY_NAME = USER_DATA.getValue("/userCompanyName").toString();
    private static final String USER_COMPANY_CATCHPHRASE = USER_DATA.getValue("/userCompanyCatchPhrase").toString();
    private static final String USER_COMPANY_BS = USER_DATA.getValue("/userCompanyBs").toString();
    private static final ISettingsFile POST_DATA = new JsonSettingsFile("post99.json");
    private static final int POST_USER_ID = Integer.parseInt(POST_DATA.getValue("/userId").toString());
    private static final int POST_ID = Integer.parseInt(POST_DATA.getValue("/id").toString());
    private static final String POST_TITLE = POST_DATA.getValue("/title").toString();
    private static final String POST_BODY = POST_DATA.getValue("/body").toString();

    public static UserInfo getTestUser(){
        Geo geo = new Geo(USER_GEO_LAT, USER_GEO_LNG);
        Address address = new Address(USER_ADDRESS_STREET, USER_ADDRESS_SUITE, USER_ADDRESS_CITY, USER_ADDRESS_ZIPCODE, geo);
        Company company = new Company(USER_COMPANY_NAME, USER_COMPANY_CATCHPHRASE, USER_COMPANY_BS);
        return new UserInfo(USER_ID, USER_NAME, USER_USERNAME, USER_EMAIL, address, USER_PHONE, USER_WEBSITE, company);
    }

    public static Post getTestPost(){
        return new Post(POST_USER_ID, POST_ID, POST_TITLE, POST_BODY);
    }
}
