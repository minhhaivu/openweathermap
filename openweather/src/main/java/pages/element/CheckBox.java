package pages.element;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CheckBox {
    private static final String PRE_LABEL = "//label[contains(text(),'";
    private static final String SUF_INPUT = "')]/input";
    private static final String SUF_SPAN = "')]//span";

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
