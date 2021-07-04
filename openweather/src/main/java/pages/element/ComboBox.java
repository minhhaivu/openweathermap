package pages.element;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComboBox {
    public static void select(WebElement comboBox, String value) {
        comboBox.click();
        comboBox.findElement(Locator.xpathTagContainText("span", value)).click();
    }
}
