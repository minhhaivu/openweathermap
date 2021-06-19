package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlaceOrderPage extends AbstractPage {
    private static final long TIMEOUT_IN_SECONDS = 20;

    @FindBy(id = "firstSearch")
    private WebElement searchTxt;

    @FindBy(className = "search-pop-up")
    private WebElement searchPopUp;

    @FindBy(className = "//div[@class='from date-input']")
    private WebElement fromDateInput;

    @FindBy(className = "//div[@class='to date-input']")
    private WebElement toDateInput;

    @FindBy(xpath = "//div[@class='google-map']//button[contains(text(),'Add location')]")
    private WebElement addLocationBtn;

    @FindBy(xpath = "//span[contains(text(),'Weather Parameters:')]")
    private WebElement weatherParameterCbb;

    @FindBy(className = "owm-check-box-group columns")
    private WebElement weatherParameterCkl;

    @FindBy(xpath = "//div[@class='owm-check-box-group columns']/../../*[@class='icon-close']")
    private WebElement weatherParaCloseBtn;

    @FindBy(xpath = "//span[contains(text(),'Units:')]")
    private WebElement unitCbb;

    @FindBy(xpath = "/*[contains(text(),' Metric (Celsius, hPa, meter/sec, mm/h) ')]/..")
    private WebElement unitCkl;

    @FindBy(xpath = "//*[contains(text(),' Metric (Celsius, hPa, meter/sec, mm/h) ')]" +
            "/../../../*[@class='icon-close']")
    private WebElement unitCloseBtn;

    @FindBy(xpath = "//span[contains(text(),'File Format:')]")
    private WebElement fileFormatCbb;

    @FindBy(xpath = "//div[@class='owm-check-box-group']")
    private WebElement fileFormatCkl;

    @FindBy(xpath = "//div[@class='owm-check-box-group']/../../*[@class='icon-close']")
    private WebElement fileFormatCloseBtn;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderBtn;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']")
    private WebElement searchResult;

    public PlaceOrderPage enterSearch(String searchStr, String searchBy) {
        searchTxt.click();
        searchPopUp.findElement(By.xpath("//button[text()='" + searchBy + "']")).click();
        searchTxt.click();
        searchTxt.sendKeys(searchStr);
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS).until(ExpectedConditions.visibilityOf
                (searchResult.findElement(By.xpath("//span[.='"+ searchBy+ "']/*[count(*)=0]"))));
        searchResult.findElement(By.xpath("//span[.='"+ searchBy+ "']/*[count(*)=0]")).click();

        return this;
    }

    public PlaceOrderPage clickAddLocationButton() {
        addLocationBtn.click();

        return this;
    }

    public PlaceOrderPage selectTimePeriod(String fromYear, String fromMonth, String fromDate,
                                           String toYear, String toMonth, String toDate) {
        String preText = "//td[contains(text(),'";
        String sufText = "')]";
        fromDateInput.click();
        fromDateInput.findElement(By.xpath(preText + fromYear + sufText)).click();
        fromDateInput.findElement(By.xpath(preText + fromMonth + sufText)).click();
        fromDateInput.findElement(By.xpath(preText + fromDate + sufText)).click();
        toDateInput.click();
        toDateInput.findElement(By.xpath(preText + toYear + sufText)).click();
        toDateInput.findElement(By.xpath(preText + toMonth + sufText)).click();
        toDateInput.findElement(By.xpath(preText + toDate + sufText)).click();

        return this;
    }

    public PlaceOrderPage unselectWeatherParameter(String[] parameters) {
        if (parameters.length > 0) {
            weatherParameterCbb.click();
            for (String para : parameters
            ) {
                weatherParameterCkl.findElement(By.xpath("//*[.='" + para + "']/input")).click();
            }
            weatherParaCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectUnit(String unit) {
        if (unit != null) {
            unitCkl.findElement(By.xpath("//*[.='" + unit + "']/input")).click();
            unitCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectFileFormat(String[] fileFormat) {
        if (fileFormat.length > 0) {
            for (String format : fileFormat
            ) {
                fileFormatCkl.findElement(By.xpath("//*[.='" + format + "']/input")).click();
            }
            fileFormatCloseBtn.click();
        }

        return this;
    }

    public void submitOrderPlace() {
        placeOrderBtn.click();

    }

}
