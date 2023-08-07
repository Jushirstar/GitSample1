package VTiger.GeneralUtilities;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class consists of generic methods related to excel file
	 * @throws Throwable 
 *  
 */

public class ExcelFileUtility {

	/**
	 * This method is used to read data from excel
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return value
	 * @throws Throwable
	 */
	
		public String getDataFromExcel(String sheetName, int rowNum, int celNum ) throws Throwable
		{
			FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
			 Workbook wb = WorkbookFactory.create(fise);
			 String value = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
			 return value;
			 
			 /*Row rw = sh.getRow(rowNum);
			 Cell cl = rw.getCell(celNum);
			 String value = cl.getStringCellValue();
			 return value;*/
		
		}
		/**
		 * This method is used to read data from excel
		 * @param sheetName
		 * @param rowNum
		 * @param cellNum
		 * @return value
		 * @throws Throwable
		 */
		
		public String getExcelDataFormatter(String sheetName,int rowNum, int cellNum) throws Throwable
		{
			FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
			
			//step 2 : open workbook in read mode
			Workbook book = WorkbookFactory.create(fis1);
			   DataFormatter format=new DataFormatter();
			  String value = format.formatCellValue(book.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
			  return value;
		}
		
		public void writeDataIntoExcel(String sheetName, int roNum, int celNo, String data) throws Throwable {
			FileInputStream fis = new FileInputStream(".\\\\src\\\\test\\\\resources\\\\TestData.xlsx");
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh = wb.createSheet(sheetName);
			Row rw = sh.createRow(roNum);
			Cell cel = rw.createCell(celNo);
			cel.setCellValue(data);
			
			FileOutputStream fos= new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
			wb.write(fos);
			wb.close();
		}

		/**
		 * This method will read all the data inside a sheet to used in the data provider 
		 * @param SHEETNAME
		 * @return 
		 * @throws Throwable
		 * @throws IOException
		 */
		public Object[][] readMultipleData(String SHEETNAME) throws Throwable, IOException
		{
			FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(SHEETNAME);
			 int lastRow = sh.getLastRowNum(); //capture the no. of rows
			 int lastcell = sh.getRow(0).getLastCellNum(); //capture the no. of cells 
			
			Object[][] data = new Object[lastRow][lastcell];
			for (int i = 0; i < lastRow; i++) //For row navigation
			{
				for (int j = 0; j < lastcell; j++) //For cell navigation 
				{
					data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
				}
			}
			return data;
				
		}
	}

