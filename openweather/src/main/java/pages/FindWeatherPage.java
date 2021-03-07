package pages;

import java.util.List;

import javax.annotation.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.Keys;

public class FindWeatherPage {
	WebDriver driver;
	
	 /**
	   * Initialize a driver for Find Weather Page
	   * 
	   * @param driver is a WebDriver
	   */
	public FindWeatherPage (WebDriver driver)
	{
		this.driver = driver;
	}
	
	//Locator section
	private By searchWeatherTextBox = By.id("q");
	private By notFoundText = By.xpath("//div[@id=\"forecast_list_ul\"]/div[text()=\"Not found\"]");
	private By searchWeatherTableRow = By.xpath("//div[@id=\"forecast_list_ul\"]/table/tbody/tr");
	private By searchWeatherTableCol = By.xpath("//div[@id=\"forecast_list_ul\"]/table/tbody/tr[1]/td");
	
	 /**
	   * Capture cityForecastWeatherLink for given city & country code
	   * 
	   * @param given city & country
	   */
	private By cityForecastWeatherLink(String city, String countrycode)
	{
		return By.xpath("//div[@id=\"forecast_list_ul\"]/table/tbody//a[contains(text(),'" + city + ", " + countrycode+ "')]");
	}
	
	 /**
	   * Search weather in given city & country code
	   * 
	   * @param given city & country code for search
	   */
	public void searchWeather (String city,@Nullable String countrycode) throws InterruptedException  
	{
		this.enterCity(city,countrycode);
		Thread.sleep(100);
		this.clickSearch();
	}
	
	 /**
	   * Check if Not Found result is returned when given city is invalid
	   * 
	   * @return boolean based on if Not Found text element is present
	   */
	public boolean checkNotFoundReturn () {
			 return driver.findElements(notFoundText).size() > 0;
	}
	
	 /**
	   * Enter given city & country code to Search Weather in your city textbox
	   * 
	   * @param given city & country code for search
	   */
	public void enterCity (String city, @Nullable String countrycode)
	{
			if((""==countrycode) || (countrycode==null))
			{
				driver.findElement(searchWeatherTextBox).sendKeys(city);
			}
			else 
				{
				driver.findElement(searchWeatherTextBox).sendKeys(city + ","+ countrycode);
				}
			}
	
	 /**
	   * Type Enter to search due to an AUTO bug - Search button does not work properly
	   * 
	   */
	public void clickSearch() 
	{
		//Due to an AUT bug. Have to work around by type Enter
		driver.findElement(searchWeatherTextBox).sendKeys(Keys.ENTER);
	}
	
	 /**
	   * Get row numbers of cities/results returned after searching weather in given city
	   * 
	   * @return Number of rows 
	   */
	public int getTableWeatherRowCount() 
	{
		
		List <WebElement> rows = driver.findElements(searchWeatherTableRow);
		return rows.size();
	}
	
	/**
	   * Get row columns of cities/results returned after searching weather in given city
	   * 
	   * @return Number of columns 
	   */
	public int getTableWeatherColumnCount()
	{
		List <WebElement> column = driver.findElements(searchWeatherTableCol);
		return column.size();
		
	}
	
	/**
	   * Get content/data (city & its detailed weather) of the returned table after searching weather in given city
	   * 
	   * @return Object [][] contains returned data
	   */
	public Object [][] getSearchWeatherData() 
	{
		Object[][] weatherData = null;
		int noRows = getTableWeatherRowCount();
		int noCols = getTableWeatherColumnCount();
		weatherData = new Object[noRows][noCols];
		for (int i=1;i<noRows+1;i++)
		{
			for (int j=1;j<noCols+1;j++)
			{
				WebElement cell = driver.findElement(By.xpath("//*[@id=\"forecast_list_ul\"]/table/tbody/tr["+i+"]/td["+j+"]"));
				weatherData[i-1][j-1]= cell.getText();
				System.out.println(weatherData[i-1][j-1]);
			}
		}
		return weatherData;
	}

	/**
	   * Check content/data (city & its detailed weather) of the returned table after searching weather in given city
	   * 
	   * @return boolean - True if returned city & country code == given city & country code. Otherwise, False
	   */
	public boolean checkSearchWeather (String city, String countrycode)
	{
		Object[][] weatherData = getSearchWeatherData();
		int noRows=weatherData.length;
		int noCols=weatherData[0].length;
		for (int i=0;i<noRows;i++)
		{
			for (int j=0;j<noCols;j++)
			{
				if (weatherData[i][j].toString().contains(city+","+countrycode)==false)
					return false;
			}
		}
	return true;
	}
	
	/**
	   * Click on returned city link to search forecast weather in given city
	   * 
	   * @param given city & country code
	   */
	public void searchForecastWeather (String city, String countrycode)
	{
		By foreCastLink = cityForecastWeatherLink(city,countrycode);
		driver.findElement(foreCastLink).click();
	}
}
