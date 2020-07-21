package controller;

import java.sql.SQLException;

import gui.ModuleFormEvent;
import model.ModuleDatabase;
import model.UserLevel;
import model.Module;


// This class is an intermediary between the GUI and the Model Classes
// Transform the data from the GUI in a way that the Model can understand it, because 
// Model is a Data Model to store data into a Database
public class ModuleController 
{
   // Declare a new object for the ModuleDatabase class
   ModuleDatabase modDb = new ModuleDatabase();

   public void saveModules() throws SQLException 
   {
      try 
	  {
	     modDb.connect();
	  } 
	  catch (Exception e) 
	  {
		 String errorMessage = e.getMessage();	
	     System.out.println("Error 08: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
	      
      modDb.saveModules();   
      modDb.disconnect();	
   }

   public String[] getModules(ModuleFormEvent m) throws SQLException
   {
	  Module module;
	   
	  // Get the values from the GUI
      String moduleName = m.getModuleName();	
	  String subModuleName = m.getModuleName();
      String userLevel = m.getUserLevel();
	  UserLevel userLevelCat = null;
	  String[] modules = new String[3];
      
	  // Define the possible values for UserLevel
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Manager")) 
	  {
	     userLevelCat = UserLevel.Manager;
	  }
	  else if(userLevel.equals("Employee")) 
	  {
	     userLevelCat = UserLevel.Employee;
	  }
	  
      module = new Module(moduleName, subModuleName, userLevelCat);
      
      try 
	  {
	     modDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 08: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      
      // Get the modules of the connected user
      modules = modDb.getModules(module);

      modDb.disconnect();

      return modules;	     
   } 
  
   public String[][] getSubModules(ModuleFormEvent m) throws SQLException
   {
	  Module module;
	   
	  // Get the values from the GUI
      String moduleName = m.getModuleName();	
	  String subModuleName = m.getModuleName();
      String userLevel = m.getUserLevel();
	  UserLevel userLevelCat = null;
	  String[][] subModules = new String[3][4];
      
	  // Define the possible values for the UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Manager")) 
	  {
	     userLevelCat = UserLevel.Manager;
	  }
	  else if(userLevel.equals("Employee")) 
	  {
	     userLevelCat = UserLevel.Employee;
	  }
	     
      module = new Module(moduleName, subModuleName, userLevelCat);
      
      
      try 
	  {
	     modDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 10: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      
      // Get the submodules of the connected user
      subModules = modDb.getSubModules(module);
      
      modDb.disconnect();
      
      return subModules;	     
   }   
   
}
