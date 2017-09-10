package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static String takeScreenshot(WebDriver webDriver, String fileName) throws IOException{
	
		fileName=fileName+DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now())+".png";
		String directory="C://Users//Pranav Paliwal//Videos//Learning//Automation Testing//2 Selenium scripts//Java//";
		String destination =directory+fileName;
		File source=((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
}
