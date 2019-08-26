import Lib.CoreTestCase;
import Lib.ui.ArticlePageObject;
import Lib.ui.MainPageObject;
import Lib.ui.SearchPageObject;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSearch() {


        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisppear();

    }
    @Test
    public void testCompareArticleTitle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticlWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getTitleElement();

        Assert.assertEquals(
                "Cannot get title",
                "Java (programming language)",
                article_title);
    }
    @Test
    public void testSwipeArticle(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticlWithSubString("Appium");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.switeToFooter();

    }
    @Test
    public void testSearchElementsInArray() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot found search input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot send keys",
                5
        );
        MainPageObject.waitForElement(
                By.xpath("//*[contains(@text, 'ava')]"),
                "Cannot send wikipedia search",
                5
        );

        List<MobileElement> listElements = driver.findElements(By.id("org.wikipedia:id/fragment_feed_feed"));

        for(MobileElement el : listElements){
            if(el.getText().equalsIgnoreCase("")){
                // do some validation //
                Assert.assertEquals(
                        "Cannot get title",
                        "ava",
                        listElements);
            }
            System.out.println(listElements);
        }

    }

    @Test
    public void testSaveFirstArticleToList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot Find about Java",
                10
        );
        MainPageObject.waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More Options'",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to reading list')]"),
                "Cannot add to reading List",
                5
        );
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "cannot press got it on onboarding",
                5
        );
        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot add to new list",
                5
        );
        String name_of_folder = "Learn Programming";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot sendKeys to folders name",
                5
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Navigate up'",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]"),
                "Cannot find 'My Lists'",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"" + name_of_folder + "\"]"),
                "Cannot find 'my folder'",
                5
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "cannot open my list",
                5
        );
        MainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete item from list"

        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "cannot find an element",
                5

        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find wikipedia search",
                5
        );
        String search_line = "Linkin park discography";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot send wikipedia search",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        MainPageObject.waitForElement(
                By.xpath(search_result_locator),
                "cannot find anything be the request " + search_line,
                15
        );
        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.id(search_result_locator)
        );
        Assert.assertTrue(
                "We found a " + amount_of_search_results + " elements",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        String search_line = "zxcasdqwe";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find wikipedia search",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot send wikipedia search",
                5
        );
        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.id("org.wikipedia:id/page_list_item_container")
        );
        Assert.assertTrue(
                "We found" + amount_of_search_results + "elements",
                amount_of_search_results > 0
        );
        String empty_results_label = "//*[text='No results found']";
    }

    @Test
    public void testPreservationOfTwoArticles() {


        String list_name = "MyList";
        String first_article = "Java (programming language)";
        String second_article = "Island of Indonesia";
        int wait = 10;

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "can't find element by id",
                wait
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "input element not found",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + first_article + "']"),
                "can't find element by id",
                wait
        );
        MainPageObject.waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "title of the page is not visible",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cant find element 'More options'",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cant find element 'Add to reading list'",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cant find onboarding button",
                wait
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "No element to clear",
                wait
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                list_name,
                "Cant find input for title",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cant find button OK to click",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find button Navigate up",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "can't find element by id",
                wait
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "input element not found",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + second_article + "']"),
                "can't find element by id",
                wait
        );
        MainPageObject.waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "title of the page is not visible",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cant find element 'More options'",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cant find element 'Add to reading list'",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + list_name + "']"),
                "Cant find list with name " + list_name,
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find button Navigate up",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cant find button My lists",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_image_container"),
                "Cant find articles list with name " + list_name,
                15
        );

        // Проверяю наличие статей на странице
        MainPageObject.waitForElement(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + first_article + "']"),
                "can't find first article on the list",
                20
        );
        MainPageObject.waitForElement(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
                "can't find second article on the list",
                wait
        );

        // Удаляю первую статью и проверяю отсуствие
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='" + first_article + "']"),
                "Cant find article with text " + first_article
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='" + first_article + "']"),
                "Cant find article with text " + first_article,
                wait
        );

        //Перехожу по оставшейся статье и проверяю заголовок
        String link_article_title = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
                "text",
                "cant find article to get its text attribute",
                wait
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
                "can't find second article on the list",
                wait
        );
        String article_title = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "text",
                "cant find article to get its text attribute",
                wait
        );
        Assert.assertEquals(
                "Article title does not match title of the link on the list",
                link_article_title,
                article_title
        );

    }

    @Test
    public void testCheckArticleTitlePresent() {


        String article_name = "Java (programming language)";

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "can't find element by id",
                10
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "input element not found",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article_name + "']"),
                "can't find element by id",
                10
        );
        MainPageObject.assertElement(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']")
        );
    }



}