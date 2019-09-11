package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.Android.AndroidArticlePageObject;
import Lib.ui.ArticlePageObject;
import Lib.ui.iOS.IOSArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else {
            return new IOSArticlePageObject(driver);
        }
    }
}
