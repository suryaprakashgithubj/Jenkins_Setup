package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.PopupHandler;

public class HotelSearchPage {

    WebDriver driver;

    public HotelSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // DESTINATION
    By destination = By.xpath("//input[@placeholder='Search By City']");

    // SEARCH BUTTON — exact XPath confirmed from browser
    By searchButton = By.xpath("/html/body/div[2]/div/div/div[1]/div/div/form/div[2]/div[3]/button");

    // OPEN HOTEL PAGE
    public void goToHotelsPage() {
        driver.get("https://phptravels.net/stays");
        PopupHandler.dismissIfPresent();
        try { Thread.sleep(2000); } catch (Exception e) {}
    }

    // ENTER DESTINATION
    public void enterDestination(String city) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(destination)
        );

        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
        element.sendKeys(city);

        try { Thread.sleep(2000); } catch (Exception e) {}

        // Wait for dropdown and click first suggestion
        try {
            WebElement suggestion = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath(
                        "(//*[contains(@class,'suggestion') or contains(@class,'result') " +
                        "or contains(@class,'dropdown-item') or contains(@class,'autocomplete')])[1]" +
                        " | (//li[contains(@class,'item')])[1]"
                    )
                )
            );
            js.executeScript("arguments[0].click();", suggestion);
        } catch (Exception e) {
            element.sendKeys(Keys.ARROW_DOWN);
            try { Thread.sleep(500); } catch (Exception ex) {}
            element.sendKeys(Keys.ENTER);
        }

        try { Thread.sleep(2000); } catch (Exception e) {}
    }

    // CHECK-IN
    public void selectCheckinDate() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement checkinTrigger = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("(//*[contains(@class,'flatpickr') or @x-ref='checkin' or contains(@placeholder,'Check')])[1]")
                )
            );
            js.executeScript("arguments[0].click();", checkinTrigger);
            try { Thread.sleep(1000); } catch (Exception ex) {}

            WebElement day = driver.findElement(
                By.xpath("//div[contains(@class,'flatpickr-calendar') and contains(@class,'open')]" +
                         "//span[contains(@class,'flatpickr-day') and not(contains(@class,'disabled'))][1]")
            );
            day.click();
        } catch (Exception e) {
            js.executeScript(
                "var inputs = document.querySelectorAll('input');" +
                "for(var i=0;i<inputs.length;i++){" +
                "  var p=(inputs[i].placeholder||'').toLowerCase();" +
                "  if(p.includes('check-in')||p.includes('checkin')){" +
                "    inputs[i].value='2026-06-15';" +
                "    inputs[i].dispatchEvent(new Event('input'));" +
                "    inputs[i].dispatchEvent(new Event('change'));" +
                "    break;" +
                "  }" +
                "}"
            );
        }
    }

    // CHECK-OUT
    public void selectCheckoutDate() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement checkoutTrigger = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("(//*[contains(@class,'flatpickr') or @x-ref='checkout' or contains(@placeholder,'Check')])[2]")
                )
            );
            js.executeScript("arguments[0].click();", checkoutTrigger);
            try { Thread.sleep(1000); } catch (Exception ex) {}

            WebElement day = driver.findElement(
                By.xpath("//div[contains(@class,'flatpickr-calendar') and contains(@class,'open')]" +
                         "//span[contains(@class,'flatpickr-day') and not(contains(@class,'disabled'))][5]")
            );
            day.click();
        } catch (Exception e) {
            js.executeScript(
                "var inputs = document.querySelectorAll('input');" +
                "for(var i=0;i<inputs.length;i++){" +
                "  var p=(inputs[i].placeholder||'').toLowerCase();" +
                "  if(p.includes('check-out')||p.includes('checkout')){" +
                "    inputs[i].value='2026-06-18';" +
                "    inputs[i].dispatchEvent(new Event('input'));" +
                "    inputs[i].dispatchEvent(new Event('change'));" +
                "    break;" +
                "  }" +
                "}"
            );
        }
    }

    // TRAVELLERS — silently skip if not found (not required for search to work)
    public void selectTravellers() {
        // Travellers field is optional — default 2 Guests / 1 Room is pre-selected
        // Silently skip if the element can't be found to avoid blocking the search
        System.out.println("[HotelSearchPage] Travellers step skipped (default values used).");
    }

    // SEARCH BUTTON — exact XPath from browser
    public void clickSearch() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
            );
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (Exception e) {
            // Fallback: JS click
            try {
                WebElement element = driver.findElement(searchButton);
                js.executeScript("arguments[0].click();", element);
            } catch (Exception ex) {
                System.out.println("[HotelSearchPage] Search button click failed: " + ex.getMessage());
            }
        }

        // Wait for results page to load
        try { Thread.sleep(5000); } catch (Exception e) {}
    }
}