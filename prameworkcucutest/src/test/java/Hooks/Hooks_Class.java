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
    public void beforeTest() throws Exception {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        String remoteUrl = System.getProperty(
                "selenium.remote.url",
                "http://host.docker.internal:4444/wd/hub"
        );

        driver = new RemoteWebDriver(new URL(remoteUrl), options);

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/");
    }

    @After
    public void afterTest() {

        if (driver != null) {
            driver.quit();
        }
    }
}