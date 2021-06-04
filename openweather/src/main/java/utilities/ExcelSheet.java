package utilities;

import lombok.Getter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.WARNING;

@Getter
public class ExcelSheet {

    private XSSFWorkbook workbook=null;
    private XSSFSheet sheet=null;

    public ExcelSheet(String sheetName, String xlFilePath) {
        try (XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(xlFilePath))) {
            workbook = workBook;
            sheet = workbook.getSheet(sheetName);
        }
        catch (IOException e) {
            Logger logger = Logger.getLogger(ExcelSheet.class.getName());
            logger.log(Level.WARNING,"Error when reading the file: %s ",e);
        }
    }

    public String getCellData(int colNum, int rowNum) {
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell cell = row.getCell(colNum);
        if (cell != null) {
            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
        }
        return "";
    }

    public Object[][] getSheetData(String sheetName, String xlFilePath) {
        ExcelSheet esh = new ExcelSheet(sheetName, xlFilePath);
        int row = esh.sheet.getLastRowNum();
        int col = esh.sheet.getRow(0).getLastCellNum();
        Object[][] sheetData = new Object[row][col];
        for (int i = 1; i < row; i++)
            for (int j = 0; j < col; j++) {
                sheetData[i - 1][j] = esh.getCellData(j, i);
            }

        return sheetData;
    }

}
