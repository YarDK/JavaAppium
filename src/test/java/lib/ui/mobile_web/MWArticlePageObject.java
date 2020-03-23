package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE_TPL = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        //OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@title='Add this page to your watchlist']";
        //OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@title='Watch']";//#page-actions-watch
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@id='ca-watch']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://*[@title='Remove this page from your watchlist']";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
