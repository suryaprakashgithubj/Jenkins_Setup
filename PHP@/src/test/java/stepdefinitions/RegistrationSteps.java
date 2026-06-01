package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegistrationPage;

public class RegistrationSteps {

    private RegistrationPage regPage;

    @When("user enters first name")
    public void user_enters_first_name() {
        DriverFactory.getDriver().get("https://phptravels.net/signup");
        regPage = new RegistrationPage();
        regPage.enterFirstName("Surya");
    }

    @And("user enters last name")
    public void user_enters_last_name() { regPage.enterLastName("Prakash"); }

    @And("user enters phone number")
    public void user_enters_phone_number() { regPage.enterPhone("9876543210"); }

    @And("user enters email")
    public void user_enters_email() {
        // Random email to avoid duplicate-account errors
        String email = "surya_" + System.currentTimeMillis() + "@gmail.com";
        regPage.enterEmail(email);
    }

    @And("user enters password")
    public void user_enters_password() { regPage.enterPassword("Surya@123"); }

    @And("user selects country")
    public void user_selects_country() { regPage.selectCountry("India"); }

    @And("clicks signup button")
    public void clicks_signup_button() { regPage.clickSignup(); }

    @Then("validate registration success")
    public void validate_registration_success() {
        System.out.println("Registration step completed.");
    }
}
