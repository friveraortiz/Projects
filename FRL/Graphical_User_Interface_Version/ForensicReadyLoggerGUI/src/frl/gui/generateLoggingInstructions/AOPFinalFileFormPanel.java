package frl.gui.generateLoggingInstructions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import frl.controller.generateLoggingInstructions.AOPFinalFileController;
import frl.gui.configFile.ProgLangDetTablePanel;
import frl.gui.toolBar.ToolBar;
import frl.model.generateAspectOrientedFiles.DBMS;
import frl.model.generateAspectOrientedFiles.InputMethod;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageHeader;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterDataSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UserSequenceDiagram;

public class AOPFinalFileFormPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
    // Define Labels //
	// Tab 1
	private JLabel projectIdLabel;
    private JLabel projectNameLabel;
    private JLabel jarFileNameLabel;
    private JLabel projInputDirLabel;
    private JLabel projOutputDirLabel;
    private JLabel progLanguageLabel;
    private JLabel dbmsLabel;
    private JLabel inputMethodLabel;
    private JLabel startProjMethodLabel;
    private JLabel endProjMethodLabel;
    private JLabel connectProjMethodLabel;
    
    // Tab 3
    private JLabel currentOperatingSystemLabel;
    private JLabel remoteOperatingSystemLabel;

    // Tab 4    
    public JLabel userIdLabel;
    public JLabel userNameLabel;  
    public JLabel methodIdLabel;
    public JLabel methodNameLabel;
    public JLabel parameterIdLabel;
    public JLabel parameterDataTypeIdLabel;
    public JLabel parameterNameLabel;
    public JLabel attributeIdLabel;
    public JLabel attributeNameLabel;
    private JLabel logDirectoryLabel;
    private JLabel aOPDirectoryLabel;
    public JLabel progLanguageIdLabel;
    public JLabel attributeName1Label;

    // Define TextFields //
    public JTextField projectIdField;
    public JTextField projectNameField;
    public JTextField jarFileNameField;
    public JTextField projInputDirField;
    public JTextField projOutputDirField;
    public JTextField startProjMethodField;
    public JTextField endProjMethodField;
    public JTextField connectProjMethodField;
    public JTextField currentOperatingSystemField;
    public JTextField remoteOperatingSystemField;
    public JTextField logDirectoryField;
    public JTextField aOPDirectoryField;
    
    public JTextField userIdField;
    public JTextField methodIdField;
    public JTextField parameterIdField;
    public JTextField parameterDataTypeIdField;
    public JTextField attributeIdField;
    public JTextField progLanguageIdField;
    
    // Define ComboBoxes //
    public JComboBox<String> progLanguageCombo;
    public JComboBox<String> dbmsCombo;
    public JComboBox<String> inputMethodCombo;
 
    public JComboBox<String> userNameCombo;
    public JComboBox<String> methodNameCombo;
    public JComboBox<String> parameterNameCombo;
    public JComboBox<String> attributeNameCombo;
    
    // Define a JList
    public JList<?> attributeNameList;
    
    // Define Buttons //
	public JButton openLogDirectoryBtn;
	public JButton openAOPDirectoryBtn;
	
	public JButton generateBtn;
	public JButton cleanBtn;
	public JButton cancelBtn;
	
	public JButton addBtn;
	public JButton deleteBtn;

    public JPanel panel1 = new JPanel();
    public JPanel panel2 = new JPanel();
    public JPanel panel3 = new JPanel();
    public JPanel panel4 = new JPanel();
    public JPanel buttonPane = new JPanel();
    public JTabbedPane umlSequenceDiagramTabPanel = new JTabbedPane();
    
    private String annotationFilePrefix;
    private String annotationFile;
    private String findWhiteSpaces;
    private String validSpecialCharacter;
    private String valueDelimiter;
    private String endStatementDelimiter;
    private String confPropertyName1;
    private String startCallMethod;
    private String endCallMethod1;
    private String callMethodDelimiter;
    private String assignValueDelimiter;
    private String guiLibDelimiter;
    private String findString;
    private String textFileNameExt3;
    
    private String attributes[]= { "Attribute1", "Attribute2", "Attribute3"};

    // Controller
    private AOPFinalFileController aopFinalFileController;
    
    // Listener //
    private AOPFinalFileFormListener aOPFinalFileFormListener;
    
    // Table //
    private ProgLangDetTablePanel progLangDetTablePanel;

	public AOPFinalFileFormPanel(String databaseConfigFile, String featuresConfigFile)
	{
		Dimension dim = getPreferredSize();
		
		// Set the Size of the Form
		dim.height = 600;
		dim.width = 1200;
		setPreferredSize(dim);
		
		// Define an object for the Controller Class
		aopFinalFileController = new AOPFinalFileController();
		
		// Define an object for the Table Panel Class
        progLangDetTablePanel = new ProgLangDetTablePanel();
        
        // Define an object for the ProgLangDetTablePanel Class
        progLangDetTablePanel.setData(aopFinalFileController.getProgLangDet());
        
        aopFinalFileController.getProgLangDet();
		
		// Create the Fields //
		projectIdLabel = new JLabel("Project Id: ");
		projectIdField = new JTextField(10); 
		 
		// Make the projectIdField not editable
		Color color = Color.LIGHT_GRAY;
		projectIdField.setBackground(color);
	    projectIdField.setToolTipText("Identifier of the Project Application");
		
	    // Set the color of the Form
	    this.setBackground(Color.lightGray);
	     
	    projectNameLabel = new JLabel("Project Name: ");
	    projectNameField = new JTextField(40);
	    projectNameField.setBackground(color);
	    projectNameField.setToolTipText("Project Application Name");
	    
	    jarFileNameLabel = new JLabel("JAR File Name: ");
	    jarFileNameField = new JTextField(40);
	    jarFileNameField.setBackground(color);
	    jarFileNameField.setToolTipText("Java Archive File Name");
	    
	    projInputDirLabel = new JLabel("Input Directory: ");
	    projInputDirField = new JTextField(40);
	    projInputDirField.setBackground(color);
	    projInputDirField.setToolTipText("Project Input Folder");
	    
	    projOutputDirLabel = new JLabel("Output Directory: ");
	    projOutputDirField = new JTextField(40);
	    projOutputDirField.setBackground(color);
	    projOutputDirField.setToolTipText("Project Output Folder");
	    
	    progLanguageIdLabel = new JLabel("Programming Language Id: ");
	    progLanguageIdField = new JTextField(30);
	    progLanguageIdField.setBackground(color);
	    progLanguageIdField.setToolTipText("Programming Language:");
	    
	    color = Color.WHITE;
	    progLanguageLabel = new JLabel("Programming Language: ");
		progLanguageCombo = new JComboBox<String>(); 
		progLanguageCombo.setBackground(color);
		progLanguageCombo.setToolTipText("Project Source Code Language");
		
	    dbmsLabel = new JLabel("DBMS: ");    
		dbmsCombo = new JComboBox<String>(); 
		dbmsCombo.setBackground(color);
		dbmsCombo.setToolTipText("Database Management System");
			    
	    inputMethodLabel = new JLabel("Input Method: ");
	    inputMethodCombo = new JComboBox<String>(); 
		inputMethodCombo.setBackground(color);
		inputMethodCombo.setToolTipText("Input Method");
	    
	    startProjMethodLabel = new JLabel("Start Method: ");
	    startProjMethodField = new JTextField(40);
	    startProjMethodField.setBackground(color);
	    startProjMethodField.setToolTipText("Project Start Method Name");
	    
	    endProjMethodLabel = new JLabel("End Method: ");
	    endProjMethodField = new JTextField(40);
	    endProjMethodField.setBackground(color);
	    endProjMethodField.setToolTipText("Project End Method Name");
	    
	    connectProjMethodLabel = new JLabel("Connect Method: ");
	    connectProjMethodField = new JTextField(40);
	    connectProjMethodField.setBackground(color);
	    connectProjMethodField.setToolTipText("Project Connect Method Name");
	    	    
		color = Color.LIGHT_GRAY;
	    currentOperatingSystemLabel = new JLabel("Current Operating System: ");
	    currentOperatingSystemField = new JTextField(40);
	    currentOperatingSystemField.setBackground(color);
	    currentOperatingSystemField.setToolTipText("Current Operating System");
	    
		color = Color.LIGHT_GRAY;
	    remoteOperatingSystemLabel = new JLabel("Remote Operating System: ");
	    remoteOperatingSystemField = new JTextField(40);
	    remoteOperatingSystemField.setBackground(color);
	    remoteOperatingSystemField.setToolTipText("Remote Operating System");
	    
		color = Color.LIGHT_GRAY;
		logDirectoryLabel = new JLabel("Log Directory: ");
		logDirectoryField = new JTextField(30);
		logDirectoryField.setBackground(color);
		logDirectoryField.setToolTipText("Logging Directory");
			    
		openLogDirectoryBtn = new JButton("Opens a Log Directory");
		openLogDirectoryBtn.setToolTipText("Opens a Log Directory from the File System");
		
		
		color = Color.LIGHT_GRAY;
		aOPDirectoryLabel = new JLabel("AOP Directory: ");
		aOPDirectoryField = new JTextField(30);
		aOPDirectoryField.setBackground(color);
		aOPDirectoryField.setToolTipText("AOP Directory");
			    
		openAOPDirectoryBtn = new JButton("Opens a AOP Directory");
		openAOPDirectoryBtn.setToolTipText("Opens a AOP Directory from the File System");
		
		color = Color.LIGHT_GRAY;
		userIdLabel = new JLabel("User Id: ");
	    userIdField = new JTextField(30);
	    userIdField.setBackground(color);
	    userIdField.setToolTipText("User Identifier");
	    
		methodIdLabel = new JLabel("Method Id: ");
	    methodIdField = new JTextField(30);
	    methodIdField.setBackground(color);
	    methodIdField.setToolTipText("Method Identifier");
	    
		parameterIdLabel = new JLabel("Parameter Id: ");
	    parameterIdField = new JTextField(30);
	    parameterIdField.setBackground(color);
	    parameterIdField.setToolTipText("Parameter Identifier");
	    
		parameterDataTypeIdLabel = new JLabel("Parameter Data Type Id: ");
		parameterDataTypeIdField = new JTextField(30);
		parameterDataTypeIdField.setBackground(color);
		parameterDataTypeIdField.setToolTipText("Parameter Data Type Identifier");
		
		attributeIdLabel = new JLabel("Attribute Id: ");
		attributeIdField = new JTextField(30);
		attributeIdField.setBackground(color);
		attributeIdField.setToolTipText("Attribute Identifier");
		
        userNameLabel = new JLabel("User Name: ");
        userNameCombo = new JComboBox<String>();
        userNameCombo.setBackground(color);
        userNameCombo.setToolTipText("List of Users that participate in the UML Sequence Diagram");
		
		methodNameLabel = new JLabel("Method Name: ");
        methodNameCombo = new JComboBox<String>();
        methodNameCombo.setBackground(color);
        methodNameCombo.setToolTipText("List of the Methods that participate in the UML Sequence Diagram");
        
		parameterNameLabel = new JLabel("Parameter Name: ");
        parameterNameCombo = new JComboBox<String>();
        parameterNameCombo.setBackground(color);
        parameterNameCombo.setToolTipText("List of the Parameters that belongs to the selected Method");
        
        attributeNameLabel = new JLabel("Attribute Name: ");
        attributeNameCombo = new JComboBox<String>();
        attributeNameCombo.setBackground(color);
        attributeNameCombo.setToolTipText("List of the Attributes that belongs to the selected Parameter");
        
	    attributeName1Label = new JLabel("Do not Log Attributes: ");
	    attributeNameList = new JList(attributes); 
	    attributeNameList.setBackground(color);
	    attributeNameList.setToolTipText("Attributes that do not be logged");

	    // JButtons Objects //
		generateBtn = new JButton("Generate");
		generateBtn.setToolTipText("Generates the Logging Instructions.");
		
		cleanBtn = new JButton("Clean");
		cleanBtn.setToolTipText("Cleans all the fields in the Logging Instructions Form");

		cancelBtn = new JButton("Cancel");
		cancelBtn.setToolTipText("Closes the Logging Instructions Form");
		
		addBtn = new JButton("Add");
		addBtn.setToolTipText("Adds a attribute that will not be logged");
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setToolTipText("Delete a attribute that will not be logged");

		// Declare the Listeners //

		// Listener for the progLanguageCombo
		progLanguageCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AOPFinalFileFormEvent ev;
				String projectIdS1="", progLanguage1="";
				
				// Obtain the value of the Objects //
				projectIdS1   = projectIdField.getText();
				progLanguage1 = (String) progLanguageCombo.getSelectedItem();  
				
				// Validate if the Id is not null
				if(projectIdS1 != null && !projectIdS1.isEmpty()) 
				{ 
				   int projectId1 = Integer.parseInt(projectIdS1);
				   // Calling the constructor # 1 for the UMLSequenceDiagramForm Event Class
				   //ev = new AOPFinalFileFormEvent(this, projectId1, "programmingLanguage", progLanguage1);
				}
				else
				{
				   // Calling the constructor # 2 for the UMLSequenceDiagram Form Event Class
				   //ev = new AOPFinalFileFormEvent(this, "programmingLanguage", progLanguage1);
				}
				
				if(aOPFinalFileFormListener != null) 
				{
				   //aOPFinalFileFormListener.progLanguageComboEventOccurred(ev);
				}	
			}
			
		});
		
		openLogDirectoryBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AOPFinalFileFormEvent ev;
				String projectIdStr="", logDirectory="";
				int projectId=0;
				
				// Obtain the value of the Objects //
				projectIdStr = projectIdField.getText();
				logDirectory = logDirectoryField.getText();
				
				// Validate if the Id is not null
				if(projectIdStr != null && !projectIdStr.isEmpty()) 
				   projectId = Integer.parseInt(projectIdStr);
				else
				   projectId = 0;
				
				// Calling the constructor # 1 for the UMLSequenceDiagramForm Event Class
				ev = new AOPFinalFileFormEvent(this, projectId, logDirectory);
				
				if(aOPFinalFileFormListener != null) 
				{
				   aOPFinalFileFormListener.openLogDirectoryEventOccurred(ev);
				}	
			}
			
		});
		
		
		openAOPDirectoryBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AOPFinalFileFormEvent ev;
				String projectIdStr="", aOPDirectory="";
				int projectId=0;
				
				// Obtain the value of the Objects //
				projectIdStr = projectIdField.getText();
				aOPDirectory = aOPDirectoryField.getText();
				
				// Validate if the Id is not null
				if(projectIdStr != null && !projectIdStr.isEmpty()) 
				   projectId = Integer.parseInt(projectIdStr);
				else
				   projectId = 0;
				
				// Calling the constructor # 1 for the UMLSequenceDiagramForm Event Class
				ev = new AOPFinalFileFormEvent(this, projectId, aOPDirectory);
				
				if(aOPFinalFileFormListener != null) 
				{
				   aOPFinalFileFormListener.openAOPDirectoryEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the userNameCombo
		userNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String userName="", userIdStr="", projectIdStr="", progrLanguageIdStr="";
				int projectId=0, userId=0, progrLanguageId=0;
				AOPFinalFileFormEvent ev;
				
				// Get the values from the GUI
				projectIdStr       = projectIdField.getText();
				userName           = (String) userNameCombo.getSelectedItem();  
				userIdStr          = userIdField.getText();
				progrLanguageIdStr = progLanguageIdField.getText();
				
				if(projectIdStr != null && !projectIdStr.trim().isEmpty())
			       projectId = Integer.valueOf(projectIdStr); 
				else
				   projectId=0;
				
				if(userIdStr != null && !userIdStr.trim().isEmpty())
				   userId = Integer.valueOf(userIdStr); 
				else
					userId=0;
				
				if(progrLanguageIdStr != null && !progrLanguageIdStr.trim().isEmpty())
				   progrLanguageId = Integer.valueOf(progrLanguageIdStr); 
				else
				   progrLanguageId=0;
				
				// Calling the constructor #6 from the AnnotateUMLSequenceDiagramFormEvent Event Class
				ev = new AOPFinalFileFormEvent(this, projectId, userId, userName, progrLanguageId);
				
				if(aOPFinalFileFormListener != null) 
				{
			       aOPFinalFileFormListener.userNameComboEventOccurred(ev);
				}	
				
			}
			
		});
		
		// Listener for the methodNameCombo
		methodNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String userIdStr="", projectIdStr="", methodName="", methodIdStr="", progrLanguageIdStr="";
				int projectId=0, userId=0, methodId=0, progrLanguageId=0;
				AOPFinalFileFormEvent ev;
				
				// Get the values from the GUI
				projectIdStr = projectIdField.getText();
				userIdStr    = userIdField.getText();
				methodIdStr  = methodIdField.getText();
				methodName   = (String) methodNameCombo.getSelectedItem();  
				progrLanguageIdStr = progLanguageIdField.getText();
				
				if(projectIdStr != null && !projectIdStr.trim().isEmpty())
			       projectId = Integer.valueOf(projectIdStr); 
				else
				   projectId =0;
				
				if(userIdStr != null && !userIdStr.trim().isEmpty())
				   userId = Integer.valueOf(userIdStr); 
				else
					userId=0;
				
				if(methodIdStr != null && !methodIdStr.trim().isEmpty())
				   methodId = Integer.valueOf(methodIdStr); 
			    else
			       methodId=0;
				
			    if(progrLanguageIdStr != null && !progrLanguageIdStr.trim().isEmpty())
			       progrLanguageId = Integer.valueOf(progrLanguageIdStr); 
				else
				   progrLanguageId=0;
				
				// Calling the constructor #7 from the AnnotateUMLSequenceDiagramFormEvent Event Class
				ev = new AOPFinalFileFormEvent(this, projectId, userId, methodId, methodName, progrLanguageId);
				
				if(aOPFinalFileFormListener != null) 
				{
			       aOPFinalFileFormListener.methodNameComboEventOccurred(ev);
				}	
			}
			
		});
		// Listener for the parameterNameCombo
		parameterNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String projectIdStr="", methodIdStr="", progrLanguageIdStr="", parameterIdStr="", parameterDataTypeIdStr="", parameterFullName="";
				int projectId=0, methodId=0, progrLanguageId=0, parameterId=0, parameterDataTypeId=0;
				AOPFinalFileFormEvent ev;
				
				// Get the values from the GUI
				projectIdStr           = projectIdField.getText();
				methodIdStr            = methodIdField.getText();
				parameterIdStr         = parameterIdField.getText(); 
				parameterDataTypeIdStr = parameterDataTypeIdField.getText(); 
				parameterFullName      = (String) parameterNameCombo.getSelectedItem();
				progrLanguageIdStr     = progLanguageIdField.getText();
				
				if(projectIdStr != null && !projectIdStr.trim().isEmpty())
			       projectId = Integer.valueOf(projectIdStr); 
				else
				   projectId =0;
				
				if(methodIdStr != null && !methodIdStr.trim().isEmpty())
				   methodId = Integer.valueOf(methodIdStr); 
			    else
			       methodId=0;
				
			    if(progrLanguageIdStr != null && !progrLanguageIdStr.trim().isEmpty())
			       progrLanguageId = Integer.valueOf(progrLanguageIdStr); 
				else
				   progrLanguageId=0;
			    
			    if(parameterIdStr != null && !parameterIdStr.trim().isEmpty())
			       parameterId = Integer.valueOf(parameterIdStr); 
				else
					parameterId=0;
			    
			    if(parameterDataTypeIdStr != null && !parameterDataTypeIdStr.trim().isEmpty())
			       parameterDataTypeId = Integer.valueOf(parameterDataTypeIdStr); 
				else
				   parameterDataTypeId=0;
			    
				// Calling the constructor #8 from the AnnotateUMLSequenceDiagramFormEvent Event Class
				ev = new AOPFinalFileFormEvent(this, projectId, methodId, parameterId, parameterDataTypeId, parameterFullName, progrLanguageId);
				
				if(aOPFinalFileFormListener != null) 
				{
			       aOPFinalFileFormListener.parameterNameComboEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the attributeNameCombo
		attributeNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   String projectIdStr="", methodIdStr="", progrLanguageIdStr="", parameterIdStr="", parameterDataTypeIdStr="", attributeIdStr="", 
					  parameterFullName="", attributeName="";
			   int projectId=0, methodId=0, progrLanguageId=0, parameterId=0, parameterDataTypeId=0, attributeId=0;
			   AOPFinalFileFormEvent ev;
				
			   // Get the values from the GUI
			   projectIdStr           = projectIdField.getText();
			   methodIdStr            = methodIdField.getText();
			   parameterIdStr         = parameterIdField.getText(); 
			   parameterDataTypeIdStr = parameterDataTypeIdField.getText(); 
			   parameterFullName      = (String) parameterNameCombo.getSelectedItem();
			   progrLanguageIdStr     = progLanguageIdField.getText();
			   attributeIdStr         = attributeIdField.getText();
			   attributeName          = (String) attributeNameCombo.getSelectedItem();
				
			   if(projectIdStr != null && !projectIdStr.trim().isEmpty())
			      projectId = Integer.valueOf(projectIdStr); 
			   else
			      projectId =0;
				
			   if(methodIdStr != null && !methodIdStr.trim().isEmpty())
			      methodId = Integer.valueOf(methodIdStr); 
			   else
			      methodId=0;
				
			   if(progrLanguageIdStr != null && !progrLanguageIdStr.trim().isEmpty())
			      progrLanguageId = Integer.valueOf(progrLanguageIdStr); 
			   else
			      progrLanguageId=0;
			    
			   if(parameterIdStr != null && !parameterIdStr.trim().isEmpty())
			      parameterId = Integer.valueOf(parameterIdStr); 
			   else
			      parameterId=0;
			    
			   if(parameterDataTypeIdStr != null && !parameterDataTypeIdStr.trim().isEmpty())
			      parameterDataTypeId = Integer.valueOf(parameterDataTypeIdStr); 
			   else
			      parameterDataTypeId=0;
			   
			   if(attributeIdStr != null && !attributeIdStr.trim().isEmpty())
			      attributeId = Integer.valueOf(attributeIdStr); 
			   else
			      parameterId=0;
			    
			   // Calling the constructor #9 from the AnnotateUMLSequenceDiagramFormEvent Event Class
			   ev = new AOPFinalFileFormEvent(this, projectId, methodId, parameterId, parameterDataTypeId, parameterFullName, 
					                          attributeId, attributeName, progrLanguageId);
				
			   if(aOPFinalFileFormListener != null) 
			   {
			      aOPFinalFileFormListener.attributeNameComboEventOccurred(ev);
			   }					

			}
			
		});
		
		// Listener for the Generate Button
		generateBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{				
			   String projectIdStr="", projectName="", projectInputDir="", projectOutputDir="", progrLanguageIdStr="", logDirectory="", 
				      progLanguageName="", objectOrientedDelimiter1="", dataTypeFullClassName="", dataTypeShortClassName="", 
				      dataTypeStringList="", dataTypeNumberList="", aOPDirectory="";
				
				AOPFinalFileFormEvent ev = null;
				boolean generateLoggingInstructions=false;
				int projectId=0, rowCount=0, progrLanguageId=0;
				
				// Obtain the value from the GUI
				projectIdStr       = projectIdField.getText();
			    projectName        = projectNameField.getText();
			    projectInputDir    = projInputDirField.getText();
			    projectOutputDir   = projOutputDirField.getText();
			    progrLanguageIdStr = progLanguageIdField.getText();
			    progLanguageName   = (String) progLanguageCombo.getSelectedItem();  
			    logDirectory       = logDirectoryField.getText();
			    aOPDirectory       = aOPDirectoryField.getText();
			    
			    // Get the count of the records of the programming language detail table
			    rowCount = progLangDetTablePanel.getRowCount();
			    
			    if(rowCount > 0)
			    {	
			       objectOrientedDelimiter1 = progLangDetTablePanel.getRowColSel(2, 2);
			       endStatementDelimiter    = progLangDetTablePanel.getRowColSel(8, 2);
			       dataTypeFullClassName    = progLangDetTablePanel.getRowColSel(29, 2);
			       dataTypeShortClassName   = progLangDetTablePanel.getRowColSel(30, 2);
			       dataTypeStringList       = progLangDetTablePanel.getRowColSel(31, 2);
				   dataTypeNumberList       = progLangDetTablePanel.getRowColSel(32, 2);
			    }
			    
			    // Get the properties from the FeaturesConfiguration File
			    getProperties(featuresConfigFile);
			    		    
				// Validate if the Id is not null
				if( (projectIdStr != null && !projectIdStr.isEmpty())) 
				   projectId = Integer.parseInt(projectIdStr);
				else
					projectId= 0;
				
				if( (progrLanguageIdStr != null && !progrLanguageIdStr.isEmpty())) 
			       progrLanguageId = Integer.parseInt(progrLanguageIdStr);
				else
				   progrLanguageId= 0;
				
				// Calling the constructor # 11 for the UMLSequenceDiagramForm Event Class
				ev = new AOPFinalFileFormEvent(this, projectId, projectName, projectInputDir, projectOutputDir, progrLanguageId,
						                       progLanguageName, objectOrientedDelimiter1, dataTypeFullClassName, dataTypeShortClassName, 
                                               dataTypeStringList, dataTypeNumberList, logDirectory, aOPDirectory, annotationFile, annotationFilePrefix,
                                               findWhiteSpaces, validSpecialCharacter, valueDelimiter, endStatementDelimiter, confPropertyName1,
                                               startCallMethod, endCallMethod1, callMethodDelimiter, assignValueDelimiter, guiLibDelimiter, 
                                               findString, textFileNameExt3);
				
				if(aOPFinalFileFormListener != null) 
				{
				   generateLoggingInstructions = aOPFinalFileFormListener.generateEventOccurred(ev);
			       
				   // Validate that the AOP File has been saved
				   if (generateLoggingInstructions == true) 
				   {
			          // Clear all the fields except the text area
				      cleanFields(databaseConfigFile);
				   }
				   
		       }	
			}
			
		});

		// Listener for the Clean Button
		cleanBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   cleanFields(databaseConfigFile);
			}
			
		});	
		
		// Listener for the Cancel Button
		cancelBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   cleanFields(databaseConfigFile);
			   
			   // Makes invisible the ConfigFileFormPanel Form
			   setVisible(false);
			   
			   // Makes visible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(true);
			   
			   if(aOPFinalFileFormListener != null) 
			   {
			      aOPFinalFileFormListener.cancelEventOccurred();
			   }
			}
		});
		
		// Clean all the fields in the Configuration File Form
		cleanFields(databaseConfigFile);
		
        // Allocate the visual components into the Configuration File Form
		layoutComponents();
		
	}
	
 	// Method #2
	public void layoutComponents() 
	{
        // set grid layout for the frame
		setLayout(new BorderLayout());
	    setBackground(Color.lightGray);
        
	    panel1.setLayout(new GridBagLayout());
	    panel2.setLayout(new BorderLayout());
        panel3.setLayout(new GridBagLayout());
        panel4.setLayout(new GridBagLayout());

        
        // Tab 1: Software System Information
        // Add the components panel
        addComponent(1, 1, 1, 1, projectIdLabel, panel1, "Software System");
        addComponent(1, 2, 1, 1, projectIdField, panel1, "Software System");
        addComponent(2, 1, 1, 1, projectNameLabel, panel1, "Software System");
        addComponent(2, 2, 1, 1, projectNameField, panel1, "Software System");
        addComponent(3, 1, 1, 1, jarFileNameLabel, panel1, "Software System");
        addComponent(3, 2, 1, 1, jarFileNameField, panel1, "Software System");
        addComponent(4, 1, 1, 1, projInputDirLabel, panel1, "Software System");
        addComponent(4, 2, 1, 1, projInputDirField, panel1, "Software System");
        addComponent(5, 1, 1, 1, projOutputDirLabel, panel1, "Software System");
        addComponent(5, 2, 1, 1, projOutputDirField, panel1, "Software System");
        
        addComponent(6, 1, 1, 1, progLanguageLabel, panel1, "Software System");
        addComponent(6, 2, 1, 1, progLanguageCombo, panel1, "Software System");
        
        addComponent(7, 1, 1, 1, dbmsLabel, panel1, "Software System");
        addComponent(7, 2, 1, 1, dbmsCombo, panel1, "Software System");
        addComponent(8, 1, 1, 1, inputMethodLabel, panel1, "Software System");
        addComponent(8, 2, 1, 1, inputMethodCombo, panel1, "Software System");
        addComponent(9, 1, 1, 1, startProjMethodLabel, panel1, "Software System");
        addComponent(9, 2, 1, 1, startProjMethodField, panel1, "Software System");
        addComponent(10, 1, 1, 1, endProjMethodLabel, panel1, "Software System");
        addComponent(10, 2, 1, 1, endProjMethodField, panel1, "Software System");
        addComponent(11, 1, 1, 1, connectProjMethodLabel, panel1, "Software System");
        addComponent(11, 2, 1, 1, connectProjMethodField, panel1, "Software System");
        
        addComponent(12, 1, 1, 1, progLanguageIdLabel, panel1, "Software System");
        addComponent(12, 2, 1, 1, progLanguageIdField, panel1, "Software System");
        
        // Tab 2: Programming Language Details Information
        // Add the table panel
        panel2.add(progLangDetTablePanel, BorderLayout.WEST);
        umlSequenceDiagramTabPanel.addTab("Programming Language Details", panel2);
        
        // Tab 3: Operating System Information
        addComponent(1, 1, 1, 1, currentOperatingSystemLabel, panel3, "Computer Information");
        addComponent(1, 2, 1, 1, currentOperatingSystemField, panel3, "Computer Information");
        addComponent(2, 1, 1, 1, remoteOperatingSystemLabel, panel3, "Computer Information");
        addComponent(2, 2, 1, 1, remoteOperatingSystemField, panel3, "Computer Information");
        
        // Tab 4: UML Sequence Diagram Information
        // Add the components panel
        addComponent(1, 1, 1, 1, userNameLabel, panel4, "Logging Details");
        addComponent(1, 2, 1, 1, userNameCombo, panel4, "Logging Details");
        addComponent(1, 3, 1, 1, userIdLabel, panel4, "Logging Details");
        addComponent(1, 4, 1, 1, userIdField, panel4, "Logging Details");
        
        addComponent(2, 1, 1, 1, methodNameLabel, panel4, "Logging Details");
        addComponent(2, 2, 1, 1, methodNameCombo, panel4, "Logging Details");
        addComponent(2, 3, 1, 1, methodIdLabel, panel4, "Logging Details");
        addComponent(2, 4, 1, 1, methodIdField, panel4, "Logging Details");

        addComponent(3, 1, 1, 1, parameterNameLabel, panel4, "Logging Details");
        addComponent(3, 2, 1, 1, parameterNameCombo, panel4, "Logging Details");       
        addComponent(3, 3, 1, 1, parameterIdLabel, panel4, "Logging Details");
        addComponent(3, 4, 1, 1, parameterIdField, panel4, "Logging Details");
        
        addComponent(4, 3, 1, 1, parameterDataTypeIdLabel, panel4, "Logging Details");
        addComponent(4, 4, 1, 1, parameterDataTypeIdField, panel4, "Logging Details");

        addComponent(5, 1, 1, 1, attributeNameLabel, panel4, "Logging Details");
        addComponent(5, 2, 1, 1, attributeNameCombo, panel4, "Logging Details");        
        addComponent(5, 3, 1, 1, attributeIdLabel, panel4, "Logging Details");
        addComponent(5, 4, 1, 1, attributeIdField, panel4, "Logging Details");
        
        addComponent(6, 1, 1, 1, addBtn, panel4, "Logging Details");
        addComponent(6, 2, 1, 1, deleteBtn, panel4, "Logging Details");
        
        addComponent(7, 1, 1, 1, attributeName1Label, panel4, "Logging Details");
        addComponent(7, 2, 1, 1, attributeNameList, panel4, "Logging Details");
        
        addComponent(8, 1, 1, 1, logDirectoryLabel, panel4, "Logging Details");
        addComponent(8, 2, 1, 1, logDirectoryField, panel4, "Logging Details");
        addComponent(8, 3, 1, 1, openLogDirectoryBtn, panel4, "Logging Details");
        
        addComponent(9, 1, 1, 1, aOPDirectoryLabel, panel4, "AOP Details");
        addComponent(9, 2, 1, 1, aOPDirectoryField, panel4, "AOP Details");
        addComponent(9, 3, 1, 1, openAOPDirectoryBtn, panel4, "AOP Details");

        // Add the buttons panel
        createButtonsPanel();
        
        // Put everything together, using the content pane's BorderLayout.
        add(umlSequenceDiagramTabPanel, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
        
	}
	
	public void addComponent(int line, int column, int height, int width, 
			                 JComponent component, JComponent compnum, 
			                 String tabName) 
	{
       GridBagConstraints gbc = new GridBagConstraints();
       buttonPane.setBackground(Color.lightGray);
       gbc.gridy = line;
       gbc.gridx = column;
       gbc.gridheight = 1;
       gbc.gridwidth = 1;
       gbc.anchor = GridBagConstraints.WEST;
       gbc.insets = new Insets(1, 5, 3, 5);
       gbc.weightx = 1.0;
       gbc.weighty = 1.0;

       gbc.gridx++;
       gbc.gridheight = height;
       gbc.gridwidth = width;
       compnum.add(component, gbc);

       add(umlSequenceDiagramTabPanel);
       umlSequenceDiagramTabPanel.addTab(tabName, compnum);    
    }
	
   public void createButtonsPanel()
   {
      buttonPane.setLayout(new FlowLayout());
      buttonPane.setBackground(Color.lightGray);
      buttonPane.add(generateBtn, FlowLayout.LEFT);
      buttonPane.add(cleanBtn, FlowLayout.CENTER);
      buttonPane.add(cancelBtn, FlowLayout.RIGHT);
   }

   public void setUserFormListener(AOPFinalFileFormListener listener) 
   {
	   this.aOPFinalFileFormListener = listener;
   }
   
   // Method #4
   public void cleanFields(String databaseConfigFile) 
   {
      // Clean all the fields in the form
	  projectIdField.setText("");
	  projectNameField.setText("");
	  jarFileNameField.setText("");
	  projInputDirField.setText("");
	  projOutputDirField.setText("");
	  startProjMethodField.setText("");
	  endProjMethodField.setText("");
	  connectProjMethodField.setText("");
	  currentOperatingSystemField.setText("");
	  remoteOperatingSystemField.setText("");
	  assignDefaultValues(databaseConfigFile);
	  
	  logDirectoryField.setText("");
	  progLanguageIdField.setText("");
	  userIdField.setText("");
	  methodIdField.setText("");
	  parameterIdField.setText("");
	  parameterDataTypeIdField.setText("");
	  attributeIdField.setText("");
	  
   }
   
   public void assignDefaultValues(String databaseConfigFile)
   {
      String progLangSel="", OS="";
	   
	  // Load current Programming Languages from the FRL Database
	  loadProgLanguages(databaseConfigFile);
	  progLanguageCombo.setSelectedItem("Java");
	  
	  
	  // Load current DBMS from the FRL Database
	  loadDBMS(databaseConfigFile);
	  dbmsCombo.setSelectedItem("MySQL");
	  
	  // Load current Input Methods from the FRL Database
	  loadInputMethods(databaseConfigFile);
	  inputMethodCombo.setSelectedItem("Database");
	  
	  // Load the Details of the Programming Language that is selected
	  progLangSel = (String) progLanguageCombo.getSelectedItem();
      loadingProgLangDet(databaseConfigFile, progLangSel);
      
      OS = System.getProperty("os.name");
      currentOperatingSystemField.setText(OS);
      
      remoteOperatingSystemField.setText("");
      
   }	 

   
   public void enableFields()
   {
      Color color; 
		  
	  color = Color.LIGHT_GRAY; 
	   
	  // Do not allow to edit in these fields
      projectIdField.setEditable(false);
	  projectNameField.setEditable(false);
	  jarFileNameField.setEditable(false);
	  projInputDirField.setEditable(false);
	  projOutputDirField.setEditable(false);
	  startProjMethodField.setEditable(false);
	  endProjMethodField.setEditable(false);
	  connectProjMethodField.setEditable(false);
	  currentOperatingSystemField.setEditable(false);
	  remoteOperatingSystemField.setEditable(true);
	  logDirectoryField.setEditable(false);
	  progLanguageIdField.setEditable(false);

	  
      projectIdField.setBackground(color);
	  projectNameField.setBackground(color);
	  jarFileNameField.setBackground(color);
	  projInputDirField.setBackground(color);
	  projOutputDirField.setBackground(color);
	  startProjMethodField.setBackground(color);
	  endProjMethodField.setBackground(color);
	  connectProjMethodField.setBackground(color);
	  currentOperatingSystemField.setBackground(color);
	  remoteOperatingSystemField.setBackground(color);
	  logDirectoryField.setBackground(color);
	  progLanguageIdField.setBackground(color);

	  // Make all these fields not navigable
	  projectIdField.setFocusable(false);
	  projectNameField.setFocusable(false);
	  jarFileNameField.setFocusable(false);
	  projInputDirField.setFocusable(false);
	  projOutputDirField.setFocusable(false);
	  startProjMethodField.setFocusable(false);
	  endProjMethodField.setFocusable(false);
	  connectProjMethodField.setFocusable(false);
	  currentOperatingSystemField.setFocusable(false);
	  remoteOperatingSystemField.setFocusable(true);
	  logDirectoryField.setFocusable(false);
	  progLanguageIdField.setFocusable(false);

	  // Do not allow to edit these combos
	  progLanguageCombo.setEnabled(false);
	  progLanguageCombo.setBackground(color);
	  dbmsCombo.setEnabled(false);
	  dbmsCombo.setBackground(color);
	  inputMethodCombo.setEnabled(false);
	  inputMethodCombo.setBackground(color);
	  
	  // Make all these fields not navigable
	  progLanguageCombo.setFocusable(false);
	  dbmsCombo.setFocusable(false);
	  inputMethodCombo.setFocusable(false);
	    
	  // Make visible these open buttons
	  openLogDirectoryBtn.setEnabled(true);
	  
	  // Make visible these open buttons
	  openAOPDirectoryBtn.setEnabled(true);
	  
   }
   
   public void disableFields()
   {
      Color color; 
		  
	  color = Color.LIGHT_GRAY; 
		   
      // Do not allow to edit in these fields
      projectIdField.setEditable(false);
	  projectNameField.setEditable(false);
	  jarFileNameField.setEditable(false);
	  projInputDirField.setEditable(false);
	  projOutputDirField.setEditable(false);
	  startProjMethodField.setEditable(false);
	  endProjMethodField.setEditable(false);
	  connectProjMethodField.setEditable(false);
	  currentOperatingSystemField.setEditable(false);
	  remoteOperatingSystemField.setEditable(true);
	  logDirectoryField.setEditable(false);
	  progLanguageIdField.setEditable(false);

		  
      projectIdField.setBackground(color);
	  projectNameField.setBackground(color);
	  jarFileNameField.setBackground(color);
	  projInputDirField.setBackground(color);
	  projOutputDirField.setBackground(color);
	  startProjMethodField.setBackground(color);
	  endProjMethodField.setBackground(color);
	  connectProjMethodField.setBackground(color);
	  currentOperatingSystemField.setBackground(color);
	  remoteOperatingSystemField.setBackground(color);
	  logDirectoryField.setBackground(color);
	  progLanguageIdField.setBackground(color);

	  // Make all these fields not navigable
	  projectIdField.setFocusable(false);
	  projectNameField.setFocusable(false);
	  jarFileNameField.setFocusable(false);
	  projInputDirField.setFocusable(false);
	  projOutputDirField.setFocusable(false);
	  startProjMethodField.setFocusable(false);
	  endProjMethodField.setFocusable(false);
	  connectProjMethodField.setFocusable(false);
	  currentOperatingSystemField.setFocusable(false);
	  remoteOperatingSystemField.setFocusable(true);
	  logDirectoryField.setFocusable(false);
	  progLanguageIdField.setFocusable(false);

	  // Do not allow to edit these combos
	  progLanguageCombo.setEnabled(false);
	  progLanguageCombo.setBackground(color);
	  dbmsCombo.setEnabled(false);
	  dbmsCombo.setBackground(color);
	  inputMethodCombo.setEnabled(false);
	  inputMethodCombo.setBackground(color);
		  
	  // Make all these fields not navigable
	  progLanguageCombo.setFocusable(false);
	  dbmsCombo.setFocusable(false);
	  inputMethodCombo.setFocusable(false);
		    
	  // Make not visible these open buttons
	  openLogDirectoryBtn.setEnabled(false);
		  
	  // Make all these fields navigable
	  openLogDirectoryBtn.setFocusable(false);
	  
	  // Make not visible these open buttons
	  openAOPDirectoryBtn.setEnabled(false);
		  
	  // Make all these fields navigable
	  openAOPDirectoryBtn.setFocusable(false);
   }
   
   public void enableGenerateButton()
   {
      // Make visible the following buttons
	  generateBtn.setVisible(true);
		  
	  // Make all these fields not navigable
	  generateBtn.setFocusable(true);
	  
	  cleanBtn.setFocusable(true);
	  cancelBtn.setFocusable(true);
   }
   
   public void enableDeleteButton()
   {
      // Make visible the following buttons
	  generateBtn.setVisible(false);
		  
	  // Make all these fields not navigable
	  generateBtn.setFocusable(false);
	  
	  cleanBtn.setFocusable(true);
	  cancelBtn.setFocusable(true);
   }
   
   public void enableViewButton()
   {
      // Make visible the following buttons
	  generateBtn.setVisible(false);
		  
	  // Make all these fields not navigable
	  generateBtn.setFocusable(false);
	  
	  cleanBtn.setFocusable(true);
	  cancelBtn.setFocusable(true);
   }
  	
  public void loadProgLanguages(String databaseConfigFile) 
  {
	  String errorMessage1 = "";
	  
     // Delete all the current values in the Combo
	  progLanguageCombo.removeAllItems();
	      
	  try 
	  {
	     // Obtain an ArrayList of all the current Programming Languages from the FRL Database
		 ArrayList<ProgrammingLanguageHeader> pL = aopFinalFileController.loadProgLanguagesHeader(databaseConfigFile);
		     
		 // Read all the current Programming Language ArrayList
		 for (int i = 0; i < pL.size(); i++) 
		 {
		    String progLangName = pL.get(i).getName();
			    
			// Assign an Programming Language to the Programming Language Combo
		    progLanguageCombo.addItem(progLangName);
	      }
		     
	   } 
      catch (Exception e) 
	   {
	      errorMessage1 = e.getMessage();
		  JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Programming Languages in the Combo.", JOptionPane.ERROR_MESSAGE);	
	   }
  }
  
  public void loadDBMS(String databaseConfigFile) 
  {
     String errorMessage1 = "";      
     // Delete all the current values in the Combo
	  dbmsCombo.removeAllItems();
	  
	  try 
	  {
	     // Obtain an ArrayList of all the current DBMS from the FRL Database
		 ArrayList<DBMS> dB = aopFinalFileController.loadDBMS(databaseConfigFile);
		     
		 // Read all the current DBMS ArrayList
		 for (int i = 0; i < dB.size(); i++) 
		 {
		    String dbmsName = dB.get(i).getName();
			    
			// Assign an DBMS to the DBMS Combo
		    dbmsCombo.addItem(dbmsName);
	      }
		 
	   } 
      catch (Exception e) 
	   {
	      errorMessage1 = e.getMessage();
		  JOptionPane.showMessageDialog(this, errorMessage1, "Loading the DBMS in the Combo.", JOptionPane.ERROR_MESSAGE);	
	   }
  }
  
  public void loadInputMethods(String databaseConfigFile) 
  {
     String errorMessage1 = "";
     
     // Delete all the current values in the Combo
	  inputMethodCombo.removeAllItems();
	  
	  try 
	  {
	     // Obtain an ArrayList of all the current Input Methods from the FRL Database
		 ArrayList<InputMethod> iM = aopFinalFileController.loadInputMethods(databaseConfigFile);
		     
		 // Read all the current Input Method ArrayList
		 for (int i = 0; i < iM.size(); i++) 
		 {
		    String inputMethodName = iM.get(i).getName();
			    
			// Assign an DBMS to the Input Method Combo
		    inputMethodCombo.addItem(inputMethodName);
	      }
		     
	   } 
      catch (Exception e) 
	   {
	      errorMessage1 = e.getMessage();
		  JOptionPane.showMessageDialog(this, errorMessage1, "Loading the DBMS in the Combo.", JOptionPane.ERROR_MESSAGE);	

	   }
  }
  
  // Method #4
  public void loadingProgLangDet(String databaseConfigFile, String progLang)
  {
     String errorMessage="";	   

	  try 
	  {
	     aopFinalFileController.loadProgLangDet(databaseConfigFile, progLang);
	  } 
	  catch (Exception e) 
	  {
        errorMessage = e.getMessage();
	  }
	  
	  progLangDetTablePanel.refresh();
	  if(errorMessage.isEmpty() == false)
          JOptionPane.showMessageDialog(this, errorMessage, "Loading the Programing Languages into the Combo.", JOptionPane.ERROR_MESSAGE);
  }
  
  public int loadUsers(String databaseConfigFile, int projectId) 
  {
     String errorMessage1="", userName="";
     int i=0, userId=0;
     ArrayList<UserSequenceDiagram> userList;
     
     // Delete all the current values in the Combo
	 userNameCombo.removeAllItems();
	 userIdField.setText("");
	  
	 try 
	 {
	    // Obtain an ArrayList from the FRL Database
		userList = aopFinalFileController.loadUsers(databaseConfigFile, projectId);
		     
		// Read all the values from the ArrayList
		for (i = 0; i < userList.size(); i++) 
		{
		   userId   = userList.get(i).getUserIdentifier();
		   userName = userList.get(i).getUserName();
			    
		   // Assign the values to the Combobox
		   userNameCombo.addItem(userName);
		    
		   if(i == 0)
		   { 	
			  userIdField.setText(String.valueOf(userId));
		   }   
	    }
		     
	  } 
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Users with Annotations in the Combo.", JOptionPane.ERROR_MESSAGE);	

	  }
      return userId;	 
  }    
  
  public int getUserId(String databaseConfigFile, int projectId, String userName) 
  {
     String errorMessage1="", userIdStr="";
     int userId=0;
     
	 userIdField.setText("");
     
	 try 
	 {
	    // Get the user
		userId    = aopFinalFileController.getUserId(databaseConfigFile, projectId, userName);
		userIdStr = String.valueOf(userId);
		
		userIdField.setText(userIdStr);

	 } 
     catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();
	    JOptionPane.showMessageDialog(this, errorMessage1, "Loading the User Identifier.", JOptionPane.ERROR_MESSAGE);	
	 }
     return userId;	 
  }  
   
  public int loadMethods(String databaseConfigFile, int projectId, int userId) 
  {
     String errorMessage1="", methodFullName="";
     int i=0, methodId=0;
     ArrayList<MethodSequenceDiagram> methodList;
     
     // Delete all the current values in the Combo
	 methodNameCombo.removeAllItems();
	 methodIdField.setText("");
	  
	 try 
	 {
	    // Obtain an ArrayList from the FRL Database
		methodList = aopFinalFileController.loadMethods(databaseConfigFile, projectId, userId);
		     
		// Read all the values from the ArrayList
		for (i = 0; i < methodList.size(); i++) 
		{
		   methodId       = methodList.get(i).getMethodIdentifier();	
		   methodFullName = methodList.get(i).getFullName();
			    
		   // Assign the values to the Combobox
		   methodNameCombo.addItem(methodFullName);
		    
		   if(i == 0)
		   { 	
			  methodIdField.setText(String.valueOf(methodId));
		   }   
	    }
		     
	  } 
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Methods with Annotations in the Combo.", JOptionPane.ERROR_MESSAGE);	

	  }
	  return methodId; 
  }    
  
  public int getMethodId(String databaseConfigFile, int projectId, int userId, String methodName) 
  {
     String errorMessage1="", methodIdStr="";
     int methodId=0;
     
     methodIdField.setText("");
     
	 try 
	 {
	    // Get the Method Id
		methodId    = aopFinalFileController.getMethodId(databaseConfigFile, projectId, userId, methodName);
		methodIdStr = String.valueOf(methodId);
		
		methodIdField.setText(methodIdStr);

	 } 
     catch (Exception e1) 
	 {
	    errorMessage1 = e1.getMessage();
	    JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Method Identifier.", JOptionPane.ERROR_MESSAGE);	
	 }
     return methodId;	 
  }  
  
 public ParameterDataSequenceDiagram loadParameters(String databaseConfigFile, int projectId, int progrLanguageId, int methodId) 
 {
    String errorMessage1="", parameterFullName="";
    int i=0, parameterId=0, parameterDataTypeId=0;
    ArrayList<ParameterSequenceDiagram> parameterList;
    ParameterDataSequenceDiagram parameterDataSequenceDiagramRecord = null;
    
    // Delete all the current values in the Combo
	parameterNameCombo.removeAllItems();
	parameterIdField.setText("");
	parameterDataTypeIdField.setText("");
	  
	try 
	{
	   // Obtain an ArrayList from the FRL Database
	   parameterList = aopFinalFileController.loadParameters(databaseConfigFile, projectId, progrLanguageId, methodId);
		     
	   // Read all the values from the ArrayList
	   for (i = 0; i < parameterList.size(); i++) 
	   {
		  parameterId         = parameterList.get(i).getParameterIdentifier();
	      parameterDataTypeId = parameterList.get(i).getDataTypeIdentifier();
	      parameterFullName   = parameterList.get(i).getParameterFullName();
			    
		  // Assign the values to the Combobox
		  parameterNameCombo.addItem(parameterFullName);
		    
		  if(i == 0)
		  { 	
			 parameterIdField.setText(String.valueOf(parameterId));
			 parameterDataTypeIdField.setText(String.valueOf(parameterDataTypeId));
			 
			 parameterDataSequenceDiagramRecord = new ParameterDataSequenceDiagram(parameterId, parameterDataTypeId, parameterFullName);
		  }   
	   }
		     
	} 
    catch (Exception e1) 
	{
	   errorMessage1 = e1.getMessage();
	   JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Parameters with Annotations in the Combo.", JOptionPane.ERROR_MESSAGE);	

	}
	
	return parameterDataSequenceDiagramRecord; 
 } 
 
 public ParameterDataSequenceDiagram getParameterDetails(String databaseConfigFile, int projectId, int progrLanguageId, int methodId, String parameterFullName) 
 {
    String errorMessage1="";
    int parameterId=0, parameterDataTypeId=0;
    ParameterDataSequenceDiagram parameterDataSequenceDiagramRecord = null;
    
	parameterIdField.setText("");
	parameterDataTypeIdField.setText("");
    
	try 
	{
	   // Get Parameter Details
	   parameterDataSequenceDiagramRecord = aopFinalFileController.getParameterDetails(databaseConfigFile, projectId, progrLanguageId, methodId, parameterFullName);
	} 
    catch (Exception e1) 
	{
	   errorMessage1 = e1.getMessage();
	   JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Parameter Details.", JOptionPane.ERROR_MESSAGE);	
	}
	
	if(parameterDataSequenceDiagramRecord != null) 
	{
	   parameterId         = parameterDataSequenceDiagramRecord.getParameterId();
	   parameterDataTypeId = parameterDataSequenceDiagramRecord.getParameterDataTypeId();
	   
	   parameterIdField.setText(String.valueOf(parameterId));
	   parameterDataTypeIdField.setText(String.valueOf(parameterDataTypeId));
	   
	}
	   
    return parameterDataSequenceDiagramRecord;	 
 } 
 
 public int loadAttributes(String databaseConfigFile, int projectId, int progrLanguageId, int methodId, int parameterId, int parameterDataTypeId) 
 {
    String errorMessage1="", attributeFullName="";
    int i=0, attributeId=0;
    ArrayList<AttributeSequenceDiagram> attributeList;
   
    // Delete all the current values in the Combo
	attributeNameCombo.removeAllItems();
	attributeIdField.setText("");
	  
	try 
	{
	   // Obtain an ArrayList from the FRL Database
	   attributeList = aopFinalFileController.loadAttributes(databaseConfigFile, projectId, progrLanguageId, methodId, 
                                                             parameterId, parameterDataTypeId);
		     
	   // Read all the values from the ArrayList
	   for (i = 0; i < attributeList.size(); i++) 
	   {
	      attributeId       = attributeList.get(i).getAttributeIdentifier();
		  attributeFullName = attributeList.get(i).getAttributeName();
			    
		  // Assign the values to the Combobox
		  attributeNameCombo.addItem(attributeFullName);
		    
		  if(i == 0)
		  { 	
		     attributeIdField.setText(String.valueOf(attributeId));
		  }   
	   }
		     
	} 
    catch (Exception e1) 
	{
	   errorMessage1 = e1.getMessage();
	   JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Attributes with Annotations in the Combo.", JOptionPane.ERROR_MESSAGE);	
	}
	
	return attributeId; 
	
   } 
 
   public int getAttributeId(String databaseConfigFile, int projectId, int progrLanguageId, int methodId, int parameterId, 
                             int parameterDataTypeId, String attributeFullName) 
   {
      String errorMessage1="", attributeIdStr="", attributeName="";
      String parts[];
      int attributeId=0;
    
      attributeIdField.setText("");
      
      if(attributeFullName != null && !attributeFullName.trim().isEmpty()) 
      { 	  
         parts         = attributeFullName.split("\\s+");   
         attributeName = parts[1];
      }
      
	  try 
	  {
	     // Get the Attribute Id
		 attributeId    = aopFinalFileController.getAttributeId(databaseConfigFile, projectId, progrLanguageId, methodId, 
				                                                parameterId, parameterDataTypeId, attributeName);
		 attributeIdStr = String.valueOf(attributeId);
		 attributeIdField.setText(attributeIdStr);
	  } 
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Attribute Identifier.", JOptionPane.ERROR_MESSAGE);	
	  }
      return attributeId;	 
   } 
   
   public void getProperties(String featuresConfigFile)
   {
      String errorMessage=""; 	   
      Properties prop = new Properties();
	  InputStream input;
	    
	  try
	  {
	     input = new FileInputStream(featuresConfigFile);
	         
	     // Load the properties file
	     prop.load(input);

	     // Get the property values
	     annotationFilePrefix  = prop.getProperty("annotationFilePrefix");
	     annotationFile        = prop.getProperty("annotationFile");
	     findWhiteSpaces       = prop.getProperty("findWhiteSpaces");
	     validSpecialCharacter = prop.getProperty("validSpecialCharacter");
	     valueDelimiter        = prop.getProperty("valueDelimiter");
	     confPropertyName1     = prop.getProperty("confPropertyName1");
	     startCallMethod       = prop.getProperty("startCallMethod");
	     endCallMethod1        = prop.getProperty("endCallMethod1");
	     callMethodDelimiter   = prop.getProperty("callMethodDelimiter");
	     assignValueDelimiter  = prop.getProperty("assignValueDelimiter");
	     guiLibDelimiter       = prop.getProperty("guiLibDelimiter");
	     findString            = prop.getProperty("findString");
	     textFileNameExt3      = prop.getProperty("textFileNameExt3");
	  } 
	  catch (IOException e1) 
	  {
	     errorMessage = e1.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Loading the Configuration Properties.", JOptionPane.ERROR_MESSAGE);	
	  }
   }
}