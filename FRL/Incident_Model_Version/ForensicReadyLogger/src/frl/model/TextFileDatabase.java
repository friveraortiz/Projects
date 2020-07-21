package frl.model;

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
   private void configureDBParameters(String propFileName) //2
   {
      Properties prop = new Properties();
	  InputStream input = null;

	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(propFileName);

		 // Load the properties file
	     prop.load(input);

	     // Get the values from the properties file
	     this.dbUser = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 prop.getProperty("dbPort");
		 this.dbUrl = prop.getProperty("dbUrl");
		 this.dbDriver = prop.getProperty("dbDriver");

	  } 
	  catch (IOException ex) 
	  {
	     String errorMessage = ex.getMessage();
	     System.out.println("Error XXXX: Occurred while loading the Configuration File. Error Message: " + errorMessage);
	  } 
	  finally 
	  {
	     if (input != null) 
		 {
	        try 
			{
		       input.close();
		    } 
			catch (IOException e) 
			{
		       String errorMessage = e.getMessage();
			   System.out.println("Error XXXX: Occurred while closing the Configuration File. Error Message: " + errorMessage);
		    }
		  }
	   }

   }  
	
   // Connect to the ForensicReadyLogger Database
   public void connect(String propFileName) throws Exception //3
   {

      if (dbCon != null)
	  return;

      // Get the database parameters from the config.properties file
      configureDBParameters(propFileName);
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e) 
	  {
		 String errorMessage = e.getMessage(); 
	     throw new Exception("Error XXXX: Occurred because the Database Driver was not found. Error Message: "+errorMessage);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
	}
	
    // Disconnect to the ForensicReadyLogger Database
	public void disconnect() //4
	{
	   if (dbCon != null) 
	   {
	      try 
		  {
		     dbCon.close();
		  } 
	      catch (SQLException e) 
		  {
	         String errorMessage = e.getMessage();   
		     System.out.println("Error XXXX: Occurred because the Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("Forensic Ready Logger Database Disconnected");
	 }

   	
   public void saveOneLineTextFilePlain(TextFilePlain tfp) throws SQLException 
   {
		
	   int col, lineNumber;
	   String lineText, insertSql;	
	   PreparedStatement insertStatement;
	   
	   // Get the values from the GUI
	   lineNumber  = tfp.getLineNumber();
	   lineText = tfp.getText();
		
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
   
   public void deleteTextFilePlain() throws SQLException 
   {
	  String deleteSql;
	  PreparedStatement deleteStatement;
	   
      deleteSql = "delete from text_file_plain ";
	  deleteSql = deleteSql + "where tfp_line_number is not null ";
	  deleteStatement = dbCon.prepareStatement(deleteSql);
	    
      deleteStatement.executeUpdate();
	  deleteStatement.close();
	  
   } 
   
   public void updateTextFilePlain(int headerId) throws SQLException
   {
      int col;
      
      String updateSql;
      PreparedStatement updateStatement;
      
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
   
   public void saveTextFileDetails(int headerId) throws SQLException
   {
		
      String selectSql, insertSql, text, type;
      int col, lineNumber, id;	
      PreparedStatement selectStatement, insertStatement;
      ResultSet selectResults;
      
  	  // Prepare the SQL Statement
	  selectSql = "select tfh_id, tfp.tfp_line_number, tfp.tfp_text ";
	  selectSql = selectSql + "from text_file_plain tfp ";
	  selectSql = selectSql + "where tfh_id = ? ";
	  selectSql = selectSql + "order by tfp.tfp_line_number";
	  	  
	  selectStatement = dbCon.prepareStatement(selectSql);
	  selectStatement.setInt(1, headerId);
	  selectResults = selectStatement.executeQuery();
      
	  // Build the insert statement
	  insertSql = "insert into text_file_details(tfh_id, tfd_line_number, ";
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
   
   public int getProgrammingLanguageId(String programmingLanguage) throws SQLException
   {
	   
      String selectSql;
      int plId = 0;
      PreparedStatement selectStatement;
      ResultSet selectResults;
      
	  // Prepare the SQL Statement
	  selectSql = "select pl_id ";
	  selectSql = selectSql + "from programming_language pl ";
	  selectSql = selectSql + "where pl.pl_name = ?";
		  	  
	  selectStatement = dbCon.prepareStatement(selectSql);
	  selectStatement.setString(1, programmingLanguage);
	  selectResults  = selectStatement.executeQuery();
		  
	  // Get the results from the query
	  if(selectResults.next()) 
	     plId = selectResults.getInt("pl_id");
	  
	  
      selectResults.close();
	  selectStatement.close();
	  
      return plId;	  
   }
   
   public int saveTextFileHeader(TextFileHeader tfh) throws SQLException
   {
		
      String selectSql1, insertSql, fileName, name;
      String path, packageName, programmingLanguage, typeStr;	
      int col, headerId = 0, plId = 0;	
      FileType type;
      PreparedStatement selectStatement1, insertStatement;
      ResultSet selectResults1;
      
      // Get the information from the GUI
      fileName = tfh.getFileName();
      path = tfh.getPath();
      name = tfh.getName();
      type = tfh.getType();
      
      // Convert Enum to String
      typeStr = type.name();
      
      packageName = tfh.getPackageName();
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
	  
	     
      selectResults1.close();
	  selectStatement1.close();
	  
	  insertStatement.close();
	  
      return headerId;	  
   }    

   public void deleteTextFileDetails() throws SQLException
   {
      String deleteSql;
	  PreparedStatement deleteStatement;
		   
	  deleteSql = "delete from text_file_details ";
	  deleteSql = deleteSql + "where tfh_id >= 0";
	  
	  deleteStatement = dbCon.prepareStatement(deleteSql);
	  deleteStatement.executeUpdate();
	  
	  deleteStatement.close();	   
	  
   }
   
   public void deleteTextFile() throws SQLException
   {
      String deleteSql;
	  PreparedStatement deleteStatement;
		   
	  deleteSql = "delete from text_file_header ";
	  deleteSql = deleteSql + "where tfh_id >= 0";
	  
	  deleteStatement = dbCon.prepareStatement(deleteSql);
	  deleteStatement.executeUpdate();
	  
	  deleteStatement.close();	   
	   
   }
   
	public ArrayList<TextFileDetails> getTextFile(int inputHeaderId) throws SQLException
	{
		
	   int headerId, lineNumber, propertyId;
	   String text, typeStr;
	   FieldType type;
	   TextFileDetails textFileDet = null;
	   ArrayList<TextFileDetails> textFileDets = new ArrayList<>();
		
	   // SQL Statements
	   String selectSql1 = "select tfd.tfh_id, tfd.tfd_line_number, tfd.tfd_text, ";
	   selectSql1 = selectSql1 +"tfd.tfd_type, tfd.tfp_id ";
	   selectSql1 = selectSql1 + "from text_file_details tfd, text_file_header tfh ";
	   selectSql1 = selectSql1 + "where tfh.tfh_id = ? and ";
	   selectSql1 = selectSql1 + "tfh.tfh_id = tfd.tfh_id";
			   
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
     
       selectStatement1.setInt(1, inputHeaderId);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   
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
	   
       return textFileDets;
      
	}
	
	public int getHeaderId(String textFileFileName, String programmingLanguage) throws SQLException
	{
	   String selectSql;
	   int headerId = 0;
	   PreparedStatement selectStatement;
	   ResultSet selectResults;
	      
	   // Prepare the SQL Statement #1
	   selectSql = "select tfh.tfh_id ";
	   selectSql = selectSql + "from text_file_header tfh, ";
	   selectSql = selectSql + "programming_language pl ";
	   selectSql = selectSql + "where tfh.tfh_filename = ? and ";
	   selectSql = selectSql + "pl.pl_name = ? and ";
	   selectSql = selectSql + "tfh.pl_id = pl.pl_id";
	   
	   selectStatement = dbCon.prepareStatement(selectSql);
	   selectStatement.setString(1, textFileFileName);
	   selectStatement.setString(2, programmingLanguage);
	   selectResults = selectStatement.executeQuery();
		  
	   // Get the results from the query
	   if(selectResults.next()) 
	      headerId = selectResults.getInt("tfh_id");
	   
	   selectResults.close();
	   selectStatement.close();
	   
	   return headerId;	
	}
	   
   public void updateTextFileDetails(TextFileDetails tfd) throws SQLException
   {

	  String updateSql, text, typeStr;
      int col, headerId, lineNum, propId;
      FieldType type;
	  PreparedStatement updateStatement;
	  
	  // Get the values from the GUI
	  text        = tfd.getText();
	  type        = tfd.getType();
	  
	  // Convert enum to string
	  typeStr    = type.toString();
	  headerId   = tfd.getHeaderId();
	  lineNum    = tfd.getLineNumber();
	  propId     = tfd.getPropertyId();
	      
	  // Prepare the SQL Statement
	  updateSql = "update text_file_details tfd ";
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
   
   public void deleteTextFileDetailsLineNum(int headerId, int lineNumber) throws SQLException
   {
      String deleteSql;
	  PreparedStatement deleteStatement;
		   
	  deleteSql = "delete from text_file_details ";
	  deleteSql = deleteSql + "where tfh_id = ? and ";
	  deleteSql = deleteSql + "tfd_line_number =?";
	  
	  deleteStatement = dbCon.prepareStatement(deleteSql);
	  deleteStatement.setInt(1, headerId);
	  deleteStatement.setInt(2, lineNumber);
	  deleteStatement.executeUpdate();
	  
	  deleteStatement.close();	   
	   
   }
   
   public void updateTextFileDetailsLineNumbers(int headerId, 
		                                        int oldLineNumber, 
		                                        int newLineNumber) throws SQLException
   {

     String updateSql;
     int col;
     PreparedStatement updateStatement;

     // Prepare the SQL Statement
     updateSql = "update text_file_details tfd ";
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
   
   public void deleteTextFileProperties() throws SQLException
   {
      String deleteSql;
	  PreparedStatement deleteStatement;
		   
	  deleteSql = "delete from text_file_properties ";
	  deleteSql = deleteSql + "where tfh_id is not null";
	  
	  deleteStatement = dbCon.prepareStatement(deleteSql);
	  deleteStatement.executeUpdate();
	  
	  deleteStatement.close();	   
	   
   }
   
   public int getMaxPropertyId(int headerId) throws SQLException
   {
		
      String selectSql1;
      int propId = 0;
      PreparedStatement selectStatement1;
      ResultSet selectResults1;
      
	  // Get the maximum value for the 
  	  // Prepare the SQL Statement
	  selectSql1 = "select max(tfp.tfp_id)+1 as prop_id ";
	  selectSql1 = selectSql1 + "from text_file_properties tfp ";
	  selectSql1 = selectSql1 + "where tfp.tfh_id = ?";
	  
	  selectStatement1 = dbCon.prepareStatement(selectSql1);
	  selectStatement1.setInt(1, headerId);
	  selectResults1 = selectStatement1.executeQuery();
	  
	  // Get the results from the query
	  if(selectResults1.next()) 
	     propId = selectResults1.getInt("prop_id");

	  if(propId == 0)
	     propId = 1;
	  
      selectResults1.close();
	  selectStatement1.close();
	  
      return propId;	  
   } 
   
   public void createStructureTextFileDetails() throws SQLException
   {
      CallableStatement callableStatement;	 
      String storeProcSql = "{call create_structure_text_file_header_1()}";
      
      callableStatement = dbCon.prepareCall(storeProcSql);
      callableStatement.execute();
      
      storeProcSql = "{call create_structure_text_file_header_2()}";
      
      callableStatement = dbCon.prepareCall(storeProcSql);
      callableStatement.execute();
      
   }
   
   public TextFileProperties getPropertyValue(int headerId, int propertyId) throws SQLException
   {
	   
      String selectSql, name, value, dataType, propTypeStr;
      PropertyType propType;
      PreparedStatement selectStatement;
      ResultSet selectResults;
      TextFileProperties tfp = null;
      
	  // Prepare the SQL Statement
	  selectSql = "select tfp.tfp_property_name, tfp.tfp_property_value, ";
	  selectSql = selectSql + "tfp.tfp_data_type, tfp.tfp_type ";
	  selectSql = selectSql + "from text_file_properties tfp ";
	  selectSql = selectSql + "where tfp.tfh_id = ? and ";
	  selectSql = selectSql + "tfp.tfp_id = ?";
	  
	  selectStatement = dbCon.prepareStatement(selectSql);
	  selectStatement.setInt(1, headerId);
	  selectStatement.setInt(2, propertyId);
	  selectResults  = selectStatement.executeQuery();
		  
	  // Get the results from the query
	  if(selectResults.next())
	  {
		 name        = selectResults.getString("tfp_property_name"); 
	     value       = selectResults.getString("tfp_property_value");	
	     dataType    = selectResults.getString("tfp_data_type");
	     propTypeStr = selectResults.getString("tfp_type");
	     
	     // Convert string to Enum
	     propType = PropertyType.valueOf(propTypeStr);
	     
	     tfp = new TextFileProperties(name, value, dataType, propType);
	  }

	  
      selectResults.close();
	  selectStatement.close();
	  
      return tfp;	  
   }
   
   public ArrayList<TextFileProperties> loadPropertyNames(int headerId) throws SQLException
   {
	   
      String selectSql, name = "";
      int propId = 0;
      PreparedStatement selectStatement;
      ResultSet selectResults;
      TextFileProperties textFileProp;
      ArrayList<TextFileProperties> textFileProps = new ArrayList<>();	

	  // Prepare the SQL Statement
	  selectSql = "select tfp.tfp_id, tfp.tfp_property_name ";
	  selectSql = selectSql + "from text_file_properties tfp ";
	  selectSql = selectSql + "where tfp.tfh_id = ? ";
	  selectSql = selectSql + "order by tfp.tfp_id";
	  
	  selectStatement = dbCon.prepareStatement(selectSql);
	  selectStatement.setInt(1, headerId);
	  selectResults  = selectStatement.executeQuery();
	  
	  // Get the results from the query
	  while(selectResults.next())
	  {
	     propId = selectResults.getInt("tfp_id");	
	     name   = selectResults.getString("tfp_property_name");
	     
		 textFileProp = new TextFileProperties(headerId, propId, name);
		 textFileProps.add(textFileProp);
	  }
	  
      selectResults.close();
	  selectStatement.close();
	  
	  return textFileProps;
	  
   }
   
   public void updatePropertyNames(int headerId, int propId, String value) throws SQLException
   {
	   
      String updateSql;
      int col;
      PreparedStatement updateStatement = null;

      // Prepare the SQL Statement
      updateSql = "update text_file_properties tfp ";
      updateSql = updateSql + "set tfp_property_value =? ";
      updateSql = updateSql + "where tfp.tfh_id =? and ";
      updateSql = updateSql + "tfp.tfp_id =?";
      
	  // Prepare the SQL Statement
	  updateStatement = dbCon.prepareStatement(updateSql);

	  col = 1;
	  updateStatement.setString(col++, value);
	  updateStatement.setInt(col++, headerId);
	  updateStatement.setInt(col++, propId);
	     
      updateStatement.executeUpdate();
      updateStatement.close();

   }  
   
	   
}
