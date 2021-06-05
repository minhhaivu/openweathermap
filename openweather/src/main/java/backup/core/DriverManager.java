package backup.core;

import org.openqa.selenium.WebDriver;
public abstract class DriverManager {
	protected WebDriver driver;
	protected abstract void createWebDriver ();
	
	/**
	* Quit driver & clean it
	* 
	* @param driver
	*/
	public void quitWebDriver () {
		if (null!=driver) {
			driver.quit();
			driver=null;
		}
	}
	
	/**
	* Create a driver if it is not initialized & maximize it
	* If the driver is available, get/reuse it.
	* 
	* @param driver
	*/
	public WebDriver getWebDriver() {
		if (null==driver) {
			createWebDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}

}
