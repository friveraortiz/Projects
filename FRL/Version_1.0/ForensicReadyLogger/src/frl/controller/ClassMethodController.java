package frl.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import frl.model.ClassMethod;
import frl.model.ClassMethodDatabase;

public class ClassMethodController 
{
	
   // Declare a new object for the ClassMethods Database class
   ClassMethodDatabase classMetDb = new ClassMethodDatabase();
   
   public void connect(String propFileName) throws Exception //1
   {
	   classMetDb.connect(propFileName);	
	   
   }
   
   public void disconnect() //2
   {
	   
      classMetDb.disconnect(); 	   
	   
   }
   
   public void deleteClassMethod() throws Exception //3
   {

      classMetDb.deleteClassMethod();
      	   
   }
   
   public void saveClassMethod(ClassMethod classMethod) throws Exception //4
   {
	  
      classMetDb.saveClassMethod(classMethod);

   }
   
   public ArrayList<ClassMethod> load(String inputClassName) throws Exception //5
   {
      // Declare ArrayList of ClassMethod
	  ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
	  
      classMetList = classMetDb.load(inputClassName);
      	   
      return classMetList;   
   }
   
   public void updateEndLineNumbers(int classMethodId, int endLineNumber) throws Exception //6
   {
	  
      classMetDb.updateEndLineNumbers(classMethodId, endLineNumber);

   }
   
   public ArrayList<ClassMethod> loadZeroEndLineNumbers() throws Exception //7
   {
      // Declare ArrayList of ClassMethod
	  ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
	  
      classMetList = classMetDb.loadZeroEndLineNumbers();
      	   
      return classMetList;   
   }
   
   public void updateTextFileName(int classMethodId, String textFileName) throws Exception //8
   {
	  
      classMetDb.updateTextFileName(classMethodId, textFileName);

   }
   
   
   public ClassMethod loadShortMethodName1(String packageName1, String className1, String shortMethodName1) throws SQLException //11
   {
	   ClassMethod cm;
	   
	   cm = classMetDb.loadShortMethodName1(packageName1, className1, shortMethodName1);
	   
	   return cm;
   }
   public ClassMethod loadShortMethodName2(String packageName1, String className1, String textFileName1) throws SQLException //11
   {
      ClassMethod cm;
	   
	  cm = classMetDb.loadShortMethodName2(packageName1, className1, textFileName1);
	   
	  return cm;
   }
   
	
}
