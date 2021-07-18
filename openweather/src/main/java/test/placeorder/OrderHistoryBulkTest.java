package test.placeorder;

import actor.Tester;
import element.DateInput;
import objects.product.CustomWeatherProduct;
import objects.search.Location;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import test.TestBase;

import java.util.*;

public class OrderHistoryBulkTest extends TestBase {

    @Test
    public void orderHistoryBulk() {
        CustomWeatherProduct product = historyBulkProduct();
        CustomWeatherProduct expectedOrderConfirmation = orderDetailConfirmation(product);
        Tester tester = Tester.getInstance();

        tester.customWeatherProductsAction
                .openMarketPlace().orderCustomWeatherProduct(product);
        CustomWeatherProduct actualOrderConfirmation = tester.customWeatherProductsAction.getOrderDetailConfirmation();

        PlaceOrderValidator.checkCustomWeatherOrderDetail(expectedOrderConfirmation, actualOrderConfirmation);

        tester.customWeatherProductsAction.closeOrderDetails();
    }

    @AfterTest
    public void closeBrowser() {
//
//        Tester tester = Tester.getInstance();
//        tester.customWeatherProductsAction.close();
    }

    private CustomWeatherProduct historyBulkProduct() {
        String name = "History Bulk";
        String city = "Fort Lauderdale FL, USA";
        Location location = new Location(city);
        String fromDate = "13-Nov-2000";
        String toDate = "21-Nov-2020";
        Date from = DateInput.stringToDate("dd-MMM-yyyy", fromDate);
        Date to = DateInput.stringToDate("dd-MMM-yyyy", toDate);
        Map<String, Boolean> weatherPara = new HashMap<>();
        weatherPara.put("Temperature", false);
        String unit = "Imperial (Fahrenheit, hPa, miles/hour, mm/h)";
        String downLoadOptions = "One file per location";
        return new CustomWeatherProduct().builder()
                .name(name)
                .location(location)
                .fromDate(from)
                .toDate(to)
                .weatherPara(weatherPara)
                .unit(unit)
                .downLoadOption(downLoadOptions)
                .build();
    }

    private CustomWeatherProduct orderDetailConfirmation(CustomWeatherProduct product) {
        product.setDatePeriod();
        product.setCustomWeatherPara();
        return new CustomWeatherProduct().builder()
                .datePeriod(product.getDatePeriod())
                .numberOfLocation(1)
                .customWeatherPara(product.getCustomWeatherPara())
                .fileFormatInString(product.getFileFormatInString())
                .unit(product.getUnit())
                .downLoadOption(product.getDownLoadOption())
                .build();
    }
}
