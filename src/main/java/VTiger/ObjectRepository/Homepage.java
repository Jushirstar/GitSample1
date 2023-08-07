package VTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VTiger.GeneralUtilities.WebDriverUtility;

public class Homepage extends WebDriverUtility {
	
	//Rule 1 : identify the webelements using @FindBy, @FindBys, @FindAll
	@FindBy (linkText="Organizations")
	private WebElement OrganisationLnk;
	
	@FindBy (linkText="Contacts")
	private WebElement ContactsLnk;
	
	@FindBy (linkText = "Opportunities")
	private WebElement OpportunitiesLnk;

	@FindBy (linkText = "Products")
	private WebElement ProductsLnk;
	
	@FindBy (xpath = "//a[.='More']")
	private WebElement MoreLnk;
	
	@FindBy (xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdministratorImg;
	
	@FindBy (linkText = "Sign Out")
	private WebElement SignOutLnk;
	
	//Rule 2 : create a constructor to initialise the web elements  
		public Homepage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

	//Rule 3 : Provide getters to access the elements 
		public WebElement getOrganisationLnk() {
			return OrganisationLnk;
		}

		public WebElement getContactsLnk() {
			return ContactsLnk;
		}

		public WebElement getOpportunitiesLnk() {
			return OpportunitiesLnk;
		}

		public WebElement getProductsLnk() {
			return ProductsLnk;
		}

		public WebElement getMoreLnk() {
			return MoreLnk;
		}

		public WebElement getAdministratorLnk() {
			return AdministratorImg;
		}

		public WebElement getSignOutLnk() {
			return SignOutLnk;
		}
		
	//Business Library 
		/**
		 * This method will click on Organisation link 
		 */
		
		public void clickOnOrgLink()
		{
			OrganisationLnk.click();
		}
		
		/**
		 * This method will click on Contacts link 
		 */
		public void clickOnContLink()
		{
			ContactsLnk.click();
		}
		
		public void clickOnOpprtLink()
		{
			OpportunitiesLnk.click();
		}
		
		/**
		 * This method will signout from the application 
		 * @param driver
		 */
		
		public void logOutofApp(WebDriver driver)
		{
			mouseOverAction(driver, AdministratorImg);
			SignOutLnk.click();
		}
		
		
	
	
}
