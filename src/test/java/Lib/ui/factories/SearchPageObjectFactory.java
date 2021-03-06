package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.Android.AndroidSearchPageObject;
import Lib.ui.SearchPageObject;
import Lib.ui.iOS.iOSSearchPageObject;
import Lib.ui.mobile_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isIos()) {
            return new iOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }
}
