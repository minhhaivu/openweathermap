package test.placeorder;

import actor.Tester;
import element.DateInput;
import objects.product.CustomWeatherOrderDetail;
import objects.product.CustomWeatherProduct;
import objects.search.Coordinates;
import org.testng.annotations.Test;
import test.TestBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistoryForecastBulkTest extends TestBase {

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
        tester.customWeatherProductsAction
                .close();
    }

    private CustomWeatherProduct historyBulkProduct() {
        Coordinates coordinates = new Coordinates("26.122439","-80.137317");
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

        CustomWeatherProduct product = new CustomWeatherProduct("History Forecast Bulk");
        product.setLocation(coordinates);
        product.setFromDate(from);
        product.setToDate(to);
        product.setUnselectedWeatherPara(unselectedWeatherPara);
        product.setUnit(unit);
        product.setFileFormat(formatFile);
        return product;
    }

    private CustomWeatherOrderDetail orderDetailConfirmation(CustomWeatherProduct product) {
        String fromTo = DateInput.dateToString("dd-MM-y", product.getFromDate())
                + " - " + DateInput.dateToString("dd-MM-y", product.getToDate());
        String weatherPara = "Pressure, Wind, Humidity, Clouds, Dew point, Precipitation";
        String fileFormatOptions = "CSV, JSON";
        String downLoadOption = "All locations in a single file";

        return new CustomWeatherOrderDetail
                (fromTo, "1", weatherPara, fileFormatOptions, product.getUnit(), downLoadOption);
    }
}
