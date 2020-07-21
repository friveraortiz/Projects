package frl.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import frl.aspectfiles.AspectOrientedFiles;
import frl.configuration.FRLConfiguration;
import frl.configuration.JavaClasses;
import frl.inputmethods.Database;

public class ForensicReadyLogger 
{
   // Declare the variables of this class	
   static boolean result = false;
   static ArrayList<String> javaClasses = new ArrayList<String>();
   static ArrayList<String> dbMethods = new ArrayList<String>();
   static ArrayList<String> dbCallHier = new ArrayList<String>();
   static String dbMethod, format;
   static FRLConfiguration frlCon;
   static Database db;
   static JavaClasses jc;
   static AspectOrientedFiles aof;
   static int c=0;
   
   public ForensicReadyLogger() //1
   {
	   
   }
   
   public static void exitApplicationError(String errorMessage) //2
   {
      System.out.println(errorMessage);
      
	  // Calls the Java Garbage Collector to release memory
	  System.gc();
			
	  // Exit from the Forensic Ready Logger Application
	  System.exit(0);
   }
   
   public static String getCurrentTime()
   {
	   
      Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
			   
	  return currentDate.toString();
   }
   
   
   public static void main(String[] args) //3
   {

	  System.out.println("");
      System.out.println("Welcome to the Forensic Ready Logger: "+ getCurrentTime());
      
      // Initialize the ArrayLists
      dbMethods.clear();
      dbCallHier.clear();
		
	  // Load the configuration parameters for the Forensic Ready Logger Application
	  frlCon = new FRLConfiguration();
	  result = frlCon.frlConfigureParameters();
		   
	  // If the configuration parameters were loaded successfully then continue
	  if(result == true)
	  {
         // Create a new JavaClasses object
	   	 jc = new JavaClasses();
	       
	     // Read the Java Files from the Application Input Directory
	   	 javaClasses = jc.readJavaFiles(frlCon.projectInputDir, frlCon.projectName, 
	   			                        frlCon.tabDelimiter1, frlCon.programmingLanguage);
	   	 format = frlCon.tabDelimiter1;
	 
	   	 System.out.println("Files: ");  
	   	 for(c = 0; c < javaClasses.size(); c++) 
	   	 {   
		    System.out.format(format, javaClasses.get(c), 1);
		    System.out.println(javaClasses.get(c));
	   	 }  

	     // Validate whether or not they are any Java Class Files in the Application Project 
  		 if(javaClasses.isEmpty() == false)
  		 {
  		    // Get the Methods Body separated into Files 	
  		    jc.getFilesMethodsBody(javaClasses, frlCon);
  		      
  		    // Validate the Input Method Configuration Parameter
  		    if(frlCon.inputMethod.equals(frlCon.dataInputMethod ))
  		    {
  		       // Create a new Database object	
  			   db = new Database(frlCon);
  			   
  			   // Generate the List of the Database Methods in the Application Project
  			   dbMethods = db.getDatabaseMethods(frlCon);
  			   
  		       // Generate the Method Hierarchy Call from the Method that performs Database Operations
  		       db.getMethodsTreeStructure(frlCon, dbMethods);  
  		       
  		       // Create a new Aspect Oriented Files object
  		       aof = new AspectOrientedFiles(frlCon);
  		     
  		       // Generate the Aspect Files into the Project Output Directory
  		       aof.generateAspectFiles(frlCon);
  		       
  		       System.out.println("");
  		       System.out.println("Good Bye from the Forensic Ready Logger: "+ getCurrentTime());
  		     
  		     }
   		  }
  		  else
  		     exitApplicationError("Error 4121: Some error(s) occurred while loading the Java Class Files from the Project Directory.");
	   }
	   else
	      exitApplicationError("Error 4122: Some error(s) occurred while loading the Forensic Ready Logger Configuration File.");

   }

}
