package Lib.ui.Android;

import Lib.ui.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "id:org.wikipedia:id/page_external_link";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        ADD_TO_READING_LIST = "xpath://*[contains(@text, 'Add to reading list')]";
        ONBOARDING_CANCEL_BUTTON = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        ARTICLE_XPATH = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
