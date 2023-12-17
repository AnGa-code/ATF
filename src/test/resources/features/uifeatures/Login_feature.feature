@Regression
Feature: Login to the Book Sharing website

  @Positive @INTPO002PD4842_TS01_TC01 @UI @INTPO002PD-5318 @Andrei
  Scenario: Ensure that user can login into the system by completing Username and Password fields with valid data
    When user logs in with "abPana" and "Apan5."
    Then The user is redirected to the MAIN_PAGE page

  @Positive @INTPO002PD4842_TS03_TC01 @UI @INTPO002PD-5318 @Andrei
  Scenario: Ensure that user will be redirected to Forgot Password page by clicking Forgot Password hyperlink
    When user clicks Forgot Password hyperlink
    Then The user is redirected to the FORGOT_PASSWORD_PAGE page

  @Negative @INTPO002PD4842_TS02_TC01 @UI @INTPO002PD4842_TS02_TC02 @INTPO002PD4842_TS02_TC03  @Andrei
  Scenario Outline: Ensure that user cannot login into the system by leaving Username field blank
    When user logs in with "<username>" and "<password>"
    Then The error message is displayed
    And The user is not redirected to the MAIN_PAGE page
    Examples:
      | username | password |
      |          | Apan5.   |
      | abPana   |          |
      | 'Tks14?  | NotNs    |

  @Positive @INTPO002PD4842_TS05_TC01 @UI @Andrei
  Scenario: Ensure that graphical elements Logo and Project Name are displayed on the Login page
    When User access Login page
    Then Endava's Logo is displayed on the left side of the Login page
    And Project Name is displayed on the left side of the Login page