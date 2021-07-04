package element;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Table {
    public static String getTableCellValueByRowName(WebElement table,String rowName) {
        if(table.findElements(Locator.xpathTagContainText("td",rowName)).isEmpty()) {

            return table.findElement(Locator.xpathContain
                    ("//b[contains(text(), '",rowName,":')]/../following-sibling::td"))
                    .getText();
        } else {

            return table.findElement(Locator.xpathContain
                    ("//td[contains(text(), '",rowName,":')]/following-sibling::td"))
                    .getText();
        }

    }
}
