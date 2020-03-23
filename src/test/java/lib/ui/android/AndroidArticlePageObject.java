package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
            TITLE_TPL = "id:org.wikipedia:id/view_page_title_text";
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
            OPTIONS_BUTTON_BY_XPATH = "xpath://android.widget.ImageView[@content-desc='More options']";
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
            ADD_TO_MY_LIST_OVERLAY_BY_ID = "id:org.wikipedia:id/onboarding_button";
            MY_NAME_LIST_TPL_BY_XPATH = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{FOLDER_NAME}']";
            MY_NAME_LIST_INPUT_BY_ID = "id:org.wikipedia:id/text_input";
            MY_LIST_OK_BUTTON_BY_XPATH = "xpath://*[@text='OK']";
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
