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


public class GraphicalUserInterfaceDatabase
{

   // Atributes for the ForensicReadyLoggerDatabase 
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
	   

   // Constructor of the Class
   public GraphicalUserInterfaceDatabase() //1
   {
     
   }
	   
   // Methods of the Class
   private void configureDBParameters(String propFileName) //2
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
	     System.out.println("Error XXXX: Occurred while loading the Configuration File. Error Message: " + errorMessage);
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
			   System.out.println("Error XXXX: Occurred while closing the Configuration File. Error Message: " + errorMessage);
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
	     throw new Exception("Error XXXX: Occurred because the Database Driver was not found. Error Message: "+errorMessage);
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
		     System.out.println("Error XXXX: Occurred because the Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("Forensic Ready Logger Database Disconnected");
	 }

	   
   public String loadOneGui(String programmingLanguage, String guiName) throws SQLException //5
   {
      String name = "";
	  String sql1;
	   
	  // SQL Statements      
	  sql1 = "select gui.gui_name ";
      sql1 = sql1 + "from graphical_user_interface gui, ";
      sql1 = sql1 + "programming_language pl ";
	  sql1 = sql1 + "where ";
	  sql1 = sql1 + "pl.pl_name = ? and ";
	  sql1 = sql1 + "gui.gui_name = ? and ";
	  sql1 = sql1 + "gui.pl_id  = pl.pl_id";

	  PreparedStatement selectStatement = dbCon.prepareStatement(sql1);
	  selectStatement.setString(1, programmingLanguage);
	  selectStatement.setString(2, guiName);
	  ResultSet selectResults = selectStatement.executeQuery();
		  
	  while(selectResults.next()) 
	  {
	     name = selectResults.getString("gui_name");	     						
	  }
				
	  selectResults.close();
	  selectStatement.close();
		  
	  return name;

   }
   
   public ArrayList<String> loadAllGui(String programmingLanguage) throws SQLException //6
   {
      String name = "", sql1;
      ArrayList<String> guiList = new ArrayList<String>();
	   
	  // SQL Statements      
	  sql1 = "select gui.gui_name ";
      sql1 = sql1 + "from graphical_user_interface gui, ";
      sql1 = sql1 + "programming_language pl ";
	  sql1 = sql1 + "where ";
	  sql1 = sql1 + "pl.pl_name = ? and ";
	  sql1 = sql1 + "gui.pl_id  = pl.pl_id";
	  
	  PreparedStatement selectStatement = dbCon.prepareStatement(sql1);
	  selectStatement.setString(1, programmingLanguage);
	  ResultSet selectResults = selectStatement.executeQuery();
		  
	  while(selectResults.next()) 
	  {
	     name = selectResults.getString("gui_name");
	     guiList.add(name);
	  }
				
	  selectResults.close();
	  selectStatement.close();
		  
	  return guiList;

   }
	
	
}
