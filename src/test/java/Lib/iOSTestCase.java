package Lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {

    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "iOS");
        cap.setCapability("deviceName", "iPhone SE");
        cap.setCapability("platformVersion", "11.3");
        cap.setCapability("app", "/Library/Tests/App/Wikipedia.app");


        driver = new IOSDriver(new URL(AppiumUrl), cap);
    }

    @Override
    protected void tearDown() throws Exception {
        if (driver != null) driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void runningAppInBackground(int seconds) {
        driver.runAppInBackground(Duration.ofMillis(seconds));
    }
}
