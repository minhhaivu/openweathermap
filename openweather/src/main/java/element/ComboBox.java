package element;

import action.WaitForAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComboBox {
    private static WebDriver pageDriver;
    private static WebElement comboBoxElement;

    public ComboBox(WebDriver driver, WebElement comboBoxElement) {
        this.pageDriver = driver;
        this.comboBoxElement = comboBoxElement;
    }

    public ComboBox(WebDriver driver, By comboBoxLocator) {
        this.pageDriver = driver;
        this.comboBoxElement = pageDriver.findElement(comboBoxLocator);
    }

    public void select(String value) {
        comboBoxElement.click();
        new WebDriverWait(pageDriver,20).until(WaitForAction.isElementPresent(Locator.xpathTagContainText("span", value)));
        comboBoxElement.findElement(Locator.xpathTagContainText("span", value)).click();
    }

}
