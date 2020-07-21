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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import controller.TravelRequestController;
import model.Employee;
import model.UserLevel;


import java.text.ParseException;
import java.text.SimpleDateFormat; 

public class TravelRequestFormPanel extends JPanel 
{
   // Define the Attributes of the Class //	
	
   // Define Labels	//
	
   private JLabel idLabel;
   private JLabel numberLabel;
   private JLabel employeeLabel;
   private JLabel transportationLabel;	
   private JLabel purposeLabel;
   private JLabel travelFromLabel;
   private JLabel travelToLabel;
   private JLabel travelDateLabel;
   private JLabel returnDateLabel;
   private JLabel notesLabel;
   private JLabel currencyLabel;
   private JLabel totalFundingLabel;
   public JLabel statusLabel;
   public JLabel statusNotesLabel;
   
   // Define Text Fields //

   public JTextField idField;
   public JTextField numberField;
   public JTextField purposeField;
   public JTextField travelFromField;
   public JTextField travelToField;
   public JTextField travelDateField;
   public JTextField returnDateField;
   public JTextField notesField;
   public JTextField totalFundingField;
   public JTextField statusNotesField;
   
   // Define ComboBox //
   public JComboBox<String> employeeCombo;
   public JComboBox<String> transportationCombo;
   public JComboBox<String> currencyCombo;
   public JComboBox<String> statusCombo;

   // Buttons //
   public JButton saveBtn;
   public JButton deleteBtn;
   public JButton cleanBtn;
   public JButton cancelBtn;
   
   // Listener //
   private TravelRequestFormListener travelRequestFormListener;
   
   // Define an attribute for the controller
   private TravelRequestController travelRequestController;
       
   String titleBorder="";
   
   // Constructor of the TravelRequestFormPanel Class
   public TravelRequestFormPanel(String userP, UserLevel userLevelP) 
   {

		Dimension dim = getPreferredSize();
		
		// Set the Size of the Form
		dim.height = 650;
		dim.width = 1150;
		setPreferredSize(dim);
		
		// Define the Objects of the class //
		
		// Define an object for the Controller Class
        travelRequestController = new TravelRequestController();
        
		// JLabel
		
		idLabel = new JLabel("Travel Request Id: ");
	    numberLabel = new JLabel("Travel Request Number: ");	
	    employeeLabel = new JLabel("Employee: ");
	    transportationLabel = new JLabel("Transportation: ");
	    purposeLabel = new JLabel("Purpose of Travel: "); 
	    travelFromLabel = new JLabel("Travel From: "); 
	    travelToLabel = new JLabel("Travel To: "); 
	    travelDateLabel = new JLabel("Travel Date: "); 
	    returnDateLabel = new JLabel("Return Date: "); 
	    notesLabel = new JLabel("Notes: "); 
	    currencyLabel = new JLabel("Currency: "); 
	    totalFundingLabel = new JLabel("Total Funding Proposed: "); 
	    statusLabel = new JLabel("Status: "); 
	    statusNotesLabel = new JLabel("Status Change Notes: "); 
	 
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
		
		purposeField = new JTextField(30); 
		purposeField.setBackground(color);
		
		travelFromField = new JTextField(50); 
		travelFromField.setBackground(color);
		
		travelToField = new JTextField(50); 
		travelToField.setBackground(color);
		
		travelDateField = new JTextField(10); 
		travelDateField.setBackground(color);
		
		returnDateField = new JTextField(10); 
		returnDateField.setBackground(color);
		
		notesField = new JTextField(50); 
		notesField.setBackground(color);
		
		totalFundingField = new JTextField(10); 
		totalFundingField.setBackground(color);
		
		statusNotesField = new JTextField(50); 
		statusNotesField.setBackground(color);
		
	    employeeCombo = new JComboBox<String>(); 
	    employeeCombo.setBackground(color);
	    
		// Setup of the Employee JCombo box
	    loadEmployees(userP, userLevelP);
		
	    transportationCombo = new JComboBox<String>(); 
	    transportationCombo.setBackground(color);
	    
	    currencyCombo = new JComboBox<String>(); 
	    currencyCombo.setBackground(color);
	    
	    statusCombo = new JComboBox<String>(); 
	    statusCombo.setBackground(color);
	    	    
	    // Assign Default Values to the Fields
	    assignDefaultValues();
	    		
		// Setup of the Transportation Jcombo box
		DefaultComboBoxModel<String> transportationModel = new DefaultComboBoxModel<String>();
		transportationModel.addElement("Airplane");
		transportationModel.addElement("Train");
		transportationModel.addElement("Taxi");
		transportationModel.addElement("Own Vehicle");
		transportationModel.addElement("Rented Vehicle");
		transportationCombo.setModel(transportationModel);
		transportationCombo.setSelectedItem("Airplane");
		
		// Setup of the Currency Jcombo box
		DefaultComboBoxModel<String> currencyModel = new DefaultComboBoxModel<String>();
		currencyModel.addElement("Euros");
		currencyModel.addElement("Pound Sterling");
		currencyModel.addElement("American Dollars");
		currencyModel.addElement("Canadian Dollars");
		currencyModel.addElement("Mexican Pesos");
		currencyCombo.setModel(currencyModel);
		currencyCombo.setSelectedItem("Euros");
		
		// Setup of the Status Jcombo box
		DefaultComboBoxModel<String> statusModel = new DefaultComboBoxModel<String>();
		statusModel.addElement("Pending");
		statusModel.addElement("Approved");
		statusModel.addElement("Rejected");
		statusCombo.setModel(statusModel);
		statusCombo.setSelectedItem("Pending");
		
		// Make Not visible the fields related to the status
		statusLabel.setVisible(false); 
	    statusNotesLabel.setVisible(false); 
		statusCombo.setVisible(false);
	    statusNotesField.setVisible(false);
	    
		
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
				TravelRequestFormEvent ev;
				boolean travelRequestSaved=false;
				
				try
				{
					// Obtain the value of the Objects //
					String idS = idField.getText();
					String number = numberField.getText();
					String purpose = purposeField.getText();
					String travelFrom = travelFromField.getText();
					String travelTo = travelToField.getText();
					
					// Date type
					String travelDateS = travelDateField.getText();
				    Date travelDate;
					travelDate = new SimpleDateFormat("dd/MM/yyyy").parse(travelDateS);
					
					String returnDateS = returnDateField.getText();
				    Date returnDate;
					returnDate = new SimpleDateFormat("dd/MM/yyyy").parse(returnDateS);
					
					String notes = notesField.getText();
					float totalFunding = Float.parseFloat(totalFundingField.getText());
					
					String transportation = (String) transportationCombo.getSelectedItem(); 
					String currency = (String) currencyCombo.getSelectedItem(); 
					String status = (String) statusCombo.getSelectedItem(); 
					
					String employee = (String) employeeCombo.getSelectedItem(); 
					String statusNotes = statusNotesField.getText();
					
					// Validate if the Id is not null
					if(idS != null && !idS.isEmpty()) 
					{ 

					   int id = Integer.parseInt(idS);
					   
					   // Calling the constructor # 1 for the Travel Request Form Event Class
					   ev = new TravelRequestFormEvent(this, id, number, employee, transportation, 
							   purpose, travelFrom, travelTo, travelDate, returnDate, notes,
							   currency, totalFunding, status, statusNotes);
					}
					else
					{
					   // Calling the constructor # 2 for the Travel Request Form Event Class
					   ev = new TravelRequestFormEvent(this, number, employee, transportation, 
							   purpose, travelFrom, travelTo, travelDate, returnDate, notes,
							   currency, totalFunding, status, statusNotes);
					}
					
					
					if(travelRequestFormListener != null) 
					{
					   
					   travelRequestSaved=travelRequestFormListener.travelRequestFormEventOccurred(ev);
				       
					   // Validate that the employee has been saved
					   if (travelRequestSaved == true) 
					   {
				          // Clear all the fields except the text area
					      cleanFields(userP, userLevelP);
					   }
					   
					}		    
					
				}
				catch (ParseException e1) 
				{
				   String errorMessage = e1.getMessage();
				   System.out.println("Error 43: Occurred while saving a Travel Request. Error Message: "+ errorMessage);

				}
				
			}
			
		});
		
		// Listener for the Save Button
		deleteBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				TravelRequestFormEvent ev;
				
				try
				{
					// Obtain the value of the Objects //
					String idS = idField.getText();
					String number = numberField.getText();
					String purpose = purposeField.getText();
					String travelFrom = travelFromField.getText();
					String travelTo = travelToField.getText();
					
					// Date type
					String travelDateS = travelDateField.getText();
				    Date travelDate;
					travelDate = new SimpleDateFormat("dd/MM/yyyy").parse(travelDateS);
					
					String returnDateS = returnDateField.getText();
				    Date returnDate;
					returnDate = new SimpleDateFormat("dd/MM/yyyy").parse(returnDateS);
					
					String notes = notesField.getText();
					float totalFunding = Float.parseFloat(totalFundingField.getText());
					
					String transportation = (String) transportationCombo.getSelectedItem(); 
					String currency = (String) currencyCombo.getSelectedItem(); 
					String status = (String) statusCombo.getSelectedItem(); 
					
					String employee = (String) employeeCombo.getSelectedItem(); 
					String statusNotes = statusNotesField.getText();
					
					// Validate if the Id is not null
					if(idS != null && !idS.isEmpty()) 
					{ 

					   int id = Integer.parseInt(idS);
					   
					   // Calling the constructor # 1 for the Travel Request Form Event Class
					   ev = new TravelRequestFormEvent(this, id, number, employee, transportation, 
							   purpose, travelFrom, travelTo, travelDate, returnDate, notes,
							   currency, totalFunding, status, statusNotes);
					}
					else
					{
					   // Calling the constructor # 2 for the Travel Request Form Event Class
					   ev = new TravelRequestFormEvent(this, number, employee, transportation, 
							   purpose, travelFrom, travelTo, travelDate, returnDate, notes,
							   currency, totalFunding, status, statusNotes);
					}
					
					
					if(travelRequestFormListener != null) 
					{
				       travelRequestFormListener.travelRequestFormDeleteEventOccurred(ev);
					}
								    
				    // Clear all the fields except the text area
				   	cleanFields(userP, userLevelP);
					
				}
				catch (ParseException e1) 
				{
				   String errorMessage = e1.getMessage();
				   System.out.println("Error 44: Occurred while deleting a Travel Request. Error Message: " + errorMessage);
				}
				
			}
			
		});

		
		// Clean Button
		cleanBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   cleanFields(userP, userLevelP);
			 
			}
		});	
		
		// Cancel Button
		cancelBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   cleanFields(userP, userLevelP);
			   
			   // Makes invisible the Employee Form Panel
			   setVisible(false);
			   
			   // Makes visible the UserToolBar Add New Button
			   UserToolBar.addNewButton.setVisible(true);
			   
			}
		});
		

		//titleBorder = submenu+"Employee";
		
		Border innerBorder = BorderFactory.createTitledBorder("Travel Request Information");
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
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(employeeLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(employeeCombo, gc);

	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(transportationLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(transportationCombo, gc);

	    	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(purposeLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(purposeField, gc);
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(travelFromLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(travelFromField, gc);
	    
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(travelToLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(travelToField, gc);
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(travelDateLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(travelDateField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(returnDateLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(returnDateField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(notesLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(notesField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(currencyLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(currencyCombo, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(totalFundingLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(totalFundingField, gc);	
		
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(statusLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(statusCombo, gc);
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(statusNotesLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(statusNotesField, gc);	
		
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
   
   public void setTravelRequestFormListener(TravelRequestFormListener listener) 
   {
	   this.travelRequestFormListener = listener;
   }
   
	public void cleanFields(String userP, UserLevel userLevelP) 
	{
	   // Clean all the fields in the form
	   numberField.setText("");
	   loadEmployees(userP, userLevelP);
	   transportationCombo.setSelectedItem("Airplane");
	   purposeField.setText(""); 
	   travelFromField.setText(""); 
	   travelToField.setText(""); 
	   
	   // Assign Default Values to the Fields
	   assignDefaultValues();
	   
	   notesField.setText(""); 
	   currencyCombo.setSelectedItem("Euros");
	   statusNotesField.setText(""); 
	 }

	 public void loadEmployees(String userP, UserLevel userLevelP) 
	 {
	    System.out.println("Loading the current Employees from the HRM Database ...");
	     
	     // Delete all the current values in the Employee Combo
	     employeeCombo.removeAllItems();
	      
	      
	     try 
	     {
	        // Obtain an arraylist of all the current Employees in the HRM database
		    ArrayList<Employee> eB = travelRequestController.loadFullNameEmployees(userP, userLevelP);
		     
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
			 System.out.println("Error 46: Occurred while loading the Employees. Error Message: " + errorMessage);
		  }
	      
   }
	 
   public void assignDefaultValues()
   {
      Calendar cal = Calendar.getInstance();
	  Date currentDate = cal.getTime();
	  String currentDateStr = null;
	  String nextDateStr = null;
	  float currentFunding = 0.0f;

	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  currentDateStr = sdf.format(currentDate.getTime());
		   
	  // Add to the current date, 1 day
	  cal.add(Calendar.DAY_OF_YEAR, 1);
	  nextDateStr = sdf.format(cal.getTime());
		      
	  // Assign value to Travel Date Field
      travelDateField.setText(currentDateStr);
      
      // Assign value to Next Date Field
      returnDateField.setText(nextDateStr);
      
      totalFundingField.setText(Float.toString(currentFunding));
      
      // Assign the next travel request number available
      assignTravelRequestNumber();
      
   }  
   
   public void assignTravelRequestNumber()
   {
      int maxTR = 0;
      String maxTRStr = null;
      int length = 0;
      int zeros = 0;
	  String zerosStr="T";
	  String travelRequestNum = "";
      
      // Get the Maximum Travel Request Number
	  try 
	  {
		maxTR = travelRequestController.maxNumTravelRequest();
	  }
	  catch (SQLException e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error 87: Occurred while loading the maximum Travel Request Number. Error Message: " + errorMessage);
	  }

	  // Increase in one the maximum Travel Request Number
	  maxTR ++;
	  
	  // Convert Integer to String
	  maxTRStr = Integer.toString(maxTR);

	  // Count how many characters it has
	  length = maxTRStr.length();
	  
	  // Get how many ceros this Travel Request Number will have
	  zeros=10-1-length;

	  // Get the String that contains all the zeros
	  for (int i = 1; i <= zeros; i++) 
	     zerosStr = zerosStr + "0";

	  // Build the travel request number
	  travelRequestNum = zerosStr + maxTRStr;
	  
	  // Assign the travel request number
	  numberField.setText(travelRequestNum);
	  
   }
	   
}
