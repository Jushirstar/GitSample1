package Vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyFilePractice {

	public static void main(String[] args) throws Throwable {
				
		//step 1 : load the document in java readable format 
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//step 2 : create object of properties class from java.util 
		Properties pro=new Properties();
		
		//step 3 : load the data into properties class 
		pro.load(fis);
		
		//step 4 : provide the key and get the value 
		String value = pro.getProperty("url");
		System.out.println(value);
		
		

	}

}
