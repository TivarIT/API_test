package utils;

import POJO.post.Post;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class PostRequestUtil {
    private static final ISettingsFile POST_TEST_DATA = new JsonSettingsFile("createPostData.json");
    private static final int POST_USER_ID = Integer.parseInt(POST_TEST_DATA.getValue("/postUserID").toString());
    private static final int POST_ID = Integer.parseInt(POST_TEST_DATA.getValue("/postId101").toString());
    private static final String TITLE = POST_TEST_DATA.getValue("/title").toString();
    private static final String BODY = POST_TEST_DATA.getValue("/body").toString();

    private static Post postModel = new Post(POST_USER_ID, POST_ID, TITLE, BODY);

    public static Post getPostModel(){
        return postModel;
    }
}
