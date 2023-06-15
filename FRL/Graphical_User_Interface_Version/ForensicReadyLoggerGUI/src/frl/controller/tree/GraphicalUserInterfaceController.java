package frl.controller.tree;
import java.util.ArrayList;

import frl.model.tree.GraphicalUserInterfaceDatabase;

public class GraphicalUserInterfaceController 
{
   // Declare a new object for the GraphicalUserInterfaceDatabase class
   GraphicalUserInterfaceDatabase graphUserIntDb = new GraphicalUserInterfaceDatabase();
   
   public void connect(String propFileName) throws Exception //1
   {
	   graphUserIntDb.connect(propFileName);	 
   }
   
   public void disconnect() throws Exception
   {
	   graphUserIntDb.disconnect(); 	   
   }
   
   public String loadOneGui(int projectId, String programmingLanguage, String guiName) throws Exception //5 
   {
	  String name = ""; 
	  
	  name = graphUserIntDb.loadOneGui(projectId, programmingLanguage, guiName);
	    
      return name;   
   }
   
   public ArrayList<String> loadAllGui(int projectId, String programmingLanguage) throws Exception //6  
   {
      ArrayList<String> guiList = new ArrayList<String>();
      
      guiList = graphUserIntDb.loadAllGui(projectId, programmingLanguage);
      
      return guiList;
   }
   
   	
}
