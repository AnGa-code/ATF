package cucumber.steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import managers.DateSubstitution;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MyProfilePage;
import properties.PropertyReader;
import scenariocontext.NoKeyException;
import scenariocontext.ScenarioContext;
import scenariocontext.ScenarioContextKey;
import selenium.utils.PagePath;


import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import static pageobjects.MyProfilePage.clickClearSendKeys;
import static selenium.utils.WaitUtils.waiter;
import static selenium.utils.WebElementsUtils.findWebElementFromPageObjectClass;
import static properties.PropertyKey.APP_URL;
import static selenium.utils.BrowserFactory.getDriver;

@Log4j2
public class GenericSteps {

    String appUrl = PropertyReader.getProperty(APP_URL);
    ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @ParameterType(".*")
    public PagePath path(String path) {
        for (PagePath element : PagePath.values()) {
            if (element.name().equals(path)) {
                return element;
            }
        }
        throw new RuntimeException("The entered path does not exist.");
    }

    @Given("{path} page is accessed")
    public void pageIsAccessed(PagePath path) {
        getDriver().get(appUrl + path.getPath());
    }

    @And("the current url contains {path} keyword")
    public void theCurrentUrlContainsKeyword(PagePath path) {
        String currentUrl = getDriver().getCurrentUrl();
        boolean currentUrlContainsKeyWord = currentUrl.contains(path.getPath());
        Assertions.assertTrue(currentUrlContainsKeyWord, "The keyword " + currentUrlContainsKeyWord + " is " + currentUrl);
    }

    @When("the following form from {string} is populated as follows:")
    public void theFollowingFormFromIsPopulatedAsFollows(String pageName, Map<String, String> fieldsAndValuesMap) {
        fieldsAndValuesMap.forEach((fieldName, fieldValue) -> {
            try {
                fieldValue = DateSubstitution.substituteString(fieldValue);
            } catch (NoKeyException e) {
                throw new RuntimeException(e);
            }
            WebElement inputElement = findWebElementFromPageObjectClass(fieldName, pageName);
            waiter.until(ExpectedConditions.elementToBeClickable(inputElement));
            if (pageName.equals("MyProfilePage")) {
                clickClearSendKeys(inputElement, fieldValue);
                for (ScenarioContextKey key : ScenarioContextKey.values()) {
                    if (key.name().equals(fieldName.toUpperCase())) {
                        scenarioContext.saveData(key, fieldValue);
                    }
                }
            } else {
                inputElement.sendKeys(fieldValue);
            }
            log.info("The [" + fieldName + "] is populated with [" + fieldValue + "]");
        });
    }

    @And("the {string} from {string} is clicked")
    public void theButtonFromIsClicked(String buttonName, String pageName) {
        try {
            WebElement elementToBeClicked = findWebElementFromPageObjectClass(buttonName, pageName);
            new WebDriverWait(getDriver(), Duration.of(2, ChronoUnit.SECONDS)).until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
            elementToBeClicked.click();
            log.info("The element " + buttonName + " is clicked");
        } catch (Exception e) {
            log.error("Error clicking element: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("the following validationMessage is displayed:")
    public void theFollowingMessageIsDisplayed(List<String> validationMessagesList) {
        validationMessagesList.forEach(validationMessage -> {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBe(By.xpath("//*[contains(text()," + validationMessage + ")]"), validationMessage.replace("\"", "")));
            boolean validationMessageIsDisplayed = getDriver().findElement(By.xpath("//*[contains(text()," + validationMessage + ")]")).isDisplayed();
            Assertions.assertTrue(validationMessageIsDisplayed, "The validation message:" + validationMessage + "is displayed");
        });
    }

    @Then("the following elements from {string} are displayed")
    public void theFollowingElementsFromAreDisplayed(String pageName, List<String> elementsList) {
        elementsList.forEach((elementName) -> {
            try {
                WebElement element = findWebElementFromPageObjectClass(elementName, pageName);
                waiter.until(ExpectedConditions.visibilityOf(element));

                Assertions.assertTrue(element.isDisplayed(), "Element '" + elementName + "' is displayed");
            } catch (Exception e) {
                log.error("Error checking visibility of element: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }
}