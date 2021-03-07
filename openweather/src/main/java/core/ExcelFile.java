package core;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFile {

public FileInputStream fis = null;
public XSSFWorkbook workbook = null;
public XSSFSheet sheet = null;
public XSSFRow row = null;
public XSSFCell cell = null;
String xlFilePath;


public ExcelFile(String xlFilePath) throws Exception
{
    this.xlFilePath = xlFilePath;
    fis = new FileInputStream(xlFilePath);
    workbook = new XSSFWorkbook(fis);
    fis.close();
}

public int getRowCount(String sheetName)
{
    sheet = workbook.getSheet(sheetName);
    int rowCount = sheet.getLastRowNum()+1;
    return rowCount;
}

public int getColumnCount(String sheetName)
{
    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(0);
    int colCount = row.getLastCellNum();
    return colCount;
}

public String getCellData(String sheetName,int colNum,int rowNum)
{
    try
    {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        if (cell!=null)
        {
        if(cell.getCellType() == CellType.STRING)
            return cell.getStringCellValue();
        else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
        {
            String cellValue  = String.valueOf(cell.getNumericCellValue());
            if (DateUtil.isCellDateFormatted(cell))
            {
                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Date date = cell.getDateCellValue();
                cellValue = df.format(date);
            }
            return cellValue;
        }else if(cell.getCellType() == CellType.BLANK)
            return "";
        else
            return String.valueOf(cell.getBooleanCellValue());
    }
        else return "";
    }
    catch(Exception e)
    {
        e.printStackTrace();
        return "row "+rowNum+" or column "+colNum +" does not exist  in Excel";
    }
}

public Object[][] getExcelData(String xlFilePath, String sheetName) throws Exception
{
    Object[][] excelData = null;
    ExcelFile etd = null;
    etd = new ExcelFile(xlFilePath);
    int rows = etd.getRowCount(sheetName);
    int columns = etd.getColumnCount(sheetName);
             
    excelData = new Object[rows-1][columns];
     
    for(int i=1; i<rows; i++)
    {
        for(int j=0; j<columns; j++)
        {
            excelData[i-1][j] = etd.getCellData(sheetName, j, i);
        }
         
    }
    return excelData;
}
	 
}
