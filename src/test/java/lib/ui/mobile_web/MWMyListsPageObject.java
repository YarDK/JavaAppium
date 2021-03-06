package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        STAR_DELETE_BY_TITLE_TPL = "xpath://*[@title='{TITLE_TPL}']/a[contains(@class, 'watch-this-article watched')]";
        ARTICLE_BY_TITLE_TPL_BY_XPATH = "xpath://*[@title='{TITLE_TPL}']";
    }

    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
