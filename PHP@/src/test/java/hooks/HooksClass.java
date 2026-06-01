package hooks;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.PopupHandler;
import utilities.ScreenshotUtils;

public class HooksClass {

    @Before(order = 1)
    public void setup(Scenario scenario) {
        System.out.println("\n========== START: " + scenario.getName() + " ==========");
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
        // Dismiss the PHPTravels demo popup that appears on every page load
        PopupHandler.dismissIfPresent();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        String status = scenario.getStatus().toString();

        // Q11: Screenshot on PASS, FAIL, and SKIPPED (all three required)
        try {
            byte[] screenshotBytes = ((org.openqa.selenium.TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);

            // Attach inline to Cucumber / Extent Report
            String label = status + "_" + scenario.getName() + "_"
                    + new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            scenario.attach(screenshotBytes, "image/png", label);

            // Also save to disk with timestamp (Q11 requirement)
            ScreenshotUtils.capture(DriverFactory.getDriver(), status + "_" + scenario.getName());

        } catch (Exception e) {
            System.out.println("[HooksClass] Screenshot error: " + e.getMessage());
        }

        if (scenario.isFailed()) {
            System.out.println("[FAILED] " + scenario.getName());
        }

        try {
            DriverFactory.quitDriver();
        } catch (Exception e) {
            System.out.println("[HooksClass] Driver quit error: " + e.getMessage());
        }

        System.out.println("========== END: " + scenario.getName()
                + " | Status: " + status + " ==========\n");
    }
}
