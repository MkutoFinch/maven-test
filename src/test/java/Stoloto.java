import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
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

public class Stoloto {
        private AppiumDriver driver;

        @Before
        public void setUp() throws Exception {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("platformName", "Android");
            cap.setCapability("deviceName", "TestDevice");
            cap.setCapability("uid","HT6B70200690");
            //cap.setCapability("platformVersion", "8.0");
            //cap.setCapability("platformVersion", "q");
            cap.setCapability("automationName", "Appium");
            cap.setCapability("appPackage", "ru.stoloto.mobile.debug");
            cap.setCapability("appActivity", "ru.stoloto.mobile.ca.presentation.ui.activity.MainActivity");
            //cap.setCapability("app", "/Users/pavelrybakov/Docs/GitHub/maven-test/src/apks/org.wikipedia.apk");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        }

        @After
        public void tearDown() {
            if (driver != null) driver.quit();
        }

        @Test
        public void auth(){

            waitForElementAndClick(By.id("ru.stoloto.mobile.debug:id/confirm"),
                    ""
                    , 5);
            waitForElementAndClick(By.id("ru.stoloto.mobile.debug:id/confirm"),"", 5);
            waitForElementAndClick(By.id("ru.stoloto.mobile.debug:id/confirm"),"", 5);
            waitForElementAndSendKeys(By.id("ru.stoloto.mobile.debug:id/ftietAuthStartPhoneNumber"),"9299519243" ,"", 5);
            waitForElementAndClick(By.id("ru.stoloto.mobile.debug:id/tvAuthStartButtonNext"), "", 5);
            waitForElementAndSendKeys(By.id("ru.stoloto.mobile.debug:id/ftietAuthLoginPhonePassword"), "finch2", "", 5);
            waitForElementAndClick(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.support.v7.widget.LinearLayoutCompat/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.ImageView"),
                    "",5);
            waitForElementAndClick(By.id("ru.stoloto.mobile.debug:id/btnPositive"), "", 5);
            waitForElementAndClick(By.id("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.support.v7.widget.LinearLayoutCompat/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ImageView"), "", 5);


            waitForElement(
                    By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[6]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TextView"),
                    "",
                    5
            );
            WebElement title_element = waitForElement(
                    By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[6]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TextView"),
                    "Cannot find text",
                    15

            );

            String acticle_title = title_element.getAttribute("text");

            Assert.assertEquals("Cannot get title", "test", acticle_title);

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

