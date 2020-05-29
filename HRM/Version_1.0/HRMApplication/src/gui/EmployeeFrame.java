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
import controller.EmployeeController;
import model.UserLevel;


public class EmployeeFrame extends JFrame 
{
	// Define the attributes for the class
    private UserToolBar userToolBar; 
    private EmployeeFormPanel employeeFormPanel;
 
    // Define an attribute for the controller
    private EmployeeController employeeController;
    
    // Define an attribute for the table
    private EmployeeTablePanel employeeTablePanel;
    
    public static String menu="";
    public static String submenu="";
    
    // Constructor of the EmployeeFrame class
	public EmployeeFrame (String userP, String menuP, UserLevel userLevelP) 
	{
        super("Employees Main Menu HRM");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        
        // The Main Form creates the objects 
        employeeFormPanel = new EmployeeFormPanel();
        employeeTablePanel = new EmployeeTablePanel();
        
        if (menuP.equals("Employees")) 
        {
        	
        	userToolBar = new UserToolBar(userP, menuP, userLevelP);
        }
        
        // Define an object for the Controller Class
        employeeController = new EmployeeController();
        
        // Define an object for the EmployeeTablePanel Class
        employeeTablePanel.setDataEmployee(employeeController.getEmployees());
        
        // Add a new Listener for the EmployeeTablePanel
        employeeTablePanel.setEmployeeTableListener(new EmployeeTableListener() 
        {
			@Override
			public void employeeDeleted(int row) 
			{
			   employeeController.deleteEmployee(row);
			}

			@Override
			public void employeeTableEventOccurred(int id, String popupMenu) 
			{
				
			   // Show the Employee Form Panel
		       employeeFormPanel.setVisible(true);
		     	   
	     	   // Clean the current values in the EmployeeFormPanel
	     	   employeeFormPanel.cleanFields();
	     	   
	     	   // Get all the values from the selected row into the EmployeeFormPanel
			   refreshEmployee();
			   
			   if (popupMenu.equals("View"))
			   {
			      employeeFormPanel.saveBtn.setVisible(false);
			      employeeFormPanel.deleteBtn.setVisible(false);
			      
			      // disable all the fields in the EmployeeFormPanel
			      disableFieldsEmployee();
			      
			      menu = "";
			      
			      // Makes invisible the UserToolBar Add New Button
			      userToolBar.addNewButton.setVisible(false);
			      
			   }
		       else
		          if (popupMenu.equals("Modify"))
		          {
		             employeeFormPanel.saveBtn.setVisible(true);
		             employeeFormPanel.deleteBtn.setVisible(false);
		             
		             // enable all the fields in the EmployeeFormPanel
		             enableFieldsEmployee();
		             
			         menu = "";
			         
			         // Makes invisible the UserToolBar Add New Button
			         userToolBar.addNewButton.setVisible(false);
		          }
		          else
			         if (popupMenu.equals("Delete"))
			         {
			            employeeFormPanel.saveBtn.setVisible(false);
			            employeeFormPanel.deleteBtn.setVisible(true);
			            
			            // disable all the fields in the EmployeeFormPanel
					    disableFieldsEmployee();
					      
				        menu = "";
				        
				        // Makes invisible the UserToolBar Add New Button
				        userToolBar.addNewButton.setVisible(false);
				        
			         }
			}
		});
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        // Retrieving from the HRM Database the existing employees's information in the employeeTable Panel
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        loadingEmployees();
        
        // MenuBar
        setJMenuBar(createEmployeeMenuBar(userP, userLevelP));
        
        // Listener for the toolbar object
        userToolBar.setUserToolBarListener(new UserToolBarListener()
        {
			@Override
			public void addNewEventOccurred() 
			{	
				menu = "Employees";
                submenu = "Add";
                
			   	// Makes visible the Employee Form Panel
				employeeFormPanel.setVisible(true);
				employeeFormPanel.idField.setText("");
				
				// Makes visible the Employee Save Button
				employeeFormPanel.saveBtn.setVisible(true);
				
				// enable all the fields in the Employee FormP anel
	            enableFieldsEmployee();
				
				// Makes invisible the Employee Delete Button
				employeeFormPanel.deleteBtn.setVisible(false);
				
				// Makes visible the Employee Table Panel
				employeeTablePanel.setVisible(true);
			}

        });	
        
        // Listener for the formPanel object
        employeeFormPanel.setEmployeeFormListener(new EmployeeFormListener() 
        {
        	
        	// Method that is defined in the Interface: EmployeeFormListener
        	@Override
			public boolean employeeFormEventOccurred(EmployeeFormEvent e) 
        	{
	
        	   boolean validEmployee1=false;
        	   boolean validEmployee2=false;
        	   boolean validEmployee3=false;
        	   boolean validEmployee4=false;
        	   boolean validEmployee5=false;
        		
               //////////////////////////////////////////////////////////
        	   // Make some validations to the Employee before saving it
        	   //////////////////////////////////////////////////////////
        	   
        	   // Validation #1: Validate the Employee fields are not empty
        	   validEmployee1=employeeController.validateEmployeeFields(e);      	   

        	   // Validation #2: Validate the Supervisor of the Employee
        	   validEmployee2=employeeController.validateSupervisor(e);
        	   System.out.println("validEmployee2: "+validEmployee2);
        	   
        	   // Validation #3: Validate the DOB and Joined Dates of the Employee
        	   validEmployee3=employeeController.validateDobJdDates(e);
        	   
        	   // Validation #4: Validate the Joined and Terminated Dates of the Employee
        	   validEmployee4=employeeController.validateJdTdDates(e);

        	   // Validation #5: Validate the FullName doesn't exist already for an 
        	   // existing Employee
        	   validEmployee5=validateFullName(e);
        	   
        	   if (validEmployee1 == false) 
        	   {
                  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 17: All the Employee Fields should be filled-in and not be empty.", "Validating an Employee", JOptionPane.ERROR_MESSAGE);

                  return false;
               }
        	   else if (validEmployee2 == false) 
        	   {
                  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 18: The Employee Full Name and the Supervisor name cannot be the same.", "Validating an Employee", JOptionPane.ERROR_MESSAGE);

                  return false;
               }
        	   else if (validEmployee3 == false) 
        	   {
                  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 19: The Birth date and Joined date cannot be the same OR the Joined Date should be Greater than the Birth date.", "Validating an Employee", JOptionPane.ERROR_MESSAGE);

                  return false;
               }
        	   else if (validEmployee4 == false) 
        	   {
                  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 20: The Joined Date and Terminated Date cannot be the same OR the Terminated Date should be Greater than the Joined Date.", "Validating an Employee", JOptionPane.ERROR_MESSAGE);

                  return false;
               }
        	   else if (validEmployee5 == false) 
        	   {
                  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 21: The First Name, Middle Name and Last Name have been already assigned to another Employee. Please, select other First Name, Middle Name and Last Name.", "Validating an Employee", JOptionPane.ERROR_MESSAGE);

                  return false;
               }
        	   else
        	   {
        	   
                  // Assign to the object controller values
            	  employeeController.addEmployee(e);
            	   
            	  // Show the information from the EmployeeFormPanel fields 
            	  // into the EmployeeTablePanel object
            	  employeeTablePanel.refresh();
     
        		  //////////////////////////////////////////////////////////////////////
		          // Saving the data from Employee FormPanel into the HRM Database
		          /////////////////////////////////////////////////////////////////////
        	   
			      System.out.println("Saving an Employee into HRM Database ...");
			      try 
			      {
			         employeeController.save(e);
			      } 
			      catch (SQLException e1) 
			      {
				     String errorMessage = e1.getMessage();
			         JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 22: Occurred while saving an Employee. Error Message: "+errorMessage, "Saving an Employee", JOptionPane.ERROR_MESSAGE);
			      }
			   
			      // Retrieving from the HRM Database the existing employees's information in the employeeTable Panel
			      loadingEmployees();
			      
			      return true;
        	   }
        	   
        	}

			@Override
			public boolean employeeFormDeleteEventOccurred(EmployeeFormEvent e) 
			{
			
			   boolean validDeleteEmp1=false;
			   boolean validDeleteEmp2=false;
	           
	           //////////////////////////////////////////////////////////
	           // Make some validations to the Employee before deleting it
	           //////////////////////////////////////////////////////////
	        	   
	           // Validation #1: Validate if the Employee can be deleted
			   // Depending on if it has an user or travel request associated
			   validDeleteEmp1=validateDeleteEmployee1(e);
			   
			   // Validation #2: Validate if the Employee can be deleted
			   // Depending on if was assigned as Supervisor of other employees
			   validDeleteEmp2=validateDeleteEmployee2(e);
	           
	           if (validDeleteEmp1 == false) 
        	   {
                  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 23: The employee cannot be deleted because has associated either an User and/or some Travel Requests.", "Validating an Employee", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
	           else
        	      if (validDeleteEmp2 == false) 
    	          {
                     JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 90: The employee cannot be deleted because is a supervisor of other Employees.", "Validating an Employee", JOptionPane.ERROR_MESSAGE);
                     return false;
                  }
        	   else
        	   {
	        	   
		           // Assign to the object controller values
		           employeeController.addEmployee(e);
		        	   
		           // Show the information from the EmployeeFormPanel fields 
		           // into the EmployeeTablePanel object
		           employeeTablePanel.refresh();
		        	   
				   //////////////////////////////////////////////////////////////////////
				   // Deleting data from the Employee FormPanel into the HRM Database
				   /////////////////////////////////////////////////////////////////////
		        	   
				   System.out.println("Deleting an Employee from the HRM Database ...");
			           
				   try 
				   {
				      employeeController.delete(e);
				   } 
				   catch (SQLException e1) 
				   {
				      String errorMessage = e1.getMessage();
					  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 24: Occurred while deleting an Employee. Error Message: "+errorMessage, "Deleting an Employee", JOptionPane.ERROR_MESSAGE);	
				   }
	
				   // Retrieving from the HRM Database the existing employees's information in the employeeTable Panel
				   loadingEmployees();
				   
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
        add(employeeFormPanel, BorderLayout.WEST);
        add(employeeTablePanel, BorderLayout.CENTER);
        
        
        // Set the Size of the Form
        pack();
		this.setSize(1150, 650);
		
		// Set up that the windows cannot be resizable by the User
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
        //Display the window.
        this.setVisible(true);
   
	   	// Makes Not Visible the Panels
		employeeFormPanel.setVisible(false);
		employeeTablePanel.setVisible(true);
	}
	
	// Method for creating the Menu Bar, Menus and Items
	public JMenuBar createEmployeeMenuBar(String userP, UserLevel userLevelP)
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
			   employeeFormPanel.setVisible(false);
			   employeeTablePanel.setVisible(true);
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
						EmployeeFrame.this, "Do you really want to sign out the HRM System?", 
						"Confirm Sign out", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{

				   // Close the current MainFrame Window 	
				   setVisible(false);	
		           dispose();
		           
				   // Calls the Java Garbage Collector to release memory
				   System.gc();
					   
				    // It displays a Window to connect to the HRM System
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
						EmployeeFrame.this, "Do you really want to exit the HRM System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   WindowListener[] listeners = getWindowListeners();
					
				   for(WindowListener listener: listeners) 
				   {
					  // Calls the Windows Closing Event
				      listener.windowClosing(new WindowEvent(EmployeeFrame.this, 0));
				   } 	
			    }
			}
		});
		
		return menuBar;
	}
	
	// This method will display all the values of the selected row in the EmployeeFormPanel
	public void refreshEmployee()
	{
		
	   // Employee Id
	   String idSel = employeeTablePanel.getEmployeeStringColSel(0);
	   employeeFormPanel.idField.setText(idSel);
	               
	   // Employee Number
	   String numberSel = employeeTablePanel.getEmployeeStringColSel(1);
	   employeeFormPanel.numberField.setText(numberSel);
		           
	   // Employee First Name
	   String firstNameSel = employeeTablePanel.getEmployeeStringColSel(2);
	   employeeFormPanel.firstNameField.setText(firstNameSel);
			      
	   // Employee Middle Name
	   String middleNameSel = employeeTablePanel.getEmployeeStringColSel(3);
	   employeeFormPanel.middleNameField.setText(middleNameSel); 
			      
	   // Employee Last Name
	   String lastNameSel = employeeTablePanel.getEmployeeStringColSel(4);
	   employeeFormPanel.lastNameField.setText(lastNameSel);
			      
	   // Employee DOB
	   String dobSel = employeeTablePanel.getEmployeeStringColSel(5);
	   employeeFormPanel.dobField.setText(dobSel);  
	  		      
	   // Employee Gender
	   String genderSel = employeeTablePanel.getEmployeeStringColSel(6);
	        	  
	   if (genderSel.equals("Male"))
	      employeeFormPanel.maleRadio.setSelected(true);
	   else
	      employeeFormPanel.femaleRadio.setSelected(true);  
			          
	   // Employee Marital Status
	   String maritalStaSel = employeeTablePanel.getEmployeeStringColSel(7);
	      	    
	   if (maritalStaSel.equals("Single"))
	      employeeFormPanel.maritalStatusCombo.setSelectedIndex(0);
	   else
	      if (maritalStaSel.equals("Married"))
		     employeeFormPanel.maritalStatusCombo.setSelectedIndex(1);
		  else
		     if (maritalStaSel.equals("Divorced"))
			    employeeFormPanel.maritalStatusCombo.setSelectedIndex(2);
		     else
			    if (maritalStaSel.equals("Widowed"))
				   employeeFormPanel.maritalStatusCombo.setSelectedIndex(3);				          

	   // Employee Mobile Phone
	   String mobilePhoneSel = employeeTablePanel.getEmployeeStringColSel(8);
	   employeeFormPanel.mobilePhoneField.setText(mobilePhoneSel);
					
	   // Employee Joined Date
	   String joinedDateSel = employeeTablePanel.getEmployeeStringColSel(9);
	   employeeFormPanel.joinedDateField.setText(joinedDateSel);  
			      
	   // Employee Terminated Date
	   String termDateSel = employeeTablePanel.getEmployeeStringColSel(10);
	   employeeFormPanel.terminatedDateField.setText(termDateSel);  
			      
	   // Employee Job Title
	   String jobTitleSel = employeeTablePanel.getEmployeeStringColSel(11);
	   employeeFormPanel.jobTitleField.setText(jobTitleSel);
			      
	   // Employee Department
	   String departmentSel = employeeTablePanel.getEmployeeStringColSel(12);
	   employeeFormPanel.departmentField.setText(departmentSel);  
			  
	   // Employee Supervisor
	   String supervisorSel = employeeTablePanel.getEmployeeStringColSel(13);
	   employeeFormPanel.supervisorCombo.setSelectedItem(supervisorSel);

	}
	
   public void loadingEmployees()
   {
      System.out.println("Loading the current Employees from the HRM Database ...");
	  try 
	  {
	     employeeController.load();
		 employeeTablePanel.refresh();
	  } 
	  catch (SQLException e) 
	  {
		 String errorMessage = e.getMessage(); 
	     JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 26: Occurred while loading the Employees. Error Message: "+errorMessage, "Loading Employees", JOptionPane.ERROR_MESSAGE);	
	  }
		
   }
	
   public void disableFieldsEmployee()
   {
      //Make all the fields not editable
      Color color = Color.LIGHT_GRAY;

      employeeFormPanel.firstNameField.setBackground(color);	
      employeeFormPanel.firstNameField.setEditable(false);

      employeeFormPanel.middleNameField.setBackground(color);
      employeeFormPanel.middleNameField.setEditable(false);

      employeeFormPanel.lastNameField.setBackground(color);
      employeeFormPanel.lastNameField.setEditable(false);

      employeeFormPanel.dobField.setBackground(color);
      employeeFormPanel.dobField.setEditable(false);

      employeeFormPanel.maleRadio.setEnabled(false);
      employeeFormPanel.femaleRadio.setEnabled(false);

      employeeFormPanel.maritalStatusCombo.setBackground(color);
      employeeFormPanel.maritalStatusCombo.setEnabled(false);
      
      employeeFormPanel.mobilePhoneField.setBackground(color);
      employeeFormPanel.mobilePhoneField.setEditable(false);

      employeeFormPanel.joinedDateField.setBackground(color);
      employeeFormPanel.joinedDateField.setEditable(false);

      employeeFormPanel.terminatedDateField.setBackground(color);
      employeeFormPanel.terminatedDateField.setEditable(false);

      employeeFormPanel.jobTitleField.setBackground(color);
      employeeFormPanel.jobTitleField.setEditable(false);

      employeeFormPanel.departmentField.setBackground(color);
      employeeFormPanel.departmentField.setEditable(false);

      employeeFormPanel.supervisorCombo.setBackground(color);
      employeeFormPanel.supervisorCombo.setEnabled(false);

   }
   
   public void enableFieldsEmployee()
   {
      //Make all the fields editable
      Color color = Color.WHITE;

      employeeFormPanel.firstNameField.setBackground(color);	
      employeeFormPanel.firstNameField.setEditable(true);

      employeeFormPanel.middleNameField.setBackground(color);
      employeeFormPanel.middleNameField.setEditable(true);

      employeeFormPanel.lastNameField.setBackground(color);
      employeeFormPanel.lastNameField.setEditable(true);

      employeeFormPanel.dobField.setBackground(color);
      employeeFormPanel.dobField.setEditable(true);

      employeeFormPanel.maleRadio.setEnabled(true);
      employeeFormPanel.femaleRadio.setEnabled(true);

      employeeFormPanel.maritalStatusCombo.setBackground(color);
      employeeFormPanel.maritalStatusCombo.setEnabled(true);
      
      employeeFormPanel.mobilePhoneField.setBackground(color);
      employeeFormPanel.mobilePhoneField.setEditable(true);

      employeeFormPanel.joinedDateField.setBackground(color);
      employeeFormPanel.joinedDateField.setEditable(true);

      employeeFormPanel.terminatedDateField.setBackground(color);
      employeeFormPanel.terminatedDateField.setEditable(true);

      employeeFormPanel.jobTitleField.setBackground(color);
      employeeFormPanel.jobTitleField.setEditable(true);

      employeeFormPanel.departmentField.setBackground(color);
      employeeFormPanel.departmentField.setEditable(true);

      employeeFormPanel.supervisorCombo.setBackground(color);
      employeeFormPanel.supervisorCombo.setEnabled(true);

   }
   
   public boolean validateDeleteEmployee1(EmployeeFormEvent e)
   {
	  boolean validDeleteEmp = false;
	  
      try 
	   {
	      validDeleteEmp=employeeController.validateDeleteEmployee1(e);
	   } 
	   catch (SQLException e1) 
	   {
		  String errorMessage = e1.getMessage();
		  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 27: Occurred while validating if the Employee can be deleted. Error Message: "+errorMessage, "Validating an Employee", JOptionPane.ERROR_MESSAGE);
	   }
      
      return validDeleteEmp;	   
   } 
   
   public boolean validateDeleteEmployee2(EmployeeFormEvent e)
   {
	  boolean validDeleteEmp = false;
	  
      try 
	   {
	      validDeleteEmp=employeeController.validateDeleteEmployee2(e);
	   } 
	   catch (SQLException e1) 
	   {
		  String errorMessage = e1.getMessage();
		  JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 91: Occurred while validating if the Employee can be deleted. Error Message: "+errorMessage, "Validating an Employee", JOptionPane.ERROR_MESSAGE);
	   }
      
      return validDeleteEmp;	   
   } 
 
   public boolean validateFullName(EmployeeFormEvent e)
   {
	  boolean validFullName = false;
	  
      try 
	   {
	      validFullName=employeeController.validateFullName(e);
	   } 
	   catch (SQLException e1) 
	   {
		  String errorMessage = e1.getMessage();
	      JOptionPane.showMessageDialog(EmployeeFrame.this, "Error 28: Occurred while validating the Full Name. Error Message: "+errorMessage, "Validating an Employee", JOptionPane.ERROR_MESSAGE);	
	   }
      
      return validFullName;	   
   } 
   
}