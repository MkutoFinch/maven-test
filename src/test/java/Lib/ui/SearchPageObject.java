package Lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULTS_ELEMENT = "//*[@text='No results found']";
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    //Templates methods
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElement(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElement(By.id(SEARCH_CANCEL_BUTTON), "cannot find cancel button", 5);
    }

    public void waitForCancelButtonToDisppear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot click to cancel search", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search element", 5);
    }

    public void waitForSearchResult(String substing) {
        String search_result_xpath = getResultSearchElement(substing);
        this.waitForElement(By.xpath(search_result_xpath), "Cannot find search result with substring" + substing, 5);
    }

    public void clickByArticleWithSubString(String substing) {
        String search_result_xpath = getResultSearchElement(substing);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring" + substing, 5);
    }

    public void waitForArticleWithSubString(String substing) {
        String search_result_xpath = getResultSearchElement(substing);
        this.waitForElement(By.xpath(search_result_xpath), "Cannot find and click search result with substring" + substing, 5);
    }

    public int getAmountOfFindArticles() {

        this.waitForElement(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "cannot find anything be the request ",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {

        this.waitForElement(By.xpath(SEARCH_EMPTY_RESULTS_ELEMENT), "Cannot find empty results for  ", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed to not fid any results ");
    }
}
