@Regression @UI @ValentinaBegu @US-INTPO002PD-4841
Feature: Register Flow

  Background:
    Given SIGNUP_PAGE page is accessed

  @ID-INTPO002PD-5015 @Positive
  Scenario: Verify UI elements on the Register Page
    Then the following elements from "RegisterPage" are displayed
      | signupHeader        |
      | EndavaLogo          |
      | bookSharingTitle    |
      | firstNameInput      |
      | lastNameInput       |
      | userNameInput       |
      | emailInput          |
      | emailLabel          |
      | passwordInput       |
      | repeatPasswordInput |
      | signUpButton        |

  @ID-INTPO002PD-5016 @Positive
  Scenario: Sign up page can be accessed from Login page
    Given LOGIN_PAGE page is accessed
    When the "signUpLink" from "LoginPage" is clicked
    Then the current url contains SIGNUP_PAGE keyword

  @ID-INTPO002PD-5029 @Positive
  Scenario: The user is successfully registered with valid data
    When the following form from "RegisterPage" is populated as follows:
      | firstNameInput      | RandomFirstName |
      | lastNameInput       | RandomLastName  |
      | userNameInput       | RandomFirstName |
      | emailInput          | RandomEmail     |
      | passwordInput       | RandomPassword  |
      | repeatPasswordInput | RepeatPassword  |
    And the "signUpButton" from "RegisterPage" is clicked
    Then the following validationMessage is displayed:
      | "New account has been successfully created." |
    And the current url contains LOGIN_PAGE keyword


  @ID-INTPO002PD-5019 @ID-INTPO002PD-5030 @Negative
  Scenario Outline: The user cannot register with invalid data
    When the following form from "RegisterPage" is populated as follows:
      | firstNameInput      | <firstName>      |
      | lastNameInput       | <lastName>       |
      | userNameInput       | <userName>       |
      | emailInput          | <email>          |
      | passwordInput       | <password>       |
      | repeatPasswordInput | <repeatPassword> |
    And the "signUpButton" from "RegisterPage" is clicked
    Then the current url contains SIGNUP_PAGE keyword

    Examples:
      | firstName           | lastName            | userName            | email               | password            | repeatPassword      |
      | a                   | RandomLastName      | RandomFirstName     | RandomEmail         | RandomPassword      | RepeatPassword      |
      | RandomFirstName     | a                   | RandomFirstName     | RandomEmail         | RandomPassword      | RepeatPassword      |
      | RandomFirstName     | RandomLastName      | a                   | RandomEmail         | RandomPassword      | RepeatPassword      |
      | RandomFirstName     | RandomLastName      | RandomFirstName     | aNNmail.com         | RandomPassword      | RepeatPassword      |
      | RandomFirstName     | RandomLastName      | RandomFirstName     | RandomEmail         | A                   | A                   |
      | RandomFirstName     | RandomLastName      | RandomFirstName     | RandomEmail         | RandomPassword      | A#1a7               |
      | InvalidSpecialChars | InvalidSpecialChars | InvalidSpecialChars | InvalidSpecialChars | InvalidSpecialChars | InvalidSpecialChars |
      | Иван                | Иванов              | Иван                | иван@mail.ru        | RandomPassword      | RepeatPassword      |

  @ID-INTPO002PD-5021 @INTPO002PD-5031 @Negative
  Scenario Outline: The user cannot register with existing username or email
    When the following form from "RegisterPage" is populated as follows:
      | firstNameInput      | RandomFirstName |
      | lastNameInput       | RandomLastName  |
      | userNameInput       | <userName>      |
      | emailInput          | <email>         |
      | passwordInput       | RandomPassword  |
      | repeatPasswordInput | RepeatPassword  |
    And the "signUpButton" from "RegisterPage" is clicked
    Then the following validationMessage is displayed:
      | "User with the same email or username already exists" |
    And the current url contains SIGNUP_PAGE keyword
    Examples:
      | userName  | email          |
      | AAA       | ionG@gmail.com |
      | Ana-Maria | ana@yahoo.com  |