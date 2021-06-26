package pages;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderPage extends AbstractPage {
    private static final long TIMEOUT_IN_SECONDS = 20;
    private static final String PRE_TEXT_1 = "//td[contains(text(),'";
    private static final String SUF_TEXT_1 = "')]";
    private static final String PRE_TEXT_2 = "//label[contains(text(),'";
    private static final String SUF_TEXT_2 = "')]/input";
    private static final String SUF_TEXT_3 = "')]//span";
    private static final String SUF_TEXT_4 = "')]//span[@class='icon-checked']";

    @FindBy(id = "gmap")
    private WebElement gmap;

    @FindBy(id = "firstSearch")
    private WebElement searchTxt;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']")
    private WebElement searchResult;

    @FindBy(xpath = "//div[@class='search-pop-up']")
    private WebElement searchByPopUp;

    @FindBy(xpath = "//div[@class='from date-input']")
    private WebElement fromDateInput;

    @FindBy(xpath = "//div[@class='to date-input']")
    private WebElement toDateInput;

    @FindBy(xpath = "//div[@class='google-map']//button[contains(text(),'Add location')]")
    private WebElement addLocationBtn;

    @FindBy(xpath = "//span[contains(text(),'Weather Parameters:')]")
    private WebElement weatherParameterCbb;

    @FindBy(xpath = "//div[@class='owm-check-box-group columns']")
    private WebElement weatherParameterCkl;

    @FindBy(xpath = "//div[@class='owm-check-box-group columns']/../../*[@class='icon-close']")
    private WebElement weatherParaCloseBtn;

    @FindBy(xpath = "//span[contains(text(),'Units:')]")
    private WebElement unitCbb;

    @FindBy(xpath = "//*[contains(text(),' Metric (Celsius, hPa, meter/sec, mm/h) ')]/..")
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

    @FindBy(xpath = "//span[contains(text(),'Download:')]")
    private WebElement downLoadOptionCbb;

    @FindBy(xpath = "//label[contains(text(),'All locations in a single file')]/..")
    private WebElement downLoadOptionCkl;

    @FindBy(xpath = "//label[contains(text(),'All locations in a single file')]" +
            "/../../../*[@class='icon-close']")
    private WebElement downLoadOptionCloseBtn;

    @FindBy(xpath = "//div[contains(text(),'Select year')]" +
            "//following-sibling::div[@class='dropdown-selector']")
    private WebElement selectYearCbb;

    @FindBy(xpath = "//div[contains(text(),'Select state')]" +
            "//following-sibling::div[@class='dropdown-selector']")
    private WebElement selectStateCbb;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderBtn;

    @FindBy(xpath = "//div[@class='pop-up pop-up-confirmation']//h3[contains(text(),'Order details')]")
    private List<WebElement> orderDetailTtl;

    @FindBy(xpath = "//table[@class='confirmation-order']")
    private WebElement orderDetailTbl;

    @FindBy(xpath = "//div[@class='pop-up pop-up-confirmation']//*[@class='icon-close']")
    private WebElement orderDetailCloseBtn;

    public PlaceOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public PlaceOrderPage enterSearch(String searchStr, String searchBy) {
        waitForPageLoaded();
        searchTxt.click();
        searchByPopUp.findElement(By.xpath("//button[text()='" + searchBy + "']")).click();
        searchTxt.click();
        searchTxt.sendKeys(searchStr);
        sleep(2);
        searchTxt.sendKeys(Keys.DOWN, Keys.RETURN);

        return this;
    }

    public PlaceOrderPage clickAddLocationButton() {
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOf(addLocationBtn));
        addLocationBtn.click();

        return this;
    }

    public PlaceOrderPage selectTimePeriod(String fromYear, String fromMonth, String fromDate,
                                           String toYear, String toMonth, String toDate) {
        fromDateInput.click();
        fromDateInput.findElement(By.xpath(PRE_TEXT_1 + fromYear + SUF_TEXT_1)).click();
        fromDateInput.findElement(By.xpath(PRE_TEXT_1 + fromMonth + SUF_TEXT_1)).click();
        fromDateInput.findElement(By.xpath(PRE_TEXT_1 + fromDate + SUF_TEXT_1)).click();
        toDateInput.click();
        toDateInput.findElement(By.xpath(PRE_TEXT_1 + toYear + SUF_TEXT_1)).click();
        toDateInput.findElement(By.xpath(PRE_TEXT_1 + toMonth + SUF_TEXT_1)).click();
        toDateInput.findElement(By.xpath("//div[@class='to date-input']"
                + PRE_TEXT_1 + toDate + SUF_TEXT_1)).click();

        return this;
    }

    public PlaceOrderPage unselectWeatherParameter(String[] parameters) {
        if (parameters.length > 0) {
            weatherParameterCbb.click();
            for (String para : parameters
            ) {
                if (weatherParameterCkl.findElement
                        (By.xpath(PRE_TEXT_2 + para + SUF_TEXT_2)).isSelected()) {
                    weatherParameterCkl.findElement(By.xpath(PRE_TEXT_2 + para + SUF_TEXT_4)).click();
                }
            }
            weatherParaCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectUnit(String unit) {
        if (unit != null) {
            unitCbb.click();
            if (!unitCkl.findElement(By.xpath(PRE_TEXT_2 + unit + SUF_TEXT_2)).isSelected()) {
                unitCkl.findElement(By.xpath(PRE_TEXT_2 + unit + SUF_TEXT_3)).click();
            }
            unitCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectFileFormat(String[] fileFormat) {
        if (fileFormat.length > 0) {
            fileFormatCbb.click();
            for (String format : fileFormat
            ) {
                if (!fileFormatCkl.findElement
                        (By.xpath(PRE_TEXT_2 + format + SUF_TEXT_2))
                        .isSelected()) {
                    fileFormatCkl.findElement
                            (By.xpath(PRE_TEXT_2 + format + SUF_TEXT_4)).click();
                }
            }
            fileFormatCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectDownLoadOption(String downLoadOption) {
        if (downLoadOption != null) {
            downLoadOptionCbb.click();
            if (!downLoadOptionCkl.findElement(By.xpath(PRE_TEXT_2 + downLoadOption + SUF_TEXT_2)).isSelected()) {
                downLoadOptionCkl.findElement(By.xpath(PRE_TEXT_2 + downLoadOption + SUF_TEXT_3)).click();
            }
            downLoadOptionCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectState(String state) {
        selectStateCbb.click();
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//div[@class='menu-item']//span[contains(text(),'" + state + "')]")));
        selectStateCbb.findElement(By.xpath("//div[@class='menu-item']//span[contains(text(),'" + state + "')]")).click();

        return this;
    }

    public PlaceOrderPage selectYear(String year) {
        selectYearCbb.click();
        selectYearCbb.findElement(By.xpath("//span[contains(text(),'" + year + "')]")).click();

        return this;
    }

    public void submitOrderPlace() {
        placeOrderBtn.click();
    }

    public void closeOrderDetailsPopUp() {
        orderDetailCloseBtn.click();
    }

    public OrderDetail getConfirmationOrderDetail() {
        String periodTime = orderDetailTbl.findElement(By.xpath
                ("//td[contains(text(), 'From - To:')]/following-sibling::td"))
                .getText();
        String noOfLocations = orderDetailTbl.findElement(By.xpath
                ("//b[contains(text(), 'Number of locations:')]/../following-sibling::td"))
                .getText();
        String weatherPara = orderDetailTbl.findElement(By.xpath
                ("//b[contains(text(), 'Weather parameters:')]/../following-sibling::td"))
                .getText();
        String fileFormat = orderDetailTbl.findElement(By.xpath
                ("//td[contains(text(), 'File formats:')]/following-sibling::td"))
                .getText();
        String unit = orderDetailTbl.findElement(By.xpath
                ("//td[contains(text(), 'Units:')]/following-sibling::td"))
                .getText();
        String downLoadOption = orderDetailTbl.findElement(By.xpath
                ("//td[contains(text(), 'Download:')]/following-sibling::td"))
                .getText();

        return new OrderDetail(periodTime, noOfLocations, weatherPara, fileFormat, unit, downLoadOption);
    }

    private void waitForPageLoaded() {
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS).
                until(isPresent(By.id("gmap")));

    }

    @Getter
    @AllArgsConstructor
    public static class OrderDetail {
        private String periodTime;
        private String noOfLocations;
        private String weatherPara;
        private String fileFormat;
        private String unit;
        private String downLoadOption;

    }
}
