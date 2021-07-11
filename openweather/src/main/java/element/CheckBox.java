package element;

import org.openqa.selenium.WebElement;

public class CheckBox {
    private final WebElement pageDriver;
    private static final String PRE_LABEL = "//label[contains(text(),'";
    private static final String SUF_INPUT = "')]/input";
    private static final String SUF_SPAN = "')]//span";

    public CheckBox (WebElement driver) {
        this.pageDriver = driver;

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
