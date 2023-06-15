package frl.process.tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import frl.controller.tree.GraphicalUserInterfaceController;
import frl.controller.tree.TreeStructureController;
import frl.model.configuration.ClassMethod;
import frl.model.configuration.FRLConfiguration;
import frl.model.tree.ChildrenMethod;
import frl.model.tree.NodeType;
import frl.model.tree.TreeStructure;

public class MethodHierarchy 
{
	
   public MethodHierarchy ()
   {
		
   }
   
   public void deleteTreeStructure(String filePath, int projectId) throws Exception
   {
      String errorMessage1="";
	  TreeStructureController treeStrucCon = new TreeStructureController();
	   
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.connect(filePath);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     throw new Exception(errorMessage1);
	  }
		  
	  // Eliminate all the records in the tree_structure table
	  try 
	  {
	     treeStrucCon.deleteTreeStructure(projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
	     throw new Exception(errorMessage1);
	  } 
		  
	  // Disconnect from the Database
	  try
	  {
	     treeStrucCon.disconnect();
	  }   
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
	     throw new Exception(errorMessage1);
	  }
		 
   }
	
   public int getId(String filePath, NodeType nodeType, int projectId) throws Exception
   {
      int lastId = 0, id= -1;	
	  String errorMessage1="";
	  TreeStructureController treeStrucCon = new TreeStructureController();
	   
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.connect(filePath);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 id = -1;
		 throw new Exception(errorMessage1);
	  }
		  
	  // Validate if there is any error Message
	  if(errorMessage1.isEmpty() == true)
	  {

	      if(nodeType == NodeType.Root) 
	 	     id = 0;
	      else
	      { 	
	         try 
	         {
		        lastId = treeStrucCon.getLastIdTreeStructure(projectId);
		        id = lastId;
		        
		        //System.out.println("ID FROM DATABASE: " + id);
		        
		        id++;
		     } 
	         catch (Exception e2) 
	         {
	            errorMessage1 = e2.getMessage();
	            id = -1;
	            throw new Exception(errorMessage1);
		     }
	    
	       }
	      
	       // Disconnect from the Database
		   try
		   {
		      treeStrucCon.disconnect();
		   }	    
		   catch (Exception e3) 
		   {
		      errorMessage1 = e3.getMessage();
		      id = -1;
		      throw new Exception(errorMessage1);
		   }
	      
	  }
	 
      return id;	
		
   }
   
   public int getParentId(String nodeType, int id)
   {
      int parentId = 0;	
	   
	  switch(nodeType)
	  {
	     case "Root":
	        parentId = -1;
	     	break;
	     	 
	     case "Node":
	        parentId = 0;
	     	break;
	     	 
	     case "Internal":	
	     	parentId = id;
	     	break;
	     	 
	     case "Leaf":	
	     	parentId = id;
	     	break;
	  }  	 

      return parentId;	
		
   }
	
   public String saveElementTreeStructure(int projectId, int id, String method, NodeType nodeType,  
			                              int parentId, int recursiveLevel, String filePath)
   {
      TreeStructure treeStructure = null;
	  String errorMessage1 = "";
	  TreeStructureController treeStrucCon = new TreeStructureController();
		  
	  // Create a new element of the treeStructure class
	  treeStructure = new TreeStructure(projectId, id, method, nodeType, parentId, recursiveLevel);
		  
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.connect(filePath);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     return errorMessage1;
	  }
		 
	  // Save the Method into a new tree structure record in the tree_structure table
      try 
	  {
	     treeStrucCon.saveTreeStructure(treeStructure);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 return errorMessage1;
	  }
		  
	  // Disconnect from the Database
	  try
	  {
	     treeStrucCon.disconnect();
	  }	    
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 return errorMessage1;
	  }
		  
      return errorMessage1;	  
   }
   
   public ArrayList<String> readMethodBodyFiles(FRLConfiguration frlCon) throws Exception
   {
      String fileName, seqDiagTxtFile, seqDiagPngFile, errorMessage1="";
	  String[] extensions = new String[] {frlCon.textFileExtName};
	  File dir = new File(frlCon.projectOutputDir);
	  ArrayList<String> javaFiles = new ArrayList<String>();
		  
	  // Validate that the Input Directory exists
	  if(dir.exists() && dir.isDirectory()) 
	  {	  
	     List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		 for (File file : files) 
		 {
		    // Convert the fileName from File type to String type 
			fileName = file.toString(); 
			seqDiagTxtFile = frlCon.projectOutputDir + frlCon.umlSeqDiagTextFileName1;
			seqDiagPngFile = frlCon.projectOutputDir + frlCon.umlSeqDiagPngFileName1;
				
			// Validate that the MethodBody file is different to the following files: 
			// - FRLConfiguration.umlSeqDiagTextFileName1 and
			// - FRLConfiguration.umlSeqDiagPngFileName1
			if( !fileName.equals(seqDiagTxtFile) && !fileName.equals(seqDiagPngFile)) 
			   javaFiles.add(file.getAbsolutePath().toString());
		  }
			  
	  }	 
	  else
	  {
	     errorMessage1 = "Error 3191: Occurred because the Ouput Project Directory: "+ frlCon.projectOutputDir + " does not exists."; 
	     throw new Exception(errorMessage1);
	  }
		  
      return javaFiles;
		 
   } 
	
   public String[] splitString(String str, String substr)
   {
      String[] parts = null;
		  
	  parts  = str.split(substr);
		
      return parts;	
   }
	
   public String getSuperClassGUI(String inputPackage, String inputClass, String objOrientedDel) throws Exception
   {

	  String errorMessage1="", errorMessage2="", superClass="";
	  Class<?> newClass = null, superclz = null;
	  
	  /*
	  if(inputClass.contains("service1"))
	     inputClass = "service";
	     */
	  /*
	  System.out.println("Inside the getSuperClass method ...");
	  System.out.println("Package               : " + inputPackage);
	  System.out.println("Class                 : " + inputClass);
	  
	  System.out.println("Object Oriented Del   : " + objOrientedDel);
	  */
	  
      // Obtain the Class and SuperClass of the current Method Body File
	  
	  //System.out.println("11.3.1");
	  
	  // Create a new class 
	  try 
	  {
		 //System.out.println("New Class Attempt: " + inputPackage + objOrientedDel + inputClass);
		  
	     newClass = Class.forName(inputPackage + objOrientedDel + inputClass);
	     
	     //System.out.println("New Class   : " + newClass);
	  } 
	  catch (ClassNotFoundException e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while attempting to create a New Class: " + newClass + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Package : " + inputPackage + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Class   : " + inputClass   + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     
	     throw new Exception(errorMessage2);
	  } 
			  		  		  
	  // Get the superclass from the class
	  //System.out.println("11.3.2");
	  
	  try
	  {
	     superclz = newClass.getSuperclass();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while obtaining the Super Class : " + superclz + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "New Class : " + newClass + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;		     
		 throw new Exception(errorMessage2);
	  } 
	  
	  //System.out.println("Super Clz: " + superclz );
	  
	  //System.out.println("11.3.3");
	  
	  if(superclz != null ) 
	  {
	     try
	     {
	        superClass = superclz.toString();
	     }
	     catch (Exception e3) 
	     {
	        errorMessage1 = e3.getMessage();
		    errorMessage2 = "Error XXXX: Occurred while converting to String the Super Class : " + superclz + System.lineSeparator();
		    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;		     
		    throw new Exception(errorMessage2);
	     } 
	  }
	  else
	     superClass = "";
	  
	  //System.out.println("FINAL Super Class: " + superClass );
	   
      return superClass;  
   }

   public boolean validateGUISuperClass(int projectId, String inputSuperClass, 
		                                FRLConfiguration frlCon, GraphicalUserInterfaceController guiCon) throws Exception
   {
      String part1 = "", part2 = "", regex="";
	  String guiSuperClass, guiName = "", errorMessage1="";
	  String[] parts;
	  boolean flag = false;	 
	   
	  // Separate the components of the inputSuperClass using the delimiter "."
	  parts = inputSuperClass.split(frlCon.findString + frlCon.objectOrientedDelimiter1);
		  
	  // Validate the parts Array is not empty
	  if (parts.length > 0) 
	  {	   
	     part1 = parts[0];
		 part2 = parts[1];
		 regex = frlCon.startFindWordWhiteSpaces + frlCon.bluePrintObject1 + frlCon.endFindWordWhiteSpaces;
		 part1 = part1.replaceAll(regex, "");
		 guiSuperClass = part1 + frlCon.objectOrientedDelimiter1 + part2;

		 // Validate if the inputSuperClass exists in the Forensic Ready Database
	     try 
	     {
		    guiName = guiCon.loadOneGui(projectId, frlCon.programmingLanguage, guiSuperClass);
		 } 
	     catch (Exception e1) 
	     {
	 	    errorMessage1 = e1.getMessage();
	 		throw new Exception(errorMessage1);
		 }
	  }
	  else
	  {	  
	     errorMessage1 = "Error XXXX: Occurred while separating the components of the Super Class String.";
		 throw new Exception(errorMessage1);
	  }	  

	  // Validate that the guiName is not empty
	  if(guiName != null && !guiName.isEmpty()) 
	     flag = true;
	  else
	     flag = false;
	   
      return flag;	
   }

   public String getVariable(String inputLine, String methodName, FRLConfiguration frlCon) //3
   {
      String variable = "", element, beforeParenthesis;
	  String[] whiteSpaces = inputLine.split(frlCon.findWhiteSpaces), parts;
	  int parentPos, i;
	   		
	  for(i = 0; i < whiteSpaces.length; i++)
	  {
	 	  element = whiteSpaces[i];
	 	  
	 	  if(element.contains(methodName))
	 	  {
		     parts = splitString(element, frlCon.objectOrientedDelimiter1 + methodName);

		     // Validate the parts Array
		     if (parts.length > 0) 
		     {	
		        variable = parts[0];
				
				// Validate if the variable has an special character equals to "("
	            parentPos = variable.indexOf(frlCon.startParameters);
	            
	            if(parentPos > 0)
	            {	
				   beforeParenthesis = variable.substring(0, parentPos);
				   variable = beforeParenthesis;
	            }
				
				// Validate if the variable does not start with a comment (//)
				if(variable.startsWith(frlCon.singleLineComment))
				   variable = "";	
		     }   
		     else
		        variable = ""; 
		     
		     // Validate if the variable contains two words separated by equal
		     if(variable.contains(frlCon.assignValueDelimiter))
		     {
		        parts    = variable.split(frlCon.assignValueDelimiter);
		        variable = parts[1];
		     }

	 	  }
	   }
	 	  
	   return variable;	  
	   
	}

	public ArrayList<String> getMethodBodyFilesVariables(String fileName, String childMethodName, FRLConfiguration frlCon) throws Exception
	{
	   String errorMessage1="", errorMessage2="", regex="", var="", line="", lineStr="";
       int lineNum = 0; 
	   boolean flag1 = false; 
	   final Charset ENCODING = StandardCharsets.UTF_8;   
	   Path path;
	   ArrayList<String> variables = new ArrayList<String>();
	   Pattern pattern;
	   Matcher matcher;

	   path  = Paths.get(fileName);
	   regex = frlCon.findWord + frlCon.objectOrientedDelimiter1 + childMethodName + frlCon.findWord;
	   
	   try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	   {
		   
	      lineStr = null;
		  lineNum = 1;
		  
		  while ((lineStr = reader.readLine()) != null) 
		  {
		     pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		     matcher = pattern.matcher(lineStr);
			 var = "";
				
			 while (matcher.find())
			 {
			    // If lineNumber is greater than 1, it means it is in the body of the method body file
				// then obtain the variables
				if(lineNum > Integer.parseInt(frlCon.startLineNum))
				{
	               line  = lineStr.trim();
	               flag1 = line.startsWith(frlCon.singleLineComment) || line.startsWith(frlCon.printMessage);
	               
				   // Validate that the line is not a comment
				   if( flag1 == false)
				      var = getVariable(line, childMethodName, frlCon);
				}
				
				// Validate that the variable is not empty or null
				if (var != null && !var.equals("")) 
				   variables.add(var);
			 }
				
			 lineNum ++;
		 		 
		  }      
	   } 
	   catch (IOException e) 
	   {
	      errorMessage1 = e.getMessage();
	      errorMessage2 = "Error XXXX: Occurred while loading the Variables for the Method: " + childMethodName;
	      errorMessage2 = errorMessage2 + "and the File: " + fileName + "\n";
	      errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	      throw new Exception(errorMessage2);
	   }
		  
	   return variables;
	
	}

   public boolean getClassVariable1(String packageMethodBody, String classMethodBody,
			                        String packageChild, String classChild, 
			                        String variable, String objOrientedDel) throws Exception
   {

	  String errorMessage1="", errorMessage2="", currField="";
      int c=0; 
	  boolean flag = false;
	  Field[] fields;
	  String[] parts;
	  Class<?> newClass   = null; 
		
	  // Get the Class using Java Reflection using the MethodBody details
	  try 
	  {
	     newClass = Class.forName(packageMethodBody + objOrientedDel + classMethodBody);
	  } 
	  catch (ClassNotFoundException e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while creating a new Class: "+ classMethodBody +"\n";
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
		   
	  // Get the Fields from the Class using Java Reflection
	  fields = newClass.getDeclaredFields();

	  // Loop of the fields array
	  for (c = 0; c < fields.length; c++)
	  {	  
	     currField = fields[c].toString();
		  
		 if(currField.contains(variable))
		 {
		    parts = currField.split(" ");
		  		
		  	// Validate the parts Array is not empty
		    if (parts.length > 0) 
		    {
		  	   // Validate if the parts[0] contains the packageChild.classChild
		  	   if(parts[0].equals(packageChild + objOrientedDel + classChild))
		  	      flag = true;
		  	   else
		  	      flag = false;
		    }
		    else
		    {
		       flag = false;
			   errorMessage1 = "Error XXXX: Occurred while separating the String: "+ currField +"\n";
			   throw new Exception(errorMessage1);
		    } 	
		  		
		 }
		  
	  } 
		  
      return flag;		  
		
   }
	
   public boolean getClassVariable2(String filePathMethodBody, 
		                            String packageChild, 
	                                String classChild, 
	                                String variable, 
	                                String findWordRegExp) throws Exception
   {
      String errorMessage1="", errorMessage2="", lineStr="",  regex="";
	  boolean flag=false;
	  Path path;
	  final Charset ENCODING = StandardCharsets.UTF_8;
      Pattern pattern;
	  Matcher matcher;
	  
	  regex = findWordRegExp + classChild + " " + variable + findWordRegExp;
	  path  = Paths.get(filePathMethodBody);
	  
	  /*
	  System.out.println("Inside Get Class Variable2 Method ...");
	  System.out.println("File  : " + filePathMethodBody);
	  System.out.println("Regex : " + regex);
	  System.out.println("Path  : " + path);
	  
	  System.out.println("11.7.2.1");
	  */
	  
	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {		  
	     lineStr = null;
		  
		 while ((lineStr = reader.readLine()) != null) 
		 {

			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(lineStr);
			
			// If the regular expression was found, turn on the flag
			if (matcher.find())
		       flag = true;
	     }
		 
      } 
	  catch (IOException e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while loading the File: " + filePathMethodBody + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Variable: " + variable + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Looking for: " + regex + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	  }
			
      return flag;  
   }
	
   public boolean validateChildMethodVariables(String packageMethodBody, String classMethodBody, 
			                                   String filePathMethodBody, String packageChild, 
			                                   String classChild, ArrayList<String> variables,
			                                   FRLConfiguration frlCon) throws Exception
   {   
	  String errorMessage1="", var="";
      int c = 0; 
	  boolean found1 = false, found2 = false, found3 = false;

	  //System.out.println("Inside the validate Child Method Variables method ");
	  
	  // Loop for all the variables
	  while (c < variables.size()) 
	  {
	     // Get the current variable 
		 var = variables.get(c);
		   
	     // Validate if the variable declared in the packageMethodBody and classMethodBody
		 // has the packageChild and classChild
		 try 
		 {
		    found1 = getClassVariable1(packageMethodBody, classMethodBody, packageChild, classChild, var, frlCon.objectOrientedDelimiter1);
		 } 
		 catch (Exception e1) 
		 {
            errorMessage1 = e1.getMessage();
            //System.out.println("Error 11.7.1 : " + errorMessage1);
            throw new Exception(errorMessage1);
		 }
			 
		 if(found1 == false)
		 {
		    try 
		    {	
			   found2 = getClassVariable2(filePathMethodBody, packageChild, classChild, var, frlCon.findWord); 
		    }
			catch (Exception e2) 
			{
	           errorMessage1 = e2.getMessage();
	           //System.out.println("Error 11.7.2 : " + errorMessage1);
	           throw new Exception(errorMessage1);
			}
		 }   
		    
	     c++;
      }	
		  
	  // Validate the flags to obtain the value of flag3
	  if(found1 == true || found2 == true)
	     found3 = true;	 
	  else
	     found3 = false;
		  
      return found3;
   } 
	
   public ArrayList<ChildrenMethod> getChildrenMethods(int projectId,
		                                               int parentId, 
		                                               String method, 
		                                               NodeType nodeType, 
		                                               FRLConfiguration frlCon) 
		                                               throws Exception //13
   {
      String errorMessage="", methodBodyFile="", packageMethod="", classMethod="", fileMethod="", nameMethod="", 
    		 fullName="", packageMetBod="", classMetBod="", fileMetBod="", superClassMetBod="";
	  String[] parts1, parts2;	  
	  boolean guiFlag=false, classMetFlag=false;
	  int i=0;
	  ChildrenMethod metFile = null;
	  java.util.Date currentTime;
	  ArrayList<String> methodBodyFiles = new ArrayList<String>();	
	  ArrayList<String> variables1 = new ArrayList<String>();
	  ArrayList<String> variables2 = new ArrayList<String>();
	  ArrayList<ChildrenMethod> metFiles = new ArrayList<>();

	   
	  // AQUI VOY
	  
	  // Initialize the arraylists
	  methodBodyFiles.clear();
	  metFiles.clear();
	  variables1.clear();
	  variables2.clear();
		  
/*
	  currentTime = new java.util.Date(); 
	  

	  System.out.println("");
	  System.out.println("STARTING the Get Children Methods at: " + currentTime);
	  */
	  	  
	  
	  // Split the parts of the method
	  parts1 = method.split(Pattern.quote(File.separator));
		   
	  packageMethod = parts1[0];
	  classMethod   = parts1[1];
	  fileMethod    = parts1[2];
	  nameMethod    = fileMethod.split(frlCon.findDot)[0];
		 
      //System.out.println("11.1");
      
	  // Load the Method Body Files from the Project Output Directory
	  try 
	  {
	     methodBodyFiles = readMethodBodyFiles(frlCon);
	  } 
	  catch (Exception e1) 
	  {
         errorMessage = e1.getMessage();

         //System.out.println("Error: " + errorMessage);
         throw new Exception(errorMessage);
	  }
	  
	  //System.out.println("Method Body Files: " + Arrays.deepToString(methodBodyFiles.toArray()));
	   
	  // Validate if the inputSuperClass exists in the Forensic Ready Database
	  GraphicalUserInterfaceController guiCon = new GraphicalUserInterfaceController();
	
	  //System.out.println("11.2");
	     
	  // Connect to the Database
	  try 
	  {
	     guiCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();

         //System.out.println("Error: " + errorMessage);
	     throw new Exception(errorMessage);
	  }
	  
	  
	  // For every Method Body File Loop 	
	  for(i = 0; i < methodBodyFiles.size(); i++) 
	  {
		  
	     methodBodyFile = methodBodyFiles.get(i); 
	     
	     /*
		 System.out.println("Method Body File: " + i + " of " + methodBodyFiles.size()); 
		 System.out.println("Method Body File : " + methodBodyFile);
		 */
	     
	     /*
	     if(methodBodyFile.contains("MissingJavaLibraries") == true)
	        System.out.println("Method CONTAINS THE WORD Missing Java Libraries : " + methodBodyFile);
	       */ 
	     
	     if(methodBodyFile.contains("MissingJavaLibraries") == false)
	     {	 
	    	 
	        fullName = frlCon.projectOutputDir + File.separator + frlCon.projectName + File.separator;
	     
	        //System.out.println("Full Name: " + fullName);
	        
	        /*   
	        System.out.println("Method Body File : " + methodBodyFile);
	        System.out.println("Full Name        : " + fullName);
	        System.out.println("Pattern Quote    : " + Pattern.quote(fullName));
	        System.out.println("BEFORE Split String METHOD ...");
	        */
	        
	        parts1 = splitString(methodBodyFile, Pattern.quote(fullName));
	        
	        /*
	        System.out.println("AFTER Split String METHOD ...");
	     
	        System.out.println("Parts 1          : " + Arrays.deepToString(parts1)); 
	        */
	     
	        // Validate the parts Array is not empty
		    if (parts1.length > 0) 
		    {
		       fileMetBod = parts1[1];
		      
		       // Split the parts of the node
		       parts2 = parts1[1].split(Pattern.quote(File.separator));
		    
		       
		       /*
		       System.out.println("File Met Bod     : " + fileMetBod);
		       System.out.println("Parts 2          : " + Arrays.deepToString(parts2)); 
		       */
		    
		       // Validate the parts Array is not empty
		       if (parts2.length > 0) 
		       {
		          packageMetBod = parts2[0];
		          classMetBod   = parts2[1];

		          /*
		          System.out.println("Package Met Bod : " + packageMetBod);
		          System.out.println("Class   Met Bod : " + classMetBod);
		          */
		       
		       
                  if(packageMetBod.contains("service1"))
                  {	   
            	  //System.out.println("*** WE NEED TO REPLACE THE WORD SERVICE1 FOR SERVICE ***");
            	  //System.out.println("INITIAL Package Met Bod : " + packageMetBod);
            	  
            	     packageMetBod = packageMetBod.replaceAll("service1", "service");
                  //System.out.println("FINAL Package Met Bod: " + packageMetBod);
                  }   
		       
               
		       //System.out.println("FINAL Class   Met Bod : " + classMetBod);
		       
		       
	              //System.out.println("11.3");
	           
		          // Obtain the superClass of the Method Body File
		          try
		          {
		 	         superClassMetBod = getSuperClassGUI(packageMetBod, classMetBod, frlCon.objectOrientedDelimiter1);
		 	         //System.out.println("Super Class Met Bod: " + superClassMetBod);
		          }
		          catch(Exception e3)
		          {
		             errorMessage = e3.getMessage();

		           //System.out.println("Error 11:3: " + errorMessage);
		              throw new Exception(errorMessage);
		          }
		 	

		          if(superClassMetBod != null && !superClassMetBod.trim().isEmpty()) 
		          {
		    	  //System.out.println("11.4");
		    	   
		 	         // Validate if is equal to a graphical interface
		             try
		             {
		 	            guiFlag = validateGUISuperClass(projectId, superClassMetBod, frlCon, guiCon);
			         //System.out.println("GUI Flag: " + guiFlag);
		             }
		             catch(Exception e4)
		             {
		                errorMessage = e4.getMessage();

		             //System.out.println("Error 11.4.1: " + errorMessage);
		                throw new Exception(errorMessage);
		             }
		 	   
		       
		 	         if (guiFlag == false)
		 	         {
			         //System.out.println("11.5");
			         
		 		        try
		 		        {
			               variables1 = getMethodBodyFilesVariables(methodBodyFile, nameMethod, frlCon);
			         
				        //System.out.println("Variables 1: " + variables1);
				       
			            }
			            catch(Exception e5)
			            {
			               errorMessage = e5.getMessage();

			            //System.out.println("Error 11.5: " + errorMessage);
			               throw new Exception(errorMessage);
			            }
			    		
	 	                if (variables1.isEmpty() == false) 
	                    {
	 	        	    //System.out.println("11.6");
	 	        	 
	 		               // Delete the duplicate elements from variables1 ArrayList and copy to a new arraylist: variables2	
	                       variables2 = (ArrayList<String>) variables1.stream().distinct().collect(Collectors.toList());
	                 
	                    //System.out.println("Variables 2: " + variables2);

	                       // Validate that the variables array is not empty
	                       if(variables2.isEmpty() == false)
	                       {
	                       //System.out.println("11.7");
	                       
	                          // Validate if the variables are declared as ClassMethod
	                	      try
	                	      {
	                             classMetFlag = validateChildMethodVariables(packageMetBod, classMetBod, 
	  	                                                                     methodBodyFile, packageMethod, 
	  			                                                             classMethod, variables2, 
	  			                                                             frlCon);
	                       
	                          //System.out.println("Class Met Flag: " + classMetFlag);
	                	      }
	                	      catch(Exception e6)
	                	      {
	                             errorMessage = e6.getMessage();	
	
	     		              //System.out.println("Error 11.7: " + errorMessage);
	                             throw new Exception(errorMessage);
	                	      }
	  		
	                	   //System.out.println("11.8");
	                	
	                          // Validate if the variables belong to the ClassMethod
	                          // Add to the ArrayList
	                          if (classMetFlag == true)
	                          {   
	                             metFile = new ChildrenMethod(parentId, fileMetBod, nodeType);
	                       
	                          //System.out.println("Met File: " + metFile);
	                       
	 	                         metFiles.add(metFile);
	                          }   
	                    
	                       }
	  		
	                    } 
 		
                     }
		          }
        
               }  
               else
               {	
                  errorMessage = "Error XXXX: Occurred while separating in the Second Level the Children Methods.";
		          //System.out.println("Error 11.9: " + errorMessage);
                  throw new Exception(errorMessage);
               }   
	        }
		    else
		    {	 
		       errorMessage = "Error XXXX: Occurred while separating in the First Level the Children Methods.";
		       //System.out.println("Error 11.10: " + errorMessage);
		       throw new Exception(errorMessage);
		    }  
		    
	     }
		 
      }
	  
	  
   
	  //System.out.println("11.11");
	     
      // Disconnect from the Database
	  try
	  {
         guiCon.disconnect();
	  }
	  catch(Exception e7)
	  {
	     errorMessage = e7.getMessage();

         //System.out.println("Error: " + errorMessage);
	     throw new Exception(errorMessage);
	  }
	  
	  /*
	  System.out.println("");
	  System.out.println("ENDING the Get Children Methods at: " + currentTime);
	  */
	  
	  //System.out.println("Met Files: " + Arrays.deepToString(metFiles.toArray()));
	  
      return metFiles;
      
   }

   public boolean getChildrenNodes(ArrayList<ChildrenMethod> childrenMethods1,
		                           int level1, 
		                           FRLConfiguration frlCon, 
		                           int projectId) throws Exception
   {
	
      int nodeId=0, level=0, parentId=0, c=0;
      String errorMessage="", method="";
      boolean grandChildFlag=false, getChildrenNodes=false;
      NodeType nodeType = null;
      ArrayList<ChildrenMethod> childrenMethods2 = new ArrayList<>();
      ChildrenMethod childMethodRow = null;
   
      // Initialize the childenMethods2 ArrayList
      childrenMethods2.clear();
      level = level1;
   
      //System.out.println("Inside the Get Children Nodes method ...");
      
      //for (ChildrenMethod childMethodRow : childrenMethods1)
      for (c = 0; c < childrenMethods1.size(); c++)  		
      { 
				
    	 childMethodRow = childrenMethods1.get(c);
    	  
         parentId = childMethodRow.getParentId();
	     method   = childMethodRow.getMethodName();
	     nodeType = childMethodRow.getNodeType();
	     
	     /*
	     System.out.println("Child Method : " + c + " of " + childrenMethods1.size());
	     
	     System.out.println("Parent Id : " + parentId);
	     System.out.println("Method    : " + method);
	     System.out.println("Node Type : " + nodeType);
	     
	     
	     System.out.println("11.1");
	     */
	     
		 // Get the Id for this GrandChildMethod
		 try 
		 {
	        nodeId = getId(frlCon.databaseConfigFile, nodeType, projectId);
	        //System.out.println("Node Id : " + nodeId);
		 } 
		 catch (Exception e1) 
		 {
			errorMessage = e1.getMessage();
			//System.out.println("ERROR MESSAGE: " + errorMessage);
			throw new Exception(errorMessage);
		 }
		
		 //System.out.println("11.2");
		 
		 // Save the Children Node into the Database
		 errorMessage = saveElementTreeStructure(projectId, nodeId, method, nodeType, parentId,
						                         level, frlCon.databaseConfigFile);
					
		 //System.out.println("ERROR MESSAGE: " + errorMessage);
		 
		 if(errorMessage.isEmpty() == false)
		 {	  
		    getChildrenNodes = false; 
		    
		    throw new Exception(errorMessage);
		 }
		 else
		    getChildrenNodes = true;
      
		 //System.out.println("Get Children Nodes : " + getChildrenNodes);
		 
		 //ystem.out.println("11.3");
		 
		 nodeType = NodeType.Internal;
		 
		 //System.out.println("Node Type : " + nodeType);
			 
		 // Get the nodeParentId
		 parentId = getParentId(nodeType.name(), nodeId);
			  
		 //System.out.println("11.4");
		 
		 //System.out.println("Parent Id : " + parentId);
		 
		 // Check if the current Node has more children
		 try 
		 {
		    childrenMethods2 = getChildrenMethods(projectId, parentId, method, nodeType, frlCon);
		    
		    //System.out.println("Children Methods 2 : " + childrenMethods2);
		 }
		 catch(Exception e2)
		 {
		    errorMessage = e2.getMessage();	 
		    //System.out.println("ERROR MESSAGE: " + errorMessage);
			throw new Exception(errorMessage);
		 }
		 
		 //System.out.println("11.5");
		 
		 // Remove null elements
		 childrenMethods2.removeAll(Collections.singleton(null));
				
		 // Remove duplicate elements
		 childrenMethods2 = (ArrayList<ChildrenMethod>) childrenMethods2.stream().distinct().collect(Collectors.toList());

		 if (childrenMethods2.isEmpty() == false) 
	     { 
		    grandChildFlag = true;
	     }
		 
		 //System.out.println("Grand Child Flag : " + grandChildFlag);
				 
      }
   
      //System.out.println("11.6");
      
	  // Validate if childrenMethods2 ArrayList is empty or not
	  if (grandChildFlag == true) 
      { 
	     level++;
	     
	     //System.out.println("Level: " + level);
	  
         // Get the current Children Methods of this node
	     try
	     {
	        getChildrenNodes(childrenMethods2,
                             level, 
                             frlCon, projectId);
	     }
	     catch(Exception e3)
	     {
	        errorMessage = e3.getMessage();	 
	        //System.out.println("ERROR MESSAGE: " + errorMessage);
		    throw new Exception(errorMessage);
	     }
      }	 
   
      return getChildrenNodes;
   }

   public void updateTreeStructure(FRLConfiguration frlCon, int projectId) throws Exception
   {
      String errorMessage1=""; 
      TreeStructureController treeStrucCon = new TreeStructureController();
      
      // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
         throw new Exception(errorMessage1);
	  } 
   
	  try 
	  {
	     treeStrucCon.updateTreeStructure(frlCon, projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
	     throw new Exception(errorMessage1);
	  }
	  
	  // Disconnect from the Database
	  try
	  {
	     treeStrucCon.disconnect();
	  }	    
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
	     throw new Exception(errorMessage1);
	  }

   }

   public String printMethodsTreeStructure(String filePath, int projectId)
   {
	
      ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();
	  String errorMessage1="";
      TreeStructureController treeStrucCon = new TreeStructureController();
      Tree tree = new Tree();
   
      // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.connect(filePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage1 = e.getMessage();
	     return errorMessage1;
	  }
		  
	  try 
	  {
	     treeStructures = treeStrucCon.loadTreeStructure2(projectId);
		 Node<String> root = tree.createTree(treeStructures);
		 tree.printTree(root, " ");
	  } 
	  catch (Exception e) 
	  {
	     errorMessage1 = e.getMessage();
	     return errorMessage1;
	  }
	   
	  // Disconnect from the Database
	  try
	  {
	     treeStrucCon.disconnect();
	  }	    
	  catch (Exception e) 
	  {
         errorMessage1 = e.getMessage();
         return errorMessage1;
	  }

      return errorMessage1;	 
      
   }
   
   public String getMethodsTreeStructureDBClasses(FRLConfiguration frlCon, int projectId)
   {
      String errorMessage="";
      TreeStructureController treeStrucCon = new TreeStructureController();
      
      // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
	     return errorMessage;
	  }
	  
	  try
	  {
	     treeStrucCon.deleteTreeStructure(projectId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
	     return errorMessage;
	  }
	  
	  try
	  {
	     treeStrucCon.createTreeStructureDBClasses(projectId, frlCon.projectInputDir);
	  }
	  catch (Exception e3) 
	  {
         errorMessage = e3.getMessage();
         return errorMessage;
	  }
	  
	  // Disconnect from the Database
	  try
	  {
	     treeStrucCon.disconnect();
	  }	    
	  catch (Exception e4) 
	  {
         errorMessage = e4.getMessage();
         return errorMessage;
	  }

      
      return errorMessage;	   
   }
   
   public String getMethodsTreeStructureNoDBClasses(FRLConfiguration frlCon, int projectId)
   {

      String errorMessage="", nodeMethod=null;	
	  int nodeParentId=0, nodeId=0, level=0, i=0, j=0;
	  boolean childrenFlag=false;
	  NodeType nodeType = null;
	  ArrayList<ChildrenMethod> childrenMethods = new ArrayList<>();
	  ArrayList<ClassMethod> dBMethods = new ArrayList<ClassMethod>();
	  TreeStructureController treeStrucCon = new TreeStructureController();
	  
	  
	  /*currentTime = new java.util.Date();  
	  System.out.println("");
	  System.out.println("STARTING the Get Methods Tree Structure at: " + currentTime);
	  */
	  
	  // Initialize the Arraylists
	  childrenMethods.clear();
	  		  
	  System.out.println("");
	  System.out.println("For the Project: " + frlCon.projectName);
	  System.out.println("This is the Call-Hierarchy of the Methods that perform Data Changes: ");
		  
	  //System.out.println("1");
	     
	  /******************8*****************
	  1.- Clean the tree structure table
	  ************************************/
	  
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
	     return errorMessage;
	  }
		  
	  // Eliminate all the records in the tree_structure table
	  try 
	  {
	     treeStrucCon.deleteTreeStructure(projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
	     return errorMessage;
	  } 
	  
	  try 
	  {
	     dBMethods = treeStrucCon.loadMethods(projectId, 1);
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage = e3.getMessage();
	     return errorMessage;
	  } 		  
	  /*
	  try 
	  {
	     deleteTreeStructure(frlCon.databaseConfigFile, projectId);
	  } 
	  catch (Exception e1) 
	  {
         errorMessage = e1.getMessage();      
         //System.out.println("Error Message 1.1:" + errorMessage);
         return errorMessage;
	  }
	  */
	  
	  // Disconnect from the Database
	  try
	  {
	     treeStrucCon.disconnect();
	  }   
	  catch (Exception e4) 
	  {
	     errorMessage = e4.getMessage();
	     return errorMessage;
	  }	 
	  
	  //System.out.println("2");
	
	  /************************
	  2.- Define the Root Node
	  *************************/
	  nodeMethod = frlCon.projectInputDir + File.separator + frlCon.projectName; 
	     
	  // Assign the type of tree structure element
	  nodeType   = NodeType.Root;
	  
	  /*
	  System.out.println("Node Method   : " + nodeMethod);
	  System.out.println("Node Type     : " + nodeType);
	  System.out.println("3");
	  */
	     
	  // Get the Node Id for this Child Element in the tree structure
	  try 
	  {
	     nodeId = getId(frlCon.databaseConfigFile, nodeType, projectId);
	  }
	  catch (Exception e5) 
	  {
	     errorMessage = e5.getMessage();
	     //System.out.println("Error Message 3.1:" + errorMessage);
         return errorMessage;
	  }
	  
	  /*
	  System.out.println("Node Id       : " + nodeId);
	  System.out.println("4");
	  */
	  

	  // Get the Node Id for this Child Element in the tree structure
	  nodeParentId = getParentId(nodeType.name(), nodeId);
		  
	  // Assign the level 
	  level = 0;
	  
	  /*
	  System.out.println("Node Parent Id : " + nodeId);
	  System.out.println("Level          : " + level);
	  System.out.println("5");
	  */


	  // Save the root node into the Database
	  errorMessage = saveElementTreeStructure(projectId, nodeId, nodeMethod, nodeType, nodeParentId, 
			                                  level, frlCon.databaseConfigFile);
	
	  //System.out.println("Error Message 5 : " + errorMessage);

	  
	  if(errorMessage.isEmpty() == false)
	  {	  
	     return errorMessage;
	  }

	  level = 1;
	  
	  //System.out.println("6");
	  //System.out.println("Level          : " + level);
	  
	  // Loop for every Method that performs Database Operations	
	  for(i = 0; i < dBMethods.size(); i++) 
	  {
		  
		 /***************************************************
		  * 1.- Save in the Tree Structure table
		  * The nodes or the leaves which are the Database Methods
		  * Level 1 items 
		 ****************************************************/
		  
		 //System.out.println("*** SAVING THE NODES LEVEL 1 ***");
		  
	     // Get the current Node Method 
	     //nodeMethod = dBMethods.get(i); 
		 nodeMethod = dBMethods.get(i).getFullMethodName();
	  
	     // Assign the type of tree structure element
	     nodeType = NodeType.Node;
	     
	     /*
	     System.out.println("Node Method: " + i + " of " + dBMethods.size()); 
	     System.out.println("Node Method          : " + nodeMethod);
	     System.out.println("Node Type            : " + nodeType);
	     System.out.println("7");
	     */
	     
	     // Get the Id for this NodeMethod
	     try 
	     {
		    nodeId = getId(frlCon.databaseConfigFile, nodeType, projectId);
		 } 
	     catch (Exception e6) 
	     {
	        errorMessage = e6.getMessage();
		    //System.out.println("Error Message 7: " + errorMessage);

	        return errorMessage;
		 }
	     
	     /*
	     System.out.println("Node Id              : " + nodeId);
	     System.out.println("8");
	     */
	     
	     // Get the nodeParentId
	     nodeParentId = getParentId(nodeType.name(), nodeId);
	     
		 // Save the node into the Database
		 errorMessage = saveElementTreeStructure(projectId, nodeId, nodeMethod, nodeType, nodeParentId, 
			   	                                 level, frlCon.databaseConfigFile);
		 
		 /*
	     System.out.println("Node Parent Id : " + nodeParentId);
	     System.out.println("Error Message  8: " + errorMessage);
	     */
	
		 if(errorMessage.isEmpty() == false)
		 {	 
		    return errorMessage;
		 }	
		 
		 /***************************************************
		  * 2.- Save in the Tree Structure table
		  * The parents of the Database Methods
		  * Level 2 items 
		 ****************************************************/
		 
		 //System.out.println("*** SAVING THE NODES LEVEL 2 ***");
		 
		 nodeType = NodeType.Internal;
				
		 // Get the nodeParentId
		 nodeParentId = getParentId(nodeType.name(), nodeId);
		 
		 /*
		 System.out.println("Node Type       : " + nodeType);
		 System.out.println("Node Parent Id  : " + nodeParentId);
		 System.out.println("9");
		 
		 
		 System.out.println("*** GETTING CHILDREN METHODS ***");
		 System.out.println("Project Id      : " + projectId);
		 System.out.println("Node Parent Id  : " + nodeParentId);
		 System.out.println("Node Method     : " + nodeMethod);
		 System.out.println("Node Type       : " + nodeType);
		 System.out.println("10");
         */
		 
		 // Get the Children of the current Node Method
	     try 
	     {
	        childrenMethods = getChildrenMethods(projectId, nodeParentId, nodeMethod, nodeType, frlCon);
		 } 
	     catch (Exception e7) 
	     {
            errorMessage = e7.getMessage();
			//System.out.println("Error Message 10: " + errorMessage);
            return errorMessage;
		 }
	     
		 //System.out.println("Children Methods: " + Arrays.deepToString(childrenMethods.toArray()));
	     

	     // Remove null elements
		 childrenMethods.removeAll(Collections.singleton(null));
				
		 // Remove duplicate elements
		 childrenMethods = (ArrayList<ChildrenMethod>) childrenMethods.stream().distinct().collect(Collectors.toList());
		 
		 
		 //System.out.println("Child Methods: " + childrenMethods.size()); 
		 
		 for (j = 0; j < childrenMethods.size(); j++) 
		 {
		   
		   //System.out.println("Child Method: " + j + " : "+ childrenMethods.get(j).getMethodName() );
		 }


		 if (childrenMethods.isEmpty() == false) 
		 { 
		    level = 2;
		    
		    /*
		    System.out.println("Level         : " + level);
		    System.out.println("11");
		    */
					
			// Get the current Children Methods of this node
		    try 
		    {
			   childrenFlag = getChildrenNodes(childrenMethods, level, frlCon, projectId);		   
		       
			} 
		    catch (Exception e8) 
		    {
               errorMessage = e8.getMessage();
               //System.out.println("Error Message   11: " + errorMessage);
               return errorMessage;
			} 
		    
		    /*
			System.out.println("Children Flag         : " + childrenFlag);
		    System.out.println("12");
			*/
			 
	      }
	      
	  }
	  
	  // Update the missing details in the Tree_Structure table
	  try
	  {
	     updateTreeStructure(frlCon, projectId);
	  }
	  catch(Exception e9)
	  {
	     errorMessage = e9.getMessage();	 
	     //System.out.println("Error Message   12: " + errorMessage);
		 return errorMessage;
	  } 
	  
	  //System.out.println("13");
	  
	  
	  // Print the Methods Tree Structure
	  errorMessage = printMethodsTreeStructure(frlCon.databaseConfigFile, projectId); 
	  
      if(errorMessage.isEmpty() == false)
      {	 
	     return errorMessage;
	  }	
      
      //System.out.println("14");
      
	  
      return errorMessage;
      
   }

}
