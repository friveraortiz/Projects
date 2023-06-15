package frl.model.inputMethods;

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

import frl.model.configuration.ClassAttribute;
import frl.model.configuration.ClassMethod;

public class DatabaseOperationsDatabase
{

   // Atributes for the ForensicReadyLoggerDatabase 
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
	   

   // Constructor of the Class
   public DatabaseOperationsDatabase()//1
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
	     errorMessage2 = "Error XXXX: Occurred while loading the Database Configuration File.\n";
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
			   errorMessage2 = "Error XXXX: Occurred while closing the Configuration File.\n";
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
		 errorMessage2 = "Error XXXX: Occurred because the Database Driver was not found.\n";
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
	      catch (SQLException e1) 
		  {
		     errorMessage1 = e1.getMessage();
			 errorMessage2 = "Error XXXX: Ocurred because the Database Connection cannot be closed..\n";
			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			 throw new Exception(errorMessage2);
		  }
	   }
		
	   dbCon = null;

	 }
	
   public ArrayList<DatabaseMethod1> loadDatabaseMethods(int projectId) throws Exception //5
   {
	  String sql="", name="", errorMessage1="", errorMessage2=""; 
	  int id=0;
	  ArrayList<DatabaseMethod1> databaseMethodsList = new ArrayList<DatabaseMethod1>();
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  DatabaseMethod1 databaseMethod;
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select dm.dm_id, dm.dm_name ";
	     sql = sql + "from database_method dm ";
	     sql = sql + "where dm.pc_id = ? ";
	     sql = sql + "order by dm.dm_name asc";
		  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		  	id   = selectResults.getInt("dm_id"); 
		    name = selectResults.getString("dm_name");

		    databaseMethod = new DatabaseMethod1(id, name);
		 
	        // Add fields to the ArrayList.
		    databaseMethodsList.add(databaseMethod); 
							
         }
			
	     selectResults.close();
	     selectStatement.close();
	     
	  
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Database Methods from the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return databaseMethodsList;

   }
   
   public ArrayList<String> loadDatabaseOperations(int projectId,
		                                           String programmingLanguage, 
		                                           String dBMS, 
		                                           String databaseMethod) throws Exception //6 
   {
	  String sql="", name="", errorMessage1="", errorMessage2="";
	  ArrayList<String> databaseClassList = new ArrayList<String>();
	  PreparedStatement selectStatement;
	  ResultSet selectResults;	  
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select dc.dc_name ";
	     sql = sql + "from database_operation db, ";
	     sql = sql + "programming_language_header plh, ";
	     sql = sql + "dbms ds, ";
	     sql = sql + "database_class dc, ";
	     sql = sql + "database_method dm ";
	     sql = sql + "where ";
	     sql = sql + "db.pc_id = ? and ";
	     sql = sql + "dc.pc_id = db.pc_id and ";
	     sql = sql + "dm.pc_id = db.pc_id and ";
	     sql = sql + "plh.plh_name = ? and ";
	     sql = sql + "ds.dbms_name = ? and ";
	     sql = sql + "dm.dm_name   = ? and ";
	     sql = sql + "db.pl_id = plh.plh_id and ";
	     sql = sql + "db.dbms_id = ds.dbms_id and ";
	     sql = sql + "db.dc_id = dc.dc_id and ";
	     sql = sql + "db.dm_id = dm.dm_id ";
	     sql = sql + "order by dm.dm_name";  
	  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, programmingLanguage);
	     selectStatement.setString(3, dBMS);
	     selectStatement.setString(4, databaseMethod);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		    name = selectResults.getString("dc_name"); 
		    
	        // Add fields to the ArrayList.
		    databaseClassList.add(name); 					
         }
			
	     selectResults.close();
	     selectStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Database Operations." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Programming Language: " + programmingLanguage + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Database: " + dBMS + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "and Database Method: " + databaseMethod + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return databaseClassList;

   }
   
   public ArrayList<ClassMethod> loadJavaFiles(int projectId) throws Exception //5
   {
	  String sql="", packageName="", className="", shortMethodName="", fullMethodName="",
			 parameterMethodName="", textFileName="", returnType1="", returnType2="",
			 dbClassType="", errorMessage1="", errorMessage2=""; 
	  int id=0, dbClassFlag=0,ciId=0, guiClassFlag=0, interfaceFlag=0;
	  ArrayList<ClassMethod> javaFileList = new ArrayList<ClassMethod>();
	  ClassMethod javaFile;
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select cm.cm_id id, ";
	     sql = sql + "cm.cm_package_name packageName, ";
	     sql = sql + "cm.cm_class_name className, ";
	     sql = sql + "cm.cm_short_method_name shortMethodName, ";
	     sql = sql + "cm.cm_full_method_name fullMethodName, ";
	     sql = sql + "cm.cm_parameter_method_name parameterMethodName, ";
	     sql = sql + "cm.cm_return_type_1 returnType1, ";
	     sql = sql + "cm.cm_return_type_2 returnType2, ";
	     sql = sql + "cm.cm_text_filename textFileName, ";
	     sql = sql + "ci.ci_database_class dbClassFlag, ";
	     sql = sql + "ci.ci_database_class_type dbClassType, ";
	     sql = sql + "cm.ci_id ciId, ";
	     sql = sql + "ci.ci_gui_class guiClassFlag, ";
	     sql = sql + "ci.ci_interface interfaceFlag ";
	     sql = sql + "from class_method cm, ";
	     sql = sql + "class_information ci ";
	     sql = sql + "where cm.pc_id = ? and ";
	     sql = sql + "cm.pc_id = ci.pc_id and ";
	     sql = sql + "cm.ci_id  = ci.ci_id ";
	     sql = sql + "order by cm.cm_package_name, cm.cm_class_name, cm.cm_short_method_name";
		  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		  	id                  = selectResults.getInt("id"); 
		    packageName         = selectResults.getString("packageName");
		    className           = selectResults.getString("className");
		    shortMethodName     = selectResults.getString("shortMethodName");
		    fullMethodName      = selectResults.getString("fullMethodName");
		    parameterMethodName = selectResults.getString("parameterMethodName");
		    returnType1         = selectResults.getString("returnType1");
		    returnType2         = selectResults.getString("returnType2");
		    textFileName        = selectResults.getString("textFileName");
		    dbClassFlag         = selectResults.getInt("dbClassFlag"); 
		    dbClassType         = selectResults.getString("dbClassType");
		    ciId                = selectResults.getInt("ciId");
		    guiClassFlag        = selectResults.getInt("guiClassFlag"); 
		    interfaceFlag       = selectResults.getInt("interfaceFlag");

		    javaFile = new ClassMethod(projectId, id, packageName, className,
			                           shortMethodName, fullMethodName, parameterMethodName,
			                           returnType1, returnType2, textFileName,
			                           ciId, dbClassFlag, dbClassType, guiClassFlag, interfaceFlag);
		 
	        // Add fields to the ArrayList.
		    javaFileList.add(javaFile); 
							
         }
			
	     selectResults.close();
	     selectStatement.close();
	     
	  
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Java File Details for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Identifier: " + id + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return javaFileList;

   }   
   
   public ArrayList<ClassAttribute> loadClassAttributes(int projectId, int classInfoId) throws Exception //5
   {
	  String sql="", errorMessage1="", errorMessage2="", packageName ="",
			 className="", shortName="", dataType=""; 
	  int id=0;
	  ClassAttribute classAttributeRecord;
	  ArrayList<ClassAttribute> classAttributeList = new ArrayList<ClassAttribute>();
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select ";
	     sql = sql + "ca.ca_id id, ";
	     sql = sql + "ca.ca_package_name packageName, ";
	     sql = sql + "ca.ca_class_name className, ";
	     sql = sql + "ca.ca_short_name shortName, ";
	     sql = sql + "ca.ca_data_type dataType ";
	     sql = sql + "from class_attribute ca ";
	     sql = sql + "where ca.pc_id = ? and ";
	     sql = sql + "ca.ci_id = ?";
		  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, classInfoId);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
	        id          = selectResults.getInt("id");
	        packageName = selectResults.getString("packageName");
	        className   = selectResults.getString("className");
	        shortName   = selectResults.getString("shortName");
	        dataType    = selectResults.getString("dataType");

		    classAttributeRecord = new ClassAttribute(projectId, id, shortName, packageName, className, dataType);
		 
	        // Add fields to the ArrayList.
		    classAttributeList.add(classAttributeRecord); 
							
         }
			
	     selectResults.close();
	     selectStatement.close();
	     
	  
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Attributes for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Information Id: " + classInfoId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: "+ System.lineSeparator() + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return classAttributeList;

   }  

   public String loadDatabaseOperation(int projectId,
		                               String programmingLanguage, 
		                               String dBMS, 
		                               String databaseMethod) throws Exception //6 
   {
	  String sql="", databaseClass="", errorMessage1="", errorMessage2="";
	  PreparedStatement selectStatement;
	  ResultSet selectResults;	  
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select dc.dc_name ";
	     sql = sql + "from database_operation db, ";
	     sql = sql + "programming_language_header plh, ";
	     sql = sql + "dbms ds, ";
	     sql = sql + "database_class dc, ";
	     sql = sql + "database_method dm ";
	     sql = sql + "where ";
	     sql = sql + "db.pc_id = ? and ";
	     sql = sql + "dc.pc_id = db.pc_id and ";
	     sql = sql + "dm.pc_id = db.pc_id and ";
	     sql = sql + "plh.plh_name = ? and ";
	     sql = sql + "ds.dbms_name = ? and ";
	     sql = sql + "dm.dm_name   = ? and ";
	     sql = sql + "db.pl_id = plh.plh_id and ";
	     sql = sql + "db.dbms_id = ds.dbms_id and ";
	     sql = sql + "db.dc_id = dc.dc_id and ";
	     sql = sql + "db.dm_id = dm.dm_id ";
	     sql = sql + "order by dm.dm_name";  
	  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, programmingLanguage);
	     selectStatement.setString(3, dBMS);
	     selectStatement.setString(4, databaseMethod);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		    databaseClass = selectResults.getString("dc_name"); 			
         }
	     
	     selectResults.close();
	     selectStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Database Operation" + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Programming Language: " + programmingLanguage + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Database: " + dBMS + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Database Method: " + databaseMethod + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return databaseClass;

   }
   
   public boolean validateDBClassName(int projectId, String className) throws Exception //5
   {
	  String sql="", errorMessage1="", errorMessage2=""; 
      boolean dbFlag=false;
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select ci.ci_database_class dBClassFlag ";
	     sql = sql + "from class_information ci ";
	     sql = sql + "where ci.pc_id = ? and ";
	     sql = sql + "lower(ci.ci_class_name) = ? and ";
	     sql = sql + "ci.ci_database_class = 1";
		  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, className);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
	        dbFlag   = selectResults.getBoolean("dBClassFlag");
         }
			
	     selectResults.close();
	     selectStatement.close();
	  
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while validating if it performs Data Operations the Class: " + className + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return dbFlag;

   }       
   
   public boolean validateDBClassNamePackage(int projectId, String packageName, String className) throws Exception //5
   {
	  String sql="", errorMessage1="", errorMessage2=""; 
      boolean dbFlag=false;
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select ci.ci_database_class dBClassFlag ";
	     sql = sql + "from class_information ci ";
	     sql = sql + "where ci.pc_id = ? and ";
	     sql = sql + "lower(ci.ci_package_name) = ? and ";
	     sql = sql + "lower(ci.ci_class_name) = ? and ";
	     sql = sql + "ci.ci_database_class = 1";
		  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, packageName);
	     selectStatement.setString(3, className);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
	        dbFlag   = selectResults.getBoolean("dBClassFlag");
         }
			
	     selectResults.close();
	     selectStatement.close();
	  
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while validating if it performs Data Operations the Package: " + packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return dbFlag;

   }   
   
   public void updateMethodDBOperations(int projectId, int methodId, int dbCase) throws Exception //8
   {
      String updateSql="", errorMessage1="", errorMessage2="";
	  PreparedStatement updateStatement;
	  int col=0;
	  
	  try
	  {
	     updateSql = "update class_method ";
		 updateSql = updateSql + "set cm_database_method = 1, ";
		 updateSql = updateSql + "dc_id = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "cm_id = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	  
	     
	     col = 1;

	     updateStatement.setInt(col++, dbCase);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, methodId);
	     
	     updateStatement.executeUpdate();
	     updateStatement.close();
	  }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Method Identifier: " + methodId + " that performs DATA Operations " + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }
	  
   }  
   
   public boolean getDBClassFlag(int projectId) throws Exception //5
   {
	  String sql="", errorMessage1="", errorMessage2=""; 
	  int recs=0;
      boolean dbFlag=false;
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select count(ci.ci_database_class) records ";
	     sql = sql + "from class_information ci ";
	     sql = sql + "where ci.pc_id = ? and ";
	     sql = sql + "ci.ci_database_class = 1";
		  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
	        recs = selectResults.getInt("records");
         }
			
	     selectResults.close();
	     selectStatement.close();
	  
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while validating if the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "has one or more Classes that performs data opeations"+ System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
	  if(recs > 0)
	     dbFlag = true;
	  else
		  dbFlag= false;
	  
      return dbFlag;

   }     
        
}