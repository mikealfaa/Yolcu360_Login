@login
Feature: user should be able to login with valid credential
  Background:
    Given the user is on the login page
    When the user changes language "TR"
    And the user click Giriş Yap button1
    And the user click Giriş Yap button2

@deneme
Scenario:Login as a user
And the user enters the valid "email" and valid "password" information
When the user click Giriş Yap login button
Then the user should be able to login


Scenario Outline:Verify that users can't login to the application with Invalid credential
  And the user enters the invalid "<email>" and "<password>" information
  When the user click Giriş Yap login button
  Then the user should not be able to login
  Examples:
    | email                      | password   |
    | mustafa_task26@hotmail.com | Ankara     |
    | mustafa26@hotmail.com      | Ankara4429 |

  Scenario Outline: Verify that Bu alanın doldurulması zorunludur message is displayed
    And the user enters the invalid "<email>" and "<password>" information
    When the user click Giriş Yap login button
    Then Warning message should be displayed in the relevant field

    Examples:
      | email                      | password   |
      | mustafa_task26@hotmail.com |            |
      |                            | Ankara4429 |