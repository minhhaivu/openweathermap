package action;

import pages.MarketPlacePage;
import pages.PlaceOrderPage;

import static pages.PlaceOrderPage.*;

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

    public void orderProduct(Product product) {
        marketPlacePage.selectProductToOrder(product.getName());
        placeOrderPage.addLocation(product.getLocation())
                .selectTimePeriod(product.getFromDate(),product.getToDate())
                .unselectWeatherParameter(product.getWeatherPara())
                .selectUnit(product.getUnit())
                .selectFileFormat(product.getFileFormat())
                .selectDownLoadOption(product.getDownLoadOption())
                .selectYear(product.getYear())
                .selectState(product.getState())
                .submitOrderPlace();
    }

    public PlaceOrderPage.OrderDetail getOrderDetailConfirmation() {

        return placeOrderPage.getOrderDetail();
    }

    public boolean isOrderDetailConfirmationDisplayed() {

        return placeOrderPage.isOrderDetailConfirmationDisplayed();
    }

    public void closeOrderDetails() {
        placeOrderPage.closeOrderDetailsPopUp();
    }

    @Override
    public void tearDown() {
        marketPlacePage.close();
    }
}
