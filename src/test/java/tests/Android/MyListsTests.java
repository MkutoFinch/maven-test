package tests.Android;

import Lib.CoreTestCase;
import Lib.ui.ArticlePageObject;
import Lib.ui.MyListsPageObject;
import Lib.ui.NavigationUI;
import Lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToList() {
        String article = "Object-oriented programming language";
        String name_of_folder = "Learning programming";
        String search_line = "Java";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubString(article);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getTitleElement();
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);


    }

    @Test
    public void testPreservationOfTwoArticles() {


        String search_line = "Java";
        String first_article = "Object-oriented programming language";
        String second_article = "Island of Indonesia";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubString(first_article);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String first_name = ArticlePageObject.getTitleElement();
        ArticlePageObject.addFirstArticleToMyList(search_line);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubString(second_article);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addSecondArticleToMyList(search_line);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(search_line);
        MyListsPageObject.checkTwoArticlesInList(first_article, second_article);
        MyListsPageObject.swipeByArticleToDelete(first_name);
        ArticlePageObject.checkRemainingArticle();

    }
}
