package utilities.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;

import java.io.File;
import java.util.*;

public class ExcelFileReader {

    private final static File folder = new File("src/test/resources/testData");
    private static String testMethodName;


    public static List<TestData> readTestData(String testName) {
        testMethodName = testName;
         return readExecel(folder);
    }


    private static List<TestData> readExecel(final File folder) {
        TestData testData;
        int columnWithTestCasesIndex = 0;
        List<TestData> result = new ArrayList<TestData>();
        try {

            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    readExecel(folder);
                }

                //Workbook workbook = new XSSFWorkbook(fileEntry);
                Workbook workbook = WorkbookFactory.create(fileEntry);
                Sheet worksheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = worksheet.iterator();
                Row row;
                Row header;
                Map<String, Integer> headerMap = new HashMap<String, Integer>();
                //go through all rows
                for (int rowIndex = 0; rowIterator.hasNext() && (rowIndex <= worksheet.getLastRowNum()); rowIndex++) {
                    row = CellUtil.getRow(rowIndex, worksheet);
                    //define table headers
                    if (rowIndex == 0) {
                        header = row;

                        for (int i = 0; header.cellIterator().hasNext() && (i < header.getLastCellNum()); i++) {
                            Cell currentCell = header.getCell(i);
                                headerMap.putIfAbsent(currentCell.getStringCellValue(), currentCell.getColumnIndex());

                        }
                        continue;
                    }
                    //find only rows which mapped with test methods
                    if (row.getCell(columnWithTestCasesIndex).getStringCellValue().equals(testMethodName)) {
                        System.out.println("[" + testMethodName + "] ran with test data:");
                        testData = new TestData();
                        //go thought cells in row, but read values only from cells with valid headers
                        for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
                            Cell cell = CellUtil.getCell(row, entry.getValue());
                            testData.setTestDataByHeaders(entry.getKey(), getCellValue(cell));
                            System.out.println(entry.getKey() + " : " + getCellValue(cell));
                        }
                        result.add(testData);
                        System.out.println();
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    private static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue() + "";
            case BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case BLANK:
                return "";
            case FORMULA:
                return cell.getCellFormula() + "";
            default:
                return cell.getStringCellValue();

        }
    }

}
