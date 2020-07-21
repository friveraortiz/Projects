package gui;
import java.util.EventListener;

public interface LoginFormPanelListener extends EventListener 
{
	public void loginFormEventOccurred(UserFormEvent e); 
}