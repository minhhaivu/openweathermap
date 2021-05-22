package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
}
