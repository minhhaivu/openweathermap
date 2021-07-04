package test.placeorder;

import actor.Tester;
import org.testng.annotations.Test;
import pages.PlaceOrderPage;
import test.TestBase;

public class OrderHistoricalWeatherDataByStateTest extends TestBase {
    @Test
    public void orderHistoricalWeather() {
        PlaceOrderPage.Product weatherProduct = new PlaceOrderPage.Product();
        weatherProduct.setName("Historical Weather Data by State");
        weatherProduct.setState("Texas");
        weatherProduct.setYear("2018");

        Tester tester = Tester.getInstance();
        tester.placeOrderAction.openMarketPlace().orderProduct(weatherProduct);
        boolean isDisplayed = tester.placeOrderAction.isOrderDetailConfirmationDisplayed();
        PlaceOrderValidator.checkOrderDetailPageDisplay(isDisplayed);
//        tester.placeOrderAction.tearDown();
    }
}
