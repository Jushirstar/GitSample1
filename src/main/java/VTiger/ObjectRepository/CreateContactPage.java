package VTiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VTiger.GeneralUtilities.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility {
	
	//Rule 1 : identify the webelements using @FindBy, @FindBys, @FindAll

	@FindBy (name = "lastname")
	private WebElement lastnameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement OrgLookupImg;
	
	@FindBy (name="search_text")
	private WebElement OrgsearchEdt;
	
	@FindBy (name = "search")
	private WebElement OrgsearchBtn;
	
	@FindBy (xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//Rule 2 ; create a constructor to initlise the web elements
	public CreateContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

  //Rule 3 ; Provide getters to access the elements
	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	public WebElement getOrgLookupImg() {
		return OrgLookupImg;
	}

	public WebElement getOrgsearchEdt() {
		return OrgsearchEdt;
	}

	public WebElement getOrgsearchBtn() {
		return OrgsearchBtn;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	//Business Library 
	/**
	 * This method will create contact with lastname
	 * @param CONTACTNAME
	 */
	public void createContact(String CONTACTNAME)
	{
		lastnameEdt.sendKeys(CONTACTNAME);
		SaveBtn.click();
	}
	
	public void createContact(WebDriver driver, String CONTACTNAME, String ORGNAME)
	{
		lastnameEdt.sendKeys(CONTACTNAME);
		OrgLookupImg.click();
		switchToWindow(driver, "Accounts");
		OrgsearchEdt.sendKeys(ORGNAME);
		OrgsearchBtn.click();
		switchToWindow(driver, "Contacts");
		driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click();
		SaveBtn.click();
	}
}
