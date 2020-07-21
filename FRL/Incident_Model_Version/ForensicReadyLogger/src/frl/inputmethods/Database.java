package frl.inputmethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import frl.configuration.FRLConfiguration;
import frl.controller.ClassMethodController;
import frl.controller.DatabaseOperationsController;
import frl.controller.GraphicalUserInterfaceController;
import frl.controller.TreeStructureController;
import frl.model.ChildrenMethod;
import frl.model.ClassMethod;
import frl.model.DatabaseMethod1;
import frl.model.NodeType;
import frl.model.TreeStructure;
import frl.tree.Node;
import frl.tree.Tree;

public class Database 
{
  	
   public Database(FRLConfiguration frlCon) //1
   {
	   
   }
			
   public ArrayList<DatabaseMethod1> loadDbMethods(String filePath) //2
   {
      String errorMessage;
	  ArrayList<DatabaseMethod1> dbMethodList = new ArrayList<DatabaseMethod1>(); 
	   
      // Connect to the Database
	  DatabaseOperationsController dbOperCon = new DatabaseOperationsController();
	   
	  try 
	  {
	     dbOperCon.connect(filePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error 3121: Occurred while connecting to the Database. Error Message: " + errorMessage);

	  }
	  
      // Get the Database Method List
	  try 
	  {
	     dbMethodList = dbOperCon.loadDatabaseMethods();
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
	     System.out.println("Error 3122: Occurred while loading the Database Methods. Error Message: " + errorMessage);

	  }
	  
	  dbOperCon.disconnect();
	  
	  return dbMethodList;
	  	   
   }
   
   public static String getVariable(String inputLine, String methodName, FRLConfiguration frlCon) //3
   {
      String variable = "", element, beforeParenthesis;
      String[] whiteSpaces = inputLine.split(frlCon.findWhiteSpaces), parts;
      int parentPos;
      		
 	  for( int i = 0; i < whiteSpaces.length; i++)
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
   
   public static ArrayList<String> getDBMethodLines(String fileName, String dbMethodName, FRLConfiguration frlCon) throws IOException //4
   {

      String lineStr = "", var; 
      String regex = frlCon.findWord + dbMethodName + frlCon.findWord;
      ArrayList<String> variables = new ArrayList<String>();
      Path path = Paths.get(fileName);
      final Charset ENCODING = StandardCharsets.UTF_8;

	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
		  
	     lineStr = null;
	     
	     while ((lineStr = reader.readLine()) != null) 
	     {

	   		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	   		Matcher matcher = pattern.matcher(lineStr);
	   		
	   		while (matcher.find())
	   		{
	   		   var = getVariable(lineStr, dbMethodName, frlCon);
	   		   
	   		   // Validate if the variable is not null or empty
	   		   if (var != null && !var.equals("")) 
	   		      variables.add(var);

	   		}
	    		 
	     }      
      }
	  
	  return variables;
   
   }

   public static String getDBClass(String inputLine, String alphaNumericRegExpr) //5
   {
	  int c = 0; 
      String className = null;

      Pattern p; 
      Matcher m1;
      
      p = Pattern.compile(alphaNumericRegExpr);
      m1 = p.matcher(inputLine); 
	  
      while (m1.find() && c ==0) 
      { 
         className = m1.group();
         c++;
      } 
      		
      return className;	  
	   
   } 
   
   public static String getVariableLines(String fileName, String variable, FRLConfiguration frlCon) throws IOException //6
   {

      String lineStr = "", className = "";
      String regex = frlCon.findWord + variable + frlCon.findWord;
      boolean found = false;
      Path path = Paths.get(fileName);
      final Charset ENCODING = StandardCharsets.UTF_8;

	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
		  
	     lineStr = null;
	     
	     while ( (lineStr = reader.readLine()) != null && found == false ) 
	     {

	   	    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	   		Matcher matcher = pattern.matcher(lineStr);
	   		
	   		while (matcher.find())
	   		{
		       found = true;
		       
		       className = getDBClass(lineStr, frlCon.alphaNumeric);

	   		}
	    		 
	     }      
      }
	  
      return className;	  
   
   }
   
   public boolean validateDbOperations(String fileName, String dbMethod, ArrayList<String> variables, FRLConfiguration frlCon) //7
   {
	   
      int c = 0, found = 0;
	  String var, errorMessage, className;
	  boolean dbFound = false;
	  ArrayList<String> dbClassList = new ArrayList<String>(); 
	   
	  // Connect to the Database
	  DatabaseOperationsController dbOperCon = new DatabaseOperationsController();
		   
	  try 
	  {
	     dbOperCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error 3171: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
	  
      // Get the operations that this database method can do
      try 
      {
	     dbClassList = dbOperCon.loadDatabaseOperations(frlCon.programmingLanguage, frlCon.dbms, dbMethod);

	  } 
      catch (Exception e) 
      {
	     errorMessage = e.getMessage();
		 System.out.println("Error 3172: Occurred while loading the Database Operations. Error Message: " + errorMessage);

	  }
      
      dbOperCon.disconnect();	
	   
	  // Loop for all the variables
	  while (c < variables.size()) 
	  {
		  // Get the current variable 
	      var = variables.get(c);

	      
	      // Obtain the Database ClassName
	      try 
	      {
			className = getVariableLines(fileName, var, frlCon);
			
			// Validate if the ClassName exists in the List of Database Classes for this Database Method
			if (dbClassList.contains(className)) 
			   found++;

		  } 
	      catch (IOException e) 
	      {
	 	     errorMessage = e.getMessage();
			 System.out.println("Error 3173: Occurred while getting the Variable Line. Error Message: " + errorMessage);
		  }
		  
	      c++;

	  }	
	  
      if(found >= 1)
         dbFound = true;	  
      else
         dbFound = false;	
      
      return dbFound;
   }  
   
   public ArrayList<String> validateDatabaseMethod(String fileName, ArrayList<DatabaseMethod1> dbMethods, FRLConfiguration frlCon) //8
   {
	   
      int c = 0; 	
      String dbMet = null;
      boolean dbFound;
      ArrayList<String> databaseMethods = new ArrayList<String>();
      ArrayList<String> variables = new ArrayList<String>();

      while (c < dbMethods.size()) 
      {
    	 dbMet = dbMethods.get(c).getName();
 	     
	     try 
		 {
	    	// Get the variables from the Method Body Files 
	        variables = getDBMethodLines(fileName, dbMet, frlCon);

	    	 
	    	// If there are some variables that contains the Database Method Word
	    	if(variables.isEmpty() == false)
	    	{		
	    	   // Validate if these variables correspond to Database Classes
	    	   dbFound = validateDbOperations(fileName, dbMet, variables, frlCon);
	    	
	    	   if(dbFound == true)
	    	   {	
		          databaseMethods.add(dbMet);
	    	   }  
	    	}
	    	
		 } 
		 catch (IOException e) 
		 {
		    String errorMessage = e.getMessage();
			System.out.println("Error 3181: Occurred while searching for a Database Method Line. Error Message: " + errorMessage);
		 }
	     
         c++;
      }
      
      
      return databaseMethods;
   }  
   
   public ArrayList<String> readMethodBodyFiles(FRLConfiguration frlCon) //9
   {
	   
      String fileName, seqDiagTxtFile, seqDiagPngFile;
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
	     System.out.println("Error 3191: Occurred because the Ouput Project Directory: "+ frlCon.projectOutputDir +" does not exists.");
	  
      return javaFiles;
	    
   } 
   
   public void printingDatabaseMethods(ArrayList<String> dBMethods, FRLConfiguration frlCon)
   {
	  String dBMethod, packageName ="", className="", textFileName="";
	  String shortMethodName, errorMessage, fullMethodName="";
	  String [] parts;
	  ClassMethodController classMetCon = new ClassMethodController(); 
	  ClassMethod cm;
      
      // Connect to the Forensic-Ready Database
	  
	  try 
	  {
        classMetCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
	 
	  // This is the Method List that performs Database Operations 	  
	  for(int i = 0; i < dBMethods.size(); i++) 
	  {
	     dBMethod = dBMethods.get(i); 
		
		 // Split the DBMethod in 3 fields by the Delimiter: "/"
	     if(dBMethod.contains(frlCon.elementsFileName))
	     {	 
		    parts = dBMethod.split(frlCon.elementsFileName);
		
		    packageName  = parts[0];
		    className    = parts[1];
		    textFileName = parts[2];
		
		    try 
		    {
		       cm = classMetCon.loadShortMethodName2(packageName, className, textFileName);
		       shortMethodName = cm.getShortMethodName();
		    
		       fullMethodName = packageName + frlCon.objectOrientedDelimiter1 + 
		    		            className   + frlCon.objectOrientedDelimiter1 +
		    		            shortMethodName;

		       String format = frlCon.tabDelimiter1;
		       System.out.format(format, fullMethodName, 1);
		       
		       System.out.println(fullMethodName);
		    } 
		    catch (SQLException e) 
		    {
		       errorMessage = e.getMessage();
		       System.out.println("Error XXXX: Occurred while loading the details of the Method that performs Database Operations. Error Message: " + errorMessage);
		    }
	     }
		
     }
	 
	 classMetCon.disconnect();

   }
   
   public ArrayList<String> getDatabaseMethods(FRLConfiguration frlCon) //11
   {
      
      String after, methodBodyFile;
      String[] parts;
	  ArrayList <String> dbClassMet = new ArrayList<String>();	
      ArrayList <DatabaseMethod1> dbMethodList = new ArrayList<DatabaseMethod1>(); 
      ArrayList <String> dbMethods = new ArrayList<String>();
	  ArrayList <String> methodBodyFiles = new ArrayList<String>();	
	  
      // Initialize the arraylists
      dbMethods.clear();
      dbClassMet.clear();
      
	  // Get the Database Methods
	  dbMethodList = loadDbMethods(frlCon.propertiesFilePath);
	  
      // Get the Names of the Method Body Files
      methodBodyFiles = readMethodBodyFiles(frlCon);
      	  
      // For every Method Body File Loop 	  
      for(int i = 0; i < methodBodyFiles.size(); i++) 
	  {
         methodBodyFile = methodBodyFiles.get(i);    	  
    	 
    	 // Validate if the Java Method Body File contains a Database Method
    	 dbMethods = validateDatabaseMethod(methodBodyFile , dbMethodList, frlCon);  
   	  
    	 // Validate that the ArrayList is not empty
   	     if(dbMethods.isEmpty() == false)
   	     {	 
   	        // Obtain the details of the Package, Class, Method
	        parts = splitString(methodBodyFile, frlCon.projectOutputDir + frlCon.projectName + frlCon.elementsFileName);
	        
	        // Validate the parts Array is not empty
	        if (parts.length > 0) 
	        {	
		       after = parts[1];  
		       
	           // Add this element that contains the information of the method that performs Database operations
	           dbClassMet.add(after); 
	        }
	        
	     }	 
      }
      
      System.out.println("");
	  System.out.println("For the Project: "+frlCon.projectName); 
	  System.out.println("These are the Methods that perform Data Changes:");
      printingDatabaseMethods(dbClassMet, frlCon);
      
      return dbClassMet;	     
	   
   }
   

   public static String[] splitString(String str, String substr)
   {
	  String[] parts = null;
	  
	  parts  = str.split(substr);
	   
      return parts;	   
   }

   public static String getSuperClass(String inputPackage, String inputClass, String objOrientedDel)
   {
	   
      // Obtain the Class and SuperClass of the current Method Body File
	  Class<?> newClass = null;
	  String superClass;
			  
	  // Create a new class 
	  try 
	  {
	     newClass = Class.forName(inputPackage + objOrientedDel + inputClass);
	  } 
	  catch (ClassNotFoundException e) 
	  {
	     String errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while creating a New Class. Error Message: " + errorMessage);
	  } 
		  		  		  
	  // Get the superclass from the class
      Class<?> superclz = newClass.getSuperclass();
      superClass = superclz.toString();
      
      return superClass;  
   }
   
   public static boolean validateGUISuperClass(String inputSuperClass, FRLConfiguration frlCon)
   {

      String part1 = "", part2 = "";
	  String guiSuperClass, guiName = "";
	  String[] parts;
      boolean flag = false;	 
      
      // Separate the components of the inputSuperClass using the delimiter "."
	  parts = inputSuperClass.split(frlCon.findString + frlCon.objectOrientedDelimiter1);
	  
	  // Validate the parts Array is not empty
      if (parts.length > 0) 
      {
      
	     part1 = parts[0];
	     part2 = parts[1];
	  
	     String regex = frlCon.startFindWordWhiteSpaces + frlCon.bluePrintObject1 + frlCon.endFindWordWhiteSpaces;
	     part1 = part1.replaceAll(regex, "");
	  
	     guiSuperClass = part1 + frlCon.objectOrientedDelimiter1 + part2;

	     // Validate if the inputSuperClass exists in the Forensic Ready Database
	     GraphicalUserInterfaceController guiCon = new GraphicalUserInterfaceController();
      
         // Connect to the Database
         try 
         {
    	    guiCon.connect(frlCon.propertiesFilePath);
	     } 
         catch (Exception e) 
         {
	        String errorMessage = e.getMessage();
		    System.out.println("Error XXXX: Occurred while connecting to the Database. Error Message: " + errorMessage);
	     }
      
         try 
         {
	        guiName = guiCon.loadOneGui(frlCon.programmingLanguage, guiSuperClass);
	     } 
         catch (Exception e) 
         {
 	        String errorMessage = e.getMessage();
 		    System.out.println("Error XXXX: Occurred while validating the Graphical User Interface from the Database. Error Message: " + errorMessage);
	     }
      
         // Disconnect from the Database
         guiCon.disconnect();

      }
      else
 	     System.out.println("Error XXXX: Occurred while separating the components of the Super Class String.");


      // Validate that the guiName is not empty
      if(guiName != null && !guiName.isEmpty()) 
         flag = true;
      else
         flag = false;
      
      return flag;	   
   }
 
   
   public static boolean getClassVariable1(String packageMethodBody, String classMethodBody,
		                                   String packageChild, String classChild, 
		                                   String variable, String objOrientedDel)
   {
	   
	  int c; 
      String currField;
      Field[] fields;
      String[] parts;
	  boolean flag = false;
      Class<?> newClass   = null; 
	   
      // Get the Class using Java Reflection using the MethodBody details
	  try 
	  {
	     newClass = Class.forName(packageMethodBody + objOrientedDel + classMethodBody);
	  } 
	  catch (ClassNotFoundException e) 
	  {
	     String errorMessage = e.getMessage();
	  	 System.out.println("Error XXXX: Occurred while creating a new Class for the Child. Error Message: " + errorMessage);
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
	           System.out.println("Error XXXX: Occurred while separating the Field String.");
	           flag = false;
	        } 	
	  		
	     }
	     
	  } 
	  
      return flag;		  
	   
   }
   
   public static boolean getClassVariable2(String filePathMethodBody, String packageChild, 
                                           String classChild, String variable, String findWordRegExp)
   {

	  String lineStr = "";
	  String regex = findWordRegExp + classChild + " " + variable + findWordRegExp;
	  boolean flag = false;
      Path path = Paths.get(filePathMethodBody);
	  final Charset ENCODING = StandardCharsets.UTF_8;
  
	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
			  
	     lineStr = null;
	     
		 while ((lineStr = reader.readLine()) != null) 
		 {

		    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(lineStr);
		   	
		   	// If the regular expression was found, turn on the flag
		   	if (matcher.find())
		   	   flag = true;
		    		 
	     }      
	  } 
      catch (IOException e) 
	  {
	     String errorMessage = e.getMessage();
	  	 System.out.println("Error XXXX: Occurred while loading the Method Body File in the getClassVariable. Error Message: " + errorMessage);
	  }
		
      return flag;  
   }
   
   public static boolean validateChildMethodVariables(String packageMethodBody, String classMethodBody, 
		                                              String filePathMethodBody, String packageChild, 
		                                              String classChild, ArrayList<String> variables,
		                                              FRLConfiguration frlCon) //7
   {   
      int c = 0; 
	  String var;
	  boolean found1 = false, found2 = false, found3 = false;

	  // Loop for all the variables
	  while (c < variables.size()) 
	  {
		  // Get the current variable 
	      var = variables.get(c);
	      
          // Validate if the variable declared in the packageMethodBody and classMethodBody
	      // has the packageChild and classChild
	      found1 = getClassVariable1(packageMethodBody, classMethodBody, packageChild, classChild, var, frlCon.objectOrientedDelimiter1);
		 
		  if(found1 == false)
		     found2 = getClassVariable2(filePathMethodBody, packageChild, 
                                        classChild, var, frlCon.findWord); 
		  
	      c++;

	  }	
	  
	  // Validate the flags to obtain the value of flag3
	  if(found1 == true || found2 == true)
	     found3 = true;	 
	  else
		  found3 = false;
	  
      
      return found3;
   } 
   
   public static ArrayList<String> getMethodBodyFilesVariables(String fileName, String childMethodName, FRLConfiguration frlCon) //4
   {
	   
	  int lineNum = 0; 
      String regex = frlCon.findWord + frlCon.objectOrientedDelimiter1 + childMethodName + frlCon.findWord;
      String var = "", line = "", lineStr="";
      boolean flag1 = false; 
	  final Charset ENCODING = StandardCharsets.UTF_8;   
      Path path = Paths.get(fileName);
      ArrayList<String> variables = new ArrayList<String>();

	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
		  
	     lineStr = null;
	     lineNum = 1;
	     
	     while ((lineStr = reader.readLine()) != null) 
	     {

	   		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	   		Matcher matcher = pattern.matcher(lineStr);
	   		var = "";
	   		
	   		while (matcher.find())
	   		{
		       // If lineNumber is greater than 1, it means it is in the body of the method body file
	   		   // then obtain the variables
	   		   if(lineNum > Integer.parseInt(frlCon.startLineNum))
	   		   {

                  line  = lineStr.trim();
                  flag1 = line.startsWith(frlCon.singleLineComment)|| line.startsWith(frlCon.printMessage);
                  
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
	     String errorMessage = e.getMessage();
  		 System.out.println("Error XXXX: Occurred while loading the Method Body File in the getMethodBodyFilesChildMethod. Error Message: " + errorMessage);
	  }
	  
      return variables;
   
   }

   
   public ArrayList<ChildrenMethod> getChildrenMethods(int parentId, String method, NodeType nodeType, FRLConfiguration frlCon) //13
   {
	  String methodBodyFile, packageMethod, classMethod, fileMethod, nameMethod;  
      String packageMetBod, classMetBod, fileMetBod, superClassMetBod;
	  String[] parts1, parts2;	  
	  boolean guiFlag, classMetFlag;
      ArrayList<String> methodBodyFiles = new ArrayList<String>();	
	  ArrayList<String> variables1 = new ArrayList<String>();
	  ArrayList<String> variables2 = new ArrayList<String>();
	  ArrayList<ChildrenMethod> metFiles = new ArrayList<>();
      ChildrenMethod metFile = null;
      
	  // Initialize the arraylists
	  methodBodyFiles.clear();
	  metFiles.clear();
	  
	  variables1.clear();
	  variables2.clear();
	  
      // Split the parts of the method
	  parts1 = splitString(method, frlCon.elementsFileName);
	      
	  packageMethod = parts1[0];
	  classMethod   = parts1[1];
	  fileMethod    = parts1[2];
	  nameMethod    = fileMethod.split(frlCon.findDot)[0];
	  
      // Load the Method Body Files from the Project Output Directory
      methodBodyFiles = readMethodBodyFiles(frlCon);
      
      // For every Method Body File Loop 	 
      for(int i = 0; i < methodBodyFiles.size(); i++) 
	  {
         methodBodyFile = methodBodyFiles.get(i); 
         parts1 = splitString(methodBodyFile, frlCon.projectOutputDir + frlCon.projectName + frlCon.elementsFileName);
         // Validate the parts Array is not empty
	     if (parts1.length > 0) 
	     {
	         fileMetBod = parts1[1];
	         
   	        // Split the parts of the node
   	        parts2 = splitString(parts1[1], frlCon.elementsFileName);
   	        
   	        // Validate the parts Array is not empty
   	        if (parts2.length > 0) 
   	        {
   	           packageMetBod = parts2[0];
   	           classMetBod   = parts2[1];

   	           // Obtain the superClass of the Method Body File
   	    	   superClassMetBod = getSuperClass(packageMetBod, classMetBod, frlCon.objectOrientedDelimiter1);
   	    	   
   	    	   // Validate if is equal to a graphical interface
   	    	   guiFlag = validateGUISuperClass(superClassMetBod, frlCon);
   	    	   
   	    	    if (guiFlag == false)
   	    	   {
   	    		   
	   	          variables1 = getMethodBodyFilesVariables(methodBodyFile, nameMethod, frlCon);
	   	    		
	   	    	  if (variables1.isEmpty() == false) 
	    	      {
	   	    		 // Delete the duplicate elements from variables1 ArrayList and copy to a new arraylist: variables2	
	    	         variables2 = (ArrayList<String>) variables1.stream().distinct().collect(Collectors.toList());

	    	         // Validate that the variables array is not empty
	    	         if(variables2.isEmpty() == false)
	    	         {	   
	    	           
	    	            // Validate if the variables are declared as ClassMethod
	    	    	    classMetFlag = validateChildMethodVariables(packageMetBod, classMetBod, 
	    	    		   	                                        methodBodyFile, packageMethod, 
	    	    				                                    classMethod, variables2, 
	    	    				                                    frlCon);
	    	    		
	    	    	    // Validate if the variables belong to the ClassMethod
	    	    	    // Add to the ArrayList
	    	    	    if (classMetFlag == true)
	    	    	    {   
	    	    	       metFile = new ChildrenMethod(parentId, fileMetBod, nodeType);
	    	  	           metFiles.add(metFile);
	    	    	    }   
	    	    	    
	    	         }
	    	    		
	    	      } 
   	    		
   	           }
   	           
   	        }  
   	        else
   	           System.out.println("Error XXXX: Occurred while separating in the second level the Method Body File String.");
	     }
	     else
	        System.out.println("Error XXXX: Occurred while separating in the first level the Method Body File String.");
	    	 
      }
      
      return metFiles;    
   }
   
   
   public int getId(TreeStructureController treeStrucCon, NodeType nodeType)
   {
      int lastId = 0, id=0;	
      

    if(nodeType == NodeType.Root) 
    	
       id = 0;
    
    else
    { 	
    	
       try 
       {
	      lastId = treeStrucCon.getLastIdTreeStructure();
	     
	      id = lastId;
	      id++;
	    } 
        catch (Exception e) 
        {
  	        
           String errorMessage = e.getMessage();
  		   System.out.println("Error XXXX: Occurred while getting the last Identifier of the tree structure. Error Message: " + errorMessage);
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
   
   public String saveElementTreeStructure(int id, String method, NodeType nodeType,  
		                                  int parentId, int recursiveLevel, 
		                                  TreeStructureController treeStrucCon)
   {
 
	  TreeStructure treeStructure = null;
	  String errorMessage1 = "", errorMessage2 = "";
	  
      // Create a new element of the treeStructure class
	  treeStructure = new TreeStructure(id, method, nodeType, parentId, recursiveLevel);
		         
	  // Save the childMethod into a new tree structure record
	  try 
	  {
	      treeStrucCon.saveTreeStructure(treeStructure);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage1 = e.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while saving a "+nodeType+" tree structure element. Error Message: " + errorMessage1;
	  }
	  
      return errorMessage2;	  
	   
   }
   
   public boolean getChildrenNodes(ArrayList<ChildrenMethod> childrenMethods1,
		                        int level1, 
		                        TreeStructureController treeStrucCon,
		                        FRLConfiguration frlCon)
   {
	   
      int nodeId = 0, level = level1, parentId;
      String errorMessage, method;
      boolean grandChildFlag = false, getChildrenNodes = false;
      NodeType nodeType = null;
      ArrayList<ChildrenMethod> childrenMethods2 = new ArrayList<>();
      
      // Initialize the childenMethods2 ArrayList
      childrenMethods2.clear();
      
      for (ChildrenMethod childMethodRow : childrenMethods1)
      { 
			 
         parentId = childMethodRow.getParentId();
	     method   = childMethodRow.getMethodName();
	     nodeType = childMethodRow.getNodeType();
	     
		 // Get the Id for this GrandChildMethod
		 nodeId = getId(treeStrucCon, nodeType);
	      
		 // Save the Children Node into the Database
		 errorMessage = saveElementTreeStructure(nodeId, method, nodeType, parentId,
						                         level, treeStrucCon);
				  
		 if(errorMessage.isEmpty() == false)
		 {	  
			getChildrenNodes = false; 
		    throw new java.lang.RuntimeException(errorMessage);
		 }
		 else
			 getChildrenNodes = true;
         
		 nodeType = NodeType.Internal;
		 
		 // Get the nodeParentId
		 parentId = getParentId(nodeType.name(), nodeId);
		  
		 // Check if the current Node has more children
		 childrenMethods2 = getChildrenMethods(parentId, method, nodeType, frlCon);
		 
		 // Remove null elements
		 childrenMethods2.removeAll(Collections.singleton(null));
			
		 // Remove duplicate elements
		 childrenMethods2 = (ArrayList<ChildrenMethod>) childrenMethods2.stream().distinct().collect(Collectors.toList());

		 if (childrenMethods2.isEmpty() == false) 
	     { 
		    grandChildFlag = true;
	     }

			 
      }
      
	  // Validate if childrenMethods2 ArrayList is empty or not
	  if (grandChildFlag == true) 
      { 
		  
	     level++;
	     
         // Get the current Children Methods of this node
	     getChildrenNodes(childrenMethods2,
                          level, 
                          treeStrucCon,
                          frlCon);
	     
      }	 
      
      return getChildrenNodes;	   
   }
   
   public void printMethodsTreeStructure(TreeStructureController treeStrucCon)
   {
	   
	  ArrayList<TreeStructure> treeStructures = new ArrayList<TreeStructure>();
	  String errorMessage;
      Tree tree = new Tree(); 
      
 	  try 
	  {
	     treeStructures = treeStrucCon.loadTreeStructure2();
	 	 
		 Node<String> root = tree.createTree(treeStructures);
		 tree.printTree(root, " ");

	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while loading the Methods Tree Structure. Error Message: " + errorMessage);

	  }
	  
   }
   
   public void getMethodsTreeStructure(FRLConfiguration frlCon, ArrayList<String> method) //14
   {

      String nodeMethod=null, errorMessage="";	
	  int nodeParentId = 0, nodeId=0, level =0, i=0;
	  NodeType nodeType = null;
	  boolean childrenFlag = false;
	  ArrayList<ChildrenMethod> childrenMethods = new ArrayList<>();
	  
	  TreeStructureController treeStrucCon = new TreeStructureController();
	  
	  // Initialize the Arraylists
	  childrenMethods.clear();
	  
	  System.out.println("");
	  System.out.println("For the Project: " +frlCon.projectName);
	  System.out.println("This is the Call-Hierarchy of the Methods that perform Data Changes:");
	  
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.connect(frlCon.propertiesFilePath);
	   } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
	  	 System.out.println("Error XXXX: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
	  
	  // Eliminate all the records in the tree_structure table
	  try 
	  {
	     treeStrucCon.deleteTreeStructure();
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while deleting the existing tree structures. Error Message: " + errorMessage);
	  }  

	  // Store the Root Node into the Database
	  nodeMethod = frlCon.projectInputDir + frlCon.projectName; 
	  
	  // Assign the type of tree structure element
	  nodeType = NodeType.Root;
	  
	  // Get the Id for this childMethod
	  nodeId = getId(treeStrucCon, nodeType);
	  
	  // Get the nodeParentId
	  nodeParentId = getParentId(nodeType.name(), nodeId);
	  
	  // Assign the level 
	  level = 0;

	  // Save the root node into the Database
	  errorMessage = saveElementTreeStructure(nodeId, nodeMethod, nodeType, nodeParentId, 
			                                 level, treeStrucCon);
	  
	  if(errorMessage.isEmpty() == false)
	  {	  
	  	 throw new java.lang.RuntimeException(errorMessage);
	  }	
	  
	  level = 1;
	  
	  // For every Method that performs Database Operations	
	  for(i = 0; i < method.size(); i++) 
	  {
		  
		 // Get the current Node Method 
	     nodeMethod = method.get(i); 
	     
	     // Assign the type of tree structure element
	     nodeType = NodeType.Node;
	     
	     // Get the Id for this NodeMethod
	     nodeId = getId(treeStrucCon, nodeType);
	     
	     // Get the nodeParentId
	     nodeParentId = getParentId(nodeType.name(), nodeId);
	     
		 // Save the node into the Database
		 errorMessage = saveElementTreeStructure(nodeId, nodeMethod, nodeType, nodeParentId, 
				                                 level, treeStrucCon);
		  
		 if(errorMessage.isEmpty() == false)
		 {	  
		    throw new java.lang.RuntimeException(errorMessage);
		 }	
		 
		 nodeType = NodeType.Internal;
			
		 // Get the nodeParentId
		 nodeParentId = getParentId(nodeType.name(), nodeId);
		    
		 //System.out.println("Node Parent Id: "+nodeParentId);
				
		 // Get the Children of the current Node Method
	     childrenMethods = getChildrenMethods(nodeParentId, nodeMethod, nodeType, frlCon);	
			
	     // Remove null elements
		 childrenMethods.removeAll(Collections.singleton(null));
			
		 // Remove duplicate elements
		 childrenMethods = (ArrayList<ChildrenMethod>) childrenMethods.stream().distinct().collect(Collectors.toList());

		 if (childrenMethods.isEmpty() == false) 
		 { 
		    level = 2;
				
			// Get the current Children Methods of this node
		    childrenFlag = getChildrenNodes(childrenMethods, level, 
						                    treeStrucCon, frlCon);  
	     }
		 
	  }
		 
	  // Update the missing details in the Tree_Structure table
	  try 
	  {
	     treeStrucCon.updateTreeStructure(frlCon);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
	     System.out.println("Error XXXX: Occurred while updating the Tree Structures. " + "Error Message: " + errorMessage);
	  }

	  // Print the Methods Tree Structure
	  printMethodsTreeStructure(treeStrucCon);
	  
	  // Disconnect from the Database
	  treeStrucCon.disconnect();	  
	  
   }
   
}