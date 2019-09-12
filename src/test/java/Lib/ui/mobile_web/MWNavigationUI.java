package Lib.ui.mobile_web;

import Lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
    }


    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
