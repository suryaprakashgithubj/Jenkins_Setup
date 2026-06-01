package StepDefinition;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Hooks.Hooks_Class;
import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    WebDriver driver;
    
    LoginPage loginPage;

    @Given("user open tutorials ninja website")
    public void user_open_tutorials_ninja_website() {
       
        driver=Hooks_Class.driver;
        loginPage = new LoginPage(driver);
    }

    @When("user click on my account")
    public void user_click_on_my_account() {

        loginPage.clickMyAccount();
    }

    @When("user click on login")
    public void user_click_on_login() {

        loginPage.clickLogin();
    }

    @When("user enter email and password")
    public void user_enter_email_and_password() {

        loginPage.enterEmail("suryarfg12123@gmail.com");

        loginPage.enterPassword("Surya123");
    }

    @Then("user click login button")
    public void user_click_login_button() {

        loginPage.clickLoginButton();
    }

    @Then("user navigate to account page")
    public void user_navigate_to_account_page() {

        String actualTitle = driver.getTitle();

        String expectedTitle = "My Account";

        Assert.assertEquals(actualTitle, expectedTitle);

        System.out.println("Assertion Passed");

        System.out.println("Login Successful");

       
    }
}