package action;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WaitForAction {
    @SuppressWarnings("java:S2142")
    public static void sleep(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            //do nothing
        }
    }

    public static ExpectedCondition<Boolean> isElementPresent(final By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    driver.findElement(locator);
                    return true;
                } catch (NoSuchElementException e) {
                    // do nothing
                }
                return false;
            }
        };
    }
}
