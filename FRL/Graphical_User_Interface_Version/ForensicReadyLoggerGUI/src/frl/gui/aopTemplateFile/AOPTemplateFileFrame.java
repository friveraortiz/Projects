package frl.gui.aopTemplateFile;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import frl.controller.aopTemplateFile.AOPTemplateFileController;
import frl.gui.main.MainFrame;
import frl.gui.toolBar.ToolBar;
import frl.gui.toolBar.ToolBarListener;
import frl.gui.user.LoginFrame;
import frl.model.user.UserLevel;

//Package #6
//Class #5
public class AOPTemplateFileFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	// Define the attributes for the class
    private ToolBar toolBar; 
    private AOPTemplateFileFormPanel aOPTemplateFileFormPanel;
 
    // Define an attribute for the controller
    private AOPTemplateFileController aOPTemplateFileController;
    
    // Define an attribute for the table
    private AOPTemplateFileTablePanel aOPTemplateFileTablePanel;
    public static String menu="";
    public static String submenu="";
    public static int projectId=0;
    public JFileChooser fileChooser;
    
    // Constructor of the AOPTemplateFileFrame class
    // Method #1
	public AOPTemplateFileFrame (String userP, String menuP, UserLevel userLevelP, 
			                     String databaseConfigFile, String featuresConfigFile) 
	{
        super("AOP Template File");
        
        // Layout of the Main Window
        setLayout(new BorderLayout());
        
        // The Main Form creates the objects 
        aOPTemplateFileFormPanel  = new AOPTemplateFileFormPanel(databaseConfigFile);
        aOPTemplateFileTablePanel = new AOPTemplateFileTablePanel();        
        
        if (menuP.equals("AOP Template File")) 
        {
           toolBar = new ToolBar(userP, menuP, userLevelP);
        }
        
        // Define an object for the Controller Class
        aOPTemplateFileController = new AOPTemplateFileController();
        
        // Define an object for the UserTablePanel Class
        aOPTemplateFileTablePanel.setData(aOPTemplateFileController.getAOPTemplateFiles());
        
        
        // Get the Project Details
        projectId = aOPTemplateFileFormPanel.getProjectIdentifier(featuresConfigFile, databaseConfigFile);
                
        // Add a new Listener for the AOPTemplateFileTable
        
        aOPTemplateFileTablePanel.setTableListener(new AOPTemplateFileTableListener() 
        {
			@Override
			public void aOPTemplateFileDeleteEventOccurred(int row) 
			{
				
			   aOPTemplateFileController.deleteAOPTemplateFile(row);
			}

			//@Override
			public void aOPTemplateFileEventOccurred(int id, String popupMenu) 
			{
			   int headerId=0;
			   
			   // Show the AOPTemplateFile Panel
		       aOPTemplateFileFormPanel.setVisible(true);
		     	   
	     	   // Clean the current values in the aOPTemplateFileFormPanel
	     	   aOPTemplateFileFormPanel.cleanFields(databaseConfigFile);
	     	   
	     	   // Get all the values from the selected row into the aOPTemplateFileTablePanel
	     	   refreshAOPTemplateFile();
			   
			   if (popupMenu.equals("View"))
			   {
				  aOPTemplateFileFormPanel.openCompleteFileNameBtn.setVisible(false);  
			      aOPTemplateFileFormPanel.saveBtn.setVisible(false);
			      aOPTemplateFileFormPanel.deleteBtn.setVisible(false);
			      
			      // disable all the fields in the aOPTemplateFileFormPanel
			      disableFields();
			      
			      menu = "";
			      
			      // Makes invisible the ToolBar Add New Button
			      ToolBar.addNewButton.setVisible(false);
			   }
		       else
		          if (popupMenu.equals("Modify"))
		          {
		        	 aOPTemplateFileFormPanel.openCompleteFileNameBtn.setVisible(true);
		             aOPTemplateFileFormPanel.saveBtn.setVisible(true);
		             aOPTemplateFileFormPanel.deleteBtn.setVisible(false);
		             
		             // enable all the fields in the aOPTemplateFileFormPanel
		             enableFields();
		             
			         menu = "";
			         
			         // Makes invisible the ToolBar Add New Button
				     ToolBar.addNewButton.setVisible(false);
				     
				     headerId = Integer.valueOf(aOPTemplateFileFormPanel.idField.getText());
				     
                     // Delete the previous information to be able to create all the structure again
				     aOPTemplateFileFormPanel.deleteAOPTemplateFileModify(databaseConfigFile, projectId, headerId);

		          }
		          else
			         if (popupMenu.equals("Delete"))
			         {
			        	aOPTemplateFileFormPanel.openCompleteFileNameBtn.setVisible(false); 
			            aOPTemplateFileFormPanel.saveBtn.setVisible(false);
			            aOPTemplateFileFormPanel.deleteBtn.setVisible(true);
			            
			            // disable all the fields in the aOPTemplateFileFormPanel
					    disableFields();
					      
				        menu = "";
				        
				        // Makes invisible the ToolBar Add New Button
					    ToolBar.addNewButton.setVisible(false);
			         }
			}
		});
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Retrieving from the Forensic Ready Logger Database the existing AOPTemplateFiles in the AOPTemplateFileTable Panel
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        loadingAOPTemplateFiles(databaseConfigFile);
                
        // MenuBar
        setJMenuBar(createUserMenuBar(userP, userLevelP, databaseConfigFile, featuresConfigFile));
        
        // Listener for the toolbar object
        toolBar.setUserToolBarListener(new ToolBarListener()
        {
			@Override
			public void addNewEventOccurred() 
			{	
				menu    = "AOP Template File";
                submenu = "Add";
                
			   	// Makes visible the aOPTemplateFileFormPanel
				aOPTemplateFileFormPanel.setVisible(true);
				aOPTemplateFileFormPanel.idField.setText("");
				
				aOPTemplateFileFormPanel.openCompleteFileNameBtn.setVisible(true); 
				
				// Makes visible the User Save Button
				aOPTemplateFileFormPanel.saveBtn.setVisible(true);
				
				// enable all the fields in the aOPTemplateFileFormPanel
	            enableFields();
	            
	            aOPTemplateFileFormPanel.cleanFields(databaseConfigFile);
	            
				// Makes invisible the User Delete Button
				aOPTemplateFileFormPanel.deleteBtn.setVisible(false);
				
				// Makes visible the User Text Panel
				aOPTemplateFileTablePanel.setVisible(true);
			}

        });	
        
        // Listener for the User formPanel object
        aOPTemplateFileFormPanel.setUserFormListener(new AOPTemplateFileFormListener() 
        {
        	// Method that is defined in the Interface: AOPTemplateFileFormEvent
        	@Override
			public boolean aOPTemplateFileSaveEventOccurred(AOPTemplateFileFormEvent ev) 
        	{
           	   String errorMessage1="";
           	   int headerId=0;
           	   boolean validaAOPTemplateFile=false;
           	   
        	   // Validation #1: Validate the AOP Template File fields are not empty
           	  validaAOPTemplateFile = aOPTemplateFileController.validateTemplateFileFields(ev);
           	  
              if (validaAOPTemplateFile == false) 
       	      {
       		     // Error #1 
       		     errorMessage1 =  "Error XXXX: All the AOP Template fields should have a value be and not be empty.";
                 JOptionPane.showMessageDialog(AOPTemplateFileFrame.this, errorMessage1, "Validating an AOP Template File.", JOptionPane.ERROR_MESSAGE);
                 return false;
              }
              else
              {	  
	
      	         // Assign to the object controller values
        	     aOPTemplateFileController.addAOPTemplateFile(ev);
      	   
      	         // Show the information from the aOPTemplateFileFormPanel fields 
      	         // into the AOPTemplateFileTablePanel object
      	         aOPTemplateFileTablePanel.refresh();
      	      
	             // Saving the Header from AOPTemplateFileTableFormPanel into the Forensic Ready Logger Database
		         try 
		         {
		            headerId = aOPTemplateFileController.saveAOPTemplateFileHeader(databaseConfigFile, ev);
		         } 
		         catch (Exception e1) 
		         {
		            // Error #4
		    	    errorMessage1 = e1.getMessage();
		            JOptionPane.showMessageDialog(AOPTemplateFileFrame.this, errorMessage1, "Saving the Header of the Aspect Oriented Template File", JOptionPane.ERROR_MESSAGE);	
		         }
		      
		         // Upload the AOP Template File into the into the Forensic Ready Logger Database
		         aOPTemplateFileFormPanel.uploadAOPTemplateFileDB(featuresConfigFile, databaseConfigFile, projectId, headerId, aOPTemplateFileFormPanel.completeFileNameField.getText());
           
		         // Retrieving from the FRL Database the existing AOPTemplateFile information in the AOPTemplateFileTable Panel
		         loadingAOPTemplateFiles(databaseConfigFile);
      	      
      	         return true;
              }
        	   
        	}

			@Override
			public boolean aOPTemplateFileDeleteEventOccurred(AOPTemplateFileFormEvent e) 
			{
	           String idStr="";
	           int id=0;
	           
	      	  // Assign to the object controller values
	          aOPTemplateFileController.addAOPTemplateFile(e);
	      	   
	      	  // Show the information from the aOPTemplateFileFormPanel fields 
	      	  // into the AOPTemplateFileTablePanel object
	      	  aOPTemplateFileTablePanel.refresh();
	      	  
	      	  idStr = aOPTemplateFileFormPanel.idField.getText();
	      	  
	      	  // Convert String into Integer
	      	  id = Integer.valueOf(idStr);
	      	  
              // Delete the Aspect Oriented Template File from the Forensic Ready Logger Database
	      	  aOPTemplateFileFormPanel.deleteAOPTemplateFile(databaseConfigFile, projectId, id);
		      
		      // Retrieving from the FRL Database the existing AOPTemplateFile information in the AOPTemplateFileTable Panel
		      loadingAOPTemplateFiles(databaseConfigFile);
	      	      
	      	  return true;
			    
			}

			@Override
			public boolean programmingLanguageComboEventOccurred(String databaseConfigFile, AOPTemplateFileFormEvent ev) 
			{
			   String progLangName="";
			   
			   progLangName = ev.getProgrammingLanguageName(); 
			   
			   aOPTemplateFileFormPanel.getProgLanguageId(databaseConfigFile, progLangName);

			   return true;
			}

			@Override
			public boolean openPathButtonEventOccurred(AOPTemplateFileFormEvent e) 
			{
			   String completeFileName="",fileName="", directory="", name="", packageName="";
			   int returnVal=0;
			   Path p;
			   File f;
						   
			   fileChooser = new JFileChooser();
			   fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			   FileNameExtensionFilter filter = new FileNameExtensionFilter("Aspect Oriented Programming File", "aj");
			   fileChooser.setFileFilter(filter);

			   returnVal = fileChooser.showOpenDialog(null);
			   if(returnVal == JFileChooser.APPROVE_OPTION)
			   {	
			      completeFileName = fileChooser.getSelectedFile().getAbsolutePath();
			      aOPTemplateFileFormPanel.completeFileNameField.setText(completeFileName);
			      
			      p         = Paths.get(completeFileName);
			      fileName  = p.getFileName().toString();
			      directory = p.getParent().toString();
			      
			      if (fileName.indexOf(".") > 0) 
			         name = fileName.substring(0, fileName.lastIndexOf("."));
			      else 
			         name = fileName;
			      
			      f           = new File(completeFileName);
			      packageName = f.getParentFile().getName();
			      
			      aOPTemplateFileFormPanel.pathField.setText(directory);
			      aOPTemplateFileFormPanel.fileNameField.setText(fileName);
			      aOPTemplateFileFormPanel.nameField.setText(name);
			      aOPTemplateFileFormPanel.packageNameField.setText(packageName.toLowerCase());
			      
			   }
			   
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
        add(aOPTemplateFileFormPanel, BorderLayout.WEST);
        add(aOPTemplateFileTablePanel, BorderLayout.CENTER);
        
        
        // Set the Size of the Form
        pack();
		this.setSize(1100, 600);
		
		// Set the location in the center of the screen
		setLocationRelativeTo(null);
		
		// Set up that the windows cannot be resizable by the User
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
        //Display the window.
        this.setVisible(true);
   
	   	// Makes Not Visible the Panels
		aOPTemplateFileFormPanel.setVisible(false);
		aOPTemplateFileTablePanel.setVisible(true);
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
		
		// Listener for the HRMSystem > Exit
		mainMenuFrlSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			   // Close the current window	
			   aOPTemplateFileFormPanel.setVisible(false);
			   aOPTemplateFileTablePanel.setVisible(true);
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
						AOPTemplateFileFrame.this, "Do you really want to Sign Out the Forensic Ready Logger System?", 
						"Confirm Sign Out", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{

				   //Close the current MainFrame Window 	
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
						AOPTemplateFileFrame.this, "Do you really want to exit the Forensic Ready Logger System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
				   WindowListener[] listeners = getWindowListeners();
					
				   for(WindowListener listener: listeners) 
				   {
					  // Calls the Windows Closing Event
				      listener.windowClosing(new WindowEvent(AOPTemplateFileFrame.this, 0));
				   } 	
			    }
			}
		});
		
		return menuBar;
	}
	
	// This method will display all the values of the selected row in the aOPTemplateFileFormPanel
	// Method #3
	public void refreshAOPTemplateFile()
	{
	   String idSel="", fileNameSel="", pathSel="", nameSel="", typeFileSel="", packageNameSel="", 
			  programmingLanguageIdSel="", programmingLanguageNameSel="", pointCutName1Sel="", pointCutName2Sel="";
		
	   // AOPFileTemplate Id Number
	   idSel = aOPTemplateFileTablePanel.getStringColSel(0);
	   aOPTemplateFileFormPanel.idField.setText(idSel);

       // File Name
       fileNameSel = aOPTemplateFileTablePanel.getStringColSel(1);
	   aOPTemplateFileFormPanel.fileNameField.setText(fileNameSel);
           
       // Path
       pathSel = aOPTemplateFileTablePanel.getStringColSel(2);
	   aOPTemplateFileFormPanel.pathField.setText(pathSel);
       
       // Name
       nameSel = aOPTemplateFileTablePanel.getStringColSel(3);
	   aOPTemplateFileFormPanel.nameField.setText(nameSel);
	   
	   // Complete FileName
	   aOPTemplateFileFormPanel.completeFileNameField.setText(pathSel + File.separator +  fileNameSel);
       
       // Type File
       typeFileSel = aOPTemplateFileTablePanel.getStringColSel(4);
	   if (typeFileSel.equals("Aspect"))
	      aOPTemplateFileFormPanel.typeFileCombo.setSelectedItem("Aspect");
       
       // Package Name
       packageNameSel = aOPTemplateFileTablePanel.getStringColSel(5);
	   aOPTemplateFileFormPanel.packageNameField.setText(packageNameSel);
       
       // Programming Language Id
       programmingLanguageIdSel = aOPTemplateFileTablePanel.getStringColSel(6);
	   aOPTemplateFileFormPanel.programmingLanguageIdField.setText(programmingLanguageIdSel);
       
       // Programming Language Name
       programmingLanguageNameSel = aOPTemplateFileTablePanel.getStringColSel(7);
	   if (programmingLanguageNameSel.equals("Java"))
	      aOPTemplateFileFormPanel.typeFileCombo.setSelectedItem("Java");
       
       // PointCutName 1
       pointCutName1Sel = aOPTemplateFileTablePanel.getStringColSel(8);
	   aOPTemplateFileFormPanel.pointCutName1Field.setText(pointCutName1Sel);
       
       // PointCutName 2
	   pointCutName2Sel = aOPTemplateFileTablePanel.getStringColSel(9);
	   aOPTemplateFileFormPanel.pointCutName2Field.setText(pointCutName2Sel);
			       
	   aOPTemplateFileFormPanel.openCompleteFileNameBtn.requestFocusInWindow();
	}
	
   
   // Method #5
   public void disableFields()
   {
      // Make all the fields not editable
      Color color = Color.LIGHT_GRAY;
      
      aOPTemplateFileFormPanel.idField.setBackground(color);
      aOPTemplateFileFormPanel.idField.setEditable(false);
      aOPTemplateFileFormPanel.idField.setFocusable(false);
      
      aOPTemplateFileFormPanel.completeFileNameField.setBackground(color);
      aOPTemplateFileFormPanel.completeFileNameField.setEditable(false);
      aOPTemplateFileFormPanel.completeFileNameField.setFocusable(false);
      
      aOPTemplateFileFormPanel.pathField.setBackground(color);
      aOPTemplateFileFormPanel.pathField.setEditable(false);
      aOPTemplateFileFormPanel.pathField.setFocusable(false);
      
      aOPTemplateFileFormPanel.fileNameField.setBackground(color);
      aOPTemplateFileFormPanel.fileNameField.setEditable(false);
      aOPTemplateFileFormPanel.fileNameField.setFocusable(false);
      
      aOPTemplateFileFormPanel.nameField.setBackground(color);
      aOPTemplateFileFormPanel.nameField.setEditable(false);
      aOPTemplateFileFormPanel.nameField.setFocusable(false);
      
      aOPTemplateFileFormPanel.packageNameField.setBackground(color);
      aOPTemplateFileFormPanel.packageNameField.setEditable(false);
      aOPTemplateFileFormPanel.packageNameField.setFocusable(false);
      
      aOPTemplateFileFormPanel.programmingLanguageIdField.setBackground(color);
      aOPTemplateFileFormPanel.programmingLanguageIdField.setEditable(false);
      aOPTemplateFileFormPanel.programmingLanguageIdField.setFocusable(false);
      
      aOPTemplateFileFormPanel.typeFileCombo.setBackground(color);
      aOPTemplateFileFormPanel.typeFileCombo.setEnabled(false);
      aOPTemplateFileFormPanel.typeFileCombo.setFocusable(false);
      
      aOPTemplateFileFormPanel.programmingLanguageNameCombo.setBackground(color);
      aOPTemplateFileFormPanel.programmingLanguageNameCombo.setEnabled(false);
      aOPTemplateFileFormPanel.programmingLanguageNameCombo.setFocusable(false);
            
      aOPTemplateFileFormPanel.pointCutName1Field.setBackground(color);
      aOPTemplateFileFormPanel.pointCutName1Field.setEditable(false);
      aOPTemplateFileFormPanel.pointCutName1Field.setFocusable(false);
      
      aOPTemplateFileFormPanel.pointCutName2Field.setBackground(color);
      aOPTemplateFileFormPanel.pointCutName2Field.setEditable(false);
      aOPTemplateFileFormPanel.pointCutName1Field.setFocusable(false);
      
   }
   
   // Method #6
   public void enableFields()
   {
      //Make all the fields editable
      Color color = Color.WHITE;
      
      aOPTemplateFileFormPanel.typeFileCombo.setBackground(color);
      aOPTemplateFileFormPanel.typeFileCombo.setEnabled(true);
      aOPTemplateFileFormPanel.typeFileCombo.setFocusable(true);
      
      aOPTemplateFileFormPanel.programmingLanguageNameCombo.setBackground(color);
      aOPTemplateFileFormPanel.programmingLanguageNameCombo.setEnabled(true);
      aOPTemplateFileFormPanel.programmingLanguageNameCombo.setFocusable(true);
            
      aOPTemplateFileFormPanel.pointCutName1Field.setBackground(color);
      aOPTemplateFileFormPanel.pointCutName1Field.setEditable(true);
      aOPTemplateFileFormPanel.pointCutName1Field.setFocusable(true);
      
      aOPTemplateFileFormPanel.pointCutName2Field.setBackground(color);
      aOPTemplateFileFormPanel.pointCutName2Field.setEditable(true);
      aOPTemplateFileFormPanel.pointCutName1Field.setFocusable(true);
      
      color = Color.LIGHT_GRAY;
      
      aOPTemplateFileFormPanel.idField.setBackground(color);
      aOPTemplateFileFormPanel.idField.setEditable(false);
      aOPTemplateFileFormPanel.idField.setFocusable(false);
      
      aOPTemplateFileFormPanel.completeFileNameField.setBackground(color);
      aOPTemplateFileFormPanel.completeFileNameField.setEditable(false);
      aOPTemplateFileFormPanel.completeFileNameField.setFocusable(false);
      
      aOPTemplateFileFormPanel.pathField.setBackground(color);
      aOPTemplateFileFormPanel.pathField.setEditable(false);
      aOPTemplateFileFormPanel.pathField.setFocusable(false);
      
      aOPTemplateFileFormPanel.fileNameField.setBackground(color);
      aOPTemplateFileFormPanel.fileNameField.setEditable(false);
      aOPTemplateFileFormPanel.fileNameField.setFocusable(false);
      
      aOPTemplateFileFormPanel.nameField.setBackground(color);
      aOPTemplateFileFormPanel.nameField.setEditable(false);
      aOPTemplateFileFormPanel.nameField.setFocusable(false);
      
      aOPTemplateFileFormPanel.packageNameField.setBackground(color);
      aOPTemplateFileFormPanel.packageNameField.setEditable(false);
      aOPTemplateFileFormPanel.packageNameField.setFocusable(false);
      
      aOPTemplateFileFormPanel.programmingLanguageIdField.setBackground(color);
      aOPTemplateFileFormPanel.programmingLanguageIdField.setEditable(false);
      aOPTemplateFileFormPanel.programmingLanguageIdField.setFocusable(false);

   }
   
   // Method #4
   public void loadingAOPTemplateFiles(String databaseConfigFile)
   {
	  String errorMessage1="";
	  
      //System.out.println("Loading the current Users from the Forensic Ready Logger Database ...");

	  try 
	  {
		 aOPTemplateFileController.load(databaseConfigFile);
	     aOPTemplateFileTablePanel.refresh();
	  } 
	  catch (Exception e) 
	  {
		 // Error #1 
		 errorMessage1 = e.getMessage();
	     JOptionPane.showMessageDialog(AOPTemplateFileFrame.this, errorMessage1, "Loading AOP Template Files", JOptionPane.ERROR_MESSAGE);	
	  }
		
   }
   

   
}