package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class base extends extentReportClass{
	public static WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\utilities\\data.properties");
		prop.load(fis);
		
		if(prop.getProperty("browser").equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\drivers\\chromedriver.exe");
			 driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	@BeforeTest
	public void launch() throws IOException
	{
		driver = initializeDriver();
		if(getClass().getName().contains("Agile"))
			driver.get(prop.getProperty("url1"));
		else if(getClass().getName().contains("NSE"))
			driver.get(prop.getProperty("url2"));
		else if(getClass().getName().contains("Shopper"))
			driver.get(prop.getProperty("url3"));
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
		driver = null;
	}
}
