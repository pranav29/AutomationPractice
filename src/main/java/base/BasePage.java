package base;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import listeners.ExtentListener;

public class BasePage {

	protected WebDriver driver;
	protected Actions action;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		action = new Actions(this.driver);
		PageFactory.initElements(this.driver, this);
	}

	// ------ SECTION: NAVIGATION BREADCRUMB -----

	@FindBy(css = ".breadcrumb.clearfix>a")
	List<WebElement> navigationLinks;

	public void clickNavigation(String pageName) {

		for (int i = 0; i < 4; i++) {
			try {
				for (WebElement navigationTab : navigationLinks) {
					if (navigationTab.getAttribute("title").equals(pageName)) {
						ExtentListener.test.log(LogStatus.INFO, "Navigating to: " + pageName + " using breadcrumbs");
						navigationTab.click();
						break;
					}
				}
			} catch (StaleElementReferenceException e) {
				ExtentListener.test.log(LogStatus.ERROR, "Recovering from StaleElementReferenceException.");

			}
		}
	}

	// ----- SECTION: HEADER ------

	// Finding and performing actions on web elements

	// Web element: Logo

	@FindBy(xpath = "//a[@href='http://automationpractice.com/']")
	private WebElement headerLogo;

	public void clickLogo() {
		headerLogo.click();
	}

	// Web element: Search item

	@FindBy(id = "search_query_top")
	WebElement searchBox;

	@FindBy(name = "submit_search")
	WebElement submitSearch;

	public void searchItem(String itemName) {
		searchBox.clear();
		searchBox.sendKeys(itemName);
		submitSearch.click();
	}
	
	// Web element: Item Search Predictions
	
	@FindBy(xpath="//div[@class='ac_results']//li")
	List<WebElement> itemSearchPredictions;
	
	public void selectItemFromSearchList(String itemName){
		searchBox.clear();
		searchBox.sendKeys(itemName);
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElements(itemSearchPredictions));
		
		for(WebElement item:itemSearchPredictions){
			if(item.getText().contains(itemName)){
				item.click();
			}
		}
	}

	// Web element: header Contact Us

	@FindBy(xpath = "//a[text()='Contact us']")
	WebElement headerContactUs;

	public void clickHeaderContactUs() {
		headerContactUs.click();
	}

	// Web element: Sign in

	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	WebElement headerSignIn;

	public void clickHeaderSignIn() {
		headerSignIn.click();
	}
	
	// Web element: Account name
	
	@FindBy(xpath="//a[@class='account']")
	WebElement headerAccountNameLink;
	
	public void clickHeaderAccountNameLink(){
		headerAccountNameLink.click();
	}
	
	@FindBy(xpath="//a[@class='account']/span")
	WebElement accountName;
	
	public String getAccountName(){
		String name=accountName.getText();
		ExtentListener.test.log(LogStatus.INFO, "Account name is: "+name);
		return name;
	}

	// Web element: headerWomenTab

	@FindBy(xpath = "//a[@title='Women']")
	private WebElement headerWomenTab;

	public void clickHeaderWomenTab() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Women tab.");
		headerWomenTab.click();
	}

	// Web element: headerDressesTab

	@FindBy(xpath = ".//*[@id='block_top_menu']/ul/li[2]/a")
	private WebElement headerDressesTab;

	public void clickHeaderDressesTab() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Dresses tab.");
		headerDressesTab.click();
	}

	// Web element: headerTshirtsTab

	@FindBy(xpath = "//div[@id='block_top_menu']/ul/li[3]/a")
	private WebElement headerTshirtsTab;

	public void clickHeadersTshirtsTab() {
		headerTshirtsTab.click();
	}

	// ---- SECTION: FOOTER -----

	// Web element: newsletter email

	@FindBy(id = "newsletter-input")
	private WebElement newsletterEmailInput;

	@FindBy(name = "submitNewsletter")
	private WebElement submitNewsletter;

	public BasePage submitNewsletterEmail(String email) {
		ExtentListener.test.log(LogStatus.INFO, "entering email as " + email + " in Newsletter field");
		newsletterEmailInput.sendKeys(email);
		ExtentListener.test.log(LogStatus.INFO, "submitting email as " + email + " in Newsletter");
		submitNewsletter.click();
		return this;
	}

	// Web element: footerWomenLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'Women')]")
	private WebElement footerWomenLink;

	public void clickFooterWomenLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerWomenLink");
		clickLink(footerWomenLink);
	}

	// Web element: footerSpecialsLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'Specials')]")
	private WebElement footerSpecialsLink;

	public void clickFooterSpecialsLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerSpecialsLink");
		clickLink(footerSpecialsLink);
	}

	// Web element: footerNewProductsLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'New products')]")
	private WebElement footerNewProductsLink;

	public void clickFooterNewProductsLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerNewProductsLink");
		clickLink(footerNewProductsLink);
	}

	// Web element: footerBestSellersLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'Best sellers')]")
	private WebElement footerBestSellersLink;

	public void clickFooterBestSellersLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerBestSellersLink");
		clickLink(footerBestSellersLink);
	}

	// Web element: footerOurStoresLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'Our stores')]")
	private WebElement footerOurStoresLink;

	public void clickFooterOurStoresLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerOurStoresLink");
		clickLink(footerOurStoresLink);
	}

	// Web element: footerContactUsLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'Contact us')]")
	private WebElement footerContactUsLink;

	public void clickFooterContactUsLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerContactUsLink");
		clickLink(footerContactUsLink);
	}

	// Web element: footerTermsAndConditionsLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'Terms and conditions')]")
	private WebElement footerTermsAndConditionsLink;

	public void clickFooterTermsAndConditionsLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerTermsAndConditionsLink");
		clickLink(footerTermsAndConditionsLink);
	}

	// Web element: footerAboutUsLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'About us')]")
	private WebElement footerAboutUsLink;

	public void clickFooterAboutUsLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerAboutUsLink");
		clickLink(footerAboutUsLink);
	}

	// Web element: footerSitemapLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'Sitemap')]")
	private WebElement footerSitemapLink;

	public void clickFooterSitemapLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerSitemapLink");
		clickLink(footerSitemapLink);
	}

	// Web element: footerMyOrdersLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'My orders')]")
	private WebElement footerMyOrdersLink;

	public void clickFooterMyOrdersLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerMyOrdersLink");
		clickLink(footerMyOrdersLink);
	}

	// Web element: footerMyCreditSlipsLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'My credit slips')]")
	private WebElement footerMyCreditSlipsLink;

	public void clickFooterMyCreditSlipsLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerMyCreditSlipsLink");
		clickLink(footerMyCreditSlipsLink);
	}

	// Web element: footerMyAddressesLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'My addresses')]")
	private WebElement footerMyAddressesLink;

	public void clickFooterMyAddressesLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerMyAddressesLink");
		clickLink(footerMyAddressesLink);
	}

	// Web element: footerMyPersonalInfoLink

	@FindBy(xpath = ".//*[@id='footer']//a[contains(text(),'My personal info')]")
	private WebElement footerMyPersonalInfoLink;

	public void clickFooterMyPersonalInfoLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on footerMyPersonalInfoLink");
		clickLink(footerMyPersonalInfoLink);
	}

	// Web element: invalidEmailAlert

	@FindBy(css = ".alert.alert-danger")
	private WebElement invalidEmailAlert;

	public String getInvalidEmailAlert() {
		String emailAlert = getAlertText(invalidEmailAlert);
		return emailAlert;
	}

	// Wait for link to be available for clicking

	protected void clickLink(WebElement link) {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(link)).click();
		} catch (NoSuchElementException e) {
			ExtentListener.test.log(LogStatus.ERROR, link + " Element not present. ", e);
		} catch (TimeoutException e) {
			ExtentListener.test.log(LogStatus.ERROR, link + " Timeout. Element not found: ", e);
		}
	}

	// Getting text from attribute

	protected String getAlertText(WebElement element) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

}
