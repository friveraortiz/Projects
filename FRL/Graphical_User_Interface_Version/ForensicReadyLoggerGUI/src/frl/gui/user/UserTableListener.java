package frl.gui.user;
import java.util.EventListener;

//Package #6
//Class #6
public interface UserTableListener extends EventListener 
{
	// Method #1
	public void userDeleted(int row);
	
	// Method #2
	public void userTableEventOccurred(int id, String popupMenu); 
}



