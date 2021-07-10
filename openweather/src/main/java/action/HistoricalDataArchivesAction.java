package action;

import objects.product.HistoricalArchives;
import pages.HistoricalArchivesOrderDetailPage;
import pages.HistoricalDataArchivesPage;
import pages.MarketPlacePage;

public class HistoricalDataArchivesAction extends ActionBase {
    private final MarketPlacePage marketPlacePage;
    private final HistoricalDataArchivesPage historicalDataArchivesPage;
    private final HistoricalArchivesOrderDetailPage historicalArchivesOrderDetailPage;


    public HistoricalDataArchivesAction() {

        super();
        marketPlacePage = getPageInstance(MarketPlacePage.class);
        historicalDataArchivesPage = getPageInstance(HistoricalDataArchivesPage.class);
        historicalArchivesOrderDetailPage = getPageInstance(HistoricalArchivesOrderDetailPage.class);

    }

    public HistoricalDataArchivesAction openMarketPlace() {
        marketPlacePage.open();

        return this;
    }

    public void orderHistoricalArchives(HistoricalArchives product) {
        marketPlacePage.selectProductToOrder(product.getName());

        historicalDataArchivesPage.selectState(product.getState());
        historicalDataArchivesPage.selectYear(product.getYear());

    }

    public String getPageTitle() {

        return historicalArchivesOrderDetailPage.getPageTitle();
    }

    @Override
    public void close() {
        historicalDataArchivesPage.close();
    }
}
