package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    public static String capture(WebDriver driver, String scenarioName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_DIR + fileName;

        try {
            new File(SCREENSHOT_DIR).mkdirs();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get(filePath));
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }

        return filePath;
    }
}
