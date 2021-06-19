package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarketPlacePage extends AbstractPage {

    @FindBy(xpath = "//div[@class='market-place']")
    private WebElement marketPlace;

    public void selectProductToOrder(String product) {
        marketPlace.findElement(By.xpath("//div[@class='market-place']//a[text()='"
                + product + "']")).click();
    }


}
