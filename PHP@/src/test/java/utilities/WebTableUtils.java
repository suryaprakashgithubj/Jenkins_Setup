package utilities;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

/**
 * WebTableUtils — Q16: Dynamic Web Table Challenge
 *
 * Reads booking/results table dynamically into a List<Map<String,String>>.
 * Validates: duplicate rows, highest/lowest booking amount, table→Map conversion.
 */
public class WebTableUtils {

    /**
     * Reads a table and returns List of row maps: column_header → cell_value
     * This is the "Convert table data into Map" requirement of Q16.
     */
    public static List<Map<String, String>> readTable(By tableLocator) {
        WebDriver driver = DriverFactory.getDriver();
        List<Map<String, String>> tableData = new ArrayList<>();

        try {
            WebElement table = driver.findElement(tableLocator);
            List<WebElement> headers = table.findElements(By.xpath(".//thead//th | .//tr[1]//th"));
            List<WebElement> rows    = table.findElements(By.xpath(".//tbody//tr"));

            List<String> columnNames = new ArrayList<>();
            for (WebElement h : headers) {
                columnNames.add(h.getText().trim());
            }

            // Loop through rows (Q16 mandatory)
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int i = 0; i < cells.size() && i < columnNames.size(); i++) {
                    rowMap.put(columnNames.get(i), cells.get(i).getText().trim());
                }
                if (!rowMap.isEmpty()) {
                    tableData.add(rowMap);
                }
            }
        } catch (Exception e) {
            System.out.println("[WebTableUtils] Table read error: " + e.getMessage());
        }

        return tableData;
    }

    /** Q16: Find duplicate rows */
    public static List<Map<String, String>> findDuplicateRows(List<Map<String, String>> data) {
        List<Map<String, String>> duplicates = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (Map<String, String> row : data) {
            String key = row.toString();
            if (!seen.add(key)) {
                duplicates.add(row);
            }
        }
        System.out.println("[WebTableUtils] Duplicate rows: " + duplicates.size());
        return duplicates;
    }

    /** Q16: Highest booking amount from table */
    public static int getHighestAmount(List<Map<String, String>> data, String amountColumn) {
        return data.stream()
            .mapToInt(row -> {
                String raw = row.getOrDefault(amountColumn, "0").replaceAll("[^0-9]", "");
                return raw.isEmpty() ? 0 : Integer.parseInt(raw);
            })
            .max().orElse(0);
    }

    /** Q16: Lowest booking amount from table */
    public static int getLowestAmount(List<Map<String, String>> data, String amountColumn) {
        return data.stream()
            .mapToInt(row -> {
                String raw = row.getOrDefault(amountColumn, "0").replaceAll("[^0-9]", "");
                return raw.isEmpty() ? 0 : Integer.parseInt(raw);
            })
            .filter(v -> v > 0)
            .min().orElse(0);
    }
}
