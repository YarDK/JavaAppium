package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "xpath://div/a[text()='Log in']",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "css:button[name='wploginattempt']";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton()
    {
        // не работает метод waitForElementPresent
        this.waitingForElement(2000);
        this.waitForElementAndClick(
                LOGIN_BUTTON,
                "Cannot find and click auth button",
                5);
    }

    public void enterLoginData(String login, String password){
        this.waitingForElement(1000);
        this.waitForElementAndClear(
                LOGIN_INPUT,
                "Cannot find and clear a login is the login input.",
                5);
        this.waitingForElement(1000);
        this.waitForElementAndSendKeys(
                LOGIN_INPUT,
                login,
                "Cannot find and put a login is the login input.",
                5);
        this.waitingForElement(1000);
        this.waitForElementAndSendKeys(
                PASSWORD_INPUT,
                password,
                "Cannot find and put a password is the password input.",
                5);
    }

    public void submitForm(){
        this.waitForElementAndClick(
                SUBMIT_BUTTON,
                "Cannot find and click submit auth button.",
                5);
    }
}
