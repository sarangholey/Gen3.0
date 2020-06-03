package com.qa.amazonind.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
/**
 * @author Sarang
 *	This class holding up
 *	Browser and Properties file initialization
 *	Code with take screenshot method
 */
public class BasePage {

	public WebDriver driver;
	public Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * @author Sarang
	 * This method is for WebDriver initialization
	 * with different browser configurations
	 * @return
	 */
	public WebDriver init_driver(Properties prop)
	{
		String browserName = prop.getProperty("browser");
		System.out.println("Running on ----> " + browserName + " browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			if(prop.getProperty("headless").equalsIgnoreCase("yes"))
			{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			//tdriver.set(driver);
			}
			else {
				//driver = new ChromeDriver();
				tlDriver.set(new ChromeDriver());
			}
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{	
			if(prop.getProperty("headless").equalsIgnoreCase("yes"))
			{
				FirefoxOptions fb = new FirefoxOptions();
				fb.addArguments("--headless");
				//driver = new FirefoxDriver(fb);
				tlDriver.set(new FirefoxDriver(fb));
			}
			else {
				//driver = new FirefoxDriver();
				tlDriver.set(new FirefoxDriver());
			}
			
		}
		else if(browserName.equalsIgnoreCase("opera"))
		{
			//driver = new OperaDriver();
			tlDriver.set(new OperaDriver());
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		}
		else {
			System.out.println(browserName + " is not found, please pass browser name as chrome or firefox or opera");
		}
		
		if(prop.getProperty("maximize").equals("yes")) {
			getDriver().manage().window().maximize();
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("ImplicitWait")), TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("PageLoadTimeout")), TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		
		//return getDriver();
		return getDriver();
	}
	
	/**
	 * @author Sarang
	 * This method returns a properties class object
	 * with initialized config.properties file 
	 * @return
	 */
	public Properties init_prop()
	{
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("Config.properties file not found please give correct path");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException occured while loading the config.properties file");
			e.printStackTrace();
		}
		
		return prop;
	}
		
	/**
	 * @author Sarang
	 * This method takes screenshot and returns 
	 * the path of the captured file
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
		
}
