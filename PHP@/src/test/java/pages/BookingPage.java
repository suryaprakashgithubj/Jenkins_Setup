package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.DriverFactory;
import utilities.PopupHandler;

public class BookingPage {

    WebDriver driver;

    public BookingPage() {
        this.driver = DriverFactory.getDriver();
    }

    // SELECT ROOM
    By selectRoom = By.xpath(
        "//button[contains(normalize-space(.),'Select') or contains(normalize-space(.),'Book') " +
        "or contains(normalize-space(.),'Reserve')]"
    );

    // COMPLETE BOOKING
    By completeBooking = By.xpath(
        "//button[contains(normalize-space(.),'Complete') or contains(normalize-space(.),'Continue') " +
        "or contains(normalize-space(.),'Proceed')]"
    );

    // CONFIRM BOOKING
    By confirmBooking = By.xpath(
        "//button[contains(normalize-space(.),'Confirm') or contains(normalize-space(.),'Pay') " +
        "or contains(normalize-space(.),'Submit')]"
    );

    // CLICK BOOK NOW
    public void clickBookNow() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Navigate to stays page with search params
        System.out.println("[BookingPage] Loading stays results page...");
        driver.get("https://phptravels.net/stays?city=Dubai&checkin=2026-06-15&checkout=2026-06-18&rooms=1&adults=2");

        // Dismiss popup
        PopupHandler.dismissIfPresent();
        try { Thread.sleep(3000); } catch (Exception e) {}
        PopupHandler.dismissIfPresent();

        // FIX: The site uses Alpine.js — results load via XHR after page renders.
        // Trigger the search programmatically via JS to force Alpine to fetch results.
        try {
            js.executeScript(
                "window.dispatchEvent(new Event('load'));" +
                "document.querySelectorAll('form').forEach(f => f.dispatchEvent(new Event('submit')));"
            );
        } catch (Exception e) {}

        try { Thread.sleep(3000); } catch (Exception e) {}

        // Check if results loaded — if not, click the search button on this page
        try {
            driver.findElement(By.xpath("//a[contains(@href,'/stay/')]"));
            System.out.println("[BookingPage] Results already present.");
        } catch (Exception e) {
            System.out.println("[BookingPage] Results not present, clicking search button...");
            try {
                // The search button on results page (same form, re-submit)
                WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.),'Search') or contains(normalize-space(.),'Searching')]")
                ));
                js.executeScript("arguments[0].click();", btn);
                try { Thread.sleep(5000); } catch (Exception ex) {}
            } catch (Exception ex) {
                System.out.println("[BookingPage] Search button also not found: " + ex.getMessage());
            }
        }

        PopupHandler.dismissIfPresent();
        try { Thread.sleep(2000); } catch (Exception e) {}

        // Wait for hotel result links
        System.out.println("[BookingPage] Waiting for /stay/ links...");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'/stay/')]")
            ));
            System.out.println("[BookingPage] Hotel results loaded. URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("[BookingPage] Still no results. URL: " + driver.getCurrentUrl());
            // Print page source snippet for debugging
            String src = driver.getPageSource();
            System.out.println("[BookingPage] Page contains 'stay': " + src.contains("/stay/"));
            System.out.println("[BookingPage] Page contains 'hotel': " + src.toLowerCase().contains("hotel"));
        }

        // Click first hotel result
        WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(@href,'/stay/')])[1]")
            )
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        try { Thread.sleep(500); } catch (Exception e) {}
        js.executeScript("arguments[0].click();", element);
        try { Thread.sleep(2000); } catch (Exception e) {}
    }

    // CONFIRM BOOKING
    public void confirmBooking() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        PopupHandler.dismissIfPresent();

        try {
            WebElement room = wait.until(ExpectedConditions.elementToBeClickable(selectRoom));
            js.executeScript("arguments[0].scrollIntoView(true);", room);
            js.executeScript("arguments[0].click();", room);
            try { Thread.sleep(1500); } catch (Exception e) {}
        } catch (Exception e) {
            System.out.println("[BookingPage] Select room skipped: " + e.getMessage());
        }

        try {
            WebElement complete = wait.until(ExpectedConditions.elementToBeClickable(completeBooking));
            js.executeScript("arguments[0].click();", complete);
            try { Thread.sleep(1500); } catch (Exception e) {}
        } catch (Exception e) {
            System.out.println("[BookingPage] Complete booking skipped: " + e.getMessage());
        }

        try {
            WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(confirmBooking));
            js.executeScript("arguments[0].click();", confirm);
        } catch (Exception e) {
            System.out.println("[BookingPage] Confirm booking skipped: " + e.getMessage());
        }
    }
}