package Misc;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.agileTestingPage;
import utilities.base;

public class Agile extends base{

	@Test
	public void abc() throws IOException, InterruptedException
	{
		Thread.sleep(5000);
		agileTestingPage atp = new agileTestingPage(driver);
		atp.clickOnMenu();
		atp.icons();
	
	}
}
