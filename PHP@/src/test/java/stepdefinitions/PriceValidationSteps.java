package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HotelSearchPage;
import pages.PriceValidationPage;

public class PriceValidationSteps {

    private PriceValidationPage pricePage;
    private HotelSearchPage hotelPage;

    @When("user searches hotels")
    public void user_searches_hotels() {

        hotelPage =
                new HotelSearchPage(DriverFactory.getDriver());

        // Open stays page
        hotelPage.goToHotelsPage();

        // Search hotel
        hotelPage.enterDestination("Dubai");

        hotelPage.selectCheckinDate();

        hotelPage.selectCheckoutDate();

        hotelPage.selectTravellers();

        hotelPage.clickSearch();

        // Initialize price page
        pricePage =
                new PriceValidationPage(DriverFactory.getDriver());
    }

    @Then("fetch all hotel prices")
    public void fetch_all_hotel_prices() {

        pricePage.fetchHotelPrices();
    }

    @And("print highest hotel price")
    public void print_highest_hotel_price() {

        pricePage.printHighestPrice();
    }

    @And("print lowest hotel price")
    public void print_lowest_hotel_price() {

        pricePage.printLowestPrice();
    }

    @And("print average hotel price")
    public void print_average_hotel_price() {

        pricePage.printAveragePrice();
    }

    @And("check duplicate hotel names")
    public void check_duplicate_hotel_names() {

        pricePage.checkDuplicateHotels();
    }
}