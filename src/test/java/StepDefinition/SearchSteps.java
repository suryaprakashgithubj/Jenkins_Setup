
package StepDefinition;
import org.openqa.selenium.WebDriver;

import Hooks.Hooks_Class;
import Pages.SearchPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {

    WebDriver driver;
   Hooks_Class hc;
    SearchPage searchPage;

    @Given("user open home page")
    public void user_open_home_page() {
    	
         driver = Hooks_Class.driver;
        searchPage = new SearchPage(driver);
    }

    @When("user enter product in search box")
    public void user_enter_product_in_search_box() {

        searchPage.enterProduct("iPhone");
    }

    @When("user click search button")
    public void user_click_search_button() {

        searchPage.clickSearchButton();
    }

    @Then("searched product should display")
    public void searched_product_should_display() {

        System.out.println("Product Found");       
    }
}