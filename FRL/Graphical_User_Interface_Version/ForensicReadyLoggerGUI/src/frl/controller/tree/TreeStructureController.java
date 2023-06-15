package frl.controller.tree;

import java.util.ArrayList;

import frl.model.configuration.ClassMethod;
import frl.model.configuration.FRLConfiguration;
import frl.model.tree.DatabaseMethod2;
import frl.model.tree.TreeStructure;
import frl.model.tree.TreeStructureDatabase;


public class TreeStructureController 
{
	
   // Declare a new object for the TreeStructureInitialDatabase class
   TreeStructureDatabase treeStrucDb = new TreeStructureDatabase();
   
   public void connect(String propFileName) throws Exception
   {
	   treeStrucDb.connect(propFileName);	 
   }
   
   public void disconnect() throws Exception
   {
	   treeStrucDb.disconnect(); 	   
   }
   
   public void deleteTreeStructure(int projectId) throws Exception //3
   {

      treeStrucDb.deleteTreeStructure(projectId);
      
   }  
   
   public int getLastIdTreeStructure(int projectId) throws Exception //4
   {
      int lastTreeStrucId;
      
      lastTreeStrucId = treeStrucDb.getLastIdTreeStructure(projectId);
      
      return lastTreeStrucId;

   }  
   
   public int getSpecificIdTreeStructure(String name, int projectId) throws Exception //5
   {
      int specTreeStrucId;
      
      specTreeStrucId = treeStrucDb.getSpecificIdTreeStructure(name, projectId);
      
      return specTreeStrucId;
   }
   
   public void saveTreeStructure(TreeStructure treeStructure) throws Exception  //6
   {
      treeStrucDb.saveTreeStructure(treeStructure);
   }
   
   public ArrayList<TreeStructure> loadTreeStructure(int projectId) throws Exception
   {
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();	   
	   
      treeStructures = treeStrucDb.loadTreeStructure1(projectId);
	   
      return treeStructures;   
   }
   
   public ArrayList<TreeStructure> loadTreeStructure2(int projectId) throws Exception
   {
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();	   
	   
      treeStructures = treeStrucDb.loadTreeStructure2(projectId);
	   
      return treeStructures;   
   }
   
   public void updateTreeStructure(FRLConfiguration frlCon, int projectId) throws Exception
   {
      treeStrucDb.updateTreeStructure(frlCon, projectId);
   }   
   
   public ArrayList<String> getLibNonPrimRetType(String programmingLanguage, int projectId) throws Exception
   {
      ArrayList<String> libraries = new ArrayList<>();
	   
	  libraries = treeStrucDb.getLibNonPrimRetType(programmingLanguage, projectId);
	   
	  return libraries;
   } 
   
   public ArrayList<ClassMethod> loadMethods(int projectId, int DBMethodFlag) throws Exception //5
   {
      ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();   
		   
      classMethodList = treeStrucDb.loadMethods(projectId, DBMethodFlag);
		   
      return classMethodList;    
   }
   
   public void createTreeStructureDBClasses(int projectId, String projectInputDir) throws Exception //5
   {
      treeStrucDb.createTreeStructureDBClasses(projectId, projectInputDir);
   }
      
}
