package gui;

import java.util.EventListener;

public interface EmployeeTableListener extends EventListener 
{
	public void employeeDeleted(int row);
	
	public void employeeTableEventOccurred(int id, String popupMenu); 
}



