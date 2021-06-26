package pages;

import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

@NoArgsConstructor
public abstract class AbstractPage {
    private static final long TIMEOUT_IN_SECONDS = 20;
    protected WebDriver driver;

    public void close() {
        driver.close();
        driver.quit();
    }

    public void sleep(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public ExpectedCondition<Boolean> isPresent(By by) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    driver.findElement(by);
                    return true;
                } catch (NoSuchElementException e) {
                    // do nothing
                }
                return false;
            }
        };
    }
}
