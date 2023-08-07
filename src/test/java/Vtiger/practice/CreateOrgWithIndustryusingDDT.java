package Vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithIndustryusingDDT {

	public static void main(String[] args) throws Throwable {
		Random r = new Random();
		int random = r.nextInt(1000);
		
		WebDriver driver = null;
		  
		//Step 1: Read all the necessary data
		
		/* Read data from property File - Common Data */
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pro=new Properties();
		pro.load(fisp);
		String Browser = pro.getProperty("browser");
		String URL = pro.getProperty("url");
		String Username = pro.getProperty("username");
		String Password = pro.getProperty("password");
		
		/* Read Data from Excel sheet - Test data */
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook book = WorkbookFactory.create(fise);
		Sheet sh = book.getSheet("Organisations");
		String OrgName = sh.getRow(4).getCell(2).getStringCellValue()+random;
		String IndustryName = book.getSheet("Organisations").getRow(4).getCell(3).getStringCellValue();
		
		//Step 2: Launch the browser - driver is acting based runtime data - RunTime polymorphism
				if(Browser.equalsIgnoreCase("chrome"))
				{
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					System.out.println(Browser +" Browser launched");
					
				}
				else if(Browser.equalsIgnoreCase("firefox"))
				{
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					System.out.println(Browser+" --- Browser launched");
				}
				else
				{
					System.out.println("invalid Browser name");
				}
				
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//step 3:  load URL
	    driver.get(URL);
		
		//step 4 : login to the application
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		
		//step 5 : click on organisation link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 6 : click on organisation lookup image (+)
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		
		//step 7 :create organisation 
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		
		//step 8: to select industry dropdown & select chemicals 
		 WebElement industryDrpDown = driver.findElement(By.name("industry"));  
		 Select sel=new Select(industryDrpDown);
		 sel.selectByValue("Chemicals");
		 
        //step 9 : to click on save 
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
        //step 10 : validation 
       String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if(OrgHeader.contains(OrgName))
        {
        	System.out.println("pass");
        	System.out.println(OrgHeader);
        }
        else
        {
        	System.out.println("fail");
        }
        
        //step 11 : signout of application 
        WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        Actions act=new Actions(driver);
        act.moveToElement(AdminImg).perform();
        
        //step 12 : click on signout        
       driver.findElement(By.linkText("Sign Out")).click();
       
       driver.close();

	}

}
