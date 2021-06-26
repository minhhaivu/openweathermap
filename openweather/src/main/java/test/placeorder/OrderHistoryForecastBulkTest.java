package test.placeorder;

import actor.PlaceOrderTester;
import org.testng.annotations.Test;
import pages.PlaceOrderPage;
import test.TestBase;

public class OrderHistoryForecastBulkTest extends TestBase {

    @Test
    public void orderHistoryBulk() {
        String product = "History Forecast Bulk";
        String city = "Fort Lauderdale FL, USA";
        String searchByLocation = "By location";
        String fromYear = "2020";
        String fromMonth = "Nov";
        String fromDate = "13";
        String toYear = "2020";
        String toMonth = "Nov";
        String toDate = "21";
        String[] unselectedWeatherPara = {"Temperature"};
        String unit = "Imperial (Fahrenheit, hPa, miles/hour, mm/h)";
        String[] formatFile = {"CSV", "JSON"};
        String downLoadOption = "One file per location";
        String fromTo = "13-11-2020 - 21-11-2020";
        String noOfLocations = "1";
        String weatherPara = "Pressure, Wind, Humidity, Clouds, Dew point, Precipitation";
        String fileFormatOptions = "CSV, JSON";
        PlaceOrderPage.OrderDetail expectationOrder = new PlaceOrderPage.OrderDetail
                (fromTo, noOfLocations, weatherPara, fileFormatOptions, unit, downLoadOption);

        PlaceOrderTester tester = PlaceOrderTester.getInstance();
        tester.placeOrderAction
                .openMarketPlace().selectProduct(product)
                .addLocation(city, searchByLocation)
                .setTimePeriod(fromYear, fromMonth, fromDate, toYear, toMonth, toDate)
                .filter(unselectedWeatherPara, unit, formatFile,downLoadOption)
                .submitOrder();
        PlaceOrderPage.OrderDetail confirmationOrder = tester.placeOrderAction.getOrderConfirmation();
        Validator.checkOrderDetail(expectationOrder, confirmationOrder);
        tester.placeOrderAction.closeOrderDetails();
        tester.placeOrderAction
                .tearDown();
    }
}
