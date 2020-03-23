package lib.ui;

import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE_TPL,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON_BY_XPATH,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY_BY_ID,
            MY_NAME_LIST_TPL_BY_XPATH,
            MY_NAME_LIST_INPUT_BY_ID,
            MY_LIST_OK_BUTTON_BY_XPATH,
            CLOSE_POPOVER_SYNC_SAVED_ARTICLE,
            CLOSE_ARTICLE_BUTTON;



    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }


    /* TEMPLATES METHODS */
    private static String getMyNameList(String name_of_folder){
        return MY_NAME_LIST_TPL_BY_XPATH.replace("{FOLDER_NAME}",name_of_folder);
    }

    private static String getTitleElement(String title){
        return TITLE_TPL.replace("{TITLE}", title);
    }
    /* TEMPLATES METHODS */

    // Закрыть всплывающее окно с предложения синхронизировать статьи
    public void clickByCloseButtonPopoverSyncSavedArticle(){
        this.waitForElementAndClick(
                CLOSE_POPOVER_SYNC_SAVED_ARTICLE,
                "Cannot find and click element 'CLOSE_POPOVER_SYNC_SAVED_ARTICLE'",
                10);
    }

    // Ожидание появления Названия статьи
    public WebElement waitForTitleElement(String title)
    {
        String title_name = getTitleElement(title);
        return this.waitForElementPresent(
                title_name,
                "Cannot find article title on page",
                20);
    }

    // Взять название статьи из элемента и вернуть его как строку
    public String getArticleTitle(String title){
        WebElement title_element = waitForTitleElement(title);
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    // Свайп от начала статья до конца, пока не появится заданный элемент
    public void swipeToFooter(int max_swipes){
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end article",
                    max_swipes);
        } else if(Platform.getInstance().isIOS()){
            this.swipeUpToFindElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end article",
                    max_swipes);
        } else {
            this.scrollVebPageTillElementNotPresent(
                    FOOTER_ELEMENT,
                    "Cannot find the end article",
                    max_swipes);
        }
    }

    // Добавление первой статья для Андроида (Скип всплывающего оверлея)
    public void addFirstArticleToMyList(String name_of_folder){

        this.waitForElementAndClick(
                OPTIONS_BUTTON_BY_XPATH,
                "'OPTIONS_BUTTON_BY_XPATH' cannot find and click",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "'OPTIONS_ADD_TO_MY_LIST_BUTTON' cannot find and click",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY_BY_ID,
                "'ADD_TO_MY_LIST_OVERLAY_BY_ID' cannot find and click (button 'Got it')",
                5);

        this.waitForElementAndClear(
                MY_NAME_LIST_INPUT_BY_ID,
                "'MY_NAME_LIST_INPUT_BY_ID' cannot find and clear",
                5);

        this.waitForElementAndSetValue(
                MY_NAME_LIST_INPUT_BY_ID,
                name_of_folder,
                "Cannot set value by 'MY_NAME_LIST_INPUT_BY_ID'",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON_BY_XPATH,
                "'MY_LIST_OK_BUTTON_BY_XPATH' cannot find and click",
                5);
    }

    // Добавление второй и последующих статей
    public void addSecondArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                OPTIONS_BUTTON_BY_XPATH,
                "'OPTIONS_BUTTON_BY_XPATH' cannot find and click",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "'OPTIONS_ADD_TO_MY_LIST_BUTTON' cannot find and click",
                5);

        String my_name_list = getMyNameList(name_of_folder);
        this.waitForElementAndClick(my_name_list,
                "Cannot find list with name '" + name_of_folder + "'.",
                5);
    }

    // Добавление статьи для iOS
    public void addArticleToMyListForIOS(){
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option button 'Add to list'",
                10);
    }

    // Добавление статьи для Веб
    public void addArticleToMyListForMW(){
        // Не работает "waitToElementPresent"
        this.waitingForElement(2000);
        //this.removeArticleFromSavedIfItAdded();
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option button 'Add to list'",
                10);
    }

    // Закрытие статьи
    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Element 'CLOSE_ARTICLE_BUTTON' (Navigate up 'X') can not find.",
                5);
    }

    // Удаление статьи, если она уже была добавлена ранее
    public void removeArticleFromSavedIfItAdded(){
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    2);
            this.waitForElementPresent(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before",
                    2);
        }
    }

    // Проверка наличия Названия статьи, не дожидаясь полной загрузки
    public void withoutWaitTitlePresent(){
        this.assertElementNotPresent(
                TITLE_TPL,
                "Title is not find without waiting");
    }
}
