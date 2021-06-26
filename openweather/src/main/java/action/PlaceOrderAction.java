package action;

import org.openqa.selenium.WebElement;
import pages.MarketPlacePage;
import pages.PlaceOrderPage;

import java.util.List;

public class PlaceOrderAction extends ActionBase {
    private final MarketPlacePage marketPlacePage;
    private final PlaceOrderPage placeOrderPage;

    public PlaceOrderAction() {

        super();
        marketPlacePage = getPageInstance(MarketPlacePage.class);
        placeOrderPage = getPageInstance(PlaceOrderPage.class);
    }

    public PlaceOrderAction openMarketPlace() {
        marketPlacePage.open();

        return this;
    }

    public PlaceOrderAction selectProduct(String product) {
        marketPlacePage.selectProductToOrder(product);

        return this;
    }

    public PlaceOrderAction addLocation(String searchStr, String searchBy) {
        placeOrderPage.enterSearch(searchStr, searchBy).clickAddLocationButton();

        return this;
    }

    public PlaceOrderAction setTimePeriod(String fromYear, String fromMonth, String fromDate,
                                          String toYear, String toMonth, String toDate) {
        placeOrderPage.selectTimePeriod(fromYear, fromMonth, fromDate, toYear, toMonth, toDate);

        return this;
    }

    public PlaceOrderAction filter(String[] weatherPara, String unit,
                                   String[] formatFile, String downLoadOption) {
        placeOrderPage
                .unselectWeatherParameter(weatherPara)
                .selectUnit(unit)
                .selectFileFormat(formatFile)
                .selectDownLoadOption(downLoadOption);

        return this;
    }

    public void orderHistoricalWeatherDataByState(String state, String year) {
        placeOrderPage.selectState(state).selectYear(year).
                submitOrderPlace();

    }

    public PlaceOrderAction filter(String[] weatherPara, String unit,
                                   String[] formatFile) {
        placeOrderPage
                .unselectWeatherParameter(weatherPara)
                .selectUnit(unit)
                .selectFileFormat(formatFile);

        return this;
    }

    public void submitOrder() {
        placeOrderPage.submitOrderPlace();

    }

    public PlaceOrderPage.OrderDetail getOrderConfirmation() {

        return placeOrderPage.getConfirmationOrderDetail();
    }

    public List<WebElement> getOrderTitle() {

        return placeOrderPage.getOrderDetailTtl();
    }

    public void closeOrderDetails() {
        placeOrderPage.closeOrderDetailsPopUp();
    }

    @Override
    public void tearDown() {
        marketPlacePage.close();

    }
}
