package frl.gui.annotateUMLSequenceDiagram;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import frl.controller.annotateUMLSequenceDiagram.AnnotateUMLSequenceDiagramController;
import frl.gui.loadUMLSequenceDiagram.UMLSequenceDiagramFrame;
import frl.gui.main.MainFrame;
import frl.gui.toolBar.ToolBar;
import frl.gui.user.LoginFrame;
import frl.model.annotateUMLSequenceDiagram.FileData;
import frl.model.annotateUMLSequenceDiagram.MethodStepData;
import frl.model.annotateUMLSequenceDiagram.ReturnTypeData;
import frl.model.annotateUMLSequenceDiagram.UserData;
import frl.model.configuration.FRLConfiguration;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ReturnTypeValueSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ValueSequenceDiagram;
import frl.model.user.UserLevel;
import frl.process.annotateUMLSequenceDiagram.AnnotateUMLSequenceDiagram;

//Package #6
//Class #5
public class AnnotateUMLSequenceDiagramFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	// Define the attributes for the class
    private ToolBar toolBar; 
    private AnnotateUMLSequenceDiagramFormPanel annotateUMLSequenceDiagramFormPanel;
    private AnnotateUMLSequenceDiagramController annotateUMLSequenceDiagramController;
    
    public static String menu="";
    public static String submenu="";
    
    // Constructor of the UserFrame class
    // Method #1
	public AnnotateUMLSequenceDiagramFrame (String userP, String menuP, UserLevel userLevelP, 
			                                String databaseConfigFile, String featuresConfigFile,
			                                int projectId, FRLConfiguration frlCon) 
	{
        super("Annotate the Incident Model");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        
        
        annotateUMLSequenceDiagramFormPanel = new AnnotateUMLSequenceDiagramFormPanel(
        		                         databaseConfigFile, featuresConfigFile, projectId, 
        		                         this.getContentPane(), frlCon);
        
        if (menuP.equals("Annotate UML Sequence Diagram")) 
        {
           toolBar = new ToolBar(userP, menuP, userLevelP);
        }
        
        // Define an object for the Controller Class
        annotateUMLSequenceDiagramController  = new AnnotateUMLSequenceDiagramController();
        
        // MenuBar
        setJMenuBar(createUserMenuBar(userP, userLevelP, databaseConfigFile, featuresConfigFile));
        
        // Code for the listeners
        annotateUMLSequenceDiagramFormPanel.setAnnotateUMLSequenceDiagramFormListener(new AnnotateUMLSequenceDiagramFormListener() 
        {
			@Override
			public boolean userNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{
		       String userName="", errorMessage="";
		       int userId=0, methodId=0, parameterId=0, parameterDataTypeId=0, attributeId=0, 
		    	   progrLangId=1, returnTypeId=0;
		       ParameterSequenceDiagram parameterRecord=null;
		       
		       // Remove all the elements from the combos
		 	   annotateUMLSequenceDiagramFormPanel.methodNameCombo.removeAllItems();
		 	   annotateUMLSequenceDiagramFormPanel.parameterNameCombo.removeAllItems();
		 	   annotateUMLSequenceDiagramFormPanel.attributeNameCombo.removeAllItems();
		 	   annotateUMLSequenceDiagramFormPanel.valueNameCombo.removeAllItems();
		 	   annotateUMLSequenceDiagramFormPanel.returnTypeValueCombo.removeAllItems();
		 	   
		       // Get the Values from the GUI
		       userName = ev.getUserName();
		       
		       // Get the User Details: User Id and UML Text and Image Files
		       userId = getUserDetails(projectId, userName, databaseConfigFile, frlCon);
		       
		 	   // Get the Methods associated to this User Id
		       methodId = getMethods(databaseConfigFile, projectId, userId, userName, frlCon); 
		       
		       /*
		       System.out.println("*** Inside User Name Combo ***");
		       System.out.println("Method Id: " + methodId);
		       System.out.println("Before Loading the Parameters");
		       */
		       
		       // Get the Parameters associated to this Project Id and Method Id
		       try
		       {
		          parameterRecord = getParameters(databaseConfigFile, projectId, methodId);
		       }
		 	   catch (Exception e1) 
		 	   {
		 	      errorMessage = e1.getMessage();
		 		  printErrorMessage(errorMessage, "Loading the Parameters");		 		 
		 	   }
		       
		       //System.out.println("After Loading the Parameters");
		       
			   // Get the Data from the parameter Record
			   if(parameterRecord != null)
			   {	  
			      parameterId         = parameterRecord.getParameterIdentifier();
				  parameterDataTypeId = parameterRecord.getDataTypeIdentifier();
			   }
			   
			   /*
			   System.out.println("Parameter Id          : " + parameterId);
			   System.out.println("Parameter Data Type Id: " + parameterDataTypeId);
			   */
			   
		       // Get the Attributes associated to this Project Id, MethodId, ParameterId, DataType Id
			   attributeId = getAttributes(databaseConfigFile, projectId, methodId, parameterId, parameterDataTypeId);
		       
			   if(attributeId > 0)
			   {	   
		          // Get the Values associated to this Project Id, MethodId, ParameterId, 
		          // DataType Id, Attribute Id
		          getValues(databaseConfigFile, projectId, methodId, parameterId, 
		                    parameterDataTypeId, progrLangId, attributeId);
			   }
			   
			   // Get the Return Type Id for this Method Id
			   returnTypeId = getReturnTypeId(databaseConfigFile, projectId, methodId);
			   
			   // Get the Return Type Value Details for this Method Id
			   loadReturnTypeValues(databaseConfigFile, projectId, methodId, returnTypeId);
			   
		              
			   return true;
			}

			@Override
			public boolean methodNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{
			   String methodName="", userName="", errorMessage="";
			   int methodId=0, userId=0, parameterId=0, parameterDataTypeId=0, attributeId=0,
				   progrLangId=1, returnTypeId=0;
			   ParameterSequenceDiagram parameterRecord=null;
			   
		       // Remove all the elements from the combos
		 	   annotateUMLSequenceDiagramFormPanel.parameterNameCombo.removeAllItems();
		 	   annotateUMLSequenceDiagramFormPanel.attributeNameCombo.removeAllItems();
		 	   annotateUMLSequenceDiagramFormPanel.valueNameCombo.removeAllItems();
			      
			   // Get the values from the GUI
			   userId     = ev.getUserId();
		       userName   = ev.getUserName();
			   methodName = ev.getMethodName();
			   
			   if(methodName != null )
			   {	   
			      // Get the Method Id
			      methodId = getMethodDetails(databaseConfigFile, projectId, userId, 
			    		                      userName, methodName, frlCon);
			    
			   }
			   
		       if(methodId > 0)
		       {	   
		    	   
		    	  /* 
		          System.out.println("*** Inside Method Name Combo ***");
		    	  System.out.println("Method Id: " + methodId);
			      System.out.println("Before Loading the Parameters");
			      */

			      // Get the Parameters associated to this Project Id and Method Id
			      try
			      {
			         parameterRecord = getParameters(databaseConfigFile, projectId, methodId);
			      }
			 	  catch (Exception e1) 
			 	  {
			 	     errorMessage = e1.getMessage();
			 	     printErrorMessage(errorMessage, "Loading the Parameters");	
			 		  
			 		 return false;
			 	  }
			      
			      //System.out.println("After Loading the Parameters");
		          
				  // Get the Data from the parameter Record
				  if(parameterRecord != null)
				  {	  
				     parameterId         = parameterRecord.getParameterIdentifier();
					 parameterDataTypeId = parameterRecord.getDataTypeIdentifier();
				  }
				   /*
				   System.out.println("Parameter Id          : " + parameterId);
				   System.out.println("Parameter Data Type Id: " + parameterDataTypeId);
				   */
				  
			      if(parameterId > 0)
			      {	
			         // Get the Attributes associated to this Project Id, MethodId, ParameterId, DataType Id
			    	 attributeId = getAttributes(databaseConfigFile, projectId, methodId, 
			    			                     parameterId, parameterDataTypeId);
			    	  
					 if(attributeId > 0)
					 {	   
				        // Get the Values associated to this Project Id, MethodId, ParameterId, 
				        // DataType Id, Attribute Id
				        getValues(databaseConfigFile, projectId, methodId, parameterId, 
				                  parameterDataTypeId, progrLangId, attributeId);
				     }
			      }
			      
				  // Get the Return Type Id for this Method Id
				  returnTypeId = getReturnTypeId(databaseConfigFile, projectId, methodId);
				  
				  if(returnTypeId > 0)
				  {
				     // Get the Return Type Value Details for this Method Id
					 loadReturnTypeValues(databaseConfigFile, projectId, methodId, returnTypeId);
				  }  
			      
		       }
		       
			   return true;
			}

			@Override
			public boolean parameterNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{
			   String parameterFullName="", parameterName="", parameterDataTypeName="";
			   int methodId=0, parameterId=0, parameterDataTypeId=0, progrLangId=1, attributeId=0;
			   String[] parts;
			   ParameterSequenceDiagram parameterRecord;
			   
		 	   annotateUMLSequenceDiagramFormPanel.attributeNameCombo.removeAllItems();
		 	   annotateUMLSequenceDiagramFormPanel.valueNameCombo.removeAllItems();
			   
			   // Get the values from the GUI
			   methodId          = ev.getMethodId();
			   parameterFullName = ev.getParameterName();
			   
			   // Obtain the two separate components
			   if((parameterFullName != null && !parameterFullName.trim().isEmpty()) && 
				  (parameterFullName.contains(" ")))
			   {	   
			      parts = parameterFullName.split(" ");
			      parameterDataTypeName = parts[0];
			      parameterName         = parts[1]; 
			   }
			   
			   /*
			   System.out.println("Inside the Parameter Name Combo ...");
			   System.out.println("Parameter Name           : " + parameterName);
			   System.out.println("Parameter Data Type Name : " + parameterDataTypeName);
			   */
			   
			   if(methodId > 0)
			   {	   
			      // Get the two Parameters Identifiers
			      parameterRecord = getParameterIds(databaseConfigFile, projectId, methodId, 
					                                parameterName, parameterDataTypeName);
			      
				   // Get the Data from the parameter Record
				   if(parameterRecord != null)
				   {	  
				      parameterId         = parameterRecord.getParameterIdentifier();
					  parameterDataTypeId = parameterRecord.getDataTypeIdentifier();
				   }
				   
				   if(parameterId > 0)
				   {	   
			          // Get the Attributes associated to this Project Id, MethodId, ParameterId, DataType Id
					  attributeId = getAttributes(databaseConfigFile, projectId, methodId, parameterId, parameterDataTypeId);
			          
					  if(attributeId > 0)
					  {	   
					     // Get the Values associated to this Project Id, MethodId, ParameterId, 
					     // DataType Id, Attribute Id
					     getValues(databaseConfigFile, projectId, methodId, parameterId, 
					               parameterDataTypeId, progrLangId, attributeId);
				      }
				   }   
			   }
			   
		       return true;
			}

			@Override
			public boolean attributeNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{
		       String attributeName="", attributeValue="", attributeFullName="";
			   int methodId=0, parameterId=0, parameterDataTypeId=0, progrLangId=1, attributeId=0;
			   
		 	   annotateUMLSequenceDiagramFormPanel.valueNameCombo.removeAllItems();
				  
			   // Get the values from the GUI
			   methodId            = ev.getMethodId();
			   parameterId         = ev.getParameterId();
			   parameterDataTypeId = ev.getParameterDataTypeId();
			   attributeFullName   = ev.getAttributeName();
				   
			   attributeName  = attributeFullName;
			   attributeValue = "";
			     
			   if(methodId > 0 && parameterId > 0 && attributeName != null)
			   {	   
			      // Get the Attribute Id associated to this Project Id, MethodId, 
			      // ParameterId, Parameter DataType Id, AttributeName and AttributeValue
				   attributeId = getAttributeId(databaseConfigFile, projectId, methodId, parameterId,
		                                        parameterDataTypeId, attributeName, attributeValue);
			   }
			   
			   if(methodId > 0 && parameterId > 0 && attributeId > 0)
			   {	   
			      // Get the Values associated to this Project Id, MethodId, ParameterId, 
				  // DataType Id, Attribute Id
				  getValues(databaseConfigFile, projectId, methodId, parameterId, 
				            parameterDataTypeId, progrLangId, attributeId);
			   }
							
			   return true;
			}

			@Override
			public boolean valueNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{
			   String attributeName="", attributeValue="", valueName="";
			   int methodId=0, parameterId=0, parameterDataTypeId=0, progrLangId=1, attributeId=0;
	  
			   // Get the values from the GUI
			   methodId             = ev.getMethodId();
			   parameterId          = ev.getParameterId();
			   parameterDataTypeId  = ev.getParameterDataTypeId();
			   attributeName        = ev.getAttributeName();
			   valueName            = ev.getValueName();
					   
			   if(methodId > 0 && parameterId > 0 && attributeName != null)
			   {	   
			      // Get the Attribute Id associated to this Project Id, MethodId, 
				  // ParameterId, Parameter DataType Id, AttributeName and AttributeValue
				  attributeId = getAttributeId(databaseConfigFile, projectId, methodId, parameterId,
			                                   parameterDataTypeId, attributeName, attributeValue);
			   }
				   
			   if(methodId > 0 && parameterId > 0 && attributeName != null && attributeId > 0)
			   {	   
			      // Get the Value Id associated to this Project Id, MethodId, ParameterId, 
				  // DataType Id and Attribute Id
				  getValueId(databaseConfigFile, projectId, methodId, parameterId, 
					         parameterDataTypeId, progrLangId, attributeId, valueName);
				  
			   }
			   
			   return true;
			}
			
			@Override
			public boolean returnTypeValueComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{
			   int methodId=0, returnTypeValueId=0;
			   String returnTypeValueName="";
		       
		       // Get the values from the GUI
			   methodId            = ev.getMethodId();
			   returnTypeValueName = ev.getReturnTypeValueName();
			   
			   if(methodId > 0 && returnTypeValueName !=null)
			   {
			      returnTypeValueId = getReturnTypeValueId(databaseConfigFile, projectId, 
                                                           methodId, returnTypeValueName);
			   }
					   
		       return true;
			}
			
			@Override
			public boolean annotationTypeEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{
			   String annotationType = "";

			   annotationType = ev.getAnnotationType();
			   
			   //System.out.println("Annotation Type: "+annotationType);
			   annotateUMLSequenceDiagramFormPanel.enableDisableFields(annotationType);

		       return true;
			}

			@Override
			public boolean addAnnotationEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{

			   addAnnotatonUMLSeqDiagram(databaseConfigFile, frlCon, ev, annotateUMLSequenceDiagramController);
		       
			   return true;
			}

			@Override
			public boolean deleteAnnotationEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{
			   return true;
			}

			@Override
			public boolean saveEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev) 
			{

			   // Call to the Save Procedure	
			   save(databaseConfigFile, frlCon, 
					annotateUMLSequenceDiagramController, 
					ev);
			   	
			   menu = "Annotate UML Sequence Diagram";
			   setVisible(false);
			   dispose();
			   System.gc();
				   
			   // Makes invisible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(true);
					
	           // Displays the UML Sequence Diagram Main Menu
			   new UMLSequenceDiagramFrame(userP, menu, userLevelP, databaseConfigFile, featuresConfigFile); 
				   
		       return true;
			}

			@Override
			public boolean cancelEventOccurred() 
			{

			   menu = "Annotate UML Sequence Diagram";
			   setVisible(false);
			   dispose();
			   System.gc();
			   
			   // Makes invisible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(true);
				
               // Displays the UML Sequence Diagram Main Menu
			   new UMLSequenceDiagramFrame(userP, menu, userLevelP, databaseConfigFile, featuresConfigFile); 
			   
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
        add(annotateUMLSequenceDiagramFormPanel, BorderLayout.WEST);
        
        // Set the Size of the Form
        pack();
		this.setSize(1000, 1000);
		
		// Set the location in the center of the screen
		setLocationRelativeTo(null);
		
		// Set up that the windows cannot be resizable by the User
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
        //Display the window.
        this.setVisible(true);
   
	   	// Makes Visible the Panel
        annotateUMLSequenceDiagramFormPanel.setVisible(true);
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
			   //umlSequenceDiagramFormPanel.setVisible(false);
			   //projectTablePanel.setVisible(false);
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
							AnnotateUMLSequenceDiagramFrame.this, "Do you really want to Sign Out the Forensic Ready Logger System?", 
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
						AnnotateUMLSequenceDiagramFrame.this, "Do you really want to exit the Forensic Ready Logger System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   WindowListener[] listeners = getWindowListeners();
					
				   for(WindowListener listener: listeners) 
				   {
					  // Calls the Windows Closing Event
				      listener.windowClosing(new WindowEvent(AnnotateUMLSequenceDiagramFrame.this, 0));
				   } 	
			    }
			}
		});
		
		return menuBar;
	}
	
	
   void printErrorMessage(String errorMessage, String title)
   {
      // Validate if the errorMessage is not empty                                                              
	  if(errorMessage != null && !errorMessage.isEmpty())                                                                                                                      
	  { 
	     JOptionPane.showMessageDialog(AnnotateUMLSequenceDiagramFrame.this, errorMessage , title, JOptionPane.ERROR_MESSAGE);
      }
   }
   	
   public int getUserDetails(int projectId, String userName, String databaseConfigFile, FRLConfiguration frlCon)
   {
      String errorMessage1="", errorMessage2="", userIdStr="", userTextFileName="", userImageFileName="",
    		 userTextFileNamePath="", userImageFileNamePath=""; 
      int userId=0;
      
      UserData userData = null;
      
      // System.out.println("Inside the AnnotateUMLSequenceDiagramFrame.getUserDetails Method ");
      
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage1 = e1.getMessage();
	     printErrorMessage(errorMessage1, "Connecting to the Database");
	  }
      
	  try 
	  {	  
	     userData = annotateUMLSequenceDiagramController.getUserDetails(projectId, userName);
	  }   
      catch(Exception e2)
      {
         errorMessage1 = e2.getMessage();
	     printErrorMessage(errorMessage1, "Getting the User Identifier");
      } 
	  
	  try 
	  {
	     annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
	     printErrorMessage(errorMessage1, "Disconnect from the Database");
	  }	
	  
	  if(userData != null)
	  {	  
	     userId            = userData.getUserIdentifier();
         userTextFileName  = userData.getUserTextFileName();
         userImageFileName = userData.getUserImageFileName();
         
	     // Convert Number to String		 
         userIdStr = String.valueOf(userId);
	  }
	  
	  // Construct the complete path of the UML Text and Image Files
	  userTextFileNamePath = frlCon.projectOutputDir + File.separator +
			                 frlCon.umlDirectoryName + File.separator + 
			                 frlCon.projectName      + File.separator + 
			                 userName                + File.separator + 
			                 userTextFileName;
	   
	  userImageFileNamePath = frlCon.projectOutputDir + File.separator +
	                          frlCon.umlDirectoryName + File.separator + 
	                          frlCon.projectName      + File.separator + 
	                          userName                + File.separator + 
	                          userImageFileName;

	  /*
	  System.out.println("User Name                 : " + userName);
	  System.out.println("User Text  File Name      : " + userImageFileName);
	  System.out.println("User Image File Name      : " + userImageFileName);
	  
	  System.out.println("User Text File Name Path  : " + userTextFileNamePath);
	  System.out.println("User Image File Name Path : " + userImageFileNamePath);
	  */

	  
      // Assign the values to the GUI
      annotateUMLSequenceDiagramFormPanel.userIdField.setText(userIdStr);
      annotateUMLSequenceDiagramFormPanel.userTextFileNameField.setText(userTextFileNamePath);
      annotateUMLSequenceDiagramFormPanel.userImageFileNameField.setText(userImageFileNamePath);
      
      // Construct the complete path of the UML Text and Image Files at the User Level
      try 
      {
         annotateUMLSequenceDiagramFormPanel.image1 = ImageIO.read(new File(userImageFileNamePath));
         annotateUMLSequenceDiagramFormPanel.img1Icon = new ImageIcon(annotateUMLSequenceDiagramFormPanel.image1);
         annotateUMLSequenceDiagramFormPanel.image1Label.setIcon(annotateUMLSequenceDiagramFormPanel.img1Icon);	
         
         //System.out.println("UPLOADING IN THE JPANEL THE UML SEQUENCE DIAGRAM FOR THE USER: " + userName);
	  } 
      catch (IOException e4) 
      {
 	     errorMessage1 = e4.getMessage();
 	     errorMessage2 = "Error 1000: Ocurred while displaying the UML Sequence Diagram Image at the User Level: " + userImageFileNamePath + System.lineSeparator();
 	     errorMessage2 = errorMessage2 + errorMessage1;
 	     
 	     printErrorMessage(errorMessage2, "Displaying the UML Sequence Diagram at the User Level");
	  }      

	  return userId;
   }   
   
   public int getMethods(String databaseConfigFile, int projectId, int userId, 
		                 String userName, FRLConfiguration frlCon)
   {
	  String errorMessage1="", errorMessage2="", methodName="", methodIdStr="", methodStepIdStr="",
			 methodTextFileName="", methodImageFileName="", 
			 methodTextFileNamePath="", methodImageFileNamePath="";
	  int i=0, methodId=0, initialMethodId=0, methodStepId=0;
	  ArrayList<MethodStepData > methodList=null;
	  
      // Delete all the current items in the Combo
	  annotateUMLSequenceDiagramFormPanel.methodNameCombo.removeAllItems();
	  
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage1 = e1.getMessage();
	     printErrorMessage(errorMessage1, "Connecting to the Database");
	  }
	     
	  try 
	  {
	     // Obtain an ArrayList of the methods
         methodList = annotateUMLSequenceDiagramController.loadMethodsSteps(projectId, userId);
	  } 
      catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
	     printErrorMessage(errorMessage1, "Loading the Methods from the Database");
	  }	 
	  
	  try
	  {
		 // Load all the methods from this Project
		 for (i = 0; i < methodList.size(); i++) 
		 {
		    methodId            = methodList.get(i).getMethodIdentifier();
		    methodName          = methodList.get(i).getMethodName();
		    methodTextFileName  = methodList.get(i).getMethodTextFileName();
			methodImageFileName = methodList.get(i).getMethodImageFileName();
			methodStepId        = methodList.get(i).getMethodStepIdentifier();
			
			// Convert Integer to String
		    methodIdStr     = String.valueOf(methodId);
		    methodStepIdStr = String.valueOf(methodStepId);
			    
		    // Assign the values to the GUI
		    annotateUMLSequenceDiagramFormPanel.methodNameCombo.addItem(methodName);
		    
		    if(i==0)
		    { 	
		       initialMethodId = methodId;
		       annotateUMLSequenceDiagramFormPanel.methodNameCombo.setSelectedItem(methodName);	
		       annotateUMLSequenceDiagramFormPanel.methodIdField.setText(methodIdStr);
		       annotateUMLSequenceDiagramFormPanel.methodStepIdField.setText(methodStepIdStr);
		       
			   // Construct the complete path of the UML Text and Image Files
			   methodTextFileNamePath = frlCon.projectOutputDir + File.separator +
					                    frlCon.umlDirectoryName + File.separator + 
					                    frlCon.projectName      + File.separator + 
					                    userName                + File.separator + 
					                    methodTextFileName;
			   
			   methodImageFileNamePath = frlCon.projectOutputDir + File.separator +
			                             frlCon.umlDirectoryName + File.separator + 
			                             frlCon.projectName      + File.separator + 
			                             userName                + File.separator + 
			                             methodImageFileName;
		       
		       annotateUMLSequenceDiagramFormPanel.methodTextFileNameField.setText(methodTextFileNamePath);
		       annotateUMLSequenceDiagramFormPanel.methodImageFileNameField.setText(methodImageFileNamePath);
		       
		       try 
		       {
		          annotateUMLSequenceDiagramFormPanel.image2 = ImageIO.read(new File(methodImageFileNamePath));
		 	      annotateUMLSequenceDiagramFormPanel.img2Icon = new ImageIcon(annotateUMLSequenceDiagramFormPanel.image2);
		 	      annotateUMLSequenceDiagramFormPanel.image2Label.setIcon(annotateUMLSequenceDiagramFormPanel.img2Icon); 
		 	     
		 	      //System.out.println("Displaying the UML Sequence Diagram at the Method Level Place #1");
		 	   } 
		       catch (IOException e3) 
		       {
		  	      errorMessage1 = e3.getMessage();
		  	      errorMessage2 = "Error 1001: Ocurred while displaying the UML Sequence Diagram Image at the Method Level: " + methodImageFileNamePath + System.lineSeparator();
		  	      errorMessage2 = errorMessage2 + errorMessage1;
		  	     
		  	      printErrorMessage(errorMessage2, "Displaying the UML Sequence Diagram at the Method Level");
		 	   }
		  	   
		    }
	      }
		     
	   } 
       catch (Exception e4) 
	   {
	      errorMessage1 = e4.getMessage();
	      printErrorMessage(errorMessage1, "Loading the Methods in the Combo");
	   }
	  
	   try 
	   {
	      annotateUMLSequenceDiagramController.disconnect();
	   } 
	   catch (Exception e5) 
	   {
	      errorMessage1 = e5.getMessage();
	      printErrorMessage(errorMessage1, "Disconnect from the Database");
	   }
	   
	   return initialMethodId;
   } 
   
   public int getMethodDetails(String databaseConfigFile, int projectId, int userId, 
		                       String userName, String methodName, FRLConfiguration frlCon)
   {
      String errorMessage1="", errorMessage2="", methodIdStr="", methodTextFileName="", methodImageFileName="", 
    		 methodTextFileNamePath="", methodImageFileNamePath="", methodStepIdStr=""; 
      int methodId=0, methodStepId=0;
      MethodStepData methodStep=null;
      
      //System.out.println("Inside Get Method Details Method ... ");
      
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage1 = e1.getMessage();
	     printErrorMessage(errorMessage1, "Connecting to the Database");
	  }
      
	  try 
	  {	  
	     methodStep = annotateUMLSequenceDiagramController.getMethodDetails(projectId, userId, methodName);
	  }   
      catch(Exception e2)
      {
         errorMessage1 = e2.getMessage();
	     printErrorMessage(errorMessage1, "Getting the Method Identifier");
      } 
	  
	  try 
	  {
	      annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
	     printErrorMessage(errorMessage1, "Disconnect from the Database");
	  }	
	  
	  // Validate that methodStep is not null
	  if(methodStep != null) 
	  {
	     methodId            = methodStep.getMethodIdentifier();	
	     methodStepId        = methodStep.getMethodStepIdentifier();
	     methodTextFileName  = methodStep.getMethodTextFileName();
	     methodImageFileName = methodStep.getMethodImageFileName();
	     
	     // Convert from Integer to String
	     methodIdStr     = String.valueOf(methodId);
	     methodStepIdStr = String.valueOf(methodStepId);
	  }

      // Assign the values to the GUI
      annotateUMLSequenceDiagramFormPanel.methodIdField.setText(methodIdStr);
      annotateUMLSequenceDiagramFormPanel.methodStepIdField.setText(methodStepIdStr);
      
	  // Construct the complete path of the UML Text and Image Files
	  methodTextFileNamePath = frlCon.projectOutputDir + File.separator +
			                   frlCon.umlDirectoryName + File.separator + 
			                   frlCon.projectName      + File.separator + 
			                   userName                + File.separator + 
			                   methodTextFileName;
	   
	  methodImageFileNamePath = frlCon.projectOutputDir + File.separator +
	                            frlCon.umlDirectoryName + File.separator + 
	                            frlCon.projectName      + File.separator + 
	                            userName                + File.separator + 
	                            methodImageFileName;
      
      annotateUMLSequenceDiagramFormPanel.methodTextFileNameField.setText(methodTextFileNamePath);
      annotateUMLSequenceDiagramFormPanel.methodImageFileNameField.setText(methodImageFileNamePath);
      
      /*
      System.out.println("User Name                 : " + userName);
      System.out.println("Method Text FileName      : " + methodTextFileName);
      System.out.println("Method Image FileName     : " + methodImageFileName);
      
      System.out.println("Method Text FileName Path : " + methodTextFileNamePath);
      System.out.println("Method Image FileName Path: " + methodImageFileNamePath);
*/      

       try 
       {
          annotateUMLSequenceDiagramFormPanel.image2 = ImageIO.read(new File(methodImageFileNamePath));
	      annotateUMLSequenceDiagramFormPanel.img2Icon = new ImageIcon(annotateUMLSequenceDiagramFormPanel.image2);
	      annotateUMLSequenceDiagramFormPanel.image2Label.setIcon(annotateUMLSequenceDiagramFormPanel.img2Icon); 
	     
	      //System.out.println("Displaying the UML Sequence Diagram at the Method Level, PLACE #2");
	   } 
       catch (IOException e4) 
       {
 	      errorMessage1 = e4.getMessage();
 	      errorMessage2 = "Error 1002: Ocurred while displaying the UML Sequence Diagram Image at the Method Level: " + methodImageFileNamePath + System.lineSeparator();
 	      errorMessage2 = errorMessage2 + errorMessage1;
 	     
 	      printErrorMessage(errorMessage2, "Displaying the UML Sequence Diagram at the Method Level");
	   }     

      return methodId;
   } 
   
   public ParameterSequenceDiagram getParameters(String databaseConfigFile, int projectId, int methodId) throws Exception
   {
	  String errorMessage1="", errorMessage2="", parameterDataTypeName="", parameterName="", elementName="", 
			 parameterIdStr="", parameterDataTypeIdStr="", packageName="";
	  int i=0, parameterId=0, parameterDataTypeId=0, paramCase=0;
	  ArrayList<ParameterSequenceDiagram> parameterList;
	  ParameterSequenceDiagram parameterRecord=null;
	  
      // Delete all the current items in the Combo
	  annotateUMLSequenceDiagramFormPanel.parameterNameCombo.removeAllItems();
	  
	  //System.out.println("*** Inside the FRAME - GetParameters Method ***");
	  
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage1 = e1.getMessage();
	     throw new Exception(errorMessage1);
	  }
	     
	  try 
	  {
	     // Obtain an ArrayList of the parameters
		 parameterList = annotateUMLSequenceDiagramController.loadParameters(projectId, methodId);
	  }	 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception(errorMessage1);
			  //printErrorMessage(errorMessage, "Loading the Parameters");
	  }
	  
	  if(parameterList.size() > 0)	
	  {	  
		 // Load all the parameters from this Project
		 for (i = 0; i < parameterList.size(); i++) 
		 {
		    parameterId           = parameterList.get(i).getParameterIdentifier();
		    parameterName         = parameterList.get(i).getParameterName();
		    parameterDataTypeId   = parameterList.get(i).getDataTypeIdentifier();
		    parameterDataTypeName = parameterList.get(i).getDataTypeName();
		    packageName           = parameterList.get(i).getPackageName();
		    
		    parameterIdStr         = "";
		    parameterDataTypeIdStr = "";
		    paramCase = 0;
		    
		    /*
		    System.out.println("Parameter Id             : " + parameterId);
		    System.out.println("Parameter Name           : " + parameterName);
		    System.out.println("Parameter Data Type Id   : " + parameterDataTypeId);
		    System.out.println("Parameter Data Type Name : " + parameterDataTypeName);
		    System.out.println("Package                  : " + packageName);
		    */
		    
			// Convert from Integer to String
			try
			{
			   parameterIdStr = String.valueOf(parameterId); 
			}
			catch (Exception e3) 
			{
			   errorMessage1 = e3.getMessage();
			   errorMessage2 = "Error XXXX: Occurred in the FRAME while converting to String the Parameter Id: " + parameterId + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   parameterIdStr = "0";
			   throw new Exception(errorMessage2);
			}  
			
			// Convert from Integer to String
			try
			{
		       parameterDataTypeIdStr = String.valueOf(parameterDataTypeId);
			}
			catch (Exception e4) 
			{
			   errorMessage1 = e4.getMessage();
			   errorMessage2 = "Error XXXX: Occurred in the FRAME while converting to String the Parameter Data Type Id: " + parameterDataTypeId + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   parameterDataTypeIdStr = "0";
			   throw new Exception(errorMessage2);
			}

				    
		    // Assign the Parameter Name to the Combo
			try
			{

			   if(packageName != null && packageName.trim().isEmpty() == false) 
			   {	
			      elementName = packageName + "." + parameterDataTypeName + " " + parameterName;
			      paramCase = 1; 
			   }   
			   else
		          if( (parameterDataTypeName != null && parameterDataTypeName.trim().isEmpty() == false) &&
		    	      (parameterName != null && parameterName.trim().isEmpty() == false) ) 	
		          {	   
			         elementName = parameterDataTypeName + " " + parameterName;
			         paramCase = 1; 
		          }   
		          else
		          {	   
		    	      elementName = "";
		    	      paramCase = 2; 
		          }
			}
			catch (Exception e5) 
			{
			   errorMessage1 = e5.getMessage();
			   errorMessage2 = "Error XXXX: Occurred in the FRAME while assigning the Element Name: " + elementName + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Package Name             : " + packageName + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Parameter Data Type Name : " + parameterDataTypeName + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Parameter Name           : " + parameterName + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   parameterDataTypeIdStr = "0";
			   throw new Exception(errorMessage2);
			}
			
            /*
			System.out.println("Element Name             : " + elementName);
			System.out.println("Parameter Case           : " + paramCase);
		    System.out.println("Parameter Data Type Id   : " + parameterDataTypeId);
		    System.out.println("Parameter Data Type Name : " + parameterDataTypeName);
             */
		    		
		    // Assign the values to the GUI
		    if(paramCase == 1)
		    {	   
			   try
			   { 
			      annotateUMLSequenceDiagramFormPanel.parameterNameCombo.addItem(elementName);
			   }
			   catch (Exception e6) 
			   {
			      errorMessage1 = e6.getMessage();
				  errorMessage2 = "Error XXXX: Occurred in the FRAME while adding to the Combo the Parameter: " + elementName + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
				  throw new Exception(errorMessage2);
			   }
		    }  
		    
		    if(i==0)
		    { 	
		       if(paramCase == 1)    
		       {
			      try
				  {  
				     annotateUMLSequenceDiagramFormPanel.parameterNameCombo.setSelectedItem(elementName);	
				  }
				  catch (Exception e7) 
				  {
				     errorMessage1 = e7.getMessage();
					 errorMessage2 = "Error XXXX: Occurred in the FRAME while selecting the Parameter: " + elementName + System.lineSeparator();
					 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
					 throw new Exception(errorMessage2);
			      }
		       }
		       
		       try
			   {   
			      annotateUMLSequenceDiagramFormPanel.parameterIdField.setText(parameterIdStr);
			   }
			   catch (Exception e8) 
			   {
			      errorMessage1 = e8.getMessage();
				  errorMessage2 = "Error XXXX: Occurred in the FRAME while setting the Value of the Parameter Id : " + parameterIdStr + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
				  throw new Exception(errorMessage2);
			   }
		       
		       
		       try
			   {   
			      annotateUMLSequenceDiagramFormPanel.parameterDataTypeIdField.setText(parameterDataTypeIdStr);
			   }
			   catch (Exception e9) 
			   {
			      errorMessage1 = e9.getMessage();
				  errorMessage2 = "Error XXXX: Occurred in the FRAME while setting the Value of the Parameter Data Type Id : " + parameterDataTypeIdStr + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
				  throw new Exception(errorMessage2);
			   }	
		       
		       try
		       {
		          parameterRecord = new ParameterSequenceDiagram(projectId, methodId, parameterId, 
		    		                                             parameterName, parameterDataTypeId, 
		    		                                             parameterDataTypeName,
		    		                                             packageName);
			   }
			   catch (Exception e10) 
			   {
			      errorMessage1 = e10.getMessage();
				  errorMessage2 = "Error XXXX: Occurred in the FRAME while creating a new Parameter Record" + parameterDataTypeIdStr + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Method Id      : " + parameterId + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Parameter Id   : " + parameterId + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Parameter Name : " + parameterName + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
				  throw new Exception(errorMessage2);
			   }
		    }
		    
		    
	     }
		     
	   } 
	  
	   try 
	   {
	      annotateUMLSequenceDiagramController.disconnect();
	   } 
	   catch (Exception e11) 
	   {
	      errorMessage1 = e11.getMessage();
	      throw new Exception(errorMessage1);
		  //printErrorMessage(errorMessage, "Disconnect from the Database.");
	   }
	   
      return parameterRecord;
	   
   } 
   
   public ParameterSequenceDiagram getParameterIds(String databaseConfigFile, int projectId, 
		                                           int methodId, String parameterName, 
		                                           String parameterDataTypeName)
   {
      String errorMessage="", parameterIdStr="", parameterDataTypeIdStr=""; 
      int parameterId=0, parameterDataTypeId=0;
      ParameterSequenceDiagram parameterRecord=null;
      
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage = e1.getMessage();
	     printErrorMessage(errorMessage, "Connecting to the Database");
	  }
      
	  try 
	  {	  
	     parameterRecord = annotateUMLSequenceDiagramController.getParametersIds(projectId, methodId, 
	    		                                                                 parameterName, 
	    		                                                                 parameterDataTypeName);
	  }   
      catch(Exception e2)
      {
         errorMessage = e2.getMessage();
	     printErrorMessage(errorMessage, "Getting the Method Identifier");
      } 
	  
	  // Validate that the parameterRecord contains data
	  if(parameterRecord != null)
	  {	  
	     parameterId         = parameterRecord.getParameterIdentifier();
	     parameterDataTypeId = parameterRecord.getDataTypeIdentifier();
	  }

	  // Assign the values to the GUI
      parameterIdStr = String.valueOf(parameterId);
      annotateUMLSequenceDiagramFormPanel.parameterIdField.setText(parameterIdStr);
      
      parameterDataTypeIdStr = String.valueOf(parameterDataTypeId);
      annotateUMLSequenceDiagramFormPanel.parameterDataTypeIdField.setText(parameterDataTypeIdStr);
      
	  try 
	  {
	      annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage = e3.getMessage();
	     printErrorMessage(errorMessage, "Disconnect from the Database");
	  }
      
      return parameterRecord;

   } 
   
   public int getAttributes(String databaseConfigFile, int projectId, int methodId,
                             int parameterId, int parameterDataTypeId)
   {
	  String errorMessage="", attributeName="", attributeIdStr="",
			 attributeDataTypeName="", element="";
	  int i=0, attributeId=0, initialAttributeId=0;
	  ArrayList<AttributeSequenceDiagram> attributeList;
	  
      // Delete all the current items in the Combo
	  annotateUMLSequenceDiagramFormPanel.attributeNameCombo.removeAllItems();
	  
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage = e1.getMessage();
	     printErrorMessage(errorMessage, "Connecting to the Database");
	  }
	     
	  try 
	  {
	     // Obtain an ArrayList of the Attributes
		 attributeList = annotateUMLSequenceDiagramController.loadAttributes(projectId, methodId,
				                                                             parameterId, 
				                                                             parameterDataTypeId);
		     
		 // Load all the attributes from this Project
		 for (i = 0; i < attributeList.size(); i++) 
		 {
		    attributeId    = attributeList.get(i).getAttributeIdentifier();
		    attributeName  = attributeList.get(i).getAttributeName();
		    attributeDataTypeName = attributeList.get(i).getAttributeDataTypeName();
		    
		    // Convert from Integer to String
		    attributeIdStr = String.valueOf(attributeId); 
		    element = attributeName;
			    
		    // Assign the values to the GUI
		    annotateUMLSequenceDiagramFormPanel.attributeNameCombo.addItem(element);
		    
		    if(i==0)
		    { 	
		       annotateUMLSequenceDiagramFormPanel.attributeIdField.setText(attributeIdStr);
		       annotateUMLSequenceDiagramFormPanel.attributeDataTypeNameField.setText(attributeDataTypeName);
		       
		       // Enable/Disable the Comparison Combox for the Parameter DataType
		       annotateUMLSequenceDiagramFormPanel.attributeCombos(attributeDataTypeName);
		       
		       initialAttributeId = attributeId;
		    }
	      }
		     
	   } 
       catch (Exception e2) 
	   {
	      errorMessage = e2.getMessage();
		  printErrorMessage(errorMessage, "Loading the Attributes in the Combo");
	   }
	  
	   try 
	   {
	      annotateUMLSequenceDiagramController.disconnect();
	   } 
	   catch (Exception e3) 
	   {
	      errorMessage = e3.getMessage();
		  printErrorMessage(errorMessage, "Disconnect from the Database.");
	   }
	   
	   return initialAttributeId;
	   
   } 
   
   public int getAttributeId(String databaseConfigFile, int projectId, int methodId, int parameterId,
		                     int parameterDataTypeId, String attributeName, String attributeValue)
   {
      String errorMessage="", attributeIdStr="", attributeDataTypeStr=""; 
      int attributeId=0;
      AttributeSequenceDiagram attributeSeqDiagramRecord = null;
      
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage = e1.getMessage();
	     printErrorMessage(errorMessage, "Connecting to the Database");
	  }
      
	  try 
	  {	  
	     attributeSeqDiagramRecord = annotateUMLSequenceDiagramController.getAttributeInfo(projectId, methodId,
                                                                                           parameterId, parameterDataTypeId,
                                                                                           attributeName, attributeValue);
	  }   
      catch(Exception e2)
      {
         errorMessage = e2.getMessage();
	     printErrorMessage(errorMessage, "Getting the Attribute Information");
      } 
	  
	  try 
	  {
	      annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage = e3.getMessage();
	     printErrorMessage(errorMessage, "Disconnect from the Database");
	  }	
	  
	  // Getting the information
	  
	  if(attributeSeqDiagramRecord != null)
	  {
	     attributeId          = attributeSeqDiagramRecord.getAttributeIdentifier();
	     attributeDataTypeStr = attributeSeqDiagramRecord.getAttributeDataTypeName();
	     
	     attributeIdStr = String.valueOf(attributeId);
	      
	     // Assign the values to the GUI
	     annotateUMLSequenceDiagramFormPanel.attributeIdField.setText(attributeIdStr);
	     
	     annotateUMLSequenceDiagramFormPanel.attributeDataTypeNameField.setText(attributeDataTypeStr);
	     
	     // Enable/Disable the Comparison Combox for the Parameter DataType
	     annotateUMLSequenceDiagramFormPanel.attributeCombos(attributeDataTypeStr);
	  }      
      
      return attributeId;
	   
   } 
   
  public void getValues(String databaseConfigFile, int projectId, int methodId, int parameterId, 
		                int parameterDataTypeId, int progrLangId, int attributeId) 
  {
	  String errorMessage="", valueName="", valueIdStr="", element="";
	  //valueEquivalence="", 
	  int i=0, valueId=0;
	  ArrayList<ValueSequenceDiagram> valueList=null;
	  
      // Delete all the current values in the Combo
	  annotateUMLSequenceDiagramFormPanel.valueNameCombo.removeAllItems();
	  
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage = e1.getMessage();
	     printErrorMessage(errorMessage, "Connecting to the Database");
	  }
	  
      /*
      System.out.println("*** Inside the Load Values Method - FRAME ***");
	  
	  System.out.println("Project Id              : " + projectId);
	  System.out.println("Method Id               : " + methodId);
	  System.out.println("Parameter Id            : " + parameterId);
	  System.out.println("Parameter Data Type Id  : " + parameterDataTypeId);
	  System.out.println("Programming Language Id : " + progrLangId);
	  System.out.println("Attribute Id            : " + attributeId);
	  */
	  
	  try 
	  {
	     // Obtain an ArrayList of all the current Values from the FRL Database
		 valueList = annotateUMLSequenceDiagramController.loadValues(projectId, methodId,
				                                                     parameterId, parameterDataTypeId,
				                                                     progrLangId, attributeId);
 
	     // Load all the current attributes from this Project
		 for (i = 0; i < valueList.size(); i++) 
		 {
		    valueId           = valueList.get(i).getValueIdentifier();
		    //valueEquivalence  = valueList.get(i).getValueEquivalence();
		    valueName         = valueList.get(i).getValueName();    
		    
		    // Convert Integer to String
		    valueIdStr = String.valueOf(valueId);
		    
		    if(i == 0)
		    {
		       annotateUMLSequenceDiagramFormPanel.valueIdField.setText(valueIdStr);
		    }
		    
		    element = valueName;
		    //element = valueEquivalence;
		    
		    annotateUMLSequenceDiagramFormPanel.valueNameCombo.addItem(element);

	      }	 
	  }
     catch (Exception e) 
	  {
	      errorMessage = e.getMessage();
		  JOptionPane.showMessageDialog(this, errorMessage, "Loading the Values in the Combo", JOptionPane.ERROR_MESSAGE);	
	  }
	  
	  try 
	  {
	     annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage = e3.getMessage();
	     printErrorMessage(errorMessage, "Disconnect from the Database");
	  }	

  } 
  
  public void getValueId(String databaseConfigFile, int projectId, int methodId, int parameterId,
		                 int parameterDataTypeId, int progLangId, int attributeId, 
		                 String valueName)
  {
     String errorMessage="", valueIdStr=""; 
     int valueId=0;
     
     try 
     {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
     catch (Exception e1) 
     {
	     errorMessage = e1.getMessage();
	     printErrorMessage(errorMessage, "Connecting to the Database");
	  }
     
	  try 
	  {	  
	     valueId = annotateUMLSequenceDiagramController.getValueId(projectId, methodId,
                                                                   parameterId, parameterDataTypeId,
                                                                   progLangId, attributeId, valueName);
	  }   
     catch(Exception e2)
     {
        errorMessage = e2.getMessage();
	     printErrorMessage(errorMessage, "Getting the Value Identifier");
     } 
	  
	  try 
	  {
	      annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage = e3.getMessage();
	     printErrorMessage(errorMessage, "Disconnect from the Database");
	  }	
	  
     valueIdStr = String.valueOf(valueId);
     
     // Assign the values to the GUI
     annotateUMLSequenceDiagramFormPanel.valueIdField.setText(valueIdStr);
     
     //System.out.println("Value Id: " + valueId);
     
  } 
  
  public int getReturnTypeId(String databaseConfigFile, int projectId, int methodId) 
  {
     String errorMessage="", returnTypeIdStr="", returnTypeName1="", returnTypeName2="", returnTypePackageName="";
	 int returnTypeId=0;
	 ReturnTypeData returnTypeRecord;
	  
     // Delete all the current values in the Combo
	 annotateUMLSequenceDiagramFormPanel.valueNameCombo.removeAllItems();
	  
     try 
     {
	    annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	 } 
     catch (Exception e1) 
     {
	     errorMessage = e1.getMessage();
	     printErrorMessage(errorMessage, "Connecting to the Database");
	 }
	  
	 try 
	 {
	    // Obtain a Record with the current Values from the FRL Database
		returnTypeRecord = annotateUMLSequenceDiagramController.loadReturnType(projectId, methodId); 

		returnTypeId    = returnTypeRecord.getReturnTypeIdentifier();
		returnTypeName1 = returnTypeRecord.getReturnTypeName();
		    
		// Convert Integer to String
		returnTypeIdStr = String.valueOf(returnTypeId);
		
	    try 
	    {
	       returnTypePackageName = annotateUMLSequenceDiagramController.getReturnTypePackageName(projectId, methodId, returnTypeId);
		} 
	    catch (Exception e2) 
	    {
		   errorMessage = e2.getMessage();
		   printErrorMessage(errorMessage, "Loading the Return Type Package Name");
		}
	    
	    if(returnTypePackageName != null && !returnTypePackageName.trim().isEmpty()) 
	       returnTypeName2 = returnTypePackageName + " " + returnTypeName1;
		else
	       returnTypeName2 = returnTypeName1;
		    
		annotateUMLSequenceDiagramFormPanel.returnTypeIdField.setText(returnTypeIdStr);
		annotateUMLSequenceDiagramFormPanel.returnTypeNameField.setText(returnTypeName2);
		
	}
    catch (Exception e3) 
	{
	   errorMessage = e3.getMessage();
	   JOptionPane.showMessageDialog(this, errorMessage, "Loading the Return Type Id", JOptionPane.ERROR_MESSAGE);	
	}
	  
	try 
	{
	   annotateUMLSequenceDiagramController.disconnect();
	} 
	catch (Exception e3) 
	{
	   errorMessage = e3.getMessage();
	   printErrorMessage(errorMessage, "Disconnect from the Database");
    }
	
	return returnTypeId;

   } 
  
  public void loadReturnTypeValues(String databaseConfigFile, int projectId, int methodId, int returnTypeId) 
  {
     String errorMessage="", returnTypeValueIdStr="", element="", returnTypeValueName1="", 
    		returnTypeValueName2="", returnTypeValueEquivalence="", returnTypeValuePackageName="";
	 int i=0, returnTypeValueId=0;
	 ArrayList<ReturnTypeValueSequenceDiagram> returnTypeValueList=null;
	  
     // Delete all the current values in the Combo
	 annotateUMLSequenceDiagramFormPanel.returnTypeValueCombo.removeAllItems();
	 
     try 
     {
	    annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	 } 
     catch (Exception e1) 
     {
	     errorMessage = e1.getMessage();
	     printErrorMessage(errorMessage, "Connecting to the Database");
	 }
	  
	 try 
	 {
	    // Obtain an ArrayList of all the current Values from the FRL Database
		returnTypeValueList = annotateUMLSequenceDiagramController.loadReturnTypeValues(projectId, methodId, returnTypeId);

	    // Load all the current attributes from this Project
		for (i = 0; i < returnTypeValueList.size(); i++) 
		{
		   returnTypeValueId           = returnTypeValueList.get(i).getReturnTypeValueIdentifier();
		   returnTypeValueName1        = returnTypeValueList.get(i).getReturnTypeValueName();
		   returnTypeValueEquivalence  = returnTypeValueList.get(i).getReturnTypeValueEquivalence();
		   returnTypeValuePackageName = returnTypeValueList.get(i).getReturnTypeValuePackageName();
		    
		   // Convert Integer to String
		   returnTypeValueIdStr = String.valueOf(returnTypeValueId);
		   
		   if(returnTypeValuePackageName != null && !returnTypeValuePackageName.trim().isEmpty()) 
		      returnTypeValueName2 = returnTypeValuePackageName + "." + returnTypeValueName1;
		   else
		      returnTypeValueName2 = returnTypeValueName1;
		    
		   if(i == 0)
		   {
		      annotateUMLSequenceDiagramFormPanel.returnTypeValueIdField.setText(returnTypeValueIdStr);
		      annotateUMLSequenceDiagramFormPanel.returnTypeNameField.setText(returnTypeValueName2);
		   }
		    
		   element = returnTypeValueEquivalence;
		    
		   annotateUMLSequenceDiagramFormPanel.returnTypeValueCombo.addItem(element);

	   }	 
	}
    catch (Exception e2) 
	{
	   errorMessage = e2.getMessage();
	   printErrorMessage(errorMessage, "Loading the Return Type Values in the Combo");
	}
	 
    try 
    {
       annotateUMLSequenceDiagramController.disconnect();
    } 
    catch (Exception e3) 
    {
       errorMessage = e3.getMessage();
	   printErrorMessage(errorMessage, "Disconnect from the Database");
	} 

   }  
  
   public int getReturnTypeValueId(String databaseConfigFile, int projectId, int methodId, String returnTypeValueName)
   {
      String errorMessage="", returnTypeValueIdStr=""; 
      int returnTypeValueId=0;
     
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage = e1.getMessage();
	     printErrorMessage(errorMessage, "Connecting to the Database");
	  }
     
	  try 
	  {	  
         returnTypeValueId = annotateUMLSequenceDiagramController.getReturnTypeValueId(projectId, methodId, returnTypeValueName);
	  }   
      catch(Exception e2)
      {
         errorMessage = e2.getMessage();
	     printErrorMessage(errorMessage, "Getting the Return Type Value Identifier");
      } 
	  
	  try 
	  {
	     annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage = e3.getMessage();
	     printErrorMessage(errorMessage, "Disconnect from the Database");
	  }	
	  
	  returnTypeValueIdStr = String.valueOf(returnTypeValueId);
     
      // Assign the values to the GUI
      annotateUMLSequenceDiagramFormPanel.returnTypeValueIdField.setText(returnTypeValueIdStr);
      
      return returnTypeValueId;
     
   }
   
   public void displayUpdatedSequenceDiagrams(FRLConfiguration frlCon, String userName,
                                              String userTextFileName, String userImageFileName,
                                              String methodTextFileName, String methodImageFileName) throws Exception
   {
      String userTextFileNamePath="", userImageFileNamePath="", methodTextFileNamePath="", methodImageFileNamePath="",
    		 errorMessage1="", errorMessage2=""; 
      
      BufferedImage image = null;


      //System.out.println("DISPLAY THE UPDATED UML SEQUENCE DIAGRAM");
            
      /******************************************************* 
      * Update the UML Sequence Diagram at the User Level 
      *******************************************************/
      // Construct the complete path of the UML Text and Image Files at the Method Level
      userTextFileNamePath = frlCon.projectOutputDir + File.separator +
      frlCon.umlDirectoryName + File.separator + 
      frlCon.projectName      + File.separator + 
      userName                + File.separator + 
      userTextFileName;

      userImageFileNamePath = frlCon.projectOutputDir + File.separator +
      frlCon.umlDirectoryName + File.separator + 
      frlCon.projectName      + File.separator + 
      userName                + File.separator + 
      userImageFileName;

      annotateUMLSequenceDiagramFormPanel.userTextFileNameField.setText(userTextFileNamePath);
      annotateUMLSequenceDiagramFormPanel.userImageFileNameField.setText(userImageFileNamePath);
      
      //System.out.println("User Image File Name: " + userImageFileName);
      
      // Assign the UML Method Image File
      //userImageFileName = "/images/" + userImageFileName;
      
      //System.out.println("UserImageFileName: " + userImageFileName);


      // Construct the complete path of the UML Text and Image Files at the User Level
      try 
      {
         image = ImageIO.read(new File(userImageFileNamePath));
         annotateUMLSequenceDiagramFormPanel.img1Icon = new ImageIcon(image);
         annotateUMLSequenceDiagramFormPanel.image1Label.setIcon(annotateUMLSequenceDiagramFormPanel.img1Icon);	
         
         //System.out.println("=> UPLOADING THE USER IMAGE WITH A DIFFERENT METHOD <=");
	  } 
      catch (IOException e1) 
      {
 	     errorMessage1 = e1.getMessage();
 	     errorMessage2 = "Error 1003: Ocurred while displaying the UML Sequence Diagram Image at the User Level: " + userImageFileNamePath + System.lineSeparator();
 	     errorMessage2 = errorMessage2 + errorMessage1;
 	     
 	    printErrorMessage(errorMessage2, "Displaying the UML Sequence Diagram at the User Level");
	  }
      

      /******************************************************* 
      * Update the UML Sequence Diagram at the Method Level 
      *******************************************************/
      // Construct the complete path of the UML Text and Image Files at the Method Level
      methodTextFileNamePath = frlCon.projectOutputDir + File.separator +
      frlCon.umlDirectoryName + File.separator + 
      frlCon.projectName      + File.separator + 
      userName                + File.separator + 
      methodTextFileName;

      methodImageFileNamePath = frlCon.projectOutputDir + File.separator +
      frlCon.umlDirectoryName + File.separator + 
      frlCon.projectName      + File.separator + 
      userName                + File.separator + 
      methodImageFileName;

      annotateUMLSequenceDiagramFormPanel.methodTextFileNameField.setText(methodTextFileNamePath);
      annotateUMLSequenceDiagramFormPanel.methodImageFileNameField.setText(methodImageFileNamePath);
      
      //System.out.println("Method Image File Name: " + methodImageFileName);
      
      // Assign the UML Method Image File
      //methodImageFileName = "/images/" + methodImageFileName;
      
      //System.out.println("methodImageFileName: " + methodImageFileName);

      // Construct the complete path of the UML Text and Image Files at the Method Level
      try 
      {
	     image = ImageIO.read(new File(methodImageFileNamePath));
	     annotateUMLSequenceDiagramFormPanel.img2Icon = new ImageIcon(image);
	     annotateUMLSequenceDiagramFormPanel.image2Label.setIcon(annotateUMLSequenceDiagramFormPanel.img2Icon); 
	     
	     //System.out.println("=> UPLOADING THE METHOD IMAGE WITH A DIFFERENT METHOD <=");
	     //System.out.println("Displaying the UML Sequence Diagram at the Method Level Place #3");
	  } 
      catch (IOException e2) 
      {
 	     errorMessage1 = e2.getMessage();
 	     errorMessage2 = "Error 1004: Ocurred while displaying the UML Sequence Diagram Image at the Method Level: " + methodImageFileNamePath + System.lineSeparator();
 	     errorMessage2 = errorMessage2 + errorMessage1;
 	     
 	     printErrorMessage(errorMessage2, "Displaying the UML Sequence Diagram at the Method Level");
	  }
      
      
   }  

   public void addAnnotatonUMLSeqDiagram(String databaseConfigFile, 
		                                 FRLConfiguration frlCon, 
		                                 AnnotateUMLSequenceDiagramFormEvent ev,
		                                 AnnotateUMLSequenceDiagramController annotateUMLSequenceDiagramController)
   
   {
	  String errorMessage1="", errorMessage2="", userTextFileName="", userImageFileName="", userNameGUI="", 
			 methodTextFileName="", methodImageFileName="";
	  int projectIdGUI=0, userIdGUI=0, methodIdGUI=0, methodStepIdGUI=0;
	  AnnotateUMLSequenceDiagram annotateUMLSeqDiagram = new AnnotateUMLSequenceDiagram();
	  FileData fileDataRecord = null;
	  UserData userData;
	  
	  // Get the Information from the GUI  
      projectIdGUI    = ev.getProjectId();
      userIdGUI       = ev.getUserId();
      userNameGUI     = ev.getUserName();
      methodIdGUI     = ev.getMethodId();
      methodStepIdGUI = ev.getMethodStepId();
		
      // Add the new annotation into the UML Sequence Diagram
      try
      {
         annotateUMLSeqDiagram.addAnnotationUMLSeqDiagram(databaseConfigFile, 
    			                                          frlCon, 
                                                          annotateUMLSequenceDiagramController,
                                                          ev);
      }
  	  catch (Exception e1) 
  	  {
         errorMessage1 = e1.getMessage();
  	     printErrorMessage(errorMessage1, "Calling the complete Add Annotation Process");
      }

	  // Load from the Database the updated UML Sequence Diagrams at the User Level and the Method Level
	  try 
	  {	  
	     fileDataRecord = annotateUMLSeqDiagram.loadUpdatedSeqDiagrams(annotateUMLSequenceDiagramController, 
	    		                                                       frlCon, databaseConfigFile,
	                                                                   projectIdGUI, userIdGUI, userNameGUI,
	                                                                   methodIdGUI, methodStepIdGUI);
	  }   
	  catch(Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
		 printErrorMessage(errorMessage1, "Creating the Updated UML Sequence Diagrams");
	  } 
		
	  // Obtain the information about the files
	  userTextFileName    = fileDataRecord.getUserTextFileName();
	  userImageFileName   = fileDataRecord.getUserImageFileName();
	  methodTextFileName  = fileDataRecord.getMethodTextFileName();
	  methodImageFileName = fileDataRecord.getMethodImageFileName();
		
	  /* 
	  System.out.println("*** GETTING THE VALUES OUTSIDE THE FUNCTION ***");
	  System.out.println("User Text File Name    : " + userTextFileName);
	  System.out.println("User Image File Name   : " + userImageFileName);
	  System.out.println("Method Text File Name  : " + methodTextFileName);
	  System.out.println("Method Image File Name : " + methodImageFileName);
	  */
		 
	  // Display in the screen the updated UML Sequence Diagrams at the User Level and the Method Level
	  //System.out.println("Displaying the updated the UML Sequence Diagrams at the User and Method Level ...");
	  
	  try 
	  {
	     displayUpdatedSequenceDiagrams(frlCon, userNameGUI, userTextFileName, userImageFileName, 
	    		                        methodTextFileName, methodImageFileName);
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 printErrorMessage(errorMessage1, "Displaying the Updated UML Sequence Diagrams in the Screen");
	  } 
	  
	  // Connect to the Database
	  try 
	  {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
	  catch (Exception e4) 
	  {
	     errorMessage1 = e4.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while Connecting to the Database";
		 errorMessage2 = errorMessage2 + errorMessage1;
		 printErrorMessage(errorMessage2, "Displaying the Updated UML Sequence Diagrams in the Screen");
      }
	  
	  // Build a new record of userData
	  userData = new UserData(projectIdGUI, userIdGUI, userNameGUI, 
			                  userTextFileName, userImageFileName);
	  
      // Update the UML Sequence Diagram Text and Image Files at the User Level
	  try 
	  {
	     annotateUMLSequenceDiagramController.updateUserFileSequenceDiagram(userData);
	  } 
	  catch (Exception e5) 
	  {
	     errorMessage1 = e5.getMessage();
		 printErrorMessage(errorMessage1, "Updating the Annotated UML Sequence Diagrams Files at the User Level");
	  } 
	  
	  // Disconnect from the Database 
	  try 
	  {
	     annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e6) 
	  {
	     errorMessage1 = e6.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while Disconnecting from the Database";
		 errorMessage2 = errorMessage2 + errorMessage1;
		 printErrorMessage(errorMessage2, "Displaying the Updated UML Sequence Diagrams in the Screen");
	   }  
	  
   }
   
   void save(String databaseConfigFile, FRLConfiguration frlCon, 
             AnnotateUMLSequenceDiagramController annotateUMLSequenceDiagramController, AnnotateUMLSequenceDiagramFormEvent ev)
   {
      String errorMessage="";	   

      AnnotateUMLSequenceDiagram annotateUMLSeqDiagram = new AnnotateUMLSequenceDiagram();
      
      try 
      {
         annotateUMLSeqDiagram.save(databaseConfigFile, frlCon, annotateUMLSequenceDiagramController, ev);
      }   
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 printErrorMessage(errorMessage, "Saving all the Annotations in the UML Sequence Diagrams");
	  } 
	   
   }
   
}