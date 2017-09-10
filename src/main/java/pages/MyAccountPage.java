package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// ----- section: My Account ------

	// web element: order history and details

	@FindBy(xpath = "//a[@title='Orders']")
	private WebElement orderHistoryAndDetails;

	public void clickOrderHistoryAndDetails() {
		clickLink(orderHistoryAndDetails);
	}

	// web element: credit slips

	@FindBy(xpath = "//a[@title='Credit slips']")
	private WebElement creditSlips;

	public void clickMyCreditSlips() {
		clickLink(creditSlips);
	}

	// web element: my addresses

	@FindBy(xpath = "//a[@title='Addresses']")
	private WebElement myAddresses;

	public void clickMyAddresses() {
		clickLink(myAddresses);
	}

	// web element: my personal information

	@FindBy(xpath = "//a[@title='Information']")
	private WebElement myPersonalInformation;

	public void clickMyPersonalInformation() {
		clickLink(myPersonalInformation);
	}
	
	// web element: my wishlists

	@FindBy(xpath = "//a[@title='My wishlists']")
	private WebElement myWishlists;

	public void clickMyWishlists() {
		clickLink(myWishlists);
	}
		
	// web element: Home button
	
	@FindBy(xpath = "//a[@title='Home']")
	private WebElement homeButton;
	
	public void clickHomeButton(){
		homeButton.click();
	}
}
