Feature: Add Personal Profile page for a registered user

  Background:
    Given user logs in with "abPana" and "Apan5."

  @Run @Positive @INTPO002PD-4857_TS01_TC01 @UI
  Scenario: Ensure that user can login into the system by completing Username and Password fields with valid data
    When user clicks on the dropdown Username
    Then The user is redirected to the MY_PROFILE_PAGE page

  @Run @Positive @INTPO002PD-4857_TS02_TC01 @UI @PersonalPageLogin @R
  Scenario: Ensure that a registered user can update First name Last name Username Email fields with valid data
    Given user access my profile page
    When the following form from "MyProfilePage" is populated as follows:
      | firstNamePersonalPage | Annnn                 |
      | lastNamePersonalPage  | Pana                  |
      | emailPersonalPage     | andreipana9@gmail.com |
      | userNamePersonalPage  | abPanmma              |
#    When user populates the firstname field with 'Andrei'
#    And user populates the Lastname field with 'Pana'
#    And user populates the Username field with 'abPana'
#    And user populates the Email field with 'andreipana9@gmail.com'
    And the "saveChangesButtonPersonalPage" from "MyProfilePage" is clicked
    Then all the changes are saved

  @Run @Negative @INTPO002PD-4857_TS03_TC01 @INTPO002PD-4857_TS03_TC02 @INTPO002PD-4857_TS03_TC04 @INTPO002PD-4857_TS03_TC05 @UI
  Scenario Outline: Ensure that a registered user cannot update First name field with valid data
    Given user access my profile page
    When user populates "firstNamePersonalPage" field with "<filedValue>" data type and <length> from "MyProfilePage" page
    And the "saveChangesButtonPersonalPage" from "MyProfilePage" is clicked
    Then changes are not saved
    And error message for first name is displayed
    Examples:
      | filedValue   | length |
      | ALPHANUMERIC | 2      |
      | ALPHANUMERIC | 31     |
      | SPACE        | 1      |

  @Run @Negative @INTPO002PD-4857_TS03_TC01 @INTPO002PD-4857_TS03_TC02 @INTPO002PD-4857_TS03_TC04 @INTPO002PD-4857_TS03_TC05 @UI
  Scenario Outline: Ensure that a registered user cannot update Last name field with valid data
    Given user access my profile page
    When user populates "lastNamePersonalPage" field with "<lastName>" data type and <length> from "MyProfilePage" page
    And the "saveChangesButtonPersonalPage" from "MyProfilePage" is clicked
    Then changes are not saved
    And error message for lastname is displayed
    Examples:
      | lastName     | length |
      | ALPHANUMERIC | 2      |
      | ALPHANUMERIC | 31     |
      | SPACE        | 1      |

  @Run @Negative @INTPO002PD-4857_TS03_TC01 @INTPO002PD-4857_TS03_TC02 @INTPO002PD-4857_TS03_TC04 @INTPO002PD-4857_TS03_TC05 @UI
  Scenario Outline: Ensure that a registered user cannot update User name field with valid data
    Given user access my profile page
    When user populates "userNamePersonalPage" field with "<userName>" data type and <length> from "MyProfilePage" page
    And the "saveChangesButtonPersonalPage" from "MyProfilePage" is clicked
    Then changes are not saved
    And error message for username is displayed

    Examples:
      | userName     | length |
      | ALPHANUMERIC | 2      |
      | ALPHANUMERIC | 31     |
      | SPACE        | 1      |


  @Run @Negative @INTPO002PD-4857_TS03_TC03 @UI
  Scenario: Ensure that a registered user cannot update Email field by using an e-mail with a wrong format
    Given user access my profile page
    When user populates email field with a ALPHANUMERIC
    And the "saveChangesButtonPersonalPage" from "MyProfilePage" is clicked
    Then changes are not saved
    And error message for email field is displayed

  @Run @Positive @INTPO002PD-4857_TS04_TC01 @UI
  Scenario: Ensure that all UI elements are displayed on My Profile page
    When user access my profile page
    Then all ui elements are displayed on profile page