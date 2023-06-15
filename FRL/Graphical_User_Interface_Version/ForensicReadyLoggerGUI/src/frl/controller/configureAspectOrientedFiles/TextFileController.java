package frl.controller.configureAspectOrientedFiles;

import java.util.ArrayList;

import frl.model.configuration.SourceDirectory;
import frl.model.configureAspectOrientedFiles.TextFileDatabase;
import frl.model.configureAspectOrientedFiles.TextFileDetails;
import frl.model.configureAspectOrientedFiles.TextFileHeader;
import frl.model.configureAspectOrientedFiles.TextFilePlain;
import frl.model.configureAspectOrientedFiles.TextFileProperties;
import frl.model.tree.DatabaseMethod2;

public class TextFileController 
{
   // Declare a new object for the TextFilePlainDatabase class
   TextFileDatabase textFileDb = new TextFileDatabase();
   
   public void connect(String propFileName) throws Exception
   {
      textFileDb.connect(propFileName);	 
   }
   
   public void disconnect() throws Exception
   {
      textFileDb.disconnect(); 	   
   }
   
   public void saveOneLineTextFilePlain(TextFilePlain tfp) throws Exception
   {
      textFileDb.saveOneLineTextFilePlain(tfp);
	   
   }
   
   public void deleteTextFilePlain() throws Exception 
   {
      textFileDb.deleteTextFilePlain();  
   }
   
   public void updateTextFilePlain(int headerId) throws Exception
   {
      textFileDb.updateTextFilePlain(headerId);   
   }
   
   public void saveTextFileDetails(int headerId) throws Exception
   {
	   textFileDb.saveTextFileDetails(headerId);
   }
   
   public int saveTextFileHeader(TextFileHeader tfh) throws Exception
   {
      int headerId;
      
      headerId = textFileDb.saveTextFileHeader(tfh);
      
      return headerId;
   }
   
   public void deleteTextFileDetails() throws Exception
   {
      textFileDb.deleteTextFileDetails();   
   }
   
   public void deleteTextFile() throws Exception
   {
      textFileDb.deleteTextFile();  	   
   }
   
   public ArrayList<TextFileDetails> getTextFile(int inputHeaderId) throws Exception
   {
      ArrayList<TextFileDetails> textFileDets = new ArrayList<>();	   
	  
      // Call the getSpecificTextFile function from the database
	  textFileDets = textFileDb.getTextFile(inputHeaderId);
	  
      return textFileDets;
       
   }
   
   public int getHeaderId(String aspectName, String programmingLanguage) throws Exception
   {
      int headerId=0;
      
      headerId = textFileDb.getHeaderId(aspectName, programmingLanguage);
      
      return headerId;
   }
   
   public void updateTextFileDetails(TextFileDetails tfd) throws Exception
   {
      textFileDb.updateTextFileDetails(tfd);	   
   }
   
   public void deleteTextFileDetailsLineNum(int headerId, int lineNumber) throws Exception
   {
      textFileDb.deleteTextFileDetailsLineNum(headerId, lineNumber);  
   }
   
   public void updateTextFileDetailsLineNumbers(int headerId, 
                                                int oldLineNumber, 
                                                int newLineNumber) throws Exception
   {
      textFileDb.updateTextFileDetailsLineNumbers(headerId, oldLineNumber, newLineNumber);
   }
   
   public void deleteTextFileProperties(int projectId) throws Exception
   {
	   textFileDb.deleteTextFileProperties(projectId);	   
   }
   
   public void createStructureTextFileDetails() throws Exception
   { 
      textFileDb.createStructureTextFileDetails();
   }
   
   public TextFileProperties getPropertyValue(int headerId, int propertyId, int projectId) throws Exception
   {
      TextFileProperties tfp;
      
      tfp = textFileDb.getPropertyValue(headerId, propertyId, projectId);
      
      return tfp;   
   }
   
   public ArrayList<TextFileProperties> loadPropertyNames(int headerId) throws Exception
   {
      ArrayList<TextFileProperties> textFileProps = new ArrayList<>();	
      textFileProps = textFileDb.loadPropertiesNames(headerId);
      
      return textFileProps;   
   }
   
   public void saveTextProperty(TextFileProperties tfp) throws Exception
   {
      textFileDb.saveTextProperty(tfp); 
   }
   
   public void deleteTextProperties(int projectId, int headerId) throws Exception
   {
      textFileDb.deleteTextProperties(projectId, headerId);
   }

   public int calculateDBMethodsCount(int projectId) throws Exception
   {
      String errorMessage="";
      int dbMethodsCount=0;
      
      try
      {
         dbMethodsCount = textFileDb.calculateDBMethodsCount(projectId);
      }
      catch (Exception e1) 
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
      
      return dbMethodsCount;
   }
   
   public ArrayList<SourceDirectory> loadSourceDirDBMethodsCount(int projectId) throws Exception 
   {
      String errorMessage="";
      ArrayList<SourceDirectory> sourceDirList = new ArrayList<>();	
      
      try
      {
         sourceDirList = textFileDb.loadSourceDirDBMethodsCount(projectId);
      }
      catch (Exception e1) 
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
   
      return sourceDirList;
   } 
   
   public ArrayList<DatabaseMethod2> loadDBMethodsDetails(int projectId, String condition) throws Exception
   {
      String errorMessage=""; 
      ArrayList<DatabaseMethod2> DBMetList = new ArrayList<DatabaseMethod2>();	   
		  
      try
      {
         DBMetList = textFileDb.loadDBMethodsDetails(projectId, condition);
      }
      catch (Exception e1) 
      {
         errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
      }
		   
      return DBMetList;    
   }	   
   
}