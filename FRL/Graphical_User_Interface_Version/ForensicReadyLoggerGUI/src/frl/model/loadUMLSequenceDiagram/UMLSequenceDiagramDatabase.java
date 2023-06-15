package frl.model.loadUMLSequenceDiagram;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import frl.model.configuration.ClassAttribute;
import frl.model.configuration.ClassMethod;
import frl.model.generateAspectOrientedFiles.ConfigFile;

public class UMLSequenceDiagramDatabase
{
   // Atributes for the ForensicReadyLoggerDatabase 
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   private List<ConfigFile> configFiles;
	   
   // Constructor of the Class
   public UMLSequenceDiagramDatabase() //1
   {          
      configFiles  = new LinkedList<ConfigFile>();
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
			   errorMessage2 = "Error XXXX: Occurred while closing the Database Configuration File." + System.lineSeparator();
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

	public void deleteUMLSequenceDiagramPlain(int projectId) throws Exception //5
	{
       String deleteSql, errorMessage1="", errorMessage2="";	
       
	   // Delete all the existing UML Sequence Diagram Plain Data
	   try 
	   {
          deleteSql = "delete from uml_sequence_diagram_plain ";
          deleteSql = deleteSql + "where pc_id = ? ";

	      PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	      deleteStatement.setInt(1, projectId);
	      deleteStatement.executeUpdate();
	      deleteStatement.close();
	   }
	   catch(Exception e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while deleting the existing UML Sequence Diagram" + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	   
	}
	
	public void saveUMLSequenceDiagramPlain(UMLSequenceDiagramPlain umlSequenceDiagramPlain) throws SQLException //6
	{
	   int col, projectId = 0, lineNumber;
	   String insertSql, errorMessage1="", errorMessage2="";
       String lineText, lineType2, datumCategory2="", datumValue, datumCategory1Str="", lineType1Str="";
       LineType1 lineType1;
       DatumCategory1 datumCategory1;

	   PreparedStatement insertStatement = null;
		
	   // Get the values from the GUI
       try 
       {
  	      projectId      = umlSequenceDiagramPlain.getProjectId();
  		  lineNumber     = umlSequenceDiagramPlain.getLineNumber();
  		  lineText       = umlSequenceDiagramPlain.getLineText();
  		  lineType1      = umlSequenceDiagramPlain.getLineType1();
  		  lineType2      = umlSequenceDiagramPlain.getLineType2();
          datumCategory1 = umlSequenceDiagramPlain.getDatumCategory1();
          datumCategory2 = umlSequenceDiagramPlain.getDatumCategory2();
          datumValue     = umlSequenceDiagramPlain.getDatumValue();
  		  
  		  // Convert Enum to String
  		  lineType1Str      = lineType1.name();
  		  datumCategory1Str = datumCategory1.name();
  		  
    	  // SQL Statement
    	  insertSql = "insert into uml_sequence_diagram_plain (pc_id, usdp_line_number, usdp_line_text, "; 
    	  insertSql = insertSql + "usdp_line_type_1, usdp_line_type_2, usdp_datum_category_1, usdp_datum_category_2, ";
    	  insertSql = insertSql + "usdp_datum_value) ";
    	  insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?)";
    	  insertStatement = dbCon.prepareStatement(insertSql);
       
    	  col = 1;
    	  insertStatement.setInt(col++, projectId);
    	  insertStatement.setInt(col++, lineNumber);
    	  insertStatement.setString(col++, lineText);
    	  insertStatement.setString(col++, lineType1Str);
    	  insertStatement.setString(col++, lineType2);
    	  insertStatement.setString(col++, datumCategory1Str);
    	  insertStatement.setString(col++, datumCategory2);
    	  insertStatement.setString(col++, datumValue);

	      insertStatement.executeUpdate();
	      insertStatement.close();
       }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while inserting a new UML Sequence Diagram for the Project Id: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }
		
	} 
	
   public String getUserNamePlain(int projectId, int lineNumber) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", userName="";
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
      // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
		 selectSql = "select usdp.usdp_datum_value userName ";
		 selectSql = selectSql + "from uml_sequence_diagram_plain usdp ";
		 selectSql = selectSql + "where usdp.pc_id = ? and ";
		 selectSql = selectSql + "usdp_line_number = ? ";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, lineNumber);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        userName = selectResults.getString("userName");
		 }
		 
	    }	
	    catch(Exception e)
	    {
	       userName="";	
	       errorMessage1 = e.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while loading the User Name Plain for the Project Identifier: "+projectId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	       throw new Exception(errorMessage2);
	    }	

      return userName;	    
	   
   }
   
   public void updateUserRecordsPlain(int projectId, int lineNumber, 
		                              String userName, int userId) throws Exception
   {
      String updateSql, errorMessage1="", errorMessage2="";
	  int col;
	  PreparedStatement updateStatement;
		  
	  try
	  {
	     updateSql = "update uml_sequence_diagram_plain ";
		 updateSql = updateSql + "set usdp_user_name = ?, ";
		 updateSql = updateSql + "usdp_user_id=? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usdp_line_number = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
				
		 updateStatement.setString(col++, userName);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, lineNumber);
			  
		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the UserName: " + userName + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "in the line Number: " +lineNumber + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "for the Project Identifier: "+ projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }  

   }
   
   public void updateConnectionRecordsPlain(int projectId, int lineNumber, String userName) throws Exception
   {
      String updateSql, errorMessage1="", errorMessage2="";
	  int col;
	  PreparedStatement updateStatement;
     
	  // Update the UserName  
      try
      {
         updateSql = "update uml_sequence_diagram_plain ";
	     updateSql = updateSql + "set usdp_datum_category_2=? ";
	     updateSql = updateSql + "where pc_id=? and ";
	     updateSql = updateSql + "usdp_line_number=?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
	     col = 1;
		
	     updateStatement.setString(col++, userName);
	     updateStatement.setInt(col++, projectId);
	     updateStatement.setInt(col++, lineNumber);
	  
	     updateStatement.executeUpdate();
	     updateStatement.close();
      }
      catch(SQLException e)
      {
         errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while updating the Datum Category 2: " + userName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "for the Project Identifier: "+ projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new SQLException(errorMessage2);
      } 
  
   }

   public ArrayList<UMLSequenceDiagramPlain> loadUserRecordsPlain(int projectId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      String lineType2="", datumCategory2="", datumValue = "";
	  int lineNumber=0;
	  DatumCategory1 datumCategory1=DatumCategory1.None; 
      LineType1 lineType1=LineType1.None, 
 		        value1=LineType1.ConnectionStarts,
 		        value2=LineType1.MethodStarts, 
 		        value3=LineType1.MethodBody,
 		        value4=LineType1.MethodEnds;
      PreparedStatement selectStatement;
      ResultSet selectResults;
      UMLSequenceDiagramPlain umlSeqDiag;
      ArrayList<UMLSequenceDiagramPlain> umlSeqDiagList = new ArrayList<UMLSequenceDiagramPlain>();
      
      // Obtain all records that belongs to this project Id
      // and contains this linetype1 values
	  try
	  {
	     // SQL Statements      
		 selectSql = "select usdp.usdp_line_number lineNumber, usdp.usdp_line_type_1 lineType1, ";
		 selectSql = selectSql + "usdp.usdp_line_type_2 lineType2, usdp.usdp_datum_category_1 datumCategory1, ";
		 selectSql = selectSql + "usdp.usdp_datum_category_2 datumCategory2, usdp.usdp_datum_value datumValue ";
		 selectSql = selectSql + "from uml_sequence_diagram_plain usdp ";
		 selectSql = selectSql + "where usdp.pc_id = ? and ";
		 selectSql = selectSql + "usdp.usdp_line_type_1 in (?,?,?,?) ";
		 selectSql = selectSql + "order by usdp.usdp_line_number asc";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);

		 // Convert the values from Enum to String
		 selectStatement.setString(2, value1.name());
		 selectStatement.setString(3, value2.name());
		 selectStatement.setString(4, value3.name());
		 selectStatement.setString(5, value4.name());
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        lineNumber     = selectResults.getInt("lineNumber");
	        // Convert String to Enum
	        lineType1      = LineType1.valueOf(selectResults.getString("lineType1"));
	        lineType2      = selectResults.getString("lineType2");
	        datumCategory1 = DatumCategory1.valueOf(selectResults.getString("datumCategory1"));
	        datumCategory2 = selectResults.getString("datumCategory2");
	        datumValue     = selectResults.getString("datumValue");

	        umlSeqDiag = new UMLSequenceDiagramPlain(projectId, lineNumber,  lineType1, 
	        		                                 lineType2, datumCategory1, datumCategory2, 
	        		                                 datumValue);
	        umlSeqDiagList.add(umlSeqDiag);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
	  return umlSeqDiagList;
   }   

   public void updateMethodDetailsPlain(int projectId, UMLSequenceDiagramPlain umlSeqDiagramPlain) throws Exception
   {
      String updateSql, errorMessage1="", errorMessage2="", methodName1="", 
    		 methodName2="", methodOwner="", methodPackageName="", methodClassName="",
    		 methodName3="";
	  int col, lineNumber1=0, lineNumber2=0, cmId=0;
	  PreparedStatement updateStatement;
	  
      // Get the data from the GUI
	  methodName1       = umlSeqDiagramPlain.getMethodName1();
	  methodName2       = umlSeqDiagramPlain.getMethodName2();
	  methodOwner       = umlSeqDiagramPlain.getMethodOwner();
	  lineNumber1       = umlSeqDiagramPlain.getLineNumber1();
	  lineNumber2       = umlSeqDiagramPlain.getLineNumber2();
	  cmId              = umlSeqDiagramPlain.getClassMethodIdentifier();
	  methodPackageName = umlSeqDiagramPlain.getMethodPackageName();
	  methodClassName   = umlSeqDiagramPlain.getMethodClassName();
	  methodName3       = umlSeqDiagramPlain.getMethodName3();
	  
	  try
	  {
	     updateSql = "update uml_sequence_diagram_plain ";
		 updateSql = updateSql + "set usdp_method_name_1 = ?, ";
		 updateSql = updateSql + "usdp_method_name_2 = ?, ";
		 updateSql = updateSql + "usdp_method_owner = ?, ";
		 updateSql = updateSql + "cm_id = ?, ";
		 updateSql = updateSql + "usdp_method_package_name = ?, ";
		 updateSql = updateSql + "usdp_method_class_name = ?, ";
		 updateSql = updateSql + "usdp_method_name_3 = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usdp_line_number between ? and ?";
	     updateStatement = dbCon.prepareStatement(updateSql);
	     
		 col = 1;
				
		 updateStatement.setString(col++, methodName1);
		 updateStatement.setString(col++, methodName2);
		 updateStatement.setString(col++, methodOwner);
		 updateStatement.setInt(col++, cmId);
		 updateStatement.setString(col++, methodPackageName);
		 updateStatement.setString(col++, methodClassName);
		 updateStatement.setString(col++, methodName3);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, lineNumber1);
		 updateStatement.setInt(col++, lineNumber2);
			  
		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Method Name 1: " + methodName1 + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "between the line Number: " + lineNumber1 +" and the line Number:" + lineNumber2 + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "for the Project Identifier: "+ projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }  

   }
   
   public ArrayList<UserSequenceDiagram> loadUsersSequenceDiagram(int projectId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", userName="";
      ArrayList<UserSequenceDiagram> userList = new ArrayList<UserSequenceDiagram>();
      UserSequenceDiagram userRecord;
      int userId=0;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain all records from the UserName 
      // to obtain the corresponding UML Sequence Diagram
	  try
	  {
	     // SQL Statements      
		 selectSql = "select usd.usd_id userId, usd.usd_name userName ";
		 selectSql = selectSql + "from user_sequence_diagram usd ";
		 selectSql = selectSql + "where usd.pc_id = ? ";
		 selectSql = selectSql + "order by usd.usd_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			userId   = selectResults.getInt("userId"); 
	        userName = selectResults.getString("userName");
	        
	        // Build a new record
	        userRecord = new UserSequenceDiagram(projectId, userId, userName);
	        
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
      
   public ArrayList<UMLSequenceDiagramFinal> loadSequenceDiagramUser(int projectId, int userId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      String lineText="";
	  int lineNumber=0;
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

   public ArrayList<MethodStepSequenceDiagram> loadMethodsSequenceDiagram(int projectId, int userId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", methodName="";
      int methodId=0, stepId=0, orderId=0;
	  ArrayList<MethodStepSequenceDiagram> methodList = new ArrayList<MethodStepSequenceDiagram>();
	  MethodStepSequenceDiagram methodRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain the different methods that corresponds to the UserName 
	  try
	  {
	     // SQL Statements      
		 selectSql = "select mssd.msd_id methodId, mssd.mssd_step_id stepId, ";
		 selectSql = selectSql + "mssd.msdd_order_id orderId, mssd.mssd_name methodName ";
		 selectSql = selectSql + "from method_step_sequence_diagram mssd ";
		 selectSql = selectSql + "where mssd.pc_id = ? and ";
		 selectSql = selectSql + "usd_id = ? ";
		 selectSql = selectSql + "order by mssd.msdd_order_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, userId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        methodId   = selectResults.getInt("methodId");
	        stepId     = selectResults.getInt("stepId");
	        orderId    = selectResults.getInt("orderId");
	        methodName = selectResults.getString("methodName");
	        
	        methodRecord = new MethodStepSequenceDiagram(projectId, userId, methodId, stepId, 
	        		                                     orderId, methodName);
	        		                                 
	        methodList.add(methodRecord);
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
	  
      return methodList;

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
		 selectSql = selectSql + "where ";
		 selectSql = selectSql + "(usdf.pc_id = ? and ";
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
		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "User Identifier   : " + userId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier : " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Step Identifier   : " + stepId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
	  return umlSeqDiagList;

}   

   public ArrayList<UMLSequenceDiagramPlain> loadMethodRecordsPlain(int projectId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="",
             lineText="", lineType2="", datumCategory2="", datumValue = "", userName="";
	  int lineNumber=0, userId=0;
	  DatumCategory1 datumCategory1=DatumCategory1.None; 
      LineType1 lineType1=LineType1.None,value0=LineType1.ConnectionStarts, value1=LineType1.MethodStarts, 
  		        value2=LineType1.MethodEnds, value3=LineType1.MethodBody;
      
	  PreparedStatement selectStatement;
      ResultSet selectResults;
      UMLSequenceDiagramPlain umlSeqDiag;
      ArrayList<UMLSequenceDiagramPlain> umlSeqDiagList = new ArrayList<UMLSequenceDiagramPlain>();
      
      // Obtain all records from the UserName 
      // to obtain the corresponding UML Sequence Diagram
	  try
	  {
	     // SQL Statements      
		 selectSql = "select usdp.pc_id projectId, usdp.usdp_line_number lineNumber, usdp.usdp_line_text lineText, usdp.usdp_line_type_1 lineType1, ";
		 selectSql = selectSql + "usdp.usdp_line_type_2 lineType2, usdp.usdp_datum_category_1 datumCategory1, ";
		 selectSql = selectSql + "usdp.usdp_datum_category_2 datumCategory2, usdp.usdp_datum_value datumValue, ";
		 selectSql = selectSql + "usdp.usdp_user_name userName, ";
		 selectSql = selectSql + "usdp.usdp_user_id userId ";
		 selectSql = selectSql + "from uml_sequence_diagram_plain usdp ";
		 selectSql = selectSql + "where pc_id = ? and ";
		 selectSql = selectSql + "usdp_line_type_1 in(?, ?, ?, ?) ";
		 selectSql = selectSql + "order by usdp.usdp_line_number asc";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 // Convert the values from Enum to String
		 selectStatement.setString(2, value0.name());
		 selectStatement.setString(3, value1.name());
		 selectStatement.setString(4, value2.name());
		 selectStatement.setString(5, value3.name());
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        lineNumber     = selectResults.getInt("lineNumber");
	        lineText       = selectResults.getString("lineText");
	        // Convert String to Enum
	        lineType1      = LineType1.valueOf(selectResults.getString("lineType1"));
	        lineType2      = selectResults.getString("lineType2");
	        datumCategory1 = DatumCategory1.valueOf(selectResults.getString("datumCategory1"));
	        datumCategory2 = selectResults.getString("datumCategory2");
	        datumValue     = selectResults.getString("datumValue");
	        userName       = selectResults.getString("userName");
	        userId         = selectResults.getInt("userId");

	        umlSeqDiag = new UMLSequenceDiagramPlain(projectId, lineNumber, lineText, 
	        		                                 lineType1, lineType2, datumCategory1, 
	        		                                 datumCategory2, datumValue, userName, userId);
	        umlSeqDiagList.add(umlSeqDiag);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
	  return umlSeqDiagList;

   }   
   
   public ClassMethod getClassMethodDetails1(int projectId, String methodName, String packageName, String className) throws Exception
   {
      String errorMessage1="", errorMessage2="", selectSql="", textFileName="", paramMethodName="";
      int id=0;	

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      ClassMethod classMethod;
      
	  try
	  {
	     // SQL Statement     
		 selectSql = "select cm.cm_id id, ";
		 selectSql = selectSql + "cm.cm_text_filename textFileName, ";
		 selectSql = selectSql + "cm.cm_parameter_method_name paramMethodName ";
		 selectSql = selectSql + "from class_method cm ";
		 selectSql = selectSql + "where cm.pc_id = ? and ";
		 selectSql = selectSql + "cm.cm_short_method_name = ? and ";
		 selectSql = selectSql + "cm.cm_package_name = ? and ";
		 selectSql = selectSql + "cm.cm_class_name = ? ";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, methodName);
		 selectStatement.setString(3, packageName);
		 selectStatement.setString(4, className);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        id              = selectResults.getInt("id");
	        textFileName    = selectResults.getString("textFileName");
	        paramMethodName = selectResults.getString("paramMethodName");
		 }
		 
		 // Build a new record of the ClassMethod data type
		 classMethod = new ClassMethod(projectId, id, textFileName, paramMethodName);
		 
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Method Identifier for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Name:  " + methodName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Package Name: " + packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Name:   " + className + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return classMethod;

   }
    
   public void saveUsersSequenceDiagram(int projectId) throws Exception
   {
      String insertSql="", selectSql="", errorMessage1="", errorMessage2="";
      LineType1 lineType1=LineType1.ConnectionStarts;
      PreparedStatement insertStatement;
	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      int userId=0, col=0;
      String userName="";


      // Obtain all the UML Sequence Diagrams
      // stored in the UML_Sequence_Diagram_Plain
   	  try
   	  {
   	     // SQL Statements      
   		 selectSql = "select distinct usdp.usdp_user_name userName ";
   		 selectSql = selectSql + "from uml_sequence_diagram_plain usdp ";
   		 selectSql = selectSql + "where usdp.pc_id = ? and ";
   		 selectSql = selectSql + "usdp.usdp_line_type_1 = ? ";
   		 selectSql = selectSql + "order by usdp.usdp_user_name";


   		 selectStatement = dbCon.prepareStatement(selectSql);
   		 selectStatement.setInt(1, projectId);
   		 selectStatement.setString(2, lineType1.toString());

   		 selectResults = selectStatement.executeQuery();
   		  
   		 while(selectResults.next()) 
   		 {
   			userId++; 
   	        userName = selectResults.getString("userName"); 
   	        
   	      try 
   	      {
   	         insertSql       = "insert into user_sequence_diagram (pc_id, usd_id, usd_name) ";
   	         insertSql       = insertSql + "values (?, ?, ?)";
    	     insertStatement = dbCon.prepareStatement(insertSql);
       
    	     col = 1;
    	     insertStatement.setInt(col++, projectId);
    	     insertStatement.setInt(col++, userId);
       	     insertStatement.setString(col++, userName);

	         insertStatement.executeUpdate();
	         insertStatement.close();
   	      }
   		  catch(Exception e1)
   		  {
   		     errorMessage1 = e1.getMessage();
   			 errorMessage2 = "Error XXXX: Ocurred while saving the Users into the Final Table for the Project Identifier: " + projectId + System.lineSeparator();
   			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
   		     throw new Exception(errorMessage2);
   		  }

   		 }
   	  }	
   	  catch(Exception e2)
   	  {
   	     errorMessage1 = e2.getMessage();
   		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram Plain for the Project Identifier: " + projectId + System.lineSeparator();
   		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
   	     throw new Exception(errorMessage2);
   	  }

	   
   }
   
   
   public void saveMethodsSequenceDiagram(int projectId) throws Exception
   {
      String insertSql="", errorMessage1="", errorMessage2="";
      LineType1 lineType1=LineType1.MethodStarts;
      PreparedStatement insertStatement;
      
      try 
      {
         insertSql = "insert into method_sequence_diagram (pc_id, msd_id, msd_owner, ";
         insertSql = insertSql + "msd_package_name, msd_class_name, ";
         insertSql = insertSql + "msd_short_name, msd_full_name, cm_id) ";
         insertSql = insertSql + "select distinct usdp.pc_id, usdp.usdp_method_id, ";
         insertSql = insertSql + "usdp.usdp_method_owner, usdp.usdp_method_package_name, usdp.usdp_method_class_name, ";
         insertSql = insertSql + "usdp.usdp_method_name_2, usdp.usdp_method_name_3, usdp.cm_id ";
         insertSql = insertSql + "from uml_sequence_diagram_plain usdp ";
         insertSql = insertSql + "where usdp.pc_id = ? and ";
         insertSql = insertSql + "usdp.usdp_line_type_1 = ? ";
         insertSql = insertSql + "order by usdp.usdp_method_id";

	     insertStatement = dbCon.prepareStatement(insertSql);
	     insertStatement.setInt(1, projectId);
	     insertStatement.setString(2, lineType1.toString());
	  
	     insertStatement.executeUpdate();
      }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while saving the Methods for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	   
   }
   
   
   public void deleteUsersSequenceDiagram(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Users
	  try 
	  {
         deleteSql = "delete from user_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Users from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
	}
   
   public void deleteMethodsSequenceDiagram(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Methods
	  try 
	  {
         deleteSql = "delete from method_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Methods from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
	}
   
   public ArrayList<UMLSequenceDiagramPlain> loadSequenceDiagramsPlain(int projectId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      String lineText="", lineType2="", datumCategory2="", datumValue = "";
	  int userId=0, methodId=0, cmId=0, lineNumber=0, stepId=0;
	  DatumCategory1 datumCategory1=DatumCategory1.None; 
      LineType1 lineType1=LineType1.None;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      UMLSequenceDiagramPlain umlSeqDiag;
      ArrayList<UMLSequenceDiagramPlain> umlSeqDiagList = new ArrayList<UMLSequenceDiagramPlain>();
   
      // Obtain all the UML Sequence Diagrams
      // stored in the UML_Sequence_Diagram_Plain

	  try
	  {
	     // SQL Statements      
		 //selectSql = "select IFNULL(usdp.usdp_user_id,0) userId, IFNULL(usdp.usdp_method_id,0) methodId, ";
		 selectSql = "select distinct IFNULL(usdp.usdp_user_id,0) userId, IFNULL(usdp.usdp_method_id,0) methodId, ";
		 //selectSql = selectSql + "usdp.usdp_line_number lineNumber, usdp.usdp_line_text lineText, usdp.usdp_line_type_1 lineType1, ";
		 selectSql = selectSql + "usdp.usdp_line_text lineText, usdp.usdp_line_type_1 lineType1, ";
		 selectSql = selectSql + "usdp.usdp_line_text lineText, usdp.usdp_line_type_1 lineType1, ";
		 selectSql = selectSql + "usdp.usdp_line_type_2 lineType2, usdp.usdp_datum_category_1 datumCategory1, usdp.usdp_datum_category_2 datumCategory2, ";
		 selectSql = selectSql + "usdp.usdp_datum_value datumValue, usdp.cm_id cmId, IFNULL(usdp.mssd_step_id,0) stepId ";
		 selectSql = selectSql + "from uml_sequence_diagram_plain usdp ";
		 selectSql = selectSql + "where usdp.pc_id = ? ";
		 //selectSql = selectSql + "order by usdp.usdp_line_number asc";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);

		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			userId         = selectResults.getInt("userId"); 
			methodId       = selectResults.getInt("methodId"); 
			//lineNumber     = selectResults.getInt("lineNumber");
	        lineText       = selectResults.getString("lineText");
	        // Convert String to Enum
	        lineType1      = LineType1.valueOf(selectResults.getString("lineType1"));
	        lineType2      = selectResults.getString("lineType2");
	        datumCategory1 = DatumCategory1.valueOf(selectResults.getString("datumCategory1"));
	        datumCategory2 = selectResults.getString("datumCategory2");
	        datumValue     = selectResults.getString("datumValue");
	        cmId           = selectResults.getInt("cmId");
	        stepId         = selectResults.getInt("stepId");

	        umlSeqDiag = new UMLSequenceDiagramPlain(projectId, userId, methodId, lineNumber,
	        		                                 lineText, lineType1, lineType2, 
	        		                                 datumCategory1, datumCategory2, datumValue, 
	        		                                 cmId, stepId);
	        umlSeqDiagList.add(umlSeqDiag);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the UML Sequence Diagram Plain for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
	  return umlSeqDiagList;

   }   
	
   public void saveUMLSequenceDiagramsFinal(UMLSequenceDiagramFinal umlSequenceDiagramFinal) throws SQLException //6
   {
      int col, projectId = 0, userId=0, methodId=0, diagramLineNumber=0, 
    	  methodLineNumber=0, cmId=0, stepId=0;
	  String insertSql, errorMessage1="", errorMessage2="", lineText, lineType2, 
			 datumCategory2="", datumValue, datumCategory1Str="", lineType1Str="";
      LineType1 lineType1;
      DatumCategory1 datumCategory1;

	  PreparedStatement insertStatement = null;
		
	   // Get the values from the GUI
      try 
      {
 	     projectId         = umlSequenceDiagramFinal.getProjectId();
 	     userId            = umlSequenceDiagramFinal.getUserIdentifier();
 	     methodId          = umlSequenceDiagramFinal.getMethodIdentifier();
 	     diagramLineNumber = umlSequenceDiagramFinal.getDiagramLineNumber();
 		 methodLineNumber  = umlSequenceDiagramFinal.getMethodLineNumber();
 		 lineText          = umlSequenceDiagramFinal.getLineText();
 		 lineType1         = umlSequenceDiagramFinal.getLineType1();
 		 lineType2         = umlSequenceDiagramFinal.getLineType2();
         datumCategory1    = umlSequenceDiagramFinal.getDatumCategory1();
         datumCategory2    = umlSequenceDiagramFinal.getDatumCategory2();
         datumValue        = umlSequenceDiagramFinal.getDatumValue();
         cmId              = umlSequenceDiagramFinal.getClassMethodIdentifier();
         stepId            = umlSequenceDiagramFinal.getMethodStepIdentifier();
 		  
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
		 errorMessage2 = "Error XXXX: Occurred while inserting a new UML Sequence Diagram Final for the Project Id: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Diagram Line Number:" + diagramLineNumber + " ,User Id :" + userId + " ,Method Id: " + methodId +  " ,Method Step Id: " + stepId +" and Method Line Number:" + methodLineNumber + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
		
	} 
 
   public void deleteSequenceDiagramsFinal(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	

	  // Delete all the existing Methods
	  try 
	  {
         deleteSql = "delete from uml_sequence_diagram_final ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing UML Sequence Diagrams Final from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
	}   

   public ArrayList<MethodDataSequenceDiagram> getMethodSequenceDiagrams(int projectId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", methodShortName="", methodFullName="", 
    		 methodReturnTypeName="", methodReturnTypeId1="", methodReturnTypeId2="";
      int methodId=0, methodCmId=0, methodReturnTypeId=0;	
      ArrayList<MethodDataSequenceDiagram> methodSeqDiagramList = new ArrayList<MethodDataSequenceDiagram>();
      MethodDataSequenceDiagram methodSeqDiagramRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
	  try
	  {
	     // SQL Statement     
		 selectSql = "select msd.msd_id methodId, ";
		 selectSql = selectSql + "msd.msd_short_name methodShortName, ";
		 selectSql = selectSql + "msd.msd_full_name methodFullName, ";
		 selectSql = selectSql + "msd.cm_id methodCmId, ";
		 selectSql = selectSql + "msd.rt_id methodReturnTypeId, ";
		 selectSql = selectSql + "rt.rt_name methodReturnTypeName, ";
		 selectSql = selectSql + "cm.cm_return_type_1 methodReturnType1, ";
		 selectSql = selectSql + "cm.cm_return_type_2 methodReturnType2 ";
		 selectSql = selectSql + "from method_sequence_diagram msd, ";
		 selectSql = selectSql + "return_type rt, ";
		 selectSql = selectSql + "class_method cm ";
		 selectSql = selectSql + "where msd.pc_id = ? and ";
		 selectSql = selectSql + "msd.rt_id = rt.rt_id and ";
		 selectSql = selectSql + "cm.pc_id = msd.pc_id and ";
		 selectSql = selectSql + "msd.cm_id = cm.cm_id ";
	     selectSql = selectSql + "order by msd.pc_id, msd.msd_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			methodId             = selectResults.getInt("methodId");
			methodShortName      = selectResults.getString("methodShortName");
	        methodFullName       = selectResults.getString("methodFullName");
	        methodCmId           = selectResults.getInt("methodCmId");
	        methodReturnTypeId   = selectResults.getInt("methodReturnTypeId");
	        methodReturnTypeName = selectResults.getString("methodReturnTypeName");
	        methodReturnTypeId1  = selectResults.getString("methodReturnType1");
	        methodReturnTypeId2  = selectResults.getString("methodReturnType2");
	        
			methodSeqDiagramRecord = new MethodDataSequenceDiagram(projectId, methodId, methodShortName, methodFullName, methodCmId,
					                                               methodReturnTypeId, methodReturnTypeName,
					                                               methodReturnTypeId1, methodReturnTypeId2);
	        
			methodSeqDiagramList.add(methodSeqDiagramRecord);
	        
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
	  
      return methodSeqDiagramList;

   }
	
   public void saveParameterSequenceDiagram(ParameterSequenceDiagram parameterSeqDiagram) throws Exception //6
   {
      int col=0, projectId=0, methodId=0, parameterId=0, progLangId=0, 
    	  dataTypeId=0, cmId=0;
	  String insertSql="", errorMessage1="", errorMessage2="", parameterName="", 
			 dataTypeName="", packageName="", className="", returnType="";
	  PreparedStatement insertStatement = null;
	  
      try 
      {
    	 // Get the values from the GUI  
 	     projectId     = parameterSeqDiagram.getProjectIdentifier();
 	     methodId      = parameterSeqDiagram.getMethodIdentifier();
         parameterId   = parameterSeqDiagram.getParameterIdentifier();
         progLangId    = parameterSeqDiagram.getProgrammingLanguageIdentifier();
         parameterName = parameterSeqDiagram.getParameterName();
         
         dataTypeId   = parameterSeqDiagram.getDataTypeIdentifier();
         dataTypeName = parameterSeqDiagram.getDataTypeName();
         packageName  = parameterSeqDiagram.getPackageName();
         className    = parameterSeqDiagram.getClassName();
         returnType   = parameterSeqDiagram.getReturnType();
         cmId         = parameterSeqDiagram.getClassMethodIdentifier();
         
   	     // SQL Statement
   	     insertSql = "insert into parameter_sequence_diagram (pc_id, msd_id, "; 
   	     insertSql = insertSql + "psd_id, pl_id, psd_parameter_name, ";
   	     insertSql = insertSql + "dt_id, psd_data_type_name, psd_package_name, ";
   	     insertSql = insertSql + "psd_class_name, psd_return_type, cm_id ) ";
   	  
   	     insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
   	     insertStatement = dbCon.prepareStatement(insertSql);
      
   	     col = 1;
   	     insertStatement.setInt(col++, projectId);
   	     insertStatement.setInt(col++, methodId);
   	     insertStatement.setInt(col++, parameterId);
   	     insertStatement.setInt(col++, progLangId);
   	     insertStatement.setString(col++, parameterName);
   	     
   	     insertStatement.setInt(col++, dataTypeId);
   	     insertStatement.setString(col++, dataTypeName);
   	     insertStatement.setString(col++, packageName);
   	     insertStatement.setString(col++, className);
   	     insertStatement.setString(col++, returnType);   
   	     insertStatement.setInt(col++, cmId);
   	     
	     insertStatement.executeUpdate();
	     insertStatement.close();
      }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Parameter Sequence Diagram for the Project Id: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Id: " + methodId + ", Parameter Id: " + parameterId + ", and Parameter Name: " + parameterName +   System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
   } 
 
   public void deleteParametersSequenceDiagram(int projectId) throws Exception //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Methods
	  try 
	  {
         deleteSql = "delete from parameter_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Parameters Sequence Diagram from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	   
	}
	
   public DataType sele(String programmingLanguage, String name) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", dataCategoryStr="";
      int progId=0, dataId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  DataCategory dataCategory;
	  DataType dataType = null;
	  
	  // Convert the name to lowercase
	  name = name.toLowerCase();
	  
      // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
		 selectSql = "select plh.plh_id progId, dt.dt_id dataId, dt.dt_category dataCategory ";
		 selectSql = selectSql + "from data_type dt, ";
		 selectSql = selectSql + "programming_language_header plh ";
		 selectSql = selectSql + "where lower(dt.dt_name) = ? and ";
		 selectSql = selectSql + "plh.plh_name = ? and ";		 
		 selectSql = selectSql + "plh.plh_id = pl_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setString(1, name);
		 selectStatement.setString(2, programmingLanguage);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        progId = selectResults.getInt("progId");
	        dataId = selectResults.getInt("dataId");
	        dataCategoryStr = selectResults.getString("dataCategory");
	        
	        // Convert String to Enum
	        dataCategory = DataCategory.valueOf(dataCategoryStr);
	        
	        // Build the dataType record
	        dataType = new DataType(progId, name, dataId, dataCategory);
		 }
		 
	    }	
	    catch(Exception e)
	    {
	       errorMessage1 = e.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while getting the Data Type from the Programming Language: " + programmingLanguage + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Data Type Name: " + name + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	       throw new Exception(errorMessage2);
	    }	

      return dataType;	    
	   
   }
   
   public DataType getDataTypeDetails2(String programmingLanguage, String name, DataCategory dataCategory1) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", dataCategoryStr="";
      int progId=0, dataId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  DataCategory dataCategory2;
	  DataType dataType = null;
	  
      // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
		 selectSql = "select plh.plh_id progId, dt.dt_id dataId, dt.dt_category dataCategory ";
		 selectSql = selectSql + "from data_type dt, ";
		 selectSql = selectSql + "programming_language_header plh ";
		 selectSql = selectSql + "where dt.dt_name = ? and ";
		 selectSql = selectSql + "plh.plh_name = ? and ";	
		 selectSql = selectSql + "dt.dt_category = ? and ";		 
		 selectSql = selectSql + "plh.plh_id =  pl_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setString(1, name);
		 selectStatement.setString(2, programmingLanguage);
		 // Convert Enum to String
		 dataCategoryStr = dataCategory1.name();
		 selectStatement.setString(3, dataCategoryStr);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        progId = selectResults.getInt("progId");
	        dataId = selectResults.getInt("dataId");
	        dataCategoryStr = selectResults.getString("dataCategory");
	        
	        // Convert String to Enum
	        dataCategory2 = DataCategory.valueOf(dataCategoryStr);
	        
	        // Build the dataType record
	        dataType = new DataType(progId, name, dataId, dataCategory2);
		 }
		 
	    }	
	    catch(Exception e)
	    {
	       errorMessage1 = e.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while getting the Data Type Identifier 2 from the Programming Language: " + programmingLanguage + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "and Data Type Name: " + name + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	       throw new Exception(errorMessage2);
	    }	

      return dataType;	    
	   
   }
	
   public ClassMethod getClassDetails(int projectId, String className, 
		                              String value1, String value2) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", packageName="", returnType1="";
      
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  ClassMethod classMethod = null;
	  
	  /*
	  System.out.println("*** INSIDE the Get Class Details ***");
	  System.out.println("Project Id : " + projectId);
	  System.out.println("Class Name : " + className);
	  System.out.println("Value 1    : " + value1);
	  System.out.println("Value 2    : " + value2);
	  */
	  
      // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
		 selectSql = "select distinct cm.cm_package_name packageName, ";
		 selectSql = selectSql + "cm.cm_return_type_1 returnType1 ";
		 selectSql = selectSql + "from class_method cm ";
		 selectSql = selectSql + "where cm.pc_id = ? and ";
		 selectSql = selectSql + "cm.cm_class_name = ? and ";
		 selectSql = selectSql + "cm.cm_short_method_name = ? and ";
		 selectSql = selectSql + "cm.cm_return_type_1 in(? , ?)";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, className);
		 selectStatement.setString(3, className);
		 selectStatement.setString(4, value1);
		 selectStatement.setString(5, value2);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        packageName = selectResults.getString("packageName");
	        returnType1 = selectResults.getString("returnType1");
	        
	        classMethod = new ClassMethod(projectId, packageName, className, returnType1);
		 }
		 
	    }	
	    catch(Exception e)
	    {
	       errorMessage1 = e.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while getting the Package Name for the Project Identifier: " + projectId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "and Class Name: " + className + "and Method Name: " + className +System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	       throw new Exception(errorMessage2);
	    }	

      return classMethod;	    
	   
   }
   
   
   
   public void updateFileNamesUserSequenceDiagram(UserSequenceDiagram userSeqDiagram) throws Exception
   {
      String updateSql, errorMessage1="", errorMessage2="", textFileName="", pngFileName="";
	  int col, projectId=0, userId=0;
	  PreparedStatement updateStatement;
	  	  
	  // Get the information from the GUI
	  projectId    = userSeqDiagram.getProjectIdentifier();
	  userId       = userSeqDiagram.getUserIdentifier();
	  textFileName = userSeqDiagram.getTextFileName();
	  pngFileName  = userSeqDiagram.getPngFileName();
	  
	  try
	  {
	     updateSql = "update user_sequence_diagram ";
		 updateSql = updateSql + "set usd_text_filename = ?, ";
		 updateSql = updateSql + "usd_png_filename = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usd_id = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
				
		 updateStatement.setString(col++, textFileName);
		 updateStatement.setString(col++, pngFileName);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
			  
		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the FileNames for the User Identifier: " + userId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "for the Project Identifier: "+ projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }  

   }
   
   public void updateFileNamesMethodSequenceDiagram(MethodSequenceDiagram methodSeqDiagram) throws Exception
   {
      String updateSql, errorMessage1="", errorMessage2="", textFileName="", pngFileName="";
	  int col, projectId=0, userId=0, methodId=0;
	  PreparedStatement updateStatement;
	  
	  // Get the information from the GUI
	  projectId    = methodSeqDiagram.getProjectIdentifier();
	  //userId       = methodSeqDiagram.getUserIdentifier();
	  methodId     = methodSeqDiagram.getMethodIdentifier();
	  textFileName = methodSeqDiagram.getTextFileName2();
	  pngFileName  = methodSeqDiagram.getPngFileName2();
		  
	  try
	  {
	     updateSql = "update method_sequence_diagram ";
		 updateSql = updateSql + "set msd_text_filename = ?, ";
		 updateSql = updateSql + "msd_png_filename = ? ";
		 updateSql = updateSql + "where pc_id =? and ";
		 updateSql = updateSql + "usd_id = ? and ";
		 updateSql = updateSql + "msd_id = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
				
		 updateStatement.setString(col++, textFileName);
		 updateStatement.setString(col++, pngFileName);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
			  
		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the FileNames in the Method Identifier: " + methodId  + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "for the Project Identifier: " +projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "and User Identifier: "+ userId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }  

   }
   
   public ArrayList<UMLSequenceDiagramPlain> loadUniqueMethodsPlain(int projectId) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", packageName="", className="",
    		 methodName2="", value="";
      int methodId=0, cmId=0;
	  UMLSequenceDiagramPlain umlSeqDiagramPlainRecord = null;
	  LineType1 lineType1=LineType1.MethodStarts;
	  
	  ArrayList<UMLSequenceDiagramPlain> umlSeqDiagramPlainList = new ArrayList<UMLSequenceDiagramPlain>();
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
	 	 selectSql = "select distinct usdp.usdp_method_package_name packageName, usdp.usdp_method_class_name className, ";
	 	 selectSql = selectSql + "usdp.usdp_method_name_2 methodName2, usdp.cm_id cmId ";
	 	 selectSql = selectSql + "from uml_sequence_diagram_plain usdp ";
	 	 selectSql = selectSql + "where usdp.pc_id = ? and ";
	 	 selectSql = selectSql + "usdp.usdp_line_type_1 = ? ";
	 	
	 	 value = lineType1.name();
	 	 
	 	 selectStatement = dbCon.prepareStatement(selectSql);
	 	 selectStatement.setInt(1, projectId);
	 	 selectStatement.setString(2, value);
	 	 
	     selectResults = selectStatement.executeQuery();
	 		  
	 	 while(selectResults.next()) 
	 	 {
	 	    packageName = selectResults.getString("packageName");
	 	    className   = selectResults.getString("className");
	 	    methodName2 = selectResults.getString("methodName2");
	 	    cmId        = selectResults.getInt("cmId");
	 	    methodId ++;
	 	        
	 	   umlSeqDiagramPlainRecord = new UMLSequenceDiagramPlain(projectId, methodId, packageName,
	 			                                                  className, methodName2, cmId);
	 	   
	 	   umlSeqDiagramPlainList.add(umlSeqDiagramPlainRecord);
	 	   
	 	 }
	 		 
      }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
	 	 errorMessage2 = "Error XXXX: Ocurred while getting the Unique Method Names for the Project Identifier: " + projectId + System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "Package Name: " + packageName + " ,Class Name: " + className + " and Method Name: " + methodName2 +System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	 	 throw new Exception(errorMessage2);
	  }	
	  
	  return umlSeqDiagramPlainList;
   
   }
   
   public void updateIdentifierPlain(UMLSequenceDiagramPlain umlSeqDiagramPlain) throws Exception
   {
      String updateSql, errorMessage1="", errorMessage2="", packageName="", className="",
    		 methodName2="";
	  int col, projectId, methodId;
	  PreparedStatement updateStatement;
	  
	  // Get the values from the GUI
	  projectId   = umlSeqDiagramPlain.getProjectId();
	  methodId    = umlSeqDiagramPlain.getMethodIdentifier();
	  packageName = umlSeqDiagramPlain.getMethodPackageName();
	  className   = umlSeqDiagramPlain.getMethodClassName();
	  methodName2 = umlSeqDiagramPlain.getMethodName2();
	  
	  try
	  {
	     updateSql = "update uml_sequence_diagram_plain ";
		 updateSql = updateSql + "set usdp_method_id = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usdp_method_package_name = ? and ";
		 updateSql = updateSql + "usdp_method_class_name = ? and ";
		 updateSql = updateSql + "usdp_method_name_2 = ? ";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
				
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setString(col++, packageName);
		 updateStatement.setString(col++, className);
		 updateStatement.setString(col++, methodName2);
		 
			  
		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Method Identifier: " + methodId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "for the Project Identifier: "+ projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Package Name: " + packageName + " , Class Name: "+ className + " and Method Name: "+ methodName2 + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }  

   }
   
   public ArrayList<Integer> loadMethodIdsPlain(int projectId, int userId, String value) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="";
      int methodId=0;
	  
	  ArrayList<Integer> umlSeqDiagramPlainList = new ArrayList<Integer>();
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
	 	 selectSql = "select distinct usdp.usdp_method_id methodId ";
	 	 selectSql = selectSql + "from uml_sequence_diagram_plain usdp ";
	 	 selectSql = selectSql + "where usdp.pc_id = ? and ";
	 	 selectSql = selectSql + "usdp.usdp_user_id = ? and ";
	 	 selectSql = selectSql + "usdp.usdp_line_type_2 = ? ";
	 	 selectSql = selectSql + "order by usdp_method_id";
	 	 
	 	 selectStatement = dbCon.prepareStatement(selectSql);
	 	 selectStatement.setInt(1, projectId);
	 	 selectStatement.setInt(2, userId);
	 	 selectStatement.setString(3, value);
	 	 
	     selectResults = selectStatement.executeQuery();
	 		  
	 	 while(selectResults.next()) 
	 	 {
	 	    methodId = selectResults.getInt("methodId");
	 	    umlSeqDiagramPlainList.add(methodId);
	 	 }
	 		 
      }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
	 	 errorMessage2 = "Error XXXX: Ocurred while getting the Method Identifiers for the Project Identifier: " + projectId + System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "User Identifier: " + userId +System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	 	 throw new Exception(errorMessage2);
	  }	
	  
	  return umlSeqDiagramPlainList;
   
   }
   
   public ArrayList<UMLSequenceDiagramPlain> getMethodStepsPlain(int projectId, int userId, 
		                                                         int methodId, String value) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", 
    		 methodName1="", packageName="", className="";
      int lineNumber=0;
      
	  ArrayList<UMLSequenceDiagramPlain> umlSeqDiagramPlainList = new ArrayList<UMLSequenceDiagramPlain>();
	  UMLSequenceDiagramPlain umlSeqDiagramPlainRecord;
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
	 	 //selectSql = "select usdp.usdp_line_number lineNumber, usdp.usdp_method_name_1 methodName1, ";
	 	 selectSql = "select distinct usdp.usdp_method_name_1 methodName1, ";
	 	 selectSql = selectSql + "usdp.usdp_method_package_name packageName, usdp.usdp_method_class_name className ";
	 	 selectSql = selectSql + "from uml_sequence_diagram_plain usdp ";
	 	 selectSql = selectSql + "where usdp.pc_id = ? and ";
	 	 selectSql = selectSql + "usdp.usdp_user_id = ? and ";
	 	 selectSql = selectSql + "usdp.usdp_method_id = ? and ";
	 	 selectSql = selectSql + "usdp.usdp_line_type_2 = ? ";
	 	 //selectSql = selectSql + "order by usdp.usdp_line_number";

	 	 selectStatement = dbCon.prepareStatement(selectSql);
	 	 selectStatement.setInt(1, projectId);
	 	 selectStatement.setInt(2, userId);
	 	 selectStatement.setInt(3, methodId);
	 	 selectStatement.setString(4, value);
	 	 
	     selectResults = selectStatement.executeQuery();
	 		  
	 	 while(selectResults.next()) 
	 	 {
	 	    //lineNumber  = selectResults.getInt("lineNumber");
	 	    methodName1 = selectResults.getString("methodName1");
	 	    packageName = selectResults.getString("packageName");
	 	    className   = selectResults.getString("className");
	 	    
	 	    umlSeqDiagramPlainRecord = new UMLSequenceDiagramPlain(projectId, userId, methodId,
	 	    		                                               lineNumber, packageName,
	 	    		                                               className, methodName1);
	 	    
	 	    umlSeqDiagramPlainList.add(umlSeqDiagramPlainRecord);
	 	 }
	 		 
      }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
	 	 errorMessage2 = "Error XXXX: Ocurred while getting the Method Steps for the Project Identifier: " + projectId + System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "User Identifier: " + userId + " and Method Identifier: " + methodId +System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	 	 throw new Exception(errorMessage2);
	  }	
	  
	  return umlSeqDiagramPlainList;
   
   }
   
   public void saveMethodStepSequenceDiagram(MethodStepSequenceDiagram methodSteps) throws Exception
   {
      String insertSql="", errorMessage1="", errorMessage2="";
      PreparedStatement insertStatement;
      int projectId=0, userId=0, methodId=0, stepId=0, orderId=0;
      String methodName="";
      
      // Get the data from the GUI
      
      projectId  = methodSteps.getProjectIdentifier();
      userId     = methodSteps.getUserIdentifier();
      methodId   = methodSteps.getMethodIdentifier();
      stepId     = methodSteps.getStepIdentifier();
      methodName = methodSteps.getMethodName();
      orderId    = methodSteps.getOrderIdentifier();
      
      try 
      {
         insertSql = "insert into method_step_sequence_diagram(pc_id, usd_id, msd_id, ";
         insertSql = insertSql + "mssd_step_id, msdd_order_id, mssd_name) ";
         insertSql = insertSql + "values (?,?,?,?,?,?)";

	     insertStatement = dbCon.prepareStatement(insertSql);
	     insertStatement.setInt(1, projectId);
	     insertStatement.setInt(2, userId);
	     insertStatement.setInt(3, methodId);
	     insertStatement.setInt(4, stepId);
	     insertStatement.setInt(5, orderId);
	     insertStatement.setString(6, methodName);
	  
	     insertStatement.executeUpdate();
      }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while saving the Methods Steps from the Project Identifier: " + projectId + " ,User Identifier: " + userId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + methodId + " and Step Identifier: " + stepId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	   
   }   
   
   public void deleteMethodStepsSequenceDiagram(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Users
	  try 
	  {
         deleteSql = "delete from method_step_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Method Steps from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
   }
   
   public void updateMethodStepPlain(UMLSequenceDiagramPlain umlSeqDiagramPlain) throws Exception
   {
      String updateSql, errorMessage1="", errorMessage2="", packageName="", className="",
    		 methodName="";
	  int col, projectId, userId, methodId, stepId;
	  PreparedStatement updateStatement;

	  // Get the data from the GUI
	  projectId   = umlSeqDiagramPlain.getProjectId();
	  userId      = umlSeqDiagramPlain.getUserIdentifier();
	  methodId    = umlSeqDiagramPlain.getMethodIdentifier();
	  packageName = umlSeqDiagramPlain.getMethodPackageName();
	  className   = umlSeqDiagramPlain.getMethodClassName();
	  methodName  = umlSeqDiagramPlain.getMethodName1();
	  stepId      = umlSeqDiagramPlain.getStepIdentifier();
		  
	  try
	  {
	     updateSql = "update uml_sequence_diagram_plain usdp ";
		 updateSql = updateSql + "set usdp.mssd_step_id = ? ";
		 updateSql = updateSql + "where pc_id = ? and ";
		 updateSql = updateSql + "usdp.usdp_user_id = ? and ";
		 updateSql = updateSql + "usdp.usdp_method_id = ? and ";
		 updateSql = updateSql + "usdp.usdp_method_package_name = ? and ";
		 updateSql = updateSql + "usdp.usdp_method_class_name = ? and ";
		 updateSql = updateSql + "usdp.usdp_method_name_1 = ?";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
			
		 updateStatement.setInt(col++, stepId);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, userId);
		 updateStatement.setInt(col++, methodId);
		 updateStatement.setString(col++, packageName);
		 updateStatement.setString(col++, className);
		 updateStatement.setString(col++, methodName);

		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Method Step Number: " + stepId + " for the Project Identifier: "+ projectId +  System.lineSeparator();
		  errorMessage2 = errorMessage2 + "User Identifier: " + userId + " and Method Identifier: " + methodId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }  

   }
   
   public void saveUserFileSequenceDiagram(UserFileSequenceDiagram userFiles) throws Exception
   {
      String insertSql="", errorMessage1="", errorMessage2="", textFileName="", pngFileName="";
      int projectId=0, userId=0, fileId=0;
      PreparedStatement insertStatement;
      
      // Get the information from the GUI
      projectId    = userFiles.getProjectIdentifier();
      userId       = userFiles.getUserIdentifier();
      fileId       = userFiles.getFileIdentifier();
      textFileName = userFiles.getTextFilename();
      pngFileName  = userFiles.getPngFilename();
      
      try 
      {
         insertSql = "insert into user_file_sequence_diagram(pc_id, usd_id, ufsd_file_id, ";
         insertSql = insertSql + "ufsd_text_filename, ufsd_png_filename) ";
         insertSql = insertSql + "values(?,?,?,?,?)";

	     insertStatement = dbCon.prepareStatement(insertSql);
	     insertStatement.setInt(1, projectId);
	     insertStatement.setInt(2, userId);
	     insertStatement.setInt(3, fileId);
	     insertStatement.setString(4, textFileName);
	     insertStatement.setString(5, pngFileName);
	  
	     insertStatement.executeUpdate();
      }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while saving the Files for the User Identifier: " + userId + " ,Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "File Identifier: " + fileId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	   
   }
   
   public void deleteUserFilesSequenceDiagram(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Users
	  try 
	  {
         deleteSql = "delete from user_file_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing User Files from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
   }
   
   public void saveMethodFileSequenceDiagram(MethodFileSequenceDiagram methodFiles) throws Exception
   {
      String insertSql="", errorMessage1="", errorMessage2="", textFileName="", pngFileName="";
      int projectId=0, userId=0, methodId=0, stepId=0, fileId=0;
      PreparedStatement insertStatement;
      
      // Get the information from the GUI
      projectId    = methodFiles.getProjectIdentifier();
      userId       = methodFiles.getUserIdentifier();
      methodId     = methodFiles.getMethodIdentifier();
      stepId       = methodFiles.getStepIdentifier();
      fileId       = methodFiles.getFileIdentifier();
      textFileName = methodFiles.getTextFilename();
      pngFileName  = methodFiles.getPngFilename();
      
      try 
      {
         insertSql = "insert into method_file_sequence_diagram(pc_id, usd_id, msd_id, ";
         insertSql = insertSql + "mssd_step_id, mfsd_file_id, mfsd_text_filename, ";
         insertSql = insertSql + "mfsd_png_filename) ";
         insertSql = insertSql + "values(?,?,?,?,?,?,?)";

	     insertStatement = dbCon.prepareStatement(insertSql);
	     insertStatement.setInt(1, projectId);
	     insertStatement.setInt(2, userId);
	     insertStatement.setInt(3, methodId);
	     insertStatement.setInt(4, stepId);
	     insertStatement.setInt(5, fileId);
	     insertStatement.setString(6, textFileName);
	     insertStatement.setString(7, pngFileName);
	  
	     insertStatement.executeUpdate();
      }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while saving the Files for the Method Identifier: " + methodId + " ,Step Identifier: " + stepId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + " ,User Identifier: " + userId + " and File Identifier: " + fileId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	   
   }
   
   public void deleteMethodFilesSequenceDiagram(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Users
	  try 
	  {
         deleteSql = "delete from method_file_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Method Files from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
   }
   
   public ArrayList<MethodParameterData> loadMethodsParametersData(int projectId, String value1, String value2) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="",
    		 parameterName="", parameterDataTypeName="", parameterPackageName="", 
    		 parameterClassName="", parameterTextFileName="", parameterReturnType="",
    		 methodTextFileName="", methodShortName="", methodPackageName="", methodClassName="";

      int parameterId=0, methodId=0, parameterDataTypeId=0, parameterClassMethodId=0, 
    	  methodClassMethodId=0, parameterProgLangId=0, parameterClassInfoId=0, parameterEnumClassFlag=0;
      
      ArrayList<MethodParameterData> methodParameterList = new ArrayList<MethodParameterData>();
      MethodParameterData methodParameterRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Obtain all records from the UserName 
      // to obtain the corresponding UML Sequence Diagram
	  try
	  {
	     // SQL Statements      
		 selectSql = "";
		 selectSql = selectSql + "select psd.psd_id parameterId, psd.psd_parameter_name parameterName, ";
		 selectSql = selectSql + "psd.dt_id parameterDataTypeId, dt.dt_name parameterDataTypeName, ";
		 selectSql = selectSql + "psd.psd_package_name parameterPackageName, psd.psd_class_name parameterClassName, ";
		 selectSql = selectSql + "cm2.cm_id parameterClassMethodId, cm2.cm_text_filename parameterTextFileName, ";
		 selectSql = selectSql + "psd.pl_id parameterProgLangId, psd.psd_return_type parameterReturnType, ";
		 selectSql = selectSql + "cm2.ci_id parameterClassInfoId, ";
		 selectSql = selectSql + "ci.ci_enum_class parameterEnumClassFlag, ";
		 selectSql = selectSql + "msd.msd_id methodId, msd.msd_package_name methodPackageName, ";
		 selectSql = selectSql + "msd.msd_class_name methodClassName, msd.msd_short_name methodShortName, ";
		 selectSql = selectSql + "msd.msd_full_name methodFullName, msd.cm_id methodClassMethodId, ";
		 selectSql = selectSql + "cm1.cm_text_filename methodTextFileName, cm1.cm_database_method methodDbFlag ";
		 selectSql = selectSql + "from parameter_sequence_diagram psd, data_type dt, ";
		 selectSql = selectSql + "method_sequence_diagram msd, class_method cm1, ";
		 selectSql = selectSql + "class_method cm2, ";
		 selectSql = selectSql + "class_information ci ";
		 selectSql = selectSql + "where psd.pc_id = ? and ";
		 selectSql = selectSql + "dt.dt_name in(?,?) and ";
		 selectSql = selectSql + "dt.pl_id = psd.pl_id and ";
		 selectSql = selectSql + "dt.dt_id = psd.dt_id and ";
		 selectSql = selectSql + "msd.pc_id = psd.pc_id and ";
		 selectSql = selectSql + "psd.msd_id = msd.msd_id and ";
		 selectSql = selectSql + "msd.pc_id = cm1.pc_id and ";
		 selectSql = selectSql + "msd.cm_id = cm1.cm_id and	";
		 selectSql = selectSql + "cm2.pc_id = msd.pc_id and ";		 
		 selectSql = selectSql + "cm2.cm_id = psd.cm_id and ";
		 selectSql = selectSql + "ci.pc_id  = psd.pc_id and ";
		 selectSql = selectSql + "ci.ci_id = cm2.ci_id ";
		 selectSql = selectSql + "order by psd.msd_id desc";		 

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1,projectId);
		 selectStatement.setString(2,value1);
		 selectStatement.setString(3,value2);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    parameterId            = selectResults.getInt("parameterId"); 
            parameterName          = selectResults.getString("parameterName"); 
            parameterDataTypeId    = selectResults.getInt("parameterDataTypeId");
			parameterDataTypeName  = selectResults.getString("parameterDataTypeName");
			parameterPackageName   = selectResults.getString("parameterPackageName"); 
			parameterClassName     = selectResults.getString("parameterClassName"); 
			parameterClassMethodId = selectResults.getInt("parameterClassMethodId");
			parameterProgLangId    = selectResults.getInt("parameterProgLangId");
			parameterTextFileName  = selectResults.getString("parameterTextFileName");
			parameterReturnType    = selectResults.getString("parameterReturnType");
			parameterClassInfoId   = selectResults.getInt("parameterClassInfoId");
			parameterEnumClassFlag = selectResults.getInt("parameterEnumClassFlag");
			
			methodId               = selectResults.getInt("methodId"); 
            methodPackageName      = selectResults.getString("methodPackageName"); 
            methodClassName        = selectResults.getString("methodClassName"); 
            methodShortName        = selectResults.getString("methodShortName"); 
			methodClassMethodId    = selectResults.getInt("methodClassMethodId");			
			methodTextFileName     = selectResults.getString("methodTextFileName"); 
			
	        // Build a new record
	        methodParameterRecord = new MethodParameterData(
	        		                          projectId, parameterId, parameterName,
	                                          parameterDataTypeId, parameterDataTypeName, 
	                                          parameterPackageName, parameterClassName, 
	                                          parameterClassMethodId,
	                                          parameterProgLangId, 
	                                          parameterTextFileName,
	                                          parameterReturnType,
	                                          parameterClassInfoId,
	                                          parameterEnumClassFlag,
	                                          methodId, methodPackageName, methodClassName, 
	                                          methodShortName, methodClassMethodId, 
	                                          methodTextFileName);
	        
	        methodParameterList.add(methodParameterRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the different Methods and Parameters Data for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return methodParameterList;

   }   
	
   public int getClassMethodId(ClassMethod classMethod) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", packageName="",
    		 className="",returnType1="";
      int projectId =0, cmId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
      // Get the data from the GUI
	  projectId   = classMethod.getProjectId();
	  packageName = classMethod.getPackageName();
	  className   = classMethod.getClassName();
	  returnType1 = classMethod.getReturnType1();
	  
      // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
		 selectSql = "select max(cm.cm_id) cmId ";
		 selectSql = selectSql + "from class_method cm ";
		 selectSql = selectSql + "where cm.pc_id = ? and ";
		 selectSql = selectSql + "cm.cm_package_name = ? and ";
		 selectSql = selectSql + "cm.cm_class_name = ? and ";
		 selectSql = selectSql + "cm_return_type_1 = ? ";
		 selectSql = selectSql + "group by cm_filename";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, packageName);
		 selectStatement.setString(3, className);
		 selectStatement.setString(4, returnType1);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        cmId = selectResults.getInt("cmId");
		 }
		 
	    }	
	    catch(Exception e)
	    {
	       errorMessage1 = e.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while getting the Class Method Id for the Project Identifier: " + projectId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Package Name: " + packageName + " ,Class Name: " + className + "and Return Type 1: " + returnType1 +System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	       throw new Exception(errorMessage2);
	    }	

      return cmId;	    
	   
   }
   
   public ClassMethod getClassMethodDetails2(int projectId, String packageName, String className, String methodName) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", textFileName="", returnType1="",
    		 returnType2="";
      int id=0;	

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      ClassMethod classMethod=null;
      
	  try
	  {
	     // SQL Statement     
		 selectSql = "select cm.cm_id id, cm.cm_text_filename textFileName, ";
		 selectSql = selectSql + "cm.cm_return_type_1 returnType1, ";
		 selectSql = selectSql + "cm.cm_return_type_2 returnType2 ";
		 selectSql = selectSql + "from class_method cm ";
		 selectSql = selectSql + "where cm.pc_id = ? and ";
		 selectSql = selectSql + "cm.cm_package_name = ? and ";
		 selectSql = selectSql + "cm.cm_class_name = ? and ";
		 selectSql = selectSql + "cm.cm_short_method_name = ? ";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, packageName);
		 selectStatement.setString(3, className);
		 selectStatement.setString(4, methodName);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        id           = selectResults.getInt("id");
	        textFileName = selectResults.getString("textFileName");
	        returnType1  = selectResults.getString("returnType1");
	        returnType2  = selectResults.getString("returnType2");
		 }
		 
		 // Build a new record of the ClassMethod data type
		 classMethod = new ClassMethod(projectId, packageName, className, id, returnType1,
			                           returnType2, textFileName);
		 
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Method Identifier for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Name: " + methodName + ", Package Name: "+ packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "and Class Name: " + className +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return classMethod;

   }  
   
   public int getAttributeId(int projectId, int methodId, int parameterId, int parameterDataTypeId, int parameterProgLangId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      int attributeId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  

      // Obtain the Maximum value of the Attribute id
	  try
	  {
	     // SQL Statements      
		 selectSql = "select max(asd.asd_id) attributeId ";
		 selectSql = selectSql + "from attribute_sequence_diagram asd ";
		 selectSql = selectSql + "where asd.pc_id = ? and ";
		 selectSql = selectSql + "asd.msd_id = ? and ";
		 selectSql = selectSql + "asd.psd_id = ? and ";
		 selectSql = selectSql + "asd.dt_id_1 = ? and ";
		 selectSql = selectSql + "asd.pl_id_1 = ? ";


		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, methodId);
		 selectStatement.setInt(3, parameterId);
		 selectStatement.setInt(4, parameterDataTypeId);
		 selectStatement.setInt(5, parameterProgLangId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    attributeId = selectResults.getInt("attributeId");
		 }
		 
	    }	
	    catch(Exception e1)
	    {
	       errorMessage1 = e1.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while getting the Maximum Attribute Id for the Project Identifier: " + projectId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Method Identifier : " + methodId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Parameter Identfier: " + parameterId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Parameter Data Type Identifier : " + parameterDataTypeId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Parameter Programming Language Identifier: " + parameterProgLangId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	       throw new Exception(errorMessage2);
	    }	

      return attributeId;	    
	   
   }
   
   public void saveAttributeSequenceDiagram(AttributeSequenceDiagram attributeSeqDiagram) throws Exception //6
   {
	  String insertSql="", errorMessage1="", errorMessage2="", attributeName="", methodName="", 
			 packageName="", className="", attributeValue="";
      int col=0, projectId=0, parameterId=0, dataTypeId1=0, attributeId=0, cmId1=0, dataTypeId2=0, 
    	  cmId2=0, methodId=0, plId1=0, plId2=0, ciId1=0, ciId2=0, caId1=0;

	  PreparedStatement insertStatement = null;
	  
      try 
      {
    	 // Get the values from the GUI  
 	     projectId         = attributeSeqDiagram.getProjectIdentifier();
 	     methodId          = attributeSeqDiagram.getMethodIdentifier();
 	     parameterId       = attributeSeqDiagram.getParameterIdentifier();
 	     dataTypeId1       = attributeSeqDiagram.getDataTypeIdentifier1();
 	     attributeId       = attributeSeqDiagram.getAttributeIdentifier();
 	     plId1             = attributeSeqDiagram.getProgrammingLanguageIdentifier1();
 	     ciId1             = attributeSeqDiagram.getClassInfoIdentifier1();
 	     caId1             = attributeSeqDiagram.getClassAttributeIdentifier1();
 	     
 	     cmId1             = attributeSeqDiagram.getClassMethodIdentifier1();
 	     attributeName     = attributeSeqDiagram.getAttributeName();
 	     attributeValue    = attributeSeqDiagram.getAttributeValue();
 	     dataTypeId2       = attributeSeqDiagram.getDataTypeIdentifier2();
 	     plId2             = attributeSeqDiagram.getProgrammingLanguageIdentifier2(); 
 	     packageName       = attributeSeqDiagram.getAttributePackageName();
 	     className         = attributeSeqDiagram.getAttributeClassName();
 	     
 	     methodName        = attributeSeqDiagram.getMethodName();
 	     cmId2             = attributeSeqDiagram.getClassMethodIdentifier2();
 	     ciId2             = attributeSeqDiagram.getClassInfoIdentifier2();
 	     
   	     // SQL Statement
 	     insertSql = "insert into attribute_sequence_diagram (pc_id, msd_id, psd_id, dt_id_1, "; 
 	     insertSql = insertSql + "pl_id_1, ci_id_1, ca_id_1, asd_id, asd_name, asd_value, ";
 	     insertSql = insertSql + "dt_id_2, pl_id_2, asd_package_name, asd_class_name, ci_id_2, ";
 	     insertSql = insertSql + "asd_method_name, cm_id_1, cm_id_2) "; 
   	     insertSql = insertSql + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
   	     
   	     insertStatement = dbCon.prepareStatement(insertSql);
      
   	     col = 1;
   	     insertStatement.setInt(col++, projectId);
   	     insertStatement.setInt(col++, methodId);
   	     insertStatement.setInt(col++, parameterId);
   	     insertStatement.setInt(col++, dataTypeId1);
   	     insertStatement.setInt(col++, plId1);
   	     insertStatement.setInt(col++, ciId1);
   	     insertStatement.setInt(col++, caId1);
   	     
   	     insertStatement.setInt(col++, attributeId);
   	     insertStatement.setString(col++, attributeName);
   	     insertStatement.setString(col++, attributeValue);
   	     insertStatement.setInt(col++, dataTypeId2);
   	     insertStatement.setInt(col++, plId2);
   	     insertStatement.setString(col++, packageName);
   	     insertStatement.setString(col++, className);
   	     insertStatement.setInt(col++, ciId2);
   	     
   	     insertStatement.setString(col++, methodName);
   	     insertStatement.setInt(col++, cmId1);
   	     insertStatement.setInt(col++, cmId2);
   	     
	     insertStatement.executeUpdate();
	     insertStatement.close();
      }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Attribute Sequence Diagram for the Project Id: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Id  : " + parameterId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Data Type Id  : " + dataTypeId1 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Attribute Id  : " + attributeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message : " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
	} 
   
   public void deleteAttributesSequenceDiagram(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Users
	  try 
	  {
         deleteSql = "delete from attribute_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Attributes from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
   }
   
   public ClassMethod getParentMethodDetails(int projectId, int cmId1, String methodName) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", packageName="",
    		 className="", shortMethodName="", textFileName="";
      int parentId = 0, cmId2=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  ClassMethod classMethod = null;
	  
      // Obtain the parent Id of the previous method
	  try
	  {
	     // SQL Statements      
		 selectSql = "select ts.ts_parent_id parentId ";
		 selectSql = selectSql + "from tree_structure ts ";
		 selectSql = selectSql + "where ts.pc_id = ? and ";
		 selectSql = selectSql + "ts.cm_id = ? and ";
		 selectSql = selectSql + "ts.ts_name = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, cmId1);
		 selectStatement.setString(3, methodName);

		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        parentId = selectResults.getInt("parentId");
		 }
		 
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while getting the Parent Method Details for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "and Class Method Identifier: " + cmId1 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
		
      // Obtain the parent Id of the previous method
	  try
	  {
	     // SQL Statements      
		 selectSql = "select ts.cm_id cmId, cm.cm_package_name packageName, cm.cm_class_name className, ";
		 selectSql = selectSql + "cm.cm_short_method_name shortMethodName, cm.cm_text_filename textFileName ";
		 selectSql = selectSql + "from tree_structure ts, ";
		 selectSql = selectSql + "class_method cm ";
		 selectSql = selectSql + "where ts.pc_id = ? and ";
		 selectSql = selectSql + "ts.ts_id = ? and ";
		 selectSql = selectSql + "ts.pc_id = cm.pc_id and ";
		 selectSql = selectSql + "cm.cm_id = ts.cm_id";
		 
		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setInt(2, parentId);

		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        cmId2           = selectResults.getInt("cmId");
	        packageName     = selectResults.getString("packageName");
	        className       = selectResults.getString("className");
	        shortMethodName = selectResults.getString("shortMethodName");
	        textFileName    = selectResults.getString("textFileName");
	        
	        classMethod = new ClassMethod(projectId, cmId2, packageName, className,  
	        		                      shortMethodName, textFileName);
		 }
		 
	  }	
	  catch(Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while getting the Method details for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier: " + parentId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }	  
	  
	  
	  return classMethod;
	   
   }
   
   public List<ConfigFile> getConfigFiles()
   {
	   return Collections.unmodifiableList(configFiles);
   }
   
   public void loadProjects() throws Exception 
   {
      int id;
      String sql="", projectName="", jarFileName="", inputDir="", outputDir="", errorMessage1="", errorMessage2="";
      String plName="", dbmsName="", inputMethodName="", startMethod="", endMethod="", connectMethod="", 
             connectMethodReturnValue="", operatingSystem="";
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
	
   public void saveValueSequenceDiagram(ValueSequenceDiagram valueSeqDiagram) throws Exception //6
   {
	  String errorMessage1="", errorMessage2="", insertSql="", valuePackageName="", valueClassName="", 
			 valueName="", valueEquivalence="";
      int col=0, projectId=0, parameterId=0, dataTypeId1=0, attributeId=0, cmId1=0, methodId=0, 
    	  plId1=0, valueId=0, dataTypeId2=0, plId2=0, ciId2=0, caId2=0;

	  PreparedStatement insertStatement = null;

      try 
      {
    	 // Get the values from the GUI  
 	     projectId         = valueSeqDiagram.getProjectIdentifier();
 	     methodId          = valueSeqDiagram.getMethodIdentifier();
 	     parameterId       = valueSeqDiagram.getParameterIdentifier();
 	     dataTypeId1       = valueSeqDiagram.getDataTypeIdentifier1();
 	     plId1             = valueSeqDiagram.getProgrammingLanguageIdentifier1();
 	     attributeId       = valueSeqDiagram.getAttributeIdentifier();
 	     valueId           = valueSeqDiagram.getValueIdentifier();
         valueName         = valueSeqDiagram.getValueName();
         valueEquivalence  = valueSeqDiagram.getValueEquivalence();
 	     valuePackageName  = valueSeqDiagram.getValuePackageName();
 	     valueClassName    = valueSeqDiagram.getValueClassName();
 	     
 	     dataTypeId2       = valueSeqDiagram.getDataTypeIdentifier2();
 	     plId2             = valueSeqDiagram.getProgrammingLanguageIdentifier2(); 
 	     ciId2             = valueSeqDiagram.getClassInfoIdentifier2();
 	     caId2             = valueSeqDiagram.getClassAttributeIdentifier2();
 	    		
 	     cmId1             = valueSeqDiagram.getClassMethodIdentifier1();

   	     // SQL Statement
 	     insertSql = "insert into value_sequence_diagram (pc_id, msd_id, psd_id, dt_id_1, pl_id_1, "; 
 	     insertSql = insertSql + "asd_id, vsd_id, vsd_name, vsd_value, "; 
 	     insertSql = insertSql + "vsd_package_name, vsd_class_name, "; 
 	     insertSql = insertSql + "dt_id_2, pl_id_2, ci_id_2, ca_id_2, cm_id_1) ";
   	     insertSql = insertSql + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
   	     
   	     insertStatement = dbCon.prepareStatement(insertSql);
      
   	     col = 1;
   	     insertStatement.setInt(col++, projectId);
   	     insertStatement.setInt(col++, methodId);
   	     insertStatement.setInt(col++, parameterId);
   	     insertStatement.setInt(col++, dataTypeId1);
   	     insertStatement.setInt(col++, plId1);
   	     insertStatement.setInt(col++, attributeId);
 	     insertStatement.setInt(col++, valueId);
 	     insertStatement.setString(col++, valueName);
 	     insertStatement.setString(col++, valueEquivalence);
 	     insertStatement.setString(col++, valuePackageName);
 	     insertStatement.setString(col++, valueClassName);
 	     
 	     insertStatement.setInt(col++, dataTypeId2);
 	     insertStatement.setInt(col++, plId2);
 	     insertStatement.setInt(col++, ciId2);
 	     insertStatement.setInt(col++, caId2);
 	     
   	     insertStatement.setInt(col++, cmId1);

   	     
	     insertStatement.executeUpdate();
	     insertStatement.close();
      }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Value Sequence Diagram " + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Project Idenfier: "+ projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Id       : " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Parameter Id    : " + parameterId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Data Type Id    : " + dataTypeId1 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Programming Language Id: " + plId1 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Attribute Id    : " + attributeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Value Id        : " + valueId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
	}  
   
   public void deleteValuesSequenceDiagram(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Users
	  try 
	  {
         deleteSql = "delete from value_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Values from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
   }
   
   public ArrayList<MethodDataSequenceDiagram> getMethodDetailsSequenceDiagram(int projectId) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", methodOwner="", methodPackageName="",
    		 methodClassName="", methodShortName="", methodFullName="",
    		 classMethodPackageName="", classMethodClassName="", classMethodFullName="", classMethodShortName="",
    		 classMethodReturnType1="", classMethodReturnType2="", classMethodTextFileName="",
    		 returnTypeName="";
      int methodId=0, classMethodId=0, returnTypeId=0;	
      ArrayList<MethodDataSequenceDiagram> methodDetSeqDiagramList = new ArrayList<MethodDataSequenceDiagram>();
      MethodDataSequenceDiagram methodDetSeqDiagramRecord;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
	  try
	  {
	     // SQL Statement     
		 selectSql = "select msd.msd_id methodId, ";
		 selectSql = selectSql + "msd.msd_owner methodOwner, ";
		 selectSql = selectSql + "msd.msd_package_name methodPackageName, ";
		 selectSql = selectSql + "msd.msd_class_name methodClassName, ";
	     selectSql = selectSql + "msd.msd_short_name methodShortName, ";
	     selectSql = selectSql + "msd.msd_full_name methodFullName, ";
	     selectSql = selectSql + "cm.cm_id classMethodId, ";
	     selectSql = selectSql + "cm.cm_package_name classMethodPackageName, ";
	     selectSql = selectSql + "cm.cm_class_name classMethodClassName, ";
	     selectSql = selectSql + "cm.cm_short_method_name classMethodShortName, ";
	     selectSql = selectSql + "cm.cm_full_method_name classMethodFullName, ";
	     selectSql = selectSql + "cm.cm_return_type_1 classMethodReturnType1, ";
	     selectSql = selectSql + "cm.cm_return_type_2 classMethodReturnType2, ";
	     selectSql = selectSql + "cm.cm_text_filename classMethodTextFileName, ";
	     selectSql = selectSql + "rt.rt_id returnTypeId, ";
	     selectSql = selectSql + "rt.rt_name returnTypeName ";
	     selectSql = selectSql + "from method_sequence_diagram msd, ";
	     selectSql = selectSql + "class_method cm, ";
	     selectSql = selectSql + "return_type rt ";
	     selectSql = selectSql + "where  msd.pc_id = ? and ";
	     selectSql = selectSql + "msd.pc_id = cm.pc_id and ";
	     selectSql = selectSql + "msd.cm_id = cm.cm_id and ";
	     selectSql = selectSql + "cm.cm_return_type_1 = rt.rt_name ";
	     selectSql = selectSql + "order by msd.msd_id"; 

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			methodId          = selectResults.getInt("methodId");
	        methodOwner       = selectResults.getString("methodOwner");
	        methodPackageName = selectResults.getString("methodPackageName");
	        methodClassName   = selectResults.getString("methodClassName");
	        methodShortName   = selectResults.getString("methodShortName");
	        methodFullName    = selectResults.getString("methodFullName");
	        
	        classMethodId           = selectResults.getInt("classMethodId");
		    classMethodPackageName  = selectResults.getString("classMethodPackageName");
		    classMethodClassName    = selectResults.getString("classMethodClassName");
		    classMethodShortName    = selectResults.getString("classMethodShortName");
		    classMethodFullName     = selectResults.getString("classMethodFullName");
		    classMethodReturnType1  = selectResults.getString("classMethodReturnType1");
		    classMethodReturnType2  = selectResults.getString("classMethodReturnType2");
		    classMethodTextFileName = selectResults.getString("classMethodTextFileName");
		    
			returnTypeId   = selectResults.getInt("returnTypeId");
	        returnTypeName = selectResults.getString("returnTypeName");
	        
			methodDetSeqDiagramRecord = new MethodDataSequenceDiagram(projectId, methodId, methodOwner,
																	  methodPackageName, methodClassName, methodShortName, 
																	  methodFullName, classMethodId, classMethodPackageName, 
																	  classMethodClassName, classMethodShortName, classMethodFullName, 
																	  classMethodReturnType1, classMethodReturnType2, classMethodTextFileName, 
																	  returnTypeId, returnTypeName);
	        
			methodDetSeqDiagramList.add(methodDetSeqDiagramRecord);
	        
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
	  
      return methodDetSeqDiagramList;

   }  
   
   public void updateReturnTypeMethodSequenceDiagram(MethodDataSequenceDiagram methodDetSeqDiagram) throws Exception
   {
      String updateSql, errorMessage1="", errorMessage2="";
	  int col, projectId=0, methodId=0, returnTypeId=0;
	  PreparedStatement updateStatement;
	  
	  // Get the information from the GUI
	  projectId    = methodDetSeqDiagram.getProjectIdentifier();
	  methodId     = methodDetSeqDiagram.getMethodIdentifier();
	  returnTypeId = methodDetSeqDiagram.getReturnTypeIdentifier();
	  		  
	  try
	  {
	     updateSql = "update method_sequence_diagram msd ";
		 updateSql = updateSql + "set msd.rt_id = ? ";
		 updateSql = updateSql + "where msd.pc_id = ? and ";
		 updateSql = updateSql + "msd.msd_id = ? ";
	     updateStatement = dbCon.prepareStatement(updateSql);	
	     
		 col = 1;
				
		 updateStatement.setInt(col++, returnTypeId);
		 updateStatement.setInt(col++, projectId);
		 updateStatement.setInt(col++, methodId);
			  
		 updateStatement.executeUpdate();
		 updateStatement.close();
	   }
	   catch(SQLException e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while updating the Return Type: " + returnTypeId  + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "for the Method Identifier: "  + methodId  + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "and the Project Identifier: " + projectId + System.lineSeparator();
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new SQLException(errorMessage2);
	   }  

   }
   
   public void saveReturnTypeValueSequenceDiagram(ReturnTypeValueSequenceDiagram returnTypeValueSeqDiagram) throws Exception
   {
      String insertSql="", errorMessage1="", errorMessage2="";
      PreparedStatement insertStatement;
      int projectId=0, methodId=0, id=0, ciId=0, caId=0, returnTypeId=0;
      String name="", equivalence="", packageName="", className="";
      
      // Get the data from the GUI
      projectId    = returnTypeValueSeqDiagram.getProjectIdentifier();
      methodId     = returnTypeValueSeqDiagram.getMethodIdentifier();
      returnTypeId = returnTypeValueSeqDiagram.getReturnTypeIdentifier();
      id           = returnTypeValueSeqDiagram.getReturnTypeValueIdentifier();
      name         = returnTypeValueSeqDiagram.getReturnTypeValueName();
      equivalence  = returnTypeValueSeqDiagram.getReturnTypeValueEquivalence();
      packageName  = returnTypeValueSeqDiagram.getReturnTypeValuePackageName();
      className    = returnTypeValueSeqDiagram.getReturnTypeValueClassName();
      ciId         = returnTypeValueSeqDiagram.getClassInformationIdentifier();
      caId         = returnTypeValueSeqDiagram.getAttributeIdentifier();
 
      try 
      {
         insertSql = "insert into return_type_value_sequence_diagram(pc_id, msd_id, rt_id, rtvsd_id, ";
         insertSql = insertSql + "rtvsd_name, rtvsd_value, rtvsd_package_name, ";
         insertSql = insertSql + "rtvsd_class_name, ci_id, ca_id)";
         insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	     insertStatement = dbCon.prepareStatement(insertSql);
	     insertStatement.setInt(1, projectId);
	     insertStatement.setInt(2, methodId);
	     insertStatement.setInt(3, returnTypeId);
	     insertStatement.setInt(4, id);
	     insertStatement.setString(5, name);
	     insertStatement.setString(6, equivalence);
	     insertStatement.setString(7, packageName);
	     insertStatement.setString(8, className);
	     insertStatement.setInt(9, ciId);
	     insertStatement.setInt(10, caId);

	     insertStatement.executeUpdate();
      }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while saving the Return Type Values from the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Identifier            : " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Return Type Value Identifier : " + id + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	   
   }  
   
   
   public void deleteReturnTypeValueSequenceDiagram(int projectId) throws SQLException //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Return Type Values
	  try 
	  {
         deleteSql = "delete from return_type_value_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(SQLException e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Return Type Values from the Project Identifier: " + projectId +System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new SQLException(errorMessage2);
	  }
	   
   }
   
   
   public ClassMethod getClassMethodDetails3(MethodDataSequenceDiagram methodDataSeqDiagram) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="", textFileName="", packageName="",
    		 className="", shortMethodName="";
      int projectId=0, id=0;
      
      ClassMethod classMethod = null;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      // Get the Data from the GUI
      
      projectId       = methodDataSeqDiagram.getProjectIdentifier();
      packageName     = methodDataSeqDiagram.getMethodPackageName();
      className       = methodDataSeqDiagram.getMethodClassName();
      shortMethodName = methodDataSeqDiagram.getMethodShortName();
      
      // Obtain the different methods that corresponds to the UserName 
	  try
	  {
	     // SQL Statements      
		 selectSql = "select cm.cm_id id, cm.cm_text_filename textFileName ";
		 selectSql = selectSql + "from class_method cm ";
		 selectSql = selectSql + "where cm.pc_id = ? and ";
		 selectSql = selectSql + "cm.cm_package_name = ? and ";
		 selectSql = selectSql + "cm.cm_class_name = ? and ";
		 selectSql = selectSql + "cm.cm_short_method_name =? ";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, packageName);
		 selectStatement.setString(3, className);
		 selectStatement.setString(4, shortMethodName);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    id           = selectResults.getInt("id");
	        textFileName = selectResults.getString("textFileName");
	        
	        // Build a new record
	        classMethod = new ClassMethod(projectId, id, textFileName);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while getting the Class Method Details for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Package Name      : " + packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Name        : " + className   + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Short Method Name : " + shortMethodName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return classMethod;

   }   

   public int getClassInformation(int projectId, String packageName, String className) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      int id=0;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
            
      // Obtain the Class Information
	  try
	  {
	     // SQL Statements      
		 selectSql = "select ci.ci_id id ";
		 selectSql = selectSql + "from class_information ci ";
		 selectSql = selectSql + "where ci.pc_id = ? and ";
		 selectSql = selectSql + "ci.ci_package_name = ? and ";
		 selectSql = selectSql + "ci.ci_class_name = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, packageName);
		 selectStatement.setString(3, className);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    id = selectResults.getInt("id");
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while getting the Class Information for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Package Name : " + packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Name   : " + className   + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return id;

   }    
   
   public ArrayList<ClassAttribute> loadClassAttributes(int projectId, int classInfoId) throws Exception //5
   {
	  String sql="", errorMessage1="", errorMessage2="", packageName="",
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
	     sql = sql + "ca.ci_id = ? and ";
	     sql = sql + "ca.ca_short_name <> 'ENUM$VALUES' ";
	     sql = sql + "order by ca.ca_id";
		  
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
   
   public ArrayList<AttributeSequenceDiagram> loadAttributeInformation(int projectId) throws Exception
   {
      String errorMessage1="", errorMessage2="", selectSql="";
      int methodId=0, parameterId=0, parameterDataTypeId=0, parameterProgLangId=0, attributeId=0, attributeClassInfoId=0;
      ArrayList<AttributeSequenceDiagram> attributeSeqDiagramList = new ArrayList<AttributeSequenceDiagram>(); 
      AttributeSequenceDiagram attributeSeqDiagramRecord;
	  PreparedStatement selectStatement;
      ResultSet selectResults;
      
      attributeSeqDiagramList.clear();
      
      // Obtain the Class Information Id 2 from the current Project and Programming Language
	  try
	  {
	     // SQL Statements   
		  
	     selectSql = "select asd.msd_id methodId, ";
		 selectSql = selectSql + "asd.psd_id parameterId, ";
		 selectSql = selectSql + "asd.dt_id_1 parameterDataTypeId, ";
		 selectSql = selectSql + "asd.pl_id_1 parameterProgLangId, ";
		 selectSql = selectSql + "asd.asd_id attributeId, ";
		 selectSql = selectSql + "asd.ci_id_2 attributeClassInfoId ";
		 selectSql = selectSql + "from attribute_sequence_diagram asd ";
		 selectSql = selectSql + "where asd.pc_id = ? and ";
		 selectSql = selectSql + "asd.ci_id_2 > 0 ";
		 selectSql = selectSql + "order by asd.msd_id, asd.ci_id_2";
		 
		  
		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
	        methodId             = selectResults.getInt("methodId");
	        parameterId          = selectResults.getInt("parameterId");
	        parameterDataTypeId  = selectResults.getInt("parameterDataTypeId");
	        parameterProgLangId  = selectResults.getInt("parameterProgLangId");
	        attributeId          = selectResults.getInt("attributeId");
	        attributeClassInfoId = selectResults.getInt("attributeClassInfoId");
	        
	        attributeSeqDiagramRecord = new AttributeSequenceDiagram(projectId, methodId, 
	        		                                                 parameterId, parameterDataTypeId,
	        		                                                 parameterProgLangId, attributeId,
	        		                                                 attributeClassInfoId);
	        
	        // Add the new Attribute Record to the ArrayList		
	        attributeSeqDiagramList.add(attributeSeqDiagramRecord);
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Attribute Information" + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Project Identifier   : " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return attributeSeqDiagramList;

   } 
   
   public DataType getDataTypeDetails1(String programmingLanguage, String name1) throws Exception
   {
      String errorMessage1="", errorMessage2="", selectSql, dataCategoryStr="";
      int dataId=0, progrId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  DataType dataType;
	  DataCategory dataCategory;
	  
	  /*
	  System.out.println("*** INSIDE THE NEW getDataTypeDetails1 DATABASE ***");
	  
	  System.out.println("Programming Language : " + programmingLanguage);
	  System.out.println("Name 1               : " + name1);
	  */
	  
      // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {

	     selectSql = "select plh.plh_id progrId, ";
	     selectSql = selectSql + "dt.dt_id dataId, ";
	     selectSql = selectSql + "dt.dt_category dataCategory ";
		 selectSql = selectSql + "from data_type dt, ";
		 selectSql = selectSql + "programming_language_header plh ";
		 selectSql = selectSql + "where dt.dt_name = ? and ";
		 selectSql = selectSql + "plh.plh_name = ? and ";		 
		 selectSql = selectSql + "plh.plh_id = pl_id"; 

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setString(1, name1);
		 selectStatement.setString(2, programmingLanguage);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			progrId         =  selectResults.getInt("progrId");	 
		    dataId          =  selectResults.getInt("dataId");	 
		    dataCategoryStr = selectResults.getString("dataCategory");
		 }
		 
		 dataCategory = DataCategory.valueOf(dataCategoryStr);
		 
		 /*
	     System.out.println("DB INFORMATION");
		 System.out.println("Name  1       : " + name1);
		 System.out.println("Data Id       : " + dataId);
		 System.out.println("Data Category : " + dataCategory);
		 */
		 
		 // Build the dataType record
	     dataType = new DataType(progrId, name1, dataId, dataCategory);
	     
	     //System.out.println("DATA TYPE 1: " + dataType);
	     
	    }	
	    catch(Exception e1)
	    {
		   errorMessage1 = e1.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while getting the Data Type from the Programming Language: " + programmingLanguage + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Data Type Name: " + name1 + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		   throw new Exception(errorMessage2);
	    }
	  
	  selectStatement.close();
	  selectResults.close();

      return dataType;	    
	   
   }
   
   public int getClassEnumFlag(int projectId, String packageName, String className) throws Exception
   {
      String selectSql="", errorMessage1="", errorMessage2="";
      int enumFlag=0;

	  PreparedStatement selectStatement;
      ResultSet selectResults;
            
      // Obtain the Class Information
	  try
	  {
	     // SQL Statements      
		 selectSql = "select ci.ci_enum_class enumFlag ";
		 selectSql = selectSql + "from class_information ci ";
		 selectSql = selectSql + "where ci.pc_id = ? and ";
		 selectSql = selectSql + "ci.ci_package_name = ? and ";
		 selectSql = selectSql + "ci.ci_class_name = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, packageName);
		 selectStatement.setString(3, className);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    enumFlag = selectResults.getInt("enumFlag");
		 }
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while getting the Class Enum Flag for the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Package Name : " + packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Name   : " + className   + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	  
      return enumFlag;

   }  
   
   public void deleteReturnTypeSequenceDiagram(int projectId) throws Exception //5
   {
      String deleteSql, errorMessage1="", errorMessage2="";	
      
	  // Delete all the existing Methods
	  try 
	  {
         deleteSql = "delete from return_type_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Return Type Sequence Diagram from the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	   
   }   
   
	
   public void saveReturnTypeSequenceDiagram(ReturnTypeSequenceDiagram returnTypeSeqDiagram) throws Exception //6
   {
      int col=0, projectId=0, methodId=0, returnTypeId=0, progLangId=0, 
    	  dataTypeId=0, cmId=0, rtId2=0;
	  String insertSql="", errorMessage1="", errorMessage2="", returnTypeName="", 
			 dataTypeName="", packageName="", className="", returnType="";
	  PreparedStatement insertStatement = null;
	  
      try 
      {
    	 // Get the values from the GUI  
 	     projectId      = returnTypeSeqDiagram.getProjectIdentifier();
 	     methodId       = returnTypeSeqDiagram.getMethodIdentifier();
 	     returnTypeId   = returnTypeSeqDiagram.getReturnTypeIdentifier();
         progLangId     = returnTypeSeqDiagram.getProgrammingLanguageIdentifier();
         returnTypeName = returnTypeSeqDiagram.getReturnTypeName();
         
         dataTypeId   = returnTypeSeqDiagram.getDataTypeIdentifier();
         dataTypeName = returnTypeSeqDiagram.getDataTypeName();
         packageName  = returnTypeSeqDiagram.getPackageName();
         className    = returnTypeSeqDiagram.getClassName();
         returnType   = returnTypeSeqDiagram.getReturnType();
         cmId         = returnTypeSeqDiagram.getClassMethodIdentifier();
         rtId2        = returnTypeSeqDiagram.getReturnTypeIdentifier2();
         
   	     // SQL Statement
   	     insertSql = "insert into return_type_sequence_diagram (pc_id, msd_id, "; 
   	     insertSql = insertSql + "rtsd_id, pl_id, rtsd_return_type_name, ";
   	     insertSql = insertSql + "dt_id, rtsd_data_type_name, rtsd_package_name, ";
   	     insertSql = insertSql + "rtsd_class_name, rtsd_return_type, cm_id, rt_id) ";
   	  
   	     insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
   	     insertStatement = dbCon.prepareStatement(insertSql);
      
   	     col = 1;
   	     insertStatement.setInt(col++, projectId);
   	     insertStatement.setInt(col++, methodId);
   	     insertStatement.setInt(col++, returnTypeId);
   	     insertStatement.setInt(col++, progLangId);
   	     insertStatement.setString(col++, returnTypeName);
   	     
   	     insertStatement.setInt(col++, dataTypeId);
   	     insertStatement.setString(col++, dataTypeName);
   	     insertStatement.setString(col++, packageName);
   	     insertStatement.setString(col++, className);
   	     insertStatement.setString(col++, returnType);   
   	     insertStatement.setInt(col++, cmId);
   	     insertStatement.setInt(col++, rtId2);
   	     
	     insertStatement.executeUpdate();
	     insertStatement.close();
      }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while inserting a new Return Type Sequence Diagram for the Project Id: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Method Id        : " + methodId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Return Type Id   : " + returnTypeId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Return Type Name : " + returnTypeName +   System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
		
   }  
   
   
   
   public void deleteReturnTypeAttributesSequenceDiagram(int projectId) throws Exception //5
   {
      String errorMessage1="", errorMessage2="", deleteSql="";	
      
	  // Delete all the existing Methods
	  try 
	  {
         deleteSql = "delete from return_type_attribute_sequence_diagram ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Return Type Attribute Sequence Diagram from the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	   
   }  
   
   public void deleteReturnTypeValuesSequenceDiagram_1(int projectId) throws Exception //5
   {
      String errorMessage1="", errorMessage2="", deleteSql="";	
      
	  // Delete all the existing Methods
	  try 
	  {
         deleteSql = "delete from return_type_value_sequence_diagram_1 ";
         deleteSql = deleteSql + "where pc_id = ? ";

	     PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the existing Return Type Value Sequence Diagram from the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	   
   }    
   
   public int getReturnType(String returnTypeName) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="";
      int returnTypeId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
      // Obtain the parent Id of the previous method
	  try
	  {
	     // SQL Statements      
		 selectSql = "select rt_id returnTypeId ";
		 selectSql = selectSql + "from return_type rt ";
		 selectSql = selectSql + "where rt.rt_name = ?";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setString(1, returnTypeName);

		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
			 returnTypeId = selectResults.getInt("returnTypeId");
		 }
		 
	  }	
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while getting the Return Type Identifier for the Return Type Name: " + returnTypeName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
	
      return returnTypeId;
	   
   }  

   public DataType getPrimitiveClassDetails(int projectId, String dataTypeName) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="", 
    		 packageName="", returnType1="";
   
      int dataTypeId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  DataType dataType=null;
	  
      // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
		 selectSql = "select dt.dt_id dataTypeId, ";
		 selectSql = selectSql + "dt_package_name dataTypePackageName, ";
		 selectSql = selectSql + "dt.dt_category dataTypeCategory ";
		 selectSql = selectSql + "from data_type dt, ";
		 selectSql = selectSql + "project_configuration pc ";
		 selectSql = selectSql + "where pc.pc_id = ? and ";
		 selectSql = selectSql + "dt.dt_name = ? and ";
		 selectSql = selectSql + "pc.plh_id = dt.pl_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, dataTypeName);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    dataTypeId  = selectResults.getInt("dataTypeId");
	        packageName = selectResults.getString("dataTypePackageName");
	        returnType1 = selectResults.getString("dataTypeCategory");
	        
	        dataType = new DataType (packageName, dataTypeId, dataTypeName, returnType1);
		 }
		 
	    }	
	    catch(Exception e1)
	    {
	       errorMessage1 = e1.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while getting the Package Name for" + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Project Identifier: " + projectId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Data Type Name: " + dataTypeName + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	       throw new Exception(errorMessage2);
	    }	

      return dataType;	    
	   
   }

   public int getDataTypeId(int projectId, String dataTypeName) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="";
   
      int dataTypeId=0;
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  
      // Obtain the proper UserName that are in the modified lineNumber
	  try
	  {
	     // SQL Statements      
		 selectSql = "select dt.dt_id dataTypeId ";
		 selectSql = selectSql + "from data_type dt, ";
		 selectSql = selectSql + "project_configuration pc ";
		 selectSql = selectSql + "where dt.dt_name = ? and ";
		 selectSql = selectSql + "pc.pc_id = ? and ";
		 selectSql = selectSql + "pc.plh_id = dt.pl_id";

		 selectStatement = dbCon.prepareStatement(selectSql);
		 selectStatement.setInt(1, projectId);
		 selectStatement.setString(2, dataTypeName);
		 selectResults = selectStatement.executeQuery();
		  
		 while(selectResults.next()) 
		 {
		    dataTypeId  = selectResults.getInt("dataTypeId");
		 }
		 
	    }	
	    catch(Exception e1)
	    {
		   errorMessage1 = e1.getMessage();
		   errorMessage2 = "Error XXXX: Ocurred while getting the Data Type Id for" + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Project Identifier: " + projectId + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Data Type Name: " + dataTypeName + System.lineSeparator();
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		   throw new Exception(errorMessage2);
	    }	

      return dataTypeId;	    
	   
   }   
}