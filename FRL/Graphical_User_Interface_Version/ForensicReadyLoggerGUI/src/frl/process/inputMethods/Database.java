package frl.process.inputMethods;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import frl.controller.configuration.ClassMethodController;
import frl.controller.inputMethods.DatabaseOperationsController;
import frl.model.configuration.ClassAttribute;
import frl.model.configuration.ClassInformation;
import frl.model.configuration.ClassMethod;
import frl.model.configuration.FRLConfiguration;
import frl.model.inputMethods.DatabaseData;
import frl.model.inputMethods.DatabaseMethod1;
import frl.process.tree.MethodHierarchy;

public class Database 
{
   public Database(FRLConfiguration frlCon) //1
   {
		   
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
			fileName       = file.toString(); 
			seqDiagTxtFile = frlCon.projectOutputDir + frlCon.umlSeqDiagTextFileName1;
			seqDiagPngFile = frlCon.projectOutputDir + frlCon.umlSeqDiagPngFileName1;
			
			// Validate that the MethodBody file is different to the following files: 
			// - FRLConfiguration.umlSeqDiagTextFileName1 and
			// - FRLConfiguration.umlSeqDiagPngFileName1
			if(!fileName.equals(seqDiagTxtFile) && !fileName.equals(seqDiagPngFile)) 
				
		       javaFiles.add(file.getAbsolutePath().toString());
		 }
		  
	  }	 
	  else
	  {	  
	     errorMessage1="Error 3191: Occurred because the Ouput Project Directory: "+ frlCon.projectOutputDir + " does not exists.";
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
   
   public String getVariable(String line, String dbMethodName, FRLConfiguration frlCon) //3
   {
      String variable="", element="", beforePar="";
      String[] whiteSpaces, parts;
      int i=0, parent=0;
      		
      // Divide the line into fields according to the white spaces present there
      whiteSpaces = line.split(frlCon.findWhiteSpaces);
    		  
 	  for(i = 0; i < whiteSpaces.length; i++)
 	  {
 	     element = whiteSpaces[i];
 	     
 	     // Validate if the current element of the line contains the DB Method Name
 	     if(element.contains(dbMethodName))
 	     {
	        parts = splitString(element, frlCon.objectOrientedDelimiter1 + dbMethodName);

	        // Obtain the variable name that contains the DB Method Name
	        if (parts.length > 0) 
	        {	
			   variable = parts[0];
			   
			   // Validate if the variable has an special character equals to "("
               parent = variable.indexOf(frlCon.startParameters);
               
               if(parent > 0)
               {	   
			      beforePar = variable.substring(0, parent);
			      variable  = beforePar;
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
   
   public ArrayList<String> getDBVariables(String fileName, String dbMethodName, FRLConfiguration frlCon) throws Exception //4
   {

      String lineStr="", var="", regex="", errorMessage1="", errorMessage2="";
      ArrayList<String> variable = new ArrayList<String>();
      Path path = Paths.get(fileName);
      final Charset ENCODING = StandardCharsets.UTF_8;
      Pattern pattern;
      Matcher matcher;

      // Obtain all the Lines inside a Method Text Files that
      // includes the Database Method
	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
		  
		 // Initialize variables 
	     lineStr = null;
	     regex   = frlCon.findWord + dbMethodName + frlCon.findWord;
	     
	     while ((lineStr = reader.readLine()) != null) 
	     {

	   		pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	   		matcher = pattern.matcher(lineStr);
	   		
	   		while (matcher.find())
	   		{
	   		   // Obtain the variable name that includes the Database Method Name	
	   		   var = getVariable(lineStr, dbMethodName, frlCon);
	   		   
	   		   // Validate if the variable name is not null or empty
	   		   // add this line to the List of Variables that have the Database Method Name
	   		   if (var != null && !var.equals("")) 	   
	   		      variable.add(var);
	   		}
	    		 
	     }      
      }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while getting the Variables for the DB Method: " + dbMethodName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "in the Method Text File: " + fileName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
	  return variable;
   
   }

   public String getDBClass(String inputLine, String alphaNumericRegExpr) //5
   {
	  int c=0; 
      String className="";

      Pattern p; 
      Matcher m1;
      
      p  = Pattern.compile(alphaNumericRegExpr);
      m1 = p.matcher(inputLine); 
	  
      while (m1.find() && c ==0) 
      { 
         className = m1.group();
         c++;
      } 
      		
      return className;	  
	   
   } 
   
   public String removeSpecialCharactersVariable(String variable1) throws Exception
   {
      String errorMessage1="", errorMessage2="", variable2="", 
    		 specialChars="", lastChar="";
      int length=0;

      // Initialize variables
      specialChars = "[!@#$%&*()_+=|<>?{}\\[\\]~-];";
      
      /*
      System.out.println("Inside the Remove Special Characters Variable Method ...");
      
      System.out.println("Variable 1: " + variable1);
      */
      
      //System.out.println("ENTERING THE METHOD removeSpecialCharactersVariable ");
      
	  try
	  {
		 length = variable1.length();
		 
		 //System.out.println("Length: " + length);
		 
		 if(length > 1)
		 {	 
			//System.out.println("The Variable 1 has a Length greater than ONE");
			 
	        lastChar = variable1.substring(length-1, length);
	        
	        //System.out.println("Last Char: " + lastChar);
	       
	        /*
	        System.out.println("Length      : " + variable1.length());
	        System.out.println("Variable 1  : " + variable1);
	        System.out.println("Substring 2 : " + lastChar);
	        */
	     
	     
	        if (specialChars.contains(lastChar) == true) 
	        {
	          
	           variable2 =  variable1.substring(0, length-1);
	           
	           /*
	           System.out.println("Variable 1: " + variable1);
	           System.out.println("Variable 2: " + variable2);
	           System.out.println("UNO");
	           */
	        
		       lastChar   = variable2.substring(variable2.length()-1, variable2.length());
		     
		     
		       /*
		       System.out.println("Length      : " + variable2.length());
		       System.out.println("Var         : " + variable2);
		       System.out.println("Substring 2 : " + lastChar);
		       System.out.println("DOS");
		       */
		       

		       if (specialChars.contains(lastChar) == true) 
		       {
		          variable2 =  variable2.substring(0, variable2.length()-1);
		          /*
		          System.out.println("Variable 1: " + variable1);
		          System.out.println("Variable 2: " + variable2);
		          System.out.println("DOS");
		          */

		       }

	        }
	        else
	           variable2 = variable1;
	     
		 }
		 else
		 {	 
			//System.out.println("*** ENTRE A ESTE CASO ***");
			
			lastChar = variable1;
			
			if (specialChars.contains(lastChar) == true)
			{	
		       //System.out.println("The Variable 1 contains just ONE SPECIAL CHARACTER : " + variable1);
		       variable2 = "";
		       /*
		        System.out.println("Variable 1: " + variable1);
		        System.out.println("Variable 2: " + variable2);
		        */
			}
			else
			{	
	           variable2 = variable1;

	           /*
	           System.out.println("The Variable 1 contains OTHER KIND OF CHARACTERS : " + variable1);
	           System.out.println("Variable 1: " + variable1);
	           System.out.println("Variable 2: " + variable2);
	           */
			}
	        
	        /*
	        System.out.println("Length      : " + variable1.length());
	        
	        System.out.println("TRES");
	        */
	        
		 }
		  
	  }
      catch (Exception e1) 
      {
         errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while removing the Special Characters of the Variable: " + variable1 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
   
	  //System.out.println("FINAL Variable 2: " + variable2);
	  
      return variable2;	      
   }
   
   public String getClassName(String fileName, String variable1, FRLConfiguration frlCon) throws Exception //6
   {

      String errorMessage1="", errorMessage2="", lineStr="", className="", regex="", variable2="";
      boolean found=false;
      Path path;
      final Charset ENCODING;
      Pattern pattern;
      Matcher matcher;

	  // Initialize variables 
	  lineStr  = null;
      ENCODING = StandardCharsets.UTF_8;
      path     = Paths.get(fileName);
      
      
      // Verify it the variable contains special characters
      // at the beginning or at the end
      
      try
      {
         variable2 = removeSpecialCharactersVariable(variable1); 
      }
      catch (Exception e1) 
      {
         errorMessage1 = e1.getMessage();
         throw new Exception(errorMessage1);
      }

	  regex = frlCon.findWord + variable2 + frlCon.findWord;  
	  
      
	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
		 
	     while ( (lineStr = reader.readLine()) != null && found == false ) 
	     {
	   	    pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	   		matcher = pattern.matcher(lineStr);

	   		while (matcher.find())
	   		{
		       found = true;
		       
		       className = getDBClass(lineStr, frlCon.alphaNumeric);
		       
	   		}
	    		 
	     }   
	     
	     //System.out.println("AFTER THE LOOP");
      }
      catch (Exception e2) 
      {
         errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while getting the Class Name of the File: " + System.lineSeparator() + fileName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Variable: " + variable2 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);
	  }
	  
      
      return className;	  
   
   }
   
   public boolean validateDbVariables(int projectId,
		                              String fileName, 
		                              String dbMethod, 
		                              ArrayList<String> variables, 
		                              FRLConfiguration frlCon) throws Exception
   {
	   
      int c=0, found=0;
	  String var="", className="", errorMessage1="";
	  boolean dbFound = false;
	  ArrayList<String> dbClassList = new ArrayList<String>(); 
	   
	  // Connect to the Database
	  DatabaseOperationsController dbOperCon = new DatabaseOperationsController();
	  
	  try 
	  {
	     dbOperCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
	  /*
	  if(fileName.contains("EmployeeDatabase"))
      {	
	  System.out.println("Inside the Validate Db Variables Method ...");
	  System.out.println("1");
      }
      */
	  
      // Get the Database Classes that this Database Method has
	  // which includes all the data operations allowed
      try 
      {
	     dbClassList = dbOperCon.loadDatabaseOperations(projectId,
	    		                                        frlCon.programmingLanguage, 
	    		                                        frlCon.dbms, 
	    		                                        dbMethod);
	  } 
      catch (Exception e2) 
      {
 	     errorMessage1 = e2.getMessage();
 		 throw new Exception(errorMessage1);
	  }
      
      /*
      if(fileName.contains("EmployeeDatabase"))
      {	  
      System.out.println("dbClassList: " + Arrays.deepToString(variables.toArray()));
      System.out.println("2");
      }
      */
      
      // Disconnect from the database
      try 
      {
	     dbOperCon.disconnect();
	  } 
      catch (Exception e3) 
      {
  	     errorMessage1 = e3.getMessage();
  		 throw new Exception(errorMessage1);
	  }	
      
      /*
      if(fileName.contains("EmployeeDatabase"))
      {
      System.out.println("3");
      }
      */
	   
	  // Loop for all the variables
	  while (c < variables.size()) 
	  {
		  // Get the current variable 
	      var = variables.get(c);
	      
	      /*
	      if(fileName.contains("EmployeeDatabase"))
	      {
	      System.out.println("Variable: " + var);
	      }
	      */

	      // Obtain the Database Class Name of this Variable 
	      try 
	      {
			className = getClassName(fileName, var, frlCon);
			
			/*
			if(fileName.contains("EmployeeDatabase"))
		    {
				System.out.println("FileName: " + fileName);
		       System.out.println("ClassName: " + className);
		    }
		    */
			
			// Validate if the className is included in the
			// Database Classes saved in the Database
			// that belong to this database method 
			// and perform data operations
			if (dbClassList.contains(className)) 
			{	
			   found++;
			   
			   /*
			   if(fileName.contains("EmployeeDatabase"))
			    {
			   System.out.println("Found         : " + found);
			    }
			   */
			   
			}   

		  } 
	      catch (Exception e4) 
	      {
	         errorMessage1 = e4.getMessage();
			 throw new Exception(errorMessage1);
		  }
	      
	      //System.out.println("4");
		  
	      c++;

	  }	
	  
	  // Validate if one or more variables has a Database Class that perform
	  // data operations
      if(found >= 1)
         dbFound = true;	  
      else
         dbFound = false;	
      
      /*
      if(fileName.contains("EmployeeDatabase"))
	    {
      System.out.println("Found    : " + found);
      System.out.println("DB Found : " + dbFound);
      System.out.println("4");
	    }
	    */
      
      return dbFound;
   }  
   
   
   public boolean validateDatabaseMethod(int projectId, 
		                                 String fileName, 
                                         ArrayList<DatabaseMethod1> dbMethods, 
                                         FRLConfiguration frlCon) throws Exception
   {
	   
      String dbMet=null, errorMessage1="", errorMessage2="";
      int c=0, times=0; 
      boolean dbMethodFlag1=false, dbMethodFlag2=false;
      ArrayList<String> variables = new ArrayList<String>();
 
      while (c < dbMethods.size()) 
      {
    	 dbMet = dbMethods.get(c).getName();
    	 
	     try 
		 {
	    	// Get the Variables from the Method Text Files 
	    	// that contains the Database Method
	    	
	    	// Explanation: these variables are lines found in the Method Text Files
	    	// that include the Database Method
	    	try
	    	{
	           variables = getDBVariables(fileName, dbMet, frlCon);
	    	}
	    	catch (Exception e1) 
			{
		        errorMessage1 = e1.getMessage();
				throw new Exception(errorMessage1);
			}
	        
	    	// If there are some variables that contains the Database Method Word
	    	if(variables.isEmpty() == false)
	    	{		
	    		
	    	   // Validate if these Variables have a Database Class
	    	   // that perform data operations
	    	   try
	    	   {
	    	      dbMethodFlag1 = validateDbVariables(projectId, fileName, dbMet, variables, frlCon);
	    	   }   
		       catch (Exception e2) 
			   {
			      errorMessage1 = e2.getMessage();
				  throw new Exception(errorMessage1);
			   }
	    	   
	    	   // Add the method to the List of the methods that perform data operations
	    	   if(dbMethodFlag1 == true)
	    	   {	   
	    	      times++;
	    	      
	    	   }  
	    	   
	    	}
	    	
		 } 
		 catch (Exception e3) 
		 {
	        errorMessage1 = e3.getMessage();
			errorMessage2 = "Error XXXX: Occurred while loading the lines of the Method: " + dbMet + System.lineSeparator();
			errorMessage2 = errorMessage2 + "File: " + fileName + System.lineSeparator();
			errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			throw new Exception(errorMessage2);
		 }
	     
         c++;
      }
      
      if(times >=1)
         dbMethodFlag2 = true;
      else
         dbMethodFlag2 = false;
      
      
      return dbMethodFlag2;
   } 
   
   public void oldPrintingDatabaseMethods(ArrayList<String> dBMethods, FRLConfiguration frlCon, int projectId) throws Exception
   {
      String dBMethod, packageName="", className="", shortMethodName="",/*, textFileName2="",*/
    		 //shortMethodName="", 
    		 fullMethodName="", errorMessage1="", errorMessage2="", format="";
	  String [] parts;
	  int i=0, id=0;
	  ClassMethodController classMetCon = new ClassMethodController();
	  ClassMethod cm;
      
	  //System.out.println("Inside the Printing Database Methods ...");
	  
      // Connect to the Forensic-Ready Database
	  try 
	  {
         classMetCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  	 
	  // This is the Method List that performs Database Operations 	  
	  for(i = 0; i < dBMethods.size(); i++) 
	  {
	     dBMethod = dBMethods.get(i); 
		
		 // Split the DBMethod in 3 fields by the Delimiter: "/"
	     if(dBMethod.contains(File.separator))
	     {	 
		    parts = dBMethod.split(Pattern.quote(File.separator));
		    /*System.out.println("AFTER PARTS");
		    System.out.println("UNO");
		    */
		    
		    packageName   = parts[0];
		    /*System.out.println("Package Name     : " + packageName);
		    System.out.println("DOS");
		    */
		    
		    className     = parts[1];
		    /*System.out.println("Class Name       : " + className);
		    System.out.println("TRES");
		    */
		    
		    shortMethodName = parts[2];
		    
		    //textFileName1 = parts[2];
		    /*System.out.println("Text File Name 1 : " + shortMethodName);
		    System.out.println("CUATRO");
		    */
		    
		    //textFileName2 = "%" + System.getProperty("file.separator") + textFileName1;
		    //System.out.println("Text File Name 1 : " + textFileName1);
		    //System.out.println("CINCO");
		    
		    try 
		    {
		       	
		       //System.out.println("Project Id     : " + projectId);	
		       
		       
		       
		       //System.out.println("Text File Name 2 : " + textFileName2);
		       
		    	
		       cm = classMetCon.loadMethodNameDetails_2(packageName, className, shortMethodName, projectId);
		       
		       id = cm.getId();
		       
		       //shortMethodName = cm.getShortMethodName();
		    
		       fullMethodName = packageName + frlCon.objectOrientedDelimiter1 + 
		    		            className   + frlCon.objectOrientedDelimiter1 +
		    		            shortMethodName;
		       
		       /*
		       System.out.println("Id             : " + id);
		       System.out.println("Method Name    : " + shortMethodName);
		       */
		       
		       format = frlCon.tabDelimiter1;
		       System.out.format(format, fullMethodName, 1);
		       System.out.println(fullMethodName);
		    } 
		    catch (Exception e3) 
		    {
		       errorMessage1 = e3.getMessage();
		       errorMessage2 = "Error XXXX: Occurred while loading the details of the Methods that performs Data Changes. \n";
		       errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		       throw new Exception(errorMessage2);
		    }
		    
		    // Update the Method that performs Data Changes
		    try
		    {
		       classMetCon.updateDatabaseMethod(projectId, id);
		    }
		    catch (Exception e4) 
		    {
		       errorMessage1 = e4.getMessage();
		       throw new Exception(errorMessage2);
		    }
		    
	     }
		
      }
	 
      // Disconnect from the Database 
      try 
      {
	     classMetCon.disconnect();
	  } 
	  catch (Exception e5) 
	  {
	     errorMessage1 = e5.getMessage();
	     throw new Exception(errorMessage1);
      }

   }
   
   public boolean validateAttributeMethod(String attributeShortName, String methodTextFile) throws Exception
   {
      String errorMessage1="", errorMessage2="", lineText1="", lineText2="";
      Path path;
	  final Charset ENCODING;
	  boolean attributeFound=false;
	  	  
	  // Initialize Variables
      path = Paths.get(methodTextFile);
      ENCODING = StandardCharsets.UTF_8;
      attributeFound=false;
      
      //attributeShortName = attributeShortName.toLowerCase();
      
	  try ( BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
	     while ( (lineText1 = reader.readLine()) != null && 
	    		 attributeFound == false ) 
		 {		    	
		    lineText2 = lineText1;
		    //lineText2 = lineText2.toLowerCase();
		    lineText2 = lineText2.trim();
		    
		    //System.out.println("Line: ");
				
		    // Validate if the method is present
		    if ( lineText2.contains(attributeShortName) == true && 
		    	 lineText2.startsWith("*") == false ) 
			{
		       attributeFound = true; 
		    }   
		    	
		  }      
	   }
	   catch (Exception e1) 
	   {
	      errorMessage1 = e1.getMessage(); 
	 	  errorMessage2 = "Error XXXX: Occurred while loading the Method Text File: " + methodTextFile + System.lineSeparator();
	 	  errorMessage2 = errorMessage2 + "For the Attribute Short Name: " + attributeShortName + System.lineSeparator();
	 	  errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
	 	  throw new Exception(errorMessage2); 	
	    }
	  

      return attributeFound;	  
   }
   
   
   public boolean validateDMLMethodClass(String dbClass, String dbMethod1, String methodTextFile) throws Exception
   {
      String errorMessage1="", errorMessage2="", lineText1="", lineText2="", value1="*", value2="@query", dbMethod2="";
      Path path;
	  final Charset ENCODING;
	  boolean dbMethodFound=false, dbMethodClassFound=false;/*, dbClassFound=false*/;
	  
	  // Initialize Variables
      path = Paths.get(methodTextFile);
      ENCODING = StandardCharsets.UTF_8;
      
      dbMethodFound      = false;
      dbMethodClassFound = false;
      
      //dbClassFound       = false;
      
      dbClass   = dbClass.toLowerCase();
      dbMethod1 = dbMethod1.toLowerCase();
      
      dbMethod2 = dbMethod1 + " ";
      
      /*
      System.out.println("METODO A BUSCAR : " + dbMethod);
      System.out.println("CLASE A BUSCAR  : " + dbClass);
      System.out.println("METHOD TEXTFILE : " + methodTextFile);
      */
      
      //System.out.println("Inside the Validate DML Method Class  method ");
      
	  try ( BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
	     while ( (lineText1 = reader.readLine()) != null && 
	    		 (dbMethodFound == false)) 
		 {  
		    	
		    lineText2 = lineText1;
		    lineText2 = lineText2.toLowerCase();
		    lineText2 = lineText2.trim();
		   
		    /*
		    if(dbClass.equals("@query"))
		    	if(dbMethod.equals("select"))
		    	{	
		    		System.out.println("Line: " + lineText2);
		    	}	
		    	*/
		    	
		    // Validate if the dbClass is present
		    /*if ( lineText2.contains(dbClass) ) 
			{
		       dbClassFound = true; 
		       
		    } 
		    */ 

		    if (  lineText2.startsWith(value1) == false &&
		    	  lineText2.startsWith(value2) == true &&
		    	  lineText2.contains(dbMethod2)) 
			{
			   dbMethodFound = true; 
			       
			   //System.out.println("DB METHOD FOUND IN: " + lineText2);
			   
			   /*
			   //if(dbClass.equals("@query"))
			      if(dbMethod.equals("select"))  		
			       System.out.println("DB METHOD FOUND IN: " + lineText2);
			       */
			}  
		    
		    	
		  }    
	     
	   }
	   catch (Exception e1) 
	   {
	      errorMessage1 = e1.getMessage(); 
	 	  errorMessage2 = "Error XXXX: Occurred while loading the Method Text File: " + methodTextFile + System.lineSeparator();
	 	  errorMessage2 = errorMessage2 + "For the Database Class: " + dbClass + System.lineSeparator();
	 	  errorMessage2 = errorMessage2 + "For the Database Method: " + dbMethod1 + System.lineSeparator();
	 	  errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
	 	  throw new Exception(errorMessage2); 	
	    }
	  

      if(dbMethodFound == true)	  
         dbMethodClassFound = true;
      else
    	  dbMethodClassFound = false;
      
      /*
	    //if(dbClass.equals("@query"))
	    	if(dbMethod.equals("select"))
	    	{  		
	  System.out.println("Db Class Found       : " + dbClassFound);
	  System.out.println("Db Method Found      : " + dbMethodFound);
	  System.out.println("Db Method Class Found: " + dbMethodClassFound);
	    	}
	    	*/
	   
      return dbMethodClassFound;	  
   }  
   
   public boolean validateDMLMethod(int projectId,
		                            String classPackageName, 
		                            String classShortName, 
		                            String methodTextFile, 
		                            ArrayList <DatabaseMethod1> dbMethodList, 
		                            FRLConfiguration frlCon) throws Exception
   {

      String errorMessage1="", dbMethod="", dbClass="";  
      int i=0;
      boolean dMLMethodFlag=false;
	   
      //System.out.println("INSIDE validate DML Method ...");
      
	  // Connect to the Database
	  DatabaseOperationsController dbOperCon = new DatabaseOperationsController();
	  
	  try 
	  {
	     dbOperCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
	  /*
      if( classPackageName.contains("org.isf.admission.service") && 
	    	   classShortName.contains("AdmissionIoOperationRepositoryImpl"))
	  System.out.println("INSIDE Validate DML Method Method ...");
      */
      
      // Loop for the DBMethodList
      //for(i = 0; i < dbMethodList.size(); i++) 
	  while ( (i < dbMethodList.size()) && 
			   dMLMethodFlag == false) 
	  {

         dbMethod = dbMethodList.get(i).getName();
         
         //System.out.println("Db Method: " + dbMethod);
         
         /*
         if( classPackageName.contains("org.isf.admission.service") && 
  	    	   classShortName.contains("AdmissionIoOperationRepositoryImpl"))
         
         */
         
         // Get the Database Class that this Database Method has
   	     // which includes the data operation allowed
         try 
         {
   	        dbClass = dbOperCon.loadDatabaseOperation(projectId,
   	    		                                      frlCon.programmingLanguage, 
   	    		                                      frlCon.dbms, 
   	    		                                      dbMethod);
   	     } 
         catch (Exception e2) 
         {
    	     errorMessage1 = e2.getMessage();
    		 throw new Exception(errorMessage1);
   	     }


         
         /*
         if( classPackageName.contains("org.isf.admission.service") && 
  	    	   classShortName.contains("AdmissionIoOperationRepositoryImpl"))

         */
         /*
         System.out.println("Db Class         : " + dbClass);
         System.out.println("Db Method        : " + dbMethod);
         System.out.println("Method Text File : " + methodTextFile);
         */
         
         // Verify if the methodTextFile contains
         // the databaseMethod(dbMethod) and databaseClass(dbClass)
         dMLMethodFlag = validateDMLMethodClass(dbClass, dbMethod, methodTextFile);
         
         //System.out.println("DML Method Flag: " + dMLMethodFlag);
         /*
         if( classPackageName.contains("org.isf.admission.service") && 
  	    	   classShortName.contains("AdmissionIoOperationRepositoryImpl"))
         
         */
         
         i++;
         
	  }   
      
      // Disconnect from the database
      try 
      {
	     dbOperCon.disconnect();
	  } 
      catch (Exception e3) 
      {
  	     errorMessage1 = e3.getMessage();
  		 throw new Exception(errorMessage1);
	  }	
      
      //System.out.println("DML Method Flag FINAL : "+ dMLMethodFlag);
      
      return dMLMethodFlag;   
   }
   
   public boolean validateDBClassElement(int projectId, String element, FRLConfiguration frlCon) throws Exception
   {
	  String errorMessage1=""; 
      boolean dbClassFlag=false;
      DatabaseOperationsController dbOperCon = new DatabaseOperationsController();

	  // Connect to the Forensic-Ready Database
      try 
	  {
         dbOperCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }

      // Validate if the element contains a Class that is a Database Class
      try
      {
         dbClassFlag = dbOperCon.validateDBClassName(projectId, element);  
      }
      catch (Exception e2) 
      {
         errorMessage1 = e2.getMessage();
         throw new Exception(errorMessage1);
      }

	  // Disconnect from the Database 
	  try 
	  {
	     dbOperCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 throw new Exception(errorMessage1);
	  }
      
      return dbClassFlag;	   
   }
   
   public boolean validateDBClassParameters(int projectId, 
		                                    int cmId, 
		                                    String packageName,
		                                    String className1,
		                                    String methodShortName,
		                                    String methodParameterName,
		                                    String methodTextFile,
		                                    FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="", parameters="", parameter="", element="";
      int i=0, j=0;
      boolean dbClassFound=false;
      String[] parameterArray;
      String[] elementArray = {""}; 
      
      /*if(packageName.equals("org.isf.accounting.manager")  && 
         className.equals("BillBrowserManager"))*/
      //System.out.println("INSIDE Validate Database Class Present method ...");
      
      if( methodParameterName.contains(frlCon.startParameters) && 
    	  methodParameterName.contains(frlCon.endParameters))
      {	  
    	 parameters = StringUtils.substringBetween(methodParameterName, frlCon.startParameters, frlCon.endParameters); 
         
         /*if(packageName.equals("org.isf.accounting.manager")  && 
                 className.equals("BillBrowserManager") )
         {*/	 
            /*System.out.println("ENTRE"); 
            System.out.println("Method Short Name     : " + methodShortName); 
            System.out.println("Method Parameter Name : " + methodParameterName); 
            System.out.println("Parameters            : " + parameters); 
            */
         //}
         
         if(parameters != null && !parameters.trim().isEmpty()) 
         {
        	 
            parameterArray = parameters.split(frlCon.valueDelimiter); 
            //System.out.println("Parameters Array: " + Arrays.deepToString(parameterArray)); 
         
            dbClassFound = false;
            
            for (i = 0; i < parameterArray.length; i++) 
            {
               parameter = parameterArray[i];
               parameter = parameter.stripLeading();
            
               //System.out.println("Parameter        : " + parameter);
  
               if(parameter.contains(frlCon.valueDelimiter))
               {	
                  elementArray = parameter.split(frlCon.valueDelimiter);
                  //System.out.println("Entre a caso 1");
               }
               else
                  if(parameter.contains(frlCon.startClassDef))
                  {	
                     elementArray = parameter.split(frlCon.startClassDef);
                     //System.out.println("Entre a caso 2");
                  } 
                  else 
                     if(parameter.contains(frlCon.endClassDef))
                     {	
                        elementArray = parameter.split(frlCon.endClassDef);
                        //System.out.println("Entre a caso 3");
                     } 
                     else
                        if(parameter.contains(" ")) 	
                        {   
                           elementArray = parameter.split(" ");
                           //System.out.println("Entre a caso 4");
                        }
               
               /*System.out.println("Counter Elements : " + elementArray.length);
               System.out.println("Elements Array   : " + Arrays.deepToString(elementArray)); 
               */

               for (j = 0; j < elementArray.length; j++) 
               {
                  element = elementArray[j];
                  element = element.stripLeading();
                  element = element.toLowerCase();
                
                  //System.out.println("Element        : " + element);
                  
                  // Validate if the Element contains a DB Class
                  try
                  {
                     dbClassFound = validateDBClassElement(projectId, element, frlCon);  
                  }
                  catch (Exception e1) 
               	  {
               	     errorMessage1 = e1.getMessage();
               	    throw new Exception(errorMessage1);
               	  }
                  
                  //System.out.println("DB Class Found : " + dbClassFound);
                  
                  if(dbClassFound == true)
                  {	  
                     //System.out.println("Element : " + element + " is a DATABASE CLASS"); 
                     break;
                  }  
                  
               }
               
               if(dbClassFound == true)
                  break;
            	   
               
               /*
               if(ArrayUtils.isNotEmpty(elementArray))
               {	   
                  // Parameter Data type 
                  part1 = elementArray[0];
                  part1 = part1.trim();
                  
                  // Parameter Name
                  if(e > 1)
                  {
                     part2 = elementArray[1];
                     part2 = part2.trim();
                  }
                  
                  // Validate if the Parameter Data Type is contained in an Array or a List               
                  if(part1.contains(frlCon.startClassDef))
                  {
                	 if(part1.contains(frlCon.valueDelimiter)) 
                	 {	 
                	    className2 = part1;
                	    cases++;
                	 }   
                	 else
                        className2 = StringUtils.substringBetween(part1, frlCon.startClassDef, frlCon.endClassDef); 
                  }
                  else
                	  className2 = part1;
                  
                  // Validate If the Parameter DataType is a Personalized Class
                  
                  // If it is a Personalized Class, validate if it is a Database Class
               }
               
               System.out.println("Elements Array   : " + Arrays.deepToString(elementArray)); 
               System.out.println("Part 1           : " + part1);
               System.out.println("Part 2           : " + part2);
               System.out.println("Class Name 2     : " + className2);
               System.out.println("Cases            : " + cases); 
                */
                
            
            }
         }
      }

      //System.out.println("DB Class Found FINAL: " + dbClassFound);
	  
      return dbClassFound;
   } 


   public boolean validateDBClassNamePackage(int projectId, String packageName, String className, FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1=""; 
      boolean dbClassFlag=false;
      DatabaseOperationsController dbOperCon = new DatabaseOperationsController();

	  // Connect to the Forensic-Ready Database
      try 
	  {
         dbOperCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }

      // Validate if the element contains a Class that is a Database Class
      try
      {
         dbClassFlag = dbOperCon.validateDBClassNamePackage(projectId, packageName, className);  
      }
      catch (Exception e2) 
      {
         errorMessage1 = e2.getMessage();
         throw new Exception(errorMessage1);
      }

	  // Disconnect from the Database 
	  try 
	  {
	     dbOperCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 throw new Exception(errorMessage1);
	  }
   
      return dbClassFlag;	   
   }   
   
   public boolean validateDBClassReturnType(int projectId, String methodReturnType1, String methodReturnType2, 
                                            FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="", packageName="", className="";
      int c=0, end=0;
      String[] parts;
      boolean dbReturnTypeFound=false;
      
 	  /*
      System.out.println("Return Type 1      : " + methodReturnType1); 
 	  System.out.println("Return Type 2      : " + methodReturnType2); 
 	  */

 	 
      if(methodReturnType1.contains("List") || methodReturnType1.contains("ArrayList") ||
    	 methodReturnType1.contains("class"))
      {
         // Get the packageName from the methodReturnType2
    	  
    	 parts = methodReturnType2.split(frlCon.findDot); 
    	 end   = parts.length - 1;
    	 
    	 //System.out.println("End                : " + end); 
    	 
    	 for (c = 0; c < parts.length; c++) 
    	 {
      	    /*System.out.println("Counter      : " + c); 
     	    System.out.println("Part Element : " + parts[c]); 
     	    */

     	    if(c <= (end-2))
     	    { 	
     	       packageName = packageName + parts[c] + frlCon.objectOrientedDelimiter1;
     	    }   
     	    else
     	    	if(c == (end-1))
         	    { 	
         	       packageName = packageName + parts[c];
         	    } 
     	        else
     	          if(c == end)
     	          {	
    	             className = className + parts[c];
     	          }

    	 }
    	 
    	 // Convert to lowercase
    	 packageName = packageName.toLowerCase();
    	 className   = className.toLowerCase();
    	 
    	 /*
    	 System.out.println("Final Package Name : " + packageName); 
    	 System.out.println("Final Class Name   : " + className); 
    	 */
    	 
    	 // Get the className from the methodReturnType2  
         // Validate if the Element contains a DB Class
         try
         {
            dbReturnTypeFound = validateDBClassNamePackage(projectId, packageName, className, frlCon);  
         }
         catch (Exception e1) 
       	 {
       	    errorMessage1 = e1.getMessage();
       	    throw new Exception(errorMessage1);
       	 }
         
         //System.out.println("DB Return Type Found  : " + dbReturnTypeFound); 
         
      }
        
      return dbReturnTypeFound;
	   
   }

   public String validateVariableStructure(int projectId, String methodTextFile, String line1, FRLConfiguration frlCon) throws Exception
   {
	   String errorMessage1="", errorMessage2="", line2="", pattern1="", pattern2="", pattern3="", pattern4="", 
			  element="", divExpr="";	
	   String[] parts, parts2;
	   Pattern pattern;
	   Matcher matcher;
	   boolean matchFound = false;
	   
	   pattern1 = "[\\w]+[\\s]{1}[\\w]+[\\s]*=";
	   pattern2 = "ArrayList<[\\w]+>[\\s]{1}[\\w]+[\\s]*=";
	   pattern3 = "List<[\\w]+>[\\s]{1}[\\w]+[\\s]*=";
	   pattern4 = "[\\W]+instanceof[\\s]{1}[\\w]+";
	   
	   //line = "Medical med = medManager.getMedical(medId);";
	   //line = "ArrayList<Therapy> therapies = new ArrayList<>();";
	   
	   line2 = line1.trim();
	   	   
	   try
	   {
		  // Pattern 1: Variable structure 
	      pattern    = Pattern.compile(pattern1, Pattern.CASE_INSENSITIVE);
	      matcher    = pattern.matcher(line2);
	      matchFound = matcher.find();
	      
	      /*
	      if(matchFound == true)
	      if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals") ||
			  methodTextFile.contains("/org.isf.agetype.model/AgeType/equals") ||
			  methodTextFile.contains("/org.isf.vaccine.model/Vaccine/equals")|| 
			   methodTextFile.contains("/org.isf.ward.model/Ward/equals"))
			    { 
				   
			   System.out.println("Inside Validate Variable Structure");	
			   System.out.println("====>> INSTANCE OF WAS FOUND");
			   System.out.println("Line        : " + line2);
			   System.out.println("Pattern     : " + pattern);
			   System.out.println("Matcher     : " + matcher);
			   System.out.println("Match Found : " + matchFound);
			   System.out.println("Element     : " + element);
			   System.out.println("CASE 1");
			    }
			    */
		   
	   
	      if(matchFound == false) 
	      {
	    	  
	    	 // Pattern 2: ArrayList Variable structure 
	         pattern = Pattern.compile(pattern2, Pattern.CASE_INSENSITIVE);
		     matcher = pattern.matcher(line2);
		     matchFound = matcher.find();
		     
		     /*
		     if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals"))
			    { 
			   System.out.println("CASE 2");	   
			   System.out.println("Line        : " + line2);
			   System.out.println("Pattern     : " + pattern);
			   System.out.println("Matcher     : " + matcher);
			   System.out.println("Match Found : " + matchFound);
			    }
			    */
			   
		   
		     if(matchFound == false) 
		     {
		    	 
		    	// Pattern 3: ArrayList Variable structure 
			    pattern = Pattern.compile(pattern3, Pattern.CASE_INSENSITIVE);
			    matcher = pattern.matcher(line2);
			    matchFound = matcher.find();
			    
			    /*
			    if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals"))
			    { 
			   System.out.println("CASE 3");	   
			   System.out.println("Line        : " + line2);
			   System.out.println("Pattern     : " + pattern);
			   System.out.println("Matcher     : " + matcher);
			   System.out.println("Match Found : " + matchFound);
			    }
			    */
			   
			   
			    if(matchFound == true) 
			    {
			       divExpr = frlCon.nonPrimitiveValue2 + frlCon.startClassDef;
		           parts   = line2.split(divExpr, 2);
		           element = parts[1]; 
		            
		           /*
			       System.out.println("PATTERN 3: " + pattern3); 
				   System.out.println("FOUND in this Line: " + line); 
				   System.out.println("Parts 0: " + parts[0]); 
				   System.out.println("Parts 1: " + parts[1]); 
				     
				   System.out.println("Initial Element: " + element); 
				   */
				    
				   divExpr = frlCon.endClassDef;
				   parts   = element.split(divExpr, 2);
		           element = parts[0]; 
		            
		           //System.out.println("Final Element: " + element); 

			    } 
			    else
				{
			    	/*
			       if(line2.contains("instanceof"))
			       {
			    	   divExpr = "instanceof";	
			    	   parts   = line2.split(divExpr, 2);
					   //element = parts[1]; 
			    	   
			    	   if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals") ||
						   methodTextFile.contains("/org.isf.agetype.model/AgeType/equals") ||
						   methodTextFile.contains("/org.isf.vaccine.model/Vaccine/equals"))
			    	   {
					   System.out.println("Inside Validate Variable Structure");	
					   System.out.println("====>> INSTANCE OF WAS FOUND");
					   System.out.println("Line        : " + line2);
					   System.out.println("Part 1      : " + parts[1]);
					   System.out.println("Part 2      : " + parts[2]);
					   System.out.println("CASE 4");
			    	   }
					   
			       }*/
			       
			       
				   // Added Case for the INSTANCE OF
				   pattern    = Pattern.compile(pattern4, Pattern.CASE_INSENSITIVE);
				   matcher    = pattern.matcher(line2);
				   matchFound = matcher.find();
					    
				   if(matchFound == true)
				   {	
				      divExpr = "instanceof";	
					  parts   = line2.split(divExpr, 2);
				      element = parts[1]; 
				      
				      parts2 = element.trim().split("\\W");
				      element = parts2[0];
				      
				      /*
					   if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals") ||
							   methodTextFile.contains("/org.isf.agetype.model/AgeType/equals") ||
							   methodTextFile.contains("/org.isf.vaccine.model/Vaccine/equals") || 
							   methodTextFile.contains("/org.isf.ward.model/Ward/equals"))
						    { 
							   
						   System.out.println("Inside Validate Variable Structure");	
						   System.out.println("====>> INSTANCE OF WAS FOUND");
						   System.out.println("Line        : " + line2);
						   System.out.println("Pattern     : " + pattern);
						   System.out.println("Matcher     : " + matcher);
						   System.out.println("Match Found : " + matchFound);
						   System.out.println("Element     : " + element);
						   System.out.println("CASE 4");
						    }
						    */
					   
				   }
				   
				   
				}
			    
		     }
		     else
		     {
			    divExpr = frlCon.nonPrimitiveValue1 + frlCon.startClassDef;
		        parts   = line2.split(divExpr, 2);
		        element = parts[1]; 
		            
		           /*
			       System.out.println("PATTERN 2: " + pattern2); 
				   System.out.println("FOUND in this Line: " + line); 
				   System.out.println("Parts 0: " + parts[0]); 
				   System.out.println("Parts 1: " + parts[1]); 
				     
				   System.out.println("Initial Element: " + element); 
				   */
				    
				divExpr = frlCon.endClassDef;
				parts   = element.split(divExpr, 2);
		        element = parts[0]; 
		            
		           //System.out.println("Final Element: " + element); 
		            
		     }
	      }
	      else
	      {

             parts = line2.split("\\s", 2);
             element = parts[0];
		     
             /*
	         System.out.println("PATTERN 1: " + pattern1); 
		     System.out.println("FOUND in this Line: " + line); 
		     System.out.println("Parts 0: " + parts[0]); 
		     System.out.println("Parts 1: " + parts[1]); 
		     
		     System.out.println("Element: " + element); 
		     */

	      }
	  }
	  catch (Exception e1) 
      {
	     errorMessage1 = e1.getMessage(); 
	 	 errorMessage2 = "Error XXXX: Occurred while validating the Line: " + line1 + System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "For the Method Text File: " + methodTextFile + System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "Project Identifier: " + projectId + System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
	 	 throw new Exception(errorMessage2); 	
      }
	   
	   /*if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals") ||
		   methodTextFile.contains("/org.isf.agetype.model/AgeType/equals"))
	   System.out.println("FINAL Element        : " + element); 
	   */

      return element;

   }  
     
   public boolean validateDBClassVariable(int projectId, String methodTextFile, 
		                                  FRLConfiguration frlCon, 
		                                  DatabaseOperationsController dbOperCon) throws Exception
   {
      String errorMessage1="", errorMessage2="", line1="", line2="", element="";
	  boolean dbVariableFound=false;
      Path path;
	  final Charset ENCODING;
	  
	  // Initialize Variables
      path     = Paths.get(methodTextFile);
      ENCODING = StandardCharsets.UTF_8;
	  
      /*
      if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals") ||
    	  methodTextFile.contains("/org.isf.agetype.model/AgeType/equals") ||
    	  methodTextFile.contains("/org.isf.vaccine.model/Vaccine/equals")|| 
		   methodTextFile.contains("/org.isf.ward.model/Ward/equals"))
      {	    
      System.out.println("*** Inside Validate DB Class Variable Method ***");	  
	  System.out.println("Project Id        : " + projectId); 
	  System.out.println("Method Text File  : " + methodTextFile);
      }
      */
	    
	  try ( BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
	     while ( (line1 = reader.readLine()) != null && 
	    		 (dbVariableFound == false)
	    	   ) 
		 {  
		    	
	        line2 = line1.trim();
	        
		    // Verify if the current line contains a variable
		    try
		    {
		       element = validateVariableStructure(projectId, methodTextFile, line2, frlCon);
		    }
		    catch (Exception e1) 
			{
			   errorMessage1 = e1.getMessage(); 
			   throw new Exception(errorMessage2); 	
			}  		    
		    
		    element = element.trim();
		    
		    /*
		    if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals") ||
		    	methodTextFile.contains("/org.isf.agetype.model/AgeType/equals") )
		    { 	
		       System.out.println("Line 2  : " + line2);
		       System.out.println("Element : " + element); 
		    } 
		    */ 
		    
		    // Validate if the element variable is not null or empty
		    if(element != null && !element.trim().isEmpty())
		    {	
		    	
		       // Convert the element into lower case letters
		       element = element.toLowerCase();
		       
		       try
		       {
		          dbVariableFound = dbOperCon.validateDBClassName(projectId, element);
		       }
		       catch (Exception e2) 
			   {
			      errorMessage1 = e2.getMessage(); 
			      throw new Exception(errorMessage2); 	
			   } 
		       
		       /*
		       if(dbVariableFound == true)
		       {	   
		       if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals") ||
		    	   methodTextFile.contains("/org.isf.agetype.model/AgeType/equals") ||
		    	   methodTextFile.contains("/org.isf.vaccine.model/Vaccine/equals")|| 
				   methodTextFile.contains("/org.isf.ward.model/Ward/equals")
		    	   )
		       {	   
		    	  System.out.println("*** Inside Validate DB Class Variable Method ***");	   
		          System.out.println("*** VALIDATING THE CLASS ***");
		          System.out.println("DB Variable Found : " + dbVariableFound); 
		          System.out.println("Element           : " + element);
		          System.out.println("Line              : " + line2);
		       }
		       
		       }
		       */
		       
		       
		       /*
		       if(dbVariableFound == true)
		       {	   

		          System.out.println("The line: " + line + System.lineSeparator() + " contains the Variable: " + element + " with a Database Class DATA TYPE");
		       } 
		       */  
		    }

		    	
		  }    
	     
	   }
	   catch (Exception e3) 
	   {
	      errorMessage1 = e3.getMessage(); 
	 	  errorMessage2 = "Error XXXX: Occurred while loading the Method Text File: " + methodTextFile + System.lineSeparator();
	 	  errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
	 	  errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
	 	  throw new Exception(errorMessage2); 	
	    }    
	   
	  /*
	  if( methodTextFile.contains("/org.isf.malnutrition.model/Malnutrition/equals") ||
		  methodTextFile.contains("/org.isf.agetype.model/AgeType/equals"))
	  {	  
		 System.out.println("*** Inside Validate DB Class Variable Method ***");	   
	     System.out.println("FINAL DB Variable Found : " + dbVariableFound); 
	  }   */
	  
      return dbVariableFound;
   }
   
   public int validateDatabaseClass(FRLConfiguration frlCon, 
		                            int projectId, 
		                            int classId,
		                            String classPackageName, 
		                            String className,
		                            int classDBFlag,
		                            int cmId, 
		                            String methodShortName,
		                            String methodParameterName,
		                            String methodReturnType1,
		                            String methodReturnType2,
		                            String methodTextFile,
		                            ArrayList <DatabaseMethod1> dbMethodList) throws Exception
   {
      String errorMessage1="", attributeShortName="", attributeClassPackageName ="",
 			 attributeClassName1="", attributeClassName2="", attributeDataType="", specialCase="Collection";  
      int i=0, dbCase=0;
      boolean dbAttributeFound=false, dbDmlFound=false, dbParameterFound=false, 
    		  dbReturnTypeFound=false, dbVariableFound=false, attributeClassFlag=false;
      DatabaseOperationsController dbOperCon = new DatabaseOperationsController();
      ArrayList<ClassAttribute> classAttributeList = new ArrayList<ClassAttribute>();
	   
      //System.out.println("Inside the Validate Database Class Method ...STARTING");	
      
      /*
      if( (classPackageName.contains("org.isf.malnutrition.model") && 
  		    className.contains("Malnutrition") &&
  		    methodShortName.contains("equals")) ||
  			classPackageName.contains("org.isf.agetype.model") && 
		        className.contains("AgeType") &&
		        methodShortName.contains("equals") ||
    		      classPackageName.contains("org.isf.vaccine.model") && 
  		        className.contains("Vaccine") &&
  		        methodShortName.contains("equals") ||
  		      classPackageName.contains("org.isf.ward.model") && 
		        className.contains("Ward") &&
		        methodShortName.contains("equals")
  		        )
      {	  
         
         System.out.println("Inside the Validate Database Class ...");
    	 System.out.println("*** GENERAL INFORMATION ***");
         System.out.println("Id                     : " + cmId);
         System.out.println("Package Name           : " + classPackageName);
         System.out.println("Class Name             : " + className);
         
         System.out.println("Method Short Name      : " + methodShortName);
         System.out.println("Method Parameter Name  : " + methodParameterName);
         System.out.println("Method Return Type 1   : " + methodReturnType1);
         System.out.println("Method Return Type 2   : " + methodReturnType2);
         System.out.println("Method Text File       : " + methodTextFile);
      }
      */
    	  
      /***********************************
       * Get the Attributes of the Class 
       **********************************/
	  // Connect to the Forensic-Ready Database
      try 
	  {
         dbOperCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
      
	  // Connect to the Forensic-Ready Database
      try 
	  {
         classAttributeList = dbOperCon.loadClassAttributes(projectId, classId);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
      
      /*
      System.out.println("Project Id           : " + projectId);
      System.out.println("Class Package Name   : " + classPackageName);
      System.out.println("Class Short   Name   : " + classShortName);
      System.out.println("Class Attribute List : " + Arrays.deepToString(classAttributeList.toArray()));
      */
      
      if(classAttributeList.isEmpty() == false)
      {	  
    	  
    	 //System.out.println("INSIDE DATABASE CASE # 2");
    	  
         // Initialize the variables
    	 i = 0; 
    	 dbAttributeFound = false;
    	     	 
         // Loop to navigate across all the attributes
    	 while ( (i < classAttributeList.size()) && 
    			  dbAttributeFound == false) 
	     {
            attributeShortName        = classAttributeList.get(i).getShortName();
            attributeClassPackageName = classAttributeList.get(i).getPackageName();
            attributeClassName1       = classAttributeList.get(i).getClassName();
            attributeDataType         = classAttributeList.get(i).getDataType();
            
            if(attributeDataType.equals(specialCase))
            {
               attributeClassName2 = attributeClassName1;
               attributeClassName2 = attributeClassName2.replace(specialCase + frlCon.startClassDef,"");
               attributeClassName2 = attributeClassName2.replace(frlCon.endClassDef,"");
            }
            else
            	attributeClassName2 = attributeClassName1;
            
            // Validate if the attribute Class and Package belongs to a Class that performs Data Operations
            try 
      	    {
               attributeClassFlag = dbOperCon.validateDBClassNamePackage(projectId, attributeClassPackageName, attributeClassName2);
      	    } 
      	    catch (Exception e2) 
      	    {
      	       errorMessage1 = e2.getMessage();
      		   throw new Exception(errorMessage1);
      	    }
            
            /*
            System.out.println("Attribute Class Flag : " + attributeClassFlag);
            System.out.println("Class DBFlag         : " + classDBFlag);
            System.out.println("Attribute Short Name : " + attributeShortName);
            System.out.println("Method Text File     : " + methodTextFile);
            */
            
            // If the Class of the Attribute performs Data Operations then, verify if it is present in this Method Text File
            if(attributeClassFlag == true || classDBFlag == 1)
            {	
               /**********************************************************************
                * 1.- Verify if an Attribute exists in the Current Method Text File
               ************************************************************************/
               dbAttributeFound = validateAttributeMethod(attributeShortName, methodTextFile);
               
               //System.out.println("Attribute Found         : " + dbAttributeFound);
            }

            i++;
                        
	     }    	       
      }
      
      /*
      System.out.println("Attribute Short Name : " + attributeShortName);
      System.out.println("Method Text File     : " + methodTextFile);
      System.out.println("DB Attribute Found   : " + dbAttributeFound);
      */
        
      if(dbAttributeFound == false)
      {    	 
         /**********************************************************************
         * 2.- Verify if an Method Body contains a DML instruction either:
         * insert, delete, update or select
         ************************************************************************/ 
    	 
    	 /*
    	 System.out.println("*** VALIDATING THE CASE NUMBER 3 ***");
    	 System.out.println("Class Package Name : " + classPackageName);
    	 System.out.println("Class Name         : " + className);
    	 System.out.println("Method Text File   : " + methodTextFile);
    	 System.out.println("DB Method List     : " + Arrays.deepToString(dbMethodList.toArray()));
    	 */
    	  
    	 dbDmlFound = validateDMLMethod(projectId, classPackageName, className, methodTextFile,  dbMethodList, frlCon);
    	 
    	 /*System.out.println("*** FINALIZING CASE NUMBER 3 ***");
    	 System.out.println("DML FOUND: " + dbDmlFound);
    	 System.out.println("********************************"); 
    	 */

    	 if(dbDmlFound == false)
    	 {
             /**************************************************************************************
              * 3.- Verify if the Method Definition contains a Parameters that has a Database Class
             ***************************************************************************************/  
    	    dbParameterFound = validateDBClassParameters(projectId, cmId, classPackageName, className, methodShortName,
            		                                     methodParameterName, methodTextFile, frlCon);
             
       	    if(dbParameterFound == false)
       	    {
                /**************************************************************************************
                 * 4.- Verify if the Method Definition contains a Return Type that has a Database Class
                ***************************************************************************************/   
       	       dbReturnTypeFound = validateDBClassReturnType(projectId, methodReturnType1, methodReturnType2, frlCon);
               
               if(dbReturnTypeFound == false)
               {	            	  
            	  
                  /*******************************************************************************
                  * 5.- Verify if the Method Body contains a Variable that has a Database Class
                  ********************************************************************************/    
                  dbVariableFound = validateDBClassVariable(projectId, methodTextFile, frlCon, dbOperCon);
                  
                   /*
                  if( (classPackageName.contains("org.isf.malnutrition.model") && 
                		    className.contains("Malnutrition") &&
                		    methodShortName.contains("equals")) ||
                			classPackageName.contains("org.isf.agetype.model") && 
              		        className.contains("AgeType") &&
              		        methodShortName.contains("equals") ||
              		      classPackageName.contains("org.isf.vaccine.model") && 
            		        className.contains("Vaccine") &&
            		        methodShortName.contains("equals")||
            	  		      classPackageName.contains("org.isf.ward.model") && 
            			        className.contains("Ward") &&
            			        methodShortName.contains("equals"))
                	  System.out.println("dbVariableFound : " + dbVariableFound);
                	  */
                  
                  if(dbVariableFound == true)
                  {	  
                     dbCase = 6;
                     /*
                     if( (classPackageName.contains("org.isf.malnutrition.model") && 
                   		    className.contains("Malnutrition") &&
                   		    methodShortName.contains("equals")) ||
                   			classPackageName.contains("org.isf.agetype.model") && 
                 		        className.contains("AgeType") &&
                 		        methodShortName.contains("equals") ||
                   		      classPackageName.contains("org.isf.vaccine.model") && 
                 		        className.contains("Vaccine") &&
                 		        methodShortName.contains("equals")||
                 	  		      classPackageName.contains("org.isf.ward.model") && 
                 			        className.contains("Ward") &&
                 			        methodShortName.contains("equals"))
                     	  System.out.println("DB CASE : " + dbCase);
                     	  */
                     
                  }   
                  else
                  {
                	  dbCase = 0;
                	  /*
                	  if( (classPackageName.contains("org.isf.malnutrition.model") && 
                	  		    className.contains("Malnutrition") &&
                	  		    methodShortName.contains("equals")) ||
                	  			classPackageName.contains("org.isf.agetype.model") && 
                			        className.contains("AgeType") &&
                			        methodShortName.contains("equals") ||
                        		      classPackageName.contains("org.isf.vaccine.model") && 
                      		        className.contains("Vaccine") &&
                      		        methodShortName.contains("equals")||
                        		      classPackageName.contains("org.isf.ward.model") && 
                      		        className.contains("Ward") &&
                      		        methodShortName.contains("equals"))
                         System.out.println("LLEGUE HASTA ACA");
                         */
                  }
               }   
               else
            	   dbCase = 5;
               
       	    }
       	    else
       	    	dbCase = 4;
    	  }
    	  else
    	     dbCase = 3;

      }
      else
  	    dbCase = 2;

      
      /***************************************************************
      * 4.- Validate all the Flags to determine whether or not the 
      * Class performs Data Operations 
      ****************************************************************/
      
      /*if(dbAttributeFound == true || dbDmlFound == true        || 
    	 dbParameterFound == true || dbReturnTypeFound == true ||
    	 dbVariableFound == true)
    	  
         dbClassFlag = true;
      else
      {	  
         dbClassFlag = false;
         dbCase = 0;
      } 
      */  
     	
	  // Disconnect from the Database 
	  try 
	  {
	     dbOperCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
	  //System.out.println("9");	     
	  //System.out.println("Inside the Validate Database Class Method ... FINISHING");	
	  
	  //return dbClassFlag;
	  
	  //System.out.println("DB Case: " + dbCase);
	  		
	  return dbCase;
	  
   }
   
   public void printingDatabaseMethods(FRLConfiguration frlCon, ArrayList <String> DBMethods) throws Exception
   {
      String format="", DBMethod="";
      int i=0;

	  // Initialize variables
      format = frlCon.tabDelimiter1;

      System.out.println("");
	  System.out.println("For the Project: " + frlCon.projectName); 
	  System.out.println("These are the Methods that perform Data Changes: ");
	    
	  for(i = 0; i < DBMethods.size(); i++) 
	  {
	     DBMethod = DBMethods.get(i);
		 System.out.format(format, DBMethod, 1);
		 System.out.println(DBMethod);
	  }   

   }
   
   public void updateMethodDBOperationsLocally(FRLConfiguration frlCon, int projectId, int cmId, int dbCase) throws Exception
   {
      String errorMessage1="";	   
      DatabaseOperationsController dbOperCon = new DatabaseOperationsController();

      // 1.- Connect to the Database
	  try 
	  {
	     dbOperCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
      // 2.- Update this method in the cm_database_method table 
      //     to indicate this method performs data operations 
      try 
  	  {	 	   
         dbOperCon.updateMethodDBOperations(projectId, cmId, dbCase);
      }
      catch(Exception e2)
      {
         errorMessage1 = e2.getMessage();
  	     throw new Exception(errorMessage1);
      }
      
      // 3.- Disconnect from the Database
      try 
      {
   	     dbOperCon.disconnect();
   	  } 
      catch (Exception e3) 
      {
         errorMessage1 = e3.getMessage();
    	 throw new Exception(errorMessage1);
   	  }	     	 
     	 
   }
   
   public DatabaseData getDatabaseMethods(FRLConfiguration frlCon, int projectId) throws Exception //11
   {
      String errorMessage1="", packageName="", className="", methodShortName="",
    		 methodParameterName="", methodReturnType1="", methodReturnType2="",
    		 methodTextFile="", methodFullName2="";
      int c=0, cmId=0, dbClassFlagInt=0, jf=0, dbCase=0, cIId=0, guiClassFlagInt=0;
      boolean dbMethodsFlag=false, dbClassFlag=false, methodFlag=false;
      
      ArrayList <DatabaseMethod1> initialDBMethodList = new ArrayList<DatabaseMethod1>(); 
	  ArrayList <String> methodDBList1 = new ArrayList<String>();	
	  ArrayList <String> methodDBList2 = new ArrayList<String>();	
	  ArrayList <String> methodDBListFinal = new ArrayList<String>();	
	  
	  ArrayList<ClassMethod> javaFilesList = new ArrayList<ClassMethod>();
	  ArrayList<ClassInformation> dbClassList = new ArrayList<ClassInformation>();
      DatabaseOperationsController dbOperCon = new DatabaseOperationsController();
      DatabaseData dbDataRecord;
	  
      // Initialize the Method DB Array Lists
      initialDBMethodList.clear();
      methodDBList1.clear();
      methodDBList2.clear();
      methodDBListFinal.clear();
      javaFilesList.clear();
      dbClassList.clear();
      
      //System.out.println("Inside the Get Database Method Method ...");
      
      // 1.- Connect to the Database
	  try 
	  {
	     dbOperCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
      
	  // 2.- Get the Database Method List for this Project from the Database 
      // These are Java Instructions stored in the database_method table
      // Such instructions can be found in each method inside the Java Files
      // If one or more of these instructions are Found in the method 
	  // it makes this method to performs data operations
      try
      {
         initialDBMethodList = dbOperCon.loadDatabaseMethods(projectId);
      }
      catch(Exception e2)
      {
         errorMessage1 = e2.getMessage();
         throw new Exception(errorMessage1);
      }
      
      // 3.- Get all the Java Files and Details from the Database from this Project
      try
      {
         javaFilesList =  dbOperCon.loadJavaFiles(projectId);
      }
      catch(Exception e3)
      {
         errorMessage1= e3.getMessage();
         throw new Exception(errorMessage1);  
      }
      
      // 4.- Disconnect from the Database
      try 
      {
	     dbOperCon.disconnect();
	  } 
      catch (Exception e4) 
      {
 	     errorMessage1 = e4.getMessage();
 		 throw new Exception(errorMessage1);
	  }	   
      
      // 5.- Validate the Database Method List
      if(initialDBMethodList.isEmpty() == false)
      {	  
    	 javaFilesList.size();
    	  
    	 // 5.1.- Validate the Java Files 
    	 if(javaFilesList.isEmpty() == false) 
         {	 
    		
            System.out.println("");
		    System.out.println("For the Project: " + frlCon.projectName); 
		    System.out.println("Getting from the Java Files the Methods that perform Data Changes ...");
	
		    System.out.println("These are the Methods that perform Data Changes: ");
		      
		    // Initialize Variables
		    //dbClassCounter = 0;
		    
            // 6.- For every Java File Loop 	  
		    for(c = 0; c < javaFilesList.size(); c++) 
	        {
		       jf = c + 1;  	
			   System.out.println("Analizing the Java File: " + jf + " of " + javaFilesList.size());
			    
		       // Get the Details of each Java File	
		       cmId                = javaFilesList.get(c).getId();
		       packageName         = javaFilesList.get(c).getPackageName();
		       className           = javaFilesList.get(c).getClassName();
			   methodShortName     = javaFilesList.get(c).getShortMethodName();

			   methodParameterName = javaFilesList.get(c).getParameterMethodName();
			   methodReturnType1   = javaFilesList.get(c).getReturnType1();
			   methodReturnType2   = javaFilesList.get(c).getReturnType2();
		       methodTextFile      = javaFilesList.get(c).getTextFileName();  
		       
		       dbClassFlagInt      = javaFilesList.get(c).getDatabaseClass();
		       
		       cIId                = javaFilesList.get(c).getCiId();
		       guiClassFlagInt     = javaFilesList.get(c).getGuiClass();
		       //interfaceFlag       = javaFilesList.get(c).getInterfaceClass();
		       
			   //methodFullName       = javaFilesList.get(c).getFullMethodName();
		       //dbClassType          = javaFilesList.get(c).getDatabaseClassType();
		       
		       dbClassFlag = false;
		       
		       /*
		       System.out.println("Cm Id              : " + cmId);
		       System.out.println("Package Name       : " + packageName);
		       System.out.println("Class Name         : " + className);
		       System.out.println("Method Short Name  : " + methodShortName);
		       System.out.println("Method Text File   : " + methodTextFile);
		       System.out.println("DB Class Flag Int  : " + dbClassFlagInt);
		       System.out.println("GUI Class Flag Int : " + guiClassFlagInt);
		       System.out.println("Class Info Id      : " + cIId);
		       */
		       
		       methodFlag = false;
		       
               // 7.- Validate if the Class of this Java File (dbClassFlagInt field), 
		       // Performs Data Operations
		       if(dbClassFlagInt == 0)
		       {
		    	   //System.out.println("*** ENTRE AL CASO UNO ***");
		    	   
                  /********************************************************/
		    	  // Case 7.1. - The Class Do not Perform Data Operations
		    	  /********************************************************/
		    	    
 	              // 7.2 Validate if the Current Method (textFile) contains a Database Method
                  dbMethodsFlag = validateDatabaseMethod(projectId, methodTextFile, initialDBMethodList, frlCon);
               
	              // 7.3 Validate that the dbMethodsFlag is True which means there is some methods that perform data operations
	   	          if(dbMethodsFlag == true)
	   	          {	 
	   	        
	   	             // Assign the Database Case
	   	        	 dbCase = 1; 
	   	        	  
			         // 7.4 Update this method in the cm_database_method table 
	   	        	 // to indicate this method performs data operations 
			       	 try 
			    	 {	 	   
			       	    updateMethodDBOperationsLocally(frlCon, projectId, cmId, dbCase);
			         }
			         catch(Exception e5)
			         {
			            errorMessage1 = e5.getMessage();
			    	    throw new Exception(errorMessage1);
			         }
	   	        	  
	   	        	 methodFullName2 = packageName + frlCon.objectOrientedDelimiter1  + 
        			                   className   + frlCon.objectOrientedDelimiter1  + 
        			                   methodShortName;
	   	        	 
	   	        	 //System.out.println("Method Full Name 2: " + methodFullName2);
                
                     // 7.6 .- Add this method that performs Data Operations
	   	        	 // to the Method List 1
	   	        	 methodDBList1.add(methodFullName2); 
                     
	   	        	 methodFlag = true;
		          }
		          
		       }
		       /*else
		       {*/

		       
		       if(methodFlag == false)
		       {	  
			      //System.out.println("*** ENTRE AL CASO DOS ***");
			    	     
			      /********************************************************/
			      /* Case 8.- The Class Do Not performs GUI Operations and 
				   *          The Class either performs Data Operations or
				   *          It is an Interface */
				  /********************************************************/ 
			         
			      /*if ( guiClassFlagInt == 0 &&
		        	  (dbClassFlagInt == 1 || interfaceFlag == 1)
		        	 )	*/
			      if ( guiClassFlagInt == 0 )	
		          {	  
		    	     
		             //dbClassCounter++;
		          
		             // 8.1- Validate if this Class performs Data Operations
		             dbCase = validateDatabaseClass(frlCon,projectId,
		        		                            cIId,
                                                    packageName, 
                                                    className,
                                                    dbClassFlagInt,
                                                    cmId, 
                                                    methodShortName,
                                                    methodParameterName,
                                                    methodReturnType1,
                                                    methodReturnType2,
                                                    methodTextFile,
                                                    initialDBMethodList); 
		          
		             //System.out.println("DB Case: " + dbCase);
		          
		             // 8.2 Validate that the dbMethodsFlag is True which means there is some methods that perform data operations
	   	             //if(dbClassFlag == true)
		             if(dbCase > 0)
	   	             {	 
	   	                // 8.3 Update this method in the cm_database_method table 
	   	        	    // indicating this method performs data operations 
				        try 
				        {	 	   
				           updateMethodDBOperationsLocally(frlCon, projectId, cmId, dbCase);
				        }
				        catch(Exception e6)
				        {
				           errorMessage1 = e6.getMessage();
				    	   throw new Exception(errorMessage1);
				        }
	   	        	  
                        // 8.4 .- Build the full Method Name Structure: model/UserDatabase/getUserLevel.txt
				        methodFullName2 = packageName + frlCon.objectOrientedDelimiter1  + 
			                              className   + frlCon.objectOrientedDelimiter1  + 
			                              methodShortName;
				     
				     
				        //System.out.println("Method Full Name: " + methodFullName2);
                		  
                        // 8.5 Add this method that performs Data Operations
	   	        	    // to the Method List 2
				        methodDBList2.add(methodFullName2); 
                     
		             }
		          }
		       }
	   	          
		       
            }		    	
		    
		   // 10.- Build the Final Method DB List that contains all the Methods that perform Data Operations
		    
		   //System.out.println("Method DB List 1 Count: " + methodDBList1.size()); 
		   methodDBListFinal.addAll(methodDBList1);
		   
		   //System.out.println("Method DB List 2 Count: " + methodDBList2.size()); 
		   methodDBListFinal.addAll(methodDBList2);
		   
		   //System.out.println("Method DB List FINAL Count: " + methodDBList1.size()); 
		    
		   // Remove the duplicate values in the Method List 2:
		   methodDBListFinal = (ArrayList<String>) methodDBListFinal.stream().distinct().collect(Collectors.toList());
		    
		   //System.out.println("Method DB List: " + Arrays.deepToString(methodDBListFinal.toArray()));
		     
           // 11.- Validate the List of the Methods that perform data changes
		   
		   
           if(methodDBListFinal.isEmpty() == false)
           {	
        	   
               // 7.- Display on the Screen the Method Name that perform data operations	
               try
               {
                  printingDatabaseMethods(frlCon, methodDBListFinal);
               }
               catch(Exception e9)
               {
                  errorMessage1 = e9.getMessage();
                  throw new Exception(errorMessage1);
               }
               
        	   //System.out.println("The List of the Methods that performs Data Operations not EMPTY.");

            }   
            else
            {	
               errorMessage1 = errorMessage1 + "Warning XXXX: The Project Id: " + projectId + System.lineSeparator();
               errorMessage1 = errorMessage1 + "Project Name: " + frlCon.projectName + System.lineSeparator();
               errorMessage1 = errorMessage1 + "Input Directory: " + frlCon.projectInputDir + File.pathSeparator + System.lineSeparator();
               errorMessage1 = errorMessage1 + "Does not have any Methods that perform Data Changes." + System.lineSeparator();
               System.out.println("");
               System.out.println(errorMessage1);
            }  
              
         }
         else
         {	 
            errorMessage1 = "Error XXXX: There are NO Java Files for the Project Id: " + projectId;
            throw new Exception(errorMessage1);
         }
         
      }
      
      else
      {	 
          errorMessage1 = "Error XXXX: There are NO Database Methods List for the Project Id: " + projectId;
          throw new Exception(errorMessage1);
      }
      
      // Create a new record
      dbDataRecord = new DatabaseData(dbClassFlag, methodDBListFinal);
      
      return dbDataRecord;	     
		   
   }
   
   public boolean validateDBClassProject(int projectId, FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="";	   
      boolean dbClassFlag = false;
      DatabaseOperationsController dbOperCon = new DatabaseOperationsController();
      
         
      // 1.- Connect to the Database
	  try 
	  {
	     dbOperCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
	  // 2.- Get the Flag about the Classes that perform Data Operations
	  try 
	  {
	     dbClassFlag = dbOperCon.getDBClassFlag(projectId);
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
	 	 throw new Exception(errorMessage1);	  
	  }		  
	  
      // 3.- Disconnect from the Database
      try 
      {
	     dbOperCon.disconnect();
	  } 
      catch (Exception e3) 
      {
 	     errorMessage1 = e3.getMessage();
 		 throw new Exception(errorMessage1);
	  }
      
      return dbClassFlag;	  
      
   }

   public String getInputMethodResults(FRLConfiguration frlCon, int projectId)
   {
      String errorMessage1="", errorMessage2="";
	  boolean dbClassFlag=false;
	  ArrayList<String> dbMethods = new ArrayList<String>();  
	  
      DatabaseData dbDataRecord;
      MethodHierarchy mh = new MethodHierarchy();
	   
	  if(frlCon.inputMethod.equals(frlCon.dataInputMethod ))
	  {	   
		 /********************************************************************/
		 // 1.- Generate the Classes and Methods that perform data operations
		 /********************************************************************/
		  		  
		 //System.out.println("Inside the Get Input Method Results method ...");
		  
	     // Get the List of the Methods that perform Data Operations in the Project
	     try 
	     {
	        dbDataRecord = getDatabaseMethods(frlCon, projectId);
		 } 
	     catch (Exception e1) 
	     {
            errorMessage1 = e1.getMessage();
            return errorMessage1;
		 }
	     
	     // Get the information from the dbDataRecord
	     
	     // Get the Method List that perform Data Operations
	     try 
	     {
		    dbMethods = dbDataRecord.getMethodDBList();
	     }
		 catch (Exception e2) 
	     {
            errorMessage1 = e2.getMessage();
            errorMessage1 = e2.getMessage(); 
   		    errorMessage2 = "Error XXXX: Occurred while loading the Method List that perform Data Operations for the Project Id: " + projectId + System.lineSeparator();
   		    errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
   		    return errorMessage2; 	  
		 }
	     
	     
	     //System.out.println("");
	     //System.out.println("DbMethods Count : " + dbMethods.size());
	     
	     
	     
	     // Get the Flag about the Classes that perform Data Operations
	     try 
	     {
	        dbClassFlag = validateDBClassProject(projectId, frlCon);
	     }
		 catch (Exception e3) 
	     {
	        errorMessage1 = e3.getMessage();
	   		return errorMessage1; 	  
		 }	
		 
	     
	     //System.out.println("DB Class Flag   : " + dbClassFlag);
	      
	     //System.out.println("");
	     //System.out.println("DB Methods List : " + Arrays.deepToString(dbMethods.toArray()));
	     
	     
	     /*********************************************/
	     // 2.- Generate the Method Hierarchy 
	     // Between:
	     // - Methods that perform Data Operations and 
	     // - Methods that perform Graphical Operations
	     /*********************************************/
	     	     
	     // Validate that the list of the methods that perform Data Changes is not empty 
	     if(dbMethods.isEmpty() == false)
		 {	
			// Generate the Hierarchy-Call of the Methods that performs Data Changes  
	    	// that they DO NOT have Classes that perform Data Changes
		    if( dbClassFlag == false)
			{  	
	           errorMessage1 = mh.getMethodsTreeStructureNoDBClasses(frlCon, projectId);
	           
	           //System.out.println("ENTRE CASO UNO ");
		       
	           return errorMessage1;
			}
			else
			{
			   //System.out.println("Saving the Method Hierarchy for the Methods with Classes that perform Data Operations ...");
			   
			   // Generate the Hierarchy-Call of the Methods that performs Data Changes   
			   // that DO have Classes that perform Data Changes
		       errorMessage1 = mh.getMethodsTreeStructureDBClasses(frlCon, projectId);
		       
		       //System.out.println("ENTRE CASO DOS");
			   
		       return errorMessage1;	 
		    }
		    
		  }	 
		  else
		  { 	
	         errorMessage1 = "The List of the Methods that performs Data Changes is empty.";
	         return errorMessage1;	 
		  }  

       }
      else
      {	   
         errorMessage1 = "The Input Method: " + frlCon.inputMethod + " has not any funcionality implemented yet in the Forensic Ready Logger System.";	   
         return errorMessage1;	 
      }
   
   }
}
