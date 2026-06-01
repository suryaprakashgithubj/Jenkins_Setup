package stepdefinitions;

import org.testng.Assert;

import base.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.BookingPage;
import pages.HotelSearchPage;

public class BookingSteps {

    HotelSearchPage hotel =
            new HotelSearchPage(DriverFactory.getDriver());

    BookingPage booking =
            new BookingPage();

    @And("user enters booking destination")
    public void user_enters_destination() {

        try {

            hotel.goToHotelsPage();

            hotel.enterDestination("Dubai");

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user selects booking checkin date")
    public void user_selects_checkin_date() {

        try {

            hotel.selectCheckinDate();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user selects booking checkout date")
    public void user_selects_checkout_date() {

        try {

            hotel.selectCheckoutDate();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user selects booking travellers")
    public void user_selects_travellers() {

        try {

            hotel.selectTravellers();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("clicks booking search button")
    public void clicks_search_button() {

        try {

            hotel.clickSearch();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user clicks book now")
    public void user_clicks_book_now() {

        try {

            booking.clickBookNow();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("user confirms booking")
    public void user_confirms_booking() {

        try {

            booking.confirmBooking();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @Then("validate booking confirmation")
    public void validate_booking_confirmation() {

        Assert.assertTrue(
                DriverFactory.getDriver()
                        .getPageSource()
                        .contains("Booking")
        );
    }
}