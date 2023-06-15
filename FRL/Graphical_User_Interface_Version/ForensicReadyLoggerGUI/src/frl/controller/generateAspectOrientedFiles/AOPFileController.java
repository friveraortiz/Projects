package frl.controller.generateAspectOrientedFiles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FilenameUtils;

import frl.gui.configFile.ConfigFileFormEvent;
import frl.model.generateAspectOrientedFiles.AOPFile;
import frl.model.generateAspectOrientedFiles.AOPFileDatabase;
import frl.model.generateAspectOrientedFiles.ConfigFile;
import frl.model.generateAspectOrientedFiles.DBMS;
import frl.model.generateAspectOrientedFiles.InputMethod;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageDetail;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageHeader;

// This class is an intermediary between the GUI and the Model Classes
// Transform the data from the GUI in a way that the Model can understand it, because 
// Model is a Data Model to store data into a Database

// Package #2
// Class #1
public class AOPFileController 
{
	// Declare a new object for the UserDatabase class
	AOPFileDatabase aopFileDb = new AOPFileDatabase();

	// Define the method: List getAOPFiles which was defined in the AOPFileDatabase Class
	// Method #1
	public List<AOPFile> getAOPFiles()
	{
		return aopFileDb.getAOPFiles();
	}
	
	public List<ConfigFile> getConfigFiles()
	{
		return aopFileDb.getConfigFiles();
	}
	
	public List<ProgrammingLanguageDetail> getProgLangDet()
	{
		return aopFileDb.getProgLangDet();
	}

	
   public void load(String databaseConfigFile) throws Exception 
   {
      
      // Connect to the Database
	  aopFileDb.connect(databaseConfigFile);

      aopFileDb.load(); 

      // Disconnect from the Database 
      aopFileDb.disconnect();

   }
   
   // Method #10
   // YA NO SE USA ESTE METODO
   public void loadAOPFile(int id, String databaseConfigFile) throws Exception 
   {
      // Connect to the FRL Database
	  aopFileDb.connect(databaseConfigFile);
	  
      aopFileDb.loadAOPFile(id); 
      
      aopFileDb.disconnect();

   }
   
   public ArrayList<ProgrammingLanguageHeader> loadProgLanguagesHeader(String databaseConfigFile) throws Exception
   {
      String errorMessage1="";
	  ArrayList<ProgrammingLanguageHeader> pL;
 
      // Connect to the Database
	  try 
	  {
	     aopFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	      
      // Load the programming languages
	  pL = aopFileDb.loadProgLanguagesHeader();
	  
	  // Disconnect from the Database
	  try 
	  {
	     aopFileDb.disconnect();
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
	     aopFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	      
      // Loading the DBMS
	  try 
	  {
	     dB = aopFileDb.loadDBMS();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  
	  
	  // Disconnect from the Database
	  try 
	  {
	     aopFileDb.disconnect();
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
	     aopFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Load the Input Methods
	  try 
	  {
		iM = aopFileDb.loadInputMethods();
	  } 
	  catch (SQLException e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception(errorMessage2);
	  }
	  
	  // Disconnect from the Database
	  try 
	  {
	     aopFileDb.disconnect();
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
	     aopFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Loading the programming languages
	  try 
	  {
	     aopFileDb.loadProgLangDet(progLangName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the database
	  try 
	  {
	     aopFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  
	 
   }
   
   // Method #11
   public boolean validateConfFileFields(ConfigFileFormEvent ev)
   {
	   
      boolean validConfFile = false; 
	  int v = 0;
	  
	  String featuresConfigFile= ev.getfeaturesConfigFile();
	  String projectName       = ev.getProjectName();
	  String jarFileName       = ev.getJarFileName();
	  String projInputDir      = ev.getProjInputDir();
	  String projOutputDir     = ev.getProjOutputDir();
	  String progLanguage      = ev.getProgLanguage();
	  String dbms              = ev.getDbms();
	  String inputMethod       = ev.getInputMethod();
	  String startProjMethod   = ev.getStartProjMethod();
	  String endProjMethod     = ev.getEndProjMethod();
	  String connectProjMethod = ev.getConnectProjMethod();
	     	  
	  // Validation: #1
	  // Validate that all the Configuration File Fields are not empty
	  
	  if(featuresConfigFile != null && !featuresConfigFile.isEmpty())
	     v++;
	
	  if(projectName != null && !projectName.isEmpty())
	     v++;

	  if(jarFileName != null && !jarFileName.isEmpty())
	     v++;
	  
	  if(projInputDir != null && !projInputDir.isEmpty())
		     v++;
	  
	  if(projOutputDir != null && !projOutputDir.isEmpty())
		     v++;
	  
	  if(progLanguage != null && !progLanguage.isEmpty())
		     v++;
	  
	  if(dbms != null && !dbms.isEmpty())
		     v++;
	  
	  if(inputMethod != null && !inputMethod.isEmpty())
		     v++;
	  
	  if(startProjMethod != null && !startProjMethod.isEmpty())
		     v++;
	  
	  if(endProjMethod != null && !endProjMethod.isEmpty())
		     v++;
	  
	  if(connectProjMethod != null && !connectProjMethod.isEmpty())
		     v++;
	  
	  // Validate that all the configuration fields have a value						     
	  if (v == 11) 
		  validConfFile = true;
	  else 
		  validConfFile = false;
	  
      return validConfFile;	     
   }   
   
   // Method #7
   public int save(ConfigFileFormEvent ev, String databaseConfigFile) throws Exception
   {
	  ConfigFile configFile;
	  String projectName="", jarFileName="", projInputDir="", projOutputDir="", progLanguage="",
	         dbms="", inputMethod="", startProjMethod="", endProjMethod="", connectProjMethod="", 
	         connectProjMethodReturnValue="",
	         operatingSystem="",
	         errorMessage1="";
	  int id1 = 0, id2=0;
	   
      // Define the variables for this method
	  id1                = ev.getId();
	  projectName        = ev.getProjectName();
	  jarFileName        = ev.getJarFileName();
	  projInputDir       = ev.getProjInputDir();
	  projOutputDir      = ev.getProjOutputDir();
	  progLanguage       = ev.getProgLanguage();
	  dbms               = ev.getDbms();
	  inputMethod        = ev.getInputMethod();
	  startProjMethod    = ev.getStartProjMethod();
	  endProjMethod      = ev.getEndProjMethod();
	  connectProjMethod  = ev.getConnectProjMethod();
	  connectProjMethodReturnValue = ev.getConnectProjMethodReturnValue();
	  operatingSystem    = ev.getOperatingSystem();

	  if (id1 == 0)
	  {
	     configFile = new ConfigFile(projectName, jarFileName, 
	    		                     projInputDir, projOutputDir, progLanguage, 
	    		                     dbms, inputMethod, startProjMethod, 
	    		                     endProjMethod, connectProjMethod, 
	    		                     connectProjMethodReturnValue,
	    		                     operatingSystem);
	  }
	  else
	  {
	     configFile = new ConfigFile(id1, projectName,
				                     jarFileName, projInputDir, projOutputDir,
					                 progLanguage, dbms, inputMethod,
					                 startProjMethod, endProjMethod, 
					                 connectProjMethod,
					                 connectProjMethodReturnValue,
					                 operatingSystem);
	  }
	  
	  // Connect to the FRL Database
	  try 
	  {
	     aopFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
      
	  // Save Configuration File
	  try 
	  {
	     id2 = aopFileDb.save(configFile);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the Database
	  try 
	  {
	     aopFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  
	  return id2;
	  
   }

   // Method #7
   public void deleteProjectInformation(String databaseConfigFile, int projectId) throws Exception
   {
	  String errorMessage1="";

	  // Connect to the FRL Database
	  try 
	  {
	     aopFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
      
	  // Delete Project Configuration
	  try 
	  {
	     aopFileDb.deleteProjectInformation(projectId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the Database
	  try 
	  {
	     aopFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  
   }
   
   // Define the method: delete a Project
   // Method #6
   public void deleteProject(int index) 
   {
      aopFileDb.deleteProject(index);
   }
   
   
   public boolean validDirectories(String inputDir, String outputDir)
   {
      boolean flag=false;
      
      if(inputDir.equals(outputDir))
         flag = false;
      else
         flag = true;
      
      return flag;
   }
   
   // Method #7
   public boolean validateProjectName(String databaseConfigFile, String projectName, String subMenu) throws Exception
   {
	  String errorMessage1="";
	  int records=0;
	  boolean flag=false;

	  // Connect to the FRL Database
	  try 
	  {
	     aopFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
      
	  // Delete Project Configuration
	  try 
	  {
	     records = aopFileDb.validateProjectName(projectName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the Database
	  try 
	  {
	     aopFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }
	  
	  if(records == 0 && subMenu.equals("Add"))
         flag = true;
      else
    	  if(records == 1 && subMenu.equals("Modify"))
             flag = true;
    	  else
    	     flag = false;	  
	  
	  
	  return flag;
	  
   }   
   
   // Method #7
   public boolean validateJarFile1(String databaseConfigFile,  String projectJarFileName, String subMenu) throws Exception
   {
	  String errorMessage1="";
	  int records=0;
	  boolean flag=false;

	  // Connect to the FRL Database
	  try 
	  {
	     aopFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
      
	  // Delete Project Configuration
	  try 
	  {
	     records = aopFileDb.validateProjectJarFileName(projectJarFileName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  } 

	  // Disconnect from the Database
	  try 
	  {
	     aopFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }
	  
	  if(records == 0 && subMenu.equals("Add"))
         flag = true;
      else
    	  if(records == 1 && subMenu.equals("Modify"))
             flag = true;
    	  else
    	     flag = false;	  
	  
	  
	  return flag;
	  
   }   
   
   public boolean validateJarFile2(String jarFileName)
   {
      String extension="";
      boolean flag=false;
	  
      extension = FilenameUtils.getExtension(jarFileName);
      
      if(extension.equals("jar"))
         flag = true;
      else
         flag = false;
	      
      return flag;
	   
   }
   
   public boolean validateMethod(String methodName) throws Exception
   {
      boolean flag=false;
      String token="", className="", errorMessage1="", errorMessage2="";
      StringTokenizer tokenizer;
      int c=0, totalTokens=0;
      
      //System.out.println("Method Name: " + methodName);
      
      tokenizer	  = new StringTokenizer(methodName, ".");
      totalTokens = tokenizer.countTokens();
      
      while(c < totalTokens) 
      { 
         token = tokenizer.nextToken();
         c++;
         
         if(c > 1)
            className = className + "." + token;
         else
            className = token;


         if(c == (totalTokens - 1))
         {

        	// Validate the method contains a package name and a class name 
            try
        	{
        	   Class.forName(className);
        	   flag = true;
        	}
        	catch(ClassNotFoundException e1)
        	{
        	   flag = false;
        	   errorMessage1 = e1.getMessage();	
        	   errorMessage2 = "Error XXXX: Ocurred while validating the Method Name: " + methodName + System.lineSeparator();
        	   errorMessage2 = errorMessage2 + errorMessage1;
    		   throw new Exception(errorMessage2);
        	}
                            
         }
      }
      

      //System.out.println("Flag       : " + flag);
      
      return flag;
   }
   
   public boolean validateEndMethod(String methodName) throws Exception
   {
      boolean flag=false;
      String errorMessage1="";
  
      if(methodName.contains("exit"))
         flag = true;
	  else
	     try 
         {
		    flag = validateMethod(methodName);
		 } 
         catch (Exception e1) 
         {
            flag = false;	 
        	errorMessage1 = e1.getMessage();	
        	throw new Exception(errorMessage1);
		 }
         
      return flag;
   }  
   
   public boolean connectMethodReturnValue(String methodName)
   {
      boolean flag=false;
  
      if(methodName != null && !methodName.trim().isEmpty())
         flag = true;
      else
         flag = false;
         
      return flag;
   }  
   
}
