package utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DataProviderClass {
  
	
  public Object[][] getTestData(String path, String sheetName) {
    
	  XSSFSheet sheet=ExcelRead.getTestDataSheet(path, sheetName);

	  int rowNum=sheet.getLastRowNum();
	  int colCount=sheet.getRow(0).getLastCellNum();
	  
	  Object[][] testData=new Object[rowNum][colCount];
	  
	  for(int i=1;i<=rowNum;i++){
		  for(int j=0;j<colCount;j++){
			  testData[i-1][j]=sheet.getRow(i).getCell(j).getStringCellValue();
		  }
	  }
	  
	  return testData;
  }
}
