package cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        stepNotifications = true,
        glue = {"cucumber.steps", "cucumber.hooks"},
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/cucumber_reports/cucumberReports.html"},
        tags = "@R"
)
public class TestRunner {

}