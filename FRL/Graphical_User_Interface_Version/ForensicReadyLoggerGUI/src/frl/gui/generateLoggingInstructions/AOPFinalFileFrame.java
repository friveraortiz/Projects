package frl.gui.generateLoggingInstructions;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import frl.controller.generateLoggingInstructions.AOPFinalFileController;
import frl.gui.main.MainFrame;
import frl.gui.projectTable.ProjectTableListener;
import frl.gui.projectTable.ProjectTablePanel;
import frl.gui.toolBar.ToolBar;
import frl.gui.toolBar.ToolBarListener;
import frl.gui.user.LoginFrame;
import frl.model.loadUMLSequenceDiagram.ParameterDataSequenceDiagram;
import frl.model.user.UserLevel;
import frl.process.generateLoggingInstructions.GenerateLoggingInstructions;

//Package #6
//Class #5
public class AOPFinalFileFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	// Define the attributes for the class
    private ToolBar toolBar; 
    private AOPFinalFileFormPanel aopFinalFileFormPanel;
    private ProjectTablePanel projectTablePanel;
    
    private AOPFinalFileController aopFinalFileController;
    
    public static String menu="";
    public static String submenu="";
    public JFileChooser fileChooser;
    
    // Constructor of the UserFrame class
    // Method #1
	public AOPFinalFileFrame (String userP, String menuP, UserLevel userLevelP, 
			                  String databaseConfigFile, String featuresConfigFile) 
	{
        super("Generate the Logging Instructions");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        aopFinalFileFormPanel = new AOPFinalFileFormPanel(databaseConfigFile, featuresConfigFile);
        projectTablePanel = new ProjectTablePanel();
        
        if (menuP.equals("Generate Logging Instructions")) 
        {
           toolBar = new ToolBar(userP, menuP, userLevelP);
        }
        
        // Define an object for the Controller Class
        aopFinalFileController = new AOPFinalFileController();
        
        // Define an object for the UserTablePanel Class
        projectTablePanel.setData(aopFinalFileController.getConfigFiles());
        
        // Add a new Listener for the ProjectTablePanel
        projectTablePanel.setProjectTableListener((ProjectTableListener) new ProjectTableListener() 
        {
        	@Override
			public void projectDeletedOccurred(int row) 
			{
               //aopFinalFileController.deleteProject(row);
			}
        	
        	@Override
			public void projectEventOccurred(int id, String popupMenu) 
			{
 	
			   // Show the UserForm Panel
			   aopFinalFileFormPanel.setVisible(true);
		     	   
	     	   // Clean the current values in the Configuration File Form
			   aopFinalFileFormPanel.cleanFields(databaseConfigFile);
	     	   
	     	   // Get all the values from the selected row into the Project Configuration File Form
			   refreshProject(databaseConfigFile);
			    
			   if (popupMenu.equals("View"))
			   {
			      menu = "UML Sequence Diagram";
	              submenu = "View";
	               
	              // Makes invisible the UserToolBar Add New Button
				  ToolBar.addNewButton.setVisible(false);
	                
				  // Makes visible the ConfigurationFormPanel
	              aopFinalFileFormPanel.setVisible(true);
	               
	              // Disable the fields, open buttons and combos in the ConfigurationFormPanel
	              aopFinalFileFormPanel.disableFields();
	               
	              // Enable the save button in the Configuration Form Panel
	              aopFinalFileFormPanel.enableViewButton();
	                
	              // Makes invisible the AOPFileTablePanel
				  projectTablePanel.setVisible(false); 
			   }
		       else
		          if (popupMenu.equals("Modify"))
		          {
				     menu = "UML Sequence Diagram";
		             submenu = "Modify";
		               
		             // Makes invisible the UserToolBar Add New Button
					 ToolBar.addNewButton.setVisible(false);
		                
					 // Makes visible the ConfigurationFormPanel
		             aopFinalFileFormPanel.setVisible(true);
		               
		             // Disable the fields, open buttons and combos in the ConfigurationFormPanel
		             aopFinalFileFormPanel.enableFields();
		               
		             // Enable the save button in the Configuration Form Panel
		             aopFinalFileFormPanel.enableGenerateButton();
		                
		             // Makes invisible the AOPFileTablePanel
					 projectTablePanel.setVisible(false);
					 					 
		          }
		          else
			         if (popupMenu.equals("Delete"))
			         {
					    menu = "UML Sequence Diagram";
			            submenu = "Delete";
			               
			            // Makes invisible the UserToolBar Add New Button
						ToolBar.addNewButton.setVisible(false);
			                
						// Makes visible the ConfigurationFormPanel
			            aopFinalFileFormPanel.setVisible(true);
			               
			            // Disable the fields, open buttons and combos in the ConfigurationFormPanel
			            aopFinalFileFormPanel.disableFields();
			               
			            // Enable the save button in the Configuration Form Panel
			            aopFinalFileFormPanel.enableDeleteButton();
			                
			            // Makes invisible the AOPFileTablePanel
					    projectTablePanel.setVisible(false); 
			         }
			}

		});

        // Retrieving the existing users in the ProjectTable Panel
        loadingProjects(databaseConfigFile);
                
        // MenuBar
        setJMenuBar(createUserMenuBar(userP, userLevelP, databaseConfigFile, featuresConfigFile));
        
        // Listener for the toolbar object
        toolBar.setUserToolBarListener(new ToolBarListener()
        {
			@Override
			public void addNewEventOccurred() 
			{				   
		       // Clean the current values in the Configuration File Form
			   aopFinalFileFormPanel.cleanFields(databaseConfigFile);
		     	   
		       // Get all the values from the selected row into the Project Configuration File Form
			   refreshProject(databaseConfigFile);
				   
			   // campos modificados
			   menu    = "UML Sequence Diagram";
               submenu = "Add";
	               
	           // Makes invisible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(false);
	                
			   // Makes visible the ConfigurationFormPanel
	           aopFinalFileFormPanel.setVisible(true);
	               
	           // Disable the fields, open buttons and combos in the ConfigurationFormPanel
	           aopFinalFileFormPanel.enableFields();
	               
	           // Enable the save button in the Configuration Form Panel
	           aopFinalFileFormPanel.enableGenerateButton();
	                
	           // Makes invisible the AOPFileTablePanel
			   projectTablePanel.setVisible(false);
			   
			}
        });	
        
        // Code for the listeners
        aopFinalFileFormPanel.setUserFormListener(new AOPFinalFileFormListener() 
        {
			@Override
			public boolean progLanguageComboEventOccurred(AOPFinalFileFormEvent ev) 
			{
		       String progLang = "";
		       
		       progLang = ev.getProgrammingLanguageName();
			   aopFinalFileFormPanel.loadingProgLangDet(databaseConfigFile, progLang);
			   
			   return true;
			}
			
			@Override
			public boolean openLogDirectoryEventOccurred(AOPFinalFileFormEvent ev) 
			{
			   String logDirectory="";
			   int returnVal;
					   
			   fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			   returnVal = fileChooser.showOpenDialog(null);
			   if(returnVal == JFileChooser.APPROVE_OPTION)
			   {	
				  logDirectory = fileChooser.getSelectedFile().getAbsolutePath();
			      aopFinalFileFormPanel.logDirectoryField.setText(logDirectory);
			   }			
						
			   return true;
			}
			
			@Override
			public boolean openAOPDirectoryEventOccurred(AOPFinalFileFormEvent ev) 
			{
			   String aOPDirectory="";
			   int returnVal;
					   
			   fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			   returnVal = fileChooser.showOpenDialog(null);
			   if(returnVal == JFileChooser.APPROVE_OPTION)
			   {	
				  aOPDirectory = fileChooser.getSelectedFile().getAbsolutePath();
			      aopFinalFileFormPanel.aOPDirectoryField.setText(aOPDirectory);
			   }			
						
			   return true;
			}
			
	 	    @Override
		    public boolean generateEventOccurred(AOPFinalFileFormEvent ev) 
	 	    {
	 	       String logDir="", projectName="", errorMessage1="";
	 	       int projectId=0, annotationsQuantity=0, propertiesQuantity=0, headerId=0;
	 	       
	 	       // Get the data from the GUI
	 	       projectId   = ev.getProjectId();
	 	       projectName = ev.getProjectName();
	 	       logDir      = aopFinalFileFormPanel.logDirectoryField.getText();
	 	       headerId    = 3;
	 	       
	 	       try 
	 	       {
	 	    	  annotationsQuantity = aopFinalFileController.validateAnnotationsQuantity(databaseConfigFile, projectId);
			   } 
	 	       catch (Exception e1) 
	 	       {
	 	 	      errorMessage1 = e1.getMessage();	   
		 	 	  JOptionPane.showMessageDialog(AOPFinalFileFrame.this, errorMessage1, 
		 	 	    		                    "Validating the Annotations in the UML Sequence Diagram.", 
		 	 	    		                     JOptionPane.ERROR_MESSAGE);
		 	 	  return false;
			   } 
	 	       
	 	      //annotationsQuantity = 0;
	 	       
	 	       try 
	 	       {
	 	    	  propertiesQuantity = aopFinalFileController.validatePropertiesQuantity(databaseConfigFile, projectId, headerId);
			   } 
	 	       catch (Exception e2) 
	 	       {
	 	 	      errorMessage1 = e2.getMessage();	   
		 	 	  JOptionPane.showMessageDialog(AOPFinalFileFrame.this, errorMessage1, 
		 	 	    		                    "Validating the Properties for the AOP Template File", 
		 	 	    		                     JOptionPane.ERROR_MESSAGE);
		 	 	  return false;
			   } 
	 	       	 	      
	 	       // Validate if the Log Directory is not empty or null
	 	       if(logDir.trim().isEmpty()== true ) 
	 	       {
	 	 	      errorMessage1 = "Error XXXX: The Log Directory should have a value and not be empty.";
	 	 	      JOptionPane.showMessageDialog(AOPFinalFileFrame.this, errorMessage1, 
	 	 	    		                        "Validating the Log Directory", 
	 	 	    		                        JOptionPane.ERROR_MESSAGE);
	 	 	      return false;
	 	 	
	 	       }
	 	       else 
	 	    	  // Validate if there are any Annotations in the UML Sequence Diagram 
	 	          if(annotationsQuantity == 0)
	 	          {
	 	             errorMessage1 = "Error XXXX: The Project Identifier: " + projectId + " and Project Name: "+ projectName + System.lineSeparator() +
	 	            		         "has not any annotations in the UML Sequence Diagram." + System.lineSeparator() +
	 	            		         "Therefore, go to the Annotate UML Sequence Diagram module first.";
	 	                             
	 	             JOptionPane.showMessageDialog(AOPFinalFileFrame.this, errorMessage1, 
	 	    		                        "Validating the Annotations in the UML Sequence Diagram", 
	 	    		                        JOptionPane.ERROR_MESSAGE);
	 	             return false;
	 	          }
	 	 	      else 
	 	 	         // Validate if there are any Properties for the AOP Template File with Header Id = 3
	 	 	         if(propertiesQuantity == 0)
	 	 	         {
	 	 	             errorMessage1 = "Error XXXX: The Project Identifier: " + projectId + " and Project Name: "+ projectName + System.lineSeparator() +
	 	 	            		         "has not any properties in the AOP Template File with Header Identifier: " + headerId + System.lineSeparator() +
	 	 	            		         "Therefore, go to the Configuration module and then to the AOP Template File submodule first.";
	 	 	                             
	 	 	             JOptionPane.showMessageDialog(AOPFinalFileFrame.this, errorMessage1, 
	 	 	    		                        "Validating the Properties in the AOP Template File", 
	 	 	    		                        JOptionPane.ERROR_MESSAGE);
	 	 	             return false;
	 	 	          }
	 	              else	   
	 	              {	
	 	                 generateLoggingInstructions(databaseConfigFile, featuresConfigFile, ev);
	     		  
	     	             // Add the new record into the Project Table Panel
	     	             projectTablePanel.setVisible(true); 
	     	             projectTablePanel.refresh();
			             loadingProjects(databaseConfigFile);
			      
			             aopFinalFileFormPanel.setVisible(false); 
			             ToolBar.addNewButton.setVisible(true);
			      
			            return true;   
	 	             }
			}

			@Override
			public boolean cancelEventOccurred() 
			{
	           // Makes invisible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(true);
				   
	           // Makes visible the AOPFileTablePanel
			   projectTablePanel.setVisible(true); 
			   
			   return true;
			}
			
			@Override
			public boolean userNameComboEventOccurred(AOPFinalFileFormEvent ev) 
			{
			   String userName="";
			   int projectId=0, userId=0, methodId=0, parameterId=0, parameterDataTypeId=0, progrLanguageId=0, attributeId=0;
			   ParameterDataSequenceDiagram parameterDataSequenceDiagramRecord;
			       
			   // Remove all the elements from the combo
			   aopFinalFileFormPanel.methodNameCombo.removeAllItems();
			   aopFinalFileFormPanel.parameterNameCombo.removeAllItems();
			   aopFinalFileFormPanel.attributeNameCombo.removeAllItems();
			 	   
			   // Get the Values from the GUI
			   projectId       = ev.getProjectId();
			   userName        = ev.getUserName();
			   progrLanguageId = ev.getProgrammingLanguageId();
			       
			   // Get the User Details: User Id
			   userId = aopFinalFileFormPanel.getUserId(databaseConfigFile, projectId, userName);
			     
			   // Get the Methods associated to this User Id
			   methodId = aopFinalFileFormPanel.loadMethods(databaseConfigFile, projectId, userId);
			   
			   // Get the Parameters associated to this UserId
			   parameterDataSequenceDiagramRecord = aopFinalFileFormPanel.loadParameters(databaseConfigFile, projectId, progrLanguageId, methodId);
			   
			   if(parameterDataSequenceDiagramRecord != null) 
			   {
			      parameterId         = parameterDataSequenceDiagramRecord.getParameterId();
			      parameterDataTypeId = parameterDataSequenceDiagramRecord.getParameterDataTypeId();
			   }
			   
			   // Get the Attributes associated to this UserId
			   attributeId = aopFinalFileFormPanel.loadAttributes(databaseConfigFile, projectId, progrLanguageId, methodId, parameterId, parameterDataTypeId);
			       
			   return true;
			}

			@Override
			public boolean methodNameComboEventOccurred(AOPFinalFileFormEvent ev) 
			{
			   String methodName="";
			   int projectId=0, userId=0, methodId=0, parameterId=0, parameterDataTypeId=0, progrLanguageId=0, attributeId=0;
			   ParameterDataSequenceDiagram parameterDataSequenceDiagramRecord;
						   
			   // Remove all the elements from the combo
			   aopFinalFileFormPanel.parameterNameCombo.removeAllItems();
			   aopFinalFileFormPanel.attributeNameCombo.removeAllItems();
				 	   
			   // Get the Values from the GUI
			   projectId       = ev.getProjectId();
			   userId          = ev.getUserId();
			   methodId        = ev.getMethodId();
			   methodName      = ev.getMethodName();
			   progrLanguageId = ev.getProgrammingLanguageId();
				       
			   // Get the Method Details: Method Id
			   methodId = aopFinalFileFormPanel.getMethodId(databaseConfigFile, projectId, userId, methodName);
			   
			   // Get the Parameters associated to this UserId
			   parameterDataSequenceDiagramRecord = aopFinalFileFormPanel.loadParameters(databaseConfigFile, projectId, progrLanguageId, methodId);
			   
			   if(parameterDataSequenceDiagramRecord != null) 
			   {
			      parameterId         = parameterDataSequenceDiagramRecord.getParameterId();
			      parameterDataTypeId = parameterDataSequenceDiagramRecord.getParameterDataTypeId();
			   }
			   
			   // Get the Attributes associated to this UserId
			   attributeId = aopFinalFileFormPanel.loadAttributes(databaseConfigFile, projectId, progrLanguageId, methodId, parameterId, parameterDataTypeId);
		   
		       return true;
			}

			@Override
			public boolean parameterNameComboEventOccurred(AOPFinalFileFormEvent ev) 
			{
			   int projectId=0, methodId=0, progrLanguageId=0, parameterId=0, parameterDataTypeId=0, attributeId=0;
			   String parameterFullName="";
			   ParameterDataSequenceDiagram parameterDataSequenceDiagramRecord;
			   
			   // Remove all the elements from the combo	
			   aopFinalFileFormPanel.attributeNameCombo.removeAllItems();
			   
			   // Get the Values from the GUI
			   projectId            = ev.getProjectId();
			   methodId             = ev.getMethodId();
			   progrLanguageId      = ev.getProgrammingLanguageId();
			   parameterId          = ev.getParameterId();
			   parameterDataTypeId  = ev.getParameterDataTypeId();
			   parameterFullName    = ev.getParameterFullName();
			   			   
			   // Get the Details of the Parameters associated to this Parameter Name
			   parameterDataSequenceDiagramRecord = aopFinalFileFormPanel.getParameterDetails(databaseConfigFile, projectId, progrLanguageId, 
					                                                                          methodId, parameterFullName);
			   
			   if(parameterDataSequenceDiagramRecord != null) 
			   {
			      parameterId         = parameterDataSequenceDiagramRecord.getParameterId();
			      parameterDataTypeId = parameterDataSequenceDiagramRecord.getParameterDataTypeId();
			   }
			   
			   // Get the Attributes associated to this UserId
			   attributeId = aopFinalFileFormPanel.loadAttributes(databaseConfigFile, projectId, progrLanguageId, methodId, parameterId, parameterDataTypeId);
			   
			   return true;
			}

			@Override
			public boolean attributeNameComboEventOccurred(AOPFinalFileFormEvent ev) 
			{
			   int projectId=0, methodId=0, progrLanguageId=0, parameterId=0, parameterDataTypeId=0, attributeId=0;
			   String attributeFullName="";
			   
		       // Get the Values from the GUI
			   projectId            = ev.getProjectId();
			   methodId             = ev.getMethodId();
			   progrLanguageId      = ev.getProgrammingLanguageId();
			   parameterId          = ev.getParameterId();
			   parameterDataTypeId  = ev.getParameterDataTypeId();
			   attributeFullName    = ev.getAttributeName();
				
			   attributeId = aopFinalFileFormPanel.getAttributeId(databaseConfigFile, projectId, progrLanguageId, methodId, parameterId, 
                                                                  parameterDataTypeId, attributeFullName);
			   return true;
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
        add(aopFinalFileFormPanel, BorderLayout.WEST);
        add(projectTablePanel, BorderLayout.CENTER);
        
        // Set the Size of the Form
        pack();
		this.setSize(1200, 600);
		
		// Set the location in the center of the screen
		setLocationRelativeTo(null);
		
		// Set up that the windows cannot be resizable by the User
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
        //Display the window.
        this.setVisible(true);
   
	   	// Makes Not Visible the Panels
		aopFinalFileFormPanel.setVisible(false);
		projectTablePanel.setVisible(true);
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
		
		// Listener for the FRL System > Exit
		mainMenuFrlSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			   // Close the current window	
			   aopFinalFileFormPanel.setVisible(false);
			   projectTablePanel.setVisible(false);
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
						    AOPFinalFileFrame.this, "Do you really want to Sign Out the Forensic Ready Logger System?", 
						    "Confirm Sign Out", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   // Close the current MainFrame Window 	
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
						AOPFinalFileFrame.this, "Do you really want to exit the Forensic Ready Logger System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   WindowListener[] listeners = getWindowListeners();
					
				   for(WindowListener listener: listeners) 
				   {
					  // Calls the Windows Closing Event
				      listener.windowClosing(new WindowEvent(AOPFinalFileFrame.this, 0));
				   } 	
			    }
			}
		});
		
		return menuBar;
	}
	
	// This method will display all the values of the selected row in the userFormPanel
	// Method #3
	public void refreshProject(String databaseConfigFile)
	{
	   String stringSel="";
	   int projectId=0, userId=0, methodId=0, parameterId=0, parameterDataTypeId=0, progrLanguageId=0, attributeId=0;
	   ParameterDataSequenceDiagram parameterDataSequenceDiagramRecord = null;
		
	   // Id
	   stringSel = projectTablePanel.getProjectStringColSel(0);
	   aopFinalFileFormPanel.projectIdField.setText(stringSel);
	   projectId = Integer.valueOf(stringSel);

	   // Project Name
	   stringSel = projectTablePanel.getProjectStringColSel(1);
	   aopFinalFileFormPanel.projectNameField.setText(stringSel);
     
	   // JAR File Name
	   stringSel = projectTablePanel.getProjectStringColSel(2);
	   aopFinalFileFormPanel.jarFileNameField.setText(stringSel);

	   // Project Input Directory
	   stringSel = projectTablePanel.getProjectStringColSel(3);
	   aopFinalFileFormPanel.projInputDirField.setText(stringSel);	   

	   // Project Output Directory
	   stringSel = projectTablePanel.getProjectStringColSel(4);
	   aopFinalFileFormPanel.projOutputDirField.setText(stringSel);	 

	   // Programming Language
	   stringSel = projectTablePanel.getProjectStringColSel(5);
	   aopFinalFileFormPanel.progLanguageCombo.setSelectedItem(stringSel);	
	   
	   // DBMS
	   stringSel = projectTablePanel.getProjectStringColSel(6);
	   aopFinalFileFormPanel.dbmsCombo.setSelectedItem(stringSel);	
	   
	   // Input Method
	   stringSel = projectTablePanel.getProjectStringColSel(7);
	   aopFinalFileFormPanel.inputMethodCombo.setSelectedItem(stringSel);	
	   
	   // Start Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(8);
	   aopFinalFileFormPanel.startProjMethodField.setText(stringSel);	
	   
	   // End Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(9);
	   aopFinalFileFormPanel.endProjMethodField.setText(stringSel);	
	   
	   // Connect Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(10);
	   aopFinalFileFormPanel.connectProjMethodField.setText(stringSel);	
	   
	   // Operating System Method
	   stringSel = projectTablePanel.getProjectStringColSel(12);
	   aopFinalFileFormPanel.currentOperatingSystemField.setText(stringSel);	
	   
	   aopFinalFileFormPanel.projectNameField.requestFocusInWindow();
	   
	   // Get the programming language
	   progrLanguageId = getProgrammingLanguageId(databaseConfigFile);
	   
	   // Get the Information for the different Combos
	   userId   = aopFinalFileFormPanel.loadUsers(databaseConfigFile, projectId);
	   methodId = aopFinalFileFormPanel.loadMethods(databaseConfigFile, projectId, userId);
	   
	   parameterDataSequenceDiagramRecord = aopFinalFileFormPanel.loadParameters(databaseConfigFile, projectId, progrLanguageId, methodId);
	   
	   if(parameterDataSequenceDiagramRecord != null) 
	   {
	      parameterId         = parameterDataSequenceDiagramRecord.getParameterId();
	      parameterDataTypeId = parameterDataSequenceDiagramRecord.getParameterDataTypeId();
	   }
	   
	   attributeId = aopFinalFileFormPanel.loadAttributes(databaseConfigFile, projectId, progrLanguageId, methodId, parameterId, parameterDataTypeId);
	   
	}
	
	
   // Method #4
   public void loadingProjects(String databaseConfigFile)
   {
	   
	  try 
	  {
	     aopFinalFileController.loadProjects(databaseConfigFile);
		 projectTablePanel.refresh();
	  } 
	  catch (Exception e) 
	  {
		 // Error #1 
		 String errorMessage = e.getMessage();
	     JOptionPane.showMessageDialog(AOPFinalFileFrame.this, "Error 6541: Occurred while loading the Projects. Error Message: "+errorMessage, "Loading Projects", JOptionPane.ERROR_MESSAGE);	
	  }
		
   }
   
   void printErrorMessage(String errorMessage, String title)
   {
      // Validate if the errorMessage is not empty                                                              
	  if(errorMessage != null && !errorMessage.isEmpty())                                                                                                                      
	  { 
	     JOptionPane.showMessageDialog(AOPFinalFileFrame.this, errorMessage , title, JOptionPane.ERROR_MESSAGE);
      }
   }
   
   void generateLoggingInstructions(String databaseConfigFile, String featuresConfigFile, AOPFinalFileFormEvent ev)
   {
      String errorMessage="";
      GenerateLoggingInstructions genLogInst = new GenerateLoggingInstructions();
	  
      errorMessage = genLogInst.generateLoggingInstructions(databaseConfigFile, featuresConfigFile, ev, aopFinalFileController);
      
      if(!errorMessage.trim().isEmpty())
         JOptionPane.showMessageDialog(this, errorMessage, "Generate Logging Instructions.", JOptionPane.ERROR_MESSAGE);	

   }
   
   void delete(String databaseConfigFile, String featuresConfigFile, AOPFinalFileFormEvent ev)
   {

	   
   }
   
   int getProgrammingLanguageId(String databaseConfigFile)
   {
      int progrLanguageId=0;
      String progLanguageIdStr="", errorMessage="";
      
      try 
      {
         progrLanguageId = aopFinalFileController.getProgrammingLanguageId(databaseConfigFile, aopFinalFileFormPanel.progLanguageCombo.getSelectedItem().toString());
      
         progLanguageIdStr = Integer.toString(progrLanguageId); 
	     aopFinalFileFormPanel.progLanguageIdField.setText(progLanguageIdStr);
      } 
      catch (Exception e) 
      {
         errorMessage = e.getMessage();
	     JOptionPane.showMessageDialog(AOPFinalFileFrame.this, "Error XXXX: Occurred while the Programming Language Identifier. Error Message: " + errorMessage, "Loading Programming Language Id", JOptionPane.ERROR_MESSAGE);	
      }
      
      return progrLanguageId;
   }
}