package Lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class ArticlePageObject extends MainPageObject {

    private static final String TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "id:org.wikipedia:id/page_external_link",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            ADD_TO_READING_LIST = "xpath://*[contains(@text, 'Add to reading list')]",
            ONBOARDING_CANCEL_BUTTON = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            ARTICLE_XPATH = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElement(TITLE, "Cannot find article time for page ", 15);
    }

    public String getTitleElement() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cant find article by 20 swipes",
                20
        );

    }

    public void addFirstArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More Options'",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_READING_LIST,
                "Cannot add to reading List",
                5
        );
        this.waitForElementAndClick(ONBOARDING_CANCEL_BUTTON,
                "cannot press got it on onboarding",
                5
        );
        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot add to new list",
                5
        );
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot sendKeys to folders name",
                5
        );
        this.waitForElementAndClick(OK_BUTTON,
                "Cannot press OK button",
                10
        );

    }

    public void addSecondArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More Options'",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_READING_LIST,
                "Cannot add to reading List",
                5
        );

        this.waitForElementAndClick(
                ("//*[@text='" + name_of_folder + "']"),
                "Cant find list with name " + name_of_folder,
                10
        );

    }

    public void closeArticle() {
        this.waitForElementAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot find 'Navigate up'",
                10
        );
    }

    public void checkRemainingArticle() {
        String link_article_title = this.waitForElementAndGetAttribute(
                (ARTICLE_XPATH),
                "text",
                "cant find article to get its text attribute",
                10
        );
        this.waitForElementAndClick(
                (ARTICLE_XPATH),
                "can't find second article on the list",
                10
        );
        String article_title = this.waitForElementAndGetAttribute(
                (TITLE),
                "text",
                "cant find article to get its text attribute",
                20
        );
        assertEquals(
                "Article title does not match title of the link on the list",
                link_article_title,
                article_title
        );

    }

    public void assertion(String search_line) {
        this.assertElement(
                (search_line)
        );
    }
}
