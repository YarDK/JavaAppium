package testsLessons.android;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactroy;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        if(Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String substring_article = "Object-oriented programming language";
        String title_article = "Java (programming language)";
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(substring_article);

        ArticlePageObject articlePageObject = ArticlePageObjectFactroy.get(driver);
        String title_before_rotation = articlePageObject.getArticleTitle(title_article);
        this.rotateScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle(title_article);

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = articlePageObject.getArticleTitle(title_article);

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        if(Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
