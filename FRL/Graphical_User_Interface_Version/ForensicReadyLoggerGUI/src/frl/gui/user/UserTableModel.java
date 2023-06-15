package frl.gui.user;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import frl.model.user.User;

// This class is a Wrapper for my Data
//Package #6
//Class #7
public class UserTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;

	// Define an atribute
    private List<User> userDb;
    
    // Define the name of the Columns
    private String[] userColNames = { "Id", "User Name", "Password", "User Level" };
	
 	// Define the Constructor of the Class empty, without any code inside
    // Method #1
	public UserTableModel() 
	{
		
	}
	
	@Override
	// Method #2
	public String getColumnName(int column) 
	{
		return userColNames[column];
	}

	// Define a Method for Class
	// Method #3
	public void setDataUser(List<User> userDb) 
	{
       this.userDb = userDb;
	}
	
	// Define the Methods of the Class
	@Override
	// Method #4
	public int getRowCount() 
	{
	   return userDb.size();	
	}

	@Override
	// Method #5
	public int getColumnCount() 
	{
	   return 4;
	}

	@Override
	// Method #6
	public Object getValueAt(int row, int col) 
	{
       User user = userDb.get(row);
       
       switch(col) 
       {
       case 0:
    	   return user.getId();
       case 1:
    	   return user.getUserName();
       case 2:
    	   return user.getPassword();
       case 3:
    	   return user.getUserLevel();
       }

       return null;
	}

}
