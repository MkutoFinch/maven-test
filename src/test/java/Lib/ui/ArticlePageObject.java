package Lib.ui;

import Lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertEquals;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            ADD_TO_READING_LIST,
            ONBOARDING_CANCEL_BUTTON,
            MY_LIST_NAME_INPUT,
            OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            ARTICLE_XPATH,
            CLOSE_ADD_TO_READING_LIST_IOS_TUTOR;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElement(TITLE, "Cannot find article time for page ", 15);
    }

    public String getTitleElement() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }

    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cant find article by 20 swipes",
                    40
            );
        } else {
            this.switeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
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

    public void addArticleToMySaved() {
        this.waitForElementAndClick(ADD_TO_READING_LIST, "Cannot find option to add article to reading list", 10);
    }

    public void closeSyncSuggestionWindow() {
        this.waitForElementAndClick(CLOSE_ADD_TO_READING_LIST_IOS_TUTOR, "Cannot close sync suggestion ", 5);
    }
}
