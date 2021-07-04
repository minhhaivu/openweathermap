package element;

import driver.DriverGetter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Menu {
    private static final long TIMEOUT_IN_SECONDS = 30;
    private static WebDriver driver;

    public Menu getInstance() {
        driver = DriverGetter.getDriver("chrome");
        return this;
    }

    public void select(String menu) {
        By menuLocator = By.xpath("//a[contains(text(),'" + menu + "')]");
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(menuLocator));
        driver.findElement(menuLocator).click();

    }
}
