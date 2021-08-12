package action;

import objects.product.CustomWeatherProduct;
import pages.CustomWeatherOrderDetailPage;
import pages.CustomWeatherProductPage;
import pages.MarketPlacePage;

public class CustomWeatherProductsAction extends ActionBase {
    private final MarketPlacePage marketPlacePage;
    private final CustomWeatherProductPage customWeatherProductPage;
    private final CustomWeatherOrderDetailPage customWeatherOrderDetailPage;

    public CustomWeatherProductsAction() {
        super();
        marketPlacePage = getPageInstance(MarketPlacePage.class);
        customWeatherProductPage = getPageInstance(CustomWeatherProductPage.class);
        customWeatherOrderDetailPage = getPageInstance(CustomWeatherOrderDetailPage.class);
    }

    public CustomWeatherProductsAction openMarketPlace() {
        marketPlacePage.open();
        return this;
    }

    public void orderCustomWeatherProduct(CustomWeatherProduct product) {
        marketPlacePage.selectProductToOrder(product.getName());
        customWeatherProductPage.search(product.getLocation())
                .selectFromDate(product.getFromDate())
                .selectToDate(product.getToDate())
                .selectWeatherParameter(product.getWeatherPara())
                .selectUnit(product.getUnit())
                .selectFileFormat(product.getFileFormat())
                .selectDownLoadOption(product.getDownLoadOption())
                .submitOrderPlace();
    }

    public CustomWeatherProduct getHistoryBulkOrderDetailConfirmation() {
        return new CustomWeatherProduct().historyBulkParse(customWeatherOrderDetailPage.getOrderDetail());
    }

    public CustomWeatherProduct getHistoryForecastBulkOrderDetailConfirmation() {
        return new CustomWeatherProduct().historyForecastBulkParse(customWeatherOrderDetailPage.getOrderDetail());
    }

    public void closeOrderDetails() {
        customWeatherProductPage.closeOrderDetailsPopUp();
    }

    @Override
    public void close() {
        marketPlacePage.close();
    }
}
