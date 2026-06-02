package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

    features = "src/main/resources/Features1",

    glue = {"StepDefinition", "Hooks"},

    plugin = {
        "pretty",
        "html:target/htmlreports/report.html",
        "json:target/jsonreports/report.json",
        "junit:target/junitreports/report.xml"
    },

    monochrome = true
)

public class Runner extends AbstractTestNGCucumberTests {

}