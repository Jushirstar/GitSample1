package Vtiger.PomPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v111.log.Log;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import VTiger.GeneralUtilities.ExcelFileUtility;
import VTiger.GeneralUtilities.JavaUtility;
import VTiger.GeneralUtilities.PropertyFileUtilty;
import VTiger.GeneralUtilities.WebDriverUtility;
import VTiger.ObjectRepository.ContactsPage;
import VTiger.ObjectRepository.CreateContactInfoPage;
import VTiger.ObjectRepository.CreateContactPage;
import VTiger.ObjectRepository.CreateOrgInformationPage;
import VTiger.ObjectRepository.CreateOrganisationPage;
import VTiger.ObjectRepository.Homepage;
import VTiger.ObjectRepository.LoginPage;
import VTiger.ObjectRepository.OrganisationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactwithOrg {

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
			      LoginPage lp= new LoginPage(driver);
			      lp.LoginToApp(USERNAME, PASSWORD);
				
				//step 5 : click on organisation link
				Homepage hp=new Homepage(driver);
				hp.clickOnOrgLink();
				
				//step 6 : click on organisation lookup image (+)
				OrganisationPage op=new OrganisationPage(driver);
				op.clickOrgLookupImg();
				
				//step 7 :create organisation 
				CreateOrganisationPage cop = new CreateOrganisationPage(driver);
				cop.createOrganisation(ORGNAME);
						       
		       //step 9 : validation 
		       CreateOrgInformationPage coip=new CreateOrgInformationPage(driver);
		       String OrgHeader = coip.getOrgheaderText();
		       Assert.assertTrue(OrgHeader.contains(ORGNAME));
		       System.out.println(OrgHeader);
		       		       
		     //step 10 : click on contacts link	
		     	hp.clickOnContLink();
		     		
		     //step 11 : click on create contact lookup icon 
		     	ContactsPage cp=new ContactsPage(driver);
		     	cp.clickContactlookupimg(driver);
		     	
		     //step 12 : lastname text field
		     	CreateContactPage ccp=new CreateContactPage(driver);
		     	ccp.createContact(driver, CONTACTNAME, ORGNAME);
		           
		     //step 18 : validation 
		        CreateContactInfoPage ccip=new CreateContactInfoPage(driver);
			    String ContactHeader = ccip.getheaderText();
			    Assert.assertTrue(ContactHeader.contains(CONTACTNAME));
			    System.out.println(ContactHeader);
		        
		        
		    //step 19 : signout of application 
		        hp.logOutofApp(driver);
		       System.out.println("logout successful");
		       
		       driver.close();

	}

}
