package frl.process.configureAspectOrientedFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


import frl.controller.configureAspectOrientedFiles.TextFileController;
import frl.controller.tree.GraphicalUserInterfaceController;
import frl.controller.tree.TreeStructureController;
import frl.model.configuration.ClassMethod;
import frl.model.configuration.FRLConfiguration;
import frl.model.configuration.SourceDirectory;
import frl.model.configureAspectOrientedFiles.FieldType;
import frl.model.configureAspectOrientedFiles.PropertyType;
import frl.model.configureAspectOrientedFiles.TextFileDetails;
import frl.model.configureAspectOrientedFiles.TextFileProperties;
import frl.model.configureAspectOrientedFiles.TextFilePropertyName;
import frl.model.tree.DatabaseMethod2;
import frl.controller.aopTemplateFile.AOPTemplateFileController;
import frl.controller.configuration.ClassMethodController;

public class AspectOrientedFiles 
{
   public AspectOrientedFiles() //1
   {
		   
   }

   public String generateMethodName2(FRLConfiguration frlCon, String text, String method) throws Exception
   {
      String endApp1="", endApp2="", errorMessage1="";

      // Validate if the input parameter has a value
      if(method != null && !method.isEmpty())
      {
         // Build the End Application 1
         endApp1 = method + frlCon.endCallMethod2;
 
         // Build the End Application 2
         endApp2 = text.replace(frlCon.confPropertyName1, endApp1);
      }
      else
      {
         errorMessage1 = "Error XXXX: The End Project Method Configuration Parameter is Empty.";
         endApp2 = "";
         throw new Exception(errorMessage1);
      }

      return endApp2;

   }

   public String generateMethodName1(FRLConfiguration frlCon, String text, String fullMethodName, int projectId) throws Exception
   {
      String packageName="", className="", shortMethodName="", returnType="", methodName1="", methodName2="", 
    		 errorMessage1="", errorMessage2="";
      ClassMethod cm;
      ClassMethodController classMethodCon = new ClassMethodController();
      
      /*
      System.out.println("");
      System.out.println("Inside the Generate Method Name 1 Method ...");
      System.out.println("Full Method Name: " + fullMethodName);
      */
  
      // Validate if the input parameter has a value
      if(fullMethodName != null && !fullMethodName.isEmpty())
      {
	     try 
         {
            classMethodCon.connect(frlCon.databaseConfigFile);
         } 
         catch (Exception e1) 
         {
            errorMessage1 = e1.getMessage();
            throw new Exception(errorMessage1);
         }

         try 
         {

            // Get the shortMethodName and returnType from the Forensic-Ready Logger Database 
            try 
            {
			   cm = classMethodCon.loadMethodNameDetails_1(projectId, fullMethodName);
			      
			   // Get all the Details
			   packageName      = cm.getPackageName();
			   className        = cm.getClassName();
			   shortMethodName  = cm.getShortMethodName();   
	           returnType       = cm.getReturnType1();
	            
	           /*
	              System.out.println("*** INFORMATION FOR THE METHOD 1 ***");
	              System.out.println("Package Name        : " + packageName);
	              System.out.println("Class Name          : " + className);
	              System.out.println("Short Method Name   : " + shortMethodName);
	              System.out.println("Return Type         : " + returnType);
	              */
	              
			} 
            catch (Exception e2) 
            {
               errorMessage1 = e2.getMessage();
               throw new Exception(errorMessage1);
			}

     
            if(returnType != null && !returnType.isEmpty())
            {	
            	   
               // Validate if the returnType2 is equals to "Constructor"
               if(returnType.equals(frlCon.initializeObjectName))
            	   
                  // Build the methodName1
                  methodName1 = packageName  + frlCon.objectOrientedDelimiter1 +
        		                className    + frlCon.objectOrientedDelimiter1 + 
        		                frlCon.initializeObjectMethod + 
        		                frlCon.endCallMethod2;	 
               else
    	          // returnType2 is equal to a method data type such as boolean, void, string, etc 
            	   
                  // Build the methodName1
                  methodName1 = returnType  +  " " + packageName + frlCon.objectOrientedDelimiter1 + 
        		                className   + frlCon.objectOrientedDelimiter1 + shortMethodName + 
        		                frlCon.endCallMethod2;
     	         
               // Build the methodName2
               methodName2 = text.replace(frlCon.confPropertyName1, methodName1);
               
               /*
               System.out.println("Method Name 1: " + methodName1);
               System.out.println("Method Name 2: " + methodName2);
               */
            }
            else
            {
               errorMessage2 = "Error XXXX: Occurred while loading the Return Type 1: " + returnType + System.lineSeparator();
               errorMessage2 = errorMessage2 + "Package Name: " + packageName + System.lineSeparator();
               errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
               errorMessage2 = errorMessage2 + "Short Method Name: " + shortMethodName + System.lineSeparator();
               errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
               throw new Exception(errorMessage2);
            }

         } 
         catch (Exception e3) 
         {
            errorMessage1 = e3.getMessage();
            errorMessage2 = "Error XXXX: Occurred while building the Final Method Name: " + methodName2 + System.lineSeparator();
            errorMessage2 = errorMessage2 + "Package Name: " + packageName + System.lineSeparator();
            errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
            errorMessage2 = errorMessage2 + "Short Method Name: " + shortMethodName + System.lineSeparator();
            errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
            throw new Exception(errorMessage2);
         }
  
         try 
         {
	        classMethodCon.disconnect();
		 } 
         catch (Exception e4) 
         {
			errorMessage1 = e4.getMessage();
            throw new Exception(errorMessage1);
	     }
  
      }
      else
      {
         errorMessage1 = "Error XXXX: The Configuration Parameter for the Method Name is Empty.";
         methodName2 = "";
         throw new Exception(errorMessage1);
      }

      return methodName2;

   }

   public String generateMethodName(FRLConfiguration frlCon, String text, String inputMethod, int projectId) throws Exception
   {
      String methodName = "", errorMessage1="";
      
      //System.out.println("Inside the generateMethodName method ...");

      switch(inputMethod)
      {
         case "startApplication":
        	
        	//System.out.println("CASE 1: Start Application");
        	 
	        try 
	        {
			   methodName = generateMethodName1(frlCon, text, frlCon.startProjectMethod, projectId);
			   
			   //System.out.println("Method Name 1: " + methodName);
			   
		    } 
	        catch (Exception e1) 
	        {
               errorMessage1 = e1.getMessage();
               throw new Exception(errorMessage1);
		    } 	 
	     break;
 
         case "endApplication":
            try 
            {
               methodName = generateMethodName2(frlCon, text, frlCon.endProjectMethod);
        	}
 	        catch (Exception e1) 
 	        {
               errorMessage1 = e1.getMessage();
               throw new Exception(errorMessage1);
 		    } 
         break;
 
         case "connectProjectMethod":
            try
            {
               methodName = generateMethodName1(frlCon, text, frlCon.connectProjectMethod, projectId);
            }
	        catch (Exception e1) 
	        {
               errorMessage1 = e1.getMessage();
               throw new Exception(errorMessage1);
		    } 
         break;
 
      }
      
      return methodName;
      
   }
	 
   public String getGuiLibList(int projectId, FRLConfiguration frlCon, String text) throws Exception
   {
      String guiLibList1="", guiLibList2="", newText, errorMessage1="";
	  int c;
	  GraphicalUserInterfaceController guiCon = new GraphicalUserInterfaceController();
	  ArrayList<String> guiList = new ArrayList<String>();

	  // Connect to the Database
	  try 
	  {
	     guiCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     throw new Exception(errorMessage1);
	  }
	   
	  // Get the Graphical User Interface from the Forensic-Ready Logger Database
	  try 
	  {
	     guiList = guiCon.loadAllGui(projectId, frlCon.programmingLanguage);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception(errorMessage1);
	  }
	   
	  // Disconnect from the Forensic-Ready Logger Database
	  try 
	  {
	     guiCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
	     throw new Exception(errorMessage1);
	  }
	      
	  // Loop into the GuiList ArrayList
	  guiLibList1 = "";
	  for (c=0; c < guiList.size(); c++) 
	  { 		      
	     guiLibList1 = guiLibList1 + guiList.get(c) + frlCon.guiLibDelimiter;
	  }
	      
	  // Building the line to assing a variable the value of the
	  // List of the graphical user interfaces
	  newText     = frlCon.methodName3 + frlCon.assignValueDelimiter;
	  guiLibList2 = text.replace(frlCon.confPropertyName1, newText);
	      
	  newText     = frlCon.whiteSpaceWordsDelimiter2 + guiLibList1 + frlCon.whiteSpaceWordsDelimiter2;
	  guiLibList2 = guiLibList2.replace(frlCon.guiLibDelimiter, newText) + frlCon.endStatementDelimiter;
	      
      return guiLibList2;   	   
   }
	   
   public String getNonPrimitiveDataTypeList(FRLConfiguration frlCon, int projectId) throws Exception
   { 
      String nonPrimDataList="", library="", errorMessage1="";
	  int c;
	  TreeStructureController treeStrucCon;
	  ArrayList<String> nonPrimLib = new ArrayList<>();
	      
	  treeStrucCon = new TreeStructureController();
	      
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
	      
	  // Get all the libraries from the Non-Primitive Return Types
	  try 
	  {
	     nonPrimLib = treeStrucCon.getLibNonPrimRetType(frlCon.programmingLanguage, projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
	     throw new Exception(errorMessage1);
	  }
	      
	  // Loop through the Non Primitives Libraries ArrayList
	  for (c = 0; c < nonPrimLib.size(); c++) 
	  {
	     library = nonPrimLib.get(c);
	     
	     if(c == 0) 
	        nonPrimDataList = nonPrimDataList + library;	 
	     else	 
	        nonPrimDataList = nonPrimDataList + " " + library;	  
	  }
	      
	  // Disconnect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStrucCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 throw new Exception(errorMessage1);
	  }
	      
      return nonPrimDataList;
   }

   public String getPropertyValue(String propertiesFilePath, String name) throws Exception
   {
      String value = "", errorMessage1="", errorMessage2="";
	  InputStream input  = null;
	  Properties  prop   = new Properties();
	      
	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(propertiesFilePath);

		 // Load the properties file
		 prop.load(input);

	     // Load a properties file from class path, inside static method
	     prop.load(input);

	     // Get the Property Value
	     value = prop.getProperty(name);

      } 
	  catch (IOException e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while loading the Configuration File.\n";
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  } 
	  finally 
	  {
	     if (input != null) 
		 {
		    try 
			{
		       input.close();
			} 
			catch (IOException e2) 
			{
			   errorMessage1 = e2.getMessage();
			   errorMessage2 = "Error XXXX: Occurred while closing the Configuration File.\n";
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   throw new Exception(errorMessage2);
			}
	     }
	  }

      return value;   
   }
   
   public String modifyOutputDirAOPFiles(String outputDirCurrentMachine, String oSRemoteMachine) throws Exception
   {
      String oSCurrentMachine="", outPutDirRemoteMachine="", errorMessage1="", errorMessage2="";
		   
	  oSCurrentMachine = System.getProperty("os.name");
		   
	  /*
	  System.out.println("OS Current Machine : " + oSCurrentMachine);
	  System.out.println("OS Remote Machine  : " + oSRemoteMachine);
	  System.out.println("Output Dir Current Machine: " + outPutDirCurrentMachine);
	  */
		 
	  try
	  {
	     if(oSRemoteMachine.contains("Windows"))
	     {	 

	        outPutDirRemoteMachine = outputDirCurrentMachine.replace("/", "\\\\") + "\\\\";
	      
	        if(oSRemoteMachine.startsWith("C:") == false) 
		       outPutDirRemoteMachine = "C:" + outputDirCurrentMachine.replace("/", "\\\\") + "\\\\";
	        
	    	outPutDirRemoteMachine = outPutDirRemoteMachine.replace("fannyriveraortiz","friverao"); 
	      }	  
	      else
		     outPutDirRemoteMachine = outputDirCurrentMachine + System.getProperty("file.separator");
	  }
	  catch (Exception e1)
	  {
	      errorMessage1 = e1.getMessage();
	      errorMessage2 = "Error XXXX: Occurred while building the OutPut Directory for the AOP Files: " + outPutDirRemoteMachine + System.lineSeparator();
	      errorMessage2 = errorMessage2 + " OutPut Directory Current Machine: " + outputDirCurrentMachine + System.lineSeparator();
	      errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	      throw new Exception(errorMessage2);
	  }  
		   
      return outPutDirRemoteMachine;
	   
   }
 
   public String saveTextFileProperties(int headerId, TextFileController textFileCon, FRLConfiguration frlCon, int projectId)
   {
      ArrayList<TextFileProperties> textFileProps = new ArrayList<>();
	  String name="", value="", dataType="", errorMessage1=""; 
	  int c=0, propId=0;
	  PropertyType propType;
	  
	  /*System.out.println("");
	  System.out.println("*** Inside the saveTextFileProperties Method ...***");
	  */
	  
	  // Delete all the previous text file properties from the project id
	  try
	  {
	     textFileCon.deleteTextProperties(projectId, headerId);  
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
         return errorMessage1;
	  }
		
	  // Load the properties from the text file property name table
	  try 
	  {
	     textFileProps = textFileCon.loadPropertyNames(headerId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
         return errorMessage1;
	  }
		  
	  // Loop through the TextFileProps ArrayList
	  for (c = 0; c < textFileProps.size(); c++) 
	  {
	     propId   = textFileProps.get(c).getPropId();
		 name     = textFileProps.get(c).getName();
		 dataType = textFileProps.get(c).getDataType();
		 propType = textFileProps.get(c).getType();
		     
		 // Assign the correct project output directory for the AOP Files
		 // Depending on the remote Operating System
		 if(name.equals(frlCon.specialPropertyOS)) 
		 {
			try
			{
		       value = modifyOutputDirAOPFiles(frlCon.projectOutputDir, frlCon.operatingSystem);
			}
		    catch(Exception e3)
			{
		       errorMessage1 = e3.getMessage();
			   return errorMessage1;
			}
		    
		    //System.out.println("Output Directory AOP Files: " + value);
		 }
		 else
		 {	 
		    // Obtain the Property Value
			try
			{
		       value = getPropertyValue(frlCon.featuresConfigFile, name);
			}
			catch(Exception e4)
			{
		       errorMessage1 = e4.getMessage();
			   return errorMessage1;
			}
		 }
		 
		 // Insert the New Property Name in the Text File Properties Table
		 try 
		 {
		    TextFileProperties tfp = new TextFileProperties(projectId, headerId, propId, name, 
					                                        value, dataType, propType);
	        textFileCon.saveTextProperty(tfp);
	     } 
		 catch (Exception e5) 
		 {
	        errorMessage1 = e5.getMessage();
			return errorMessage1;
		 }

      }
	  
	  return errorMessage1;
		   
   }
   
   public String saveTextProperties(String databaseConfigFile, 
		                            TextFileProperties textFilePropertyRecord, 
		                            TextFilePropertyName textFilePropertyNameRecord)
   {
      String errorMessage1="", errorMessage2="";
      
      // Define an attribute for the controller
      AOPTemplateFileController aOPTemplateFileController;
      
      // Define an object for the Controller Class
      aOPTemplateFileController = new AOPTemplateFileController();
      
      try 
	  {
	     aOPTemplateFileController.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while connecting to the Forensic Ready Logger Database." + System.lineSeparator() ;
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     return errorMessage2;
	  }
      
      // Saving the Text Properties
	  try 
	  {
	     aOPTemplateFileController.saveTextFileProperty(databaseConfigFile, textFilePropertyRecord);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while saving the AOP Template File Properties." + System.lineSeparator() ;
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     return errorMessage2;	     
	  }
	  
      // Saving the Text Property Names
	  try 
	  {
	      aOPTemplateFileController.saveTextFilePropertyName(databaseConfigFile, textFilePropertyNameRecord);
	  } 
	  catch (Exception e3) 
	  {
		  errorMessage1 = e3.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while saving the AOP Template File Property Names." + System.lineSeparator() ;
		  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		  return errorMessage2;	 
	  }
	  
	  try 
	  {
	     aOPTemplateFileController.disconnect();
	  } 
	  catch (Exception e4) 
	  {
	     errorMessage1 = e4.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while disconnecting from the Forensic Ready Logger Database." + System.lineSeparator() ;
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     return errorMessage2;
	  }
	  
      return errorMessage2;
      
   }   
   
   public String createAOPTemplateFileProperties(String featuresConfigFile, String databaseConfigFile, int projectId, int headerId)
   {
      String errorMessage1="", errorMessage2="", textFilePackageName="", methodName1="", textFileName="", projectName="", projectOutputDir="", 
    		 umlSeqDiagTextFileName1="", methodName3="", bluePrintObject1="", objectOrientedDelimiter1="", startParameters="", endParameters="",  
    		 newLine1="", whiteSpaceWordsDelimiter1="", guiLibDelimiter="", invalidSpecialCharacter="", validSpecialCharacter="", 
    		 member1="", member2="", startSendMessage="", endSendMessage="", userName="", startNote="", endNote="",  colorDelimiter="", 
    		 colorNote="", position="", space="", startDivision="", endDivision="", pointCutName1a="", methodName2="", pointCutName1b="", methodName6="",
    		 umlSeqDiagPngFileName1="", startUMLSeqDiagram="", endUMLSeqDiagram="", pointCutName2a="", methodName4 = "", pointCutName2b="", methodName5="",
    	     annotationFile="", doNotLogAttributes="", findWhiteSpaces="", programmingLanguage="", dataTypeFullClassName="", dataTypeShortClassName="",
    	     dataTypeStringList="", dataTypeNumberList="", pointCutName3a="", methodName9="", propertyName="", propertyValue="",
    	     connectProjectMethod="", connectProjectMethodReturnValue="", umlSeqDiagTextFileName2="";
      
      int propId=0;
      Properties prop = new Properties();
      InputStream input;
      
      TextFileProperties textFilePropertyRecord;
      TextFilePropertyName textFilePropertyNameRecord;
      PropertyType propertyType;
       
      // Get the properties value for Header 1
      
      try
      {
         input = new FileInputStream(featuresConfigFile);
         
         // Load the properties file
         prop.load(input);
         
         textFilePackageName = prop.getProperty("textFilePackageName");
         
         // Project Output Directory
         projectOutputDir = prop.getProperty("projectOutputDir") ;
         
         if(headerId == 1)
         {
            textFileName              = prop.getProperty("textFileName1");       	 
            methodName1               = prop.getProperty("methodName1");
            projectName               = prop.getProperty("projectName");
            umlSeqDiagTextFileName1   = prop.getProperty("umlSeqDiagTextFileName1");
            methodName3               = prop.getProperty("methodName3");
            bluePrintObject1          = prop.getProperty("bluePrintObject1");
            objectOrientedDelimiter1  = prop.getProperty("objectOrientedDelimiter1");
            startParameters           = prop.getProperty("startParameters");
            endParameters             = prop.getProperty("endParameters");
            newLine1                  = prop.getProperty("newLine1");
            whiteSpaceWordsDelimiter1 = prop.getProperty("whiteSpaceWordsDelimiter1");
            guiLibDelimiter           = prop.getProperty("guiLibDelimiter");
            invalidSpecialCharacter   = prop.getProperty("invalidSpecialCharacter");
            validSpecialCharacter     = prop.getProperty("validSpecialCharacter");
            member1                   = prop.getProperty("member1");
            member2                   = prop.getProperty("member2");
            startSendMessage          = prop.getProperty("startSendMessage");
            endSendMessage            = prop.getProperty("endSendMessage");
            userName                  = prop.getProperty("userName");
            startNote                 = prop.getProperty("startNote");
            endNote                   = prop.getProperty("endNote");
            colorDelimiter            = prop.getProperty("colorDelimiter");
            colorNote                 = prop.getProperty("colorNote");
            position                  = prop.getProperty("position");
            space                     = prop.getProperty("space");
            startDivision             = prop.getProperty("startDivision");
            endDivision               = prop.getProperty("endDivision");
            pointCutName1a            = prop.getProperty("pointCutName1a");
            methodName2               = prop.getProperty("methodName2");
            pointCutName1b            = prop.getProperty("pointCutName1b");
            methodName6               = prop.getProperty("methodName6");
            
            connectProjectMethod      = prop.getProperty("connectProjectMethod");
            connectProjectMethodReturnValue  = prop.getProperty("connectProjectMethodReturnValue");
            endUMLSeqDiagram          = prop.getProperty("endUMLSeqDiagram");
    	    umlSeqDiagPngFileName1    = prop.getProperty("umlSeqDiagPngFileName1");
    	    umlSeqDiagTextFileName2   = prop.getProperty("umlSeqDiagTextFileName2");

         }
         else
        	 if(headerId == 2)
        	 {
                textFileName            = prop.getProperty("textFileName2");          		 
        	    umlSeqDiagTextFileName1 = prop.getProperty("umlSeqDiagTextFileName1");
        	    umlSeqDiagPngFileName1  = prop.getProperty("umlSeqDiagPngFileName1");
        	    newLine1                = prop.getProperty("newLine1");
        	    startUMLSeqDiagram      = prop.getProperty("startUMLSeqDiagram");
        	    endUMLSeqDiagram        = prop.getProperty("endUMLSeqDiagram");
        	    umlSeqDiagTextFileName2   = prop.getProperty("umlSeqDiagTextFileName2");
        	    
        	    pointCutName2a          = prop.getProperty("pointCutName2a");
        	    methodName4             = prop.getProperty("methodName4");
        	    pointCutName2b          = prop.getProperty("pointCutName2b");
        	    methodName5             = prop.getProperty("methodName5");
        	 }
        	 else
            	 if(headerId == 3)
            	 {
            		textFileName             = prop.getProperty("textFileName3");   
                    annotationFile           = prop.getProperty("annotationFile");
            		doNotLogAttributes       = prop.getProperty("doNotLogAttributes");
            		findWhiteSpaces          = prop.getProperty("findWhiteSpaces");
            		objectOrientedDelimiter1 = prop.getProperty("objectOrientedDelimiter1");
            		programmingLanguage      = prop.getProperty("programmingLanguage").toLowerCase();
            		dataTypeFullClassName    = prop.getProperty("dataTypeFullClassName");
            		dataTypeShortClassName   = prop.getProperty("dataTypeShortClassName");
            		dataTypeStringList       = prop.getProperty("dataTypeStringList");
            		dataTypeNumberList       = prop.getProperty("dataTypeNumberList");
            		pointCutName3a           = prop.getProperty("pointCutName3a");
            		methodName9              = prop.getProperty("methodName9");
            	 }
         
       } 
       catch (IOException e1) 
       {
 	      errorMessage1 = e1.getMessage();
 	      errorMessage2 = "Error XXXX: Occurred while loading the AOP Template File Properties." + System.lineSeparator() ;
 	      errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
 	      return errorMessage2;
       }
      
      propId = 0;
      
      if(headerId == 1)
      {
         propId++;
    	 propertyName  = "textFilePackageName";
         propertyValue = textFilePackageName;
         propertyType  = PropertyType.Component;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e2) 
         {
   	        errorMessage2 = e2.getMessage();
   	        return errorMessage2;
         }
 
         propId++;
    	 propertyName  = "methodName1";
         propertyValue = methodName1;
         propertyType  = PropertyType.Method;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e3) 
         {
   	        errorMessage2 = e3.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "textFileName1";
         propertyValue = textFileName;
         propertyType  = PropertyType.Component;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e4) 
         {
   	        errorMessage2 = e4.getMessage();
   	        return errorMessage2;
         }

         propId++;
    	 propertyName  = "projectName";
         propertyValue = projectName;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e5) 
         {
   	        errorMessage2 = e5.getMessage();
   	        return errorMessage2;
         }
         
         // Project Output Directory
         propId++;
    	 propertyName  = "projectOutputDir";
         propertyValue = projectOutputDir;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e6) 
         {
   	        errorMessage2 = e6.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "umlSeqDiagTextFileName1";
         propertyValue = umlSeqDiagTextFileName1;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e7) 
         {
   	        errorMessage2 = e7.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "methodName3";
         propertyValue = methodName3;
         propertyType  = PropertyType.Method;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e8) 
         {
   	        errorMessage2 = e8.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "bluePrintObject1";
         propertyValue = bluePrintObject1;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e9) 
         {
   	        errorMessage2 = e9.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "objectOrientedDelimiter1";
         propertyValue = objectOrientedDelimiter1;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e10) 
         {
   	        errorMessage2 = e10.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "startParameters";
         propertyValue = startParameters;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e11) 
         {
   	        errorMessage2 = e11.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "endParameters";
         propertyValue = endParameters;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e12) 
         {
   	        errorMessage2 = e12.getMessage();
   	        return errorMessage2;
         }

         propId++;
    	 propertyName  = "newLine1";
         propertyValue = newLine1;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e13) 
         {
   	        errorMessage2 = e13.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "whiteSpaceWordsDelimiter1";
         propertyValue = whiteSpaceWordsDelimiter1;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e14) 
         {
   	        errorMessage2 = e14.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "guiLibDelimiter";
         propertyValue = guiLibDelimiter;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e15) 
         {
   	        errorMessage2 = e15.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "invalidSpecialCharacter";
         propertyValue = invalidSpecialCharacter;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e16) 
         {
   	        errorMessage2 = e16.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "validSpecialCharacter";
         propertyValue = validSpecialCharacter;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e17) 
         {
   	        errorMessage2 = e17.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "member1";
         propertyValue = member1;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e17) 
         {
   	        errorMessage2 = e17.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "member2";
         propertyValue = member2;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e18) 
         {
   	        errorMessage2 = e18.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "startSendMessage";
         propertyValue = startSendMessage;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e19) 
         {
   	        errorMessage2 = e19.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "endSendMessage";
         propertyValue = endSendMessage;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e20) 
         {
   	        errorMessage2 = e20.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "userName";
         propertyValue = userName;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e21) 
         {
   	        errorMessage2 = e21.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "startNote";
         propertyValue = startNote;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e22) 
         {
   	        errorMessage2 = e22.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "endNote";
         propertyValue = endNote;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e23) 
         {
   	        errorMessage2 = e23.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "colorDelimiter";
         propertyValue = colorDelimiter;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e24) 
         {
   	        errorMessage2 = e24.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
    	 propertyName  = "colorNote";
         propertyValue = colorNote;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e25) 
         {
   	        errorMessage2 = e25.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "position";
         propertyValue = position;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e26) 
         {
   	        errorMessage2 = e26.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "space";
         propertyValue = space;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e27) 
         {
   	        errorMessage2 = e27.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "startDivision";
         propertyValue = startDivision;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e28) 
         {
   	        errorMessage2 = e28.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "endDivision";
         propertyValue = endDivision;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e29) 
         {
   	        errorMessage2 = e29.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "connectProjectMethod";
         propertyValue = connectProjectMethod;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e30) 
         {
   	        errorMessage2 = e30.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "connectProjectMethodReturnValue";
         propertyValue = connectProjectMethodReturnValue;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "Object", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "Object", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e31) 
         {
   	        errorMessage2 = e31.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "endUMLSeqDiagram";
         propertyValue = endUMLSeqDiagram;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e32) 
         {
   	        errorMessage2 = e32.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "umlSeqDiagPngFileName1";
         propertyValue = umlSeqDiagPngFileName1;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e33) 
         {
   	        errorMessage2 = e33.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "umlSeqDiagTextFileName2";
         propertyValue = umlSeqDiagTextFileName2;
         propertyType  = PropertyType.Attribute;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e34) 
         {
   	        errorMessage2 = e34.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "pointCutName1a";
         propertyValue = pointCutName1a;
         propertyType  = PropertyType.Component;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e35) 
         {
   	        errorMessage2 = e35.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "methodName2";
         propertyValue = methodName2;
         propertyType  = PropertyType.Method;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e36) 
         {
   	        errorMessage2 = e36.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "pointCutName1b";
         propertyValue = pointCutName1b;
         propertyType  = PropertyType.Component;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e37) 
         {
   	        errorMessage2 = e37.getMessage();
   	        return errorMessage2;
         }
         
         propId++;
         propertyName  = "methodName6";
         propertyValue = methodName6;
         propertyType  = PropertyType.Method;
         
         textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
         textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
         
         try
         {
            saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
         }
         catch (Exception e38) 
         {
   	        errorMessage2 = e38.getMessage();
   	        return errorMessage2;
         }

      }
      else 
         if(headerId == 2)
         {
            propId++; 
            propertyName  = "textFilePackageName";
            propertyValue = textFilePackageName;
            propertyType  = PropertyType.Component;
              
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e39) 
            {
      	        errorMessage2 = e39.getMessage();
      	        return errorMessage2;
            }
            
            propId++;
        	propertyName  = "textFileName2";
            propertyValue = textFileName;
            propertyType  = PropertyType.Component;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);

            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e40) 
            {
      	        errorMessage2 = e40.getMessage();
      	        return errorMessage2;
            }            
            
            // Project Output Directory
            propId++;
        	propertyName  = "projectOutputDir";
            propertyValue = projectOutputDir;
            propertyType  = PropertyType.Attribute;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e41) 
            {
      	        errorMessage2 = e41.getMessage();
      	        return errorMessage2;
            }   
            
            propId++;
        	propertyName  = "umlSeqDiagTextFileName1";
            propertyValue = umlSeqDiagTextFileName1;
            propertyType  = PropertyType.Attribute;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e42) 
            {
      	        errorMessage2 = e42.getMessage();
      	        return errorMessage2;
            } 
            
            propId++;
        	propertyName  = "umlSeqDiagPngFileName1";
            propertyValue = umlSeqDiagPngFileName1;
            propertyType  = PropertyType.Attribute;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e43) 
            {
      	        errorMessage2 = e43.getMessage();
      	        return errorMessage2;
            } 
            
            propId++;
        	propertyName  = "newLine1";
            propertyValue = newLine1;
            propertyType  = PropertyType.Attribute;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e44) 
            {
      	        errorMessage2 = e44.getMessage();
      	        return errorMessage2;
            } 
            
            propId++;
        	propertyName  = "startUMLSeqDiagram";
            propertyValue = startUMLSeqDiagram;
            propertyType  = PropertyType.Attribute;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e45) 
            {
      	        errorMessage2 = e45.getMessage();
      	        return errorMessage2;
            } 
            
            propId++;
        	propertyName  = "endUMLSeqDiagram";
            propertyValue = endUMLSeqDiagram;
            propertyType  = PropertyType.Attribute;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e46) 
            {
      	        errorMessage2 = e46.getMessage();
      	        return errorMessage2;
            } 
            
            propId++;
            propertyName  = "umlSeqDiagTextFileName2";
            propertyValue = umlSeqDiagTextFileName2;
            propertyType  = PropertyType.Attribute;
            
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e47) 
            {
      	        errorMessage2 = e47.getMessage();
      	        return errorMessage2;
            } 
            
            propId++;
        	propertyName  = "pointCutName2a";
            propertyValue = pointCutName2a;
            propertyType  = PropertyType.Component;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e48) 
            {
      	        errorMessage2 = e48.getMessage();
      	        return errorMessage2;
            } 
            
            propId++;
        	propertyName  = "methodName4";
            propertyValue = methodName4;
            propertyType  = PropertyType.Method;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e49) 
            {
      	        errorMessage2 = e49.getMessage();
      	        return errorMessage2;
            }           
            
            propId++;
        	propertyName  = "pointCutName2b";
            propertyValue = pointCutName2b;
            propertyType  = PropertyType.Component;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e50) 
            {
      	        errorMessage2 = e50.getMessage();
      	        return errorMessage2;
            }     
            
            propId++;
        	propertyName  = "methodName5";
            propertyValue = methodName5;
            propertyType  = PropertyType.Method;
             
            textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
            textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
            
            try
            {
               saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
            }
            catch (Exception e51) 
            {
      	        errorMessage2 = e51.getMessage();
      	        return errorMessage2;
            }  
    	  
         }
         else 
            if(headerId == 3)
            {
               propId++;
           	   propertyName  = "textFilePackageName";
               propertyValue = textFilePackageName;
               propertyType  = PropertyType.Component;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e52) 
               {
         	        errorMessage2 = e52.getMessage();
         	        return errorMessage2;
               }  
        	  
               propId++;
           	   propertyName  = "textFileName3";
               propertyValue = textFileName;
               propertyType  = PropertyType.Component;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e53) 
               {
         	        errorMessage2 = e53.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "annotationFile";
               propertyValue = annotationFile;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e54) 
               {
         	        errorMessage2 = e54.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "doNotLogAttributes";
               propertyValue = doNotLogAttributes;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e55) 
               {
         	        errorMessage2 = e55.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "findWhiteSpaces";
               propertyValue = findWhiteSpaces;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e56) 
               {
         	        errorMessage2 = e56.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "objectOrientedDelimiter1";
               propertyValue = objectOrientedDelimiter1;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e57) 
               {
         	        errorMessage2 = e57.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "programmingLanguage";
               propertyValue = programmingLanguage;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e58) 
               {
         	        errorMessage2 = e58.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "dataTypeFullClassName";
               propertyValue = dataTypeFullClassName;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e59) 
               {
         	        errorMessage2 = e59.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "dataTypeShortClassName";
               propertyValue = dataTypeShortClassName;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e60) 
               {
         	        errorMessage2 = e60.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "dataTypeStringList";
               propertyValue = dataTypeStringList;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "ArrayList<String>", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "ArrayList<String>", propertyType);
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e61) 
               {
         	        errorMessage2 = e61.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "dataTypeNumberList";
               propertyValue = dataTypeNumberList;
               propertyType  = PropertyType.Attribute;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "ArrayList<String>", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "ArrayList<String>", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e62) 
               {
         	        errorMessage2 = e62.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "pointCutName3a";
               propertyValue = pointCutName3a;
               propertyType  = PropertyType.Component;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);

               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e63) 
               {
         	        errorMessage2 = e63.getMessage();
         	        return errorMessage2;
               }  
               
               propId++;
           	   propertyName  = "methodName9";
               propertyValue = methodName9;
               propertyType  = PropertyType.Method;
                
               textFilePropertyRecord     = new TextFileProperties(projectId, headerId, propId, propertyName, propertyValue, "String", propertyType);
               textFilePropertyNameRecord = new TextFilePropertyName(headerId, propId, propertyName, "String", propertyType);
               
               try
               {
                  saveTextProperties(databaseConfigFile, textFilePropertyRecord, textFilePropertyNameRecord);
               }
               catch (Exception e64) 
               {
         	        errorMessage2 = e64.getMessage();
         	        return errorMessage2;
               }  
            }
      
      return errorMessage2;	   
   }

   public String getConfigPropValues(int projectId, int headerId, String aspectNameExt, 
		                             int propertyId, String text, String dbMethodList,
			                         FRLConfiguration frlCon, TextFileController textFileCon) throws Exception   
   {
      String errorMessage1="", name="", value="", line="", newValue="", propTypeStr="", dataType="", specialProject="OpenHospital";
	  TextFileProperties tfp = null;
	  PropertyType propType;
	  
	  // Get all the text file properties for this header id
	  try 
	  {
	     tfp = textFileCon.getPropertyValue(headerId, propertyId, projectId);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	 	 throw new Exception(errorMessage1);
	  }
	      
	  // Obtain each of the text file properties
	  name     = tfp.getName();
	  value    = tfp.getValue();	
	  propType = tfp.getType();
	  dataType = tfp.getDataType();
		  
	  // Convert Enum to String
	  propTypeStr = propType.name();
	  
	  // Validate if the property Type is equals to "Component"
	  if(propTypeStr.equals(frlCon.fieldType3))
	  {	  
		 if(value.equals(frlCon.textFileName1)) 
		 {	 
		    //extension = extension.replace(".aj", "");
		    //value     = value + extension;
			value = aspectNameExt;
		    line  = text.replace(frlCon.confPropertyName1, value);
		 }   
	     else 
	     {	 
	        line  = text.replace(frlCon.confPropertyName1, value);
	     }   
	  }
	  else
	     // Validate if the property Type is equals to "Attribute"
		 if(propTypeStr.equals(frlCon.fieldType4))
		 {

		    if(name.equals(frlCon.specialDelimiter)) 
			{	   
			   newValue = name + frlCon.assignValueDelimiter + value;
			}   
		    else
		       if(dataType.equals("String"))
		       {	   
		             newValue = name + frlCon.assignValueDelimiter + 
		        	   	        frlCon.whiteSpaceWordsDelimiter2 + value + 
		        		        frlCon.whiteSpaceWordsDelimiter2;
		       }
		       else
		          if(dataType.equals("Object"))
		          {
		             newValue = name + frlCon.assignValueDelimiter + value;
		          }
		    	
		     line  = text.replace(frlCon.confPropertyName1, newValue); 		
		       
		  }
		  // Validate if the property Type is equals to "Method"
		  else 
			  if(propTypeStr.equals(frlCon.fieldType5))
		      {
		        // Validate if the value is equal to "nonPrimitiveDataType"
		        if(value.equals(frlCon.methodName1))
		        {	 
		           // Generate the Non Primitive Data Type List
		    	   try
		    	   {
		    	      line = getNonPrimitiveDataTypeList(frlCon, projectId);
		    	   }
		    	   catch(Exception e2)
		    	   {
		    	      errorMessage1 = e2.getMessage();
		    	      throw new Exception(errorMessage1);
		    	   }
		        }	
		        else
		           // Validate if the value is equal to "databaseMethods"	
		           if(value.equals(frlCon.methodName2))
		           {	
		              //System.out.println("GENERATING DATABASE METHODS ...");	
		              line = dbMethodList;
		           }   		           
		           else
			          // Validate if the value is equal to "guiLibs"	
			          if(value.equals(frlCon.methodName3))
			          {	   
			             // Generate the Graphical User Interface Library List 
			    	     try
			    	     {
			                line = getGuiLibList(projectId, frlCon, text);
			    	     }
			             catch (Exception e4) 
					     {
					        errorMessage1 = e4.getMessage();
					        throw new Exception(errorMessage1);
					     }
			          } 
			          else  
			             // Generate the Starting Application Method Name   
			             if(value.equals(frlCon.methodName4))
			             {	  
					        try 
					        {
						       line = generateMethodName(frlCon, text, frlCon.methodName4, projectId);
						    } 
		                    catch (Exception e5) 
					        {
		                       errorMessage1 = e5.getMessage();
		                       throw new Exception(errorMessage1);
						    }
			             }  	
					     else
			                // Generate the Ending Application Method Name 
			                if(value.equals(frlCon.methodName5))
			                {	 
			            	   try
			            	   {
			                      line = generateMethodName(frlCon, text, frlCon.methodName5, projectId);
			            	   }
			                   catch (Exception e6) 
						       {
			                      errorMessage1 = e6.getMessage();
			                      throw new Exception(errorMessage1);
							   }
			                }   
			                else
			                {	 
				               // Generate the Connect Application Method Name 
				               if(value.equals(frlCon.methodName6))
				               {	   
				                  // Generate the Connect Application Method Name 
				                  try
				                  {
				                     line = generateMethodName(frlCon, text, frlCon.methodName6, projectId); 
				                  }
			                      catch (Exception e7) 
							      {
					                 errorMessage1 = e7.getMessage();
					                 throw new Exception(errorMessage1);
							      }
				                  
				               } 
	   
			                }       
         }
	  			
      return line; 	
   }   
   
   public String generateDBMethodsListString(int projectId, ArrayList<DatabaseMethod2> DBMetList,
		                                     FRLConfiguration frlCon) throws Exception
   { 
      int c=0, pipeLineEnd=0;
	  String errorMessage1="", errorMessage2="", packageName="", className="", shortMethodName="",
			 returnType1="", returnType2="", line="", dbMethodList="", returnTypeName="";
		   
	  try
	  {
	     pipeLineEnd  = DBMetList.size() - 1;
	      
	     // Loop through the DatabaseMethodDetails ArrayList
	     for (c = 0; c < DBMetList.size(); c++) 
	     {  
	        // Get the fields from the textFileDetails ArrayList
	        packageName     = DBMetList.get(c).getPackageName();
		    className       = DBMetList.get(c).getClassName();
		    shortMethodName = DBMetList.get(c).getShortMethodName();
		    returnType1     = DBMetList.get(c).getReturnType1();
		    returnType2     = DBMetList.get(c).getReturnType2();
			 
		    // Validate if the returnType1 is equal to "Class"
		    if(returnType1.toLowerCase().equals(frlCon.bluePrintObject1))
		       returnType1 = "";	
			 
		    // Build the line that contains the call method for the Aspect
		    if(returnType1.equals(frlCon.nonPrimitiveValue1) || 
			   returnType1.equals(frlCon.nonPrimitiveValue2))
		    {
		       returnTypeName = returnType1 + frlCon.startClassDef + returnType2 + frlCon.endClassDef;
		    }
		    else
		    {	 
		       returnTypeName = returnType1 + returnType2;
		    }
		 
		    ///line = "                            " +
		    line = "      " +
			       frlCon.startCallMethod + returnTypeName + " " + 
			       packageName     + frlCon.objectOrientedDelimiter1  + 
			       className       + frlCon.objectOrientedDelimiter1  + 
			       shortMethodName + frlCon.endCallMethod1;
		 
		    // Validate if the counter is less or equal to pipeLineFlag end value
		    if(c < pipeLineEnd)
		       line = line + " " + frlCon.callMethodDelimiter + " " +
		              System.getProperty("line.separator");
		    else
		       line = line + frlCon.endStatementDelimiter;
		     
		    dbMethodList = dbMethodList + line;
			 
	     } 
	  }
	  catch (Exception e1) 
      {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while creating the Methods that perform Data Operations for the Project Id: "+ projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
      } 
	      
      return dbMethodList;   
   }   
   
   public void generateAOPFile(int projectId, int headerId, 
		                       String aspectName, String extension1, 
		                       String aspectNameExt,
		                       String DBMethodListStr,
		                       ArrayList<TextFileDetails> textFileDets, 
		                       FRLConfiguration frlCon,
		                       TextFileController textFileCon) throws Exception
   {
      String errorMessage1="", errorMessage2="", fileName="", format="", filePath="", line="",
    		 text="";
      int i=0, propertyId=0;
      FieldType type;
      File file1;
      FileWriter fw = null;
      BufferedWriter bw = null;	 
  	  
     // Build the Name of the File using the Aspect Name and the extension
     fileName = aspectName + extension1;

     format = frlCon.tabDelimiter1;
     System.out.format(format, fileName, 1);
     System.out.println(fileName);

     // Build the Path for the New Aspect Oriented File that will be created
     // in the Project Output Directory
     filePath = frlCon.projectOutputDir + File.separator + fileName;

     // Create a new Text File
     file1 = new File(filePath);
  
     /*******************************************/
     // 2.- Build the content of the AOP File
     /******************************************/
      
     try 
     {
        file1.createNewFile();
	    fw = new FileWriter(file1.getAbsoluteFile());
	    bw = new BufferedWriter(fw);
     } 
     catch (IOException e1) 
     {
        errorMessage1 = e1.getMessage();
	    errorMessage2 = "Error XXXX: Occurred while creating a New Aspect Oriented File: "+ filePath + System.lineSeparator();
	    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	    throw new Exception(errorMessage2);
     }
  	  
     // Loop through the Details of the Aspect Oriented File ArrayList
     for (i = 0; i < textFileDets.size(); i++) 
     { 
        // Get the fields from the textFileDetails ArrayList
	    text       = textFileDets.get(i).getText();
	    type       = textFileDets.get(i).getType();
	    propertyId = textFileDets.get(i).getPropertyId();
	          
	    // Validate if the field type is equal to "Personalized"
	    if (type.name().equals(frlCon.fieldType1))
	    {	 
	       // Get the final value of the line from the Text File Properties
	       // or from some methods 			 
	       try 
	       {
	          line = getConfigPropValues(projectId, headerId, aspectNameExt, propertyId, text, DBMethodListStr, frlCon, textFileCon);
	       }
           catch (Exception e2) 
		   {
              fw.close(); 
              bw.close();
	          errorMessage1 = e2.getMessage();
	          throw new Exception(errorMessage1);
		    }
	    } 	
	    else 
	       // Validate if the field type is equal to "Fixed"	
	       if (type.name().equals(frlCon.fieldType2))
	          line = text;

	     /*if(aspectName.equals("FrlDatabaseMethods"))
	        System.out.println("Line: " + line);
	        */
	     
         // Write in the text file
         try 
         {
	        fw.append(line);
	        fw.append(System.lineSeparator());
	     } 
         catch (IOException e3) 
         {
            fw.close(); 
            bw.close(); 
       	    errorMessage1 = e3.getMessage();
   	        errorMessage2 = "Error XXXX: Occurred while writing the content of the new Aspect Oriented File: " + filePath + System.lineSeparator();
   	        errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
   	        throw new Exception(errorMessage2);
         }
		
      } 
       
      // Close text file
      try 
      {
         fw.close(); 
         bw.close();
      } 
      catch (IOException e4) 
      {
         errorMessage1 = e4.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while closing the Buffer Writter." + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
      }	
   
   }
   
   public void prepareInfoAOPFile(int projectId, int headerId, String aspectName, 
		                          ArrayList<TextFileDetails> textFileDets, 
		                          FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="", errorMessage2="", extension1="", extension2="", condition="",
    		 aspectNameExt="", DBMethodListStr="", connectMethodStr="", packageName="", className="", shortMethodName="", 
    		 returnType1="", returnType2="", text="";
      int dbMetCount=0, aspectCase=0, i=0, j=0, k=0, f=0, srcDirId=0, srcDirDbMet=0, range=0, 
    	  aspectMetLimit=0, start=0, end=0, finalEnd=0; 
      ArrayList<DatabaseMethod2> DBMetList1 = new ArrayList<DatabaseMethod2>();
      ArrayList<DatabaseMethod2> DBMetList2 = new ArrayList<DatabaseMethod2>();
      ArrayList<SourceDirectory> sourceDirList = new ArrayList<>();
      
      DatabaseMethod2 dbMetRecord; 
      TextFileController textFileCon = new TextFileController();
      
      // Initialize ArrayLists
      DBMetList1.clear();
      DBMetList2.clear();
      sourceDirList.clear();
      
      // Initialize Variables
	  aspectMetLimit = 500;
				  
	  // Connect to the Forensic Ready Logger Database
      try 
      {
         textFileCon.connect(frlCon.databaseConfigFile);
      } 
      catch (Exception e1) 
      {
         errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
      }
      
      /*******************************************/
      // 1.- Build the Filename of the AOP File
      /******************************************/
      
	  // Build the extension and the condition depending on the Aspect Name
      // Aspect Name: FrlStart
	  if(aspectName.equals(frlCon.textFileName2))
	  {
		 aspectCase       = 1;
		 
	     condition        = "";
		 extension1       = ".aj";
		 aspectNameExt    = "";
		 DBMethodListStr  = "";
		 connectMethodStr = "";
	  }
      else 
    	  // Aspect Name: FrlStartFrlDatabaseMethods 
         if(aspectName.equals(frlCon.textFileName1))
	     {        	
            text = "call(configuration_property_1) || ";
            		
            // Bring the Connect Application Method Name 
        	try
            {
        	   connectMethodStr = generateMethodName(frlCon, text, frlCon.methodName6, projectId); 
            }
            catch (Exception e7) 
		    {
                errorMessage1 = e7.getMessage();
                throw new Exception(errorMessage1);
		    }
        	
        	connectMethodStr = "      " + connectMethodStr;
        	//System.out.println("Connect Project Method: " + connectMethodStr);
        	
            dbMetCount = 0; 
           
            // Calculate the quantity of the methods that perform Data Operations for this Project
       	    try
    	    {
    	       dbMetCount = textFileCon.calculateDBMethodsCount(projectId);
    	    }
    	    catch (Exception e2) 
    	    {
    	       errorMessage1 = e2.getMessage();
    		   throw new Exception(errorMessage1);
    	    }
            
       	    System.out.println("This Project has " + dbMetCount + " Methods that perform Data Operations");
       	    System.out.println("Dividing these Methods in the following Aspect Oriented Files ...");
       	    
       	    // Validate how many methods that perform data operation are
       	    // if there are more than 500 methods
       	    if(dbMetCount >= aspectMetLimit)
       	    {	
       	       aspectCase = 2;
       	       
       	       // Counter for the Aspect Oriented File
       	       f = 0;
       	       
       	       //System.out.println("Inside the Case 2");
       	    	
       	       try
               {
                  sourceDirList = textFileCon.loadSourceDirDBMethodsCount(projectId);
               }
               catch (Exception e3) 
               {
                  errorMessage1 = e3.getMessage();
      	          throw new Exception(errorMessage1);
               }
            
               // Loop for the Source Directories ArrayList
               for (i = 0; i < sourceDirList.size(); i++) 
               {
                  srcDirId    = sourceDirList.get(i).getIdDir();
                  srcDirDbMet = sourceDirList.get(i).getDbMetCount();

                  condition   = "= " + srcDirId;
                  
                  /*
                  System.out.println("Source Dir Id : " + srcDirId);
                  System.out.println("DB Met Count  : " + srcDirDbMet);
                  System.out.println("Condition     : " + condition);
                  */
                  
                  System.out.println("Source Dir: " + srcDirId);
                  
                  // Load the Database Methods List for this Source Directory
         	      try 
         	      {
         	         DBMetList1 = textFileCon.loadDBMethodsDetails(projectId, condition);
         	      } 
         	      catch (Exception e4) 
         	      {
         	         errorMessage1 = e4.getMessage();
         	         throw new Exception(errorMessage1);
         	      }
         	      
         	      // Print the DB Met List Array
         	      /*System.out.println("DB Method List 1      : " + Arrays.deepToString(DBMetList1.toArray()));
         	      System.out.println("DB Method List 1 Size : " + DBMetList1.size());
         	      System.out.println("Dividing the AOP Files into Chunks ...");
         	      */
         	      
         	      // Initialize Variables
         	      finalEnd = srcDirDbMet;
         		  j = 0;
         		  k = 0;
         		   
         	  	  start = k;
         	  	  j     = start;
         	  	  
         	      // Calculate the end
         		  if(srcDirDbMet <= aspectMetLimit)
         		  {	  
         	         end = srcDirDbMet;
         		  }   
         		  else
         		  {	  
         		     end = aspectMetLimit;
         		  }  
         	  	 	   
         		  /*
         	  	  System.out.println("*** INITIAL VALUES *** ");
      	      
      	          System.out.println("Source Directory DB Met : " + srcDirDbMet);
      	          System.out.println("AOF Methods Limit       : " + aspectMetLimit);
      	          System.out.println("Final End               : " + finalEnd);
      	          System.out.println("Start                   : " + start);
      	   	      System.out.println("End                     : " + end);
         	      System.out.println("Counter J : " + j);
         	      System.out.println("Counter K : " + k);
         	      System.out.println("");
         	      */
         	     
         	      //System.out.println("===> J LOOP STARTS: J " + j);

         	      while (j < finalEnd) 
       	          {
       	    	     /*System.out.println("=====================");  
       	    	     System.out.println("===> INSIDE J LOOP J: " + j);
       	    	     System.out.println("=====================");
       	    	  
       	    	     System.out.println("BEFORE K LOOP K: " + k); 
       	    	     */
       	    	     
       	    	     // Initialize the List of the Methods that perform Data Operations #2
       	    	     DBMetList2.clear();

       	    	     for (k = start; k < end; k++) 
       	             {  
       		    	    //System.out.println("===> INSIDE K LOOP ");
       	    	    	 
       	    	        // Get the fields from the textFileDetails ArrayList
       	    	        try
       	    	        {
          	               packageName     = DBMetList1.get(k).getPackageName();
          		           className       = DBMetList1.get(k).getClassName();
          		           shortMethodName = DBMetList1.get(k).getShortMethodName();
          		           returnType1     = DBMetList1.get(k).getReturnType1();
          		           returnType2     = DBMetList1.get(k).getReturnType2();
       	    	        }
       	    	        catch (Exception e4) 
               	        {
       	    	           errorMessage1 = e4.getMessage();
       	    		       errorMessage2 = "Error XXXX: Occurred while loading the Methods that perform Data Operations" + System.lineSeparator();
       	    		       errorMessage2 = errorMessage2 + "Package Name: " + packageName + System.lineSeparator();
       	    		       errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
       	    		       errorMessage2 = errorMessage2 + "Short Method Name: " + shortMethodName + System.lineSeparator();
       	    		       errorMessage2 = errorMessage2 + "Source Dir: " + srcDirDbMet + System.lineSeparator();
       	    		       errorMessage2 = errorMessage2 + "Start: " + start + System.lineSeparator();
       	    		       errorMessage2 = errorMessage2 + "End: " + end + System.lineSeparator();
       	    		       errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
       	    		       throw new Exception(errorMessage2);
               	        }
          		    
          		        // Build a new record 
          		        dbMetRecord = new DatabaseMethod2(packageName, className, 
      			                                          shortMethodName, returnType1, returnType2);
          		         
          		        // Put the content into the DBMetList2 to generate the String
          		        DBMetList2.add(dbMetRecord);
 
       	             }
       	    	     
       	    	     //System.out.println("AFTER K LOOP K : " + k);
       	    	     
       	    	     //System.out.println("DB Method List 2      : " + Arrays.deepToString(DBMetList2.toArray()));
       	    	     
       	    	     // Generate the Database Method String
       	    	     DBMethodListStr = "";
       	    	     
   	                 try 
   	                 {
   	                    DBMethodListStr = generateDBMethodsListString(projectId, DBMetList2, frlCon);
   	                 } 
   	                 catch (Exception e5) 
   	                 {
   	                    errorMessage1 = e5.getMessage();
   	         	        throw new Exception(errorMessage1);
   	                 }
       	    	 
   	                 //System.out.println("DB Method String : " + DBMethodListStr);
   	                 
   	                 f++;
   	                 
   	                 extension1    = "_" + f + ".aj";
 	                 extension2    = extension1.replace(".aj", "");
 	                 aspectNameExt = frlCon.textFileName1 + extension2;
 	                 
 	                 // Validate if it is the First FRLDatabase Method File
	                 // Add to the List of the Methods that perform Data Operations 
	              	 // The Connect Project Method
 	                 if(f == 1)
 	                 {
 	              	    connectMethodStr = connectMethodStr + System.lineSeparator();
 	              	    DBMethodListStr  = connectMethodStr + DBMethodListStr;
 	              	    
 	              	    //System.out.println("Connect Method Str : " + connectMethodStr);
 	              	    //System.out.println("DB Method Lis tStr : " + DBMethodListStr);
 	                 }
 	                 
 	                 /*
 	                 System.out.println("Extension 1       : " + extension1);
 	                 System.out.println("Extension 2       : " + extension2);
 	                 System.out.println("Aspect Name Ext 2 : " + aspectNameExt);
 	                 */
 	               
 	      	         try
 	    		     {
 	    	            generateAOPFile(projectId, headerId, aspectName, extension1, 
 	                                    aspectNameExt, DBMethodListStr, textFileDets,
 	                                    frlCon, textFileCon);
 	    		     }
 	    		     catch (Exception e6) 
 	        	     {
 	        	        errorMessage1 = e6.getMessage();
 	        	        throw new Exception(errorMessage1);
 	        	     }
       	    	 
       	    	     start = k;
       	    	     j = start;
       	    	 
       	    	     end = k + aspectMetLimit;
       	    	 
       	    	     if(end > finalEnd)
       	  	         {	  
       	    	        range = finalEnd - k;	 
       	                end   = k + range;
       	            
       	  	         }   
       	    	     /*
       	    	     System.out.println("*************************");	    	 
       	    	     System.out.println("*** NEXT VALUES FOR K *** ");
       	    	     System.out.println("Start     : " + start);
       	    	     System.out.println("End       : " + end);
       	    	     System.out.println("Range     : " + range);
       	    	     System.out.println("Counter J : " + j);
       	    	     System.out.println("************************");
       	    	     */

       	          } 	
       	      
       	          //System.out.println("===> J LOOP ENDS J: " + j);

       	       }
            } 
       	    else
       	    {	
       	       // There are less than 500 methods, then put ALL the Data Operations Methods in ONE Aspect Oriented File	
       	       aspectCase = 3;
       	       
               condition     = "> 0";
               extension1    = "_1.aj";
               
               // Generate the Database Methods Array List 
         	   try 
         	   {
         	      DBMetList1 = textFileCon.loadDBMethodsDetails(projectId, condition);
         	   } 
         	   catch (Exception e7) 
         	   {
         	      errorMessage1 = e7.getMessage();
         	      throw new Exception(errorMessage1);
         	   }
         	   
         	   // Generate the Database Methods String
         	   try 
         	   {
         	      DBMethodListStr = generateDBMethodsListString(projectId, DBMetList1, frlCon);
         	   } 
         	   catch (Exception e8) 
         	   {
         	      errorMessage1 = e8.getMessage();
         	      throw new Exception(errorMessage1);
         	   }
         	   
         	   // Add to the List of the Methods that perform Data Operations 
         	   // The Connect Project Method
         	   connectMethodStr = connectMethodStr + System.lineSeparator();
         	   DBMethodListStr  = connectMethodStr + DBMethodListStr;

         	   //System.out.println("DB Method List Inicial: " + System.lineSeparator() + DBMethodListStr);
         	   //System.out.println("Connect Project Method : " + connectMethodStr);
         	   //System.out.println("DB Method List Final: " + System.lineSeparator() + DBMethodListStr);         	  
         	   
               extension1    = "_1.aj";
               extension2    = extension1.replace(".aj", "");
               aspectNameExt = frlCon.textFileName1 + extension2;
       	    }

	     }
  
	  // Validate the aspect Case
	  if(aspectCase == 1 || aspectCase == 3 )
	  {
	     try
		 {
	        generateAOPFile(projectId, headerId, aspectName, extension1, 
                            aspectNameExt, DBMethodListStr, textFileDets,
                            frlCon, textFileCon);
		 }
		 catch (Exception e9) 
    	 {
    	    errorMessage1 = e9.getMessage();
    	    throw new Exception(errorMessage1);
    	 }
	  }
	  
	  // Disconnect from the Database
	  try 
	  {
	     textFileCon.disconnect();
	  }
	  catch (Exception e10) 
	  {
	     errorMessage1 = e10.getMessage();
		 throw new Exception(errorMessage1);
	  }	
	  	  
   
   }

   public void configureAspectOrientedFile(int projectId, String aspectName, FRLConfiguration frlCon) throws Exception
   {
      int headerId=0;
      String errorMessage1="";
  
      TextFileController textFileCon;
	  ArrayList<TextFileDetails> textFileDets = new ArrayList<>();	
	  
	  //System.out.println("Inside the generateAspectFile method ...");
	  
      // Create a new object of TextFileController class
      textFileCon = new TextFileController();
				  
	  // Connect to the Forensic Ready Logger Database
      try 
      {
         textFileCon.connect(frlCon.databaseConfigFile);
      } 
      catch (Exception e1) 
      {
         errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
      }
		
      // Get the current Header Id for the Aspect Oriented File
      try 
      {
         headerId = textFileCon.getHeaderId(aspectName, frlCon.programmingLanguage);
      } 
      catch (Exception e2) 
      {
         errorMessage1 = e2.getMessage();
         throw new Exception(errorMessage1);
      }
   
      // Save the properties for the Aspect Oriented File
      errorMessage1 = saveTextFileProperties(headerId, textFileCon, frlCon, projectId);
   
      if (errorMessage1.isEmpty() == false)
      {	  
         throw new Exception(errorMessage1); 
      }   
		
      // Get the Details from the Database for the Aspect Oriented File
      try 
      {
         textFileDets = textFileCon.getTextFile(headerId);
      } 
      catch (Exception e3) 
      {
         errorMessage1 = e3.getMessage();
         throw new Exception(errorMessage1);
      }
      
	  try 
	  {
		textFileCon.disconnect();
	  }
	  catch (Exception e4) 
	  {
	     errorMessage1 = e4.getMessage();
		 throw new Exception(errorMessage1);
	  }
   
	  // Generate the Aspect Oriented Programming File
	  try
	  {
         prepareInfoAOPFile(projectId, headerId, aspectName, textFileDets, frlCon);
	  }
      catch (Exception e5) 
      {
         errorMessage1 = e5.getMessage();
         throw new Exception(errorMessage1);
      }
   
   }    
   
   public String generateAspectOrientedFiles(int projectId, FRLConfiguration frlCon)
   {
      String errorMessage1="", folder="", format="", aspectName="";
      int headerId=0;
      
	  System.out.println("");
	  System.out.println("For the Project: " + frlCon.projectName);
	  System.out.println("These are the generated Aspect Oriented Programming Files: ");
   
	  folder = frlCon.projectOutputDir;
	  format = frlCon.tabDelimiter1;
	  
	  System.out.println("Folder: ");
	  System.out.format(format, folder, 1);
	  System.out.println(folder);
   
	  System.out.println("Files: ");
	  
	  // Initialize Variables
	  headerId = 1;
	  /*****************************************************************/
	  // 1.- Build the properties that are required for the AOP Files
	  // In the tables: textFileProperty and textFilePropertyName
	  /*****************************************************************/
	  while (headerId <=3 )
	  {
	     try
		 {
		    createAOPTemplateFileProperties(frlCon.featuresConfigFile, frlCon.databaseConfigFile, projectId, headerId); 
		 }
		 catch(Exception e1)
		 {
		    errorMessage1 = e1.getMessage();
			return errorMessage1;			  
		 }
	     
	     headerId++;
	  }
	  
	  /*****************************************************************/
	  // 2.- Generate the Aspect File #1: FrlStart
	  /*****************************************************************/
	  aspectName = frlCon.textFileName2;
	  
	  System.out.println("Aspect Name : " + aspectName);
			  
	  try
	  {
	     configureAspectOrientedFile(projectId, aspectName, frlCon);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
	     return errorMessage1;		  
	  }

	  /*****************************************************************/
	  // Generate the Aspect Files #2: FrlDatabaseMethods
	  /*****************************************************************/
	  aspectName = frlCon.textFileName1;
	  
	  System.out.println("Aspect Name : " + aspectName);

	  try 
	  {
	     configureAspectOrientedFile(projectId, aspectName, frlCon);
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
	     return errorMessage1;		  
	  }
	  
	  return errorMessage1;
	  
   }
   
}
