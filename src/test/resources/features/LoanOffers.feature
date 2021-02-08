Feature: Checking Upgrade Website
  @UiTest
  Scenario: Create account and verify if loan offer is the same when user signs in to account later

    Given user is on homepage
    When user enters "2000" and selects "Business"
    And user clicks on Check your rate button
    And user enters firstName and lastName
    And user enters home Address
    And user enters DOB
    And user enters annual and additional Income
    And user enters email address and new password
    And user checks box for Terms of use
    And user clicks on Check your rate button
    Then user should see Great news, here are your offers! message
    And user should see Offered Loan Amount, Offered Monthly payment, Offered Loan Term, Offered Interest Rate and APR
    And user clicks on sign out button
    And user should log out successfully and see You've been successfully logged out. message
    Then user navigates to login page
    And user enters previously entered email and password
    And user clicks on Sign in to your account button
    Then user should see Great news, here are your offers! message
    And user should see previously generated Offered Loan Amount, Offered Monthly payment, Offered Loan Term, Offered Interest Rate and APR












