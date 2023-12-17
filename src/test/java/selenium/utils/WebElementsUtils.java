package selenium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

import static selenium.utils.BrowserFactory.getDriver;

public class WebElementsUtils {
    private static final Logger logger = LogManager.getLogger(WebElementsUtils.class);
    public static WebElement findWebElementFromPageObjectClass(String elementName, String pageName) {
        try {
            Class<?> classInstance = Class.forName("pageobjects." + pageName);
            Field classField = classInstance.getDeclaredField(elementName);
            classField.setAccessible(true);
            return (WebElement) classField.get(classInstance.getDeclaredConstructor(WebDriver.class).newInstance(getDriver()));
        } catch (Exception e) {
            logger.error("Error finding web element: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}