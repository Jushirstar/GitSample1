package VTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VTiger.GeneralUtilities.WebDriverUtility;

public class CreateOrganisationPage extends WebDriverUtility {
	
	//Rule 1 : identify the webelements using @FindBy, @FindBys, @FindAll
	@FindBy (name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy (name = "industry")
	private WebElement IndusDrpDwn;
	
	@FindBy (name = "accounttype")
	private WebElement TypeDrpDwn;
	
	@FindBy (xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//Rule 2 : Create a constructor of initialise the web elements
	public CreateOrganisationPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Rule 3 ; Provide getters to access the elements
	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndusDrpDwn() {
		return IndusDrpDwn;
	}

	public WebElement getTypeDrpDwn() {
		return TypeDrpDwn;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	//Business Library 
	/**
	 * This method will create organisation with mandatory fields 
	 * @param ORGNAME
	 */
	public void createOrganisation(String ORGNAME) {
		OrgNameEdt.sendKeys(ORGNAME);
		SaveBtn.click();
	}
	
	/**
	 * This method will create organisation with industry drop down 
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createOrganisationwithIndustry(String ORGNAME, String INDUSTRY)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		handleDropDown(IndusDrpDwn, INDUSTRY);
		SaveBtn.click();
	}
	
	/**
	 * This method will create organisation with industry and Type drop down
	 * @param ORGNAME
	 * @param INDUSTRY
	 * @param TYPE
	 */
	
	public void createOrganisationwithIndustryandType(String ORGNAME, String INDUSTRY, String TYPE)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		handleDropDown(IndusDrpDwn, INDUSTRY);
		handleDropDown(TypeDrpDwn, TYPE);
		SaveBtn.click();
	}
	
	
	
	
	

}
