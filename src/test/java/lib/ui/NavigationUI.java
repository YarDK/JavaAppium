package lib.ui;

import io.appium.java_client.AppiumDriver;


abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK;

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    // Клик на глваной странице по кнопке в тапБаре "Мой список"
    public void clickMyList(){
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Element 'MY_LISTS_LINK' can not find.",
                5
        );
    }
}
