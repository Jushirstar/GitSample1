package VTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	@FindBy (xpath = "//img[@title='Create Contact...']")
	private WebElement CreateContLookUpImg;
	
	//Rule 2 ; create a constructor to initlise the web elements
	
	public ContactsPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Rule 3 ; Provide getters to access the elements
	public WebElement getCreateContLookUpImg() {
		return CreateContLookUpImg;
	}
	
	//Business Library 
	public void clickContactlookupimg(WebDriver driver)
	{
		CreateContLookUpImg.click();
	}
	
	
	
}
