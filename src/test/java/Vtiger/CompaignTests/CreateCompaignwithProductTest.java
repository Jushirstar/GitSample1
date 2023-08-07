package Vtiger.CompaignTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import VTiger.GeneralUtilities.BaseClass;
import VTiger.GeneralUtilities.ExcelFileUtility;
import VTiger.GeneralUtilities.JavaUtility;
import VTiger.GeneralUtilities.PropertyFileUtilty;
import VTiger.GeneralUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

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
	    if(ProductHeader.contains(PRODUCTNAME))
	       {
	       	System.out.println("product created");
	       	System.out.println(ProductHeader);
	       }
	       else
	       {
	       	System.out.println("fail");
	       }
	    
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
	    if(CompaignHeader.contains(COMPAIGNNAME))
	       {
	       	System.out.println("compaign created");
	       	System.out.println(CompaignHeader);
	       }
	       else
	       {
	       	System.out.println("fail");
	       }
	    
//step 13 : signout of application 
        WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
              

	}

}
