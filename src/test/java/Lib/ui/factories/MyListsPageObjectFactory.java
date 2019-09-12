package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.Android.AndroidMyListsPageObject;
import Lib.ui.MyListsPageObject;
import Lib.ui.iOS.IOSMyListsPageObject;
import Lib.ui.mobile_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IOSMyListsPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }

}
