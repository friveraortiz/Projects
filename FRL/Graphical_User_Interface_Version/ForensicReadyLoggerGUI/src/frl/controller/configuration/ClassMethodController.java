package frl.controller.configuration;

import java.util.ArrayList;

import frl.model.configuration.ClassAttribute;
import frl.model.configuration.ClassInformation;
import frl.model.configuration.ClassMethod;
import frl.model.configuration.ClassMethodDatabase;
import frl.model.configuration.SourceDirectory;


public class ClassMethodController 
{
	
   // Declare a new object for the ClassMethods Database class
   ClassMethodDatabase classMetDb = new ClassMethodDatabase();
   
   public void connect(String propFileName) throws Exception //1
   {
      String errorMessage="";
      
      try 
      {	   
	     classMetDb.connect(propFileName);
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
   }
   
   public void disconnect() throws Exception  //2
   {
      String errorMessage="";
	      
	  try 
	  {	  
         classMetDb.disconnect();
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
	   
   }
   
   public void deleteClassMethod(int projectId) throws Exception //3
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
         classMetDb.deleteClassMethod(projectId);
	  }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
      	   
   }
   
   public void saveClassMethod(ClassMethod classMethod) throws Exception //4
   {
      String errorMessage="";
	      
	  try 
	  {	 	
         classMetDb.saveClassMethod(classMethod);
	  }  
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }

   }
   
   public ArrayList<ClassMethod> load(String inputClassName, int projectId) throws Exception //5
   {
      String errorMessage="";	   
      // Declare ArrayList of ClassMethod
	  ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
	  
	  try
	  {
         classMetList = classMetDb.load(inputClassName, projectId);
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
      	   
      return classMetList;   
   }
   
   public void updateEndLineNumbers(int classMethodId, int endLineNumber, int projectId) throws Exception //6
   {
      String errorMessage="";	   
	  
      try
      {
         classMetDb.updateEndLineNumbers(classMethodId, endLineNumber, projectId);
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }

   }
   
   public ArrayList<ClassMethod> loadZeroEndLineNumbers(int projectId) throws Exception //7
   {
	  String errorMessage="";
      // Declare ArrayList of ClassMethod
	  ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
	  
	  try
	  {
         classMetList = classMetDb.loadZeroEndLineNumbers(projectId);
	  }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
      	   
      return classMetList;   
   }
   
   public void updateTextFileName(int classMethodId, String textFileName, int projectId) throws Exception //8
   {
	  String errorMessage="";
	  
	  try
	  {
         classMetDb.updateTextFileName(classMethodId, textFileName, projectId);
	  }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }

   }
   
   
   public ClassMethod loadMethodNameDetails_1(int projectId, String methodName) throws Exception //11
   {
	   ClassMethod cm;
	   String errorMessage="";
	   
	   try
	   {
	      cm = classMetDb.loadMethodNameDetails_1(projectId, methodName);
	   }   
	   catch(Exception e)
	   {
	      errorMessage = e.getMessage();
		  throw new Exception(errorMessage);
	   }
	   
	   return cm;
   }
   
   public ClassMethod loadMethodNameDetails_2(String packageName1, String className1, 
		                                   String shortMethodName1, int projectId) throws Exception //11
   {
      ClassMethod cm;
      String errorMessage;
	  
      try
      {
	     cm = classMetDb.loadMethodNameDetails_2(packageName1, className1, shortMethodName1, projectId);
      }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
	  return cm;
   }
   
   public void updateDatabaseMethod(int projectId, int classMethodId) throws Exception //8
   {
      classMetDb.updateDatabaseMethod(projectId, classMethodId);
   }
   
   public ArrayList<ClassMethod> loadJavaFiles(int projectId) throws Exception //7
   {
      String errorMessage="";	   
      // Declare ArrayList of ClassMethod
	  ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
	  
	  try
	  {
         classMetList = classMetDb.loadJavaFiles(projectId);
	  }   
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
      	   
      return classMetList;   
   }   
     
   public void updateStartLineNumbers(int projectId, int classMethodId, 
                                      int startLineNumber) throws Exception
   {
	  String errorMessage="";
	  
	  try
	  {
         classMetDb.updateStartLineNumbers(projectId, classMethodId, startLineNumber);
	  }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }

   } 
   
   
   public void updateClassInformation(int projectId, String packageName, String className, 
                                      /*int classDataFlag, String classDataType,*/ int cdId) throws Exception //8
   {
      String errorMessage="";
	  
	  try
	  {
         classMetDb.updateClassInformation(projectId, packageName, className, /*classDataFlag, classDataType,*/ cdId);
	  }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
     }

   }    

   public void saveClassAttribute(ClassAttribute classAttribute) throws Exception //6
   {
      String errorMessage="";
	  
	  try
	  {
         classMetDb.saveClassAttribute(classAttribute);
	  }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
     }

   }  
   
   public void deleteClassAttribute(int projectId) throws Exception //3
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
         classMetDb.deleteClassAttribute(projectId);  
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
      	   
   }

   public ArrayList<ClassMethod> loadIncompleteParameterMethods(int projectId) throws Exception //7
   {
      String errorMessage="";	   
      // Declare ArrayList of ClassMethod
	  ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
	  
	  try
	  {
         classMetList = classMetDb.loadIncompleteParameterMethods(projectId);
	  }   
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
      	   
      return classMetList;   
   }   
   
   public void updateParameterMethodName(int projectId, int classMethodId, String parameterMethodName) throws Exception //8
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
         classMetDb.updateParameterMethodName(projectId, classMethodId, parameterMethodName);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
      	   
   }   

   public int saveClassInformation(ClassInformation classInformation) throws Exception //6
   {
      String errorMessage="";
      int id=0;
	     
	  try 
	  {	 	   
         id = classMetDb.saveClassInformation(classInformation);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
	  
	  return id;
      	   
   }  
 
   public void deleteClassInformation(int projectId) throws Exception //3
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
         classMetDb.deleteClassInformation(projectId);  
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
   	   
   }   

   public void updateInterfaceFlag(int projectId, int classInfoId, int interfaceFlag) throws Exception //8
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
         classMetDb.updateInterfaceFlag(projectId, classInfoId, interfaceFlag);
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
	   
   }     
   
   public void saveSourceDirectory(SourceDirectory sourceDirectory) throws Exception  //6
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
         classMetDb.saveSourceDirectory(sourceDirectory);
      }
      catch(Exception  e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
	   
   } 

   public void deleteSourceDirectories(int projectId) throws Exception //5
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
         classMetDb.deleteSourceDirectories(projectId);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
	   
   }   
   
   public ArrayList<String> loadGUIProject(int projectId, String programmingLanguage) throws Exception //6
   {
      String errorMessage="";
	  ArrayList<String> guiList = new ArrayList<String>(); 
      
      guiList.clear();
	      
	  try 
	  {	 	   
		  guiList = classMetDb.loadGUIProject(projectId, programmingLanguage);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
	  
	  return guiList;
	   
   } 	
}
