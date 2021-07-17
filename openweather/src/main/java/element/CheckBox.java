package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBox {
    private final WebDriver pageDriver;
    private final WebElement checkBoxElement;

    public CheckBox(WebDriver driver, WebElement elementCbk) {
        this.pageDriver = driver;
        this.checkBoxElement = elementCbk;
    }

    public CheckBox(WebDriver driver, By checkBoxLocator) {
        this.pageDriver = driver;
        this.checkBoxElement = pageDriver.findElement(checkBoxLocator);
    }

    public void select(boolean value) {
        if (checkBoxElement.isSelected() != value) {
            checkBoxElement.findElement(By.xpath("//span[@class='icon-checked']")).click();
        }
    }
}
