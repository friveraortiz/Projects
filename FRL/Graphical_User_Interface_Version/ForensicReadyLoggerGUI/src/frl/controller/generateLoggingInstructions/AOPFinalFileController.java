package frl.controller.generateLoggingInstructions;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;

import frl.gui.generateLoggingInstructions.AOPFinalFileFormEvent;
import frl.model.configuration.ClassMethod;
import frl.model.configureAspectOrientedFiles.TextFileDetails;
import frl.model.configureAspectOrientedFiles.TextFileHeader;
import frl.model.configureAspectOrientedFiles.TextFileProperties;
import frl.model.generateAspectOrientedFiles.ConfigFile;
import frl.model.generateAspectOrientedFiles.DBMS;
import frl.model.generateAspectOrientedFiles.InputMethod;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageDetail;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageHeader;
import frl.model.generateLoggingInstructions.AOPFinalFileDatabase;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterDataSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramFinal;
import frl.model.loadUMLSequenceDiagram.UserSequenceDiagram;

public class AOPFinalFileController 
{
   // Declare a new object for the Database class
   AOPFinalFileDatabase aopFinalFileDb = new AOPFinalFileDatabase();
   
   public List<ConfigFile> getConfigFiles()
   {
      return aopFinalFileDb.getConfigFiles();
   }
   
   public void loadProjects(String databaseConfigFile) throws Exception 
   {
      String errorMessage = "";
	   
	  // Connect to the Database
      aopFinalFileDb.connect(databaseConfigFile);

	  try
	  {	 	
		  aopFinalFileDb.loadProjects();
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }	

      // Disconnect from the Database 
	  aopFinalFileDb.disconnect();   
   }   
	
   public String validateEmptyFields(AOPFinalFileFormEvent ev)
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
	   
   public String validateUMLImageFile(AOPFinalFileFormEvent ev)
   {
		   
      String umlImageFile="", imageExt="", wrongExtFiles="";
		  
	  umlImageFile = ev.getUmlSeqDiagramImage();
		  
	  imageExt = FilenameUtils.getExtension(umlImageFile);
		  
	  if(!imageExt.equals("png"))
	     wrongExtFiles = umlImageFile;
		  
		     
      return wrongExtFiles;
   }
	   
   public String validateUMLTextFile(AOPFinalFileFormEvent ev)
   {
		   
      String umlTextFile="", textExt="", wrongExtFiles="";
		  
	  umlTextFile = ev.getUmlSeqDiagramText();
		  
	  textExt = FilenameUtils.getExtension(umlTextFile);
		  
	  if(!textExt.equals("txt"))
	     wrongExtFiles = umlTextFile;
		  
		     
	  return wrongExtFiles;
   }
   
   public boolean validateAnnotationsFile(String seqDiagramTextFileName) throws Exception
   {
	  String line="", string1="[#purple]>", string2="note left of", string3="#lightpink",
			 errorMessage1="", errorMessage2="";
	  boolean flag=false;
      File file;
      
      file = new File(seqDiagramTextFileName);
      
	  try 
	  {
	     Scanner scanner = new Scanner(file);
	       
	     while (scanner.hasNextLine() && flag == false) 
	     {
	        line = scanner.nextLine();

	        if(line.contains(string1))
		       flag = true; 
	        else
	           if(line.contains(string2) && line.contains(string3)) 
	              flag = true;
	           else
	              flag = false;
	     }
	     scanner.close();
	   } 
	   catch(Exception e1) 
	   { 
	      errorMessage1 = e1.getMessage();
	 	  errorMessage2 = "Error XXXX: Occurred while loading the UML Sequence Diagram Text File: " + seqDiagramTextFileName + System.lineSeparator();
	 	  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	 	  throw new Exception(errorMessage2);	
	   }
	   return flag; 
   }
   
   public boolean validateUMLFileNames(String umlSeqDiagramTextFileName, String umlSeqDiagramImageFileName) throws Exception
   {
      boolean flag=false;
      
      if (umlSeqDiagramTextFileName.indexOf(".") > 0)
	     umlSeqDiagramTextFileName = umlSeqDiagramTextFileName.substring(0, umlSeqDiagramTextFileName.lastIndexOf("."));
      
      if (umlSeqDiagramImageFileName.indexOf(".") > 0)
	     umlSeqDiagramImageFileName = umlSeqDiagramImageFileName.substring(0, umlSeqDiagramImageFileName.lastIndexOf("."));
      
	  if(umlSeqDiagramTextFileName.equals(umlSeqDiagramImageFileName))
	     flag = true;
	  else
	     flag = false;	  
		  
	  return flag;
   }
   
	public List<ProgrammingLanguageDetail> getProgLangDet()
	{
		return aopFinalFileDb.getProgLangDet();
	}   
	
	  
	public ArrayList<ProgrammingLanguageHeader> loadProgLanguagesHeader(String databaseConfigFile) throws Exception
	{
	   String errorMessage1="";
	   ArrayList<ProgrammingLanguageHeader> pL;
	 
	   // Connect to the Database
	   try 
	   {
	      aopFinalFileDb.connect(databaseConfigFile);
	   }
	   catch (Exception e1) 
	   {
	      errorMessage1 = e1.getMessage();	
		  throw new Exception(errorMessage1);
	   }  
		      
	   // Load the programming languages
	   pL = aopFinalFileDb.loadProgLanguagesHeader();
		  
	   // Disconnect from the Database
	   try 
	   {
	      aopFinalFileDb.disconnect();
	   }
	   catch (Exception e2) 
	   {
	      errorMessage1 = e2.getMessage();	
		  throw new Exception(errorMessage1);
	   }  
  
	   return pL;
   }

   public ArrayList<DBMS> loadDBMS(String databaseConfigFile) throws Exception
   {
      String errorMessage1="";
	  ArrayList<DBMS> dB;
	  
      // Connect to the Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	      
      // Loading the DBMS
	  try 
	  {
	     dB = aopFinalFileDb.loadDBMS();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  
	  
	  // Disconnect from the Database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  
      return dB;
   }

   public ArrayList<InputMethod> loadInputMethods(String databaseConfigFile) throws Exception
   {
      String errorMessage1="", errorMessage2="";
	  ArrayList<InputMethod> iM;

	  // Connects to the Database
	  try 
	  {
		  aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Load the Input Methods
	  try 
	  {
		iM = aopFinalFileDb.loadInputMethods();
	  } 
	  catch (SQLException e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception(errorMessage2);
	  }
	  
	  // Disconnect from the Database
	  try 
	  {
		  aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  
      return iM;
   }	

   // Method #9
   public void loadProgLangDet(String databaseConfigFile, String progLangName) throws Exception
   {
      String errorMessage1 ="";
   
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the programming languages
	  try 
	  {
	     aopFinalFileDb.loadProgLangDet(progLangName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  	 
   }   
   
   public ArrayList<UserSequenceDiagram> loadUsers(String databaseConfigFile, int projectId) throws Exception
   {
      String errorMessage1 ="";
      ArrayList<UserSequenceDiagram> userList = new ArrayList<UserSequenceDiagram>();
   
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the ArrayList
	  try 
	  {
	     userList = aopFinalFileDb.loadUsers(projectId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }
	  
	  return userList;
	  	 
   }     
   
   public ArrayList<MethodSequenceDiagram> loadMethods(String databaseConfigFile, int projectId, int userId) throws Exception
   {
      String errorMessage1 ="";
      ArrayList<MethodSequenceDiagram> methodList = new ArrayList<MethodSequenceDiagram>();
   
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
	  // Loading the ArrayList
	  try 
	  {
	     methodList = aopFinalFileDb.loadMethods(projectId, userId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }
	  
	  return methodList;
	  	 
   }  
   
   public ArrayList<ParameterSequenceDiagram> loadParameters(String databaseConfigFile, int projectId, int progrLanguageId, int methodId) throws Exception
   {
      String errorMessage1 ="";
      ArrayList<ParameterSequenceDiagram> parameterList = new ArrayList<ParameterSequenceDiagram>();
   
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the ArrayList
	  try 
	  {
	     parameterList = aopFinalFileDb.loadParameters(projectId, progrLanguageId, methodId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }
	  
	  return parameterList;
	  	 
   }   
   
   public ArrayList<AttributeSequenceDiagram> loadAttributes(String databaseConfigFile, int projectId, int progrLanguageId, int methodId, 
		                                                     int parameterId, int parameterDataTypeId) throws Exception
   {
      String errorMessage1 ="";
      ArrayList<AttributeSequenceDiagram> attributeList = new ArrayList<AttributeSequenceDiagram>();
   
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the ArrayList
	  try 
	  {
	     attributeList = aopFinalFileDb.loadAttributes(projectId, progrLanguageId, methodId, parameterId, parameterDataTypeId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }
	  
	  return attributeList;
	  	 
   }    
   
   public int getProgrammingLanguageId(String databaseConfigFile, String progrLanguageName) throws Exception
   {
	  int progrLanguageId=0;  
      String errorMessage1 ="";
   
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the programming languages
	  try 
	  {
	     progrLanguageId = aopFinalFileDb.getProgrammingLanguageId(progrLanguageName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
	  
	  return progrLanguageId;
	  	 
   }     
   
   public int getUserId(String databaseConfigFile, int projectId, String userName) throws Exception
   {
	  int userId=0;  
      String errorMessage1 ="";
   
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the User Identifier
	  try 
	  {
	     userId = aopFinalFileDb.getUserId(projectId, userName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
	  
	  return userId;
	  	 
   }       
     
   public int getMethodId(String databaseConfigFile, int projectId, int userId, String methodName) throws Exception
   {
	  int methodId=0;  
      String errorMessage1 ="";
   
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the Method Identifier
	  try 
	  {
	     methodId = aopFinalFileDb.getMethodId(projectId, userId, methodName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
	  
	  return methodId;
	  	 
   }      
   
   public ParameterDataSequenceDiagram getParameterDetails(String databaseConfigFile, int projectId, int progrLanguageId, int methodId, String parameterFullName) throws Exception
   {
      String errorMessage1 ="";
	  ParameterDataSequenceDiagram parameterDataSequenceDiagramRecord = null;
 
      // Connect to the FRL Database
	  try 
	  {
	     aopFinalFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the Parameter Details
	  try 
	  {
	     parameterDataSequenceDiagramRecord = aopFinalFileDb.getParameterDetails(projectId, progrLanguageId, methodId, parameterFullName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFinalFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
	  
	  return parameterDataSequenceDiagramRecord;
	  	 
   }  
   
  public int getAttributeId(String databaseConfigFile, int projectId, int progrLanguageId, int methodId, int parameterId, 
		                    int parameterDataTypeId, String attributeFullName) throws Exception
  {
	 int attributeId=0;  
     String errorMessage1 ="";
  
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Loading the Attribute Identifier
	 try 
	 {
	    attributeId = aopFinalFileDb.getAttributeId(projectId, progrLanguageId, methodId, parameterId, parameterDataTypeId, attributeFullName);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	     aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	 } 
	  
	 return attributeId;
	  	 
  }    
  
  public ArrayList<UMLSequenceDiagramFinal> loadAnnotations(String databaseConfigFile, int projectId) throws Exception
  {
     String errorMessage1 ="";
	 ArrayList<UMLSequenceDiagramFinal> umlSeqDiagramFinalList = new ArrayList<UMLSequenceDiagramFinal>();	
  
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Loading the Annotations
	 try 
	 {
	    umlSeqDiagramFinalList = aopFinalFileDb.loadAnnotations(projectId);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	    aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	    errorMessage1 = e3.getMessage();	
		throw new Exception(errorMessage1);
	 } 
	  
	 return umlSeqDiagramFinalList;  	 
  }  
  
  public void updateTextFileProperties(String databaseConfigFile, int projectId, int headerId, String annotationFile) throws Exception
  {
     String errorMessage1 ="";
 
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Update the textFileProperties 
	 try 
	 {
	    aopFinalFileDb.updateTextFileProperties(projectId, headerId, annotationFile);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	     aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	 } 
	  	 
  }
  
  public TextFileHeader loadAOPFileHeader(String databaseConfigFile, int headerId) throws Exception
  {  
     String errorMessage1 ="";
     TextFileHeader textFileHeaderRecord = null;
  
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Loading the AOP Header
	 try 
	 {
	    textFileHeaderRecord = aopFinalFileDb.loadAOPFileHeader(headerId);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	    aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	    errorMessage1 = e3.getMessage();	
		throw new Exception(errorMessage1);
	 } 
	  
	 return textFileHeaderRecord;  	 
  }   
  
  public ArrayList<TextFileDetails> loadAOPFileDetails(String databaseConfigFile, int headerId) throws Exception
  {  
     String errorMessage1 ="";
     ArrayList<TextFileDetails> textFileDetailsList = new ArrayList<TextFileDetails>();
  
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Loading the AOP Details
	 try 
	 {
	    textFileDetailsList = aopFinalFileDb.loadAOPFileDetails(headerId);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	    aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	    errorMessage1 = e3.getMessage();	
		throw new Exception(errorMessage1);
	 } 
	  
	 return textFileDetailsList;  	 
  } 
  
  public TextFileProperties loadAOPFileProperties(String databaseConfigFile, int projectId, int headerId, int propertyId) throws Exception
  {  
     String errorMessage1 ="";
     TextFileProperties textFilePropertiesRecord = null;
  
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Loading the AOP Properties
	 try 
	 {
	    textFilePropertiesRecord = aopFinalFileDb.loadAOPFileProperties(projectId, headerId, propertyId);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	    aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	    errorMessage1 = e3.getMessage();	
		throw new Exception(errorMessage1);
	 } 
	  
	 return textFilePropertiesRecord;  	 
  }   
  
  public ArrayList<ClassMethod> loadAnnotatedMethodsDet(String databaseConfigFile, int projectId) throws Exception
  {  
     String errorMessage1 ="";
	 ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
  
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Loading the AOP Details
	 try 
	 {
	    classMethodList = aopFinalFileDb.loadAnnotatedMethodsDet(projectId);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	    aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	    errorMessage1 = e3.getMessage();	
		throw new Exception(errorMessage1);
	 } 
	  
	 return classMethodList;  	 
  }   

  public int validateAnnotationsQuantity(String databaseConfigFile, int projectId) throws Exception
  {  
     String errorMessage1 ="";
	 int annotationsQuantity=0;
  
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Obtain the quantity of annotations for the UML Sequence Diagram
	 try 
	 {
        annotationsQuantity = aopFinalFileDb.validateAnnotationsQuantity(projectId);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	    aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	    errorMessage1 = e3.getMessage();	
		throw new Exception(errorMessage1);
	 } 
	  
	 return annotationsQuantity;  	 
  }   

  public int validatePropertiesQuantity(String databaseConfigFile, int projectId, int headerId) throws Exception
  {  
     String errorMessage1 ="";
	 int propertiesQuantity=0;
  
     // Connect to the FRL Database
	 try 
	 {
	    aopFinalFileDb.connect(databaseConfigFile);
	 }
	 catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Obtain the quantity of properties for the UML Sequence Diagram
	 try 
	 {
	    propertiesQuantity = aopFinalFileDb.validatePropertiesQuantity(projectId, headerId);
	 }
	 catch (Exception e2) 
	 {
	    errorMessage1 = e2.getMessage();	
		throw new Exception(errorMessage1);
	 } 

	 // Disconnect from the database
	 try 
	 {
	    aopFinalFileDb.disconnect();
	 }
	 catch (Exception e3) 
	 {
	    errorMessage1 = e3.getMessage();	
		throw new Exception(errorMessage1);
	 } 
	  
	 return propertiesQuantity;  	 
  }  
  
}
