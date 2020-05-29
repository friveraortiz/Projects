package gui;
import javax.swing.SwingUtilities;

public class HRMApp 
{
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable () 
		{
		   // Displays the Login Window	
		   @Override
		   public void run () 
		   {
		      // It displays a Window to connect to the HRM System
		      new LoginFrame();
		      
		   }
	    });
	}
}
	