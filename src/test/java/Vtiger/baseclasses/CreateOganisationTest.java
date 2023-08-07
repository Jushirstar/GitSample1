package Vtiger.baseclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import VTiger.GeneralUtilities.BaseClass;
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

@Listeners(VTiger.GeneralUtilities.ListenerImplementationClass.class)
public class CreateOganisationTest extends BaseClass {

	@Test (groups ={"Smokesuit", "RegressionSuit"} )
	public void createOrg() throws Throwable {
		
/* Read Data from Excel sheet - Test data */
    String ORGNAME = eUtil.getDataFromExcel("Organisations", 1, 2)+jUtil.getRandomNumber();
				
//step 4 : click on organisation link
	Homepage hp=new Homepage(driver);
	hp.clickOnOrgLink();
    		
//step 5 : click on organisation lookup image (+)
	OrganisationPage op=new OrganisationPage(driver);
	op.clickOrgLookupImg();
			
//step 6 :create organisation 
	CreateOrganisationPage cop=new CreateOrganisationPage(driver);
	cop.createOrganisation(ORGNAME);
	  
//step 8 : validation 
	CreateOrgInformationPage coip=new CreateOrgInformationPage(driver);
	 String OrgHeader = coip.getOrgheaderText();
	 Assert.assertTrue(OrgHeader.contains(ORGNAME));
	 System.out.println(OrgHeader);
	     
	}

/*@Test
public void sampleTest()
{
	//Assert.fail(); //intensionally fail the script
	System.out.println("demo executed");
}*/
}

