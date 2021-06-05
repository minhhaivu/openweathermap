package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverGetter {
    private static WebDriver driver;

    public static WebDriver getDriver(String browserType) {
        if (Objects.isNull(driver)) {
            driver = initDriver(browserType);
        }

        return driver;
    }

    private static WebDriver initDriver(String browserType) {
        WebDriver driver = null;

        switch (browserType) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(initFirefoxOptions());
                break;
            case "ie":
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(initChromeOptions());
                break;
        }

        return driver;
    }

    private static ChromeOptions initChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        return options;
    }

    private static FirefoxOptions initFirefoxOptions () {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("geo.enabled", false);
        return options;
    }
}
