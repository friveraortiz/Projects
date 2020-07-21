package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.UserLevel;
import model.User;

public class UserTablePanel extends JPanel 
{
	private JTable userTable;
	private UserTableModel userTableModel;
	
	// Defined for a PopupMenu
	private JPopupMenu userPopup;
	
	// Listener for the User Table
	UserTableListener userTableListener;
	
	// PopupMenu selected
	private String popupMenu="";
	
	// Create the constructor of the class
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
		JMenuItem viewUse = new JMenuItem("View");
		JMenuItem modifyUse = new JMenuItem("Modify");
		JMenuItem deleteUse = new JMenuItem("Delete");
		userPopup.add(viewUse);
		userPopup.add(modifyUse);
		userPopup.add(deleteUse);
		
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
		viewUse.addActionListener(new ActionListener() 
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
		modifyUse.addActionListener(new ActionListener() 
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
		deleteUse.addActionListener(new ActionListener() 
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
		add(new JScrollPane(userTable), BorderLayout.CENTER);
		
		
	}
	
	// This method: setDataUser was defined first in the UserTableModel Class
	public void setDataUser(List<User> userDb) 
	{
		userTableModel.setDataUser(userDb);
	}
	
	// This method is to display the data in the UserTablePanel when the data changes
	// in the UserFormPanel object
	public void refresh()
	{
		userTableModel.fireTableDataChanged(); 
	}
	
	public void setUserTableListener(UserTableListener listener) 
	{
		this.userTableListener = listener;
	}
	
	public int getUserIdSelected()
	{
		int rowSel = userTable.getSelectedRow();
		int idSel=(int)userTable.getModel().getValueAt(rowSel, 0);
		
		return idSel;
	}
	
	public String getUserStringColSel(int col) 
	{
	   // declare the variables
	   int rowSel = userTable.getSelectedRow();
	   int colSel = col;
	   int idSel;
	   String userNameSel;	
	   String passwordSel; 
	   String employeeSel;
	   String emailSel;
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
		      
		   // User Employee
	       case 3 :
	          employeeSel=(String)userTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=employeeSel;
		      break;  
		      
		   // User Email
	       case 4 :
	          emailSel=(String)userTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=emailSel;
		      break;
		   
		   // User Level
	       case 5 :
	          userLevelSel=(UserLevel)userTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=userLevelSel.toString();
	          break;
	    }

		return stringSel;	
	   
	}
	
			
}
