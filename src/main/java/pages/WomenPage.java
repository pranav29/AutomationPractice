package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import base.BasePage;
import listeners.ExtentListener;

public class WomenPage extends BasePage {

	public WomenPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// ----- SECTION: WOMEN BLOCK -----

	/*
	 * Tops drilldown
	 * 
	 * 
	 */

	@FindBy(xpath = "//*[@id='categories_block_left']/div/ul/li")
	List<WebElement> subCategoryDrilldown;

	public WomenPage clickSubCategoryDrilldown(String type) {

		if (type.equalsIgnoreCase("Tops")) {
			WebElement drilldownElement = subCategoryDrilldown.get(0).findElement(By.tagName("span"));
			ExtentListener.test.log(LogStatus.INFO, "Clicking drilldown Tops(+)");
			drilldownElement.click();
		}
		if (type.equalsIgnoreCase("Dresses")) {
			WebElement drilldownElement = subCategoryDrilldown.get(1).findElement(By.tagName("span"));
			ExtentListener.test.log(LogStatus.INFO, "Clicking drilldown Dresses(+)");
			drilldownElement.click();
		}
		return this;
	}

	/*
	 * Links in sub categories
	 * 
	 */

	@FindBy(xpath = "//*[@id='categories_block_left']/div/ul/li/ul//a")
	List<WebElement> linksInSubCategories;

	public void clickLinkInSubCategories(String linkText) {

		for (WebElement link : linksInSubCategories) {
			if (link.getText().equalsIgnoreCase(linkText)) {
				ExtentListener.test.log(LogStatus.INFO, "Clicking link: " + linkText);
				link.click();
			}
		}

	}

	/*
	 * Categories
	 */

	@FindBy(xpath = "//ul[@id='ul_layered_category_0']//input")
	List<WebElement> categories;

	public WomenPage selectCategory(String category) {

		if (category.equalsIgnoreCase("Tops")) {
			WebElement categoryCheckbox = categories.get(0);
			ExtentListener.test.log(LogStatus.INFO, "Clicking on category: Tops checkbox.");
			categoryCheckbox.click();
		}
		if (category.equalsIgnoreCase("Dresses")) {
			WebElement categoryCheckbox = categories.get(1);
			ExtentListener.test.log(LogStatus.INFO, "Clicking on category: Dresses checkbox.");
			categoryCheckbox.click();
		}

		return this;

	}

	/*
	 * Size
	 */

	@FindBy(xpath = "//ul[@id='ul_layered_id_attribute_group_1']//a")
	List<WebElement> sizes;

	public WomenPage selectSize(String size) {
		clickCheckboxLink(size, sizes);
		return this;
	}

	/*
	 * Color
	 */

	@FindBy(xpath = "//ul[@id='ul_layered_id_attribute_group_3']//a")
	List<WebElement> colors;

	public WomenPage selectColor(String color) {
		clickCheckboxLink(color, colors);
		return this;
	}

	/*
	 * Compositions
	 */

	@FindBy(xpath = "//ul[@id='ul_layered_id_feature_5']//a")
	List<WebElement> compositions;

	public WomenPage selectComposition(String composition) {
		clickCheckboxLink(composition, compositions);
		return this;
	}

	/*
	 * Styles
	 */

	@FindBy(xpath = "//ul[@id='ul_layered_id_feature_6']//a")
	List<WebElement> styles;

	public WomenPage selectStyle(String style) {
		clickCheckboxLink(style, styles);
		return this;
	}

	/*
	 * Properties
	 */

	@FindBy(xpath = "//ul[@id='ul_layered_id_feature_7']//a")
	List<WebElement> properties;

	public WomenPage selectProperty(String property) {
		clickCheckboxLink(property, properties);
		return this;
	}

	/*
	 * Availability. In Stock checkbox.
	 * 
	 * Web element: inStockCheckbox.
	 * 
	 * Action: checkInStockCheckbox.
	 * 
	 * 1. if checkbox is not checked then click on it to check it. 2. else do
	 * not do anything. add in the log.
	 * 
	 * Action: uncheckInStockCheckbox
	 * 
	 * 1. if checkbox is checked then click on it to uncheck it. 2. else do not
	 * do anything. add in the log.
	 */

	@FindBy(id = "layered_quantity_1")
	WebElement inStockCheckbox;

	public WomenPage checkInStockCheckbox() {
		if (inStockCheckbox.isSelected() != true) {
			ExtentListener.test.log(LogStatus.INFO, "Selecting the In Stock checkbox");
			inStockCheckbox.click();
		} else {
			ExtentListener.test.log(LogStatus.INFO, "In Stock checkbox is already checked");
		}
		return this;
	}

	public WomenPage uncheckInStockCheckbox() {
		if (inStockCheckbox.isSelected() == true) {
			ExtentListener.test.log(LogStatus.INFO, "Unchecking the In Stock checkbox");
			inStockCheckbox.click();
		} else {
			ExtentListener.test.log(LogStatus.INFO, "In Stock checkbox is already unchecked");
		}
		return this;
	}

	/*
	 * Price range slider.
	 * 
	 * 
	 */

	@FindBy(xpath = "//ul[@id='ul_layered_price_0']//a")
	List<WebElement> sliders;

	public WomenPage moveLeftSlider() {
		WebElement leftSlider = sliders.get(0);
		action.moveToElement(leftSlider).perform();
		action.dragAndDropBy(leftSlider, 73, 0).build().perform();
		return this;
	}

	public WomenPage moveRightSlider() {
		WebElement rightSlider = sliders.get(1);
		action.moveToElement(rightSlider).perform();
		action.dragAndDropBy(rightSlider, -151, 0).build().perform();
		return this;
	}

	/*
	 * Product Add to Wishlist
	 * 
	 */

	@FindBy(xpath = "//div[@id='center_column']//a[@class='product-name']")
	List<WebElement> productNameLinks;

	@FindBy(css = ".addToWishlist")
	List<WebElement> addtoWishlistLinks;

	public WomenPage clickAddToWishlist(String productName) {

		ExtentListener.test.log(LogStatus.INFO, "Clicking on Add to Wishlist for product: "+productName);
		clickAddLinks(productName,addtoWishlistLinks);
		return this;
	}

	/*
	 * Product Add to Cart
	 * 
	 */

	@FindBy(css = ".add_to_compare")
	List<WebElement> addtoCompareLinks;

	public WomenPage clickAddToCompare(String productName) {

		ExtentListener.test.log(LogStatus.INFO, "Clicking on Add to Compare for product: "+productName);
		clickAddLinks(productName,addtoCompareLinks);
		return this;
	}
	
	/*
	 * Remove from Add to Compare
	 * 
	 * */
	
	public WomenPage removeFromAddToCompare(String productName) {

		ExtentListener.test.log(LogStatus.INFO, "Removing product: "+productName+" by clicking Add to Compare.");
		clickAddLinks(productName,addtoCompareLinks);
		return this;
	}

	/*
	 * Close(X) messages
	 * 
	 * Web element: closeMessage. Action: clickCloseMessage.
	 * 
	 * 1. Clicking on the Close(x).
	 */

	@FindBy(xpath = "//a[@title='Close']")
	WebElement closeMessage;

	public WomenPage clickCloseMessage() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Close(X).");
		closeMessage.click();
		return this;
	}

	// ------ FUNCTIONS -----

	/*
	 * Function to click the checkbox links
	 */
	
	private void clickCheckboxLink(String checkboxName, List<WebElement> checkboxes) {
		int count = 0;
		for (WebElement link : checkboxes) {
			if (link.getText().equalsIgnoreCase(checkboxName)) {
				ExtentListener.test.log(LogStatus.INFO, "Clicking on checkbox: " + checkboxName);
				link.click();
				count = 1;
			}
		}

		if (count == 0) {
			ExtentListener.test.log(LogStatus.INFO,
					"checkbox: " + checkboxName + " is not available. Use correct value.");
		}

	}

	/*
	 * Function to click Add to Wishlist or Add to Cart link for the product
	 */
	
	private void clickAddLinks(String productName,List<WebElement> links){

		for (int i = 0; i < productNameLinks.size(); i++) {
			if (productNameLinks.get(i).getText().equalsIgnoreCase(productName)) {
				System.out.println(productNameLinks.get(i).getText());
				WebElement addToCartLink = links.get(i);
				action.moveToElement(productNameLinks.get(i)).perform();
				action.moveToElement(addToCartLink).click().build().perform();
			}
		}

		
	}
}
