package gui;
import java.util.EventListener;

public interface TravelRequestFormListener extends EventListener 
{
   public boolean travelRequestFormEventOccurred(TravelRequestFormEvent e); 
   
   public void travelRequestFormDeleteEventOccurred(TravelRequestFormEvent e); 
   
}