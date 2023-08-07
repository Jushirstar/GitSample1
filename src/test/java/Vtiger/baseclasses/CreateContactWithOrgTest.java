package Vtiger.baseclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import VTiger.GeneralUtilities.BaseClass;
import VTiger.ObjectRepository.ContactsPage;
import VTiger.ObjectRepository.CreateContactInfoPage;
import VTiger.ObjectRepository.CreateContactPage;
import VTiger.ObjectRepository.CreateOrgInformationPage;
import VTiger.ObjectRepository.CreateOrganisationPage;
import VTiger.ObjectRepository.Homepage;
import VTiger.ObjectRepository.LoginPage;
import VTiger.ObjectRepository.OrganisationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrgTest extends BaseClass{
	
	@Test(groups = "RegressionSuit")
	public void createContactwithOrg() throws Throwable
	{
		/* Read Data from Excel sheet - Test data */
		String ORGNAME = eUtil.getDataFromExcel("Contacts", 4, 3)+jUtil.getRandomNumber();
		String CONTACTNAME = eUtil.getDataFromExcel("Contacts", 4, 2);
		
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
	}

}
