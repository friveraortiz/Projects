package gui;
import java.util.EventListener;

public interface EmployeeFormListener extends EventListener 
{
   public boolean employeeFormEventOccurred(EmployeeFormEvent e); 
   
   public boolean employeeFormDeleteEventOccurred(EmployeeFormEvent e); 
   
}