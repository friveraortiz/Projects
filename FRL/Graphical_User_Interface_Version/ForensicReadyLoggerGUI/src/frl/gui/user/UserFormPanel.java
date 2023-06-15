package frl.gui.user;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

import frl.gui.toolBar.ToolBar;

//Package #6
//Class #4
public class UserFormPanel extends JPanel 
{

   private static final long serialVersionUID = 1L;
   // Define the Attributes of the Class //	
	
   // Define Labels	//	
   private JLabel idLabel;
   private JLabel userNameLabel;
   private JLabel passwordLabel;
   private JLabel confirmPasswordLabel;
   private JLabel userLevelLabel;
 
   // Define Text Fields //

   // Personal Information	
   public JTextField idField;
   public JTextField userNameField;
   
   /*
   public JPasswordField passwordField;
   public JPasswordField confirmPasswordField;
   */
   public JTextField passwordField;
   public JTextField confirmPasswordField;
   
   
   // Define ComboBox //
   public JComboBox<String> userLevelCombo;
   
   // Buttons //
   public JButton saveBtn;
   public JButton deleteBtn;
   public JButton cleanBtn;
   public JButton cancelBtn;
   
   // Listener //
   private UserFormListener userFormListener;
   
   String titleBorder="";
   
   // Constructor of the UserFormPanel Class
   // Method #1
   public UserFormPanel() 
   {

		Dimension dim = getPreferredSize();
		
		// Set the Size of the Form
		dim.height = 400;
		dim.width = 900;
		setPreferredSize(dim);
		
	    // Set the color of the Form
	    this.setBackground(Color.lightGray);
	    
		// Define the Objects of the class //
		
		// JLabels
	    // Define Label Fields
	    
		idLabel = new JLabel("User Id: ");
	    userNameLabel = new JLabel("User Name: ");	
	    passwordLabel = new JLabel("Password: ");
	    confirmPasswordLabel = new JLabel("Confirm Password: ");
	    userLevelLabel = new JLabel("User Level: "); 

		// JTextFields
	    // Define Text Fields //
 
	    idField = new JTextField(10); 
	    
	    // Make the idField not editable
	    Color color = Color.LIGHT_GRAY;
	    idField.setBackground(color);
	    idField.setEditable(false);
	    
		color = Color.WHITE;
		userNameField = new JTextField(50); 
		userNameField.setBackground(color);

		passwordField = new JTextField(50); 
		passwordField.setBackground(color);
		
		confirmPasswordField = new JTextField(50); 
		confirmPasswordField.setBackground(color);
		
		/*
		passwordField = new JPasswordField(50);
		passwordField.setEchoChar('*');
		passwordField.setBackground(color);
		
		confirmPasswordField = new JPasswordField(50);
		confirmPasswordField.setEchoChar('*');
		confirmPasswordField.setBackground(color);
		*/
		
		userLevelCombo = new JComboBox<String>(); 
		userLevelCombo.setBackground(color);
			    
		// Setup of the UserLevel JCombo box
		DefaultComboBoxModel<String> userLevelModel = new DefaultComboBoxModel<String>();
		userLevelModel.addElement("Admin");
		userLevelModel.addElement("Security");
		userLevelModel.addElement("Developer");
		userLevelCombo.setModel(userLevelModel);
		userLevelCombo.setSelectedItem("Admin");
		
		// The JButton Objects
		saveBtn = new JButton("Save");
		deleteBtn = new JButton("Delete");
		cleanBtn = new JButton("Clean");
		cancelBtn = new JButton("Cancel");

		userNameField.requestFocusInWindow();
			    
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
				/*char[] passwordC = passwordField.getPassword();
				char[] confirmPasswordC = confirmPasswordField.getPassword();
				
				// Convert from Char to String
				String password = String.valueOf(passwordC); 
				String confirmPassword = String.valueOf(confirmPasswordC); 
				*/
				
				String password = passwordField.getText();
				String confirmPassword = confirmPasswordField.getText();
				
				String userLevel = (String) userLevelCombo.getSelectedItem();  
				
				// Validate if the Id is not null
				if(idS != null && !idS.isEmpty()) 
				{ 
				   int id = Integer.parseInt(idS);
				   // Calling the constructor # 1 for the UserForm Event Class
				   ev = new UserFormEvent(this, id, userName, password, confirmPassword, userLevel);
				}
				else
				{
				   // Calling the constructor # 2 for the UserForm Event Class
				   ev = new UserFormEvent(this, userName, password, confirmPassword, userLevel);
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
				/*
				char[] passwordC = passwordField.getPassword();
				char[] confirmPasswordC = confirmPasswordField.getPassword();
				
				// Convert from Char to String
				String password = String.valueOf(passwordC); 
				String confirmPassword = String.valueOf(confirmPasswordC);
				*/
				
				String password = passwordField.getText();
				String confirmPassword = confirmPasswordField.getText();
				
				String userLevel = (String) userLevelCombo.getSelectedItem();  
				
				// Validate if the Id is not null
				if(idS != null && !idS.isEmpty()) 
				{ 
				   int id = Integer.parseInt(idS);
				   
				   // Calling the constructor # 1 for the UserForm Event Class
				   ev = new UserFormEvent(this, id, userName, password, confirmPassword, userLevel);
				}
				else
				{
				   // Calling the constructor # 2 for the EmployeeForm Event Class
				   ev = new UserFormEvent(this, userName, password, confirmPassword, userLevel);
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
			   ToolBar.addNewButton.setVisible(true);
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("User Information");
		Border outerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
   }
   
   // Method #2
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
		//gc.anchor = GridBagConstraints.LINE_END;
		add(cancelBtn, gc);
		
	
   }
   
   // Method #3
   public void setUserFormListener(UserFormListener listener) 
   {
	   this.userFormListener = listener;
   }
   
   // Method #4
   public void cleanFields() 
   {
      // Clean all the fields in the form
	  idField.setText("");
	  userNameField.setText("");
	  passwordField.setText(""); 
	  confirmPasswordField.setText("");
	  userLevelCombo.setSelectedItem("Admin");
	  
        
	  userNameField.requestFocusInWindow();
   }
   	   
}
