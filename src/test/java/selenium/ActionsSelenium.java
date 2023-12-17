package selenium;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static selenium.utils.BrowserFactory.getDriver;

public class ActionsSelenium {

    private static final Logger logger = LogManager.getLogger(ActionsSelenium.class);

    public static void takeScreenshot() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) getDriver();
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHSSS");
            String timestamp = dateFormat.format(new Date());
            String fileName = "target/screenshots/image_" + timestamp + ".png";
            File destinationFile = new File(fileName);
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            logger.error("Path destination of the folder is not found, please check the file location");
        }
    }

    public static void scrollVertical(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("window.scrollBy(0, arguments[0]);", element);
    }

    public static void scrollHorizontallyToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView({ inline: 'true' });", element);
    }

    public static void scrollToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToTop() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.scrollTo(0, 0)");
    }
    public static void dropdownHandlingVisibleText(WebElement dropdown, String visibleText) {

        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
}