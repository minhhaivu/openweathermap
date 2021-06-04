package backup.core;

import backup.data.DriverType;

public class DriverManagerFactory {

    /**
     * Create Driver Manager as the given type
     *
     * @param type: CHROME, FIREFOX, IE
     */
    public static DriverManager getDriverManager(DriverType type) {
        DriverManager driverManager;
        switch (type) {
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            default:
                driverManager = new IEDriverManager();
                break;
        }
        return driverManager;
    }

}
