package frl.model.user;

import java.io.*;
import java.sql.*;
import java.util.*;

//Package #8
//Class #2
public class UserDatabase 
{
   // Atributes for the User Database Class	
   private List<User> users;
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   // Method #1
   public UserDatabase()
   {
      users = new LinkedList<User>();
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
		 this.dbUser     = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 this.dbUrl      = prop.getProperty("dbUrl");
		 this.dbDriver   = prop.getProperty("dbDriver");

	   } 
	   catch (IOException e1) 
	   {
		  // Error #1 
	      errorMessage1 = e1.getMessage();
		  errorMessage2 = "Error 8221: Occurred while loading the Database Configuration File. \n ";
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
				// Error #2 
			    errorMessage1 = e2.getMessage();
				errorMessage2 = "Error 8222: Occurred while closing the Database Configuration File. \n ";
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
	  return;

      // Get the database configuration parameters
      try
      {
         configureDBParameters(databaseConfigFile);
      }
      catch(Exception e1)
      {
 		 // Error #1
 		 errorMessage2 = e1.getMessage();
 	     throw new Exception(errorMessage2);  
      }
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e2) 
	  {
		 // Error #2
		 errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error 8231: The Database Driver was not found. \n";
		 errorMessage2 = errorMessage2 + "Error Message: "+errorMessage1;
	     throw new Exception(errorMessage2);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
   }
	
   // Disconnect to the FRL Database
   // Method #4
   public void disconnect() throws Exception
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
	    	 // Error #1 
	        errorMessage1 = e.getMessage();
		    errorMessage2 = "Error 8241: The Database Connection cannot be closed. \n";
		    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		    throw new Exception(errorMessage2);
		 }
	  }
		
	   dbCon = null;
   }
   
   // Method #5
   public boolean validateUser(User user) throws Exception
   {
       String userName = "", password, selectSql1, errorMessage1="", errorMessage2="";
	   int count;
	   boolean validUser;
	   PreparedStatement selectStatement1;
	   ResultSet selectResults1;

	   try
	   {
	      // Get the values from the GUI
          userName = user.getUserName();	
	      password = user.getPassword();
	      validUser = false; 
	   
          // SQL Statements
	      selectSql1 = "select count(us_username) count ";
	      selectSql1 = selectSql1 + "from user where us_username=? ";
	      selectSql1 = selectSql1 + "and us_password = AES_ENCRYPT(?, us_key_string)";

	      selectStatement1 = dbCon.prepareStatement(selectSql1);
	      selectStatement1.setString(1, userName);
	      selectStatement1.setString(2, password);
	      selectResults1 = selectStatement1.executeQuery();
	      selectResults1.next();
	      
	      count = selectResults1.getInt(1);

	      // Validate if the user and password exists in the FRL Database
	      if (count == 1)
	         validUser = true;	 
	      else
	         validUser = false;   
	      
	     
	      selectResults1.close();
	      selectStatement1.close();
	   }
	   catch(Exception e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error 8241: Occurred while validating a User Name: " + userName + "\n";
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	   
	   return validUser;
	} 
	
   // Method #6
   public UserLevel getUserLevel(User user) throws Exception
   {
      String errorMessage1="", errorMessage2="";
      String userName = "", password, selectSql1, userLevelStr;
	  UserLevel userLevel;
	  PreparedStatement selectStatement1;
	  ResultSet selectResults1;
	   
	  try
	  {
	     // Get the values from the GUI
         userName  = user.getUserName();	
	     password  = user.getPassword();
	     userLevel = null; 
	   
	     // SQL Statements
	     selectSql1 = "select us_user_level ";
	     selectSql1 = selectSql1 + "from user where us_username=? ";
	     selectSql1 = selectSql1 + "and us_password = AES_ENCRYPT(?, us_key_string)";
	          		       
	     selectStatement1 = dbCon.prepareStatement(selectSql1);
	     selectStatement1.setString(1, userName);
	     selectStatement1.setString(2, password);
	     selectResults1 = selectStatement1.executeQuery();
	   
	     // Validate if there are results in the query
	     if (!selectResults1.next() ) 
		    userLevel = null; 
	      else
	      {	   
	         userLevelStr = selectResults1.getString(1);
	         userLevel = UserLevel.valueOf(userLevelStr);
	      }
	   
	      selectResults1.close();
	      selectStatement1.close();
	   }
	   catch(Exception e)
	   {
	      errorMessage1 = e.getMessage();
		  errorMessage2 = "Error 8241: Occurred while loading the User Level of the User Name: " + userName + "\n";
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
	      
	   return userLevel;
   }	
   
   // Method #7	
   public void save(User user) throws Exception 
   {
	   // Get the values from the GUI
	   int userId = 0, count, col;
	   String userName, password, selectSql1, insertSql, updateSql;
	   String errorMessage1="", errorMessage2="", userIdStr="";
	   UserLevel userLevel;
	   PreparedStatement selectStatement1, insertStatement, updateStatement;
	   ResultSet selectResult1;
		
	   try
	   {
	      // Get the values from the GUI
	      userId    = user.getId();
	      userName  = user.getUserName();
	      password  = user.getPassword();
	      userLevel = user.getUserLevel();
	      
		   // Convert integer to string
		   userIdStr = String.valueOf(userId);
	   
	      // SQL Statements
	      selectSql1 = "select count(*) as count from user where us_user_id=?";
	      selectStatement1 = dbCon.prepareStatement(selectSql1);
	      selectStatement1.setInt(1, userId);
	      selectResult1 = selectStatement1.executeQuery();
	      selectResult1.next();
	      count = selectResult1.getInt(1);

	      selectStatement1.close();
	   }
	   catch(Exception e1)
	   {
	      errorMessage1 = e1.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while counting the Users with the Identifier" + userIdStr +"\n";
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  throw new Exception(errorMessage2);
	   }
			    
	   // Validate if the user already exists or not
	   if (count == 0) 
       {
		  try
		  {
             //System.out.println("Inserting an User with ID: "+userId);
		     insertSql = "insert into user (us_user_id, us_username, us_password, us_key_string, us_user_level) ";
		     insertSql = insertSql + "values (?, ?, ";
		     insertSql = insertSql + "AES_ENCRYPT(?, unhex(sha2('"+ password +"',512))),";
		     insertSql = insertSql + "unhex(sha2(?, 512)), ?)";
		     insertStatement = dbCon.prepareStatement(insertSql); 

             col = 1;
		     insertStatement.setInt(col++, userId);
		     insertStatement.setString(col++, userName);
		     insertStatement.setString(col++, password);
		     insertStatement.setString(col++, password);
		     insertStatement.setString(col++, userLevel.name());
			
		     insertStatement.executeUpdate();
		     insertStatement.close();
		  }
		  catch(Exception e1)
		  {
		     errorMessage1 = e1.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while inserting a new User with Identifier:" + userId + "\n";
			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			 throw new Exception(errorMessage2);
		  }
			
	    } 
	    else 
		{
	    	try
	    	{
	 	       updateSql = "update user set us_username=?, ";
		       updateSql = updateSql + "us_password=AES_ENCRYPT(?, unhex(sha2('"+ password +"',512))),";
		       updateSql = updateSql + "us_key_string=unhex(sha2(?, 512)), ";
		       updateSql = updateSql + "us_user_level=? where us_user_id=?";
		       updateStatement = dbCon.prepareStatement(updateSql);
			
		       col = 1;

		       updateStatement.setString(col++, userName);
			   updateStatement.setString(col++, password);
			   updateStatement.setString(col++, password);
			   updateStatement.setString(col++, userLevel.name());
			   updateStatement.setInt(col++, userId);

			   updateStatement.executeUpdate();
			   updateStatement.close();
	    	}
			catch(Exception e1)
			{
			   errorMessage1 = e1.getMessage();
			   errorMessage2 = "Error XXXX: Occurred while updating an User with Identifier: " + userIdStr + "\n";
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   throw new Exception(errorMessage2);
			}	
	   }
   }
   
   // Method #8
   public void delete(User user) throws Exception 
   {
		// Get the values from the GUI
		int userId, count, col;
		String selectSql, deleteSql, errorMessage1="", errorMessage2="", userIdStr = "";
		PreparedStatement selectStatement, deleteStatement;
		ResultSet selectResult;
		
		try
		{
	       // Get the values from the GUI
		   userId = user.getId();
		   
		   // Convert integer to string
		   userIdStr = String.valueOf(userId);
		   
		   // SQL Statements
		   selectSql = "select count(*) as count from user where us_user_id=?";
		   selectStatement = dbCon.prepareStatement(selectSql);
		
		   deleteSql = "delete from user where us_user_id=?";
		   deleteStatement = dbCon.prepareStatement(deleteSql);
			
		   selectStatement.setInt(1, userId);

		   selectResult = selectStatement.executeQuery();
		   selectResult.next();

           count = selectResult.getInt(1);
            
           if (count != 0) 
           {
              col = 1;
		      //System.out.println("Deleting an User with ID: "+user_id);
		      deleteStatement.setInt(col, userId);
		      deleteStatement.executeUpdate();
		   }
            
 		   deleteStatement.close();
		   selectStatement.close();
        }
		catch(Exception e1)
		{
		   errorMessage1 = e1.getMessage();
		   errorMessage2 = "Error XXXX: Occurred while deleting an User with Identifier: " + userIdStr + "\n";
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		   throw new Exception(errorMessage2);
		}
   } 
	
   // Method #9
   public void load() throws Exception 
   {
	  int userId;
      String sql, userName, password, userLevel, errorMessage1="", errorMessage2="";
      User user;
      Statement selectStatement;
      ResultSet results;
		 
      try
      {
         users.clear();
      
         // SQL Statements
	     sql = "select u.us_user_id userId, u.us_username userName, ";
	     sql = sql + "AES_DECRYPT(u.us_password, u.us_key_string) AS userPassword, "; 
	     sql = sql + "u.us_user_level userLevel from user u ";
	     sql = sql + "order by u.us_user_id";
	  
	     selectStatement = dbCon.createStatement();
		
	     results = selectStatement.executeQuery(sql);
		
	     while(results.next()) 
	     {
	        userId    = results.getInt("userId");
	        userName  = results.getString("userName");
	        password  = results.getString("userPassword");
	        userLevel = results.getString("userLevel");	
		 
	        user = new User(userId, userName, password, password, UserLevel.valueOf(userLevel));
	        users.add(user);			
		 }
		
		 results.close();
		 selectStatement.close();
      }
      catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the Users. \n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
      
   }

   // Method #10
   public void loadUser(int userId) throws Exception
   {
      String selectSql, errorMessage1="", errorMessage2="",
    		 userName, password, userLevel, userIdStr="";
      PreparedStatement selectStatement;
      ResultSet selectResults;

	  User user;

	  try
	  {
	     // SQL Statements 
	     selectSql = "select u.us_user_id userId, u.us_username userName, ";
	     selectSql = selectSql + "AES_DECRYPT(u.us_password, u.us_key_string) AS userPassword, "; 
	     selectSql = selectSql + "u.us_key_string userKeyString, ";
	     selectSql = selectSql + "u.us_user_level userLevel from user u ";
	     selectSql= selectSql + "where us_user_id=?"; 
	  
	     selectStatement = dbCon.prepareStatement(selectSql);
	     selectStatement.setInt(1, userId);

	     selectResults = selectStatement.executeQuery();
	     selectResults.next();

	     userName  = selectResults.getString("userName");
	     password  = selectResults.getString("userPassword");
	     userLevel = selectResults.getString("userLevel");
	     
	     userIdStr = String.valueOf(userId);
	  			
	     user = new User(userId, userName, password, password, UserLevel.valueOf(userLevel));
				
	     users.add(user);
							
	     selectResults.close();
	     selectStatement.close();
	  }
	  catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the User with Id: " + userIdStr + "\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	
   }
   
   // Add a new user
   // Method #11
   public void addUser(User user) 
   {
      users.add(user); 
   }
   
   // Get a user
   // Method #12
   public List<User> getUsers()
   {
	   return Collections.unmodifiableList(users);
   }
   
   
   // Delete an user
   // Method #13
   public void deleteUser(int id)
   {
	   users.remove(id);
   }
   
   // Method #14
   public boolean validateUserName(User user) throws Exception
   {
	  int userId1, userId2, userNameExists=0;
	  String userName1 = "", selectSql1, selectSql2, errorMessage1="", errorMessage2="";
	  boolean validUserName;
	  PreparedStatement selectStatement1, selectStatement2;
	  ResultSet selectResults1, selectResults2;
	  
	  try
	  {
	     // Get the values from the GUI
	     userId1   = user.getId();
	     userName1 = user.getUserName();
         validUserName = false;
	   
	     // SQL Statements
	     selectSql1 = "select count(us_user_id) as count from user where us_username=?";
	     selectStatement1 = dbCon.prepareStatement(selectSql1);
	   
	     // Set the UserName from the GUI
	     selectStatement1.setString(1, userName1);
	     selectResults1 = selectStatement1.executeQuery();
	     selectResults1.next();
	     userNameExists = selectResults1.getInt("count");
	     selectResults1.close();
		 selectStatement1.close();
	  }
      catch(Exception e)
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while counting the existing Users with the UserName: " + userName1 +"\n";
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	   
	  // Validate if the UserName already exist for an existing User
	  if (userNameExists == 1)
	  {
		 try
		 {
	     selectSql2 = "select us_user_id from user where us_username=?";
		 selectStatement2 = dbCon.prepareStatement(selectSql2);
		   
		 selectStatement2.setString(1, userName1);
		 selectResults2 = selectStatement2.executeQuery();
		 selectResults2.next();
		 userId2 = selectResults2.getInt("us_user_id");
		 
		 // Validate if the user_id already has an associated user in the Forensic Ready Logger database
		 if (userId1 == userId2)
		    validUserName = true;	 
		 else
	        validUserName = false;
		   
		 selectResults2.close();
		 selectStatement2.close();
		 }
	     catch(Exception e)
		 {
		    errorMessage1 = e.getMessage();
			errorMessage2 = "Error XXXX: Occurred while loading the User Identifier of the UserName: " + userName1 + "\n";
			errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			throw new Exception(errorMessage2);
	     }
	   
	   }
	   else
	      validUserName = true;	 
		   
	   return validUserName;
   } 
	
}
