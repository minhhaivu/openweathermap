package element;

import action.WaitForAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComboBox {
    private final WebDriver driver;

    public ComboBox(WebDriver driver) {
        this.driver = driver;
    }

    public void select(WebElement comboBox, String value) {
        comboBox.click();
        new WebDriverWait(driver,20).until(WaitForAction.isElementPresent(Locator.xpathTagContainText("span", value)));
        comboBox.findElement(Locator.xpathTagContainText("span", value)).click();
    }
}
