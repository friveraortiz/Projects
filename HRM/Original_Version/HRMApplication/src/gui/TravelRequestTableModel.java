package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.TravelRequest;

// This class is a Wrapper for my Data
public class TravelRequestTableModel extends AbstractTableModel 
{
	
	// Define an atribute
    private List<TravelRequest> travelRequestDb;
    
    // Define the name of the Columns
    private String[] travelRequestColNames = {"Id", "Number", "Employee", "Transportation", 
    		"Purpose", "Travel From", "Travel To", "Travel Date", "Return Date",
    		"Notes", "Currency", "Total Funding", "Status", "Status Notes" };
	
 	// Define the Constructor of the Class empty, without any code inside
	public TravelRequestTableModel() 
	{
		
	}
	
	@Override
	public String getColumnName(int column) 
	{
		return travelRequestColNames[column];
	}

	// Define a Method for Class
	public void setDataTravelRequest(List<TravelRequest> travelRequestDb) 
	{
       this.travelRequestDb = travelRequestDb;
	}
	
	// Define the Methods of the Class
	@Override
	public int getRowCount() 
	{
	   return travelRequestDb.size();
	}

	@Override
	public int getColumnCount() 
	{
	   return 14;
	}

	@Override
	public Object getValueAt(int row, int col) 
	{
       TravelRequest travelRequest = travelRequestDb.get(row);
       
       switch(col) 
       {
       case 0:
    	   return travelRequest.getId();
       case 1:
    	   return travelRequest.getNumber();
       case 2:
    	   return travelRequest.getEmployee();
       case 3:
    	   return travelRequest.getTransportation();
       case 4:
    	   return travelRequest.getPurpose();
       case 5:
    	   return travelRequest.getTravelFrom();
       case 6:
    	   return travelRequest.getTravelTo();
       case 7:
    	   return travelRequest.getTravelDate();
       case 8:
    	   return travelRequest.getReturnDate();
       case 9:
    	   return travelRequest.getNotes();
       case 10:
    	   return travelRequest.getCurrency();
       case 11:
    	   return travelRequest.getTotalFunding();
       case 12:
    	   return travelRequest.getStatus();
       case 13:
    	   return travelRequest.getStatusNotes();
       }
       
       return null;
	}

}
