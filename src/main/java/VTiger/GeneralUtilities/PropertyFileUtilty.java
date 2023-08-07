package VTiger.GeneralUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyFileUtilty {
	/**
	 * This class consists of generic methods related to property file 
	 * @author Rashi
	 * @param args
	 * @return 
	 * @throws FileNotFoundException 
	 */
	
		public String getDataFromPropertyFile(String key) throws Throwable {
			/**
			 * this method read data from property file based on given key
			 * 
			 */
		
			FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
			Properties pro=new Properties();
			pro.load(fis);
			String value = pro.getProperty(key);
			return value;
			
		}
		

	}

