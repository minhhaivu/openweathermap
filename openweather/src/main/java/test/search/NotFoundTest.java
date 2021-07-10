package test.search;

import actor.Tester;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class NotFoundTest {
    @Test(dataProvider = "invalidCities")
    public void notFoundTest(String city) {
        Tester tester = Tester.getInstance();
        List<WebElement> returnResult = tester.searchAction.searchNotFound(city);
        SearchValidator.checkNotFound(returnResult);
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
