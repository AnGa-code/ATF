package selenium.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import properties.PropertyReader;

import java.time.Duration;

import static properties.PropertyKey.DURATION_POLLING;
import static properties.PropertyKey.DURATION_TIMEOUT;
import static selenium.utils.BrowserFactory.getDriver;

public class WaitUtils {

    static String durationTimeout = PropertyReader.getProperty(DURATION_TIMEOUT);
    static String durationPoling = PropertyReader.getProperty(DURATION_POLLING);

    public static Wait<WebDriver> waiter = new FluentWait<>(getDriver())
            .withTimeout(Duration.ofSeconds(Long.parseLong(String.valueOf(durationTimeout))))
            .pollingEvery(Duration.ofSeconds(Integer.parseInt(String.valueOf(durationPoling))))
            .ignoring(NoSuchElementException.class);
}