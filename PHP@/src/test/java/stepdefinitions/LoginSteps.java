package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utilities.PopupHandler;

public class LoginSteps {

    private LoginPage loginPage;

    // Handles all 4 data rows including empty strings from Scenario Outline
    @When("user enters {string} and {string}")
    public void user_enters_credentials(String username, String password) {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://phptravels.net/login");
        // Dismiss popup that appears on every page navigation
        PopupHandler.dismissIfPresent();
        loginPage = new LoginPage();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
    }

    @And("clicks login button")
    public void clicks_login_button() {
        loginPage.clickLogin();
    }

    // FIXED: step text must match feature file — "validate login result {string}"
    @Then("validate login result {string}")
    public void validate_login_result(String username) {
        WebDriver driver = DriverFactory.getDriver();
        String currentUrl = driver.getCurrentUrl();
        String pageTitle  = driver.getTitle();

        System.out.println("---------- Login Validation ----------");
        System.out.println("Username tested : [" + username + "]");
        System.out.println("Current URL     : " + currentUrl);
        System.out.println("Page Title      : " + pageTitle);

        if (username.isEmpty()) {
            System.out.println("Result: EXPECTED FAILURE — blank username");
        } else if (currentUrl.contains("account") || currentUrl.contains("dashboard")) {
            System.out.println("Result: LOGIN SUCCESS");
        } else {
            System.out.println("Result: LOGIN FAILED (invalid credentials or blank password)");
        }
        System.out.println("--------------------------------------");
    }
}
