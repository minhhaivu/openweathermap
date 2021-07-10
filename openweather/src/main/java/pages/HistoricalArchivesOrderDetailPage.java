package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HistoricalArchivesOrderDetailPage extends AbstractPage{

    @FindBy(xpath = "//div[@class='pop-up pop-up-confirmation']//h3[contains(text(),'Order details')]")
    private List<WebElement> orderDetailTtl;

    public void HistoricalArchivesOrderDetailPage (WebDriver driver) {
        this.pageDriver=driver;
    }

    public String getPageTitle() {
        return pageDriver.getTitle();
    }
}
