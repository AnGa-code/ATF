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
public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@aria-label='close']//*[name()='svg']")
    WebElement popUp;
    @FindBy(xpath = "//div[@class='sc-iwyWfK kljzgU']")
    WebElement usernameDropdown;
    @FindBy(xpath = "/html/body/div/div[1]/div/div/div[3]/div/div/a")
    WebElement myProfileLink;

    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/div[5]/a")
    WebElement uploadBooksTab;

    public void navigateToUploadPageFromHeader() {
        waiter.until(ExpectedConditions.visibilityOf(usernameDropdown)).click();
        waiter.until(ExpectedConditions.visibilityOf(myProfileLink)).click();
    }

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
}