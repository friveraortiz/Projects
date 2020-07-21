package gui;
import java.util.EventObject;

public class UserFormEvent extends EventObject 
{

   private int id;
   private String userName;	
   private String password;
   private String confirmPassword;
   private String employee;
   private String email;
   private String userLevel;
	   
	
	public UserFormEvent(Object source) 
	{
		super(source);
	}


	public UserFormEvent(Object source, int id, String userName, String password, String confirmPassword, 
			String employee, String email, String userLevel) 
	{
		super(source);
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.employee = employee;
		this.email = email;
		this.userLevel = userLevel;
	}
	
	public UserFormEvent(Object source, String userName, String password, String confirmPassword, 
			String employee, String email, String userLevel) 
	{
		super(source);
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.employee = employee;
		this.email = email;
		this.userLevel = userLevel;
	}
	
	public UserFormEvent(Object source, String userName, String password) 
	{
		super(source);
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


	public String getConfirmPassword() {
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


	public String getUserLevel() {
		return userLevel;
	}


	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}


}	

