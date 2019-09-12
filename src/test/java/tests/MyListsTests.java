package tests;

import Lib.CoreTestCase;
import Lib.Platform;
import Lib.ui.ArticlePageObject;
import Lib.ui.MyListsPageObject;
import Lib.ui.NavigationUI;
import Lib.ui.SearchPageObject;
import Lib.ui.factories.ArticlePageObjectFactory;
import Lib.ui.factories.MyListsPageObjectFactory;
import Lib.ui.factories.NavigationUIFactory;
import Lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static String NAME_OF_FOLDER = "Learning programming";
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
            ArticlePageObject.closeSyncSuggestionWindow();
        }

        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
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
        String second_title = "Java (programming language)";

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
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubString(second_title);
        ArticlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addSecondArticleToMyList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeSyncSuggestionWindow();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }
        MyListsPageObject.checkTwoArticlesInList(first_article, second_title);
        MyListsPageObject.swipeByArticleToDelete(first_name);
        ArticlePageObject.checkRemainingArticle();
    }
}
