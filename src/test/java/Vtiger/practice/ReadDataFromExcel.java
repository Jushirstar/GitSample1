package Vtiger.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws Throwable, IOException {
		
		// step 1 : load the file in java readable format 
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//step 2 : create a workbook for a file loaded 
		Workbook wb = WorkbookFactory.create(fis);
		
		//step 3 : navigate to required sheet
		Sheet sh = wb.getSheet("Organisation");
		
		//step 4 : navigate to required row 
		Row rw = sh.getRow(4);
		
		//step 5 : navigate to required cell 
		Cell cel = rw.getCell(3);
		
		//step 6 : capture the value from excel
		String value = cel.getStringCellValue();
		System.out.println(value);
		

	}

}
