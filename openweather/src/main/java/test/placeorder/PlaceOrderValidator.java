package test.placeorder;

import action.HistoricalDataArchivesAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import objects.product.CustomWeatherProduct;
import org.testng.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderValidator {

    private static final HistoricalDataArchivesAction historicalDataArchivesAction
            = new HistoricalDataArchivesAction();

    public static void checkCustomWeatherOrderDetail(CustomWeatherProduct expectedOrder,
                                                     CustomWeatherProduct actualOrder) {
        Assert.assertTrue(expectedOrder.equals(actualOrder));
    }

    public static void checkOrderDetailPageDisplay() {
        Assert.assertEquals(historicalDataArchivesAction.getPageTitle(), "Order Detail",
                "Order Detail Confirmation is not displayed!");
    }
}
