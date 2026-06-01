
package StepDefinition;
import org.openqa.selenium.WebDriver;

import Hooks.Hooks_Class;
import Pages.AddCartPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddCartSteps {

    WebDriver driver;

    AddCartPage addCartPage;

    @Given("user open tutorials ninja home")
    public void user_open_tutorials_ninja_home() {
    	
    	driver = Hooks_Class.driver;
        addCartPage = new AddCartPage(driver);
    }

    @When("user search product for cart")
    public void user_search_product_for_cart() {

        addCartPage.searchProduct("iPhone");

        addCartPage.clickSearchButton();
    }

    @When("user click add to cart button")
    public void user_click_add_to_cart_button() {

        addCartPage.clickAddCartButton();
    }

    @Then("product should be added to cart successfully")
    public void product_should_be_added_to_cart_successfully() {

        System.out.println("Product Added To Cart Successfully");

        
    }
}