package VTiger.GeneralUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import VTiger.ObjectRepository.Homepage;
import VTiger.ObjectRepository.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class consists of all the basic configuration annotations for 
 * all the common actions
 * @author HP
 *
 */
public class BaseClass {
	
	public ExcelFileUtility eUtil=new ExcelFileUtility();
	public PropertyFileUtilty pUtil=new PropertyFileUtilty();
	public JavaUtility jUtil=new JavaUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public WebDriver driver = null;
	
	//only used for listeners to take screenshot 
	public static WebDriver sdriver;
	
	@BeforeSuite(groups = {"Smokesuit", "RegressionSuit"}) // better in general should give (alwaysRun = true)
	public void bsConfig() 
	{
		System.out.println("======db connection successfull");
	}
	
	//@Parameters ("browser") //this key hold a value of chrome or firefox which is in xml file
	@BeforeTest
	//@BeforeClass(alwaysRun = true)
	public void bcConfig(/*String BROWSER*/) throws Throwable
	{
		String BROWSER = pUtil.getDataFromPropertyFile("browser");
		String URL = pUtil.getDataFromPropertyFile("url");
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER +" Browser launched");
			
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER+" --- Browser launched");
		}
		else
		{
			System.out.println("invalid Browser name");
		}
		
       wUtil.maximizeWindow(driver);
       wUtil.waitForElementsToLoad(driver);
       
       //only used for listeners to take screenshot
       sdriver=driver;
       
       //step 3:  load URL
		driver.get(URL);
			
	}
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws Throwable
	{
		String USERNAME = pUtil.getDataFromPropertyFile("username");
		String PASSWORD = pUtil.getDataFromPropertyFile("password");
		
		LoginPage lp=new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
		
		System.out.println("====login successfull");
	}
	
	@AfterMethod(alwaysRun = true)
	public void amConfig()
	{
		Homepage hp=new Homepage(driver);
		hp.logOutofApp(driver);
		
		System.out.println("====Logout Successfull");
	}
	
	@AfterClass(alwaysRun = true)
	public void acConfig() 
	{ 
		driver.close();
		System.out.println("=========browser closed");
		
	}
	
	@AfterSuite
	public void asConfig() 
	{
		System.out.println("======db connection closed");
	}
	
	

}
