package frl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.Signature;

import gui.UserFormEvent;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;

import java.lang.String;

public aspect FrlDatabaseMethods
{
   String projectName="HRMApplication";
   String projectOutputDir="/Users/fannyriveraortiz/eclipse-workspace/Directories/Output/";
   String umlSeqDiagTextFileName1="IncidentSequenceDiagram.txt";
   String guiLibs="java.awt;javax.swing;org.eclipse.swt;javafx;org.apache.pivot.wtk;com.trolltech.qt.gui;";
   String bluePrintObject1="class";
   String objectOrientedDelimiter1=".";
   String startParameters="(";
   String endParameters=")";
   String newLine1="%n";
   String whiteSpaceWordsDelimiter1=" ";
   
   String guiLibDelimiter=";";
   String invalidSpecialCharacter="$";
   String validSpecialCharacter="_";
   String member1="actor";
   String member2="participant";
   String startSendMessage="->";
   String endSendMessage=":";
   String userName="User";

   String startNote="note left of";
   String endNote="end note";
   String colorDelimiter="#";
   String colorNote="lightskyblue";
   String position="order";
   String space="||20||";
   String startDivision="== Connects to the";
   String endDivision="==";
   
   String umlSeqDiagTextFile=projectOutputDir + umlSeqDiagTextFileName1;
   String callerClass="", calleeClass="", callerClassLine, calleeClassLine;
   String packageMethod="", classMethod="";
   String nameMethod="", fullMethod="", callerSuperClass="", calleeSuperClass="";
   String callerType="", calleeType="", errorMessage="", note="";
   String line="", content="", currentUser="";
   int actorOrder=1, partOrder=1, methodsCounter=0, userCounter=1;
   boolean callerGuiFlag= false, calleeGuiFlag=false;
   Class<?> callerClassObj1, callerClassObj2, calleeClassObj1, calleeClassObj2; 
   Signature method;
   Object[] methodArgs;
   
   /*Evaluation Start */
   long time1, time2, duration;
   /* Evaluation End */

   String connectMethodName        = "controller.UserController.validateUser";
   String fullMethodName           = "";
   Object connectMethodReturnValue = true;
   
   String endUMLSeqDiagram="@enduml";
   String umlSeqDiagPngFileName1="IncidentSequenceDiagram.png";
   String umlSeqDiagPngFile=projectOutputDir  + umlSeqDiagPngFileName1;
   int userAttempt=1;
   boolean userConMet=false;
   
   int numberLines=0;
   String umlSeqDiagTextFileName2="IncidentSequenceDiagramTEMPORAL.txt";
   String umlSeqDiagTextFile2=projectOutputDir + umlSeqDiagTextFileName2;
   File inputFile  = new File(umlSeqDiagTextFile);
   File outputFile = new File(umlSeqDiagTextFile2);
   
   pointcut databaseMethods(): 
                            call(void model.ModuleDatabase.saveModules(..)) || 
                            call(void controller.ModuleController.saveModules(..)) || 
                            call(void test.TestModuleDb.main(..)) || 
                            call(String model.ModuleDatabase.getModules(..)) || 
                            call(String controller.ModuleController.getModules(..)) || 
                            call(String model.ModuleDatabase.getSubModules(..)) || 
                            call(String controller.ModuleController.getSubModules(..)) || 
                            call(void model.EmployeeDatabase.loadEmployee(..)) || 
                            call(void test.TestEmployeeDb.main(..)) || 
                            call(void controller.EmployeeController.loadEmployee(..)) || 
                            call(int model.EmployeeDatabase.maxNumEmployee(..)) || 
                            call(int controller.EmployeeController.maxNumEmployee(..)) || 
                            call(void model.EmployeeDatabase.load(..)) || 
                            call(void controller.EmployeeController.load(..)) || 
                            call(boolean model.EmployeeDatabase.validateFullName(..)) || 
                            call(boolean controller.EmployeeController.validateFullName(..)) || 
                            call(boolean model.EmployeeDatabase.validateDeleteEmployee2(..)) || 
                            call(boolean controller.EmployeeController.validateDeleteEmployee2(..)) || 
                            call(Listmodel.Employee model.EmployeeDatabase.loadSupervisors(..)) || 
                            call(Listmodel.Employee controller.EmployeeController.loadSupervisors(..)) || 
                            call(boolean model.EmployeeDatabase.validateDeleteEmployee1(..)) || 
                            call(boolean controller.EmployeeController.validateDeleteEmployee1(..)) || 
                            call(void model.EmployeeDatabase.save(..)) || 
                            call(void controller.EmployeeController.save(..)) || 
                            call(void model.EmployeeDatabase.delete(..)) || 
                            call(void controller.EmployeeController.delete(..)) || 
                            call(boolean model.UserDatabase.validateDeleteUser(..)) || 
                            call(void test.TestUserDb.main(..)) || 
                            call(boolean controller.UserController.validateDeleteUser(..)) || 
                            call(boolean model.UserDatabase.validateEmail(..)) || 
                            call(boolean controller.UserController.validateEmail(..)) || 
                            call(void model.UserDatabase.load(..)) || 
                            call(void controller.UserController.load(..)) || 
                            call(boolean model.UserDatabase.validateEmployee(..)) || 
                            call(boolean controller.UserController.validateEmployee(..)) || 
                            call(boolean model.UserDatabase.validateUserName(..)) || 
                            call(boolean controller.UserController.validateUserName(..)) || 
                            call(model.UserLevel model.UserDatabase.getUserLevel(..)) || 
                            call(model.UserLevel controller.UserController.getUserLevel(..)) || 
                            call(void test.TestLoginDb.main(..)) || 
                            call(void model.UserDatabase.save(..)) || 
                            call(void controller.UserController.save(..)) || 
                            call(boolean model.UserDatabase.validateUser(..)) || 
                            call(boolean controller.UserController.validateUser(..)) || 
                            call(void model.UserDatabase.loadUser(..)) || 
                            call(void controller.UserController.loadUser(..)) || 
                            call(Listmodel.Employee model.UserDatabase.loadFullNameEmployees(..)) || 
                            call(Listmodel.Employee controller.UserController.loadFullNameEmployees(..)) || 
                            call(void model.UserDatabase.delete(..)) || 
                            call(void controller.UserController.delete(..)) || 
                            call(void model.TravelRequestDatabase.loadTravelRequest(..)) || 
                            call(void test.TestTravelRequestDb.main(..)) || 
                            call(void controller.TravelRequestController.loadTravelRequest(..)) || 
                            call(void model.TravelRequestDatabase.load(..)) || 
                            call(void controller.TravelRequestController.load(..)) || 
                            call(int model.TravelRequestDatabase.maxNumTravelRequest(..)) || 
                            call(int controller.TravelRequestController.maxNumTravelRequest(..)) || 
                            call(void model.TravelRequestDatabase.save(..)) || 
                            call(void controller.TravelRequestController.save(..)) || 
                            call(Listmodel.Employee model.TravelRequestDatabase.loadFullNameEmployees(..)) || 
                            call(Listmodel.Employee controller.TravelRequestController.loadFullNameEmployees(..)) || 
                            call(void model.TravelRequestDatabase.delete(..)) || 
                            call(void controller.TravelRequestController.delete(..));

   before(): databaseMethods()
   {

      // Get the Caller Class Details
      callerClassObj1 = thisEnclosingJoinPointStaticPart.getSignature().getDeclaringType();
      getCallerClassDetails(callerClassObj1);

      // Get the Callee Class Details
      calleeClassObj1 = thisJoinPoint.getTarget().getClass();
      getCalleeClassDetails(calleeClassObj1);

      // Get the details of the Method being executed
      method        = thisJoinPoint.getSignature();
      packageMethod = method.getDeclaringType().getPackageName();
      classMethod   = method.getDeclaringType().getSimpleName();
      nameMethod    = method.getName();
      methodArgs    = thisJoinPoint.getArgs();
      fullMethod	= nameMethod + startParameters;
      
      // Build the full Method Name
      fullMethodName = packageMethod + "." + classMethod + "." + nameMethod;
 
      // Add in the UML Sequence Diagram the information of a new User Connected
      if(fullMethodName.equals(connectMethodName) == true)
      {
         userConnection();
      }
      
      // Validate if the CallerClass is a Graphical User Interface Class
      // Build the Caller Class Line
      if(callerGuiFlag == true)
      {	  
    	 // Build the method line 
         fullMethod = fullMethod + endParameters;
         
         // Build the note line
         note = startNote   + whiteSpaceWordsDelimiter1 + currentUser + whiteSpaceWordsDelimiter1 + colorDelimiter + colorNote + 
                whiteSpaceWordsDelimiter1 + newLine1 + callerClass + 
                newLine1 + endNote + newLine1;
         
         // Assign the value to the caller Class
         callerClass = currentUser;
         
         // Build the caller Class Line
         callerClassLine = member1 + whiteSpaceWordsDelimiter1  + callerClass + 
        		           whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + actorOrder;
      }   
      else
      {	  
    	 // Build the method line  
         fullMethod = fullMethod + Arrays.toString(methodArgs) + endParameters;
         
         // The note line will be empty
         note = "";
         
         // Increase the participants Order Counter
         partOrder++;
         
         // Build the caller Class Line
         callerClassLine = member2 + whiteSpaceWordsDelimiter1 + callerClass + 
        		           whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;
      }   
      
      partOrder++;
      
      // Build the callee Class Line
      calleeClassLine = member2 + whiteSpaceWordsDelimiter1 + calleeClass + 
    		            whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;
      
      /* Evaluation Start */
	  time1 = System.currentTimeMillis(); 
	  /* Evaluation End */ 
    		
      // Generate the Plant UML line to generate the UML Sequence Diagram Text File
 	  updateSeqDiagTextFile(callerClass, calleeClass, fullMethod, note, callerClassLine, calleeClassLine);
	        
	  /* Evaluation Start */
	  time2 = System.currentTimeMillis();
	  duration = time2 - time1;
	  System.out.println("Message FRL: The UML Sequence Diagram Exchange for the Method: " + fullMethod +  " was Generated in : " + 
	  duration + " milliseconds.");

	  /* Evaluation End */
      
   } 

   pointcut connect():
      call(boolean controller.UserController.validateUser(..));
   
   after() returning(Object methodReturnTypeValue): connect() && args(..)
   {
	  System.out.println("After Connecting PointCut and returning a value");
	   
      if (methodReturnTypeValue.toString().equals(methodReturnTypeValue) == true)
      {	  
         userCounter ++; 
      }   
      else 
      {
         /* Evaluation Start */
     	 time1 = System.currentTimeMillis(); 
     	 /* Evaluation End */ 
     	 
    	 userCounter = 1;
        		 
    	 // Delete UML Sequence Diagram Png File
         deleteSeqDiagramFile(umlSeqDiagPngFile); 
         
         // Create and update the UML Sequence Diagram Files 
         updateFiles();
          
    	 /* Evaluation Start */
    	 time2 = System.currentTimeMillis();
    	 duration = time2 - time1;
    	 System.out.println("Message FRL: The UML Sequence Diagram Files were FINALLY Created in : " + duration + " milliseconds");      
    	 /* Evaluation End */
    	  
         System.out.println("Message FRL: Good Bye to the Forensic-Ready Logger.");
          
      }
      
   }
   
   
   after() throwing(): connect() && args(..)
   {
     // System.out.println("After Connecting PointCut and throwing an Error");
	  
      /* Evaluation Start */
	  time1 = System.currentTimeMillis(); 
	  /* Evaluation End */ 
	   
      // Create and update the UML Sequence Diagram Files 
      updateFiles();
      
	  /* Evaluation Start */
	  time2 = System.currentTimeMillis();
	  duration = time2 - time1;
	  System.out.println("Message FRL: The UML Sequence Diagram Files were FINALLY Created in : " + duration + " milliseconds");      
	  /* Evaluation End */
	  
      //System.out.println("Message FRL: Good Bye to the Forensic-Ready Logger.");
	   
   }

   
   public void updateFiles()
   {
	   
	  // Update the UML Sequence Diagram Text File with the @endUml Instruction  
      content = endUMLSeqDiagram + newLine1;
      content = String.format(content);
      
      // Write the contents into the UML Sequence Diagram Text File
      writeTextFile(umlSeqDiagTextFile, content);
      
	  // Conting the number of lines of the UML Sequence Diagram Text File
	  countLinesTextFile();
	  
	  // Deleting the repeated @enduml instructions
	  deleteLineTextFile();
      
      // Create the UML Sequence Diagram Png File
   	  createSeqDiagramPngFile(umlSeqDiagTextFile);
	      
  	  // Print on the screen Informative Messages
	  System.out.println("Message FRL: UML Sequence Diagram Text File created: "+umlSeqDiagTextFile);
	  System.out.println("Message FRL: UML Sequence Diagram PNG File created : "+umlSeqDiagPngFile);
      //System.out.println("Message FRL: Good Bye to the Forensic-Ready Logger.");
	  
   } 
   
   public void countLinesTextFile()
   {
		try 
		{
			LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(umlSeqDiagTextFile));
			lineNumberReader.skip(Long.MAX_VALUE);
			numberLines = lineNumberReader.getLineNumber();
			lineNumberReader.close();
		} 
		catch (Exception e1) 
		{
		   errorMessage = e1.getMessage();
		   System.out.println("Error FRL: Occurred while counting the lines of the UML Sequence Diagram Text File" + "Error Message: " + errorMessage);
		}		

		//System.out.println("Lines: "+numberLines);
   }
   
   public  void deleteLineTextFile()
   {
      String line1="";
      int c=0;
      
	  try 
	  {
	     try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		    		
		    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) 
		 {
		
		    while ((line1 = reader.readLine()) != null) 
		    {
		       c++;
		       
		       if(c != numberLines)
		       {	
		          if (!line1.equals(endUMLSeqDiagram)) 
		          {
		             writer.write(line1);
		             writer.newLine();
		          }
		       }
		       else
		       {		
		          writer.write(line1);
	              writer.newLine();
		       }   
		    }
		 }
		 catch (Exception e1) 
		 {
		    errorMessage = e1.getMessage();
			System.out.println("Error FRL: Occurred while writing a new UML Sequence Diagram Text File without double @enduml instructions." + "Error Message: " + errorMessage);
		 }

		 if (inputFile.delete()) 
		 {
		    // Rename the output file to the input file
		    if (!outputFile.renameTo(inputFile)) 
		    {
		       errorMessage = "Error FRL: Ocurred while renaming the File: " + umlSeqDiagTextFile2 + " to " + umlSeqDiagTextFile;	
		       System.out.println(errorMessage);
		       throw new Exception(errorMessage);
		    }
		 } 
		 else
		 {
	        errorMessage = "Error FRL: Ocurred while deleting the File: " + umlSeqDiagTextFile;
		    System.out.println(errorMessage);
		    throw new Exception(errorMessage);
		 }
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
	     System.out.println("Error FRL: Occurred while deleting or renaming the new UML Sequence Diagram Text File without double @enduml instructions." + "Error Message: " + errorMessage);
      }
   }  
   
   public void createSeqDiagramPngFile(String textFile)
   {
      File file1 = new File(textFile); 
	  SourceFileReader reader = null;
	  List<GeneratedImage> list = null;
	  
	  // Create the Reader of the UML Sequence Diagram Text File
	  try 
	  {
	     reader = new SourceFileReader(file1);
	  } 
	  catch (IOException e) 
	  {
	     errorMessage = e.getMessage();
	     System.out.println("Error FRL: Occurred while reading the UML Sequence Diagram Text File: "+textFile + "Error Message: " + errorMessage);
	  }
	   
	  // Generate the list of images using the Sequence Diagram Text File
	  try 
	  {
	     list = reader.getGeneratedImages();
	  } 
	  catch (IOException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error FRL: Occurred while generating the UML Sequence Diagram Png File" + "Error Message: " + errorMessage);
	  }
	   
	  // Generated the UML Sequence Diagram Png File
	  file1 = list.get(0).getPngFile();
 	   
   }
   
   public void deleteSeqDiagramFile(String inputFile)
   {
      File file = new File(inputFile);
	   
      // Delete an existing UML Sequence Diagram File
	  if(file.exists() && !file.isDirectory()) 
	     file.delete();
	  
   }

   public void userConnection()
   {
		  
	  currentUser = userName + validSpecialCharacter + userCounter;
	  
   	  methodsCounter = 0;
   	  
      line    = "";
      content = "";
	  

      // Add a division in the UML Sequence Diagram Text File  
      line = startDivision + whiteSpaceWordsDelimiter1 + 
      projectName   + whiteSpaceWordsDelimiter1 + 
      endDivision   + newLine1;

      content = content + line;

      content = String.format(content);

      // Write this division into the UML Sequence Diagram Text File
      writeTextFile(umlSeqDiagTextFile, content);
      
   }
   
   public void updateSeqDiagTextFile(String inputCallerClass, String inputCalleeClass, 
		                             String inputMethod, String inputNote, String inputCallerClassLine,
		                             String inputCalleeClassLine)
   {
	   
      line    = "";
      content = "";
      
      // Generating the content of the Text File which contains Plant UML instructions
      // to generate the UML Sequence Diagram
      
      line    = inputCallerClassLine + newLine1;
      content = content + line;
      
      line    = inputCalleeClassLine + newLine1;
      content = content + line;
      
      line    = inputNote;
      content = content + line;

      // Increase the methods Counter
      methodsCounter ++;
      
      line = inputCallerClass + whiteSpaceWordsDelimiter1 + startSendMessage + 
    		 inputCalleeClass + endSendMessage            + whiteSpaceWordsDelimiter1 + 
    		 methodsCounter   + objectOrientedDelimiter1  + inputMethod + newLine1;
      content = content + line;     
      
      line    = space + newLine1;
      content = content + line;
      
      content = String.format(content);

      // Write the Plant Uml Line into the UML Sequence Diagram Text File
      writeTextFile(umlSeqDiagTextFile, content);   

   } 
   
   public void writeTextFile(String textfile, String content)
   {
      FileWriter fw = null;
      BufferedWriter bw;
      File file;

      try
      {
         // Create a new textFile
         file = new File(umlSeqDiagTextFile);

         if(file.exists())
         {
            fw = new FileWriter(file,true);
            bw = new BufferedWriter(fw);

            // Write in the textFile
            fw.append(content);

            // Close the textFile
            bw.close();
         }
         
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
         System.out.println("Error FRL: Occurred while opening the UML Sequence Diagram Text File: "+umlSeqDiagTextFile + "Error Message: " + errorMessage);
      }
            
   }  
   
   public boolean validateSuperClass(String inputSuperClass)
   {
      String inputGuiLibStr, lib;
      String [] guiLibsArray; 
      int c=0;
      boolean guiFlag = false;	

      inputGuiLibStr = guiLibs;
   
      // Get the Array from the Input GUI Library String
      guiLibsArray = inputGuiLibStr.split(guiLibDelimiter); 
   
      while(c < guiLibsArray.length && guiFlag == false)
      {
         lib = guiLibsArray[c];
         
         // Validate if the inputSuperClass contains the current Library prefix
	     if(inputSuperClass.contains(lib))
	        guiFlag = true;	
	  
	     c++;
      } 
   
      return guiFlag;   
   }  

   public String getSuperClass(Class<?>  inputClass)
   {
	   
      // Obtain the Class and SuperClass of the current Method Body File
      String superClass = "";
		  		  		  
      // Get the superclass from the class
      Class<?> superclz = inputClass.getSuperclass();
      superClass = superclz.toString();
   
      return superClass;  
   }

   public Class<?> getUpdatedClass(String inputClass)
   {
      Class<?> classObj = null;
	
      try 
      {
         classObj = Class.forName(inputClass);
      } 
      catch (ClassNotFoundException e) 
      {
         errorMessage = e.getMessage();
	     System.out.println("Error FRL: Occurred while creating a New Class. Error Message: " + errorMessage);
      }
	
      return classObj;	
   }
   
   public String validateClass(String inputClass)
   {
      String outputClass = inputClass, element1, searchWhiteSpace="\\s+";	
      String[] elements;
   
      // Replace the bluePrintObject: "class" for ""
      if(outputClass.contains(bluePrintObject1))
         outputClass = outputClass.replace(bluePrintObject1,"");
	
      // Remove the WhiteSpaces from the beginning of the string
      outputClass = outputClass.replaceFirst(searchWhiteSpace, "");
   
      // Replace the specialCharacter1:"$" for "_"
      if(outputClass.contains(invalidSpecialCharacter))
      {	   
	     // Replace the dollarDelimiter:"$" for "_"
         outputClass = outputClass.replace(invalidSpecialCharacter, validSpecialCharacter);

	     // Divide the string in two parts using the "_" delimiter
         elements = outputClass.split(validSpecialCharacter); 
      
         // Get the first part
  	     element1 = elements[0];
         outputClass = element1;
      
      }   
   
      return outputClass;
   }
   
   public void getCalleeClassDetails(Class<?> calleeClassObj0)
   {
	   
	  // Get the calleeClass 
      calleeClass = calleeClassObj0.toString();

	  // Validate the calleeClass
	  calleeClass = validateClass(calleeClass);

	  //Get the Updated Object Class 
	  calleeClassObj2 = getUpdatedClass(calleeClass);

	  //Get the superClass of the caller Class
	  calleeSuperClass = getSuperClass(calleeClassObj2);
	
	  // Get the flag of the callerSuperClass GUI
	  calleeGuiFlag = validateSuperClass(calleeSuperClass);

	  // Validate if callerSuperClass corresponds to GUI
	  if(calleeGuiFlag == true)
	  {
	     //calleeClass = calleeClass;
	     calleeType = member1;
	  }
	  else
	     calleeType = member2;
   }
   
   public void getCallerClassDetails(Class<?> callerClassObj0)
   {
	   
      // Get the caller Class
      callerClass = callerClassObj0.toString();

	  // Validate the caller Class
	  callerClass = validateClass(callerClass);

	  // Get the Updated Object Class 
	  callerClassObj2 = getUpdatedClass(callerClass);

	  // Get the superClass of the caller Class
	  callerSuperClass = getSuperClass(callerClassObj2);

	  // Get the flag of the callerSuperClass GUI
	  callerGuiFlag = validateSuperClass(callerSuperClass);

	  // Validate if callerSuperClass corresponds to GUI
	  if(callerGuiFlag == true)
	  {	
	     //callerClass = callerClass;
	     callerType = member1;
	  }
	  else
	     callerType = member2;
   }
  
}
