package stepdefinitions;

import io.cucumber.java.en.Given;

/**
 * Shared step used by ALL feature files.
 * "user launches browser" is defined ONCE here only.
 * HooksClass @Before already opens the browser; this step is a no-op marker.
 */
public class CommonSteps {

    @Given("user launches browser")
    public void user_launches_browser() {
        // Browser is already launched and baseUrl opened by HooksClass @Before
        System.out.println("Browser ready.");
    }
}
