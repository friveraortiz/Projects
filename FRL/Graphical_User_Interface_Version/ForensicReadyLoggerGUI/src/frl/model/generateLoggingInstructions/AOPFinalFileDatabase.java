package frl.model.generateLoggingInstructions;

import java.io.*;
import java.sql.*;
import java.util.*;

import frl.model.configuration.ClassMethod;
import frl.model.configureAspectOrientedFiles.FieldType;
import frl.model.configureAspectOrientedFiles.FileType;
import frl.model.configureAspectOrientedFiles.PropertyType;
import frl.model.configureAspectOrientedFiles.TextFileDetails;
import frl.model.configureAspectOrientedFiles.TextFileHeader;
import frl.model.configureAspectOrientedFiles.TextFileProperties;
import frl.model.generateAspectOrientedFiles.ConfigFile;
import frl.model.generateAspectOrientedFiles.DBMS;
import frl.model.generateAspectOrientedFiles.InputMethod;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageDetail;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageHeader;
import frl.model.loadUMLSequenceDiagram.AnnotationType;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterDataSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramFinal;
import frl.model.loadUMLSequenceDiagram.UserSequenceDiagram;


public class AOPFinalFileDatabase 
{
   // Atributes for the User Database Class	
   //private List<AOPFile> aopFiles;
   private List<ConfigFile> configFiles;
   private List<ProgrammingLanguageDetail> progLangDets;
   
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   // Method #1
   public AOPFinalFileDatabase()
   {
      //aopFiles     = new LinkedList<AOPFile>();
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
		  errorMessage2 ="Error XXXX: Occurred while loading the Database Configuration File.\n";
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
				errorMessage2   = "Error XXXX: Occurred while closing the Database Configuration File.\n";
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
		 errorMessage2 = "Error XXXX: The Database Driver was not found.\n";
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
		 errorMessage2 = "Error XXXX: The Database Connection could not be obtained.\n";
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
		     errorMessage2 = "Error XXXX: The Database Connection cannot be closed.\n";
		     errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		     throw new Exception(errorMessage2);
		  }
      }
		
      dbCon = null;
   }

   public List<ConfigFile> getConfigFiles()
   {
      return Collections.unmodifiableList(configFiles);
   }

   public void loadProjects() throws Exception 
   {
      int id=0;
      String sql="", projectName="", jarFileName="", inputDir="", outputDir="", errorMessage1="", errorMessage2="";
      String plName="", dbmsName="", inputMethodName="", startMethod="", endMethod="", connectMethod="", 
             connectMethodReturnValue="",operatingSystem="";
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
		 errorMessage2 = "Error XXXX: Ocurred while loading the Project Application Configuration.\n";
		 errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		 throw new Exception(errorMessage2);
      }
   }
   
   public List<ProgrammingLanguageDetail> getProgLangDet()
   {
	   return Collections.unmodifiableList(progLangDets);
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
		 errorMessage2 = "Error XXXX: Ocurred while loading the Programming Languages.\n";
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
		 errorMessage2 = "Error XXXX: Ocurred while loading the DBMS.\n";
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
		 errorMessage2 = "Error XXXX: Ocurred while loading the Input Methods.\n";
		 errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return inMet;
   
   } 


   // Method #9
   public void loadProgLangDet(String progLangName) throws Exception
   {
      int id;
	  String name, value, sql="", errorMessage1="", errorMessage2=""; 
	  ProgrammingLanguageDetail progLangDet;
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
	     
      progLangDets.clear();
   
      // SQL Statements
      try 
      {
	     sql = "select pld.pld_id id, ";
	     sql = sql + "pld.pld_element_name name, pld.pld_element_value value ";
	     sql = sql + "from programming_language_detail pld, ";
	     sql = sql + "programming_language_header plh ";
	     sql = sql + "where plh.plh_name = ? and ";
	     sql = sql + "plh.plh_id = pld.plh_id ";
	     sql = sql + "order by pld.pld_id";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setString(1, progLangName);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        id    = results.getInt("id");
		    name  = results.getString("name");
		    value = results.getString("value");
		 
		    progLangDet = new ProgrammingLanguageDetail(id, name, value);
		    progLangDets.add(progLangDet);
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the details from the programming languages.\n";
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
   }
   
   public ArrayList<UserSequenceDiagram> loadUsers(int projectId) throws Exception
   {
      int userId=0;
	  String sql="", errorMessage1="", errorMessage2="", userName=""; 
	  UserSequenceDiagram userRecord;	
	  ArrayList<UserSequenceDiagram> userList = new ArrayList<UserSequenceDiagram>();	
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statements
      try 
      {
	     sql = "select distinct usdf.usd_id userId, ";
	     sql = sql + "usd.usd_name userName ";
	     sql = sql + "from uml_sequence_diagram_final usdf, ";
	     sql = sql + "user_sequence_diagram usd ";
	     sql = sql + "where usdf.pc_id = ? and ";
	     sql = sql + "usdf.usdf_annotation_type in('Method', 'ReturnType', 'Parameter') and ";
	     sql = sql + "usdf.usdf_annotation_details = 'Y' and ";
	     sql = sql + "usdf.pc_id = usd.pc_id and ";
	     sql = sql + "usdf.usd_id = usd.usd_id ";
	     sql = sql + "order by usdf.usd_id";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        userId    = results.getInt("userId");
	        userName  = results.getString("userName");
		 
	        // Build the Record
		    userRecord = new UserSequenceDiagram(projectId, userId, userName);
		     
	        // Add the Record to the ArrayList.
	        userList.add(userRecord); 
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Users with Annotations from the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return userList;
   }   
   
   public ArrayList<MethodSequenceDiagram> loadMethods(int projectId, int userId) throws Exception
   {
      int methodId=0;
	  String sql="", errorMessage1="", errorMessage2="", methodOwnerName="", methodPackageName="", methodClassName="", 
			 methodShortName="", methodFullName=""; 
	  MethodSequenceDiagram methodRecord;	
	  ArrayList<MethodSequenceDiagram> methodList = new ArrayList<MethodSequenceDiagram>();	
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statements
      try 
      {
	     sql = "select msd.msd_id methodId, ";
	     sql = sql + "msd.msd_owner methodOwnerName, ";
	     sql = sql + "msd.msd_package_name methodPackageName, ";
	     sql = sql + "msd.msd_class_name methodClassName, ";
	     sql = sql + "msd.msd_short_name methodShortName, ";
	     sql = sql + "concat(msd.msd_owner, '.', msd.msd_short_name) methodFullName ";
	     sql = sql + "from uml_sequence_diagram_final usdf, ";
	     sql = sql + "method_sequence_diagram msd ";
	     sql = sql + "where usdf.pc_id = ? and ";
	     sql = sql + "usdf.usd_id = ? and ";
	     sql = sql + "usdf.usdf_annotation_type in('Method', 'ReturnType', 'Parameter') and ";
	     sql = sql + "usdf.usdf_annotation_details = 'Y' and ";
	     sql = sql + "usdf.pc_id = msd.pc_id and ";
	     sql = sql + "usdf.msd_id = msd.msd_id ";
	     sql = sql + "order by usdf.msd_id";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, userId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        methodId           = results.getInt("methodId");
	        methodOwnerName    = results.getString("methodOwnerName");
	        methodPackageName  = results.getString("methodPackageName");
	        methodClassName    = results.getString("methodClassName");
	        methodShortName    = results.getString("methodShortName");
	        methodFullName     = results.getString("methodFullName");
		 
	        // Build the Record
		    methodRecord = new MethodSequenceDiagram(projectId, methodId, methodOwnerName, 
		    		                                 methodPackageName, methodClassName, methodShortName, 
		    		                                 methodFullName);
		     
	        // Add the Record to the ArrayList.
	        methodList.add(methodRecord); 
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Methods with Annotations from the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return methodList;
   }  
   
   public ArrayList<ParameterSequenceDiagram> loadParameters(int projectId, int progrLanguageId, int methodId) throws Exception
   {
	  int parameterId=0, parameterDataTypeId=0; 
	  String sql="", errorMessage1="", errorMessage2="", parameterPackageName="", parameterClassName="", 
			 parameterDataTypeName="", parameterName="", parameterFullName=""; 
	  ParameterSequenceDiagram parameterRecord;	
	  ArrayList<ParameterSequenceDiagram> parameterList = new ArrayList<ParameterSequenceDiagram>();	
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statements
      try 
      {
	     sql = "select psd.psd_id parameterId, ";
	     sql = sql + "psd.dt_id parameterDataTypeId, ";
	     sql = sql + "psd.psd_package_name parameterPackageName, ";
	     sql = sql + "psd.psd_class_name parameterClassName, ";
	     sql = sql + "psd.psd_data_type_name parameterDataTypeName, ";
	     sql = sql + "psd.psd_parameter_name parameterName, ";
	     sql = sql + "concat(psd.psd_package_name,'.',psd.psd_class_name,' ',psd.psd_parameter_name ) parameterFullName ";
	     sql = sql + "from parameter_sequence_diagram psd, ";
	     sql = sql + "method_sequence_diagram msd ";
	     sql = sql + "where psd.pc_id = ? and ";
	     sql = sql + "psd.pl_id = ? and ";
	     sql = sql + "psd.msd_id = ?  and ";
	     sql = sql + "psd.pc_id = msd.pc_id and ";
	     sql = sql + "psd.msd_id = msd.msd_id ";
	     sql = sql + "order by psd.msd_id, psd.psd_id";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, progrLanguageId);
	     selectStatement.setInt(3, methodId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        parameterId           = results.getInt("parameterId");
	        parameterDataTypeId   = results.getInt("parameterDataTypeId");
	        parameterPackageName  = results.getString("parameterPackageName");
	        parameterClassName    = results.getString("parameterClassName");
	        parameterDataTypeName = results.getString("parameterDataTypeName");
	        parameterName         = results.getString("parameterName");
	        parameterFullName     = results.getString("parameterFullName");
		 
	        // Build the Record
		    parameterRecord = new ParameterSequenceDiagram(projectId, methodId, parameterId, 
		    		                                       parameterName, parameterDataTypeId, parameterDataTypeName, 
		    		                                       parameterPackageName, parameterClassName, parameterFullName);
		     
	        // Add the Record to the ArrayList.
	        parameterList.add(parameterRecord); 
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Parameters with Annotations from the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Programming Language Identifier: " + progrLanguageId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return parameterList;
   }  
   
   public ArrayList<AttributeSequenceDiagram> loadAttributes(int projectId, int progrLanguageId, int methodId, int parameterId, int parameterDataTypeId) throws Exception
   {
	  int attributeId=0; 
	  String sql="", errorMessage1="", errorMessage2="", attributeName="", attributeDataTypeName="", attributeValue="", attributeFullName=""; 
	  AttributeSequenceDiagram attributeRecord;	
	  ArrayList<AttributeSequenceDiagram> attributeList = new ArrayList<AttributeSequenceDiagram>();	
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statements
      try 
      {
	     sql = "select asd.asd_id attributeId, ";
	     sql = sql + "asd.asd_name attributeName, ";
	     sql = sql + "dt.dt_name attributeDataTypeName, ";
	     sql = sql + "asd.asd_value attributeValue ";
	     sql = sql + "from attribute_sequence_diagram asd, ";
	     sql = sql + "parameter_sequence_diagram psd, ";
	     sql = sql + "data_type dt ";
	     sql = sql + "where asd.pc_id = ? and ";
	     sql = sql + "asd.pl_id_1 = ? and ";
	     sql = sql + "asd.msd_id = ? and ";
	     sql = sql + "asd.psd_id = ? and ";
	     sql = sql + "asd.dt_id_1 = ? and ";
	     sql = sql + "asd.pc_id = psd.pc_id and ";
	     sql = sql + "asd.psd_id = psd.psd_id and ";
	     sql = sql + "asd.msd_id = psd.msd_id and ";
	     sql = sql + "asd.dt_id_2 = dt.dt_id and ";
	     sql = sql + "dt.pl_id = asd.pl_id_1 ";
	     sql = sql + "order by asd.msd_id, asd.psd_id, asd.dt_id_1, asd.asd_id";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, progrLanguageId);
	     selectStatement.setInt(3, methodId);
	     selectStatement.setInt(4, parameterId);
	     selectStatement.setInt(5, parameterDataTypeId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	    	attributeId           = results.getInt("attributeId");
	        attributeName         = results.getString("attributeName");
	        attributeDataTypeName = results.getString("attributeDataTypeName");
	        attributeValue        = results.getString("attributeValue");
		 
	        if(attributeValue != null && !attributeValue.trim().isEmpty()) 
	           attributeFullName = attributeValue + " " + attributeName;
	        else
	           attributeFullName = attributeDataTypeName + " " + attributeName;
	        
	        // Build the Record
	        attributeRecord = new AttributeSequenceDiagram(projectId, methodId, parameterId, parameterDataTypeId, attributeId, attributeFullName, 
	        		                                       attributeDataTypeName, attributeValue);
		     
	        // Add the Record to the ArrayList.
	        attributeList.add(attributeRecord); 
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Attributes with Annotations from the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Programming Language Identifier : " + progrLanguageId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Method Identifier               : " + methodId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Parameter Identifier            : " + parameterId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Parameter Data Type Identifier  : " + parameterDataTypeId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return attributeList;
   } 
   
   public int getProgrammingLanguageId(String progrLanguageName) throws Exception
   {
	  int progrLanguageId=0; 
	  String sql="", errorMessage1="", errorMessage2=""; 
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statements
      try 
      {
	     sql = "select plh.plh_id progrLanguageId ";
	     sql = sql + "from programming_language_header plh ";
	     sql = sql + "where plh.plh_name = ? ";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setString(1, progrLanguageName);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        progrLanguageId = results.getInt("progrLanguageId");
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while getting the Programming Language Identifier " + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Programming Language Name: " + progrLanguageName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return progrLanguageId;
   }   
   
   public int getUserId(int projectId, String userName) throws Exception
   {
	  int userId=0; 
	  String sql="", errorMessage1="", errorMessage2=""; 
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statements
      try 
      {
	     sql = "select usd.usd_id userId ";
	     sql = sql + "from user_sequence_diagram usd ";
	     sql = sql + "where usd.pc_id = ? and ";
	     sql = sql + "usd.usd_name = ?";
	  
         selectStatement = dbCon.prepareStatement(sql);
         selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, userName);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        userId = results.getInt("userId");
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while getting the User Identifier " + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "User Name: " + userName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return userId;
   }
   
   public int getMethodId(int projectId, int userId, String methodName) throws Exception
   {
	  int methodId=0; 
	  String sql="", errorMessage1="", errorMessage2=""; 
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statements
      try 
      {
	     sql = "select msd.msd_id methodId ";
	     sql = sql + "from method_sequence_diagram msd ";
	     sql = sql + "where msd.pc_id = ? and ";
	     sql = sql + "concat(msd.msd_owner, '.', msd.msd_short_name) = ?";
	  
         selectStatement = dbCon.prepareStatement(sql);
         selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, methodName);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        methodId = results.getInt("methodId");
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while getting the Method Identifier " + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Method Name     : " + methodName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "User Identifier : " + userId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return methodId;
   }     
   
   public ParameterDataSequenceDiagram getParameterDetails(int projectId, int progrLanguageId, int methodId, String parameterFullName) throws Exception
   {
	  String sql="", errorMessage1="", errorMessage2=""; 
	  int parameterId=0, parameterDataTypeId=0;
	  ParameterDataSequenceDiagram parameterDataSequenceDiagramRecord = null;
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statements
      try 
      {
	     sql = "select psd.psd_id parameterId, ";
	     sql = sql + "psd.dt_id parameterDataTypeId ";
	     sql = sql + "from parameter_sequence_diagram psd ";
	     sql = sql + "where psd.pc_id = ? and ";
	     sql = sql + "psd.pl_id = ? and ";
	     sql = sql + "psd.msd_id = ? and ";
	     sql = sql + "concat(psd.psd_package_name,'.', psd.psd_class_name,' ', psd.psd_parameter_name) = ?";

         selectStatement = dbCon.prepareStatement(sql);
         selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, progrLanguageId);
	     selectStatement.setInt(3, methodId);
	     selectStatement.setString(4, parameterFullName);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        parameterId         = results.getInt("parameterId");
	        parameterDataTypeId = results.getInt("parameterDataTypeId");
	        
	        parameterDataSequenceDiagramRecord = new ParameterDataSequenceDiagram(parameterId, parameterDataTypeId, parameterFullName);
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while getting the Parameter Details " + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Programming Language Identifier : " + progrLanguageId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Method Identifier               : " + methodId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Parameter Full Name             : " + parameterFullName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return parameterDataSequenceDiagramRecord;
   }  
   
   public int getAttributeId(int projectId, int progrLanguageId, int methodId, int parameterId, int parameterDataTypeId, String attributeName) throws Exception
   {
	  int attributeId=0;
	  String sql="", errorMessage1="", errorMessage2=""; 
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
	  
      // SQL Statements
      try 
      {
	     sql = "select asd.asd_id attributeId ";
	     sql = sql + "from attribute_sequence_diagram asd, ";
	     sql = sql + "parameter_sequence_diagram psd, ";
	     sql = sql + "data_type dt ";
	     sql = sql + "where asd.pc_id = ? and ";
	     sql = sql + "asd.pl_id_1 = ? and ";
	     sql = sql + "asd.msd_id = ? and ";
	     sql = sql + "asd.psd_id = ? and ";
	     sql = sql + "asd.dt_id_1 = ? and ";
	     sql = sql + "asd.asd_name = ? and ";
	     sql = sql + "asd.pc_id = psd.pc_id and ";
	     sql = sql + "asd.psd_id = psd.psd_id and ";
	     sql = sql + "asd.msd_id = psd.msd_id and ";
	     sql = sql + "asd.dt_id_2 = dt.dt_id and ";
	     sql = sql + "dt.pl_id = asd.pl_id_1";   
	  
         selectStatement = dbCon.prepareStatement(sql);
         selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, progrLanguageId);
	     selectStatement.setInt(3, methodId);
	     selectStatement.setInt(4, parameterId);
	     selectStatement.setInt(5, parameterDataTypeId);
	     selectStatement.setString(6, attributeName);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        attributeId = results.getInt("attributeId");
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while getting the Attribute Identifier " + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Project Identifier              : " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Programming Language Identifier : " + progrLanguageId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Method Identifier               : " + methodId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Parameter Identifier            : " + parameterId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Parameter Data Identifier       : " + parameterDataTypeId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Attribute Name                  : " + attributeName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return attributeId;
   }     
   
   
   public ArrayList<UMLSequenceDiagramFinal> loadAnnotations(int projectId) throws Exception
   {
	  String sql="", errorMessage1="", errorMessage2="", annotationTypeStr="", fullMethodName="", methodOwner="",  methodShortName="", 
			 fullAnnotationCondition="", annotationElement1="", annotationOperator="", annotationElement2="";
	  int methodId=0;
	  
	  AnnotationType annotationType;
	  
	  UMLSequenceDiagramFinal umlSeqDiagramFinalRecord;	
	  ArrayList<UMLSequenceDiagramFinal> umlSeqDiagramFinalList = new ArrayList<UMLSequenceDiagramFinal>();	
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
	  
      // SQL Statements
      try 
      {
	     sql = "select distinct usdf.usdf_annotation_type annotationType, ";
	     sql = sql + "usdf.msd_id methodId, ";
	     sql = sql + "concat(msd.msd_owner, '.', msd.msd_short_name) fullMethodName, ";
	     sql = sql + "msd.msd_owner methodOwner, ";
	     sql = sql + "msd.msd_short_name methodShortName, ";
	     sql = sql + "usdf.usdf_datum_value fullAnnotationCondition, ";
	     sql = sql + "usdf.usdf_annotation_element_1 annotationElement1, ";
	     sql = sql + "usdf.usdf_annotation_operator annotationOperator, ";
	     sql = sql + "usdf.usdf_annotation_element_2 annotationElement2 ";
	     sql = sql + "from uml_sequence_diagram_final usdf, ";
	     sql = sql + "method_sequence_diagram msd ";
	     sql = sql + "where usdf.pc_id = ? and ";
	     sql = sql + "usdf.usdf_annotation_type in('Method', 'ReturnType', 'Parameter') and ";
	     sql = sql + "usdf.usdf_annotation_details = 'Y' and ";
	     sql = sql + "usdf.pc_id = msd.pc_id and ";
	     sql = sql + "usdf.msd_id = msd.msd_id ";
	     sql = sql + "order by usdf.msd_id";
	  
         selectStatement = dbCon.prepareStatement(sql);
         selectStatement.setInt(1, projectId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        annotationTypeStr       = results.getString("annotationType");
	        methodId                = results.getInt("methodId");
	        fullMethodName          = results.getString("fullMethodName"); 
	        methodOwner             = results.getString("methodOwner");
	        methodShortName         = results.getString("methodShortName"); 
	   		fullAnnotationCondition = results.getString("fullAnnotationCondition");
	   		annotationElement1      = results.getString("annotationElement1"); 
	   		annotationOperator      = results.getString("annotationOperator");
	   		annotationElement2      = results.getString("annotationElement2");
	   		
	   		// Convert String to Enum
	   		annotationType = AnnotationType.valueOf(annotationTypeStr);
	   		
	   		umlSeqDiagramFinalRecord = new UMLSequenceDiagramFinal(projectId, annotationType, methodId, 
	   				                                               fullMethodName, methodOwner, methodShortName, 
	   				                                               fullAnnotationCondition, annotationElement1, annotationOperator, 
	   				                                               annotationElement2);
	   		
	   		umlSeqDiagramFinalList.add(umlSeqDiagramFinalRecord);
	   		
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Annotations" + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Project Identifier              : " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return umlSeqDiagramFinalList;
      
   }
   
   public void updateTextFileProperties(int projectId, int headerId, String annotationFile) throws Exception
   {
      int col;
      String updateSql, errorMessage1="", errorMessage2="";
      PreparedStatement updateStatement;
      
      try
      {
         // Prepare the SQL Statement
         updateSql = "update text_file_property  tfp ";
         updateSql = updateSql + "set tfp_value = ? ";
	     updateSql = updateSql + "where pc_id = ? and ";
	     updateSql = updateSql + "tfh_id = ? and ";
	     updateSql = updateSql + "tfp_id = ? and ";
	     updateSql = updateSql + "tfp_name = 'annotationFile'";
	    	  
	     updateStatement = dbCon.prepareStatement(updateSql);
	    
	     col = 1;
	     updateStatement.setString(col++, annotationFile);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, headerId);
	     updateStatement.setInt(col++, headerId);
	  	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
      }
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while updating the Annotation File: "+ annotationFile +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Project Identifier: "+ projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Header Identifier: "+ headerId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
   }
   
   public TextFileHeader loadAOPFileHeader(int headerId) throws Exception
   {
	  String sql="", errorMessage1="", errorMessage2="", longFileName="", pathName="", shortFileName="", fileType="",
			 packageName="", programmingLanguageName="", pointCut1Name="", pointCut2Name=""; 
	  FileType type;
	  TextFileHeader textFileHeaderRecord = null;	
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statement
      try 
      {
	     sql = "select tfh_filename longFileName, ";
	     sql = sql + "tfh_path pathName, ";
	     sql = sql + "tfh_name shortFileName, ";
	     sql = sql + "tfh_type fileType, ";
	     sql = sql + "tfh_package_name packageName, ";
	     sql = sql + "plh.plh_name programmingLanguageName, ";
	     sql = sql + "tfh_pointcut_name_1 pointCut1Name, ";
	     sql = sql + "tfh_pointcut_name_2 pointCut2Name ";
	     sql = sql + "from text_file_header tfh, ";
	     sql = sql + "programming_language_header plh ";
	     sql = sql + "where tfh.tfh_id = ? and ";
	     sql = sql + "tfh.pl_id  = plh.plh_id";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, headerId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        longFileName    = results.getString("longFileName");
	        pathName        = results.getString("pathName");
	        shortFileName   = results.getString("shortFileName");
	        fileType        = results.getString("fileType");
	        packageName     = results.getString("packageName");
	        programmingLanguageName = results.getString("programmingLanguageName");
	        pointCut1Name   = results.getString("pointCut1Name");
	        pointCut2Name   = results.getString("pointCut2Name");
	        
	        // Convert String to Enum
	        type = FileType.valueOf(fileType);
		 
	        // Build the Record using the Constructor #3
	        textFileHeaderRecord = new TextFileHeader(headerId, longFileName, pathName, shortFileName,
		                                              type, packageName, programmingLanguageName, pointCut1Name,
		                                              pointCut2Name);
		     
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Aspect Oriented Programming Header from the Header Identifier: " + headerId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return textFileHeaderRecord;
   }   
   
   public ArrayList<TextFileDetails> loadAOPFileDetails(int headerId) throws Exception
   {
	  String sql="", errorMessage1="", errorMessage2="", lineText="", lineTypeStr="";
	  int lineNumber=0, linePropertyId=0;
	  FieldType lineType;
	  TextFileDetails textFileDetailsRecord = null;	
	  ArrayList<TextFileDetails> textFileDetailsList = new ArrayList<TextFileDetails>();
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statement
      try 
      {
	     sql = "select tfd.tfd_line_number lineNumber, ";
	     sql = sql + "tfd.tfd_text lineText, ";
	     sql = sql + "tfd_type lineType, ";
	     sql = sql + "tfp_id linePropertyId ";
	     sql = sql + "from text_file_detail tfd ";
	     sql = sql + "where tfd.tfh_id = ?";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, headerId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        lineNumber  = results.getInt("lineNumber");
	        lineText    = results.getString("lineText");
	        lineTypeStr = results.getString("lineType");
	        linePropertyId  = results.getInt("linePropertyId");
	        
	        // Convert String to Enum
	        lineType = FieldType.valueOf(lineTypeStr);
		 
	        // Build the Record using the Constructor #2
	        textFileDetailsRecord = new TextFileDetails(headerId, lineNumber, lineText, lineType, linePropertyId);
	        
	        // Add the Record to the ArrayList
	        textFileDetailsList.add(textFileDetailsRecord);
		     
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Aspect Oriented Programming Details from the Header Identifier: " + headerId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return textFileDetailsList;
   } 
   
   public TextFileProperties loadAOPFileProperties(int projectId, int headerId, int propertyId) throws Exception
   {
	  String sql="", errorMessage1="", errorMessage2="", propertyName="", propertyValue="", propertyDataType="", propertyType=""; 
	  PropertyType type;
	  TextFileProperties textFilePropertiesRecord = null;	
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statement
      try 
      {
	     sql = "select tfp.tfp_name propertyName, ";
	     sql = sql + "tfp.tfp_value propertyValue, ";
	     sql = sql + "tfp.tfp_data_type propertyDataType, ";
	     sql = sql + "tfp_type propertyType ";
	     sql = sql + "from text_file_property tfp ";
	     sql = sql + "where tfp.pc_id  = ? and ";
	     sql = sql + "tfp.tfh_id = ? and ";
	     sql = sql + "tfp.tfp_id = ?";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, headerId);
	     selectStatement.setInt(3, propertyId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        propertyName     = results.getString("propertyName");
	        propertyValue    = results.getString("propertyValue");
	        propertyDataType = results.getString("propertyDataType");
	        propertyType     = results.getString("propertyType");
	        
	        // Convert String to Enum
	        type = PropertyType.valueOf(propertyType);
		 
	        // Build the Record using the Constructor #2
	        textFilePropertiesRecord = new TextFileProperties(projectId, propertyName, propertyValue, propertyDataType, type);
		     
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Aspect Oriented Programming Properties from the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Header Identifier: " + headerId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Property Identifier: " + propertyId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return textFilePropertiesRecord;
   } 
   
   public ArrayList<ClassMethod> loadAnnotatedMethodsDet(int projectId) throws Exception
   {
	  String sql="", errorMessage1="", errorMessage2="", packageName="", className="", 
			 fullName="", shortName="", returnType1="", returnType2="";
	  int methodId=0;
	  ClassMethod classMethodRecord = null;	
	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statement
      try 
      {
    	  
	     sql = "select distinct cm.cm_id methodId, ";
	     sql = sql + "cm.cm_return_type_1 returnType1, ";
	     sql = sql + "cm.cm_return_type_2 returnType2, ";
	     sql = sql + "cm.cm_package_name packageName, ";
	     sql = sql + "cm.cm_class_name className, ";
	     sql = sql + "cm.cm_short_method_name shortName ";
	     sql = sql + "from uml_sequence_diagram_final usdf, ";
	     sql = sql + "class_method cm ";
	     sql = sql + "where usdf.pc_id = ? and ";
	     sql = sql + "usdf.usdf_annotation_type in('Method', 'ReturnType', 'Parameter') and ";
	     sql = sql + "usdf.usdf_annotation_details = 'Y' and ";
	     sql = sql + "usdf.pc_id = cm.pc_id and ";
	     sql = sql + "usdf.cm_id = cm.cm_id";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        methodId     = results.getInt("methodId");
	        returnType1  = results.getString("returnType1");
	        returnType2  = results.getString("returnType2");
	        packageName  = results.getString("packageName");
	        className    = results.getString("className");
	        shortName    = results.getString("shortName");
	        
	        // Build the full Method Name
	        if(returnType1.equals("Class"))
	           fullName = returnType2 + ' '+ packageName + '.' + className + '.' + shortName;
	        else
	        	fullName = returnType1 + ' ' + packageName + '.' + className + '.' + shortName;
		 
	        // Build the Record using the Constructor #2
	        classMethodRecord = new ClassMethod(projectId, methodId, packageName, 
	        		                            className, shortName, fullName, 
	        		                            returnType1, returnType2);
	        
	        // Add the Record to the ArrayList
	        classMethodList.add(classMethodRecord);
		     
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while loading the Method Details from the Annotations in the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return classMethodList;
      
   }  
   
   public int validateAnnotationsQuantity(int projectId) throws Exception
   {
	  String sql="", errorMessage1="", errorMessage2="";
	  int annotationsQuantity=0;
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statement
      try 
      {
	     sql = "select count(usdf.usdf_annotation_type) annotationsQuantity ";
	     sql = sql + "from uml_sequence_diagram_final usdf ";
	     sql = sql + "where usdf.pc_id = ? and ";
	     sql = sql + "usdf.usdf_annotation_type in('Method', 'ReturnType', 'Parameter') and ";
	     sql = sql + "usdf.usdf_annotation_details = 'Y'";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        annotationsQuantity  = results.getInt("annotationsQuantity");
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
	     errorMessage1 = e.getMessage();	
	     errorMessage2 = "Error XXXX: Occurred while counting the quantity of Annotations: " + annotationsQuantity + " in the UML Sequence Diagram for the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return annotationsQuantity;
   }    
   
   public int validatePropertiesQuantity(int projectId, int headerId) throws Exception
   {
	  String sql="", errorMessage1="", errorMessage2="";
	  int propertiesQuantity=0;
	  
	  PreparedStatement selectStatement;
	  ResultSet results;
   
      // SQL Statement
      try 
      {
	     sql = "select count(tfp.tfp_name) propertyQuantity ";
	     sql = sql + "from text_file_property tfp ";
	     sql = sql + "where tfp.pc_id  = ? and ";
	     sql = sql + "tfp.tfh_id = ?";
	  
         selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, headerId);
	     results = selectStatement.executeQuery();
		
	     while(results.next()) 
	     {
	        propertiesQuantity  = results.getInt("propertyQuantity");
	     }
		
	     results.close();
	     selectStatement.close();
      }
      catch(Exception e)
      {
 	     errorMessage1 = e.getMessage();	
 	     errorMessage2 = "Error XXXX: Occurred while counting the quantity of Properties: " + propertiesQuantity + " for the Project Identifier: " + projectId + System.lineSeparator();
 	     errorMessage2 = errorMessage2 + "and AOP Template File with Header Identifier: " + headerId + System.lineSeparator();
 	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         throw new Exception(errorMessage2);
      }
      
      return propertiesQuantity;
   }     
   
}
