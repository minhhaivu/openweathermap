package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchPage extends AbstractPage {

    @FindBy(id = "forecast_list_ul")
    private WebElement forecastList;

    @FindBy(xpath = "//h2[contains(text(), 'Weather in your city')]")
    private WebElement title;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // TODO: use a common method to get row/cell text
    public List<CityInfo>  getSearchResult() {
        List<CityInfo> cities = new ArrayList<>();
            for (WebElement row : getTableRows()) {
                String name = row.findElement(By.tagName("a")).getText();
                String temperature =
                        row.findElement(By.xpath("//span[@class='badge badge-info']")).getText();
                cities.add(new CityInfo(name, temperature));
            }

        return cities;
   }

    private List<WebElement> getTableRows() {
        // TODO: handle both cases: has result and no result
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(forecastList));
        return forecastList.findElements(By.xpath("//tr"));
    }

    @Getter
    @AllArgsConstructor
    public class CityInfo {
        private String cityName;
        private String temperature;
    }
}
