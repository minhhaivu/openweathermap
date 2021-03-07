package searchweather;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import core.DriverManager;
import core.DriverManagerFactory;
import data.DriverType;
import pages.FindWeatherPage;
import pages.WeatherForecastPage;
import data.Constant;


/**
* TC4	Search forecast weather in valid cities
* TO1	Verify that current time & detailed weather along map & the weather forecast in minutes, in hours, in 8 next days are displayed in Forecast Weather page	
* TO2	Verify that forecast weather page is updated correspondingly to the selected city	
* 
* Step 1	Navigate to https://openweathermap.org/	
* Step 2	"Enter city into Weather in your city textbo
* 			City: London"	
* Step 3	Type Enter	
* Step 4	"Click on the first city returned
* 			City: London, GB"	
* Step 5	Verify the returned result	"Current time & detailed weather along map & the weather forecast in minutes, in hours, in 8 next days are displayed 
* 			City: London, GB"
* Step 6	Back to previous page	
* Step 7	"Click on the second city returned
* 			City: London, CA"	
* Step 8	Verify that forecast weather page is updated correspondingly to the selected city	"Current time & detailed weather along map & the weather forecast in minutes, in hours, in 8 next days are displayed 
* 			City: London, CA"
* Step 9	Close the web page	
* 
* @param driver
*/
@Listeners(utilities.ListenerReport.class)
public class SearchForecastWeather {
	DriverManager driverManager;
	WebDriver driver;
	
  @BeforeTest
  @Parameters("browser")
  public void setBrowser (@Optional("CHROME") DriverType browser) {
	 driverManager = DriverManagerFactory.getDriverManager(browser);
	 driver=driverManager.getWebDriver();
	 driver.get(Constant.URL);
  }	 
  
  @Test
  public void searchForecastWeather () throws InterruptedException
  {
	 FindWeatherPage openWeather = new FindWeatherPage(driver);
	 WeatherForecastPage forecastWeather=new WeatherForecastPage(driver);
	 openWeather.searchWeather(Constant.city,null);
	 openWeather.checkSearchWeather(Constant.city,"");
	 openWeather.searchForecastWeather(Constant.city, Constant.countrycode1);
	 forecastWeather.checkWeatherForecast(Constant.city,Constant.countrycode1);
	 driver.navigate().back();
	 openWeather.searchForecastWeather(Constant.city, Constant.countrycode2);
	 forecastWeather.checkWeatherForecast(Constant.city,Constant.countrycode2);
  }
  
  @AfterClass
  public void quitDriver () {
	 driverManager.quitWebDriver();
  }
}
