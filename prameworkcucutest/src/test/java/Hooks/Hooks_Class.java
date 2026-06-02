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
            System.out.println("🔥 Starting test session...");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless=new");

            driver = new RemoteWebDriver(
                URI.create("http://selenium-hub:4444").toURL(),
                options
            );

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            driver.get("https://tutorialsninja.com/demo/");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("WebDriver init failed", e);
        }
    }

    @After
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}