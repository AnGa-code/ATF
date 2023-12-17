package managers;

import com.github.javafaker.Faker;
import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.util.Random;

public class RandomDataGeneration {
    private static final String NUMERIC_CHARACTERS = "0123456789";
    private static final String ALPHABETIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALPHANUMERIC_CHARACTERS_MIXT = NUMERIC_CHARACTERS + ALPHABETIC_CHARACTERS;
    private static final String ALPHANUMERIC_CHARACTERS_UPPER = NUMERIC_CHARACTERS + ALPHABETIC_CHARACTERS.toUpperCase();
    private static final String ALPHANUMERIC_CHARACTERS_LOWER = NUMERIC_CHARACTERS + ALPHABETIC_CHARACTERS.toLowerCase();
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*|?";
    private static final String ALPHANUMERIC_SPECIAL_CHARACTERS = ALPHANUMERIC_CHARACTERS_MIXT + SPECIAL_CHARACTERS;
    private static Random objectOfRandom = new Random();
    private static Faker fakerObject = new Faker();

    public static String generateEmail(String prefix, String suffix) {
        String randomMidPart = String.valueOf(fakerObject.random().nextInt(1, 10000));
        return prefix + randomMidPart + suffix;
    }

    public static String generateEmail() {
        return fakerObject.internet().emailAddress();
    }

    public static String generateFirstName(int minLength, int maxLength) {
        String firstName = fakerObject.name().firstName();
        while (firstName.length() < minLength && firstName.length() > maxLength) {
            firstName = fakerObject.name().firstName();
        }
        return firstName;
    }

    public static String generateInvalidFirstName(int minLength) {
        String invalidFirstName = fakerObject.name().firstName();
        while (invalidFirstName.length() < minLength) {
            invalidFirstName = fakerObject.name().firstName();
        }
        return invalidFirstName;
    }

    public static String generateLastName(int minLength, int maxLength) {
        String lastName = fakerObject.name().lastName();
        while (lastName.length() < minLength && lastName.length() > maxLength) {
            lastName = fakerObject.name().firstName();
        }
        return lastName;
    }

    public static String generateUserName(int minLength, int maxLength, boolean includeSpecialSymbol) {
        String userName = fakerObject.name().username();
        while (userName.length() < minLength && userName.length() > maxLength) {
            userName = fakerObject.name().username();
        }
        return userName;
    }

    public static String generatePassword(int minLength, int maxLength, boolean includeUpperCase, boolean includeLowerCase, boolean includeDigit, boolean includeSpecialSymbol) {
        return fakerObject.internet().password();
    }


    public static String generateRandom(String typeOfData, int length) {
        String returnvariable = "";
        switch (typeOfData) {

            case "numeric":
                returnvariable = RandomStringUtils.randomNumeric(length);
                break;

            case "alphabetic":
                returnvariable = RandomStringUtils.randomAlphabetic(length);
                break;

            case "alphanumeric":
                returnvariable = RandomStringUtils.randomAlphanumeric(length);
                break;

            case "special":
                returnvariable = RandomStringUtils.random(length, SPECIAL_CHARACTERS);
                break;

            case "alphanumericSpecial":
                returnvariable = RandomStringUtils.random(length, ALPHANUMERIC_SPECIAL_CHARACTERS);
                break;

            case "email":
                returnvariable = generateRandomStringEmail(length, ALPHANUMERIC_CHARACTERS_MIXT);
                break;

            case "space":
                returnvariable = " ";
                break;

            case "alphabeticLowerCase":
                returnvariable = RandomStringUtils.random(length, ALPHABETIC_CHARACTERS.toLowerCase());
                break;

            case "alphabeticUpperCase":
                returnvariable = RandomStringUtils.random(length, ALPHABETIC_CHARACTERS.toUpperCase());
                break;

            case "alphanumericUpperCase":
                returnvariable = RandomStringUtils.random(length, ALPHANUMERIC_CHARACTERS_UPPER);
                break;

            case "alphanumericLowerCase":
                returnvariable = RandomStringUtils.random(length, ALPHANUMERIC_CHARACTERS_LOWER);
                break;

            case "password":
                returnvariable = generateRandomPassword(length);
        }
        return returnvariable;
    }

    private static String generateRandomStringEmail(int length, String characters) {
        StringBuilder stringBuilder = new StringBuilder(length);
        String email = "@gmail.com";
        String stringBeforeAdd = "";
        for (int i = 0; i < length; i++) {
            int randomIndex = objectOfRandom.nextInt(characters.length());
            stringBeforeAdd = (stringBuilder.append(characters.charAt(randomIndex))).toString();
        }
        return stringBeforeAdd + email;
    }

    public static String generateRandomPassword(int length) {

        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4");
        }

        String uppercase = RandomStringUtils.random(1, ALPHABETIC_CHARACTERS.toUpperCase());
        String lowercase = RandomStringUtils.random(1, ALPHABETIC_CHARACTERS.toLowerCase());
        String specialCharacters = RandomStringUtils.random(1, SPECIAL_CHARACTERS);
        String number = RandomStringUtils.randomNumeric(1);
        String passwordWithoutRequirements = RandomStringUtils.random(length - 4, ALPHANUMERIC_SPECIAL_CHARACTERS);

        return uppercase + lowercase + number + specialCharacters + passwordWithoutRequirements;
    }

    public static String generateInvalidInputSpecialCharacters(int length) {

        if (length < 3) {
            throw new IllegalArgumentException("The length must be at least 3");
        }

        return RandomStringUtils.random(length, SPECIAL_CHARACTERS);
    }


}