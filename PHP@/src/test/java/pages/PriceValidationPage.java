package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PriceValidationPage {

    WebDriver driver;

    public PriceValidationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Hotel prices
    By hotelPrices =
            By.xpath("//strong[contains(text(),'USD')]");

    // Hotel names
    By hotelNames =
            By.xpath("//h5 | //h4");

    List<Double> prices = new ArrayList<>();

    // Fetch prices
    public void fetchHotelPrices() {

        List<WebElement> priceList =
                driver.findElements(hotelPrices);

        prices.clear();

        for (WebElement price : priceList) {

            try {

                String text =
                        price.getText()
                                .replace("USD", "")
                                .replace(",", "")
                                .trim();

                double value =
                        Double.parseDouble(text);

                prices.add(value);

                System.out.println(value);

            } catch (Exception e) {

                System.out.println("Invalid price skipped");
            }
        }
    }

    // Highest
    public void printHighestPrice() {

        if (prices.size() > 0) {

            double max =
                    Collections.max(prices);

            System.out.println(
                    "Highest Price: " + max);
        }
    }

    // Lowest
    public void printLowestPrice() {

        if (prices.size() > 0) {

            double min =
                    Collections.min(prices);

            System.out.println(
                    "Lowest Price: " + min);
        }
    }

    // Average
    public void printAveragePrice() {

        if (prices.size() > 0) {

            double sum = 0;

            for (double p : prices) {

                sum += p;
            }

            double avg =
                    sum / prices.size();

            System.out.println(
                    "Average Price: " + avg);
        }
    }

    // Duplicate Hotels
    public void checkDuplicateHotels() {

        List<WebElement> hotels =
                driver.findElements(hotelNames);

        List<String> names =
                new ArrayList<>();

        boolean duplicate = false;

        for (WebElement hotel : hotels) {

            String name =
                    hotel.getText();

            if (names.contains(name)) {

                duplicate = true;

                System.out.println(
                        "Duplicate Hotel: " + name);
            }

            names.add(name);
        }

        if (!duplicate) {

            System.out.println(
                    "No Duplicate Hotels");
        }
    }
}