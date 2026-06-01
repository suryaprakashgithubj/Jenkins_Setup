package utilities;

import base.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * ExceptionHandler — Q14: Advanced Exception Handling Framework
 *
 * Handles:
 *  - NoSuchElementException
 *  - TimeoutException
 *  - StaleElementReferenceException  (with retry)
 *  - ElementClickInterceptedException (with JS click fallback)
 *
 * Q14 conditions:
 *  ✔ Retry stale elements
 *  ✔ Continue execution where possible (no hard crash)
 */
public class ExceptionHandler {

    private static final int MAX_RETRIES = 3;

    /**
     * Safe click — handles StaleElement and ClickIntercepted automatically.
     * Returns true if click succeeded, false if all retries failed.
     */
    public static boolean safeClick(By locator) {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
                el.click();
                return true;

            } catch (StaleElementReferenceException e) {
                // Q14: Retry stale elements
                System.out.println("[ExceptionHandler] StaleElement on click — retry " + attempt);

            } catch (ElementClickInterceptedException e) {
                // Q14: Fall back to JS click when element is covered by overlay
                System.out.println("[ExceptionHandler] ClickIntercepted — using JS click");
                try {
                    WebElement el = driver.findElement(locator);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                    return true;
                } catch (Exception inner) {
                    System.out.println("[ExceptionHandler] JS click also failed: " + inner.getMessage());
                }

            } catch (NoSuchElementException e) {
                // Q14: Log and continue — don't crash the test
                System.out.println("[ExceptionHandler] NoSuchElement: " + locator + " — skipping.");
                return false;

            } catch (TimeoutException e) {
                // Q14: Log timeout and continue
                System.out.println("[ExceptionHandler] Timeout waiting for: " + locator);
                return false;
            }
        }
        System.out.println("[ExceptionHandler] All retries exhausted for: " + locator);
        return false;
    }

    /**
     * Safe getText — returns empty string instead of throwing on missing element.
     */
    public static String safeGetText(By locator) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            return driver.findElement(locator).getText().trim();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("[ExceptionHandler] safeGetText failed for: " + locator);
            return "";
        }
    }
}
