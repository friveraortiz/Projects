package frl.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class DatabaseOperationsDatabase
{

   // Atributes for the ForensicReadyLoggerDatabase 
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
	   

   // Constructor of the Class
   public DatabaseOperationsDatabase()//1
   {
     
   }
	   
   // Methods of the Class
   private void configureDBParameters(String propFileName)//2
   {
      Properties prop = new Properties();
	  InputStream input = null;

	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(propFileName);

		 // Load the properties file
	     prop.load(input);

	     // Get the values from the properties file
	     this.dbUser = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 prop.getProperty("dbPort");
		 this.dbUrl = prop.getProperty("dbUrl");
		 this.dbDriver = prop.getProperty("dbDriver");

	  } 
	  catch (IOException ex) 
	  {
	     String errorMessage = ex.getMessage();
	     System.out.println("Error 5521: Occurred while loading the Configuration File. Error Message: " + errorMessage);
	  } 
	  finally 
	  {
	     if (input != null) 
		 {
	        try 
			{
		       input.close();
		    } 
			catch (IOException e) 
			{
		       String errorMessage = e.getMessage();
			   System.out.println("Error 5522: Occurred while closing the Configuration File. Error Message: " + errorMessage);
		    }
		  }
	   }

   }  
	
   // Connect to the ForensicReadyLogger Database
   public void connect(String propFileName) throws Exception //3
   {

      if (dbCon != null)
	  return;

      // Get the database parameters from the config.properties file
      configureDBParameters(propFileName);
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e) 
	  {
		 String errorMessage = e.getMessage(); 
	     throw new Exception("Error 5531: Occurred because the Database Driver was not found. Error Message: "+errorMessage);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
	  //System.out.println("Forensic Ready Logger Database Connected");
	}
	
    // Disconnect to the ForensicReadyLogger Database
	public void disconnect() //4
	{
	   if (dbCon != null) 
	   {
	      try 
		  {
		     dbCon.close();
		  } 
	      catch (SQLException e) 
		  {
	         String errorMessage = e.getMessage();   
		     System.out.println("Error 5541: Ocurred because the Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("Forensic Ready Logger Database Disconnected");
	 }

	
   public ArrayList<DatabaseMethod1> loadDatabaseMethods() throws SQLException //5
   {
      // Declare ArrayList of ClassMethodDetails
	  ArrayList<DatabaseMethod1> databaseMethodsList = new ArrayList<DatabaseMethod1>();	   

	  // SQL Statements      
	  String sql1 = "select dm_id, dm_name ";
	  sql1 = sql1 + "from database_method ";
	  sql1 = sql1 + "order by dm_name asc";
		  
	  PreparedStatement selectStatement = dbCon.prepareStatement(sql1);
	  ResultSet selectResults = selectStatement.executeQuery();
	  
	  while(selectResults.next()) 
	  {
		  
		 int id      = selectResults.getInt("dm_id"); 
		 String name = selectResults.getString("dm_name");

		 
		 DatabaseMethod1 databaseMethod = new DatabaseMethod1(id, name);
		 
	     // Add fields to the ArrayList.
		 databaseMethodsList.add(databaseMethod); 
							
      }
			
	  selectResults.close();
	  selectStatement.close();
	  
      return databaseMethodsList;

   }
   
   public ArrayList<String> loadDatabaseOperations(String programmingLanguage, String dBMS, String databaseMethod) throws SQLException //6 
   {
      // Declare ArrayList of DatabaseClass
	  ArrayList<String> databaseClassList = new ArrayList<String>();	
	  
	  // SQL Statements      
	  String sql1 = "select dc.dc_name ";
	  sql1 = sql1 + "from database_operation db, ";
	  sql1 = sql1 + "programming_language pl, ";
	  sql1 = sql1 +	"dbms ds, ";
	  sql1 = sql1 +	"database_class dc, ";
	  sql1 = sql1 + "database_method dm ";
	  sql1 = sql1 + "where ";
	  sql1 = sql1 + "pl.pl_name = ? and ";
	  sql1 = sql1 + "ds.dbms_name = ? and ";
	  sql1 = sql1 + "dm.dm_name = ? and ";
	  sql1 = sql1 + "db.pl_id = pl.pl_id and ";
	  sql1 = sql1 + "db.dbms_id = ds.dbms_id and ";
	  sql1 = sql1 + "db.dc_id = dc.dc_id and ";
	  sql1 = sql1 + "db.dm_id = dm.dm_id ";
	  sql1 = sql1 + "order by dm.dm_name";  
	  
	  PreparedStatement selectStatement = dbCon.prepareStatement(sql1);
	  selectStatement.setString(1, programmingLanguage);
	  selectStatement.setString(2, dBMS);
	  selectStatement.setString(3, databaseMethod);
	  ResultSet selectResults = selectStatement.executeQuery();
	  
	  
	  while(selectResults.next()) 
	  {
		  
		 String name = selectResults.getString("dc_name");

	     // Add fields to the ArrayList.
		 databaseClassList.add(name); 
							
      }
			
	  selectResults.close();
	  selectStatement.close();
	  
      return databaseClassList;

   }
   
   
}
