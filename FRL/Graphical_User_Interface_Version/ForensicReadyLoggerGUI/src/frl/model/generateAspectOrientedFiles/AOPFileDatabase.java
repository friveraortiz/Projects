package frl.model.generateAspectOrientedFiles;

import java.io.*;
import java.sql.*;
import java.util.*;

//Package #8
//Class #2
public class AOPFileDatabase 
{
   // Atributes for the User Database Class	
   private List<AOPFile> aopFiles;
   private List<ConfigFile> configFiles;
   private List<ProgrammingLanguageDetail> progLangDets;
   
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   // Method #1
   public AOPFileDatabase()
   {
      aopFiles     = new LinkedList<AOPFile>();
      configFiles  = new LinkedList<ConfigFile>();
      progLangDets = new LinkedList<ProgrammingLanguageDetail>();
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
		 this.dbUser = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 this.dbUrl = prop.getProperty("dbUrl");
		 this.dbDriver = prop.getProperty("dbDriver");

	   } 
	   catch (IOException e1) 
	   {
	      errorMessage1 = e1.getMessage();
		  errorMessage2 ="Error XXXX: Occurred while loading the Database Configuration File."+ System.lineSeparator();
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
			    errorMessage1   = e2.getMessage();
				errorMessage2   = "Error XXXX: Occurred while closing the Database Configuration File."+ System.lineSeparator();
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
      {	 
	     return;
      }

      // Get the database parameters from the config.properties file
      try 
      {
         configureDBParameters(databaseConfigFile);
      }
      catch(Exception e1)
      {
	     errorMessage1   = e1.getMessage();
		 throw new Exception(errorMessage1);
      }

      // Loading the DB Driver
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e) 
	  {
		 // Error #1 
		 errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: The Database Driver was not found."+ System.lineSeparator();
		 errorMessage2 = errorMessage2 +"Error Message: "+errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
	  // Get the Connection
	  try 
	  {
		dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: The Database Connection could not be obtained."+ System.lineSeparator();
		 errorMessage2 = errorMessage2 +"Error Message: "+errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
   }
	
   // Disconnect to the FRL Database
   // Method #4
   public void disconnect() throws Exception
   {
      String errorMessage1 = "", errorMessage2="";	   
	  
      if (dbCon != null) 
	  {
	      try 
		  {
		     dbCon.close();
		  } 
	      catch (SQLException e) 
		  {
	    	 errorMessage1 = e.getMessage();
		     errorMessage2 = "Error XXXX: The Database Connection cannot be closed."+ System.lineSeparator();
		     errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		     throw new Exception(errorMessage2);
		  }
      }
		
      dbCon = null;
   }
   
  
   public void load() throws Exception 
   {
      int id;
      String sql, projectName, jarFileName, inputDir, outputDir, errorMessage1="", errorMessage2="";
      String plName="", dbmsName="", inputMethodName="", startMethod="", endMethod="", 
    		 connectMethod="", connectMethodReturnValue="",
             operatingSystem="";
      Statement selectStatement;
      ResultSet results;
	     
      try
      {
         configFiles.clear();
      
         // SQL Statements
	     sql = "select pc.pc_id id, pc.pc_project_name projectName, pc.pc_jar_filename jarFileName, ";
	     sql = sql + "pc.pc_project_input_dir inputDir, pc.pc_project_output_dir outputDir, "; 
	     sql = sql + "plh.plh_name plName, db.dbms_name dbmsName, im.im_name inputMethodName, ";
	     sql = sql + "pc.pc_start_project_method startMethod, pc.pc_end_project_method endMethod, ";
	     sql = sql + "pc.pc_connect_project_method connectMethod, ";
	     sql = sql + "pc.pc_connect_project_method_return_value connectMethodReturnValue, ";
	     sql = sql + "pc.pc_operating_system operatingSystem ";
	     sql = sql + "from project_configuration pc, programming_language_header plh, ";
	     sql = sql + "dbms db, input_method im ";
	     sql = sql + "where  pc.plh_id  = plh.plh_id and ";
	     sql = sql + "pc.dbms_id = db.dbms_id and ";
	     sql = sql + "pc.im_id   = im.im_id ";
	     sql = sql + "order by pc.pc_id";
	     
	     selectStatement = dbCon.createStatement();
	     results = selectStatement.executeQuery(sql);
		
	     while(results.next()) 
	     {
	        id              = results.getInt("id");
	        projectName     = results.getString("projectName");	
	        jarFileName     = results.getString("jarFileName");
	        inputDir        = results.getString("inputDir");
	        outputDir       = results.getString("outputDir");
	        plName          = results.getString("plName");
	        dbmsName        = results.getString("dbmsName");
	        inputMethodName = results.getString("inputMethodName");
	        startMethod     = results.getString("startMethod");
	        endMethod       = results.getString("endMethod");
	        connectMethod   = results.getString("connectMethod");
	        connectMethodReturnValue = results.getString("connectMethodReturnValue");
	        operatingSystem = results.getString("operatingSystem");
	        
		    ConfigFile configFile = new ConfigFile(id, projectName, jarFileName, inputDir, outputDir, 
		    		                               plName, dbmsName, inputMethodName, startMethod,
		    		                               endMethod, connectMethod, connectMethodReturnValue,
		    		                               operatingSystem);
		    configFiles.add(configFile);	
	     }
		
         results.close();
	     selectStatement.close();
      }
      catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Project Application Configuration."+ System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		 throw new Exception(errorMessage2);
      }
   }

   // Method #10
   // YA NO SE USA ESTE METODO
   public void loadAOPFile(int aopFileId) throws SQLException
   {
      String selectSql;
      int id;
      String fileName, path;
      String name, type, packageName;

	  // SQL Statements 
	  selectSql = "select tfh.tfh_id id, tfh.tfh_filename fileName, tfh.tfh_path path, ";
	  selectSql = selectSql + "tfh.tfh_name name, tfh.tfh_type type, tfh.tfh_package_name packageName "; 
	  selectSql = selectSql + "from text_file_header tfh ";
	  selectSql = selectSql + "where tfh.tfh_id=?"; 
	  
	  PreparedStatement selectStatement = dbCon.prepareStatement(selectSql);
	  selectStatement.setInt(1, aopFileId);

	  ResultSet selectResults = selectStatement.executeQuery();
	  selectResults.next();

	  id          = selectResults.getInt("id");
	  fileName    = selectResults.getString("fileName");	
	  path        = selectResults.getString("path");
	  name        = selectResults.getString("name");
	  type        = selectResults.getString("type");
	  packageName = selectResults.getString("packageName");
	  			
	  AOPFile aopFile = new AOPFile(id, fileName, path, name, AOPFileType.valueOf(type), packageName);
	  aopFiles.add(aopFile);
							
	  selectResults.close();
	  selectStatement.close();
	
   }
   
   // Add a new user
   // Method #11
   public void addConfigFile(ConfigFile configFile) 
   {
      configFiles.add(configFile); 
   }
   
   // Get a user
   // Method #12
   public List<AOPFile> getAOPFiles()
   {
	   return Collections.unmodifiableList(aopFiles);
   }
   
   public List<ConfigFile> getConfigFiles()
   {
	   return Collections.unmodifiableList(configFiles);
   }
   
   public ArrayList<ProgrammingLanguageHeader> loadProgLanguagesHeader() throws Exception 
   {
      // Declare ArrayList of Programming Languages
	  ArrayList<ProgrammingLanguageHeader> progLanguages = new ArrayList<ProgrammingLanguageHeader>();
	  String errorMessage1="", errorMessage2="";
	  
	  try 
	  {
	     // Select the current programming languages from the FRL Database
	     String sql = "select plh.plh_id id, plh.plh_name name ";
	            sql = sql + "from programming_language_header plh ";
	            sql = sql + "order by plh.plh_name asc" ;
	         
	     Statement selectStatement = dbCon.createStatement();
	     ResultSet results = selectStatement.executeQuery(sql);
		
	     while(results.next()) 
	     {
	        int id      = results.getInt("id");
		    String name = results.getString("name");
		 
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
		 errorMessage2 = "Error XXXX: Ocurred while loading the Programming Languages."+ System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return progLanguages;
      
   }
   
   public ArrayList<DBMS> loadDBMS() throws Exception 
   {
      String errorMessage1="", errorMessage2="";   
      // Declare ArrayList of DBMS
	  ArrayList<DBMS> dbms = new ArrayList<DBMS>();	 
	  
	  try 
	  {
	     // Select the current DBMS from the FRL Database
	     String sql = "select db.dbms_id id, db.dbms_name name ";
	            sql = sql + "from dbms db ";
	            sql = sql + "order by db.dbms_name asc" ;
	         
	     Statement selectStatement = dbCon.createStatement();
	     ResultSet results = selectStatement.executeQuery(sql);
		
	     while(results.next()) 
	     {
	        int id      = results.getInt("id");
		    String name = results.getString("name");
		 
	        DBMS dB = new DBMS(id, name); 
	     
	        // Add fields to the ArrayList.
	        dbms.add(dB); 
	     
	     }
		
	     results.close();
	     selectStatement.close();
	  }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the DBMS."+ System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return dbms;
      
   }
   
   public ArrayList<InputMethod> loadInputMethods() throws Exception 
   {
	  String errorMessage1="", errorMessage2="";  
      // Declare ArrayList of Input Methods
	  ArrayList<InputMethod> inMet = new ArrayList<InputMethod>();	 
	  
	  // Select the current Input Methods from the FRL Database
	  try 
	  {
	     String sql = "select im.im_id id, im.im_name name ";
	            sql = sql + "from input_method im ";
	            sql = sql + "order by im.im_name asc" ;
	         
	     Statement selectStatement = dbCon.createStatement();
	     ResultSet results = selectStatement.executeQuery(sql);
		
	     while(results.next()) 
	     {
	        int id      = results.getInt("id");
		    String name = results.getString("name");
		 
	        InputMethod iM = new InputMethod(id, name); 
	     
	        // Add fields to the ArrayList.
	        inMet.add(iM); 
	     
	     }
		
	     results.close();
	     selectStatement.close();
	  }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Input Methods."+ System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return inMet;
      
   }
   
   public List<ProgrammingLanguageDetail> getProgLangDet()
   {
	   return Collections.unmodifiableList(progLangDets);
   }
   
   // Method #9
   public void loadProgLangDet(String progLangName) throws Exception
   {
	  int id;
	  String name, value, errorMessage1="", errorMessage2=""; 	   
      progLangDets.clear();
      
      // SQL Statements
      try 
      {
	     String sql = "select pld.pld_id id, ";
	            sql = sql + "pld.pld_element_name name, pld.pld_element_value value ";
	            sql = sql + "from programming_language_detail pld, ";
	            sql = sql + "programming_language_header plh ";
	            sql = sql + "where plh.plh_name = ? and ";
	            sql = sql + "plh.plh_id = pld.plh_id ";
	            sql = sql + "order by pld.pld_id";
	  
         PreparedStatement selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setString(1, progLangName);
	     ResultSet results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        id    = results.getInt("id");
		    name  = results.getString("name");
		    value = results.getString("value");
		 
		    ProgrammingLanguageDetail progLangDet = new ProgrammingLanguageDetail(id, name, value);
		    progLangDets.add(progLangDet);
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
 	     errorMessage1 = e.getMessage();	
 	     errorMessage2 = "Error XXXX: Occurred while loading the details from the programming languages."+ System.lineSeparator();
 	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }

   }
   
   // Method #7	
   public int save(ConfigFile configFile) throws Exception
   {
      int plId=0, dbmsId=0, imId=0, count=0, col, id1=0, id2=0, id=0;
      String selectSql="", insertSql="", updateSql="", errorMessage1="", errorMessage2="",
             projectName="", jarFileName="", projectInputDir="", projectOutputDir="", 
             programmingLanguage="", dbms="", inputMethod="", startProjMethod="",
		     endProjMethod="", connectProjMethod="", connectProjMethodReturnValue="", 
		     operatingSystem="";
      PreparedStatement selectStatement = null, insertStatement, updateStatement;
      ResultSet selectResult;
		
	  // Get the values from the GUI
      id1                  = configFile.getId();
	  projectName          = configFile.getProjectName();
	  jarFileName          = configFile.getJarFileName();
	  projectInputDir      = configFile.getProjectInputDir();
	  projectOutputDir     = configFile.getProjectOutputDir();
	  programmingLanguage  = configFile.getProgrammingLanguage();
	  dbms                 = configFile.getDbms();
	  inputMethod          = configFile.getInputMethod();
	  startProjMethod      = configFile.getStartProjectMethod();
	  endProjMethod        = configFile.getEndProjectMethod();
	  connectProjMethod    = configFile.getConnectProjectMethod();
	  connectProjMethodReturnValue = configFile.getConnectProjectMethodReturnValue();
	  operatingSystem      = configFile.getOperatingSystem();
	  
	  // SQL Statements
	  
	  // Verify if the project already exists
	  try 
	  {
	     selectSql = "select count(*) as count ";
	     selectSql = selectSql +"from project_configuration pc ";
	     selectSql = selectSql +"where pc.pc_project_name = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);

	     selectStatement.setString(1, projectName);
	     selectResult = selectStatement.executeQuery();
	     selectResult.next();
	     count = selectResult.getInt(1);
	  }
	  catch (SQLException e1) 
	  {
	     errorMessage1 = e1.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while counting the records from the Project Configuration." + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
	  }
	  
	  // Obtain the value of the Id for the project configuration
	  try 
	  {
	     selectSql = "select max(pc.pc_id) id ";
	     selectSql = selectSql + "from project_configuration pc";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 
		 selectResult = selectStatement.executeQuery();
		 selectResult.next();
		 id2 = selectResult.getInt("id");
		 
		 id2 ++;
		 
		 id = id2;
	  } 
	  catch (SQLException e2) 
	  {
	     errorMessage1 = e2.getMessage();	
		 errorMessage2 = "Error XXXX: Occurred while loading the Maximum Identifier of the Project Configuration." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
	  // Obtain the value of the Programming Language Identifier
	  try 
	  {
	     selectSql = "select plh.plh_id id ";
	     selectSql = selectSql + "from programming_language_header plh ";
	     selectSql = selectSql + "where plh_name =?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 
		 selectStatement.setString(1, programmingLanguage);
		 selectResult = selectStatement.executeQuery();
		 selectResult.next();
		 plId = selectResult.getInt("id");
	   } 
	   catch (SQLException e3) 
	   {
	      errorMessage1 = e3.getMessage();	
		  errorMessage2 = "Error XXXX: Occurred while loading the Programming Language Identifier." + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }

	  // Obtain the value of the idDbms
	  try
	  {
	     selectSql = "select db.dbms_id id ";
	     selectSql = selectSql + "from dbms db ";
	     selectSql = selectSql + "where db.dbms_name =?";
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setString(1, dbms);
	     selectResult = selectStatement.executeQuery();
	     selectResult.next();
	     dbmsId = selectResult.getInt("id");
	  }
	  catch (SQLException e4)
	  {
	     errorMessage1 = e4.getMessage();	
		 errorMessage2 = "Error XXXX: Occurred while loading the DBMS Identifier." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
	  // Obtain the value of the Input Method Identifier
	  try
	  {
	     selectSql = "select im.im_id id ";
	     selectSql = selectSql + "from input_method im ";
	     selectSql = selectSql + "where im.im_name =?";
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setString(1, inputMethod);
	     selectResult = selectStatement.executeQuery();
	     selectResult.next();
	     imId = selectResult.getInt("id");
	  }
	  catch (SQLException e5)
	  {
	     errorMessage1 = e5.getMessage();	
		 errorMessage2 = "Error XXXX: Occurred while loading the Input Method Identifier." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
	  // Validate if the feature configuration already exists
	  if (count == 0) 
      {
	     // Inserting a new record into the project_configuration table
         try
         {
	        insertSql = "insert into project_configuration (pc_id, pc_project_name, pc_jar_filename, pc_project_input_dir, ";
	        insertSql = insertSql + "pc_project_output_dir, plh_id, dbms_id, im_id, pc_start_project_method, ";
	        insertSql = insertSql + "pc_end_project_method, pc_connect_project_method, pc_operating_system, pc_connect_project_method_return_value) ";
	        insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	        insertStatement = dbCon.prepareStatement(insertSql);
	  
            col = 1;
            
            id = id2;
            
            insertStatement.setInt(col++, id);
            insertStatement.setString(col++, projectName);
            insertStatement.setString(col++, jarFileName);
            insertStatement.setString(col++, projectInputDir);
            insertStatement.setString(col++, projectOutputDir );
            insertStatement.setInt(col++, plId);
            insertStatement.setInt(col++, dbmsId);
            insertStatement.setInt(col++, imId);
            insertStatement.setString(col++, startProjMethod);
            insertStatement.setString(col++, endProjMethod);
            insertStatement.setString(col++, connectProjMethod);
            insertStatement.setString(col++, operatingSystem);
            insertStatement.setString(col++, connectProjMethodReturnValue);
            			
            insertStatement.executeUpdate();
            insertStatement.close();
            			
	     }
	     catch (SQLException e6) 
	     {
	        errorMessage1 = e6.getMessage();	
		    errorMessage2 = "Error XXXX: Occurred while inserting a record into the Configuration table." + System.lineSeparator();
		    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		    throw new Exception(errorMessage2);
	     }
      }
      else
      {	  
         // Updating a record in the project_configuration table
         try
         {
	        updateSql = "update project_configuration ";
	        updateSql = updateSql + "set pc_project_name=?, ";
	        updateSql = updateSql + "pc_jar_filename=?, ";
	        updateSql = updateSql + "pc_project_input_dir=?, ";
	        updateSql = updateSql + "pc_project_output_dir=?, ";
	        updateSql = updateSql + "plh_id=?, ";
	        updateSql = updateSql + "dbms_id=?, ";
	        updateSql = updateSql + "im_id=?, ";
	        updateSql = updateSql + "pc_start_project_method=?, ";
	        updateSql = updateSql + "pc_end_project_method=?, ";
	        updateSql = updateSql + "pc_connect_project_method=?, ";
	        updateSql = updateSql + "pc_operating_system=?, ";
	        updateSql = updateSql + "pc_connect_project_method_return_value=? ";
	        updateSql = updateSql + "where pc_id = ?";
	        
	        updateStatement = dbCon.prepareStatement(updateSql);
			    
	        // Validate if the feature configuration already exists or not

		  	col = 1;
		  	id = id1;
			
		 	updateStatement.setString(col++, projectName);
		 	updateStatement.setString(col++, jarFileName);
		 	updateStatement.setString(col++, projectInputDir);
		 	updateStatement.setString(col++, projectOutputDir );
		 	updateStatement.setInt(col++, plId);
		 	updateStatement.setInt(col++, dbmsId);
		 	updateStatement.setInt(col++, imId);
		 	updateStatement.setString(col++, startProjMethod);
		 	updateStatement.setString(col++, endProjMethod);
		    updateStatement.setString(col++, connectProjMethod);
		    updateStatement.setString(col++, operatingSystem);
		    updateStatement.setString(col++, connectProjMethodReturnValue);
		    updateStatement.setInt(col++, id);
		    
		    updateStatement.executeUpdate();
				
		    updateStatement.close();

         }
	     catch (SQLException e7) 
	     {
	        errorMessage1 = e7.getMessage();	
		    errorMessage2 = "Error XXXX: Occurred while updating a record into the Configuration table." + System.lineSeparator();
		    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		    throw new Exception(errorMessage2);
	     }
      }
      
      // Closing the Select Statement Variable
	  try 
	  {
	     selectStatement.close();
	  } 
	  catch (SQLException e8) 
	  {
	     errorMessage1 = e8.getMessage();	
		 errorMessage2 = "Error XXXX: Occurred while closing the Select Statement Variable."+ System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
	  return id;
	   
   }
   
   public void deleteProjectInformation(int projectId) throws SQLException //5
   {
      String deleteSql="", errorMessage1="", errorMessage2="";	
      PreparedStatement deleteStatement;

	  // Delete the Properties of the Aspect Oriented Files
	  try 
	  {
         deleteSql = "delete from class_attribute ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Properties of the Aspect Oriented Files." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "in the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	  
	  // Delete the Properties of the Aspect Oriented Files
	  try 
	  {
         deleteSql = "delete from class_information ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e2)
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Properties of the Aspect Oriented Files." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "in the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }	  
      
	  // Delete the Properties of the Aspect Oriented Files
	  try 
	  {
         deleteSql = "delete from source_directory ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e3)
	  {
	     errorMessage1 = e3.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Properties of the Aspect Oriented Files." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "in the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }	  
	  
	  // Delete the Properties of the Aspect Oriented Files
	  try 
	  {
         deleteSql = "delete from text_file_property ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e4)
	  {
	     errorMessage1 = e4.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Properties of the Aspect Oriented Files." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "in the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	  
	  // Delete the Call-Hierarchy of the Methods that perform data changes
	  try 
	  {
         deleteSql = "delete from tree_structure ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e5)
	  {
	     errorMessage1 = e5.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Call-Hierarchy of the Methods that performs Data Changes." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "in the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }

	  // Delete the Methods that perform data changes
	  try 
	  {
         deleteSql = "delete from class_method ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e6)
	  {
	     errorMessage1 = e6.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Methods that performs Data Changes."+ System.lineSeparator();
		 errorMessage2 = errorMessage2 + "in the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }

	  // Delete the Project Configuration
	  try 
	  {
         deleteSql = "delete from project_configuration ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e7)
	  {
	     errorMessage1 = e7.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Project Configuration." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "in the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }	  
	  
	}
   
   // Delete a Project
   // Method #13
   public void deleteProject(int id)
   {
      configFiles.remove(id);
   }
   
   // Method #9
   public int validateProjectName(String projectName) throws Exception
   {
	  int records=0;
	  String sql="", errorMessage1="", errorMessage2=""; 	   
      
      // SQL Statements
      try 
      {
	     sql = "select count(*) records ";
	     sql = sql + "from project_configuration pc ";
	     sql = sql + "where pc.pc_project_name = ? ";
	     sql = sql + "group by pc.pc_project_name";
	  
         PreparedStatement selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setString(1, projectName);
	     ResultSet results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        records = results.getInt("records");
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
 	     errorMessage1 = e.getMessage();	
 	     errorMessage2 = "Error XXXX: Occurred while validating the Project: " + projectName + System.lineSeparator();
 	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return records;

   }
   
   // Method #9
   public int validateProjectJarFileName(String projectJarFileName) throws Exception
   {
	  int records=0;
	  String sql="", errorMessage1="", errorMessage2=""; 	   
      
      // SQL Statements
      try 
      {
	     sql = "select count(*) records ";
	     sql = sql + "from project_configuration pc ";
	     sql = sql + "where pc.pc_jar_filename = ? ";
	     sql = sql + "group by pc.pc_project_name";
	  
         PreparedStatement selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setString(1, projectJarFileName);
	     ResultSet results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        records = results.getInt("records");
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
 	     errorMessage1 = e.getMessage();	
 	     errorMessage2 = "Error XXXX: Occurred while validating the Jar File Name: "+ projectJarFileName + System.lineSeparator();
 	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return records;

   }
   
}
