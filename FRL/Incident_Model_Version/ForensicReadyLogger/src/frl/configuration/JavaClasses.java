package frl.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import frl.controller.ClassMethodController;
import frl.model.ClassMethod;

public class JavaClasses
{
	
   public JavaClasses() //1
   {
	   
   }
   
   public ArrayList<String> readJavaFiles(String inputDir, String projectName, 
		                                  String tabDelimiter, String programmingLanguage) //2
   {
	   
      File dir = new File(inputDir);
      String pl = programmingLanguage.toLowerCase(), format;
	  String[] extensions = new String[] {pl};
	  ArrayList<String> javaFiles = new ArrayList<String>();
	  
	  // Validate that the Input Directory exists
	  if(dir.exists() && dir.isDirectory()) 
	  {	 
		 
		 System.out.println("");
		 System.out.println("For the Project: "+projectName ); 
	     System.out.println("Loading the Source Code ...");
		 format = tabDelimiter;
		 System.out.println("Folder: ");
		 System.out.format(format, dir.getAbsolutePath(), 1);
		 System.out.println(dir.getAbsolutePath());
		 
		  
		 List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		 for (File file : files) 
		 {
		    javaFiles.add(file.getAbsolutePath().toString());
		 }
		  
	  }	 
	  else	  
	     System.out.println("Error 1221: Ocurred because the Project Directory: "+ inputDir +" does not exists.");
	  
      return javaFiles;
	    
   }
   
   public static String getMethodFile(String fileName, String textFileExtDelimiter) //3
   {
      String methodFileName = null;
      String cStr = null;
      int c = 0;
      
      File file = new File(fileName + textFileExtDelimiter);

      while(file.exists())
      {

         c++;

         // Convert the counter into a String
         cStr = String.valueOf(c);

         file = new File(fileName + cStr + textFileExtDelimiter);
         
      } 
      
      methodFileName = file.getName();
       
      return methodFileName;
   }  
   
   public static String createDirectory(String pathName, String folderName) //4
   {   
	  String fullFolderName = pathName + folderName; 
	  File newFolder = new File(fullFolderName);
	  
	  if (!newFolder.exists() ) 
	     newFolder.mkdir();
	   
      return fullFolderName;	        
   }
   
   public void deleteDirectory(String pathName, String folderName) //5
   {   
	  String fullFolderName = pathName + folderName; 
	  File folder = new File(fullFolderName);
	  String errorMessage;
  
	  try 
	  {
	     FileUtils.deleteDirectory(folder);
	  } 
	  catch (IOException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error 1251: Occurred while deleting the Project Output Directory. Error Message: " + errorMessage);

	  }
      
   }
   
   public static String getFileMethodBody(String projectName, String outputDir,
		                                  String packageName, String className, 
		                                  String shortMethodName, String fileName, 
		                                  int lineStart, int lineEnd, 
		                                  FRLConfiguration frlCon) //6
   {
		String errorMessage   = null;
		String subDirName     = null;
		String createdDir     = null;
		String fileTxtName    = null;
		String fileMethodName = null;
		
		// Create the Application Directory
		subDirName = outputDir;
		createdDir = createDirectory(subDirName, projectName);
		
		// Create the Package Directory
		subDirName = createdDir + frlCon.elementsFileName;
		createdDir = createDirectory(subDirName, packageName);
		
		// Create the Class Directory
		subDirName = createdDir + frlCon.elementsFileName;
		createdDir = createDirectory(subDirName, className);
		
		subDirName = createdDir + frlCon.elementsFileName;
		
		fileTxtName = shortMethodName;
		
		fileMethodName = getMethodFile(subDirName + fileTxtName, frlCon.textFileExtDelimiter);
		
		try 
		{
		   // Appends to new text file 
		   Writer fileWriter = new FileWriter(subDirName+fileMethodName, false);
		
		   try 
		   {
			
			   BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
				
			   String line = "";
			   int c = 1;
			   String methodBody = "";
				
			   while ((line = fileReader.readLine()) != null) 
			   {	 
			      if(c >= lineStart && c <=lineEnd)  
				     methodBody += line + frlCon.newLine2;  
				  c ++;
			   }   
					
			   // Write the content of the Method Body into a Text File
			   fileReader.close();
			   fileWriter.write(methodBody);
			   fileWriter.flush();
			   fileWriter.close();
				
		    } 
			catch (Exception e) 
			{
			   errorMessage = e.getMessage();
			   System.out.println("Error Error 1261: Occurred while loading the Java File. Error Message: " + errorMessage);
			}
		
		} 
		catch (IOException e1) 
		{
		   errorMessage = e1.getMessage();
		   System.out.println("Error 1262: Occurred while writing into a Text File. Error Message: " + errorMessage);
		} 
      return fileMethodName;	
   }
   
   public static void getClassMethodsBody(ArrayList<String> inputClassFileNames, FRLConfiguration frlCon) //7
   {
	   
      String inputClassName = null, className, fileName = null;
      String fileName1, packageName1, shortMethodName1;
      String errorMessage, fileMethodBody1;
      String fileNameSec[];
      int id1, lineStart1, lineEnd1 = 0;
      ClassMethodController classMetCon = new ClassMethodController();
      ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
      
      // Connect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.connect(frlCon.propertiesFilePath);
	  } 
      catch (Exception e) 
      {
		 errorMessage = e.getMessage();
		 System.out.println("Error 1271: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      
      // For every Input Class Loop 
      for(int i = 0; i < inputClassFileNames.size(); i++) 
	  {
	    	  
         inputClassName = inputClassFileNames.get(i);
         
         // Obtain the filename
    	 fileName = inputClassName;
    	  
    	 // Split the filename into sections
    	 fileNameSec = fileName.split(frlCon.elementsFileName);
    	  
    	 // Get the class name of the file
    	 className = fileNameSec[fileNameSec.length - 1];
    	 className = className.replaceFirst(frlCon.replacePattern, "");
    	 
         try 
         {
   		    classMetList = classMetCon.load(className);
   		    
   		    // For every Method Loop 
            for(int j = 0; j < classMetList.size(); j++) 
      	    {
               
               id1  	        = classMetList.get(j).getId();
               packageName1     = classMetList.get(j).getPackageName();
               shortMethodName1 = classMetList.get(j).getShortMethodName();
               fileName1        = classMetList.get(j).getFileName();
               lineStart1       = classMetList.get(j).getStartLineNumber();
               lineEnd1         = classMetList.get(j).getEndLineNumber();
               
               fileMethodBody1 = getFileMethodBody(frlCon.projectName, frlCon.projectOutputDir, packageName1, 
                                                   className, shortMethodName1, fileName1, 
                                                   lineStart1, lineEnd1, frlCon);
               
               // Update the FileMethodBody into the Class Methods  table
               classMetCon.updateTextFileName(id1, fileMethodBody1);
               
      	    }
   		    
   	     } 
         catch (Exception e1) 
         {
   		    errorMessage = e1.getMessage();
   		    System.out.println("Error 1272: Occurred while loading the existing Class Method. Error Message: " + errorMessage);
   	     }
             
      } 
      
      // Disconnect from the Forensic Ready Logger Database
      classMetCon.disconnect();
        
   }  
 
   
   public static int getMethodLastLineNumber(String fileName, String inputCharacter) throws IOException //8
   {
	   
      Path path = Paths.get(fileName);
      final Charset ENCODING = StandardCharsets.UTF_8;
      String lineStr = null;
      int lineNum = 0, prevEnd = 0, lastEnd = 0;
      
	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
		  
	     lineStr = null;
	     lineNum = 1;
	     
	     while ((lineStr = reader.readLine()) != null) 
	     {

	        if (lineStr.contains(inputCharacter))
	    	{	 
	           prevEnd = lastEnd;	
	           lastEnd = lineNum;
	    	}
	    	 
	    	lineNum ++;
	    		 
	     }      
      }
	  
      return prevEnd + 1;
   
   }
  
   
   public static void getClassMethodsZeroEndLines(ArrayList<String> inputClassFileNames, 
		                                          String propertiesFilePath,
		                                          String endMethod) //9
   {
	   
      String errorMessage,fileName;
      int id, endLineNum;    
      ClassMethodController classMetCon = new ClassMethodController();
      ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
      
      // Connect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.connect(propertiesFilePath);
	  } 
      catch (Exception e) 
      {
		 errorMessage = e.getMessage();
		 System.out.println("Error 1291: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      
      // Load the Class Methods  that have End Line Numbers Zero
      try 
      {
    	 // For every Input Class Loop  
   	     classMetList = classMetCon.loadZeroEndLineNumbers();
   		    
   	     for(int i = 0; i < classMetList.size(); i++) 
 	     {
   	    	// Get the information from the Database 
   	        id         = classMetList.get(i).getId();
   	        fileName   = classMetList.get(i).getFileName();
   	        endLineNum = getMethodLastLineNumber(fileName, endMethod); 
   	        
   	        // Update the EndLinesNumber field in the ClassMethod Table
        	classMetCon.updateEndLineNumbers(id, endLineNum);
   	        	        
   	     }
   	     
      }   
      catch (Exception e1) 
      {

         errorMessage = e1.getMessage();
   		 System.out.println("Error 1292: Occurred while loading the existing Class Method with Zero End Line Numbers. Error Message: " + errorMessage);

   	  }
             
      // Disconnect from the Forensic Ready Logger Database
      classMetCon.disconnect();
        
   }
   

   public static void getClassMethodsEndLines(ArrayList<String> inputClassFileNames, 
		                                      String propertiesFilePath, 
		                                      String elementsFileName,
		                                      String replacePattern) //10
   {
	   
      String inputClassName = null, className, fileName = null, errorMessage;
      String fileNameSec[];
      int id1, endLine1 = 0, size, k;
      
      ClassMethodController classMetCon = new ClassMethodController();
      ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
      
      // Connect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.connect(propertiesFilePath);
	  } 
      catch (Exception e) 
      {

		 errorMessage = e.getMessage();
		 System.out.println("Error 12101: Occurred while connecting to the Database. Error Message: " + errorMessage);

	  }
      
      // For every Input Class Loop 
      for(int i = 0; i < inputClassFileNames.size(); i++) 
	  {
	    	  
         inputClassName = inputClassFileNames.get(i);
         
         // Obtain the filename
    	 fileName = inputClassName;
    	  
    	 // Split the filename into sections
    	 fileNameSec = fileName.split(elementsFileName);
    	  
    	 // Get the class name of the file
    	 className = fileNameSec[fileNameSec.length - 1];
    	 className = className.replaceFirst(replacePattern, "");
         
         try 
         {
   		    classMetList = classMetCon.load(className);
   		    size = classMetList.size()-1;
   		
   		    // For every Method Loop 
            for(int j = 0; j < classMetList.size(); j++) 
      	    {
               
               id1 = classMetList.get(j).getId();

           	   // Calculate the End Line Number
           	   if(j < size)
           	   {	   
           	      k        = j + 1;
                  endLine1 = classMetList.get(k).getStartLineNumber() - 1;
           	   }
           	   else   
           		   endLine1 = 0;
           	     
           	   // Update the EndLinesNumber field in the ClassMethod Table
           	   classMetCon.updateEndLineNumbers(id1, endLine1);
           	   
      	    }
   		    
   	     } 
         catch (Exception e1) 
         {

   		    errorMessage = e1.getMessage();
   		    System.out.println("Error 12102: Occurred while loading the existing Class Method . Error Message: " + errorMessage);

   	     }
             
      } 
      
      // Disconnect from the Forensic Ready Logger Database
      classMetCon.disconnect();
        
   }

   public static ArrayList<Integer> getMethodStartLineNumbers(String fileName, String inputFullMethodName) throws IOException //11
   {
	   
      String lineStr = null;
	  int    lineNum = 0;
	  Path path = Paths.get(fileName);
      final Charset ENCODING = StandardCharsets.UTF_8;
      ArrayList<Integer> lineNumbers = new ArrayList<Integer>();
      
	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {
		  
	     lineStr = null;
	     lineNum = 1;
	     
	     while ((lineStr = reader.readLine()) != null) 
	     {
	    	 
	        if (lineStr.contains(inputFullMethodName))    
	           lineNumbers.add(lineNum);
	    	 
	    	lineNum ++;
	    		 
	     }      
      }
       
   return lineNumbers;
   
   }
  
   public static boolean verifyProjectException(String projectException, String shortMethodName) //12
   {
      boolean found = false;
      
	  if(projectException.equals(shortMethodName))
	     found = true;

      return found;	   
   }  
   
   public static String getFinalMethodName(String packageName, String className, String methodName, FRLConfiguration frlCon) //13
   {
	   
      String finalMethodName = methodName;
      String element = "", tok1 = "", tok2 = "";
      boolean libFound1 = false, libFound2;
      Pattern libPattern = Pattern.compile(frlCon.findPLPattern2);
      
      libFound1 = libPattern.matcher(methodName).find();
      
      if (libFound1 == true) 
      {
    	  // For example: Initial Method Name: public java.util.ArrayList<Employee> loadFullNameEmployees(
    	  StringTokenizer stokenizer1 = new StringTokenizer(methodName, " ");
    	  
       	  finalMethodName = "";
       	           
          while(stokenizer1.hasMoreElements()) 
          {
             // returns the next token
             tok1    = (String) stokenizer1.nextElement();
             element = "";
             
             libFound2 = tok1.contains(frlCon.objectOrientedDelimiter1);
             
             if(libFound2 == true)
             {	 
            	 
                StringTokenizer stokenizer2 = new StringTokenizer(tok1, frlCon.objectOrientedDelimiter1);

                while(stokenizer2.hasMoreElements()) 
                {
                   // returns the next token
                   tok2 = (String) stokenizer2.nextElement();
                } 
                
                element = tok2;
                
             }
             else
             {	 
                element = tok1;
             }   
               
             finalMethodName = finalMethodName + " " + element;
  
          } 
    	  
      }
      
      return finalMethodName;
	   
   }
   
   public static String getFinalConstructorShortName(String packageName, String shortConsName, String objectOrientedDelimiter1) //14
   {
	   
	  String replaceStr = null;
	  boolean packageFound = false;
	  String initialConsName = null;
	  String finalShortConsName = null;
	   
      // Verify if the package. is there in the constructor Name
	  packageFound = shortConsName.contains(packageName);
			 
	  // If the packageName was found remove it from the constructor name
	  if(packageFound == true)
	  {	 
				 
	     initialConsName = shortConsName;
				     
		 // Replace every occurrence of "package." 
		 replaceStr      = packageName + objectOrientedDelimiter1;
		 initialConsName = initialConsName.replace(replaceStr, "");
		 finalShortConsName = initialConsName;
		        
      }	
	  
      return finalShortConsName;	   
   }
   
     
   public static ArrayList<String> getClassConstructorsFullMethodsNames(String packageName, 
		                                                                String className, 
		                                                                ArrayList<String> projExceptionList,
		                                                                FRLConfiguration frlCon
		                                                                ) //15
   {
	  // Get the Full Name of the Constructors and the Methods of the Class
	   
      String errorMessage; 
	  String inputClassName = packageName + frlCon.objectOrientedDelimiter1 + className;	

      Method[] classMethods;
      String shortMethodName = null, fullMethodName1 = null, fullMethodName2 =null;
      String initialMethodName = null, finalMethodName = null;
      String returnTypeMethod = null;
      
	  String shortConsName = null, fullConsName = null, initialConsName = null, finalConsName1 = null;
	  String finalConsName2 = null, replaceStr = null, str = null, substr = null, before = null;
	  String[] parts;
      
	  Constructor<?>[] classCons = null;
	  Class<?> newClass = null;
	  ArrayList<String> classConsMethods = new ArrayList<String>();
	  boolean exceptionFound;
	  
	  // Initialize the ArrayList
	  classConsMethods.clear();
	  
	  // Create a new class 
	  try 
	  {
	     newClass = Class.forName(inputClassName);
	  } 
	  catch (ClassNotFoundException e) 
	  {
		  
	     errorMessage = e.getMessage(); 
		 System.out.println("Error 12151: Occurred while creating a New Class: "+newClass +" Error Message: " + errorMessage);
	  } 
	  
	  // Get the constructor(s) of the class
	  classCons = newClass.getDeclaredConstructors();

	  // Constructor Loop
	  for(int i = 0; i < classCons.length; i++)
	  {
	     fullConsName  = classCons[i].toGenericString();   
		 shortConsName = classCons[i].getName();
		
	     str     = fullConsName;
	     substr  = inputClassName + frlCon.objectOrientedDelimiter1;
	     parts   = str.split(substr);
	     before  = parts[0];
	     
	     // Assign an initial format for the method
	     initialConsName = before + shortConsName;
	     
		 // Replace every occurrence of "package." 
	     replaceStr     = packageName + frlCon.objectOrientedDelimiter1;
	     finalConsName1 = initialConsName.replace(replaceStr, "");
	     
	     finalConsName2 = getFinalConstructorShortName(packageName, shortConsName, frlCon.objectOrientedDelimiter1);
	     
	     returnTypeMethod = frlCon.initializeObjectName;
	     fullMethodName2 = finalConsName1 + frlCon.objectOrientedDelimiter2 + finalConsName2 + frlCon.objectOrientedDelimiter2 + returnTypeMethod;
	     classConsMethods.add(fullMethodName2);
	    
	   }
	    
	   // Get the declared methods for this class
	   classMethods = newClass.getDeclaredMethods();
	  
	   exceptionFound = false;
	   
	   // Build the method name that can be found in the Java file
	   for(int i = 0; i < classMethods.length; i++)
	   {
		  
	      shortMethodName  = classMethods[i].getName();
		  fullMethodName1  = classMethods[i].toGenericString();
		  
		  // Get the Return Type Method
		  returnTypeMethod  = classMethods[i].getGenericReturnType().toString();
		  
		  // Verify if the shortMethodName corresponds to an Exception
		  exceptionFound = verifyProjectException(frlCon.methodNameException, shortMethodName);
		  
		  // If the shortMethodName is not in the Project Exception List then
		  // Add to the list of the Class Methods
		  if(exceptionFound == false)
		  {	  
	         str     = fullMethodName1;
	         substr  = inputClassName + frlCon.objectOrientedDelimiter1;
	         parts   = str.split(substr);
	         before  = parts[0];
	     
	         // Assign an initial format for the method
	         initialMethodName = before + shortMethodName;
	     
             // Replace every occurrence of "package." 
	         replaceStr        = packageName + frlCon.objectOrientedDelimiter1;
	         initialMethodName = initialMethodName.replace(replaceStr, "");
	         finalMethodName = getFinalMethodName(packageName, className, initialMethodName, frlCon);
	      
	         // Return final MethodName, shortMethodName and returnType of the Method
	         fullMethodName2 = finalMethodName + frlCon.objectOrientedDelimiter2 + shortMethodName + frlCon.objectOrientedDelimiter2 + returnTypeMethod;
	         classConsMethods.add(fullMethodName2);
	         
		  }
	     
	   }
	   	  
      return classConsMethods;
		   
   }
 
   public static String breakDownReturnType(String outputReturnType1, 
		                                    boolean libFound5,
		                                    FRLConfiguration frlCon)
   {
      String outputReturnType2 = "", part1 = "", part2 = "";
      String[] parts;
      
      // Divide the Output Return Type in two fields
      if(outputReturnType1.contains(frlCon.startClassList))
      {	  
         parts = outputReturnType1.split(frlCon.startClassList);
         part1 = parts[0];
         part2 = frlCon.startClassList + parts[1];
      }
      else
      {	  
         if(libFound5 == true)
         {
            part1 = frlCon.bluePrintObject2;
        	part2 = outputReturnType1;	
         }
         else
         {	 
            part1 = outputReturnType1;	
            part2 = " ";
         }
      }   
    	  
      outputReturnType2 = part1 + frlCon.objectOrientedDelimiter2 + part2 ;
    		  
      return outputReturnType2;	   
   }
   
   public static String validateArrayListType(String inputReturnType,
                                              FRLConfiguration frlCon)
   {
      String outputReturnType="", element="";
      boolean libFound1=false, libFound2=false;
      StringTokenizer stokenizer1;
      
      stokenizer1 = new StringTokenizer(inputReturnType, frlCon.objectOrientedDelimiter1);
	  
	  outputReturnType = "";
	 
	  while(stokenizer1.hasMoreElements()) 
      {
         // returns the next token
         element    = (String) stokenizer1.nextElement();
         libFound1 = element.contains(frlCon.startClassList);
         libFound2 = element.contains(frlCon.endClassList);
         
         if(libFound1 == true)
            outputReturnType = outputReturnType + element;
         else 
            if (libFound2 == true)
               outputReturnType = outputReturnType + 
                                  frlCon.objectOrientedDelimiter1 + 
                                  element;
      } 

      return outputReturnType;
      
   }
   
   public static String validateInterfacePLType(String inputReturnType,
                                                FRLConfiguration frlCon)
   {
	   
      String outputReturnType="", element="", tok1="", tok2="";
      boolean libFound1=false;
      StringTokenizer stokenizer1, stokenizer2;

	  stokenizer1 = new StringTokenizer(inputReturnType, frlCon.whiteSpaceWordsDelimiter1);
	  
   	  outputReturnType = "";
   	           
      while(stokenizer1.hasMoreElements()) 
      {
         // returns the next token
         tok1    = (String) stokenizer1.nextElement();
         element = "";
         
         libFound1 = tok1.contains(frlCon.objectOrientedDelimiter1);
         
         
         if(libFound1 == true)
         {	 
        	 
            stokenizer2 = new StringTokenizer(tok1, frlCon.objectOrientedDelimiter1);
            
            while(stokenizer2.hasMoreElements()) 
            {
               // returns the next token
               tok2 = (String) stokenizer2.nextElement();
            }
            
            element = tok2;
            
         }
         else
            element = tok1;
           
         outputReturnType = element;
         
      } 
    
      return outputReturnType;	   
   }
   
   public static String validateClassType(String inputReturnType,
		                                  FRLConfiguration frlCon)
   {
      String outputReturnType="", element="", tok1="";
	  StringTokenizer stokenizer1;

	  stokenizer1 = new StringTokenizer(inputReturnType, frlCon.whiteSpaceWordsDelimiter1);	  
	                  
	  while(stokenizer1.hasMoreElements()) 
	  {
	     // returns the next token
	     tok1    = (String) stokenizer1.nextElement();
	     element = tok1;
	     outputReturnType = element;
	      
	  } 
	    	  
      return outputReturnType;
      
   }
   
   public static String getFinalReturnType(String inputReturnType, FRLConfiguration frlCon) //13
   {

      String outputReturnType="", lastChar;
      Pattern 
              libPattern1 = Pattern.compile(frlCon.findInterfacePattern2),
              libPattern2 = Pattern.compile(frlCon.findPLPattern2), 
              libPattern3 = Pattern.compile(frlCon.findClassPattern2),
              libPattern4 = Pattern.compile(frlCon.findArrayListPattern2),
    		  libPattern5 = Pattern.compile(frlCon.findListPattern2);
      
      boolean libFound1=false, libFound2=false, libFound3=false, 
    		  libFound4=false, libFound5=false;
      
      libFound4 = libPattern4.matcher(inputReturnType).find();
      
      if(libFound4 == false)
      {	  
         libFound5 = libPattern5.matcher(inputReturnType).find();
      
         if(libFound5 == false)
         {	  
            libFound1 = libPattern1.matcher(inputReturnType).find();
            libFound2 = libPattern2.matcher(inputReturnType).find();
            libFound3 = libPattern3.matcher(inputReturnType).find();
         }
      }

      // Get the last Character from the Input Return Type
      lastChar = inputReturnType.substring(inputReturnType.length()- 1);
      
      // Remove the semicolon delimiter from the end of the inputReturnType String
      if(lastChar.equals(frlCon.endStatementDelimiter))
         inputReturnType  = inputReturnType.replace(frlCon.endStatementDelimiter, "");

      if (libFound1 == true || libFound2 == true) 
         outputReturnType = validateInterfacePLType(inputReturnType, frlCon);   
      else
    	  if(libFound3 == true)
    	     outputReturnType = validateClassType(inputReturnType, frlCon);
    	  else 
    	     if (libFound4 == true || libFound5 == true) 
	    	    outputReturnType = validateArrayListType(inputReturnType, frlCon);
             else
                outputReturnType = inputReturnType;


      // Divide the output Return Type into two fields
      outputReturnType = breakDownReturnType(outputReturnType, libFound3, frlCon);
      
      
      return outputReturnType;
	   
   }
 
   public static void getClassMethodsStartLines(FRLConfiguration frlCon, ArrayList<String> inputClassFileNames) //16
   {
	   
      String inputClassName = null, className, packageName, fullMethodName, line, shortMethodName;
      String returnType, fileNameSec[], fileName = null, errorMessage = null;
      String finalReturnType, finalReturnType1, finalReturnType2;
      int    lineNumber;
      String[] method, parts;
      
      ArrayList<String> initialFullMethodsNames = new ArrayList<String>();
      ArrayList<String> fullMethodsNames = new ArrayList<String>();
      ArrayList<Integer> methodStartLineNumbers = new ArrayList<Integer>();
      ArrayList<String> projExcepList = new ArrayList<String>(); 
      ClassMethodController classMetCon = new ClassMethodController();
      ClassMethod classMethod;
      
 	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 System.out.println("Error 12161: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }	
	  
	  
      // Delete the existing Class Method  in the Database
	  try 
	  {
	     classMetCon.deleteClassMethod();
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error 12162: Occurred while deleting the existing Class Methods. Error Message: " + errorMessage);
	  }
	  
      // For every Input Class Loop 
      for(int i = 0; i < inputClassFileNames.size(); i++) 
	  {
	    	  
         inputClassName = inputClassFileNames.get(i);
         
         // Obtain the filename
    	 fileName = inputClassName;
    	  
    	 // Split the filename into sections
    	 fileNameSec = fileName.split(frlCon.elementsFileName);
    	  
    	 // Get the class name of the file
    	 className = fileNameSec[fileNameSec.length - 1];
    	 className = className.replaceFirst(frlCon.replacePattern, "");
    	  
    	 // Get the package name of the file
    	 packageName = fileNameSec[fileNameSec.length - 2];
    	 
    	 
         // Get the Full Method Names of the Input Class File Names
		 initialFullMethodsNames = getClassConstructorsFullMethodsNames(packageName, className, projExcepList, frlCon);
		 
    	 // Remove duplicate values from the Full Method Names of the Input Class File Names
    	 fullMethodsNames = (ArrayList<String>) initialFullMethodsNames.stream().distinct().collect(Collectors.toList());
    	 
    	 // For every Method Loop 
         for(int j = 0; j < fullMethodsNames.size(); j++) 
   	     {
        	line            = fullMethodsNames.get(j);
        	method          = line.split(frlCon.objectOrientedDelimiter2);
        	fullMethodName  = method[0];
        	shortMethodName = method[1];
        	returnType      = method[2];
        	
        	// Get the final returnType
        	finalReturnType = getFinalReturnType(returnType, frlCon);   
        	
        	// Get the finalReturnType divided in two fields by ~
        	parts = finalReturnType.split(frlCon.objectOrientedDelimiter2);
        	finalReturnType1 = parts[0];
        	finalReturnType2 = parts[1];
        	
        	// Validate the value of finalReturnType2
        	if(finalReturnType2.equals(" "))
        	   finalReturnType2 = "";
        	        	
    	    // Get the initial Line Numbers for the Method
    	    try 
    	    {
    	    	
	           methodStartLineNumbers = getMethodStartLineNumbers(fileName, fullMethodName);
	           
	           // For every Start Line Number Loop 
	           for(int k = 0; k < methodStartLineNumbers.size(); k++) 
	     	   {
	              lineNumber = methodStartLineNumbers.get(k);
	              
	              // Insert a record into the class_method table
	        	  try 
	        	  {

	        		 classMethod = new ClassMethod(packageName, className, 
	        				                       fullMethodName, shortMethodName, 
	        				                       finalReturnType1, finalReturnType2, 
	        				                       fileName, lineNumber); 
	        		 
	        	     classMetCon.saveClassMethod(classMethod);   
	        	       		 
	        	  } 
	        	  catch (Exception e) 
	        	  {
	        	     errorMessage = e.getMessage(); 
	        		 System.out.println("Error 12165: Occurred while saving a new Class Method. Error Message: " + errorMessage);
	        	  }
	        	  
	     	   }
	           
		    } 
    	    catch (IOException e) 
    	    {
			  errorMessage = e.getMessage();
			  System.out.println("Error 12166: Occurred while getting the existing Start Method Line Numbers. Error Message: " + errorMessage);
		    }
    	    
   	     }  
             
      } 
      
      // Disconnect to the Forensic Ready Logger Database
      classMetCon.disconnect();
        
   }
   
   public void getFilesMethodsBody(ArrayList<String> javaClasses, FRLConfiguration frlCon) //17
   {
	   
	  // Delete the Output Project Directory
	  deleteDirectory(frlCon.projectOutputDir, frlCon.projectName);
		   
      // Get the Start Lines of every Method Name 
      getClassMethodsStartLines(frlCon, javaClasses);	
   	  
      // Get the End Lines of every Method Name  
   	  getClassMethodsEndLines(javaClasses, frlCon.propertiesFilePath, 
   			                  frlCon.elementsFileName, frlCon.replacePattern); 
   	  
   	  // Get the value for End Lines that have a Zero value
   	  getClassMethodsZeroEndLines(javaClasses, frlCon.propertiesFilePath, frlCon.endMethod);
   	  
   	  // Get the Method Body separated in Files
   	  getClassMethodsBody(javaClasses, frlCon);
   }  
  

}
