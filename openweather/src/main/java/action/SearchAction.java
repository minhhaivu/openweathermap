package action;

import pages.HomePage;
import pages.SearchPage;

import java.util.List;

public class SearchAction extends ActionBase {

    private HomePage homePage;
    private SearchPage searchPage;

    public SearchAction() {
        super();

        homePage = getPageInstance(HomePage.class);
        searchPage = getPageInstance(SearchPage.class);
    }

    public List<SearchPage.CityInfo> search(String string) {
        homePage.open().enterSearchTxt(string).submitSearch();
        return searchPage.getSearchResult();
    }
}
