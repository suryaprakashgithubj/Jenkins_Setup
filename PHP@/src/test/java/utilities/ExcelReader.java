package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<String[]> readData(String filePath, String sheetName) {
        List<String[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getLastRowNum();

            for (int i = 1; i <= rows; i++) { // skip header row
                Row row = sheet.getRow(i);
                if (row == null) continue;

                int cols = row.getLastCellNum();
                String[] rowData = new String[cols];

                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = (cell == null) ? "" : cell.toString().trim();
                }
                data.add(rowData);
            }

        } catch (IOException e) {
            throw new RuntimeException("Cannot read Excel file: " + e.getMessage());
        }

        return data;
    }
}
