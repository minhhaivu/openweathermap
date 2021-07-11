package pages;

import element.ComboBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoricalDataArchivesPage extends AbstractPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Select year')]" +
            "//following-sibling::div[@class='dropdown-selector']")
    private WebElement selectYearCbb;

    @FindBy(xpath = "//div[contains(text(),'Select state')]" +
            "//following-sibling::div[@class='dropdown-selector']")
    private WebElement selectStateCbb;

    public HistoricalDataArchivesPage(WebDriver driver) {
        this.driver = driver;
    }

    public HistoricalDataArchivesPage selectState(String state) {
        ComboBox comboBox = new ComboBox(driver);
        comboBox.select(selectStateCbb, state);

        return this;
    }

    public HistoricalDataArchivesPage selectYear(String year) {
        ComboBox comboBox = new ComboBox(driver);
        comboBox.select(selectYearCbb, year);

        return this;
    }


}
