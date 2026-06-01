package utilities;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * PopupHandler — dismisses the PHPTravels demo warning popup.
 *
 * The popup appears on EVERY page with "I Understand & Continue" button.
 * It overlays content and blocks all clicks if not dismissed first.
 *
 * XPath: //button[contains(text(),'I Understand')]
 */
public class PopupHandler {

    /**
     * Dismisses the demo warning popup if it is visible.
     * Safe to call on any page — silently skips if popup is not present.
     */
    public static void dismissIfPresent() {
        WebDriver driver = DriverFactory.getDriver();
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(6));
            WebElement popup = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'I Understand') or contains(text(),'Continue')]")
            ));
            // Use JS click — avoids interception if something is still animating
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", popup);
            System.out.println("[PopupHandler] Demo popup dismissed.");
        } catch (Exception ignored) {
            // Popup not present on this page — perfectly fine
        }
    }

    /**
     * JS-based click — bypasses ElementClickInterceptedException.
     * Use this for any button that might be covered by an overlay.
     */
    public static void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].click();", element);
    }
}
