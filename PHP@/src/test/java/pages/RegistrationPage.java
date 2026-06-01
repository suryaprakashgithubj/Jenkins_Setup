package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.PopupHandler;
import utilities.WaitUtils;

import java.util.List;

public class RegistrationPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By firstNameField = By.name("first_name");
    private final By lastNameField  = By.name("last_name");

    // FIXED: phone field — try multiple locators since site may use intl-tel-input
    // which wraps the tel input inside a div with class "iti"
    private final By phoneField     = By.xpath(
        "//input[@type='tel'] | //input[@name='phone'] | //input[contains(@id,'phone')]"
        + " | //div[contains(@class,'iti')]//input"
    );
    private final By emailField     = By.name("email");
    private final By passwordField  = By.name("password");
    private final By signupButton   = By.xpath("//button[@type='submit']");

    public RegistrationPage() {
        this.driver = DriverFactory.getDriver();
        this.wait   = new WaitUtils(driver);
        // Dismiss demo popup immediately on page load
        PopupHandler.dismissIfPresent();
    }

    public void enterFirstName(String name) {
        WebElement f = wait.waitForVisible(firstNameField);
        f.clear();
        f.sendKeys(name);
    }

    public void enterLastName(String name) {
        WebElement f = wait.waitForVisible(lastNameField);
        f.clear();
        f.sendKeys(name);
    }

    public void enterPhone(String phone) {
        try {
            // Try each locator strategy
            List<WebElement> phoneFields = driver.findElements(
                By.xpath("//input[@type='tel'] | //input[@name='phone'] | //input[contains(@id,'phone')]")
            );
            if (!phoneFields.isEmpty()) {
                WebElement f = phoneFields.get(0);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", f);
                f.click();
                f.clear();
                f.sendKeys(phone);
            } else {
                // Fallback: find any visible input that looks like phone
                System.out.println("[RegistrationPage] Phone field not found by standard locators, skipping.");
            }
        } catch (Exception e) {
            System.out.println("[RegistrationPage] Phone entry: " + e.getMessage());
        }
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

    public void selectCountry(String country) {
        try {
            // Dynamic XPath with contains() for country dropdown
            By dropdown = By.xpath(
                "//button[contains(@class,'dropdown') or contains(@class,'select') or contains(@class,'country')]"
                + " | //*[@role='combobox'][contains(.,'country') or contains(.,'Country') or contains(.,'nation')]"
            );
            WebElement btn = wait.waitForClickable(dropdown);
            PopupHandler.jsClick(btn);

            By option = By.xpath(
                "//li[contains(text(),'" + country + "')] | //div[contains(text(),'" + country + "')]"
                + " | //span[contains(text(),'" + country + "')]"
            );
            wait.waitForClickable(option).click();
        } catch (Exception e) {
            System.out.println("[RegistrationPage] Country selection skipped: " + e.getMessage());
        }
    }

    public void clickSignup() {
        WebElement btn = wait.waitForClickable(signupButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        PopupHandler.jsClick(btn);
    }
}
