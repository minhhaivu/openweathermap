package element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button {
    private static WebDriver pageDriver;
    private static WebElement buttonElement;
    private static WebElement popUpElement;

    public Button(WebDriver driver, WebElement buttonElement) {
        this.pageDriver = driver;
        this.buttonElement = buttonElement;
    }

    public void click() {
        new WebDriverWait(pageDriver, 10)
                .until(ExpectedConditions.visibilityOf(buttonElement));
        buttonElement.click();
    }

    public void doubleClick() {
        new WebDriverWait(pageDriver, 10)
                .until(ExpectedConditions.visibilityOf(buttonElement));
        Actions action = new Actions(pageDriver);
        action.doubleClick(buttonElement).perform();
    }

}
