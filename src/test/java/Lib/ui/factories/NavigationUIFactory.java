package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.Android.AndroidNavigationUI;
import Lib.ui.NavigationUI;
import Lib.ui.iOS.IOSNavigationUI;
import io.appium.java_client.AppiumDriver;

public class NavigationUIFactory {
    public static NavigationUI get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else {
            return new IOSNavigationUI(driver);

        }
    }
}
