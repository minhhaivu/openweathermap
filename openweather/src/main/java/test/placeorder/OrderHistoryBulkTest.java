package test.placeorder;

import actor.Tester;
import element.DateInput;
import objects.product.CustomWeatherOrderDetail;
import objects.product.CustomWeatherProduct;
import objects.search.Location;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import test.TestBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistoryBulkTest extends TestBase {

    @Test
    public void orderHistoryBulk() {
        CustomWeatherProduct product = historyBulkProduct();
        CustomWeatherOrderDetail expectationOrder = orderDetailConfirmation(product);
        Tester tester = Tester.getInstance();

        tester.customWeatherProductsAction
                .openMarketPlace().orderCustomWeatherProduct(product);
        CustomWeatherOrderDetail confirmationOrder = tester.customWeatherProductsAction.getOrderDetailConfirmation();

        PlaceOrderValidator.checkCustomWeatherOrderDetail(expectationOrder, confirmationOrder);

        tester.customWeatherProductsAction.closeOrderDetails();
    }

    @AfterTest
    public void closeBrowser() {

        Tester tester = Tester.getInstance();
//        tester.customWeatherProductsAction.close();
    }

    private CustomWeatherProduct historyBulkProduct() {
        String city = "Fort Lauderdale FL, USA";
        Location location = new Location(city);
        String fromDate = "13-Nov-2000";
        String toDate = "21-Nov-2020";
        Date from = DateInput.stringToDate("dd-MMM-yyyy", fromDate);
        Date to = DateInput.stringToDate("dd-MMM-yyyy", toDate);
        List<String> unselectedWeatherPara = new ArrayList<>();
        unselectedWeatherPara.add("Temperature");
        String unit = "Imperial (Fahrenheit, hPa, miles/hour, mm/h)";
        List<String> formatFile = new ArrayList<>();
        formatFile.add("CSV");
        formatFile.add("JSON");
        String downLoadOptions = "One file per location";

        return new CustomWeatherProduct("History Bulk",location,
                from,to,unselectedWeatherPara,unit,formatFile,downLoadOptions);
    }

    private CustomWeatherOrderDetail orderDetailConfirmation(CustomWeatherProduct product) {
        String fromTo = DateInput.dateToString("dd-MM-y", product.getFromDate())
                + " - " + DateInput.dateToString("dd-MM-y", product.getToDate());
        String weatherPara = "Min temperature, Max temperature, Feels like, Wind, Pressure, " +
                "Humidity, Clouds, Weather conditions, Rain, Snow";
        String fileFormatOptions = "CSV, JSON";

        return new CustomWeatherOrderDetail
                (fromTo, "1", weatherPara, fileFormatOptions, product.getUnit(), product.getDownLoadOption());
    }
}
