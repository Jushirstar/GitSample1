package VTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {//Rule 1 : Create seperate POM class for every page 
	
	
	//Rule 2 : identify the webelements using @FindBy, @FindBys, @FindAll
	@FindBy(name="user_name")
	private WebElement userNameEdt;
	
	@FindBy(name="user_password")
	private WebElement PasswordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
		
	//Rule 3 : create a constructor to initlise the web elements  
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
		
	//Rule 3 : Provide getters to access the elements 
	public WebElement getUserNameEdt() {
	return userNameEdt;
	}

	public WebElement getPasswordEdt() {
	return PasswordEdt;
	}

   public WebElement getLoginBtn() {
	return loginBtn;
	}
		
		//Business Library - Project specific generic method 
   
   /**
    * This method will perform login operation 
    * @param USERNAME
    * @param PASSWORD
    */
     public void LoginToApp(String USERNAME, String PASSWORD)
     {
    	 userNameEdt.sendKeys(USERNAME);
    	 PasswordEdt.sendKeys(PASSWORD);
    	 loginBtn.click();
     }
		

}
