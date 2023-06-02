package hariKrishna.DataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDriven extends Utility{
	
	@Test(dataProvider = "excel")
	public void dataTest(String user, String pass) {
		driver.findElement(By.id("username")).sendKeys(user);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.id("Login")).click();
		Assert.assertEquals(
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.",
				driver.findElement(By.id("error")).getText());
	}

	@DataProvider(name="excel")
	public Object[][] getExcelData() throws IOException {
		Object [][] data = getExcel("D:\\Edu\\Java\\data\\dataSheet.xlsx","LoginPass");
		return data;
		
	}
	
	public Object[][] getExcel(String path, String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int sheets = workbook.getNumberOfSheets();
		XSSFSheet sheet = null;
		for (int i = 0; i< sheets; i++) {
			if(workbook.getSheetAt(i).getSheetName().contains(sheetName)) {
				sheet = workbook.getSheetAt(i);
				break;
			}
		}
		int rowIndex = sheet.getLastRowNum();
		XSSFRow row = sheet.getRow(0);
		int columnIndex = row.getLastCellNum();
		Object[][] data = new Object[rowIndex][columnIndex];
		for(int i =0; i< rowIndex; i++) {
			row = sheet.getRow(i+1);
			for(int j = 0; j< columnIndex; j++) {
				XSSFCell cell = row.getCell(j);
				if(cell.getCellType()==CellType.STRING) {
					data[i][j] = cell.getStringCellValue();
				} else {
					data[i][j] = NumberToTextConverter.toText(cell.getNumericCellValue());
				}
			}
		}
		return data;
	}
}
