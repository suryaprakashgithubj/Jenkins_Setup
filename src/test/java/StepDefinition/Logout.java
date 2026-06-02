//package StepDefinition;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class Logout {
//
//    WebDriver driver;
//
//    @Given("user login into tutorials ninja application")
//    public void user_login_into_tutorials_ninja_application() {
//
//        driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        driver.get("https://tutorialsninja.com/demo/");
//
//        driver.findElement(By.xpath("//span[text()='My Account']")).click();
//
//        driver.findElement(By.linkText("Login")).click();
//
//        driver.findElement(By.id("input-email"))
//                .sendKeys("suryarfg12123@gmail.com");
//
//        driver.findElement(By.id("input-password"))
//                .sendKeys("Surya123");
//
//        driver.findElement(By.xpath("//input[@value='Login']"))
//                .click();
//    }
//
//    @When("user click my account dropdown")
//    public void user_click_my_account_dropdown() {
//
//        driver.findElement(By.xpath("//span[text()='My Account']")).click();
//    }
//
//    @When("user click logout option")
//    public void user_click_logout_option() throws InterruptedException {
//
//        driver.findElement(By.linkText("Logout")).click();
//        
//        Thread.sleep(2000);
//    }
//
//    @Then("user should logout successfully")
//    public void user_should_logout_successfully() {
//
//        System.out.println("Logout Successful");
//
//        driver.quit();
//    }
//}

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