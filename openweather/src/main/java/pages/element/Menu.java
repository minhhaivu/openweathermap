package pages.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menu {
    private static final long TIMEOUT_IN_SECONDS = 30;
    private static WebDriver driver;

    public Menu(WebDriver driver) {
        this.driver = driver;
    }

    public void select(String menu) {
        By menuLocator = By.xpath("//a[contains(text(),'" + menu + "')]");
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(menuLocator));
        driver.findElement(menuLocator).click();
    }
}
