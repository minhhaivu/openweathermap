package pages;

import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor
public abstract class AbstractPage {
    protected WebDriver driver;

    public void close() {
        driver.close();
        driver.quit();
    }
}
