package pages;

import element.ComboBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoricalDataArchivesPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(text(),'Select year')]" +
            "//following-sibling::div[@class='dropdown-selector']")
    private WebElement selectYearCbb;

    @FindBy(xpath = "//div[contains(text(),'Select state')]" +
            "//following-sibling::div[@class='dropdown-selector']")
    private WebElement selectStateCbb;

    public HistoricalDataArchivesPage(WebDriver driver) {
        pageDriver = driver;
    }

    public HistoricalDataArchivesPage selectState(String state) {
        ComboBox comboBox = new ComboBox(pageDriver, selectStateCbb);
        comboBox.select(state);
        return this;
    }

    public HistoricalDataArchivesPage selectYear(String year) {
        ComboBox comboBox = new ComboBox(pageDriver, selectYearCbb);
        comboBox.select(year);
        return this;
    }


}
