package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
@CucumberOptions(
        
        features = "src/test/resources/features",

        glue = {"stepdefinitions", "hooks"},

        tags = "not @Skip",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/CucumberReport.html",
                "json:target/cucumber-reports/CucumberReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true

)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
