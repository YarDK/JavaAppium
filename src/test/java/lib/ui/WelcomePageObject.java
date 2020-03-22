package lib.ui;

import io.appium.java_client.AppiumDriver;

// Класс только для последовательного пропуска всех окон Велком онка для iOS
public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK_BY_ID = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT_BY_ID = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK_BY_ID = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK_BY_ID = "id:Learn more about data collected",
    NEXT_BUTTON_BY_ID = "id:Next",
    GET_STARTED_BUTTON_BY_ID = "id:Get started",
    SKIP_BUTTON = "id:Skip";

    public WelcomePageObject(AppiumDriver driver){
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK_BY_ID,"Cannot find 'Learn more about Wikipedia' link.", 10);
    }

    public void waitForNewWaysToExploreText(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT_BY_ID,"Cannot find 'New ways to explore' text.", 10);
    }

    public void waitForAddOrEditPreferredLanguagesLink(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK_BY_ID,"Cannot find 'Add or edit preferred languages' link.", 10);
    }

    public void waitForLearnMoreAboutDataCollectedLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK_BY_ID,"Cannot find 'Learn more about data collected' link.", 10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_BUTTON_BY_ID,"Cannot find and click 'Next' button.", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON_BY_ID,"Cannot find and click 'Get started' button.", 10);
    }

    public void clickSkip(){
        this.waitForElementAndClick(SKIP_BUTTON,"Skip button cannot find and click", 10);
    }

}
