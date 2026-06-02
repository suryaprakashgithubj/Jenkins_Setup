package Hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks_Class {

    public static WebDriver driver;

    @Before
    public void beforeTest() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/");
    }

    @After
    public void afterTest() {

        driver.quit();
    }
}