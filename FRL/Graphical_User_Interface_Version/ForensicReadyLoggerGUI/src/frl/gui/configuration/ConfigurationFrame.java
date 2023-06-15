package frl.gui.configuration;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import frl.gui.aopTemplateFile.AOPTemplateFileFrame;
import frl.gui.configuration.ConfigurationFrame;
import frl.gui.main.MainFrame;
import frl.gui.toolBar.ToolBar;
import frl.gui.user.LoginFrame;
import frl.gui.user.UserFrame;
import frl.model.user.UserLevel;

//Package #4
//Class #2
public class ConfigurationFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private ToolBar toolBar; 
    public static String menu="";
    public static String submenu="";
   
    // Buttons //
    private JButton userBtn;
    private JButton aOPTemplateFileBtn;
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
    // Constructor of the MainFrame class
    // Method #1
	public ConfigurationFrame (String user, UserLevel userLevel, 
			                   String databaseConfigFile, String featuresConfigFile) 
	{
		// Create and set up the window.
        super("Configuration Module");
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
		userBtn = new JButton("User");
	    aOPTemplateFileBtn = new JButton("AOP Template File");
	    
		c.gridx = 0;
		
	    userBtn.setVisible(true);
	    // Set the ToolTip text in the JButton Object 
		userBtn.setToolTipText("Opens the User Module"); 
	     
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;      
		c.weightx = 0.0;
	     
		c.gridy = 1; // second row
		
	    pane.add(userBtn, c);
	    
	    c.gridx++;
	    
	    aOPTemplateFileBtn.setVisible(true);
	    // Set the ToolTip text in the JButton Object 
	    aOPTemplateFileBtn.setToolTipText("Opens the Aspect Oriented Programming Template File Module"); 
	    pane.add(aOPTemplateFileBtn, c);
	     		
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
		JMenuItem mainMenuFrlSItem = new JMenuItem("Main Menu");
        // Set the tooltip text in the JMenuItem Object 
		mainMenuFrlSItem.setToolTipText("Opens the Forensic-Ready Logger System Main Menu");
		
		// Creates the Items of the Menu
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
		
		// Adds the Menus to the Menu Bar
		menuBar.add(frlSystemMenu);
		
       // Listeners to the Objects //
		
		// Listener for the FRL System > Exit
		mainMenuFrlSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			   // Close the current window	
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
						ConfigurationFrame.this, "Do you really want to Sign Out the Forensic Ready Logger System?", 
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
						ConfigurationFrame.this, "Do you really want to Exit the Forensic Ready Logger System?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{
					WindowListener[] listeners = getWindowListeners();
					
					for(WindowListener listener: listeners) 
					{
					   // Calls the Windows Closing Event
					   listener.windowClosing(new WindowEvent(ConfigurationFrame.this, 0));
					} 	
			    }
				
			}
		});
		
		userBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

			   menu = "User";
          		
			   setVisible(false);
			   dispose();
			   System.gc();
				
               // Displays the User Module
			   new UserFrame(userP, menu, userLevelP, databaseConfigFile, featuresConfigFile); 
				
			}
		});	
		
		aOPTemplateFileBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
		       menu = "AOP Template File";
			   setVisible(false);
			   dispose();
			   System.gc();
				
               // Displays the AOP Template File Main Menu
			   new AOPTemplateFileFrame(userP, menu, userLevelP, databaseConfigFile, featuresConfigFile); 
				
			}
		});
		
		return menuBar;
	}
	
}