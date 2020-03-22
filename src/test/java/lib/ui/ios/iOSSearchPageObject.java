package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_FIELD = "id:Search Wikipedia";
        INITIALIZED_SEARCH_FIELD = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeSearchField";
        SEARCH_CLOSE_BUTTON = "id:Close";
        SEARCH_MINI_CLOSE_BUTTON = "id:clear mini";
        SEARCH_RESULT_LIST = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "id:{SUBSTRING}";
        SEARCH_RESULT_BY_TITLE_TPL = "id:{TITLE_TPL}";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[{ELEMENT_INDEX}]//XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
    }

    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
