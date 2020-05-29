package frl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import org.aspectj.lang.Signature;
import java.util.ArrayList;

public aspect FrlDatabaseMethods
{
   String projectName="HRMApplication";
   String projectOutputDir="/Users/fannyriveraortiz/Desktop/Output/";
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
   String colorNote="aqua";
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
   int actorOrder=1, partOrder=1, methodsCounter=0, userCounter=0;
   boolean callerGuiFlag= false, calleeGuiFlag=false;
   Class<?> callerClassObj1, callerClassObj2, calleeClassObj1, calleeClassObj2; 
   Signature method;
   Object[] methodArgs;

   pointcut databaseMethods(): 
                            call(void model.ModuleDatabase.saveModules(..)) || 
                            call(void controller.ModuleController.saveModules(..)) || 
                            call(void test.TestModuleDb.main(..)) || 
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
                            call(ArrayList<model.Employee> model.EmployeeDatabase.loadSupervisors(..)) || 
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
                            call(boolean model.UserDatabase.validateUser(..)) || 
                            call(model.UserLevel model.UserDatabase.getUserLevel(..)) || 
                            call(boolean controller.UserController.validateUser(..)) || 
                            call(void model.UserDatabase.save(..)) || 
                            call(void controller.UserController.save(..)) || 
                            call(boolean model.UserDatabase.validateUser(..)) || 
                            call(void model.UserDatabase.loadUser(..)) || 
                            call(void controller.UserController.loadUser(..)) || 
                            call(ArrayList<model.Employee> model.UserDatabase.loadFullNameEmployees(..)) || 
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
                            call(ArrayList<model.Employee> model.TravelRequestDatabase.loadFullNameEmployees(..)) || 
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
    		  
      // Generate the Plant UML line to generate the UML Sequence Diagram Text File
      updateSeqDiagTextFile(callerClass, calleeClass, fullMethod, note, callerClassLine, calleeClassLine);

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
   
   pointcut connect(): 
   	                      execution(gui.LoginFrame.new(..));

   before(): connect()
   {
   	   
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
	  
	  // Initialize the methods counter
	  methodsCounter = 0;
	  
	  // Increase the users counters
	  userCounter ++;
	  
	  currentUser = userName + validSpecialCharacter + userCounter;
	  
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
