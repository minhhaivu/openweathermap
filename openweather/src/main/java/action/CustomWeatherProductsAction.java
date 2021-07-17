package action;

import objects.product.CustomWeatherOrderDetail;
import objects.product.CustomWeatherProduct;
import pages.CustomWeatherOrderDetailPage;
import pages.CustomWeatherProductPage;
import pages.MarketPlacePage;

import java.util.HashMap;

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

    public CustomWeatherProductsAction selectWeather(HashMap<String,Boolean> para) {
        customWeatherProductPage.selectWeatherParameter(para);
        return this;
    }

    public void selectFileFormat(HashMap<String,Boolean> fileFormat){
        customWeatherProductPage.selectFileFormat(fileFormat);
    }

    public void orderCustomWeatherProduct(CustomWeatherProduct product) {
        marketPlacePage.selectProductToOrder(product.getName());
        customWeatherProductPage.search(product.getLocation())
                .selectFromDate(product.getFromDate())
                .selectToDate(product.getToDate())
                .unselectWeatherParameter(product.getUnselectedWeatherPara())
                .selectUnit(product.getUnit())
                .selectFileFormat(product.getFileFormat())
                .selectDownLoadOption(product.getDownLoadOption())
                .submitOrderPlace();
    }

    public CustomWeatherOrderDetail getOrderDetailConfirmation() {

        return customWeatherOrderDetailPage.getOrderDetail();
    }

    public void closeOrderDetails() {
        customWeatherProductPage.closeOrderDetailsPopUp();
    }

    @Override
    public void close() {
        marketPlacePage.close();
    }
}
