package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {


WebDriver driver;

public LoginPage(WebDriver driver) {

    this.driver = driver;
}

By myAccount =
        By.xpath("//a[@title='My Account']");

By loginLink =
        By.linkText("Login");

By emailField =
        By.id("input-email");

By passwordField =
        By.id("input-password");

By loginButton =
        By.xpath("//input[@value='Login']");

public void clickMyAccount() {

    WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.elementToBeClickable(myAccount))
            .click();
}

public void clickLogin() {

    driver.findElement(loginLink)
            .click();
}

public void enterEmail(String email) {

    driver.findElement(emailField)
            .sendKeys(email);
}

public void enterPassword(String password) {

    driver.findElement(passwordField)
            .sendKeys(password);
}

public void clickLoginButton() {

    driver.findElement(loginButton)
            .click();
}


}
