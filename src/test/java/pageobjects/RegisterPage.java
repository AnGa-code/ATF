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
import static selenium.utils.PagePath.SIGNUP_PAGE;
import static selenium.utils.WaitUtils.waiter;

@Log4j2
public class RegisterPage extends Page {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(text(),'New account has been successfully created.')]")
    private WebElement successMessage;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/section/div[2]/div/header")
    private WebElement signupHeader;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/section/div[1]/img")
    private WebElement EndavaLogo;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/section/div[1]/h1")
    private WebElement bookSharingTitle;
    @FindBy(xpath = "//input[@placeholder='First name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@placeholder='Last name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@placeholder='Enter username']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@placeholder='Enter email']")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/section/div[2]/div/form/div[4]/p")
    private WebElement emailLabel;

    @FindBy(xpath = "//input[@placeholder='Enter password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@placeholder='Repeat password']")
    private WebElement repeatPasswordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signUpButton;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    WebElement loginButton;

    @FindBy(xpath = "//p[contains(text(),'Should be from 3 to 30 letters or symbols(space, -')]//img[@alt='alert icon']")
    private WebElement errorMessage;

    @Override
    public boolean isLoaded() {
        String pageUrl = getProperty(APP_URL) + SIGNUP_PAGE.getPath();
        try {
            waiter.until(ExpectedConditions.urlContains(pageUrl));
        } catch (TimeoutException exception) {
            log.error("RegisterPage is not loaded. Current URL is " + getDriver().getCurrentUrl());
            return false;
        }
        return true;
    }
}
