package test.placeorder;

import actor.PlaceOrderTester;
import org.testng.annotations.Test;
import pages.PlaceOrderPage;
import test.TestBase;

public class OrderHistoryBulkTest extends TestBase {

    @Test
    public void orderHistoryBulk() {
        String product = "History Bulk";
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
        String fromTo = "13-11-2020 - 21-11-2020";
        String noOfLocations = "1";
        String weatherPara = "Min temperature, Max temperature, Feels like, Wind, Pressure, " +
                "Humidity, Clouds, Weather conditions, Rain, Snow";
        String fileFormatOptions = "CSV, JSON";
        String downLoadOption = "All locations in a single file";
        PlaceOrderPage.OrderDetail expectationOrder = new PlaceOrderPage.OrderDetail
                (fromTo, noOfLocations, weatherPara, fileFormatOptions, unit, downLoadOption);

        PlaceOrderTester tester = PlaceOrderTester.getInstance();
        tester.placeOrderAction
                .openMarketPlace().selectProduct(product)
                .addLocation(city, searchByLocation)
                .setTimePeriod(fromYear, fromMonth, fromDate, toYear, toMonth, toDate)
                .filter(unselectedWeatherPara, unit, formatFile)
                .submitOrder();
        PlaceOrderPage.OrderDetail confirmationOrder = tester.placeOrderAction.getOrderConfirmation();
        Validator.checkOrderDetail(expectationOrder, confirmationOrder);
        tester.placeOrderAction.closeOrderDetails();
        tester.placeOrderAction.tearDown();
    }
}
