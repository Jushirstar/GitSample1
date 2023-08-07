package Vtiger.ProductsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import VTiger.GeneralUtilities.ExcelFileUtility;
import VTiger.GeneralUtilities.JavaUtility;
import VTiger.GeneralUtilities.PropertyFileUtilty;
import VTiger.GeneralUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProducts {

	public static void main(String[] args) throws Throwable {
	     //create object for all utilities 
				ExcelFileUtility eUtil=new ExcelFileUtility();
				PropertyFileUtilty pUtil=new PropertyFileUtilty();
				JavaUtility jUtil=new JavaUtility();
				WebDriverUtility wUtil=new WebDriverUtility();
				WebDriver driver = null;
						  
		//Step 1: Read all the necessary data
				
				/* Read data from property File - Common Data */
				String BROWSER = pUtil.getDataFromPropertyFile("browser");
				String URL = pUtil.getDataFromPropertyFile("url");
				String USERNAME = pUtil.getDataFromPropertyFile("username");
				String PASSWORD = pUtil.getDataFromPropertyFile("password");
				
				
		/* Read Data from Excel sheet - Test data */
				String VENDORNAME = eUtil.getDataFromExcel("Products", 1, 3)+jUtil.getRandomNumber();
				String PRODUCTNAME = eUtil.getDataFromExcel("Products", 1, 2);
				String GLACCOUNT = eUtil.getDataFromExcel("Products", 1, 4);
				
		//Step 2: Launch the browser - driver is acting based runtime data - RunTime polymorphism
				if(BROWSER.equalsIgnoreCase("chrome"))
					{
						WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver();
						System.out.println(BROWSER +" Browser launched");
					}
				else if(BROWSER.equalsIgnoreCase("firefox"))
					{
						WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver();
						System.out.println(BROWSER+" --- Browser launched");
					}
				else
					{
						System.out.println("invalid Browser name");
					}
						
			    wUtil.maximizeWindow(driver);
			    wUtil.waitForElementsToLoad(driver);
						
		//step 3:  load URL
			     driver.get(URL);
				
		//step 4 : login to the application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
	    //step 5 : click on vendor link 
				driver.findElement(By.xpath("//a[.='More']")).click();
				driver.findElement(By.linkText("Vendors")).click();
				
	   //step 6 : click on contact lookup image (+)
				driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();

				
	   //step 7 : vendorname textfield
	 			driver.findElement(By.name("vendorname")).sendKeys(VENDORNAME);
				
	   //step 8 : to click on save 
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			       
	   //step 9 : validation 
			    String VendorHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
			    if(VendorHeader.contains(VENDORNAME))
			       {
			       	System.out.println("vendor name created");
			       	System.out.println(VendorHeader);
			       }
			       else
			       {
			       	System.out.println("fail");
			       }
			       
	   //step 10 : click on products link 
			    driver.findElement(By.linkText("Products")).click();
			      
	   //step 11 : click on products lookup image (+)
			    driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
			      
	  //step 12 : product name text field 
			    driver.findElement(By.name("productname")).sendKeys(PRODUCTNAME);
			      
	  //step 13 : click on vendor name lookup image (+)
			    driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling::img[@alt='Select']")).click();
			    
	 //step 14 : handle GL account dropdown
			    WebElement AccountDrpDwn = driver.findElement(By.name("glacct"));
			    wUtil.handleDropDown(AccountDrpDwn, GLACCOUNT);
			      
	  //step 14 : switch to child window 
			    wUtil.switchToWindow(driver, "Vendors");
			    
	 //step 15 : search for vendor 
			    driver.findElement(By.name("search_text")).click();
			    driver.findElement(By.name("search")).click();
			    driver.findElement(By.xpath("//a[.='"+VENDORNAME+"']")).click();
			    
	 //step 16 : switch back to parent window
			    wUtil.switchToWindow(driver, "Products");
			    
	 //step 17: to click on save 
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			       
	 //step 18 : validation 
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
     //step 19 : signout of application 
		        WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		        wUtil.mouseOverAction(driver, AdminImg);
		        
     //step 20 : click on signout        
		       driver.findElement(By.linkText("Sign Out")).click();
		       System.out.println("logout successful");
		       
		       driver.close();
			      
	}

}
