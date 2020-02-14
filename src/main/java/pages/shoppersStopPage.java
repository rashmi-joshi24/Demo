package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class shoppersStopPage {

	WebDriver driver;
	
	public shoppersStopPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='dy-custom-slider']//div[contains(@class,'next-arrow')]")
	WebElement nextArrow;
	
	@FindBy(xpath="//div[contains(@id,'slick-slide') and @aria-hidden='false']//img")
	WebElement imgSliding;
	
	@FindBy(xpath="//div[@class='container container-responsive']//a[@title='MEN']")
	WebElement MenLink;
	
	@FindBy(xpath="(//div[@class='container container-responsive']//a[contains(text(),\"Men's Fragrance\")])[3]")
	WebElement MenFragranceLink;
	
	@FindBy(xpath="//ul[contains(@class,'text-right')]//a[contains(text(),'All Stores')]")
	WebElement allStoresLink;
	
	@FindBy(xpath="//select[@id='city-name']")
	WebElement selectCity;
	
	@FindBy(xpath="((//a[contains(text(),\"Men's Fragrance\")])[3]//parent::li/div/ul/li/div)[1]/ul")
	WebElement menAccessories;

	
	public void clickBannerSlider() throws InterruptedException
	{
		String text = imgSliding.getAttribute("title");
		nextArrow.click();
		
		while(!imgSliding.getAttribute("title").equals(text))
		{
			Thread.sleep(2000);
			nextArrow.click();
		}
	}
	
	public void getMenAccessories() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(MenLink).build().perform();
		Thread.sleep(1000);
		MenFragranceLink.click();
		Thread.sleep(1000);
		System.out.println(menAccessories.getText());
	}

	public void findStores()
	{
		allStoresLink.click();
		
		Select s = new Select(selectCity);
		List<WebElement> ele = s.getOptions();
		int count = ele.size();
		for(int i=0;i<count;i++)
		{
			String val = ele.get(i).getText();
			System.out.println(val);
			
			if(val.equals("Agra"))
			{
				System.out.println("Agra is available in the list");
			}
			else if(val.equals("Bhopal"))
			{
				System.out.println("Bhopal is available in the list");
			}
			else if(val.equals("Mysore"))
			{
				System.out.println("Mysore is available in the list");
			}
		}
		System.out.println(driver.getTitle());
	}
	
}
