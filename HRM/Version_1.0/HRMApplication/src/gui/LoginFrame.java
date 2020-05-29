package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import controller.UserController;
import model.UserLevel;


public class LoginFrame extends JFrame 
{
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private LoginFormPanel loginFormPanel;
   
    
    // Constructor of the LoginFrame class
	public LoginFrame () 
	{
		// Creates the new Window for the Login Frame Class
        super("Human Resources Management System Login");
        
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
              
			  // Validation #1: Validate if the User and Password exists in the HRM System
              validUser1 = validateUser(e);
             
         	  if (validUser1 == false) 
        	  {
                 JOptionPane.showMessageDialog(LoginFrame.this, "Error 01: The User or the Password are not correct.", "Validating a User", JOptionPane.ERROR_MESSAGE);
              }
        	  else
        	  {
		         // Hide the Login Frame Window
			     setVisible(false);
			     dispose();
			     System.gc();
			  
			     //Get the UserLevel of the connected user 
			     UserLevel userLevel=getUserLevel(e);
			     
			     // Opens the HRM Main Windows
                 new MainFrame(userName, userLevel);
			  
        	  }
              
		   }

        });
        
        // Set the Minimum Size of the Form
        this.setMinimumSize(new Dimension(350, 400));

        // Set the Size of the Form
		setSize(400, 500);
        this.setVisible(true);
        
		// Set up that the windows cannot be resizable by the User
		this.setResizable(false);
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
	
	public boolean validateUser(UserFormEvent e)
	{
       UserController userController = new UserController();
	   boolean validUser=false;
	   
 	   try 
 	   {
	      validUser=userController.validateUser(e);
	   } 
 	   catch (SQLException e2) 
 	   {
		  String errorMessage = e2.getMessage();
	      JOptionPane.showMessageDialog(LoginFrame.this, "Error 02: Occurred while validating a User. Error Message: "+errorMessage, "Validating a User", JOptionPane.ERROR_MESSAGE);
	   }
 	   
 	      
 	   return validUser;      
		
	}
	
	public UserLevel getUserLevel(UserFormEvent e)
	{
       UserController userController = new UserController();
	   UserLevel userLevel = null;
		
 	   try 
 	   {
	      userLevel=userController.getUserLevel(e);
	   } 
 	   catch (SQLException e2) 
 	   {
		  String errorMessage = e2.getMessage();
	      JOptionPane.showMessageDialog(LoginFrame.this, "Error 03: Occurred while loading the UserLevel. Error Message: "+errorMessage, "Loading the User Level", JOptionPane.ERROR_MESSAGE);
	   }
 	   
 	      
 	   return userLevel;      
		
	}
	
}