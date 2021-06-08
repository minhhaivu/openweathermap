package action;

import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.SearchPage;

import java.util.List;

public class SearchAction extends ActionBase {

    private final HomePage homePage;
    private final SearchPage searchPage;

    public SearchAction() {
        super();

        homePage = getPageInstance(HomePage.class);
        searchPage = getPageInstance(SearchPage.class);
    }

    @Override
    public void tearDown() {
        searchPage.close();
    }

    public List<SearchPage.CityInfo> search(String string) {
        homePage.open().enterSearchTxt(string).submitSearch();
        return searchPage.getSearchResult();
    }

    public List<WebElement> searchNotFound(String string) {
        homePage.open().enterSearchTxt(string).submitSearch();
        return searchPage.getNotFoundText();
    }

}
