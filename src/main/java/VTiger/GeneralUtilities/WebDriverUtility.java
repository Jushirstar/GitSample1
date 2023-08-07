

package VTiger.GeneralUtilities;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of all the reusable methods related to webdriver actions
 * @author HP
 *
 */
public class WebDriverUtility {
	/**
	 * This method used to maximize window 
	 * @param driver
	 */
	
	public void maximizeWindow(WebDriver driver) 
	{
		driver.manage().window().maximize();
		
	}
	/**
	 * This method used to minimize window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method is used for all the findelement and findelements operations to be performed
	 * @param driver
	 */
	public void waitForElementsToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait until the specified element is visible in DOM  
	 * @param driver
	 * @param element
	 */
	public void waitForElementToVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	 
	/**
	 * This method is used to handle dropdown using index
	 * @param element
	 * @param Index
	 */
         public void handleDropDown(WebElement element, int Index)
         {
        	 Select sel=new Select(element);
        	 sel.selectByIndex(Index);
         }
         
       /**
        * This method is used to handle dropdown using value
        * @param element
        * @param value
        */
         public void handleDropDown(WebElement element, String value)
         {
        	 Select sel=new Select(element);
        	 sel.selectByValue(value);
         }
       
         /**
          * This method used to handle dropdown using visible text
          * @param VisibleText
          * @param element
          */
         
         public void handleDropDown(String VisibleText, WebElement element)
         {
        	 Select sel=new Select(element);
        	 sel.selectByVisibleText(VisibleText);
         }
         /**
          * This method is used to move mouse to elemeny
          * @param driver
          * @param element
          */
         
         public void mouseOverAction(WebDriver driver, WebElement element)
         {
        	 Actions act=new Actions(driver);
        	 act.moveToElement(element).perform();
         }
	
         /**
          * This method is used to double click anywhere on web page 
          * @param driver
          */
         public void doubleClickAction(WebDriver driver)
         {
        	 Actions act=new Actions(driver);
        	 act.doubleClick().perform();
         }
         
         /**
          * This method is used to double click on element 
          * @param driver
          * @param element
          */

         public void doubleClickAction(WebDriver driver, WebElement element)
         {
        	 Actions act=new Actions(driver);
        	 act.doubleClick(element).perform();
         }
         
         /**
          * This method is used to right click anywhere on web page 
          * @param driver
          */
         public void rightClickAction(WebDriver driver)
         {
        	 Actions act=new Actions(driver);
        	 act.contextClick().perform();
         }
         
         /**
          * This method is used to right click on particular web element
          * @param driver
          * @param element
          */
         
         public void rightClickAction(WebDriver driver, WebElement element)
         {
        	 Actions act= new Actions(driver);
        	 act.contextClick(element).perform();
         }
         
         /**
          * This method is used to perform drag and drop action
          * @param driver
          * @param srcElement
          * @param targetElement
          */
         public void dragAndDrop(WebDriver driver, WebElement srcElement, WebElement targetElement)
         {
        	 Actions act= new Actions(driver);
        	 act.dragAndDrop(srcElement, targetElement);
         }
         
         /**
          * This method is used to move the cursor anywhere on the webpage based on X & Y coodinates 
          * @param driver
          * @param xOffset
          * @param yOffset
          */
         public void moveAcrossWebpage(WebDriver driver, int xOffset, int yOffset)
         {
        	 Actions act=new Actions(driver);
        	 act.moveByOffset(xOffset, yOffset).click().perform();
         }
         /**
          * This method s used to scroll vertically for 500 pixels 
          * @param driver
          */
         
         public void scrollActions(WebDriver driver)
         {
        	 JavascriptExecutor js =(JavascriptExecutor) driver;
        	 js.executeScript("window.scrollBy(0,500);, ", "");
         }
         
         /**
          * This method will scroll vertically until the element reference
          * @param driver
          * @param element
          */
         public void scrollActions(WebDriver driver, WebElement element)
         {
        	 JavascriptExecutor js= (JavascriptExecutor) driver;
        	 int y= element.getLocation().getY();
        	 js.executeScript("window.scrollBy(0, "+y+");", element);
        	 
        	 //JavascriptExecutor js= (JavascriptExecutor) driver;        	 
        	 //js.executeScript("argument[0].scrolLIntoView();", element);
         }
         
         /**
          * This method will accept the alert popup 
          * @param driver
          */
         public void acceptAlert(WebDriver driver)
         {
        	 driver.switchTo().alert().accept();
         }
         
         /**
          * This method will cancel the alert popup 
          * @param driver
          */
         public void cancelAlert(WebDriver driver)
         {
        	 driver.switchTo().alert().dismiss();
         }
         
         /**
          * This method will enter the text in prompt popup 
          * @param driver
          * @param text
          */
         public void sendTextToAlert(WebDriver driver, String text)
         {
        	 driver.switchTo().alert().sendKeys(text);
         }
         
         /**
          * This method will capture the alert message and return to the caller
          * @param driver
          * @return
          */
         public String getAlertText(WebDriver driver)
         {
        	return driver.switchTo().alert().getText();
         }
         
         /**
          * This method will switch to frame based on frame index
          * @param driver
          * @param index
          */
         public void handleFrame(WebDriver driver, int index) 
         {
			driver.switchTo().frame(index);
		}
         
         /**
          * This method will switch to frame based on framename or ID
          * @param driver
          * @param nameorID
          */
         public void handleFrame(WebDriver driver, String nameorID)
         {
        	 driver.switchTo().frame(nameorID);
         }
         
         /**
          * This method will switch to frame based on web element
          * @param driver
          * @param element
          */
         public void handleFrame (WebDriver driver, WebElement element)
         {
        	 driver.switchTo().frame(element);
         }
         
         /**
          * this method will switch the control back to the immediate parent
          * @param driver
          */
         public void switchToParentFrame(WebDriver driver)
         {
        	 driver.switchTo().parentFrame();
         }
         
         /**
          * This method will help to switch the control back to current page
          * @param driver
          */
         public void switchToDefaultPage(WebDriver driver)
         {
        	 driver.switchTo().defaultContent();
         }
         
         /**
          * This method will switch the selenium control from parent to child or
          * child to parent based on partial window title
          * @param driver
          * @param partialWinTitle
          */
         public void switchToWindow(WebDriver driver, String partialWinTitle)
         {
        	 //step 1 : capture all the window ids 
        	Set<String> allWindowIds = driver.getWindowHandles();
        	 
        	 //step 2 : iterate through individual id's 
        	for (String winID : allWindowIds)
        	{
        		 //step 3 : switch to each ID and capture each ID 
        		 String currentTitle = driver.switchTo().window(winID).getTitle();
        		
        		//step 4: compare the title with required reference
        		 if(currentTitle.contains(partialWinTitle))
        		 {
        			 break;
        		 }
        	}
        	 
         }
         
         /**
          * This method will take screenshot and return absolute path of it
          * @param driver
          * @param screenshotName
          * @return
          * @throws IOException
          */
         
         public String screenshot(WebDriver driver, String screenshotName) throws IOException
         {
        	 TakesScreenshot ts=(TakesScreenshot) driver;
        	 File src = ts.getScreenshotAs(OutputType.FILE);
        	 File dst = new File(".\\Screenshot\\"+screenshotName+".png");
        	 Files.copy(src, dst); // this class is from common IO tool
        	 
        	 return dst.getAbsolutePath(); //attach the screen shot to extent reports
         }
         
	}


