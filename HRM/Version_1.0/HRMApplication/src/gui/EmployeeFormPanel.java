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
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import controller.EmployeeController;
import model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat; 

public class EmployeeFormPanel extends JPanel 
{
   // Define the Attributes of the Class //	
	
   // Define Labels	//
	
   private JLabel idLabel;
   private JLabel numberLabel;	
   private JLabel firstNameLabel;
   private JLabel middleNameLabel;
   private JLabel lastNameLabel;
   private JLabel dobLabel;
   private JLabel genderLabel;
   private JLabel maritalStatusLabel;
   private JLabel mobilePhoneLabel;
   private JLabel joinedDateLabel;
   private JLabel terminatedDateLabel;
   private JLabel jobTitleLabel;
   private JLabel departmentLabel;
   private JLabel supervisorLabel;
   
   // Define Text Fields //

   public JTextField idField;
   public JTextField numberField;	
   public JTextField firstNameField;
   public JTextField middleNameField;
   public JTextField lastNameField;
   public JTextField dobField;

   // Define Radio Buttons //
   public JRadioButton maleRadio;
   public JRadioButton femaleRadio;
   public ButtonGroup genderGroup;

   // Define ComboBox //
   public JComboBox<String> maritalStatusCombo;
   public JTextField mobilePhoneField;
   public JComboBox<String> supervisorCombo;
   public JTextField joinedDateField;
   public JTextField terminatedDateField;
   public JTextField jobTitleField;
   public JTextField departmentField;
   
   // Buttons //
   public JButton saveBtn;
   public JButton deleteBtn;
   public JButton cleanBtn;
   public JButton cancelBtn;
   
   // Listener //
   private EmployeeFormListener employeeFormListener;
   
   // Define an attribute for the controller
   private EmployeeController employeeController;
       
   String titleBorder="";
   
   // Constructor of the EmployeeFormPanel Class
   public EmployeeFormPanel() 
   {

		Dimension dim = getPreferredSize();
		
		// Set the Size of the Form
		dim.height = 650;
		dim.width = 1150;
		setPreferredSize(dim);
		
		// Define the Objects of the class //
		
		
		// Define an object for the Controller Class
        employeeController = new EmployeeController();
        
		// JLabel
		
	    // Personal Information	
		idLabel = new JLabel("Employee Id: ");
	    numberLabel = new JLabel("Employee Number: ");	
	    firstNameLabel = new JLabel("First Name: ");
	    middleNameLabel = new JLabel("Middle Name: "); 
	    lastNameLabel = new JLabel("Last Name: "); 
	    dobLabel = new JLabel("Date of Birth: "); 
	    genderLabel = new JLabel("Gender: "); 
	    maritalStatusLabel = new JLabel("Marital Status: "); 
	    mobilePhoneLabel = new JLabel("Mobile Phone: "); 

	    // Work Information
	    joinedDateLabel = new JLabel("Joined Date: "); 
	    terminatedDateLabel = new JLabel("Termination Date: "); 
	    jobTitleLabel = new JLabel("Job Title: "); 
	    departmentLabel = new JLabel("Department: "); 
	    supervisorLabel = new JLabel("Supervisor: ");
	 
		// JTextField

	    // Define Text Fields //
 	    
	    idField = new JTextField(10); 
	    // Make the idField not editable
	    Color color = Color.LIGHT_GRAY;
	    idField.setBackground(color);
	    idField.setEditable(false);
	    
	    // Make the numberField not editable
		numberField = new JTextField(10); 
		numberField.setBackground(color);
		numberField.setEditable(false);
	    
		color = Color.WHITE;

		firstNameField = new JTextField(25); 
		firstNameField.setBackground(color);
		
		middleNameField = new JTextField(25); 
		middleNameField.setBackground(color);
		
		lastNameField = new JTextField(25); 
		lastNameField.setBackground(color);
		
		dobField = new JTextField(10); 
		dobField.setBackground(color);

	    // Define Radio Buttons //
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		maleRadio.setBackground(color);
		femaleRadio.setBackground(color);
		
		maleRadio.setActionCommand("Male");
		femaleRadio.setActionCommand("Female");
		genderGroup = new ButtonGroup();
		maleRadio.setSelected(true);

	    maritalStatusCombo = new JComboBox<String>(); 
	    maritalStatusCombo.setBackground(color);
	    
	    mobilePhoneField = new JTextField(20); 
	    mobilePhoneField.setBackground(color);

	    // Work Information
	    joinedDateField = new JTextField(10); 
	    joinedDateField.setBackground(color);
	    
	    terminatedDateField = new JTextField(10); 
	    terminatedDateField.setBackground(color);
	    
		jobTitleField = new JTextField(50); 
		jobTitleField.setBackground(color);
	    
	    departmentField = new JTextField(50);
	    departmentField.setBackground(color);
	    supervisorCombo = new JComboBox<String>(); 
		supervisorCombo.setBackground(color);
		
	    // Assign Default Values to the Fields
		assignDefaultValues();
		
		// Setup of the Marital Status Jcombo box
		DefaultComboBoxModel<String> maritalStatusModel = new DefaultComboBoxModel<String>();
		maritalStatusModel.addElement("Single");
		maritalStatusModel.addElement("Married");
		maritalStatusModel.addElement("Divorced");
		maritalStatusModel.addElement("Widowed");
		maritalStatusCombo.setModel(maritalStatusModel);
		maritalStatusCombo.setSelectedIndex(0);
		
		// The JRadioButton objects
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		maleRadio.setActionCommand("Male");
		femaleRadio.setActionCommand("Female");
		genderGroup = new ButtonGroup();
		maleRadio.setSelected(true);
		
		// Set up gender radios
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		
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
				EmployeeFormEvent ev;
				boolean employeeSaved=false;
				
				try
				{
					// Obtain the value of the Objects //
					String idS = idField.getText();
					String number = numberField.getText();
					String firstName = firstNameField.getText();
					String middleName = middleNameField.getText();
					String lastName = lastNameField.getText();
					
					// Date type
					String dobS = dobField.getText();
				    Date dob;
					dob = new SimpleDateFormat("dd/MM/yyyy").parse(dobS);
					
				    String gender = genderGroup.getSelection().getActionCommand();
					String maritalStatus = (String) maritalStatusCombo.getSelectedItem();  
					String mobilePhone = mobilePhoneField.getText();   
					
					// Date type
					String joinedDateS = joinedDateField.getText();
					Date joinedDate = new SimpleDateFormat("dd/MM/yyyy").parse(joinedDateS);
	
					// Date type
					String terminatedDateS = terminatedDateField.getText();
					Date terminatedDate = new SimpleDateFormat("dd/MM/yyyy").parse(terminatedDateS);
	
					String jobTitle = jobTitleField.getText();  
					String department = departmentField.getText();  
					String supervisor = (String) supervisorCombo.getSelectedItem(); 
					
					// Validate if the Id is not null
					if(idS != null && !idS.isEmpty()) 
					{ 

					   int id = Integer.parseInt(idS);
					   
					   // Calling the constructor # 1 for the EmployeeForm Event Class
					   ev = new EmployeeFormEvent(this, id, number, firstName, middleName, lastName, 
								 dob, gender,  maritalStatus, mobilePhone, joinedDate, terminatedDate,  jobTitle,  department,
								 supervisor);
					}
					else
					{

					   ev = new EmployeeFormEvent(this, number, firstName, middleName, lastName, 
								 dob, gender,  maritalStatus, mobilePhone, joinedDate, terminatedDate,  jobTitle,  department,
								 supervisor);
					}
					
					
					if(employeeFormListener != null) 
					{
					   
					   employeeSaved=employeeFormListener.employeeFormEventOccurred(ev);
				       
					   // Validate that the employee has been saved
					   if (employeeSaved == true) 
					   {
				          // Clear all the fields except the text area
					      cleanFields();
					   }
					   
					}		    
					
				}
				catch (Exception e1) 
				{
				   String errorMessage = e1.getMessage();
				   System.out.println("Error 12: Occurred while saving an Employee. Error Message: " + errorMessage);

				}
				
			}
			
		});
		
		// Listener for the Save Button
		deleteBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				EmployeeFormEvent ev;
				boolean employeeDeleted=false;
				
				try
				{
					// Obtain the value of the Objects //
					String idS = idField.getText();
					String number = numberField.getText();
					String firstName = firstNameField.getText();
					String middleName = middleNameField.getText();
					String lastName = lastNameField.getText();
					
					// Date type
					String dobS = dobField.getText();
				    Date dob;
					dob = new SimpleDateFormat("dd/MM/yyyy").parse(dobS);
					
				    String gender = genderGroup.getSelection().getActionCommand();
					String maritalStatus = (String) maritalStatusCombo.getSelectedItem();  
					String mobilePhone = mobilePhoneField.getText();   
					
					// Date type
					String joinedDateS = joinedDateField.getText();
					Date joinedDate = new SimpleDateFormat("dd/MM/yyyy").parse(joinedDateS);
	
					// Date type
					String terminatedDateS = terminatedDateField.getText();
					Date terminatedDate = new SimpleDateFormat("dd/MM/yyyy").parse(terminatedDateS);
	
					String jobTitle = jobTitleField.getText();  
					String department = departmentField.getText();  
					//String supervisor = supervisorField.getText();
					String supervisor = (String) supervisorCombo.getSelectedItem(); 
					
					// Validate if the Id is not null
					if(idS != null && !idS.isEmpty()) 
					{ 
					   int id = Integer.parseInt(idS);
		
					   // Calling the constructor # 1 for the EmployeeForm Event Class
					   ev = new EmployeeFormEvent(this, id, number, firstName, middleName, lastName, 
								 dob, gender,  maritalStatus, mobilePhone, joinedDate, terminatedDate,  jobTitle,  department,
								 supervisor);
					}
					else
					{

					   ev = new EmployeeFormEvent(this, number, firstName, middleName, lastName, 
								 dob, gender,  maritalStatus, mobilePhone, joinedDate, terminatedDate,  jobTitle,  department,
								 supervisor);
					}
					
					
					if(employeeFormListener != null) 
					{
					   employeeDeleted=employeeFormListener.employeeFormDeleteEventOccurred(ev);
					}
					
					if (employeeDeleted == true) 
					{
				       // Clear all the fields except the text area
				   	   cleanFields();
					}   
				   	
				   						
				}
				catch (ParseException e1) 
				{
				   String errorMessage = e1.getMessage();
				   System.out.println("Error 13: Occurred while deleting an Employee. Error Message: " + errorMessage);

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
		

		//titleBorder = submenu+"Employee";
		
		Border innerBorder = BorderFactory.createTitledBorder("Employee Information");
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
	    add(numberLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(numberField, gc);
	 
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(firstNameLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(firstNameField, gc);
	    	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(middleNameLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(middleNameField, gc);
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(lastNameLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(lastNameField, gc);
	    
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(dobLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(dobField, gc);
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(genderLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio, gc);

	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio, gc);

	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(maritalStatusLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maritalStatusCombo, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(mobilePhoneLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(mobilePhoneField, gc);	
		
	   
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(joinedDateLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(joinedDateField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(terminatedDateLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(terminatedDateField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(jobTitleLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(jobTitleField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(departmentLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(departmentField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(supervisorLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(supervisorCombo, gc);	
	
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
   
   public void setEmployeeFormListener(EmployeeFormListener listener) 
   {
	   this.employeeFormListener = listener;
   }
   
	public void cleanFields() 
	{
	   // Clean all the fields in the form
	   numberField.setText("");
	   firstNameField.setText(""); 
	   middleNameField.setText(""); 
	   lastNameField.setText(""); 
	   dobField.setText(""); 
	   mobilePhoneField.setText(""); 
	   joinedDateField.setText("");; 
	   terminatedDateField.setText("");
	   jobTitleField.setText(""); 
	   departmentField.setText("");
	   maleRadio.setSelected(true);
	   maritalStatusCombo.setSelectedItem("Single");
	   
	   // Assign Default Values to the Fields
	   assignDefaultValues();
	 }

	 public void loadSupervisors() 
	 {
	      System.out.println("Loading the current Supervisors from the HRM Database ...");
	      
	      // Delete all the current values in the Employee Combo
	      supervisorCombo.removeAllItems();
	      
	      try 
	      {
	    	 // Obtain an arraylist of all the current Employees in the HRM database
		     ArrayList<Employee> eB = employeeController.loadSupervisors();
		     
		     // Read all the current employees arraylist
			 for (int i = 0; i < eB.size(); i++) 
			 {
			    String employeeFullName = eB.get(i).getFullName(); 
			    
			    // Assign an Employee to the Employee Combo
			    supervisorCombo.addItem(employeeFullName);
			 }
		     
		  } 
	      catch (SQLException e) 
	      {
		     String errorMessage = e.getMessage();
			 System.out.println("Error 15: Occurred while loading the current Supervisors. Error Message: " + errorMessage);

		  }
	      
   }

   public void assignDefaultDates()
   {
	 	
	  Calendar cal = Calendar.getInstance();
	  Date currentDate = cal.getTime();
	  String currentDateStr = null;
	  String terminatedDateStr = null;

	  try
	  {
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     currentDateStr = sdf.format(currentDate.getTime());
	   
	     // Assign value to Joined Date Field
	     joinedDateField.setText(currentDateStr);
	  
	     // Add to the current date, 980 years
	     cal.add(Calendar.YEAR, 980);
	     terminatedDateStr = sdf.format(cal.getTime());
	      
	     // Assign value to Terminated Date Field
	     terminatedDateField.setText(terminatedDateStr);
      }
      catch (Exception e) 
      {
	     String errorMessage = e.getMessage();
	     System.out.println("Error 16: Occurred while assigning the default dates to Current Date and Terminated Date. Error Message: " + errorMessage);
	  }
	  
   }
   
   public void assignEmployeeNumber()
   {
      int maxEmp = 0;
      String maxEmpStr = null;
      int length = 0;
      int zeros = 0;
	  String zerosStr="E";
	  String employeeNum = "";
      
      // Get the Maximum Employee Number
	  try 
	  {
		maxEmp = employeeController.maxNumEmployee();
	  }
	  catch (SQLException e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 89: Occurred while loading the maximum Employee Number. Error Message: " + errorMessage);
	  }

	  // Increase in one the maximum Employee Number
	  maxEmp ++;
	  
	  // Convert Integer to String
	  maxEmpStr = Integer.toString(maxEmp);

	  // Count how many characters it has
	  length = maxEmpStr.length();
	  
	  // Get how many ceros this Employee Number will have
	  zeros=10-1-length;

	  // Get the String that contains all the zeros
	  for (int i = 1; i <= zeros; i++) 
	     zerosStr = zerosStr + "0";

	  // Build the employee number
	  employeeNum = zerosStr + maxEmpStr;
	  
	  // Assign the travel request number
	  numberField.setText(employeeNum);
	  
   }
   
   public void assignDefaultValues()
   {
	   // assign default dates
	   assignDefaultDates();
	   
	   // load current supervisors
	   loadSupervisors();
	   
	   // Assign the next travel request number available
	   assignEmployeeNumber();
	   
   }
	 
	   
}
