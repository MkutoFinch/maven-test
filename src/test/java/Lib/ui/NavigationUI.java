package Lib.ui;

import Lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation butoon", 5);
        } else
            System.out.println("Method openNavigation bot work for platform  " + Platform.getInstance().getPlatformVar());
    }

    public void clickMyList() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Cannot find 'My Lists' ", 10);
        }
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find 'My Lists'",
                5
        );
    }
}
