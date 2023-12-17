package cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.LoginPage;
import properties.PropertyReader;

import scenariocontext.ScenarioContext;
import selenium.utils.PagePath;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static pageobjects.LoginPage.backToLoginButton;
import static pageobjects.LoginPage.bookSharingLogo;
import static pageobjects.LoginPage.endavaLogo;
import static pageobjects.LoginPage.errorrMesage;
import static pageobjects.LoginPage.forgotPasswordHyperLink;
import static pageobjects.LoginPage.getCurrentUrl;
import static pageobjects.LoginPage.getErrorMessage;
import static pageobjects.LoginPage.popUpErrorMessage;
import static pageobjects.LoginPage.popUpSuccesLogin;
import static properties.PropertyKey.APP_URL;
import static scenariocontext.ScenarioContextKey.PASSWORD_MYPROFILE;
import static scenariocontext.ScenarioContextKey.USERNAME_MYPROFILE;
import static selenium.utils.BrowserFactory.getDriver;
import static selenium.utils.WaitUtils.waiter;

public class LoginUiSteps {
    Logger log = LogManager.getLogger(LoginUiSteps.class.getName());
    String appUrl = PropertyReader.getProperty(APP_URL);
    ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @Then("Endava's Logo is displayed on the left side of the Login page")
    public void endavaSLogoIsDisplayedOnTheLeftSideOfTheLoginPage() {
        waiter.until(ExpectedConditions.visibilityOf(endavaLogo));
        assertThat(endavaLogo.isDisplayed(), equalTo(true));
    }

    @And("The error message is displayed")
    public void theErrorMessageIsDisplayed() {
        waiter.until(ExpectedConditions.visibilityOf(popUpErrorMessage));
        String message = getErrorMessage();
        assertThat(message, containsString(errorrMesage));
    }

    @Given("user logs in with {string} and {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
        getDriver().get(appUrl);
        scenarioContext.saveData(PASSWORD_MYPROFILE, password);
        scenarioContext.saveData(USERNAME_MYPROFILE, username);
        LoginPage LoginPage = new LoginPage(getDriver());
        if (username.isEmpty()) {
            LoginPage.loginToBookSharing(" ", password);
        } else if (password.isEmpty()) {
            LoginPage.loginToBookSharing(username, " ");
        } else
            LoginPage.loginToBookSharing(username, password);
    }

    @When("User access Login page")
    public void userAccessLoginPage() {
        getDriver().get(appUrl);
        LoginPage loginPage = new LoginPage(getDriver());
        log.info("User access login  page");
    }

    @And("Project Name is displayed on the left side of the Login page")
    public void projectNameIsDisplayedOnTheLeftSideOfTheLoginPage() {
        waiter.until(ExpectedConditions.visibilityOf(bookSharingLogo));
        assertThat(bookSharingLogo.isDisplayed(), equalTo(true));
    }

    @When("user clicks Forgot Password hyperlink")
    public void userClicksForgotPasswordHyperlink() {
        getDriver().get(appUrl);
        LoginPage LoginPage = new LoginPage(getDriver());
        LoginPage.clickWebElement(forgotPasswordHyperLink);
    }

    @Then("^The user is(| not) redirected to the (.*) page$")
    public void theUserIsNotRedirectedToThePage(String isRedirected, PagePath path) {
        if (isRedirected.contains("not")) {
            assertThat("Url does not match ", getCurrentUrl(), not(path.getPath()));
        } else {
            waiter.until(ExpectedConditions.visibilityOf(path.equals(PagePath.FORGOT_PASSWORD_PAGE) ? backToLoginButton : popUpSuccesLogin));
            assertThat("User is not redirected " + getCurrentUrl(), getCurrentUrl(), equalTo(appUrl + path.getPath()));
        }
    }
}