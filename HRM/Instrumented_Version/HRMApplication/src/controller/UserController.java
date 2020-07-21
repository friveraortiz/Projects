package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import gui.UserFormEvent;
import model.Employee;
import model.User;
import model.UserDatabase;
import model.UserLevel;


// This class is an intermediary between the GUI and the Model Classes
// Transform the data from the GUI in a way that the Model can understand it, because 
// Model is a Data Model to store data into a Database
public class UserController 
{
	// Declare a new object for the UserDatabase class
	UserDatabase usrDb = new UserDatabase();

	// Define the method: List getUsers which was defined in the UserDatabase Class
	public List<User> getUsers()
	{
		return usrDb.getUsers();
	}
	
	// Define the method: addUser which was defined in the UserDatabase Class
	public void addUser(UserFormEvent ev) 
	{
	     User user;
		
		 // Define the variables for this method
		 int id = ev.getId();
	     String userName = ev.getUserName();	
	     String password = ev.getPassword();
	     String email = ev.getEmail();
	     String employee = ev.getEmployee();
	     String userLevel = ev.getUserLevel();
	     UserLevel userLevelCat = null;
	     
	     // Define the possible values for UserLevel
	     if(userLevel.equals("Admin")) 
	     {
	        userLevelCat = UserLevel.Admin;
	     }
	     else if(userLevel.equals("Manager")) 
		 {
	    	 userLevelCat = UserLevel.Manager;
		 }
		 else if(userLevel.equals("Employee")) 
		 {
		    userLevelCat = UserLevel.Employee;
		 }
	     
	     if (id == 0)
	     {
	    	 user = new User(userName, password, password, employee, email, userLevelCat);
	     }
	     else
	     {
	         user = new User(id, userName, password, password, employee, email, userLevelCat);
	     }
	    
	     usrDb.addUser(user);
	     
	}
	
	// Define the method: delete an User
	public void deleteUser(int index) 
	{
		usrDb.deleteUser(index);
	}
	
   public void save(UserFormEvent ev) throws SQLException 
   {
	  User user;
	   
      // Define the variables for this method
	  int id = ev.getId();
	  String userName = ev.getUserName();	
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();
	  String email = ev.getEmail();
	  String employee = ev.getEmployee();
	  String userLevel = ev.getUserLevel();
	  UserLevel userLevelCat = null;
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Manager")) 
	  {
	     userLevelCat = UserLevel.Manager;
	  }
	  else if(userLevel.equals("Employee")) 
	  {
	     userLevelCat = UserLevel.Employee;
      }
		  
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  
      try 
	  {
	     usrDb.connect();
	  } 
      catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
	     System.out.println("Error 38: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      
	  usrDb.save(user); 

	  usrDb.disconnect();	
	  
   }
   
   public void delete(UserFormEvent ev) throws SQLException 
   {
      User user;
	  int id = ev.getId();
	  String userName = ev.getUserName();	
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();
	  String email = ev.getEmail();
	  String employee = ev.getEmployee();
	  String userLevel = ev.getUserLevel();
	  UserLevel userLevelCat = null;
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Manager")) 
	  {
	     userLevelCat = UserLevel.Manager;
	  }
	  else if(userLevel.equals("Employee")) 
	  {
	     userLevelCat = UserLevel.Employee;
      }
		    	 
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  
      try 
	  {
	     usrDb.connect();
	  } 
      catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
	     System.out.println("Error 38: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      usrDb.delete(user); 

      usrDb.disconnect();	
	  
   }

	   
   public void load() throws SQLException 
   {
      try 
	  {
	     usrDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 38: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      usrDb.load(); 

      usrDb.disconnect();	
   }
   
   public void loadUser(int id) throws SQLException 
   {
      try 
	  {
	     usrDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 38: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }	   
      usrDb.loadUser(id); 
      usrDb.disconnect();	
   } 
   
   public ArrayList<Employee> loadFullNameEmployees() throws SQLException
   {
	   
      try 
	  {
	     usrDb.connect();
	  } 
      catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
	     System.out.println("Error 29: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
	  ArrayList<Employee> eB = usrDb.loadFullNameEmployees();
	  
	  usrDb.disconnect();
	  
      return eB;
   }
   
   public boolean validateUserFields(UserFormEvent ev)
   {
	  boolean validUser = false; 
	  int v = 0;
	  String userName = ev.getUserName();	
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();
	  String email = ev.getEmail();
	  String employee = ev.getEmployee();
	     	  
	  // Validation: #1
	  // Validate that all the User Fields are not empty
	  
	  if(userName != null && !userName.isEmpty())
	     v++;
	
	  if(password != null && !password.isEmpty())
	     v++;

	  if(confirmPassword != null && !confirmPassword.isEmpty())
	     v++;
	  
	  if(email != null && !email.isEmpty())
	     v++;

	  if(employee != null && !employee.isEmpty() && !employee.trim().isEmpty())
	     v++;
	  
	  // Validate that all the users fields have a value						     
	  if (v == 5) 
	     validUser = true;
	  else 
		  validUser = false;
	  
      return validUser;	     
   }   

   public boolean validateEmployee(UserFormEvent ev) throws SQLException
   {
	  boolean validEmployee = false; 
	  User user;
	  int id = ev.getId();
	  String userName = ev.getUserName();	
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();
	  String email = ev.getEmail();
	  String employee = ev.getEmployee();
	  String userLevel = ev.getUserLevel();
	  UserLevel userLevelCat = null;
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Manager")) 
	  {
	     userLevelCat = UserLevel.Manager;
	  }
	  else if(userLevel.equals("Employee")) 
	  {
	     userLevelCat = UserLevel.Employee;
      }
		  
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  
      try 
	  {
	     usrDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 38: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

      // Validation: #2
	  // Validate if the user has been already assigned to another Employee
	  validEmployee = usrDb.validateEmployee(user); 

      usrDb.disconnect();		  
	  
	  return validEmployee;
      
   }  
   public boolean validatePassword(UserFormEvent ev)
   {
	  boolean validPassword = false; 
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();

	  // Validate that the password and confirm password are the same	  
	  // Validation: #3
	  if (password.equals(confirmPassword))
	     validPassword = true;
	  else
	     validPassword = false;
	  
	  
	  return validPassword;
      
   } 
   
   public boolean validateUserName(UserFormEvent ev) throws SQLException
   {
	  boolean validUserName = false; 
	  User user;
	  int id = ev.getId();
	  String userName = ev.getUserName();	
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();
	  String email = ev.getEmail();
	  String employee = ev.getEmployee();
	  String userLevel = ev.getUserLevel();
	  UserLevel userLevelCat = null;
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Manager")) 
	  {
	     userLevelCat = UserLevel.Manager;
	  }
	  else if(userLevel.equals("Employee")) 
	  {
	     userLevelCat = UserLevel.Employee;
      }
		  
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, employee, email, userLevelCat);
	  }

      try 
	  {
	     usrDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 38: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

	  // Validation: #4
	  // Validate that the UserName doesn't exist already for an existing User
	  validUserName = usrDb.validateUserName(user); 
	  
      usrDb.disconnect();	
	  
	  return validUserName;
      
   } 
   
   public boolean validateEmail(UserFormEvent ev) throws SQLException
   {
	  boolean validEmail = false; 
	  User user;
	  int id = ev.getId();
	  String userName = ev.getUserName();	
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();
	  String email = ev.getEmail();
	  String employee = ev.getEmployee();
	  String userLevel = ev.getUserLevel();
	  UserLevel userLevelCat = null;
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Manager")) 
	  {
	     userLevelCat = UserLevel.Manager;
	  }
	  else if(userLevel.equals("Employee")) 
	  {
	     userLevelCat = UserLevel.Employee;
      }
		  
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, employee, email, userLevelCat);
	  }

      try 
	  {
	     usrDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 38: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

	  // Validation: #5
	  // Validate that the Email doesn't exist already for an existing User
	  validEmail = usrDb.validateEmail(user); 
	  
      usrDb.disconnect();	
	  
	  return validEmail;
      
   } 
   
   public boolean validateDeleteUser(UserFormEvent ev) throws SQLException
   {
	  boolean deleteUser = false; 
	  
	  User user;
	  int id = ev.getId();
	  String userName = ev.getUserName();	
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();
	  String email = ev.getEmail();
	  String employee = ev.getEmployee();
	  String userLevel = ev.getUserLevel();
	  UserLevel userLevelCat = null;
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Manager")) 
	  {
	     userLevelCat = UserLevel.Manager;
	  }
	  else if(userLevel.equals("Employee")) 
	  {
	     userLevelCat = UserLevel.Employee;
      }
		  
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, employee, email, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, employee, email, userLevelCat);
	  }
      try 
	  {
	     usrDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 38: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

	  // Validation: #6
	  // Validate if the User is supervisor of other employees
	  deleteUser = usrDb.validateDeleteUser(user); 
	  
	  usrDb.disconnect();	
	  
	  return deleteUser;
      
   }   
   
   public boolean validateUser(UserFormEvent ev) throws SQLException
   {
	  User user;
	   
	  // Get the values from the GUI
      String userName = ev.getUserName();	
	  String password = ev.getPassword();
      boolean validUser = false;
      
      user = new User(userName, password);
      
      try 
	  {
	     usrDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 02: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

      // Validation: #1
      // Verify that the user and password exists in the HRM Database
      validUser=usrDb.validateUser(user);
      usrDb.disconnect();
	  
      return validUser;	     
   }  
   
   public UserLevel getUserLevel(UserFormEvent ev) throws SQLException
   {
	  User user;
	   
	  // Get the values from the GUI
      String userName = ev.getUserName();	
	  String password = ev.getPassword();
      UserLevel userLevel = null;
      
      user = new User(userName, password);
      
      try 
	  {
	     usrDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 04: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      
	  // Validation: #2
      // Get the user level of the connected user
      userLevel=usrDb.getUserLevel(user);
      
      usrDb.disconnect();
	  
      return userLevel;	     
   } 
   
   
   
}
