package frl.gui.main;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import frl.controller.module.ModuleController;
import frl.gui.configuration.ConfigurationFrame;
import frl.gui.generateAspectOrientedFiles.AOPFileFrame;
import frl.gui.generateLoggingInstructions.AOPFinalFileFrame;
import frl.gui.loadUMLSequenceDiagram.UMLSequenceDiagramFrame;
import frl.gui.toolBar.ToolBar;
import frl.gui.user.LoginFrame;
import frl.model.module.ModuleFormEvent;
import frl.model.user.UserLevel;

//Package #4
//Class #2
public class MainFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private ToolBar toolBar; 
    public static String menu="";
    public static String submenu="";
   
    // Buttons //
    private JButton configurationBtn;
    private JButton generateAOPFilesBtn;
    private JButton annotateSeqDiagramBtn;
    private JButton generateLoggingInstructionsBtn;
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
    // Constructor of the MainFrame class
    // Method #1
	public MainFrame (String user, UserLevel userLevel, 
			          String databaseConfigFile, String featuresConfigFile) 
	{
		// Create and set up the window.
        super("Forensic Ready Logger System Main");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Layout of the Main Window
      	// Create and set up the window.
    
        // Set up the content pane.
        Container pane = getContentPane();
        if (RIGHT_TO_LEFT) 
        {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

	    pane.setLayout(new GridBagLayout());
        pane.setBackground(Color.lightGray);
	    GridBagConstraints c = new GridBagConstraints();
	    
	    if (shouldFill) 
		{
		   // natural height, maximum width
		   c.fill = GridBagConstraints.HORIZONTAL;
		}

	    toolBar   = new ToolBar(user,"main",userLevel);
		if (shouldWeightX) 
		{
		   c.weightx = 0.5;
		}
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(toolBar, c);

		
		// Create the Buttons
		configurationBtn = new JButton("Configuration");
		configurationBtn.setVisible(false);
		
	    generateAOPFilesBtn = new JButton("Generate AOP Files");
	    generateAOPFilesBtn.setVisible(false);
		
		annotateSeqDiagramBtn = new JButton("Annotate UML Sequence Diagram");
		annotateSeqDiagramBtn.setVisible(false);
		
	    generateLoggingInstructionsBtn = new JButton("Generate Logging Instructions");
	    generateLoggingInstructionsBtn.setVisible(false);
	    
		// Validate which modules this user has access
		String[] modules = new String[6];
		try 
		{
		   modules = getModules(new ModuleFormEvent(this,"", "", userLevel.toString()), databaseConfigFile);		   
		} 
		catch (Exception e) 
		{
		   String errorMessage = e.getMessage();
		   JOptionPane.showMessageDialog(MainFrame.this, "Error 4211: Occurred while loading the Modules the User has access to. Error Message: "+errorMessage, "Loading the Modules", JOptionPane.ERROR_MESSAGE);
		}
		
		// Depending on the user level, provide access to the modules in the HRM System
		int m = 0;
		c.gridx = 0;
		
		for (int i = 0; i < modules.length; i++) 
		{

	       if(modules[i] != null && !modules[i].isEmpty() && !modules[i].trim().isEmpty())
	       {	   
	          if(modules[i].equals("Establish the Configuration"))
	          {	  
	    	     configurationBtn.setVisible(true);
	    	     
	    	     // Set the ToolTip text in the JButton Object 
	    		 configurationBtn.setToolTipText("Opens the Configuration Setup Module"); 
	    	     m++;
	    	     
	    		 c.fill = GridBagConstraints.HORIZONTAL;
	    		 c.ipady = 0;      
	    		 c.weightx = 0.0;
	    		 
	    	     if(m >=2 )
		    	    c.gridx++;
	    	     
	    		 c.gridy = 1; // second row
	    		
	    	     pane.add(configurationBtn, c);
	          }   
	          else 
	             if(modules[i].equals("Generate AOP File")) 
	             {	 
		    	    generateAOPFilesBtn.setVisible(true);
		    	    
		    	    // Set the ToolTip text in the JButton Object  
		    	    generateAOPFilesBtn.setToolTipText("Opens the Aspect Oriented Programming Files Module"); 
	    	        m++;
	    	        
	    			c.fill = GridBagConstraints.HORIZONTAL;
	    			c.ipady = 0;      //make this component tall
	    			c.weightx = 0.0;
	    			
	    	        if(m >=2 )
		    	       c.gridx++;
	    	        
	    			c.gridy = 1; // second row
	    			
		            pane.add(generateAOPFilesBtn, c);
	             }   
	             else
	                if(modules[i].equals("Annotate Sequence Diagram"))
	                {	
	                   annotateSeqDiagramBtn.setVisible(true);
	                   // Set the ToolTip text in the JButton Object 
	  	    		   annotateSeqDiagramBtn.setToolTipText("Opens the Annotate the Unified Modelling Language Sequence Diagram Module"); 
	                   m++;
	                   
	         		   c.fill = GridBagConstraints.HORIZONTAL;
		        	   c.ipady = 0;      
		        	   // make this component tall
		        	   c.weightx = 0.0;
		        	   
	                   if(m >=2 )
	   	    	          c.gridx++;
	                   
	        		   c.gridy = 1; 
	        		   
	           		   pane.add(annotateSeqDiagramBtn, c);
	                } 
	                else 
	                   if(modules[i].equals("Generate Logging Instructions")) 
	                   {	 
		    	          generateLoggingInstructionsBtn.setVisible(true);
		    	    
		    	          // Set the ToolTip text in the JButton Object  
		    	          generateLoggingInstructionsBtn.setToolTipText("Opens the Logging Instructions Module"); 
		    	          m++;
	    	        
		    	          c.fill = GridBagConstraints.HORIZONTAL;
		    	          c.ipady = 0;      
		    	          //make this component tall
		    	          c.weightx = 0.0;
	    			
		    	          if(m >=2 )
		    	             c.gridx++;
	    	        
	    			      c.gridy = 1; // second row
	    			
		                  pane.add(generateLoggingInstructionsBtn, c);
	                   }  
	       }

	    }
		
        // A Menu
        setJMenuBar(createMenuBar(user, userLevel, databaseConfigFile, featuresConfigFile));
        
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
        
         // Set the Size of the Form
		setSize(500, 650);
		
		// Set the location in the center of the screen
		setLocationRelativeTo(null);
		
	    // Set the color of the Form
	    setBackground(Color.lightGray);
		
		// Set up that the windows cannot be resizable by the User
		setResizable(false);
        
        // Display the window.
        pack();
        setVisible(true);
        
	}
	
	// Method for creating the Menu Bar, Menus and Items
	// Method #2
	private JMenuBar createMenuBar(String userP, UserLevel userLevelP, 
			                       String databaseConfigFile, String featuresConfigFile)
	{
		// Creates the Menu Bar //
		JMenuBar menuBar = new JMenuBar();
		
		// Creates the Menus //
		
		// Creates the FRL System Menu
		JMenu frlSystemMenu = new JMenu("Forensic Ready Logger System");
		
		// Creates the Items of the Menu
		JMenuItem signoutFrlSItem = new JMenuItem("Sign Out");
        // Set the tooltip text in the JMenuItem Object 
		signoutFrlSItem.setToolTipText("Disconnects the current User from the Forensic-Ready Logger System");
		JMenuItem exitFrlSItem = new JMenuItem("Exit");
        // Set the tooltip text in the JMenuItem Object 
		exitFrlSItem.setToolTipText("Closes the Forensic-Ready Logger System");
		
		// Adds the items to the Menu
		frlSystemMenu.add(signoutFrlSItem);
		frlSystemMenu.addSeparator();
		frlSystemMenu.add(exitFrlSItem);
		
		// Adds the Menus to the Menu Bar
		menuBar.add(frlSystemMenu);
		
       // Listeners to the Objects //

		signoutFrlSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{

				// Shows a Dialog to confirm if you want to exit the application
				int action = JOptionPane.showConfirmDialog(
						MainFrame.this, "Do you really want to Sign Out the Forensic Ready Logger System?", 
						"Confirm Sign Out", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{

				   // Close the current MainFrame Window 	
				   setVisible(false);	
		           dispose();
		           
				   // Calls the Java Garbage Collector to release memory
				   System.gc();
					   
				    // It displays a Window to connect to the HRM System
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
						MainFrame.this, "Do you really want to Exit the Forensic Ready Logger System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
					WindowListener[] listeners = getWindowListeners();
					
					for(WindowListener listener: listeners) 
					{
					   // Calls the Windows Closing Event
					   listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					} 	
			    }
				
			}
		});
		
		generateAOPFilesBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
		       menu = "Generate AOP File";
			   setVisible(false);
			   dispose();
			   System.gc();
				
               // Displays the AOP Files Main Menu
			   new AOPFileFrame(userP, menu, userLevelP, databaseConfigFile, featuresConfigFile); 
				
			}
		});
		
		configurationBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

			   menu = "User";
          		
			   setVisible(false);
			   dispose();
			   System.gc();
				
               // Displays the Configuration Main Menu
			   //new UserFrame(userP, menu, userLevelP, databaseConfigFile, featuresConfigFile); 
			   new ConfigurationFrame(userP, userLevelP, databaseConfigFile, featuresConfigFile); 
				
			}
		});	
		
		annotateSeqDiagramBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   menu = "Annotate UML Sequence Diagram";
			   setVisible(false);
			   dispose();
			   System.gc();
				
               // Displays the UML Sequence Diagram Main Menu
			   new UMLSequenceDiagramFrame(userP, menu, userLevelP, databaseConfigFile, featuresConfigFile); 
				
			}
		});
		
		generateLoggingInstructionsBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
		       menu = "Generate Logging Instructions";
			   setVisible(false);
			   dispose();
			   System.gc();
				
               // Displays the AOP Files Main Menu
			   new AOPFinalFileFrame(userP, menu, userLevelP, databaseConfigFile, featuresConfigFile); 
				
			}
		});
		
		return menuBar;
	}
	
	// Method #3
	public String[] getModules(ModuleFormEvent m, String databaseConfigFile) throws Exception
	{
	   ModuleController moduleController = new ModuleController();
	   String[] modules = new String[6];
	   String errorMessage1="", errorMessage2="";
	   
	   // Populate the modules table into the Database
	   try 
	   {
	      moduleController.saveModules(databaseConfigFile);
	   }
	   catch(Exception e1)
	   {
		  // Error #1 
	      errorMessage1 = e1.getMessage(); 
		  errorMessage2 = "Error XXXX: Ocurred while saving the modules of the Forensic Ready Logger System. \n";
		  errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		  JOptionPane.showMessageDialog(MainFrame.this, errorMessage2, "Saving the Modules", JOptionPane.ERROR_MESSAGE);
	   }
	   
	   // Get the modules table from the database
	   try 
	   {
	      modules = moduleController.getModules(m, databaseConfigFile);
	   } 
	   catch (SQLException e2) 
	   {
		  // Error #2
	      errorMessage1 = e2.getMessage(); 
		  errorMessage2 = "Error 4231: Occurred while loading the Modules the user has access to. \n";
		  errorMessage2 = errorMessage2 + "Error Message: "+ errorMessage1;
		  JOptionPane.showMessageDialog(MainFrame.this, errorMessage2, "Loading the Modules", JOptionPane.ERROR_MESSAGE);
	   }
	   
       return modules;

	}
	
}