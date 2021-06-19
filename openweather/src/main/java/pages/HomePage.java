package pages;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage extends AbstractPage {
    private static final String BASE_URL = "https://openweathermap.org/";
    private static final long TIMEOUT_IN_SECONDS = 90;

    @FindBy(xpath = "//h1/span[contains(text(), 'OpenWeather')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@id='desktop-menu']//input[@placeholder='Weather in your city']")
    private WebElement searchCityTxt;

    @FindBy(xpath = "//div[@class='owm-loader']")
    private WebElement loadingSpinner;

    @FindBy(id = "desktop-menu")
    private WebElement desktopMenu;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage open() {
        driver.get(BASE_URL);
        waitForPageLoaded();

        return this;
    }

    public HomePage enterSearchTxt(String search) {
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(searchCityTxt));
        searchCityTxt.clear();
        searchCityTxt.sendKeys(search);
        return this;
    }

    public void submitSearch() {
        searchCityTxt.sendKeys(Keys.ENTER);
    }

    public HomePage selectMenu(String menu) {
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//a[contains(text(),'" + menu + "')]")));
        desktopMenu.findElement(By.xpath("//a[contains(text(),'" + menu + "')]")).click();

        return this;
    }

  private void waitForPageLoaded() {
    WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
    ExpectedCondition<Boolean> isPresent =
        new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver driver) {
            try {
              driver.findElement(By.xpath("//div[@class='owm-loader']"));
              return true;
            } catch (NoSuchElementException e) {
              // do nothing
            }

            return false;
          }
        };

    new WebDriverWait(driver, 10).until(ExpectedConditions.not(isPresent));
  }

    public void switchToPage(String title) {
        String currentPage = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()
        ) {
            if (!currentPage.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                if (driver.getTitle().contains(title)) {
                    break;
                }
            }
        }
    }

}
