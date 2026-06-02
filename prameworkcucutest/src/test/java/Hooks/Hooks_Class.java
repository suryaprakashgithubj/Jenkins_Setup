package Hooks;

import java.net.URL;
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
            System.out.println("🔥 Starting Selenium Grid Session");

            ChromeOptions options = new ChromeOptions();

            // ✅ REQUIRED for Docker stability
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            // ❌ DO NOT use headless while debugging (keep OFF first)
            // options.addArguments("--headless=new");

            driver = new RemoteWebDriver(
                new URL("http://selenium-hub:4444/wd/hub"),
                options
            );

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            driver.get("https://tutorialsninja.com/demo/");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterTest() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Driver quit failed: " + e.getMessage());
        }
    }
}