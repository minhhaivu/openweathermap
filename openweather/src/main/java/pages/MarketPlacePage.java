package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MarketPlacePage extends AbstractPage {
    private static final String URL = "https://home.openweathermap.org/marketplace";
    private static final long TIME_OUT_IN_SECONDS = 40;

    @FindBy(xpath = "//div[@class='market-place']")
    private WebElement marketPlace;

    @FindBy(id = "custom_weather_products")
    private WebElement title;

    public MarketPlacePage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public MarketPlacePage open() {
        pageDriver.get(URL);
        waitForPageLoaded();

        return this;
    }

    public void selectProductToOrder(String product) {
        marketPlace.findElement(By.xpath("//a[contains(text(),'"
                + product + "')]")).click();
    }

    private void waitForPageLoaded() {
        new WebDriverWait(pageDriver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOf(title));
    }


}
