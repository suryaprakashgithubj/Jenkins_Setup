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
            System.out.println("🔥 HOOKS EXECUTED");

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless=new");

            driver = new RemoteWebDriver(
                new URL("http://selenium-hub:4444"),
                options
            );

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://tutorialsninja.com/demo/");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}