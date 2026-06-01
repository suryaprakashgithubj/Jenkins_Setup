package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.PopupHandler;
import utilities.WaitUtils;

public class LoginPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By emailField    = By.name("email");
    private final By passwordField = By.name("password");

    // FIXED: actual button text on phptravels.net/login is "Sign In to your account"
    private final By loginButton   = By.xpath(
        "//button[contains(text(),'Sign In') or contains(text(),'Login') or contains(text(),'login')]"
        + " | //button[@type='submit']"
    );

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        this.wait   = new WaitUtils(driver);
        // Dismiss demo popup as soon as the page is ready
        PopupHandler.dismissIfPresent();
    }

    public void enterEmail(String email) {
        WebElement f = wait.waitForVisible(emailField);
        f.clear();
        f.sendKeys(email);
    }

    public void enterPassword(String pw) {
        WebElement f = wait.waitForVisible(passwordField);
        f.clear();
        f.sendKeys(pw);
    }

    public void clickLogin() {
        WebElement btn = wait.waitForClickable(loginButton);
        // JS click bypasses ElementClickInterceptedException from popup overlay
        PopupHandler.jsClick(btn);
    }
}
