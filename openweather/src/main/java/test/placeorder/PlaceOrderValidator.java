package test.placeorder;

import action.HistoricalDataArchivesAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import objects.product.CustomWeatherOrderDetail;
import org.testng.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderValidator {

    private static HistoricalDataArchivesAction historicalDataArchivesAction
            = new HistoricalDataArchivesAction();

    public static void checkCustomWeatherOrderDetail(CustomWeatherOrderDetail expectedOrder,
                                                     CustomWeatherOrderDetail actualOrder) {
        Assert.assertEquals(expectedOrder.getPeriodTime(), actualOrder.getPeriodTime());
        Assert.assertEquals(expectedOrder.getNoOfLocations(), actualOrder.getNoOfLocations());
        Assert.assertEquals(expectedOrder.getWeatherPara(), actualOrder.getWeatherPara());
        Assert.assertEquals(expectedOrder.getFileFormat(), actualOrder.getFileFormat());
        Assert.assertEquals(expectedOrder.getUnit(), actualOrder.getUnit());
        Assert.assertEquals(expectedOrder.getDownLoadOption(), actualOrder.getDownLoadOption());
    }

    public static void checkOrderDetailPageDisplay() {
        Assert.assertEquals(historicalDataArchivesAction.getPageTitle(),"Order Detail",
                "Order Detail Confirmation is not displayed!");
    }
}
