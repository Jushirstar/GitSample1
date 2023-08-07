package VTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactInfoPage {
	//Rule 1 : identify the webelements using @FindBy, @FindBys, @FindAll
		@FindBy (xpath = "//span[@class='dvHeaderText']")
		private WebElement ContHeaderText;
		
		//Rule 2 : Create a constructor of initialise the web elements
		public CreateContactInfoPage (WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		
		//Rule 3 ; Provide getters to access the elements
		public WebElement getOrgHeaderText() {
			return ContHeaderText;
		}
			
		//Business Library 
		/**
		 * This method will capture the header text and return it to caller 
		 * @return
		 */
		public String getheaderText()
		{
		   return ContHeaderText.getText();
		}
}
