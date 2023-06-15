package frl.gui.user;
import java.util.EventObject;

//Package #6
//Class #2
public class UserFormEvent extends EventObject 
{

   private static final long serialVersionUID = 1L;
   private int id;
   private String userName;	
   private String password;
   private String confirmPassword;
   private String userLevel;
   
   // Constructor # 1
   // Method #1	
   public UserFormEvent(Object source) 
   {
		super(source);
   }
	
   // Constructor # 2
   // Method #2
   public UserFormEvent(Object source, int id, String userName, String password, String confirmPassword, 
			             String userLevel) 
   {
		super(source);
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.userLevel = userLevel;
   }
	
   // Constructor # 3
   // Method #3
   public UserFormEvent(Object source, String userName, String password, String confirmPassword, 
			             String userLevel) 
   {
		super(source);
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.userLevel = userLevel;
   }
   
   // Constructor #4
   // Method #4
   public UserFormEvent(Object source, String userName, String password) 
   {
		super(source);
		this.userName = userName;
		this.password = password;
   }
   
   // Method #5
   public int getId() 
   {
		return id;
   }

   // Method #6
   public void setId(int id) 
   {
		this.id = id;
   }

   // Method #7
   public String getUserName() 
   {
		return userName;
   }

   // Method #8
   public void setUserName(String userName) 
   {
		this.userName = userName;
   }

   // Method #9
   public String getPassword() 
   {
		return password;
   }

   // Method #10
   public void setPassword(String password) 
   {
		this.password = password;
   }

   // Method #11	
   public String getConfirmPassword() 
   {
		return confirmPassword;
   }

   // Method #12
   public void setconfirmPassword(String confirmPassword)
   {
		this.confirmPassword = confirmPassword;
   }

   // Method #13
   public String getUserLevel() 
   {
		return userLevel;
   }

   // Method #14
   public void setUserLevel(String userLevel) 
   {
		this.userLevel = userLevel;
   }


}	

