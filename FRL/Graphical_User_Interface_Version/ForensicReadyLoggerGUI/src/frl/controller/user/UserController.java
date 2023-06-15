package frl.controller.user;

import java.util.List;

import frl.gui.user.UserFormEvent;
import frl.model.user.User;
import frl.model.user.UserDatabase;
import frl.model.user.UserLevel;

// This class is an intermediary between the GUI and the Model Classes
// Transform the data from the GUI in a way that the Model can understand it, because 
// Model is a Data Model to store data into a Database

// Package #2
// Class #1
public class UserController 
{
	// Declare a new object for the UserDatabase class
	UserDatabase usrDb = new UserDatabase();

	// Define the method: List getUsers which was defined in the UserDatabase Class
	// Method #1
	public List<User> getUsers()
	{
		return usrDb.getUsers();
	}
	
	// Method #2
	public void connect(String databaseConfigFile) throws Exception
	{
	   String errorMessage1="";
	   
	   try 
	   {
	      usrDb.connect(databaseConfigFile);
	   } 
	   catch (Exception e ) 
	   {
	      // Error #1 
		  errorMessage1 = e.getMessage();	
		  throw new Exception(errorMessage1);
	   }
	   
	}
	
	// Method #3
    public boolean validateUser(UserFormEvent ev, String databaseConfigFile) throws Exception
    {
       User user;
	   String userName, password, errorMessage="";
	   boolean validUser=false;
		   
	   // Get the values from the GUI
	   userName = ev.getUserName();	
	   password = ev.getPassword();
	      
	   user = new User(userName, password);
	   
	   // Connect to the FRL Database
	   try 
	   {
	      connect(databaseConfigFile);
	   }
	   catch(Exception e)
	   {
	      errorMessage = e.getMessage();
		  throw new Exception(errorMessage);
	   }
	   
	   // Validation: #1
	   // Verify that the user and password exists in the ForensicReadyLogger Database
	   try
	   {
	      validUser = usrDb.validateUser(user);
	   }
	   catch (Exception e ) 
	   {
		  errorMessage = e.getMessage();	
		  throw new Exception(errorMessage);
	   }
	   
	   // Disconnect from the Database
	   try 
	   {
	      usrDb.disconnect();
	   }
	   catch(Exception e)
	   {
	      errorMessage = e.getMessage();
		  throw new Exception(errorMessage);
	   }
		  
	   return validUser;	     
   }  
   
   // Method #4
   public UserLevel getUserLevel(UserFormEvent ev, String databaseConfigFile) throws Exception
   {
      User user;
	  String userName, password, errorMessage="";
	  UserLevel userLevel=null;
		   
	  // Get the values from the GUI
	  userName = ev.getUserName();	
	  password = ev.getPassword();
	  
	  user = new User(userName, password);
	      
	  // Connect to the FRL Database
	  try 
	  {
	      connect(databaseConfigFile);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	      
	  // Validation: #2
	  // Get the user level of the connected user
	  try
	  {
	     userLevel = usrDb.getUserLevel(user);
	  }
	  catch (Exception e ) 
	  {
	     errorMessage = e.getMessage();	
		 throw new Exception(errorMessage);
	  }
	      
	   // Disconnect from the Database
	   try 
	   {
	      usrDb.disconnect();
	   }
	   catch(Exception e)
	   {
	      errorMessage = e.getMessage();
		  throw new Exception(errorMessage);
	   }
		  
	  return userLevel;	     
   } 
	   
	// Define the method: addUser which was defined in the UserDatabase Class
    // Method #5
	public void addUser(UserFormEvent ev) 
	{
	     User user;
		 int id;
	     String userName, password, userLevel;
	     UserLevel userLevelCat = null;
		
		 // Define the variables for this method
		 id = ev.getId();
	     userName = ev.getUserName();	
	     password = ev.getPassword();
	     userLevel = ev.getUserLevel();
	     
	     // Define the possible values for UserLevel
	     if(userLevel.equals("Admin")) 
	     {
	        userLevelCat = UserLevel.Admin;
	     }
	     else if(userLevel.equals("Security")) 
		 {
	    	 userLevelCat = UserLevel.Security;
		 }
		 else if(userLevel.equals("Developer")) 
		 {
		    userLevelCat = UserLevel.Developer;
		 }
	     
	     if (id == 0)
	     {
	    	 user = new User(userName, password, password, userLevelCat);
	     }
	     else
	     {
	         user = new User(id, userName, password, password, userLevelCat);
	     }
	    
	     usrDb.addUser(user);
	     
	}
	
	// Define the method: delete an User
	// Method #6
   public void deleteUser(int index) 
   {
      usrDb.deleteUser(index);
   }
	
   // Method #7
   public void save(UserFormEvent ev, String databaseConfigFile) throws Exception 
   {
	  User user;
	  int id;
	  String userName, password, confirmPassword, userLevel, errorMessage="";
	  UserLevel userLevelCat = null;
	   
      // Define the variables for this method
	  id              = ev.getId();
	  userName        = ev.getUserName();	
	  password        = ev.getPassword();
	  confirmPassword = ev.getConfirmPassword();
	  userLevel       = ev.getUserLevel();
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Security")) 
	  {
	     userLevelCat = UserLevel.Security;
	  }
	  else if(userLevel.equals("Developer")) 
	  {
	     userLevelCat = UserLevel.Developer;
      }
		  
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, userLevelCat);
	  }
	  
	  // Connect to the FRL Database
	  try 
	  {
	      connect(databaseConfigFile);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
      
	  try
	  {
	     usrDb.save(user); 
	  }
	  catch (Exception e ) 
	  {
	     errorMessage = e.getMessage();	
		 throw new Exception(errorMessage);
	  }

	  // Disconnect from the Database
	  try 
	  {
	      usrDb.disconnect();
	  }
	  catch(Exception e)
	  {
	      errorMessage = e.getMessage();
		  throw new Exception(errorMessage);
	  }
	  
   }
   
   // Method #8
   public void delete(UserFormEvent ev, String databaseConfigFile) throws Exception 
   {
      User user;
	  int id = ev.getId();
	  String userName, password, confirmPassword, userLevel, errorMessage="";
	  UserLevel userLevelCat = null;
	  
	  id              = ev.getId();
	  userName        = ev.getUserName();	
	  password        = ev.getPassword();
	  confirmPassword = ev.getConfirmPassword();
	  userLevel       = ev.getUserLevel();
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Security")) 
	  {
	     userLevelCat = UserLevel.Security;
	  }
	  else if(userLevel.equals("Developer")) 
	  {
	     userLevelCat = UserLevel.Developer;
      }
		    	 
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, userLevelCat);
	  }
	  
	  // Connect to the FRL Database
	  try 
	  {
	      connect(databaseConfigFile);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  try
	  {
         usrDb.delete(user);
	  }
	  catch (Exception e ) 
	  {
	     errorMessage = e.getMessage();	
		 throw new Exception(errorMessage);
	  }

	  // Disconnect from the Database
	  try 
	  {
	     usrDb.disconnect();
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
   }

   // Method #9
   public void load(String databaseConfigFile) throws Exception 
   {
      String errorMessage ="";
      
	  // Connect to the FRL Database
	  try 
	  {
	      connect(databaseConfigFile);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
      
	  try
	  {
         usrDb.load();
	  }
	  catch (Exception e ) 
	  {
	     errorMessage = e.getMessage();	
		 throw new Exception(errorMessage);
	  }

	  // Disconnect from the Database
	  try 
	  {
	     usrDb.disconnect();
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
   }
   
   
   // Method #11
   public boolean validateUserFields(UserFormEvent ev)
   {
	  boolean validUser = false; 
	  int v = 0;
	  String userName, password, confirmPassword;
	  
	  userName        = ev.getUserName();	
	  password        = ev.getPassword();
	  confirmPassword = ev.getConfirmPassword();
	     	  
	  // Validation: #1
	  // Validate that all the User Fields are not empty
	  
	  if(userName != null && !userName.isEmpty())
	     v++;
	
	  if(password != null && !password.isEmpty())
	     v++;

	  if(confirmPassword != null && !confirmPassword.isEmpty())
	     v++;
	  
	  // Validate that all the users fields have a value						     
	  if (v == 3) 
	     validUser = true;
	  else 
		  validUser = false;
	  
      return validUser;	     
   }   

   // Method #12
   public boolean validatePassword(UserFormEvent ev)
   {
	  boolean validPassword = false;
	  String password, confirmPassword;
	  
	  password        = ev.getPassword();
	  confirmPassword = ev.getConfirmPassword();

	  // Validate that the password and confirm password are the same	  
	  // Validation: #3
	  if (password.equals(confirmPassword))
	     validPassword = true;
	  else
	     validPassword = false;
	  
	  
	  return validPassword;
      
   } 
   
   // Method #13
   public boolean validateUserName(UserFormEvent ev, String databaseConfigFile) throws Exception
   {
	  boolean validUserName = false; 
	  User user;
	  int id;
	  String userName, password, confirmPassword, userLevel, errorMessage = "";
	  UserLevel userLevelCat = null;
	  
	  id = ev.getId();
	  userName = ev.getUserName();	
	  password = ev.getPassword();
	  confirmPassword = ev.getConfirmPassword();
	  userLevel = ev.getUserLevel();
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Security")) 
	  {
	     userLevelCat = UserLevel.Security;
	  }
	  else if(userLevel.equals("Developer")) 
	  {
	     userLevelCat = UserLevel.Developer;
      }
		  
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, userLevelCat);
	  }

	  // Connect to the FRL Database
	  try 
	  {
	      connect(databaseConfigFile);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }

	  // Validation: #4
	  // Validate that the UserName doesn't exist already for an existing User
	  try
	  {
	     validUserName = usrDb.validateUserName(user);
	  }
	  catch (Exception e ) 
	  {
	     errorMessage = e.getMessage();	
		 throw new Exception(errorMessage);
	  }
	  
	  // Disconnect from the Database
	  try 
	  {
	     usrDb.disconnect();
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  return validUserName;
      
   } 
   
   // Method #14
   public boolean validateDeleteUser(UserFormEvent ev, String databaseConfigFile) throws Exception
   {
	  boolean deleteUser = false; 
	  
	  //User user;
	  /*int id = ev.getId();
	  String userName = ev.getUserName();	
	  String password = ev.getPassword();
	  String confirmPassword = ev.getConfirmPassword();
	  */
	  String userLevel = ev.getUserLevel();
	  String errorMessage="";
	  UserLevel userLevelCat = null;
	     
	  // Define the possible values for UserLevelCat
	  if(userLevel.equals("Admin")) 
	  {
	     userLevelCat = UserLevel.Admin;
	  }
	  else if(userLevel.equals("Security")) 
	  {
	     userLevelCat = UserLevel.Security;
	  }
	  else if(userLevel.equals("Developer")) 
	  {
	     userLevelCat = UserLevel.Developer;
      }
	  
	  /*
	  if (id == 0)
	  {

	     user = new User(userName, password, confirmPassword, userLevelCat);
	  }
	  else
	  {
	     user = new User(id, userName, password, confirmPassword, userLevelCat);
	  }
	  */
	  
	  // Connect to the FRL Database
	  try 
	  {
	      connect(databaseConfigFile);
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
      
	  // Validation: #6
	  // Validate if the User is supervisor of other employees
	  //deleteUser = usrDb.validateDeleteUser(user); 

	  // Disconnect from the Database
	  try 
	  {
	     usrDb.disconnect();
	  }
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  return deleteUser;
      
   }   
    
}
