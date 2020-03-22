package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL_BY_XPATH = "xpath://XCUIElementTypeLink[@name='{TITLE_TPL}']";
        ARTICLE_BY_TITLE_TPL_BY_ID = "id:{TITLE_TPL}";
        LIST_WITH_ALL_ARTICLES = "//XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView";
    }

    public iOSMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
