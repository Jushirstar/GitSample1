package Vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgwithIndustry {

	public static void main(String[] args) throws Throwable {
		
		Random r = new Random();
		int random = r.nextInt(1000);
		
		//step 1 : launch the browser 
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//step 2:  load URL
		driver.get("http://localhost:8888/");
		
		//step 3 : login to the application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step 4 : click on organisation link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 5 : click on organisation lookup image (+)
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		
		//step 6 :create organisation 
		String OrgName = "L&T"+random;
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		
		//step 7: to select dropdown of industry
		WebElement industry = driver.findElement(By.name("industry"));
		
		//step 8 : to select chemicals from dropdown
		Select sel = new Select(industry);
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
	}

}
