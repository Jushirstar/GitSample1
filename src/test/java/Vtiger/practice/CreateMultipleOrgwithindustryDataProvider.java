package Vtiger.practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import VTiger.GeneralUtilities.ExcelFileUtility;
import VTiger.GeneralUtilities.JavaUtility;
import VTiger.GeneralUtilities.PropertyFileUtilty;
import VTiger.GeneralUtilities.WebDriverUtility;
import VTiger.ObjectRepository.CreateOrgInformationPage;
import VTiger.ObjectRepository.CreateOrganisationPage;
import VTiger.ObjectRepository.Homepage;
import VTiger.ObjectRepository.LoginPage;
import VTiger.ObjectRepository.OrganisationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateMultipleOrgwithindustryDataProvider {

	ExcelFileUtility eUtil=new ExcelFileUtility();
	PropertyFileUtilty pUtil=new PropertyFileUtilty();
	JavaUtility jUtil=new JavaUtility();
	WebDriverUtility wUtil=new WebDriverUtility();
	WebDriver driver = null;
	
		@Test (dataProvider = "getData")
	public void createMultipleOrg(String ORG, String INDUSTRY) throws Throwable 
		{
			
			  
			//Step 1: Read all the necessary data*/
					
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

			 /*Read Data from Excel sheet - Test data */
			String ORGNAME = ORG +jUtil.getRandomNumber();
										
			//step 3:  load URL
				driver.get(URL);
					
			//step  : login to the application
				LoginPage Lpg=new LoginPage(driver);
				Lpg.LoginToApp(USERNAME, PASSWORD);
					
			//step 4 : click on organisation link
				Homepage hpg = new Homepage(driver);
				hpg.clickOnOrgLink();
			    
			//step 5 : click on organisation lookup image (+)
				OrganisationPage opg= new OrganisationPage(driver);
				opg.clickOrgLookupImg();
			
			//step 6 :create organisation 
				CreateOrganisationPage copg = new CreateOrganisationPage(driver);
				copg.createOrganisationwithIndustry(ORGNAME, INDUSTRY);
							  
			//step 9 : validation 
				CreateOrgInformationPage coinfopage=new CreateOrgInformationPage(driver);
				coinfopage.getOrgheaderText();
			    String OrgHeader = coinfopage.getOrgheaderText();
			 if(OrgHeader.contains(ORGNAME))
			 {
			 	System.out.println("pass");
			 	System.out.println(OrgHeader);
			 }
			 else
			 {
			 	System.out.println("fail");
			 }
			 
			 //step 10 : signout of application 
			   hpg.logOutofApp(driver);
			   driver.close();

		}
		
	
	@DataProvider
	public Object[][] getData() throws Throwable, IOException
	{
		return eUtil.readMultipleData("MultipleData");
	}
		

	}


