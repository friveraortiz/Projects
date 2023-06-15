package frl.model.tree;

import java.io.File;
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
import java.util.regex.Pattern;

import frl.model.configuration.ClassMethod;
import frl.model.configuration.FRLConfiguration;

public class TreeStructureDatabase
{
	
   // Attributes for the ForensicReadyLoggerDatabase 
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;

   // Constructor of the Class
   public TreeStructureDatabase() //1
   {
     
   }
	   
   // Methods of the Class
   private void configureDBParameters(String propFileName) throws Exception
   {
	  String errorMessage1="", errorMessage2="";  
      Properties prop   = new Properties();
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
		 this.dbUrl = prop.getProperty("dbUrl");
		 this.dbDriver = prop.getProperty("dbDriver");

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
			   errorMessage2 = "Error XXXX: Occurred while closing the Database Configuration File. \n";
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
	  catch (ClassNotFoundException e1) 
	  {
	     errorMessage1 = e1.getMessage();
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

   public void deleteTreeStructure(int projectId) throws Exception 
   {
      String deleteSql, errorMessage1="", errorMessage2="";
	  PreparedStatement deleteStatement;
       
	  try
	  {
	     deleteSql = "delete from tree_structure ";
	     deleteSql = deleteSql + "where pc_id = ? and ";
	     deleteSql = deleteSql + "ts_id >= 0 ";
	     deleteStatement = dbCon.prepareStatement(deleteSql);
	     deleteStatement.setInt(1, projectId);
	    
	     deleteStatement.executeUpdate();
	     deleteStatement.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting all the Tree Structures. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }	 
   } 
	
   public int getLastIdTreeStructure(int projectId) throws Exception
   {
      String selectSql1, errorMessage1="", errorMessage2="";	
	  int treeStrucId;	
	  PreparedStatement selectStatement1;
	  ResultSet selectResults1;
	   
	  try
	  {
	     // SQL Statements
	     selectSql1 = "select max(ts_id) lastId ";
	     selectSql1 = selectSql1 + "from tree_structure ";
	     selectSql1 = selectSql1 + "where pc_id = ? ";
	   
	     selectStatement1 = dbCon.prepareStatement(selectSql1);
	     selectStatement1.setInt(1, projectId);
	     selectResults1 = selectStatement1.executeQuery();
	   
	     // Validate there are results in the query
	     if (selectResults1.next()) 
	        treeStrucId = selectResults1.getInt("lastId");
	     else 
	        treeStrucId = 0;
								
         selectResults1.close();
	     selectStatement1.close();
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Last Identifier of the Tree Structures. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	   
      return treeStrucId;
	   
   }
	
   public int getSpecificIdTreeStructure(String name, int projectId) throws Exception
   {
      String selectSql1, errorMessage1="", errorMessage2="";	
	  int treeStrucId;
	  PreparedStatement selectStatement1;
	  ResultSet selectResults1;
		
	  try
	  {
	     // SQL Statements
	     selectSql1 = "select ts_id id from tree_structure ";
	     selectSql1 = selectSql1 + "where pc_id = ? and ";
	     selectSql1 = selectSql1 + "ts_name = ? ";
	     selectStatement1 = dbCon.prepareStatement(selectSql1);
      
	     selectStatement1.setInt(1, projectId);
         selectStatement1.setString(1, name);
         
	     selectResults1 = selectStatement1.executeQuery();
	   
	     // Validate there are results in the query
	     if (selectResults1.next()) 
	        treeStrucId = selectResults1.getInt("id");
	     else 
	        treeStrucId = -1;
	   
         selectResults1.close();
	     selectStatement1.close();
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Identifier from the Tree Structures with the Name: "+ name +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	   
      return treeStrucId;
       
   }
	
   public void saveTreeStructure(TreeStructure treeStructure) throws Exception 
   {
      String name="", insertSql="", errorMessage1="", errorMessage2="";
	  int projectId=0, id=0, parentId=0, level=0, cmId=0, col=0;
	  NodeType nodeType;
	  PreparedStatement insertStatement;
	   
	  try
	  {
	     // Get the values from the GUI
		 projectId = treeStructure.getProjectId();
	     id        = treeStructure.getId();
	     name      = treeStructure.getName();	
	     nodeType  = treeStructure.getNodeType();
	     parentId  = treeStructure.getParentId();	
	     level     = treeStructure.getLevel();
	     cmId      = treeStructure.getCmId();
		
	     // Build the insert statement
	     insertSql = "insert into tree_structure (pc_id, ts_id, ts_name, ts_node_type, ts_parent_id, ts_level, cm_id) ";
	     insertSql = insertSql + "values (?, ?, ?, ?, ?, ?, ?)";
	     insertStatement = dbCon.prepareStatement(insertSql);
		
	     col = 1;
	     insertStatement.setInt(col++, projectId);
	     insertStatement.setInt(col++, id);
	     insertStatement.setString(col++, name);
	     insertStatement.setString(col++, nodeType.name());
	     insertStatement.setInt(col++, parentId);
	     insertStatement.setInt(col++, level);
	     insertStatement.setInt(col++, cmId);
						
	     insertStatement.executeUpdate();
	     insertStatement.close();
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while saving a new Tree Structure with the Identifier: " + id + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      } 

   }
	
   public ArrayList<TreeStructure> loadTreeStructure1(int projectId) throws Exception
   {
		
      String name1, nodeTypeStr, selectSql1, errorMessage1="", errorMessage2="";
      int parentId, level, id = 0;
      NodeType nodeType;
      TreeStructure treeStructure;
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();
      PreparedStatement selectStatement1;
      ResultSet selectResults1;
      
      try
      {
  	     // SQL Statement
	     selectSql1 = "select ts.ts_id, ";
	     selectSql1 = selectSql1 +"ts.ts_name ,";
	     selectSql1 = selectSql1 +"ts.ts_node_type, ";
	     selectSql1 = selectSql1 +"ts.ts_parent_id, ";
	     selectSql1 = selectSql1 +"ts.ts_level ";
	     selectSql1 = selectSql1 + "from tree_structure ts ";
	     selectSql1 = selectSql1 + "where pc_id = ? ";
	     selectSql1 = selectSql1 + "order by ts.ts_id";

	     selectStatement1 = dbCon.prepareStatement(selectSql1);
	     selectStatement1.setInt(1, projectId);
	     selectResults1 = selectStatement1.executeQuery();
	   
	     // Validate there are results in the query
	     while(selectResults1.next()) 
	     {	  
	    	 id          = selectResults1.getInt("ts_id");
	    	 name1       = selectResults1.getString("ts_name");
	    	 nodeTypeStr = selectResults1.getString("ts_node_type");
	     
	    	 // Convert String to Enum
	    	 nodeType    = NodeType.valueOf(nodeTypeStr);
	    	 parentId    = selectResults1.getInt("ts_parent_id");
	    	 level       = selectResults1.getInt("ts_level");
	     
	    	 treeStructure = new TreeStructure(projectId, id, name1, nodeType, parentId, level);
	    	 treeStructures.add(treeStructure);
	     }
     
	     selectResults1.close();
	     selectStatement1.close();
      }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the details of the Tree Structure with the Identifier: " + id + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	  
	  return treeStructures;
       
   }
	
   public ArrayList<TreeStructure> loadTreeStructure2(int projectId) throws Exception
   {
		
      String name1, name2, nodeTypeStr, selectSql1, selectSql2, errorMessage1="", errorMessage2="";
      int parentId, level, id = 0;
      NodeType nodeType;
      TreeStructure treeStructure;
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();
      PreparedStatement selectStatement1, selectStatement2;
      ResultSet selectResults1, selectResults2;
      
      try
      {
         // SQL Statement #1
         selectSql1 = "select ts.ts_id, "; 
         selectSql1 = selectSql1 +"ts.ts_name, "; 
         selectSql1 = selectSql1 +"ts.ts_node_type, ";
         selectSql1 = selectSql1 +"ts.ts_parent_id, "; 
         selectSql1 = selectSql1 +"ts.ts_level ,";
         selectSql1 = selectSql1 +"ts.ts_name as ts_name_2 ";
         selectSql1 = selectSql1 +"from tree_structure ts ";
         selectSql1 = selectSql1 +"where pc_id = ? and ";
         selectSql1 = selectSql1 +"ts.ts_id = 0 ";
         selectSql1 = selectSql1 +"order by ts.ts_id ";
      
         selectStatement1 = dbCon.prepareStatement(selectSql1);
         selectStatement1.setInt(1, projectId);
         selectResults1 = selectStatement1.executeQuery();
	   
         // Validate there are results in the query
         while(selectResults1.next()) 
         {	  
        	 id          = selectResults1.getInt("ts_id");
        	 name1       = selectResults1.getString("ts_name");
        	 nodeTypeStr = selectResults1.getString("ts_node_type");
	     
        	 // Convert String to Enum
        	 nodeType    = NodeType.valueOf(nodeTypeStr);
        	 parentId    = selectResults1.getInt("ts_parent_id");
        	 level       = selectResults1.getInt("ts_level");
        	 name2       = selectResults1.getString("ts_name_2");
	     
        	 treeStructure = new TreeStructure(projectId, id, name1, nodeType, parentId, level, name2);
        	 treeStructures.add(treeStructure);
         
         }
         selectResults1.close();
         selectStatement1.close();
      }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the details of the Tree Structure with the Identifier: " + id + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	  
      try
      {
	     // SQL Statement #2
    	 selectSql2 = "select ts.ts_id, "; 
         selectSql2 = selectSql2 +"ts.ts_name, ";
         selectSql2 = selectSql2 +"ts.ts_node_type, ";
         selectSql2 = selectSql2 +"ts.ts_parent_id, "; 
         selectSql2 = selectSql2 +"ts.ts_level, ";
         selectSql2 = selectSql2 +"concat(cm.cm_package_name, '.', cm.cm_class_name, '.', cm.cm_short_method_name) as ts_name_2 "; 
         selectSql2 = selectSql2 +"from tree_structure ts, ";
         selectSql2 = selectSql2 +"class_method cm ";
         selectSql2 = selectSql2 +"where ts.pc_id = ? and ";
         selectSql2 = selectSql2 +"ts.ts_id > 0 and ";
         selectSql2 = selectSql2 +"ts.pc_id = cm.pc_id and ";
         selectSql2 = selectSql2 +"ts.cm_id = cm.cm_id ";
         selectSql2 = selectSql2 +"order by ts.ts_id ";
      
	     selectStatement2 = dbCon.prepareStatement(selectSql2);
	     selectStatement2.setInt(1, projectId);
	     selectResults2   = selectStatement2.executeQuery();
	   
	     // Validate there are results in the query
	     while(selectResults2.next()) 
	     {	  
	        id          = selectResults2.getInt("ts_id");
	        name1       = selectResults2.getString("ts_name");
	        nodeTypeStr = selectResults2.getString("ts_node_type");
	     
	        // Convert String to Enum
	        nodeType    = NodeType.valueOf(nodeTypeStr);
	        parentId    = selectResults2.getInt("ts_parent_id");
	        level       = selectResults2.getInt("ts_level");
	        name2       = selectResults2.getString("ts_name_2");
	     
	        treeStructure = new TreeStructure(projectId, id, name1, nodeType, parentId, level, name2);
	        treeStructures.add(treeStructure);
         
	     }
	  
	     selectResults2.close();
	     selectStatement2.close();
      }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the details of the Tree Structures and the Methods. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	  
	  return treeStructures;

   }
   
   public int getClassMethodId(String packageName, String className, String textFileName, int projectId, FRLConfiguration frlCon) throws Exception
   {
	  String errorMessage1="", errorMessage2="", textFileName1="", selectSql="";
	  int cmId = 0;
      PreparedStatement selectStatement;
	  ResultSet selectResults;  
	  
	  //textFileName = "%" + textFileName;
	  
	  ///Users/fannyriveraortiz/eclipse-workspace/Directories/Output/HRMApplication/gui/UserFormEvent/getId.txt
	  
	  textFileName1 = frlCon.projectOutputDir + File.separator + 
			          frlCon.projectName      + File.separator + 
			          packageName             + File.separator + 
			          className               + File.separator + 
			          textFileName;
	  
	  if(textFileName1.endsWith(".txt") == false)
	     textFileName1 = textFileName1 + ".txt";
		  
	  try
	  {
	     // SQL Statement
	     selectSql = "select cm.cm_id id ";
	     selectSql = selectSql +"from class_method cm ";
	     selectSql = selectSql +"where cm.pc_id = ? and ";
	     selectSql = selectSql +"cm.cm_package_name = ? and ";		  
	     selectSql = selectSql + "cm.cm_class_name = ? and ";
	     selectSql = selectSql + "cm.cm_text_filename = ?";
		  
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setString(2, packageName);
	     selectStatement.setString(3, className);
	     selectStatement.setString(4, textFileName1);
		  
	     selectResults = selectStatement.executeQuery();
		  	   
	     // Validate there are results in the query
	     if (selectResults.next()) 
	        cmId = selectResults.getInt("id");
		  
	     selectResults.close();
	     selectStatement.close();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Identifier from the Methods with the Text File: " + textFileName + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	  
	  return cmId;
   }
   
   public void updateTreeStructure(FRLConfiguration frlCon, int projectId) throws Exception
   {

      ArrayList<TreeStructure> treeStructure = new ArrayList<TreeStructure>();
      int c, col, id = 0, cmId;
      String updateSql, name, packageName = "", errorMessage1="", errorMessage2="";
      String className = "", textFileName ="";
      String[] parts;
      PreparedStatement updateStatement;

      try
      {
         // Call the procedure to load the tree Structures from the Database
         treeStructure = loadTreeStructure1(projectId);
      
         // Prepare the Update Statement
         updateSql = "update tree_structure ts ";
         updateSql = updateSql + "set ts.cm_id = ? ";
         updateSql = updateSql + "where ts.pc_id = ? and ";
	     updateSql = updateSql + "ts.ts_id = ?";
	    
	     updateStatement = dbCon.prepareStatement(updateSql);
	  	    
	     // Loop for every tree structure
         for (c = 0; c < treeStructure.size(); c++) 
         { 		      
    	    // Get the fields from the Tree_Structure ArrayList
	        id    = treeStructure.get(c).getId();
	        name  = treeStructure.get(c).getName();
	        
	        // Split the name field into 3 parts
	        parts = name.split(Pattern.quote(File.separator));
	        
	        if(id >= 1)
	        {
	        	packageName  = parts[0]; 
	        	className    = parts[1];
	        	textFileName = parts[2];
	        	
	        	cmId = getClassMethodId(packageName, className, textFileName, projectId, frlCon);
	        	
	        	// Add additional details to the SQL statement
	        	col = 1;
	        	updateStatement.setInt(col++, cmId);
	        	updateStatement.setInt(col++, projectId);
	        	updateStatement.setInt(col++, id);
	  	  
	        	updateStatement.executeUpdate();
	        }
	  
         }
      
	     updateStatement.close();
      }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while updating the Tree Structure with the Identifier: " + id + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	  
   }
 
   
   public ArrayList<String> getLibNonPrimRetType(String programmingLanguage, int projectId) throws Exception
   {
      String selectSql1, library, errorMessage1="", errorMessage2="";
	  ArrayList<String> libraries = new ArrayList<>();
	  PreparedStatement selectStatement1;
	  ResultSet selectResults1;
	   
	  try
	  {
	     // SQL Statements
		 selectSql1 = "select distinct rt.rt_library ";
		 selectSql1 = selectSql1 + "from tree_structure ts, class_method cm, ";
		 selectSql1 = selectSql1 + "return_type rt, programming_language_header plh ";
		 selectSql1 = selectSql1 + "where ts.pc_id = ? and ";
		 selectSql1 = selectSql1 + "plh.plh_name = ? and ";
		 selectSql1 = selectSql1 + "rt.rt_library <> 'None' and ";
		 selectSql1 = selectSql1 + "rt.rt_type = 'NonPrimitive' and ";
		 selectSql1 = selectSql1 + "ts.pc_id = cm.pc_id and ";
		 selectSql1 = selectSql1 + "ts.cm_id = cm.cm_id and ";
		 selectSql1 = selectSql1 + "cm.cm_return_type_1  = rt.rt_name and ";
		 selectSql1 = selectSql1 + "rt.pl_id = plh.plh_id";
	   
	     selectStatement1 = dbCon.prepareStatement(selectSql1);
	     selectStatement1.setInt(1, projectId);
         selectStatement1.setString(2, programmingLanguage);
	     selectResults1 = selectStatement1.executeQuery();
	   
	     // Validate there are results in the query
	     while (selectResults1.next()) 
	     {	   
	        library     = selectResults1.getString("rt_library");
	        libraries.add(library);
	     }
	   
         selectResults1.close();
	     selectStatement1.close();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Libraries. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  } 
	   
      return libraries;
   }
   
   public ArrayList<ClassMethod> loadMethods(int projectId, int DBMethodFlag) throws Exception //5
   {
      String sql="", packageName="", className="", shortMethodName="", fullMethodName="",
    		 errorMessage1="", errorMessage2=""; 
	  
	  ClassMethod classMethodRecord;
	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
	  
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select distinct cm.cm_package_name packageName, ";
	     sql = sql + "cm.cm_class_name className, ";
	     sql = sql + "cm.cm_short_method_name shortName, ";
	     sql = sql + "concat(cm.cm_package_name,'/', cm.cm_class_name, '/',cm.cm_short_method_name) fullName ";
	     sql = sql + "from class_method cm ";
	     sql = sql + "where cm.pc_id = ? and ";
	     sql = sql + "cm_database_method = ?";
		  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectStatement.setInt(2, DBMethodFlag);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {
		    packageName     = selectResults.getString("packageName");
		    className       = selectResults.getString("className");
		    shortMethodName = selectResults.getString("shortName");
		    fullMethodName  = selectResults.getString("fullName");

		    classMethodRecord = new ClassMethod(projectId, packageName, className, 
		    		                            shortMethodName, fullMethodName);
		 
	        // Add fields to the ArrayList
		    classMethodList.add(classMethodRecord); 
							
      }
			
	     selectResults.close();
	     selectStatement.close();
	  
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Methods from the Project Identifier: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "which have the Flag: " + DBMethodFlag  + " related to perform Data Operations" + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      return classMethodList;
   }   
   
   public void createTreeStructureDBClasses(int projectId, String projectInputDir) throws Exception //5
   {
      String sql="", packageName="", className="", shortMethodName="", 
    		 fullMethodName="", errorMessage1="", errorMessage2=""; 
      int id=0, c=-1, parentId=0, level=0;
      NodeType nodeType;
	  
      TreeStructure treeStructure;
      
	  PreparedStatement selectStatement;
	  ResultSet selectResults;
	  
	  try
	  {
	     // SQL Statements      
	     sql = "select distinct cm.cm_id id, ";
	     sql = sql + "cm.cm_package_name packageName, ";
	     sql = sql + "cm.cm_class_name className, ";
	     sql = sql + "cm.cm_short_method_name shortMethodName ";
	     sql = sql + "from class_method cm ";
	     sql = sql + "where cm.pc_id = ? and ";
	     sql = sql + "cm_database_method = 1";
		  
	     selectStatement = dbCon.prepareStatement(sql);
	     selectStatement.setInt(1, projectId);
	     selectResults = selectStatement.executeQuery();
	  
	     while(selectResults.next()) 
	     {	 
	        id              = selectResults.getInt("id");	 
		    packageName     = selectResults.getString("packageName");
		    className       = selectResults.getString("className");
		    shortMethodName = selectResults.getString("shortMethodName");
		    
		    // Build the full Method Name field
		    fullMethodName = packageName + "/" + className + shortMethodName;
		    
		    // Increase the counter
		    c++;
		    
		    // Assign the values for the nodeType and parentId fields
		    if(c == 0)
		    {	
		       fullMethodName = projectInputDir;
		       nodeType       = NodeType.Root;
		       parentId       = -1;
		       level          = 0;
		    }   
		    else
		    {	
		       nodeType = NodeType.Node;
		       parentId = 0;
		       level    = 1;
		    }
		    
		    // Build a new record of a TreeStructure class
		    treeStructure = new TreeStructure(projectId, c, fullMethodName, nodeType, parentId, level, id);
		    
		    // Save this record into the tree table
		    try
		    {
		       saveTreeStructure(treeStructure);
		    }
		    catch(Exception e1)
			{
		       errorMessage1 = e1.getMessage();	
		       throw new Exception(errorMessage1);
			}
							
      }
			
	  selectResults.close();
	  selectStatement.close();
	  
	  }
	  catch(Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while inserting the Tree Structure of the Methods that have Classes that perform Database Operations." + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId  + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  

   }    
   
}
