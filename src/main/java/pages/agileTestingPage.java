package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CaptureScreenshot;

public class agileTestingPage {
	
	public WebDriver driver;
	
	public agileTestingPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Certifications')]")
	WebElement certificationslink;
	
	@FindBy(xpath="//img[@alt='ATA Certifications']//parent::div//area")
	List<WebElement> icon;
	
	@FindBy(xpath="//area[@alt='CP-CCT']")
	WebElement cpcct;
	
	public void clickOnMenu()
	{
		certificationslink.click();
	}
	
	
	public void icons() throws IOException, InterruptedException
	{
		int count = icon.size();
		System.out.println("Total number of links are "+count);
		
		for(int i=0;i<count;i++)
		{
			String url = icon.get(i).getAttribute("href");
			System.out.println("Link is : "+url);
			
		}
		CaptureScreenshot.screenshot();
		Thread.sleep(2000);
				
		//Actions action = new Actions(driver);
		//action.moveToElement(cpcct).perform();
		Point point = cpcct.getLocation();
		int x = point.getX();
		int y = point.getY();
		System.out.println(" x "+x);
		System.out.println(" y "+y);
		Actions action = new Actions(driver);
		action.moveToElement(cpcct, 5, 5);
		//action.clickAndHold(cpcct).moveByOffset(x,y);
	
		Thread.sleep(4000);
		CaptureScreenshot.screenshot();
	}
	
	
	
}
