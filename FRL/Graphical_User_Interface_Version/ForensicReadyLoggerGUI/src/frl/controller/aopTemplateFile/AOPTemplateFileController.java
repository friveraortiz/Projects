package frl.controller.aopTemplateFile;

import java.util.ArrayList;
import java.util.List;

import frl.gui.aopTemplateFile.AOPTemplateFileFormEvent;
import frl.model.aopTemplateFile.AOPTemplateFile;
import frl.model.aopTemplateFile.AOPTemplateFileDatabase;
import frl.model.configureAspectOrientedFiles.TextFilePlain;
import frl.model.configureAspectOrientedFiles.TextFileProperties;
import frl.model.configureAspectOrientedFiles.TextFilePropertyName;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageHeader;


public class AOPTemplateFileController
{

   // Declare a new object for the AOPTemplateFileDatabase class
   AOPTemplateFileDatabase aOPTemplateFileDb = new AOPTemplateFileDatabase();

   // Method #1
   public List<AOPTemplateFile> getAOPTemplateFiles()
   {
      return aOPTemplateFileDb.getAOPTemplateFiles();
   }
	
   // Method #2
   public void connect(String databaseConfigFile) throws Exception
   {
      String errorMessage1="";
	   
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  } 
	  catch (Exception e ) 
	  {
	     // Error #1 
		 errorMessage1 = e.getMessage();	
		 throw new Exception(errorMessage1);
      }
	   
   }
 
   // Method #3
   public void disconnect() throws Exception
   {
      String errorMessage="";
	      
	  try 
	  {	  
	     aOPTemplateFileDb.disconnect();
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
	   
   }
   
   // Method #4
   public void addAOPTemplateFile(AOPTemplateFileFormEvent ev) 
   {
      AOPTemplateFile aOPTemplateFile;
      int id=0, programmingLanguageId=0;
      String fileName="", path="", name="", typeFile="", packageName="", programmingLanguageName="", pointCutName1="", pointCutName2="";
		
	  // Define the variables for this method
	  id            = ev.getId();
	  fileName      = ev.getFileName();
	  path          = ev.getPath(); 
	  name          = ev.getName();
	  typeFile      = ev.getTypeFile();
	  packageName   = ev.getPackageName();
	  programmingLanguageId   = ev.getProgrammingLanguageId();
	  programmingLanguageName = ev.getProgrammingLanguageName();
	  pointCutName1 = ev.getPointCut1Name();
	  pointCutName2 = ev.getPointCut2Name();
	     
	  if (id == 0)
	  {
	     aOPTemplateFile = new AOPTemplateFile(fileName, path, name, typeFile, packageName, 
	                                           programmingLanguageId, programmingLanguageName, 
	                                           pointCutName1, pointCutName2);
	  }
	  else
	  {
	     aOPTemplateFile = new AOPTemplateFile(id, fileName, path, name, typeFile, packageName, 
                                               programmingLanguageId, programmingLanguageName, 
                                               pointCutName1, pointCutName2);
	  }
	    
	  aOPTemplateFileDb.addAOPTemplateFile(aOPTemplateFile);
	     
   }
   
   // Method #5
   public void deleteAOPTemplateFile(int index) 
   {
     aOPTemplateFileDb.deleteAOPTemplateFile(index);
   }
   

   // Method #6
   public void load(String databaseConfigFile) throws Exception 
   {
      String errorMessage ="";
      
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
	     aOPTemplateFileDb.load();
	  }
	  catch (Exception e ) 
	  {
	     errorMessage = e.getMessage();	
		 throw new Exception(errorMessage);
	  }

	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
   }
   
   // Method #7
   public ArrayList<ProgrammingLanguageHeader> loadProgLanguagesHeader(String databaseConfigFile) throws Exception
   {
      String errorMessage1="";
	  ArrayList<ProgrammingLanguageHeader> pL;
 
      // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	      
      // Load the programming languages
	  pL = aOPTemplateFileDb.loadProgLanguagesHeader();
	  
	  // Disconnect from the Database
	  try 
	  {
		  aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  

	  
      return pL;
   }  
   
   // Method #8
   public int getProgLanguageId(String databaseConfigFile, String programLanguageName) throws Exception 
   {
      String errorMessage1="";
	  int programLanguageId=0;
 
      // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	      
      // Get the Programming Language Identifier
	  programLanguageId = aOPTemplateFileDb.getProgLanguageId(programLanguageName);
	  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  

	  
      return programLanguageId;
   } 
   
   public int saveAOPTemplateFileHeader(String databaseConfigFile, AOPTemplateFileFormEvent aOPTemplateFile) throws Exception 
   {  
      String errorMessage1="";
      int id=0;
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         id = aOPTemplateFileDb.saveAOPTemplateFileHeader(aOPTemplateFile);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
	  
	  return id;
 
   }
   
   public void saveOneLineTextFilePlain(String databaseConfigFile, TextFilePlain tfp) throws Exception 
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.saveOneLineTextFilePlain(tfp);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   }  
   
   public void updateTextFilePlain(String databaseConfigFile, int headerId) throws Exception
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.updateTextFilePlain(headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   }   
 
   public void saveTextFileDetails(String databaseConfigFile, int headerId) throws Exception
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.saveTextFileDetails(headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   }   
   
   public void deleteAOPTemplateFileHeader(String databaseConfigFile, int headerId) throws Exception
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.deleteAOPTemplateFileHeader(headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   }  
   
   public void deleteAOPTemplateFilePlainDetails(String databaseConfigFile, int headerId) throws Exception 
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.deleteAOPTemplateFilePlainDetails(headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   } 
   
   public void deleteAOPTemplateFileDetails(String databaseConfigFile, int headerId) throws Exception
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.deleteAOPTemplateFileDetails(headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   }     
   
   public void deleteAOPTemplateFileProperties(String databaseConfigFile, int projectId, int headerId) throws Exception
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.deleteAOPTemplateFileProperties(projectId, headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   }     
   
   public void deleteAOPTemplateFilePropertyNames(String databaseConfigFile, int headerId) throws Exception
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.deleteAOPTemplateFilePropertyNames(headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   }  
   
   public void createAOPTemplateFileStructures(String databaseConfigFile, int projectId, int headerId) throws Exception
   {  
      String errorMessage1="";   
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.createAOPTemplateFileStructure(projectId, headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
 
   }  
   
   public int getProjectIdentifier(String databaseConfigFile, String projectName) throws Exception 
   {  
      String errorMessage1=""; 
      int projectId=0;
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         projectId = aOPTemplateFileDb.getProjectIdentifier(projectName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
	  
	  return projectId;
 
   }     
   
   public void updateAOPTemplateFileLineNumbers(String databaseConfigFile, int headerId) throws Exception 
   {  
      String errorMessage1=""; 
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.updateAOPTemplateFileLineNumbers(headerId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
 
   }   

   public boolean validateTemplateFileFields(AOPTemplateFileFormEvent ev)
   {
	  boolean validAOPTemplateFile = false; 
	  int programmingLanguageId=0, v = 0;
	  String fileName="", path="", name="", typeFile="", packageName="", pointCut1Name="";
	  
      fileName              = ev.getFileName();
      path                  = ev.getPath();
      name                  = ev.getName();
      typeFile              = ev.getTypeFile();
      packageName           = ev.getPackageName();
      programmingLanguageId = ev.getProgrammingLanguageId();
      pointCut1Name         = ev.getPointCut1Name();
	     	  
	  // Validation: #1
	  // Validate that all the Fields are not empty
	  
	  if(fileName != null && !fileName.isEmpty())
	     v++;

	  if(path != null && !path.isEmpty())
	     v++;
	  
	  if(name != null && !name.isEmpty())
	     v++;
	  
	  if(typeFile != null && !typeFile.isEmpty())
	     v++;	  
	  
	  if(packageName != null && !packageName.isEmpty())
	     v++;	
	  
	  if(programmingLanguageId > 0)
	     v++;

	  if(pointCut1Name != null && !pointCut1Name.isEmpty())
	     v++;	  
	  
	  // Validate that all the users fields have a value						     
	  if (v == 7) 
	     validAOPTemplateFile = true;
	  else 
		  validAOPTemplateFile = false;
	  
      return validAOPTemplateFile;	     
   }   
   
   public void saveTextFileProperty(String databaseConfigFile, TextFileProperties textFileProperties) throws Exception
   {  
      String errorMessage1=""; 
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.saveTextFileProperty(textFileProperties);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
 
   }  
   
   public void saveTextFilePropertyName(String databaseConfigFile, TextFilePropertyName textFilePropertyName) throws Exception
   {  
      String errorMessage1=""; 
      
	  // Connect to the Database
	  try 
	  {
	     aOPTemplateFileDb.connect(databaseConfigFile);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		      
	  // Call the method
	  try 
	  {
         aOPTemplateFileDb.saveTextFilePropertyName(textFilePropertyName);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 throw new Exception(errorMessage1);
	  }  
		  
	  // Disconnect from the Database
	  try 
	  {
	     aOPTemplateFileDb.disconnect();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	
		 throw new Exception(errorMessage1);
	  } 
 
   }    
}
