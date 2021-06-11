package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WeatherForecastPage {
	WebDriver driver;
	
	//Constructor of this PageObject
	public WeatherForecastPage (WebDriver driver)
	{
		this.driver = driver;
	}
	
	 /**
	   * Capture cityLable for given city & country code
	   * 
	   * @param given city & country
	   */
	private By cityLabel(String city, String countrycode)
	{
		return By.xpath("//div[@id=\"weather-widget\"]//h2[contains(text(),'" + city + ", " + countrycode+ "')]");
	}
	
	 /**
	   * Capture cityForecastWeatherLink for given city & country code
	   * 
	   * @param given city & country
	   * @return true if given city & country is present in cityLabel element of Forecast Weather page
	   */
	public boolean checkWeatherForecast(String city, String countrycode)
	{
		return driver.findElements(cityLabel(city,countrycode)).size() > 0;
	}

}
