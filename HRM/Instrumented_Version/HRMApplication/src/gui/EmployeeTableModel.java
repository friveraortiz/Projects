package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Employee;

// This class is a Wrapper for my Data
public class EmployeeTableModel extends AbstractTableModel 
{
	
	// Define an atribute
    private List<Employee> employeeDb;
    
    // Define the name of the Columns
    private String[] employeeColNames = {"Id", "Number", "First Name", "Middle Name", 
    		"Last Name", "DOB", "Gender", "Marital Status", "Mobile Phone",
    		"Joined Date", "Terminated Date", "Job Title", "Department", "Supervisor" };
	
 	// Define the Constructor of the Class empty, without any code inside
	public EmployeeTableModel() 
	{
		
	}
	
	@Override
	public String getColumnName(int column) 
	{
		return employeeColNames[column];
	}

	// Define a Method for Class
	public void setDataEmployee(List<Employee> employeeDb) 
	{
       this.employeeDb = employeeDb;
	}
	
	// Define the Methods of the Class
	@Override
	public int getRowCount() 
	{
	   return employeeDb.size();
	}

	@Override
	public int getColumnCount() 
	{
	   return 14;
	}

	@Override
	public Object getValueAt(int row, int col) 
	{
       Employee employee = employeeDb.get(row);
       
       switch(col) 
       {
       case 0:
    	   return employee.getId();
       case 1:
    	   return employee.getNumber();
       case 2:
    	   return employee.getFirstName();
       case 3:
    	   return employee.getMiddleName();
       case 4:
    	   return employee.getLastName();
       case 5:
    	   return employee.getDob();
       case 6:
    	   return employee.getGender();
       case 7:
    	   return employee.getMaritalStatus();
       case 8:
    	   return employee.getMobilePhone();
       case 9:
    	   return employee.getJoinedDate();
       case 10:
    	   return employee.getTerminatedDate();
       case 11:
    	   return employee.getJobTitle();
       case 12:
    	   return employee.getDepartment();
       case 13:
    	   return employee.getSupervisor();
       }
       
       return null;
	}

}
