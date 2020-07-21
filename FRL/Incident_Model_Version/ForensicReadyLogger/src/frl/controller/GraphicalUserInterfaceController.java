package frl.controller;
import java.sql.SQLException;
import java.util.ArrayList;

import frl.model.GraphicalUserInterfaceDatabase;


public class GraphicalUserInterfaceController 
{
	
   // Declare a new object for the GraphicalUserInterfaceDatabase class
   GraphicalUserInterfaceDatabase graphUserIntDb = new GraphicalUserInterfaceDatabase();
   
   public void connect(String propFileName) throws Exception //1
   {
	   graphUserIntDb.connect(propFileName);	 
   }
   
   public void disconnect() //2
   {
	   graphUserIntDb.disconnect(); 	   
   }
   
   
   public String loadOneGui(String programmingLanguage, String guiName) throws Exception //3
   {
	  String name = ""; 
	  
	  name = graphUserIntDb.loadOneGui(programmingLanguage, guiName);
	    
      return name;   
   }
   
   public ArrayList<String> loadAllGui(String programmingLanguage) throws SQLException //6
   {
      ArrayList<String> guiList = new ArrayList<String>();
      
      guiList = graphUserIntDb.loadAllGui(programmingLanguage);
      return guiList;
   }
   	
}
