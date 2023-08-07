package VTiger.GeneralUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class provides implemention to the IRetryAnalyser interface 
 * @author HP
 *
 */

public class RetryAnalyserImplementation implements IRetryAnalyzer {
	
	int count=1;
	int retrycount=3;
	                                 //pass //fail 
	public boolean retry(ITestResult result)
	{
		while (count<=retrycount) //1<=3 //2<=3 //3<=3 //4<=3 fail
		{
			count++; //2 //3 //4
			return true; //retry //retry //retry
		}
		
		return false; //stop retry
	}

}
