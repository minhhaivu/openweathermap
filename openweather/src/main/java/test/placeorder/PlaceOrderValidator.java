package test.placeorder;

import action.HistoricalDataArchivesAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import objects.product.CustomWeatherProduct;
import org.testng.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderValidator {

    private final static HistoricalDataArchivesAction historicalDataArchivesAction
            = new HistoricalDataArchivesAction();

    public static void checkCustomWeatherOrderDetail(CustomWeatherProduct expectedOrder,
                                                     CustomWeatherProduct actualOrder) {
        Assert.assertEquals(expectedOrder.getDatePeriod(), actualOrder.getDatePeriod());
        Assert.assertEquals(expectedOrder.getNumberOfLocation(), actualOrder.getNumberOfLocation());
        Assert.assertEquals(expectedOrder.getCustomWeatherPara(), actualOrder.getCustomWeatherPara());
        Assert.assertEquals(expectedOrder.getFileFormatInString(), actualOrder.getFileFormatInString());
        Assert.assertEquals(expectedOrder.getUnit(), actualOrder.getUnit());
        Assert.assertEquals(expectedOrder.getDownLoadOption(), actualOrder.getDownLoadOption());
    }

    public static void checkOrderDetailPageDisplay() {
        Assert.assertEquals(historicalDataArchivesAction.getPageTitle(), "Order Detail",
                "Order Detail Confirmation is not displayed!");
    }
}
