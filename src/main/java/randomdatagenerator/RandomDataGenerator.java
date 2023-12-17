package randomdatagenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.util.Random;

public class RandomDataGenerator {

    private static final String NUMERIC_CHARACTERS = "0123456789";
    private static final String ALPHABETIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABETIC_CHARACTERS_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHABETIC_CHARACTERS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHANUMERIC_CHARACTERS_UPPER = NUMERIC_CHARACTERS + ALPHABETIC_CHARACTERS_UPPER_CASE;
    private static final String ALPHANUMERIC_CHARACTERS_LOWER = NUMERIC_CHARACTERS + ALPHABETIC_CHARACTERS_LOWER_CASE;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_=+[]{}|;:,.<>?/";
    private static final String ACCEPTED_SPECIAL_CHARACTERS = "-'";
    private static final String ALPHABETIC_CHARACTERS_LOWER_CASE_ACCEPTED_SPECIAL = ALPHABETIC_CHARACTERS_LOWER_CASE + ACCEPTED_SPECIAL_CHARACTERS;
    private static final String ALPHABETIC_CHARACTERS_UPPER_CASE_ACCEPTED_SPECIAL = ALPHABETIC_CHARACTERS_UPPER_CASE + ACCEPTED_SPECIAL_CHARACTERS;
    private static final String ALPHANUMERIC_CHARACTERS_MIXT = NUMERIC_CHARACTERS + ALPHABETIC_CHARACTERS + ACCEPTED_SPECIAL_CHARACTERS;
    private static final String ALPHANUMERIC_SPECIAL_CHARACTERS = ALPHANUMERIC_CHARACTERS_MIXT + SPECIAL_CHARACTERS;
    private static final String ALPHANUMERIC_SPECIAL_CHARACTERS_SPECIAL_ACCEPTED = ALPHANUMERIC_CHARACTERS_MIXT + SPECIAL_CHARACTERS;

    private static Random objectOfRandom = new Random();
    private static final Logger logger = LogManager.getLogger(RandomDataGenerator.class);

    public static String generateRandom(String typeOfData, int length) {
        String returnvariable = "";
        switch (typeOfData) {

            case "NUMERIC":
                returnvariable = RandomStringUtils.randomNumeric(length);
                break;

            case "ALPHABETIC":
                returnvariable = RandomStringUtils.randomAlphabetic(length);
                break;

            case "ALPHANUMERIC":
                returnvariable = RandomStringUtils.randomAlphanumeric(length);
                break;

            case "SPECIAL":
                returnvariable = RandomStringUtils.random(length, SPECIAL_CHARACTERS);
                break;

            case "ALPHANUMERICSPECIAL":
                returnvariable = RandomStringUtils.random(length, ALPHANUMERIC_SPECIAL_CHARACTERS_SPECIAL_ACCEPTED);
                break;

            case "EMAIL":
                returnvariable = generateRandomStringEmail(length, ALPHANUMERIC_CHARACTERS_MIXT);
                break;

            case "SPACE":
                returnvariable = " ";
                break;

            case "ALPHABETICLOWERCASE":
                returnvariable = RandomStringUtils.random(length, ALPHABETIC_CHARACTERS_LOWER_CASE_ACCEPTED_SPECIAL);
                break;

            case "ALPHABETICUPPERCASE":
                returnvariable = RandomStringUtils.random(length, ALPHABETIC_CHARACTERS_UPPER_CASE_ACCEPTED_SPECIAL);
                break;

            case "ALPHANUMERICUPPERCASE":
                returnvariable = RandomStringUtils.random(length, ALPHANUMERIC_CHARACTERS_UPPER + ACCEPTED_SPECIAL_CHARACTERS);
                break;

            case "ALPHANUMERICLOWERCASE":
                returnvariable = RandomStringUtils.random(length, ALPHANUMERIC_CHARACTERS_LOWER + ACCEPTED_SPECIAL_CHARACTERS);
                break;

            case "PASSWORD":
                returnvariable = generateRandomPassword(length);
        }
        return returnvariable;
    }

    private static String generateRandomStringEmail(int length, String characters) {
        StringBuilder stringBuilder = new StringBuilder(length);
        final String[] emailDomains = {"@gmail.com", "@yahoo.com", "@mail.ru","@outlook.com"};
        String email = emailDomains[objectOfRandom.nextInt(emailDomains.length)];
        String stringBeforeAdd = "";
        for (int i = 0; i < length; i++) {
            int randomIndex = objectOfRandom.nextInt(characters.length());
            stringBeforeAdd = (stringBuilder.append(characters.charAt(randomIndex))).toString();
        }
        return stringBeforeAdd + email;
    }

    public static String generateRandomPassword(int length) {

        if (length < 5) {
            logger.info("Password length must be at least 5");
        }
        String uppercase = RandomStringUtils.random(1, ALPHABETIC_CHARACTERS_UPPER_CASE);
        String lowercase = RandomStringUtils.random(1, ALPHABETIC_CHARACTERS_LOWER_CASE);
        String acceptedSpecialCharacters = RandomStringUtils.random(1,ACCEPTED_SPECIAL_CHARACTERS);
        String number = RandomStringUtils.randomNumeric(1);
        String specialCharacters = RandomStringUtils.random(1, SPECIAL_CHARACTERS);
        String passwordWithoutRequirements = RandomStringUtils.random(length - 5, ALPHANUMERIC_SPECIAL_CHARACTERS);

        return uppercase + lowercase + number + specialCharacters + passwordWithoutRequirements + acceptedSpecialCharacters;
    }
}