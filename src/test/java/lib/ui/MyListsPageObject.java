package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;


abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL_BY_XPATH,
            ARTICLE_BY_TITLE_TPL_BY_XPATH,
            ARTICLE_BY_TITLE_TPL_BY_ID,
            LIST_WITH_ALL_ARTICLES;


    /* TEMPLATES METHODS */
    private static String getFolderByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL_BY_XPATH.replace("{NAME_FOLDER}",name_of_folder);
    }

    private static String getTitleOfSaveArticleByXpath(String article_title){
        return ARTICLE_BY_TITLE_TPL_BY_XPATH.replace("{TITLE_TPL}", article_title);

    }
    /* TEMPLATES METHODS */


    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    // Открыть папку со статьями, для Андроид
    public void openFolderByName(String name_of_folder){
        String folder_name = getFolderByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name,
                "Cannot find folder by name '" + name_of_folder + "'.",
                5);
    }

    // Ожидание прогрузки всех папок, для Андроид
    public void waitForListPresent(String name_of_folder){
        String folder_name = getFolderByName(name_of_folder);
        this.waitForElementPresent(
                folder_name,
                "Cannot find folder with name '" +name_of_folder+ "'.",
                10);
    }

    // Ожидание полной загрузки статьи в папке (в списке) и проверка на присутствие статьи в списке
    public void waitForArticleToAppearByTitleByXpath(String article_title){
        String title = getTitleOfSaveArticleByXpath(article_title);
        this.waitForElementPresent(
                title,
                "Save article with title " + article_title + "' can not find.",
                15);
    }

    // Ожидание полной загрузки статьи в папке (в списке) и проверка на отсутствие статьи в списке
    public void waitForArticleToDisappearByTitleByXpath(String article_title){
        String title = getTitleOfSaveArticleByXpath(article_title);
        this.waitForElementNotPresent(
                title,
                "Save article still present with title " + article_title + "'.",
                15);
    }

    //Свайп статьи в списке влево и удаление ее
    public void swipeByArticleToDelete(String article_title){
        String title_by_xpath = getTitleOfSaveArticleByXpath(article_title);
        this.waitForArticleToAppearByTitleByXpath(article_title);

        if(Platform.getInstance().isAndroid()){
            this.swipeElementToLeft(
                    title_by_xpath,
                    "Article with title '" + article_title + "' can not find.");
        } else {
            this.swipeElementToLeft(
                    title_by_xpath + "/..",
                    "Article with title '" + article_title + "' can not find.");
            this.clickElementToTheRightSideIOS(title_by_xpath + "/..",
                    "Cannot find and click by right side element");
        }
        this.waitForArticleToDisappearByTitleByXpath(article_title);
    }

    // Клик по статье в списке с определенным названием
    public void clickByArticleWithTitle(String title){
        String saving_title = getTitleOfSaveArticleByXpath(title);
        this.waitForElementAndClick(
                saving_title,
                "Title with name '" + title + "' cannot find and clicked",
                5);
    }

}

