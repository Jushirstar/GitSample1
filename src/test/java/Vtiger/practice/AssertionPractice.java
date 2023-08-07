package Vtiger.practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionPractice {

	@Test
	public void sampleTest()
	{
		int a=1;
		int b=2;
		
		System.out.println("step 1");
		System.out.println("step 2");
		Assert.assertEquals(b,a); //fail //stops further execution 
		System.out.println("step 3");
		System.out.println("step 4");
	}
	
	@Test
	public void sampleTest1()
	{
		SoftAssert sa=new SoftAssert();
		int a=1; //expected
		int b=2; //actual
		
		System.out.println("step 1");
		sa.assertEquals(false, true); //fail // both should be true (continue execution)
		
		System.out.println("step 2");
		Assert.assertEquals(b, a); // fail
		
		System.out.println("step 3");
	}
}
