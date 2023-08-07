package Vtiger.ContactTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import VTiger.GeneralUtilities.ExcelFileUtility;
import VTiger.GeneralUtilities.JavaUtility;
import VTiger.GeneralUtilities.PropertyFileUtilty;
import VTiger.GeneralUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactwithOrgTest {

@Test
public void createContactwithOrgTest() throws Throwable {
		
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
		String ORGNAME = eUtil.getDataFromExcel("Contacts", 4, 3)+jUtil.getRandomNumber();
		String CONTACTNAME = eUtil.getDataFromExcel("Contacts", 4, 2);
		
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
		
		//step 5 : click on organisation link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 6 : click on organisation lookup image (+)
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		
		//step 7 :create organisation 
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
       //step 8 : to click on save 
       driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
       
       //step 9 : validation 
      String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
       if(OrgHeader.contains(ORGNAME))
       {
       	System.out.println("organisation created");
       	System.out.println(OrgHeader);
       }
       else
       {
       	System.out.println("fail");
       }
       
     //step 10 : click on contacts link	
     	driver.findElement(By.linkText("Contacts")).click();
     		
     //step 11 : click on create contact lookup icon 
     	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
     	
     //step 12 : lastname text field
     	driver.findElement(By.name("lastname")).sendKeys(CONTACTNAME);
       
    //Step 13 : Click on org lookup icon 
     	driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
     	
     //step 14 : switch to child window 
     	wUtil.switchToWindow(driver, "Accounts");
     	
     //step 15 : search for organisation
     	driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
     	driver.findElement(By.name("search")).click();
     	driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
     	
     //step 16 : switch back to parent window 
     	wUtil.switchToWindow(driver, "Contacts");
     	
     //step 17 :  to click on save 
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
     //step 18 : validation 
       String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if(ContactHeader.contains(CONTACTNAME))
        {
        	System.out.println("contact created");
        	System.out.println(ContactHeader);
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
