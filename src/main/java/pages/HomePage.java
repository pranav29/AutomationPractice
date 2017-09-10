package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BasePage;
import listeners.ExtentListener;

public class HomePage extends BasePage {
	

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// ----- section: products -----

	// web element: productsLink

	@FindBy(css = "ul#homefeatured a")
	private List<WebElement> productsLinks;

	// web element: productImageLink

	private WebElement productImageLink;

	public void clickProductImageLink(String productName) {

		String linkClass = "product_img_link";

		productImageLink = getProductLink(linkClass, productName);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productImageLink);

		action.moveToElement(productImageLink, 10, 5).perform();
		action.click().build().perform();
	}

	// web element: productNameLink

	private WebElement productNameLink;

	public void clickProductNameLink(String productName) {

		String linkClass = "product-name";

		productNameLink = getProductLink(linkClass, productName);
		
		ExtentListener.test.log(LogStatus.INFO, "Clicking product name: "+productName);

		clickLink(productNameLink);
	}

	// web element: quick view

	public void clickProductQuickView(String productName) {

		String attributeClass = "class";

		String quickViewClass = "quick-view";

		String attributeHref = "href";

		String href = getHref(productName);
		
		ExtentListener.test.log(LogStatus.INFO, "Clicking Quick View of product: "+productName);

		findElementAndClick(attributeClass, quickViewClass, attributeHref, href);

	}

	// web element: Add to cart

	public HomePage clickAddToCart(String productName) {

		String attributeTitle = "title";

		String title = "Add to cart";

		String attributeDataProductId = "data-id-product";

		String href = getHref(productName);

		String productId = href.substring(51, 52);
		
		ExtentListener.test.log(LogStatus.INFO, "Clicking Add To Cart button for product: "+productName);

		findElementAndClick(attributeTitle, title, attributeDataProductId, productId);
		
		return this;

	}

	// web element: More

	public void clickMoreButton(String productName) {

		String attributeTitle="title";
		
	    String moreButtonTitle = "View";
	    
	    String attributeHref="href";

		String href = getHref(productName);
		
		ExtentListener.test.log(LogStatus.INFO, "Clicking More button for product: "+productName);

		findElementAndClick(attributeTitle, moreButtonTitle, attributeHref, href);
	}
	
	/*
	 * Product: Proceed to checkout button
	 * 
	 * Function to click on Procedd to checkout button to navigate to Order page.
	 * */
	
	@FindBy(xpath="//a[@title='Proceed to checkout']")
	WebElement proceedToCheckout;
	
	public void clickProceedtoCheckout(){
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Proceed to Checkout button.");
		proceedToCheckout.click();
	}

	// function to click on specific link in the product

	public WebElement getProductLink(String linkIdentifier, String productIdentifier) {

		for (WebElement link : productsLinks) {
			if (link.getAttribute("class").equals(linkIdentifier)
					&& link.getAttribute("title").equals(productIdentifier)) {

				return link;

			}

		}

		return null;

	}

	// function to get product url and move to the product

	public String getHref(String productName) {
		String href;

		String productImageLinkClass = "product_img_link";

		productImageLink = getProductLink(productImageLinkClass, productName);

		href = productImageLink.getAttribute("href");

		ExtentListener.test.log(LogStatus.INFO, "Bringing element into view");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productImageLink);

		action.moveToElement(productImageLink).perform();

		return href;

	}

	// function to move to product and click

	public void findElementAndClick(String attributeToBeMatched1, String attributeValue1, String attributeToBeMatched2,
			String attributeValue2) {

		for (WebElement element : productsLinks) {
			if (element.getAttribute(attributeToBeMatched1).equals(attributeValue1)
					&& element.getAttribute(attributeToBeMatched2).equals(attributeValue2)) {

				ExtentListener.test.log(LogStatus.INFO, "Moving to element and clicking it");
				action.moveToElement(element).perform();
				action.click().build().perform();

			}
		}

	}
}
