package utilities;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class extentReportClass {
	
	public ExtentReports reports;
	public ExtentTest logger;

	@BeforeClass
	public void startClass()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss");
		String timeStamp = sdf.format(new Date());
		reports = new ExtentReports(System.getProperty("user.dir")+"/test-output/STMExtentReport"+timeStamp+".html");
	}
	
	@BeforeMethod
	public void startMethod(Method method)
	{
		logger = reports.startTest(method.getName());
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, "Test case is passed "+result.getName());
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, logger.addScreenCapture(CaptureScreenshot.screenshotPath())+"Test case is failed "+result.getName());
			//CaptureScreenshot.screenshot();
		}
	}
	
	@AfterClass
	public void endTest()
	{
		reports.endTest(logger);
		reports.flush();
	}
}
