
package StepDefinition;
import org.openqa.selenium.WebDriver;
import Hooks.Hooks_Class;
import Pages.ProductRemovePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductRemove {

    WebDriver driver;
Hooks_Class hc;
    ProductRemovePage removeCartPage;

    @Given("user add product into cart")
    public void user_add_product_into_cart() {
    	
         driver = Hooks_Class.driver;
        removeCartPage = new ProductRemovePage(driver);

        removeCartPage.searchProduct("iPhone");

        removeCartPage.clickSearchButton();

        removeCartPage.clickAddCartButton();
    }

    @When("user open shopping cart")
    public void user_open_shopping_cart() {

        removeCartPage.clickShoppingCart();
    }

    @When("user remove cart item")
    public void user_remove_cart_item() {

        removeCartPage.clickRemoveButton();
    }

    @Then("cart item should be removed")
    public void cart_item_should_be_removed() {

        System.out.println("Cart Item Removed Successfully");

    }
}