package action;

import driver.DriverGetter;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;

public abstract class ActionBase {
    private String browserType;

    public ActionBase() {
        browserType = "chrome";
    }

    protected <T extends AbstractPage> T getPageInstance(Class<T> clazz) {
        return PageFactory.initElements(DriverGetter.getDriver(browserType), clazz);
    }
}
