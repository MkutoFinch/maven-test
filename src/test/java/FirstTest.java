import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "AndroidTestDevice");
        //cap.setCapability("uid","192.168.56.103:5555");
        cap.setCapability("platformVersion", "8.0");
        cap.setCapability("automationName", "Appium");
        cap.setCapability("appPackage", "org.wikipedia");
        cap.setCapability("appActivity", ".main.MainActivity");
        cap.setCapability("app", "/Users/pavelrybakov/Docs/GitHub/maven-test/src/apks/org.wikipedia.apk");

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
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='java']"),
                "Cannot Find about Java",
                10
        );
        WebElement title_element = waitForElement(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title",
                15

        );

        String acticle_title = title_element.getAttribute("text");

        Assert.assertEquals("Cannot get title", "Java (programming language)", acticle_title);
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
}
