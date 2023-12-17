package pageobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static properties.PropertyKey.APP_URL;
import static properties.PropertyReader.getProperty;
import static selenium.utils.BrowserFactory.getDriver;
import static selenium.utils.WaitUtils.waiter;

@Log4j2
public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='1']/div[1]/div[2]")
    public static WebElement popUpSuccesLogin;
    @FindBy(xpath = "//*[@id='root']/div/div")
    public static WebElement popUpErrorMessage;
    @FindBy(xpath = "//*[@id='root']/section/div[1]/img")
    public static WebElement endavaLogo;
    @FindBy(xpath = "//*[@id='root']/section/div[1]/h1")
    public static WebElement bookSharingLogo;
    @FindBy(xpath = "//*[@id='root']/section/div[2]/div/form/a")
    public static WebElement forgotPasswordHyperLink;
    @FindBy(xpath = "//*[@id='root']/h1/a")
    public static WebElement backToLoginButton;
    @FindBy(xpath = "//*[@id=\"root\"]/section/div[2]/div/header/a")
    private WebElement signUpLink;
    @FindBy(xpath = "//input[@placeholder='Enter username']")
    WebElement usernameInput;
    @FindBy(xpath = "//input[@placeholder='Enter password']")
    WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;
    @FindBy(xpath = "//div[contains(text(),'Login successful')]")
    private WebElement successMessage;

    public static String errorrMesage = "Invalid email or password provided. Please double-check and try again.";

    @Override
    public boolean isLoaded() {
        String pageUrl = getProperty(APP_URL);
        try {
            waiter.until(ExpectedConditions.urlContains(pageUrl));
        } catch (TimeoutException exception) {
            log.error("HomePage is not loaded. Current URL is " + getDriver().getCurrentUrl());
            return false;
        }
        return true;
    }

    public void loginToBookSharing(String username, String password) {

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        loginButton.click();
    }

    public void clickWebElement(WebElement webElement) {
        webElement.click();
    }

    public static String getErrorMessage() {
        return popUpErrorMessage.getText();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}