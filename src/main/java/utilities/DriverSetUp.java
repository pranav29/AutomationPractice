package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverSetUp {

	private static WebDriver driver;
	
	public static void setDriver(){
	
		driver= new ChromeDriver();
	}
	
	public static WebDriver getDriver(){
		return driver;
	}
}
