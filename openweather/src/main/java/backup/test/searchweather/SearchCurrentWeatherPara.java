package backup.test.searchweather;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backup.core.DriverManager;
import backup.core.DriverManagerFactory;
import backup.core.ExcelFile;
import backup.data.DriverType;
import pages.FindWeatherPage;
import backup.data.Constant;

public class SearchCurrentWeatherPara {
    DriverManager driverManager;
    WebDriver driver;
    String invalidCityFilePath = "src/main/java/backup.data/SearchWeather.xlsx";
    String invalidCitySheet = "invalid cities";

    /**
     * Step 1	Navigate to https://openweathermap.org/
     */
    @BeforeTest
    @Parameters("browser")
    public void setBrowser(@Optional("CHROME") DriverType browser) {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        driver.get(Constant.URL);
    }

    /**
     * Step 2	"Enter city into Weather in your city text box
     * City 1: Londo"
     * Step 3	Type Enter
     * Step 4	Verify the returned result	==> Not Found result is displayed
     * Step 5	"Repeat step 2 to step 4 for the following backup.data:
     * - City 2: Londo, US
     * - City 3: US
     * - City 4: UK, US,
     * - City 5: �
     * - City 6: ????
     * - City 7: ,,,,
     * - City 8: ****
     * - City 9: <blank>
     * - City 10: This city does not exist in the world! This city does not exist in the world! This city does not exist in the world! This city does not exist in the world!
     * - ....."
     * Expected Result: Not Found result is displayed
     */
    @Test(dataProvider = "invalidCities")
    public void searchCurrentWeather(String city, String countrycode) {
        FindWeatherPage openWeather = new FindWeatherPage(driver);
        openWeather.allowCookies();
        openWeather.searchWeather(city, countrycode);
		assertTrue(openWeather.checkNotFoundReturn(), "AUT bug!!! Not Found is not retured when searching weather in an invalid city: " + city + "," + countrycode);
    }

    @DataProvider(name = "invalidCities")
    public Object[][] invalidCitiesData() throws Exception {
        ExcelFile excel = new ExcelFile(invalidCityFilePath);
        return excel.getExcelData(invalidCityFilePath, invalidCitySheet);
    }

    /**
     * Step 6	Close the web page
     */
    @AfterClass
    public void quitDriver() {
        driverManager.quitWebDriver();
    }
}
