package frl.model;

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
	     System.out.println("Error 5221: Occurred while loading the Configuration File. Error Message: " + errorMessage);
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
			   System.out.println("Error 5222: Occurred while closing the Configuration File. Error Message: " + errorMessage);
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
	     throw new Exception("Error 5231: Occurred because the Database Driver was not found. Error Message: "+errorMessage);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
	  //System.out.println("Forensic Ready Logger Database Connected");
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
		     System.out.println("Error 5241: Occurred because the Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("Forensic Ready Logger Database Disconnected");
	 }

	public void deleteClassMethod() throws SQLException //5
	{
	   // Delete all the existing Java DB Operations	
       String deleteSql = "delete from class_method";
	   PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	   deleteStatement.executeUpdate();
	   deleteStatement.close();
	}
	
	public void saveClassMethod(ClassMethod classMethod) throws SQLException //6
	{
		
	   // Get the values from the GUI
	   int id                 = classMethod.getId();
	   String packageName     = classMethod.getPackageName();
	   String className       = classMethod.getClassName();
	   String fullMethodName  = classMethod.getFullMethodName();
	   String shortMethodName = classMethod.getShortMethodName();
	   String returnType1     = classMethod.getReturnType1();
	   String returnType2     = classMethod.getReturnType2();
	   String fileName        = classMethod.getFileName();	
	   int startLineNumber    = classMethod.getStartLineNumber();

	   // SQL Statements
	   String insertSql = "insert into class_method (cm_id, cm_package_name, cm_class_name, ";
			  insertSql = insertSql + "cm_full_method_name, cm_short_method_name, cm_return_type_1, ";
			  insertSql = insertSql + "cm_return_type_2, cm_filename, cm_start_line_number) ";
			  insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	   PreparedStatement insertStatement = dbCon.prepareStatement(insertSql);
   
       //System.out.println("Inserting a Class Method with ID: "+id);
          
	   int col = 1;
	   insertStatement.setInt(col++, id);
	   insertStatement.setString(col++, packageName);
	   insertStatement.setString(col++, className);
	   insertStatement.setString(col++, fullMethodName);
	   insertStatement.setString(col++, shortMethodName);
	   insertStatement.setString(col++, returnType1);
	   insertStatement.setString(col++, returnType2);
	   insertStatement.setString(col++, fileName);
	   insertStatement.setInt(col++, startLineNumber);
		   
	   insertStatement.executeUpdate();
		
	   insertStatement.close();
		
	} 

	
   public ArrayList<ClassMethod> load(String inputClassName) throws SQLException //7
   {

	  int id, startLineNumber, endLineNumber ; 
	  String packageName, className, fullMethodName, shortMethodName, returnType1;
	  String returnType2, fileName, sql1, className1;
	     
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
	  ClassMethod classMethod;

	  // Load Existing ClassMethodDetails
	  className1 = inputClassName;

	  // SQL Statements      
	  sql1 = "select cm_id, cm_package_name, cm_class_name, cm_short_method_name, ";
	  sql1 = sql1 + "cm_full_method_name, cm_return_type_1, cm_return_type_2, cm_filename, ";
	  sql1 = sql1 + "cm_start_line_number, cm_end_line_number ";
	  sql1 = sql1 + "from class_method ";
	  sql1 = sql1 + "where cm_class_name = ? ";
	  sql1 = sql1 + "order by cm_class_name, cm_start_line_number asc";
		  
	  selectStatement = dbCon.prepareStatement(sql1);
	  selectStatement.setString(1, className1);
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
		 
		 classMethod = new ClassMethod(id, packageName, className, fullMethodName, 
				                       shortMethodName, returnType1, returnType2, fileName,  
                                       startLineNumber, endLineNumber);
		 
	     // Add fields to the ArrayList.
		 classMethodList.add(classMethod); 
							
      }
			
	  selectResults.close();
	  selectStatement.close();
	  
      return classMethodList;

   }
   
   public void updateEndLineNumbers(int classMethodId, int endLineNumber) throws SQLException //8
   {
      String updateSql = "update class_method set ";
      		 updateSql = updateSql+ "cm_end_line_number=? where cm_id=?";
	  PreparedStatement updateStatement = dbCon.prepareStatement(updateSql);
	  
	  int col = 1;
		
	  //System.out.println("Updating an Class Methods Details with ID: "+classMethodDetId);
			
	  updateStatement.setInt(col++, endLineNumber);
	  updateStatement.setInt(col++, classMethodId);
	  
	  updateStatement.executeUpdate();
	  updateStatement.close();
   }

   public ArrayList<ClassMethod> loadZeroEndLineNumbers() throws SQLException //9
   {
      int id, startLineNumber, endLineNumber;
	  String packageName, className, shortMethodName, fullMethodName, returnType1;
	  String returnType2, fileName, sql1;

	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
	  ClassMethod classMethodDetails;

	  // SQL Statements      
	  sql1 = "select cm_id, cm_package_name, cm_class_name, ";
	  sql1 = sql1 + "cm_short_method_name, cm_full_method_name, ";
	  sql1 = sql1 + "cm_return_type_1, cm_return_type_2, cm_filename, ";
	  sql1 = sql1 + "cm_start_line_number, cm_end_line_number ";
	  sql1 = sql1 + "from class_method ";
	  sql1 = sql1 + "where cm_end_line_number = 0 ";
	  sql1 = sql1 + "order by cm_id asc";
	  
	  PreparedStatement selectStatement = dbCon.prepareStatement(sql1);
	  ResultSet selectResults = selectStatement.executeQuery();
	  
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
		 
		 classMethodDetails = new ClassMethod(id, packageName, className, 
                                              fullMethodName, shortMethodName, returnType1, 
                                              returnType2, fileName, startLineNumber,endLineNumber);
	     
	     // Add fields to the ArrayList.
		 classMethodList.add(classMethodDetails); 
							
      }
			
	  selectResults.close();
	  selectStatement.close();
	  
      return classMethodList;

   }  
   
   public void updateTextFileName(int classMethodId, String textFileName) throws SQLException //10
   {
      String updateSql = "update class_method set ";
      		 updateSql = updateSql+ "cm_text_filename=? where cm_id=?";
	  PreparedStatement updateStatement = dbCon.prepareStatement(updateSql);
	  
	  int col = 1;
		
	  //System.out.println("Updating an Class Methods  with ID: "+classMethodDetId);
			
	  updateStatement.setString(col++, textFileName);
	  updateStatement.setInt(col++, classMethodId);
	  
	  updateStatement.executeUpdate();
	  updateStatement.close();
   }
   
   public ClassMethod loadShortMethodName1(String packageName1, String className1, String shortMethodName1) throws SQLException //11
   {

	  String sql1 = "", shortMethodName2 = "", returnType2 = "";
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ClassMethod cm;

	  // SQL Statements      
	  sql1 = "select cm.cm_short_method_name, cm.cm_return_type_1 ";
	  sql1 = sql1 + "from class_method cm ";
	  sql1 = sql1 + "where cm.cm_package_name = ? and ";
	  sql1 = sql1 + "cm.cm_class_name = ? and ";
	  sql1 = sql1 + "cm.cm_short_method_name = ?";
	  
	  selectStatement = dbCon.prepareStatement(sql1);
	  selectStatement.setString(1, packageName1);
	  selectStatement.setString(2, className1);
	  selectStatement.setString(3, shortMethodName1);
	  selectResults = selectStatement.executeQuery();
	  
	  while(selectResults.next()) 
	  {
	     shortMethodName2 = selectResults.getString("cm_short_method_name");
	     returnType2      = selectResults.getString("cm_return_type_1");	
      }
	  
	  cm = new ClassMethod(shortMethodName2, returnType2);
			
	  selectResults.close();
	  selectStatement.close();
	  
      return cm;

   }
   
   public ClassMethod loadShortMethodName2(String packageName1, String className1, String textFileName1) throws SQLException //11
   {
	  String sql1 = "", shortMethodName2 = "", returnType2 = "";
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ClassMethod cm;

	  // SQL Statements      
	  sql1 = "select cm.cm_short_method_name, cm.cm_return_type_1 ";
	  sql1 = sql1 + "from class_method cm ";
	  sql1 = sql1 + "where cm.cm_package_name = ? and ";
	  sql1 = sql1 + "cm.cm_class_name = ? and ";
	  sql1 = sql1 + "cm.cm_text_filename = ?";
	  
	  selectStatement = dbCon.prepareStatement(sql1);
	  selectStatement.setString(1, packageName1);
	  selectStatement.setString(2, className1);
	  selectStatement.setString(3, textFileName1);
	  selectResults = selectStatement.executeQuery();
	  
	  while(selectResults.next()) 
	  {
	     shortMethodName2 = selectResults.getString("cm_short_method_name");
	     returnType2      = selectResults.getString("cm_return_type_1");
      }
	  
	  cm = new ClassMethod(shortMethodName2, returnType2);
			
	  selectResults.close();
	  selectStatement.close();
	  
      return cm;

   }
	
}
