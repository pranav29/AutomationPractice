package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.DriverSetUp;
import utilities.Screenshot;

public class ExtentListener implements ITestListener {
	
	private ExtentReports reports =  new ExtentReports("C:\\Users\\Pranav Paliwal\\Videos\\Learning\\Automation Testing\\2 Selenium scripts\\Java\\AutomationPractice.html");
	public static ExtentTest test;
	private WebDriver driver;

	public void onFinish(ITestContext arg0) {
		reports.endTest(test);
		reports.flush();
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		try {
			String path=Screenshot.takeScreenshot(DriverSetUp.getDriver(),arg0.getName());
			String imagePath= test.addScreenCapture(path);
			test.log(LogStatus.FAIL, arg0.getName()+ " failed",imagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		test= reports.startTest(arg0.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult arg0) {
		test.log(LogStatus.PASS, arg0.getMethod().getMethodName()+" passed successfully");
		
	}
	
}
