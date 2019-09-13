package Lib.ui;

import Lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public static String decapitalize(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        char[] c = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {

        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title) {

        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    private static String getSavedArticleXpathBySubstring(String article_substring) {

        return ARTICLE_BY_TITLE_TPL.replace("{SUBSTRING}", article_substring);
    }
    public void openFolderByName(String name_of_folder) {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                (folder_name_xpath),
                "Cannot find  folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {

        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent((article_xpath),
                "saved article still present " + article_title, 15);

    }

    public void waitForArticleToAppearByTitle(String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElement((article_xpath),
                "saved article still present " + article_title, 15);

    }

    public void waitForArticleToAppearBySubstring(String substring) {

        String article_xpath = getSavedArticleXpathByTitle(substring);
        this.waitForElement((article_xpath),
                "saved article still present " + substring, 15);

    }
    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIos()) {
            this.swipeElementToLeft((article_xpath),
                    "Cannot delete item from list");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot click to butoon to remove article from saved", 10);
        }

        if (Platform.getInstance().isIos()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "cannot find save article ");
        }
        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }


    public void checkTwoArticlesInList(String first_article, String second_article) {
        this.waitForElement(
                ("//*[@text='" + decapitalize(first_article) + "']"),
                "can't find first article on the list",
                20
        );
        this.waitForElement(
                ("//*[@text='" + decapitalize(second_article) + "']"),
                "can't find second article on the list",
                10
        );
    }
}