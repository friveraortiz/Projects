package frl.controller;

import java.util.ArrayList;
import frl.model.DatabaseMethod1;
import frl.model.DatabaseOperationsDatabase;


public class DatabaseOperationsController 
{
	
   // Declare a new object for the DatabaseOperationsDatabase class
   DatabaseOperationsDatabase databaseOperDb = new DatabaseOperationsDatabase();
   
   public void connect(String propFileName) throws Exception //1
   {
      databaseOperDb.connect(propFileName);	 
   }
   
   public void disconnect() //2
   {
      databaseOperDb.disconnect(); 	   
   }
   
   public ArrayList<DatabaseMethod1> loadDatabaseMethods() throws Exception //3
   {
      // Declare ArrayList of DatabaseMethod
	  ArrayList<DatabaseMethod1> dbMethodList = new ArrayList<DatabaseMethod1>(); 
	  
      dbMethodList = databaseOperDb.loadDatabaseMethods();
      	   
      return dbMethodList;   
   }
   
   public ArrayList<String> loadDatabaseOperations(String programmingLanguage, String dBMS, String databaseMethod) throws Exception //4 
   {
      // Declare ArrayList of DatabaseClass
	  ArrayList<String> dbClassList = new ArrayList<String>(); 
	  
      dbClassList = databaseOperDb.loadDatabaseOperations(programmingLanguage, dBMS, databaseMethod);
      	   
      return dbClassList;   
   }
   	
}
