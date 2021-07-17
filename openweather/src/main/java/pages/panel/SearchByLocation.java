package pages.panel;

import action.WaitForAction;
import element.Locator;
import objects.search.Location;
import objects.search.LocationType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;

public class SearchByLocation extends AbstractPage implements Search {
    private static final long TIMEOUT_IN_SECONDS = 30;

    @FindBy(id = "gmap")
    private WebElement gmap;

    @FindBy(id = "firstSearch")
    private WebElement searchTxt;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']")
    private WebElement searchResult;

    @FindBy(xpath = "//div[@class='search-pop-up']")
    private WebElement searchByPopUp;

    @FindBy(xpath = "//div[@class='google-map']//button[contains(text(),'Add location')]")
    private WebElement addLocationBtn;

    public SearchByLocation(WebDriver driver) {}

    @Override
    public void search(LocationType type) {
        if(!(type instanceof Location)) {
            throw new UnsupportedOperationException("Type is not expected");
        }
        String location = (String )type.getInfo();

        searchTxt.click();
        searchByPopUp.findElement(Locator.buttonContainText("By location")).click();

        searchTxt.click();
        searchTxt.sendKeys(location);
        WaitForAction.sleep(2);
        searchTxt.sendKeys(Keys.DOWN, Keys.RETURN);

        new WebDriverWait(pageDriver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOf(addLocationBtn));
        addLocationBtn.click();
    }
}
