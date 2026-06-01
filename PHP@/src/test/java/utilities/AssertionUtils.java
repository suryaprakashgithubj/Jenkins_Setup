package utilities;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * AssertionUtils — Q15: Advanced Assertions
 *
 * Provides both Hard Assert (stops on first failure)
 * and Soft Assert (collects all failures, reports at end).
 *
 * Usage of SoftAssert: create one instance per test,
 * call softAssert.assertAll() at the end to report failures.
 */
public class AssertionUtils {

    // ── Hard Assert ──────────────────────────────────────────────────────────
    // Stops execution immediately on failure

    public static void assertUrl(String expectedUrlFragment) {
        String actual = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(actual.contains(expectedUrlFragment),
            "URL mismatch — expected to contain: [" + expectedUrlFragment + "] but got: [" + actual + "]");
        System.out.println("[HardAssert] URL OK: " + actual);
    }

    public static void assertTitle(String expectedTitle) {
        String actual = DriverFactory.getDriver().getTitle();
        Assert.assertTrue(actual.contains(expectedTitle),
            "Title mismatch — expected: [" + expectedTitle + "] actual: [" + actual + "]");
        System.out.println("[HardAssert] Title OK: " + actual);
    }

    public static void assertElementVisible(By locator) {
        WebDriver driver = DriverFactory.getDriver();
        boolean visible = !driver.findElements(locator).isEmpty()
                && driver.findElement(locator).isDisplayed();
        Assert.assertTrue(visible, "Element not visible: " + locator);
        System.out.println("[HardAssert] Element visible: " + locator);
    }

    // ── Soft Assert ──────────────────────────────────────────────────────────
    // Collects all failures; call softAssert.assertAll() at end of test

    public static SoftAssert createSoftAssert() {
        return new SoftAssert();
    }

    public static void softAssertUrl(SoftAssert softAssert, String expectedFragment) {
        String actual = DriverFactory.getDriver().getCurrentUrl();
        softAssert.assertTrue(actual.contains(expectedFragment),
            "URL should contain [" + expectedFragment + "] but was [" + actual + "]");
    }

    public static void softAssertTitle(SoftAssert softAssert, String expectedTitle) {
        String actual = DriverFactory.getDriver().getTitle();
        softAssert.assertTrue(actual.contains(expectedTitle),
            "Title should contain [" + expectedTitle + "] but was [" + actual + "]");
    }

    public static void softAssertElementVisible(SoftAssert softAssert, By locator) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            boolean visible = driver.findElement(locator).isDisplayed();
            softAssert.assertTrue(visible, "Element not visible: " + locator);
        } catch (Exception e) {
            softAssert.fail("Element not found: " + locator);
        }
    }

    public static void softAssertText(SoftAssert softAssert, By locator, String expected) {
        try {
            String actual = DriverFactory.getDriver().findElement(locator).getText().trim();
            softAssert.assertTrue(actual.contains(expected),
                "Text should contain [" + expected + "] but was [" + actual + "]");
        } catch (Exception e) {
            softAssert.fail("Could not get text from: " + locator);
        }
    }
}
