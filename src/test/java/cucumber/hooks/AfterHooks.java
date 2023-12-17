package cucumber.hooks;

import io.cucumber.java.After;
import org.apache.logging.log4j.ThreadContext;
import scenariocontext.ScenarioContext;

public class AfterHooks {

    ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @After
    public void afterScenario() {
        ThreadContext.clearAll();
        scenarioContext.clearContext();
    }
}