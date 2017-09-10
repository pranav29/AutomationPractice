package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BasePage;
import listeners.ExtentListener;

public class ProductPage extends BasePage {
	

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	// ----- SECTION: ITEM INFORMATION -----
	
	// Web element: Item name
	
	@FindBy(xpath="//h1[@itemprop='name']")
	WebElement itemName;
	
	public String getProductName(){
		String name=itemName.getText();
		return name;
	}

	// ----- SECTION: BUY BLOCK -----

	// Web Element: Quantity Input

	@FindBy(id = "quantity_wanted")
	WebElement quantityInput;

	public ProductPage enterQuantityInput(String quantity) {
		ExtentListener.test.log(LogStatus.INFO, "Entering quantity: " + quantity);
		quantityInput.sendKeys(quantity);
		return this;
	}

	// Web Element: Decrease Quantity

	@FindBy(css = ".product_quantity_down")
	WebElement quantityDecrease;

	public ProductPage clickQuantityDecrease() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking - button");
		quantityDecrease.click();
		return this;
	}

	// Web Element: Increase Quantity

	@FindBy(css = ".product_quantity_down")
	WebElement quantityIncrease;

	public ProductPage clickQuantityIncrease() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking + button");
		quantityIncrease.click();
		return this;
	}

	// Web Element: Select Size

	@FindBy(id = "group_1")
	WebElement sizeDropdown;

	public ProductPage selectSize(String size) {

		Select selectSize = new Select(sizeDropdown);
		ExtentListener.test.log(LogStatus.INFO, "Selecting size: " + size);
		selectSize.selectByVisibleText(size);
		return this;
	}

	// Web Element: Color to pick

	@FindBy(xpath = "//ul[@id='color_to_pick_list']//a")
	List<WebElement> colors;

	public ProductPage selectColor(String color) {

		for (WebElement colorToPick : colors) {
			if (colorToPick.getAttribute("title").equals(color)) {
				ExtentListener.test.log(LogStatus.INFO, "Clicking color: " + color);
				colorToPick.click();
			}
		}

		return this;
	}

	// Web Element: Add to Cart

	@FindBy(xpath = "//p[@id='add_to_cart']/button")
	WebElement addToCartButton;

	public ProductPage clickAddToCartButton() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Add To Cart button");
		addToCartButton.click();
		return this;
	}

	// Web Element: Wishlist Button

	@FindBy(id = "wishlist_button")
	WebElement addToWishlist;

	public ProductPage clickAddToWishlist() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Add to Wishlist button");
		addToWishlist.click();
		return this;
	}

	// Web Element: Continue Shopping Button

	@FindBy(xpath = "//span[@title='Continue shopping']")
	WebElement continueShoppingButton;

	public ProductPage clickContinueShoppingButton() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Continue Shopping button");
		clickLink(continueShoppingButton);
		return this;
	}

	// Web Element: Proceed to checkout Button

	@FindBy(xpath = "//a[@title='Proceed to checkout']")
	WebElement proceedToCheckout;

	public void clickProceedToCheckout() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Proceed To Checkout button");
		clickLink(proceedToCheckout);
	}

	// Web Element: Close Window

	@FindBy(xpath = "//span[@title='Close window']")
	WebElement closeWindow;

	public ProductPage clickCloseWindow() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Close(X) in the pop up window");
		clickLink(closeWindow);
		return this;
	}

	// ----- SECTION: PRODUCT IMAGES AND VIEW -----

	// ----- SUB-SECTION: BIG PIC ------

	// Web ELement: Big Pic

	@FindBy(id = "bigpic")
	WebElement productBigImage;

	public ProductPage clickProductBigImage() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Product Big Image");
		productBigImage.click();
		return this;
	}

	public String getProductBigImageSource() {
		String bigImageSource = getImageSource(productBigImage);
		return bigImageSource;
	}

	// ------ SUB-SECTION: FANCYBOX -----

	// Web Element: fancybox image

	@FindBy(css = ".fancybox-image")
	WebElement fancyboxImage;

	public String getFancyboxImageSource() {
		String fancyboxImageSource = getImageSource(fancyboxImage);
		return fancyboxImageSource;
	}

	// Web Element: fancybox image next

	@FindBy(css = ".fancybox-next")
	WebElement fancyboxNext;

	public ProductPage clickFancyboxNext() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Next(>) on Fancybox");
		fancyboxNext.click();
		return this;
	}

	// Web Element: fancybox image previous

	@FindBy(css = ".fancybox-prev")
	WebElement fancyboxPrev;

	public ProductPage clickFancyboxPrev() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Previous(<) on Fancybox");
		fancyboxPrev.click();
		return this;
	}

	// Web Element: fancybox close

	@FindBy(css = ".fancybox-close")
	WebElement fancyboxClose;

	public ProductPage clickFancyboxClose() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking Close(X) on Fancybox");
		fancyboxClose.click();
		return this;
	}

	// ----- SUB-SECTION: VIEW BLOCK WITH IMAGE THUMB LIST ------

	// Web Element: Scroll left

	@FindBy(id = "view_scroll_left")
	WebElement scrollLeft;

	public ProductPage clickScrollLeft() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking SCroll Left(<) on Image Thumb List");
		scrollLeft.click();
		return this;
	}

	// Web Element: Scroll right

	@FindBy(id = "view_scroll_right")
	WebElement scrollRight;

	public ProductPage clickScrollRight() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking SCroll Right(>) on Image Thumb List");
		scrollRight.click();
		return this;
	}

	// Web Element: Thumb List Frame

	@FindBy(xpath = "//ul[@id='thumbs_list_frame']//a")
	List<WebElement> thumbnails;
	
	public ProductPage mouseHoverThumbnail(int thumbnailNumber){
		try{
			WebElement thumbnail=thumbnails.get(thumbnailNumber);
			ExtentListener.test.log(LogStatus.INFO, "Moving mouse to the thumbnail");
			action.moveToElement(thumbnail).perform();
			}
			catch(NoSuchElementException e){
				ExtentListener.test.log(LogStatus.ERROR, "No such element exception: "+e);
			}
			catch(ArrayIndexOutOfBoundsException e){
				ExtentListener.test.log(LogStatus.ERROR, "Invalid element number entered "+e);
			}
			return this;
	}

	public ProductPage clickThumbnail(int thumbnailNumber){
		try{
		WebElement thumbnail=thumbnails.get(thumbnailNumber);
		ExtentListener.test.log(LogStatus.INFO, "Clicking on image thumbnail");
		thumbnail.click();
		}
		catch(NoSuchElementException e){
			ExtentListener.test.log(LogStatus.ERROR, "No such element exception: "+e);
		}
		catch(ArrayIndexOutOfBoundsException e){
			ExtentListener.test.log(LogStatus.ERROR, "Invalid element number entered "+e);
		}
		return this;
	}

	public String getThumbnailShown() {

		String thumbnailSource;

		for (WebElement thumbnail : thumbnails) {
			if (thumbnail.getAttribute("class").equals("fancybox shown")) {
				WebElement thumbnailImageElement=thumbnail.findElement(By.tagName("img"));
				thumbnailSource = getImageSource(thumbnailImageElement);
				ExtentListener.test.log(LogStatus.INFO, "Returning image source for the thumbnail shown : "+thumbnailSource);
				return thumbnailSource;
			}
		}

		return null;
	}

	// Function to get image source

	public String getImageSource(WebElement imageElement) {
		String imageSource;
		imageSource = imageElement.getAttribute("src");
		return imageSource;
	}
}
