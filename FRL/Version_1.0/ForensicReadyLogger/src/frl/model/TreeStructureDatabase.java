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

import frl.configuration.FRLConfiguration;


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
		     System.out.println("Error XXXX: Occurred because the Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("Forensic Ready Logger Database Disconnected");
	 }

	public void deleteTreeStructure() throws SQLException 
	{

       String deleteSql = "delete from tree_structure ";
	   deleteSql = deleteSql + "where ts_id >= 0 ";
	   PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	    
        //System.out.println("Delete all the records of the Tree Structure ");

	   deleteStatement.executeUpdate();
	   deleteStatement.close();
	} 
	
	public int getLastIdTreeStructure() throws SQLException
	{
	   int treeStrucId;	
	   
	   // SQL Statements
	   String selectSql1 = "select max(ts_id) lastId from tree_structure ";
	   
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   
	   // Validate there are results in the query
	   if (selectResults1.next()) 
	      treeStrucId = selectResults1.getInt("lastId");
	   else 
	      treeStrucId = 0;
								
       selectResults1.close();
	   selectStatement1.close();
	   
       return treeStrucId;
	   
	}
	
	public int getSpecificIdTreeStructure(String name) throws SQLException
	{
		
	   int treeStrucId;
		
	   // SQL Statements
	   String selectSql1 = "select ts_id from tree_structure ";
	   selectSql1 = selectSql1 + "where ts_name = ?";
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
      
       selectStatement1.setString(1, name);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   
	   // Validate there are results in the query
	   if (selectResults1.next()) 
	      treeStrucId = selectResults1.getInt("ts_id");
	   else 
	      treeStrucId = -1;
	   
       selectResults1.close();
	   selectStatement1.close();
	   
       return treeStrucId;
       
	}
	
	
	public void saveTreeStructure(TreeStructure treeStructure) throws SQLException 
	{
		
	   // Get the values from the GUI
	   int id        = treeStructure.getId();
	   String name   = treeStructure.getName();	
	   NodeType nodeType = treeStructure.getNodeType();
	   int parentId  = treeStructure.getParentId();	
	   int level     = treeStructure.getLevel();
	   int col;
		
	   String insertSql;
	   PreparedStatement insertStatement;
		
	   // Build the insert statement
	   insertSql = "insert into tree_structure (ts_id, ts_name, ts_node_type, ts_parent_id, ts_level) ";
	   insertSql = insertSql + "values (?, ?, ?, ?, ?)";
	   insertStatement = dbCon.prepareStatement(insertSql);
		
	   col = 1;
	   insertStatement.setInt(col++, id);
	   insertStatement.setString(col++, name);
	   insertStatement.setString(col++, nodeType.name());
	   insertStatement.setInt(col++, parentId);
	   insertStatement.setInt(col++, level);
						
	   insertStatement.executeUpdate();
	   insertStatement.close();

   }
	
	
   public ArrayList<TreeStructure> loadTreeStructure1() throws SQLException
   {
		
      String name1, nodeTypeStr, selectSql1;
      int parentId, level, id;
      NodeType nodeType;
      TreeStructure treeStructure;
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();
      PreparedStatement selectStatement1;
      ResultSet selectResults1;
      
  	  // SQL Statement
      
	  selectSql1 = "select ts.ts_id, ";
	  selectSql1 = selectSql1 +"ts.ts_name ,";
	  selectSql1 = selectSql1 +"ts.ts_node_type, ";
	  selectSql1 = selectSql1 +"ts.ts_parent_id, ";
	  selectSql1 = selectSql1 +"ts.ts_level ";
	  selectSql1 = selectSql1 + "from tree_structure ts ";
	  selectSql1 = selectSql1 + "order by ts.ts_id";

	  selectStatement1 = dbCon.prepareStatement(selectSql1);
	  
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
	     
	     treeStructure = new TreeStructure(id, name1, nodeType, parentId, level);
	     treeStructures.add(treeStructure);
         
	  }
     
	  selectResults1.close();
	  selectStatement1.close();
	  
	  return treeStructures;
       
   }
	
   public ArrayList<TreeStructure> loadTreeStructure2() throws SQLException
   {
		
      String name1, name2, nodeTypeStr, selectSql1, selectSql2;
      int parentId, level, id;
      NodeType nodeType;
      TreeStructure treeStructure;
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();
      PreparedStatement selectStatement1, selectStatement2;
      ResultSet selectResults1, selectResults2;
      
      // SQL Statement #1
      selectSql1 = "select ts.ts_id, "; 
      selectSql1 = selectSql1 +"ts.ts_name, "; 
      selectSql1 = selectSql1 +"ts.ts_node_type, ";
      selectSql1 = selectSql1 +"ts.ts_parent_id, "; 
      selectSql1 = selectSql1 +"ts.ts_level ,";
      selectSql1 = selectSql1 +"ts.ts_name as ts_name_2 ";
      selectSql1 = selectSql1 +"from tree_structure ts ";
      selectSql1 = selectSql1 +"where ts.ts_id = 0 ";
      selectSql1 = selectSql1 +"order by ts.ts_id ";
      
	  selectStatement1 = dbCon.prepareStatement(selectSql1);
	  
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
	     
	     treeStructure = new TreeStructure(id, name1, nodeType, parentId, level, name2);
	     treeStructures.add(treeStructure);
         
	  }
     
	  // SQL Statement #2
	  
      selectSql2 = "select ts.ts_id, "; 
      selectSql2 = selectSql2 +"ts.ts_name, ";
      selectSql2 = selectSql2 +"ts.ts_node_type, ";
      selectSql2 = selectSql2 +"ts.ts_parent_id, "; 
      selectSql2 = selectSql2 +"ts.ts_level, ";
      selectSql2 = selectSql2 +"concat(cm.cm_package_name, '.', cm.cm_class_name, '.', cm.cm_short_method_name) as ts_name_2 "; 
      selectSql2 = selectSql2 +"from tree_structure ts, ";
      selectSql2 = selectSql2 +"class_method cm ";
      selectSql2 = selectSql2 +"where ts.ts_id > 0 and ";
      selectSql2 = selectSql2 +"ts.cm_id = cm.cm_id ";
      selectSql2 = selectSql2 +"order by ts.ts_id ";
      
	  selectStatement2 = dbCon.prepareStatement(selectSql2);
	  
	  selectResults2 = selectStatement2.executeQuery();
	   
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
	     
	     treeStructure = new TreeStructure(id, name1, nodeType, parentId, level, name2);
	     treeStructures.add(treeStructure);
         
	  }
	  
	  selectResults1.close();
	  selectStatement1.close();
	  
      selectResults2.close();
	  selectStatement2.close();
	  
	  return treeStructures;

   }
   
    
   public int getClassMethodId(String packageName, String className, String textFileName) throws SQLException
   {
	  String selectSql="";
	  int cmId = -1;
      PreparedStatement selectStatement;
	  ResultSet selectResults;  
	  
	  // SQL Statement
	  selectSql = "select cm.cm_id ";
	  selectSql = selectSql +"from class_method cm ";
	  selectSql = selectSql +"where cm.cm_package_name = ? and ";		  
	  selectSql = selectSql + "cm.cm_class_name = ? and ";
	  selectSql = selectSql + "cm.cm_text_filename = ?";
		  
	  selectStatement = dbCon.prepareStatement(selectSql);
	     
	  selectStatement.setString(1, packageName);
	  selectStatement.setString(2, className);
	  selectStatement.setString(3, textFileName);
		  
	  selectResults = selectStatement.executeQuery();
		  	   
	  // Validate there are results in the query
	  if (selectResults.next()) 
	     cmId = selectResults.getInt("cm_id");
		  
	  selectResults.close();
	  selectStatement.close();
	  
	  return cmId;
   }
   
   public void updateTreeStructure(FRLConfiguration frlCon) throws SQLException
   {
      ArrayList<TreeStructure> treeStructure = new ArrayList<TreeStructure>();
      int c, col, id = 0, cmId;
      String updateSql, name, packageName = "";
      String className = "", textFileName ="";
      String[] parts;
      PreparedStatement updateStatement;

      // Call the procedure to load the tree Structures from the Database
      treeStructure = loadTreeStructure1();
      
      // Prepare the Update Statement
      updateSql = "update tree_structure ts ";
      updateSql = updateSql + "set ts.cm_id = ? ";
	  updateSql = updateSql + "where ts.ts_id = ?";
	    
	  updateStatement = dbCon.prepareStatement(updateSql);
	  	    
	  // Loop for every tree structure
      for (c = 0; c < treeStructure.size(); c++) 
      { 		      

    	 // Get the fields from the Tree_Structure ArrayList
	     id    = treeStructure.get(c).getId();
	     name  = treeStructure.get(c).getName();
	     
	     // Split the name field into 3 parts
	     parts = name.split(frlCon.elementsFileName);
	     
	     if(id >= 1)
	     {
	        packageName  = parts[0]; 
	        className    = parts[1];
	        textFileName = parts[2];
	        
	        cmId = getClassMethodId(packageName, className, textFileName);
	        
	        // Add additional details to the SQL statement
	  	    col = 1;
	  	    updateStatement.setInt(col++, cmId);
	  	    updateStatement.setInt(col++, id);
	  	  
	  	    updateStatement.executeUpdate();
	     }
	  
      }
      
	  updateStatement.close();

	  
   }
   
   public ArrayList<DatabaseMethod2> loadDBMethodsDetails() throws SQLException
   {
	   
      int id;
      String packageName, className, textFileName, shortMethodName;
      String returnType1, returnType2, selectSql1;
	  DatabaseMethod2 dbMetDet;
	  ArrayList<DatabaseMethod2> dbMetDets = new ArrayList<DatabaseMethod2>();
	  PreparedStatement selectStatement1;
	  ResultSet selectResults1;
	      
	  // SQL Statement
	  selectSql1 = "select distinct cm.cm_id as id, cm.cm_package_name as package_name, cm.cm_class_name as class_name, ";
	  selectSql1 = selectSql1 + "cm.cm_text_filename as text_file_name, cm.cm_short_method_name as short_method_name, ";
	  selectSql1 = selectSql1 + "cm.cm_return_type_1 as return_type_1 , ";
	  selectSql1 = selectSql1 + "cm.cm_return_type_2 as return_type_2 ";
	  selectSql1 = selectSql1 + "from tree_structure ts, class_method cm ";
	  selectSql1 = selectSql1 + "where ts.cm_id = cm.cm_id";
	  
	  selectStatement1 = dbCon.prepareStatement(selectSql1);
	      
	  selectResults1 = selectStatement1.executeQuery();
		   
	  // Validate there are results in the query
	  while(selectResults1.next()) 
	  {	  
	     id              = selectResults1.getInt("id");
		 packageName     = selectResults1.getString("package_name");
		 className       = selectResults1.getString("class_name");
		 textFileName    = selectResults1.getString("text_file_name");
		 shortMethodName = selectResults1.getString("short_method_name");
		 returnType1     = selectResults1.getString("return_type_1");
		 returnType2     = selectResults1.getString("return_type_2");
		 
		 dbMetDet = new DatabaseMethod2(id, packageName, className, 
				                        textFileName, shortMethodName, 
				                        returnType1, returnType2);
		 dbMetDets.add(dbMetDet);
	         
	  }   
		   
	  selectResults1.close();
      selectStatement1.close();
		  
      return dbMetDets;	   
	      
   }
   
	public ArrayList<String> getLibNonPrimRetType(String programmingLanguage) throws SQLException
	{
		
	   String library;
	   ArrayList<String> libraries = new ArrayList<>();
	   
		
	   // SQL Statements
	   String selectSql1 = "select distinct rt.rt_library ";
	   selectSql1 = selectSql1 + "from tree_structure ts, class_method cm, ";
	   selectSql1 = selectSql1 + "return_type rt, programming_language pl ";
	   selectSql1 = selectSql1 + "where rt.rt_library <> 'None' and ";
	   selectSql1 = selectSql1 + "rt.rt_type = 'NonPrimitive' and ";
	   selectSql1 = selectSql1 + "pl.pl_name = ? and ";
	   selectSql1 = selectSql1 + "ts.cm_id = cm.cm_id and ";
	   selectSql1 = selectSql1 + "cm.cm_return_type_1  = rt.rt_name and ";
	   selectSql1 = selectSql1 + "rt.pl_id = pl.pl_id";
	   
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
       selectStatement1.setString(1, programmingLanguage);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   
	   // Validate there are results in the query
	   while (selectResults1.next()) 
	   {	   
	      library     = selectResults1.getString("rt_library");
	      libraries.add(library);
	   }
	   
      selectResults1.close();
	  selectStatement1.close();
	   
      return libraries;
      
	}
   
	
}
