package test.placeorder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.PlaceOrderPage;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderValidator {

    public static void checkOrderDetail(PlaceOrderPage.OrderDetail expectedOrder,
                                        PlaceOrderPage.OrderDetail actualOrder) {
        Assert.assertEquals(expectedOrder.getPeriodTime(), actualOrder.getPeriodTime());
        Assert.assertEquals(expectedOrder.getNoOfLocations(), actualOrder.getNoOfLocations());
        Assert.assertEquals(expectedOrder.getWeatherPara(), actualOrder.getWeatherPara());
        Assert.assertEquals(expectedOrder.getFileFormat(), actualOrder.getFileFormat());
        Assert.assertEquals(expectedOrder.getUnit(), actualOrder.getUnit());
        Assert.assertEquals(expectedOrder.getDownLoadOption(), actualOrder.getDownLoadOption());
    }

    public static void checkOrderDetailPageDisplay(boolean isDisplayed) {
        Assert.assertTrue(isDisplayed,
                "Order Detail Confirmation is not displayed!");
    }
}
