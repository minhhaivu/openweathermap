package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBox {
    private final WebDriver pageDriver;
    private WebElement checkBoxElement;
    private static final String PRE_LABEL = "//label[contains(text(),'";
    private static final String SUF_INPUT = "')]/input";
    private static final String SUF_SPAN = "')]//span";

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

    public static void select(WebElement checkBoxList, String checkBoxName) {
        if (!checkBoxList.findElement(Locator.xpathContain(PRE_LABEL, checkBoxName, SUF_INPUT)).isSelected()) {
            checkBoxList.findElement(Locator.xpathContain(PRE_LABEL, checkBoxName, SUF_SPAN)).click();
        }
    }

    public static void unselect(WebElement checkBoxList, String checkBoxName) {
        if (checkBoxList.findElement(Locator.xpathContain(PRE_LABEL, checkBoxName, SUF_INPUT)).isSelected()) {
            checkBoxList.findElement(Locator.xpathContain(PRE_LABEL, checkBoxName, SUF_SPAN)).click();
        }
    }

}
