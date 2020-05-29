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
import controller.TravelRequestController;
import model.UserLevel;


public class TravelRequestFrame extends JFrame 
{
	// Define the attributes for the class
    private UserToolBar userToolBar; 
    private TravelRequestFormPanel travelRequestFormPanel;
 
    // Define an attribute for the controller
    private TravelRequestController travelRequestController;
    
    // Define an attribute for the table
    private TravelRequestTablePanel travelRequestTablePanel;
    
    public static String menu="";
    public static String submenu="";
    
    // Constructor of the TravelRequestFrame class
	public TravelRequestFrame (String userP, String menuP, UserLevel userLevelP, String [][] subModules) 
	{
        super("Travel Requests Main Menu HRM");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        
        // The Main Form creates the objects 
        travelRequestFormPanel = new TravelRequestFormPanel(userP,userLevelP);
        travelRequestTablePanel = new TravelRequestTablePanel(subModules);
        
        if (menuP.equals("Travel Requests")) 
        {
        	
        	userToolBar = new UserToolBar(userP, menuP, userLevelP);
        }
        
        // Define an object for the Controller Class
        travelRequestController = new TravelRequestController();
        
        // Define an object for the TravelRequestTablePanel Class
        travelRequestTablePanel.setDataTravelRequest(travelRequestController.getTravelRequests());
        
        
        // Add a new Listener for the TravelRequestTablePanel
        travelRequestTablePanel.setTravelRequestTableListener(new TravelRequestTableListener() 
        {
			@Override
			public void travelRequestDeleted(int row) 
			{
			   travelRequestController.deleteTravelRequest(row);
			}

			@Override
			public void travelRequestTableEventOccurred(int id, String popupMenu) 
			{
		     	   
			   // Show the Travel Request Form Panel
			   travelRequestFormPanel.setVisible(true); 
			      
	     	   // Clean the current values in the TravelRequestFormPanel
	     	   travelRequestFormPanel.cleanFields(userP, userLevelP);
	     	   
	     	   // Get all the values from the selected row into the TravelRequestFormPanel
			   refreshTravelRequest();
			   
			   // Get the status of the travel request selected
			   String status = getStatusTravelRequest();
			   
	           // Validate if the selected Travel Request has the status equal to Pending
	           if (status.equals("Pending"))
	           {
			   
			      if (popupMenu.equals("View"))
			      {

				     travelRequestFormPanel.saveBtn.setVisible(false);
				     travelRequestFormPanel.deleteBtn.setVisible(false);
			      
			         // disable all the fields in the TravelRequestsFormPanel
			         disableFieldsTravelRequest(popupMenu);
			      
			         menu = "";
			      
			         // Makes invisible the UserToolBar Add New Button
			         userToolBar.addNewButton.setVisible(false);
			      
			       }
		           else
		             if (popupMenu.equals("Modify"))
		             {
		        	    travelRequestFormPanel.saveBtn.setVisible(true);
		        	    travelRequestFormPanel.deleteBtn.setVisible(false);
		             
		                // enable all the fields in the TravelRequestFormPanel
		                enableFieldsTravelRequest();
		             
			            menu = "";
			         
			            // Makes invisible the UserToolBar Add New Button
			            userToolBar.addNewButton.setVisible(false);
		             }
		             else
		        	 
			            if (popupMenu.equals("Delete"))
			            {

			        	   travelRequestFormPanel.saveBtn.setVisible(false);
			        	   travelRequestFormPanel.deleteBtn.setVisible(true);
			            
			               // disable all the fields in the TravelRequestFormPanel
					       disableFieldsTravelRequest(popupMenu);
					      
				           menu = "";
				        
				           // Makes invisible the UserToolBar Add New Button
				           userToolBar.addNewButton.setVisible(false);
				        
			            }
			            else
				           if (popupMenu.equals("ChangeStatus"))
				           {
				           
				              // Show the TravelRequest Form Panel
							  travelRequestFormPanel.setVisible(true); 
							     
				              travelRequestFormPanel.saveBtn.setVisible(true);
				              travelRequestFormPanel.deleteBtn.setVisible(false);
				             
				              // enable the fields related to Change Status and
				              // disable the rest of the fields
				              enableFieldsChangeStatus();
				             
					          menu = "";
					         
					          // Makes invisible the UserToolBar Add New Button
					          userToolBar.addNewButton.setVisible(false);
				        	}
				        		
		       }
	           else 
	        	   if(status.equals("Approved") | status.equals("Rejected"))
	               {
	        	   
				      if (popupMenu.equals("View"))
				      {

					     travelRequestFormPanel.saveBtn.setVisible(false);
					     travelRequestFormPanel.deleteBtn.setVisible(false);
				      
				         // disable all the fields in the TravelRequestsFormPanel
				         disableFieldsTravelRequest(popupMenu);
				      
				         menu = "";
				      
				         // Makes invisible the UserToolBar Add New Button
				         userToolBar.addNewButton.setVisible(false);
				      
				       }
				       else
				    	   if (popupMenu.equals("Delete"))
				            {

				        	   travelRequestFormPanel.saveBtn.setVisible(false);
				        	   travelRequestFormPanel.deleteBtn.setVisible(true);
				            
				               // disable all the fields in the TravelRequestFormPanel
						       disableFieldsTravelRequest(popupMenu);
						      
					           menu = "";
					        
					           // Makes invisible the UserToolBar Add New Button
					           userToolBar.addNewButton.setVisible(false);
					        
				            }
				    	   else
				    		   if (popupMenu.equals("Modify"))
					           {
				    			  // Hide the TravelRequestForm Panel
							      travelRequestFormPanel.setVisible(false); 
							      JOptionPane.showMessageDialog(TravelRequestFrame.this, "Error 47: The Travel Request has already been "+status+" and cannot be modified.", "Modifiying a Travel Request", JOptionPane.ERROR_MESSAGE);
					           }
				    		   else
				                  if (popupMenu.equals("ChangeStatus"))
				                  {
			                         // Hide the TravelRequest Form Panel
					                 travelRequestFormPanel.setVisible(false); 
					                 JOptionPane.showMessageDialog(TravelRequestFrame.this, "Error 48: The Travel Request has already been "+status+" and cannot change its current status.", "Changing Status to a Travel Request", JOptionPane.ERROR_MESSAGE);
				                  }
	                }
			}
		});
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Retrieving from the HRM Database the existing travel requests information in the travelRequest Table Panel
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        loadingTravelRequests(userP, userLevelP);
                
        // MenuBar
        setJMenuBar(createEmployeeMenuBar(userP, userLevelP));
        
        // Listener for the toolbar object
        userToolBar.setUserToolBarListener(new UserToolBarListener()
        {
			@Override
			public void addNewEventOccurred() 
			{	
				menu = "TravelRequests";
                submenu = "Add";
                
			   	// Makes visible the Travel Request Form Panel
                travelRequestFormPanel.setVisible(true);
                travelRequestFormPanel.idField.setText("");
				
				// Makes visible the Travel Request Save Button
                travelRequestFormPanel.saveBtn.setVisible(true);
				
				// enable all the fields in the TravelRequestFormPanel
	            enableFieldsTravelRequest();
	            
				// Makes invisible the Travel Request Delete Button
	            travelRequestFormPanel.deleteBtn.setVisible(false);
				
				// Makes visible the TravelRequest TablePanel
	            travelRequestTablePanel.setVisible(true);
			}

        });	
        
        // Listener for the formPanel object
        travelRequestFormPanel.setTravelRequestFormListener(new TravelRequestFormListener() 
        {
        	
        	// Method that is defined in the Interface: TravelRequestFormListener
        	@Override
			public boolean travelRequestFormEventOccurred(TravelRequestFormEvent e) 
        	{
	
        	   boolean validTravelRequest1=false;
        	   boolean validTravelRequest2=false;
        	   boolean validTravelRequest3=false;
        		
               ////////////////////////////////////////////////////////////////
        	   // Make some validations to the Travel Request before saving it
        	   ////////////////////////////////////////////////////////////////
        	   
        	   // Validation #1: Validate that the Travel fields are not empty
        	   validTravelRequest1=travelRequestController.validateTravelRequestFields(e);
        	   
        	   // Validation #2: Validate the Travel and Return Dates 
        	   validTravelRequest2=travelRequestController.validateTravelReturnDates(e);
        	   
        	   // Validation #3: Validate the TotalFunding 
        	   validTravelRequest3=travelRequestController.validateTotalFunding(e);

        	   if (validTravelRequest1 == false) 
        	   {
                  JOptionPane.showMessageDialog(TravelRequestFrame.this, "Error 49: All the Travel Request fields should be filled-in and should not be empty.", "Validating a Travel Request", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else if (validTravelRequest2 == false) 
        	   {
                  JOptionPane.showMessageDialog(TravelRequestFrame.this, "Error 50: The Return Date should be the same or greather than the Travel Date.", "Validating a Travel Request", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else if (validTravelRequest3 == false) 
        	   {
                  JOptionPane.showMessageDialog(TravelRequestFrame.this, "Error 51: The Total Funding proposed should be greather than 0.0.", "Validating a Travel Request", JOptionPane.ERROR_MESSAGE);
                  return false;
               }
        	   else
        	   {
        	   
                  // Assign to the object controller values
            	  travelRequestController.addTravelRequest(e);
            	   
            	  // Show the information from the TravelRequestFormPanel fields 
            	  // into the TravelRequestTablePanel object
            	  travelRequestTablePanel.refresh();
     
        		  //////////////////////////////////////////////////////////////////////
		          // Saving the data from the TravelRequestFormPanel into the HRM Database
		          /////////////////////////////////////////////////////////////////////
        	   
			      System.out.println("Saving a Travel Request into HRM Database ...");
	           
			      try 
			      {
			         travelRequestController.save(e);
			      } 
			      catch (SQLException e1) 
			      {
				     String errorMessage = e1.getMessage();
			         JOptionPane.showMessageDialog(TravelRequestFrame.this, "Error 52: Occurred while saving a Travel Request. Error Message: "+errorMessage, "Saving a Travel Request", JOptionPane.ERROR_MESSAGE);	
			      }
			   
			      // Retrieving from the HRM Database the existing travel request's information in the travelRequestTable Panel
			      loadingTravelRequests(userP, userLevelP);
			      
			      return true;
        	   }
        	   
        	}

			@Override
			public void travelRequestFormDeleteEventOccurred(TravelRequestFormEvent e) 
			{

	           // Assign to the object controller values
	           travelRequestController.addTravelRequest(e);
	        	   
	           // Show the information from the TravelRequestFormPanel fields 
	           // into the TravelRequestTablePanel object
	           travelRequestTablePanel.refresh();
	        	   
			   //////////////////////////////////////////////////////////////////////
			   // Deleting data from the Travel RequestFormPanel into the HRM Database
			   /////////////////////////////////////////////////////////////////////
	        	   
			   System.out.println("Deleting a Travel Request from the HRM Database ...");

			   try 
			   {
				  travelRequestController.delete(e);

			   } 
			   catch (SQLException e1) 
			   {
				  String errorMessage = e1.getMessage();    
			      JOptionPane.showMessageDialog(TravelRequestFrame.this, "Error 53: Occurred while deleting a Travel Request. Error Message: "+errorMessage, "Deleting a Travel Request", JOptionPane.ERROR_MESSAGE);	
			   }
			   //travelRequestController.disconnect();
				   
			   // Retrieving from the HRM Database the existing travel requests' information in the TravelRequestTable Panel
			   loadingTravelRequests(userP, userLevelP);
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
        add(travelRequestFormPanel, BorderLayout.WEST);
        add(travelRequestTablePanel, BorderLayout.CENTER);
        
        
        // Set the Size of the Form
        pack();
		this.setSize(1150, 650);
		
		// Set up that the windows cannot be resizable by the User
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
        //Display the window.
        this.setVisible(true);
   
	   	// Makes Not Visible the Panels
        travelRequestFormPanel.setVisible(false);
        travelRequestTablePanel.setVisible(true);
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
			   travelRequestFormPanel.setVisible(false);
			   travelRequestTablePanel.setVisible(true);
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
						TravelRequestFrame.this, "Do you really want to sign out the HRM System?", 
						"Confirm Sign out", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{

				   //Close the current MainFrame Window 	
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
						TravelRequestFrame.this, "Do you really want to exit the HRM System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   WindowListener[] listeners = getWindowListeners();
					
				   for(WindowListener listener: listeners) 
				   {
					  // Calls the Windows Closing Event
				      listener.windowClosing(new WindowEvent(TravelRequestFrame.this, 0));
				   } 	
			    }
			}
		});
		

		return menuBar;
	}
	
	// This method will display all the values of the selected row in the TravelRequestFormPanel
	public void refreshTravelRequest()
	{
		
	   // Travel Request Id
	   String idSel = travelRequestTablePanel.getTravelRequestStringColSel(0);
	   travelRequestFormPanel.idField.setText(idSel);
	               
	   // Travel Request Number
	   String numberSel = travelRequestTablePanel.getTravelRequestStringColSel(1);
	   travelRequestFormPanel.numberField.setText(numberSel);
		           
	   // Employee
	   String employeeSel = travelRequestTablePanel.getTravelRequestStringColSel(2);
	   travelRequestFormPanel.employeeCombo.setSelectedItem(employeeSel);
			      
	   // Transportation
	   String transportationSel = travelRequestTablePanel.getTravelRequestStringColSel(3);
		           
	   if (transportationSel.equals("Airplane"))
	      travelRequestFormPanel.transportationCombo.setSelectedItem("Airplane");
	   else
	      if (transportationSel.equals("Train"))
		     travelRequestFormPanel.transportationCombo.setSelectedItem("Train");
		  else
		     if (transportationSel.equals("Taxi"))
			    travelRequestFormPanel.transportationCombo.setSelectedItem("Taxi");
			 else
			    if (transportationSel.equals("OwnVehicle"))
				   travelRequestFormPanel.transportationCombo.setSelectedItem("Own Vehicle");	
				else
				   if (transportationSel.equals("RentedVehicle"))
				      travelRequestFormPanel.transportationCombo.setSelectedItem("Rented Vehicle");

	   // Purpose
	   String purposeSel = travelRequestTablePanel.getTravelRequestStringColSel(4);
	   travelRequestFormPanel.purposeField.setText(purposeSel);
			      
	   // Travel From
	   String travelFromSel = travelRequestTablePanel.getTravelRequestStringColSel(5);
	   travelRequestFormPanel.travelFromField.setText(travelFromSel);  
	  		      
	   // Travel To
	   String travelToSel = travelRequestTablePanel.getTravelRequestStringColSel(6);
	   travelRequestFormPanel.travelToField.setText(travelToSel);  
			          
	   // Travel Date
	   String travelDateSel = travelRequestTablePanel.getTravelRequestStringColSel(7);
	   travelRequestFormPanel.travelDateField.setText(travelDateSel);  

	   // Return Date
	   String returnDateSel = travelRequestTablePanel.getTravelRequestStringColSel(8);
	   travelRequestFormPanel.returnDateField.setText(returnDateSel);  
					
	   // Notes
	   String notesSel = travelRequestTablePanel.getTravelRequestStringColSel(9);
	   travelRequestFormPanel.notesField.setText(notesSel);  
			      
	   // Currency
	   String currencySel = travelRequestTablePanel.getTravelRequestStringColSel(10);
		      	    
	   if (currencySel.equals("Euros"))
	      travelRequestFormPanel.currencyCombo.setSelectedItem("Euros");
	   else
	      if (currencySel.equals("PoundSterling"))
		     travelRequestFormPanel.currencyCombo.setSelectedItem("Pound Sterling");
		  else
		     if (currencySel.equals("AmericanDollars"))
			    travelRequestFormPanel.currencyCombo.setSelectedItem("American Dollars");
			 else
			    if (currencySel.equals("CanadianDollars"))
				   travelRequestFormPanel.currencyCombo.setSelectedItem("Canadian Dollars");	
				else
				   if (currencySel.equals("MexicanPesos"))
				      travelRequestFormPanel.currencyCombo.setSelectedItem("Mexican Pesos");
			      
	   // Total Funding
	   String totalFundingSel = travelRequestTablePanel.getTravelRequestStringColSel(11);
	   travelRequestFormPanel.totalFundingField.setText(totalFundingSel);
	
	  // Display just the status for Add or Modify a Travel Request: 
	  // Pending, Approved and Rejected 
	  originalStatus();
	   
	  // Status
	  String statusSel = travelRequestTablePanel.getTravelRequestStringColSel(12);
		      	    
	  if (statusSel.equals("Pending"))
	     travelRequestFormPanel.statusCombo.setSelectedItem("Pending");
	  else
	     if (statusSel.equals("Approved"))
		    travelRequestFormPanel.statusCombo.setSelectedItem("Approved");
		 else
		    if (statusSel.equals("Rejected"))
			   travelRequestFormPanel.statusCombo.setSelectedItem("Rejected");

			  
	  // Status Notes
	  String statusNotesSel = travelRequestTablePanel.getTravelRequestStringColSel(13);
	  travelRequestFormPanel.statusNotesField.setText(statusNotesSel);

	}
	
   public String getStatusTravelRequest()
   {
      String statusSel = "";	   
	   
      // Get the Status of the Travel Request Selected
      statusSel = travelRequestTablePanel.getTravelRequestStringColSel(12);
      
      return statusSel;
   }
	
   public void loadingTravelRequests(String userP, UserLevel userLevelP)
   {

	  try 
	  {
	     travelRequestController.load(userP, userLevelP);
		 travelRequestTablePanel.refresh();
	  } 
	  catch (SQLException e) 
	  {
		 String errorMessage = e.getMessage(); 
	     JOptionPane.showMessageDialog(TravelRequestFrame.this, "Error 55: Occurred while loading the Travel Requests. Error Message: "+errorMessage, "Loading Travel Requests", JOptionPane.ERROR_MESSAGE);	
	  }
		
   }
	
   public void disableFieldsTravelRequest(String popupMenu)
   {
      //Make all the fields not editable
      Color color = Color.LIGHT_GRAY;

      travelRequestFormPanel.employeeCombo.setBackground(color);
      travelRequestFormPanel.employeeCombo.setEnabled(false);
      
      travelRequestFormPanel.transportationCombo.setBackground(color);
      travelRequestFormPanel.transportationCombo.setEnabled(false);
      
      travelRequestFormPanel.purposeField.setBackground(color);	
      travelRequestFormPanel.purposeField.setEditable(false);

      travelRequestFormPanel.travelFromField.setBackground(color);	
      travelRequestFormPanel.travelFromField.setEditable(false);
      
      travelRequestFormPanel.travelToField.setBackground(color);	
      travelRequestFormPanel.travelToField.setEditable(false);
      
      travelRequestFormPanel.travelDateField.setBackground(color);	
      travelRequestFormPanel.travelDateField.setEditable(false);
      
      travelRequestFormPanel.returnDateField.setBackground(color);	
      travelRequestFormPanel.returnDateField.setEditable(false);
      
      travelRequestFormPanel.notesField.setBackground(color);	
      travelRequestFormPanel.notesField.setEditable(false);
      
      travelRequestFormPanel.currencyCombo.setBackground(color);
      travelRequestFormPanel.currencyCombo.setEnabled(false);

      travelRequestFormPanel.totalFundingField.setBackground(color);	
      travelRequestFormPanel.totalFundingField.setEditable(false);
      
	  // Make Not visible the fields related to the status
      travelRequestFormPanel.statusCombo.setEnabled(false);
      travelRequestFormPanel.statusNotesField.setEditable(false);
      
      // Display just the status for Add or Modify a Travel Request: 
      // Pending, Approved and Rejected
      //originalStatus();
      
      if(popupMenu.equals("View"))
      {	  
         travelRequestFormPanel.statusLabel.setVisible(true); 
         travelRequestFormPanel.statusNotesLabel.setVisible(true); 
         travelRequestFormPanel.statusCombo.setVisible(true);
         travelRequestFormPanel.statusNotesField.setVisible(true);
      }
      else
      {
         travelRequestFormPanel.statusLabel.setVisible(false); 
         travelRequestFormPanel.statusNotesLabel.setVisible(false); 
         travelRequestFormPanel.statusCombo.setVisible(false);
         travelRequestFormPanel.statusNotesField.setVisible(false);
      }
            
   }
   
   public void enableFieldsTravelRequest()
   {
      //Make all the fields editable
      Color color = Color.WHITE;

      travelRequestFormPanel.employeeCombo.setBackground(color);
      travelRequestFormPanel.employeeCombo.setEnabled(true);
      
      travelRequestFormPanel.transportationCombo.setBackground(color);
      travelRequestFormPanel.transportationCombo.setEnabled(true);
      
      travelRequestFormPanel.purposeField.setBackground(color);	
      travelRequestFormPanel.purposeField.setEditable(true);

      travelRequestFormPanel.travelFromField.setBackground(color);	
      travelRequestFormPanel.travelFromField.setEditable(true);
      
      travelRequestFormPanel.travelToField.setBackground(color);	
      travelRequestFormPanel.travelToField.setEditable(true);
      
      travelRequestFormPanel.travelDateField.setBackground(color);	
      travelRequestFormPanel.travelDateField.setEditable(true);
      
      travelRequestFormPanel.returnDateField.setBackground(color);	
      travelRequestFormPanel.returnDateField.setEditable(true);
      
      travelRequestFormPanel.notesField.setBackground(color);	
      travelRequestFormPanel.notesField.setEditable(true);
      
      travelRequestFormPanel.currencyCombo.setBackground(color);
      travelRequestFormPanel.currencyCombo.setEnabled(true);

      travelRequestFormPanel.totalFundingField.setBackground(color);	
      travelRequestFormPanel.totalFundingField.setEditable(true);
      
	  // Make Not visible the fields related to the status
      travelRequestFormPanel.statusCombo.setEnabled(false);
      travelRequestFormPanel.statusNotesField.setEditable(false);
      
      travelRequestFormPanel.statusLabel.setVisible(false); 
      travelRequestFormPanel.statusNotesLabel.setVisible(false); 
      travelRequestFormPanel.statusCombo.setVisible(false);
      travelRequestFormPanel.statusNotesField.setVisible(false);
      
      // Display just the status for Add or Modify a Travel Request: 
      // Pending, Approved and Rejected
      originalStatus();
      
   }
 
   public void enableFieldsChangeStatus()
   {
      //Make all the fields not editable
      Color color = Color.LIGHT_GRAY;

      travelRequestFormPanel.employeeCombo.setBackground(color);
      travelRequestFormPanel.employeeCombo.setEnabled(false);
      
      travelRequestFormPanel.transportationCombo.setBackground(color);
      travelRequestFormPanel.transportationCombo.setEnabled(false);
      
      travelRequestFormPanel.purposeField.setBackground(color);	
      travelRequestFormPanel.purposeField.setEditable(false);

      travelRequestFormPanel.travelFromField.setBackground(color);	
      travelRequestFormPanel.travelFromField.setEditable(false);
      
      travelRequestFormPanel.travelToField.setBackground(color);	
      travelRequestFormPanel.travelToField.setEditable(false);
      
      travelRequestFormPanel.travelDateField.setBackground(color);	
      travelRequestFormPanel.travelDateField.setEditable(false);
      
      travelRequestFormPanel.returnDateField.setBackground(color);	
      travelRequestFormPanel.returnDateField.setEditable(false);
      
      travelRequestFormPanel.notesField.setBackground(color);	
      travelRequestFormPanel.notesField.setEditable(false);
      
      travelRequestFormPanel.currencyCombo.setBackground(color);
      travelRequestFormPanel.currencyCombo.setEnabled(false);

      travelRequestFormPanel.totalFundingField.setBackground(color);	
      travelRequestFormPanel.totalFundingField.setEditable(false);
      
	  // Make visible the fields related to the status
      travelRequestFormPanel.statusLabel.setVisible(true); 
      travelRequestFormPanel.statusNotesLabel.setVisible(true); 
      travelRequestFormPanel.statusCombo.setVisible(true);
      travelRequestFormPanel.statusNotesField.setVisible(true);
      
      // Make editable the fields related to the status
      color = Color.WHITE;
      
      travelRequestFormPanel.statusCombo.setBackground(color);
      travelRequestFormPanel.statusCombo.setEnabled(true);

      travelRequestFormPanel.statusNotesField.setBackground(color);	
      travelRequestFormPanel.statusNotesField.setEditable(true);
      
      // Display just the status for Change Status: Approved and Rejected
      changeStatus();
      
   } 
   
   public void originalStatus() 
   {
      travelRequestFormPanel.statusCombo.removeAllItems();
	  travelRequestFormPanel.statusCombo.addItem("Pending");
	  travelRequestFormPanel.statusCombo.addItem("Approved");
	  travelRequestFormPanel.statusCombo.addItem("Rejected");
	  travelRequestFormPanel.statusCombo.setSelectedItem("Pending");
	  travelRequestFormPanel.statusNotesField.setText(""); 
   }
   
   public void changeStatus() 
   {
      travelRequestFormPanel.statusCombo.removeAllItems();
	  travelRequestFormPanel.statusCombo.addItem("Approved");
	  travelRequestFormPanel.statusCombo.addItem("Rejected");
	  travelRequestFormPanel.statusCombo.setSelectedItem("Approved");
	  travelRequestFormPanel.statusNotesField.setText(""); 	   
   }
   
   
}