package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;

import static org.apache.commons.lang3.ArrayUtils.toArray;

abstract public class SearchPageObject extends MainPageObject {

     protected static String
             SEARCH_FIELD,
             INITIALIZED_SEARCH_FIELD,
             SEARCH_CLOSE_BUTTON,
             SEARCH_MINI_CLOSE_BUTTON,
             SEARCH_RESULT_LIST,
             SEARCH_RESULT_BY_SUBSTRING_TPL,
             SEARCH_RESULT_BY_TITLE_TPL,
             SEARCH_RESULT_ELEMENT,
             SEARCH_RESULT_ELEMENT_TITLE,
            SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }


    /* TEMPLATES METHODS */
    private static String getResultSearchElementBySubstring(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getResultSearchElementByTitle(String title){
        return SEARCH_RESULT_BY_TITLE_TPL.replace("{TITLE_TPL}",title);
    }
    /* TEMPLATES METHODS */


    // Ожидание появления кнопки Закрыть
    public void waitForCloseButtonToAppear(){
        this.waitForElementPresent(
                SEARCH_CLOSE_BUTTON,
                "Element 'SEARCH_CLOSE_BUTTON' can not find",
                5
        );
    }


    // Проверка отсутствия кнопки Закрыть
    public void waitForCloseButtonToDisappear(){
        this.waitForElementNotPresent(
                SEARCH_CLOSE_BUTTON,
                "Element 'SEARCH_CLOSE_BUTTON' stile present",
                5
        );
    }


    // Нажатие по кнопке Закрыть
    public void clickByCloseButton(){
        this.waitForElementAndClick(
                SEARCH_CLOSE_BUTTON,
                "Element 'SEARCH_CLOSE_BUTTON' can not find and click",
                5
        );
    }


    // Иницициализация поискового окна (сделать активным)
    public void initSearchInput(){
        this.waitForElementAndClick(
                SEARCH_FIELD,
                "Element 'SEARCH_FIELD'can not find or clicked",
                10
        );

        this.waitForElementPresent(
                INITIALIZED_SEARCH_FIELD,
                "Element 'INITIALIZED_SEARCH_FIELD' can not find",
                10
        );
    }


    // Ввод ключевого слова в строку поиска
    public void typeSearchLine(String search_line){
        if(Platform.getInstance().isIOS()){
            driver.getKeyboard();
        }

        this.waitForElementAndSetValue(
                INITIALIZED_SEARCH_FIELD,
                search_line,
                "Element 'INITIALIZED_SEARCH_FIELD'can not find",
                10
        );
    }


    // Отчистка поля ввода, для iOS
    public void clearSearchField(){
        this.waitForElementAndClick(
                SEARCH_MINI_CLOSE_BUTTON,
                "Cannot find element 'SEARCH_MINI_CLOSE_BUTTON'",
                10
        );
    }


    // Ожидание появления результата поиска с определенным Описанием
    public void waitForSearchResult(String substring){
        String search_result = getResultSearchElementBySubstring(substring);

        this.waitForElementPresent(
                search_result,
                "Result cannot find with substring '" + substring + "'"
        );
    }


    // Клик по статье с определенным Описанием
    public void clickByArticleWithSubstring(String substring){
        String search_result = getResultSearchElementBySubstring(substring);

        this.waitForElementAndClick(
                search_result,
                "Result cannot find and click with substring '" + substring +
                                "'. Incoming element '"+search_result+"'",
                5
        );
    }

    // Получение числа суммы найденных статей на странице
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by thw request.",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }


    // Ожидание появления лейбла "Результатов не найдено"
    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "cannot found element 'SEARCH_EMPTY_RESULT_ELEMENT'");
    }


    // Сообщить, если не найдено результатов
    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Found some result");
    }


    // Получить плейсхолдер инициализированного поискового поля, для Андроида
    public String getPlaceholderSearchField(){
        WebElement element_search_field = this.waitForElementPresent(
                INITIALIZED_SEARCH_FIELD,
                "Element 'SEARCH_FIELD'can not find",
                5
        );

        return element_search_field.getAttribute("text");
    }


    // Ожидание статьи с определенной парой Название и Описание
    public void waitForElementByTitleAndDescription(String title, String description){
        String search_result_description_by_xpath = getResultSearchElementBySubstring(description);
        String search_result_title_by_xpath = getResultSearchElementByTitle(title);
        this.waitForElementPresent(SEARCH_RESULT_LIST,"Element 'SEARCH_RESULT_LIST' not found");
        this.assertTwoElementNotPresent(
                search_result_description_by_xpath,
                search_result_title_by_xpath,
                "Cannot find result with title '"+title+"' and description '"+description+"'.");
    }


    // Взять имена заданного количества статей
    public Object[] getNameArticles(int amount_article){
        this.waitForElementPresent(
                SEARCH_RESULT_LIST,
                "Element 'SEARCH_RESULT_LIST' not found",
                10
        );

        if(Platform.getInstance().isIOS()) {
            return this.getArticlesOnListSetNumberIOS(SEARCH_RESULT_ELEMENT, amount_article).toArray();
        } else {
            return this.getArticlesOnListSetNumberAndroid(SEARCH_RESULT_ELEMENT_TITLE, amount_article).toArray();
        }
    }


    // Верификация статьи на наличие ключевого слова
    public boolean verificationFirstThreeArticles(Object article, String contain_text){
        return article.toString().contains(contain_text);
    }

}
