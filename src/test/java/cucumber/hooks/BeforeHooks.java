package cucumber.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class BeforeHooks {

    private final Logger logger= LogManager.getLogger(BeforeHooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
        ThreadContext.put("scenarioName", scenarioName);
        logger.info("The scenario is: " + scenarioName);
    }
}