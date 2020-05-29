package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ModuleDatabase 
{
   // Atributes for the Module Database Class		
   private List<Module> modules;
   private String dbUser;
   private String dbPassword;
   private String dbPort;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   public ModuleDatabase()
   {
      modules = new LinkedList<Module>();
	   
   }
   // Methods of the Class
   
   public void configureDBParameters()
   {
      Properties prop = new Properties();
	  InputStream input = null;

	  try 
	  {
         // Specify the path of the configuration file
	     input = new FileInputStream("/Users/fannyriveraortiz/eclipse-workspace/HRMApplication/resources/config.properties");

	     // Load the properties file
		 prop.load(input);

		 // Get the values from the properties file
		 this.dbUser = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 this.dbPort = prop.getProperty("dbPort");
		 this.dbUrl = prop.getProperty("dbUrl");
		 this.dbDriver = prop.getProperty("dbDriver");

	   } 
	   catch (IOException ex) 
	   {
	      String errorMessage = ex.getMessage();
		  System.out.println("Error 70: Occurred while loading the Config File. Error Message: " + errorMessage);
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
				System.out.println("Error 71: Occurred while closing the Config File. Error Message: " + errorMessage);
			 }
		  }
	   }

   }  
   
   // Connect to the HRM Database
   public void connect() throws Exception 
   {

      if (dbCon != null)
	  return;

      // Get the database parameters from the config.properties file
      configureDBParameters();
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e) 
	  {
		 String errorMessage = e.getMessage(); 
	     throw new Exception("Error 72: The Database driver was not found. Error Message: "+errorMessage);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
	  //System.out.println("HRM Database Connected");
	}
	
    // Disconnect to the HRM Database
	public void disconnect() 
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
		     System.out.println("Error 73: The HRM Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("HRM Database Disconnected");
	 }
   
 
	public void saveModules() throws SQLException 
	{
	   int col = 1;
	   int moduleId;	
	   String moduleName;	
	   String subModuleName;
	   UserLevel userLevel;
		   
	   // SQL Statements
	   String deleteSql = "delete from modules";
	   PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	   deleteStatement.executeUpdate();
	   deleteStatement.close();
		
	   for (int x = 1; x <= 6; x++) 
	   {

   	      String insertSql = "insert into modules (module_id, module_name, submodule_name, user_level) ";
   	      insertSql = insertSql + "values (?, ?, ?, ?) ";
   	      PreparedStatement insertStatement = dbCon.prepareStatement(insertSql);
   	      
   	      col = 1;
   	   
  		  // Depending on the counter, build the insert statement
  		  switch(x) 
  		  {
  	         case 1 :
  	   		     moduleName = "Employees";	
  	   		     subModuleName =null;
  	   		     userLevel = UserLevel.Admin;
  	   		     moduleId = x;
  	   		     
  				 insertStatement.setInt(col++, moduleId);	
  		 		 insertStatement.setString(col++, moduleName);
  				 insertStatement.setString(col++, subModuleName);
  				 insertStatement.setString(col++, userLevel.name());
  				 
  	         break;
  	         
  	         case 2 :
  	   		     moduleName = "Employees";	
  	   		     subModuleName =null;
  	   		     userLevel = UserLevel.Manager;
  	   		     moduleId = x;
  	   		     
  				 insertStatement.setInt(col++, moduleId);
  		 		 insertStatement.setString(col++, moduleName);
  				 insertStatement.setString(col++, subModuleName);
  				 insertStatement.setString(col++, userLevel.name());
  			  
  	         break;
  	         
  	         case 3 :
  	   		     moduleName = "Users";	
  	   		     subModuleName =null;
  	   		     userLevel = UserLevel.Admin;
  	   		     moduleId = x;
  	   		     
  				 insertStatement.setInt(col++, moduleId);
  		 		 insertStatement.setString(col++, moduleName);
  				 insertStatement.setString(col++, subModuleName);
  				 insertStatement.setString(col++, userLevel.name());
  			  
  	         break;
  	         
  	         case 4 :
  	   		     moduleName = "Travel Requests";	
  	   		     subModuleName ="Change Status";
  	   		     userLevel = UserLevel.Admin;
  	   		     moduleId = x;
  	   		     
  				 insertStatement.setInt(col++, moduleId);
  		 		 insertStatement.setString(col++, moduleName);
  				 insertStatement.setString(col++, subModuleName);
  				 insertStatement.setString(col++, userLevel.name());
  			  
  	         break;  
  	         case 5 :
  	   		     moduleName = "Travel Requests";	
  	   		     subModuleName ="Change Status";
  	   		     userLevel = UserLevel.Manager;
  	   		     moduleId = x;
  	   		     
  				 insertStatement.setInt(col++, moduleId);
  		 		 insertStatement.setString(col++, moduleName);
  				 insertStatement.setString(col++, subModuleName);
  				 insertStatement.setString(col++, userLevel.name());
  			  
  	         break;  
  	         case 6 :
  	   		     moduleName = "Travel Requests";	
  	   		     subModuleName =null;
  	   		     userLevel = UserLevel.Employee;
  	   		     moduleId = x;
  	   		     
  				 insertStatement.setInt(col++, moduleId);
  		 		 insertStatement.setString(col++, moduleName);
  				 insertStatement.setString(col++, subModuleName);
  				 insertStatement.setString(col++, userLevel.name());
  			  
  	         break;  
  	   
  	         
  	      }	
		  
		  insertStatement.executeUpdate();
		  insertStatement.close();
		  
	   }
		   
	
	} 
	
	@SuppressWarnings("null")
	public String[] getModules(Module module) throws SQLException
	{
	   // Get the values from the GUI
	   UserLevel userLevel = module.getUserLevel(); 
	   String userLevelCat = userLevel.toString();
	   String[] modules = new String[3];
	   
	   // SQL Statements
	   String selectSql = "select module_name from modules where user_level=?";
	          		       
	   PreparedStatement selectStatement = dbCon.prepareStatement(selectSql);
	   selectStatement.setString(1, userLevelCat);
	   ResultSet selectResults = selectStatement.executeQuery();
	   	
	   int c = 0;
	   while (selectResults.next()) 
	   {
	      String moduleName = selectResults.getString(1);
	      
	      if(moduleName != null && !moduleName.isEmpty() && !moduleName.trim().isEmpty())
	         modules[c] = moduleName; 
	      else
	         modules[c] = "None"; 
	      
	      c++;  
	   }
	   
	   selectResults.close();
	   selectStatement.close();
	      
       return modules;
	}
	
	@SuppressWarnings("null")
	public String[][] getSubModules(Module module) throws SQLException
	{
	   
	   // Get the values from the GUI	
	   UserLevel userLevel = module.getUserLevel(); 
	   String userLevelCat = userLevel.toString();
	   String[][] subModules = new String[3][4];
	   
	   // SQL Statements
	   String selectSql1 = "select module_name from modules where user_level=?";
	          		       
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   selectStatement1.setString(1, userLevelCat);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	       
	   int i = 0;
	   while (selectResults1.next()) 
	   {
		  int j = 0;
		   
	      String moduleName = selectResults1.getString(1);
	      subModules[i][j] = moduleName; 
	      
	      String selectSql2 = "select distinct submodule_name from modules where module_name=?";
	             selectSql2 = selectSql2 + " and user_level =?";
		  PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
		  selectStatement2.setString(1, moduleName);
		  selectStatement2.setString(2, userLevelCat);
		  ResultSet selectResults2 = selectStatement2.executeQuery();
		  
		  j=0;
	      while (selectResults2.next()) 
		  {
	         String subModuleName = selectResults2.getString(1); 

		     if(subModuleName != null && !subModuleName.isEmpty() && !subModuleName.trim().isEmpty())
		     {
		    	 j++;
		         subModules[i][j] = subModuleName;
		     }   
		     
		  }
		  selectResults2.close();
		  selectStatement2.close();
	      
	      i++;  
	   }
	   
	   selectResults1.close();
	   selectStatement1.close();
	   
       return subModules;
	}
	
   
}
