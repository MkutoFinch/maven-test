package Lib;

import Lib.ui.WelcomePageObject;
import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);

        } else
            System.out.println("Method rotateScreenPortrait does nothing to platform " + Platform.getInstance().getPlatformVar());
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else
            System.out.println("Method rotateScreenLandscape does nothing to platform " + Platform.getInstance().getPlatformVar());
    }

    protected void runningAppInBackground(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofMillis(seconds));
        } else
            System.out.println("Method runningAppInBackground does nothing to platform " + Platform.getInstance().getPlatformVar());

    }

    private void skipWelcomePageForIOSApp() {
        if (Platform.getInstance().isIos()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject((AppiumDriver) driver);
            WelcomePageObject.clickSkip();
        } else
            System.out.println("Method skipWelcomePageForIOSApp does nothing to platform " + Platform.getInstance().getPlatformVar());
    }
    }
}

