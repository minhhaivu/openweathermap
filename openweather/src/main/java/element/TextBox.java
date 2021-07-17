package element;

import action.WaitForAction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBox {
    private final WebDriver pageDriver;
    private final WebElement textBoxElement;

    public TextBox(WebDriver driver, WebElement textBox) {
        this.pageDriver=driver;
        this.textBoxElement = textBox;
    }

    public void enter(String value) {
        textBoxElement.click();
        textBoxElement.sendKeys(value);
    }

    public void enter(String value, CharSequence keyToApply) {
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

}
