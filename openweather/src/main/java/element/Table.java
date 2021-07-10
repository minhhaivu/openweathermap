package element;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
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

    public static String getTableCellValueByIndex(WebElement table, int rowIdx, int colIdx) {

        return table.findElement(By.xpath("//table/tbody/tr["+rowIdx+"]/td[" +colIdx+ "]")).toString();
    }

    public static int getTableRow(WebElement table) {

        return table.findElements(By.xpath("//table/tbody/tr")).size();
    }


    public static int getTableColumn(WebElement table) {

        return table.findElements(By.xpath("//table/tbody/tr/td")).size();
    }
}
