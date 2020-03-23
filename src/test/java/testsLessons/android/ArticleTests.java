package testsLessons.android;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactroy;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String substring_article = "Object-oriented programming language";
        String title_article = "Java (programming language)";
        if(Platform.getInstance().isIOS()) {
            searchPageObject.clickByArticleWithSubstring(title_article+"\n"+substring_article);
        } else {
            searchPageObject.clickByArticleWithSubstring(substring_article);
        }

        ArticlePageObject articlePageObject = ArticlePageObjectFactroy.get(driver);
        String article_title_actual = articlePageObject.getArticleTitle(title_article);


        assertEquals(
                "We see unexpected title",
                title_article,
                article_title_actual
        );
    }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject articlePageObject = ArticlePageObjectFactroy.get(driver);
        articlePageObject.waitForTitleElement("Appium");
        articlePageObject.swipeToFooter(20);
    }
}
