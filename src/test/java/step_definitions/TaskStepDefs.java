package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Actions;
import pages.LoginPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

public class TaskStepDefs extends Actions {
    LoginPage loginPage=new LoginPage();

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));
        System.out.println("Driver.get().getCurrentUrl() = " + Driver.get().getCurrentUrl());
        System.out.println("Driver.get().getTitle() = " + Driver.get().getTitle());
    }

    @Given("the user click Giriş Yap button1")
    public void the_user_click_Giriş_Yap_button1() {
        BrowserUtils.waitFor(3);
        loginPage.Girişyapbutton1Loc.click();
        BrowserUtils.waitFor(3);
    }

    @Given("the user click Giriş Yap button2")
    public void the_user_click_Giriş_Yap_button2() {
        loginPage.Girişyapbutton2Loc.click();
        BrowserUtils.waitFor(3);
    }

    @Given("the user enters the valid {string} and valid {string} information")
    public void the_user_enters_the_valid_and_valid_information(String email, String password) {
        login(email,password);
    }

    @When("the user click Giriş Yap login button")
    public void the_user_click_Giriş_Yap_login_button() {
        loginPage.GirişyapLoginLoc.click();
        BrowserUtils.waitFor(3);
    }

    @Then("the user should be able to login")
    public void the_user_should_be_able_to_login() {
        assertLogin();
    }

    @Given("the user enters the invalid {string} and {string} information")
    public void the_user_enters_the_invalid_and_information(String email, String password) {
        BrowserUtils.waitFor(5);
        login2(email,password);
        BrowserUtils.waitFor(5);
    }

    @Then("the user should not be able to login")
    public void the_user_should_not_be_able_to_login() {
        assertFalseLogin();
    }

    @Then("Warning message should be displayed in the relevant field")
    public void warning_message_should_be_displayed_in_the_relevant_field() {
        assertEmptyLogin();
    }

    @When("the user changes language {string}")
    public void theUserChangesLanguage(String language) {
        BrowserUtils.waitFor(5);
        loginPage.changeLanguage(language);
    }
}
