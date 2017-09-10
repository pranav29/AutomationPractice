package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import listeners.ExtentListener;
import utilities.DataProviderClass;

@Listeners(listeners.ExtentListener.class)
public class invalidLoginTest extends BaseTest {

	private String path = "C:\\Users\\Pranav Paliwal\\Videos\\Learning\\Automation Testing\\2 Selenium scripts\\Java\\AutomationPractice\\src\\main\\java\\testData\\TestData.xlsx";
	private String sheetName = "invalidLoginTest";

	@Test(dataProvider = "invalidLoginData")
	public void loginTest(String userName, String password, String expectedErrorMessage) {

		homePage.clickHeaderSignIn();

		loginPage.login(userName, password);
		
		String actualErrorMessage=loginPage.getCreateAccountError();
		
		ExtentListener.test.log(LogStatus.INFO, "Actual error message: "+actualErrorMessage+" Expected error message: "+expectedErrorMessage);
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

	}

	@DataProvider(name = "invalidLoginData")
	public Object[][] getTestData() {
		
		DataProviderClass dataProvider=new DataProviderClass();

		Object[][] loginData = dataProvider.getTestData(path, sheetName);

		return loginData;
	}

}
