package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import controller.UserController;
import model.Employee;

public class UserFormPanel extends JPanel 
{
   // Define the Attributes of the Class //	
	
   // Define Labels	//	
   private JLabel idLabel;
   private JLabel userNameLabel;
   private JLabel passwordLabel;
   private JLabel confirmPasswordLabel;
   private JLabel employeeLabel;
   private JLabel emailLabel;
   private JLabel userLevelLabel;
 
   
   // Define Text Fields //

   // Personal Information	
   public JTextField idField;
   public JTextField userNameField;	
   public JPasswordField passwordField;
   public JPasswordField confirmPasswordField;
   public JTextField emailField;
   
   // Define ComboBox //
   public JComboBox<String> employeeCombo;
   public JComboBox<String> userLevelCombo;

   
   // Buttons //
   public JButton saveBtn;
   public JButton deleteBtn;
   public JButton cleanBtn;
   public JButton cancelBtn;
   
   // Listener //
   private UserFormListener userFormListener;
   
   // Define an attribute for the controller
   private UserController userController;
   
          
   String titleBorder="";
   
   // Constructor of the UserFormPanel Class
   public UserFormPanel() 
   {

		Dimension dim = getPreferredSize();
		
		// Set the Size of the Form
		dim.height = 400;
		dim.width = 900;
		setPreferredSize(dim);
		
		// Define the Objects of the class //
		
        // Define an object for the Controller Class
        userController = new UserController();
		
		// JLabel
		
		idLabel = new JLabel("User Id: ");
	    userNameLabel = new JLabel("User Name: ");	
	    passwordLabel = new JLabel("Password: ");
	    confirmPasswordLabel = new JLabel("Confirm Password: ");
	    employeeLabel = new JLabel("Employee: "); 
	    emailLabel = new JLabel("Email: "); 
	    userLevelLabel = new JLabel("User Level: "); 

		// JTextField

	    // Define Text Fields //
 
	    idField = new JTextField(10); 
	    //Make the idField not editable
	    Color color = Color.LIGHT_GRAY;
	    idField.setBackground(color);
	    idField.setEditable(false);
	    
		color = Color.WHITE;
		userNameField = new JTextField(50); 
		userNameField.setBackground(color);

		passwordField = new JPasswordField(50);
		passwordField.setEchoChar('*');
		passwordField.setBackground(color);
		
		confirmPasswordField = new JPasswordField(50);
		confirmPasswordField.setEchoChar('*');
		confirmPasswordField.setBackground(color);
		
		employeeCombo = new JComboBox<String>(); 
		employeeCombo.setBackground(color);

		emailField = new JTextField(50); 
		emailField.setBackground(color);
		
		userLevelCombo = new JComboBox<String>(); 
		userLevelCombo.setBackground(color);
		
		// Setup of Employee JCombo box
		loadEmployees();
	    
		// Setup of the UserLevel JCombo box
		DefaultComboBoxModel<String> userLevelModel = new DefaultComboBoxModel<String>();
		userLevelModel.addElement("Admin");
		userLevelModel.addElement("Manager");
		userLevelModel.addElement("Employee");
		userLevelCombo.setModel(userLevelModel);
		userLevelCombo.setSelectedItem("Admin");
		
		// The JButton Objects
		saveBtn = new JButton("Save");
		deleteBtn = new JButton("Delete");
		cleanBtn = new JButton("Clean");
		cancelBtn = new JButton("Cancel");
			    
		// Listeners //
		
		// Save Button
		saveBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				UserFormEvent ev;
				boolean userSaved=false;
				
				// Obtain the value of the Objects //
				String idS = idField.getText();
				String userName = userNameField.getText();
				// Obtain the values into Char values
				char[] passwordC = passwordField.getPassword();
				char[] confirmPasswordC = confirmPasswordField.getPassword();
				
				// Convert from Char to String
				String password = String.valueOf(passwordC); 
				String confirmPassword = String.valueOf(confirmPasswordC); 
				
				String employee = (String) employeeCombo.getSelectedItem(); 
				
				String email = emailField.getText();
				String userLevel = (String) userLevelCombo.getSelectedItem();  
				
				// Validate if the Id is not null
				if(idS != null && !idS.isEmpty()) 
				{ 
				   int id = Integer.parseInt(idS);
				   // Calling the constructor # 1 for the UserForm Event Class
				   ev = new UserFormEvent(this, id, userName, password, confirmPassword, employee, email, userLevel);
				}
				else
				{
				   // Calling the constructor # 2 for the UserForm Event Class
				   ev = new UserFormEvent(this, userName, password, confirmPassword, employee, email, userLevel);
				}
				
				
				if(userFormListener != null) 
				{
				   
				   userSaved=userFormListener.userFormEventOccurred(ev);
			       
				   // Validate that the user has been saved
				   if (userSaved == true) 
				   {
			          // Clear all the fields except the text area
				      cleanFields();
				   }
				   
				}	
				
			}
			
		});
		
		// Listener for the Save Button
		deleteBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				UserFormEvent ev;
				boolean userDeleted=false;
				
				// Obtain the value of the Objects //
				String idS = idField.getText();
				String userName = userNameField.getText();

				// Obtain the values into Char values
				char[] passwordC = passwordField.getPassword();
				char[] confirmPasswordC = confirmPasswordField.getPassword();
				
				// Convert from Char to String
				String password = String.valueOf(passwordC); 
				String confirmPassword = String.valueOf(confirmPasswordC); 
				
				String employee = (String) employeeCombo.getSelectedItem();  
				
				String email = emailField.getText();
				
				String userLevel = (String) userLevelCombo.getSelectedItem();  
				
				// Validate if the Id is not null
				if(idS != null && !idS.isEmpty()) 
				{ 
				   int id = Integer.parseInt(idS);
				   // Calling the constructor # 1 for the UserForm Event Class
				   ev = new UserFormEvent(this, id, userName, password, confirmPassword, employee, email, userLevel);
				}
				else
				{
				   // Calling the constructor # 2 for the EmployeeForm Event Class
				   ev = new UserFormEvent(this, userName, password, confirmPassword, employee, email, userLevel);
				}
				
				
				if(userFormListener != null) 
				{
				   userDeleted=userFormListener.userFormDeleteEventOccurred(ev);
				}
				
				if (userDeleted == true) 
				{
			       // Clear all the fields except the text area
			   	   cleanFields();
				}   
			   	
				
			}
			
		});

		
		// Clean Button
		cleanBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   cleanFields();
			 
			}
			
		});	
		
		// Cancel Button
		cancelBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   cleanFields();
			   
			   // Makes invisible the Employee Form Panel
			   setVisible(false);
			   
			   // Makes visible the UserToolBar Add New Button
			   UserToolBar.addNewButton.setVisible(true);
			   
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("User Information");
		Border outerBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	
		
		layoutComponents();
	
   }
   
   public void layoutComponents() 
   {
	    // Define the positions of the objects in the Panel //
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		// First row //
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;

	
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(idLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(idField, gc);
		

	    // Second row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(userNameLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(userNameField, gc);
	 
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(passwordLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(passwordField, gc);
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(confirmPasswordLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(confirmPasswordField, gc);
	    	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(employeeLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(employeeCombo, gc);
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(emailLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(emailField, gc);
	    	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(userLevelLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(userLevelCombo, gc);	
		
		// Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
	    
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(saveBtn, gc);
		
		// Last row //
		
		gc.weightx = 1;
		gc.weighty = 0.1;
	    
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(deleteBtn, gc);
		
		gc.weightx = 1;
		gc.weighty = 0.1;
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.CENTER;
		add(cleanBtn, gc);
		
		gc.weightx = 1;
		gc.weighty = 0.1;
	    
		gc.gridx = 2;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_END;
		add(cancelBtn, gc);
		
	
   }
   
   public void setUserFormListener(UserFormListener listener) 
   {
	   this.userFormListener = listener;
   }
   
   public void cleanFields() 
   {
      // Clean all the fields in the form
	  userNameField.setText("");
	  passwordField.setText(""); 
	  confirmPasswordField.setText(""); 
	  loadEmployees();
	  emailField.setText(""); 
   }
   
   public void loadEmployees() 
   {
      System.out.println("Loading the current Employees from the HRM Database ...");
      
      // Delete all the current values in the Employee Combo
      employeeCombo.removeAllItems();
      
      try 
      {
    	 // Obtain an arraylist of all the current Employees in the HRM database
	     ArrayList<Employee> eB = userController.loadFullNameEmployees();
	     
	     // Read all the current employees arraylist
		 for (int i = 0; i < eB.size(); i++) 
		 {
		    String employeeFullName = eB.get(i).getFullName(); 
		    
		    // Assign an Employee to the Employee Combo
		    employeeCombo.addItem(employeeFullName);
		 }
	     
	  } 
      catch (SQLException e) 
      {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 30: Occurred while loading the Employees. Error Message: " + errorMessage);
	  }
      
   }

	   
}
