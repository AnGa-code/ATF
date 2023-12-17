package cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.LoginPage;
import properties.PropertyReader;
import scenariocontext.NoKeyException;
import scenariocontext.ScenarioContext;
import pageobjects.MyProfilePage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static pageobjects.MyProfilePage.clickClearSendKeys;
import static properties.PropertyKey.APP_URL;

import static randomdatagenerator.RandomDataGenerator.generateRandom;
import static scenariocontext.ScenarioContextKey.EMAILPERSONALPAGE;
import static scenariocontext.ScenarioContextKey.FIRSTNAMEPERSONALPAGE;
import static scenariocontext.ScenarioContextKey.LASTNAMEPERSONALPAGE;
import static scenariocontext.ScenarioContextKey.PASSWORD_MYPROFILE;
import static scenariocontext.ScenarioContextKey.USERNAMEPERSONALPAGE;
import static pageobjects.MyProfilePage.getFieldValue;
import static selenium.utils.BrowserFactory.getDriver;
import static selenium.utils.WaitUtils.waiter;
import static selenium.utils.WebElementsUtils.findWebElementFromPageObjectClass;

public class PersonalPageSteps {

    ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private static final Logger logger = LogManager.getLogger(PersonalPageSteps.class);

    @When("user clicks on the dropdown Username")
    public void userClicksOnTheMyProfileButton() {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getPopUp()));
        waiter.until(ExpectedConditions.invisibilityOf(personalPageUserActivity.getPopUp()));
        personalPageUserActivity.getDropdownUsername().click();
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getMyProfileValueDropdownUsername()));
        personalPageUserActivity.getMyProfileValueDropdownUsername().click();
    }

    @Given("user access my profile page")
    public void userAccessMyProfilePage() {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getPopUp()));
        waiter.until(ExpectedConditions.invisibilityOf(personalPageUserActivity.getPopUp()));
        waiter.until(ExpectedConditions.elementToBeClickable(personalPageUserActivity.getDropdownUsername()));
        personalPageUserActivity.getDropdownUsername().click();
        waiter.until(ExpectedConditions.elementToBeClickable(personalPageUserActivity.getMyProfileValueDropdownUsername()));
        personalPageUserActivity.getMyProfileValueDropdownUsername().click();
    }

//    @When("user populates the firstname field with {string}")
//    public void userPopulatesFirstNameFieldWithValidData(String validData) {
//        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
//        WebElement firstNameField = personalPageUserActivity.getFirstNamePersonalPage();
//        scenarioContext.saveData(FIRSTNAME_MYPROFILE, validData);
//        personalPageUserActivity.clickClearSendKeys(firstNameField, validData);
//    }

//    @And("click on the saveChanges button")
//    public void clickOnTheSaveChangesButton() {
//        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
//        personalPageUserActivity.getSaveChangesButtonPersonalPage().click();
//    }

//    @And("user populates the Lastname field with {string}")
//    public void userPopulatesLastnameFieldWithPanus(String validData) {
//        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
//        WebElement lastNameField = personalPageUserActivity.getLastNamePersonalPage();
//        scenarioContext.saveData(LASTNAME_MYPROFILE, validData);
//        personalPageUserActivity.clickClearSendKeys(lastNameField, validData);
//    }
//
//    @And("user populates the Username field with {string}")
//    public void userPopulatesUsernameFieldWithValidData(String validData) {
//        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
//        WebElement lastNameField = personalPageUserActivity.getUserNamePersonalPage();
//        scenarioContext.saveData(USERNAME_MYPROFILE, validData);
//        personalPageUserActivity.clickClearSendKeys(lastNameField, validData);
//    }

//    @And("user populates the Email field with {string}")
//    public void userPopulatesEmailFieldWithValidData(String validData) {
//        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
//        WebElement lastNameField = personalPageUserActivity.getEmailPersonalPage();
//        scenarioContext.saveData(EMAIL_MYPROFILE, validData);
//        personalPageUserActivity.clickClearSendKeys(lastNameField, validData);
//    }

    @Then("all the changes are saved")
    public void allTheChangesAreSaved() throws NoKeyException {
        MyProfilePage myProfilePage = new MyProfilePage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        myProfilePage.getDropdownUsername().click();
        String userName = scenarioContext.getData(USERNAMEPERSONALPAGE);
        String password = scenarioContext.getData(PASSWORD_MYPROFILE);
        waiter.until(ExpectedConditions.visibilityOf(myProfilePage.getLogoutElementOFDropdown()));
        myProfilePage.getLogoutElementOFDropdown().click();
        loginPage.loginToBookSharing(userName, password);
        waiter.until(ExpectedConditions.visibilityOf(myProfilePage.getPopUp()));
        waiter.until(ExpectedConditions.invisibilityOf(myProfilePage.getPopUp()));
        myProfilePage.getDropdownUsername().click();
        waiter.until(ExpectedConditions.visibilityOf(myProfilePage.getMyProfileValueDropdownUsername()));
        myProfilePage.getMyProfileValueDropdownUsername().click();
        assertThat(scenarioContext.getData(FIRSTNAMEPERSONALPAGE), equalTo(getFieldValue(myProfilePage.getFirstNamePersonalPage())));
        assertThat(scenarioContext.getData(LASTNAMEPERSONALPAGE), equalTo(getFieldValue(myProfilePage.getLastNamePersonalPage())));
        assertThat(scenarioContext.getData(EMAILPERSONALPAGE), equalTo(getFieldValue(myProfilePage.getEmailPersonalPage())));
        assertThat(userName, equalTo(getFieldValue(myProfilePage.getUserNamePersonalPage())));
    }

    @When("user populates {string} field with {string} data type and {int} from {string} page")
    public void userPopulatesFields(String fieldName, String datatype, int length, String pageName) {
        WebElement inputElement = findWebElementFromPageObjectClass(fieldName, pageName);
        if (datatype.equals("SPACE")) {
            clickClearSendKeys(inputElement, " ");
        } else {
            clickClearSendKeys(inputElement, generateRandom(datatype, length));
        }
        logger.info("{} field is populated", fieldName);
    }

//    @And("user populates username field with {string} data type and {int}")
//    public void userPopulatesUsernameFieldWithFieldWithLength(String datatype, int length) {
//        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
//        WebElement userNameField = personalPageUserActivity.getUserNamePersonalPage();
//        String username = generateRandom(datatype, length);
//        clickClearSendKeys(userNameField, username);
//        logger.info(username);
//    }


    @When("^user populates email field with a (.*)$")
    public void userPopulatesEmailFieldWithAWrongFormatOrExisting(String data) {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        WebElement emailField = personalPageUserActivity.getEmailPersonalPage();
        int length = 10;
        if (data.equals("andreipana9@gmail.com")) {
            clickClearSendKeys(personalPageUserActivity.getEmailPersonalPage(), "andreipana8@gmail.com");
        } else {
            clickClearSendKeys(emailField, generateRandom(data, length));
        }
    }

//    @When("user populates user name field with an existing username {string}")
//    public void userPopulatesUserNameFieldWithAnExistingUsernameAbPana(String username) {
//        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
//        WebElement userNameField = personalPageUserActivity.getUserNamePersonalPage();
//        clickClearSendKeys(userNameField, username);
//    }

//    @When("user populates lastname field with {string} data type and {int}")
//    public void userPopulatesLastnameFieldWithFieldWithLength(String datatype, int length) {
//        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
//        WebElement lastNameField = personalPageUserActivity.getLastNamePersonalPage();
//        clickClearSendKeys(lastNameField, generateRandom(datatype, length));
//    }

    @Then("all ui elements are displayed on profile page")
    public void allUiElementsAreDisplayedOnProfilePage() {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getEndavaLogoPersonalPage()));
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getUserNamePersonalPage()));
        assertThat(personalPageUserActivity.getUserNamePersonalPage().isDisplayed(), equalTo(true));
        assertThat(personalPageUserActivity.getEndavaLogoPersonalPage().isDisplayed(), equalTo(true));
        assertThat(personalPageUserActivity.getCatalogDropdawnPersonalPage().isDisplayed(), equalTo(true));
        assertThat(personalPageUserActivity.getUserNamePersonalPage().isDisplayed(), equalTo(true));
        assertThat(personalPageUserActivity.getEmailPersonalPage().isDisplayed(), equalTo(true));
        assertThat(personalPageUserActivity.getLastNamePersonalPage().isDisplayed(), equalTo(true));
        assertThat(personalPageUserActivity.getFirstNamePersonalPage().isDisplayed(), equalTo(true));
    }

    @Then("changes are not saved")
    public void changesAreNotSaved() {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        assertFalse("Element is enabled ", personalPageUserActivity.getSaveChangesButtonPersonalPage().isEnabled());
    }

    @And("error message for email field is displayed")
    public void errorMessageIsDisplayed() {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getEmailErrorMessagePersonalPage()));
        assertThat(personalPageUserActivity.getEmailErrorMessagePersonalPage().isDisplayed(), equalTo(true));
    }

    @And("error message for username is displayed")
    public void errorMessageForUsernameIsDisplayed() {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getUserNameErrorMessagePersonalPage()));
        assertThat(personalPageUserActivity.getUserNameErrorMessagePersonalPage().isDisplayed(), equalTo(true));
    }

    @And("error message for lastname is displayed")
    public void errorMessageForLastnameIsDisplayed() {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        assertThat(personalPageUserActivity.getLastNameErrorMessagePersonalPage().isDisplayed(), equalTo(true));
    }

    @And("error message for first name is displayed")
    public void errorMessageForFirstNameIsDisplayed() {
        MyProfilePage personalPageUserActivity = new MyProfilePage(getDriver());
        waiter.until(ExpectedConditions.visibilityOf(personalPageUserActivity.getFirstNameErrorMessagePersonalPage()));
        assertThat(personalPageUserActivity.getFirstNameErrorMessagePersonalPage().isDisplayed(), equalTo(true));
    }
}