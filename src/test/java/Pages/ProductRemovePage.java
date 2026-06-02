package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductRemovePage {

    WebDriver driver;

    public ProductRemovePage(WebDriver driver) {

        this.driver = driver;
    }

    By searchBox =By.name("search");

    By searchButton =By.xpath("//button[@class='btn btn-default btn-lg']");

    By addCartButton =By.xpath("//span[text()='Add to Cart']");

    By shoppingCart =By.linkText("Shopping Cart");

    By removeButton =By.xpath("//button[@data-original-title='Remove']");

    public void searchProduct(String product) {

        driver.findElement(searchBox)
                .sendKeys(product);
    }

    public void clickSearchButton() {

        driver.findElement(searchButton)
                .click();
    }

    public void clickAddCartButton() {

        driver.findElement(addCartButton)
                .click();
    }

    public void clickShoppingCart() {

        driver.findElement(shoppingCart)
                .click();
    }

    public void clickRemoveButton() {

        driver.findElement(removeButton)
                .click();
    }
}