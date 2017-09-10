package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataProviderClass;

@Listeners(listeners.ExtentListener.class)
public class validLoginTest extends BaseTest {

	private String path = "C:\\Users\\Pranav Paliwal\\Videos\\Learning\\Automation Testing\\2 Selenium scripts\\Java\\AutomationPractice\\src\\main\\java\\testData\\TestData.xlsx";
	private String sheetName = "validLoginTest";

	@Test(dataProvider = "validLoginData")
	public void loginTest(String userName, String password, String expectedAccountName) {

		homePage.clickHeaderSignIn();

		loginPage.login(userName, password);
		
		String actualAccountName=myAccountPage.getAccountName();
		
		Assert.assertEquals(actualAccountName, expectedAccountName);

	}

	@DataProvider(name = "validLoginData")
	public Object[][] getTestData() {
		
		DataProviderClass dataProvider=new DataProviderClass();

		Object[][] loginData = dataProvider.getTestData(path, sheetName);

		return loginData;
	}

}
