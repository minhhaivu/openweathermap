package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {

    @FindBy(id = "forecast_list_ul")
    private WebElement forecastList;

    @FindBy(xpath = "//h2[contains(text(), 'Weather in your city')]")
    private WebElement title;

    public SearchPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public List<CityInfo> getSearchResult() {
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
        new WebDriverWait(pageDriver, 10).until(ExpectedConditions.visibilityOf(title));
        return forecastList.findElements(By.xpath("//tr"));
    }

    public List<WebElement> getNotFoundText (){
        new WebDriverWait(pageDriver, 10).until(ExpectedConditions.visibilityOf(title));
        return forecastList.findElements(By.xpath("//div[contains(text(),'Not found')]"));
    }

    @Getter
    @AllArgsConstructor
    public static class CityInfo {
        private final String cityName;
        private final String temperature;
    }
}
