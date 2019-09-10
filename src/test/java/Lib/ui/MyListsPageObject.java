package Lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject {

    private static final String FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";

    public MyListsPageObject(AppiumDriver driver) {
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

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft((article_xpath),
                "Cannot delete item from list"

        );
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