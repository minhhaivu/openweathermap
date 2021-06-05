package test.search;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import pages.SearchPage;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {

    public static void checkResultCount(int actual, int expected) {
        Assert.assertEquals(actual, expected, "Result count is not correct");
    }

    public static void checkResultDetail(SearchPage.CityInfo actual, SearchPage.CityInfo expected) {}

    public static void checkResultDetail(
            List<SearchPage.CityInfo> actual, List<SearchPage.CityInfo> expected) {}
}
