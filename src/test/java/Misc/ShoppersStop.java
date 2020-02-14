package Misc;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.shoppersStopPage;
import utilities.base;

public class ShoppersStop extends base{
	
	@Test
	public void bcde() throws InterruptedException
	{
		shoppersStopPage ss = new shoppersStopPage(driver);
		ss.clickBannerSlider();
		ss.getMenAccessories();
		ss.findStores();
	}
	
}
