package Lib.ui.iOS;

import Lib.ui.MyListsPageObject;
import io.appium.java_client.AppiumDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name='{TITLE}')]";
    }

    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
