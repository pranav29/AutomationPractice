package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.OrderPage;
import pages.ProductPage;
import pages.WomenPage;
import utilities.DataProviderClass;
import utilities.DriverSetUp;

public class BaseTest {
  
  protected WebDriver driver;
  protected BasePage basePage;
  protected HomePage homePage;
  protected LoginPage loginPage;
  protected MyAccountPage myAccountPage;
  protected ProductPage productPage;
  protected OrderPage orderPage;
  protected WomenPage womenPage;

  
  @BeforeMethod
  public void setUp() {
	  //setting up driver
	  DriverSetUp.setDriver();
	  
	  //getting driver instance
	  driver=DriverSetUp.getDriver();
	  
	  //initializing page classes
	  basePage=new BasePage(driver);
	  homePage=new HomePage(driver);
	  loginPage = new LoginPage(driver);
	  myAccountPage=new MyAccountPage(driver);
	  productPage=new ProductPage(driver);
	  orderPage=new OrderPage(driver);
	  womenPage=new WomenPage(driver);
	  
	  // maximizing the browser window
	  driver.manage().window().maximize();
	  
	  driver.get("http://automationpractice.com");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
