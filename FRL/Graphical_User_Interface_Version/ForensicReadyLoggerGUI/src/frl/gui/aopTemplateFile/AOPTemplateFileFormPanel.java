package frl.gui.aopTemplateFile;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.Border;

import frl.controller.aopTemplateFile.AOPTemplateFileController;
import frl.gui.toolBar.ToolBar;
import frl.model.generateAspectOrientedFiles.ProgrammingLanguageHeader;
import frl.model.configureAspectOrientedFiles.PropertyType;
import frl.model.configureAspectOrientedFiles.TextFilePlain;
import frl.model.configureAspectOrientedFiles.TextFileProperties;
import frl.model.configureAspectOrientedFiles.TextFilePropertyName;

//Package #6
//Class #4
public class AOPTemplateFileFormPanel extends JPanel 
{

   private static final long serialVersionUID = 1L;
   // Define the Attributes of the Class //	
	
   // Define Labels	//	
   private JLabel idLabel;
   private JLabel completeFileNameLabel;
   private JLabel pathLabel;
   private JLabel fileNameLabel;
   private JLabel nameLabel;
   private JLabel typeFileLabel;
   private JLabel packageNameLabel;
   private JLabel programmingLanguageIdLabel;
   private JLabel programmingLanguageNameLabel;
   private JLabel pointCutName1Label;
   private JLabel pointCutName2Label;
 
   // Define Text Fields //

   // Personal Information	
   public JTextField idField;
   public JTextField completeFileNameField;
   public JTextField pathField;
   public JTextField fileNameField;
   public JTextField nameField;
   public JTextField packageNameField;
   public JTextField pointCutName1Field;
   public JTextField pointCutName2Field;
   public JTextField programmingLanguageIdField;
   
   // Define ComboBox //
   public JComboBox<String> programmingLanguageNameCombo;
   public JComboBox<String> typeFileCombo;
   
   // Buttons //
   public JButton saveBtn;
   public JButton deleteBtn;
   public JButton cleanBtn;
   public JButton cancelBtn;
   public JButton openCompleteFileNameBtn;
   
   // Define an attribute for the controller
   private AOPTemplateFileController aOPTemplateFileController;
   
   // Listener //
   private AOPTemplateFileFormListener aOPTemplateFileFormListener;
   
   String titleBorder="";
   
   // Constructor of the UserFormPanel Class
   // Method #1
   public AOPTemplateFileFormPanel(String databaseConfigFile) 
   {
		Dimension dim = getPreferredSize();
		
        // Define an object for the Controller Class
        aOPTemplateFileController = new AOPTemplateFileController();
		
		// Set the Size of the Form
		dim.height = 600;
		dim.width = 1100;
		setPreferredSize(dim);
		
	    // Set the color of the Form
	    this.setBackground(Color.lightGray);
	    
		// Define the Objects of the class //
		
		// JLabels
	    // Define Label Fields
	    
		idLabel = new JLabel("AOP Template File Id: ");
	    fileNameLabel = new JLabel("File Name: ");	
	    completeFileNameLabel = new JLabel("Complete File Name: ");
	    pathLabel = new JLabel("Path Name: ");
	    nameLabel = new JLabel("Name: ");
	    typeFileLabel = new JLabel("Type File: "); 
	    packageNameLabel = new JLabel("Package Name: "); 
	    programmingLanguageNameLabel = new JLabel("Programming Language: "); 
	    programmingLanguageIdLabel = new JLabel("Progra Language Id: "); 
	    pointCutName1Label = new JLabel("PointCut Name 1: "); 
	    pointCutName2Label = new JLabel("PointCut Name 2: "); 

		// JTextFields
	    // Define Text Fields //
 
	    idField = new JTextField(10); 
	    
	    // Make the idField not editable
	    Color color = Color.LIGHT_GRAY;
	    idField.setBackground(color);
	    idField.setEditable(false);
	    
		color = Color.WHITE;
		idField = new JTextField(50); 
		idField.setBackground(color);
		
		fileNameField = new JTextField(50); 
		fileNameField.setBackground(color);
		
		completeFileNameField = new JTextField(50); 
		completeFileNameField.setBackground(color);
		
		pathField = new JTextField(50); 
		pathField.setBackground(color);
		
		nameField = new JTextField(50); 
		nameField.setBackground(color);
		
		packageNameField = new JTextField(50); 
		packageNameField.setBackground(color);
		
		pointCutName1Field = new JTextField(50); 
		pointCutName1Field.setBackground(color);
		
		pointCutName2Field = new JTextField(50); 
		pointCutName2Field.setBackground(color);
		
		programmingLanguageIdField = new JTextField(50); 
		programmingLanguageIdField.setBackground(color);
		
		typeFileCombo = new JComboBox<String>(); 
		typeFileCombo.setBackground(color);
			    
		// Setup of the TypeFile JCombo box
		DefaultComboBoxModel<String> typeFileModel = new DefaultComboBoxModel<String>();
		typeFileModel.addElement("Aspect");
		typeFileCombo.setModel(typeFileModel);
		typeFileCombo.setSelectedItem("Aspect");
		
	    color = Color.WHITE;
		programmingLanguageNameCombo = new JComboBox<String>(); 
		programmingLanguageNameCombo.setBackground(color);
		programmingLanguageNameCombo.setToolTipText("Project Source Code Language");
		
		// The JButton Objects
		saveBtn   = new JButton("Save");
		deleteBtn = new JButton("Delete");
		cleanBtn  = new JButton("Clean");
		cancelBtn = new JButton("Cancel");
		
		openCompleteFileNameBtn = new JButton("Opens an AOP File");
		openCompleteFileNameBtn.setToolTipText("Opens a Aspect Oriented Template File from the File System");

		openCompleteFileNameBtn.requestFocusInWindow();
			    
		// Listeners //
		
		// Listener for the progLanguageCombo
		programmingLanguageNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   AOPTemplateFileFormEvent ev = null;
			   int programmingLanguageIdN=0;
			   String programmingLanguageId, programmingLanguageName="";
				
			   // Obtain the value of the Objects //
			   programmingLanguageId   = programmingLanguageIdField.getText();
			   programmingLanguageName = (String) programmingLanguageNameCombo.getSelectedItem();  
				
			   if(programmingLanguageId != null && !programmingLanguageId.isEmpty()) 
			      programmingLanguageIdN = Integer.parseInt(programmingLanguageId);
			   else
			      programmingLanguageIdN = 0;
					
			   // Calling the constructor # 4 for the AOPTemplateFileFormEvent Class
			   ev = new AOPTemplateFileFormEvent(this, programmingLanguageIdN, programmingLanguageName);
				
			   if(aOPTemplateFileFormListener != null) 
			   {
			      aOPTemplateFileFormListener.programmingLanguageComboEventOccurred(databaseConfigFile, ev);
			   }	
			}
			
		});
		
		
		openCompleteFileNameBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AOPTemplateFileFormEvent ev;
				String path="";
				
				// Obtain the value of the Objects //
				path = pathField.getText();
				
				// Calling the constructor # 5 for the AOPTemplateFileFormEvent Class
				ev = new AOPTemplateFileFormEvent(this, path);
				
				if(aOPTemplateFileFormListener != null) 
				{
				   aOPTemplateFileFormListener.openPathButtonEventOccurred(ev);
				}	
			}
			
		});
		
		// Save Button
		saveBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AOPTemplateFileFormEvent ev = null;
				boolean aopTemplateFileSaved=false;
				
				String  id="", fileName="", path="", name="", packageName="", pointCutName1="", pointCutName2="", 
				        programmingLanguageId="", programmingLanguageName="", typeFile="";
				int idN=0, plIdN=0;
				
				// Obtain the value of the Objects //
				id            = idField.getText();
				fileName      = fileNameField.getText();
				path          = pathField.getText();
				name          = nameField.getText();
				packageName   = packageNameField.getText();
				pointCutName1 = pointCutName1Field.getText();
				pointCutName2 = pointCutName2Field.getText();
				programmingLanguageId = programmingLanguageIdField.getText();
				
				// Validate if the Id is not null
				if(programmingLanguageId != null && !programmingLanguageId.isEmpty()) 
				   plIdN = Integer.parseInt(programmingLanguageId);
				
				programmingLanguageName = (String) programmingLanguageNameCombo.getSelectedItem();  
				typeFile = (String) typeFileCombo.getSelectedItem(); 
				
				// Validate if the Id is not null
				if(id != null && !id.isEmpty()) 
				{ 
				   // Convert from String to Integer	
				   idN   = Integer.parseInt(id);
				   
				   // Calling the constructor # 2 for the AOPTemplateFileFormEvent Class
				   ev = new AOPTemplateFileFormEvent(this, idN, fileName, path, 
                                                     name, typeFile, packageName, plIdN,
                                                     programmingLanguageName, pointCutName1, pointCutName2);
                   
				}
				else
				{
			       // Calling the constructor # 3 for the AOPTemplateFileFormEvent Class
				   ev = new AOPTemplateFileFormEvent(this, fileName, path, 
                                                     name, typeFile, packageName, plIdN,
                                                     programmingLanguageName, pointCutName1, pointCutName2);
				}
				
				if(aOPTemplateFileFormListener != null) 
				{
					aopTemplateFileSaved = aOPTemplateFileFormListener.aOPTemplateFileSaveEventOccurred(ev);
			       
				   // Validate that the user has been saved
				   if (aopTemplateFileSaved == true) 
				   {
			          // Clear all the fields except the text area
				      cleanFields(databaseConfigFile);
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
				AOPTemplateFileFormEvent ev = null;
				boolean aopTemplateFileDeleted=false;
				
				String  id="", fileName="", path="", name="", packageName="", pointCutName1="", pointCutName2="", 
				        programmingLanguageId="", programmingLanguageName="", typeFile="";
				int idN=0, plIdN=0;
				
				// Obtain the value of the Objects //
				id            = idField.getText();
				fileName      = fileNameField.getText();
				path          = pathField.getText();
				name          = nameField.getText();
				packageName   = packageNameField.getText();
				pointCutName1 = pointCutName1Field.getText();
				pointCutName2 = pointCutName2Field.getText();
				programmingLanguageId = programmingLanguageIdField.getText();
				
				programmingLanguageName = (String) programmingLanguageNameCombo.getSelectedItem();  
				typeFile = (String) typeFileCombo.getSelectedItem(); 
				
				
				// Validate if the Id is not null
				if(id != null && !id.isEmpty()) 
				{ 
				   // Convert from String to Integer	
				   idN   = Integer.parseInt(id);
				   plIdN = Integer.parseInt(programmingLanguageId);
				   
				   // Calling the constructor # 2 for the AOPTemplateFileFormEvent Class
				   ev = new AOPTemplateFileFormEvent(this, idN, fileName, path, 
                                                     name, typeFile, packageName, plIdN,
                                                     programmingLanguageName, pointCutName1, pointCutName2);
                   
				}
				else
				{
			       // Calling the constructor # 3 for the AOPTemplateFileFormEvent Class
				   ev = new AOPTemplateFileFormEvent(this, fileName, path, 
                                                     name, typeFile, packageName, plIdN,
                                                     programmingLanguageName, pointCutName1, pointCutName2);
				}
				
				if(aOPTemplateFileFormListener != null) 
				{
					aopTemplateFileDeleted = aOPTemplateFileFormListener.aOPTemplateFileDeleteEventOccurred(ev);
			       
				   // Validate that the user has been saved
				   if (aopTemplateFileDeleted == true) 
				   {
			          // Clear all the fields except the text area
				      cleanFields(databaseConfigFile);
				   }
				   
				}	
			}
			
		});

		
		// Clean Button
		cleanBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   cleanFields(databaseConfigFile);
			 
			}
			
		});	
		
		// Cancel Button
		cancelBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   cleanFields(databaseConfigFile);
			   
			   // Makes invisible the Employee Form Panel
			   setVisible(false);
			   
			   // Makes visible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(true);
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("AOP Template File Information");
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
		

	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(completeFileNameLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(completeFileNameField, gc);
	    
		gc.gridx++;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.CENTER;
	    add(openCompleteFileNameBtn, gc);
	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(pathLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(pathField, gc);
	 
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(fileNameLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(fileNameField, gc);
	    
  
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(nameLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(nameField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(typeFileLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(typeFileCombo, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(packageNameLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(packageNameField, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(programmingLanguageNameLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(programmingLanguageNameCombo, gc);	
		
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(pointCutName1Label, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(pointCutName1Field, gc);	
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(pointCutName2Label, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(pointCutName2Field, gc);
		
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(programmingLanguageIdLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(programmingLanguageIdField, gc);	
		
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
		add(cancelBtn, gc);
		
	
   }
   
   // Method #3
   public void setUserFormListener(AOPTemplateFileFormListener listener) 
   {
      this.aOPTemplateFileFormListener = listener;
   }
   
   public void getProgLanguageId(String databaseConfigFile, String name)
   {
      String errorMessage1="", progLangId="";
	  int progLangIdNumber=0;
      
      try 
	  {
	     aOPTemplateFileController.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
		 errorMessage1 = e1.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage1, "Connecting to the Forensic Ready Logger Database.", JOptionPane.ERROR_MESSAGE);	
	  }
      
      try 
	  {
         progLangIdNumber = aOPTemplateFileController.getProgLanguageId(databaseConfigFile, name);
	  } 
	  catch (Exception e2) 
	  {
		 errorMessage1 = e2.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage1, "Getting the Programming Language Identifier.", JOptionPane.ERROR_MESSAGE);	
	  }
      
	  // Convert from integer to String
	  progLangId = String.valueOf(progLangIdNumber);
 	  programmingLanguageIdField.setText(progLangId);
      
      try 
	  {
         aOPTemplateFileController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
		 errorMessage1 = e3.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage1, "Disconnecting from the Forensic Ready Logger Database.", JOptionPane.ERROR_MESSAGE);	
	  }
	   
   }
   
   public void loadProgLanguages(String databaseConfigFile) 
   {
	  String errorMessage1="", progLangName="", progLangId="";
	  int i=0, progLangIdNumber=0;
	  
      // Delete all the current values in the Combo
	  programmingLanguageNameCombo.removeAllItems();
	      
	  try 
	  {
	     // Obtain an ArrayList of all the current Programming Languages from the FRL Database
		 ArrayList<ProgrammingLanguageHeader> pL = aOPTemplateFileController.loadProgLanguagesHeader(databaseConfigFile);
		     
		 // Read all the current Programming Language ArrayList
		 for (i = 0; i < pL.size(); i++) 
		 {
		    progLangName     = pL.get(i).getName();
		    progLangIdNumber = pL.get(i).getId();
		    
		    // Convert from integer to String
		    progLangId = String.valueOf(progLangIdNumber);
			    
			// Assign an Programming Language to the Programming Language Combo
		    programmingLanguageNameCombo.addItem(progLangName);
		    programmingLanguageIdField.setText(progLangId);
	      }
		     
	   } 
       catch (Exception e) 
	   {
	      errorMessage1 = e.getMessage();
		  JOptionPane.showMessageDialog(this, errorMessage1, "Loading the Programming Languages in the Combo.", JOptionPane.ERROR_MESSAGE);	
	   }
   }
   
   public void uploadAOPTemplateFileDB(String featuresConfigFile, String databaseConfigFile, int projectId, int headerId, String textFile)
   {
	  String line="", errorMessage="";
	  int lineNum = 0;
	  Reader inReader = null;
	  boolean occurredError=false;
	  
	  // Connect to the Forensic Ready Logger Database
      try 
	  {
	     aOPTemplateFileController.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
		 occurredError = true; 
	     errorMessage = e1.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Connecting to the Forensic Ready Logger Database.", JOptionPane.ERROR_MESSAGE);	
	  }
	  
	  try 
	  {
	     inReader = new FileReader(textFile);
	  } 
	  catch (FileNotFoundException e2) 
	  {
		 occurredError = true; 
	     errorMessage = e2.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Opening the AOP Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
		  
	  BufferedReader reader = new BufferedReader(inReader);

	  // Read the Text File line by line and insert the current line into the TextFile Plain
	  try 
	  {
	     while ((line = reader.readLine()) != null) 
		 {
		    lineNum++; 
			    
			// Create a new TextFilePlain Object
		    TextFilePlain tfp = new TextFilePlain(headerId, lineNum, line);
			    
		    try
		    {
		       // Save the current line into the Database
			   aOPTemplateFileController.saveOneLineTextFilePlain(databaseConfigFile, tfp);
		    }   
			catch (Exception e3) 
			{
			   occurredError = true;
			   errorMessage = e3.getMessage();
			   JOptionPane.showMessageDialog(this, errorMessage, "Saving the Plain Details of the AOP Template File into the Database.", JOptionPane.ERROR_MESSAGE);	
		    }
	     }
	  }   
	  catch (IOException e4) 
	  {
		 occurredError = true; 
	     errorMessage = e4.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Loading the AOP Template File.", JOptionPane.ERROR_MESSAGE);	
	  } 

	  // Update the Header Id in the Text_File_Plain records that we just inserted
	  try 
	  {
	     aOPTemplateFileController.updateTextFilePlain(databaseConfigFile, headerId);
	  } 
	  catch (Exception e5) 
	  {
		 occurredError = true;  
	     errorMessage = e5.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Updating the Plain Details of the AOP Template File.", JOptionPane.ERROR_MESSAGE);
	  }
	  
	  // Copy the records inserted in the Text_File_Plain table to the TextFileDetails table
	  try 
	  {
	     aOPTemplateFileController.saveTextFileDetails(databaseConfigFile, headerId);
	  } 
	  catch (Exception e6) 
	  {
		 occurredError = true;  
	     errorMessage = e6.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Saving the Details of the AOP Template File into the Database.", JOptionPane.ERROR_MESSAGE);	
	  }
	  
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFileProperties(databaseConfigFile, projectId, headerId);
	  } 
	  catch (Exception e7) 
	  {
		 occurredError = true;  
	     errorMessage = e7.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Deleting the AOP Template File Properties from the Database.", JOptionPane.ERROR_MESSAGE);	
	  }
	  
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFilePropertyNames(databaseConfigFile, headerId);
	  } 
	  catch (Exception e8) 
	  {
		 occurredError = true;  
	     errorMessage = e8.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Deleting the AOP Template File Property Names from the Database.", JOptionPane.ERROR_MESSAGE);	
	  }

	  // Create the necessary records in the table: textFileDetail
	  try 
	  {
	     aOPTemplateFileController.createAOPTemplateFileStructures(databaseConfigFile, projectId, headerId);
	  } 
	  catch (Exception e9) 
	  {
		 occurredError = true; 	  
	     errorMessage = e9.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Creating the Structure of the AOP Template File into the Database.", JOptionPane.ERROR_MESSAGE);	
	  } 
	  
	  // Update the Line Numbers in the textFileDetail table
	  try 
	  {
	     aOPTemplateFileController.updateAOPTemplateFileLineNumbers(databaseConfigFile, headerId);
	  } 
	  catch (Exception e10) 
	  {
		 occurredError = true; 	  
	     errorMessage = e10.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Updating the Line Numbers in the AOP Template File.", JOptionPane.ERROR_MESSAGE);	
	  } 
	  
	  // Validate if the AOP Template File could not be uploaded to the Forensic Ready Logger Database, 
	  // then delete all the Details from the Database
	  if(occurredError == true)
	  {
	     try
	     {
	        deleteAOPTemplateFile(databaseConfigFile, projectId, headerId);
	     }
		 catch (Exception e11) 
		 {
		    errorMessage = e11.getMessage();
			JOptionPane.showMessageDialog(this, errorMessage, "Deleting the AOP Template File Header.", JOptionPane.ERROR_MESSAGE);	
		 }
	  }
	  
	  // Disconnect from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.disconnect();
	  } 
	  catch (Exception e12) 
	  {
	     errorMessage = e12.getMessage();
		 JOptionPane.showMessageDialog(this, errorMessage, "Disconnecting from the Forensic Ready Logger Database.", JOptionPane.ERROR_MESSAGE);	
	  }
	  
   }
   
   public void deleteAOPTemplateFile(String databaseConfigFile, int projectId, int headerId)
   {
      String errorMessage="";
      
      // Delete the Properties of the AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFileProperties(databaseConfigFile, projectId, headerId);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Properties of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
	  
      // Delete the Property Names of the AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFilePropertyNames(databaseConfigFile, headerId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Property Names of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
      
      // Delete the Details from AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFileDetails(databaseConfigFile, headerId);
	  } 
	  catch (Exception e3) 
	  {
	     // Error #4
	     errorMessage = e3.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Details of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
	      
      // Delete the Plain Details from AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFilePlainDetails(databaseConfigFile, headerId);
	  } 
	  catch (Exception e4) 
	  {
	     errorMessage = e4.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Plain Details of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
	      
      // Delete the Header from AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFileHeader(databaseConfigFile, headerId);
	  } 
	  catch (Exception e5) 
	  {
	     errorMessage = e5.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Header of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
   }
   
   public void deleteAOPTemplateFileModify(String databaseConfigFile, int projectId, int headerId)
   {
      String errorMessage="";
      
      // Delete the Properties of the AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFileProperties(databaseConfigFile, projectId, headerId);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Properties of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
	  
      // Delete the Property Names of the AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFilePropertyNames(databaseConfigFile, headerId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Property Names of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
      
      // Delete the Details from AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFileDetails(databaseConfigFile, headerId);
	  } 
	  catch (Exception e3) 
	  {
	     // Error #4
	     errorMessage = e3.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Details of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
	      
      // Delete the Plain Details from AOP Template File from the Forensic Ready Logger Database
	  try 
	  {
	     aOPTemplateFileController.deleteAOPTemplateFilePlainDetails(databaseConfigFile, headerId);
	  } 
	  catch (Exception e4) 
	  {
	     errorMessage = e4.getMessage();
	     JOptionPane.showMessageDialog(this, errorMessage, "Deleting the Plain Details of the Aspect Oriented Template File.", JOptionPane.ERROR_MESSAGE);	
	  }
	      
   }  
   
   public int getProjectIdentifier(String featuresConfigFile, String databaseConfigFile)
   {
      String errorMessage="", projectName=""; 
      int projectId=0;
      Properties prop = new Properties();
      InputStream input;
       
      try
      {
         input = new FileInputStream(featuresConfigFile);
         
         // Load the properties file
         prop.load(input);

         // Get the property value
         projectName = prop.getProperty("projectName");
         
       } 
       catch (IOException e1) 
       {
  	      errorMessage = e1.getMessage();
	      JOptionPane.showMessageDialog(this, errorMessage, "Loading the Project Name", JOptionPane.ERROR_MESSAGE);	
       }
      
       // Get the Project Identifier from the Forensic Ready Logger Database
	   try 
	   {
	      projectId = aOPTemplateFileController.getProjectIdentifier(databaseConfigFile, projectName);
	   } 
	   catch (Exception e2) 
	   {
	      errorMessage = e2.getMessage();
	      JOptionPane.showMessageDialog(this, errorMessage, "Loading the Project Identifier", JOptionPane.ERROR_MESSAGE);	
	   }
	   
	   return projectId;
	   
   }
   
   public void cleanFields(String databaseConfigFile) 
   {
      // Clean all the fields in the form
	  idField.setText("");
	  completeFileNameField.setText("");
	  pathField.setText("");
      fileNameField.setText("");
	  nameField.setText("");
	  packageNameField.setText("");
	  pointCutName1Field.setText("");
	  pointCutName2Field.setText("");
	  programmingLanguageIdField.setText("");
	  typeFileCombo.setSelectedItem("Aspect");
	  
	  // Load current Programming Languages from the FRL Database
	  loadProgLanguages(databaseConfigFile);
	  programmingLanguageNameCombo.setSelectedItem("Java");
        
	  openCompleteFileNameBtn.requestFocusInWindow();
   }
   	   
}
