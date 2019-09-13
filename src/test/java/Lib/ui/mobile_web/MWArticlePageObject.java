package Lib.ui.mobile_web;

import Lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        ADD_TO_READING_LIST = "css:#ca-watch";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions a#ca-watch.mw-ui-icon-mf-watched watched";

    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}