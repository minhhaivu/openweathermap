package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager extends DriverManager{
	
	/**
	* Create a Firefox Web Driver with given options & implicitly wait in timeout
	* 
	* @param driver
	*/
	@Override
	public void createWebDriver() {
		FirefoxOptions options= new FirefoxOptions();
		WebDriverManager.firefoxdriver().setup();
		options.addPreference("geo.enabled", false);
		this.driver= new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}
