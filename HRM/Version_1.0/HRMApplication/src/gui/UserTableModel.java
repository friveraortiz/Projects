package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.User;

// This class is a Wrapper for my Data
public class UserTableModel extends AbstractTableModel 
{
	
	// Define an atribute
    private List<User> userDb;
    
    // Define the name of the Columns
    private String[] userColNames = {"Id", "User Name", "Password", "Employee", "Email", "User Level" };
	
 	// Define the Constructor of the Class empty, without any code inside
	public UserTableModel() 
	{
		
	}
	
	@Override
	public String getColumnName(int column) 
	{
		return userColNames[column];
	}

	// Define a Method for Class
	
	public void setDataUser(List<User> userDb) 
	{
       this.userDb = userDb;
	}
	
	// Define the Methods of the Class
	@Override
	public int getRowCount() 
	{
	   return userDb.size();	
	}

	@Override
	public int getColumnCount() 
	{
	   return 6;
	}

	@Override
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
    	   return user.getEmployee();
       case 4:
    	   return user.getEmail();
       case 5:
    	   return user.getUserLevel();
       }

       return null;
	}

}
