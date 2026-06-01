package stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import pages.LogoutPage;

import base.DriverFactory;

public class LogoutSteps {

    LogoutPage logout = new LogoutPage();

    @And("user clicks profile")
    public void user_clicks_profile() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(
                            DriverFactory.getDriver(),
                            Duration.ofSeconds(20)
                    );

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.id("page-loader")
                    )
            );

            DriverFactory.getDriver()
                    .findElement(logout.profileDropdown)
                    .click();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user clicks logout")
    public void user_clicks_logout() {

        try {

            DriverFactory.getDriver()
                    .findElement(logout.logoutButton)
                    .click();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @Then("validate logout")
    public void validate_logout() {

        Assert.assertTrue(
                DriverFactory.getDriver()
                        .getCurrentUrl()
                        .contains("login")
        );
    }
}