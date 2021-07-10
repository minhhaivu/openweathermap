package pages;

import action.WaitForAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import element.Menu;

public class HomePage extends AbstractPage {
    private static final String BASE_URL = "https://openweathermap.org/";
    private static final long TIMEOUT_IN_SECONDS = 30;

    @FindBy(xpath = "//h1/span[contains(text(), 'OpenWeather')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@id='desktop-menu']//input[@placeholder='Weather in your city']")
    private WebElement searchCityTxt;

    @FindBy(id = "desktop-menu")
    private WebElement desktopMenu;

    private By loadingSpinner = By.xpath("//div[@class='owm-loader']");

    public HomePage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public HomePage open() {
        pageDriver.get(BASE_URL);
        waitForPageLoaded();

        return this;
    }

    public HomePage enterSearchTxt(String search) {
        new WebDriverWait(pageDriver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(searchCityTxt));
        searchCityTxt.clear();
        searchCityTxt.sendKeys(search);
        return this;
    }

    public void submitSearch() {
        searchCityTxt.sendKeys(Keys.ENTER);
    }

    private void waitForPageLoaded() {
        ExpectedCondition<Boolean> spinnerPresence = WaitForAction.isElementPresent(loadingSpinner);
        new WebDriverWait(pageDriver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.not(spinnerPresence));
    }

    public void switchToPage(String title) {
        String currentPage = pageDriver.getWindowHandle();
        for (String windowHandle : pageDriver.getWindowHandles()) {
            if (!currentPage.equals(windowHandle)) {
                pageDriver.switchTo().window(windowHandle);
                if (pageDriver.getTitle().contains(title)) {
                    break;
                }
            }
        }
    }

    public void openSignInPage() {
        Menu menu = new Menu(pageDriver);
        menu.select("Sign In");
    }
}
