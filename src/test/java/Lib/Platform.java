package Lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Platform {
    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_ANDROID = "Android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform() {
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIos()) {
            return new IOSDriver(URL, this.getIosDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of a driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIos() {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "AndroidTestDevice");
        cap.setCapability("uid", "emulator-5554");
        cap.setCapability("platformVersion", "8.0");
        cap.setCapability("appPackage", "org.wikipedia");
        cap.setCapability("appActivity", ".main.MainActivity");
        cap.setCapability("app", "/Library/Tests/Apks/org.wikipedia.apk");
        return cap;
    }

    private DesiredCapabilities getIosDesiredCapabilities() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "iOS");
        cap.setCapability("deviceName", "iPhone SE");
        cap.setCapability("platformVersion", "11.3");
        cap.setCapability("app", "/Library/Tests/Apks/Wikipedia.app");
        return cap;
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}

