package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.Android.AndroidNavigationUI;
import Lib.ui.NavigationUI;
import Lib.ui.iOS.IOSNavigationUI;
import Lib.ui.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {

    public static NavigationUI get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
