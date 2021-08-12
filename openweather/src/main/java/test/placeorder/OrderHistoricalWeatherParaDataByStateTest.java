package test.placeorder;

import actor.Tester;
import objects.product.HistoricalArchives;
import org.testng.annotations.Test;
import test.TestBase;

public class OrderHistoricalWeatherParaDataByStateTest extends TestBase {
    @Test
    public void orderHistoricalWeather() {
        HistoricalArchives weatherProduct = new HistoricalArchives
                ("Historical Weather Data by State","Texas","2018");

        Tester tester = Tester.getInstance();

        tester.historicalDataArchivesAction.openMarketPlace().orderHistoricalArchives(weatherProduct);

        PlaceOrderValidator.checkOrderDetailPageDisplay();

        tester.historicalDataArchivesAction.close();
    }
}
