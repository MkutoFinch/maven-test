package Lib.ui.iOS;

import Lib.ui.NavigationUI;
import io.appium.java_client.AppiumDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
    }


    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
