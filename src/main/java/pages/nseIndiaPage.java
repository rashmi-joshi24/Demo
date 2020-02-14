package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import utilities.CaptureScreenshot;

public class nseIndiaPage {

	WebDriver driver;
	
	public String linkValue;
	
	public nseIndiaPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='marketIndices']//ul[@class='advanceTab']/li")
	List<WebElement> watch;
	
	@FindBy(xpath="//input[@id='keyword']")
	WebElement searchBox;
	
	@FindBy(xpath="//p[@class='title']//span[@id='companyName']")
	WebElement companyName;
	
	@FindBy(xpath="//a[contains(text(),'Face Value')]//following-sibling::span")
	WebElement faceValue;
	
	@FindBy(xpath="//a[contains(text(),'52 week high')]//following-sibling::span[1]")
	WebElement _52weekHigh;
	
	@FindBy(xpath="//a[contains(text(),'52 week low')]//following-sibling::span[1]")
	WebElement _52weekLow;
		

	public void getNumbersFromMarket()
	{
		int count = watch.size();
		
		for(int i=0;i<count;i++)
		{
			if(watch.get(i).getAttribute("id").equals("advances"))
			{
				String[] valAdvances = watch.get(i).getText().split("\\s");
				System.out.println("Number of advances = "+valAdvances[1]);
			}
			else if(watch.get(i).getAttribute("id").equals("unchanged"))
			{
				String[] valUnchanged = watch.get(i).getText().split("\\s");
				System.out.println("Number of unchanged = "+valUnchanged[1]);
			}
			else if(watch.get(i).getAttribute("id").equals("declines"))
			{
				String[] valDeclines = watch.get(i).getText().split("\\s");
				System.out.println("Number of declines = "+valDeclines[1]);
			}
		}
	}
	
	public void searchCompany(String company) throws InterruptedException, IOException
	{
		searchBox.clear();
		searchBox.sendKeys(company);
		Thread.sleep(1000);
		searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		Assert.assertTrue("Company name does not match", companyName.getText().contains(company));
		System.out.println("Company Name is : "+company);
		System.out.println("Face value is "+faceValue.getText());
		System.out.println("52 week high value is "+_52weekHigh.getText());
		System.out.println("52 week low value is "+_52weekLow.getText());
		CaptureScreenshot.screenshot();
		Thread.sleep(2000);
	}
	
}
