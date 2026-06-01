package stepdefinitions;

import org.testng.Assert;

import base.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HotelSearchPage;

public class HotelSearchSteps {

    HotelSearchPage hotel =
            new HotelSearchPage(DriverFactory.getDriver());

    @When("user enters destination")
    public void user_enters_destination() {

        try {

            hotel.enterDestination("Dubai");

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user selects checkin date")
    public void user_selects_checkin_date() {

        try {

            hotel.selectCheckinDate();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user selects checkout date")
    public void user_selects_checkout_date() {

        try {

            hotel.selectCheckoutDate();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user selects travellers")
    public void user_selects_travellers() {

        try {

            hotel.selectTravellers();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("clicks search button")
    public void clicks_search_button() {

        try {

            hotel.clickSearch();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @Then("validate hotel results displayed")
    public void validate_hotel_results_displayed() {

        Assert.assertTrue(

                DriverFactory.getDriver()
                        .getPageSource()
                        .contains("stays")

        );
    }
}