package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCartPage {


WebDriver driver;

public AddCartPage(WebDriver driver) {

    this.driver = driver;
}

By searchBox = By.name("search");

By searchButton =
        By.xpath("//button[@class='btn btn-default btn-lg']");

By addCartButton =
        By.xpath("//span[text()='Add to Cart']/parent::button");

public void searchProduct(String product) {

    driver.findElement(searchBox)
            .sendKeys(product);
}

public void clickSearchButton() {

    driver.findElement(searchButton)
            .click();
}

public void clickAddCartButton() {

    WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.elementToBeClickable(addCartButton))
            .click();
}


}
