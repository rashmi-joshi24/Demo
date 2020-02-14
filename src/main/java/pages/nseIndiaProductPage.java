package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.collect.Ordering;

public class nseIndiaProductPage {
	
	WebDriver driver;
		
	public nseIndiaProductPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[contains(text(),'Live Market')]")
	WebElement liveMarketLink;
	
	@FindBy(xpath="//li[@id='main_liveany']//a[contains(text(),'Top Ten Gainers')]")
	WebElement topTenGainersLink;
	
	@FindBy(xpath="//a[text()='Gainers']")
	WebElement gainersTab;
	
	@FindBy(xpath="//a[text()='Losers']")
	WebElement losersTab;
	
	public void getTable(String val)
	{
		Actions action = new Actions(driver);
		action.moveToElement(liveMarketLink).build().perform();
		topTenGainersLink.click();
		if(val.equals("Gainers"))
			gainersTab.click();
		else if(val.equals("Losers"))
			losersTab.click();
	}
	
	public String[][] getDataFromTable(String table)
	{
		String tableName = null;
		if(table.equals("Gainers"))
			tableName = "topGainers";
		else if(table.equals("Losers"))
			tableName = "topLosers";
		
		int rows = driver.findElements(By.xpath("//table[@id='"+tableName+"']//tr")).size(); 
		int cols = driver.findElements(By.xpath("//table[@id='"+tableName+"']//tr[1]/th")).size();
		String[][] data = new String[rows][cols];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				if(i==0)
					data[i][j] = driver.findElement(By.xpath("//table[@id='"+tableName+"']//tr[1]/th["+(j+1)+"]")).getText();
				else
					data[i][j] = driver.findElement(By.xpath("//table[@id='"+tableName+"']//tr["+(i+1)+"]/td["+(j+1)+"]")).getText();
			}
		}
		return data;
	}
	
	public void checkColumSortedinDescendingOrder(String table, String column)
	{
		String tableName = null;
		if(table.equals("Gainers"))
			tableName = "topGainers";
		else if(table.equals("Losers"))
			tableName = "topLosers";
		
		int rows = driver.findElements(By.xpath("//table[@id='"+tableName+"']//tr")).size(); 
		int cols = driver.findElements(By.xpath("//table[@id='"+tableName+"']//tr[1]/th")).size();
		int colNumber;
		for(int i=0;i<cols;i++)
		{
			if(driver.findElement(By.xpath("//table[@id='"+tableName+"']//tr[1]/th["+(i+1)+"]")).getAttribute("title").equals(column))
			{
				colNumber = i+1;
				break;
			}
		}
		
		List<Double> vals = new ArrayList();
		for(int i=2;i<=rows;i++)
		{
			Double value = Double.parseDouble(driver.findElement(By.xpath("//table[@id='"+tableName+"']//tr["+i+"]/td[3]")).getText());
			vals.add(value);
		}
		
		System.out.println(Ordering.natural().reverse().isOrdered(vals));
	}
}
