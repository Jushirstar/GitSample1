package Vtiger.practice;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteQuery {

	public static void main(String[] args) throws SQLException {
		
        //Get driver from MySql jar and register this in driver manager 
		Driver driverref= new Driver();
		
		//Step 1 :Register the driver	
		DriverManager.registerDriver(driverref);
		
		//Step 2 : Get connection with the driver - provide DB name 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientdb", "root", "rootpassword");
		
		//step 3 : issue create statement 
		Statement state = con.createStatement();
		
		//step 4 : Execute the query - provide table name 
		ResultSet result = state.executeQuery("select * from sampletable;");
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
		}
		
		//step 5 : close the data base
		con.close();
	}

}
