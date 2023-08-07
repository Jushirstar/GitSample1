package VTiger.GeneralUtilities;

import java.util.Date;
import java.util.Random;

/**
 * this class consists of all generic methods related to java
 * @author HP
 *
 */
public class JavaUtility {
	
	/**
	 * This method is used to generate the random number to use every time 
	 * @return random value
	 */
	public int getRandomNumber()
	{
		Random r = new Random();
		int ran = r.nextInt(1000);
		return ran;
	}
	
	public String getSystemDate()
	{
		Date d=new Date();
		String date = d.toString();
		return date;
	}
	
	public String getSystemDateInFormat()
	{
		Date d=new Date();
		String[] date = d.toString().split(" ");
		String CurrentDate = date[2];
		String Month = date[1];
		String year = date[5];
		String time = date[3].replace(":", "-");
		
		String DateInFormat=CurrentDate+" "+Month+"_"+year+"_"+time;
		return DateInFormat;
	}

}
