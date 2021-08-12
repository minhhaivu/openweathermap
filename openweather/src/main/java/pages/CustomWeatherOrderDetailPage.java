package pages;

import element.Table;
import objects.product.OrderDetail;
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

    public OrderDetail getOrderDetail() {
        Table orderTable = new Table(pageDriver,orderDetailTbl);
        String periodTime = orderTable.getRowValue("From - To");
        String noOfLocations = orderTable.getRowValue("Number of locations");
        String weatherPara = orderTable.getRowValue("Weather parameters");
        String fileFormat = orderTable.getRowValue("File formats");
        String unit = orderTable.getRowValue("Units");
        String downLoadOption = orderTable.getRowValue("Download");
        return new OrderDetail(periodTime,Integer.valueOf(noOfLocations),weatherPara,fileFormat,unit,downLoadOption);
    }


}
