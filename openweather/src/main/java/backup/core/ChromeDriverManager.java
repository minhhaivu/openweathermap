package backup.core;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager extends DriverManager{
	
	/**
	* Create a Chrome Web Driver with given options & implicitly wait in timeout
	* 
	* @param driver
	*/
	@Override
	public void createWebDriver() {
		ChromeOptions options= new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		this.driver= new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
