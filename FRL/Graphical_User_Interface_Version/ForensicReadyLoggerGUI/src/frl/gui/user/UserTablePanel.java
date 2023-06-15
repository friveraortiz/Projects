package frl.gui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import frl.model.user.User;
import frl.model.user.UserLevel;

//Package #6
//Class #8
public class UserTablePanel extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private JTable userTable;
	private UserTableModel userTableModel;
	
	// Defined for a PopupMenu
	private JPopupMenu userPopup;
	
	// Listener for the User Table
	UserTableListener userTableListener;
	
	// PopupMenu selected
	private String popupMenu="";
	
	// Create the constructor of the class
	// Method #1
	public UserTablePanel() 
	{
		
		// Define the Object userTable
		userTableModel = new UserTableModel();
		userTable = new JTable(userTableModel);
		PasswordCellRenderer passwordCellRenderer = new PasswordCellRenderer();
		
		// Hide the contents of the Password Field
		userTable.getColumnModel().getColumn(2).setCellRenderer(passwordCellRenderer);
		
		// Defined for a PopupMenu
		userPopup = new JPopupMenu();
		JMenuItem view= new JMenuItem("View");
		// Set the tooltip text in the JMenuItem Object 
		view.setToolTipText("Consults the details of this User");
		
		JMenuItem modify = new JMenuItem("Modify");
		// Set the tooltip text in the JMenuItem Object 
		modify.setToolTipText("Changes the details of this User"); 
		
		JMenuItem delete = new JMenuItem("Delete");
		// Set the tooltip text in the JMenuItem Object 
		delete.setToolTipText("Eliminates this User"); 
		userPopup.add(view);
		userPopup.add(modify);
		userPopup.add(delete);
		
		// Listener for the Mouse to display a Popup Menu
		userTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				
			    // If we do right click with the mouse, then display a Popup Menu
				if(e.getButton() == MouseEvent.BUTTON3) 
				{
					
				   userPopup.show(userTable, e.getX(), e.getY());
				}
				
			}
		});
		
		
		// Action Listener for the View User Popup Menu
		view.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// Get from the current row selected, the Employee Id
				int idSel=getUserIdSelected();
				
				if(userTableListener != null) 
				{
				   // The View Popup Menu was selected	
				   popupMenu="View";	
				   			      
				   // Call the interface which is implemented in the EmployeeFrame Class
				   userTableListener.userTableEventOccurred(idSel, popupMenu);
				}
			}
	    });
		
		// Action Listener for the Modify User Popup Menu
		modify.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			   // Get from the current row selected, the User Id
			   int idSel=getUserIdSelected();
				
			   if(userTableListener != null) 
			   {
				  // The Modify Popup Menu was selected	
			      popupMenu="Modify";
			      
			      // Call the interface which is implemented in the UserFrame Class
				  userTableListener.userTableEventOccurred(idSel, popupMenu);
			   }
				
			}
	    });
		
		// Action Listener for the Delete User Popup Menu
		delete.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				// Get from the current row selected, the User Id
				int idSel=getUserIdSelected();
				
				if(userTableListener != null) 
				{
				   // The Delete Popup Menu was selected	
				   popupMenu="Delete";
				   
				   // Call the interface which is implemented in the UserFrame Class
				   userTableListener.userTableEventOccurred(idSel, popupMenu);
					  
				}
				
			}
	    });
		
		
		// add the object into the Panel
		setLayout(new BorderLayout());
        setBackground(Color.lightGray);
		add(new JScrollPane(userTable), BorderLayout.CENTER);
		
		
	}
	
	// This method: setDataUser was defined first in the UserTableModel Class
	// Method #2
	public void setDataUser(List<User> userDb) 
	{
		userTableModel.setDataUser(userDb);
	}
	
	// This method is to display the data in the UserTablePanel when the data changes
	// in the UserFormPanel object
	// Method #3
	public void refresh()
	{
		userTableModel.fireTableDataChanged(); 
	}
	
	// Method #4
	public void setUserTableListener(UserTableListener listener) 
	{
		this.userTableListener = listener;
	}
	
	// Method #5
	public int getUserIdSelected()
	{
		int rowSel = userTable.getSelectedRow();
		int idSel=(int)userTable.getModel().getValueAt(rowSel, 0);
		
		return idSel;
	}
	
	// Method #6
	public String getUserStringColSel(int col) 
	{
	   // declare the variables
	   int rowSel = userTable.getSelectedRow();
	   int colSel = col;
	   int idSel;
	   String userNameSel;	
	   String passwordSel; 
	   UserLevel userLevelSel;
	   String stringSel="";

	   switch(colSel) 
	   { 
	       // User Id Number
           case 0 :
              idSel=(int)userTable.getModel().getValueAt(rowSel, 0);
              stringSel=Integer.toString(idSel);
           break;
          
	       // User Name
	       case 1 :
	          userNameSel=(String)userTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=userNameSel;
	          break;
	           
	       // User Password
	       case 2 :
	          passwordSel=(String)userTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=passwordSel;
		      break;  
		      
		   // User Level
	       case 3 :
	          userLevelSel=(UserLevel)userTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=userLevelSel.toString();
	          break;
	    }

		return stringSel;	
	   
	}
				
}
