package Lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {
    private static final String
            STEP_LEARN_MORE_LINK = "Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "New ways to explore",
            STEP_ADD_OR_EDIT_PREF_LANG = "Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "Learn more about data collected",
            STEP_GET_STARTED = "Get started",
            STEP_NEXT = "Next";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }


    public void waitForLearnMoreLink() {
        this.waitForElement(By.id(STEP_LEARN_MORE_LINK), "Cannot find mire about 'Learn more about Wikipedia'", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(By.id(STEP_NEXT), "Cannot find and click 'next'", 10);
    }

    public void waitForNewWayToExplore() {
        this.waitForElement(By.id(STEP_NEW_WAYS_TO_EXPLORE_TEXT), "Cannot find about 'New ways to explore'", 10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElement(By.id(STEP_ADD_OR_EDIT_PREF_LANG), "Cannot find about 'Add or edit preferred languages'", 10);
    }

    public void waitForLearnMoreAboutDataCollected() {
        this.waitForElement(By.id(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED), "Cannot find about 'Learn more about data collected'", 10);
    }

    public void waitForGetStarted() {
        this.waitForElementAndClick(By.id(STEP_GET_STARTED), "Cannot find about 'Get started'", 10);
    }

}

