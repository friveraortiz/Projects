package frl.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import frl.configuration.FRLConfiguration;
import frl.controller.TextFileController;
import frl.model.FieldType;
import frl.model.FileType;
import frl.model.TextFileDetails;
import frl.model.TextFileHeader;
import frl.model.TextFilePlain;

public class Utilities 
{
	
   String errorMessage;
   
   public void saveTextFileDB(TextFileController tfpCon, String textFile)
   {
	  String line;
	  int lineNum = 0;
	  Reader inReader = null;
	  
	  try 
	  {
	     inReader = new FileReader(textFile);
	  } 
	  catch (FileNotFoundException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while trying to open the Text File. " + "Error Message: " + errorMessage);
	  }
		  
	  BufferedReader reader = new BufferedReader(inReader);

	  // Read the Text File line by line 
	  // insert the current line into the TextFile Plain
	  try 
	  {
	     while ((line = reader.readLine()) != null) 
		 {
		    lineNum++; 
			    
			// Create a new TextFilePlain Object
		    TextFilePlain tfp = new TextFilePlain(lineNum, line);
			    
		    // Save the current line into the Database
			tfpCon.saveOneLineTextFilePlain(tfp);
	     }
	  }   
	  catch (IOException e) 
	  {
	     errorMessage = e.getMessage();
	     System.out.println("Error XXX: Occurred while reading the Text File. " + "Error Message: " + errorMessage);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while saving a Text Line into the Database. " + "Error Message: " + errorMessage);
      }
	  
   }
   
 
   
   public void uploadTextFileDb(FRLConfiguration frlCon, 
		                        String textFileNameExt,
		                        TextFileHeader textFileHeader,
		                        TextFileController tfpCon,
		                        String path)
   {
		   
	  String textFileFullName;
	  int headerId = 0;
	  
	  // Get the texFileName to upload into the Database
	  textFileFullName = path  + textFileNameExt;
	  
	  // Insert a new text file header record
	  try 
	  {
	     headerId = tfpCon.saveTextFileHeader(textFileHeader);
		 System.out.println("Uploading a new Text File with Header Id: "+headerId);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while creating a new Text File Header Record. " + "Error Message: " + errorMessage);
	  }
	  
	  // Insert the TextFile into the Text_File_Plain Table
	  saveTextFileDB(tfpCon, textFileFullName);
	  
	  // Update the header Id 
	  // in the Text_File_Plain records that we just inserted
	  try 
	  {
	     tfpCon.updateTextFilePlain(headerId);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while updating the Header Id into the Text File Plain table. " + "Error Message: " + errorMessage);
	  }
	  
	  // Copy the records inserted in the Text_File_Plain table 
	  // to the TextFileDetails table
	  try 
	  {
	     tfpCon.saveTextFileDetails(headerId);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while inserting records into the Text File Details table. " + "Error Message: " + errorMessage);
	  }
	  

   }
   
   public void deletePreviousTextFileRecs(TextFileController tfpCon)
   {
	   
	  System.out.println("Delete previous records in the Text File Tables ...");
	   
      // Delete all the records from the Text File Details table
	  try 
	  {
	     tfpCon.deleteTextFileDetails();
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while deleting records from the Text File Details table. " + "Error Message: " + errorMessage);
	  }
	  
	  // Delete all the records from the Text File Plain table 
	  try 
	  {
	     tfpCon.deleteTextFilePlain();
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while deleting records from the Text File Plain table. " + "Error Message: " + errorMessage);
	  }
		 
	  // Delete all the records from the Text File Properties table
	  try 
	  {
	     tfpCon.deleteTextFileProperties();
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while deleting records from the Text File Properties table. " + "Error Message: " + errorMessage);
	  }
	     
	  // Delete all the previous records from the TextFileHeader table
	  try 
	  {
	     tfpCon.deleteTextFile();
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while deleting a record from the Text File Header table. " + "Error Message: " + errorMessage);
	  }

	   
   }
   
   public void uploadTextFilesDb(FRLConfiguration frlCon)
   {
      String textFileNameExt = null, textFileName, textFileTypeStr, path="";
	  TextFileHeader tfh;
	  FileType textFileType;
	  

	  // Build the full input path name
	  path = frlCon.projectInputDir + frlCon.textFilePath;
	  TextFileController tfpCon = new TextFileController();
	  
	  System.out.println("Uploading the Text Files into the Database ...");
	  System.out.println("Input Directory: "+path);
	  
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
		tfpCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while connecting to the Database." + "Error Message: " + errorMessage);
	  }
	  
	  // Delete previous records in the tables: 
	  // text_file_header, text_file_details, text_file_plain
	  // associated with this textfile
	  deletePreviousTextFileRecs(tfpCon);
	   
      // File #1: FrlDatabaseMethods.aj  
	  textFileNameExt = frlCon.textFileNameExt1;  
	  textFileName    = frlCon.textFileName1; 
	  textFileTypeStr = frlCon.textFileType1;
	  
	  // Convert String to Enum
	  textFileType    = FileType.valueOf(textFileTypeStr);
	  
	 
	  // Build a new TextFileHeader object
	  tfh = new TextFileHeader(textFileNameExt, path, textFileName, 
			                   textFileType, frlCon.textFilePackageName, frlCon.programmingLanguage);
	  
	  // Upload the File #1 to the Database 
      uploadTextFileDb(frlCon, textFileNameExt, tfh, tfpCon, path);
      
      // File #2: FrlStart.aj
	  textFileNameExt = frlCon.textFileNameExt2;  
	  textFileName    = frlCon.textFileName2; 
	  textFileTypeStr = frlCon.textFileType2;
	  
	  // Convert String to Enum
	  textFileType    = FileType.valueOf(textFileTypeStr);
	 
	  // Build the full input path name
	  path = frlCon.projectInputDir + frlCon.textFilePath;
	 
	  // Build a new TextFileHeader object
	  tfh = new TextFileHeader(textFileNameExt, path, textFileName, 
			                   textFileType, frlCon.textFilePackageName, frlCon.programmingLanguage);
	 
	  // Upload the File #2 to the Database 
      uploadTextFileDb(frlCon, textFileNameExt, tfh, tfpCon, path);
      
	  // Disconnect from the Forensic Ready Logger Database
	  tfpCon.disconnect();
      
   }
   
   public void modifyTextFileLine(FRLConfiguration frlCon, 
		                          String textFileNameExt,
		                          String textFileLineSearch,
		                          String textFileLineReplace,
		                          FieldType textFileLineType,
		                          int textFileLinePropId)
   {
		   
	  int headerId=0, c, lineNumber;
	  String text;
	  boolean flag = false;
	  ArrayList<TextFileDetails> textFileDets = new ArrayList<>();	
	  TextFileDetails textFileDet;
	  
	  System.out.println("Updating a Line in the Text File: "+textFileNameExt);
 		 
	  TextFileController tfpCon = new TextFileController();
	  
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
		tfpCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while connecting to the Database." + "Error Message: " + errorMessage);
	  }
	  
	  // Get the header Id from the current textFileName
	  try 
	  {
	     headerId = tfpCon.getHeaderId(textFileNameExt, frlCon.programmingLanguage);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while loading the Text File Header Id into Modify Lines. " + "Error Message: " + errorMessage);
	  }
	  
      // Get the whole textFileDetails ArrayList from the Database
	  try 
	  {
	     textFileDets = tfpCon.getTextFile(headerId);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while loading the Text File from the Database into the Database class. " + "Error Message: " + errorMessage);
	  }
	  
	  // Loop through the textFileDetails ArrayList
	  c = 0;
      while( c < textFileDets.size() && flag == false)
      {
 	     // Get the fields from the textFileDetails ArrayList
 	     lineNumber = textFileDets.get(c).getLineNumber();
 		 text       = textFileDets.get(c).getText();
 		 
 	     // Validate if the field type is equal to "Personalized"
 	     if (text.contains(textFileLineSearch))
 	     {
 	        System.out.println("Line Number: " + lineNumber); 
 	        System.out.println("From       : " + textFileLineSearch);
 	        System.out.println("To         : " + textFileLineReplace);
 	        flag = true;
 	        
 	        // Update the Text_File_Details
 	        textFileDet = new TextFileDetails(headerId, 
 	        		                          lineNumber, 
 	        		                          textFileLineReplace, 
 	        		                          textFileLineType,
 	        		                          textFileLinePropId);
 	        try 
 	        {
		       tfpCon.updateTextFileDetails(textFileDet);
			} 
 	        catch (SQLException e) 
 	        {
 	  	       errorMessage = e.getMessage();
 			   System.out.println("Error XXX: Occurred while updating a Text File Details record." + "Error Message: " + errorMessage);
			}
 	        
 	     }

         c++;
      }
	  
	  // Disconnect from the Forensic Ready Logger Database
	  tfpCon.disconnect();
		     
   }  
 
   
   public void deleteTextFileLine(FRLConfiguration frlCon, 
		                          String textFileNameExt,
		                          String textFileLineSearch)
   {
		   
	  int headerId=0, c, lineNumber;
	  String text;
	  boolean flag = false;
	  ArrayList<TextFileDetails> textFileDets = new ArrayList<>();	
	  
	  System.out.println("Deleting a Line in the Text File: "+textFileNameExt);
 		 
	  TextFileController tfpCon = new TextFileController();
	  
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
		tfpCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while connecting to the Database." + "Error Message: " + errorMessage);
	  }
	  
	  // Get the header Id from the Database
	  try 
	  {
	     headerId = tfpCon.getHeaderId(textFileNameExt, frlCon.programmingLanguage);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while loading the Text File Header Id into Utilities Class. " + "Error Message: " + errorMessage);
	  }
	  
      // Get the whole textFileDetails ArrayList from the Database
	  try 
	  {
	     textFileDets = tfpCon.getTextFile(headerId);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while loading the Text File from the Database into the Database class. " + "Error Message: " + errorMessage);
	  }
	  
	  // Loop through the textFileDetails ArrayList
	  c = 0;
      while( c < textFileDets.size() && flag == false)
      {
 	     // Get the fields from the textFileDetails ArrayList
 	     lineNumber = textFileDets.get(c).getLineNumber();
 		 text       = textFileDets.get(c).getText();
 		 
 	     // Validate if the field type is equal to "Personalized"
 	     if (text.contains(frlCon.textFileLineSearch))
 	     {
 	        System.out.println("Line Number: " + lineNumber);
 	        System.out.println("Content    : " + textFileLineSearch);
 	        flag = true;
 	        
 	        try 
 	        {
		       tfpCon.deleteTextFileDetailsLineNum(headerId, lineNumber);
			} 
 	        catch (SQLException e) 
 	        {
 	  	       errorMessage = e.getMessage();
 			   System.out.println("Error XXX: Occurred while deleting a Text File Details record." + "Error Message: " + errorMessage);
			}
 	        
 	     }

         c++;
      }
	  
	  // Disconnect from the Forensic Ready Logger Database
	  tfpCon.disconnect();
		     
   }
   
   
   public void updateTextFileLineNumbers(FRLConfiguration frlCon, String textFileNameExt)
   {
		   
	  int headerId=0, i, j, oldLineNum=0, newLineNum=0;
	  ArrayList<TextFileDetails> textFileDets = new ArrayList<>();	
	  
	  System.out.println("Updating the Line Numbers in the Text File: "+textFileNameExt);
 		 
	  TextFileController tfpCon = new TextFileController();
	  
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
		tfpCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while connecting to the Database." + "Error Message: " + errorMessage);
	  }
	  
	  // Get the header Id from the Database
	  try 
	  {
	     headerId = tfpCon.getHeaderId(textFileNameExt, frlCon.programmingLanguage);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while loading the Text File Header Id into Utilities Class. " + "Error Message: " + errorMessage);
	  }
	  
      // Get the whole textFileDetails ArrayList from the Database
	  try 
	  {
	     textFileDets = tfpCon.getTextFile(headerId);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while loading the Text File from the Database into the Database class. " + "Error Message: " + errorMessage);
	  }
	  
	  // Loop through the textFileDetails ArrayList
	  j=1;
	  for(i=0; i < textFileDets.size(); i++)
	  {
 	     // Get the fields from the textFileDetails ArrayList
 	     oldLineNum = textFileDets.get(i).getLineNumber();
 	     
         if(oldLineNum != j)
         {	
            newLineNum = j;	 
        	
 	        try 
 	        {
		       tfpCon.updateTextFileDetailsLineNumbers(headerId, oldLineNum, newLineNum);
		    } 
 	        catch (SQLException e) 
 	        {
 	  	       errorMessage = e.getMessage();
 			   System.out.println("Error XXX: Occurred while updating the Text File Details Line Numbers." + "Error Message: " + errorMessage);
		    }
         }
         j++;
 	        
      }

	  // Disconnect from the Forensic Ready Logger Database
      tfpCon.disconnect();
		     
   }  

	   
	   /*
     // Calling the methods from the Utilities class:
 
	   Utilities ut = new Utilities(); 
	   
	   
	   // Uploads the 2 Aspect Oriented Programming Text Files into the Database
	   ut.uploadTextFilesDb(frlCon);
	    
	   // Creates the Structure for the Text_File_Details for headers=1,2,3
	   // Calling the stored procedures from the database
	   
	   String errorMessage="";
	   TextFileController tfpCon = new TextFileController();
     try 
     {
        tfpCon.createStructureTextFileDetails();
     } 
     catch (SQLException e) 
     {
        errorMessage = e.getMessage();
        System.out.println("Error XXX: Occurred while executing the stored procedure in the Database. " + "Error Message: " + errorMessage);
     }


	   // Run Manually the following stored procedures in the 
	   //Forensic-Ready Logger Database
	   /*
	   create_structure_text_file_properties, 
	   create_structure_text_file_header_1, 
	   create_structure_text_file_header_2, 
	   create_structure_text_file_header_3
	   
	   
	   // Update the Line Numbers in the Header = 1
	   ut.updateTextFileLineNumbers(frlCon, frlCon.textFileNameExt1);
	   
	    
	   /*
	   // Modify an specific Line into the text_file_details table
	   Integer propId;
	   
	   // Convert String to integer
	   propId = Integer.valueOf(frlCon.textFileLinePropId);
	   
	   ut.modifyTextFileLine(frlCon, 
	                         frlCon.textFileNameExt1,
	                         frlCon.textFileLineSearch,
	                         frlCon.textFileLineReplace,
	                         FieldType.valueOf(frlCon.textFileLineType),
	                         propId
	                        );
	   
	   
	   
	   // Delete an specific line from the text_file_details table
	   ut.deleteTextFileLine(frlCon, frlCon.textFileNameExt1, frlCon.textFileLineSearch);
	   */
   
   
}
