package frl.model.annotateUMLSequenceDiagram;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import frl.model.loadUMLSequenceDiagram.AnnotationType;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.DatumCategory1;
import frl.model.loadUMLSequenceDiagram.LineType1;
import frl.model.loadUMLSequenceDiagram.MethodSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ReturnTypeValueSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramFinal;
import frl.model.loadUMLSequenceDiagram.ValueSequenceDiagram;

public class AnnotateUMLSequenceDiagramDatabase
{
   // Atributes for the ForensicReadyLoggerDatabase 
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
      
   // Constructor of the Class
   public AnnotateUMLSequenceDiagramDatabase() //1
   {          
   }
	   
   // Methods of the Class
   private void configureDBParameters(String propFileName) throws Exception //2
   {
      Properties prop = new Properties();
	  InputStream input = null;
	  String errorMessage1="", errorMessage2="";

	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(propFileName);

		 // Load the properties file
	     prop.load(input);

	     // Get the values from the properties file
	     this.dbUser     = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 prop.getProperty("dbPort");
		 this.dbUrl      = prop.getProperty("dbUrl");
		 this.dbDriver   = prop.getProperty("dbDriver");

	  } 
	  catch (IOException e1) 
	  {
		 errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Database Configuration File." + System.lineSeparator();
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
			   errorMessage2 = "Error XXXX: Occurred while closing the Database Configuration File."+System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   throw new Exception(errorMessage2);
		    }
		  }
	   }

   }  
	
   // Connect to the ForensicReadyLogger Database
   public void connect(String dbConfigFilePath) throws Exception //3
   {
      String errorMessage1="", errorMessage2="";	   

      if (dbCon != null)
	  return;

      // Get the database parameters from the config.properties file
      try
      {
         configureDBParameters(dbConfigFilePath);
      }
      catch(Exception e)
      {
 	     errorMessage1 = e.getMessage();
 	     throw new Exception(errorMessage1);  
      }
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e) 
	  {
		 errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: The Database Driver was not found."+System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
	}
	
   // Disconnect to the ForensicReadyLogger Database
   public void disconnect() throws Exception //4
   {
      String errorMessage1="", errorMessage2="";
	   
	  if (dbCon != null) 
	  {
	     try 
		 {
		    dbCon.close();
		 } 
	     catch (SQLException e) 
		 {
	        errorMessage1 = e.getMessage();
			errorMessage2 = "Error XXXX: The Database Connection cannot be closed."+System.lineSeparator();
			errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		    throw new Exception(errorMessage2);
	     }
	  }
		
      dbCon = null;
   }
   
   public ArrayList<UserData> loadUsers(int projectId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", userName="", 
    		 userTextFileName="", userImageFileName="";
	  ArrayList<UserData> userList = new ArrayList<UserData>();
	  UserData userRecord;
	  int userId=0;

	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
      // Obtain the different Users that corresponds to the project Id
	  try
	  {
	     // SQL Statements      
		 selectSql = "select usd.usd_id userId, ";
		 selectSql = selectSql + "usd.usd_name userName, ";
		 selectSql = selectSql + "ufsd.ufsd_text_filename userTextFileName, ";
		 selectSql = selectSql + "ufsd.ufsd_png_filename userImageFileName ";
		 selectSql = selectSql + "from user_sequence_diagram usd, ";
		 selectSql = selectSql + "user_file_sequence_diagram ufsd ";
		 selectSql = selectSql + "where usd.pc_id = ? and ";
		 selectSql = selectSql + "ufsd.pc_id = usd.pc_id and ";
		 selectSql = selectSql + "ufsd.usd_id = usd.usd_id ";
		 selectSql = selectSql + "order by usd.usd_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectResults = selectStatement.executeQuery();
			  
		 while(selectResults.next()) 
		 {
		    userId            = selectResults.getInt("userId"); 
		    userName          = selectResults.getString("userName");
		    userTextFileName  = selectResults.getString("userTextFileName");
		    userImageFileName = selectResults.getString("userImageFileName");
		    
		    // Build a new record
		    userRecord = new UserData(projectId, userId, userName, userTextFileName, userImageFileName);
		        
		    userList.add(userRecord);
	      }
	   }	
	   catch(Exception e1)
	   {
	      errorMessage1 = e1.getMessage();
	      errorMessage2 = "Error XXXX: Ocurred while loading the different Users for the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
		  
      return userList;

   }   
   
   public ArrayList<MethodStepData> loadMethodsSteps(int projectId, int userId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", methodName="", methodTextFileName="",
    		 methodImageFileName="";
      int methodId=0, stepId=0;
	  ArrayList<MethodStepData> methodDataList = new ArrayList<MethodStepData>();
	  MethodStepData methodDataRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the different method Steps that corresponds to the project Id and user Id
	  try
	  {
	     // SQL Statements      
		 selectSql = "select mssd.msd_id methodId, ";
		 selectSql = selectSql + "mssd.mssd_step_id stepId, ";
		 selectSql = selectSql + "mssd.msdd_order_id orderId, ";
		 selectSql = selectSql + "mssd.mssd_name methodName, ";
		 selectSql = selectSql + "mfsd.mfsd_text_filename methodTextFileName, ";
		 selectSql = selectSql + "mfsd.mfsd_png_filename methodImageFileName ";
		 selectSql = selectSql + "from method_step_sequence_diagram mssd, ";
		 selectSql = selectSql + "method_file_sequence_diagram mfsd ";
		 selectSql = selectSql + "where mssd.pc_id = ? and ";
		 selectSql = selectSql + "mssd.usd_id = ? and ";
		 selectSql = selectSql + "mfsd.pc_id = mssd.pc_id and ";
		 selectSql = selectSql + "mfsd.usd_id = mssd.usd_id and ";
		 selectSql = selectSql + "mssd.msd_id = mfsd.msd_id and ";
		 selectSql = selectSql + "mfsd.mssd_step_id = mssd.mssd_step_id ";
		 selectSql = selectSql + "order by mssd.msdd_order_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, userId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        methodId            = selectResults.getInt("methodId");
	        stepId              = selectResults.getInt("stepId");
	        methodName          = selectResults.getString("methodName");
	        methodTextFileName  = selectResults.getString("methodTextFileName");
	        methodImageFileName = selectResults.getString("methodImageFileName");
	        
	        methodDataRecord = new MethodStepData(projectId, userId, methodId, 
		                                          stepId, methodName, 
		                                          methodTextFileName, methodImageFileName);
	        		                                 
	        methodDataList.add(methodDataRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the different Methods for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return methodDataList;

   }
   
   public UserData getUserDetails(int projectId, String userName) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", userTextFileName="", userImageFileName="";
      int userId=0;
      UserData userData = null;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the User Identifier that corresponds to this project Id and userName
	  try
	  {
	     // SQL Statements      
		 selectSql = "select usd.usd_id userId, ";
		 selectSql = selectSql + "usd.usd_name userName, ";
		 selectSql = selectSql + "ufsd.ufsd_text_filename userTextFileName, ";
		 selectSql = selectSql + "ufsd.ufsd_png_filename userImageFileName ";
		 selectSql = selectSql + "from user_sequence_diagram usd, ";
		 selectSql = selectSql + "user_file_sequence_diagram ufsd ";
		 selectSql = selectSql + "where usd.pc_id = ? and ";
		 selectSql = selectSql + "usd.usd_name = ? and ";
		 selectSql = selectSql + "ufsd.pc_id = usd.pc_id and ";
		 selectSql = selectSql + "ufsd.usd_id = usd.usd_id ";
		 selectSql = selectSql + "order by usd.usd_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, userName);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			userId            = selectResults.getInt("userId"); 
			userTextFileName  = selectResults.getString("userTextFileName");
		    userImageFileName = selectResults.getString("userImageFileName");
		    
		    userData = new UserData(projectId, userId, userName, 
			                        userTextFileName,  userImageFileName);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the User Identifier for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "User Name: " + userName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return userData;

   }  
   
   public MethodStepData getMethodDetails1(int projectId, int userId, String methodName) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", textFileName="", imageFileName="";
      int id=0, stepId=0;
      
      MethodStepData methodStepRecord=null;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
   
      // Obtain the details of the method step
	  try
	  {
	     // SQL Statements      
		 selectSql = "select mssd.msd_id id, mssd.mssd_step_id stepId, ";
		 selectSql = selectSql + "mfsd_text_filename textFileName, mfsd_png_filename imageFileName ";
		 selectSql = selectSql + "from method_step_sequence_diagram mssd, ";
		 selectSql = selectSql + "method_file_sequence_diagram mfsd ";
		 selectSql = selectSql + "where mssd.pc_id  = ? and ";
		 selectSql = selectSql + "mssd.usd_id = ? and ";
		 selectSql = selectSql + "mssd.mssd_name = ? and ";
		 selectSql = selectSql + "mfsd.usd_id = mssd.usd_id and ";
		 selectSql = selectSql + "mfsd.msd_id = mssd.msd_id and ";
		 selectSql = selectSql + "mfsd.mssd_step_id = mssd.mssd_step_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, userId);
		 selectStatement.setString(3, methodName);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			id           = selectResults.getInt("id"); 
			stepId       = selectResults.getInt("stepId"); 
			textFileName = selectResults.getString("textFileName"); 
			imageFileName = selectResults.getString("imageFileName"); 
			
			methodStepRecord = new MethodStepData(projectId, userId, id, 
		                                          stepId, methodName, 
		                                          textFileName, imageFileName);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Method Step Details for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Name: " + methodName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return methodStepRecord;

   }  
   
   public ArrayList<ParameterSequenceDiagram> loadParameters(int projectId, int methodId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", dataTypeName="", parameterName="", packageName="";
      int parameterId=0, dataTypeId=0;
	  ArrayList<ParameterSequenceDiagram> parameterList = new ArrayList<ParameterSequenceDiagram>();
	  ParameterSequenceDiagram parameterRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the different attributes that corresponds to the project Id, method Id
	  try
	  {
	     // SQL Statements 
		 selectSql = "select psd_id parameterId, dt_id dataTypeId, ";  
		 selectSql = selectSql + "psd.psd_data_type_name dataTypeName, ";
		 selectSql = selectSql + "psd.psd_package_name packageName, ";
		 selectSql = selectSql + "psd.psd_parameter_name parameterName ";
		 selectSql = selectSql + "from parameter_sequence_diagram psd ";
		 selectSql = selectSql + "where psd.pc_id = ? and ";
		 selectSql = selectSql + "psd.msd_id = ? ";
		 selectSql = selectSql + "order by psd.psd_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        parameterId   = selectResults.getInt("parameterId");
	        parameterName = selectResults.getString("parameterName");
	        dataTypeId    = selectResults.getInt("dataTypeId");
	        dataTypeName  = selectResults.getString("dataTypeName");
	        packageName   = selectResults.getString("packageName");

	        parameterRecord = new ParameterSequenceDiagram(projectId, methodId,
	        		                                       parameterId, parameterName, 
	        		                                       dataTypeId, dataTypeName,
	        		                                       packageName);
	        		                                               
	        parameterList.add(parameterRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the different Parameters for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Identifier: " + parameterId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return parameterList;

   } 
   
   public ParameterSequenceDiagram getParametersIds(int projectId, int methodId, 
		                                            String parameterName, 
		                                            String parameterDataTypeName) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", packageName="";
      int parameterId=0, parameterDataTypeId=0;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      ParameterSequenceDiagram parameterRecord=null;
      
      // Obtain the parameter identifiers that corresponds to the projectId, methodId,
      // and parameter name and parameter data type name
	  try
	  {
	     // SQL Statements      
		 selectSql = "select psd.psd_id parameterId, ";
		 selectSql = selectSql + "psd.dt_id parameterDataTypeId, ";
		 selectSql = selectSql + "psd.psd_package_name packageName ";
		 selectSql = selectSql + "from parameter_sequence_diagram psd ";
		 selectSql = selectSql + "where psd.pc_id = ? and ";
		 selectSql = selectSql + "psd.msd_id = ? and ";
		 selectSql = selectSql + "psd.psd_parameter_name =? and ";
		 selectSql = selectSql + "concat(psd.psd_package_name, '.', psd.psd_data_type_name) =?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setString(3, parameterName);
		 selectStatement.setString(4, parameterDataTypeName);
		 
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			parameterId         = selectResults.getInt("parameterId");
			parameterDataTypeId = selectResults.getInt("parameterDataTypeId");
			packageName         = selectResults.getString("packageName");
			
			parameterRecord = new ParameterSequenceDiagram(projectId, methodId,
                                                           parameterId, parameterName, 
                                                           parameterDataTypeId, parameterDataTypeName,
                                                           packageName);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Parameter Identifiers for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Id: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Name: " + parameterName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Data Type Name: " + parameterDataTypeName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return parameterRecord;

   }  
   
   public ArrayList<AttributeSequenceDiagram> loadAttributes(int projectId, int methodId,
		                                                     int parameterId, 
		                                                     int parameterDataTypeId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", 
    		 attributeName="", attributeValue="", attributeDataTypeName="";
      int attributeId=0, attributeDataTypeId=0;
	  ArrayList<AttributeSequenceDiagram> attributeList = new ArrayList<AttributeSequenceDiagram>();
	  AttributeSequenceDiagram attributeRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the different attributes that corresponds to the project Id, method Id, parameter id 
      // and parameter data type id
	  try
	  {
	     // SQL Statements 
		 selectSql = "select asd.asd_id attributeId, "; 
		 selectSql = selectSql + "asd.asd_name attributeName, ";
		 selectSql = selectSql + "asd.asd_value attributeValue, ";
		 selectSql = selectSql + "asd.dt_id_2 attributeDataTypeId, ";
		 selectSql = selectSql + "dt.dt_name attributeDataTypeName ";
		 selectSql = selectSql + "from attribute_sequence_diagram asd, ";
		 selectSql = selectSql + "data_type dt ";
		 selectSql = selectSql + "where asd.pc_id = ? and ";
		 selectSql = selectSql + "asd.msd_id = ? and ";
		 selectSql = selectSql + "asd.psd_id = ? and ";
		 selectSql = selectSql + "asd.dt_id_1 = ? and ";
		 selectSql = selectSql + "dt.dt_id = asd.dt_id_2";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setInt(3, parameterId);
		 selectStatement.setInt(4, parameterDataTypeId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        attributeId    = selectResults.getInt("attributeId");
	        attributeName  = selectResults.getString("attributeName");
	        attributeValue = selectResults.getString("attributeValue");
	        attributeDataTypeId = selectResults.getInt("attributeDataTypeId");
	        attributeDataTypeName = selectResults.getString("attributeDataTypeName");

	        attributeRecord = new AttributeSequenceDiagram(projectId, methodId,
	        		                                       parameterId, parameterDataTypeId, 
	        		                                       attributeId, attributeDataTypeId, attributeDataTypeName,
	        		                                       attributeName, attributeValue);
	        		                                               
	        attributeList.add(attributeRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the different Attributes for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Identifier: " + parameterId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Data Type Identifier: " + parameterDataTypeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return attributeList;

   } 
   
   public AttributeSequenceDiagram getAttributeInfo(int projectId, int methodId,
                                                    int parameterId, int parameterDataTypeId,
                                                    String attributeName, String attributeValue) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", attributeDataTypeName="";
      int attributeId=0, attributeDataTypeId=0;
      AttributeSequenceDiagram attributeSeqDiagramRecord = null;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the different methods that corresponds to the project Id, user Id
	  try
	  {
	     // SQL Statements      
		 selectSql = "select asd.asd_id attributeId, ";
		 selectSql = selectSql + "asd.dt_id_2 attributeDataTypeId, ";
		 selectSql = selectSql + "dt.dt_name attributeDataTypeName ";
		 selectSql = selectSql + "from attribute_sequence_diagram asd, ";
		 selectSql = selectSql + "data_type dt ";
		 selectSql = selectSql + "where asd.pc_id  = ? and ";
		 selectSql = selectSql + "asd.msd_id = ? and ";
		 selectSql = selectSql + "asd.psd_id = ? and ";
		 selectSql = selectSql + "asd.dt_id_1 = ? and ";
		 selectSql = selectSql + "asd.asd_name = ? and ";
		 selectSql = selectSql + "dt.dt_id = asd.dt_id_2";
		 
		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setInt(3, parameterId);
		 selectStatement.setInt(4, parameterDataTypeId);
		 selectStatement.setString(5, attributeName);
		 
		 if(attributeValue != null && !attributeValue.trim().isEmpty())
		    selectStatement.setString(6, attributeValue);
		 
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    attributeId           = selectResults.getInt("attributeId"); 
		    attributeDataTypeId   = selectResults.getInt("attributeDataTypeId"); 
		    attributeDataTypeName = selectResults.getString("attributeDataTypeName"); 
		    
		    attributeSeqDiagramRecord = new AttributeSequenceDiagram(projectId, methodId, 
		    		                                                 parameterId, 
		    		                                                 attributeId, attributeDataTypeId,
		    		                                                 attributeDataTypeName); 
		 }

	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Attribute Identifier for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Identifier: " + parameterId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Data Type Identifier: " + parameterDataTypeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Attribute Name: " + attributeName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return attributeSeqDiagramRecord;

   } 
   
   public ArrayList<ValueSequenceDiagram> loadValues(int projectId, int methodId,
		                                             int parameterId, int parameterDataTypeId,
		                                             int progrLangId, int attributeId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", valueName="", valueEquivalence="";
      int valueId=0;
	  ArrayList<ValueSequenceDiagram> valueList = new ArrayList<ValueSequenceDiagram>();
	  ValueSequenceDiagram valueRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      

      // Obtain the different values that corresponds to the project Id, method Id, parameter id 
      // and parameter data type id, programming Language Id and attribute Id
	  try
	  {
	     // SQL Statements 
		 selectSql = "select vsd.vsd_id valueId, "; 
		 selectSql = selectSql + "vsd.vsd_name valueName, ";
		 selectSql = selectSql + "vsd.vsd_value valueEquivalence ";
		 selectSql = selectSql + "from value_sequence_diagram vsd ";
		 selectSql = selectSql + "where vsd.pc_id = ? and ";
		 selectSql = selectSql + "vsd.msd_id = ? and ";
		 selectSql = selectSql + "vsd.psd_id = ? and ";
		 selectSql = selectSql + "vsd.dt_id_1 = ? and ";
		 selectSql = selectSql + "vsd.pl_id_1 = ? and ";
		 selectSql = selectSql + "vsd.asd_id = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setInt(3, parameterId);
		 selectStatement.setInt(4, parameterDataTypeId);
		 selectStatement.setInt(5, progrLangId);
		 selectStatement.setInt(6, attributeId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        valueId          = selectResults.getInt("valueId");
	        valueName        = selectResults.getString("valueName");
	        valueEquivalence = selectResults.getString("valueEquivalence");

	        valueRecord = new ValueSequenceDiagram(projectId, methodId, parameterId,
	        		                               parameterDataTypeId, progrLangId, 
	        		                               attributeId, valueId, valueName, 
                                                   valueEquivalence);
	        		                                               
	        valueList.add(valueRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the different Values for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Identifier: " + parameterId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Data Type Identifier: " + parameterDataTypeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Programming Language Identifier: " + progrLangId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Attribute Identifier: " + attributeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return valueList;

   } 
   
   public int getValueId(int projectId, int methodId,
                         int parameterId, int parameterDataTypeId,
                         int progLangId, int attributeId, 
                         String valueEquivalence) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      int valueId=0;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the value Id that corresponds to the project Id, method Id, parameter Id,
      // parameter Data type id and attribute id
	  try
	  {
	     // SQL Statements      
		 selectSql = "select vsd.vsd_id valueId ";
		 selectSql = selectSql + "from value_sequence_diagram vsd ";
		 selectSql = selectSql + "where vsd.pc_id = ? and ";
		 selectSql = selectSql + "vsd.msd_id = ? and ";
		 selectSql = selectSql + "vsd.psd_id = ? and ";
		 selectSql = selectSql + "vsd.dt_id_1 = ? and ";
		 selectSql = selectSql + "vsd.pl_id_1 = ? and ";
		 selectSql = selectSql + "vsd.asd_id = ? and ";
		 selectSql = selectSql + "vsd.vsd_value = ? ";
		 
		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setInt(3, parameterId);
		 selectStatement.setInt(4, parameterDataTypeId);
		 selectStatement.setInt(5, progLangId);
		 selectStatement.setInt(6, attributeId);
		 selectStatement.setString(7, valueEquivalence);
		 
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    valueId = selectResults.getInt("valueId"); 
		 }

	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Value Identifier for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Identifier: " + parameterId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Data Type Identifier: " + parameterDataTypeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Programming Language Identifier: " + progLangId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Attribute Identifier: " + attributeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Value Equivalence: " + valueEquivalence + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return valueId;

   }    
   
   public ReturnTypeData loadReturnType(int projectId, int methodId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", returnTypeName="";
      int returnTypeId=0;
      ReturnTypeData returnTypeDataRecord = null;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      
      // Obtain the details of the return type corresponds to the project Id, method Id
	  try
	  {
	     // SQL Statements 
		 selectSql = "select rt.rt_id returnTypeId, ";
		 selectSql = selectSql + "rt.rt_name returnTypeName ";
		 selectSql = selectSql + "from method_sequence_diagram msd, ";
		 selectSql = selectSql + "return_type rt ";
		 selectSql = selectSql + "where msd.pc_id  = ? and ";
		 selectSql = selectSql + "msd.msd_id = ? and ";
		 selectSql = selectSql + "msd.rt_id  = rt.rt_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        returnTypeId    = selectResults.getInt("returnTypeId");
	        returnTypeName  = selectResults.getString("returnTypeName");

	        returnTypeDataRecord = new ReturnTypeData(projectId, methodId,
	        		                                  returnTypeId, returnTypeName);

		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Return Type Details for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return returnTypeDataRecord;

   } 
   
   public ArrayList<ReturnTypeValueSequenceDiagram> loadReturnTypeValues(int projectId, int methodId,
		                                                                 int returnTypeId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", returnTypeValueName="", 
    		 returnTypeValueEquivalence="", returnTypeValuePackageName="";
      int returnTypeValueId=0;
	  ArrayList<ReturnTypeValueSequenceDiagram> returnTypeValueList = new ArrayList<ReturnTypeValueSequenceDiagram>();
	  ReturnTypeValueSequenceDiagram returnTypeValueRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
  
      // Obtain the different return type values that corresponds to the project Id, method Id 
      // and return type id
	  try
	  {
	     // SQL Statements 
		 selectSql = "select rtvsd.rtvsd_id returnTypeValueId, ";
		 selectSql = selectSql + "rtvsd.rtvsd_name returnTypeValueName, ";
		 selectSql = selectSql + "rtvsd.rtvsd_value returnTypeValueEquivalence, ";
		 selectSql = selectSql + "rtvsd.rtvsd_package_name returnTypeValuePackageName ";
		 selectSql = selectSql + "from return_type_value_sequence_diagram rtvsd ";
		 selectSql = selectSql + "where rtvsd.pc_id = ? and ";
		 selectSql = selectSql + "rtvsd.msd_id = ? and ";
		 selectSql = selectSql + "rtvsd.rt_id = ? ";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setInt(3, returnTypeId);
		 
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        returnTypeValueId          = selectResults.getInt("returnTypeValueId");
	        returnTypeValueName        = selectResults.getString("returnTypeValueName");
	        returnTypeValueEquivalence = selectResults.getString("returnTypeValueEquivalence");
	        returnTypeValuePackageName = selectResults.getString("returnTypeValuePackageName");

	        returnTypeValueRecord = new ReturnTypeValueSequenceDiagram(projectId, 
	        		                                                   methodId, 
	        		                                                   returnTypeId,
	        		                                                   returnTypeValueId,
	        		                                                   returnTypeValueName,
	        		                                                   returnTypeValueEquivalence,
	        		                                                   returnTypeValuePackageName);
	        		                                               
	        returnTypeValueList.add(returnTypeValueRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the different Return Type Values for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Return Type Identifier: " + returnTypeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return returnTypeValueList;

   } 
   
   public ArrayList<UMLSequenceDiagramFinal> loadUMLSequenceDiagramMethod(int projectId, int userId, 
		                                                                  int methodId, int methodStepId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", value1="DiagramStarts", value2="DiagramEnds",
    		 value3="ConfigurationStarts", value4="ConfigurationBody", value5="ConfigurationEnds", 
    		 lineText="", lineType1Str="", lineType2="", datumCategory1Str="", datumCategory2="", datumValue="";
      int diagramLineNumber=0, methodLineNumber=0, cmId=0;
      
      UMLSequenceDiagramFinal umlSeqDiagramFinalRecord;
      ArrayList<UMLSequenceDiagramFinal> umlSeqDiagramFinalList = new ArrayList<UMLSequenceDiagramFinal>();
      
      LineType1 lineType1;
      DatumCategory1 datumCategory1;
      
	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the UML Sequence Diagram that corresponds to the project Id, userId, method Id and methodStepId
	  try
	  {
	     // SQL Statements 
		 selectSql = "select usdf.usdf_diagram_line_number diagramLineNumber, ";
		 selectSql = selectSql + "usdf.usdf_method_line_number methodLineNumber, ";
		 selectSql = selectSql + "usdf.usdf_line_text lineText, ";
		 selectSql = selectSql + "usdf.usdf_line_type_1 lineType1, ";
		 selectSql = selectSql + "usdf.usdf_line_type_2 lineType2, ";
		 selectSql = selectSql + "usdf.usdf_datum_category_1 datumCategory1, ";
		 selectSql = selectSql + "usdf.usdf_datum_category_2 datumCategory2, ";
		 selectSql = selectSql + "usdf.usdf_datum_value datumValue, ";
		 selectSql = selectSql + "usdf.cm_id cmId ";
		 selectSql = selectSql + "from uml_sequence_diagram_final usdf ";
		 selectSql = selectSql + "where ";
		 selectSql = selectSql + "(usdf.pc_id  = ? and ";
		 selectSql = selectSql + "usdf.usd_id = ? and ";
		 selectSql = selectSql + "usdf.msd_id = ? and ";
		 selectSql = selectSql + "usdf.mssd_step_id = ?) or ";
		 selectSql = selectSql + "(usdf.pc_id  = ? and ";
		 selectSql = selectSql + "usdf.usdf_line_type_1 in (?, ?, ?, ?, ?)) ";
		 selectSql = selectSql + "order by usdf.usdf_diagram_line_number";
		 
		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, userId);
		 selectStatement.setInt(3, methodId);
		 selectStatement.setInt(4, methodStepId);
		 selectStatement.setInt(5, projectId);
		 selectStatement.setString(6, value1);
		 selectStatement.setString(7, value2);
		 selectStatement.setString(8, value3);
		 selectStatement.setString(9, value4);
		 selectStatement.setString(10, value5);
		 
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        diagramLineNumber = selectResults.getInt("diagramLineNumber");
	        methodLineNumber  = selectResults.getInt("methodLineNumber");
	        lineText          = selectResults.getString("lineText");
	        lineType1Str      = selectResults.getString("lineType1");
	        lineType2         = selectResults.getString("lineType2");
	        datumCategory1Str = selectResults.getString("datumCategory1");
	        datumCategory2    = selectResults.getString("datumCategory2");
	        datumValue        = selectResults.getString("datumValue");
	        cmId              = selectResults.getInt("cmId");
	        
	        // Convert String to Enum
	        lineType1         = LineType1.valueOf(lineType1Str);
	        datumCategory1    = DatumCategory1.valueOf(datumCategory1Str);
	        
	        umlSeqDiagramFinalRecord = new UMLSequenceDiagramFinal(projectId, diagramLineNumber, userId, 
                                                                   methodId, methodStepId,
                                                                   methodLineNumber, lineText, lineType1, 
                                                                   lineType2, datumCategory1,
                                                                   datumCategory2, datumValue, cmId);
	        		                                               
	        umlSeqDiagramFinalList.add(umlSeqDiagramFinalRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram at the Method Level" + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Project Identifier     : " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "User Identifier        : " + userId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier      : " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Step Identifier : " + methodStepId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return umlSeqDiagramFinalList;
      
   } 
   
   public int getReturnTypeValueId(int projectId, int methodId, String returnTypeValueName) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      int returnTypeValueId=0;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the details of the return type corresponds to the project Id, method Id
	  try
	  {
	     // SQL Statements 
		 selectSql = "select rtvsd.rtvsd_id returnTypeValueId ";
		 selectSql = selectSql + "from return_type_value_sequence_diagram rtvsd ";
		 selectSql = selectSql + "where rtvsd.pc_id = ? and ";
		 selectSql = selectSql + "rtvsd.msd_id = ? and ";
		 selectSql = selectSql + "rtvsd.rtvsd_value = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setString(3, returnTypeValueName);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        returnTypeValueId = selectResults.getInt("returnTypeValueId");
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Return Type Value Identifier for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Return Type Value Name: " + returnTypeValueName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return returnTypeValueId;

   }  
   
   public void saveAnnotationUMLSequenceDiagram(ArrayList<UMLSequenceDiagramFinal> umlSequenceDiagram) throws SQLException //6
   {
      int col, projectId = 0, userId=0, methodId=0, diagramLineNumber=0, 
    	  methodLineNumber=0, cmId=0, stepId=0, c=0;
	  String insertSql, errorMessage1="", errorMessage2="", lineText, lineType2, 
			 datumCategory2="", datumValue, datumCategory1Str="", lineType1Str="";
      LineType1 lineType1;
      DatumCategory1 datumCategory1;

	  PreparedStatement insertStatement = null;
	  
	  // Loop for the 3 new records
	  for (c = 0; c < umlSequenceDiagram.size(); c++) 
	  {
	     // Get the values from the GUI
	     try 
	     {
	 	    projectId         = umlSequenceDiagram.get(c).getProjectId();
	 	    userId            = umlSequenceDiagram.get(c).getUserIdentifier();
	 	    methodId          = umlSequenceDiagram.get(c).getMethodIdentifier();
	 	    diagramLineNumber = umlSequenceDiagram.get(c).getDiagramLineNumber();
	 		methodLineNumber  = umlSequenceDiagram.get(c).getMethodLineNumber();
	 		lineText          = umlSequenceDiagram.get(c).getLineText();
	 		lineType1         = umlSequenceDiagram.get(c).getLineType1();
	 		lineType2         = umlSequenceDiagram.get(c).getLineType2();
	        datumCategory1    = umlSequenceDiagram.get(c).getDatumCategory1();
	        datumCategory2    = umlSequenceDiagram.get(c).getDatumCategory2();
	        datumValue        = umlSequenceDiagram.get(c).getDatumValue();
	        cmId              = umlSequenceDiagram.get(c).getClassMethodIdentifier();
	        stepId            = umlSequenceDiagram.get(c).getMethodStepIdentifier();
	        cmId              = umlSequenceDiagram.get(c).getClassMethodIdentifier();
	 		  
	 		// Convert Enum to String
	 		lineType1Str      = lineType1.name();
	 		datumCategory1Str = datumCategory1.name();
	 		  
	   	    // SQL Statement
	   	    insertSql = "insert into uml_sequence_diagram_final (pc_id, usdf_diagram_line_number, usd_id, msd_id, "; 
	   	    insertSql = insertSql + "mssd_step_id, usdf_method_line_number, usdf_line_text, usdf_line_type_1, usdf_line_type_2, ";
	   	    insertSql = insertSql + "usdf_datum_category_1, usdf_datum_category_2, usdf_datum_value, cm_id) ";
	   	    insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	   	    insertStatement = dbCon.prepareStatement(insertSql);
	      
	   	    col = 1;
	   	    insertStatement.setInt(col++, projectId);
	   	    insertStatement.setInt(col++, diagramLineNumber);
	   	    insertStatement.setInt(col++, userId);
	   	    insertStatement.setInt(col++, methodId);
	   	    insertStatement.setInt(col++, stepId);
	   	    insertStatement.setInt(col++, methodLineNumber);
	   	    insertStatement.setString(col++, lineText);
	   	    insertStatement.setString(col++, lineType1Str);
	   	    insertStatement.setString(col++, lineType2);
	   	    insertStatement.setString(col++, datumCategory1Str);
	   	    insertStatement.setString(col++, datumCategory2);
	   	    insertStatement.setString(col++, datumValue);
	   	    insertStatement.setInt(col++, cmId);

		    insertStatement.executeUpdate();
		    insertStatement.close();
		     
	     }
		 catch(SQLException e)
		 {
		    errorMessage1 = e.getMessage();
			errorMessage2 = "Error XXXX: Occurred while inserting a new Annotation for the UML Sequence Diagram for the Project Id: " + projectId +System.lineSeparator();
			errorMessage2 = errorMessage2 + "Diagram Line Number:" + diagramLineNumber + " ,User Id :" + userId + " ,Method Id: " + methodId +  " ,Method Step Id: " + stepId +" and Method Line Number:" + methodLineNumber + System.lineSeparator();
			errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			throw new SQLException(errorMessage2);
		 }
		  
	  }

	} 
   public void updateMethodLineNumbersSequenceDiagram(ArrayList<UMLSequenceDiagramFinal> umlSequenceDiagramList) throws Exception
   {
      String lineType2="", lineText="", updateSql="", errorMessage1="", errorMessage2="", methodDefMethodlineText="", 
    		 annotationDetails="", annotationElement1="", annotationOperator="", annotationElement2="",
    		 NoteBodyAnnotationDetails="", NoteBodyAnnotationElement1="", NoteBodyAnnotationOperator="", NoteBodyAnnotationElement2="";
      
	  int col=0, projectId=0, userId=0, methodId=0, methodStepId=0, cmId=0, diagramLineNumber=0, 
		  methodLineNumber=0, c=0, methodEndDiagramLineNumber=0, methodEndMethodLineNumber=0,  
		  methodDefDiagramLineNumber=0, methodDefMethodLineNumber=0,
		  NoteEndDiagramLineNumber=0, NoteEndMethodLineNumber=0,
		  NoteStartDiagramLineNumber=0, NoteStartMethodLineNumber=0,
		  NoteBodyDiagramLineNumber=0, NoteBodyMethodLineNumber=0;
	  
	  LineType1 lineType1 = null;
	  DatumCategory1 datumCategory;
	  PreparedStatement updateStatement;
      
      //System.out.println("*** Update Method Line Numbers Sequence Diagram DATABASE ***");
      
      // Loop for all the elements of the UML Sequence Diagram ArrayList
      for (c = 0; c < umlSequenceDiagramList.size(); c++) 
  	  {
         // Get the information from the GUI
   	     projectId          = umlSequenceDiagramList.get(c).getProjectId();
   	     userId             = umlSequenceDiagramList.get(c).getUserIdentifier();
   	     methodId           = umlSequenceDiagramList.get(c).getMethodIdentifier();
   	     methodStepId       = umlSequenceDiagramList.get(c).getMethodStepIdentifier();
   	     lineText           = umlSequenceDiagramList.get(c).getLineText();
   	     lineType1          = umlSequenceDiagramList.get(c).getLineType1();
   	     lineType2          = umlSequenceDiagramList.get(c).getLineType2();
   	     datumCategory      = umlSequenceDiagramList.get(c).getDatumCategory1();
   	     diagramLineNumber  = umlSequenceDiagramList.get(c).getDiagramLineNumber();
   	     methodLineNumber   = umlSequenceDiagramList.get(c).getMethodLineNumber();
   	     cmId               = umlSequenceDiagramList.get(c).getClassMethodIdentifier();
   	     annotationDetails  = umlSequenceDiagramList.get(c).getAnnotationDetails();
  	     annotationElement1 = umlSequenceDiagramList.get(c).getAnnotationElement1();
  	     annotationOperator = umlSequenceDiagramList.get(c).getAnnotationOperator();
  	     annotationElement2 = umlSequenceDiagramList.get(c).getAnnotationElement2();
   	     
   	     if (lineType2.equals("End"))
   	     {	 
    	    methodEndDiagramLineNumber = diagramLineNumber;
   	        methodEndMethodLineNumber  = methodLineNumber;
   	     }   
   	     else 
   	        if (lineType2.equals("Method Definition"))
   	        { 	
    	       methodDefDiagramLineNumber = diagramLineNumber;
   	           methodDefMethodLineNumber  = methodLineNumber;
   	           methodDefMethodlineText    = lineText;
   	        }
   	        else
    	       if (lineType2.equals("Note Starts"))
       	       {	        	
       	          NoteStartDiagramLineNumber = diagramLineNumber;
       	          NoteStartMethodLineNumber  = methodLineNumber;
       	   	   }	
    	       else
    	    	   if (lineType2.equals("Note Body"))
    	    	   {
    	       	      NoteBodyDiagramLineNumber  = diagramLineNumber;
    	       	      NoteBodyMethodLineNumber   = methodLineNumber;
    	       	      NoteBodyAnnotationDetails  = annotationDetails;
    	       	      NoteBodyAnnotationElement1 = annotationElement1;
    	       	      NoteBodyAnnotationOperator = annotationOperator;
    	       	      NoteBodyAnnotationElement2 = annotationElement2;
    	    	   }
   	           else	
   	              if (lineType2.equals("Note Ends"))
   	              {	        	
   	                 NoteEndDiagramLineNumber = diagramLineNumber;
   	                 NoteEndMethodLineNumber  = methodLineNumber;
   	   	           }
  	  }   
      
      // Update the Method Ends Record
      diagramLineNumber = methodEndDiagramLineNumber;
      methodLineNumber  = NoteEndMethodLineNumber;
      lineType2         = "End";
      
	  try
	  {
	     updateSql = "update uml_sequence_diagram_final ";
		 updateSql = updateSql + "set usdf_method_line_number = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usd_id = ? and ";
		 updateSql = updateSql + "msd_id = ? and ";
		 updateSql = updateSql + "mssd_step_id = ? and ";
		 updateSql = updateSql + "usdf_diagram_line_number = ? and ";
		 updateSql = updateSql + "usdf_line_type_2 = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
		    
		 updateStatement.setInt(col++, methodLineNumber);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setInt(col++, methodStepId);
		 updateStatement.setInt(col++, diagramLineNumber);
		 updateStatement.setString(col++, lineType2);

		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Method Line Number for the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Diagram Line Number: " + diagramLineNumber + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Line Type 2: " + lineType1 + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	  }
	  
      // Update the Method Definition Record
      diagramLineNumber--;
      methodLineNumber--;
      lineType2        = "Method Definition";
      
	  try
	  {
	     updateSql = "update uml_sequence_diagram_final ";
		 updateSql = updateSql + "set usdf_method_line_number = ? , ";
		 updateSql = updateSql + "usdf_line_text = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usd_id = ? and ";
		 updateSql = updateSql + "msd_id = ? and ";
		 updateSql = updateSql + "mssd_step_id = ? and ";
		 updateSql = updateSql + "usdf_diagram_line_number = ? and ";
		 updateSql = updateSql + "usdf_line_type_2 = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
		    
		 updateStatement.setInt(col++, methodLineNumber);
		 updateStatement.setString(col++, methodDefMethodlineText);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setInt(col++, methodStepId);
		 updateStatement.setInt(col++, diagramLineNumber);
		 updateStatement.setString(col++, lineType2);

		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Method Line Number for the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Diagram Line Number: " + diagramLineNumber + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Line Type 2: " + lineType1 + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }
	  
      // Update the Note Starts Record
      diagramLineNumber = NoteStartDiagramLineNumber;
      methodLineNumber  = methodDefMethodLineNumber;
      lineType2         = "Note Starts";
      
	  try
	  {
	     updateSql = "update uml_sequence_diagram_final ";
		 updateSql = updateSql + "set usdf_method_line_number = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usd_id = ? and ";
		 updateSql = updateSql + "msd_id = ? and ";
		 updateSql = updateSql + "mssd_step_id = ? and ";
		 updateSql = updateSql + "usdf_diagram_line_number = ? and ";
		 updateSql = updateSql + "usdf_line_type_2 = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
		    
		 updateStatement.setInt(col++, methodLineNumber);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setInt(col++, methodStepId);
		 updateStatement.setInt(col++, diagramLineNumber);
		 updateStatement.setString(col++, lineType2);

		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Method Line Number for the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Diagram Line Number: " + diagramLineNumber + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Line Type 2: " + lineType1 + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }

	  
      // Update the Note Body Record
      diagramLineNumber++;
      methodLineNumber++;
      lineType2 = "Note Body";
      
	  try
	  {
	     updateSql = "update uml_sequence_diagram_final ";
		 updateSql = updateSql + "set usdf_method_line_number = ?, ";
		 updateSql = updateSql + "usdf_annotation_details = ?, ";
		 updateSql = updateSql + "usdf_annotation_element_1 = ?, ";
		 updateSql = updateSql + "usdf_annotation_operator = ?, ";
		 updateSql = updateSql + "usdf_annotation_element_2 = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usd_id = ? and ";
		 updateSql = updateSql + "msd_id = ? and ";
		 updateSql = updateSql + "mssd_step_id = ? and ";
		 updateSql = updateSql + "usdf_diagram_line_number = ? and ";
		 updateSql = updateSql + "usdf_line_type_2 = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
		    
		 updateStatement.setInt(col++, methodLineNumber);
		 updateStatement.setString(col++, NoteBodyAnnotationDetails);
		 updateStatement.setString(col++, NoteBodyAnnotationElement1);
		 updateStatement.setString(col++, NoteBodyAnnotationOperator);
		 updateStatement.setString(col++, NoteBodyAnnotationElement2);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setInt(col++, methodStepId);
		 updateStatement.setInt(col++, diagramLineNumber);
		 updateStatement.setString(col++, lineType2);

		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Method Line Number for the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Diagram Line Number: " + diagramLineNumber + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Line Type 2: " + lineType1 + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }
	  
      // Update the Note Ends Record
      methodLineNumber++;
      diagramLineNumber++;
      lineType2 = "Note Ends";
      
	  try
	  {
	     updateSql = "update uml_sequence_diagram_final ";
		 updateSql = updateSql + "set usdf_method_line_number = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usd_id = ? and ";
		 updateSql = updateSql + "msd_id = ? and ";
		 updateSql = updateSql + "mssd_step_id = ? and ";
		 updateSql = updateSql + "usdf_diagram_line_number = ? and ";
		 updateSql = updateSql + "usdf_line_type_2 = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
		    
		 updateStatement.setInt(col++, methodLineNumber);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setInt(col++, methodStepId);
		 updateStatement.setInt(col++, diagramLineNumber);
		 updateStatement.setString(col++, lineType2);

		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Method Line Number for the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Diagram Line Number: " + diagramLineNumber + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Line Type 2: " + lineType1 + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }

   }
   
   public void updateUserLineNumbersSequenceDiagram(int projectId) throws Exception
   {
      String updateSql="", selectSql="", errorMessage1="", errorMessage2="";	
      int col=0, userId=0, diagramLineNumber=0, methodId=0,
    	  methodStepId=0, methodLineNumber=0, c=0;
	  
	  PreparedStatement updateStatement, selectStatement1, selectStatement2;
      ResultSet selectResults1, selectResults2;
      
      //System.out.println("*** Update User Line Numbers Sequence Diagram Database ***");
      
      // Get all the Different Users in the UML Sequence Diagram Table 
      // that belong to this Project Identifier
  	  try
	  {
	     // SQL Statements 
		 selectSql = "select distinct usd_id userId ";
		 selectSql = selectSql + "from uml_sequence_diagram_final usdf ";
		 selectSql = selectSql + "where usdf.pc_id = ?";

		 selectStatement1 = dbCon.prepareStatement(selectSql);
		 selectStatement1.setInt(1, projectId);
		 
		 selectResults1 = selectStatement1.executeQuery();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading all the Users for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
  	  
  	  // Loop for all the Users
	  while(selectResults1.next()) 
	  {
	     userId = selectResults1.getInt("userId");
	     
	     c = 0;
	     
		 // Get the UML Sequence Diagram at the User Level for this User Id     
	  	 try
		 {
		    // SQL Statements 
			selectSql = "select usdf.usdf_diagram_line_number diagramLineNumber, ";
			selectSql = selectSql + "usdf.msd_id methodId, ";
			selectSql = selectSql + "usdf.mssd_step_id methodStepId, ";
			selectSql = selectSql + "usdf.usdf_method_line_number methodLineNumber ";
			selectSql = selectSql + "from uml_sequence_diagram_final usdf, ";
			selectSql = selectSql + "method_step_sequence_diagram mssd ";
			selectSql = selectSql + "where usdf.pc_id = ? and ";
			selectSql = selectSql + "usdf.usd_id = ? and ";
			selectSql = selectSql + "usdf.pc_id = mssd.pc_id and ";
			selectSql = selectSql + "usdf.usd_id = mssd.usd_id and ";
			selectSql = selectSql + "usdf.msd_id = mssd.msd_id and ";
			selectSql = selectSql + "usdf.mssd_step_id = mssd.mssd_step_id ";
			selectSql = selectSql + "order by usdf.mssd_step_id, mssd.msdd_order_id, usdf.usdf_method_line_number";

			selectStatement2 = dbCon.prepareStatement(selectSql);
			selectStatement2.setInt(1, projectId);
			selectStatement2.setInt(2, userId);
			 
			selectResults2 = selectStatement2.executeQuery();
			  
			// Loop for all the lines that contain the UML Sequence Diagram for this User
			while(selectResults2.next()) 
			{
		       diagramLineNumber = selectResults2.getInt("diagramLineNumber");
		       methodId          = selectResults2.getInt("methodId");
		       methodStepId      = selectResults2.getInt("methodStepId");
		       methodLineNumber  = selectResults2.getInt("methodLineNumber");
	            
		       // Increase the counter
		       c++;
		        
		       // Update the User Line Numbers
		       try
		  	   {
		  	      updateSql = "update uml_sequence_diagram_final ";
		  		  updateSql = updateSql + "set usdf_user_line_number = ? ";
		  		  updateSql = updateSql + "where pc_id = ? and ";
		  		  updateSql = updateSql + "usdf_diagram_line_number = ? and ";
		  		  updateSql = updateSql + "usd_id = ? and ";
		  		  updateSql = updateSql + "msd_id = ? and ";
		  		  updateSql = updateSql + "mssd_step_id = ? and ";
		  		  updateSql = updateSql + "usdf_method_line_number = ?";
		  		 
		  		  updateStatement = dbCon.prepareStatement(updateSql);	
		  		     
		  		  col = 1;
		  			    
		  		  updateStatement.setInt(col++, c);
		          updateStatement.setInt(col++, projectId);
		  		  updateStatement.setInt(col++, diagramLineNumber);
		  		  updateStatement.setInt(col++, userId);
		  		  updateStatement.setInt(col++, methodId);
		  		  updateStatement.setInt(col++, methodStepId);
		  		  updateStatement.setInt(col++, methodLineNumber);

		  		  updateStatement.executeUpdate();
		  		  updateStatement.close();
		  	   }
		  	   catch(SQLException e2)
		  	   {
		  	      errorMessage1 = e2.getMessage();
		  		  errorMessage2 = "Error XXXX: Occurred while updating the User Line Number for the Project Identifier: " + projectId + System.lineSeparator();
		  		  errorMessage2 = errorMessage2 + "Diagram Line Number: " + diagramLineNumber + System.lineSeparator();
		  		  errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		  		  errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		  		  errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
		  		  errorMessage2 = errorMessage2 + "Method Line Number: " + methodLineNumber + System.lineSeparator();
		  	      errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  		  throw new SQLException(errorMessage2);
		       }	   

			}
		 }	
		 catch(Exception e3)
		 {
		    errorMessage1 = e3.getMessage();
			errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram for the User Level for the Project Identifier: " + projectId + System.lineSeparator();
			errorMessage2 = errorMessage2 + "User Identifier  : " + userId + System.lineSeparator();
			errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		    throw new Exception(errorMessage2);
		 }
	  }  
 
   }
    
   public void updateDiagramLineNumbersSequenceDiagram(int projectId) throws Exception
   {
      String updateSql="", selectSql="", errorMessage1="", errorMessage2="", lineType1="";	
      int col=0, methodId=0, methodStepId=0, methodLineNumber=0, userId=0, userLineNumber=0, c=0;
	  
	  PreparedStatement updateStatement, selectStatement;
      ResultSet selectResults;
      
  	  lineType1 = LineType1.DiagramEnds.name();
      
      //System.out.println("*** Update Diagram Line Numbers Sequence Diagram Database ***");
 	  // Get the UML Sequence Diagram at the User Level     
  	  try
	  {
	     // SQL Statements 
		 selectSql = "select usdf.usd_id userId, ";
		 selectSql = selectSql + "usdf.msd_id methodId, ";
		 selectSql = selectSql + "usdf.mssd_step_id methodStepId, ";
		 selectSql = selectSql + "usdf.usdf_method_line_number methodLineNumber, ";
		 selectSql = selectSql + "usdf.usdf_user_line_number userLineNumber ";
		 selectSql = selectSql + "from uml_sequence_diagram_final usdf ";
		 selectSql = selectSql + "where usdf.pc_id = ? and ";
		 selectSql = selectSql + "usdf.usdf_line_type_1 <> ? ";
		 selectSql = selectSql + "order by usdf.usd_id, usdf.usdf_user_line_number";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, lineType1);
		 
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        userId            = selectResults.getInt("userId");
	        methodId          = selectResults.getInt("methodId");
	        methodStepId      = selectResults.getInt("methodStepId");
	        methodLineNumber  = selectResults.getInt("methodLineNumber");
	        userLineNumber    = selectResults.getInt("userLineNumber");
            
	        // Increase the counter
	        c++;
	        
	        // Update the Diagram Line Numbers
	        try
	  	    {
	  	       updateSql = "update uml_sequence_diagram_final ";
	  		   updateSql = updateSql + "set usdf_diagram_line_number = ? ";
	  		   updateSql = updateSql + "where pc_id = ? and ";
	  		   updateSql = updateSql + "usd_id = ? and ";
	  		   updateSql = updateSql + "msd_id = ? and ";
	  		   updateSql = updateSql + "mssd_step_id = ? and ";
	  		   updateSql = updateSql + "usdf_method_line_number = ? and ";
	  		   updateSql = updateSql + "usdf_user_line_number = ? ";
	  		 
	  		   updateStatement = dbCon.prepareStatement(updateSql);	
	  		     
	  		   col = 1;
	  			    
	  		   updateStatement.setInt(col++, c);
	           updateStatement.setInt(col++, projectId);
	  		   updateStatement.setInt(col++, userId);
	  		   updateStatement.setInt(col++, methodId);
	  		   updateStatement.setInt(col++, methodStepId);
	  		   updateStatement.setInt(col++, methodLineNumber);
	  		   updateStatement.setInt(col++, userLineNumber);

	  		   updateStatement.executeUpdate();
	  		   updateStatement.close();
	  	    }
	  	    catch(SQLException e1)
	  	    {
	  	       errorMessage1 = e1.getMessage();
	  		   errorMessage2 = "Error XXXX: Occurred while updating the Diagram Line Number for the Project Identifier: " + projectId + System.lineSeparator();
	  		   errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
	  		   errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
	  		   errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
	  		   errorMessage2 = errorMessage2 + "Method Line Number: " + methodLineNumber + System.lineSeparator();
	  		   errorMessage2 = errorMessage2 + "User Line Number: " + userLineNumber + System.lineSeparator();
	  	       errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	  		   throw new SQLException(errorMessage2);
	        }	   

		 }
	  }	
	  catch(Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram for the Diagram Level for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }

   }
   
   public void updateEndSequenceDiagram(int projectId) throws Exception
   {
      String updateSql="", selectSql="", errorMessage1="", errorMessage2="", lineType1="";	
      int col=0, maxDiagramLineNumber=0;
	  
	  PreparedStatement updateStatement, selectStatement;
      ResultSet selectResults;
      
 	  // Get the Maximum Diagram Line Number at the Project Level     
  	  try
	  {
	     // SQL Statements 
		 selectSql = "select max(usdf.usdf_diagram_line_number) maxDiagramLineNumber ";
		 selectSql = selectSql + "from uml_sequence_diagram_final usdf ";
		 selectSql = selectSql + "where usdf.pc_id  = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
            maxDiagramLineNumber = selectResults.getInt("maxDiagramLineNumber");
		 }
	  }		 
      catch(Exception e1)
	  {
         errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Maximum Diagram Line Number for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	
  	  lineType1 = LineType1.DiagramEnds.name();
  	  maxDiagramLineNumber++;
  	  
      // Update the End of the UML Sequence Diagram for this User
	  try
	  {
	     updateSql = "update uml_sequence_diagram_final usdf ";
	  	 updateSql = updateSql + "set usdf_diagram_line_number = ? ";
	     updateSql = updateSql + "where usdf.pc_id  = ? and ";
	     updateSql = updateSql + "usdf_line_type_1 = ?";

	  	 updateStatement = dbCon.prepareStatement(updateSql);	
	  		     
	  	 col = 1;
	  			    
	  	 updateStatement.setInt(col++, maxDiagramLineNumber);
	     updateStatement.setInt(col++, projectId);
	  	 updateStatement.setString(col++, lineType1);

	  	 updateStatement.executeUpdate();
	  	 updateStatement.close();
	  }
	  catch(SQLException e2)
	  {
	     errorMessage1 = e2.getMessage();
	  	 errorMessage2 = "Error XXXX: Occurred while updating the Maximum Diagram Line Number for the Project Identifier: " + projectId + System.lineSeparator();
	  	 errorMessage2 = errorMessage2 + "and Line Type 1: " + lineType1 + System.lineSeparator();
	  	 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new SQLException(errorMessage2);
	  }	   

   }    

   
   public ArrayList<UMLSequenceDiagramFinal> loadSequenceDiagramUser(int projectId, int userId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      String lineText="";
	  int lineNumber=0;
      LineType1 value1 = LineType1.DiagramStarts, value2 = LineType1.DiagramEnds, 
    		    value3 = LineType1.ConfigurationStarts, value4 = LineType1.ConfigurationBody,
 		        value5 = LineType1.ConfigurationEnds, value6 = LineType1.ConnectionStarts, 
 		        value7 = LineType1.MethodStarts, value8 = LineType1.MethodBody,
 		        value9 = LineType1.MethodEnds;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      UMLSequenceDiagramFinal umlSeqDiagRecord;
      ArrayList<UMLSequenceDiagramFinal> umlSeqDiagList = new ArrayList<UMLSequenceDiagramFinal>();
   
      // Obtain all records from the UserId
      // to obtain the corresponding UML Sequence Diagram
	  try
	  {
	     // SQL Statements      
		 selectSql = "select usdf.usdf_diagram_line_number diagramLineNumber, usdf.usdf_line_text lineText ";
		 selectSql = selectSql + "from uml_sequence_diagram_final usdf ";
		 selectSql = selectSql + "where usdf.pc_id = ? and ";
		 selectSql = selectSql + "usdf.usd_id in(0, ?) and ";
		 selectSql = selectSql + "usdf.usdf_line_type_1 in (?,?,?,?,?,?,?,?,?) ";
		 selectSql = selectSql + "order by usdf.usdf_diagram_line_number asc";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, userId);
		 // Convert the values from Enum to String
		 selectStatement.setString(3, value1.name());
		 selectStatement.setString(4, value2.name());
		 selectStatement.setString(5, value3.name());
		 selectStatement.setString(6, value4.name());
		 selectStatement.setString(7, value5.name());
		 selectStatement.setString(8, value6.name());
		 selectStatement.setString(9, value7.name());
		 selectStatement.setString(10, value8.name());
		 selectStatement.setString(11, value9.name());
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    lineNumber = selectResults.getInt("diagramLineNumber");	        
	        lineText   = selectResults.getString("lineText");

	        umlSeqDiagRecord = new UMLSequenceDiagramFinal(projectId, userId, lineNumber, lineText);
	        umlSeqDiagList.add(umlSeqDiagRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "User Identifier: " + userId + " and Diagram Line Number: " + lineNumber +  System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
	  return umlSeqDiagList;

   }
   
   public ArrayList<UMLSequenceDiagramFinal> loadSequenceDiagramMethod(UMLSequenceDiagramFinal seqDiagramFinal) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      String lineText="";
	  int lineNumber=0, projectId=0, userId=0, methodId=0, stepId=0;
      LineType1 value1 = LineType1.DiagramStarts,
 	   	        value2 = LineType1.DiagramEnds,
 		        value3 = LineType1.ConfigurationStarts,
 		        value4 = LineType1.ConfigurationBody,
 		        value5 = LineType1.ConfigurationEnds,
 		        value6 = LineType1.ConnectionStarts, 
 		        value7 = LineType1.MethodStarts,
 		        value8 = LineType1.MethodBody,
 		        value9 = LineType1.MethodEnds;
      
      PreparedStatement selectStatement;
      ResultSet selectResults;
      UMLSequenceDiagramFinal umlSeqDiagRecord;
      ArrayList<UMLSequenceDiagramFinal> umlSeqDiagList = new ArrayList<UMLSequenceDiagramFinal>();
      
      // Get the data from the GUI
      projectId = seqDiagramFinal.getProjectId();
      userId    = seqDiagramFinal.getUserIdentifier();
      methodId  = seqDiagramFinal.getMethodIdentifier();
      stepId    = seqDiagramFinal.getMethodStepIdentifier();
      
      // Obtain all records from the UserId
      // to obtain the corresponding UML Sequence Diagram
	  try
	  {
	     // SQL Statements      
		 selectSql = "select usdf.usdf_diagram_line_number diagramLineNumber, usdf.usdf_line_text lineText ";
		 selectSql = selectSql + "from uml_sequence_diagram_final usdf ";
		 selectSql = selectSql + "where (usdf.pc_id = ? and ";
		 selectSql = selectSql + "usdf.usd_id = 0 and ";
		 selectSql = selectSql + "usdf.usdf_line_type_1 in(?,?,?,?,?) ) or ";
		 selectSql = selectSql + "(usdf.pc_id = ? and ";
		 selectSql = selectSql + "usdf.usd_id = ? and ";
		 selectSql = selectSql + "usdf.msd_id = ? and ";
		 selectSql = selectSql + "usdf.mssd_step_id = ? and ";
		 selectSql = selectSql + "usdf.usdf_line_type_1 in (?,?,?,?)) ";
		 selectSql = selectSql + "order by usdf.usdf_diagram_line_number asc";
		 
		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 // Convert the values from Enum to String
		 selectStatement.setString(2, value1.name());
		 selectStatement.setString(3, value2.name());
		 selectStatement.setString(4, value3.name());
		 selectStatement.setString(5, value4.name());
		 selectStatement.setString(6, value5.name());
		 selectStatement.setInt(7, projectId);
		 selectStatement.setInt(8, userId);
		 selectStatement.setInt(9, methodId);
		 selectStatement.setInt(10, stepId);
		 selectStatement.setString(11, value6.name());
		 selectStatement.setString(12, value7.name());
		 selectStatement.setString(13, value8.name());
		 selectStatement.setString(14, value9.name());
		 
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        lineNumber = selectResults.getInt("diagramLineNumber");  
	        lineText   = selectResults.getString("lineText");

	        umlSeqDiagRecord = new UMLSequenceDiagramFinal(projectId, userId, methodId, 
	        		                                       stepId, lineNumber, lineText);
	        umlSeqDiagList.add(umlSeqDiagRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram" + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Project Identifier     : " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "User Identifier        : " + userId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier      : " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Step Identifier : " + stepId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
	  return umlSeqDiagList;

   } 

   public void updateMethodSequenceDiagram(UMLSequenceDiagramFinal umlSequenceDiagramRecord) throws Exception
   {
      String lineText="", datumValue="", updateSql="", errorMessage1="", errorMessage2="";
	  int col=0, projectId=0, userId=0, methodId=0, methodStepId=0, diagramLineNumber=0, 
		  methodLineNumber=0;
	  
	  PreparedStatement updateStatement;
      
      // Get the information from the GUI
   	  projectId         = umlSequenceDiagramRecord.getProjectId();
   	  userId            = umlSequenceDiagramRecord.getUserIdentifier();
   	  methodId          = umlSequenceDiagramRecord.getMethodIdentifier();
   	  methodStepId      = umlSequenceDiagramRecord.getMethodStepIdentifier();
   	  lineText          = umlSequenceDiagramRecord.getLineText();
   	  diagramLineNumber = umlSequenceDiagramRecord.getDiagramLineNumber();
   	  methodLineNumber  = umlSequenceDiagramRecord.getMethodLineNumber();
   	  datumValue        = umlSequenceDiagramRecord.getDatumValue();
      
	  try
	  {
	     updateSql = "update uml_sequence_diagram_final ";
		 updateSql = updateSql + "set usdf_line_text = ?, ";
		 updateSql = updateSql + "usdf_datum_value = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usdf_diagram_line_number = ? and "; 
		 updateSql = updateSql + "usd_id = ? and ";
		 updateSql = updateSql + "msd_id = ? and ";
		 updateSql = updateSql + "mssd_step_id = ? and ";
		 updateSql = updateSql + "usdf_method_line_number = ?"; 
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
		    
		 updateStatement.setString(col++, lineText);
		 updateStatement.setString(col++, datumValue);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, diagramLineNumber);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setInt(col++, methodStepId);
		 updateStatement.setInt(col++, methodLineNumber);

		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while the Method Identifier: " + methodId + System.lineSeparator(); 
		  errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Diagram Line Number: " + diagramLineNumber + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Line Number: " + methodLineNumber + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	  }   
   
   }   
   
   public void updateAnnotationUMLSequenceDiagram(ArrayList<UMLSequenceDiagramFinal> umlSequenceDiagram) throws SQLException //6
   {
      int col, projectId = 0, userId=0, methodId=0, diagramLineNumber=0, 
    	  methodLineNumber=0, stepId=0, c=0;
	  String updateSql="", errorMessage1="", errorMessage2="", lineText="", 
			 datumValue="";

	  PreparedStatement updateStatement = null;
	  
	  // Loop for the 3 new records
	  for (c = 0; c < umlSequenceDiagram.size(); c++) 
	  {
	     // Get the values from the GUI
	     try 
	     {
	 	    projectId         = umlSequenceDiagram.get(c).getProjectId();
	 	    userId            = umlSequenceDiagram.get(c).getUserIdentifier();
	 	    methodId          = umlSequenceDiagram.get(c).getMethodIdentifier();
	 	    diagramLineNumber = umlSequenceDiagram.get(c).getDiagramLineNumber();
	 		methodLineNumber  = umlSequenceDiagram.get(c).getMethodLineNumber();
	 		lineText          = umlSequenceDiagram.get(c).getLineText();
	        datumValue        = umlSequenceDiagram.get(c).getDatumValue();
	        stepId            = umlSequenceDiagram.get(c).getMethodStepIdentifier();
	 		  
	   	    // SQL Statement
		    updateSql = "update uml_sequence_diagram_final ";
			updateSql = updateSql + "set usdf_line_text = ? ,";
			updateSql = updateSql + "usdf_datum_value = ? ";
			updateSql = updateSql + "where pc_id = ? and ";
			updateSql = updateSql + "usdf_diagram_line_number = ? and ";
			updateSql = updateSql + "usd_id = ? and ";
			updateSql = updateSql + "msd_id = ? and ";
			updateSql = updateSql + "mssd_step_id = ? and ";
			updateSql = updateSql + "usdf_method_line_number = ?";
		    updateStatement = dbCon.prepareStatement(updateSql);	
		     
			col = 1;
			
			updateStatement.setString(col++, lineText);	  
			updateStatement.setString(col++, datumValue);	  
			updateStatement.setInt(col++, projectId);
			updateStatement.setInt(col++, diagramLineNumber);
			updateStatement.setInt(col++, userId);
			updateStatement.setInt(col++, methodId);
			updateStatement.setInt(col++, stepId);
			updateStatement.setInt(col++, methodLineNumber);

			updateStatement.executeUpdate();
			updateStatement.close();
	      		     
	     }
		 catch(SQLException e)
		 {
		    errorMessage1 = e.getMessage();
			errorMessage2 = "Error XXXX: Occurred while updating an existing Annotation for the UML Sequence Diagram for the Project Id: " + projectId +System.lineSeparator();
			errorMessage2 = errorMessage2 + "Diagram Line Number:" + diagramLineNumber + " ,User Id :" + userId + " ,Method Id: " + methodId +  " ,Method Step Id: " + stepId +" and Method Line Number:" + methodLineNumber + System.lineSeparator();
			errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			throw new SQLException(errorMessage2);
		 }
		  
	  }

	} 

   public void updateAnnotationType(UMLSequenceDiagramFinal umlSequenceDiagramRecord) throws Exception
   {
      String annotationTypeStr="", updateSql="", errorMessage1="", errorMessage2="";
	  int col=0, projectId=0, userId=0, methodId=0, methodStepId=0;
	  AnnotationType annotationType;
	  
	  PreparedStatement updateStatement;
      
      // Get the information from the GUI
   	  projectId         = umlSequenceDiagramRecord.getProjectId();
   	  userId            = umlSequenceDiagramRecord.getUserIdentifier();
   	  methodId          = umlSequenceDiagramRecord.getMethodIdentifier();
   	  methodStepId      = umlSequenceDiagramRecord.getMethodStepIdentifier();
   	  annotationType    = umlSequenceDiagramRecord.getAnnotationType();
   	  
   	  annotationTypeStr = annotationType.name();
      
	  try
	  {
	     updateSql = "update uml_sequence_diagram_final ";
		 updateSql = updateSql + "set usdf_annotation_type = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usd_id = ? and ";
		 updateSql = updateSql + "msd_id = ? and ";
		 updateSql = updateSql + "mssd_step_id = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
		    
		 updateStatement.setString(col++, annotationTypeStr);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setInt(col++, methodStepId);

		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Annotation Type for the Project Identifier: " + projectId + System.lineSeparator(); 
		  errorMessage2 = errorMessage2 + "User Identifier: " + userId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Method Step Identifier: " + methodStepId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	  }   
   
   }  
   
   public String getReturnTypePackageName(int projectId, int methodId, int returnTypeId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", returnTypePackageName="";

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the details of the return type package name that corresponds to the project Id, method Id and return type id
	  try
	  {
	     // SQL Statements 
		 selectSql = "select distinct rtvsd.rtvsd_package_name returnTypePackageName ";
		 selectSql = selectSql + "from return_type_value_sequence_diagram rtvsd ";
		 selectSql = selectSql + "where rtvsd.pc_id = ? and ";
		 selectSql = selectSql + "rtvsd.msd_id = ? and ";
		 selectSql = selectSql + "rtvsd.rt_id = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setInt(3, returnTypeId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    returnTypePackageName = selectResults.getString("returnTypePackageName");
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Return Type Package Name for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Return Type Identifier: " + returnTypeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return returnTypePackageName;

   }  
   
   public MethodSequenceDiagram getMethodDetails2(int projectId, int methodId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", ownerName="", packageName="", className="", shortName="";

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      MethodSequenceDiagram methodSeqDiagramRecord = null;
      
      // Obtain the details of the return type package name that corresponds to the project Id, method Id and return type id
	  try
	  {
	     // SQL Statements 
		 selectSql = "select msd.msd_owner ownerName, ";
		 selectSql = selectSql + "msd.msd_package_name packageName, ";
		 selectSql = selectSql + "msd.msd_class_name className, ";
		 selectSql = selectSql + "msd.msd_short_name shortName ";
		 selectSql = selectSql + "from method_sequence_diagram msd ";
		 selectSql = selectSql + "where msd.pc_id = ? and ";
		 selectSql = selectSql + "msd.msd_id = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			ownerName   = selectResults.getString("ownerName");
		    packageName = selectResults.getString("packageName");
		    className   = selectResults.getString("className");
		    shortName   = selectResults.getString("shortName");
		    
		    methodSeqDiagramRecord = new MethodSequenceDiagram(projectId, methodId, ownerName, packageName, className, shortName);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Method Details for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return methodSeqDiagramRecord;

   } 
   
   public void updateUserFileSequenceDiagram(UserData userData) throws Exception
   {
      String userTextFileName="", userImageFileName="", updateSql="", 
    		 errorMessage1="", errorMessage2="";
	  int col=0, projectId=0, userId=0;
	  
	  PreparedStatement updateStatement;
      
      // Get the information from the GUI
   	  projectId             = userData.getProjectIdentifier();
   	  userId                = userData.getUserIdentifier();
   	  userTextFileName      = userData.getUserTextFileName();
   	  userImageFileName     = userData.getUserImageFileName();
   	    	  
	  try
	  {
	     updateSql = "update user_file_sequence_diagram ";
		 updateSql = updateSql + "set ufsd_text_filename = ?, ";
		 updateSql = updateSql + "ufsd_png_filename = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usd_id = ? and "; 
		 updateSql = updateSql + "ufsd_file_id = 1";
		 
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
		    
		 updateStatement.setString(col++, userTextFileName);
		 updateStatement.setString(col++, userImageFileName);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);

		 updateStatement.executeUpdate();
		 updateStatement.close();
		 
	   }
	   catch(SQLException e1)
	   {
	      errorMessage1 = e1.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the User Identifier: " + userId + System.lineSeparator(); 
		  errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "User Text File: " + userTextFileName + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "User Image File: " + userImageFileName + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	  }   
   
   }     
   
}