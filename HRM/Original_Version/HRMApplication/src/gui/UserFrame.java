package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import controller.UserController;
import model.UserLevel;


public class UserFrame extends JFrame 
{
	// Define the attributes for the class
    private UserToolBar userToolBar; 
    private UserFormPanel userFormPanel;
 
    // Define an attribute for the controller
    private UserController userController;
    
    // Define an attribute for the table
    private UserTablePanel userTablePanel;

    public static String menu="";
    public static String submenu="";
    
    // Constructor of the UserFrame class
	public UserFrame (String userP, String menuP, UserLevel userLevelP) 
	{
        super("Users Main Menu HRM");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        
        // The Main Form creates the objects 
        userFormPanel = new UserFormPanel();
        userTablePanel = new UserTablePanel();
        
        if (menuP.equals("Users")) 
        {
        	userToolBar = new UserToolBar(userP, menuP, userLevelP);
        }
        
        // Define an object for the Controller Class
        userController = new UserController();
        
        // Define an object for the UserTablePanel Class
        userTablePanel.setDataUser(userController.getUsers());
        
        // Add a new Listener for the UserTablePanel
        userTablePanel.setUserTableListener(new UserTableListener() 
        {
			@Override
			public void userDeleted(int row) 
			{
				
			   userController.deleteUser(row);
			}

			//@Override
			public void userTableEventOccurred(int id, String popupMenu) 
			{
				
			   // Show the UserForm Panel
		       userFormPanel.setVisible(true);
		     	   
	     	   // Clean the current values in the userFormPanel
	     	   userFormPanel.cleanFields();
	     	   
	     	   // Get all the values from the selected row into the userFormPanel
			   refreshUser();
			   
			   if (popupMenu.equals("View"))
			   {
			      userFormPanel.saveBtn.setVisible(false);
			      userFormPanel.deleteBtn.setVisible(false);
			      
			      // disable all the fields in the userFormPanel
			      disableFieldsUsers();
			      
			      menu = "";
			      
			      // Makes invisible the UserToolBar Add New Button
			      userToolBar.addNewButton.setVisible(false);
			   }
		       else
		          if (popupMenu.equals("Modify"))
		          {
		             userFormPanel.saveBtn.setVisible(true);
		             userFormPanel.deleteBtn.setVisible(false);
		             
		             // enable all the fields in the userFormPanel
		             enableFieldsUsers();
		             
			         menu = "";
			         
			         // Makes invisible the UserToolBar Add New Button
				     userToolBar.addNewButton.setVisible(false);
		          }
		          else
			         if (popupMenu.equals("Delete"))
			         {
			            userFormPanel.saveBtn.setVisible(false);
			            userFormPanel.deleteBtn.setVisible(true);
			            
			            // disable all the fields in the userFormPanel
					    disableFieldsUsers();
					      
				        menu = "";
				        
				        // Makes invisible the UserToolBar Add New Button
					    userToolBar.addNewButton.setVisible(false);
			         }
			}
		});
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        // Retrieving from the HRM Database the existing employees's information in the employeeTable Panel
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        loadingUsers();
                
        // MenuBar
        setJMenuBar(createUserMenuBar(userP, userLevelP));
        
        // Listener for the toolbar object
        userToolBar.setUserToolBarListener(new UserToolBarListener()
        {
			@Override
			public void addNewEventOccurred() 
			{	
				menu = "Users";
                submenu = "Add";
                
			   	// Makes visible the UserFormPanel
				userFormPanel.setVisible(true);
				userFormPanel.idField.setText("");
				
				// Makes visible the User Save Button
				userFormPanel.saveBtn.setVisible(true);
				
				// enable all the fields in the userFormPanel
	            enableFieldsUsers();
	            
				// Makes invisible the User Delete Button
				userFormPanel.deleteBtn.setVisible(false);
				
				// Makes visible the User Text Panel
				userTablePanel.setVisible(true);
			}

        });	
        
        // Listener for the User formPanel object
        userFormPanel.setUserFormListener(new UserFormListener() 
        {
        	// Method that is defined in the Interface: UserFormListener
        	@Override
			public boolean userFormEventOccurred(UserFormEvent e) 
        	{
	
        	   boolean validUser1=false;
        	   boolean validUser2=false;
        	   boolean validUser3=false;
        	   boolean validUser4=false;
        	   boolean validUser5=false;
        	   
        	   // Validation #1: Validate the User fields are not empty
        	   validUser1=userController.validateUserFields(e);
        	   
        	   // Validation #2: Validate the User is assigned to an Available Employee that
        	   // doesn't have a User yet
        	   validUser2=validateEmployee(e);
        	   
        	   // Validation #3: Validate the Password and Confirm Password are the same
        	   validUser3=userController.validatePassword(e);
        	   
        	   // Validation #4: Validate the UserName doesn't exist already for an 
        	   // existing user
        	   validUser4=validateUserName(e);
        	   
        	   // Validation #5: Validate the Email doesn't exist already for an 
        	   // existing user
        	   validUser5=validateEmail(e);
        	   
        	   if (validUser1 == false) 
        	   {
                  JOptionPane.showMessageDialog(UserFrame.this, "Error 31: All the User fields should be filled-in and not be empty.", "Validating a User", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else if (validUser2 == false) 
        	   {
                  JOptionPane.showMessageDialog(UserFrame.this, "Error 32: The Employee has already been assigned to another User. Please, select another available Employee.", "Validating a User", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else if (validUser3 == false) 
        	   {
                  JOptionPane.showMessageDialog(UserFrame.this, "Error 33: The Password and Confirm Password should be the same.", "Validating a User", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else if (validUser4 == false) 
        	   {
                  JOptionPane.showMessageDialog(UserFrame.this, "Error 34: The User Name has already been assigned to another User. Please, select another User Name", "Validating a User", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else if (validUser5 == false) 
        	   {
                  JOptionPane.showMessageDialog(UserFrame.this, "Error 35: The Email has already been assigned to another User. Please, select another Email", "Validating a User", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else
        	   {
        		
        	      // Assign to the object controller values
        	      userController.addUser(e);
        	   
        	      // Show the information from the userFormPanel fields 
        	      // into the UserTablePanel object
        	      userTablePanel.refresh();
        	   
		          //////////////////////////////////////////////////////////////////////
		          // Saving the data from Employee FormPanel into the HRM Database
		          /////////////////////////////////////////////////////////////////////
	           
			      try 
			      {
			         userController.save(e);
			      } 
			      catch (SQLException e2) 
			      {
			    	 String errorMessage = e2.getMessage(); 
			         JOptionPane.showMessageDialog(UserFrame.this, "Error 36: Occurred while saving a User. Error Message: "+errorMessage, "Saving a User", JOptionPane.ERROR_MESSAGE);	
			      }
	           
			      // Retrieving from the HRM Database the existing users's information in the userTable Panel
			      loadingUsers();
			   
			      return true;
        	   }
        	   
        	}

			@Override
			public boolean userFormDeleteEventOccurred(UserFormEvent e) 
			{
	   
			   boolean deleteUser=false;
		           
		       //////////////////////////////////////////////////////////
		       // Make some validations to the User before deleting it
		       //////////////////////////////////////////////////////////
		        	   
		       // Validation #1: Validate if the User can be deleted
			  // Depending on whether or not is a Supervisor of other employees
			  deleteUser=validateDeleteUser(e);
				   
		      if (deleteUser == false) 
	          {
	             JOptionPane.showMessageDialog(UserFrame.this, "Error 92: The user cannot be deleted because is a Supervisor of other Employees.", "Validating a User", JOptionPane.ERROR_MESSAGE);
	             return false;
	          }
		   	  else
	          {
				
	              // Assign to the object controller values
	              userController.addUser(e);
	        	   
	              // Show the information from the userFormPanel fields 
	              // into the UserTablePanel object
	              userTablePanel.refresh();
	        	   
			      //////////////////////////////////////////////////////////////////////
			      // Deleting data from the User FormPanel into the HRM Database
			      /////////////////////////////////////////////////////////////////////
	        	   
			      System.out.println("Deleting a User from the HRM Database ...");

			      try 
			      {
			         userController.delete(e);
			      } 
			      catch (SQLException e1) 
			      {
			         String errorMessage = e1.getMessage();  
			         JOptionPane.showMessageDialog(UserFrame.this, "Error 37: Occurred while deleting a User. Error Message: "+errorMessage, "Deleting a User", JOptionPane.ERROR_MESSAGE);	
			      }
		           
			      //userController.disconnect();
				   
			      // Retrieving from the HRM Database the existing users's information in the userTable Panel
			      loadingUsers();
			   
			      return true;
			   
	          }
			    
			}
			
        });
        
        // Listener for the Window
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
				
				System.exit(0);
			}
			
		});
        

		add(userToolBar, BorderLayout.NORTH);
        add(userFormPanel, BorderLayout.WEST);
        add(userTablePanel, BorderLayout.CENTER);
        
        
        // Set the Size of the Form
        pack();
		this.setSize(900, 400);
		
		// Set up that the windows cannot be resizable by the User
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
        //Display the window.
        this.setVisible(true);
   
	   	// Makes Not Visible the Panels
		userFormPanel.setVisible(false);
		userTablePanel.setVisible(true);
	}

	
	// Method for creating the Menu Bar, Menus and Items
	public JMenuBar createUserMenuBar(String userP, UserLevel userLevelP)
	{
		
		// Creates the Menu Bar //
		JMenuBar menuBar = new JMenuBar();
		
		// Creates the Menus //
		
		// Creates the HRM System Menu
		JMenu hrmSystemMenu = new JMenu("HRM System");
		
		// Creates the Items of the Menu
		JMenuItem signoutHrmSItem = new JMenuItem("Sign out");
		JMenuItem mainMenuHrmSItem = new JMenuItem("HRM Main Menu");
		JMenuItem exitHrmSItem = new JMenuItem("Exit");
		
		// Adds the items to the Menu
		hrmSystemMenu.add(signoutHrmSItem);
		hrmSystemMenu.addSeparator();
		hrmSystemMenu.add(mainMenuHrmSItem);
		hrmSystemMenu.addSeparator();
		hrmSystemMenu.add(exitHrmSItem);
		
		// Adds the Menus to the Menu Bar
		menuBar.add(hrmSystemMenu);
		
       // Listeners to the Menus //
		
		// Listener for the HRMSystem > Exit
		mainMenuHrmSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			   // Close the current window	
			   userFormPanel.setVisible(false);
			   userTablePanel.setVisible(true);
			   dispose();
			   System.gc();
			   
			   new MainFrame(userP, userLevelP);
			}
		});

		signoutHrmSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{

				// Shows a Dialog to confirm if you want to exit the application
				int action = JOptionPane.showConfirmDialog(
						UserFrame.this, "Do you really want to sign out the HRM System?", 
						"Confirm Sign out", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{

				   //Close the current MainFrame Window 	
				   setVisible(false);	
		           dispose();
				   // Calls the Java Garbage Collector to release memory
				   System.gc();
					   
				    // It displays a Window to connect to the HRM Software System
				    new LoginFrame();
					
			    } 
				
			}
		});
		// Listener for the HRMSystem > Exit
		exitHrmSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				// Shows a Dialog to confirm if you want to exit the application
				int action = JOptionPane.showConfirmDialog(
						UserFrame.this, "Do you really want to exit the HRM System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   WindowListener[] listeners = getWindowListeners();
					
				   for(WindowListener listener: listeners) 
				   {
					  // Calls the Windows Closing Event
				      listener.windowClosing(new WindowEvent(UserFrame.this, 0));
				   } 	
			    }
			}
		});
		
		return menuBar;
	}
	
	// This method will display all the values of the selected row in the userFormPanel
	public void refreshUser()
	{

	   // User Id
	   String idSel = userTablePanel.getUserStringColSel(0);
	   userFormPanel.idField.setText(idSel);

	   // User Name
	   String userNameSel = userTablePanel.getUserStringColSel(1);
	   userFormPanel.userNameField.setText(userNameSel);
     
	   // User Password
	   String passwordSel = userTablePanel.getUserStringColSel(2);
	   userFormPanel.passwordField.setText(passwordSel);
			      
	   // User Confirm Password
	   String confirmPasswordSel = userTablePanel.getUserStringColSel(2);
	   userFormPanel.confirmPasswordField.setText(confirmPasswordSel);
			      
	   // User Full Name
	   String employeeSel = userTablePanel.getUserStringColSel(3);
	   userFormPanel.employeeCombo.setSelectedItem(employeeSel);
			      
	   // User Email
	   String emailSel = userTablePanel.getUserStringColSel(4);
	  userFormPanel.emailField.setText(emailSel);
			      
	   // User Level
	   String userLevelSel = userTablePanel.getUserStringColSel(5);
	      	    
	   if (userLevelSel.equals("Admin"))
	      userFormPanel.userLevelCombo.setSelectedItem("Admin");
	   else
	      if (userLevelSel.equals("Manager"))
		     userFormPanel.userLevelCombo.setSelectedItem("Manager");
		  else
		     if (userLevelSel.equals("Employee"))
			    userFormPanel.userLevelCombo.setSelectedItem("Employee");				          
	}
	
   public void loadingUsers()
   {
      System.out.println("Loading the current Users from the HRM Database ...");

	  try 
	  {
	     userController.load();
		 userTablePanel.refresh();
	  } 
	  catch (SQLException e) 
	  {
		 String errorMessage = e.getMessage();
	     JOptionPane.showMessageDialog(UserFrame.this, "Error 39: Occurred while loading the Users. Error Message: "+errorMessage, "Loading Users", JOptionPane.ERROR_MESSAGE);	
	  }
		
   }
   
   public void disableFieldsUsers()
   {
      //Make all the fields not editable
      Color color = Color.LIGHT_GRAY;

      userFormPanel.userNameField.setBackground(color);
      userFormPanel.userNameField.setEditable(false);
      
      userFormPanel.passwordField.setBackground(color);	
      userFormPanel.passwordField.setEditable(false);
      
      userFormPanel.confirmPasswordField.setBackground(color);	
      userFormPanel.confirmPasswordField.setEditable(false);

      userFormPanel.employeeCombo.setBackground(color);
      userFormPanel.employeeCombo.setEnabled(false);

      userFormPanel.emailField.setBackground(color);
      userFormPanel.emailField.setEditable(false);

      userFormPanel.userLevelCombo.setBackground(color);
      userFormPanel.userLevelCombo.setEnabled(false);
      
   }
   
   public void enableFieldsUsers()
   {
      //Make all the fields editable
      Color color = Color.WHITE;

      userFormPanel.userNameField.setBackground(color);
      userFormPanel.userNameField.setEditable(true);
      
      userFormPanel.passwordField.setBackground(color);	
      userFormPanel.passwordField.setEditable(true);
      
      userFormPanel.confirmPasswordField.setBackground(color);	
      userFormPanel.confirmPasswordField.setEditable(true);

      userFormPanel.employeeCombo.setBackground(color);
      userFormPanel.employeeCombo.setEnabled(true);

      userFormPanel.emailField.setBackground(color);
      userFormPanel.emailField.setEditable(true);

      userFormPanel.userLevelCombo.setBackground(color);
      userFormPanel.userLevelCombo.setEnabled(true);

   }
   
   public boolean validateEmployee(UserFormEvent e)
   {
	  boolean validEmployee = false;
	  
      try 
	   {
	      validEmployee=userController.validateEmployee(e);
	   } 
	   catch (SQLException e1) 
	   {
		  String errorMessage = e1.getMessage();
	      JOptionPane.showMessageDialog(UserFrame.this, "Error 40: Occurred while validating an Employee. Error Message: "+errorMessage, "Validating an Employee", JOptionPane.ERROR_MESSAGE);	
	   }
      
      return validEmployee;	   
   }
   
   public boolean validateUserName(UserFormEvent e)
   {
	  boolean validUserName = false;
	  
      try 
	   {
	      validUserName=userController.validateUserName(e);
	   } 
	   catch (SQLException e1) 
	   {
		  String errorMessage = e1.getMessage();
	      JOptionPane.showMessageDialog(UserFrame.this, "Error 41: Occurred while validating the UserName. Error Message: "+errorMessage, "Validating a User", JOptionPane.ERROR_MESSAGE);	
	   }
      
      //userController.disconnect(); 
      
      return validUserName;	   
   }
   
   public boolean validateEmail(UserFormEvent e)
   {
	  boolean validEmail = false;
	  
      try 
	   {
	      validEmail=userController.validateEmail(e);
	   } 
	   catch (SQLException e1) 
	   {
		  String errorMessage = e1.getMessage();
	      JOptionPane.showMessageDialog(UserFrame.this, "Error 42: Occurred while validating the Email. Error Message: "+errorMessage, "Validating a User", JOptionPane.ERROR_MESSAGE);	
	   }
      
      return validEmail;	   
   }
   public boolean validateDeleteUser(UserFormEvent e)
   {
	  boolean deleteUsr = false;
	  
      try 
	   {
	      deleteUsr=userController.validateDeleteUser(e);
	   } 
	   catch (SQLException e1) 
	   {
		  String errorMessage = e1.getMessage();
		  JOptionPane.showMessageDialog(UserFrame.this, "Error 93: Occurred while validating if the User can be deleted. Error Message: "+errorMessage, "Validating a User", JOptionPane.ERROR_MESSAGE);
	   }
      
      //userController.disconnect(); 
      
      return deleteUsr;	   
   }   
   
}