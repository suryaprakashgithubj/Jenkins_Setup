package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegPage {

    WebDriver driver;

    public RegPage(WebDriver driver) {

        this.driver = driver;
    }

    By myAccount =By.xpath("//span[text()='My Account']");

    By registerLink =By.linkText("Register");

    By firstName =By.id("input-firstname");

    By lastName =By.id("input-lastname");

    By email =By.id("input-email");

    By telephone =By.id("input-telephone");

    By password =By.id("input-password");

    By confirmPassword =By.id("input-confirm");

    By agree =By.name("agree");

    By continueButton =By.xpath("//input[@value='Continue']");

    public void clickMyAccount() {

        driver.findElement(myAccount)
                .click();
    }

    public void clickRegister() {

        driver.findElement(registerLink)
                .click();
    }

    public void enterFirstName(String firstname) {

        driver.findElement(firstName)
                .sendKeys(firstname);
    }

    public void enterLastName(String lastname) {

        driver.findElement(lastName)
                .sendKeys(lastname);
    }

    public void enterEmail(String mail) {

        driver.findElement(email)
                .sendKeys(mail);
    }

    public void enterTelephone(String phone) {

        driver.findElement(telephone)
                .sendKeys(phone);
    }

    public void enterPassword(String pass) {

        driver.findElement(password)
                .sendKeys(pass);

        driver.findElement(confirmPassword)
                .sendKeys(pass);
    }

    public void clickAgree() {

        driver.findElement(agree)
                .click();
    }

    public void clickContinue() {

        driver.findElement(continueButton)
                .click();
    }
}