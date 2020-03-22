package testsHomework;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex8RefactoringTemplate extends CoreTestCase {

    @Test
    public void testVerificationFirstThreeArticles()
    {
        /*
        Ex12*: Рефакторинг тестов
        Адаптировать по iOS тест на поиск и верификацию трех результатов выдачи поиска.
        */
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_text = "Java";
        searchPageObject.typeSearchLine(search_text);

        Object[] articles_names = searchPageObject.getNameArticles(3);

        assertTrue(
                "First result cannot contain " + search_text,
                searchPageObject.verificationFirstThreeArticles(articles_names[0], search_text)
        );

        assertTrue(
                "Second result cannot contain " + search_text,
                searchPageObject.verificationFirstThreeArticles(articles_names[1], search_text)
        );

        assertTrue(
                "Thirty result cannot contain " + search_text,
                searchPageObject.verificationFirstThreeArticles(articles_names[2], search_text)
        );
    }
}
