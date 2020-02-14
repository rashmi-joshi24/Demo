package Misc;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.nseIndiaPage;
import pages.nseIndiaProductPage;
import utilities.base;
import utilities.*;

public class NSEIndia extends base{
	
	String sheet="Company" ;
			
	@Test(priority=0)
	public void abc()
	{
		nseIndiaPage nse = new nseIndiaPage(driver);
		nse.getNumbersFromMarket();
	}
	
	@Test(priority=3,dataProvider="CompanyName")
	public void dataMultiple(String company) throws InterruptedException, IOException
	{
		nseIndiaPage nse1 = new nseIndiaPage(driver);
		nse1.searchCompany(company);
	}
	
	@Test(priority=1)
	public void aaa() throws IOException, InterruptedException
	{
		Thread.sleep(5000);
		nseIndiaProductPage nsep = new nseIndiaProductPage(driver);
		nsep.getTable("Gainers");
		String data[][] = nsep.getDataFromTable("Gainers");
		readExcel.addExcelSheet("Gainers", data);
		nsep.getTable("Losers");
		data = nsep.getDataFromTable("Losers");
		readExcel.addExcelSheet("Losers", data);
		nsep.checkColumSortedinDescendingOrder("Losers","% Change");
		
	}
	
	@Test(priority=2)
	public void bbb()
	{
		Assert.assertTrue(true);
	
	}
	
		
	@DataProvider(name="CompanyName")
	public Object[][] Comp() throws IOException 
	{
		return readExcel.readExcelSheet(sheet);
	}
}
