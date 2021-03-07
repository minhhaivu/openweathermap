package core;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IEDriverManager extends DriverManager{
	
	/**
	* Create a IE Web Driver with given options & implicitly wait in timeout
	* 
	* @param driver
	*/
	@Override
	public void createWebDriver() {
		InternetExplorerOptions options= new InternetExplorerOptions();
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		options.ignoreZoomSettings();
		WebDriverManager.iedriver().setup();
		this.driver= new InternetExplorerDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}
