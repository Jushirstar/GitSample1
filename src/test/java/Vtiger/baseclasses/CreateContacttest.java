package Vtiger.baseclasses;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import VTiger.GeneralUtilities.BaseClass;
import VTiger.ObjectRepository.ContactsPage;
import VTiger.ObjectRepository.CreateContactInfoPage;
import VTiger.ObjectRepository.CreateContactPage;
import VTiger.ObjectRepository.Homepage;

@Listeners(VTiger.GeneralUtilities.ListenerImplementationClass.class)
public class CreateContacttest extends BaseClass {

	@Test(groups = "Smokesuit")
	public void createContact() throws Throwable
	{
		/* Read Data from Excel sheet - Test data */
  		String CONTACTNAME = eUtil.getDataFromExcel("Contacts", 1, 2)+jUtil.getRandomNumber();
  		String NAMETYPE = eUtil.getDataFromExcel("Contacts", 1, 3);
  	 
  	//step 5 : click on contacts link	
		Homepage hp = new Homepage(driver);
		hp.clickOnContLink();
			
  	//step 11 : click on create contact lookup icon 
     	ContactsPage cp=new ContactsPage(driver);
     	cp.clickContactlookupimg(driver);
     	
     //step 12 : lastname text field
     	CreateContactPage ccp=new CreateContactPage(driver);
     	ccp.createContact(CONTACTNAME);
           
     //step 18 : validation 
        CreateContactInfoPage ccip=new CreateContactInfoPage(driver);
	    String ContactHeader = ccip.getheaderText();
	    Assert.assertTrue(ContactHeader.contains(CONTACTNAME));
	    System.out.println(ContactHeader);
	      			
	}
}
