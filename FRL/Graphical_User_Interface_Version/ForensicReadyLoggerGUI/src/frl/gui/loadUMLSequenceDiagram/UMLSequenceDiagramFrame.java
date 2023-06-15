package frl.gui.loadUMLSequenceDiagram;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import frl.controller.loadUMLSequenceDiagram.LoadUMLSequenceDiagramController;
import frl.gui.annotateUMLSequenceDiagram.AnnotateUMLSequenceDiagramFrame;
import frl.gui.main.MainFrame;
import frl.gui.projectTable.ProjectTableListener;
import frl.gui.projectTable.ProjectTablePanel;
import frl.gui.toolBar.ToolBar;
import frl.gui.toolBar.ToolBarListener;
import frl.gui.user.LoginFrame;
import frl.model.configuration.FRLConfiguration;
import frl.model.user.UserLevel;
import frl.process.loadUMLSequenceDiagram.UMLSeqDiagram;

//Package #6
//Class #5
public class UMLSequenceDiagramFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	// Define the attributes for the class
    private ToolBar toolBar; 
    private UMLSequenceDiagramFormPanel umlSequenceDiagramFormPanel;
    private ProjectTablePanel projectTablePanel;
    
    private LoadUMLSequenceDiagramController  umlSeqDiagramController;
    
    public String menu="";
    public String submenu="";
    public JFileChooser fileChooser;
   
    
    // Constructor of the UserFrame class
    // Method #1
	public UMLSequenceDiagramFrame (String userP, String menuP, UserLevel userLevelP, 
			                        String databaseConfigFile, String featuresConfigFile) 
	{
        super("Load the Incident Model");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        umlSequenceDiagramFormPanel = new UMLSequenceDiagramFormPanel(databaseConfigFile, featuresConfigFile);
        projectTablePanel = new ProjectTablePanel();
        
        if (menuP.equals("Annotate UML Sequence Diagram")) 
        {
           toolBar = new ToolBar(userP, menuP, userLevelP);
        }
        
        // Define an object for the Controller Class
        //aopFileController = new AOPFileController();
        umlSeqDiagramController = new LoadUMLSequenceDiagramController();
        
        // Define an object for the UserTablePanel Class
        //projectTablePanel.setData(aopFileController.getConfigFiles());
        projectTablePanel.setData(umlSeqDiagramController.getConfigFiles());
        
        // Add a new Listener for the ProjectTablePanel
        projectTablePanel.setProjectTableListener((ProjectTableListener) new ProjectTableListener() 
        {
        	@Override
			public void projectDeletedOccurred(int row) 
			{
               //umlSeqDiagramController.deleteProject(row);
			}
        	
        	@Override
			public void projectEventOccurred(int id, String popupMenu) 
			{
			   // Show the UserForm Panel
			   umlSequenceDiagramFormPanel.setVisible(true);
		     	   
	     	   // Clean the current values in the Configuration File Form
			   umlSequenceDiagramFormPanel.cleanFields(databaseConfigFile);
	     	   
	     	   // Get all the values from the selected row into the Project Configuration File Form
			   refreshProject();
			   
			   if (popupMenu.equals("View"))
			   {
			      menu = "UML Sequence Diagram";
	              submenu = "View";
	               
	              // Makes invisible the UserToolBar Add New Button
				  ToolBar.addNewButton.setVisible(false);
	                
				  // Makes visible the ConfigurationFormPanel
	              umlSequenceDiagramFormPanel.setVisible(true);
	               
	              // Disable the fields, open buttons and combos in the ConfigurationFormPanel
	              umlSequenceDiagramFormPanel.disableFields();
	               
	              // Enable the save button in the Configuration Form Panel
	              umlSequenceDiagramFormPanel.enableViewButton();
	                
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
		             umlSequenceDiagramFormPanel.setVisible(true);
		               
		             // Disable the fields, open buttons and combos in the ConfigurationFormPanel
		             umlSequenceDiagramFormPanel.enableFields();
		               
		             // Enable the save button in the Configuration Form Panel
		             umlSequenceDiagramFormPanel.enableSaveButton();
		                
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
			            umlSequenceDiagramFormPanel.setVisible(true);
			               
			            // Disable the fields, open buttons and combos in the ConfigurationFormPanel
			            umlSequenceDiagramFormPanel.disableFields();
			               
			            // Enable the save button in the Configuration Form Panel
			            umlSequenceDiagramFormPanel.enableDeleteButton();
			                
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
			   umlSequenceDiagramFormPanel.cleanFields(databaseConfigFile);
		     	   
		       // Get all the values from the selected row into the Project Configuration File Form
			   refreshProject();
				   
			   // campos modificados
			   menu = "UML Sequence Diagram";
               submenu = "Add";
	               
	           // Makes invisible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(false);
	                
			   // Makes visible the ConfigurationFormPanel
	           umlSequenceDiagramFormPanel.setVisible(true);
	               
	           // Disable the fields, open buttons and combos in the ConfigurationFormPanel
	           umlSequenceDiagramFormPanel.enableFields();
	               
	           // Enable the save button in the Configuration Form Panel
	           umlSequenceDiagramFormPanel.enableSaveButton();
	                
	           // Makes invisible the AOPFileTablePanel
			   projectTablePanel.setVisible(false);
			   
			}
        });	
        
        // Code for the listeners
        umlSequenceDiagramFormPanel.setUserFormListener(new UMLSequenceDiagramFormListener() 
        {
			@Override
			public boolean progLanguageComboEventOccurred(UMLSequenceDiagramFormEvent e) 
			{
		       String progLang = "";
		       
		       progLang = e.getProgLanguage();
			   umlSequenceDiagramFormPanel.loadingProgLangDet(databaseConfigFile, progLang);
			   
			   return true;
			}
			
			@Override
			public boolean openUmlSeqDiagramImageEventOccurred(UMLSequenceDiagramFormEvent e) 
			{
			   String umlSequenceDiagramImage;
			   int returnVal;
					   
			   fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			   FileNameExtensionFilter filter = new FileNameExtensionFilter("UML Sequence Diagram Image File", "png");
			   fileChooser.setFileFilter(filter);

			   returnVal = fileChooser.showOpenDialog(null);
			   if(returnVal == JFileChooser.APPROVE_OPTION)
			   {	
				  umlSequenceDiagramImage = fileChooser.getSelectedFile().getAbsolutePath();
			      umlSequenceDiagramFormPanel.umlSeqDiagramImageField.setText(umlSequenceDiagramImage);
			   }			
						
			   return true;
			}

			@Override
			public boolean openUmlSeqDiagramTextEventOccurred(UMLSequenceDiagramFormEvent e) 
			{
		       String umlSequenceDiagramText;
			   int returnVal;
						   
			   fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			   FileNameExtensionFilter filter = new FileNameExtensionFilter("UML Sequence Diagram Text File", "txt");
			   fileChooser.setFileFilter(filter);

			   returnVal = fileChooser.showOpenDialog(null);
			   if(returnVal == JFileChooser.APPROVE_OPTION)
			   {	
			      umlSequenceDiagramText = fileChooser.getSelectedFile().getAbsolutePath();
				  umlSequenceDiagramFormPanel.umlSeqDiagramTextField.setText(umlSequenceDiagramText);
			   }			
							
			   return true;
			}
			
	 	    @Override
		    public boolean saveEventOccurred(UMLSequenceDiagramFormEvent ev) 
	 	    {
	 	       String emptyFields="", wrongExtFile1="", wrongExtFile2="", outputDir1="", projectName="",
	 	    		  umlImageFileName="", umlTextFileName="", errorMessage1="", errorMessage2="";
	 	       int files=0, c=0, message=0;
	 	       boolean validUmlImageFileName1=false, validUmlTextFileName1=false, validUmlTextFileName2=false, flag=false;
	 	       File outputDir2;
	 	       FRLConfiguration frlCon  = new FRLConfiguration();
	 	       ArrayList<Boolean> validContent = new ArrayList<Boolean>();

	 	       // Get the fields from the GUI
	 	       projectName = umlSequenceDiagramFormPanel.projectNameField.getText();
	 	       outputDir1  = umlSequenceDiagramFormPanel.projOutputDirField.getText() + 
	 	    		         System.getProperty("file.separator") +
	 	    		         projectName;
	 	       
	 	       outputDir2 = new File(outputDir1);
	 	       
	 	       umlImageFileName = ev.getUmlSeqDiagramImage();
	 	       umlTextFileName  = ev.getUmlSeqDiagramText();
	 	       
	 	       // Loading the Configuration Parameters
	 	       errorMessage1 = frlCon.loadParameters(featuresConfigFile, databaseConfigFile);
	 	       printErrorMessage(errorMessage1, "Loading the Configuration Parameters");
	 	       
	 	       //System.out.println("ONE");
	 	       
		 	   // Validations
		 	   emptyFields = umlSeqDiagramController.validateEmptyFields(ev);
		 	   
		 	   //System.out.println("TWO");
	 	       
	 	       // Validations
	     	   if (emptyFields != null && !emptyFields.isEmpty())
        	   {
	     		  if(emptyFields.contains(","))  
	     	         errorMessage1 = "Error XXXX: The Fields: " + emptyFields + " are EMPTY. "+ System.lineSeparator() + "They should have a VALUE." ;  
	     		  else
	     			 errorMessage1 = "Error XXXX: The Field: " + emptyFields + " is EMPTY."+System.lineSeparator() + "It should have a VALUE." ; 
	     		  
        		  // Error #1 	     		  
	     		  printErrorMessage(errorMessage1, "Validating the UML Sequence Diagram Files");
                  return false;
               }
	     	   else 
	     	   { 
		 	      wrongExtFile1 = umlSeqDiagramController.validateUMLImageFile(umlImageFileName);
		 	      wrongExtFile2 = umlSeqDiagramController.validateUMLTextFile(umlTextFileName);
		 	       
		 	      validUmlImageFileName1 = umlSeqDiagramController.validateFileName(umlImageFileName, frlCon.umlSeqDiagPngFileName1);
		 	      validUmlTextFileName1  = umlSeqDiagramController.validateFileName(umlTextFileName, frlCon.umlSeqDiagTextFileName1);
		 	       
		 	      //System.out.println("THREE");
		 	     
		 	      try 
		 	      {
				     files = umlSeqDiagramController.validateTextFiles(outputDir2);
				  } 
		 	      catch (Exception e2) 
		 	      {
		 	 	     errorMessage1 = e2.getMessage();
		 	    	 printErrorMessage(errorMessage1, "Validating the Project Text Files");
				  }
		 	      
		 	      //System.out.println("FOURTH");
		 	       
		 	      try 
		 	      {
		 	         validContent = umlSeqDiagramController.validateContentTextFile(umlTextFileName, frlCon);
				  } 
		 	      catch (Exception e1) 
		 	      {
		 	         errorMessage1 = e1.getMessage(); 
		 	 	     errorMessage2 = "Error XXXX: An error has occurred while validating the Content of the UML Text File: " + System.lineSeparator() + umlTextFileName + System.lineSeparator();  
		 	    	 errorMessage2 = errorMessage2 + "Error: "+ errorMessage1;
		 	    	 printErrorMessage(errorMessage2, "Validating the UML Sequence Text File");
				  }
		 	       
		 	      //System.out.println("FIFHT");
		 	     
		 	      if (validContent.contains(false)) 
		 	         validUmlTextFileName2 = false;
		 	      else
		 	          validUmlTextFileName2 = true;
		 	      
		 	      //System.out.println("SIXTH");
		 	       
	     	      if(wrongExtFile1 != null && !wrongExtFile1.isEmpty())
	     	      {
                     errorMessage1 = "Error XXXX: The UML Sequence Diagram Image FileName: " + System.lineSeparator() + wrongExtFile1 + System.lineSeparator()+ " is INVALID and should have a PNG extension." ; 
	     		  
        		     // Error #2 
                     printErrorMessage(errorMessage1, "Validating the UML Sequence Diagram Image File");
                     return false;
	     	       }
		     	   else 
			          if(wrongExtFile2 != null && !wrongExtFile2.isEmpty())
			     	  {
		                errorMessage1 = "Error XXXX: The UML Sequence Diagram Text FileName: " + System.lineSeparator() + wrongExtFile2 + System.lineSeparator()+ " is INVALID and should have a TXT extension." ; 
			     		  
		        		// Error #3 
		                printErrorMessage(errorMessage1,"Validating the UML Sequence Diagram Text File");
		                return false;
			     	  }
	     	          else
	       	  	         if(validUmlImageFileName1 == false)
		     	        {
		 	 		       // Error #4 
		 	     	       errorMessage1 = "Error XXXX: The UML Sequence Diagram Image FileName: " + System.lineSeparator() + umlImageFileName + System.lineSeparator() + "has NOT an Expected Name: "+ frlCon.umlSeqDiagPngFileName1 + System.lineSeparator(); 
			     	       printErrorMessage(errorMessage1,"Validating the UML Sequence Diagram Image File");
		 	 		       return false;
		     	        }
	       	  	        else
	     	               if(validUmlTextFileName1 == false)
	     	               {
	 	 		              // Error #5 
	 	     	              errorMessage1 = "Error XXXX: The UML Sequence Diagram Text File: " + System.lineSeparator() + umlTextFileName + System.lineSeparator() + "has NOT an Expected Name: "+ frlCon.umlSeqDiagTextFileName1 + System.lineSeparator(); 
		     	              printErrorMessage(errorMessage1,"Validating the UML Sequence Diagram Text File");
	 	 		              return false;
	     	               }
	     	               else
  	                          if(validUmlTextFileName2 == false)
	                          {
  	                             errorMessage1="";
  	                             message=0;
  	                             
  	                             for (c = 0; c < validContent.size(); c++) 
  	                        	 {
  	                                 flag = validContent.get(c);
  	                               
  	                                 if(flag == false)
  	                                 {	 
  	                                    message++;	
  	                                    
  	  	                                switch(c) 
  	  	                                {
  	  	                                   case 0:
  	  	                                   errorMessage1 = errorMessage1 + "the Start UML Diagram Statement";
  	  	                                   break;
  	  	                                
  	  	                                   case 1:
  	  	                                   errorMessage1 = errorMessage1 + "the End UML Diagram Statement"; 
  	  	                                   break;
  	  	                                
  	  	                                   case 2:
  	  	                                   errorMessage1 = errorMessage1 + "the Connection Statement";
  	  	                                   break;
  	  	                                
  	  	                                   case 3:
  	  	                                   errorMessage1 = errorMessage1 + "the Space Symbol"; 
  	  	                                   break;
  	  	                                
  	  	                                   case 4:
  	  	                                   errorMessage1 = errorMessage1 + "the Send a Message Symbol";
  	  	                                   break;
  	  	                                }
  	  	                                
  		  	                            if(message >= 1)
  		  	                               errorMessage1 = errorMessage1 + ", ";
  	  	                               	  	                               
  	                                 }
  	                                 
  	                        	 }
  	                             
  	                             // Error #6 
  	                             errorMessage2 = "Error XXXX: The UML Sequence Diagram Text File: " + System.lineSeparator() + umlTextFileName + System.lineSeparator() + "is missing: " + errorMessage1 + System.lineSeparator() + "in the PlantUML Syntaxis." + System.lineSeparator(); 
  	                          
    	                         printErrorMessage(errorMessage2,"Validating the UML Sequence Diagram Text File");
 		                         return false;
	                          }
	     	                  else
		     	                  if(files <=0 && (submenu.equals("Add") || submenu.equals("Modify")) )
		     	        	      {
		 		        		     // Error #7 
		     	        		     errorMessage1 = "Error XXXX: The Project Name: " + projectName + " has NOT any generated Text Files." + System.lineSeparator() + "It is mandatory to visit the Generate AOP Files Module First." ;  
		     	        		     printErrorMessage(errorMessage1,"Validating the Project Text Files");
		 		                     return false;
		     	        	      }
	     	        	          else
	     	                      {
	     		                     save(databaseConfigFile, featuresConfigFile, ev,
	     		        	              userP, menuP, userLevelP, frlCon);
	     	        		 
	     		                     // Add the new record into the Project Table Panel
	     		                     projectTablePanel.setVisible(true); 
	     		                     projectTablePanel.refresh();
			                         loadingProjects(databaseConfigFile);
			      
			                         umlSequenceDiagramFormPanel.setVisible(false); 
			                         ToolBar.addNewButton.setVisible(true);
			      
			                         return true;   
	     	                      }
	     	   }      
			}

			@Override
			public boolean deleteEventOccurred(UMLSequenceDiagramFormEvent ev) 
			{
	           delete(databaseConfigFile, featuresConfigFile, ev);
	           
	     	   projectTablePanel.setVisible(true); 
	     	   projectTablePanel.refresh();
			   loadingProjects(databaseConfigFile);
			      
			   umlSequenceDiagramFormPanel.setVisible(false); 
			   ToolBar.addNewButton.setVisible(true);

		       return true;
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
        add(umlSequenceDiagramFormPanel, BorderLayout.WEST);
        add(projectTablePanel, BorderLayout.CENTER);
        
        // Set the Size of the Form
        pack();
		this.setSize(1000, 500);
		
		// Set the location in the center of the screen
		setLocationRelativeTo(null);
		
		// Set up that the windows cannot be resizable by the User
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
        //Display the window.
        this.setVisible(true);
   
	   	// Makes Not Visible the Panels
		umlSequenceDiagramFormPanel.setVisible(false);
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
			   umlSequenceDiagramFormPanel.setVisible(false);
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
							UMLSequenceDiagramFrame.this, "Do you really want to Sign Out the Forensic Ready Logger System?", 
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
						UMLSequenceDiagramFrame.this, "Do you really want to exit the Forensic Ready Logger System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   WindowListener[] listeners = getWindowListeners();
					
				   for(WindowListener listener: listeners) 
				   {
					  // Calls the Windows Closing Event
				      listener.windowClosing(new WindowEvent(UMLSequenceDiagramFrame.this, 0));
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
	   umlSequenceDiagramFormPanel.projectIdField.setText(stringSel);

	   // Project Name
	   stringSel = projectTablePanel.getProjectStringColSel(1);
	   umlSequenceDiagramFormPanel.projectNameField.setText(stringSel);
     
	   // JAR File Name
	   stringSel = projectTablePanel.getProjectStringColSel(2);
	   umlSequenceDiagramFormPanel.jarFileNameField.setText(stringSel);

	   // Project Input Directory
	   stringSel = projectTablePanel.getProjectStringColSel(3);
	   umlSequenceDiagramFormPanel.projInputDirField.setText(stringSel);	   

	   // Project Output Directory
	   stringSel = projectTablePanel.getProjectStringColSel(4);
	   umlSequenceDiagramFormPanel.projOutputDirField.setText(stringSel);	 

	   // Programming Language
	   stringSel = projectTablePanel.getProjectStringColSel(5);
	   umlSequenceDiagramFormPanel.progLanguageCombo.setSelectedItem(stringSel);	
	   
	   // DBMS
	   stringSel = projectTablePanel.getProjectStringColSel(6);
	   umlSequenceDiagramFormPanel.dbmsCombo.setSelectedItem(stringSel);	
	   
	   // Input Method
	   stringSel = projectTablePanel.getProjectStringColSel(7);
	   umlSequenceDiagramFormPanel.inputMethodCombo.setSelectedItem(stringSel);	
	   
	   // Start Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(8);
	   umlSequenceDiagramFormPanel.startProjMethodField.setText(stringSel);	
	   
	   // End Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(9);
	   umlSequenceDiagramFormPanel.endProjMethodField.setText(stringSel);	
	   
	   // Connect Project Method
	   stringSel = projectTablePanel.getProjectStringColSel(10);
	   umlSequenceDiagramFormPanel.connectProjMethodField.setText(stringSel);	
	   
	   // Operating System Method
	   stringSel = projectTablePanel.getProjectStringColSel(11);
	   umlSequenceDiagramFormPanel.operatingSystemField.setText(stringSel);	
	   
	   umlSequenceDiagramFormPanel.projectNameField.requestFocusInWindow();
	   
	}
	
	
   // Method #4
   public void loadingProjects(String databaseConfigFile)
   {
	   
	  try 
	  {
	     umlSeqDiagramController.loadProjects(databaseConfigFile);
		 projectTablePanel.refresh();
	  } 
	  catch (Exception e) 
	  {
		 // Error #1 
		 String errorMessage = e.getMessage();
	     JOptionPane.showMessageDialog(UMLSequenceDiagramFrame.this, "Error 6541: Occurred while loading the Projects. Error Message: "+errorMessage, "Loading Projects", JOptionPane.ERROR_MESSAGE);	
	  }
		
   }
   
   void printErrorMessage(String errorMessage, String title)
   {
      // Validate if the errorMessage is not empty                                                              
	  if(errorMessage != null && !errorMessage.isEmpty())                                                                                                                      
	  { 
	     JOptionPane.showMessageDialog(UMLSequenceDiagramFrame.this, errorMessage , title, JOptionPane.ERROR_MESSAGE);
      }
   }
   
   void save(String databaseConfigFile, String featuresConfigFile, 
		     UMLSequenceDiagramFormEvent ev,
		     String userP, String menuP, UserLevel userLevelP,
		     FRLConfiguration frlCon)
   {
      String umlSeqDiagTextFile = "", errorMessage="";
      int projectId;
      UMLSeqDiagram umlSeqDiag = new UMLSeqDiagram();
      
      // Get the values from the GUI
      umlSeqDiagTextFile = ev.getUmlSeqDiagramText();
      projectId          = ev.getProjectId();

      System.out.println("Generating the Annotating the UML Sequence Diagram Screen ...");
            
      // Process the UML Sequence Diagram Image and Text Files
      // to generate all the necessary elements to annotate the UML Sequence Diagram File
      errorMessage = umlSeqDiag.loadUMLSeqDiagramElements(databaseConfigFile, umlSeqDiagTextFile, frlCon, projectId);
      printErrorMessage(errorMessage, "Loading the UML Sequence Diagram Text File");
      
      //System.out.println("Error Message: " + errorMessage);
      
      
      if(errorMessage != null && errorMessage.trim().isEmpty() == true) 
      {
         // Deletes the resources of the Current Screen
         setVisible(false);
	     dispose();
	     System.gc();
	  
	  
	     new AnnotateUMLSequenceDiagramFrame(userP, menuP, userLevelP, databaseConfigFile, 
			                                 featuresConfigFile, projectId, frlCon); 
      }
      
      
      			                              
   }
   
   void delete(String databaseConfigFile, String featuresConfigFile, UMLSequenceDiagramFormEvent ev)
   {

	   
   }
   
}