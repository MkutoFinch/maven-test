package tests;

import Lib.CoreTestCase;
import Lib.Platform;
import Lib.ui.*;
import Lib.ui.factories.ArticlePageObjectFactory;
import Lib.ui.factories.MyListsPageObjectFactory;
import Lib.ui.factories.NavigationUIFactory;
import Lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static String NAME_OF_FOLDER = "Learning programming";
    private String login = "Mkuteg";
    private String password = "qwerty1488";
    @Test
    public void testSaveFirstArticleToList() {

        String article = "bject-oriented programming language";

        String search_line = "Java";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubString(article);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getTitleElement();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticleToMySaved();
            if (Platform.getInstance().isIos()) {
                ArticlePageObject.closeSyncSuggestionWindow();
            }
        }
        if (Platform.getInstance().isMW()) {
            AutorisationPageObject Auth = new AutorisationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not the same page after login ", article_title, ArticlePageObject.getTitleElement());
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);


    }

    @Test
    public void testPreservationOfTwoArticles() {

        String search_line = "Java";
        String first_article = "JavaScript";
        String second_article = "Java (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubString(first_article);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_name = ArticlePageObject.getTitleElement();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeSyncSuggestionWindow();
        }
        if (Platform.getInstance().isMW()) {
            AutorisationPageObject Auth = new AutorisationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not the same page after login ", first_name, ArticlePageObject.getTitleElement());
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubString(second_article);
        ArticlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addSecondArticleToMyList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticleToMySaved();
            if (Platform.getInstance().isIos()) {
                ArticlePageObject.closeSyncSuggestionWindow();
            }
        }
        if (Platform.getInstance().isMW()) {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }
        MyListsPageObject.checkTwoArticlesInList(first_article, second_article);
        MyListsPageObject.swipeByArticleToDelete(first_name);
        ArticlePageObject.checkRemainingArticle();
    }
}
