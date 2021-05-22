package test;

import driver.DriverGetter;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pages.AbstractPage;

public class TestBase {
    private static String browserType;

    @BeforeSuite
    public void executeBeforeSuite() {
        setupTestSuite();
    }

    protected <T extends AbstractPage> T getPageInstance(Class<T> clazz) {
        return PageFactory.initElements(DriverGetter.getDriver(browserType), clazz);
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

    protected  void closeTestSuite() {
        // define later
    }
}
