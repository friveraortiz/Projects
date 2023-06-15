package frl.model.configureAspectOrientedFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import frl.model.configuration.SourceDirectory;
import frl.model.tree.DatabaseMethod2;

public class TextFileDatabase
{

   // Attributes for the ForensicReadyLoggerDatabase 
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
	   

   // Constructor of the Class
   public TextFileDatabase() //1
   {
     
   }
	   
   // Methods of the Class
   private void configureDBParameters(String propFileName) throws Exception
   {
	  String errorMessage1="", errorMessage2="";  
      Properties prop = new Properties();
	  InputStream input = null;

	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(propFileName);

		 // Load the properties file
	     prop.load(input);

	     // Get the values from the properties file
	     this.dbUser     = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 this.dbUrl      = prop.getProperty("dbUrl");
		 this.dbDriver   = prop.getProperty("dbDriver");

	  } 
	  catch (IOException e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the Database Configuration File. \n";
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  } 
	  finally 
	  {
	     if (input != null) 
		 {
	        try 
			{
		       input.close();
		    } 
			catch (IOException e2) 
			{
			   errorMessage1 = e2.getMessage();
			   errorMessage2 = "Error XXXX: Occurred while closing the Database Configuration File.. \n";
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   throw new Exception(errorMessage2);
		    }
		  }
	   }

   }  
	
   // Connect to the ForensicReadyLogger Database
   public void connect(String propFileName) throws Exception //3
   {
      String errorMessage1="", errorMessage2=""; 
      
      if (dbCon != null)
	  return;

      // Get the database parameters from the config.properties file
      try
      {
         configureDBParameters(propFileName);
      }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred because the Database Driver was not found. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
	}
	
   // Disconnect to the ForensicReadyLogger Database
   public void disconnect() throws Exception
   {
      String errorMessage1="", errorMessage2="";
      
      if (dbCon != null) 
	  {
	      try 
		  {
		     dbCon.close();
		  } 
	      catch (SQLException e1) 
		  {
		     errorMessage1 = e1.getMessage();
			 errorMessage2 = "Error XXXX: Occurred because the Database Connection cannot be closed. \n";
			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			 throw new Exception(errorMessage2);
	      }
	   }
		
      dbCon = null;
	   
   }
   	
   public void saveOneLineTextFilePlain(TextFilePlain tfp) throws Exception 
   {		
      int col, lineNumber = 0;
	  String lineText, insertSql, errorMessage1="", errorMessage2="";
	  PreparedStatement insertStatement;
	   
	  try
	  {
	     // Get the values from the GUI
	     lineNumber = tfp.getLineNumber();
	     lineText   = tfp.getText();
		
	     // Build the insert statement
	     insertSql = "insert into text_file_plain (tfp_line_number, tfp_text) ";
	     insertSql = insertSql + "values (?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);
	   
	     col = 1;
	     insertStatement.setInt(col++, lineNumber);
	     insertStatement.setString(col++, lineText);
						
	     insertStatement.executeUpdate();
	     insertStatement.close();
	  }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Text File Plain with Line Number: "+ lineNumber +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }

   }
   
   public void deleteTextFilePlain() throws Exception 
   {
	  String deleteSql, errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
	   
	  try
	  {
         deleteSql = "delete from text_file_plain ";
	     deleteSql = deleteSql + "where tfp_line_number is not null ";
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	    
         deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting Text File Plains that have Line Numbers. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
   } 
   
   public void updateTextFilePlain(int headerId) throws Exception
   {
      int col;
      String updateSql, errorMessage1="", errorMessage2="";
      PreparedStatement updateStatement;
      
      try
      {
         // Prepare the SQL Statement
         updateSql = "update text_file_plain tfp ";
         updateSql = updateSql + "set tfp.tfh_id =? ";
	     updateSql = updateSql + "where tfp.tfh_id is null ";
	    	  
	     updateStatement = dbCon.prepareStatement(updateSql);
	    
	     col = 1;
	     updateStatement.setInt(col++, headerId);
	  	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while updating a Text File Plain with Header Identifier: "+ headerId +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
   }
   
   public void saveTextFileDetails(int headerId) throws Exception
   {
      String selectSql, insertSql, text, type, errorMessage1="", errorMessage2="";
      int col, lineNumber = 0, id = 0;	
      PreparedStatement selectStatement, insertStatement;
      ResultSet selectResults;
      
      try
      {
  	     // Prepare the SQL Statement
	     selectSql = "select tfh_id, tfp.tfp_line_number, tfp.tfp_text ";
	     selectSql = selectSql + "from text_file_plain tfp ";
	     selectSql = selectSql + "where tfh_id = ? ";
	     selectSql = selectSql + "order by tfp.tfp_line_number";
	  	  
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setInt(1, headerId);
	     selectResults = selectStatement.executeQuery();
         selectResults.close();
	     selectStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading a Text File Plain with Header Identifier: "+ headerId +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }      
      
      try
      {
	     // Build the insert statement
	     insertSql = "insert into text_file_detail(tfh_id, tfd_line_number, ";
	     insertSql = insertSql + "tfd_text, tfd_type) ";
	     insertSql = insertSql + "values (?, ?, ?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);
	  				
	     // Loop while there are results in the query
	     while(selectResults.next()) 
	     {	
	        id          = selectResults.getInt("tfh_id");
	        lineNumber  = selectResults.getInt("tfp_line_number");
	        text        = selectResults.getString("tfp_text");
	        type        = FieldType.Fixed.name();
	     
		    col = 1;		
		    insertStatement.setInt(col++, id);
		    insertStatement.setInt(col++, lineNumber);
		    insertStatement.setString(col++, text);      
		    insertStatement.setString(col++, type);
	     
		    insertStatement.executeUpdate();
         }   

	     insertStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Text File Details with Header Identifier: "+ id +"\n";
		 errorMessage2 = errorMessage2 + "and Line Number: " + lineNumber + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }          
	  
   } 
   
   public int getProgrammingLanguageId(String programmingLanguage) throws Exception
   {
	   
      String selectSql, errorMessage1="", errorMessage2="";
      int plId = 0;
      PreparedStatement selectStatement;
      ResultSet selectResults;
      
      try
      {
	     // Prepare the SQL Statement
	     selectSql = "select plh.plh_id ";
	     selectSql = selectSql + "from programming_language_header plh ";
	     selectSql = selectSql + "where plh.plh_name = ?";
		  	  
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setString(1, programmingLanguage);
	     selectResults  = selectStatement.executeQuery();
		  
	     // Get the results from the query
	     if(selectResults.next()) 
	        plId = selectResults.getInt("pl_id");
	  
         selectResults.close();
	     selectStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Identifier of the Programming Language with Name: "+ programmingLanguage +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }   
	  
      return plId;	  
   }
   
   public int saveTextFileHeader(TextFileHeader tfh) throws Exception
   {
      String selectSql1, insertSql, fileName, name, path, packageName;
      String programmingLanguage, typeStr, errorMessage1="", errorMessage2="";
      int col, headerId = 0, plId = 0;	
      FileType type;
      PreparedStatement selectStatement1, insertStatement;
      ResultSet selectResults1;
      
      try
      {
         // Get the information from the GUI
         fileName = tfh.getFileName();
         path     = tfh.getPath();
         name     = tfh.getName();
         type     = tfh.getType();
      
         // Convert Enum to String
         typeStr  = type.name();
      
         packageName         = tfh.getPackageName();
         programmingLanguage = tfh.getProgrammingLanguage();
      
         // Get the header Id
         // Prepare the SQL Statement
         selectSql1 = "select max(tfh.tfh_id) as tfh_id ";
         selectSql1 = selectSql1 + "from text_file_header tfh";
	  	  
         selectStatement1 = dbCon.prepareStatement(selectSql1);
         selectResults1 = selectStatement1.executeQuery();
	  
         // Get the results from the query
         if(selectResults1.next()) 
        	 headerId = selectResults1.getInt("tfh_id");
	  
         // Increase the value of the header Id
         headerId++;
	  
         // Get the programming Language Id
         plId = getProgrammingLanguageId(programmingLanguage); 
         selectResults1.close();
         selectStatement1.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the maximum identifier of the Text File Header. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
      
      try
      {
	     // Build the insert statement
    	 insertSql = "insert into text_file_header(tfh_id, tfh_filename, tfh_path, ";
    	 insertSql = insertSql + "tfh_name, tfh_type, tfh_package_name, pl_id ) ";
    	 insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?) ";
    	 insertStatement = dbCon.prepareStatement(insertSql);
	  
    	 // Execute insert Statement
    	 col = 1;		
    	 insertStatement.setInt(col++, headerId);
    	 insertStatement.setString(col++, fileName);   
    	 insertStatement.setString(col++, path);
    	 insertStatement.setString(col++, name);
    	 insertStatement.setString(col++, typeStr);
    	 insertStatement.setString(col++, packageName);
    	 insertStatement.setInt(col++, plId);
	     
    	 insertStatement.executeUpdate();
    	 insertStatement.close();

      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Text File Header with Header Identifier: " + headerId + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
      return headerId;	  
   }    

   public void deleteTextFileDetails() throws Exception
   {
      String deleteSql, errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
		   
	  try
	  {
	     deleteSql = "delete from text_file_detail ";
	     deleteSql = deleteSql + "where tfh_id >= 0";
	  
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.executeUpdate();
	  
	     deleteStatement.close();
	  }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Text File Details with the Identifier greater or equal to Zero. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
   }
   
   public void deleteTextFile() throws Exception
   {
      String deleteSql, errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
		   
	  try
	  {
	     deleteSql = "delete from text_file_header ";
	     deleteSql = deleteSql + "where tfh_id >= 0";
	  
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.executeUpdate();
	  
	     deleteStatement.close();
	  }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Text File Header with the Identifier greater or equal to Zero. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	   
   }
   
   public ArrayList<TextFileDetails> getTextFile(int inputHeaderId) throws Exception
   {
      int headerId, lineNumber, propertyId;
	  String text, typeStr, selectSql1, errorMessage1="", errorMessage2="";
	  FieldType type;
	  TextFileDetails textFileDet = null;
	  ArrayList<TextFileDetails> textFileDets = new ArrayList<>();
	  PreparedStatement selectStatement1;
	  ResultSet selectResults1;
		
	  try
	  {
	     // SQL Statements
	     selectSql1 = "select tfd.tfh_id, tfd.tfd_line_number, tfd.tfd_text, ";
	     selectSql1 = selectSql1 +"tfd.tfd_type, tfd.tfp_id ";
	     selectSql1 = selectSql1 + "from text_file_detail tfd, text_file_header tfh ";
	     selectSql1 = selectSql1 + "where tfh.tfh_id = ? and ";
	     selectSql1 = selectSql1 + "tfh.tfh_id = tfd.tfh_id";
			   
	     selectStatement1 = dbCon.prepareStatement(selectSql1);
     
	     selectStatement1.setInt(1, inputHeaderId);
	     selectResults1 = selectStatement1.executeQuery();
	   
	     // Validate there are results in the query
	     while (selectResults1.next()) 
	     {	   
	    	 headerId   = selectResults1.getInt("tfh_id");
	    	 lineNumber = selectResults1.getInt("tfd_line_number");
	    	 text       = selectResults1.getString("tfd_text");
	    	 typeStr    = selectResults1.getString("tfd_type");
	    	 // Convert string to enum
	    	 type = FieldType.valueOf(typeStr);
	    	 propertyId = selectResults1.getInt("tfp_id");
	      
	    	 textFileDet = new TextFileDetails(headerId, lineNumber, text, type, propertyId);
	    	 textFileDets.add(textFileDet);
	     }
	   
	     selectResults1.close();
	     selectStatement1.close();
	  }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the details of the Text File Header and Text File Details with the Header Identifier: " + inputHeaderId + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      } 
	   
      return textFileDets;
      
   }
	
   public int getHeaderId(String aspectName, String programmingLanguage) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2=""; 
	  int headerId = 0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	      
	  try
	  {
	     // Prepare the SQL Statement #1
	     selectSql = "select tfh.tfh_id id ";
		 selectSql = selectSql + "from text_file_header tfh, ";
		 selectSql = selectSql + "programming_language_header plh ";
		 selectSql = selectSql + "where tfh.tfh_name = ? and ";
		 selectSql = selectSql + "plh.plh_name = ? and ";
		 selectSql = selectSql + "tfh.pl_id = plh.plh_id";
	   
		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setString(1, aspectName);
		 selectStatement.setString(2, programmingLanguage);
		 selectResults = selectStatement.executeQuery();
		  
		 // Get the results from the query
		 if(selectResults.next()) 
	        headerId = selectResults.getInt("id");
	   
	     selectResults.close();
	     selectStatement.close();
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Header of the Aspect Oriented File: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Programming Language: " + programmingLanguage + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Aspect Name: " + aspectName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	   
	  return headerId;	
	}
	   
    public void updateTextFileDetails(TextFileDetails tfd) throws Exception
    {

	   String updateSql, text, typeStr, errorMessage1="", errorMessage2="";
       int col, headerId = 0, lineNum = 0, propId;
       FieldType type;
	   PreparedStatement updateStatement;
	  
	   try
	   {
	      // Get the values from the GUI
	      text        = tfd.getText();
	      type        = tfd.getType();
	  
	      // Convert enum to string
	      typeStr    = type.toString();
	      headerId   = tfd.getHeaderId();
	      lineNum    = tfd.getLineNumber();
	      propId     = tfd.getPropertyId();
	      
	      // Prepare the SQL Statement
	      updateSql = "update text_file_detail tfd ";
	      updateSql = updateSql + "set tfd.tfd_text =?, ";
	      updateSql = updateSql + "tfd.tfd_type =? ,";
	      updateSql = updateSql + "tfd.tfp_id =? ";
	      updateSql = updateSql + "where tfd.tfh_id =? and ";
	      updateSql = updateSql + "tfd.tfd_line_number =?";
		    	  
	      updateStatement = dbCon.prepareStatement(updateSql);
		    
	      col = 1;
	      updateStatement.setString(col++, text);
	      updateStatement.setString(col++, typeStr);
	      updateStatement.setInt(col++, propId);
	      updateStatement.setInt(col++, headerId);
	      updateStatement.setInt(col++, lineNum);
		  	  
	      updateStatement.executeUpdate();
	      updateStatement.close();
	   }
	   catch (Exception e1) 
	   {
	      errorMessage1 = e1.getMessage();
	      errorMessage2 = "Error XXXX: Occurred while updating the Text File Details with the Header Identifier: " + headerId +"\n";
		  errorMessage2 = errorMessage2 + " and the Line Number: "+ lineNum +"\n";
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	    
   }
   
   public void deleteTextFileDetailsLineNum(int headerId, int lineNumber) throws Exception
   {
      String deleteSql, errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
		   
	  try
	  {
	     deleteSql = "delete from text_file_detail ";
	     deleteSql = deleteSql + "where tfh_id = ? and ";
	     deleteSql = deleteSql + "tfd_line_number =?";
	  
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, headerId);
	     deleteStatement.setInt(2, lineNumber);
	     deleteStatement.executeUpdate();
	  
	     deleteStatement.close();
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while deleting the Text File Details with the Header Identifier: " + headerId +"\n";
		 errorMessage2 = errorMessage2 + " and the Line Number: "+ lineNumber +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	   
   }
   
   public void updateTextFileDetailsLineNumbers(int headerId, 
		                                        int oldLineNumber, 
		                                        int newLineNumber) throws Exception
   {

     String updateSql, errorMessage1="", errorMessage2="";
     int col;
     PreparedStatement updateStatement;

     try
     {
        // Prepare the SQL Statement
        updateSql = "update text_file_detail tfd ";
        updateSql = updateSql + "set tfd.tfd_line_number =? ";
        updateSql = updateSql + "where tfd.tfh_id =? and ";
        updateSql = updateSql + "tfd.tfd_line_number =?";
	    	  
        updateStatement = dbCon.prepareStatement(updateSql);
	    
        col = 1;
        updateStatement.setInt(col++, newLineNumber);
        updateStatement.setInt(col++, headerId);
        updateStatement.setInt(col++, oldLineNumber);
	  	  
        updateStatement.executeUpdate();
        updateStatement.close();
     }
     catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();
		errorMessage2 = "Error XXXX: Occurred while updating the Text File Details with the Header Identifier: " + headerId +"\n";
		errorMessage2 = errorMessage2 + " and the Line Number: "+ oldLineNumber +"\n";
		errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		throw new Exception(errorMessage2);
     }
	  
   }
   
   public void deleteTextFileProperties(int projectId) throws Exception
   {
      String deleteSql, errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
		 
	  try
	  {
	     deleteSql = "delete from text_file_property ";
	     deleteSql = deleteSql + "where pc_id = ? and ";
	     deleteSql = deleteSql + "tfh_id is not null";
	  
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	     
	  }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Text File Properties.\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
	   
   }
   
   public int getMaxPropertyId(int headerId, int projectId) throws Exception
   {
      String selectSql1, errorMessage1="", errorMessage2="";
      int propId = 0;
      PreparedStatement selectStatement1;
      ResultSet selectResults1;
      
      try
      {
	     // Get the maximum value for the 
  	     // Prepare the SQL Statement
	     selectSql1 = "select max(tfp.tfp_id)+1 as propId ";
	     selectSql1 = selectSql1 + "from text_file_property tfp ";
	     selectSql1 = selectSql1 + "where tfp.pc_id = ? and ";
	     selectSql1 = selectSql1 + "tfp.tfh_id = ?";
	  
	     selectStatement1 = dbCon.prepareStatement(selectSql1);
	     selectStatement1.setInt(1, projectId);
	     selectStatement1.setInt(2, headerId);
	     selectResults1 = selectStatement1.executeQuery();
	  
	     // Get the results from the query
	     if(selectResults1.next()) 
	        propId = selectResults1.getInt("propId");

	     if(propId == 0)
	        propId = 1;
	  
         selectResults1.close();
	     selectStatement1.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Text File Properties with the Header Identifier: " + headerId +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
      return propId;	  
   } 
   
   public void createStructureTextFileDetails() throws Exception
   {
	  String storeProcSql, errorMessage1="", errorMessage2=""; 
      CallableStatement callableStatement;
      
      try
      {
         storeProcSql = "{call create_structure_text_file_header_1()}";
         callableStatement = dbCon.prepareCall(storeProcSql);
         callableStatement.execute();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while calling the store procedure: create_structure_text_file_header_1 \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
      
      try
      {
         storeProcSql = "{call create_structure_text_file_header_2()}";
         callableStatement = dbCon.prepareCall(storeProcSql);
         callableStatement.execute();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while calling the store procedure: create_structure_text_file_header_2 \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
      
   }
   
   public TextFileProperties getPropertyValue(int headerId, int propertyId, int projectId) throws Exception
   {
      String selectSql, name, value, dataType, propTypeStr, errorMessage1="", errorMessage2="";
      PropertyType propType;
      PreparedStatement selectStatement;
      ResultSet selectResults;
      TextFileProperties tfp = null;
      
      try
      {
	     // Prepare the SQL Statement
	     selectSql = "select tfp.tfp_name name, tfp.tfp_value value, ";
	     selectSql = selectSql + "tfp.tfp_data_type dataType, tfp.tfp_type type ";
	     selectSql = selectSql + "from text_file_property tfp ";
	     selectSql = selectSql + "where tfp.pc_id = ? and ";
	     selectSql = selectSql + "tfp.tfh_id = ? and ";
	     selectSql = selectSql + "tfp.tfp_id = ?";
	  
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, headerId);
	     selectStatement.setInt(3, propertyId);
	     selectResults  = selectStatement.executeQuery();
		  
	     // Get the results from the query
	     if(selectResults.next())
	     {
		    name        = selectResults.getString("name"); 
	        value       = selectResults.getString("value");	
	        dataType    = selectResults.getString("dataType");
	        propTypeStr = selectResults.getString("type");
	     
	        // Convert string to Enum
	        propType = PropertyType.valueOf(propTypeStr);
	     
	        tfp = new TextFileProperties(projectId, name, value, dataType, propType);
	     }

         selectResults.close();
	     selectStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading a Text File Properties with Header Identifier: "+ headerId +"\n";
		 errorMessage2 = errorMessage2 + "and Property Identifier: " + propertyId +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
      return tfp;	  
   }
   
   public ArrayList<TextFileProperties> loadPropertiesNames(int headerId) throws Exception
   {
      String selectSql, name = "", dataType, propTypeStr, errorMessage1="", errorMessage2="";
      int propId = 0;
      PropertyType propType;
      PreparedStatement selectStatement;
      ResultSet selectResults;
      TextFileProperties textFileProp;
      ArrayList<TextFileProperties> textFileProps = new ArrayList<>();	

      try
      {
	     // Prepare the SQL Statement
	     selectSql = "select tfpn.tfpn_id id, tfpn.tfpn_name name, ";
	     selectSql = selectSql + "tfpn_data_type dataType, tfpn_type propType ";
	     selectSql = selectSql + "from text_file_property_name tfpn ";
	     selectSql = selectSql + "where tfpn.tfh_id = ? ";
	     selectSql = selectSql + "order by tfpn.tfpn_id";
	  
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setInt(1, headerId);
	     selectResults  = selectStatement.executeQuery();
	     
	     // Get the results from the query
	     while(selectResults.next())
	     {
	    	propId      = selectResults.getInt("id");	
	        name        = selectResults.getString("name");
	        dataType    = selectResults.getString("dataType");
	        propTypeStr = selectResults.getString("propType");
	        // Convert from String to Enum
	        propType    = PropertyType.valueOf(propTypeStr);
	     
		    textFileProp = new TextFileProperties(headerId, propId, name, dataType, propType);
		    textFileProps.add(textFileProp);
	     }
	  
         selectResults.close();
	     selectStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading a Text File Property Name with Header Identifier: "+ headerId +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
	  return textFileProps;
	  
   }
   
   public void saveTextProperty(TextFileProperties tfp) throws Exception
   {
      int projectId = 0, headerId=0, propId=0, col;
	  String name, value, dataType, propTypeStr, insertSql, errorMessage1="", errorMessage2="";
	  PreparedStatement insertStatement;
	  PropertyType propType;
			
	  // Get the values from the GUI
	  try 
	  {
	     projectId = tfp.getProjectId(); 
	     headerId  = tfp.getHeaderId();
	     propId    = tfp.getPropId();
	     name      = tfp.getName();
	     value     = tfp.getValue();
	     dataType  = tfp.getDataType();
	     propType  = tfp.getType();
	     // Convert Enum to String
	     propTypeStr = propType.name();

	     // SQL Statements
	     insertSql = "insert into text_file_property (pc_id, tfh_id, tfp_id, tfp_name, ";
	     insertSql = insertSql + "tfp_value, tfp_data_type, tfp_type ) ";
		 insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);

	     col = 1;
	     insertStatement.setInt(col++, projectId);
	     insertStatement.setInt(col++, headerId);
	     insertStatement.setInt(col++, propId);
	     insertStatement.setString(col++, name);
	     insertStatement.setString(col++, value);
	     insertStatement.setString(col++, dataType);
	     insertStatement.setString(col++, propTypeStr);
			   
	     insertStatement.executeUpdate();
			
	     insertStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while inserting a new Text File Properties with Project Identifier: "+ projectId +"\n";
	     errorMessage2 = errorMessage2 + "and Header Identifier: "+ headerId +"\n";
	     errorMessage2 = errorMessage2 + "and Property Identifier: "+ propId +"\n";
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
			
   }
   
   public void deleteTextProperties(int projectId, int headerId) throws Exception
   {
      String deleteSql, errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
		   
	  try
	  {
	     deleteSql = "delete from text_file_property ";
		 deleteSql = deleteSql + "where pc_id = ? and ";
		 deleteSql = deleteSql + "tfh_id = ?";
		 deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.setInt(2, headerId);
		    
	     deleteStatement.executeUpdate();
		 deleteStatement.close();
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while deleting the Text File Properties from the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Header Identifier: " + headerId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	   
   }

   public int calculateDBMethodsCount(int projectId) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="";
      int dbMethodsCount=0;
      PreparedStatement selectStatement;
      ResultSet selectResults;

      try
      {
	     // Prepare the SQL Statement
	     selectSql = "select count(distinct cm.cm_package_name, cm.cm_class_name, cm.cm_short_method_name, ";
	     selectSql = selectSql + "cm.cm_parameter_method_name, cm.cm_return_type_1, cm.cm_return_type_2) dbMethodsCount ";
	     selectSql = selectSql + "from tree_structure ts, ";
	     selectSql = selectSql + "class_method cm ";
	     selectSql = selectSql + "where ";
	     selectSql = selectSql + "ts.pc_id = ? and ";
	     selectSql = selectSql + "cm.cm_return_type_1 <> 'Constructor' and ";
	     selectSql = selectSql + "ts.pc_id = cm.pc_id and ";
	     selectSql = selectSql + "ts.cm_id = cm.cm_id";	     

	  
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setInt(1, projectId);
	     selectResults  = selectStatement.executeQuery();
	     
	     // Get the results from the query
	     while(selectResults.next())
	     {
	        dbMethodsCount = selectResults.getInt("dbMethodsCount");	
	     }
	  
         selectResults.close();
	     selectStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Count of the Methods that perform Data Operations For the Project Id: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
	  return dbMethodsCount;
	  
   }   

   public ArrayList<SourceDirectory> loadSourceDirDBMethodsCount(int projectId) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="";
      int sdId=0, sdDBMetCount=0;	
      
      SourceDirectory sourceDirRecord;	
      ArrayList<SourceDirectory> sourceDirList = new ArrayList<>();	
      PreparedStatement selectStatement;
      ResultSet selectResults;
      
      sourceDirList.clear();

      try
      {
	     // Prepare the SQL Statement
	     selectSql = "select ";
	     selectSql = selectSql + "sd.sd_id sdId, ";
	     selectSql = selectSql + "count(distinct cm.cm_package_name, cm.cm_class_name, ";
	     selectSql = selectSql + "cm.cm_short_method_name, cm.cm_return_type_1, ";
	     selectSql = selectSql + "cm.cm_return_type_2, sd.sd_id) dbMetCount ";
	     selectSql = selectSql + "from tree_structure ts, ";
	     selectSql = selectSql + "class_method cm, ";
	     selectSql = selectSql + "source_directory sd ";
	     selectSql = selectSql + "where ts.pc_id = ? and ";
	     selectSql = selectSql + "cm.cm_return_type_1 <> 'Constructor' and ";
	     selectSql = selectSql + "ts.pc_id = cm.pc_id and ";
	     selectSql = selectSql + "ts.cm_id = cm.cm_id and ";
	     selectSql = selectSql + "cm.sd_id = sd.sd_id and ";
	     selectSql = selectSql + "sd.pc_id = cm.pc_id ";
	     selectSql = selectSql + "group by sd.sd_id";    
	  
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setInt(1, projectId);
	     selectResults  = selectStatement.executeQuery();
	     
	     // Get the results from the query
	     while(selectResults.next())
	     {
	        sdId         = selectResults.getInt("sdId");
	        sdDBMetCount = selectResults.getInt("dbMetCount");
	    	 
	        sourceDirRecord = new SourceDirectory(projectId, sdId, sdDBMetCount);
	        
	        sourceDirList.add(sourceDirRecord);
	        
	     }
	  
         selectResults.close();
	     selectStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Source Directories and DB Methods Count for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
	  return sourceDirList;
	  
   }   	
   
   public ArrayList<DatabaseMethod2> loadDBMethodsDetails(int projectId, String condition) throws Exception
   {

      String packageName="", className="", shortMethodName="";
      String returnType1="", returnType2="", selectSql1="", errorMessage1="", errorMessage2="";
	  DatabaseMethod2 DBMetRecord;
	  ArrayList<DatabaseMethod2> DBMetList = new ArrayList<DatabaseMethod2>();
	  PreparedStatement selectStatement1;
	  ResultSet selectResults1;
	   
	  try
	  {
	     // SQL Statement
	     selectSql1 = "select distinct cm.cm_package_name packageName, ";
	     selectSql1 = selectSql1 + "cm.cm_class_name className, ";
	     selectSql1 = selectSql1 + "cm.cm_short_method_name shortMethodName, ";
	     selectSql1 = selectSql1 + "cm.cm_return_type_1 returnType1, ";
	     selectSql1 = selectSql1 + "cm.cm_return_type_2 returnType2, ";
	     selectSql1 = selectSql1 + "sd.sd_id sdId ";
	     selectSql1 = selectSql1 + "from tree_structure ts, ";
	     selectSql1 = selectSql1 + "class_method cm, ";
	     selectSql1 = selectSql1 + "source_directory sd ";
	     selectSql1 = selectSql1 + "where ts.pc_id = ? and ";
	     selectSql1 = selectSql1 + "cm.cm_return_type_1 <> 'Constructor' and ";
	     selectSql1 = selectSql1 + "ts.pc_id = cm.pc_id and ";
	     selectSql1 = selectSql1 + "ts.cm_id = cm.cm_id and ";
	     selectSql1 = selectSql1 + "sd.sd_id " + condition + " and ";
	     selectSql1 = selectSql1 + "cm.sd_id = sd.sd_id and ";
	     selectSql1 = selectSql1 + "sd.pc_id = cm.pc_id";
	     	     
	  
	     selectStatement1 = dbCon.prepareStatement(selectSql1);
	     selectStatement1.setInt(1, projectId);
	     selectResults1 = selectStatement1.executeQuery();
		   
	     // Validate there are results in the query
	     while(selectResults1.next()) 
	     {	  
	        packageName     = selectResults1.getString("packageName");
	        className       = selectResults1.getString("className");
	        shortMethodName = selectResults1.getString("shortMethodName");
	        returnType1     = selectResults1.getString("returnType1");
	        returnType2     = selectResults1.getString("returnType2");
		 
	        DBMetRecord = new DatabaseMethod2(packageName, className, 
				                              shortMethodName, returnType1, returnType2);
	        DBMetList.add(DBMetRecord);
	         
	     }   
		   
	     selectResults1.close();
         selectStatement1.close();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the details of the Methods" + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	  
      return DBMetList;	   
	      
   }   
   
}
