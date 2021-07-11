package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInput {

    public static void selectYear(WebElement calendar, int year) {
        By yearLocator = Locator.tdContainText(Integer.toString(year));

        if (calendar.findElements(yearLocator).isEmpty()) {
            int minYear = Integer.parseInt(Table.getTableCellValueByIndex(calendar, 1, 1));
            int maxYear = Integer.parseInt(Table.getTableCellValueByIndex(calendar, Table.getTableRow(calendar), Table.getTableColumn(calendar)));

            while (year < minYear) {
                calendar.findElement(By.xpath("//table/thead//th[1]")).click();
            }
            while (year < maxYear) {
                calendar.findElement(By.xpath("//table/thead//th[3]")).click();

            }
        }
            calendar.findElement(yearLocator).click();

        }

        public static void selectMonth (WebElement calendar, String month){
            By monthLocator = Locator.tdContainText(month);
            calendar.findElement(monthLocator).click();

        }

        public static void selectDate (WebElement calendar,int date){
            By dateLocator = Locator.tdContainText(Integer.toString(date));
            calendar.findElement(dateLocator).click();
        }

        public static void selectDate (WebElement calendar,int year, String month,int date){
            selectYear(calendar, year);
            selectMonth(calendar, month);
            selectDate(calendar, date);
        }

        public static Date stringToDate (String dateFormatPattern, String dateStr){
            return new SimpleDateFormat(
                    dateFormatPattern).parse(dateStr, new ParsePosition(0));
        }

        public static String dateToString (String dateFormatPattern, Date date){
            return new SimpleDateFormat(
                    dateFormatPattern).format(date);
        }

    }
