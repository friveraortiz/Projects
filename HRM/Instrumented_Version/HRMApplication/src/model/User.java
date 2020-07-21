package model;

import java.io.Serializable;


// This Class is to store the data, it has the same atributes as the UserFormEvent Class
public class User implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the Employees, start with number 1
	private static int count = 1;
	
    private int id;	
    private String userName;	
    private String password;
    private String confirmPassword;
    private String employee;
    private String email;
    private UserLevel userLevel;
	
    // Constructor #1 of the User Class that assigns an ID
	public User(String userName, String password, String confirmPassword, String employee,
     String email, UserLevel userLevel)
	{
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.employee = employee;
		this.email = email;
		this.userLevel = userLevel;
		
		
	    // Everytime that I create a new user, it will increase the count counter
	    this.id = count;
	    count++;
	    
	}
	
    // Constructor #2 of the User Class that assigns an ID
	public User(int id, String userName, String password, String confirmPassword, String employee,
		     String email, UserLevel userLevel)
	{
		// Calls the Constructor #1 of the User Class
        this(userName, password, confirmPassword, employee, email, userLevel);
		
		this.id = id;
		
	}
	
    // Constructor #3 of the User Class that assigns an ID
	public User(String userName, String password)
	{
       this.userName = userName;
	   this.password = password;
		
	}
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getconfirmPassword() {
		return confirmPassword;
	}

	public void setconfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}

	@Override
	public String toString() 
	{
		return id + ": " + userName + " "+ employee + " "+ userLevel;
	}
	
}
