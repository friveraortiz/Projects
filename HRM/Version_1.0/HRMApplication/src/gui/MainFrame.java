package gui;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import controller.ModuleController;
import model.UserLevel;
import gui.ModuleFormEvent;

public class MainFrame extends JFrame 
{
	
    private UserToolBar userToolBar; 
    public static String menu="";
    public static String submenu="";
   
    // Buttons //
    private JButton employeesBtn;
    private JButton usersBtn;
    private JButton travelReqBtn;
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
    // Constructor of the MainFrame class
	public MainFrame (String user, UserLevel userLevel) 
	{
		
		//Create and set up the window.
        super("Human Resources Management System Main Menu");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Layout of the Main Window
      	//Create and set up the window.
    
        //Set up the content pane.
        Container pane = getContentPane();
        if (RIGHT_TO_LEFT) 
        {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    if (shouldFill) 
		{
		// natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;
		}

	    userToolBar   = new UserToolBar(user,"main",userLevel);
		if (shouldWeightX) 
		{
		   c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(userToolBar, c);

		
		// Create the Buttons
	    employeesBtn = new JButton("Employees");
	    employeesBtn.setVisible(false);
	    
		usersBtn = new JButton("Users");
		usersBtn.setVisible(false);
		
		travelReqBtn = new JButton("Travel Requests");
		travelReqBtn.setVisible(false);
	    
		// Validate which modules this user has access
		String[] modules = new String[6];
		try 
		{
		   modules = getModules(new ModuleFormEvent(this,"", "", userLevel.toString()));
		   		   
		} 
		catch (SQLException e) 
		{
		   String errorMessage = e.getMessage();
		   JOptionPane.showMessageDialog(MainFrame.this, "Error 04: Occurred while loading the Modules the user has access to. Error Message: "+errorMessage, "Loading the Modules", JOptionPane.ERROR_MESSAGE);
		}
		
		// Depending on the user level, provide access to the modules in the HRM System
		int m = 0;
		c.gridx = 0;
		
		for (int i = 0; i < modules.length; i++) 
		{

	       if(modules[i] != null && !modules[i].isEmpty() && !modules[i].trim().isEmpty())
	       {	   
	          if(modules[i].equals("Employees"))
	          {	  
	    	     employeesBtn.setVisible(true);	
	    	     m++;
	    	     
	    		 c.fill = GridBagConstraints.HORIZONTAL;
	    		 c.ipady = 0;      //make this component tall
	    		 c.weightx = 0.5;
	    		 
	    	     if(m >=2 )
		    	    c.gridx++;
	    	     
	    		 c.gridy = 1; // second row
	    			
	             pane.add(employeesBtn, c);
	          }   
	          else 
	             if(modules[i].equals("Users")) 
	             {	 
	    	        usersBtn.setVisible(true);
	    	        m++;
	    	        
	    			c.fill = GridBagConstraints.HORIZONTAL;
	    			c.ipady = 0;      //make this component tall
	    			c.weightx = 0.0;
	    			
	    	        if(m >=2 )
		    	       c.gridx++;
	    	        
	    			c.gridy = 1; // second row
	    			
	    	        pane.add(usersBtn, c);
	             }   
	             else
	                if(modules[i].equals("Travel Requests"))
	                {	
	                   travelReqBtn.setVisible(true); 
	                   m++;
	                   
	         		   c.fill = GridBagConstraints.HORIZONTAL;
		        	   c.ipady = 0;      //make this component tall
		        	   c.weightx = 0.0;
		        	   
	                   if(m >=2 )
	   	    	          c.gridx++;
	                   
	        		   c.gridy = 1; // second row
	        		   
	           		   pane.add(travelReqBtn, c);
	                }   
	       }
	       
	       
	       
	    }
		
		// Validate which submodules this user has access
		String[][] subModules = new String[3][4];
		try 
		{
		   subModules=getSubModules(new ModuleFormEvent(this,"", "", userLevel.toString()));		   
		} 
		catch (SQLException e) 
		{
		   String errorMessage = e.getMessage();
		   JOptionPane.showMessageDialog(MainFrame.this, "Error 05: Occurred while loading the SubModules the user has access to. Error Message: "+errorMessage, "Loading the SubModules", JOptionPane.ERROR_MESSAGE);
		}
		
        // A Menu
        setJMenuBar(createMenuBar(user, userLevel, subModules));
        
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
		
		// Set up that the windows cannot be resizable by the User
		setResizable(false);
        
        //Display the window.
        pack();
        setVisible(true);
        
	}
	
	// Method for creating the Menu Bar, Menus and Items
	private JMenuBar createMenuBar(String userP, UserLevel userLevelP, String[][] subModules)
	{
		// Creates the Menu Bar //
		JMenuBar menuBar = new JMenuBar();
		
		// Creates the Menus //
		
		// Creates the HRM System Menu
		JMenu hrmSystemMenu = new JMenu("HRM System");
		
		// Creates the Items of the Menu
		JMenuItem signoutHrmSItem = new JMenuItem("Sign out");
		JMenuItem exitHrmSItem = new JMenuItem("Exit");
		
		// Adds the items to the Menu
		hrmSystemMenu.add(signoutHrmSItem);
		hrmSystemMenu.addSeparator();
		hrmSystemMenu.add(exitHrmSItem);
		
		// Adds the Menus to the Menu Bar
		menuBar.add(hrmSystemMenu);
		
       // Listeners to the Objects //

		signoutHrmSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{

				// Shows a Dialog to confirm if you want to exit the application
				int action = JOptionPane.showConfirmDialog(
						MainFrame.this, "Do you really want to sign out the HRM System?", 
						"Confirm Sign out", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) 
				{

				   //Close the current MainFrame Window 	
				   setVisible(false);	
		           dispose();
				   // Calls the Java Garbage Collector to release memory
				   System.gc();
					   
				    // It displays a Window to connect to the HRM System
				    new LoginFrame();
					
			    } 
				
			}
		});
		
		// Listener for the HRMSystem > Exit
		exitHrmSItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				// Shows a Dialog to confirm if you want to exit the application
				int action = JOptionPane.showConfirmDialog(
						MainFrame.this, "Do you really want to exit the HRM System?", 
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
		
		
		employeesBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				menu = "Employees";
          		
				setVisible(false);
				dispose();
				System.gc();
				
                // Displays the Employees Main Menu
				new EmployeeFrame(userP, menu, userLevelP); 
				
			}
		});
		
		usersBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				menu = "Users";
          		
				setVisible(false);
				dispose();
				System.gc();
				
                // Displays the Users Main Menu
				new UserFrame(userP, menu, userLevelP); 
				
			}
		});	
		
		travelReqBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				menu = "Travel Requests";
          		
				setVisible(false);
				dispose();
				System.gc();
				
                // Displays the Travel Requests Main Menu
				new TravelRequestFrame(userP, menu, userLevelP, subModules); 
				
			}
		});
		return menuBar;
	}
	
	public String[] getModules(ModuleFormEvent m) throws SQLException
	{
	   ModuleController moduleController = new ModuleController();
	   String[] modules = new String[6];
	   
	   // Populate the modules table into the Database
	   moduleController.saveModules();
	   
	   // Get the modules table from the database
	   try 
	   {
	      modules = moduleController.getModules(m);
	   } 
	   catch (SQLException e) 
	   {
		  String errorMessage = e.getMessage();
		  JOptionPane.showMessageDialog(MainFrame.this, "Error 06: Occurred while loading the Modules the user has access to. Error Message: "+errorMessage, "Loading the Modules", JOptionPane.ERROR_MESSAGE);
		
	   }
	   
       return modules;

	}
	
	public String[][] getSubModules(ModuleFormEvent m) throws SQLException
	{
	   ModuleController moduleController = new ModuleController();
	   String[][] subModules = new String[3][4];
	   
	   try 
	   {
	      subModules = moduleController.getSubModules(m);
	   } 
	   catch (SQLException e) 
	   {
	      String errorMessage = e.getMessage();
		  JOptionPane.showMessageDialog(MainFrame.this, "Error 07: Occurred while loading the SubModules the user has access to. Error Message: "+errorMessage, "Loading the SubModules", JOptionPane.ERROR_MESSAGE);
	   }
	   
       return subModules;

	}
	
	
}