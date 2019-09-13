package Lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AutorisationPageObject extends MainPageObject {
    public static final String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AutorisationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElement(LOGIN_BUTTON, "Cannot find login button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find login button", 5);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot send value to login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot send value to password input", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot click to submit button", 5);
    }
}
