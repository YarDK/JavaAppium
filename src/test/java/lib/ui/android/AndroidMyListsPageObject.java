package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
            FOLDER_BY_NAME_TPL_BY_XPATH = "xpath://*[@text='{NAME_FOLDER}']";
            ARTICLE_BY_TITLE_TPL_BY_XPATH = "xpath://*[@text='{TITLE_TPL}']";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
