package test;


import java.sql.SQLException;

import controller.UserController;
import gui.UserFormEvent;
import model.UserLevel;


public class TestLoginDb 
{
	

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Running HRM Login Database Test ...");

        UserController loginController = new UserController();
		//LoginDatabase db = new LoginDatabase();
		

		/*
		try 
		{
		   boolean validUser = false;
		   
		   validUser=db.validateUser(new Login("friverao", "123"));
		   System.out.println("Valid User: "+validUser);
		} 
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}
		
		boolean validUser = false;
		
	 	try 
	 	{
		   validUser=loginController.validateUser(new LoginFormEvent("TestLoginDb", "friverao1", new String("123")));
		   System.out.println("Valid User: "+validUser);
		} 
	 	catch (SQLException e2) 
	 	{
	 	   e2.printStackTrace();
		   String errorMessage = e2.getMessage();
		   System.out.println("Error Message: "+errorMessage);
		   //JOptionPane.showMessageDialog(LoginFrame.this, "An error ocurred while validating a User: "+errorMessage, "Validating a User", JOptionPane.ERROR_MESSAGE);
		}*/
		
		/*
		UserLevel userLevel;
		
	 	try 
	 	{
		   userLevel=db.getUserLevel(new Login("aportillo", "12345"));
		   System.out.println("User Level: "+userLevel);
		} 
	 	catch (SQLException e2) 
	 	{
	 	   e2.printStackTrace();
		   String errorMessage = e2.getMessage();
		   System.out.println("Error Message: "+errorMessage);
		   //JOptionPane.showMessageDialog(LoginFrame.this, "An error ocurred while validating a User: "+errorMessage, "Validating a User", JOptionPane.ERROR_MESSAGE);
		}*/
		
		
		UserLevel userLevel = null;
		
	 	try 
	 	{
		   userLevel=loginController.getUserLevel((new UserFormEvent("TestLoginDb", "aguzman", "12345")));
		   System.out.println("User Level: "+userLevel);
		} 
	 	catch (SQLException e2) 
	 	{
	 	   e2.printStackTrace();
		   String errorMessage = e2.getMessage();
		   System.out.println("Error Message: "+errorMessage);
		   //JOptionPane.showMessageDialog(LoginFrame.this, "An error ocurred while validating a User: "+errorMessage, "Validating a User", JOptionPane.ERROR_MESSAGE);
		}
		

	 	//db.disconnect();
	
	}

}
