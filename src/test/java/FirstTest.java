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
        cap.setCapability("automationName", "Appium");
        cap.setCapability("appPackage", "org.wikipedia");
        cap.setCapability("appActivity", ".main.MainActivity");
        cap.setCapability("app", "/Users/user1/Tests/JavaAppiumAutomation/apks/org.wikipedia.apk");

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
                5
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
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                "Learn Programming",
                "Cannot sendKeys to folders name",
                5
        );
        waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                10
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[//android.widget.ImageButton[@content-desc=\"Navigate up\"]]"),
                "Cannot find 'Navigate up'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[//android.widget.ImageButton[@content-desc=\"My lists\"]]"),
                "Cannot find 'Navigate up'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[//android.widget.ImageButton[@content-desc=\"Learn Programming\"]]"),
                "Cannot find 'my list'",
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
                "can find an element",
                5

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

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElement(by, "", 15);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int low_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + low_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).
                waitAction(150).
                moveTo(left_x, middle_y).
                release().
                perform();
    }



}