package frl.model.user;

import java.io.Serializable;

// This Class is to store the data, it has the same atributes as the UserFormEvent Class
//Package #8
//Class #1
public class User implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the Employees, start with number 1
	private static int count = 1;
	
    private int id;	
    private String userName;	
    private String password;
    private String confirmPassword;
    private UserLevel userLevel;
	
    // Constructor #1 of the User Class that assigns an ID
    // Method #1
	public User(String userName, String password, String confirmPassword, UserLevel userLevel)
	{
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.userLevel = userLevel;
		
		
	    // Everytime that I create a new user, it will increase the count counter
	    this.id = count;
	    count++;
	    
	}
	
    // Constructor #2 of the User Class that assigns an ID
    // Method #2
	public User(int id, String userName, String password, String confirmPassword, UserLevel userLevel)
	{
		// Calls the Constructor #1 of the User Class
        this(userName, password, confirmPassword, userLevel);
		
		this.id = id;
		
	}
	
    // Constructor #3 of the User Class that assigns an ID
    // Method #3
	public User(String userName, String password)
	{
       this.userName = userName;
	   this.password = password;
		
	}
	
    // Method #4
	public int getId() 
	{
		return id;
	}
	
    // Method #5
	public void setId(int id) 
	{
		this.id = id;
	}
	
    // Method #6
	public String getUserName() 
	{
		return userName;
	}
	
    // Method #7
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	
    // Method #8
	public String getPassword() 
	{
		return password;
	}
	
    // Method #9
	public void setPassword(String password) 
	{
		this.password = password;
	}

    // Method #10
	public String getConfirmPassword() 
	{
		return confirmPassword;
	}
	
    // Method #11
	public void setConfirmPassword(String confirmPassword) 
	{
		this.confirmPassword = confirmPassword;
	}

    // Method #12
	public UserLevel getUserLevel() 
	{
		return userLevel;
	}
	
    // Method #13
	public void setUserLevel(UserLevel userLevel) 
	{
		this.userLevel = userLevel;
	}

    // Method #14
	public String toString() 
	{
		return id + ": " + userName + " "+ userLevel;
	}
	
}
