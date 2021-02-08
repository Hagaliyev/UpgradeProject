package com.upgrade.step_definitions;

import com.upgrade.pages.CheckRatePage;
import com.upgrade.utils.ConfigurationReader;
import com.upgrade.utils.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class CheckLoanRateStep {

      CheckRatePage checkRatePage =new CheckRatePage();
      String initialOfferedLoanAmount;
      String initialOfferedMonthlyPayment;
      String initialOfferedLoanTerm;
      String initialOfferedAPR;
      String initialOfferedInterestRate;
      String currentOfferedLoanAmount;
      String currentOfferedMonthlyPayment;
      String currentOfferedLoanTerm;
      String currentOfferedAPR;
      String currentOfferedInterestRate;
      String email;
      String password;

    /**
     * For demo purposes i added Thread.sleeps in some steps to make test steps more visible. Without thread.sleeps
     * test runs too fast in some steps and viewer will not be able to see all steps of the test.
     */

    @Given("user is on homepage")
    public void user_is_on_homepage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("user enters {string} and selects {string}")
    public void user_enters_and_selects(String string, String string2) {
       checkRatePage.getLoanAmountField().sendKeys(string);

       Select dropdown=new Select(checkRatePage.getLoanPurposeDropdown());
       dropdown.selectByVisibleText(string2);
    }

    @And("user clicks on Check your rate button")
    public void user_clicks_on_check_your_rate_button() {
        checkRatePage.getSubmitButton().click();
        checkRatePage.getMessage().click(); //this part was added yesterday
    }

    @And("user enters firstName and lastName")
    public void user_enters_first_name_and_last_name() {
        checkRatePage.getFirstNameField().sendKeys(checkRatePage.firstName);
        checkRatePage.getLastNameField().sendKeys(checkRatePage.lastName);
    }
    @When("user enters home Address")
    public void user_enters_home_address() throws InterruptedException {
        checkRatePage.getStreetAddressField().sendKeys(checkRatePage.streetAddress);
        checkRatePage.getAddressSuggestions().get(0).click();
        Thread.sleep(1000);
        checkRatePage.getZipCodeField().sendKeys("11226");
    }


    @And("user enters DOB")
    public void user_enters_dob() throws InterruptedException {
        Thread.sleep(1000);
       checkRatePage.getDOBField().sendKeys("10");
        //checkRatePage.getDOBField().sendKeys(checkRatePage.DOBMonth+"");
        checkRatePage.getDOBField().sendKeys("15");
        Thread.sleep(1000);
        //checkRatePage.getDOBField().sendKeys(checkRatePage.DOBDay+"");
        Thread.sleep(1000);
        checkRatePage.getDOBField().sendKeys("2000");
        //checkRatePage.getDOBField().sendKeys(checkRatePage.DOBYear+"");
        Thread.sleep(2000);
        checkRatePage.getSubmitButton().click();
    }

    @And("user enters annual and additional Income")
    public void user_enters_annual_and_additional_income() {
        checkRatePage.getBorrowerIncomeField().sendKeys(checkRatePage.annualIncome+"");
        checkRatePage.getBorrowerAdditionalIncome().sendKeys(checkRatePage.additionalIncome+""+Keys.ENTER);
    }

    @And("user enters email address and new password")
    public void user_enters_email_address_and_new_password() {
        email =checkRatePage.email;
        checkRatePage.getEmailField().sendKeys(email);
        password= checkRatePage.password;
        checkRatePage.getNewPasswordField().sendKeys(password);
        System.out.println("email = " + email);
        System.out.println("password = " + password);
    }

    @And("user checks box for Terms of use")
    public void user_checks_box_for_terms_of_use() {
        checkRatePage.getConsentCheckbox().click();
    }

    @Then("user should see Great news, here are your offers! message")
    public void user_should_see_great_news_here_are_your_offers_message() {
        checkRatePage.getOfferMessage().isDisplayed();
    }

    @Then("user should see Offered Loan Amount, Offered Monthly payment, Offered Loan Term, Offered Interest Rate and APR")
    public void user_should_see_offered_loan_amount_offered_monthly_payment_offered_loan_term_offered_interest_rate_and_apr() {
        checkRatePage.getOfferedLoanAmount().isDisplayed();
        initialOfferedLoanAmount=checkRatePage.getOfferedLoanAmount().getText();
        checkRatePage.getOfferedMonthlyPayment().isDisplayed();
        initialOfferedMonthlyPayment=checkRatePage.getOfferedMonthlyPayment().getText();
        checkRatePage.getOfferedLoanTerm().isDisplayed();
        initialOfferedLoanTerm=checkRatePage.getOfferedLoanTerm().getText();
        checkRatePage.getOfferedInterestRate().isDisplayed();
        initialOfferedInterestRate=checkRatePage.getOfferedInterestRate().getText();
        checkRatePage.getOfferedAPR().isDisplayed();
        initialOfferedAPR=checkRatePage.getOfferedAPR().getText();
    }

    @Then("user clicks on sign out button")
    public void user_clicks_on_sign_out_button() throws InterruptedException {
        checkRatePage.getMenuButton().click();
        Thread.sleep(2000);
        checkRatePage.getSignOut().click();
    }

    @Then("user should log out successfully and see You've been successfully logged out. message")
    public void user_should_log_out_successfully_and_see_you_ve_been_successfully_logged_out_message() {
       checkRatePage.getLogOutSuccessMessage().isDisplayed();
    }

    @Then("user navigates to login page")
    public void user_navigates_to_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("urlSignIn"));
    }

    @Then("user enters previously entered email and password")
    public void user_enters_previously_entered_email_and_password() throws InterruptedException {
      checkRatePage.getEmailField().sendKeys(email);
      Thread.sleep(2000);
      checkRatePage.getCurrentPasswordField().sendKeys(password);
    }

    @Then("user clicks on Sign in to your account button")
    public void user_clicks_on_sign_in_to_your_account_button() {
        checkRatePage.getSubmitButton().click();
    }

    @Then("user should see previously generated Offered Loan Amount, Offered Monthly payment, Offered Loan Term, Offered Interest Rate and APR")
    public void user_should_see_previously_generated_offered_loan_amount_offered_monthly_payment_offered_loan_term_offered_interest_rate_and_apr() throws InterruptedException {
        currentOfferedLoanAmount=checkRatePage.getOfferedLoanAmount().getText();
        currentOfferedMonthlyPayment=checkRatePage.getOfferedMonthlyPayment().getText();
        currentOfferedLoanTerm=checkRatePage.getOfferedLoanTerm().getText();
        currentOfferedInterestRate=checkRatePage.getOfferedInterestRate().getText();
        currentOfferedAPR=checkRatePage.getOfferedAPR().getText();
        Thread.sleep(2000);
        Assert.assertTrue(initialOfferedLoanAmount.equals(currentOfferedLoanAmount));
        Assert.assertTrue(initialOfferedMonthlyPayment.equals(currentOfferedMonthlyPayment));
        Assert.assertTrue(initialOfferedLoanTerm.equals(currentOfferedLoanTerm));
        Assert.assertTrue(initialOfferedInterestRate.equals(currentOfferedInterestRate));
        Assert.assertTrue(initialOfferedAPR.equals(currentOfferedAPR));
    }







}
