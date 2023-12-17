package cucumber.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import lombok.extern.log4j.Log4j2;

import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.LoginPage;
import scenariocontext.NoKeyException;
import scenariocontext.ScenarioContext;
import pageobjects.MyProfilePage;

import static pageobjects.MyProfilePage.clickClearSendKeys;
import static scenariocontext.ScenarioContextKey.PASSWORD_MYPROFILE;
import static scenariocontext.ScenarioContextKey.USERNAMEPERSONALPAGE;
import static scenariocontext.ScenarioContextKey.USERNAME_MYPROFILE;
import static selenium.ActionsSelenium.takeScreenshot;
import static selenium.utils.BrowserFactory.getDriver;
import static selenium.utils.BrowserFactory.quitDriver;
import static selenium.utils.BrowserFactory.setDriver;
import static selenium.utils.WaitUtils.waiter;

@Log4j2
public class UIHooks {

    ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @Before("@UI")
    public void browserInitialisation() {
        setDriver();
        log.info("Driver is set.");
    }

    @After(value = "@UI", order = 2)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot();
        }
    }

    @After(value = "@UI", order = 1)
    public void tearDown() {
        quitDriver();
        log.info("Driver is quit.");
    }

    @After("@PersonalPageLogin")
    public void afterPersonalPage() throws NoKeyException {
        String userName = scenarioContext.getData(USERNAMEPERSONALPAGE);
        LoginPage loginPage = new LoginPage(getDriver());
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        personalPageUserActivity.getDropdownUsername().click();
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getLogoutElementOFDropdown()));
        personalPageUserActivity.getLogoutElementOFDropdown().click();
        loginPage.loginToBookSharing(userName, scenarioContext.getData(PASSWORD_MYPROFILE));
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getPopUp()));
        waiter.until(ExpectedConditions.invisibilityOf(personalPageUserActivity.getPopUp()));
        personalPageUserActivity.getDropdownUsername().click();
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getMyProfileValueDropdownUsername()));
        personalPageUserActivity.getMyProfileValueDropdownUsername().click();
        clickClearSendKeys(personalPageUserActivity.getUserNamePersonalPage(), scenarioContext.getData(USERNAME_MYPROFILE));
        personalPageUserActivity.getSaveChangesButtonPersonalPage().click();
        log.info("User was reset successfully");
    }
}