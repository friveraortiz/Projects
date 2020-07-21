package test;


import java.sql.SQLException;
import model.User;
import model.UserDatabase;
import model.UserLevel;


public class TestUserDb 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Running HRM Users Database Test ...");

		UserDatabase db = new UserDatabase();
		try 
		{
			db.connect();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// Convert a String to a Date
		
	        
		try 
		{
		   db.save(new User(1, "hsmith", "12345", "12345", "Henry Smith", "hsmith@hotmail.com", UserLevel.Admin));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		

		/*
		try 
		{
			db.load();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			db.loadUser(2);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		try 
		{
			db.loadFullNameEmployees();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		
		
		try 
		{
		   boolean validEmployee = false;
		   
		   
		   validEmployee=db.validateEmployee(new User(2, "aperez", "123456789", "Alejandro Perez", "Alejandro Perez Guiterrez", "agutierrez@hotmail.com", UserLevel.Admin));
		   System.out.println("Valid Employee 2: "+validEmployee);
		} 
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}*/
		
		/*
		try 
		{
		   boolean validUserName = false;
		   
		   validUserName=db.validateUserName(new User(14, "jperez", "12345", "12345", "Juan Pedro Perez", "jpperez@hotmail.com", UserLevel.Employee));
		   System.out.println("Valid UserName: "+validUserName);
		} 
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}*/
		
		/*
		try 
		{
		   boolean validEmail = false;
		   
		   validEmail=db.validateEmail(new User(29, "avelazquez", "123", "123", "Andres Velazquez", "avelazques@hotmai.com", UserLevel.Employee));
		   System.out.println("Valid Email: "+validEmail);
		} 
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}
		
		try 
		{
		   boolean deleteEmp = false;
		   
		   deleteEmp=db.validateDeleteUser(new User(6, "avelazquez", "123", "123", "Alejandro Velazques", "avelaquez@hotmai.com", UserLevel.Manager));
		   System.out.println("Delete Employee: "+deleteEmp);

		   System.out.println("Delete Employee: "+deleteEmp);
		} 
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}*/
		
		
		db.disconnect();
	
	}

}
