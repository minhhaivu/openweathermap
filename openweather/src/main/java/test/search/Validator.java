package test.search;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.SearchPage;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {

    public static void checkResultCount(int actual, int expected) {
        Assert.assertEquals(actual, expected, "Result count is not correct");
    }

    public static void checkResultDetail(
            List<SearchPage.CityInfo> actual, List<SearchPage.CityInfo> expected) {
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals
                    (actual.get(i).getCityName(), expected.get(i).getCityName(),
                            "List of city is not correct!");
        }
    }

    public static void checkNotFound(List<WebElement> returnResult) {
        Assert.assertTrue(returnResult.isEmpty(), "AUT defect: Not Found message is not returned when searching invalid city");
    }
}
