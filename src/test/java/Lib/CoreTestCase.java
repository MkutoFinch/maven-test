package Lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {
    public static final String PLATFORM_IOS = "iOS";
    public static final String PLATFORM_ANDROID = "Android";

    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        DesiredCapabilities cap = this.getCapabilitiesByPlatformEnv();

        driver = new AndroidDriver(new URL(AppiumUrl), cap);
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities cap = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {

            cap.setCapability("platformName", "Android");
            cap.setCapability("deviceName", "AndroidTestDevice");
            cap.setCapability("uid", "emulator-5554");
            cap.setCapability("platformVersion", "8.0");
            cap.setCapability("appPackage", "org.wikipedia");
            cap.setCapability("appActivity", ".main.MainActivity");
            cap.setCapability("app", "/Library/Tests/Apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            cap.setCapability("platformName", "iOS");
            cap.setCapability("deviceName", "iPhone SE");
            cap.setCapability("platformVersion", "11.3");
            cap.setCapability("app", "/Users/user1/Downloads/Wikipedia.app");
        } else {
            throw new Exception("Cannot het run platform from env variable. Platform value " + platform);

        }
        return cap;
    }
}

