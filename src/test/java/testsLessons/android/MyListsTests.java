package testsLessons.android;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactroy;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactroy.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);


        searchPageObject.initSearchInput();
        String substring_article = "Object-oriented programming language";
        String title_article = "Java (programming language)";
        searchPageObject.typeSearchLine("Java");

        if(Platform.getInstance().isAndroid()) {
            searchPageObject.clickByArticleWithSubstring(substring_article);
            articlePageObject.waitForTitleElement(title_article);
            String name_of_folder = "Learning Programming";
            articlePageObject.addFirstArticleToMyList(name_of_folder);
            articlePageObject.closeArticle();

            navigationUI.clickMyList();

            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(title_article);
        }else{
            searchPageObject.clickByArticleWithSubstring(title_article+"\n"+substring_article);
            articlePageObject.waitForTitleElement(title_article);
            articlePageObject.addArticleToMyListForIOS();
            articlePageObject.clickByCloseButtonPopoverSyncSavedArticle();
            articlePageObject.closeArticle();

            navigationUI.clickMyList();
            myListsPageObject.swipeByArticleToDelete(title_article+"\n"+substring_article);

        }
    }
}
