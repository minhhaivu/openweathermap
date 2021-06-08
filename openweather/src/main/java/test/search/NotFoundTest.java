package test.search;

import actor.SearchTester;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class NotFoundTest {
    @Test(dataProvider = "invalidCities")
    public void notFoundTest(String city) {
        SearchTester tester = SearchTester.getInstance();
        List<WebElement> returnResult = tester.searchAction.searchNotFound(city);
        Validator.checkNotFound(returnResult);
    }

    @DataProvider
    private Object[][] invalidCities() {
        return new Object[][]{
                {"Londo, US"},
                {"US"},
                {"UK, US"},
                {"..."},
                {"????"},
                {",,,,"},
                {"****"},
                {"This city does not exist in the world! This city does not exist in the world! " +
                        "This city does not exist in the world! This city does not exist in the world!"}
        };
    }

}
