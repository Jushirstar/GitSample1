package Vtiger.practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TestNGPractice {
	
	@Test (priority = 2, invocationCount = 2)
	public void createContact()
	{
		System.out.println("contact created");
	}
	
	@Test (priority = 1)
	public void modifyContact()
	{
		//Assert.fail(); //purposefully fail the script
		System.out.println("contact modified");
	}
	
	@Test (invocationCount = 2)
	public void deleteContact()
	{
		System.out.println("contact deleted");
	}

}
