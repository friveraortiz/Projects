package gui;
import java.util.EventListener;

public interface UserFormListener extends EventListener 
{
   public boolean userFormEventOccurred(UserFormEvent e); 
   
   public boolean userFormDeleteEventOccurred(UserFormEvent e); 
   
}