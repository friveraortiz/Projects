package frl.gui.generateAspectOrientedFiles;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

//Package #6
//Class #4
public class AOFFileFormPanel extends JPanel 
{

   private static final long serialVersionUID = 1L;
   // Define the Attributes of the Class //	
	
   // Define Labels	//	
   private JLabel idLabel;
   private JLabel fileNameLabel;
   private JLabel pathLabel;
   private JLabel nameLabel;
   private JLabel fileTypeLabel;
   private JLabel packageNameLabel;
 
   // Define Text Fields //
   public JTextField idField;
   public JTextField fileNameField;
   public JTextField pathField;
   public JTextField nameField;
   public JTextField packageNameField;
   
   // Define ComboBox //
   public JComboBox<String> fileTypeCombo;
   
   // Buttons //
   public JButton saveBtn;
   public JButton deleteBtn;
   public JButton cleanBtn;
   public JButton cancelBtn;
   
   // Listener //
   private AOPFileFormListener aOPFileFormListener;
   
   String titleBorder="";
   
   // Constructor of the UserFormPanel Class
   // Method #1
   public AOFFileFormPanel(String databaseConfigFile) 
   {

		Dimension dim = getPreferredSize();
		
		// Set the Size of the Form
		dim.height = 600;
		dim.width = 1000;
		setPreferredSize(dim);
		
	    // Set the color of the Form
	    this.setBackground(Color.lightGray);
	    
		// Define the Objects of the class //
		
		// JLabels
	    // Define Label Fields
	    
		idLabel = new JLabel("User Id: ");
	    fileNameLabel = new JLabel("File Name: ");	
	    pathLabel = new JLabel("Path: ");
	    nameLabel = new JLabel("Name: ");
	    fileTypeLabel = new JLabel("File Type: "); 
	    packageNameLabel = new JLabel("Package Name: "); 

		// JTextFields
	    // Define Text Fields //
 
	    idField = new JTextField(10); 
	    
	    // Make the idField not editable
	    Color color = Color.LIGHT_GRAY;
	    idField.setBackground(color);
	    idField.setEditable(false);
	    
		color = Color.WHITE;
		fileNameField = new JTextField(50); 
		fileNameField.setBackground(color);
	    // Set the tooltip text in the JTextField Object 
	    fileNameField.setToolTipText("Long File Name");

		pathField = new JTextField(50); 
		pathField.setBackground(color);
	    // Set the tooltip text in the JTextField Object 
	    pathField.setToolTipText("Folder Location of this File");
		
		nameField = new JTextField(50); 
		nameField.setBackground(color);
	    // Set the tooltip text in the JTextField Object 
	    nameField.setToolTipText("Short File Name");
		
		fileTypeCombo = new JComboBox<String>(); 
		fileTypeCombo.setBackground(color);
	    // Set the tooltip text in the JTextField Object 
	    fileTypeCombo.setToolTipText("File Type");
			    
		// Setup of the fileType JCombo box
		DefaultComboBoxModel<String> fileTypeModel = new DefaultComboBoxModel<String>();
		fileTypeModel.addElement("Aspect");
		fileTypeModel.addElement("Class");
		fileTypeCombo.setModel(fileTypeModel);
		fileTypeCombo.setSelectedItem("Aspect");
		
		packageNameField = new JTextField(50); 
		packageNameField.setBackground(color);
	    // Set the tooltip text in the JTextField Object 
	    packageNameField.setToolTipText("Package Name");
		
		// The JButton Objects
		saveBtn = new JButton("Save");
        // Set the tooltip text in the JButton Object 
		saveBtn.setToolTipText("Stores the details of this Aspect Oriented Programming File into the Forensic-Ready Logger System");
		
		deleteBtn = new JButton("Delete");
        // Set the tooltip text in the JButton Object 
		deleteBtn.setToolTipText("Eliminates this Aspect Oriented Programming File from the Forensic-Ready Logger System");
		
		cleanBtn = new JButton("Clean");
        // Set the tooltip text in the JButton Object 
		cleanBtn.setToolTipText("Cleans all the fields into the Aspect Oriented Programming File Information Form");
		
		cancelBtn = new JButton("Cancel");
        // Set the tooltip text in the JButton Object 
		cancelBtn.setToolTipText("Closes the Aspect Oriented Programming File Information Form");

		fileNameField.requestFocusInWindow();
			    
		// Listeners //
		
		// Save Button
		saveBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AOPFileFormEvent ev;
				boolean userSaved=false;
				
				// Obtain the value of the Objects //
				String idS         = idField.getText();
				String fileName    = fileNameField.getText();
				String path        = pathField.getText();
				String name        = nameField.getText();
				String fileType    = (String) fileTypeCombo.getSelectedItem();  
				String packageName = packageNameField.getText();
				
				// Validate if the Id is not null
				if(idS != null && !idS.isEmpty()) 
				{ 
				   int id = Integer.parseInt(idS);
				   // Calling the constructor # 1 for the UserForm Event Class
				   ev = new AOPFileFormEvent(this, id, fileName, path, name, fileType, packageName);
				}
				else
				{
				   // Calling the constructor # 2 for the UserForm Event Class
				   ev = new AOPFileFormEvent(this, fileName, path, name, fileType, packageName);
				}
				
				if(aOPFileFormListener != null) 
				{
				   userSaved = aOPFileFormListener.aopFileFormEventOccurred(ev);
			       
				   // Validate that the user has been saved
				   if (userSaved == true) 
				   {
			          // Clear all the fields except the text area
				      cleanFields();
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
				AOPFileFormEvent ev;
				boolean userDeleted=false;
				
				// Obtain the value of the Objects //
				String idS         = idField.getText();
				String fileName    = fileNameField.getText();
				String path        = pathField.getText();
				String name        = nameField.getText();
				String fileType    = (String) fileTypeCombo.getSelectedItem(); 
				String packageName = packageNameField.getText();
				
				// Validate if the Id is not null
				if(idS != null && !idS.isEmpty()) 
				{ 
				   int id = Integer.parseInt(idS);
				   
				   // Calling the constructor # 1 for the UserForm Event Class
				   ev = new AOPFileFormEvent(this, id, fileName, path, name, fileType, packageName);
				}
				else
				{
				   // Calling the constructor # 2 for the EmployeeForm Event Class
				   ev = new AOPFileFormEvent(this, fileName, path, name, fileType, packageName);
				}
				
				if(aOPFileFormListener != null) 
				{
				   userDeleted=aOPFileFormListener.aopFileFormDeleteEventOccurred(ev);
				}
				
				if (userDeleted == true) 
				{
			       // Clear all the fields except the text area
			   	   cleanFields();
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
			   AOPFileToolBar.addNewButton.setVisible(true);
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Aspect Oriented Programming File Information");
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
		

	    // Second row //
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
	    add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(nameField, gc);
	    	    
	    // Next row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
	    add(fileTypeLabel, gc);
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(fileTypeCombo, gc);	
		
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
   public void setUserFormListener(AOPFileFormListener listener) 
   {
	   this.aOPFileFormListener = listener;
   }
   
   // Method #4
   public void cleanFields() 
   {
      // Clean all the fields in the form
	  idField.setText("");
	  fileNameField.setText("");
	  pathField.setText(""); 
	  nameField.setText("");
	  fileTypeCombo.setSelectedItem("Aspect");
	  packageNameField.setText("");
	  
	  fileNameField.requestFocusInWindow();
   }
   	   
}
