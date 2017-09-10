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
public class placeOrderTest extends BaseTest {

	private String path = "C:\\Users\\Pranav Paliwal\\Videos\\Learning\\Automation Testing\\2 Selenium scripts\\Java\\AutomationPractice\\src\\main\\java\\testData\\TestData.xlsx";
	private String sheetName = "placeOrderTest";

	@Test(dataProvider="placeOrderData")
	public void verifyOrder(String email, String password, String pageName, String productName,
			String expectedMessage) {

		homePage.clickHeaderSignIn();
		
		loginPage.login(email, password);

		myAccountPage.clickNavigation(pageName);

		homePage.clickAddToCart(productName).clickProceedtoCheckout();

		orderPage.clickProceedToCheckoutCartSummaryLink().clickProceedToCheckoutAddressSectionButton()
				.clickTermsOfServiceCheckbox().clickProceedToCheckoutShippingSectionButton().clickPayByWireLink()
				.clickConfirmButton();

		String actualMessage = orderPage.getOrderConfirmationMessage();

		ExtentListener.test.log(LogStatus.INFO,
				"Expected message: " + expectedMessage + " Actual message: " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider(name = "placeOrderData")
	public Object[][] getTestData() {
		DataProviderClass dataProvider = new DataProviderClass();

		Object[][] placeOrderTestData = dataProvider.getTestData(path, sheetName);

		return placeOrderTestData;
	}
}
