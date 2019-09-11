package Lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULTS_ELEMENT;
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private int timeOut = 15;
    //Templates methods
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", timeOut);
        this.waitForElement(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", timeOut);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElement(SEARCH_CANCEL_BUTTON, "cannot find cancel button", timeOut);
    }

    public void waitForCancelButtonToDisppear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", timeOut);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot click to cancel search", timeOut);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search element", timeOut);
    }

    public void waitForSearchResult(String substing) {
        String search_result_xpath = getResultSearchElement(substing);
        this.waitForElement((search_result_xpath), "Cannot find search result with substring " + substing, timeOut);
    }

    public void clickByArticleWithSubString(String substing) {
        String search_result_xpath = getResultSearchElement(substing);
        this.waitForElementAndClick((search_result_xpath), "Cannot find and click search result with substring " + substing, timeOut);
    }

    public void waitForArticleWithSubString(String substing) {
        String search_result_xpath = getResultSearchElement(substing);
        this.waitForElement((search_result_xpath), "Cannot find search result with substring " + substing, timeOut);
    }

    public int getAmountOfFindArticles() {

        this.waitForElement(
                SEARCH_RESULT_ELEMENT,
                "cannot find anything be the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {

        this.waitForElement(SEARCH_EMPTY_RESULTS_ELEMENT, "Cannot find empty results for  ", timeOut);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed to not fid any results ");
    }


}
