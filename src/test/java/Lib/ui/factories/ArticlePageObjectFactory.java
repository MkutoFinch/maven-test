package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.Android.AndroidArticlePageObject;
import Lib.ui.ArticlePageObject;
import Lib.ui.iOS.IOSArticlePageObject;
import Lib.ui.mobile_web.MWArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IOSArticlePageObject(driver);
        } else {
            return new MWArticlePageObject(driver);
        }
    }
}
