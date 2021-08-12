package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInput {
    private final WebDriver pageDriver;
    private final WebElement calendar;
    private final WebElement backwardBtn;
    private final WebElement forwardBtn;

    public DateInput(WebDriver driver, WebElement calendar) {
        this.pageDriver = driver;
        this.calendar = calendar;
        this.backwardBtn = calendar.findElement(By.xpath(".//table/thead//th[1]"));
        this.forwardBtn = calendar.findElement(By.xpath(".//table/thead//th[3]"));
    }

    public void selectYear(int year) {
        By yearLocator = Locator.tdContainText(Integer.toString(year));
        if (calendar.findElements(yearLocator).isEmpty()) {
            Table table = new Table(pageDriver, calendar);
            int runTime = 0;
            int minYear = Integer.parseInt(table.getTableCellValue(1, 1));
            int maxYear = Integer.parseInt
                    (table.getTableCellValue(table.getTableRowCount(), table.getTableColumnCount()));
            while ((year < minYear) && (runTime < 8)) {
                runTime++;
                backwardBtn.click();
                minYear = Integer.parseInt(table.getTableCellValue(1, 1));
            }
            while ((year > maxYear) && (runTime < 8)) {
                runTime++;
                forwardBtn.click();
                maxYear = Integer.parseInt
                        (table.getTableCellValue(table.getTableRowCount(), table.getTableColumnCount()));
            }
        }
        calendar.findElement(yearLocator).click();
    }

    public void selectMonth(String month) {
        By monthLocator = Locator.tdContainText(month);
        calendar.findElement(monthLocator).click();
    }

    public void selectDate(int date) {
        By dateLocator = By.xpath(".//td[contains(text(),'" + date + "')]");
        calendar.findElement(dateLocator).click();
    }

    public void selectDate(int year, String month, int date) {
        selectYear(year);
        selectMonth(month);
        selectDate(date);
    }

    public static Date stringToDate(String dateFormatPattern, String dateStr) {
        return new SimpleDateFormat(
                dateFormatPattern).parse(dateStr, new ParsePosition(0));
    }

    public static Date stringToDate(String dateStr) {
        return stringToDate("dd-MMM-yyyy", dateStr);
    }

    public static String dateToString(String dateFormatPattern, Date date) {
        return new SimpleDateFormat(
                dateFormatPattern).format(date);
    }

    public static String dateToString(Date date) {
        return dateToString("dd-MMM-yyyy",date);
    }

    public static Date changeFormat(String newFormat, Date date) {
        return DateInput.stringToDate(newFormat,DateInput.dateToString(newFormat,date));
    }

}
