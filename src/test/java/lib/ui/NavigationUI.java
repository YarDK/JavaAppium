package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }

    // asdasd
    public void openNavigation()
    {
        if(Platform.getInstance().isMW()){
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    // Клик на глваной странице по кнопке в тапБаре "Мой список"
    public void clickMyList(){
        if(Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttemps(
                    MY_LISTS_LINK,
                    "Element 'MY_LISTS_LINK' can not find.",
                    5);
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Element 'MY_LISTS_LINK' can not find.",
                    5);
        }
    }
}
