package tests;

import Lib.CoreTestCase;
import Lib.ui.ArticlePageObject;
import Lib.ui.MyListsPageObject;
import Lib.ui.NavigationUI;
import Lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.close_article();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);


    }
//    @Test
//    public void testPreservationOfTwoArticles() {
//
//
//        String list_name = "MyList";
//        String first_article = "Java (programming language)";
//        String second_article = "Island of Indonesia";
//
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "can't find element by id",
//                10
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "input element not found",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + first_article + "']"),
//                "can't find element by id",
//                10
//        );
//        MainPageObject.waitForElement(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "title of the page is not visible",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
//                "Cant find element 'More options'",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
//                "Cant find element 'Add to reading list'",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/onboarding_button"),
//                "Cant find onboarding button",
//                10
//        );
//        MainPageObject.waitForElementAndClear(
//                By.id("org.wikipedia:id/text_input"),
//                "No element to clear",
//                10
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/text_input"),
//                list_name,
//                "Cant find input for title",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text='OK']"),
//                "Cant find button OK to click",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
//                "Cant find button Navigate up",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "can't find element by id",
//                10
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "input element not found",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + second_article + "']"),
//                "can't find element by id",
//                10
//        );
//        MainPageObject.waitForElement(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "title of the page is not visible",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
//                "Cant find element 'More options'",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
//                "Cant find element 'Add to reading list'",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text='" + list_name + "']"),
//                "Cant find list with name " + list_name,
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
//                "Cant find button Navigate up",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
//                "Cant find button My lists",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/item_image_container"),
//                "Cant find articles list with name " + list_name,
//                15
//        );
//
//        // Проверяю наличие статей на странице
//        MainPageObject.waitForElement(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + first_article + "']"),
//                "can't find first article on the list",
//                20
//        );
//        MainPageObject.waitForElement(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
//                "can't find second article on the list",
//                10
//        );
//
//        // Удаляю первую статью и проверяю отсуствие
//        MainPageObject.swipeElementToLeft(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='" + first_article + "']"),
//                "Cant find article with text " + first_article
//        );
//        MainPageObject.waitForElementNotPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='" + first_article + "']"),
//                "Cant find article with text " + first_article,
//                10
//        );
//
//        //Перехожу по оставшейся статье и проверяю заголовок
//        String link_article_title = MainPageObject.waitForElementAndGetAttribute(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
//                "text",
//                "cant find article to get its text attribute",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
//                "can't find second article on the list",
//                10
//        );
//        String article_title = MainPageObject.waitForElementAndGetAttribute(
//                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
//                "text",
//                "cant find article to get its text attribute",
//                10
//        );
//        assertEquals(
//                "Article title does not match title of the link on the list",
//                link_article_title,
//                article_title
//        );
//
//    }
}