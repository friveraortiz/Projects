package frl.gui.main;
import javax.swing.*;

import frl.gui.user.LoginFrame;

// Package #4
// Class #1
public class ForensicReadyLoggerGUI 
{
   // Method #1
   public static void main(String[] args) 
   {
      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
    	 {
            String databaseConfigFile;
        	String featuresConfigFile;
        	
    	    databaseConfigFile = "/Users/fannyriveraortiz/eclipse-workspace/ForensicReadyLoggerGUI/resources/frlDatabaseConfig.properties";
    	    featuresConfigFile = "/Users/fannyriveraortiz/eclipse-workspace/ForensicReadyLoggerGUI/resources/frlFeaturesConfig.properties";
 		      
    	    // It displays a Window to connect to the Forensic Ready Logger System
 		    new LoginFrame(databaseConfigFile, featuresConfigFile);
         }
      });
   }
}
