package gui;

import java.util.EventListener;

public interface TravelRequestTableListener extends EventListener 
{
	public void travelRequestDeleted(int row);
	
	public void travelRequestTableEventOccurred(int id, String popupMenu); 
}



