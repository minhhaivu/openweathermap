package pages.panel;

import objects.search.Location;
import objects.LocationType;
import org.openqa.selenium.WebDriver;
import pages.AbstractPage;

public class SearchByLocation extends AbstractPage implements Search {

    public SearchByLocation(WebDriver driver) {}

    @Override
    public void search(LocationType type) {
        if(!(type instanceof Location)) {
            throw new UnsupportedOperationException("Type is not expected");
        }

        // add location for ByLocation
    }
}
