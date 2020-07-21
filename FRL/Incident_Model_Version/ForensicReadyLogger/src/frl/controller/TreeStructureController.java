package frl.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import frl.configuration.FRLConfiguration;
import frl.model.DatabaseMethod2;
import frl.model.TreeStructure;
import frl.model.TreeStructureDatabase;


public class TreeStructureController 
{
	
   // Declare a new object for the TreeStructureInitialDatabase class
   TreeStructureDatabase treeStrucDb = new TreeStructureDatabase();
   
   public void connect(String propFileName) throws Exception
   {
	   treeStrucDb.connect(propFileName);	 
   }
   
   public void disconnect() //2
   {
	   treeStrucDb.disconnect(); 	   
   }
   
   public void deleteTreeStructure() throws SQLException //3
   {

      treeStrucDb.deleteTreeStructure();

   }  
   
   public int getLastIdTreeStructure() throws SQLException //4
   {
      int lastTreeStrucId;
      
      lastTreeStrucId = treeStrucDb.getLastIdTreeStructure();
      
      return lastTreeStrucId;

   }  
   
   public int getSpecificIdTreeStructure(String name) throws SQLException //5
   {
      int specTreeStrucId;
      
      specTreeStrucId = treeStrucDb.getSpecificIdTreeStructure(name);
      
      return specTreeStrucId;
   }
   
   public void saveTreeStructure(TreeStructure treeStructure) throws SQLException  //6
   {

      treeStrucDb.saveTreeStructure(treeStructure);

   }
   
   public ArrayList<TreeStructure> loadTreeStructure1() throws SQLException
   {
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();	   
	   
      treeStructures = treeStrucDb.loadTreeStructure1();
	   
      return treeStructures;   
   }
   
   public ArrayList<TreeStructure> loadTreeStructure2() throws SQLException
   {
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();	   
	   
      treeStructures = treeStrucDb.loadTreeStructure2();
	   
      return treeStructures;   
   }
   
   public void updateTreeStructure(FRLConfiguration frlCon) throws SQLException
   {
      treeStrucDb.updateTreeStructure(frlCon);
   }   
   
   public ArrayList<DatabaseMethod2> loadDBMethodsDetails() throws SQLException
   {
      ArrayList<DatabaseMethod2> dbMetDets = new ArrayList<DatabaseMethod2>();	   
		   
      dbMetDets = treeStrucDb.loadDBMethodsDetails();
		   
      return dbMetDets;    
   }	
   
	public ArrayList<String> getLibNonPrimRetType(String programmingLanguage) throws SQLException
	{
	   ArrayList<String> libraries = new ArrayList<>();
	   
	   libraries = treeStrucDb.getLibNonPrimRetType(programmingLanguage);
	   
	   return libraries;
	}   
      
   	
}
