
package StepDefinition;

import org.openqa.selenium.WebDriver;
import Hooks.Hooks_Class;
import Pages.LogoutPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Logout {

	WebDriver driver;
	LogoutPage logoutPage;

	@Given("user login into tutorials ninja application")
	public void user_login_into_tutorials_ninja_application() {

		driver = Hooks_Class.driver;
		logoutPage = new LogoutPage(driver);

		logoutPage.clickMyAccount();

		logoutPage.clickLogin();

		logoutPage.enterEmail("suryarfg12123@gmail.com");

		logoutPage.enterPassword("Surya123");

		logoutPage.clickLoginButton();
	}

	@When("user click my account dropdown")
	public void user_click_my_account_dropdown() {

		logoutPage.clickMyAccount();
	}

	@When("user click logout option")
	public void user_click_logout_option() {

		logoutPage.clickLogout();
	}

	@Then("user should logout successfully")
	public void user_should_logout_successfully() {

		System.out.println("Logout Successful");

//        hc.afterTest();
	}
}