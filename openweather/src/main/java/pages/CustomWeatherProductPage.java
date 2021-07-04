package pages;

import objects.search.Coordinates;
import objects.search.Location;
import objects.search.Import;
import objects.LocationType;
import org.openqa.selenium.WebDriver;
import pages.panel.Search;
import pages.panel.SearchByCoordinates;
import pages.panel.SearchByLocation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomWeatherProductPage extends AbstractPage {

    public CustomWeatherProductPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    private void search(LocationType location) {
        if (location instanceof Location) {
            String locationString = (String) location.getInfo();
            // enter locationString to textbox
        } else if(location instanceof Coordinates) {
            Map<String, String> map = (HashMap<String,String>) location.getInfo();
            // enter long, lat
        } else if(location instanceof Import) {
            String filePath = (String) location.getInfo();
            // handle popup windows and enter filepath
        } else {
            throw  new UnsupportedOperationException();
        }
    }

    public CustomWeatherProductPage inputSearchString(LocationType type) {
        Search searchPanel;

        if(type instanceof Location) {
            searchPanel = new SearchByLocation(pageDriver);
        } else if(type instanceof Coordinates) {
            searchPanel = new SearchByCoordinates(pageDriver);
        } else if(type instanceof Import) {
            searchPanel = new pages.panel.Import(pageDriver);
        } else {
            throw new UnsupportedOperationException("");
        }

        searchPanel.search(type);
        return this;
    }

    public CustomWeatherProductPage selectFromDate(Date date) {
        return this;
    }

    public CustomWeatherProductPage selectToDate(Date date) {
        return this;
    }

    public CustomWeatherProductPage addLocation() {
        return this;
    }
}
