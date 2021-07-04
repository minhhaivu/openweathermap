package pages.panel;

import objects.LocationType;
import org.openqa.selenium.WebDriver;
import pages.AbstractPage;

public class Import extends AbstractPage implements Search {

    public Import(WebDriver driver) {
        pageDriver = driver;
    }

    @Override
    public void search(LocationType type) {
        if(!(type instanceof objects.search.Import)) {
            throw new UnsupportedOperationException("Type is not expected");
        }

        // add location for ByLocation
    }
}
