package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_FIELD = "css:button#searchIcon";
        INITIALIZED_SEARCH_FIELD = "css:form>input[type='search']";
        SEARCH_CLOSE_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_LIST = "xpath://div[@class='results']";
        SEARCH_RESULT_ELEMENT_TITLE = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-result";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}

//div[contains(@class, 'wikidata-description')][contains(text(),'General-purpose, high-level programming language')]