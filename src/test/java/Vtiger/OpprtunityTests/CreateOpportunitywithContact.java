package Vtiger.OpprtunityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import VTiger.GeneralUtilities.ExcelFileUtility;
import VTiger.GeneralUtilities.JavaUtility;
import VTiger.GeneralUtilities.PropertyFileUtilty;
import VTiger.GeneralUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunitywithContact {

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
				String CONTACTNAME = eUtil.getDataFromExcel("Opportunites", 1, 2)+jUtil.getRandomNumber();
				String OPPORNAME = eUtil.getDataFromExcel("Opportunites", 1, 3);
				String RELATEDTYPE = eUtil.getDataFromExcel("Opportunites", 1, 4);
				
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
		
		//step 5 ; click on contacts link	
		     	driver.findElement(By.linkText("Contacts")).click();
	     		
	    //step 6 : click on create contact lookup icon 
		        driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		        	
	    //step 7 : lastname text field
		        driver.findElement(By.name("lastname")).sendKeys(CONTACTNAME);
		        	
	    //step 8 ; click on save button 
		        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		        
	    //step 9 : validation 
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
		        
	    //step 10 ; click on Opportunities link 
		        driver.findElement(By.linkText("Opportunities")).click();
		        
	    //step 11 ; click on create opportunities lookup image
		        driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		        
        //step 12 ; create opportunity
		        driver.findElement(By.name("potentialname")).sendKeys(OPPORNAME);
		
		//step 13 ; click on type dropdown 
		        WebElement RelatedTypeDrpDwn = driver.findElement(By.id("related_to_type"));
		        wUtil.handleDropDown(RelatedTypeDrpDwn, RELATEDTYPE);
		        		        
		//step 14 ; click on related to look up image
		        driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img[@title='Select']")).click();
		               
		//step 15 ; switch to child window 
		        wUtil.switchToWindow(driver, "Contacts");
		        
		//step 16 : select created contact 
		        driver.findElement(By.name("search_text")).sendKeys(CONTACTNAME);
		     	driver.findElement(By.name("search")).click();
		     	  //driver.findElement(By.xpath("//a[text()='"+CONTACTNAME+"']")).click();
		        
	   //step 17 ; switch back to parent window s
		     	wUtil.switchToWindow(driver, "Potentials");
		     	
	   //step 18 : to click on save 
		           driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		           
	   //step 19 : validation 
		          String OpporHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		           if(OpporHeader.contains(OPPORNAME))
		           {
		           	System.out.println("Potential created");
		           	System.out.println(OpporHeader);
		           }
		           else
		           {
		           	System.out.println("fail");
		           }
		           
	   //step 20 : signout of application 
		           WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		           wUtil.mouseOverAction(driver, AdminImg);
		           
	  //step 21 : click on signout        
		          driver.findElement(By.linkText("Sign Out")).click();
		          System.out.println("logout successful");
		          
		          driver.close();
			

	}

}
