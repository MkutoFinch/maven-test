package tests.Android;

import Lib.CoreTestCase;
import Lib.ui.ArticlePageObject;
import Lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResult() {
        String search_line = "Java";
        String substring = "Object-oriented programming language";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubString(substring);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String title_before_rotation = ArticlePageObject.getTitleElement();
        rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getTitleElement();
        assertEquals("Article title have been change after screen rotation ", title_before_rotation, title_after_rotation);
        rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getTitleElement();
        assertEquals("Article title have been change after screen rotation ", title_before_rotation, title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        String search_line = "Java";
        String substring = "Object-oriented programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForArticleWithSubString(substring);
        runningAppInBackground(2);
        SearchPageObject.waitForArticleWithSubString(substring);
    }
}
