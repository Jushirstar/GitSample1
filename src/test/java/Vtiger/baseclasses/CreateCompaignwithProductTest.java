package Vtiger.baseclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import VTiger.GeneralUtilities.BaseClass;


public class CreateCompaignwithProductTest extends BaseClass {

	@Test
	public void createCompaignWithProTest() throws Throwable {
	
/* Read Data from Excel sheet - Test data */
		String PRODUCTNAME = eUtil.getDataFromExcel("Products", 1, 2)+ jUtil.getRandomNumber();
		String COMPAIGNNAME = eUtil.getDataFromExcel("Compaigns", 1, 2);
		String COMPAIGNTYPE = eUtil.getDataFromExcel("Compaigns", 1, 3);
		String COMPAIGNSTATUS = eUtil.getDataFromExcel("Compaigns", 1, 4);
				
//step 5 : click on products link 
	    driver.findElement(By.linkText("Products")).click();
	      
//step 6 : click on products lookup image (+)
	    driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
	      
//step 7 : product name text field 
	    driver.findElement(By.name("productname")).sendKeys(PRODUCTNAME);
	    
	  //step 17: to click on save 
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	       
//step 8 : validation 
	    String ProductHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
	    Assert.assertTrue(ProductHeader.contains(PRODUCTNAME));
	    	    
//step 9 ; click on compaign link in more options 
	    driver.findElement(By.xpath("//a[.='More']")).click();
		driver.findElement(By.linkText("Campaigns")).click();
		
//step 10 ; Click on create compaign lookup image 
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
//step 11 ; compaign name text field 
		driver.findElement(By.name("campaignname")).sendKeys(COMPAIGNNAME);
		WebElement CompaignType = driver.findElement(By.name("campaigntype"));
		wUtil.handleDropDown(CompaignType, COMPAIGNTYPE);
		WebElement CompStatus = driver.findElement(By.name("campaignstatus"));
		wUtil.handleDropDown(CompStatus, COMPAIGNSTATUS);
		
//step 12 ; click on products lookup image 
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		
//step 13 : swicth to child window
		wUtil.switchToWindow(driver, "Products");
		
//step 14 : search created product
		driver.findElement(By.name("search_text")).sendKeys(PRODUCTNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='"+PRODUCTNAME+"']")).click();

//step 15 : switch back to parent window 
		wUtil.switchToWindow(driver, "Campaigns");
		
//step 16 ; to click on save 
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
//step 17 : validation 
	    String CompaignHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    Assert.assertTrue(CompaignHeader.contains(COMPAIGNNAME));
	     System.out.println(CompaignHeader);
	       
	                
	}
}

