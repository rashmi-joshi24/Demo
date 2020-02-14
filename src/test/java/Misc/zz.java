package Misc;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class zz {
	
	@BeforeTest
	public void a()
	{
		System.out.println("Before test");
	}
	
	@AfterTest
	public void aa()
	{
		System.out.println("After test");
	}
	
	@Test
	public void ac()
	{
		System.out.println("test 1");
	}
	
	@Test
	public void ab()
	{
		System.out.println("test 2");
	}
	
	@BeforeMethod
	public void bb()
	{
		System.out.println("Before method");
	}
	
	@AfterMethod
	public void acc()
	{
		System.out.println("After method");
	}
	
	@BeforeClass
	public void addd()
	{
		System.out.println("before class");
	}
	
	@AfterClass
	public void aaddd()
	{
		System.out.println("after class");
	}

}
