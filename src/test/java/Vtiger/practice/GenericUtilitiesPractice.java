package Vtiger.practice;

import VTiger.GeneralUtilities.ExcelFileUtility;
import VTiger.GeneralUtilities.JavaUtility;
import VTiger.GeneralUtilities.PropertyFileUtilty;

public class GenericUtilitiesPractice {

	public static void main(String[] args) throws Throwable {
		
		//JavaUtility 
		JavaUtility JUtil = new JavaUtility();
		int value = JUtil.getRandomNumber();
		System.out.println(value);
		
		System.out.println(JUtil.getSystemDate());
		
		System.out.println(JUtil.getSystemDateInFormat());
		
		//FileUtility 
		PropertyFileUtilty pUtil=new PropertyFileUtilty();
		String value1 = pUtil.getDataFromPropertyFile("password");
		System.out.println(value1);
		//System.out.println(pUtil.getDataFromPropertyFile("username"));
		
		//ExcelFileUtility 
		ExcelFileUtility ExUtil=new ExcelFileUtility();
		System.out.println(ExUtil.getDataFromExcel("Organisation", 1, 2)); // Qspiders 
		System.out.println(ExUtil.getDataFromExcel("Organisation", 4, 3)); // chemicals 
		
		ExUtil.writeDataIntoExcel("practice2", 2, 2, "star");
		System.out.println("data added");
		
		

	}

}
