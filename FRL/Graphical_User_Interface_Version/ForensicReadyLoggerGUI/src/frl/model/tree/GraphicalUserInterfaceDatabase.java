package frl.model.tree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
   private void configureDBParameters(String propFileName) throws Exception
   {
	  String errorMessage1="", errorMessage2=""; 
      Properties prop = new Properties();
	  InputStream input = null;

	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(propFileName);

		 // Load the properties file
	     prop.load(input);

	     // Get the values from the properties file
	     this.dbUser     = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 this.dbUrl      = prop.getProperty("dbUrl");
		 this.dbDriver   = prop.getProperty("dbDriver");

	  } 
	  catch (IOException e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the Database Configuration File. \n";
	     errorMessage2 = "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  } 
	  finally 
	  {
	     if (input != null) 
		 {
	        try 
			{
		       input.close();
		    } 
			catch (IOException e2) 
			{
			   errorMessage1 = e2.getMessage();
			   errorMessage2 = "Error XXXX: Occurred while closing the Database Configuration File. \n";
			   errorMessage2 = "Error Message: " + errorMessage1;
			   throw new Exception(errorMessage2);
		    }
		  }
	   }

   }  
	
   // Connect to the ForensicReadyLogger Database
   public void connect(String propFileName) throws Exception //3
   {
      String errorMessage1="", errorMessage2="";  

      if (dbCon != null)
	  return;

      // Get the database parameters from the config.properties file
      try
      {
         configureDBParameters(propFileName);
      }
      catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e2) 
	  {
		 errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred because the Database Driver was not found. \n";
		 errorMessage2 = "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

	}
	
    // Disconnect to the ForensicReadyLogger Database
	public void disconnect() throws Exception
	{
	   String errorMessage1="", errorMessage2=""; 
	   
	   if (dbCon != null) 
	   {
	      try 
		  {
		     dbCon.close();
		  } 
	      catch (Exception e1) 
		  {
			 errorMessage1 = e1.getMessage();
			 errorMessage2 = "Error XXXX: Occurred because the Database Connection cannot be closed. \n";
			 errorMessage2 = "Error Message: " + errorMessage1;
			 throw new Exception(errorMessage2);
		  }
	   }
		
	   dbCon = null;
	   
	 }

   public String loadOneGui(int projectId, String programmingLanguage, String guiName) throws Exception //5
   {
      String errorMessage1="", errorMessage2="", name="", sql1="";
      PreparedStatement selectStatement;
      ResultSet selectResults;
      
      try
      {
	     // SQL Statements      
	     sql1 = "select gui.gui_name ";
         sql1 = sql1 + "from graphical_user_interface gui, ";
         sql1 = sql1 + "project_configuration pc, ";
         sql1 = sql1 + "programming_language_header plh ";
	     sql1 = sql1 + "where ";
	     sql1 = sql1 + "gui.pc_id = ? and ";
	     sql1 = sql1 + "plh.plh_name = ? and ";
	     sql1 = sql1 + "gui.gui_name = ? and ";
	     sql1 = sql1 + "gui.pc_id    = pc.pc_id";

	     selectStatement = dbCon.prepareStatement(sql1);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, programmingLanguage);
	     selectStatement.setString(3, guiName);
	     selectResults = selectStatement.executeQuery();
		  
	     while(selectResults.next()) 
	     {
	        name = selectResults.getString("gui_name");	     						
	     }
				
	     selectResults.close();
	     selectStatement.close();
      }
      catch(Exception e1)
      {
		 errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Graphical User Interface: " + guiName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Programming Language: " + programmingLanguage + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }		 
		  
	  return name;

   }
   
   public ArrayList<String> loadAllGui(int projectId, String programmingLanguage) throws Exception //6
   {
      String errorMessage1="", errorMessage2="", name="", sql1="";
      ArrayList<String> guiList = new ArrayList<String>();
      PreparedStatement selectStatement;
      ResultSet selectResults;
      
      try
      {
	     // SQL Statements      
	     sql1 = "select gui.gui_name ";
         sql1 = sql1 + "from graphical_user_interface gui, ";
         sql1 = sql1 + "project_configuration pc, ";
         sql1 = sql1 + "programming_language_header plh ";
	     sql1 = sql1 + "where ";
	     sql1 = sql1 + "gui.pc_id = ? and ";
	     sql1 = sql1 + "plh.plh_name = ? and ";
	     sql1 = sql1 + "gui.pc_id    = pc.pc_id";
	  
	     selectStatement = dbCon.prepareStatement(sql1);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, programmingLanguage);
	     selectResults = selectStatement.executeQuery();
		  
	     while(selectResults.next()) 
	     {
	        name = selectResults.getString("gui_name");
	        guiList.add(name);
	     }
				
	     selectResults.close();
	     selectStatement.close();
      }
      catch(Exception e1)
      {
 		 errorMessage1 = e1.getMessage();
 		 errorMessage2 = "Error XXXX: Occurred while loading the Graphical User Interfaces " + System.lineSeparator();
 		 errorMessage2 = errorMessage2 + "Programming Language: " + programmingLanguage + System.lineSeparator();
 		 errorMessage2 = errorMessage2 + "Project Identifier: " + projectId + System.lineSeparator();
 		 errorMessage2 = "Error Message: " + errorMessage1;
 		 throw new Exception(errorMessage2);
      }	
		  
	  return guiList;

   }
   
		
}