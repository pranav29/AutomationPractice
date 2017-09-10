package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BasePage;
import listeners.ExtentListener;

public class LoginPage extends BasePage {
	
	private ExtentTest test;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	// ----- section: create account -----

	// Web element: emailCreate

	@FindBy(id = "email_create")
	WebElement emailCreate;

	public LoginPage enterCreateAccountEmail(String email) {
		emailCreate.clear();
		ExtentListener.test.log(LogStatus.INFO, "entering email: "+email+" in create account");
		emailCreate.sendKeys(email);
		return this;
	}

	// Web element: submitCreate

	@FindBy(id = "SubmitCreate")
	WebElement submitCreate;

	public LoginPage clickCreateAccountButton() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on Create an account button");
		submitCreate.click();
		return this;
	}

	// Web element: create account error

	@FindBy(xpath = "//div[@id='center_column']//li")
	WebElement createAccountError;

	public String getCreateAccountError() {
		String accountErrorText = getAlertText(createAccountError);
		return accountErrorText;
	}

	// ---- section: login -----

	// performing login operation

	// Web element: login email, password, submit button

	@FindBy(id = "email")
	WebElement loginEmail;

	@FindBy(id = "passwd")
	WebElement loginPassword;

	@FindBy(id = "SubmitLogin")
	WebElement submitLoginButton;

	public LoginPage login(String email, String password) {
		loginEmail.clear();
		loginPassword.clear();
		ExtentListener.test.log(LogStatus.INFO, "entering email: "+email+" in Login section");
		loginEmail.sendKeys(email);
		ExtentListener.test.log(LogStatus.INFO, "entering password: "+password+" in Login section");
		loginPassword.sendKeys(password);
		ExtentListener.test.log(LogStatus.INFO, "Clicking on SignIn button");
		submitLoginButton.click();
		return this;
	}

	// Web element: login error

	@FindBy(xpath = "//div[@id='center_column']//li")
	WebElement loginError;

	public String getLoginError() {
		String loginErrorText = getAlertText(loginError);
		return loginErrorText;
	}

	// Web element: forgot password link

	@FindBy(xpath = "//a[contains(text(),'Forgot')]")
	WebElement forgotPasswordLink;

	public void clickForgotPasswordLink() {
		ExtentListener.test.log(LogStatus.INFO, "clicking on Forgot Your Password link");
		forgotPasswordLink.click();
	}

}
