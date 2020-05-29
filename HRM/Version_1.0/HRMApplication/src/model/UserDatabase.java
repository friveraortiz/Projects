package model;

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

public class UserDatabase 
{
   // Atributes for the User Database Class	
   private List<User> users;
   private String dbUser;
   private String dbPassword;
   private String dbPort;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   public UserDatabase()
   {
      users = new LinkedList<User>();
   }
   
   // Methods of the Class
   
   public void configureDBParameters()
   {
      Properties prop = new Properties();
	  InputStream input = null;

	  try 
	  {
         // Specify the path of the configuration file
	     input = new FileInputStream("/Users/fannyriveraortiz/eclipse-workspace/HRMApplication/resources/config.properties");

	     // Load the properties file
		 prop.load(input);

		 // Get the values from the properties file
		 this.dbUser = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 this.dbPort = prop.getProperty("dbPort");
		 this.dbUrl = prop.getProperty("dbUrl");
		 this.dbDriver = prop.getProperty("dbDriver");

	   } 
	   catch (IOException ex) 
	   {
	      String errorMessage = ex.getMessage();
		  System.out.println("Error 82: Occurred while loading the Config File. Error Message: " + errorMessage);
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
				System.out.println("Error 83: Occurred while closing the Config File. Error Message: " + errorMessage);
			 }
		  }
	   }

   } 
   
   // Connect to the HRM Database
   public void connect() throws Exception 
   {

      if (dbCon != null)
	  return;

      // Get the database parameters from the config.properties file
      configureDBParameters();
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e) 
	  {
		 String errorMessage = e.getMessage(); 
	     throw new Exception("Error 84: The Database driver was not found. Error Message: "+errorMessage);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
	  //System.out.println("HRM Database Connected");
	}
	
    // Disconnect to the HRM Database
	public void disconnect() 
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
		     System.out.println("Error 85: The HRM Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("HRM Database Disconnected");
	 }
   
	public void save(User user) throws SQLException 
	{
		
	   // Get the values from the GUI
	   int user_Id = user.getId();
	   String userName = user.getUserName();
	   String password = user.getPassword();
	   String email = user.getEmail();
	   UserLevel userLevel = user.getUserLevel();
	   String employeeName = user.getEmployee();
	   int employee_Id =0;
	   
	   // SQL Statements
	   String selectSql1 = "select count(*) as count from users where user_id=?";
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   selectStatement1.setInt(1, user_Id);
	   ResultSet selectResult1 = selectStatement1.executeQuery();
	   selectResult1.next();
	   int count = selectResult1.getInt(1);
		
	   String selectSql2 = "select employee_id as employee_id from employees where full_name=?";
	   PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
	   selectStatement2.setString(1, employeeName);
	   ResultSet selectResult2 = selectStatement2.executeQuery();
	   
	   // Validate that the EmployeeName is not null
	   if (selectResult2.next())
	   {	   
		   employee_Id = selectResult2.getInt(1);
	   }   
	   else
	   {
	      String errorMessage = "The Employee: "+ employeeName + " does not exist in the Employees table";
	      System.out.println("Error 86: Occurred while getting the Supervisor Id. Error Message: " + errorMessage);
	   }
		
	   String insertSql = "insert into users (user_id, username, password, key_string, email, user_level, employee_id) ";
	   insertSql = insertSql + "values (?, ?, ";
	   insertSql = insertSql + "AES_ENCRYPT(?, unhex(sha2('"+ password +"',512))),";
	   insertSql = insertSql + "unhex(sha2(?, 512)), ?, ?, ?)";
	   PreparedStatement insertStatement = dbCon.prepareStatement(insertSql);

	   
	   String updateSql = "update users set username=?, ";
	   updateSql = updateSql + "password=AES_ENCRYPT(?, unhex(sha2('"+ password +"',512))),";
	   updateSql = updateSql + "key_string=unhex(sha2(?, 512)), ";
	   updateSql = updateSql + "email=?, user_level=?, employee_id=? where user_id=?";
	   PreparedStatement updateStatement = dbCon.prepareStatement(updateSql);
			    
	   // Validate that the employee_Id field is not empty
	   if (employee_Id != 0)
	   {	
	      // Validate if the user already exists or not
	      if (count == 0) 
          {
        	System.out.println("Inserting an User with ID: "+user_Id);

        	int col = 1;
			insertStatement.setInt(col++, user_Id);
			insertStatement.setString(col++, userName);
			insertStatement.setString(col++, password);
			insertStatement.setString(col++, password);
			insertStatement.setString(col++, email);
			insertStatement.setString(col++, userLevel.name());
			insertStatement.setInt(col++, employee_Id);
			
			insertStatement.executeUpdate();
			
		  } 
	      else 
		  {
			int col = 1;
			
			System.out.println("Updating an User with ID: "+user_Id);
			
			updateStatement.setString(col++, userName);
			updateStatement.setString(col++, password);
			updateStatement.setString(col++, password);
			updateStatement.setString(col++, email);
			updateStatement.setString(col++, userLevel.name());
			updateStatement.setInt(col++, employee_Id);
			updateStatement.setInt(col++, user_Id);

			updateStatement.executeUpdate();
				
	      }
	   }
		
	   insertStatement.close();
	   updateStatement.close();
	   selectStatement1.close();
	   selectStatement2.close();
	} 

	public void delete(User user) throws SQLException 
	{
		// Get the values from the GUI
		int user_id = user.getId();
		
		// SQL Statements
		String selectSql = "select count(*) as count from users where user_id=?";
		PreparedStatement selectStament = dbCon.prepareStatement(selectSql);
		
		String deleteSql = "delete from users where user_id=?";
		PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
			
		selectStament.setInt(1, user_id);

		ResultSet selectResult = selectStament.executeQuery();
		selectResult.next();

        int count = selectResult.getInt(1);
            
        if (count != 0) 
        {
           int col = 1;
		   System.out.println("Deleting an User with ID: "+user_id);
		   deleteStatement.setInt(col, user_id);
		   deleteStatement.executeUpdate();
		}
            
 		deleteStatement.close();
		selectStament.close();
	} 
	

   public void load() throws SQLException 
   {
      users.clear();
      
      // SQL Statements
	  String sql = "select u.user_id, u.username, ";
	  sql = sql + "AES_DECRYPT(u.password, u.key_string) AS password, "; 
	  sql = sql + "u.email, u.user_level, e.full_name as employee from users u, employees e ";
	  sql= sql + "where u.employee_id = e.employee_id order by u.user_id";
	  
	  Statement selectStatement = dbCon.createStatement();
		
	  ResultSet results = selectStatement.executeQuery(sql);
		
	  while(results.next()) 
	  {
	     int userId = results.getInt("user_id");
		 String userName = results.getString("username");
		 String password = results.getString("password");
		 String employee = results.getString("employee");
		 String email = results.getString("email");
		 String userLevel = results.getString("user_level");	
		 
		 User user = new User(userId, userName, password, password, employee, email, UserLevel.valueOf(userLevel));
		 users.add(user);
						
		}
		
		results.close();
		selectStatement.close();
   }

   	  
   public void loadUser(int userId) throws SQLException
   {

	  // SQL Statements 
	  String selectSql = "select u.user_id, u.username, ";
	  selectSql = selectSql + "AES_DECRYPT(u.password, u.key_string) AS password, "; 
	  selectSql = selectSql + "u.key_string, ";
	  selectSql = selectSql + "u.email, u.user_level, e.full_name as employee from users u, employees e ";
	  selectSql= selectSql + "where u.employee_id = e.employee_id and user_id=?"; 
	  
	  PreparedStatement selectStatement = dbCon.prepareStatement(selectSql);
	  selectStatement.setInt(1, userId);

	  ResultSet selectResults = selectStatement.executeQuery();
	  selectResults.next();

	  String userName = selectResults.getString("username");
	  String password = selectResults.getString("password");
	  String employee = selectResults.getString("employee");
	  String email = selectResults.getString("email");
	  String userLevel = selectResults.getString("user_level");
	  			
	  User user = new User(userId, userName, password, password, employee, email, UserLevel.valueOf(userLevel));
				
	  users.add(user);
							
	  selectResults.close();
	  selectStatement.close();
	
   }
   
   // Add a new user
   public void addUser(User user) 
   {
      users.add(user); 
   }
   
   // Get a user
   public List<User> getUsers()
   {
	   return Collections.unmodifiableList(users);
   }
   
   
   // Delete an user
   public void deleteUser(int id)
   {
	   users.remove(id);
   }
   
   public ArrayList<Employee> loadFullNameEmployees() throws SQLException 
   {
      // Declare ArrayList of Employee
	  ArrayList<Employee> employeeBasics = new ArrayList<Employee>();	 
	   
	  // SQL Statements
	  String sql = "select employee_id, number, full_name from employees order by full_name asc";
	  Statement selectStatement = dbCon.createStatement();
		
	  ResultSet results = selectStatement.executeQuery(sql);
		
	  while(results.next()) 
	  {
	     int employeeId = results.getInt("employee_id");
	     String number = results.getString("number");
		 String fullName = results.getString("full_name");
		 
	     Employee eB = new Employee(employeeId, number, fullName); 
	     
	     // Add comment to ArrayList.
	     employeeBasics.add(eB); 
	     
	  }
		
	  results.close();
	  selectStatement.close();
	  
      return employeeBasics;
      
   }
 
	public boolean validateEmployee(User user) throws SQLException
	{
	  
	   boolean validEmployee = false;
	   int userExists = 0;
	   
	   // Get the values from the GUI
	   int userId = user.getId();
	   String employeeName = user.getEmployee();
	   
	   // SQL Statements
	   String selectSql1 = "select employee_id as employee_id from employees where full_name=?";
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   
	   String selectSql2 = "select count(user_id) as count from users where employee_id=?";
	   PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
	   
	   // Get the Employee Id from the selected employee Name
	   selectStatement1.setString(1, employeeName);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   selectResults1.next();
	   int employeeId = selectResults1.getInt("employee_id");
	   
	   // Verify if the user already exists
	   selectStatement2.setInt(1, employeeId);
	   ResultSet selectResults2 = selectStatement2.executeQuery();
	   selectResults2.next();
	   userExists = selectResults2.getInt("count");
	   
	   if (userExists == 1)
	   {
		   String selectSql3 = "select user_id from users where employee_id=?";
		   PreparedStatement selectStatement3 = dbCon.prepareStatement(selectSql3);
		   
		   selectStatement3.setInt(1, employeeId);
		   ResultSet selectResults3 = selectStatement3.executeQuery();
		   selectResults3.next();
		   int user_Id = selectResults3.getInt("user_id");
		   
		   // Validate if the employee_id already has an associated user in the HRM database
		   if (userId == user_Id)
		      validEmployee = true;	 
		   else
			   validEmployee = false;
		   
		   selectResults3.close();
		   selectStatement3.close();
	   
	   }
	   else
		   validEmployee = true;	 
		   
	   	   
       selectResults1.close();
	   selectStatement1.close();
       selectResults2.close();
	   selectStatement2.close();
	  
	   
	   return validEmployee;
	} 
	
	public boolean validateUserName(User user) throws SQLException
	{
	  
	   boolean validUserName = false;
	   
	   // Get the values from the GUI
	   int userId = user.getId();
	   String userName = user.getUserName();
	   
	   // SQL Statements
	   String selectSql1 = "select count(user_id) as count from users where username=?";
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   
	   // Set the UserName from the GUI
	   selectStatement1.setString(1, userName);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   selectResults1.next();
	   int userNameExists = selectResults1.getInt("count");
	   
	   // Validate if the UserName already exist for an existing User
	   if (userNameExists == 1)
	   {
		   String selectSql2 = "select user_id from users where username=?";
		   PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
		   
		   selectStatement2.setString(1, userName);
		   ResultSet selectResults2 = selectStatement2.executeQuery();
		   selectResults2.next();
		   int user_Id = selectResults2.getInt("user_id");
		   	   
		   // Validate if the user_id already has an associated user in the HRM database
		   if (userId == user_Id)
		      validUserName = true;	 
		   else
			   validUserName = false;
		   
		   selectResults2.close();
		   selectStatement2.close();
	   
	   }
	   else
		   validUserName = true;	 
		   
	   	   
       selectResults1.close();
	   selectStatement1.close();
	  
	   
	   return validUserName;
	} 
	
	public boolean validateEmail(User user) throws SQLException
	{
	  
	   boolean validEmail = false;
	   
	   // Get the values from the GUI
	   int userId = user.getId();
	   String email = user.getEmail();
	   
	   // SQL Statements
	   String selectSql1 = "select count(user_id) as count from users where email=?";
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   
	   // Set the Email from the GUI
	   selectStatement1.setString(1, email);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   selectResults1.next();
	   int emailExists = selectResults1.getInt("count");
	   
	   // Validate if the Email already exist for an existing User
	   if (emailExists == 1)
	   {
		   String selectSql2 = "select user_id from users where email=?";
		   PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
		   
		   selectStatement2.setString(1, email);
		   ResultSet selectResults2 = selectStatement2.executeQuery();
		   selectResults2.next();
		   int user_Id = selectResults2.getInt("user_id");
		   	   
		   // Validate if the user_id already has an associated user in the HRM database
		   if (userId == user_Id)
		      validEmail = true;	 
		   else
			   validEmail = false;
		   
		   selectResults2.close();
		   selectStatement2.close();
	   
	   }
	   else
		   validEmail = true;	 
		   
	   	   
       selectResults1.close();
	   selectStatement1.close();
	  
	   
	   return validEmail;
	}

	public boolean validateDeleteUser(User user) throws SQLException
	{
	  
	   boolean deleteUser = false;
	   int empSup = 0;
	   
	   // Get the values from the GUI
	   String employeeName = user.getEmployee();
	   
	   // SQL Statements
	   String selectSql1 = "select employee_id as employee_id from employees where full_name=?";
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   
	   String selectSql2 = "select count(*) as count from employees where supervisor_id =?";
	   PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
	   
	   // Get the Employee Id from the selected employee Name
	   selectStatement1.setString(1, employeeName);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   selectResults1.next();
	   int employeeId = selectResults1.getInt("employee_id");
	   
	   // Verify if the Employee Id is supervisor of other employees
	   selectStatement2.setInt(1, employeeId);
	   ResultSet selectResults2 = selectStatement2.executeQuery();
	   selectResults2.next();
	   empSup = selectResults2.getInt("count");
	   
	   // Validate if the Employee_id is supervisor of other employees
	   if (empSup >= 1)
	      deleteUser = false;	 
	   else
		   deleteUser = true;	 
		    
       selectResults1.close();
	   selectStatement1.close();
       selectResults2.close();
	   selectStatement2.close();
	  
	   return deleteUser;
	} 
	
	public boolean validateUser(User user) throws SQLException
	{
	  	
	   // Get the values from the GUI
       String userName = user.getUserName();	
	   String password = user.getPassword();
	   boolean validUser = false; 
	   
       // SQL Statements
	   String selectSql1 = "select count(username) count ";
	          selectSql1 = selectSql1 + "from users where username=? ";
	          selectSql1 = selectSql1 + "and password = AES_ENCRYPT(?, key_string)";

	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   selectStatement1.setString(1, userName);
	   selectStatement1.setString(2, password);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   selectResults1.next();
	   int count = selectResults1.getInt(1);

	   // Validate if the user and password exists in the HRM Database
	   if (count == 1)
	      validUser = true;	 
	   else
	      validUser = false;   
	      
	     
	   selectResults1.close();
	   selectStatement1.close();
	   
	   
	   return validUser;
	} 
	
	public UserLevel getUserLevel(User user) throws SQLException
	{
	  	
	   // Get the values from the GUI
       String userName = user.getUserName();	
	   String password = user.getPassword();
	   UserLevel userLevel = null; 
	   
	   // SQL Statements
	   String selectSql1 = "select user_level ";
	          selectSql1 = selectSql1 + "from users where username=? ";
	          selectSql1 = selectSql1 + "and password = AES_ENCRYPT(?, key_string)";
	          		       
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   selectStatement1.setString(1, userName);
	   selectStatement1.setString(2, password);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   
	   // Validate if there are results in the query
	   if (!selectResults1.next() ) 
		  userLevel = null; 
	   else
	   {	   
	      String userLevelStr = selectResults1.getString(1);
	      userLevel = UserLevel.valueOf(userLevelStr);
	   }
	   
	   selectResults1.close();
	   selectStatement1.close();
	      
	   return userLevel;
	}	
	
   
}
