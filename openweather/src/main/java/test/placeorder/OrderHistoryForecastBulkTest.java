package test.placeorder;

import actor.Tester;
import pages.element.DateInput;
import org.testng.annotations.Test;
import pages.PlaceOrderPage;
import test.TestBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistoryForecastBulkTest extends TestBase {

    @Test
    public void orderHistoryBulk() {
        PlaceOrderPage.Product product = historyBulkProduct();
        PlaceOrderPage.OrderDetail expectationOrder = orderDetailConfirmation(product);
        Tester tester = Tester.getInstance();

        tester.placeOrderAction
                .openMarketPlace().orderProduct(product);
        PlaceOrderPage.OrderDetail confirmationOrder = tester.placeOrderAction.getOrderDetailConfirmation();
        PlaceOrderValidator.checkOrderDetail(expectationOrder, confirmationOrder);
        tester.placeOrderAction.closeOrderDetails();
        tester.placeOrderAction
                .close();
    }

    private PlaceOrderPage.Product historyBulkProduct() {
        String city = "Fort Lauderdale FL, USA";
        String searchByLocation = "By location";
        PlaceOrderPage.Location location = new PlaceOrderPage.Location(searchByLocation, city);
        String fromDate = "13-Nov-2020";
        String toDate = "21-Nov-2020";
        Date from = DateInput.stringToDate("dd-MMM-yyyy", fromDate);
        Date to = DateInput.stringToDate("dd-MMM-yyyy", toDate);
        List<String> unselectedWeatherPara = new ArrayList<>();
        unselectedWeatherPara.add("Temperature");
        String unit = "Imperial (Fahrenheit, hPa, miles/hour, mm/h)";
        List<String> formatFile = new ArrayList<>();
        formatFile.add("CSV");
        formatFile.add("JSON");

        PlaceOrderPage.Product product = new PlaceOrderPage.Product();
        product.setName("History Bulk");
        product.setLocation(location);
        product.setFromDate(from);
        product.setToDate(to);
        product.setWeatherPara(unselectedWeatherPara);
        product.setUnit(unit);
        product.setFileFormat(formatFile);
        return product;
    }

    private PlaceOrderPage.OrderDetail orderDetailConfirmation(PlaceOrderPage.Product product) {
        String fromTo = DateInput.dateToString("dd-MM-y", product.getFromDate())
                + " - " + DateInput.dateToString("dd-MM-y", product.getToDate());
        String weatherPara = "Min temperature, Max temperature, Feels like, Wind, Pressure, " +
                "Humidity, Clouds, Weather conditions, Rain, Snow";
        String fileFormatOptions = "CSV, JSON";

        return new PlaceOrderPage.OrderDetail
                (fromTo, "1", weatherPara, fileFormatOptions, product.getUnit(), product.getDownLoadOption());
    }
}
