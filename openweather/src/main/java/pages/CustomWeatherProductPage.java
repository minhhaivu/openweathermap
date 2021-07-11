package pages;


import action.WaitForAction;
import actor.Tester;
import element.CheckBox;
import element.DateInput;
import element.Locator;
import element.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import objects.product.CustomWeatherOrderDetail;
import objects.product.OrderDetail;
import objects.search.Coordinates;
import objects.search.Import;
import objects.search.Location;
import objects.search.LocationType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

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
            Map<String, String> coordinatesInfo = (HashMap<String, String>) location.getInfo();
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

        searchTxt.click();
        searchByPopUp.findElement(Locator.buttonContainText("By location")).click();

        searchTxt.click();
        searchTxt.sendKeys(location);
        WaitForAction.sleep(5);
        searchTxt.sendKeys(Keys.DOWN, Keys.RETURN);

        new WebDriverWait(pageDriver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOf(addLocationBtn));
        addLocationBtn.click();
    }

    private void inputSearchString(String latitude, String longitude) {
        waitForPageLoaded();

        searchTxt.click();
        searchByPopUp.findElement(Locator.buttonContainText("By coordinates")).click();

        new WebDriverWait(pageDriver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOf(searchCoordinatePanel));
        latitudeTxt.click();
        latitudeTxt.sendKeys(latitude);

        longitudeTxt.click();
        longitudeTxt.sendKeys(longitude);
        longitudeTxt.sendKeys(Keys.RETURN);

        new WebDriverWait(pageDriver, TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOf(addLocationBtn));
        Actions action = new Actions(pageDriver);
        action.doubleClick(addLocationBtn).perform();
    }

    private void importCSVFile(String filePath) {

        searchTxt.click();
        searchByPopUp.findElement(Locator.buttonContainText("Import")).click();

        importCSVFileBtn.click();

    }

    public CustomWeatherProductPage selectFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();

        fromDateInput.click();
        calendar.setTime(date);
        DateInput.selectDate(fromDateInput, calendar.get(Calendar.YEAR),
                calendar.getDisplayName((Calendar.MONTH), Calendar.SHORT, Locale.getDefault()),
                calendar.get(Calendar.DATE));
        return this;
    }

    public CustomWeatherProductPage selectToDate(Date date) {
        Calendar calendar = Calendar.getInstance();

        toDateInput.click();
        calendar.setTime(date);
        DateInput.selectYear(toDateInput, calendar.get(Calendar.YEAR));
        DateInput.selectMonth(toDateInput, calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
        toDateInput.findElement(By.xpath("//div[@class='to date-input']//td[contains(text(),"
                + Integer.toString(calendar.get(Calendar.DATE)) + ")]")).click();

        return this;
    }

    //To be deleted after refactoring test
    public CustomWeatherProductPage unselectWeatherParameter(List<String> parameters) {
        weatherParameterCbb.click();
        for (String para : parameters
        ) {
            CheckBox.unselect(weatherParameterCkl, para);
        }
        weatherParaCloseBtn.click();

        return this;
    }

    public CustomWeatherProductPage selectWeatherParameter(HashMap<String, Boolean> parameters) {
        weatherParameterCbb.click();
        parameters.forEach((para, value) -> {
            By checkBoxLocator = By.xpath("//div[@class='owm-check-box-group columns']" + PRE_LABEL + para + SUF_INPUT);
            CheckBox weatherCkb = new CheckBox(pageDriver, checkBoxLocator);
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

    public CustomWeatherProductPage selectFileFormat(List<String> fileFormat) {
        fileFormatCbb.click();
        for (String format : fileFormat
        ) {
            CheckBox.select(fileFormatCkl, format);
        }
        fileFormatCloseBtn.click();

        return this;
    }

    public CustomWeatherProductPage selectFileFormat(HashMap<String, Boolean> fileFormat) {
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
        String periodTime = Table.getTableCellValueByRowName(orderDetailTbl, "From - To");
        String noOfLocations = Table.getTableCellValueByRowName(orderDetailTbl, "Number of locations");
        String weatherPara = Table.getTableCellValueByRowName(orderDetailTbl, "Weather parameters");
        String fileFormat = Table.getTableCellValueByRowName(orderDetailTbl, "File formats");
        String unit = Table.getTableCellValueByRowName(orderDetailTbl, "Units");
        String downLoadOption = Table.getTableCellValueByRowName(orderDetailTbl, "Download");

        return new OrderDetail(periodTime, noOfLocations, weatherPara, fileFormat, unit, downLoadOption);
    }

    private void waitForPageLoaded() {
        new WebDriverWait(pageDriver, TIMEOUT_IN_SECONDS).
                until(WaitForAction.isElementPresent(By.id("gmap")));
    }

}
