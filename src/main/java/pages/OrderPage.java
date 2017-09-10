package pages;

import java.util.ArrayList;
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

public class OrderPage extends BasePage {

	public OrderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	/*
	 * ------ COMMON -------
	 * 
	 * Continue Shopping
	 *
	 * Web Element: continueShoppingLink. Action: clickContinueShoppingLink.
	 */

	@FindBy(xpath = "//a[@title='Previous']")
	WebElement continueShoppingLink;

	public void clickContinueShoppingLink() {
		continueShoppingLink.click();
	}

	// ----- SECTION: SHOPPING CART SUMMARY -----

	// List of all the products in the checkout area
	@FindBy(xpath = "//tbody/tr/td")
	List<WebElement> productsInCheckout;

	// ----- SUB-SECTION: PRODUCT -----

	/*
	 * List Web Element: cartProduct. Action: clickCartProduct.
	 * 
	 * 1. checking if productsInCheckout list is empty or not. 2. getting the
	 * elements with class = "cart_product" and putting them in cartProduct
	 * list. 3. finding the link of that product for which 'index' is provided
	 * and putting it in Web Element productImageLink. 4. clicking on the Web
	 * Element productImageLink
	 */

	List<WebElement> cartProduct;

	public void clickCartProduct(int index) {
		if (productsInCheckout.size() > 0) {
			cartProduct = getCartSummaryColumn("cart_product");
			ExtentListener.test.log(LogStatus.INFO, "Cliking on product: " + index);
			WebElement productImageLink = cartProduct.get(index).findElement(By.tagName("a"));
			productImageLink.click();
		} else {
			ExtentListener.test.log(LogStatus.INFO, "Cannot click the product. Cart is empty.");
		}
	}

	// ----- SUB-SECTION: DESCRIPTION -----

	/*
	 * List Web Element: cartDescription. Action: clickProductDescription
	 * 
	 * 1. checking if productsInCheckout list is empty or not. 2. getting the
	 * elements with class = "cart_description" and putting them in
	 * cartDescription list 3. finding the link of that product for which
	 * 'index' is provided and putting it in Web Element productNameLink. 4.
	 * clicking on the Web Element productNameLink
	 */

	List<WebElement> cartDescription;

	public void clickProductDescription(int index) {
		if (productsInCheckout.size() > 0) {
			cartDescription = getCartSummaryColumn("cart_description");
			ExtentListener.test.log(LogStatus.INFO, "Cliking on product: " + index);
			WebElement productNameLink = cartDescription.get(index).findElement(By.tagName("p"))
					.findElement(By.tagName("a"));
			productNameLink.click();
		} else {
			ExtentListener.test.log(LogStatus.INFO, "Cannot click the product. Cart is empty.");
		}
	}

	// ----- SUB-SECTION: QUANTITY -----

	/*
	 * List Web Element: cartQuantity. Action: enterProductQuantity
	 * 
	 * 1. checking if productsInCheckout list is empty or not. 2. getting the
	 * elements with class = "cart_quantity text-center" and putting them in
	 * cartQuantity list. 3. finding the product using
	 * 'productSequenceInCart-1'(list has zero based indexing), then finding the
	 * 'input' web elements for that product and putting these web elements in
	 * List quantityInputs 'Qty' input field. 4. putting the 'input' web element
	 * visible into quantityInput. 5. sending the 'quantity' to the input field.
	 * 
	 */

	List<WebElement> cartQuantity;

	public OrderPage enterProductQuantity(int productSequenceInCart, String quantity) {
		if (productsInCheckout.size() > 0) {
			cartQuantity = getCartSummaryColumn("cart_quantity text-center");
			ExtentListener.test.log(LogStatus.INFO,
					"Entering quantity: " + quantity + " in product number: " + productSequenceInCart);
			List<WebElement> quantityInputs = cartQuantity.get(productSequenceInCart - 1)
					.findElements(By.tagName("input"));
			WebElement quantityInput = quantityInputs.get(1);
			quantityInput.sendKeys(quantity);
		} else {
			ExtentListener.test.log(LogStatus.INFO, "Cannot enter quantity in the product. Cart is empty.");
		}
		return this;
	}

	/*
	 * Action: clickQuantityDecrease
	 * 
	 * 1. checking if productsInCheckout list is empty or not. 2. sending
	 * productSequenceInCart and quantity decrease(0) and getting the quantity
	 * decrease button and clicking it.
	 */

	public OrderPage clickQuantityDecrease(int productSequenceInCart) {
		if (productsInCheckout.size() > 0) {
			ExtentListener.test.log(LogStatus.INFO,
					"Increasing the quantity by 1 of product number " + productSequenceInCart);
			WebElement productQuantityDecrease = getQuantityButton(productSequenceInCart, 0);
			productQuantityDecrease.click();

		} else {
			ExtentListener.test.log(LogStatus.INFO, "Cannot decrease the quantity for the product. Cart is empty.");
		}
		return this;
	}

	/*
	 * Action: clickQuantityIncrease
	 * 
	 * 1. checking if productsInCheckout list is empty or not. 2. getting the
	 * elements with class ="cart_quantity text-center" and putting them in
	 * cartQuantity list. 3. sending cartQuantity, productSequenceInCart and
	 * quantity increase(1) and getting the quantity decrease button and
	 * clicking it.
	 */

	public OrderPage clickQuantityIncrease(int productSequenceInCart) {
		if (productsInCheckout.size() > 0) {
			ExtentListener.test.log(LogStatus.INFO,
					"Increasing the quantity by 1 of product number " + productSequenceInCart);
			WebElement productQuantityIncrease = getQuantityButton(productSequenceInCart, 1);
			productQuantityIncrease.click();

		} else {
			ExtentListener.test.log(LogStatus.INFO, "Cannot increase the quantity for the product. Cart is empty.");
		}
		return this;
	}

	// ----- SUB-SECTION: DELETE -----

	/*
	 * List Web Element: productDeleteButtons. Action: clickProductDeleteButton
	 * 
	 * @param int productSequenceInCart: Sequence of products in cart(E.g.
	 * 1,2..)
	 * 
	 * 1. getting the web elements(td) containing delete buttons. 2. putting the
	 * delete button link(a) for the product required into web element
	 * productDeleteButton. 3. clicking on Delete button.
	 */

	List<WebElement> productDeleteButtons;

	public OrderPage clickProductDeleteButton(int productSequenceInCart) {
		productDeleteButtons = getCartSummaryColumn("cart_delete text-center");
		ExtentListener.test.log(LogStatus.INFO, "Cliking on delete button for product: " + productSequenceInCart);
		WebElement productDeleteButton = productDeleteButtons.get(productSequenceInCart - 1)
				.findElement(By.tagName("a"));
		productDeleteButton.click();
		return this;
	}

	/*
	 * Proceed to checkout Cart Summary
	 * 
	 * WebElement: proceedToCheckoutCartSummaryLink. Action:
	 * clickProceedToCheckoutCartSummaryLink.
	 */

	@FindBy(xpath = "//div[@id='center_column']//a[@title='Proceed to checkout']")
	WebElement proceedToCheckoutCartSummaryLink;

	public OrderPage clickProceedToCheckoutCartSummaryLink() {
		proceedToCheckoutCartSummaryLink.click();
		return this;
	}

	// ----- SECTION: ADDRESS -----

	/*
	 * Choose a delivery address WebElement: deliveryAddress. Action:
	 * selectDeliveryAddress.
	 * 
	 * @param String addressOption: the address option to be selected
	 * 
	 * 1. put all the address options in deliveryAddressDropdown. 2. selecting
	 * the option from dropdown using the visible text.
	 */

	@FindBy(id = "id_address_delivery")
	WebElement deliveryAddress;

	public OrderPage selectDeliveryAddress(String addressOption) {
		Select deliveryAddressDropdown = new Select(deliveryAddress);
		ExtentListener.test.log(LogStatus.INFO, "Selecting option: " + addressOption);
		deliveryAddressDropdown.selectByVisibleText(addressOption);
		return this;
	}

	public String getDeliveryAddressOptionText() {
		Select deliveryAddressDropdown = new Select(deliveryAddress);
		String selectedOptionText = deliveryAddressDropdown.getFirstSelectedOption().getText();
		return selectedOptionText;
	}

	/*
	 * Checkbox Delivery Address as Billing Address WebElement:
	 * 
	 */

	@FindBy(id = "addressesAreEquals")
	WebElement billingAddressCheckbox;

	public OrderPage clickBillingAddressCheckbox(Boolean check) {
		if (check == billingAddressCheckbox.isSelected()) {
			if (billingAddressCheckbox.isSelected() == true) {
				ExtentListener.test.log(LogStatus.INFO,
						"'Use the delivery address as the billing address' checkbox is already checked");
			} else {
				ExtentListener.test.log(LogStatus.INFO,
						"'Use the delivery address as the billing address' checkbox is already unchecked");
			}
		}

		else {
			if (check == true) {
				ExtentListener.test.log(LogStatus.INFO,
						"Clicking on 'Use the delivery address as the billing address' checkbox to check it");
				billingAddressCheckbox.click();
			} else {
				ExtentListener.test.log(LogStatus.INFO,
						"Clicking on 'Use the delivery address as the billing address' checkbox to uncheck it");
				billingAddressCheckbox.click();
			}

		}

		return this;
	}

	/*
	 * Choose a billing address WebElement: billingAddress. Action:
	 * selectBillingAddress.
	 * 
	 * @param String addressOption: the address option to be selected
	 * 
	 * 1. put all the address options in billingAddressDropdown. 2. selecting
	 * the option from dropdown using the visible text.
	 */

	@FindBy(id = "id_address_invoice")
	WebElement billingAddress;

	public OrderPage selectBillingAddress(String addressOption) {
		Select billingAddressDropdown = new Select(billingAddress);
		ExtentListener.test.log(LogStatus.INFO, "Selecting option: " + addressOption);
		billingAddressDropdown.selectByVisibleText(addressOption);
		return this;
	}

	public String getBillingAddressOptionText() {
		Select billingAddressDropdown = new Select(billingAddress);
		String selectedOptionText = billingAddressDropdown.getFirstSelectedOption().getText();
		return selectedOptionText;
	}

	/*
	 * Delivery Address update button.
	 * 
	 * Web Element: updateDeliveryAddressButton. Action:
	 * clickUpdateDeliveryAddressButton.
	 */

	@FindBy(xpath = "//ul[@id='address_delivery']//a")
	WebElement updateDeliveryAddressButton;

	public void clickUpdateDeliveryAddressButton() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Update button in Delivery Address section.");
		updateDeliveryAddressButton.click();
	}

	/*
	 * Billing Address update button.
	 * 
	 * Web Element: updateBillingAddressButton. Action:
	 * clickUpdateBillingAddressButton.
	 */

	@FindBy(xpath = "//ul[@id='address_invoice']//a")
	WebElement updateBillingAddressButton;

	public void clickUpdateBillingAddressButton() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Update button in Billing Address section.");
		updateBillingAddressButton.click();
	}

	/*
	 * Add a new address
	 * 
	 * Web Element: addNewAddress. Action: clickAddNewAddress.
	 */

	@FindBy(xpath = "//a[@title='Add']")
	WebElement addNewAddress;

	public void clickAddNewAddress() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Update button in Billing Address section.");
		addNewAddress.click();
	}

	/*
	 * Add order comment
	 * 
	 * Web Element: orderComment. Action: enterOrderComment.
	 * 
	 * @param String message: text to be entered in order comment.
	 */

	@FindBy(xpath = "//textarea[@name='message']")
	WebElement orderComment;

	public OrderPage enterOrderComment(String message) {
		ExtentListener.test.log(LogStatus.INFO, "Entering order comment.");
		orderComment.sendKeys(message);
		return this;
	}

	/*
	 * Proceed to checkout address
	 * 
	 * Web Element: proceedToCheckoutAddressSection. Action:
	 * clickProceedToCheckoutAddressSectionButton
	 */

	@FindBy(name = "processAddress")
	WebElement proceedToCheckoutAddressSection;

	public OrderPage clickProceedToCheckoutAddressSectionButton() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Proceed To Checkout button in Address section.");
		clickProceedToCheckoutButton(proceedToCheckoutAddressSection);
		return this;
	}

	// ------ SUB-SECTION: SHIPPING -------

	/*
	 * Terms of service checkbox
	 * 
	 * Web Element: termsOfServiceCheckbox. Action: clickTermsOfServiceCheckbox.
	 */

	@FindBy(id = "cgv")
	WebElement termsOfServiceCheckbox;

	public OrderPage clickTermsOfServiceCheckbox() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Terms of Service checkbox.");
		termsOfServiceCheckbox.click();
		return this;
	}

	/*
	 * Read the Terms of Service Link
	 * 
	 * Web element: readTheTermsOfService. Action:
	 * clickReadTheTermsOfServiceButton.
	 * 
	 * 1. Clicking on the Read the Terms of Service link.
	 */

	@FindBy(xpath = "//a[contains(text(),'Read')]")
	WebElement readTheTermsOfService;

	public OrderPage clickReadTheTermsOfServiceButton() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Read the Terms of Service link.");
		readTheTermsOfService.click();
		return this;
	}

	/*
	 * Close(X) Terms of Service messages
	 * 
	 * Web element: closeMessage. Action: clickCloseMessage.
	 * 
	 * 1. Clicking on the Close(x).
	 */

	@FindBy(xpath = "//a[@title='Close']")
	WebElement closeMessage;

	public OrderPage clickCloseMessage() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Close(X).");
		closeMessage.click();
		return this;
	}

	/*
	 * Proceed to checkout shipping
	 * 
	 * Web Element: proceedToCheckoutShippingSection. Action:
	 * clickProceedToCheckoutShippingSectionButton
	 */

	@FindBy(name = "processCarrier")
	WebElement proceedToCheckoutShippingSection;

	public OrderPage clickProceedToCheckoutShippingSectionButton() {
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Proceed To Checkout button in Shipping section.");
		clickProceedToCheckoutButton(proceedToCheckoutShippingSection);
		return this;
	}
	
	// ----- SUB-SECTION: PAYMENT -----
	
	/*
	 * Pay by wire
	 * 
	 * Web element: payByWire. Action: clickPayByWireLink.
	 * 
	 * 1. Clicking on the Pay by bank wire link.
	 * */
	
	@FindBy(css=".bankwire")
	WebElement payByWire;
	
	public OrderPage clickPayByWireLink(){
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Pay by Wire in payment section.");
		payByWire.click();
		return this;
	}


	/*
	 * Pay by check
	 * 
	 * Web element: payByCheck. Action: clickPayByCheckLink.
	 * 
	 * 1. Clicking on the Pay by bank check link.
	 * */
	
	@FindBy(css=".cheque")
	WebElement payByCheck;
	
	public OrderPage clickPayByCheckLink(){
		ExtentListener.test.log(LogStatus.INFO, "Clicking on Pay by Check in payment section.");
		payByCheck.click();
		return this;
	}

	/* 
	 * I confirm my order button.
	 * 
	 * Web element: confirmButton. Action: clickConfirmButton.
	 * 
	 * 1. Clicking the Confirm button
	 */
	
	@FindBy(css="button.button-medium")
	WebElement confirmButton;
	
	public OrderPage clickConfirmButton(){
		ExtentListener.test.log(LogStatus.INFO, "Clicking 'I confirm my order' button.");
		confirmButton.click();
		return this;
	}
	
	/*
	 * Other Payment methods link.
	 * 
	 * Web element: otherPaymentLink. Action: clickOtherPaymnentMethodsLink.
	 * 
	 * 1. Clicking the Other Payment Methods link
	 * */
	
	@FindBy(css="a.button-exclusive")
	WebElement otherPaymentLink;
	
	public OrderPage clickOtherPaymnentMethodsLink(){
		ExtentListener.test.log(LogStatus.INFO, "Clicking Other payment methods link.");
		otherPaymentLink.click();
		return this;
	}
	
	/*
	 * Back to orders link
	 * 
	 * Web element: backToOrdersLink. Action: clickBackToOrdersLink.
	 * 
	 * 1. Clicking the Other Payment Methods link
	 * */
	
	@FindBy(xpath="//a[@title='Back to orders']")
	WebElement backToOrdersLink;
	
	public OrderPage clickBackToOrdersLink(){
		ExtentListener.test.log(LogStatus.INFO, "Clicking Back to orders link.");
		backToOrdersLink.click();
		return this;
	}
	
	
	/*
	 * Order confirmation message
	 * 
	 * Function to get order confirmation message 
	 * */
	
	@FindBy(xpath="//div[@id='center_column']/div/p/strong")
	WebElement orderConfirmationMessage;
	
	public String getOrderConfirmationMessage(){
		
		String message= orderConfirmationMessage.getText().substring(5);
		ExtentListener.test.log(LogStatus.INFO, "Getting order confirmation message");
		
		return message;
	}
	// ------ FUNCTIONS -----

	/*
	 * Function to get specific column values from the Cart Summary
	 */

	
	private List<WebElement> getCartSummaryColumn(String columnName) {
		List<WebElement> productInformationColumn = new ArrayList<WebElement>();
		try {
			for (WebElement productInformation : productsInCheckout) {
				if (productInformation.getAttribute("class").equals(columnName)) {
					productInformationColumn.add(productInformation);
				}
			}
		} catch (NoSuchElementException e) {
			ExtentListener.test.log(LogStatus.ERROR, e);
		}
		return productInformationColumn;
	}

	/*
	 * Function to get either the Quantity Increase or Quantity Decrease button
	 * link.
	 * 
	 * @param int index: the product whose QTY web elements are required
	 * 
	 * @param int quantityButton: the quantity button web element requested to
	 * be returned. Quantity Decrease is 0. Quantity Increase is 1.
	 * 
	 * 1. getting the elements with class ="cart_quantity text-center" and
	 * putting them in cartQuantity list. 2. getting all the quantity button
	 * links(a) for the product into list productQuantityButtons. 3. return the
	 * product quantity button using quantityButton(Quantity Decrease is 0.
	 * Quantity Increase is 1.).
	 */

	private WebElement getQuantityButton(int productNumber, int quantityButton) {
		cartQuantity = getCartSummaryColumn("cart_quantity text-center");
		List<WebElement> productQuantityButtons = cartQuantity.get(productNumber).findElements(By.tagName("a"));
		return productQuantityButtons.get(quantityButton);
	}

	/*
	 * Function to get billing address label
	 */

	@FindBy(xpath = "//div[@id='address_invoice_form']/label")
	WebElement billingAddressDropdownLabel;

	public String getBillingAddressDropdownLabel() {
		String label = billingAddressDropdownLabel.getText();
		return label;
	}

	/*
	 * Function to click on Proceed to checkout button
	 * 
	 * @param WebElement button: Proceed to checkout web element to be clicked
	 */

	private void clickProceedToCheckoutButton(WebElement button) {
		button.click();
	}
}
