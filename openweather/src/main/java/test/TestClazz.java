package test;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;

import java.util.List;

public class TestClazz extends TestBase {

    @Test
    public void runMyTest() {
        HomePage homePage = getPageInstance(HomePage.class);
        homePage.open().enterSearchTxt("iilymtics");
        SearchPage searchPage = getPageInstance(SearchPage.class);
        List<SearchPage.CityInfo> cites = searchPage.getSearchResult();
        searchPage.checkSearchResult("iilymtics");
        searchPage.checkSearchResult("Not Found");
        System.out.println();
        searchPage.close();

    }
}
