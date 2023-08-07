package Vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcel {

	public static void main(String[] args) throws Throwable {
		
		    // step 1 : load the file in java readable format 
				FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
				
			//step 2 : create a workbook for a file loaded 
				Workbook wb = WorkbookFactory.create(fis);
				
			//step 3 : create sheet
				Sheet sheet = wb.createSheet("practice");
				
			//step 4 : create row 
				Row row = sheet.createRow(1);
				
			//step 5 : create cell 
				 Cell cell = row.createCell(3);
				  
			//step 6 : set value to cell 
				 cell.setCellValue("Bluetooth");
				  
			//step 7 : open the file in java write format
				  FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
				  
		   //step 8 : call the write method 
				wb.write(fos);
				System.out.println("data added");
		
		   //step 9 : to close workbook 
				 wb.close();
				 System.out.println("workbook closed");

	}

}
