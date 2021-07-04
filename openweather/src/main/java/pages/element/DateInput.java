package pages.element;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateInput {

    public static void selectYear(WebElement calendar, int year) {
        By yearLocator = Locator.tdContainText(Integer.toString(year));
        calendar.findElement(yearLocator).click();
        //Need to improve for year is not present after clicking

    }

    public static void selectMonth(WebElement calendar, String month) {
        By monthLocator = Locator.tdContainText(month);
        calendar.findElement(monthLocator).click();

    }

    public static void selectDate(WebElement calendar, int date) {
        By dateLocator = Locator.tdContainText(Integer.toString(date));
        calendar.findElement(dateLocator).click();
    }

    public static Date stringToDate(String dateFormatPattern, String dateStr) {
        return new SimpleDateFormat(
                dateFormatPattern).parse(dateStr, new ParsePosition(0));
    }

    public static String dateToString(String dateFormatPattern, Date date) {
        return new SimpleDateFormat(
                dateFormatPattern).format(date);
    }

}
