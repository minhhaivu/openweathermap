package test.placeorder;

import actor.PlaceOrderTester;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import test.TestBase;

import java.util.List;

public class OrderHistoricalWeatherDataByStateTest extends TestBase {
    @Test
    public void orderHistoricalWeather() {
        String product = "Historical Weather Data by State";
        String state = "Texas";
        String year = "2018";

        PlaceOrderTester tester = PlaceOrderTester.getInstance();
        tester.placeOrderAction.openMarketPlace().selectProduct(product)
                .orderHistoricalWeatherDataByState(state, year);
        List<WebElement> orderTitle = tester.placeOrderAction.getOrderTitle();
        Validator.checkOrderDetailPageDisplay(orderTitle);
        tester.placeOrderAction.tearDown();
    }
}
