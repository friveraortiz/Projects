package frl.gui.projectTable;
import java.util.EventListener;

//Package #6
//Class #6
public interface ProjectTableListener extends EventListener 
{
	// Method #1
	public void projectDeletedOccurred(int row);
	
	// Method #2
	public void projectEventOccurred(int id, String popupMenu); 
}