package frl.controller.loadUMLSequenceDiagram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import frl.gui.loadUMLSequenceDiagram.UMLSequenceDiagramFormEvent;
import frl.model.configuration.ClassAttribute;
import frl.model.configuration.ClassMethod;
import frl.model.configuration.FRLConfiguration;
import frl.model.generateAspectOrientedFiles.ConfigFile;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.DataCategory;
import frl.model.loadUMLSequenceDiagram.DataType;
import frl.model.loadUMLSequenceDiagram.MethodDataSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodFileSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodParameterData;
import frl.model.loadUMLSequenceDiagram.MethodSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodStepSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ReturnTypeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ReturnTypeValueSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramDatabase;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramFinal;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramPlain;
import frl.model.loadUMLSequenceDiagram.UserFileSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UserSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ValueSequenceDiagram;

public class LoadUMLSequenceDiagramController 
{
	
   // Declare a new object for the ClassMethods Database class
   UMLSequenceDiagramDatabase umlSeqDiagramDb = new UMLSequenceDiagramDatabase();
   
   public void connect(String dbConfigFilePath) throws Exception //1
   {
      String errorMessage="";
      
      try 
      {	   
         umlSeqDiagramDb.connect(dbConfigFilePath);
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
	     umlSeqDiagramDb.disconnect();
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
	   
   }
   
   public void deleteUMLSequenceDiagramPlain(int projectId) throws Exception //3
   {
      String errorMessage="";
	      
	  try 
	  {	 	   
	     umlSeqDiagramDb.deleteUMLSequenceDiagramPlain(projectId);
	  }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
      	   
   }
   
   public void saveUMLSequenceDiagramPlain(UMLSequenceDiagramPlain umlSequenceDiagram) throws Exception //4
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.saveUMLSequenceDiagramPlain(umlSequenceDiagram);
	  }  
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }

   }
   
   public ArrayList<UMLSequenceDiagramPlain> loadUserRecordsPlain(int projectId) throws Exception
   {
      String errorMessage="";
	  ArrayList<UMLSequenceDiagramPlain> umlSeqDiagList = new ArrayList<UMLSequenceDiagramPlain>();
	  
      try 
	  {	 	
         umlSeqDiagList = umlSeqDiagramDb.loadUserRecordsPlain(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
      
      return umlSeqDiagList;
	   
   }
      
   public ArrayList<UserSequenceDiagram> loadUsersSequenceDiagram(int projectId) throws Exception
   {
      String errorMessage="";
      ArrayList<UserSequenceDiagram> userList = new ArrayList<UserSequenceDiagram>();

	  try 
	  {	 	
	     userList = umlSeqDiagramDb.loadUsersSequenceDiagram(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return userList;
      
   }  
   
   public ArrayList<UMLSequenceDiagramFinal> loadSequenceDiagramUser(int projectId, int userId) throws Exception
   {
      String errorMessage="";
      ArrayList<UMLSequenceDiagramFinal> umlSeqDiag = new ArrayList<UMLSequenceDiagramFinal>();
      
	  try 
	  {	 	
	     umlSeqDiag = umlSeqDiagramDb.loadSequenceDiagramUser(projectId, userId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return umlSeqDiag; 
      
   }  
   
   public ArrayList<MethodStepSequenceDiagram> loadMethodsSequenceDiagram(int projectId, int userId) throws Exception
   {
      String errorMessage="";
	  ArrayList<MethodStepSequenceDiagram> methodList = new ArrayList<MethodStepSequenceDiagram>();

	  try 
	  {	 	
	     methodList = umlSeqDiagramDb.loadMethodsSequenceDiagram(projectId, userId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		  
	  return methodList;
   }
   
   public ArrayList<UMLSequenceDiagramFinal> loadSequenceDiagramMethod(UMLSequenceDiagramFinal seqDiagramFinal) throws Exception
   {
      String errorMessage="";
	  ArrayList<UMLSequenceDiagramFinal> umlSeqDiag = new ArrayList<UMLSequenceDiagramFinal>();
	      
	  try 
	  {	 	
	     umlSeqDiag = umlSeqDiagramDb.loadSequenceDiagramMethod(seqDiagramFinal);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		  
	  return umlSeqDiag; 

   }     
   
   public ArrayList<UMLSequenceDiagramPlain> loadMethodRecordsPlain(int projectId) throws Exception
   {
      String errorMessage="";
      ArrayList<UMLSequenceDiagramPlain> umlSeqDiag = new ArrayList<UMLSequenceDiagramPlain>();
      
	  try 
	  {	 	
	     umlSeqDiag = umlSeqDiagramDb.loadMethodRecordsPlain(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return umlSeqDiag; 
      
   }   
   
   public void updateMethodDetailsPlain(int projectId, UMLSequenceDiagramPlain umlSeqDiagram) throws Exception
   {
      String errorMessage="";	   
	
      try 
	  {	 	
	     umlSeqDiagramDb.updateMethodDetailsPlain(projectId, umlSeqDiagram);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
   }
   
   public ClassMethod getClassMethodDetails1(int projectId, String methodName, String packageName, String className) throws Exception
   {
      String errorMessage="";
      ClassMethod classMethod;
		   
	  try 
	  {	 	
	     classMethod = umlSeqDiagramDb.getClassMethodDetails1(projectId, methodName, packageName, className);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
	  }
	  
	  return classMethod;
	   
   }
   
   public String getUserNamePlain(int projectId, int lineNumber) throws Exception
   {
      String errorMessage="", userName="";	
      
	  try 
	  {	 	
	     userName = umlSeqDiagramDb.getUserNamePlain(projectId, lineNumber);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return userName; 
	   
   }
   
   public void updateConnectionRecordsPlain(int projectId, int lineNumber, String userName) throws Exception
   {
      String errorMessage="";	
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.updateConnectionRecordsPlain(projectId, lineNumber, userName);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
  
   public void updateUserRecordsPlain(int projectId, int lineNumber, String userName, int userId) throws Exception
   {
      String errorMessage="";	
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.updateUserRecordsPlain(projectId, lineNumber, userName, userId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   
   public void saveUsersSequenceDiagram(int projectId) throws Exception
   {
      String errorMessage="";	
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.saveUsersSequenceDiagram(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		   	   
   }
   
   public void saveMethodsSequenceDiagram(int projectId) throws Exception
   {
      String errorMessage="";	
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.saveMethodsSequenceDiagram(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		   	   
   }   
   
   public void deleteUsersSequenceDiagram(int projectId) throws Exception
   {
      String errorMessage="";	
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.deleteUsersSequenceDiagram(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		   	   
   }    
   
   public void deleteMethodsSequenceDiagram(int projectId) throws Exception
   {
      String errorMessage="";	
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.deleteMethodsSequenceDiagram(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		   	   
   }  
   
   public ArrayList<UMLSequenceDiagramPlain> loadSequenceDiagramsPlain(int projectId) throws Exception
   {
      String errorMessage="";
      ArrayList<UMLSequenceDiagramPlain> umlSeqDiagList = new ArrayList<UMLSequenceDiagramPlain>();
      
	  try 
	  {	 	
		  umlSeqDiagList = umlSeqDiagramDb.loadSequenceDiagramsPlain(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  return umlSeqDiagList;
	  
   }
   
   public void saveUMLSequenceDiagramsFinal(UMLSequenceDiagramFinal umlSequenceDiagramFinal) throws Exception //6
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.saveUMLSequenceDiagramsFinal(umlSequenceDiagramFinal);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public void deleteSequenceDiagramsFinal(int projectId) throws Exception
   {
      String errorMessage="";	
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.deleteSequenceDiagramsFinal(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		   	   
   }     
   
   public ArrayList<MethodDataSequenceDiagram> getMethodSequenceDiagrams(int projectId) throws Exception
   {
      String errorMessage="";	
      ArrayList<MethodDataSequenceDiagram> methodSeqDiagram = new ArrayList<MethodDataSequenceDiagram>();
      
	  try 
	  {	 	
	     methodSeqDiagram = umlSeqDiagramDb.getMethodSequenceDiagrams(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return methodSeqDiagram; 
   }   
   
   public void saveParameterSequenceDiagram(ParameterSequenceDiagram parameterSeqDiagram) throws Exception //6
   {
      String errorMessage="";
      
      try 
	  {	 	
	     umlSeqDiagramDb.saveParameterSequenceDiagram(parameterSeqDiagram);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		  
   }
   
   public void deleteParametersSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.deleteParametersSequenceDiagram(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
   }
   
   public DataType getDataTypeDetails1(String programmingLanguage, String name) throws Exception
   {
      String errorMessage="";
      DataType dataType;
	      
	  try 
	  {	 	
	     dataType = umlSeqDiagramDb.getDataTypeDetails1(programmingLanguage, name);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
	  }
	  
	  return dataType;
   }
   
   
   public DataType getDataTypeDetails2(String programmingLanguage, String name, DataCategory dataCategory1) throws Exception
   {
      String errorMessage="";
      DataType dataType;
	      
	  try 
	  {	 	
	     dataType = umlSeqDiagramDb.getDataTypeDetails2(programmingLanguage, name, dataCategory1);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
	  }
	  
	  return dataType;
   }
   
   public ClassMethod getClassDetails(int projectId, String className, String value1, String value2) throws Exception
   {
      String errorMessage="";
      ClassMethod classMethod = null;
		      
	  try 
	  {	 	
	     classMethod = umlSeqDiagramDb.getClassDetails(projectId, className, value1, value2);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		  
	  return classMethod;
   }
   
   public void updateFileNamesUserSequenceDiagram(UserSequenceDiagram userSeqDiagram) throws Exception
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.updateFileNamesUserSequenceDiagram(userSeqDiagram);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public void updateFileNamesMethodSequenceDiagram(MethodSequenceDiagram methodSeqDiagram) throws Exception
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.updateFileNamesMethodSequenceDiagram(methodSeqDiagram);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }  
   }
   
   public ArrayList<UMLSequenceDiagramPlain> loadUniqueMethodsPlain(int projectId) throws Exception
   {
      String errorMessage="";
      ArrayList<UMLSequenceDiagramPlain> umlSeqDiagramPlain;
		      
	  try 
	  {	 	
		  umlSeqDiagramPlain = umlSeqDiagramDb.loadUniqueMethodsPlain(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  return umlSeqDiagramPlain;
   }
   
   public void updateIdentifierPlain(UMLSequenceDiagramPlain umlSeqDiagramPlain) throws Exception
   {
      String errorMessage="";
    	  
      try
      {
         umlSeqDiagramDb.updateIdentifierPlain(umlSeqDiagramPlain);
    	  
      }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public ArrayList<Integer> loadMethodIdsPlain(int projectId, int userId, String value) throws Exception
   {
      String errorMessage="";
      ArrayList<Integer> methodList;
      
      try
      {
         methodList = umlSeqDiagramDb.loadMethodIdsPlain(projectId, userId, value);
    	  
      }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
      
      return methodList;
	   
   }
   
   public ArrayList<UMLSequenceDiagramPlain> getMethodStepsPlain(int projectId, int userId, 
		                                                         int methodId, String value) throws Exception
   {      
      String errorMessage="";
      ArrayList<UMLSequenceDiagramPlain> methodSteps;
   
      try
      {
         methodSteps = umlSeqDiagramDb.getMethodStepsPlain(projectId, userId, methodId, value);
 	  
      }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
   
      return methodSteps;
	   
   }
   
   public void saveMethodStepSequenceDiagram(MethodStepSequenceDiagram methodSteps) throws Exception
   {      
      String errorMessage="";
      
      try
      {
         umlSeqDiagramDb.saveMethodStepSequenceDiagram(methodSteps);
      }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public void deleteMethodStepsSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage="";
	      
	  try
	  {
	     umlSeqDiagramDb.deleteMethodStepsSequenceDiagram(projectId);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public void updateMethodStepPlain(UMLSequenceDiagramPlain umlSeqDiagramPlain) throws Exception
   {
      String errorMessage="";
	      
	  try
	  {
	     umlSeqDiagramDb.updateMethodStepPlain(umlSeqDiagramPlain);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }	   
   }
   
   public void saveUserFileSequenceDiagram(UserFileSequenceDiagram userFiles) throws Exception
   {
      String errorMessage="";
	      
	  try
	  {
	     umlSeqDiagramDb.saveUserFileSequenceDiagram(userFiles);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }	  	   
	   
   }
   
   public void deleteUserFilesSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage="";
      
      try 
      {
         umlSeqDiagramDb.deleteUserFilesSequenceDiagram(projectId);
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
 		 throw new Exception(errorMessage);
      }
   }
   
   public void saveMethodFileSequenceDiagram(MethodFileSequenceDiagram methodFiles) throws Exception
   {
      String errorMessage="";
	      
	  try
	  {
	     umlSeqDiagramDb.saveMethodFileSequenceDiagram(methodFiles);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }	  	   
	   
   }
   
   public void deleteMethodFilesSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage="";
      
      try 
      {
         umlSeqDiagramDb.deleteMethodFilesSequenceDiagram(projectId);
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
 		 throw new Exception(errorMessage);
      }
   }
   
   public ArrayList<MethodParameterData> loadMethodsParametersData(int projectId, String value1, String value2) throws Exception
   {
      String errorMessage="";
      ArrayList<MethodParameterData> methodParameterList;
	      
	  try 
	  {
		  methodParameterList = umlSeqDiagramDb.loadMethodsParametersData(projectId, value1, value2);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	 	throw new Exception(errorMessage);
	  }
	  
	  return methodParameterList;
   }   
   
   public int getClassMethodId(ClassMethod classMethod) throws Exception
   {
	 String errorMessage="";  
     int cmId=0;
     
	  try 
	  {
	     cmId = umlSeqDiagramDb.getClassMethodId(classMethod);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	 	 throw new Exception(errorMessage);
	  }
     
	 return cmId;  
   }
   
   public ClassMethod getClassMethodDetails2(int projectId, String packageName, String className, String methodName ) throws Exception
   {
      String errorMessage="";
      ClassMethod classMethod;
		   
	  try 
	  {	 	
	     classMethod = umlSeqDiagramDb.getClassMethodDetails2(projectId, packageName, className, methodName);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
	  }
	  
	  return classMethod;
	   
   } 
   
   public int getAttributeId(int projectId, int methodId, int parameterId, int methodDataTypeId, int methodProgLangId) throws Exception
   {
      String errorMessage="";
      int attributeId=0;
			   
	  try 
	  {	 	
	     attributeId = umlSeqDiagramDb.getAttributeId(projectId, methodId, parameterId, methodDataTypeId, methodProgLangId);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return attributeId;	   
   }
   
   public void saveAttributeSequenceDiagram(AttributeSequenceDiagram attributeSeqDiagram) throws Exception //6
   {
      String errorMessage="";
			   
	  try 
	  {	 	
	     umlSeqDiagramDb.saveAttributeSequenceDiagram(attributeSeqDiagram);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public void deleteAttributesSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage="";
	      
	  try
	  {
	     umlSeqDiagramDb.deleteAttributesSequenceDiagram(projectId);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public ClassMethod getParentMethodDetails(int projectId, int cmId1, String methodName) throws Exception
   {
      String errorMessage="";
      ClassMethod classMethod = null;
		   
	  try 
	  {	 	
	     classMethod = umlSeqDiagramDb.getParentMethodDetails(projectId, cmId1, methodName);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }	
	  
	  return classMethod;
   }

   
   public List<ConfigFile> getConfigFiles()
   {
      return umlSeqDiagramDb.getConfigFiles();
   }
	
   public void loadProjects(String databaseConfigFile) throws Exception 
   {
      String errorMessage = "";
	   
	  // Connect to the Database
	  umlSeqDiagramDb.connect(databaseConfigFile);

	  try
	  {	 	
	     umlSeqDiagramDb.loadProjects();
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }	

      // Disconnect from the Database 
      umlSeqDiagramDb.disconnect();   
   }
   
   // Method #11
   public String validateEmptyFields(UMLSequenceDiagramFormEvent ev)
   {
	   
      String featuresConfigFile="", projectName="", os="", umlImageFile="", umlTextFile="",
    		 emptyFields="";
	  int projectId=0;
	  
	  featuresConfigFile = ev.getfeaturesConfigFile();
	  projectId          = ev.getProjectId();
	  projectName        = ev.getProjectName();
	  os                 = ev.getOperatingSystem();
	  umlImageFile       = ev.getUmlSeqDiagramImage();
	  umlTextFile        = ev.getUmlSeqDiagramText();
	  
	  // Validate if the fields are empty
	  
	  if(featuresConfigFile == null || featuresConfigFile.isEmpty() == true)
	     emptyFields = "Features Configuration File";
	  
	  if(projectId <= 0)
	  {	  
		 if(emptyFields.isEmpty() == true)	  
	        emptyFields = "Project Identifier";
		 else
			 emptyFields = emptyFields + ", Project Identifier";
	  }		 

	  if(projectName == null || projectName.isEmpty() == true)
	  {	  
		 if(emptyFields.isEmpty() == true)	  
	        emptyFields = "Project Name";
		 else
			 emptyFields = emptyFields + ", Project Name";
	  }		

	  if(os == null || os.isEmpty())
	  {	  
		 if(emptyFields.isEmpty() == true)	  
	        emptyFields = "Operating System";
		 else
			 emptyFields = emptyFields + ", Operating System";
	  }	
		  
	  if(umlImageFile == null || umlImageFile.isEmpty() == true)
	  {	  
		 if(emptyFields.isEmpty() == true)	  
	        emptyFields = "UML Sequence Diagram Image File";
		 else
			 emptyFields = emptyFields + ", UML Sequence Diagram Image File";
	  }	
	  
	  if(umlTextFile == null || umlTextFile.isEmpty() == true)
	  {	  
		 if(emptyFields.isEmpty() == true)	  
	        emptyFields = "UML Sequence Diagram Text File";
		 else
			 emptyFields = emptyFields + ", UML Sequence Diagram Text File";
	  }	
	  
      return emptyFields;	     
   } 
   
   public String validateUMLImageFile(String umlImageFile)
   {
	   
      String imageExt="", wrongExtFiles="";
	  
	  imageExt = FilenameUtils.getExtension(umlImageFile);
	  
	  if(!imageExt.equals("png"))
	     wrongExtFiles = umlImageFile;
	     
      return wrongExtFiles;
   }
   
   public String validateUMLTextFile(String umlTextFile)
   {
	   
      String textExt="", wrongExtFiles="";
	  
	  textExt = FilenameUtils.getExtension(umlTextFile);
	  
	  if(!textExt.equals("txt"))
		  wrongExtFiles = umlTextFile;
	  
      return wrongExtFiles;
   }
   
   public void saveValueSequenceDiagram(ValueSequenceDiagram valueSeqDiagram) throws Exception //6
   {
      String errorMessage="";
		   
	  try 
	  {	 	
	     umlSeqDiagramDb.saveValueSequenceDiagram(valueSeqDiagram);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
   }
   
   public void deleteValuesSequenceDiagram(int projectId) throws Exception //6
   {
      String errorMessage="";
		   
	  try 
	  {	 	
	     umlSeqDiagramDb.deleteValuesSequenceDiagram(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
   }
   
   
   public ArrayList<MethodDataSequenceDiagram> getMethodDetailsSequenceDiagram(int projectId) throws Exception
   {
      String errorMessage="";
      ArrayList<MethodDataSequenceDiagram> methodDetList;
      
      try
      {
         methodDetList = umlSeqDiagramDb.getMethodDetailsSequenceDiagram(projectId);
      }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
      
      return methodDetList;
	   
   }
   
   public void updateReturnTypeMethodSequenceDiagram(MethodDataSequenceDiagram methodDetSeqDiagram) throws Exception
   {
      String errorMessage=""; 
      
	  try
	  {
	     umlSeqDiagramDb.updateReturnTypeMethodSequenceDiagram(methodDetSeqDiagram);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public ClassMethod getClassMethodDetails3(MethodDataSequenceDiagram methodDataSeqDiagram) throws Exception
   {
      String errorMessage="";
      ClassMethod classMethod=null;
      
	  try
	  {
	     classMethod = umlSeqDiagramDb.getClassMethodDetails3(methodDataSeqDiagram);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  return classMethod;
   }
   
   public void saveReturnTypeValueSequenceDiagram(ReturnTypeValueSequenceDiagram returnTypeValueSeqDiagram) throws Exception
   {
      String errorMessage="";
      
	  try
	  {
	     umlSeqDiagramDb.saveReturnTypeValueSequenceDiagram(returnTypeValueSeqDiagram);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }

   }
   
   
   public void deleteReturnTypeValueSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage="";
      
	  try
	  {
	     umlSeqDiagramDb.deleteReturnTypeValueSequenceDiagram(projectId);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }

   }
   
   
   public int validateTextFiles(File directory) throws Exception
   {
		   
      String errorMessage1="", errorMessage2="";
      int count=0, i=0;
	  File[] files;

	  if (directory.exists() && directory.isDirectory()) 
	  {
	     files = directory.listFiles();
		 
	     try
		 {
		    for(i=0; i < files.length; i++)
		    {
		       if(files[i].isDirectory())
		          count += validateTextFiles(files[i]);
		       else 
		          if(files[i].getName().endsWith(".txt"))
		             count++;
		    }
		 }
		 catch(Exception e1)
		 {
		    errorMessage1 = e1.getMessage();
			errorMessage2 = "Error XXXX: Occurred while validating the Text Files from the Project Directory: " + System.lineSeparator() + directory + System.lineSeparator();
			errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			throw new Exception(errorMessage2);
	     }
	  }
	  else
	  {
	     errorMessage1 = errorMessage1 + "Error XXXX: Occurred while loading the Project Directory: " + System.lineSeparator() + directory + System.lineSeparator();
	     errorMessage1 = errorMessage1 + "which contains the generated Project Text Files by the Forensic-Ready Logger." + System.lineSeparator();
	     errorMessage1 = errorMessage1 + "Error Message: This Project Directory DOES NOT exist." + System.lineSeparator();
		 errorMessage1 = errorMessage1 + "You need to generate it FIRST in the Generate AOP Files Module.";
		 throw new Exception(errorMessage1); 
	  }

      return count;

   }
   
   public boolean validateFileName(String fileName, String expectedName)
   {
      boolean flag=false;
      String name="";
	   
      name = FilenameUtils.getName(fileName);
      
      if(name.equals(expectedName))
         flag = true;
      else
         flag = false;
    	  
      return flag;    
   }
   
   public ArrayList<Boolean> validateContentTextFile(String fileName, FRLConfiguration frlCon) throws Exception
   {
      boolean flag1=false, flag2=false, flag3=false, flag4=false, flag5=false;
      String errorMessage="", line="";
      ArrayList<Boolean> validContent = new ArrayList<Boolean>();

      try 
      {
          FileReader fReader = new FileReader(fileName);
          BufferedReader fileBuff = new BufferedReader(fReader);
          
          while ((line = fileBuff.readLine()) != null) 
          {
        	 // Validate the content of the UML Sequence Diagram Text File 
             if(line.contains(frlCon.startUMLSeqDiagram))
                flag1 = true; 
             else 
                if(line.contains(frlCon.endUMLSeqDiagram))
                   flag2 = true;
                else
                	if(line.contains(frlCon.startDivision))
                       flag3 = true;
                	else
                       if(line.contains(frlCon.space))
                          flag4 = true;
                       else
                           if(line.contains(frlCon.startSendMessage))
                              flag5 = true;
                    	   
          }
          
          fileBuff.close();
          
          validContent.add(flag1);
          validContent.add(flag2);
          validContent.add(flag3);
          validContent.add(flag4);
          validContent.add(flag5);
          
      } 
      catch (Exception e1) 
      {
         errorMessage = e1.getMessage();
         throw new Exception(errorMessage);
      }
      
	  return validContent;
	   
   }
   
   public int getClassInformation(int projectId, String packageName, String className) throws Exception
   {
      String errorMessage="";
      int id=0;
      
	  try
	  {
	     id = umlSeqDiagramDb.getClassInformation(projectId, packageName, className);
	  }
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  return id;

   } 
   
   public ArrayList<ClassAttribute> loadClassAttributes(int projectId, int classInfoId) throws Exception //5
   {
      String errorMessage="";
	  ArrayList<ClassAttribute> classAttributeList = new ArrayList<ClassAttribute>(); 
	  
	  try
	  {
	     classAttributeList = umlSeqDiagramDb.loadClassAttributes(projectId, classInfoId);
	  }
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return classAttributeList;   
   }    
   
   public ArrayList<AttributeSequenceDiagram> loadAttributeInformation(int projectId) throws Exception
   {
      String errorMessage="";
	  ArrayList<AttributeSequenceDiagram> attributeSeqDiagramList = new ArrayList<AttributeSequenceDiagram>(); 
	  
	  try
	  {
	     attributeSeqDiagramList = umlSeqDiagramDb.loadAttributeInformation(projectId);
	  }
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  
      return attributeSeqDiagramList;   
   }    
   
   public int getClassEnumFlag(int projectId, String packageName, String className) throws Exception
   {
	 String errorMessage="";  
     int enumFlag=0;
     
	  try 
	  {
	     enumFlag = umlSeqDiagramDb.getClassEnumFlag(projectId, packageName, className);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	 	 throw new Exception(errorMessage);
	  }
     
	 return enumFlag;  
   } 
   
   
   public void deleteReturnTypeSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.deleteReturnTypeSequenceDiagram(projectId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
   }   

   public void saveReturnTypeSequenceDiagram(ReturnTypeSequenceDiagram returnTypeSeqDiagram) throws Exception //6
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.saveReturnTypeSequenceDiagram(returnTypeSeqDiagram);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
   }  
   
   
   public void deleteReturnTypeAttributesSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.deleteReturnTypeAttributesSequenceDiagram(projectId);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
   }    
   
   public void deleteReturnTypeValuesSequenceDiagram_1(int projectId) throws Exception //5
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     umlSeqDiagramDb.deleteReturnTypeValuesSequenceDiagram_1(projectId);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
   }    
      
   public int getReturnType(String returnTypeName) throws Exception
   {
      String errorMessage="";
      int returnTypeId=0;
	      
	  try 
	  {	 	
		  returnTypeId = umlSeqDiagramDb.getReturnType(returnTypeName);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  return returnTypeId;
   }    
   
   public DataType getPrimitiveClassDetails(int projectId, String dataTypeName) throws Exception
   {
      String errorMessage="";
      DataType dataType=null;
	      
	  try 
	  {	 	
	     dataType = umlSeqDiagramDb.getPrimitiveClassDetails(projectId, dataTypeName);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return dataType;
   } 
   
   public int getDataTypeId(int projectId, String dataTypeName) throws Exception
   {
      String errorMessage="";
      int dataTypeId=0;
	      
	  try 
	  {	 	
	     dataTypeId = umlSeqDiagramDb.getDataTypeId(projectId, dataTypeName);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return dataTypeId;
   }     
}
