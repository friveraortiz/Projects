package gui;
import java.util.EventListener;

public interface UserTableListener extends EventListener 
{
	public void userDeleted(int row);
	public void userTableEventOccurred(int id, String popupMenu); 
}



