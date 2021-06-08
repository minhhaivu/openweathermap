package test;

import org.testng.annotations.*;

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
}
