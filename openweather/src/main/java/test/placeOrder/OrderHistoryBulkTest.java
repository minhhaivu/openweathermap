package test.placeOrder;

import action.PlaceOrderAction;
import actor.PlaceOrderTester;
import org.testng.annotations.Test;
import test.TestBase;

public class OrderHistoryBulkTest extends TestBase {

    @Test
    public void orderHistoryBulk() {
        String product = "History Bulk";
        String city="Fort Lauderdale, FL, USA";
        String searchByLocation="By location";
        PlaceOrderTester tester = PlaceOrderTester.getInstance();
        tester.placeOrderAction.openMarketPlace().selectProduct(product)
                .addLocation(city,searchByLocation);
        ;



    }
}
