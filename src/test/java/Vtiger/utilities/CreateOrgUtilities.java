package Vtiger.utilities;

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

public class CreateOrgUtilities {

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

/* Read Data from Excel sheet - Test data */
    String ORGNAME = eUtil.getDataFromExcel("Organisations", 1, 2)+jUtil.getRandomNumber();
				
//step 3:  load URL
	driver.get(URL);
		
//step  : login to the application
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
		
	
//step 4 : click on organisation link
	driver.findElement(By.linkText("Organizations")).click();
		
//step 5 : click on organisation lookup image (+)
	driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		
//step 6 :create organisation 
   driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

//step 7 : to click on save 
    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
    
//step 8 : validation 
    String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
     if(OrgHeader.contains(ORGNAME))
     {
     	System.out.println("pass");
     	System.out.println(OrgHeader);
     }
     else
     {
     	System.out.println("fail");
     }

//step 9 : signout of application 
    WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
    wUtil.mouseOverAction(driver, AdminImg);
    
    
 //step 10 : click on signout        
   driver.findElement(By.linkText("Sign Out")).click();
   
   driver.close();


	}

}
