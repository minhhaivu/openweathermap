package pages;


import action.WaitForAction;
import element.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import objects.product.OrderDetail;
import objects.search.Coordinates;
import objects.search.Import;
import objects.search.Location;
import objects.search.LocationType;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomWeatherProductPage extends AbstractPage {
    private static final long TIMEOUT_IN_SECONDS = 30;
    private static final String PRE_LABEL = "//label[contains(text(),'";
    private static final String SUF_SPAN = "')]//span";
    private static final String SUF_INPUT = "')]/input";

    @FindBy(id = "gmap")
    private WebElement gmap;

    @FindBy(id = "firstSearch")
    private WebElement searchTxt;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']")
    private WebElement searchResult;

    @FindBy(xpath = "//div[@class='search-pop-up']")
    private WebElement searchByPopUp;

    @FindBy(xpath = "//div[count(input[@class='coordinates'])=2]")
    private WebElement searchCoordinatePanel;

    @FindBy(xpath = "//input[@placeholder='Latitude']")
    private WebElement latitudeTxt;

    @FindBy(xpath = "//input[@placeholder='Longitude']")
    private WebElement longitudeTxt;

    @FindBy(xpath = "//button[@class='Import CSV file']")
    private WebElement importCSVFileBtn;

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

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderBtn;

    @FindBy(xpath = "//div[@class='pop-up pop-up-confirmation']//h3[contains(text(),'Order details')]")
    private List<WebElement> orderDetailTtl;

    @FindBy(xpath = "//table[@class='confirmation-order']")
    private WebElement orderDetailTbl;

    @FindBy(xpath = "//div[@class='pop-up pop-up-confirmation']//*[@class='icon-close']")
    private WebElement orderDetailCloseBtn;

    public CustomWeatherProductPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public CustomWeatherProductPage search(LocationType location) {
        if (location instanceof Location) {
            inputSearchString((String) location.getInfo());
        } else if (location instanceof Coordinates) {
            Map<String, String> coordinatesInfo = (Map<String, String>) location.getInfo();
            inputSearchString(coordinatesInfo.get("LAT"), coordinatesInfo.get("LONG"));
        } else if (location instanceof Import) {
            importCSVFile((String) location.getInfo());
        } else {
            throw new UnsupportedOperationException();
        }

        return this;
    }

    private void inputSearchString(String location) {
        waitForPageLoaded();
        TextBox search = new TextBox(pageDriver,searchTxt,searchByPopUp);
        search.selectPopUpOption("By location");
        search.enter(location,3,Keys.DOWN + "," + Keys.RETURN);
        Button addLocation = new Button(pageDriver,addLocationBtn);
        addLocation.click();
    }

    private void inputSearchString(String latitude, String longitude) {
        waitForPageLoaded();
        TextBox search = new TextBox(pageDriver,searchTxt,searchByPopUp);
        search.selectPopUpOption("By coordinates");
        TextBox latTxt = new TextBox(pageDriver,latitudeTxt);
        latTxt.enter(latitude);
        TextBox longTxt = new TextBox(pageDriver,longitudeTxt);
        longTxt.enter(longitude,Keys.RETURN);
        Button addLocation = new Button(pageDriver,addLocationBtn);
        addLocation.doubleClick();
    }

    private void importCSVFile(String filePath) {
        waitForPageLoaded();
        TextBox search = new TextBox(pageDriver,searchTxt,searchByPopUp);
        search.selectPopUpOption("Import");
        importCSVFileBtn.click();
    }

    public CustomWeatherProductPage selectFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        fromDateInput.click();
        DateInput from = new DateInput(pageDriver,fromDateInput);
        from.selectDate(calendar.get(Calendar.YEAR),
                calendar.getDisplayName((Calendar.MONTH), Calendar.SHORT, Locale.getDefault()),
                calendar.get(Calendar.DATE));
        return this;
    }

    public CustomWeatherProductPage selectToDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        toDateInput.click();
        DateInput to = new DateInput(pageDriver,toDateInput);
        to.selectDate(calendar.get(Calendar.YEAR),
                calendar.getDisplayName((Calendar.MONTH), Calendar.SHORT, Locale.getDefault()),
                calendar.get(Calendar.DATE));
        return this;
    }

    public CustomWeatherProductPage selectWeatherParameter(Map<String, Boolean> parameters) {
        weatherParameterCbb.click();
        parameters.forEach((para, value) -> {
            WebElement weatherElement = weatherParameterCbb.
                    findElement(By.xpath(PRE_LABEL + para + SUF_INPUT));
            CheckBox weatherCkb = new CheckBox(pageDriver, weatherElement);
            weatherCkb.select(value);
        });
        weatherParaCloseBtn.click();
        return this;
    }

    public CustomWeatherProductPage selectUnit(String unit) {
        unitCbb.click();
        unitCkl.findElement(By.xpath(PRE_LABEL + unit + SUF_SPAN)).click();
        unitCloseBtn.click();

        return this;
    }

     public CustomWeatherProductPage selectFileFormat(Map<String, Boolean> fileFormat) {
        fileFormatCbb.click();
        fileFormat.forEach((format, value) -> {
            WebElement checkbox = fileFormatCkl.findElement(By.xpath
                    (PRE_LABEL + format + SUF_INPUT));
            CheckBox fileFormatCkb = new CheckBox(pageDriver, checkbox);
            fileFormatCkb.select(value);
        });

        fileFormatCloseBtn.click();

        return this;
    }

    public CustomWeatherProductPage selectDownLoadOption(String downLoadOption) {
        if (downLoadOption != null) {
            downLoadOptionCbb.click();
            downLoadOptionCkl.findElement(By.xpath(PRE_LABEL + downLoadOption + SUF_SPAN)).click();
            downLoadOptionCloseBtn.click();
        }
        return this;
    }

    public void submitOrderPlace() {
        placeOrderBtn.click();
    }

    public void closeOrderDetailsPopUp() {
        orderDetailCloseBtn.click();
    }

    public OrderDetail getOrderDetail() {
        Table orderTable = new Table(pageDriver,orderDetailTbl);
        String periodTime = orderTable.getRowValue("From - To");
        String noOfLocations = orderTable.getRowValue("Number of locations");
        String weatherPara = orderTable.getRowValue("Weather parameters");
        String fileFormat = orderTable.getRowValue("File formats");
        String unit = orderTable.getRowValue("Units");
        String downLoadOption = orderTable.getRowValue("Download");

        return new OrderDetail(periodTime, noOfLocations, weatherPara, fileFormat, unit, downLoadOption);
    }

    private void waitForPageLoaded() {
        new WebDriverWait(pageDriver, TIMEOUT_IN_SECONDS).
                until(WaitForAction.isElementPresent(By.id("gmap")));
    }

}
