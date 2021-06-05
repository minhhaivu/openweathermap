package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractPage {
    private static final String BASE_URL = "https://openweathermap.org/";
    private static final long TIMEOUT_IN_SECONDS = 20;

    @FindBy(xpath = "//h1/span[contains(text(), 'OpenWeather')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@id='desktop-menu']//input[@placeholder='Weather in your city']")
    private WebElement searchCityTxt;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage open() {
        driver.get(BASE_URL);
        waitForPageLoaded();

        return this;
    }

    public HomePage enterSearchTxt(String search) {
        searchCityTxt.clear();
        searchCityTxt.sendKeys(search);
        return this;
    }

    public void submitSearch() {
        searchCityTxt.sendKeys(Keys.ENTER);
    }

    private void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }
}
