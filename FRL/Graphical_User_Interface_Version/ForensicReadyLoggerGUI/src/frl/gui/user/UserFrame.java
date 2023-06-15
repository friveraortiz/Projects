package frl.gui.user;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import frl.controller.user.UserController;
import frl.gui.main.MainFrame;
import frl.gui.toolBar.ToolBar;
import frl.gui.toolBar.ToolBarListener;
import frl.model.user.UserLevel;

//Package #6
//Class #5
public class UserFrame extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	// Define the attributes for the class
    private ToolBar toolBar; 
    private UserFormPanel userFormPanel;
 
    // Define an attribute for the controller
    private UserController userController;
    
    // Define an attribute for the table
    private UserTablePanel userTablePanel;
    public static String menu="";
    public static String submenu="";
    
    // Constructor of the UserFrame class
    // Method #1
	public UserFrame (String userP, String menuP, UserLevel userLevelP, 
			          String databaseConfigFile, String featuresConfigFile) 
	{
        super("User");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        
        // The Main Form creates the objects 
        userFormPanel = new UserFormPanel();
        userTablePanel = new UserTablePanel();
        
        if (menuP.equals("User")) 
        {
        	toolBar = new ToolBar(userP, menuP, userLevelP);
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
			      
			      // Makes invisible the ToolBar Add New Button
			      ToolBar.addNewButton.setVisible(false);
			   }
		       else
		          if (popupMenu.equals("Modify"))
		          {
		             userFormPanel.saveBtn.setVisible(true);
		             userFormPanel.deleteBtn.setVisible(false);
		             
		             // enable all the fields in the userFormPanel
		             enableFieldsUsers();
		             
			         menu = "";
			         
			         // Makes invisible the ToolBar Add New Button
				     ToolBar.addNewButton.setVisible(false);
		          }
		          else
			         if (popupMenu.equals("Delete"))
			         {
			            userFormPanel.saveBtn.setVisible(false);
			            userFormPanel.deleteBtn.setVisible(true);
			            
			            // disable all the fields in the userFormPanel
					    disableFieldsUsers();
					      
				        menu = "";
				        
				        // Makes invisible the ToolBar Add New Button
					    ToolBar.addNewButton.setVisible(false);
			         }
			}
		});
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        // Retrieving from the Forensic Ready Logger Database the existing users in the employeeTable Panel
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        loadingUsers(databaseConfigFile);
                
        // MenuBar
        setJMenuBar(createUserMenuBar(userP, userLevelP, databaseConfigFile, featuresConfigFile));
        
        // Listener for the toolbar object
        toolBar.setUserToolBarListener(new ToolBarListener()
        {
			@Override
			public void addNewEventOccurred() 
			{	
				menu = "User";
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
        	   String errorMessage1="";
        	   
        	   // Validation #1: Validate the User fields are not empty
        	   validUser1=userController.validateUserFields(e);
        	   
        	   // Validation #2: Validate the Password and Confirm Password are the same
        	   validUser2=userController.validatePassword(e);
        	   
        	   // Validation #3: Validate the UserName doesn't exist already for an 
        	   // existing user
        	   validUser3=validateUserName(e, databaseConfigFile);
        	   
        	   if (validUser1 == false) 
        	   {
        		  // Error #1 
        		  errorMessage1 =  "Error 6511: All the User fields should have a value be and not be empty.";
                  JOptionPane.showMessageDialog(UserFrame.this, errorMessage1, "Validating a User", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else if (validUser2 == false) 
        	   {
        		  // Error #2  
        		  errorMessage1 = "Error 6512: The Password and the Confirm Password fields should have the same value.";
                  JOptionPane.showMessageDialog(UserFrame.this, errorMessage1, "Validating a User", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else if (validUser3 == false) 
        	   {
        		  // Error #3
        		  errorMessage1 = "Error 6513: The User Name has already been assigned to another User. Please, select another User Name";
                  JOptionPane.showMessageDialog(UserFrame.this, errorMessage1, "Validating a User", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else
        	   {
        		
        	      // Assign to the object controller values
        	      userController.addUser(e);
        	   
        	      // Show the information from the userFormPanel fields 
        	      // into the UserTablePanel object
        	      userTablePanel.refresh();
        	   
		          // Saving the data from User FormPanel into the Forensic Ready Logger Database
			      try 
			      {
			         userController.save(e, databaseConfigFile);
			      } 
			      catch (Exception e1) 
			      {
			    	 // Error #4
			    	 errorMessage1 = e1.getMessage();
			         JOptionPane.showMessageDialog(UserFrame.this, errorMessage1, "Saving a User", JOptionPane.ERROR_MESSAGE);	
			      }
	           
			      // Retrieving from the FRL Database the existing users's information in the userTable Panel
			      loadingUsers(databaseConfigFile);
			   
			      return true;
        	   }
        	   
        	}

			@Override
			public boolean userFormDeleteEventOccurred(UserFormEvent e) 
			{
	           String errorMessage1="";
			   //boolean deleteUser=false;
		           
		       //////////////////////////////////////////////////////////
		       // Make some validations to the User before deleting it
		       //////////////////////////////////////////////////////////
		        	   
		       // Validation #1: Validate if the User can be deleted
			   /*deleteUser=validateDeleteUser(e);
				   
		       if (deleteUser == false) 
	           {
	              JOptionPane.showMessageDialog(UserFrame.this, "Error 92: The user cannot be deleted because is a Supervisor of other Employees.", "Validating a User", JOptionPane.ERROR_MESSAGE);
	              return false;
	           }
		   	   else
	           {*/
				
	              // Assign to the object controller values
	              userController.addUser(e);
	        	   
	              // Show the information from the userFormPanel fields 
	              // into the UserTablePanel object
	              userTablePanel.refresh();
	        	   
			      // Deleting data from the User FormPanel into the FRL Database
	        	   
			      System.out.println("Deleting a User from the Forensic Ready Logger System ...");

			      try 
			      {
			         userController.delete(e, databaseConfigFile);
			      } 
			      catch (Exception e1) 
			      {
			    	 // Error #5 
			    	 errorMessage1 = e1.getMessage();
			         JOptionPane.showMessageDialog(UserFrame.this, errorMessage1, "Deleting a User", JOptionPane.ERROR_MESSAGE);	
			      }
		           
			      // Retrieving from the Forensic Ready Logger Database the existing users's information in the userTable Panel
			      loadingUsers(databaseConfigFile);
			   
			      return true;
			   
	          //}
			    
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
        

		add(toolBar, BorderLayout.NORTH);
        add(userFormPanel, BorderLayout.WEST);
        add(userTablePanel, BorderLayout.CENTER);
        
        
        // Set the Size of the Form
        pack();
		this.setSize(900, 400);
		
		// Set the location in the center of the screen
		setLocationRelativeTo(null);
		
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
	// Method #2
	public JMenuBar createUserMenuBar(String userP, UserLevel userLevelP, 
			                          String databaseConfigFile, String featuresConfigFile)
	{
		
		// Creates the Menu Bar //
		JMenuBar menuBar = new JMenuBar();
		
		// Creates the Menus //
		
		// Creates the Forensic Ready Logger System Menu
		JMenu frlSystemMenu = new JMenu("Forensic Ready Logger System");
		
		// Creates the Items of the Menu
		JMenuItem mainMenuFrlSItem = new JMenuItem("Main Menu");
        // Set the tooltip text in the JMenuItem Object 
		mainMenuFrlSItem.setToolTipText("Opens the Forensic-Ready Logger System Main Menu");
		JMenuItem signoutFrlSItem = new JMenuItem("Sign Out");
        // Set the tooltip text in the JMenuItem Object 
		signoutFrlSItem.setToolTipText("Disconnects the current User from the Forensic-Ready Logger System");
		JMenuItem exitFrlSItem = new JMenuItem("Exit");
        // Set the tooltip text in the JMenuItem Object 
		exitFrlSItem.setToolTipText("Closes the Forensic-Ready Logger System");
		
		// Adds the items to the Menu
		frlSystemMenu.add(mainMenuFrlSItem);
		frlSystemMenu.addSeparator();
		
		frlSystemMenu.add(signoutFrlSItem);
		frlSystemMenu.addSeparator();

		frlSystemMenu.add(exitFrlSItem);
		frlSystemMenu.addSeparator();
		
		// Adds the Menus to the Menu Bar
		menuBar.add(frlSystemMenu);
		
       // Listeners to the Menus //
		
		// Listener for the HRMSystem > Exit
		mainMenuFrlSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			   // Close the current window	
			   userFormPanel.setVisible(false);
			   userTablePanel.setVisible(true);
			   dispose();
			   System.gc();
			   
			   new MainFrame(userP, userLevelP, databaseConfigFile, featuresConfigFile);
			}
		});

		signoutFrlSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{

				// Shows a Dialog to confirm if you want to exit the application
				int action = JOptionPane.showConfirmDialog(
						UserFrame.this, "Do you really want to Sign Out the Forensic Ready Logger System?", 
						"Confirm Sign Out", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{

				   //Close the current MainFrame Window 	
				   setVisible(false);	
		           dispose();
		           
				   // Calls the Java Garbage Collector to release memory
				   System.gc();
					   
				   // It displays a Window to connect to the FRL Software System
				   new LoginFrame(databaseConfigFile, featuresConfigFile);
					
			    } 
				
			}
		});
		
		// Listener for the FRLSystem > Exit
		exitFrlSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				// Shows a Dialog to confirm if you want to exit the application
				int action = JOptionPane.showConfirmDialog(
						UserFrame.this, "Do you really want to exit the Forensic Ready Logger System?", 
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
	// Method #3
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
			      
	   // User Level
	   String userLevelSel = userTablePanel.getUserStringColSel(5);
	   
	   if (userLevelSel.equals("Admin"))
	      userFormPanel.userLevelCombo.setSelectedItem("Admin");
	   else
	      if (userLevelSel.equals("Developer"))
		     userFormPanel.userLevelCombo.setSelectedItem("Developer");
		  else
		     if (userLevelSel.equals("Security"))
			    userFormPanel.userLevelCombo.setSelectedItem("Security");	 
	   
	   userFormPanel.userNameField.requestFocusInWindow();
	}
	
   // Method #4
   public void loadingUsers(String databaseConfigFile)
   {
	  String errorMessage1="";
	  
      //System.out.println("Loading the current Users from the Forensic Ready Logger Database ...");

	  try 
	  {
	     userController.load(databaseConfigFile);
		 userTablePanel.refresh();
	  } 
	  catch (Exception e) 
	  {
		 // Error #1 
		 errorMessage1 = e.getMessage();
	     JOptionPane.showMessageDialog(UserFrame.this, errorMessage1, "Loading Users", JOptionPane.ERROR_MESSAGE);	
	  }
		
   }
   
   // Method #5
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

      userFormPanel.userLevelCombo.setBackground(color);
      userFormPanel.userLevelCombo.setEnabled(false);
      
   }
   
   // Method #6
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

      userFormPanel.userLevelCombo.setBackground(color);
      userFormPanel.userLevelCombo.setEnabled(true);
      
      userFormPanel.userNameField.requestFocusInWindow();

   }
   
   // Method #7
   public boolean validateUserName(UserFormEvent e, String databaseConfigFile)
   {
	  boolean validUserName = false;
	  String errorMessage1="";
	  
      try 
	   {
	      validUserName = userController.validateUserName(e, databaseConfigFile);
	   } 
	   catch (Exception e1) 
	   {
		  // Error #1  
		  errorMessage1 = e1.getMessage();
	      JOptionPane.showMessageDialog(UserFrame.this, errorMessage1, "Validating a User", JOptionPane.ERROR_MESSAGE);	
	   }
      
      return validUserName;	   
   }
  
   // Method #8
   public boolean validateDeleteUser(UserFormEvent e, String databaseConfigFile)
   {
	  boolean deleteUsr = false;
	  String errorMessage = "";
	  
      try 
	   {
	      deleteUsr=userController.validateDeleteUser(e, databaseConfigFile);
	   } 
	   catch (Exception e1) 
	   {
		  // Error #1 
		  errorMessage = e1.getMessage();
		  JOptionPane.showMessageDialog(UserFrame.this, errorMessage, "Validating a User", JOptionPane.ERROR_MESSAGE);
	   }
      
      return deleteUsr;	   
   }   
   
}