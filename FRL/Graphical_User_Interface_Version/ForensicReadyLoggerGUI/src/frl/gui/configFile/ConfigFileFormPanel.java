package frl.gui.configFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import frl.controller.generateAspectOrientedFiles.AOPFileController;
import frl.gui.toolBar.ToolBar;
import frl.model.generateAspectOrientedFiles.DBMS;
import frl.model.generateAspectOrientedFiles.InputMethod;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageHeader;

public class ConfigFileFormPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
    // Define Labels //
	// Tab 1
	private JLabel idLabel;
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
    private JLabel connectProjMethodReturnValueLabel;
        
    private JLabel operatingSystemLabel;

    // Define TextFields //
    public JTextField idField;
    public JTextField projectNameField;
    public JTextField jarFileNameField;
    public JTextField projInputDirField;
    public JTextField projOutputDirField;
    public JTextField startProjMethodField;
    public JTextField endProjMethodField;
    public JTextField connectProjMethodField;
    public JTextField connectProjMethodReturnValueField;
    public JTextField operatingSystemField;
    
    // Define ComboBoxes //
    public JComboBox<String> progLanguageCombo;
    public JComboBox<String> dbmsCombo;
    public JComboBox<String> inputMethodCombo;
    
    // Define Buttons //
	public JButton openProjectNameBtn;
	public JButton openJarFileNameBtn;
	public JButton openProjInputDirBtn;
	public JButton openProjOutputDirBtn;
	
	public JButton saveBtn;
	public JButton deleteBtn;
	public JButton cleanBtn;
	public JButton cancelBtn;

    public JPanel panel1 = new JPanel();
    public JPanel panel2 = new JPanel();
    public JPanel panel3 = new JPanel();	
    public JPanel buttonPane = new JPanel();
    public JTabbedPane configTabPanel = new JTabbedPane();

    // Controller
    private AOPFileController aopFileController;
    
    // Listener //
    private ConfigFileFormListener configFileFormListener;
    
    // Table //
    private ProgLangDetTablePanel progLangDetTablePanel;

	public ConfigFileFormPanel(String databaseConfigFile, String featuresConfigFile)
	{
		Dimension dim = getPreferredSize();
		
		// Set the Size of the Form
		dim.height = 500;
		dim.width = 1000;
		setPreferredSize(dim);
		
		// Define an object for the Controller Class
		aopFileController = new AOPFileController();
		
		// Define an object for the Table Panel Class
        progLangDetTablePanel = new ProgLangDetTablePanel();
        
        // Define an object for the ProgLangDetTablePanel Class
        progLangDetTablePanel.setData(aopFileController.getProgLangDet());
		
		// Create the Fields //
		idLabel = new JLabel("Id: ");
		idField = new JTextField(10); 
		 
		// Make the idField not editable
		Color color = Color.LIGHT_GRAY;
		idField.setBackground(color);
	    idField.setToolTipText("Identifier of the Project Application");
		
	    // Set the color of the Form
	    this.setBackground(Color.lightGray);
	     
	    projectNameLabel = new JLabel("Project Name: ");
	    projectNameField = new JTextField(40);
	    projectNameField.setBackground(color);
	    projectNameField.setToolTipText("Project Application Name");
	    
		openProjectNameBtn = new JButton("Open Project");
		openProjectNameBtn.setToolTipText("Opens a Project Application from the File System");
	    
	    jarFileNameLabel = new JLabel("JAR File Name: ");
	    jarFileNameField = new JTextField(40);
	    jarFileNameField.setBackground(color);
	    jarFileNameField.setToolTipText("Java Archive File Name");
	    
		openJarFileNameBtn = new JButton("Open JAR File");
		openJarFileNameBtn.setToolTipText("Opens a Java Archive File from the File System");
	    
	    projInputDirLabel = new JLabel("Input Directory: ");
	    projInputDirField = new JTextField(40);
	    projInputDirField.setBackground(color);
	    projInputDirField.setToolTipText("Project Input Folder");
	    
	    openProjInputDirBtn = new JButton("Open Input Folder");
	    openProjInputDirBtn.setToolTipText("Opens an Input Folder from the File System");
	 
	    projOutputDirLabel = new JLabel("Output Directory: ");
	    projOutputDirField = new JTextField(40);
	    projOutputDirField.setBackground(color);
	    projOutputDirField.setToolTipText("Project Output Folder");
	    
	    openProjOutputDirBtn = new JButton("Open Output Folder");
	    openProjOutputDirBtn.setToolTipText("Opens an Output Folder from the File System");
	    
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
	    
	    connectProjMethodReturnValueLabel = new JLabel("Connect Method Return Value: ");
	    connectProjMethodReturnValueField = new JTextField(40);
	    connectProjMethodReturnValueField.setBackground(color);
	    connectProjMethodReturnValueField.setToolTipText("Project Connect Method Return Value");
	    	    
		color = Color.LIGHT_GRAY;
	    operatingSystemLabel = new JLabel("Operating System: ");
	    operatingSystemField = new JTextField(40);
	    operatingSystemField.setBackground(color);
	    operatingSystemField.setToolTipText("Operating System");
	    
	    // JButtons Objects //
		saveBtn = new JButton("Save");
		saveBtn.setToolTipText("Stores the details of the Configuration of the Project Application in the Forensic-Ready Logger System");
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setToolTipText("Eliminates the Configuration of the Project Application from the Forensic-Ready Logger System");

		cleanBtn = new JButton("Clean");
		cleanBtn.setToolTipText("Cleans all the fields in the Project Application Configuration Information Form");

		cancelBtn = new JButton("Cancel");
		cancelBtn.setToolTipText("Closes the Project Application Configuration Information Form");

		// Declare the Listeners //
		
		// Listener for the Open Project Name Button
		openProjectNameBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConfigFileFormEvent ev;
				String idS1, projectName1;
				
				// Obtain the value of the Objects //
				idS1         = idField.getText();
			    projectName1 = projectNameField.getText();
				
				// Validate if the Id is not null
				if(idS1 != null && !idS1.isEmpty()) 
				{ 
				   int id1 = Integer.parseInt(idS1);
				   // Calling the constructor # 1 for the ConfigFileForm Event Class
				   ev = new ConfigFileFormEvent(this, id1, "projectName", projectName1);
				}
				else
				{
				   // Calling the constructor # 2 for the ConfigFile Form Event Class
				   ev = new ConfigFileFormEvent(this, "projectName", projectName1);
				}
				
				if(configFileFormListener != null) 
				{
				   configFileFormListener.configFileFormOpenProjectNameEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the Open JAR File Button
		openJarFileNameBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConfigFileFormEvent ev;
				String idS1, jarFileName1;
				
				// Obtain the value of the Objects //
				idS1         = idField.getText();
			    jarFileName1 = jarFileNameField.getText();
				
				// Validate if the Id is not null
				if(idS1 != null && !idS1.isEmpty()) 
				{ 
				   int id1 = Integer.parseInt(idS1);
				   // Calling the constructor # 1 for the ConfigFileForm Event Class
				   ev = new ConfigFileFormEvent(this, id1, "jarFileName", jarFileName1);
				}
				else
				{
				   // Calling the constructor # 2 for the ConfigFile Form Event Class
				   ev = new ConfigFileFormEvent(this, "jarFileName", jarFileName1);
				}
				
				if(configFileFormListener != null) 
				{
				   configFileFormListener.configFileFormOpenJarFileEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the Open Input Directory Button
		openProjInputDirBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConfigFileFormEvent ev;
				String idS1, projInputDir1;
				
				// Obtain the value of the Objects //
				idS1          = idField.getText();
			    projInputDir1 = projInputDirField.getText();
				
				// Validate if the Id is not null
				if(idS1 != null && !idS1.isEmpty()) 
				{ 
				   int id1 = Integer.parseInt(idS1);
				   // Calling the constructor # 1 for the ConfigFileForm Event Class
				   ev = new ConfigFileFormEvent(this, id1, "projInputDir", projInputDir1);
				}
				else
				{
				   // Calling the constructor # 2 for the ConfigFile Form Event Class
				   ev = new ConfigFileFormEvent(this, "projInputDir", projInputDir1);
				}
				
				if(configFileFormListener != null) 
				{
				   configFileFormListener.configFileFormOpenProjInputDirEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the Open Output Directory Button
		openProjOutputDirBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConfigFileFormEvent ev;
				String idS1, projOutputDir1;
				
				// Obtain the value of the Objects //
				idS1           = idField.getText();
			    projOutputDir1 = projOutputDirField.getText();
				
				// Validate if the Id is not null
				if(idS1 != null && !idS1.isEmpty()) 
				{ 
				   int id1 = Integer.parseInt(idS1);
				   // Calling the constructor # 1 for the ConfigFileForm Event Class
				   ev = new ConfigFileFormEvent(this, id1, "projOutputDir", projOutputDir1);
				}
				else
				{
				   // Calling the constructor # 2 for the ConfigFile Form Event Class
				   ev = new ConfigFileFormEvent(this, "projOutputDir", projOutputDir1);
				}
				
				if(configFileFormListener != null) 
				{
				   configFileFormListener.configFileFormOpenProjOutputDirEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the progLanguageCombo
		progLanguageCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConfigFileFormEvent ev;
				String idS1="", progLanguage1="";
				
				// Obtain the value of the Objects //
				idS1          = idField.getText();
				progLanguage1 = (String) progLanguageCombo.getSelectedItem();  
				
				// Validate if the Id is not null
				if(idS1 != null && !idS1.isEmpty()) 
				{ 
				   int id1 = Integer.parseInt(idS1);
				   // Calling the constructor # 1 for the ConfigFileForm Event Class
				   ev = new ConfigFileFormEvent(this, id1, "programmingLanguage", progLanguage1);
				}
				else
				{
				   // Calling the constructor # 2 for the ConfigFile Form Event Class
				   ev = new ConfigFileFormEvent(this, "programmingLanguage", progLanguage1);
				}
				
				if(configFileFormListener != null) 
				{
				   configFileFormListener.configFileFormProgLanguageComboEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the Save Button
		saveBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConfigFileFormEvent ev;
				boolean configFileSaved = false;
				int id, rowCount;
				
				String idS ="", projectName="", jarFileName="", 
				       projInputDir="", projOutputDir="", progLanguage="", dbms="",
			           inputMethod="", startProjMethod="", endProjMethod="", connectProjMethod="",
			           connectProjMethodReturnValue="", operatingSystem="";
				String newLine1="", newLine2="", objectOrientedDelimiter1="", startMethod="", endMethod="", 
					   startParameters="", endParameters="", singleLineComment="", endStatementDelimiter="", 
					   startClassList="", endClassList="", bluePrintObject1="", bluePrintObject2="", 
					   initializeObjectName="", initializeObjectMethod="", subProgramObject="", 
					   methodNameException="", printMessage="", startLineNum="", startArray="", endArray="",
					   classDataType="", returnValueMethod="", nonPrimitiveValue1="", 
					   nonPrimitiveValue2="", nonPrimitiveValue3="",
					   nonPrimitiveValue4="", startClassDef="", endClassDef="";
				
				// Obtain the value of the Objects //
				idS                = idField.getText();
			    projectName        = projectNameField.getText();
			    jarFileName        = jarFileNameField.getText();
			    projInputDir       = projInputDirField.getText();
			    projOutputDir      = projOutputDirField.getText();
			    progLanguage       = (String) progLanguageCombo.getSelectedItem();  
			    dbms               = (String) dbmsCombo.getSelectedItem();  
			    inputMethod        = (String) inputMethodCombo.getSelectedItem();  
			    startProjMethod    = startProjMethodField.getText();
			    endProjMethod      = endProjMethodField.getText();
			    connectProjMethod  = connectProjMethodField.getText();
			    connectProjMethodReturnValue = connectProjMethodReturnValueField.getText();
			    operatingSystem    = operatingSystemField.getText();
			    
			    // Get the count of the records of the programming language detail table
			    rowCount = progLangDetTablePanel.getRowCount();
			    
			    if(rowCount > 0)
			    {	
			       newLine1                 = progLangDetTablePanel.getRowColSel(0, 2);
			       newLine2                 = progLangDetTablePanel.getRowColSel(1, 2);
			       objectOrientedDelimiter1 = progLangDetTablePanel.getRowColSel(2, 2);
			       startMethod              = progLangDetTablePanel.getRowColSel(3, 2);
			       endMethod                = progLangDetTablePanel.getRowColSel(4, 2);
			       startParameters          = progLangDetTablePanel.getRowColSel(5, 2);
			       endParameters            = progLangDetTablePanel.getRowColSel(6, 2);
			       singleLineComment        = progLangDetTablePanel.getRowColSel(7, 2);
			       endStatementDelimiter    = progLangDetTablePanel.getRowColSel(8, 2);
			       startClassList           = progLangDetTablePanel.getRowColSel(9, 2);
			       endClassList             = progLangDetTablePanel.getRowColSel(10, 2);
			       bluePrintObject1         = progLangDetTablePanel.getRowColSel(11, 2);
			       bluePrintObject2         = progLangDetTablePanel.getRowColSel(12, 2);
			       initializeObjectName     = progLangDetTablePanel.getRowColSel(13, 2);
			       initializeObjectMethod   = progLangDetTablePanel.getRowColSel(14, 2);
			       subProgramObject         = progLangDetTablePanel.getRowColSel(15, 2);
			       methodNameException      = progLangDetTablePanel.getRowColSel(16, 2);
			       printMessage             = progLangDetTablePanel.getRowColSel(17, 2);
			       startLineNum             = progLangDetTablePanel.getRowColSel(18, 2);
			       startArray               = progLangDetTablePanel.getRowColSel(19, 2);
			       endArray                 = progLangDetTablePanel.getRowColSel(20, 2);
			       classDataType            = progLangDetTablePanel.getRowColSel(21, 2);
			       returnValueMethod        = progLangDetTablePanel.getRowColSel(22, 2);
			       nonPrimitiveValue1       = progLangDetTablePanel.getRowColSel(23, 2);
				   nonPrimitiveValue2       = progLangDetTablePanel.getRowColSel(24, 2); 
				   nonPrimitiveValue3       = progLangDetTablePanel.getRowColSel(25, 2);
				   nonPrimitiveValue4       = progLangDetTablePanel.getRowColSel(26, 2); 
				   startClassDef            = progLangDetTablePanel.getRowColSel(27, 2);
				   endClassDef              = progLangDetTablePanel.getRowColSel(28, 2);
			    }
			    			    
				// Validate if the Id is not null
				if( (idS != null && !idS.isEmpty())) 
				{ 
				   id = Integer.parseInt(idS);
				   // Calling the constructor # 1 for the ConfigFileForm Event Class
				   ev = new ConfigFileFormEvent(this, id, featuresConfigFile, databaseConfigFile,
						                        projectName, 
						                        jarFileName, projInputDir, projOutputDir, 
						                        progLanguage, dbms, inputMethod, 
				                                startProjMethod, endProjMethod, connectProjMethod,
				                                connectProjMethodReturnValue,
				                                operatingSystem, newLine1, newLine2, objectOrientedDelimiter1, 
				                                startMethod, endMethod, startParameters, endParameters, 
				                                singleLineComment, endStatementDelimiter, startClassList, 
				                                endClassList, bluePrintObject1, bluePrintObject2, 
				                                initializeObjectName, initializeObjectMethod, subProgramObject, 
				                                methodNameException, printMessage, startLineNum, startArray, endArray,
				                                classDataType, returnValueMethod, nonPrimitiveValue1, nonPrimitiveValue2,  
				                                nonPrimitiveValue3,nonPrimitiveValue4, startClassDef, endClassDef);
				}
				else
				{
				   // Calling the constructor # 2 for the ConfigFile Form Event Class
				   ev = new ConfigFileFormEvent(this, featuresConfigFile, databaseConfigFile,
						                        projectName, 
						                        jarFileName, projInputDir, projOutputDir, 
						                        progLanguage, dbms, inputMethod, 
                                                startProjMethod, endProjMethod, connectProjMethod,
                                                connectProjMethodReturnValue,
                                                operatingSystem, newLine1, newLine2, objectOrientedDelimiter1, 
				                                startMethod, endMethod, startParameters, endParameters, 
				                                singleLineComment, endStatementDelimiter, startClassList, 
				                                endClassList, bluePrintObject1, bluePrintObject2, 
				                                initializeObjectName, initializeObjectMethod, subProgramObject, 
				                                methodNameException, printMessage, startLineNum, startArray, endArray,
				                                classDataType,returnValueMethod, nonPrimitiveValue1, nonPrimitiveValue2,  
				                                nonPrimitiveValue3,nonPrimitiveValue4, startClassDef, endClassDef);
				}
				
				if(configFileFormListener != null) 
				{
				   configFileSaved = configFileFormListener.configFileFormSaveEventOccurred(ev);
			       
				   // Validate that the user has been saved
				   if (configFileSaved == true) 
				   {
			          // Clear all the fields except the text area
				      cleanFields(databaseConfigFile);
				   }
				   
		       }	
			}
			
		});

		// Listener for the Delete Button
		deleteBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConfigFileFormEvent ev = null;
				boolean configFileDeleted=false;
				String idS ="", projectName="", projOutputDir="";
				int id;
					
			    // Obtain the value of the Objects //
			    idS           = idField.getText();
				projectName   = projectNameField.getText();
				projOutputDir = projOutputDirField.getText();
				
				// Validate if the Id is not null
				if(idS != null && !idS.isEmpty()) 
				{ 
				   id = Integer.parseInt(idS);
				   
				   // Calling the constructor # 1 for the ConfigFileForm Event Class
				   ev = new ConfigFileFormEvent(this, featuresConfigFile, databaseConfigFile,
						                        id, projectName, projOutputDir);
				}
				
				if(configFileFormListener != null) 
				{
				   configFileDeleted = configFileFormListener.configFileFormDeleteEventOccurred(ev);
				}
				
				if (configFileDeleted == true) 
				{
			       // Clear all the fields except the text area
			   	   cleanFields(databaseConfigFile);
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
			   
			   if(configFileFormListener != null) 
			   {
			      configFileFormListener.configFileFormCancelEventOccurred();
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
        
        // Tab 1: Software System Information
        // Add the components panel
        addComponent(1, 1, 1, 1, idLabel, panel1, "Software System");
        addComponent(1, 2, 1, 1, idField, panel1, "Software System");
        addComponent(2, 1, 1, 1, projectNameLabel, panel1, "Software System");
        addComponent(2, 2, 1, 1, projectNameField, panel1, "Software System");
        addComponent(2, 3, 1, 1, openProjectNameBtn, panel1, "Software System");
        addComponent(3, 1, 1, 1, jarFileNameLabel, panel1, "Software System");
        addComponent(3, 2, 1, 1, jarFileNameField, panel1, "Software System");
        addComponent(3, 3, 1, 1, openJarFileNameBtn, panel1, "Software System");
        addComponent(4, 1, 1, 1, projInputDirLabel, panel1, "Software System");
        addComponent(4, 2, 1, 1, projInputDirField, panel1, "Software System");
        addComponent(4, 3, 1, 1, openProjInputDirBtn, panel1, "Software System");
        addComponent(5, 1, 1, 1, projOutputDirLabel, panel1, "Software System");
        addComponent(5, 2, 1, 1, projOutputDirField, panel1, "Software System");
        addComponent(5, 3, 1, 1, openProjOutputDirBtn, panel1, "Software System");
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
        addComponent(12, 1, 1, 1, connectProjMethodReturnValueLabel, panel1, "Software System");
        addComponent(12, 2, 1, 1, connectProjMethodReturnValueField, panel1, "Software System");
        
        // Tab 2: Programming Language Details Information
        // Add the table panel
        panel2.add(progLangDetTablePanel, BorderLayout.WEST);
        configTabPanel.addTab("Programming Language Details", panel2);
        
        // Tab 3: Operating System Information
        addComponent(1, 1, 1, 1, operatingSystemLabel, panel3, "Computer Information");
        addComponent(1, 2, 1, 1, operatingSystemField, panel3, "Computer Information");
        
        // Add the buttons panel
        createButtonsPanel();
        
        // Put everything together, using the content pane's BorderLayout.
        add(configTabPanel, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);

	}
	
	public void addComponent(int line, int column, int height, int width, 
			                 JComponent component, JComponent compnum, String tabName) 
	{
       GridBagConstraints gbc = new GridBagConstraints();
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

       add(configTabPanel);
       configTabPanel.addTab(tabName, compnum);     
    }
	
   public void createButtonsPanel()
   {
      buttonPane.setLayout(new FlowLayout());
      buttonPane.setBackground(Color.lightGray);
      buttonPane.add(saveBtn, FlowLayout.LEFT);
      buttonPane.add(cleanBtn, FlowLayout.CENTER);
      buttonPane.add(cancelBtn, FlowLayout.RIGHT);
      buttonPane.add(deleteBtn, FlowLayout.LEFT);
   }

   public void setUserFormListener(ConfigFileFormListener listener) 
   {
	   this.configFileFormListener = listener;
   }
   
   // Method #4
   public void cleanFields(String databaseConfigFile) 
   {
      // Clean all the fields in the form
	  idField.setText("");
	  projectNameField.setText("");
	  jarFileNameField.setText("");
	  projInputDirField.setText("");
	  projOutputDirField.setText("");
	  startProjMethodField.setText("");
	  endProjMethodField.setText("");
	  connectProjMethodField.setText("");
	  operatingSystemField.setText("");
	  
	  assignDefaultValues(databaseConfigFile);
	  
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
      operatingSystemField.setText(OS);
		   
   }	 
   	
   public void loadProgLanguages(String databaseConfigFile) 
   {
	  String errorMessage1 = "";
	  
      // Delete all the current values in the Combo
	  progLanguageCombo.removeAllItems();
	      
	  try 
	  {
	     // Obtain an ArrayList of all the current Programming Languages from the FRL Database
		 ArrayList<ProgrammingLanguageHeader> pL = aopFileController.loadProgLanguagesHeader(databaseConfigFile);
		     
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
		 ArrayList<DBMS> dB = aopFileController.loadDBMS(databaseConfigFile);
		     
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
		 ArrayList<InputMethod> iM = aopFileController.loadInputMethods(databaseConfigFile);
		     
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
	     aopFileController.loadProgLangDet(databaseConfigFile, progLang);
	  } 
	  catch (Exception e) 
	  {
         errorMessage = e.getMessage();
	  }
	  
	  progLangDetTablePanel.refresh();
	  if(errorMessage.isEmpty() == false)
           JOptionPane.showMessageDialog(this, errorMessage, "Loading the Programing Languages into the Combo.", JOptionPane.ERROR_MESSAGE);
   }
   
   public void enableFields()
   {
      Color color; 
		  
	  color = Color.LIGHT_GRAY; 
	   
	  // Do not allow to edit in these fields
      idField.setEditable(false);
      idField.setBackground(color);

	  jarFileNameField.setEditable(false);
	  jarFileNameField.setBackground(color);
	  projInputDirField.setEditable(false);
	  projInputDirField.setBackground(color);
	  projOutputDirField.setEditable(false);
	  projOutputDirField.setBackground(color);

	  
	  // Make all these fields not navigable
	  idField.setFocusable(false);
	  jarFileNameField.setFocusable(false);
	  projInputDirField.setFocusable(false);
	  projOutputDirField.setFocusable(false);

	  
	  color = Color.WHITE; 
	  
	  // Allow to edit these fields
	  projectNameField.setEditable(true);
	  projectNameField.setBackground(color);
	  startProjMethodField.setEditable(true);
	  startProjMethodField.setBackground(color);
	  endProjMethodField.setEditable(true);
	  endProjMethodField.setBackground(color);
	  connectProjMethodField.setEditable(true);
	  connectProjMethodField.setBackground(color);
	  operatingSystemField.setEditable(true);
	  operatingSystemField.setBackground(color);
	  
	  // Allow to edit these combos
	  progLanguageCombo.setEnabled(true);
	  progLanguageCombo.setBackground(color);
	  dbmsCombo.setEnabled(true);
	  dbmsCombo.setBackground(color);
	  inputMethodCombo.setEnabled(true);
	  inputMethodCombo.setBackground(color);
	  
	  // Make all these fields navigable
	  startProjMethodField.setFocusable(true);
	  endProjMethodField.setFocusable(true);
	  projectNameField.setFocusable(true);
	  connectProjMethodField.setFocusable(true);
	  operatingSystemField.setFocusable(true);
	  progLanguageCombo.setFocusable(true);
	  dbmsCombo.setFocusable(true);
	  inputMethodCombo.setFocusable(true);
	    
	  // Make visible these open buttons
	  openProjectNameBtn.setVisible(true);
	  openJarFileNameBtn.setVisible(true);
	  openProjInputDirBtn.setVisible(true);
	  openProjOutputDirBtn.setVisible(true);
	  
	  // Enable these open buttons
	  openProjectNameBtn.setEnabled(true);
	  openJarFileNameBtn.setEnabled(true);
	  openProjInputDirBtn.setEnabled(true);
	  openProjOutputDirBtn.setEnabled(true);
	  
	  // Make all these fields navigable
	  openProjectNameBtn.setFocusable(true);
	  openJarFileNameBtn.setFocusable(true);
	  openProjInputDirBtn.setFocusable(true);
	  openProjOutputDirBtn.setFocusable(true);
   }
   
   public void disableFields()
   {
	  Color color; 
	  
	  color = Color.LIGHT_GRAY;
	  
	  // Do not allow to edit in these fields
      idField.setEditable(false);
      idField.setBackground(color);
	  projectNameField.setEditable(false);
	  projectNameField.setBackground(color);
	  jarFileNameField.setEditable(false);
	  jarFileNameField.setBackground(color);
	  projInputDirField.setEditable(false);
	  projInputDirField.setBackground(color);
	  projOutputDirField.setEditable(false);
	  projOutputDirField.setBackground(color);
	  operatingSystemField.setEditable(false);
	  operatingSystemField.setBackground(color);
	  
	  connectProjMethodField.setBackground(color);
	  connectProjMethodField.setEditable(false);
	  
	  // Make all these fields are not navigable
	  idField.setFocusable(false);
	  projectNameField.setFocusable(false);
	  jarFileNameField.setFocusable(false);
	  projInputDirField.setFocusable(false);
	  projOutputDirField.setFocusable(false);
	  operatingSystemField.setFocusable(false);
	  connectProjMethodField.setFocusable(false);
	  startProjMethodField.setFocusable(false);
	  endProjMethodField.setFocusable(false);
	  
	  // Do not allow to edit these fields
	  startProjMethodField.setEditable(false);
	  startProjMethodField.setBackground(color);
	  endProjMethodField.setEditable(false);
	  endProjMethodField.setBackground(color);
	  
	  // Do not Allow to edit these combos
	  progLanguageCombo.setEnabled(false);
	  progLanguageCombo.setBackground(color);
	  dbmsCombo.setEnabled(false);
	  dbmsCombo.setBackground(color);
	  inputMethodCombo.setEnabled(false);
	  inputMethodCombo.setBackground(color);
	  
	  // Make all these fields navigable
	  progLanguageCombo.setFocusable(false);
	  dbmsCombo.setFocusable(false);
	  inputMethodCombo.setFocusable(false);
	  
	  // Make not visible these open buttons
	  openProjectNameBtn.setVisible(false);
	  openJarFileNameBtn.setVisible(false);
	  openProjInputDirBtn.setVisible(false);
	  openProjOutputDirBtn.setVisible(false);
	  
	  // Disable these open buttons
	  openProjectNameBtn.setEnabled(false);
	  openJarFileNameBtn.setEnabled(false);
	  openProjInputDirBtn.setEnabled(false);
	  openProjOutputDirBtn.setEnabled(false);
	  
	  // Make all these fields not navigable
	  openProjectNameBtn.setFocusable(false);
	  openJarFileNameBtn.setFocusable(false);
	  openProjInputDirBtn.setFocusable(false);
	  openProjOutputDirBtn.setFocusable(false);	  
   }
   
   public void enableSaveButton()
   {
      // Make visible the following buttons
	  saveBtn.setVisible(true);
	  deleteBtn.setVisible(false);
		  
	  // Make all these fields not navigable
	  saveBtn.setFocusable(true);
	  deleteBtn.setFocusable(false);
	  
	  cleanBtn.setFocusable(true);
	  cancelBtn.setFocusable(true);
   }
   
   public void enableDeleteButton()
   {
      // Make visible the following buttons
	  saveBtn.setVisible(false);
	  deleteBtn.setVisible(true);
		  
	  // Make all these fields not navigable
	  saveBtn.setFocusable(false);
	  deleteBtn.setFocusable(true);
	  
	  cleanBtn.setFocusable(true);
	  cancelBtn.setFocusable(true);
   }
   
   public void enableViewButton()
   {
      // Make visible the following buttons
	  saveBtn.setVisible(false);
	  deleteBtn.setVisible(false);
		  
	  // Make all these fields not navigable
	  saveBtn.setFocusable(false);
	  deleteBtn.setFocusable(false);
	  
	  cleanBtn.setFocusable(true);
	  cancelBtn.setFocusable(true);
   }
   
}
