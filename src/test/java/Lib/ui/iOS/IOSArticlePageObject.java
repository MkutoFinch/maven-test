package Lib.ui.iOS;

import Lib.ui.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        ADD_TO_READING_LIST = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_ADD_TO_READING_LIST_IOS_TUTOR = "id:places auth close";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}


