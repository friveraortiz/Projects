package frl.gui.user;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import frl.controller.user.UserController;
import frl.gui.main.MainFrame;
import frl.model.user.UserLevel;

//Package #3
//Class #3
public class LoginFrame extends JFrame 
{

   private static final long serialVersionUID = 1L;
   private LoginFormPanel loginFormPanel;
   
   // Constructor of the LoginFrame class
   // Method #1
   public LoginFrame(String databaseConfigFile, String featuresConfigFile) 
   {
     // Creates the new Window for the Login Frame Class
     super("Forensic Ready Logger System Login");
        
     // Layout of the Main Window
     setLayout(new BorderLayout());
        
     // Creation of the Panel for the LoginFrame Class
     loginFormPanel = new LoginFormPanel();
                
     // Add a Panel for the Login Frame Class
     add(loginFormPanel, BorderLayout.WEST);
        
     addWindowListener(new WindowAdapter() 
	 {
	    public void windowClosing(WindowEvent arg0) 
		{
		   // Releases all of the native screen resources used by this Window, its subcomponents, and all of its owned children. 
		  // That is, the resources for these Components will be destroyed, any memory they consume will be returned to the OS, 
		  // and they will be marked as undisplayable.
		  dispose();
				
		  // Calls the Java Garbage Collector to release memory
		  System.gc();
				
	     }
			
	  });

	  // Listener for the loginFormPanel object
      loginFormPanel.setLoginFormPanelListener(new LoginFormPanelListener() 
      {

	      public void loginFormEventOccurred(UserFormEvent e) 
		  {

		     boolean validUser1=false;
             String userName = e.getUserName();
              
			 // Validation #1: Validate if the User and Password exists in the Forensic Ready Logger System
             validUser1 = validateUser(e, databaseConfigFile);
             
         	 if (validUser1 == false) 
        	 {
         		// Error #1 
                JOptionPane.showMessageDialog(LoginFrame.this, "Error 3311: The User or the Password is not correct.", "Validating a User", JOptionPane.ERROR_MESSAGE);
             }
        	 else
        	 {
		        // Hide the Login Frame Window
			    setVisible(false);
			    dispose();
			    System.gc();
			  
			    //Get the UserLevel of the connected user 
			    UserLevel userLevel=getUserLevel(e, databaseConfigFile);
			    
			    // Opens the Forensic Ready Logger Main Window
                new MainFrame(userName, userLevel, databaseConfigFile, featuresConfigFile);
			  
        	 }
              
	     }

      });
        
      // Set the Minimum Size of the Form
      this.setMinimumSize(new Dimension(350, 400));
      
      // Set the Size of the Form
	  this.setSize(400, 500);
	  
	  // Set the location in the center of the screen
	  this.setLocationRelativeTo(null);
	 
	  // Make visible the Window
      this.setVisible(true);
        
	  // Set up that the windows cannot be resizable by the User
	  this.setResizable(false);
		
	  this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
   }
	
   // Method #2
   public boolean validateUser(UserFormEvent ue, String databaseConfigFile)
   {
      UserController userController = new UserController();
	  boolean validUser=false;
	  String errorMessage1="";
	   
 	  try 
 	  {
	     validUser = userController.validateUser(ue, databaseConfigFile);
	  } 
 	  catch (Exception e1) 
 	  {
 		 // Error #1 
         errorMessage1 = e1.getMessage();
	     JOptionPane.showMessageDialog(LoginFrame.this, errorMessage1, "Validating a User", JOptionPane.ERROR_MESSAGE);
	  }
 	   
 	  return validUser;      	
   }
	
   // Method #3
   public UserLevel getUserLevel(UserFormEvent ue, String databaseConfigFile)
   {
      UserController userController = new UserController();
	  UserLevel userLevel = null;
	  String errorMessage1="";
		
 	  try 
 	  {
	     userLevel = userController.getUserLevel(ue, databaseConfigFile);
	  } 
 	  catch (Exception e1) 
 	  {
 		 // Error #1 
         errorMessage1 = e1.getMessage();
	     JOptionPane.showMessageDialog(LoginFrame.this, errorMessage1, "Loading the User Level", JOptionPane.ERROR_MESSAGE);
	  }
 	   
 	   return userLevel;      
   }
	
}