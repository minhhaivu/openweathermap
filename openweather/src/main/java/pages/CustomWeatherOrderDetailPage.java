package pages;

import element.Table;
import objects.product.CustomWeatherOrderDetail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomWeatherOrderDetailPage extends AbstractPage{

    @FindBy(xpath = "//div[@class='pop-up pop-up-confirmation']//h3[contains(text(),'Order details')]")
    private List<WebElement> orderDetailTtl;

    @FindBy(xpath = "//table[@class='confirmation-order']")
    private WebElement orderDetailTbl;

    @FindBy(xpath = "//div[@class='pop-up pop-up-confirmation']//*[@class='icon-close']")
    private WebElement orderDetailCloseBtn;

    public CustomWeatherOrderDetailPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public void closeOrderDetailsPopUp() {
        orderDetailCloseBtn.click();
    }

    public CustomWeatherOrderDetail getOrderDetail() {
        String periodTime = Table.getTableCellValueByRowName(orderDetailTbl, "From - To");
        String noOfLocations = Table.getTableCellValueByRowName(orderDetailTbl, "Number of locations");
        String weatherPara = Table.getTableCellValueByRowName(orderDetailTbl, "Weather parameters");
        String fileFormat = Table.getTableCellValueByRowName(orderDetailTbl, "File formats");
        String unit = Table.getTableCellValueByRowName(orderDetailTbl, "Units");
        String downLoadOption = Table.getTableCellValueByRowName(orderDetailTbl, "Download");

        return new CustomWeatherOrderDetail (periodTime, noOfLocations, weatherPara, fileFormat, unit, downLoadOption);
    }


}
