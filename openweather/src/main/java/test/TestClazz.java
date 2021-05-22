package test;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;

import java.util.List;

public class TestClazz extends TestBase {

    @Test
    public void runMyTest() {
        HomePage homePage = getPageInstance(HomePage.class);
        homePage.open().enterSearchTxt("van").submitSearch();

        SearchPage searchPage = getPageInstance(SearchPage.class);
        List<SearchPage.CityInfo> cites = searchPage.getSearchResult();
        System.out.println();


        searchPage.close();
    }
}
