package frl.gui.generateAspectOrientedFiles;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


import frl.controller.generateAspectOrientedFiles.AOPFileController;
import frl.gui.configFile.ConfigFileFormEvent;
import frl.gui.configFile.ConfigFileFormListener;
import frl.gui.configFile.ConfigFileFormPanel;
import frl.gui.main.MainFrame;
import frl.gui.projectTable.ProjectTableListener;
import frl.gui.projectTable.ProjectTablePanel;
import frl.gui.toolBar.ToolBar;
import frl.gui.toolBar.ToolBarListener;
import frl.gui.user.LoginFrame;
import frl.model.configuration.FRLConfiguration;
import frl.model.user.UserLevel;
import frl.process.configuration.JavaClasses;
import frl.process.configureAspectOrientedFiles.AspectOrientedFiles;
import frl.process.inputMethods.Database;
import frl.process.loadUMLSequenceDiagram.UMLSeqDiagram;

//Package #6
//Class #5
public class AOPFileFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	// Define the attributes for the class
    private ToolBar toolBar; 
    private ConfigFileFormPanel configFileFormPanel;
    private ProjectTablePanel projectTablePanel;
    private AOPFileController aopFileController;
    
    public String menu="";
    public String submenu="";
    public JFileChooser fileChooser;
    
    // Constructor of the UserFrame class
    // Method #1
	public AOPFileFrame (String userP, String menuP, UserLevel userLevelP, 
			             String databaseConfigFile, String featuresConfigFile) 
	{
        super("Generate the Aspect Oriented Programming Files");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        configFileFormPanel = new ConfigFileFormPanel(databaseConfigFile, featuresConfigFile);
        projectTablePanel = new ProjectTablePanel();
        
        if (menuP.equals("Generate AOP File")) 
        {
           toolBar = new ToolBar(userP, menuP, userLevelP);
        }
        
        // Define an object for the Controller Class
        aopFileController = new AOPFileController();
        
        // Define an object for the UserTablePanel Class
        projectTablePanel.setData(aopFileController.getConfigFiles());
        
        // Add a new Listener for the ProjectTablePanel
        projectTablePanel.setProjectTableListener(new ProjectTableListener() 
        {
			@Override
			public void projectDeletedOccurred(int row) 
			{
			   aopFileController.deleteProject(row);
			}

			//@Override
			public void projectEventOccurred(int id, String popupMenu) 
			{
			   // Show the UserForm Panel
			   configFileFormPanel.setVisible(true);
		     	   
	     	   // Clean the current values in the Configuration File Form
			   configFileFormPanel.cleanFields(databaseConfigFile);
	     	   
	     	   // Get all the values from the selected row into the Project Configuration File Form
			   refreshProject();
			   
			   if (popupMenu.equals("View"))
			   {
			      menu = "AOP File";
	              submenu = "View";
	               
	              // Makes invisible the UserToolBar Add New Button
				  ToolBar.addNewButton.setVisible(false);
	                
				  // Makes visible the ConfigurationFormPanel
	              configFileFormPanel.setVisible(true);
	               
	              // Disable the fields, open buttons and combos in the ConfigurationFormPanel
	              configFileFormPanel.disableFields();
	               
	              // Enable the save button in the Configuration Form Panel
	              configFileFormPanel.enableViewButton();
	                
	              // Makes invisible the AOPFileTablePanel
				  projectTablePanel.setVisible(false); 
			   }
		       else
		          if (popupMenu.equals("Modify"))
		          {
				     menu = "AOP File";
		             submenu = "Modify";
		               
		             // Makes invisible the UserToolBar Add New Button
					 ToolBar.addNewButton.setVisible(false);
		                
					 // Makes visible the ConfigurationFormPanel
		             configFileFormPanel.setVisible(true);
		               
		             // Disable the fields, open buttons and combos in the ConfigurationFormPanel
		             configFileFormPanel.enableFields();
		               
		             // Enable the save button in the Configuration Form Panel
		             configFileFormPanel.enableSaveButton();
		                
		             // Makes invisible the AOPFileTablePanel
					 projectTablePanel.setVisible(false);
					 					 
		          }
		          else
			         if (popupMenu.equals("Delete"))
			         {
					    menu = "AOP File";
			            submenu = "Delete";
			               
			            // Makes invisible the UserToolBar Add New Button
						ToolBar.addNewButton.setVisible(false);
			                
						// Makes visible the ConfigurationFormPanel
			            configFileFormPanel.setVisible(true);
			               
			            // Disable the fields, open buttons and combos in the ConfigurationFormPanel
			            configFileFormPanel.disableFields();
			               
			            // Enable the save button in the Configuration Form Panel
			            configFileFormPanel.enableDeleteButton();
			                
			            // Makes invisible the AOPFileTablePanel
					    projectTablePanel.setVisible(false); 
			         }
			}
		});

        // Retrieving the existing users in the AOPFileTable Panel
        loadingProjects(databaseConfigFile);
                
        // MenuBar
        setJMenuBar(createUserMenuBar(userP, userLevelP, databaseConfigFile, featuresConfigFile));
        
        // Listener for the toolbar object
        toolBar.setUserToolBarListener(new ToolBarListener()
        {
			@Override
			public void addNewEventOccurred() 
			{	
			   menu = "AOP File";
               submenu = "Add";
               
               // Makes invisible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(false);
                
			   // Makes visible the ConfigurationFormPanel
               configFileFormPanel.setVisible(true);
               
               // Enable the fields, open buttons and combos in the ConfigurationFormPanel
               configFileFormPanel.enableFields();
               
               // Enable the save button in the Configuration Form Panel
               configFileFormPanel.enableSaveButton();
                
               // Makes invisible the AOPFileTablePanel
			   projectTablePanel.setVisible(false); 
			}
        });	
        
        // Code for the listeners
        configFileFormPanel.setUserFormListener(new ConfigFileFormListener() 
        {

			@Override
			public boolean configFileFormOpenProjectNameEventOccurred(ConfigFileFormEvent e) 
			{
			   String projectName;
			   int returnVal;
			   
			   fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		       returnVal = fileChooser.showOpenDialog(null);
		       
		       if(returnVal == JFileChooser.APPROVE_OPTION)
		       {	
		    	  projectName = fileChooser.getSelectedFile().getName(); 
		          configFileFormPanel.projectNameField.setText(projectName);
		       }
			   return true;
			}
			
			@Override
			public boolean configFileFormOpenJarFileEventOccurred(ConfigFileFormEvent e) 
			{
			   String jarFileName="";
			   int returnVal;
			   
		       fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			   FileNameExtensionFilter filter = new FileNameExtensionFilter("JAR Files", "jar");
			   fileChooser.setFileFilter(filter);

			   returnVal = fileChooser.showOpenDialog(null);
			   
			   if(returnVal == JFileChooser.APPROVE_OPTION)
			   {	
			      jarFileName = fileChooser.getSelectedFile().getName();			      
			      configFileFormPanel.jarFileNameField.setText(jarFileName);
			   }			
				
			   return true;
			}
			
			@Override
			public boolean configFileFormOpenProjInputDirEventOccurred(ConfigFileFormEvent e) 
			{
			   String openProjInputDir;
			   int returnVal;
			   
		       fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			   
			   returnVal = fileChooser.showOpenDialog(null);
			   if(returnVal == JFileChooser.APPROVE_OPTION)
			   {	
			      openProjInputDir = fileChooser.getSelectedFile().getAbsolutePath(); 
			      configFileFormPanel.projInputDirField.setText(openProjInputDir);
			   }

		       return true;
			}

			@Override
			public boolean configFileFormOpenProjOutputDirEventOccurred(ConfigFileFormEvent e) 
			{
			   String openProjOutputDir;
			   int returnVal;
			   
			   fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			   returnVal = fileChooser.showOpenDialog(null);
			   if(returnVal == JFileChooser.APPROVE_OPTION)
			   {	
			      openProjOutputDir = fileChooser.getSelectedFile().getAbsolutePath(); 
				  configFileFormPanel.projOutputDirField.setText(openProjOutputDir);
			   }
				return true;
			}
			
			@Override
			public boolean configFileFormProgLanguageComboEventOccurred(ConfigFileFormEvent e) 
			{
		       String progLang = "";
		       
		       progLang = e.getProgLanguage();
			   configFileFormPanel.loadingProgLangDet(databaseConfigFile, progLang);
			   
			   return true;
			}
			
	 	    @Override
		    public boolean configFileFormSaveEventOccurred(ConfigFileFormEvent ev) 
	 	    {
	 	       boolean validProject=false, validJarFile1=false, validJarFile2=false, validDirs=false, 
	 	    		   validConfigFile1=false, validStartMethod=false, validConnectMethod=false, 
	 	    		   validEndMethod=false, validConnectMethodReturnValue=false;
	 	       String jarFileName="", inputDir="", outputDir="", projectName="", startMethod="",
	 	    		  connectMethod="", endMethod="", connectMethodReturnValue="", errorMessage="";
	 	       
	 	       // Get the data from the GUI
	 	       projectName   = configFileFormPanel.projectNameField.getText();
	 	       jarFileName   = configFileFormPanel.jarFileNameField.getText();
	 	       inputDir      = configFileFormPanel.projInputDirField.getText();
	 	       outputDir     = configFileFormPanel.projOutputDirField.getText();
	 	       startMethod   = configFileFormPanel.startProjMethodField.getText();
	 	       connectMethod = configFileFormPanel.connectProjMethodField.getText();
	 	       connectMethodReturnValue = configFileFormPanel.connectProjMethodReturnValueField.getText();
	 	       endMethod     = configFileFormPanel.endProjMethodField.getText();
	 	       
	 	       // Validations
	 	       try 
	 	       {
			      validProject = aopFileController.validateProjectName(databaseConfigFile, projectName, submenu);
			   } 
	 	       catch (Exception e1) 
	 	       {
	 	 	      errorMessage = e1.getMessage();
	              JOptionPane.showMessageDialog(AOPFileFrame.this, errorMessage, "Validating the Project Name: " + projectName, JOptionPane.ERROR_MESSAGE);
	              return false;
			   }
	 	       
	 	       try 
	 	       {
			      validJarFile1 = aopFileController.validateJarFile1(databaseConfigFile, jarFileName, submenu);
			   } 
	 	       catch (Exception e2) 
	 	       {
	 	 	      errorMessage = e2.getMessage();
	              JOptionPane.showMessageDialog(AOPFileFrame.this, errorMessage, "Validating the JAR File Name: " + jarFileName, JOptionPane.ERROR_MESSAGE);
	              return false;
			   }

	 	       validConfigFile1   = aopFileController.validateConfFileFields(ev);
	 	       validDirs          = aopFileController.validDirectories(inputDir, outputDir);
	 	       validJarFile2      = aopFileController.validateJarFile2(jarFileName);
	 	       
	 	       try 
	 	       {
			      validStartMethod = aopFileController.validateMethod(startMethod);
			   } 
	 	       catch (Exception e3) 
	 	       {
	 	 	      errorMessage = e3.getMessage();
	              JOptionPane.showMessageDialog(AOPFileFrame.this, errorMessage, "Validating the Start Method Name: " + startMethod, JOptionPane.ERROR_MESSAGE);
	              return false;
			   }
	 	       
	 	       try 
	 	       {
			      validConnectMethod = aopFileController.validateMethod(connectMethod);
			   }
	 	       catch (Exception e4) 
	 	       {
	 	 	      errorMessage = e4.getMessage();
	              JOptionPane.showMessageDialog(AOPFileFrame.this, errorMessage, "Validating the Connect Method Name: " + connectMethod, JOptionPane.ERROR_MESSAGE);
	              return false;
			   }
	 	       
	 	       validConnectMethodReturnValue = aopFileController.connectMethodReturnValue(connectMethodReturnValue);
	 	       
	 	       try 
	 	       {
			      validEndMethod = aopFileController.validateEndMethod(endMethod);
			   } 
	 	       catch (Exception e5) 
	 	       {
	 	 	      errorMessage = e5.getMessage();
	              JOptionPane.showMessageDialog(AOPFileFrame.this, errorMessage, "Validating the End Method Name: " + endMethod, JOptionPane.ERROR_MESSAGE);
	              return false;
			   }
	 	       
	 	       if(validConfigFile1 == false)
	 	       {	   
 	              // Error #4 
                  JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: All the fields should have a value and cannot be EMPTY.", "Validating the Project", JOptionPane.ERROR_MESSAGE);
                  return false;  
	 	       }
	 	       else
	     	      if (validProject == false) 
        	      {
		             // Error #1
		             JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: The Project Name: " + projectName + " already exists and it should be UNIQUE.", "Validating the Project Name", JOptionPane.ERROR_MESSAGE);
		             return false;
                  }
	     	      else 
	     	         if (validJarFile1 == false) 
        	         {
	                    // Error #2
			            JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: The JAR File Name: " + jarFileName + System.lineSeparator() +  " already exists and it should be UNIQUE.", "Validating the JAR File Name", JOptionPane.ERROR_MESSAGE);
 	                    return false;
                     }
		     	     else 
			            if (validJarFile2 == false) 
		        	    {
			               // Error #3
			               JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: The JAR File Name: " + jarFileName + " should have a JAR extension.", "Validating the Jar File Name", JOptionPane.ERROR_MESSAGE);
			               return false;
		                }
	     	            else 
	     	               if(validDirs == false) 
	     	               {
		                      // Error #4
		                      JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: The Input Directory: " + inputDir + System.lineSeparator() + " and the Output Directory: " + outputDir + System.lineSeparator() + " should be DIFFERENT.", "Validating the Directories", JOptionPane.ERROR_MESSAGE);
		                      return false;
	     	                }
	     	                else
	     	                	
	     	                	if(validStartMethod == false)
	 	     	                {
	  		                       // Error #5
	  		                       JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: The Start Project Method Name: " + startMethod + System.lineSeparator() + " is INVALID. It should contain 3 elements: Package Name, Class Name and Method Name." , "Validating the Start Project Method", JOptionPane.ERROR_MESSAGE);
	  		                       return false;
	  	     	                }
	     	                	else
	     	                	
	     	                	   if(validConnectMethod == false)
	                               {
                                      // Error #6
                                      JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: The Connect Project Method Name: " + connectMethod + System.lineSeparator() + " is INVALID. It should contain 3 elements: Package Name, Class Name and Method Name." , "Validating the Connect Project Method", JOptionPane.ERROR_MESSAGE);
                                      return false;
	                               }
	     	                	   else
	     	                	      if(validEndMethod == false)
		                              {
	                                     // Error #7
	                                     JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: The End Project Method Name: " + endMethod + System.lineSeparator() + " is INVALID. It should contain the word: EXIT." , "Validating the End Project Method", JOptionPane.ERROR_MESSAGE);
	                                     return false;
		                              }
	     	                          else 
	   	     	                	   if(validConnectMethodReturnValue == false)
		                               {
	                                      // Error #6
	                                      JOptionPane.showMessageDialog(AOPFileFrame.this, "Error XXXX: The Connect Project Method Return Value: " + validConnectMethodReturnValue + System.lineSeparator() + " is EMPTY. It should contain a value." , "Validating the Connect Project Method Return Value", JOptionPane.ERROR_MESSAGE);
	                                      return false;
		                               }
	   	     	                	   else
	     	                           {
	     		                          save(databaseConfigFile, featuresConfigFile, ev);
	     		  
	     		                          // Add the new record into the Project Table Panel
	     		                          projectTablePanel.setVisible(true); 
	     		                          projectTablePanel.refresh();
			                              loadingProjects(databaseConfigFile);
			      
			                              configFileFormPanel.setVisible(false); 
			                              ToolBar.addNewButton.setVisible(true);
			      
			                              return true;   
	     	                           }
			}

			@Override
			public boolean configFileFormDeleteEventOccurred(ConfigFileFormEvent ev) 
			{
	           delete(databaseConfigFile, featuresConfigFile, ev);
	           
	     	   projectTablePanel.setVisible(true); 
	     	   projectTablePanel.refresh();
			   loadingProjects(databaseConfigFile);
			      
			   configFileFormPanel.setVisible(false); 
			   ToolBar.addNewButton.setVisible(true);

		       return true;
			}

			@Override
			public boolean configFileFormCancelEventOccurred() 
			{
	           // Makes invisible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(true);
				   
	           // Makes visible the AOPFileTablePanel
			   projectTablePanel.setVisible(true); 
			   
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
        add(configFileFormPanel, BorderLayout.WEST);
        add(projectTablePanel, BorderLayout.CENTER);
        
        // Set the Size of the Form
        pack();
		this.setSize(1000, 600);
		
		// Set the location in the center of the screen
		setLocationRelativeTo(null);
		
		// Set up that the windows cannot be resizable by the User
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
        //Display the window.
        this.setVisible(true);
   
	   	// Makes Not Visible the Panels
		configFileFormPanel.setVisible(false);
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
			   configFileFormPanel.setVisible(false);
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
						AOPFileFrame.this, "Do you really want to Sign Out the Forensic Ready Logger System?", 
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
						AOPFileFrame.this, "Do you really want to exit the Forensic Ready Logger System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   WindowListener[] listeners = getWindowListeners();
					
				   for(WindowListener listener: listeners) 
				   {
					  // Calls the Windows Closing Event
				      listener.windowClosing(new WindowEvent(AOPFileFrame.this, 0));
				   } 	
			    }
			}
		});
		
		return menuBar;
	}
	
	// This method will display all the values of the selected row in the userFormPanel
	// Method #3
	public void refreshProject()
	{
	   String stringSel="";
		
	   // Id
	   stringSel = projectTablePanel.getProjectStringColSel(0);
	   configFileFormPanel.idField.setText(stringSel);

	   // Project Name
	   stringSel = projectTablePanel.getProjectStringColSel(1);
	   configFileFormPanel.projectNameField.setText(stringSel);
     
	   // JAR File Name
	   stringSel = projectTablePanel.getProjectStringColSel(2);
	   configFileFormPanel.jarFileNameField.setText(stringSel);

	   // Project Input Directory
	   stringSel = projectTablePanel.getProjectStringColSel(3);
	   configFileFormPanel.projInputDirField.setText(stringSel);	   

	   // Project Output Directory
	   stringSel = projectTablePanel.getProjectStringColSel(4);
	   configFileFormPanel.projOutputDirField.setText(stringSel);	 

	   // Programming Language
	   stringSel = projectTablePanel.getProjectStringColSel(5);
	   configFileFormPanel.progLanguageCombo.setSelectedItem(stringSel);	
	   
	   // DBMS
	   stringSel = projectTablePanel.getProjectStringColSel(6);
	   configFileFormPanel.dbmsCombo.setSelectedItem(stringSel);	
	   
	   // Input Method
	   stringSel = projectTablePanel.getProjectStringColSel(7);
	   configFileFormPanel.inputMethodCombo.setSelectedItem(stringSel);	
	   
	   // Start Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(8);
	   configFileFormPanel.startProjMethodField.setText(stringSel);	
	   
	   // End Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(9);
	   configFileFormPanel.endProjMethodField.setText(stringSel);	
	   
	   // Connect Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(10);
	   configFileFormPanel.connectProjMethodField.setText(stringSel);	
	   
	   // Connect Project Method Return Value
	   stringSel = projectTablePanel.getProjectStringColSel(11);
	   configFileFormPanel.connectProjMethodReturnValueField.setText(stringSel);	
	   
	   // Operating System Method
	   stringSel = projectTablePanel.getProjectStringColSel(12);
	   configFileFormPanel.operatingSystemField.setText(stringSel);	
	   
	   configFileFormPanel.projectNameField.requestFocusInWindow();
	}
	
   // Method #4
   public void loadingProjects(String databaseConfigFile)
   {
	  	  
	  try 
	  {
	     aopFileController.load(databaseConfigFile);
		 projectTablePanel.refresh();
	  } 
	  catch (Exception e) 
	  {
		 // Error #1 
		 String errorMessage = e.getMessage();
	     JOptionPane.showMessageDialog(AOPFileFrame.this, "Error 6541: Occurred while loading the Users. Error Message: "+errorMessage, "Loading Users", JOptionPane.ERROR_MESSAGE);	
	  }
		
   }
   
   void printErrorMessage(String errorMessage, String title)
   {
      // Validate if the errorMessage is not empty                                                              
	  if(errorMessage != null && !errorMessage.isEmpty())                                                                                                                      
	  { 
	     JOptionPane.showMessageDialog(AOPFileFrame.this, errorMessage , title, JOptionPane.ERROR_MESSAGE);
      }
   }
   
   void save(String databaseConfigFile, String featuresConfigFile, ConfigFileFormEvent ev)
   {
	  String errorMessage = "";
	  int projectId=0;
	  
      FRLConfiguration frlCon;
	  JavaClasses jc;
	  Database db;
	  AspectOrientedFiles aof;

	  // Store the Project Configuration into the Database 
	  try 
	  {
	     projectId = aopFileController.save(ev, databaseConfigFile);
	  } 
	  catch (Exception e) 
	  {
         errorMessage = e.getMessage();
	  }
	  
	  // If there is any error, print on the screen the error message
	  printErrorMessage(errorMessage, "Saving the Project Configuration");
	  
	  if(errorMessage.isEmpty() == true)
	  {	  
	     // Load the Project Configuration and the Forensic-Ready Logger General Parameters
	     frlCon = new FRLConfiguration();
	     errorMessage = frlCon.configureParameters(ev, featuresConfigFile, databaseConfigFile); 
	  
	     // If there is any error, print on the screen the error message
	     printErrorMessage(errorMessage, "Loading the Parameters from the Configuration File");
	     
	     if(errorMessage.isEmpty() == true)
		 {	 
	        // Get the Source Code Files of the Project
	        jc = new JavaClasses();
	        errorMessage = jc.getSourceCode(frlCon, projectId); 
	  
	        // If there is any error, print on the screen the error message
	        printErrorMessage(errorMessage, "Loading the Source Code");
	        
	        // AQUI VOY: 
	        
	        if(errorMessage.isEmpty() == true)
			{
	           // Get the Methods that perform Data Changes                                                                                             
	           db = new Database(frlCon); 
	           errorMessage = db.getInputMethodResults(frlCon, projectId);
	  
	           // If there is any error, print on the screen the error message
	           printErrorMessage(errorMessage, "Loading the Methods that perform Data Changes");
	           
	           if(errorMessage.isEmpty() == true)
	           {	   
                  // Generate the Aspect Files into the Project Output Directory                                                                                   
	              aof = new AspectOrientedFiles();                                                                                         
	              errorMessage = aof.generateAspectOrientedFiles(projectId, frlCon);
	  
	              // If there is any error, print on the screen the error message
	              printErrorMessage(errorMessage, "Creating the Aspect Oriented Files");
	           }
	           
			}
			
		 }
	  }

   }
   
   void delete(String databaseConfigFile, String featuresConfigFile, ConfigFileFormEvent ev)
   {
	  int projectId = 0;
      String errorMessage="", projectOutputDir="";
      JavaClasses jc = new JavaClasses();
      UMLSeqDiagram umlSeqDiag = new UMLSeqDiagram();

      // Get the values from the GUI
	  projectId        = ev.getId();
	  projectOutputDir = ev.getProjOutputDir();

      // Deleting the previous UML Sequence Diagram from the uml_sequence_diagram_plain Table
	  errorMessage = umlSeqDiag.deleteUMLSequenceDiagramPlain(databaseConfigFile, projectId);
	     
	  // If there is any error, print on the screen the error message
	  printErrorMessage(errorMessage, "Deleting the Data of the UML Sequence Diagram Plain");
	  
	  /* Delete all the data related to the Annotations of the Project
	   * method_file_sequence_diagram, user_file_sequence_diagram, uml_sequence_diagram_final
	   * value_sequence_diagram, return_type_value_sequence_diagram, attribute_sequence_diagram 
	   * parameter_sequence_diagram, method_step_sequence_diagram, method_sequence_diagram 
	   * user_sequence_diagram 
	  */
	  try 
	  {
	     umlSeqDiag.deletePreviousSeqDiagramsData(projectId, databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
	  }
	  
	  // If there is any error, print on the screen the error message
	  printErrorMessage(errorMessage, "Deleting the Data of the Annotations of the Project");
	  
	  /* Delete all the data related to the Project Configuration
	   * class_attribute, class_information, source_directory, text_file_property
	   * tree_structure, class_method
	   */
	  try 
	  {
	     aopFileController.deleteProjectInformation(databaseConfigFile, projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
	  }
		  
	  // If there is any error, print on the screen the error message
	  printErrorMessage(errorMessage, "Deleting the Data of the Configuration of the Project");
	  
	  // Delete all the Files generated in the Output Folder
	  // that belongs to this Project Configuration
	  errorMessage = jc.cleanOutputDirectory(projectOutputDir);
	  	  
	  // If there is any error, print on the screen the error message
	  printErrorMessage(errorMessage, "Deleting the Files of the Project");
	   
   }
   
}