package test.search;

import actor.Tester;
import org.testng.annotations.Test;
import pages.SearchPage;
import test.TestBase;

import java.util.ArrayList;
import java.util.List;

public class CityDetailTest extends TestBase {

    @Test
    public void cityDetailTest() {
        List<SearchPage.CityInfo> expectedCities = cityList();
        Tester tester = Tester.getInstance();
        List<SearchPage.CityInfo> cityList = tester.searchAction.search("Ho Chi Minh");
        SearchValidator.checkResultDetail(cityList, expectedCities);
        tester.searchAction.tearDown();
    }

    private static List<SearchPage.CityInfo> cityList() {
        List<SearchPage.CityInfo> cities = new ArrayList<>();
        cities.add(new SearchPage.CityInfo("Thanh pho Ho Chi Minh, VN", null));
        cities.add(new SearchPage.CityInfo("Thành phố Hồ Chí Minh, VN", null));
        return cities;
    }

}
