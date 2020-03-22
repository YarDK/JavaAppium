package testsHomework;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactroy;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex6AssertTitle extends CoreTestCase {
    /*
    Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
    Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
    Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
    */
    @Test
    public void testAssertTitle()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactroy.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.withoutWaitTitlePresent();
    }
}
