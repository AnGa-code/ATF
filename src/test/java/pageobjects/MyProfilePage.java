package pageobjects;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v119.input.Input;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.Page;

import static org.openqa.selenium.Keys.BACK_SPACE;
import static org.openqa.selenium.Keys.CLEAR;
import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.DELETE;
import static org.openqa.selenium.Keys.chord;
import static properties.PropertyKey.APP_URL;
import static properties.PropertyReader.getProperty;
import static selenium.utils.BrowserFactory.getDriver;
import static selenium.utils.PagePath.MY_PROFILE_PAGE;
import static selenium.utils.WaitUtils.waiter;

@Getter
@Log4j2
public class MyProfilePage extends Page {

    @FindBy(xpath = "//*[@id='root']/div[1]/div/div/div[2]")
    private WebElement dropdownUsername;
    @FindBy(xpath = "//*[@id='root']/div[1]/div/div/div[3]/div/div/a")
    private WebElement myProfileValueDropdownUsername;
    @FindBy(xpath = "//*[@id='root']/div[3]/div")
    private WebElement popUp;
    @FindBy(xpath = "//*[@id='root']/div[2]/div[2]/div/form/div/div[2]/button")
    private WebElement saveChangesButtonPersonalPage;
    @FindBy(xpath = "//*[@id='firstName']")
    private WebElement firstNamePersonalPage;
    @FindBy(xpath = "//*[@id='lastName']")
    private WebElement lastNamePersonalPage;
    @FindBy(xpath = "//*[@id='userName']")
    private WebElement userNamePersonalPage;
    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailPersonalPage;
    @FindBy(xpath = "//*[@id='root']/div[1]/div/div/div[3]/div/div/p[2]")
    private WebElement logoutElementOFDropdown;
    @FindBy(xpath = "//*[@id='5']/div[1]/div[1]")
    private WebElement popupLogout;
    @FindBy(xpath = "//*[@id='root']/div[1]/div/a/img")
    private WebElement endavaLogoPersonalPage;
    @FindBy(xpath = "//*[@id='root']/div[1]/div/a/img")
    private WebElement catalogDropdawnPersonalPage;
    @FindBy(xpath = "//*[@id='root']/div[2]/div[2]/div/form/div/div[1]/div[1]/p")
    private WebElement emailErrorMessagePersonalPage;
    @FindBy(xpath = "//*[@id='root']/div[2]/div[2]/div/form/div/div[1]/div[1]/p[1]")
    private WebElement firstNameErrorMessagePersonalPage;
    @FindBy(xpath = "//*[@id='root']/div[2]/div[2]/div/form/div/div[1]/div[1]/p")
    private WebElement userNameErrorMessagePersonalPage;
    @FindBy(xpath = "//*[@id='root']/div[2]/div[2]/div/form/div/div[1]/div[2]/p")
    private WebElement lastNameErrorMessagePersonalPage;

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        String pageUrl = getProperty(APP_URL) + MY_PROFILE_PAGE.getPath();
        try {
            waiter.until(ExpectedConditions.urlContains(pageUrl));
        } catch (TimeoutException exception) {
            log.error("Profile page is not loaded. Current URL is " + getDriver().getCurrentUrl());
            return false;
        }
        return true;
    }

    public static String getFieldValue(WebElement element) {
        return element.getAttribute("value");
    }

    public static void clickClearSendKeys(WebElement webElement, String dataToSend) {
        waiter.until(ExpectedConditions.elementToBeClickable(webElement));
try {
    Thread.sleep(5000);
}catch
(Exception e){
    e.getMessage();
}
        webElement.click();
        webElement.sendKeys(dataToSend);
        String s = Keys.chord(CONTROL,"a");
        webElement.sendKeys(s);
        webElement.sendKeys(DELETE);
        webElement.sendKeys(dataToSend);
    }
}