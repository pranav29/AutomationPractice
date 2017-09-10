package utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.relevantcodes.extentreports.LogStatus;

import listeners.ExtentListener;

public class ExcelRead {
	
	private static XSSFWorkbook wb=null;
	private static XSSFSheet sheet=null;
	
	public static XSSFSheet getTestDataSheet(String path, String sheetName){
		
		File src=new File(path);
		
		try{
		FileInputStream excelSheet=new FileInputStream(src);
		wb=new XSSFWorkbook(excelSheet);
		sheet=wb.getSheet(sheetName);
		}
		catch(Exception e){
			ExtentListener.test.log(LogStatus.ERROR, "Exception: "+e);
		}
		
		return sheet;
	}

}
