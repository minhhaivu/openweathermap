package pages.panel;

import objects.search.Coordinates;
import objects.LocationType;
import org.openqa.selenium.WebDriver;
import pages.AbstractPage;

public class SearchByCoordinates extends AbstractPage implements Search {

    public SearchByCoordinates(WebDriver driver) {
        this.pageDriver = driver;
    }

    @Override
    public void search(LocationType type) {
        if (!(type instanceof Coordinates)) {
            throw new UnsupportedOperationException("");
        }

        // search by coordinates
    }
}
