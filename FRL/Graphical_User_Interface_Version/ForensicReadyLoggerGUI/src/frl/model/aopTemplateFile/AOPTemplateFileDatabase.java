package frl.model.aopTemplateFile;

import java.io.*;
import java.sql.*;
import java.util.*;

import frl.gui.aopTemplateFile.AOPTemplateFileFormEvent;
import frl.model.configureAspectOrientedFiles.TextFilePlain;
import frl.model.configureAspectOrientedFiles.TextFileProperties;
import frl.model.configureAspectOrientedFiles.TextFilePropertyName;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageHeader;
import frl.model.configureAspectOrientedFiles.FieldType;
import frl.model.configureAspectOrientedFiles.PropertyType;


public class AOPTemplateFileDatabase 
{

   private List<AOPTemplateFile> aOPTemplateFiles;
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   // Method #1
   public AOPTemplateFileDatabase()
   {
      aOPTemplateFiles = new LinkedList<AOPTemplateFile>();
   }
   
   // Methods of the Class
   // Method #2
   public void configureDBParameters(String databaseConfigFile) throws Exception
   {
      Properties prop = new Properties();
	  InputStream input = null;
	  String errorMessage1="", errorMessage2="";

	  try 
	  {
         // Specify the path of the configuration file
	     input = new FileInputStream(databaseConfigFile);
	     
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
		  // Error #1 
	      errorMessage1 = e1.getMessage();
		  errorMessage2 = "Error 8221: Occurred while loading the Database Configuration File." + System.lineSeparator();
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
				// Error #2 
			    errorMessage1 = e2.getMessage();
				errorMessage2 = "Error 8222: Occurred while closing the Database Configuration File." + System.lineSeparator();
				errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
				throw new Exception(errorMessage2);
				
			 }
		  }
	   }

   } 
   
   // Connect to the FRL Database
   // Method #3
   public void connect(String databaseConfigFile) throws Exception 
   {
      String errorMessage1="", errorMessage2="";	   

      if (dbCon != null)
	     return;

      // Get the database configuration parameters
      try
      {
         configureDBParameters(databaseConfigFile);
      }
      catch(Exception e1)
      {
 		 // Error #1
 		 errorMessage2 = e1.getMessage();
 	     throw new Exception(errorMessage2);  
      }
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e2) 
	  {
		 // Error #2
		 errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error 8231: The Database Driver was not found." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "+errorMessage1;
	     throw new Exception(errorMessage2);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
   }
	
   // Disconnect to the FRL Database
   // Method #4
   public void disconnect() throws Exception
   {
      String errorMessage1="", errorMessage2="";
      
	  if (dbCon != null) 
	  {
	     try 
		 {
		    dbCon.close();
		 } 
	     catch (Exception e) 
		 {
	    	 // Error #1 
	        errorMessage1 = e.getMessage();
		    errorMessage2 = "Error 8241: The Database Connection cannot be closed." + System.lineSeparator();
		    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		    throw new Exception(errorMessage2);
		 }
	  }
		
	   dbCon = null;
   }
   
   // Method #5
   public void addAOPTemplateFile(AOPTemplateFile aOPTemplateFile) 
   {
	   aOPTemplateFiles.add(aOPTemplateFile); 
   }
   
   // Method #6
   public List<AOPTemplateFile> getAOPTemplateFiles()
   {
      return Collections.unmodifiableList(aOPTemplateFiles);
   }
   
   // Method #7
   public void deleteAOPTemplateFile(int id)
   {
      aOPTemplateFiles.remove(id);
   }
   
   // Method #8
   public void load() throws Exception 
   {

      int id=0, programmingLanguageId=0;
      String fileName="", path="", name="", typeFile="", packageName="", programmingLanguageName="", pointCutName1="", pointCutName2="", 
    		 sql="", errorMessage1="", errorMessage2="";
      
      AOPTemplateFile aOPTemplateFile;
      Statement selectStatement;
      ResultSet results;
		 
      try
      {
         aOPTemplateFiles.clear();
      
         // SQL Statements
	     sql = "select tfh.tfh_id id, tfh.tfh_filename fileName, tfh.tfh_path path, ";
	     sql = sql + "tfh.tfh_name name, tfh.tfh_type typeFile, tfh.tfh_package_name packageName, "; 
	     sql = sql + "tfh.pl_id programmingLanguageId, plh.plh_name programmingLanguageName, ";
	     sql = sql + "tfh.tfh_pointcut_name_1 pointCutName1, tfh.tfh_pointcut_name_2 pointCutName2 ";
	     sql = sql + "from text_file_header tfh, programming_language_header plh ";
	     sql = sql + "where tfh.pl_id = plh.plh_id ";
	     sql = sql + "order by tfh.tfh_id";
	     
	     selectStatement = dbCon.createStatement();
		
	     results = selectStatement.executeQuery(sql);
		
	     while(results.next()) 
	     {
	        id            = results.getInt("id");
	        fileName      = results.getString("fileName");
	        path          = results.getString("path");
	        name          = results.getString("name");
	        typeFile      = results.getString("typeFile");
	        packageName   = results.getString("packageName");
	        programmingLanguageId   = results.getInt("programmingLanguageId");
	        programmingLanguageName = results.getString("programmingLanguageName");
	        pointCutName1 = results.getString("pointCutName1");
	        pointCutName2 = results.getString("pointCutName2");
		 
	        aOPTemplateFile = new AOPTemplateFile(id, fileName, path, name, typeFile, packageName, 
		                                          programmingLanguageId, programmingLanguageName, pointCutName1, pointCutName2);
	        aOPTemplateFiles.add(aOPTemplateFile);			
		 }
		
		 results.close();
		 selectStatement.close();
      }
      catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the AOP Template Files." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
      
   }
   
   // Method #9
   public ArrayList<ProgrammingLanguageHeader> loadProgLanguagesHeader() throws Exception 
   {
      // Declare ArrayList of Programming Languages
	  ArrayList<ProgrammingLanguageHeader> progLanguages = new ArrayList<ProgrammingLanguageHeader>();
	  String name="", sql="", errorMessage1="", errorMessage2="";
	  int id=0;
	  
	  try 
	  {
	     // Select the current programming languages from the FRL Database
	     sql = "select plh.plh_id id, plh.plh_name name ";
	     sql = sql + "from programming_language_header plh ";
	     sql = sql + "order by plh.plh_name asc" ;
	         
	     Statement selectStatement = dbCon.createStatement();
	     ResultSet results = selectStatement.executeQuery(sql);
		
	     while(results.next()) 
	     {
	        id   = results.getInt("id");
		    name = results.getString("name");
		 
	        ProgrammingLanguageHeader pL = new ProgrammingLanguageHeader(id, name); 
	     
	        // Add fields to the ArrayList.
	        progLanguages.add(pL); 
	     
	     }
		
	     results.close();
	     selectStatement.close();
	  }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Programming Languages." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return progLanguages;
      
   }   
   
   // Method #10
   public int getProgLanguageId(String programLanguageName) throws Exception 
   {
      // Declare ArrayList of Programming Languages
	  String sql="", errorMessage1="", errorMessage2="";
	  int programLanguageId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  try 
	  {
	     // Get the Programming Language Identifier
	     sql = "select plh.plh_id  programmingLanguageId ";
	     sql = sql + "from programming_language_header plh ";
	     sql = sql + "where plh.plh_name = ?" ;
	         
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setString(1, programLanguageName);
	     selectResults = selectStatement.executeQuery();
	     
		
	     while(selectResults.next()) 
	     {
	        programLanguageId = selectResults.getInt("programmingLanguageId");
	     }
		
	     selectResults.close();
	     selectStatement.close();
	     
	  }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while getting the Programming Language Identifier." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
	  return programLanguageId;
      
   }  
   
   // Method #11
   public int saveAOPTemplateFileHeader(AOPTemplateFileFormEvent aOPTemplateFile) throws Exception 
   {
	   // Get the values from the GUI
	   int id=0, programmingLanguageId=0, count=0, col=0;
	   String fileName="", path="", name="", typeFile="", packageName="", pointCutName1="", pointCutName2="", 
			  selectSql1="", insertSql="", updateSql="", errorMessage1="", errorMessage2="";

	   PreparedStatement selectStatement1, insertStatement, updateStatement;
	   ResultSet selectResult1;
		
	   try
	   {
	      // Get the values from the GUI
		  id          = aOPTemplateFile.getId();
		  fileName    = aOPTemplateFile.getFileName();
		  path        = aOPTemplateFile.getPath();
		  name        = aOPTemplateFile.getName();
		  typeFile    = aOPTemplateFile.getTypeFile();
		  packageName = aOPTemplateFile.getPackageName();
		  programmingLanguageId = aOPTemplateFile.getProgrammingLanguageId();
		  pointCutName1 = aOPTemplateFile.getPointCut1Name();
		  pointCutName2 = aOPTemplateFile.getPointCut2Name();
	      
	      // SQL Statements
	      selectSql1       = "select count(*) as count from text_file_header tfh where tfh_id=?";
	      selectStatement1 = dbCon.prepareStatement(selectSql1);
	      selectStatement1.setInt(1, id);
	      selectResult1    = selectStatement1.executeQuery();
	      selectResult1.next();
	      count            = selectResult1.getInt(1);

	      selectStatement1.close();
	   }
	   catch(Exception e1)
	   {
	      errorMessage1 = e1.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while counting the Aspect Oriented Template Files with the Identifier" + id + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
			    
	   // Validate if the AOPTemplate File already exists or not
	   if (count == 0) 
       {
		   
	      try
	      {
	         // Get the header Id
		     // Prepare the SQL Statement
		     selectSql1 = "select max(tfh.tfh_id) as tfhId ";
		     selectSql1 = selectSql1 + "from text_file_header tfh";
			  	  
		     selectStatement1 = dbCon.prepareStatement(selectSql1);
		     selectResult1 = selectStatement1.executeQuery();
			  
		     // Get the results from the query
		     if(selectResult1.next()) 
		        id = selectResult1.getInt("tfhId");
			  
		     // Increase the value of the header Id
		     id++;
	      }
		  catch(Exception e2)
		  {
		     errorMessage1 = e2.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while getting the Maximum Identfier from the Aspect Oriented Template Files" + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			 throw new Exception(errorMessage2);
		  }
		
		  try
		  {
		     insertSql = "insert into text_file_header (tfh_id, tfh_filename, tfh_path, tfh_name, ";
		     insertSql = insertSql + "tfh_type, tfh_package_name, pl_id, tfh_pointcut_name_1, tfh_pointcut_name_2) ";
		     insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ? )";
		     insertStatement = dbCon.prepareStatement(insertSql); 

             col = 1;
		     insertStatement.setInt(col++, id);
		     insertStatement.setString(col++, fileName);
		     insertStatement.setString(col++, path);
		     insertStatement.setString(col++, name);
		     insertStatement.setString(col++, typeFile);
		     insertStatement.setString(col++, packageName);
		     insertStatement.setInt(col++, programmingLanguageId);
		     insertStatement.setString(col++, pointCutName1);
		     insertStatement.setString(col++, pointCutName2);
			
		     insertStatement.executeUpdate();
		     insertStatement.close();
		  }
		  catch(Exception e3)
		  {
		     errorMessage1 = e3.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while inserting a new Aspect Oriented Template File with Header Identifier:" + id + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			 throw new Exception(errorMessage2);
		  }
			
	    } 
	    else 
		{
	    	try
	    	{
	 	       updateSql = "update text_file_header ";
		       updateSql = updateSql + "set tfh_filename = ?, ";
		       updateSql = updateSql + "tfh_path = ?, ";
		       updateSql = updateSql + "tfh_name = ? ,";
		       updateSql = updateSql + "tfh_type = ?, ";
		       updateSql = updateSql + "tfh_package_name = ?, ";
		       updateSql = updateSql + "pl_id = ?, ";
		       updateSql = updateSql + "tfh_pointcut_name_1 = ?, ";
		       updateSql = updateSql + "tfh_pointcut_name_2 = ? ";
		       updateSql = updateSql + "where tfh_id = ?";
		       updateStatement = dbCon.prepareStatement(updateSql);
			
		       col = 1;

		       updateStatement.setString(col++, fileName);
		       updateStatement.setString(col++, path);
			   updateStatement.setString(col++, name);
			   updateStatement.setString(col++, typeFile);
			   updateStatement.setString(col++, packageName);
			   updateStatement.setInt(col++, programmingLanguageId);
			   updateStatement.setString(col++, pointCutName1);
			   updateStatement.setString(col++, pointCutName2);
			   updateStatement.setInt(col++, id);

			   updateStatement.executeUpdate();
			   updateStatement.close();
	    	}
			catch(Exception e4)
			{
			   errorMessage1 = e4.getMessage();
			   errorMessage2 = "Error XXXX: Occurred while updating an Aspect Oriented Template File with Header Identifier: " + id + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   throw new Exception(errorMessage2);
			}	
	   }
	   
	   return id;
   } 
   
   // Method #12
   public void saveOneLineTextFilePlain(TextFilePlain tfp) throws Exception 
   {	
      int col, id=0, lineNumber=0;
	  String lineText="", insertSql="", errorMessage1="", errorMessage2="";	
	  PreparedStatement insertStatement;
	   
	  // Get the values from the GUI
	  id         = tfp.getHeaderId();
	  lineNumber = tfp.getLineNumber();
	  lineText   = tfp.getText();
		
	  try
	  {
	     // Build the insert statement
	     insertSql = "insert into text_file_plain (tfh_id , tfp_line_number, tfp_text) ";
	     insertSql = insertSql + "values (?, ?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);
	   
	     col = 1;
	     insertStatement.setInt(col++, id);
	     insertStatement.setInt(col++, lineNumber);
	     insertStatement.setString(col++, lineText);
						
	     insertStatement.executeUpdate();
	     insertStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new record in the Text File Plain with Header Identifier: " + id + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Line Number: " + lineNumber + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
   }
   
   // Method #13
   public void updateTextFilePlain(int headerId) throws Exception
   {
      int col=0;
      String updateSql="", errorMessage1="", errorMessage2="";
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
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while updating a Text File Plain with Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
	  
   }
   
   // Method #14
   public void saveTextFileDetails(int headerId) throws Exception
   {
      String selectSql="", insertSql="", text="", type="", errorMessage1="", errorMessage2="";
      int col=0, lineNumber=0, id=0;	
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
      }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading a Text File Plain with Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Line Number: " + lineNumber + System.lineSeparator();
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

         selectResults.close();
	     selectStatement.close();
	     insertStatement.close();
	  }
	  catch(Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new record into Text File Detail with Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Line Number: " + lineNumber + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
	  
   } 
   
   // Method #15
   public void deleteAOPTemplateFileHeader(int headerId) throws Exception
   {
      String deleteSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
		   
	  try
	  {
	     deleteSql = "delete from text_file_header ";
	     deleteSql = deleteSql + "where tfh_id = ?";
	  
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, headerId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting an Aspect Oriented Template File with Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
   }
   
   public void deleteAOPTemplateFilePlainDetails(int headerId) throws Exception 
   {
	  String deleteSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
	   
	  try 
	  {
         deleteSql = "delete from text_file_plain ";
	     deleteSql = deleteSql + "where tfh_id = ?";
	  
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, headerId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Plain Details of the Aspect Oriented Template File with Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
   }
   
   // Method #16
   public void deleteAOPTemplateFileDetails(int headerId) throws Exception
   {
      String deleteSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
		   
	  try 
	  {
         deleteSql = "delete from text_file_detail ";
	     deleteSql = deleteSql + "where tfh_id = ?";
	  
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, headerId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Details of the Aspect Oriented Template File with Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
   }
   
   // Method #17
   public void deleteAOPTemplateFileProperties(int projectId, int headerId) throws Exception
   {
      String deleteSql="", errorMessage1="", errorMessage2="";
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
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Properties of the Aspect Oriented Template File with Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
   }
   
   // Method #18
   public void deleteAOPTemplateFilePropertyNames(int headerId) throws Exception
   {
      String deleteSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
		   
	  try 
	  {
         deleteSql = "delete from text_file_property_name ";
   	     deleteSql = deleteSql + "where tfh_id = ?";
	  
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, headerId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Property Names of the Aspect Oriented Template File with Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
   }   
   
   
   // Method #19
   public void createAOPTemplateFileStructure(int projectId, int headerId) throws Exception
   {
      CallableStatement callableStatement;	
      String storeProcSql="", errorMessage1="", errorMessage2="";
      
      if(headerId == 1)
      {	  
         try
         {
            storeProcSql = "{call create_structure_AOP_template_file_1( " + projectId + " )}";
            callableStatement = dbCon.prepareCall(storeProcSql);
            callableStatement.execute();
            callableStatement.close();
         }
	     catch(Exception e1)
	     {
	        errorMessage1 = e1.getMessage();
		    errorMessage2 = "Error XXXX: Occurred while creating the Structure of the Aspect Oriented Template File with Identifier: 1 " + System.lineSeparator();
		    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		    throw new Exception(errorMessage2);
	     }	
      }
      else 
    	  if(headerId == 2)
          {
             try
             {
                storeProcSql = "{call create_structure_AOP_template_file_2( " + projectId + " )}";
                callableStatement = dbCon.prepareCall(storeProcSql);
                callableStatement.execute();
                callableStatement.close();
             }
	         catch(Exception e2)
	         {
	            errorMessage1 = e2.getMessage();
		        errorMessage2 = "Error XXXX: Occurred while creating the Structure of the Aspect Oriented Template File with Identifier: 2 " + System.lineSeparator();
		        errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		        throw new Exception(errorMessage2);
	         }
          }
          else
          {	  
             try
             {
                storeProcSql = "{call create_structure_AOP_template_file_3( " + projectId + " )}";
                callableStatement = dbCon.prepareCall(storeProcSql);
                callableStatement.execute();
                callableStatement.close();
             }
	         catch(Exception e3)
	         {
	            errorMessage1 = e3.getMessage();
		        errorMessage2 = "Error XXXX: Occurred while creating the Structure of the Aspect Oriented Template File with Identifier: 3 " + System.lineSeparator();
		        errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		        throw new Exception(errorMessage2);
	         }	
          }
      }   

   // Method #8
   public int getProjectIdentifier(String projectName) throws Exception 
   {

      String sql="", errorMessage1="", errorMessage2="";
      int projectId=0;
      
      PreparedStatement selectStatement;
      ResultSet selectResults;
		 
      try
      {
         // SQL Statement
	     sql = "select pc_id projectId ";
	     sql = sql + "from project_configuration pc "; 
	     sql = sql + "where pc.pc_project_name = ?";
	     
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setString(1, projectName);
	     selectResults = selectStatement.executeQuery();
		
	     while(selectResults.next()) 
	     {
	        projectId = selectResults.getInt("projectId");		
		 }
		
	     selectResults.close();
		 selectStatement.close();
      }
      catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while getting the Project Identifier from the Project Name: " + projectName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
      
      return projectId;
      
   } 
   
   public void updateAOPTemplateFileLineNumbers(int headerId) throws Exception 
   {
	   String selectSql="", updateSql="", errorMessage1="", errorMessage2="";
	   int col, i=0, newLineNumber=0, oldLineNumber=0;
	   PreparedStatement updateStatement, selectStatement;
	   ResultSet selectResults;
			 
	   try
	   {
	      // SQL Statement
		  selectSql = "select tfd.tfd_line_number oldLineNumber ";
		  selectSql = selectSql + "from text_file_detail tfd "; 
		  selectSql = selectSql + "where tfd.tfh_id = ? ";
		  selectSql = selectSql + "order by tfd.tfd_line_number asc";
		   
		  selectStatement = dbCon.prepareStatement(selectSql);
		  selectStatement.setInt(1, headerId);
		  selectResults = selectStatement.executeQuery();
		  
		  while(selectResults.next()) 
		  {
		     oldLineNumber = selectResults.getInt("oldLineNumber");	
		     
		     i++;
			     
		     if(oldLineNumber != i)
		     {	
		        newLineNumber = i;	 
		        	
		 	    try 
		 	    {
		 	       // Prepare the SQL Statement
		 	       updateSql = "update text_file_detail tfd ";
		 	       updateSql = updateSql + "set tfd.tfd_line_number = ? ";
		 	       updateSql = updateSql + "where tfd.tfh_id = ? and ";
		 	       updateSql = updateSql + "tfd.tfd_line_number = ?";

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
		 		   errorMessage2 = "Error XXXX: Occurred while updating the AOP Template File with the Header Identifier: " + headerId + System.lineSeparator();
		 		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 		   throw new Exception(errorMessage2);
				}
		     }	     
	      }
				
		  selectResults.close();
		  selectStatement.close();
	   }
	   catch(Exception e2)
	   {
	      errorMessage1 = e2.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while loading the Line Numbers from the AOP Template File with Header Identifier: " + headerId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	    }		
		  
   }
   
   // Method #14
   public void saveTextFileProperty(TextFileProperties textFileProperties) throws Exception
   {
      String insertSql="", errorMessage1="", errorMessage2="", name="", value="", dataType="";
      int col=0, projectId=0, headerId=0, propId=0;
      PreparedStatement insertStatement;
      PropertyType type;
      
      // Get the Data from the GUI
      projectId = textFileProperties.getProjectId();
      headerId  = textFileProperties.getHeaderId();
      propId    = textFileProperties.getPropId();
      name      = textFileProperties.getName();
      value     = textFileProperties.getValue();
      dataType  = textFileProperties.getDataType();
      type      = textFileProperties.getType();
      
	  try
	  {
	     // Build the insert statement
	     insertSql = "insert into text_file_property(pc_id, tfh_id, tfp_id, tfp_name, ";
	     insertSql = insertSql + "tfp_value, tfp_data_type, tfp_type) ";
	     insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);
	  				
		 col = 1;		
		 insertStatement.setInt(col++, projectId);
		 insertStatement.setInt(col++, headerId);
		 insertStatement.setInt(col++, propId);      
		 insertStatement.setString(col++, name);
		 insertStatement.setString(col++, value);
		 insertStatement.setString(col++, dataType);
		 insertStatement.setString(col++, type.name());
		 insertStatement.executeUpdate();

	     insertStatement.close();
	  }
	  catch(Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new record into Text File Property for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Property Identifier: " + propId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
	  
   }    
   
   // Method #14
   public void saveTextFilePropertyName(TextFilePropertyName textFilePropertyName) throws Exception
   {
      String insertSql="", errorMessage1="", errorMessage2="", name="", dataType="";
      int col=0, headerId=0, propId=0;
      PreparedStatement insertStatement;
      PropertyType type;
      
      // Get the Data from the GUI
      headerId  = textFilePropertyName.getHeaderId();
      propId    = textFilePropertyName.getPropId();
      name      = textFilePropertyName.getName();
      dataType  = textFilePropertyName.getDataType();
      type      = textFilePropertyName.getType();
      
	  try
	  {
	     // Build the insert statement
	     insertSql = "insert into text_file_property_name(tfh_id, tfpn_id, tfpn_name, ";
	     insertSql = insertSql + "tfpn_data_type, tfpn_type) ";
	     insertSql = insertSql + "values (?, ?, ?, ?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);
	  				
		 col = 1;		
		 insertStatement.setInt(col++, headerId);
		 insertStatement.setInt(col++, propId);      
		 insertStatement.setString(col++, name);
		 insertStatement.setString(col++, dataType);
		 insertStatement.setString(col++, type.name());
		 insertStatement.executeUpdate();

	     insertStatement.close();
	  }
	  catch(Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new record into Text File Property Name with Header Identifier: " + headerId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Property Identifier: " + propId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }	
	  
   }       
      
}
