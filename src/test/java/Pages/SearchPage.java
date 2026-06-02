package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {

        this.driver = driver;
    }

    By searchBox =By.name("search");

    By searchButton =By.xpath("//button[@class='btn btn-default btn-lg']");

    public void enterProduct(String product) {

        driver.findElement(searchBox)
                .sendKeys(product);
    }

    public void clickSearchButton() {

        driver.findElement(searchButton)
                .click();
    }
}