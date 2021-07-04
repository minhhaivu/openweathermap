package pages;

import action.WaitForAction;
import element.*;
import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderPage extends AbstractPage {
    private static final long TIMEOUT_IN_SECONDS = 30;
    private static final String PRE_LABEL = "//label[contains(text(),'";
    private static final String SUF_SPAN = "')]//span";

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

    public PlaceOrderPage addLocation(Location location) {
        if (location != null) {
            waitForPageLoaded();
            searchTxt.click();
            switch (location.searchBy) {
                case "By location":
                    searchByPopUp.findElement(Locator.buttonContainText(location.searchBy)).click();
                    searchTxt.click();
                    searchTxt.sendKeys(location.locationOrImportFile);
                    WaitForAction.sleep(2);
                    searchTxt.sendKeys(Keys.DOWN, Keys.RETURN);
                    break;
                case "import":
                    //implement later
                default:
                    //implement later
                    break;
            }
            new WebDriverWait(driver, TIMEOUT_IN_SECONDS)
                    .until(ExpectedConditions.visibilityOf(addLocationBtn));
            addLocationBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectTimePeriod(Date fromDate, Date toDate) {
        if (fromDate != null) {
            fromDateInput.click();
            Calendar calender = Calendar.getInstance();
            calender.setTime(fromDate);
            DateInput.selectYear(fromDateInput, calender.get(Calendar.YEAR));
            DateInput.selectMonth(fromDateInput, calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
            DateInput.selectDate(fromDateInput, calender.get(Calendar.DATE));
            toDateInput.click();
            calender.setTime(toDate);
            DateInput.selectYear(toDateInput, calender.get(Calendar.YEAR));
            DateInput.selectMonth(toDateInput, calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
            toDateInput.findElement(By.xpath("//div[@class='to date-input']//td[contains(text(),"
                    + Integer.toString(calender.get(Calendar.DATE)) + ")]")).click();
        }

        return this;
    }

    public PlaceOrderPage unselectWeatherParameter(List<String> parameters) {
        if (parameters != null) {
            weatherParameterCbb.click();
            for (String para : parameters
            ) {
                CheckBox.unselect(weatherParameterCkl, para);
            }
            weatherParaCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectUnit(String unit) {
        if (unit != null) {
            unitCbb.click();
            unitCkl.findElement(By.xpath(PRE_LABEL + unit + SUF_SPAN)).click();
            unitCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectFileFormat(List<String> fileFormat) {
        if (fileFormat != null) {
            fileFormatCbb.click();
            for (String format : fileFormat
            ) {
                CheckBox.select(fileFormatCkl, format);
            }
            fileFormatCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectDownLoadOption(String downLoadOption) {
        if (downLoadOption != null) {
            downLoadOptionCbb.click();
            downLoadOptionCkl.findElement(By.xpath(PRE_LABEL + downLoadOption + SUF_SPAN)).click();
            downLoadOptionCloseBtn.click();
        }

        return this;
    }

    public PlaceOrderPage selectState(String state) {
        if (state != null) {
            ComboBox.select(selectStateCbb, state);
        }

        return this;
    }

    public PlaceOrderPage selectYear(String year) {
        if (year != null) {
            ComboBox.select(selectYearCbb, year);
        }

        return this;
    }

    public void submitOrderPlace() {
        placeOrderBtn.click();
    }

    public boolean isOrderDetailConfirmationDisplayed() {
        return orderDetailTtl.isEmpty();
    }

    public void closeOrderDetailsPopUp() {
        orderDetailCloseBtn.click();
    }

    public OrderDetail getOrderDetail() {
        String periodTime = Table.getTableCellValueByRowName(orderDetailTbl, "From - To");
        String noOfLocations = Table.getTableCellValueByRowName(orderDetailTbl, "Number of locations");
        String weatherPara = Table.getTableCellValueByRowName(orderDetailTbl, "Weather parameters");
        String fileFormat = Table.getTableCellValueByRowName(orderDetailTbl, "File formats");
        String unit = Table.getTableCellValueByRowName(orderDetailTbl, "Units");
        String downLoadOption = Table.getTableCellValueByRowName(orderDetailTbl, "Download");

        return new OrderDetail(periodTime, noOfLocations, weatherPara, fileFormat, unit, downLoadOption);
    }

    private void waitForPageLoaded() {
        new WebDriverWait(driver, TIMEOUT_IN_SECONDS).
                until(WaitForAction.isElementPresent(By.id("gmap")));
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class Product {
        private String name;
        private Location location;
        private Date fromDate;
        private Date toDate;
        private List<String> weatherPara;
        private String unit;
        private List<String> fileFormat;
        private String downLoadOption;
        private String state;
        private String year;
    }

    @Setter
    @Getter
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    public static class OrderDetail {
        private String periodTime;
        private String noOfLocations;
        private String weatherPara;
        private String fileFormat;
        private String unit;
        private String downLoadOption;

    }

    @Setter
    @NoArgsConstructor
    public static class Location {
        private String searchBy;
        private String locationOrImportFile;
        private Coordinates coordinates;

        public Location(String searchBy, String locationOrImportFile) {
            this.searchBy = searchBy;
            this.locationOrImportFile = locationOrImportFile;
        }

        public Location(String searchBy, Coordinates coordinates) {
            this.searchBy = searchBy;
            this.coordinates = coordinates;
        }
    }

    @Setter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Coordinates {
        private String latitude;
        private String longitude;

    }

}
