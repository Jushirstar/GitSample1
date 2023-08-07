package VTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.github.dockerjava.api.model.Driver;

public class OrganisationPage {

	//Rule 1 : identify the webelements using @FindBy, @FindBys, @FindAll
	
	@FindBy (xpath = "//img[@title='Create Organization...']")
	private WebElement CreateOrgLookUpImg;
	
	//Rule 2 ; create a constructor to initlise the web elements
	
	public OrganisationPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Rule 3 ; Provide getters to access the elements
	public WebElement getOrgLookUpImg() {
		return CreateOrgLookUpImg;
	}
		
	//Business Library 
	
	/**
	 * This method will click on Create Organisation lookup image
	 */
		public void clickOrgLookupImg()
		{
			CreateOrgLookUpImg.click();
		}
				
}

