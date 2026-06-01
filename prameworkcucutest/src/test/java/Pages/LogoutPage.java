package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {

    WebDriver driver;

    public LogoutPage(WebDriver driver) {

        this.driver = driver;
    }

    By myAccount =By.xpath("//span[text()='My Account']");

    By loginLink =By.linkText("Login");

    By emailField =By.id("input-email");

    By passwordField =By.id("input-password");

    By loginButton =By.xpath("//input[@value='Login']");

    By logoutLink =By.linkText("Logout");

    public void clickMyAccount() {

        driver.findElement(myAccount)
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

    public void clickLogout() {

        driver.findElement(logoutLink)
                .click();
    }
}