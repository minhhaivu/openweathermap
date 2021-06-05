package test.search;

import actor.SearchTester;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SearchPage;
import test.TestBase;

import java.util.List;

public class ResultCountTest extends TestBase {

    @Test(dataProvider = "initTestData")
    public void testResultCount(String searchString, int expectedCount) {
        SearchTester tester = SearchTester.getInstance();
        List<SearchPage.CityInfo> cityInfoList = tester.searchAction.search(searchString);
        Validator.checkResultCount(cityInfoList.size(), expectedCount);
    }

    @DataProvider
    private Object[][] initTestData() {
        return new Object[][] {
                {"keychron", 1},
                {"Thanh pho Ho Chi Minh, VN", 1},
                {"Ho Chi Minh, VN", 2}
        };
    }
}
