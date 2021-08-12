package test.placeorder;

import actor.Tester;
import element.DateInput;
import objects.product.CustomWeatherProduct;
import objects.product.DownLoadOption;
import objects.product.Unit;
import objects.product.WeatherPara;
import objects.search.Location;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import test.TestBase;

import java.util.*;

public class OrderHistoryBulkTest extends TestBase {

    @Test
    public void orderHistoryBulk() {
        CustomWeatherProduct product = historyBulkProduct();
        Tester tester = Tester.getInstance();

        tester.customWeatherProductsAction
                .openMarketPlace().orderCustomWeatherProduct(product);
        CustomWeatherProduct orderConfirmation =
                tester.customWeatherProductsAction.getHistoryBulkOrderDetailConfirmation();

        PlaceOrderValidator.checkCustomWeatherOrderDetail(product, orderConfirmation);

        tester.customWeatherProductsAction.closeOrderDetails();
    }

    @AfterTest
    public void closeBrowser() {
        Tester tester = Tester.getInstance();
        tester.customWeatherProductsAction.close();
    }

    private CustomWeatherProduct historyBulkProduct() {
        String name = "History Bulk";
        String city = "Fort Lauderdale FL, USA";
        Location location = new Location(city);
        String fromDate = "13-Nov-2000";
        String toDate = "21-Nov-2020";
        Map<String, Boolean> weatherPara = new HashMap<>();
        weatherPara.put(WeatherPara.TEMPERATURE.getPara(), false);
        return CustomWeatherProduct.builder()
                .name(name)
                .location(location)
                .fromDate(DateInput.stringToDate(fromDate))
                .toDate(DateInput.stringToDate(toDate))
                .weatherPara(weatherPara)
                .unit(Unit.IMPERIAL.getName())
                .downLoadOption(DownLoadOption.ALL_LOCATION.getOption())
                .build();
    }
}
