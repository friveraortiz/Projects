package frl.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import frl.model.TextFileDatabase;
import frl.model.TextFileDetails;
import frl.model.TextFileHeader;
import frl.model.TextFilePlain;
import frl.model.TextFileProperties;

public class TextFileController 
{
	
   // Declare a new object for the TextFilePlainDatabase class
   TextFileDatabase textFileDb = new TextFileDatabase();
   
   public void connect(String propFileName) throws Exception
   {
      textFileDb.connect(propFileName);	 
   }
   
   public void disconnect() //2
   {
      textFileDb.disconnect(); 	   
   }
   
   public void saveOneLineTextFilePlain(TextFilePlain tfp) throws SQLException
   {
      textFileDb.saveOneLineTextFilePlain(tfp);
	   
   }
   
   public void deleteTextFilePlain() throws SQLException 
   {
      textFileDb.deleteTextFilePlain();  
   }
   
   public void updateTextFilePlain(int headerId) throws SQLException
   {
      textFileDb.updateTextFilePlain(headerId);   
   }
   
   public void saveTextFileDetails(int headerId) throws SQLException
   {
	   textFileDb.saveTextFileDetails(headerId);
   }
   
   public int saveTextFileHeader(TextFileHeader tfh) throws SQLException
   {
      int headerId;
      
      headerId = textFileDb.saveTextFileHeader(tfh);
      
      return headerId;
   }
   
   public void deleteTextFileDetails() throws SQLException
   {
      textFileDb.deleteTextFileDetails();   
   }
   
   public void deleteTextFile() throws SQLException
   {
      textFileDb.deleteTextFile();  	   
   }
   
   public ArrayList<TextFileDetails> getTextFile(int inputHeaderId) throws SQLException
   {
      ArrayList<TextFileDetails> textFileDets = new ArrayList<>();	   
	  
      // Call the getSpecificTextFile function from the database
	  textFileDets = textFileDb.getTextFile(inputHeaderId);
	  
      return textFileDets;
       
   }
   
   public int getHeaderId(String textFileName, String programmingLanguage) throws SQLException
   {
      int headerId;
      
      headerId = textFileDb.getHeaderId(textFileName, programmingLanguage);
      return headerId;
   }
   
   public void updateTextFileDetails(TextFileDetails tfd) throws SQLException
   {
      textFileDb.updateTextFileDetails(tfd);	   
   }
   
   public void deleteTextFileDetailsLineNum(int headerId, int lineNumber) throws SQLException
   {
      textFileDb.deleteTextFileDetailsLineNum(headerId, lineNumber);  
   }
   
   public void updateTextFileDetailsLineNumbers(int headerId, 
                                                int oldLineNumber, 
                                                int newLineNumber) throws SQLException
   {
      textFileDb.updateTextFileDetailsLineNumbers(headerId, oldLineNumber, newLineNumber);
   }
   
   public void deleteTextFileProperties() throws SQLException
   {
	   textFileDb.deleteTextFileProperties();	   
   }
   
   public void createStructureTextFileDetails() throws SQLException
   { 
      textFileDb.createStructureTextFileDetails();
   }
   
   public TextFileProperties getPropertyValue(int headerId, int propertyId) throws SQLException
   {
      TextFileProperties tfp;
      
      tfp = textFileDb.getPropertyValue(headerId, propertyId);
      
      return tfp;   
   }
   
   public ArrayList<TextFileProperties> loadPropertyNames(int headerId) throws SQLException
   {
      ArrayList<TextFileProperties> textFileProps = new ArrayList<>();	
      textFileProps = textFileDb.loadPropertyNames(headerId);
      return textFileProps;   
   }
   
   public void updatePropertyNames(int headerId, int propId, String value) throws SQLException
   {
      textFileDb.updatePropertyNames(headerId, propId, value);	   
   }

}
