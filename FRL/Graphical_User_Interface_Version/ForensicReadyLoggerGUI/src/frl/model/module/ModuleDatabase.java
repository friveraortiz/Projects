package frl.model.module;

import java.io.*;
import java.sql.*;
import java.util.*;

import frl.model.user.UserLevel;

//Package #7
//Class #2
public class ModuleDatabase 
{
   // Atributes for the Module Database Class		
   private List<Module> modules;
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   // Method #1
   public ModuleDatabase()
   {
      modules = new LinkedList<Module>();
	   
   }
   
   // Methods of the Class
   // Method #2
   public void configureDBParameters(String databaseConfigFile) throws Exception
   {
      Properties prop = new Properties();
	  InputStream input = null;
	  String errorMessage1="",errorMessage2="";

	  try 
	  {
         // Specify the path of the configuration file
	     input = new FileInputStream(databaseConfigFile);
	     
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
		  // Error #1 
	      errorMessage1 = e1.getMessage();
	      errorMessage2 = "Error 7221: Occurred while loading the Database Configuration File. \n";
	      errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
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
		    	// Error #2 
			    errorMessage1 = e2.getMessage();
			    errorMessage2 = "Error 7222: Occurred while closing the Database Configuration File. \n";
			    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			    throw new Exception(errorMessage2);
			 }
		  }
	   }

   }  
   
   // Connect to the FRL Database
   // Method #3
   public void connect(String databaseConfigFile) throws Exception 
   {
       String errorMessage1="", errorMessage2="";	   

      if (dbCon != null)
	  return;

      // Get the database parameters from the frlconfig.properties file
      try 
      {
         configureDBParameters(databaseConfigFile);
      }
      catch(Exception e1)
      {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage2);
      }
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e2) 
	  {
		 errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error 7231: The Database Driver was not found. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

	}
	
    // Disconnect to the HRM Database
   // Method #4
   public void disconnect() throws Exception
   {
      String errorMessage1 ="", errorMessage2 =""; 	   
	   if (dbCon != null) 
	   {
	      try 
		  {
		     dbCon.close();
		  } 
	      catch (SQLException e1) 
		  {
	    	 errorMessage1 = e1.getMessage(); 
		     errorMessage2 = "Error 7241: The Database Connection cannot be closed. \n";
		     errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		     throw new Exception(errorMessage2);
		  }
	   }
		
	   dbCon = null;

   }
   
   // Method #5
   public void saveModules() throws Exception 
   {
	   int col = 1,x, moduleId;	
	   String moduleName, deleteSql, insertSql, errorMessage1="", errorMessage2="";
	   UserLevel userLevel;
	   PreparedStatement deleteStatement, insertStatement;
	   
	   try
	   {
	      // SQL Statements
	      deleteSql = "delete from module ";
	      deleteSql = deleteSql + "where mo_module_id >= 1";
	      deleteStatement = dbCon.prepareStatement(deleteSql);
	      deleteStatement.executeUpdate();
	      deleteStatement.close();

	      for (x = 1; x <= 7; x++) 
	      {

   	         insertSql = "insert into module (mo_module_id, mo_module_name, us_user_level) ";
   	         insertSql = insertSql + "values (?, ?, ?) ";
   	         insertStatement = dbCon.prepareStatement(insertSql);
   	      
   	         col = 1;
   	   
  		     // Depending on the counter, build the insert statement
  		     switch(x) 
  		     {
  		  
	            case 1 :
  	   		        moduleName = "Establish the Configuration";	
  	   		        userLevel  = UserLevel.Admin;
  	   		        moduleId   = x;
  	   		     
  	   		        insertStatement.setInt(col++, moduleId);
  	   		        insertStatement.setString(col++, moduleName);
  	   		        insertStatement.setString(col++, userLevel.name());
  	   		        break;
  	         
	            case 2 :
  	   		     	moduleName = "Generate AOP File";	
  	   		     	userLevel  = UserLevel.Admin;
  	   		     	moduleId   = x;
  	   		     
  	   		     	insertStatement.setInt(col++, moduleId);	
  	   		     	insertStatement.setString(col++, moduleName);
  	   		     	insertStatement.setString(col++, userLevel.name());
  	   		     	break;
  	         
	            case 3 :
  	   		     	moduleName = "Generate AOP File";	
  	   		     	userLevel  = UserLevel.Developer;
  	   		     	moduleId   = x;
  	   		     
  	   		     	insertStatement.setInt(col++, moduleId);
  	   		     	insertStatement.setString(col++, moduleName);
  	   		     	insertStatement.setString(col++, userLevel.name());
  	   		     	break;
  	         
	            case 4 :
  	   		     	moduleName = "Annotate Sequence Diagram";	
  	   		     	userLevel  = UserLevel.Admin;
  	   		     	moduleId   = x;
  	   		     
  	   		     	insertStatement.setInt(col++, moduleId);
  	   		     	insertStatement.setString(col++, moduleName);
  	   		     	insertStatement.setString(col++, userLevel.name());
  	   		     	break;  
  	   		     	
	            case 5 :
  	   		     	moduleName = "Annotate Sequence Diagram";	
  	   		     	userLevel  = UserLevel.Security;
  	   		     	moduleId   = x;
  	   		     
  	   		     	insertStatement.setInt(col++, moduleId);
  	   		     	insertStatement.setString(col++, moduleName);
  	   		     	insertStatement.setString(col++, userLevel.name());
  	   		     	break; 
  	   		     	
	            case 6 :
  	   		     	moduleName = "Generate Logging Instructions";	
  	   		     	userLevel  = UserLevel.Admin;
  	   		     	moduleId   = x;
  	   		     
  	   		     	insertStatement.setInt(col++, moduleId);
  	   		     	insertStatement.setString(col++, moduleName);
  	   		     	insertStatement.setString(col++, userLevel.name());
  	   		     	break;  
  	   		     	
	            case 7 :
  	   		     	moduleName = "Generate Logging Instructions";	
  	   		     	userLevel  = UserLevel.Security;
  	   		     	moduleId   = x;
  	   		     
  	   		     	insertStatement.setInt(col++, moduleId);
  	   		     	insertStatement.setString(col++, moduleName);
  	   		     	insertStatement.setString(col++, userLevel.name());
  	   		     	break;  
  		     }	
		  
  		     insertStatement.executeUpdate();
  		     insertStatement.close();
	      }
	      
	   }
	   catch (Exception e1) 
	   {
	      errorMessage1 = e1.getMessage(); 
		  errorMessage2 = "Error XXXX: While saving the Modules. \n";
		  errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	   
   } 
	
   // Method #6
   public String[] getModules(Module module) throws Exception
   {
	   // Get the values from the GUI
	   UserLevel userLevel; 
	   String userLevelCat, selectSql, errorMessage1="", errorMessage2="", moduleName;
	   String[] modules = new String[4];
	   PreparedStatement selectStatement;
	   ResultSet selectResults;
	   int c;
	   
	   userLevel = module.getUserLevel(); 
	   userLevelCat = userLevel.toString();
	   
	   try
	   {
	      // SQL Statements
	      selectSql = "select mo_module_name from module where us_user_level=?";
	          		       
	      selectStatement = dbCon.prepareStatement(selectSql);
	      selectStatement.setString(1, userLevelCat);
	      selectResults = selectStatement.executeQuery();
	   	
	      c = 0;
	      while (selectResults.next()) 
	      {
	         moduleName = selectResults.getString(1);
	      
	         if(moduleName != null && !moduleName.isEmpty() && !moduleName.trim().isEmpty())
	            modules[c] = moduleName; 
	         else
	            modules[c] = "None"; 
	      
	         c++;  
	      }
	   
	      selectResults.close();
	      selectStatement.close();
	   }
	   catch (Exception e1) 
	   {
	      errorMessage1 = e1.getMessage(); 
		  errorMessage2 = "Error XXXX: While loading the Modules. \n";
		  errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	      
       return modules;
   }
		
}
