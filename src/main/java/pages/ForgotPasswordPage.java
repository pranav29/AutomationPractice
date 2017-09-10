package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BasePage;
import listeners.ExtentListener;

public class ForgotPasswordPage extends BasePage {
	
	

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		
	}
	
	// Web element: Back to Login
	
	@FindBy(xpath="//a[@title='Back to Login']")
	WebElement backToLoginButton;
	
	public void clickBackToLoginButton(){
		ExtentListener.test.log(LogStatus.INFO, "clicking on Back To login button");
		backToLoginButton.click();
	}

	// ----- section: retrieve password -----
	
	// Web element: email input, retrieve password button

	@FindBy(id = "email")
	WebElement emailForgotPassword;

	@FindBy(xpath = "//form[@id='form_forgotpassword']//button")
	WebElement retrievePasswordButton;

	public void retrievePassword(String email) {
		ExtentListener.test.log(LogStatus.INFO, "entering email: " + email);
		emailForgotPassword.sendKeys(email);
		ExtentListener.test.log(LogStatus.INFO, "clicking Retrieve Password button");
		retrievePasswordButton.click();

	}
	
	// Web element: email alert
	
	@FindBy(xpath="//div[@id='center_column']//ol/li")
	WebElement emailAlert;
	
	public String getEmailAlert(){
		String alertText = getAlertText(emailAlert);
		return alertText;
	}

}
