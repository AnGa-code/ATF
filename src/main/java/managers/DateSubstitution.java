package managers;

import scenariocontext.NoKeyException;
import scenariocontext.ScenarioContext;

import static managers.RandomDataGeneration.generateInvalidInputSpecialCharacters;
import static managers.RandomDataGeneration.generateRandomPassword;
import static scenariocontext.ScenarioContextKey.PASSWORD;
import static scenariocontext.ScenarioContextKey.USERNAME;

public class DateSubstitution {

    public static String substituteString(String value) throws NoKeyException {
        switch (value.toUpperCase()) {
            case "RANDOMFIRSTNAME":
                return RandomDataGeneration.generateFirstName(3, 30);
            case "RANDOMLASTNAME":
                return RandomDataGeneration.generateLastName(3, 30);
            case "RANDOMUSERNAME":
                String username = RandomDataGeneration.generateUserName(3, 30, false);
                ScenarioContext.getInstance().saveData(USERNAME, username);
            case "RANDOMEMAIL":
                return RandomDataGeneration.generateEmail();
            case "RANDOMPASSWORD":
                String password = generateRandomPassword(4);
                ScenarioContext.getInstance().saveData(PASSWORD, password);
                return password;
            case "REPEATPASSWORD":
                return ScenarioContext.getInstance().getData(PASSWORD);
            case "INVALIDSPECIALCHARS":
                return generateInvalidInputSpecialCharacters(8);
        }
        return value;
    }
}