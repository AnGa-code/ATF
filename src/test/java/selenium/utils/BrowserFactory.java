package selenium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import properties.PropertyReader;

import static properties.PropertyKey.BROWSER_TYPE;
import static properties.PropertyKey.CHROMEDRIVER;
import static properties.PropertyKey.EDGEDRIVER;

public class BrowserFactory {

    private static WebDriver driver;
    static String chromeDriverPath = PropertyReader.getProperty(CHROMEDRIVER);
    static String edgeDriverPath = PropertyReader.getProperty(EDGEDRIVER);
    static String browser = PropertyReader.getProperty(BROWSER_TYPE);

    public BrowserFactory() {
    }

    public static void setDriver() {

            switch (browser.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
                    break;

                case "edge":
                    System.setProperty("webdriver.edge.driver", edgeDriverPath);
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    driver = new EdgeDriver(edgeOptions);
                    driver.manage().window().maximize();
                    break;
            }
    }

    public static void quitDriver(){
        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}