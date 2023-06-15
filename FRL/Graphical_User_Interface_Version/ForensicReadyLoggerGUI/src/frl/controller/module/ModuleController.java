package frl.controller.module;

import frl.model.module.Module;
import frl.model.module.ModuleDatabase;
import frl.model.module.ModuleFormEvent;
import frl.model.user.UserLevel;


// This class is an intermediary between the GUI and the Model Classes
// Transform the data from the GUI in a way that the Model can understand it, because 
// Model is a Data Model to store data into a Database

// Package #1
// Class #1
public class ModuleController 
{
   // Declare a new object for the ModuleDatabase class
   ModuleDatabase modDb = new ModuleDatabase();
   
   // Method #1
   public void connect(String databaseConfigFile) throws Exception
   {
      String errorMessage="";
	   
	  try 
	  {
	      modDb.connect(databaseConfigFile);
	  } 
	  catch (Exception e) 
	  {
	     // Error #1 
		 errorMessage = e.getMessage();	
	     throw new Exception(errorMessage);	  
	  }
	   
	}
	  
   // Method #2
   public void saveModules(String databaseConfigFile) throws Exception 
   {
      String errorMessage = "";
      
      // Connect to the FRL Database
      try 
      {
	     connect(databaseConfigFile);
      }
      catch(Exception e)
      {
	     errorMessage = e.getMessage();
         throw new Exception(errorMessage);
      }
	  
      try
      {
         modDb.saveModules();
      }
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();	
	     throw new Exception(errorMessage);	  
	  }
      
      // Disconnect from the database
      try
      {
         modDb.disconnect();
      }
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();	
	     throw new Exception(errorMessage);	  
	  }
      
   }

   // Method #3
   public String[] getModules(ModuleFormEvent m, String databaseConfigFile) throws Exception
   {
	  Module module;
      String moduleName, userLevel, errorMessage = "";
	  UserLevel userLevelCat = null;
	  String[] modules = new String[3];
	   
	  // Get the values from the GUI
      moduleName = m.getModuleName();	
      userLevel = m.getUserLevel();
      errorMessage = "";
      
	  // Define the possible values for UserLevel
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else 
	     if(userLevel.equals("Security")) 
	     {
	        userLevelCat = UserLevel.Security;
	     }
	     else if(userLevel.equals("Developer")) 
	     {
	        userLevelCat = UserLevel.Developer;
	     }
	  

      module = new Module(moduleName, userLevelCat);
      
      // Connect to the FRL Database
      try 
      {
	     connect(databaseConfigFile);
      }
      catch(Exception e)
      {
	     errorMessage = e.getMessage();
         throw new Exception(errorMessage);
      }
      
      // Get the modules of the connected user
      try
      {
         modules = modDb.getModules(module);
      }
      catch(Exception e)
      {
	     errorMessage = e.getMessage();
         throw new Exception(errorMessage);
      }

      // Disconnect to the FRL Database
      try 
      {
    	  modDb.disconnect();
      }
      catch(Exception e)
      {
	     errorMessage = e.getMessage();
         throw new Exception(errorMessage);
      }

      return modules;	     
   } 
  
   
}
