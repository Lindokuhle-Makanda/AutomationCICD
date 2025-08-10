package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	WebDriver driver;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal(); // create thread fot each entry so that there wont be mix match when running tests parallel
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);// it will assign 1 unit thread id(ErrorValidationTest)
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		//First option test.log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable()); 
		//screen shot,attach it to the report
		
		//code that will get the driver from the results
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String path = null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// not implemented
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}
	@Override
	public void onStart(ITestContext context) {
		// not implemented
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();//This will ensure that all reports are developed
	}
}
