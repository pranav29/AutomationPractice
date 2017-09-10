package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.ExtentListener;
import utilities.DataProviderClass;

@Listeners(ExtentListener.class)
public class searchItemTest extends BaseTest {
	
	private String path = "C:\\Users\\Pranav Paliwal\\Videos\\Learning\\Automation Testing\\2 Selenium scripts\\Java\\AutomationPractice\\src\\main\\java\\testData\\TestData.xlsx";
	private String sheetName = "searchItemTest";

	@Test(dataProvider="searchItemData")
	public void searchItem(String email, String password, String item, String expectedProductName) {
		
		homePage.clickHeaderSignIn();
		
		loginPage.login(email, password);
		
		myAccountPage.selectItemFromSearchList(item);
		
		String actualProductName=productPage.getProductName();
		
		Assert.assertEquals(actualProductName, expectedProductName);
	}
	
	@DataProvider(name="searchItemData")
	public Object[][] getTestData(){
		
		DataProviderClass data=new DataProviderClass();
		
		Object[][] searchItemData=data.getTestData(path, sheetName);
		
		return searchItemData;
	}
}
