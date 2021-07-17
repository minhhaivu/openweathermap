package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Table {
    private final WebDriver pageDriver;
    private final WebElement tableElement;

    private static final String B_ROW_PRE = "//b[contains(text(),'";
    private static final String B_ROW_SUF = ":')]/../following-sibling::td";
    private static final String TD_ROW_PRE = "//td[contains(text(),'";
    private static final String TD_ROW_SUF = ":')]/following-sibling::td";

    private static final By row = By.xpath(".//table/tbody/tr");
    private static final By column = By.xpath("./td");

    private WebElement cell(int rowIdx, int colIdx) {
        return tableElement.findElement(By.xpath(".//table/tbody/tr[" + rowIdx + "]/td[" + colIdx + "]"));
    }

    public Table(WebDriver driver, WebElement table) {
        this.pageDriver = driver;
        this.tableElement = table;
    }

    public String getRowValue(String rowName) {
        return rowValueWebElement(rowName).getText();
    }

    public int getTableRowCount() {
        return tableElement.findElements(row).size();
    }

    public int getTableColumnCount() {
        return tableElement.findElement(row).findElements(column).size();
    }

    public String getTableCellValue(int rowIdx, int colIdx) {
        return cell(rowIdx,colIdx).getText();
    }

    private boolean isRowHeaderBold(String rowName) {
        return tableElement.findElements(Locator.xpathTagContainText("td", rowName)).isEmpty();
    }

    private WebElement rowValueWebElement(String rowName) {
        return isRowHeaderBold(rowName)
                ? tableElement.findElement(By.xpath(B_ROW_PRE + rowName + B_ROW_SUF))
                : tableElement.findElement(By.xpath(TD_ROW_PRE + rowName + TD_ROW_SUF));
    }

}
