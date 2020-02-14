package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel {
	
	public static Object[][] readExcelSheet(String sheetName) throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\utilities\\testData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rows][cols];
		for(int i=1;i<=rows;i++)
		{
			Row row = sheet.getRow(i);
			for(int j=0;j<cols;j++) 
			{
				Cell cell = row.getCell(j);
				String val = cell.getStringCellValue();
				data[i-1][j] = val;
			}
		}
		return data;
	}
	
	public static void writeExcelSheet(String sheetName,String[][] data) throws IOException
	{
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\utilities\\testData2.xlsx");
		FileOutputStream fis = new FileOutputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int rows = data.length;
		int cols = data[0].length;
		for(int i=0;i<rows;i++)
		{
			Row row = sheet.createRow(i);
			for(int j=0;j<cols;j++)
			{
				Cell cell = row.createCell(j);
				cell.setCellValue(data[i][j]);
			}
		}
				
		wb.write(fis);
		fis.close();
	}
	
	public static void addExcelSheet(String sheetName,String[][] data) throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\utilities\\testData2.xlsx";	
		FileInputStream fi = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet sheet = wb.createSheet(sheetName);
				
		int rows = data.length;
		int cols = data[0].length;
		for(int i=0;i<rows;i++)
		{
			Row row = sheet.createRow(i);
			for(int j=0;j<cols;j++)
			{
				Cell cell = row.createCell(j);
				cell.setCellValue(data[i][j]);
			}
		}
		FileOutputStream fis = new FileOutputStream(path);		
		wb.write(fis);
		fis.close();
	}

}
