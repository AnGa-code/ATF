package scenariocontext;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static ScenarioContext instance;
    private Map<ScenarioContextKey, Object> contextMap;

    private ScenarioContext() {
        contextMap = new HashMap<>();
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public <T> T getData(ScenarioContextKey key) throws NoKeyException {
        if (contextMap.containsKey(key)) {
            return (T) contextMap.get(key);
        } else {
            throw new NoKeyException("The key is not found");
        }
    }

    public void saveData(ScenarioContextKey key, Object value) {
        contextMap.put(key, value);
    }

    public void clearContext() {
        contextMap.clear();
    }
}