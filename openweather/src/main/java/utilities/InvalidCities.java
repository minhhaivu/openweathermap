package utilities;

import org.testng.annotations.DataProvider;

public class InvalidCities {
    @DataProvider(name = "invalid-cities")
    public Object[][] getInvalidCities() {
        final String sheetName = "invalidCities";
        final String xlFileName = "src/main/resources/invalidCities.xlsx";
        ExcelSheet esh = new ExcelSheet(sheetName, xlFileName);
        return esh.getSheetData(sheetName, xlFileName);
    }
}
