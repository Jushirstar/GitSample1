package Vtiger.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContact {

	public static void main(String[] args) {
		
		//step 1 : Launch browser 
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		//step 2 : fetch URL 
		driver.get("http://localhost:8888/");
		
		//step 3 : login to the application 
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.manage().window().maximize();
		
		//step 4 : click on contacts link	
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 5 : click on create contact lookup icon 
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step 6 : create contact with mandatory fields 
		WebElement nametype = driver.findElement(By.name("salutationtype"));
		//handle dropdown
		Select sel=new Select(nametype);
		sel.selectByValue("Mr.");
		driver.findElement(By.name("firstname")).sendKeys("RAJU");
		driver.findElement(By.name("lastname")).sendKeys("R");
		
		//step 7 : click on save button 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step 8 : validation 
		String ContactName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(ContactName.contains("RAJU"))
		{
			System.out.println("pass");
		}
		else 
		{
			System.out.println("fail");
		}
		
		//step 9 : signout of application 
        WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        Actions act=new Actions(driver);
        act.moveToElement(AdminImg).perform();
        
        //step 10 : click on signout        
       driver.findElement(By.linkText("Sign Out")).click();

	}

}
