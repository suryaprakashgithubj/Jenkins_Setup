package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * WaitUtils — Q10: Reusable Wait Utility
 *
 * FIXED: Added StaleElementReferenceException retry handling (Q14).
 * All required methods present: waitForClickable, waitForVisible,
 * waitForPresence, waitForAlert, waitForFrame.
 *
 * Thread.sleep() is strictly prohibited — only WebDriverWait used.
 */
public class WaitUtils {

    private final WebDriverWait wait;
    private final WebDriver driver;

    // FIXED: Store driver reference so retry logic can re-find elements
    public WaitUtils(WebDriver driver) {
        long seconds = Long.parseLong(ConfigReader.get("explicitWait"));
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // ── Q10 Required Methods ─────────────────────────────────────────────────

    public WebElement waitForClickable(By locator) {
        return retryOnStale(() ->
            wait.until(ExpectedConditions.elementToBeClickable(locator))
        );
    }

    public WebElement waitForVisible(By locator) {
        return retryOnStale(() ->
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
        );
    }

    public WebElement waitForPresence(By locator) {
        return retryOnStale(() ->
            wait.until(ExpectedConditions.presenceOfElementLocated(locator))
        );
    }

    public Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForFrame(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public boolean waitForUrl(String urlFragment) {
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    public List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // ── Q14: StaleElementReferenceException retry ────────────────────────────

    /**
     * FIXED: Retries the action once on StaleElementReferenceException.
     * Q14 requires retry on stale elements without crashing the test.
     * No Thread.sleep() — pure retry logic.
     */
    private WebElement retryOnStale(java.util.function.Supplier<WebElement> action) {
        try {
            return action.get();
        } catch (StaleElementReferenceException e) {
            System.out.println("[WaitUtils] StaleElementReferenceException caught — retrying once.");
            return action.get(); // retry once
        }
    }
}
