import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;


public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "AndroidTestDevice");
        //cap.setCapability("uid","192.168.56.103:5555");
        cap.setCapability("uid", "emulator-5554");
        cap.setCapability("platformVersion", "8.0");
        //cap.setCapability("automationName", "Appium");
        cap.setCapability("appPackage", "org.wikipedia");
        cap.setCapability("appActivity", ".main.MainActivity");
        cap.setCapability("app", "/Library/Tests/Apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }
    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
    @Test
    public void searchTest(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find wikipedia search",
                5
        );
        waitForElement(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot send wikipedia search",
                5
        );
//        waitForElement(
//            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
//                "Cannot Find about Java",
//                15
//        );
    }
    @Test
    public void testCancelSearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot found search input",
                10
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot send keys",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear field",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X on page",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X still presented on this page",
                5
        );
    }
    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find wikipedia search",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot send wikipedia search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot Find about Java",
                10
        );
        WebElement title_element = waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title",
                15
        );

        String acticle_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "Cannot get title",
                "Java (programming language)",
                acticle_title);
    }
    @Test
    public void testSwipeArticle(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find wikipedia search",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot send wikipedia search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot Find about Java",
                10
        );
        waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title",
                15
        );
        swipeUpQuick();
        swipeUpToFindElement(
                By.id("org.wikipedia:id/page_external_link"),
                "Cant find :open desktop version",
                20
        );

    }
    @Test
    public void searchElementsInArray(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot found search input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot send keys",
                5
        );
        waitForElement(
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
    public void saveFirstArticleToList() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find wikipedia search",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot send wikipedia search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot Find about Java",
                10
        );
        waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More Options'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to reading list')]"),
                "Cannot add to reading List",
                5
        );
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "cannot press got it on onboarding",
                5
        );
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot add to new list",
                5
        );
        String name_of_folder = "Learn Programming";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot sendKeys to folders name",
                5
        );
        waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                10
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Navigate up'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]"),
                "Cannot find 'My Lists'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"" + name_of_folder + "\"]"),
                "Cannot find 'my folder'",
                5
        );
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "cannot open my list",
                5
        );
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete item from list"

        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "cannot find an element",
                5

        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find wikipedia search",
                5
        );
        String search_line = "Linkin park discography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot send wikipedia search",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElement(
                By.xpath(search_result_locator),
                "cannot find anything be the request " + search_line,
                15
        );
        int amount_of_search_results = getAmountOfElements(
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

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find wikipedia search",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot send wikipedia search",
                5
        );
        int amount_of_search_results = getAmountOfElements(
                By.id("org.wikipedia:id/page_list_item_container")
        );
        Assert.assertTrue(
                "We found" + amount_of_search_results + "elements",
                amount_of_search_results > 0
        );
        String empty_results_label = "//*[text='No results found']";
    }

    @Test
    public void preservationOfTwoArticles() {


        String list_name = "MyList";
        String first_article = "Java (programming language)";
        String second_article = "Island of Indonesia";
        int wait = 10;

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "can't find element by id",
                wait
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "input element not found",
                wait
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + first_article + "']"),
                "can't find element by id",
                wait
        );
        waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "title of the page is not visible",
                wait
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cant find element 'More options'",
                wait
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cant find element 'Add to reading list'",
                wait
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cant find onboarding button",
                wait
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "No element to clear",
                wait
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                list_name,
                "Cant find input for title",
                wait
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cant find button OK to click",
                wait
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find button Navigate up",
                wait
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "can't find element by id",
                wait
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "input element not found",
                wait
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + second_article + "']"),
                "can't find element by id",
                wait
        );
        waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "title of the page is not visible",
                wait
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cant find element 'More options'",
                wait
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cant find element 'Add to reading list'",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@text='" + list_name + "']"),
                "Cant find list with name " + list_name,
                wait
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find button Navigate up",
                wait
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cant find button My lists",
                wait
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_image_container"),
                "Cant find articles list with name " + list_name,
                15
        );

        // Проверяю наличие статей на странице
        waitForElement(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + first_article + "']"),
                "can't find first article on the list",
                20
        );
        waitForElement(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
                "can't find second article on the list",
                wait
        );

        // Удаляю первую статью и проверяю отсуствие
        swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='" + first_article + "']"),
                "Cant find article with text " + first_article
        );
        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='" + first_article + "']"),
                "Cant find article with text " + first_article,
                wait
        );

        //Перехожу по оставшейся статье и проверяю заголовок
        String link_article_title = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
                "text",
                "cant find article to get its text attribute",
                wait
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java']"),
                "can't find second article on the list",
                wait
        );
        String article_title = waitForElementAndGetAttribute(
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

    private WebElement waitForElement(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElement(By by, String error_message) {
        return waitForElement(by, error_message);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElement(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElement(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear( By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElement(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfswipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.
                press(x, start_y).
                waitAction(timeOfswipe).
                moveTo(x, end_y).
                release().
                perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        driver.findElements(by).size();
        int already_swiped = 0;
        while(driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes){
                waitForElement(by, "cannot find element by swiping up.\n" + error_message);
                        return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected String waitForElementAndGetAttribute(By by, String attribute, String err_msg, int timeout) {
        WebElement element = waitForElement(by, err_msg, timeout);
        return element.getAttribute(attribute);
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElement(by, "", 15);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int low_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + low_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).
                waitAction(300).
                moveTo(left_x, middle_y).
                release().
                perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }



}