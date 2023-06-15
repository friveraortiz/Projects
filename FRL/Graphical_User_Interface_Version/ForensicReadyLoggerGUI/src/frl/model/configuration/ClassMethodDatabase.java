package frl.model.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class ClassMethodDatabase
{

   // Atributes for the ForensicReadyLoggerDatabase 
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
	   
   // Constructor of the Class
   public ClassMethodDatabase() //1
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
			   errorMessage2 = "Error XXXX: Occurred while closing the Database Configuration File.." + System.lineSeparator();
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
		 errorMessage2 = "Error XXXX: The Database Driver was not found.." + System.lineSeparator();
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
	      catch (Exception e) 
		  {
			 errorMessage1 = e.getMessage();
			 errorMessage2 = "Error XXXX: The Database Connection cannot be closed.." + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		     throw new Exception(errorMessage2);
		  }
	   }
		
	   dbCon = null;
	 }

	public void deleteClassMethod(int projectId) throws Exception //5
	{
       String deleteSql="", errorMessage1="", errorMessage2="";	
       
	   // Delete all the existing Methods
	   try 
	   {
          deleteSql = "delete from class_method ";
          deleteSql = deleteSql + "where pc_id = ? ";

	      PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	      deleteStatement.setInt(1, projectId);
	      deleteStatement.executeUpdate();
	      deleteStatement.close();
	   }
	   catch(Exception e)
	   {
	      errorMessage1 = e.getMessage();
	      errorMessage2 = "Error XXXX: Occurred while deleting the existing Methods for the Project Id: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	   
	}
	
	public void saveClassMethod(ClassMethod classMethod) throws Exception //6
	{
	   int projectId=0, id=0, startLineNumber=0, sdId=0, col=0;
	   String packageName="", className="", fullMethodName="", 
	          shortMethodName="", returnType1="", returnType2="", fileName="",
	          paramMethodName="", insertSql="", errorMessage1="", errorMessage2="";
       PreparedStatement insertStatement;
		
	   // Get the values from the GUI
       try 
       {
    	  projectId       = classMethod.getProjectId(); 
	      id              = classMethod.getId();
	      packageName     = classMethod.getPackageName();
	      className       = classMethod.getClassName();
	      fullMethodName  = classMethod.getFullMethodName();
	      shortMethodName = classMethod.getShortMethodName();
	      returnType1     = classMethod.getReturnType1();
	      returnType2     = classMethod.getReturnType2();
	      fileName        = classMethod.getFileName();	
	      startLineNumber = classMethod.getStartLineNumber();
	      paramMethodName = classMethod.getParameterMethodName();
	      sdId            = classMethod.getSdId();

	      // SQL Statements
	      insertSql = "insert into class_method (pc_id, cm_id, cm_package_name, cm_class_name, ";
		  insertSql = insertSql + "cm_full_method_name, cm_short_method_name, cm_return_type_1, ";
		  insertSql = insertSql + "cm_return_type_2, cm_filename, cm_start_line_number, ";
		  insertSql = insertSql + "cm_parameter_method_name, sd_id) ";
		  insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	      insertStatement = dbCon.prepareStatement(insertSql);
   
	      col = 1;
	      insertStatement.setInt(col++, projectId);
	      insertStatement.setInt(col++, id);
	      insertStatement.setString(col++, packageName);
	      insertStatement.setString(col++, className);
	      insertStatement.setString(col++, fullMethodName);
	      insertStatement.setString(col++, shortMethodName);
	      insertStatement.setString(col++, returnType1);
	      insertStatement.setString(col++, returnType2);
	      insertStatement.setString(col++, fileName);
	      insertStatement.setInt(col++, startLineNumber);
	      insertStatement.setString(col++, paramMethodName);
	      insertStatement.setInt(col++, sdId);
		   
	      insertStatement.executeUpdate();
		
	      insertStatement.close();
       }
	   catch(Exception e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while inserting a new Method : " + shortMethodName + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "For the Project Id: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Package Name: " + packageName + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
		
   } 

   public ArrayList<ClassMethod> load(String inputClassName, int projectId) throws Exception //7
   {

	  int id=0, startLineNumber=0, endLineNumber=0, sdId=0; 
	  String packageName="", className="", fullMethodName="", shortMethodName="", returnType1="", paramMethodName="";
	  String returnType2="", fileName="",  className1="";
	  String errorMessage1="", errorMessage2="", sql1="";
	     
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
	  ClassMethod classMethod;

	  try
	  {
	     // Load Existing ClassMethodDetails
	     className1 = inputClassName;

	     // SQL Statements      
	     sql1 = "select cm_id, cm_package_name, cm_class_name, cm_short_method_name, ";
	     sql1 = sql1 + "cm_full_method_name, cm_return_type_1, cm_return_type_2, cm_filename, ";
	     sql1 = sql1 + "cm_start_line_number, cm_end_line_number, cm_parameter_method_name, ";
	     sql1 = sql1 + "sd_id ";
	     sql1 = sql1 + "from class_method ";
	     sql1 = sql1 + "where pc_id = ? and ";
	     sql1 = sql1 + "cm_class_name = ? ";
	     sql1 = sql1 + "order by cm_class_name, cm_start_line_number asc";
		  
	     selectStatement = dbCon.prepareStatement(sql1);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, className1);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		  
		    id              = selectResults.getInt("cm_id"); 
		    packageName     = selectResults.getString("cm_package_name");
		    className       = selectResults.getString("cm_class_name");
		    fullMethodName  = selectResults.getString("cm_full_method_name");
		    shortMethodName = selectResults.getString("cm_short_method_name");
		    returnType1     = selectResults.getString("cm_return_type_1");
		    returnType2     = selectResults.getString("cm_return_type_2");
		    fileName        = selectResults.getString("cm_filename");
		    startLineNumber = selectResults.getInt("cm_start_line_number");
		    endLineNumber   = selectResults.getInt("cm_end_line_number");
		    paramMethodName = selectResults.getString("cm_parameter_method_name");
		    sdId            = selectResults.getInt("sd_id");
		 
		    classMethod = new ClassMethod(projectId, id, packageName, className, fullMethodName, 
				                          shortMethodName, returnType1, returnType2, fileName,  
                                          startLineNumber, endLineNumber, paramMethodName, sdId);
		 
		    // Add fields to the ArrayList.
		    classMethodList.add(classMethod); 
							
	     }
			
	     selectResults.close();
	     selectStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the details of the Methods for the Class: " + inputClassName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
      return classMethodList;

   }
   
   public void updateEndLineNumbers(int classMethodId, int endLineNumber, int projectId) throws Exception //8
   {
      String updateSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement updateStatement;
	  int col=0;
	  
	  try
	  {
	     updateSql = "update class_method set ";
   		 updateSql = updateSql + "cm_end_line_number=? ";
   		 updateSql = updateSql + "where pc_id=? and ";
   		 updateSql = updateSql + "cm_id=?";
	     updateStatement = dbCon.prepareStatement(updateSql);	  
	     col = 1;
		
	     updateStatement.setInt(col++, endLineNumber);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, classMethodId);
	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Methods.." + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
   }

   public ArrayList<ClassMethod> loadZeroEndLineNumbers(int projectId) throws Exception //9
   {
      int id=0, startLineNumber=0, endLineNumber=0, sdId=0;
	  String packageName="", className="", shortMethodName="", fullMethodName="", returnType1="", paramMethodName="";
	  String returnType2="", fileName="", sql1="", errorMessage1="", errorMessage2="";

	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
	  ClassMethod classMethodDetails;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;

	  try 
	  {
	     // SQL Statements      
	     sql1 = "select cm_id, cm_package_name, cm_class_name, ";
	     sql1 = sql1 + "cm_short_method_name, cm_full_method_name, ";
	     sql1 = sql1 + "cm_return_type_1, cm_return_type_2, cm_filename, ";
	     sql1 = sql1 + "cm_start_line_number, cm_end_line_number, cm_parameter_method_name, sd_id ";
	     sql1 = sql1 + "from class_method ";
	     sql1 = sql1 + "where (pc_id = ? and ";
	     sql1 = sql1 + "cm_end_line_number = 0) or ";
	     sql1 = sql1 + "(cm_start_line_number > cm_end_line_number) ";
	     sql1 = sql1 + "order by cm_id asc";

	     selectStatement = dbCon.prepareStatement(sql1);
	     selectStatement.setInt(1, projectId);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		    id              = selectResults.getInt("cm_id"); 
		    packageName     = selectResults.getString("cm_package_name");
		    className       = selectResults.getString("cm_class_name");
		    shortMethodName = selectResults.getString("cm_short_method_name");
		    fullMethodName  = selectResults.getString("cm_full_method_name");
		    returnType1     = selectResults.getString("cm_return_type_1");
		    returnType2     = selectResults.getString("cm_return_type_2");
		    fileName        = selectResults.getString("cm_filename");
	        startLineNumber = selectResults.getInt("cm_start_line_number");
	        endLineNumber   = selectResults.getInt("cm_end_line_number");
	        paramMethodName = selectResults.getString("cm_parameter_method_name");
	        sdId            = selectResults.getInt("sd_id");
		 
		    classMethodDetails = new ClassMethod(projectId, id, packageName, className, 
                                                 fullMethodName, shortMethodName, returnType1, 
                                                 returnType2, fileName, startLineNumber, endLineNumber, 
                                                 paramMethodName, sdId);
	     
	        // Add fields to the ArrayList.
		    classMethodList.add(classMethodDetails); 
							
         }
			
	     selectResults.close();
	     selectStatement.close();
	  }   
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the Methods that have End Line Numbers equal to Zero.." + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
      return classMethodList;

   }  
   
   public void updateTextFileName(int classMethodId, String textFileName, int projectId) throws Exception //10
   {
      String updateSql="", errorMessage1="", errorMessage2="";
	  int col=0;
	  PreparedStatement updateStatement;
	  
	  try
	  {
         updateSql = "update class_method set ";
	     updateSql = updateSql + "cm_text_filename=? ";
	     updateSql = updateSql + "where pc_id=? and ";
	     updateSql = updateSql + "cm_id=?";
         updateStatement = dbCon.prepareStatement(updateSql);

         col = 1;
         
	     updateStatement.setString(col++, textFileName);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, classMethodId);
	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Text File Names in the Methods.." + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
   }
   
   public ClassMethod loadMethodNameDetails_1(int projectId, String methodName) throws Exception //11
   {
	  String packageName="", className="", shortMethodName="", returnType1="", returnType2="", 
			 errorMessage1="", errorMessage2="", sql1="";
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ClassMethod cm;
	  	  
	  try
	  {
	     // SQL Statements      
	     sql1 = "select ";
	     sql1 = sql1 + "cm.cm_package_name packageName, ";
	     sql1 = sql1 + "cm.cm_class_name className, ";
	     sql1 = sql1 + "cm.cm_short_method_name shortMethodName, ";
	     sql1 = sql1 + "cm.cm_return_type_1 returnType1, ";
	     sql1 = sql1 + "cm.cm_return_type_2 returnType2 ";
	     sql1 = sql1 + "from class_method cm ";
	     sql1 = sql1 + "where cm.pc_id = ? and ";
	     sql1 = sql1 + "concat(cm.cm_package_name,'.', cm.cm_class_name,'.', cm.cm_short_method_name) = ?";

	     selectStatement = dbCon.prepareStatement(sql1);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, methodName);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
	        packageName     = selectResults.getString("packageName");
	        className       = selectResults.getString("className");
	        shortMethodName = selectResults.getString("shortMethodName");
	    	returnType1     = selectResults.getString("returnType1");
	    	returnType2     = selectResults.getString("returnType2");
	     }
	  
	     cm = new ClassMethod(projectId, packageName, className, shortMethodName, returnType1, returnType2);
			
	     selectResults.close();
	     selectStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the details of the Method : " + methodName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Project Id: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
      return cm;

   }
   
   public ClassMethod loadMethodNameDetails_2(String packageName1, String className1, 
		                                     String shortMethodName1, int projectId) throws Exception //11
   {
	  String sql1="", shortMethodName="", returnType="", 
			 errorMessage1="", errorMessage2="";
	  int id=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ClassMethod cm;
	  
	  try
	  {
	     // SQL Statements      
		  sql1 = "select cm.cm_id id, cm.cm_return_type_1 returnType1 ";
		  sql1 = sql1 + "from class_method cm ";
		  sql1 = sql1 + "where cm.pc_id = ? and ";
		  sql1 = sql1 + "cm.cm_package_name = ? and ";
		  sql1 = sql1 + "cm.cm_class_name = ? and ";
		  sql1 = sql1 + "cm.cm_short_method_name = ?";
	  
		  selectStatement = dbCon.prepareStatement(sql1);
		  selectStatement.setInt(1, projectId);
		  selectStatement.setString(2, packageName1);
		  selectStatement.setString(3, className1);
		  selectStatement.setString(4, shortMethodName1);
		  selectResults = selectStatement.executeQuery();
	  
		  while(selectResults.next()) 
		  {
		     id               = selectResults.getInt("id");
			 returnType       = selectResults.getString("returnType1");
		  }
	  
		  cm = new ClassMethod(projectId, id, shortMethodName, returnType);
			
		  selectResults.close();
		  selectStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading some details version 2 of the Method : " + shortMethodName1 + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Package Name: " + packageName1 + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Class Name: " + className1 + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Project Id: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
      return cm;

   }
      
   public void updateDatabaseMethod(int projectId, int id) throws Exception //8
   {
      String updateSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement updateStatement;
	  int col=0;
	  boolean flag=true;
	  
	  try
	  {
	     updateSql = "update class_method ";
   		 updateSql = updateSql + "set cm_database_method = ? ";
   		 updateSql = updateSql + "where pc_id = ? and ";
   		 updateSql = updateSql + "cm_id = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	  
	     col = 1;
		
	     updateStatement.setBoolean(col++, flag);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, id);
	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Database Method field from the Method with Identifier: " + id + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "and Project Identifier: "+ projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
   }
   
   public ArrayList<ClassMethod> loadJavaFiles(int projectId) throws Exception //7
   {

	  int id=0, startLineNumber=0, endLineNumber=0; 
	  String packageName="", className="", fullMethodName="", shortMethodName="",
			 fileName="", sql1="", errorMessage1="", errorMessage2="";
	     
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
	  ClassMethod classMethod;

	  try
	  {
	     // Load Existing ClassMethodDetails

	     // SQL Statements      
	     sql1 = "select cm_id, cm_package_name, cm_class_name, cm_short_method_name, ";
	     sql1 = sql1 + "cm_full_method_name, cm_filename, ";
	     sql1 = sql1 + "cm_start_line_number, cm_end_line_number ";
	     sql1 = sql1 + "from class_method ";
	     sql1 = sql1 + "where pc_id = ? ";
	     sql1 = sql1 + "order by cm_id asc";
		  
	     selectStatement = dbCon.prepareStatement(sql1);
	     selectStatement.setInt(1, projectId);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		  
		    id              = selectResults.getInt("cm_id"); 
		    packageName     = selectResults.getString("cm_package_name");
		    className       = selectResults.getString("cm_class_name");
		    shortMethodName = selectResults.getString("cm_short_method_name");
		    fullMethodName  = selectResults.getString("cm_full_method_name");
		    fileName        = selectResults.getString("cm_filename");
		    startLineNumber = selectResults.getInt("cm_start_line_number");
		    endLineNumber   = selectResults.getInt("cm_end_line_number");
		 
		    classMethod = new ClassMethod(projectId, id, packageName, className, 
		    		                      shortMethodName, fullMethodName, fileName,  
                                          startLineNumber, endLineNumber);
		 
		    // Add fields to the ArrayList.
		    classMethodList.add(classMethod); 
							
	     }
			
	     selectResults.close();
	     selectStatement.close();
	  }
      catch(Exception e1)
      {
         errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the Java Files for the Project Id: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + 
	    		         errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
      return classMethodList;

   }  
   
   public void updateStartLineNumbers(int projectId, int classMethodId, 
		                              int startLineNumber) throws Exception //8
   {
      String updateSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement updateStatement;
	  int col=0;
	  
	  try
	  {
	     updateSql = "update class_method ";
   		 updateSql = updateSql + "set cm_start_line_number=? ";
   		 updateSql = updateSql + "where pc_id=? and ";
   		 updateSql = updateSql + "cm_id=?";
	     updateStatement = dbCon.prepareStatement(updateSql);	  
	     col = 1;
		
	     updateStatement.setInt(col++, startLineNumber);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, classMethodId);
	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
	  }
      catch(Exception e1)
      {
         errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Project Id: " + projectId + " Method Id: " + classMethodId + System.lineSeparator();
	     errorMessage2 =  errorMessage2 + "and Start Line Number: " + startLineNumber + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
   }   
   
   public void updateClassInformation(int projectId, String packageName, String className, 
		                              int ciId) throws Exception //8
   {
      String updateSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement updateStatement;
	  int col=0;
	  
	  try
	  {
	     updateSql = "update class_method ";
   		 updateSql = updateSql + "set ci_id = ? ";
   		 updateSql = updateSql + "where pc_id = ? and ";
   		 updateSql = updateSql + "cm_package_name = ? and ";
   		 updateSql = updateSql + "cm_class_name = ?";
   		 
	     updateStatement = dbCon.prepareStatement(updateSql);	  
	     col = 1;
		
	     updateStatement.setInt(col++, ciId);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setString(col++, packageName);
	     updateStatement.setString(col++, className);
	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Class Information Identifier: " + ciId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "For the Package Name: " + packageName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Project Id: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
   }   


   public void saveClassAttribute(ClassAttribute classAttribute) throws Exception //6
   {
      int projectId=0, id=0, ciId=0, col=0;
      String packageName="", className="", fullName="", shortName="", dataType="", insertSql="", 
    		 errorMessage1="", errorMessage2="";
      PreparedStatement insertStatement;
	
      // Get the values from the GUI
      try 
      {
	     projectId   = classAttribute.getProjectId(); 
         id          = classAttribute.getId();
         packageName = classAttribute.getPackageName();
         className   = classAttribute.getClassName();
         fullName    = classAttribute.getFullName();
         shortName   = classAttribute.getShortName();
         ciId        = classAttribute.getClassInfoId();
         dataType    = classAttribute.getDataType();

         // SQL Statements
         insertSql = "insert into class_attribute(pc_id, ca_id, ca_package_name, ca_class_name, ";
	     insertSql = insertSql + "ca_full_name, ca_short_name, ci_id, ca_data_type) ";
	     insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?)";
         insertStatement = dbCon.prepareStatement(insertSql);

         col = 1;
         insertStatement.setInt(col++, projectId);
         insertStatement.setInt(col++, id);
         insertStatement.setString(col++, packageName);
         insertStatement.setString(col++, className);
         insertStatement.setString(col++, fullName);
         insertStatement.setString(col++, shortName);
         insertStatement.setInt(col++, ciId);
         insertStatement.setString(col++, dataType);
	   
         insertStatement.executeUpdate();
	
         insertStatement.close();
      }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while inserting a new Attribute for the Project Id: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Class Information Identifier : "  + ciId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Attribute Package Name : "  + packageName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Attribute Class Name   : "  + className   + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Attribute Short Name   : "  + shortName   + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	
   }
   
   public void deleteClassAttribute(int projectId) throws Exception //5
   {
      String deleteSql="", errorMessage1="", errorMessage2="";	
      
	   // Delete all the existing Attributes
	   try 
	   {
         deleteSql = "delete from class_attribute ";
         deleteSql = deleteSql + "where pc_id = ?";

	      PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	      deleteStatement.setInt(1, projectId);
	      deleteStatement.executeUpdate();
	      deleteStatement.close();
	   }
	   catch(Exception e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while deleting the existing Attributes for the Project Id: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	   
   }


   public ArrayList<ClassMethod> loadIncompleteParameterMethods(int projectId) throws Exception //7
   {

	  int id=0, startLineNumber=0; 
	  String fullMethodName="", paramMethodName="", javaFileName="", sql1="", errorMessage1="", errorMessage2="";
	     
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
	  ClassMethod classMethod;
	  
	  try
	  {
	     // SQL Statements      
	     sql1 = "select cm_id id, ";
	     sql1 = sql1 + "cm_full_method_name fullMethodName, ";
	     sql1 = sql1 + "cm_parameter_method_name parameterMethodName, ";
	     sql1 = sql1 + "cm_start_line_number startLineNumber, ";
	     sql1 = sql1 + "cm_filename javaFileName ";
	     sql1 = sql1 + "from class_method ";
	     sql1 = sql1 + "where pc_id = ? and ";
	     sql1 = sql1 + "cm_parameter_method_name NOT LIKE '%{%'";
		  
	     selectStatement = dbCon.prepareStatement(sql1);
	     selectStatement.setInt(1, projectId);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		  
		    id              = selectResults.getInt("id"); 
		    fullMethodName  = selectResults.getString("fullMethodName");
		    paramMethodName = selectResults.getString("parameterMethodName");
		    startLineNumber = selectResults.getInt("startLineNumber");
		    javaFileName    = selectResults.getString("javaFileName");
		 
		    classMethod = new ClassMethod(projectId, id, fullMethodName, paramMethodName, javaFileName,
			                              startLineNumber);
		    
		 
		    // Add fields to the ArrayList.
		    classMethodList.add(classMethod); 
							
	     }
			
	     selectResults.close();
	     selectStatement.close();
	  }
      catch(Exception e1)
      {
         errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the Incomplete Parameter Method Names for the Project Id: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Identifier: " + id + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Full Method Name: " + fullMethodName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Parameter Method Name: " + paramMethodName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
      return classMethodList;

   }   

   public void updateParameterMethodName(int projectId, int classMethodId, String parameterMethodName) throws Exception //8
   {
      String updateSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement updateStatement;
	  int col=0;
	  
	  try
	  {
	     updateSql = "update class_method ";
		 updateSql = updateSql + "set cm_parameter_method_name = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "cm_id = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
	     col = 1;
		
	     updateStatement.setString(col++, parameterMethodName);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, classMethodId);
	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
	  }
      catch(Exception e1)
      {
         errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Parameter Method Name : " + System.lineSeparator() + parameterMethodName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Class Method Identifier: " + classMethodId + System.lineSeparator();
	     
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
   }  
   
   public int saveClassInformation(ClassInformation classInformation) throws Exception //6
   {
	   int col=0, projectId=0, id=0, databaseClass=0, guiClass=0, interfaceFlag=0, enumFlag=0;
	   String insertSql="", errorMessage1="", errorMessage2="", databaseClassType="", 
			   packageName="", className="", fileName="";
       PreparedStatement insertStatement;
      
       		
	  // Get the values from the GUI
      try 
      {
   	     projectId         = classInformation.getProjectId();
	     id                = classInformation.getId();
	     packageName       = classInformation.getPackageName();
	     className         = classInformation.getClassName();
	     fileName          = classInformation.getFileName();
	     
	     databaseClass     = classInformation.getDatabaseClass();
	     databaseClassType = classInformation.getDatabaseClassType();
	     
	     interfaceFlag     = classInformation.getInterfaceFlag();
	     guiClass          = classInformation.getGuiClass();
	     enumFlag          = classInformation.getEnumClass();
	     
	     // SQL Statements
	     insertSql = "insert into class_information(pc_id, ci_id, ci_package_name, ci_class_name, ";
	     insertSql = insertSql + "ci_filename, ci_database_class, ci_database_class_type, ";
	     insertSql = insertSql + "ci_interface, ci_gui_class, ci_enum_class) ";
		 insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);
  
	     col = 1;
	     insertStatement.setInt(col++, projectId);
	     insertStatement.setInt(col++, id);
	     insertStatement.setString(col++, packageName);
	     insertStatement.setString(col++, className);
	     insertStatement.setString(col++, fileName);
	     insertStatement.setInt(col++, databaseClass);
	     insertStatement.setString(col++, databaseClassType);
	     insertStatement.setInt(col++, interfaceFlag);
	     insertStatement.setInt(col++, guiClass);
	     insertStatement.setInt(col++, enumFlag);
		   
	     insertStatement.executeUpdate();
		
	     insertStatement.close();
      }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Class Information record with id: " + id + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "For the Project Id: "  + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Package Name : "       + packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Name : "         + className + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "FileName : "           + fileName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Database Class: "      + databaseClass + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Database Class Type: " + databaseClassType + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Interface Flag: "      + interfaceFlag + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Enum Flag: "           + enumFlag + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "       + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
      return id;   
   }  

   public void deleteClassInformation(int projectId) throws Exception //5
   {
      String deleteSql="", errorMessage1="", errorMessage2="";	
   
	   // Delete all the existing Attributes
	   try 
	   {
         deleteSql = "delete from class_information ";
         deleteSql = deleteSql + "where pc_id = ?";

	      PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	      deleteStatement.setInt(1, projectId);
	      deleteStatement.executeUpdate();
	      deleteStatement.close();
	   }
	   catch(Exception e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while deleting the existing Class Information records for the Project Id: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	   
   }  
   
   public void updateInterfaceFlag(int projectId, int classInfoId, int interfaceFlag) throws Exception //8
   {
      String updateSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement updateStatement;
	  int col=0;
	  
	  try
	  {
	     updateSql = "update class_information ";
   		 updateSql = updateSql + "set ci_interface = ? ";
   		 updateSql = updateSql + "where pc_id = ? and ";
   		 updateSql = updateSql + "ci_id = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);
	     
	     col = 1;
	     updateStatement.setInt(col++, interfaceFlag);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, classInfoId);
	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Class Information Identifier: " + classInfoId  + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Project Identifier: " + projectId  + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
   } 
	
   public void saveSourceDirectory(SourceDirectory sourceDirectory) throws Exception 
   {
      int projectId=0, idDir=0, col=0;
	  String nameDir="", pathDir="", insertSql="", errorMessage1="", errorMessage2="";
      PreparedStatement insertStatement;
		
	  // Get the values from the GUI
      try 
      {
   	     projectId = sourceDirectory.getProjectId();
	     idDir     = sourceDirectory.getIdDir();
	     nameDir   = sourceDirectory.getNameDir();
	     pathDir   = sourceDirectory.getPathDir();
	     
	     // SQL Statements
	     insertSql = "insert into source_directory (pc_id, sd_id, sd_name, sd_path) ";
		 insertSql = insertSql + "values(?, ?, ?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);
  
	     col = 1;
	     insertStatement.setInt(col++, projectId);
	     insertStatement.setInt(col++, idDir);
	     insertStatement.setString(col++, nameDir);
	     insertStatement.setString(col++, pathDir);
	     
	     insertStatement.executeUpdate();
	     
	     insertStatement.close();
      }
	  catch(Exception  e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Source Directory : " + nameDir + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "For the Project Id: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception (errorMessage2);
	  }
		
   }   
 
   public void deleteSourceDirectories(int projectId) throws Exception //5
   {
      String deleteSql="", errorMessage1="", errorMessage2="";	
   
	   // Delete all the existing Attributes
	   try 
	   {
          deleteSql = "delete from source_directory ";
          deleteSql = deleteSql + "where pc_id = ? and ";
          deleteSql = deleteSql + "sd_id >= 1";

	      PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	      deleteStatement.setInt(1, projectId);
	      deleteStatement.executeUpdate();
	      deleteStatement.close();
	   }
	   catch(Exception e1)
	   {
	      errorMessage1 = e1.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while deleting the existing Source Directories for the Project Id: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	   
   }  
   
   public ArrayList<String> loadGUIProject(int projectId, String programmingLanguage) throws Exception //6
   {
      String errorMessage1="", errorMessage2="", name="", sql1="";
      ArrayList<String> guiList = new ArrayList<String>();
      PreparedStatement selectStatement;
      ResultSet selectResults;
      
      try
      {
	     // SQL Statements      
	     sql1 = "select gui.gui_name ";
         sql1 = sql1 + "from graphical_user_interface gui, ";
         sql1 = sql1 + "project_configuration pc, ";
         sql1 = sql1 + "programming_language_header plh ";
	     sql1 = sql1 + "where ";
	     sql1 = sql1 + "gui.pc_id = ? and ";
	     sql1 = sql1 + "plh.plh_name = ? and ";
	     sql1 = sql1 + "gui.pc_id    = pc.pc_id";
	  
	     selectStatement = dbCon.prepareStatement(sql1);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, programmingLanguage);
	     selectResults = selectStatement.executeQuery();
		  
	     while(selectResults.next()) 
	     {
	        name = selectResults.getString("gui_name");
	        guiList.add(name);
	     }
				
	     selectResults.close();
	     selectStatement.close();
      }
      catch(Exception e1)
      {
 		 errorMessage1 = e1.getMessage();
 		 errorMessage2 = "Error XXXX: Occurred while loading the Graphical User Interfaces " + System.lineSeparator();
 		 errorMessage2 = errorMessage2 + "Programming Language: " + programmingLanguage + System.lineSeparator();
 		 errorMessage2 = errorMessage2 + "Project Identifier: " + projectId + System.lineSeparator();
 		 errorMessage2 = "Error Message: " + errorMessage1;
 		 throw new Exception(errorMessage2);
      }	
		  
	  return guiList;

   }   
   
}
