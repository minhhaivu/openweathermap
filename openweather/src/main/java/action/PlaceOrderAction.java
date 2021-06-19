package action;

import pages.HomePage;
import pages.MarketPlacePage;
import pages.PlaceOrderPage;

public class PlaceOrderAction extends ActionBase {
    private final HomePage homePage;
    private final MarketPlacePage marketPlacePage;
    private final PlaceOrderPage placeOrderPage;

    public PlaceOrderAction() {

        super();
        homePage = getPageInstance(HomePage.class);
        marketPlacePage = getPageInstance(MarketPlacePage.class);
        placeOrderPage = getPageInstance(PlaceOrderPage.class);
    }

    public PlaceOrderAction  openMarketPlace() {
        homePage.open().selectMenu("Marketplace").switchToPage("Marketplace");

        return this;
    }

    public PlaceOrderAction selectProduct(String product) {
        marketPlacePage.selectProductToOrder(product);

        return this;
    }

    public void addLocation(String searchStr, String searchBy) {
        placeOrderPage.enterSearch(searchStr, searchBy).clickAddLocationButton();

    }

    public void setTimePeriod(String fromYear, String fromMonth, String fromDate,
                              String toYear, String toMonth, String toDate) {
        placeOrderPage.selectTimePeriod(fromYear, fromMonth, fromDate, toYear, toMonth, toDate);

    }

    public void filter(String[] weatherPara, String unit,
                       String[] formatFile) {
        placeOrderPage.unselectWeatherParameter(weatherPara).selectUnit(unit)
                .selectFileFormat(formatFile);

    }

    public void submitOrder() {
        placeOrderPage.submitOrderPlace();
    }

    @Override
    public void tearDown() {
        marketPlacePage.close();

    }
}
