package pages;

import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor
public abstract class AbstractPage {
    protected WebDriver pageDriver;

    public void close() {
        pageDriver.quit();
    }
}
