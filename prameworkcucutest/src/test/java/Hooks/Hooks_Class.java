package Hooks;

import java.net.URI;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks_Class {

    public static WebDriver driver;

    @Before
    public void beforeTest() {
        try {
            System.out.println("🔥 Starting Selenium Grid Session...");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");

            // IMPORTANT: Selenium Grid URL
            driver = new RemoteWebDriver(
                URI.create("http://selenium-hub:4444/wd/hub").toURL(),
                options
            );

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            driver.get("https://tutorialsninja.com/demo/");

            System.out.println("✅ Browser launched successfully");

        } catch (Exception e) {
            System.out.println("❌ Failed to create WebDriver session");
            e.printStackTrace();

            // IMPORTANT: fail fast instead of continuing with null driver
            throw new RuntimeException(e);
        }
    }

    @After
    public void afterTest() {
        System.out.println("🧹 Closing browser session...");

        if (driver != null) {
            driver.quit();
        } else {
            System.out.println("⚠️ Driver was null, skipping quit()");
        }
    }
}