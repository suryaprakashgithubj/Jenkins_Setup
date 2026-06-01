
package StepDefinition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import Hooks.Hooks_Class;
import Pages.RegPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Reg {

    WebDriver driver;
Hooks_Class hc;
    RegPage regPage;

    @Given("user open registration page")
    public void user_open_registration_page() {
         driver = Hooks_Class.driver;
        regPage = new RegPage(driver);

        regPage.clickMyAccount();

        regPage.clickRegister();
    }

    @When("^user enter firstname (.*)$")
    public void user_enter_firstname(String firstname) {

        regPage.enterFirstName(firstname);
    }

    @When("^user enter lastname (.*)$")
    public void user_enter_lastname(String lastname) {

        regPage.enterLastName(lastname);
    }

    @When("^user enter register email (.*)$")
    public void user_enter_email(String email) {

        regPage.enterEmail(email);
    }

    @When("^user enter telephone (.*)$")
    public void user_enter_telephone(String telephone) {

        regPage.enterTelephone(telephone);
    }

    @When("^user enter password (.*)$")
    public void user_enter_password(String password) {

        regPage.enterPassword(password);

        regPage.clickAgree();
    }

    @Then("user click continue button")
    public void user_click_continue_button() {

        regPage.clickContinue();
    }

    @Then("account should be created successfully")
    public void account_should_be_created_successfully() {

        String pageText =
                driver.findElement(By.tagName("body"))
                        .getText();

        System.out.println(pageText);

        if (pageText.contains(
                "Warning: E-Mail Address is already registered!")) {

            System.out.println(
                    "Email already registered. Use new email.");
        }

        Assert.assertTrue(
                pageText.contains(
                        "Your Account Has Been Created!")
        );

        System.out.println("Registration Successful");

        
    }
}