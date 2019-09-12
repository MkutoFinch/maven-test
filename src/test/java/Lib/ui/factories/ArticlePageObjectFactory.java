package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.Android.AndroidArticlePageObject;
import Lib.ui.ArticlePageObject;
import Lib.ui.iOS.IOSArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else {
            return new IOSArticlePageObject(driver);
        }
    }
}
