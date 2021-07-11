package test;

import actor.Tester;
import objects.product.CustomWeatherOrderDetail;
import objects.product.CustomWeatherProduct;
import org.testng.annotations.*;
import pages.CustomWeatherOrderDetailPage;
import pages.CustomWeatherProductPage;
import pages.MarketPlacePage;

import java.util.HashMap;

public class TestBase {
    private String browserType;

    @BeforeSuite
    public void executeBeforeSuite() {
        setupTestSuite();
    }

    @Parameters("browserType")
    @BeforeTest
    public void executeBeforeTest(@Optional("chrome") String browserType) {
        this.browserType = browserType;
    }

    @AfterSuite
    public void executeAfterSuite() {
        closeTestSuite();
    }

    protected void setupTestSuite() {
        // define later
    }

    protected void closeTestSuite() {
        // define later
    }

    protected void executeBeforeClass() {
        // register new User
    }

    @Test
    public void orderHistoryBulk() {

        Tester tester = Tester.getInstance();
        HashMap<String,Boolean> weatherPara = new HashMap<> ();
        weatherPara.put("Temperature",false);
        weatherPara.put("Min temperature",true);
        HashMap<String,Boolean> fileFormat = new HashMap<>();
        fileFormat.put("CSV",true);
        fileFormat.put("JSON",false);
        tester.customWeatherProductsAction
                .openMarketPlace().selectWeather(weatherPara).selectFileFormat(fileFormat);

    }


    }
