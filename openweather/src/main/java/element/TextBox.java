package element;

import action.WaitForAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextBox {
    private final WebDriver pageDriver;
    private final WebElement textBoxElement;
    private final WebElement popUpElement;

    public TextBox(WebDriver driver, WebElement textBox) {
        this.pageDriver=driver;
        this.textBoxElement = textBox;
        popUpElement = null;
    }

    public TextBox(WebDriver driver, WebElement textBoxElement, WebElement popUpElement) {
        this.pageDriver = driver;
        this.textBoxElement = textBoxElement;
        this.popUpElement = popUpElement;
    }

    public void enter(String value) {
        textBoxElement.click();
        textBoxElement.sendKeys(value);
    }

    public void enter(String value, CharSequence keyToApply) {
        new WebDriverWait(pageDriver,10)
                .until(ExpectedConditions.visibilityOf(textBoxElement));
        textBoxElement.click();
        textBoxElement.sendKeys(value);
        textBoxElement.sendKeys(keyToApply);
    }

    public void enter(String value, int waitTime, CharSequence keyToApply) {
        textBoxElement.click();
        textBoxElement.sendKeys(value);
        WaitForAction.sleep(waitTime);
        textBoxElement.sendKeys(keyToApply);
    }

    public void selectPopUpOption(String option) {
        textBoxElement.click();
        popUpElement.findElement(By.xpath("//*[contains(text(),'" + option + "')]")).click();
    }

}
