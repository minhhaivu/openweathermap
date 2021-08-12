package test;

import element.DateInput;
import objects.product.Unit;
import org.testng.annotations.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.time.*;

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

//    @Test
//    public void main() {
//
//    }


    protected void closeTestSuite() {
        // define later
    }

    protected void executeBeforeClass() {
        // register new User
    }

}
