package testsHomework;

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

public class Ex5SavingTwoArticles extends CoreTestCase {

    /*
    Написать тест, который:
    1. Сохраняет две статьи в одну папку
    2. Удаляет одну из статей
    3. Убеждается, что вторая осталась
    4. Переходит в неё и убеждается, что title совпадает

    Ex11: Рефакторинг
    Адаптирвать под iOS
    */

    @Test
    public void testSavingTwoArticles() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactroy.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String substring_article_java = "Object-oriented programming language";
        String title_article_java = "Java (programming language)";
        searchPageObject.typeSearchLine("Java");

        if(Platform.getInstance().isAndroid()) {
            searchPageObject.clickByArticleWithSubstring(substring_article_java);
            articlePageObject.waitForTitleElement(title_article_java);
            String article_title_java = articlePageObject.getArticleTitle(title_article_java);
            String name_of_folder = "Learning Programming";
            articlePageObject.addFirstArticleToMyList(name_of_folder);
            articlePageObject.closeArticle();
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Ruby");
            searchPageObject.clickByArticleWithSubstring("Programming language");
            articlePageObject.waitForTitleElement(title_article_java);
            String article_title_ruby = articlePageObject.getArticleTitle(title_article_java);
            articlePageObject.addSecondArticleToMyList(name_of_folder);
            articlePageObject.closeArticle();
            navigationUI.clickMyList();
            myListsPageObject.waitForListPresent(name_of_folder);
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_title_ruby);
            myListsPageObject.waitForArticleToAppearByTitleByXpath(article_title_java);
            myListsPageObject.waitForArticleToDisappearByTitleByXpath(article_title_ruby);
            myListsPageObject.clickByArticleWithTitle(article_title_java);
        }else{
            String title_and_substring_java = title_article_java + "\n"+ substring_article_java;
            String title_article_ruby = "Ruby (programming language)";
            String substring_article_ruby = "Programming language";
            String title_and_substring_ruby = title_article_ruby + "\n" + substring_article_ruby;
            String substring_article_ruby_in_my_list = "High-level programming language first released in 1995";
            String title_and_substring_ruby_in_my_list = title_article_ruby + "\n" + substring_article_ruby_in_my_list;

            searchPageObject.clickByArticleWithSubstring(title_and_substring_java);
            articlePageObject.waitForTitleElement(title_article_java);
            articlePageObject.addArticleToMyListForIOS();
            articlePageObject.clickByCloseButtonPopoverSyncSavedArticle();
            articlePageObject.closeArticle();
            searchPageObject.initSearchInput();
            searchPageObject.clearSearchField();
            searchPageObject.typeSearchLine("Ruby");
            searchPageObject.clickByArticleWithSubstring(title_and_substring_ruby);
            articlePageObject.waitForTitleElement(title_article_ruby);
            articlePageObject.addArticleToMyListForIOS();
            articlePageObject.closeArticle();
            navigationUI.clickMyList();
            myListsPageObject.swipeByArticleToDelete(title_and_substring_ruby_in_my_list);
            myListsPageObject.waitForArticleToAppearByTitleByXpath(title_and_substring_java);
            myListsPageObject.waitForArticleToDisappearByTitleByXpath(title_and_substring_ruby_in_my_list);
            myListsPageObject.clickByArticleWithTitle(title_and_substring_java);
            articlePageObject.waitForTitleElement(title_article_java);
        }

        assertEquals(
                "Article is not about '" + title_article_java + "'",
                articlePageObject.getArticleTitle(title_article_java),
                title_article_java
        );
    }
}
