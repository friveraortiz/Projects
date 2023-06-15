package frl.controller.inputMethods;

import java.util.ArrayList;

import frl.model.configuration.ClassAttribute;
import frl.model.configuration.ClassMethod;
import frl.model.inputMethods.DatabaseMethod1;
import frl.model.inputMethods.DatabaseOperationsDatabase;

public class DatabaseOperationsController 
{
	
   // Declare a new object for the DatabaseOperationsDatabase class
   DatabaseOperationsDatabase databaseOperDb = new DatabaseOperationsDatabase();
   
   public void connect(String propFileName) throws Exception //1
   {
      databaseOperDb.connect(propFileName);	 
   }
   
   public void disconnect() throws Exception //2
   {
      databaseOperDb.disconnect(); 	   
   }
   
   public ArrayList<DatabaseMethod1> loadDatabaseMethods(int projectId) throws Exception //3
   {
      // Declare ArrayList of DatabaseMethod
	  ArrayList<DatabaseMethod1> dbMethodList = new ArrayList<DatabaseMethod1>(); 
	  
      dbMethodList = databaseOperDb.loadDatabaseMethods(projectId);
      	   
      return dbMethodList;   
   }
   
   public ArrayList<String> loadDatabaseOperations(int projectId, 
		                                           String programmingLanguage, 
		                                           String dBMS, 
		                                           String databaseMethod) throws Exception //4 
   {
      // Declare ArrayList of DatabaseClass
	  ArrayList<String> dbClassList = new ArrayList<String>(); 
	  
      dbClassList = databaseOperDb.loadDatabaseOperations(projectId, 
    		                                              programmingLanguage, 
    		                                              dBMS, 
    		                                              databaseMethod);
      	   
      return dbClassList;   
   }

   public ArrayList<ClassMethod> loadJavaFiles(int projectId) throws Exception
   {
      // Declare ArrayList of DatabaseMethod
	  ArrayList<ClassMethod> javaFileList = new ArrayList<ClassMethod>(); 
	  
	  javaFileList = databaseOperDb.loadJavaFiles(projectId);
      	   
      return javaFileList;   
   }  
  
   public ArrayList<ClassAttribute> loadClassAttributes(int projectId, int classInfoId) throws Exception //5
   {
      // Declare ArrayList of Attributes
	  ArrayList<ClassAttribute> classAttributeList = new ArrayList<ClassAttribute>(); 
	  
	  classAttributeList = databaseOperDb.loadClassAttributes(projectId, classInfoId);
      	   
      return classAttributeList;   
   }    
   
   public String loadDatabaseOperation(int projectId, 
		                               String programmingLanguage, 
		                               String dBMS, 
		                               String databaseMethod) throws Exception //4 
   {
      // Declare ArrayList of DatabaseClass
	  String dbClass=""; 
	  
      dbClass = databaseOperDb.loadDatabaseOperation(projectId, 
    		                                         programmingLanguage, 
    		                                         dBMS, 
    		                                         databaseMethod);
      	   
      return dbClass;   
   }
   
   public boolean validateDBClassName(int projectId, String className) throws Exception //5
   {
      String errorMessage="";
      boolean dbFlag=false;
	      
	  try 
	  {	 	   
	     dbFlag = databaseOperDb.validateDBClassName(projectId, className);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
	  
	  return dbFlag;
   	   
   }     

   public boolean validateDBClassNamePackage(int projectId, String packageName, String className) throws Exception //5
   {
      String errorMessage="";
      boolean dbFlag=false;
	      
	  try 
	  {	 	   
	     dbFlag = databaseOperDb.validateDBClassNamePackage(projectId, packageName, className);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
	  
	  return dbFlag;
	   
   }        

   public void updateMethodDBOperations(int projectId, int methodId, int dbCase) throws Exception //8
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
	     databaseOperDb.updateMethodDBOperations(projectId, methodId, dbCase);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
	   
   } 
   
   public boolean getDBClassFlag(int projectId) throws Exception //5
   {
      String errorMessage="";
      boolean dbFlag=false;
	      
	  try 
	  {	 	   
	     dbFlag = databaseOperDb.getDBClassFlag(projectId);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
	  
	  return dbFlag;
	   
   }      
   
}
