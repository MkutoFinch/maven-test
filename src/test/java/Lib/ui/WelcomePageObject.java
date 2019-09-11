package Lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {
    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREF_LANG = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
            STEP_GET_STARTED = "id:Get started",
            STEP_NEXT = "id:Next",
            STEP_SKIP = "id:Skip";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }


    public void waitForLearnMoreLink() {
        this.waitForElement(STEP_LEARN_MORE_LINK, "Cannot find more about 'Learn more about Wikipedia'", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(STEP_NEXT, "Cannot find and click 'next'", 10);
    }

    public void waitForNewWayToExplore() {
        this.waitForElement(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find about 'New ways to explore'", 10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElement(STEP_ADD_OR_EDIT_PREF_LANG, "Cannot find about 'Add or edit preferred languages'", 10);
    }

    public void waitForLearnMoreAboutDataCollected() {
        this.waitForElement(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED, "Cannot find about 'Learn more about data collected'", 10);
    }

    public void waitForGetStarted() {
        this.waitForElementAndClick(STEP_GET_STARTED, "Cannot find about 'Get started'", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(STEP_SKIP, "Cannot find skip button", 10);
    }
}

