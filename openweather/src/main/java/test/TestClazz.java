package test;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import utilities.InvalidCities;

public class TestClazz extends TestBase {

    @Test (dataProvider = "invalid-cities",dataProviderClass = InvalidCities.class)
    public void runMyTest(String invalidcity) {
        HomePage homePage = getPageInstance(HomePage.class);
        homePage.open().enterSearchTxt(invalidcity);
        SearchPage searchPage = getPageInstance(SearchPage.class);
        searchPage.checkSearchResult(invalidcity);
        searchPage.checkSearchResult("Not Found");
        System.out.println();
        searchPage.close();

    }
}
