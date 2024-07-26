package pages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataProvider {

    @DataProvider(name = "excelData1")
    public Object[][] getExcelData() throws IOException {
        String fileName = "E:\\project\\web automation\\src\\test\\resources\\testdata.xlsx"; // Update file path
        String sheetName = "testdata"; // Update sheet name
        List<Object[]> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet " + sheetName + " does not exist.");
            }

            int noOfRows = sheet.getPhysicalNumberOfRows();
            if (noOfRows < 2) {
                throw new IOException("Sheet " + sheetName + " does not have enough rows.");
            }

            for (int i = 1; i < noOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    String methodName = row.getCell(0).getStringCellValue();
                    String personalNumber = row.getCell(1).getStringCellValue();
                    String password = row.getCell(2).getStringCellValue();
                    dataList.add(new Object[]{methodName, personalNumber, password});
                }
            }
        } catch (Exception e) {
            System.out.println("The exception is: " + e.getMessage());
        }

        return dataList.toArray(new Object[0][]);
    }

    public static List<String> readMethodNamesFromExcel() {

        String fileName = "E:\\project\\web automation\\src\\test\\resources\\testdata.xlsx"; // Update file path
        String sheetName = "testdata"; //
        List<String> methodNames = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    methodNames.add(cell.getStringCellValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return methodNames;
    }



    @DataProvider(name = "excelData")
    public static Object[][] excelData(Method method) {
        String excelFilePath = "E:\\project\\web automation\\src\\test\\resources\\testdata.xlsx";
        String methodName = method.getName();
        List<Object[]> data = getExcelData(excelFilePath, methodName);
        return data.toArray(new Object[0][0]);
    }

    private static List<Object[]> getExcelData(String filePath, String methodName) {
        List<Object[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String currentMethodName = row.getCell(0).getStringCellValue();
                if (currentMethodName.equals(methodName)) {
                    String personalNumber = row.getCell(1).getStringCellValue();
                    String password = row.getCell(2).getStringCellValue();
                    data.add(new Object[]{currentMethodName, personalNumber, password});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
