package Vtiger.practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataProviderPractice {

	@Test (dataProvider = "getdata") //or @Test (dataprovider ="phones") - dataprovider name
	public void addToCart(String name, int price, String model)
	{
		System.out.println("Phone name is "+name+" price is "+price+" model is "+model);
	}
	
	@DataProvider (name="phones")
	public Object[][] getdata()
	{
		                            //row, column
		Object [][] data = new Object[3][3];
		
		data[0][0] = "IPhone"; //1st set of data 
		data[0][1] = 20000;
		data[0][2] = "S13";
		
		data[1][0] = "Samsung"; //2nd set of data
		data[1][1] = 15000;
		data[1][2] = "A80";
		
		data[2][0] = "Vivo";  //3rd set of data
		data[2][1] = 10000;
		data[2][2] = "y12";
		return data;
		
	}
}


