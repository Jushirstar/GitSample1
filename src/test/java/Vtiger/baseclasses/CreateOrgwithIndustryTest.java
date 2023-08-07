package Vtiger.baseclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
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
public class CreateOrgwithIndustryTest extends BaseClass {

	@Test (groups ={"Smokesuit", "RegressionSuit"} )
	public void createOrgwithIndustry() throws Throwable {
	/* Read Data from Excel sheet - Test data */
	String ORGNAME = eUtil.getDataFromExcel("Organisations", 7, 2)+jUtil.getRandomNumber();
	String INDUSTRY = eUtil.getDataFromExcel("Organisations", 7, 3);
	String TYPE = eUtil.getDataFromExcel("Organisations", 7, 4);
								
	//step 4 : click on organisation link
		Homepage hp=new Homepage(driver);
		hp.clickOnOrgLink();
		Reporter.log("Organisation link clicked");

	//step 5 : click on organisation lookup image (+)
		OrganisationPage opg= new OrganisationPage(driver);
		opg.clickOrgLookupImg();
		Reporter.log("Org look up icon clicked");

	//step 6 :create organisation 
		CreateOrganisationPage copg = new CreateOrganisationPage(driver);
		copg.createOrganisationwithIndustryandType(ORGNAME, INDUSTRY, TYPE);
		Reporter.log("Organisation created");

	 //step 9 : validation 
		CreateOrgInformationPage coinfopage=new CreateOrgInformationPage(driver);
		String OrgHeader = coinfopage.getOrgheaderText();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println("validated");
	 
	 //step 10 : signout of application 
	 hp.logOutofApp(driver);
	 driver.close();
	}

	/* @Test
 	 public void simpletest()
 {
 	//Assert.fail(); //intensionally fail the script
 	System.out.println("demo test");
	}*/

}
