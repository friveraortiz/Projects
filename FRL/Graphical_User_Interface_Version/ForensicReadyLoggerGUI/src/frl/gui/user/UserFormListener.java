package frl.gui.user;
import java.util.EventListener;

//Package #6
//Class #3
public interface UserFormListener extends EventListener 
{
   // Method #1	
   public boolean userFormEventOccurred(UserFormEvent e); 
   
   // Method #2
   public boolean userFormDeleteEventOccurred(UserFormEvent e); 
   
}