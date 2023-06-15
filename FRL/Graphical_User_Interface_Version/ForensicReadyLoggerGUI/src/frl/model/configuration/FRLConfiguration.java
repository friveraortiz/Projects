package frl.model.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import frl.gui.configFile.ConfigFileFormEvent;

public class FRLConfiguration 
{
   // # 1.- Software System General Information #
   public String featuresConfigFile;
   public String databaseConfigFile;
   public String projectName;
   public String jarFileName;
   public String jarFullFilePath;
   public String projectInputDir;
   public String projectOutputDir;
   public String programmingLanguage;
   public String dbms;
   public String inputMethod;
   public String startProjectMethod;
   public String endProjectMethod;
   public String connectProjectMethod;
   public String connectProjectMethodReturnValue;
   
   // # 2.- Programming Language Information #
   
   // # Source Code Information#
   public String newLine1;
   public String newLine2;
   public String objectOrientedDelimiter1;
   public String startMethod;
   public String endMethod;
   public String startParameters;
   public String endParameters;
   public String singleLineComment;
   public String endStatementDelimiter;
   public String startClassList;
   public String endClassList;
   public String bluePrintObject1;
   public String bluePrintObject2;
   public String initializeObjectName;
   public String initializeObjectMethod;
   public String subProgramObject;  
   public String methodNameException;
   public String printMessage;
   public String startLineNum;
   public String startArray;
   public String endArray;
   public String classDataType;
   
   public String returnValueMethod;
   public String nonPrimitiveValue1;
   public String nonPrimitiveValue2;
   public String nonPrimitiveValue3;
   public String nonPrimitiveValue4;
   public String startClassDef; 
   public String endClassDef;
   
   public String dataTypeFullClassName;
   public String dataTypeShortClassName;
   public String dataTypeStringList;
   public String dataTypeNumberList;
   
   
   // # 3.- Operating System #
   // Operating System #
   public String operatingSystem;
   public String specialPropertyOS;
   
   // # 4.- Forensic-Ready Logger Internal Parameters #
   // # Delimiters #
   public String objectOrientedDelimiter2;
   public String textFileExtName;
   public String textFileExtDelimiter;
   public String tabDelimiter1;
   public String whiteSpaceWordsDelimiter1;
   public String whiteSpaceWordsDelimiter2;
   public String dataInputMethod;
   public String valueDelimiter;
   
   // # Regular Expressions #
   public String findString;
   public String findDot;
   public String findWhiteSpaces;
   public String findWord;
   public String startFindWordWhiteSpaces;
   public String endFindWordWhiteSpaces;
   public String alphaNumeric;
   public String replacePattern;
   public String findPLPattern2;
   public String findClassPattern2;
   public String findClassPattern3;
   public String findInterfacePattern2;
   public String findListPattern2;
   public String findArrayListPattern2;

   // # 5.- Aspect Information #
   // # Text Files Information #
   public String textFilePath;
   public String textFilePackageName;
   public String textFileNameExt1;
   public String textFileName1;
   public String textFileType1;
   public String pointCutName1a;
   public String pointCutName1b;
   public String textFileNameExt2;
   public String textFileName2;
   public String textFileType2;
   public String pointCutName2a;
   public String pointCutName2b;
   public String textFileNameExt3;
   public String textFileName3;
   public String textFileType3;
   public String pointCutName3a;

   public String textFileLineSearch;
   public String textFileLineReplace;
   public String textFileLineType;  
   public String textFileLinePropId;
  
   // # Aspect Files Information #
   public String fieldType1;
   public String fieldType2;
   public String fieldType3;
   public String fieldType4;
   public String fieldType5;
   public String confPropertyName1;
   public String startCallMethod;
   public String endCallMethod1;
   public String endCallMethod2;
   public String methodName1;
   public String methodName2;
   public String methodName3;
   public String methodName4;
   public String methodName5;
   public String methodName6;
   public String methodName9;
   public String callMethodDelimiter;
   public String assignValueDelimiter;
   public String specialDelimiter;
   public String guiLibDelimiter;
   
   // # 6.- Sequence Diagram Information #
   // # Sequence Diagram File Information # 
   public String umlSeqDiagTextFileName1;
   public String umlSeqDiagPngFileName1; 
   public String umlSeqDiagTextFileName2;
   
   // # Delimiters #
   public String startSendMessage;
   public String endSendMessage;
   public String invalidSpecialCharacter;
   public String validSpecialCharacter;
   public String colorDelimiter;
   
   // # Plant UML Reserved Words #
   public String startUMLSeqDiagram;
   public String endUMLSeqDiagram;
   public String member1;
   public String member2; 
   public String userName;
   public String regExpSearchWhiteSpace1;
   public String startNote;
   public String endNote;
   public String colorNote;
   public String position;
   public String space;
   public String startDivision;
   public String endDivision;
   
   public String member1Note;
   public String member2Note;
   public String fullMessage;
   
   public String startDiagram;
   public String endDiagram;
   public String connectUser;
   public String startParameter;
   public String endParameter;
   public String bodyParameter;
   public String specificationParameter;
   public String startNote1;
   public String endNote1;
   public String bodyNote1;
   public String endUMLSeqDiagram1;
   
   public String methodSpecification;
   public String memberMethod;
   public String member2Method;
   public String methodName7;
   public String methodName8;
   
   public String umlDirectoryName;
   public String imageDirectoryName;
   public String stepName1;
   public String stepName2;
   public String originalPrefix;
   
   public String annotationFile;
   public String doNotLogAttributes;
   public String logDirectoryName;
   public String annotationFilePrefix;
   public String colorMethod;
   public String colorNote1;
   
   // Method #1
   public FRLConfiguration() //1
   {

	   
   }
   
   // Method #2
   public String changeProperty(String featuresConfigFile, String key, String value)
   {
      Path path = Paths.get(featuresConfigFile);
	  String errorMessage1="", errorMessage2 = "", finalLine="";
	  List<String> fileContent = null;
	  int line;
	   
	  // Read all the lines from the featuresConfigFile into the fileContent variable
	  try 
	  {
	     fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
	  } 
	  catch (IOException e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: While reading the lines from the Features Configuration File. Error Message: " + errorMessage1;
		 return errorMessage2;
	  }

	  // Loop into the lines from the fileContent variable
	  for (line = 0; line < fileContent.size(); line++) 
	  {
	     // Validate if the current line begins with the key value 
	     if(fileContent.get(line).startsWith(key))
	     {
		    finalLine = key + "=" + value;
	        fileContent.set(line, finalLine);
	        break;
	     }
	  }

	  // Write the modified line into the featuresConfig File
	  try 
	  {
	     Files.write(path, fileContent, StandardCharsets.UTF_8);
	  } 
	  catch (IOException e2) 
	  {
         errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: While writing the modified line into the Features Configuration File. Error Message: " + errorMessage1;
		 return errorMessage2;
	  }
	  
      return errorMessage2;
   }
   
   // Method #3
   public String changeProperties(String featConfigFile)
   {
      String errorMessage = "";	   
		 
	  errorMessage = changeProperty(featConfigFile, "databaseConfigFile", this.databaseConfigFile);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
      
	  errorMessage = changeProperty(featConfigFile, "jarFullFilePath", this.jarFullFilePath);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
      
	  errorMessage = changeProperty(featConfigFile, "featuresConfigFile", this.featuresConfigFile);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "projectName", this.projectName); 
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "jarFileName", this.jarFileName);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "projectInputDir", this.projectInputDir);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "projectOutputDir", this.projectOutputDir);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "programmingLanguage", this.programmingLanguage);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "dbms", this.dbms);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "inputMethod", this.inputMethod);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "startProjectMethod", this.startProjectMethod);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "endProjectMethod", this.endProjectMethod);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "connectProjectMethod", this.connectProjectMethod);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "connectProjectMethodReturnValue", this.connectProjectMethodReturnValue);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "newLine1", this.newLine1);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "newLine2", this.newLine2);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
		 
	  errorMessage = changeProperty(featConfigFile, "objectOrientedDelimiter1", this.objectOrientedDelimiter1);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "startMethod", this.startMethod);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "endMethod", this.endMethod);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "startParameters", this.startParameters);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "endParameters", this.endParameters);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "singleLineComment", this.singleLineComment);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "endStatementDelimiter", this.endStatementDelimiter);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "startClassList", this.startClassList);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "endClassList", this.endClassList);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "bluePrintObject1", this.bluePrintObject1);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "bluePrintObject2", this.bluePrintObject2);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "initializeObjectName", this.initializeObjectName);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
		 
	  errorMessage = changeProperty(featConfigFile, "initializeObjectMethod", this.initializeObjectMethod);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "subProgramObject", this.subProgramObject);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "methodNameException", this.methodNameException);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "printMessage", this.printMessage);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "startLineNum", this.startLineNum);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "startArray", this.startArray);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "endArray", this.endArray);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "returnValueMethod", this.returnValueMethod);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "ArrayList", this.nonPrimitiveValue1);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "List", this.nonPrimitiveValue2);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "Array", this.nonPrimitiveValue3);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "Interface", this.nonPrimitiveValue4);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "startClassDef", this.startClassDef);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	  
	  errorMessage = changeProperty(featConfigFile, "endClassDef", this.endClassDef);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
	
	  
	  errorMessage = changeProperty(featConfigFile, "operatingSystem", this.operatingSystem);
	  // Validate if there is any error
	  if(errorMessage.isEmpty() == false)
		     return errorMessage;
		 
      return errorMessage;
   }
   
   // Method #4
   public void printConfigurationParameters()
   {
      String format;
      
      // Printing the values that contains the configuration file
      format = this.tabDelimiter1;
	  System.out.println("For the Project: " + this.projectName ); 
	  System.out.println("These are the Configuration Parameters: "); 
	  System.out.println("File: ");
	  System.out.format(format, this.featuresConfigFile, 1);
	  System.out.println(this.featuresConfigFile);
	
	  // # 1.- Software System General Information #
      System.out.println("");
      System.out.println("**********************************************************");
      System.out.println("Section 1: Software System Information.");
      System.out.println("**********************************************************");
      
      System.out.println("Database Configuration File          :"+this.databaseConfigFile);
      System.out.println("Project Name                         :"+this.projectName);
      System.out.println("Project Jar File                     :"+this.jarFileName);
      System.out.println("Project Jar File Path                :"+this.jarFullFilePath);
      System.out.println("Project Input  Directory             :"+this.projectInputDir);
      System.out.println("Project Output Directory             :"+this.projectOutputDir);
      System.out.println("Programming Language                 :"+this.programmingLanguage);
      System.out.println("DBMS                                 :"+this.dbms);
      System.out.println("Input Method                         :"+this.inputMethod);
      System.out.println("Start Project Method                 :"+this.startProjectMethod);
      System.out.println("End Project Method                   :"+this.endProjectMethod);
      System.out.println("Connect Project Method               :"+this.connectProjectMethod);
      System.out.println("Connect Project Method Return Value  :"+this.connectProjectMethodReturnValue);
		 
	  // # 2.- Programming Language Information #
      System.out.println("");
      System.out.println("**********************************************************");
      System.out.println("Section 2: Programming Language Information.");
      System.out.println("**********************************************************");

	  // # Source Code Information #
      System.out.println("New Line 1                           :"+this.newLine1);
      System.out.println("New Line 2                           :"+this.newLine2);
      System.out.println("Object Oriented Delimiter 1          :"+this.objectOrientedDelimiter1);
      System.out.println("Start Method                         :"+this.startMethod);
      System.out.println("End Method                           :"+this.endMethod);	 
      System.out.println("Start Parameters                     :"+this.startParameters);
      System.out.println("End Parameters                       :"+this.endParameters);
      System.out.println("Single Line Comment                  :"+this.singleLineComment);
      System.out.println("End Statement Delimiter              :"+this.endStatementDelimiter);
      System.out.println("Starts Declaring Class for a List    :"+this.startClassList);
      System.out.println("Ends Declaring Class for a List      :"+this.endClassList);
      System.out.println("Blue Print Object 1                  :"+this.bluePrintObject1);
      System.out.println("Blue Print Object 2                  :"+this.bluePrintObject2);
      System.out.println("Initializes Object Name              :"+this.initializeObjectName);
      System.out.println("Initializes Object Method            :"+this.initializeObjectMethod);
      System.out.println("SubProgram Object                    :"+this.subProgramObject);
      System.out.println("Method Name Exception                :"+this.methodNameException);
      System.out.println("Print Message Instruction            :"+this.printMessage);
      System.out.println("Start Line Number                    :"+this.startLineNum);
      System.out.println("Start Array                          :"+this.startArray);
      System.out.println("End Array                            :"+this.endArray);	
      System.out.println("Class Data Type                      :"+this.classDataType);
      System.out.println("Return Method Value                  :"+this.returnValueMethod);
      System.out.println("Non Primitive Value 1                :"+this.nonPrimitiveValue1);
      System.out.println("Non Primitive Value 2                :"+this.nonPrimitiveValue2);
      System.out.println("Non Primitive Value 3                :"+this.nonPrimitiveValue3);
      System.out.println("Non Primitive Value 4                :"+this.nonPrimitiveValue4);
      System.out.println("Start Class Definition               :"+this.startClassDef);
      System.out.println("End Class Definition                 :"+this.endClassDef);
      System.out.println("Data Type Full Class Name            :"+this.dataTypeFullClassName);
      System.out.println("Data Type Short Class Name           :"+this.dataTypeShortClassName);
      System.out.println("Data Type String List                :"+this.dataTypeStringList);
      System.out.println("Data Type Number List                :"+this.dataTypeNumberList);	 
		 
	  // # 3.- Operating System Information #
	  // # Operating System
      System.out.println("");
      System.out.println("**********************************************************");
	  System.out.println("Section 3: Operating System Information.");
	  System.out.println("**********************************************************");
	  System.out.println("Operating System                     :"+this.operatingSystem);
      System.out.println("Special Property OS                  :"+this.specialPropertyOS);

	  // # 4.- Forensic-Ready Logger Internal Parameters #
	  // # Delimiters #
      System.out.println("");
      System.out.println("**********************************************************");
      System.out.println("Section 4: Forensic-Ready Logger Internal Parameters.");
      System.out.println("**********************************************************");
      System.out.println("Object Oriented Delimiter 2          :"+this.objectOrientedDelimiter2); 
      System.out.println("Text File Extension Name             :"+this.textFileExtName);
      System.out.println("Text File Extension Deliminter       :"+this.textFileExtDelimiter);
      System.out.println("Tab Delimiter1                       :"+this.tabDelimiter1);
      System.out.println("White Space Words Delimiter 1        :"+this.whiteSpaceWordsDelimiter1);
      System.out.println("White Space Words Delimiter 2        :"+this.whiteSpaceWordsDelimiter2);
      System.out.println("Data Input Method                    :"+this.dataInputMethod);
      System.out.println("Value Delimiter                      :"+this.valueDelimiter);
       
	  // # Regular Expressions #
      System.out.println("Finds String                         :"+this.findString);
      System.out.println("Finds Dot                            :"+this.findDot);
      System.out.println("Finds White Spaces                   :"+this.findWhiteSpaces);
      System.out.println("Finds Word                           :"+this.findWord);
      System.out.println("Starts Find Word White Spaces        :"+this.startFindWordWhiteSpaces);
      System.out.println("Ends Find Word White Spaces          :"+this.endFindWordWhiteSpaces); 
      System.out.println("Alpha Numeric                        :"+this.alphaNumeric);
      System.out.println("Replaces Pattern                     :"+this.replacePattern);
      System.out.println("Finds PL Pattern 2                   :"+this.findPLPattern2);
      System.out.println("Finds Class Pattern 2                :"+this.findClassPattern2);
      System.out.println("Finds Class Pattern 3                :"+this.findClassPattern3);
      System.out.println("Finds Interface Pattern 2            :"+this.findInterfacePattern2);
      System.out.println("Finds List Pattern 2                 :"+this.findListPattern2);
      System.out.println("Finds ArrayList Pattern 2            :"+this.findArrayListPattern2);
		 
	  // # 5.- Aspect Information #
	  // # Text Files Information #
      System.out.println("");
      System.out.println("**********************************************************");
      System.out.println("Section 5: Aspect Information.");
      System.out.println("**********************************************************");
      System.out.println("Text FilePath                        :"+this.textFilePath);
      System.out.println("Text File Package Name               :"+this.textFilePackageName);
      System.out.println("Text File Full Name 1                :"+this.textFileNameExt1);
      System.out.println("Text FileName 1                      :"+this.textFileName1);
      System.out.println("Text File Type 1                     :"+this.textFileType1);
      System.out.println("Point Cut Name 1a                    :"+this.pointCutName1a);
      System.out.println("Point Cut Name 1b                    :"+this.pointCutName1b);
		 
      System.out.println("Text File Full Name 2                :"+this.textFileNameExt2);
      System.out.println("Text FileName 2                      :"+this.textFileName2);
      System.out.println("Text File Type 2                     :"+this.textFileType2);
      System.out.println("Point Cut Name 2a                    :"+this.pointCutName2a);
      System.out.println("Point Cut Name 2b                    :"+this.pointCutName2b);
      
      System.out.println("Text File Full Name 3                :"+this.textFileNameExt3);
      System.out.println("Text FileName 3                      :"+this.textFileName3);
      System.out.println("Text File Type 3                     :"+this.textFileType3);
      System.out.println("Point Cut Name 3a                    :"+this.pointCutName3a);
      
      System.out.println("Text File Line Search                :"+this.textFileLineSearch);
      System.out.println("Text File Line Replace               :"+this.textFileLineReplace);
      System.out.println("Text File Line Type                  :"+this.textFileLineType);
      System.out.println("Text File Line Property Identifier   :"+this.textFileLinePropId);
		 
	  //# Aspect Files Information #
      System.out.println("Field Type 1                         :"+this.fieldType1);
      System.out.println("Field Type 2                         :"+this.fieldType2);
      System.out.println("Field Type 3                         :"+this.fieldType3);
      System.out.println("Field Type 4                         :"+this.fieldType4);
      System.out.println("Field Type 5                         :"+this.fieldType5);
		 
      System.out.println("Configuration Property 1             :"+this.confPropertyName1);
      System.out.println("Start Call Method                    :"+this.startCallMethod);
      System.out.println("End Call Method 1                    :"+this.endCallMethod1);
      System.out.println("End Call Method 2                    :"+this.endCallMethod2);
      System.out.println("Method Name 1                        :"+this.methodName1);
      System.out.println("Method Name 2                        :"+this.methodName2);
      System.out.println("Method Name 3                        :"+this.methodName3);
      System.out.println("Method Name 4                        :"+this.methodName4);
      System.out.println("Method Name 5                        :"+this.methodName5);
      System.out.println("Method Name 6                        :"+this.methodName6);
      System.out.println("Method Name 9                        :"+this.methodName9);

      System.out.println("Call Method Delimiter                :"+this.callMethodDelimiter);
      System.out.println("Assign Value Delimiter               :"+this.assignValueDelimiter);
      System.out.println("Special Character Delimiter          :"+this.specialDelimiter);
      System.out.println("Gui Library Delimiter                :"+this.guiLibDelimiter);

	  // # 6.- Sequence Diagram Information #
	  // # Sequence Diagram File Information # 
      System.out.println("");
      System.out.println("**********************************************************");
      System.out.println("Section 6: Sequence Diagram Information.");
      System.out.println("**********************************************************");
      System.out.println("UML Sequence Diagram Text FileName 1 :"+this.umlSeqDiagTextFileName1);
      System.out.println("UML Sequence Diagram Png  FileName 1 :"+this.umlSeqDiagPngFileName1);
      System.out.println("UML Sequence Diagram Text FileName 2 :"+this.umlSeqDiagTextFileName2);
		 
	  // # Delimiters #
      System.out.println("Start Send Message                   :"+this.startSendMessage);
      System.out.println("End Send Message                     :"+this.endSendMessage);
      System.out.println("Invalid Special Character            :"+this.invalidSpecialCharacter);
      System.out.println("Valid Special Character              :"+this.validSpecialCharacter);
      System.out.println("Color Delimiter                      :"+this.colorDelimiter);
		 
	  // # Plant UML Reserved Words #
      System.out.println("Start UML Sequence Diagram           :"+this.startUMLSeqDiagram);
      System.out.println("End UML Sequence Diagram             :"+this.endUMLSeqDiagram);
      System.out.println("Member 1                             :"+this.member1);
      System.out.println("Member 2                             :"+this.member2);
      System.out.println("UserName                             :"+this.userName);
      System.out.println("Reg Expression Search White Spaces 1 :"+this.regExpSearchWhiteSpace1);
      System.out.println("Start Note Delimiter                 :"+this.startNote);
      System.out.println("End Note Delimiter                   :"+this.endNote);
      System.out.println("Color Note                           :"+this.colorNote);
      System.out.println("Position Delimiter                   :"+this.position);
      System.out.println("Space between Lines                  :"+this.space);
      System.out.println("Start Division Delimiter             :"+this.startDivision);
      System.out.println("End Division Delimiter               :"+this.endDivision);
      System.out.println("Note Member 1                        :"+this.member1Note);
      System.out.println("Note Member 2                        :"+this.member2Note);
      System.out.println("Full Message                         :"+this.fullMessage);
      System.out.println("Method Specification                 :"+this.methodSpecification);
      
      System.out.println("Start Diagram                        :"+this.startDiagram);
      System.out.println("End Diagram                          :"+this.endDiagram);
      System.out.println("User Connected                       :"+this.connectUser);
      System.out.println("Start Parameter                      :"+this.startParameter);
      System.out.println("End Parameter                        :"+this.endParameter);
      System.out.println("Body Parameter                       :"+this.bodyParameter);
      System.out.println("Specification Parameter              :"+this.specificationParameter);
      System.out.println("Start Note 1                         :"+this.startNote1);
      System.out.println("End Note 1                           :"+this.endNote1);
      System.out.println("Body Note 1                          :"+this.bodyNote1); 
      System.out.println("End UML Sequence Diagram             :"+this.endUMLSeqDiagram1);
      
      System.out.println("Method Specification                 :"+this.methodSpecification);
      System.out.println("Member Method 1                      :"+this.memberMethod);
      System.out.println("Member Method 2                      :"+this.member2Method);
      System.out.println("Method Name 7                        :"+this.methodName7);
      System.out.println("Method Name 8                        :"+this.methodName8);
      System.out.println("UML Directory Name                   :"+this.umlDirectoryName);
      System.out.println("Image Directory Name                 :"+this.imageDirectoryName);
      System.out.println("Step Name 1                          :"+this.stepName1);
      System.out.println("Step Name 2                          :"+this.stepName2);
      System.out.println("Original Prefix                      :"+this.originalPrefix);
      
	  // # 7.- Generating Logs Information #
	  // # Generating Logs Information # 
      System.out.println("");
      System.out.println("**********************************************************");
      System.out.println("Section 7: Generating Logs Information.");
      System.out.println("**********************************************************");  
      System.out.println("Annotation File                      :"+this.annotationFile);
      System.out.println("Do Not Log this Attributes           :"+this.doNotLogAttributes);
      System.out.println("Log Directory Name                   :"+this.logDirectoryName);
      System.out.println("Annotation File Prefix               :"+this.annotationFilePrefix);
      System.out.println("Color Method                         :"+this.colorMethod);
      System.out.println("Color Note 1                         :"+this.colorNote1);
  
   }
   
   // Method #5
   public String configureParameters(ConfigFileFormEvent ev, String featConfigFile, String databaseConfigFile) //2
   {
      Properties  prop   = new Properties();
	  InputStream input  = null;
      String errorMessage1 = "", errorMessage2 = "";
	  
      // Get the values from the Configuration File Form (GUI)
		 
	  //# 1.- Software System General Information #
	  this.featuresConfigFile       = ev.getfeaturesConfigFile();
	  this.databaseConfigFile       = databaseConfigFile;
				 
	  this.projectName              = ev.getProjectName();
	  this.jarFileName              = ev.getJarFileName();
	  this.projectInputDir          = ev.getProjInputDir();
	  this.projectOutputDir         = ev.getProjOutputDir();
	  this.jarFullFilePath          = this.projectInputDir + File.separator + this.jarFileName;
	  this.programmingLanguage      = ev.getProgLanguage();
	  this.dbms                     = ev.getDbms();
	  this.inputMethod              = ev.getInputMethod();
	  this.startProjectMethod       = ev.getStartProjMethod();
	  this.endProjectMethod         = ev.getEndProjMethod();
	  this.connectProjectMethod     = ev.getConnectProjMethod();
	  this.connectProjectMethodReturnValue = ev.getConnectProjMethodReturnValue();

	  // # 2.- Programming Language Information #
	  this.newLine1                 = ev.getNewLine1();
	  this.newLine2                 = ev.getNewLine2();
	  this.objectOrientedDelimiter1 = ev.getObjectOrientedDelimiter1();
	  this.startMethod              = ev.getStartMethod();
	  this.endMethod                = ev.getEndMethod();
	  this.startParameters          = ev.getStartParameters();
	  this.endParameters            = ev.getEndParameters();
	  this.singleLineComment        = ev.getSingleLineComment();
	  this.endStatementDelimiter    = ev.getEndStatementDelimiter();
	  this.startClassList           = ev.getStartClassList();
	  this.endClassList             = ev.getEndClassList();
	  this.bluePrintObject1         = ev.getBluePrintObject1();
	  this.bluePrintObject2         = ev.getBluePrintObject2();	 
	  this.initializeObjectName     = ev.getInitializeObjectName();
	  this.initializeObjectMethod   = ev.getInitializeObjectMethod();
	  this.subProgramObject         = ev.getSubProgramObject();
	  this.methodNameException      = ev.getMethodNameException();
	  this.printMessage             = ev.getPrintMessage();
	  this.startLineNum             = ev.getStartLineNum();
	  this.startArray               = ev.getStartArray();
	  this.endArray                 = ev.getEndArray();
	  this.classDataType            = ev.getClassDataType();
	  
	  this.returnValueMethod        = ev.getReturnValueMethod();
	  this.nonPrimitiveValue1       = ev.getNonPrimitiveValue1();
	  this.nonPrimitiveValue2       = ev.getNonPrimitiveValue2();
	  this.nonPrimitiveValue3       = ev.getNonPrimitiveValue3();
	  this.nonPrimitiveValue4       = ev.getNonPrimitiveValue4();
	  this.startClassDef            = ev.getStartClassDef();
	  this.endClassDef              = ev.getEndClassDef();
	  
	  // # 3.- Operating System Information #
	  this.operatingSystem          = ev.getOperatingSystem();
		 
	  // Change the property values into the Features Configuration Properties File
	  errorMessage2 = changeProperties(featConfigFile);
	  
	  // Validate if there is any error
	  if(errorMessage2.isEmpty() == false)
	     return errorMessage2;
		 
	  // Load the remaining configuration parameters from the Features Configuration Properties File
	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(featuresConfigFile);

		 // Load the properties file
		 prop.load(input);
		 
		 // # Operating System
         this.specialPropertyOS        = prop.getProperty("specialPropertyOS");
         
         //# 2.- Programming Language Information #
         //# 2.1 Source Code Information#
         this.dataTypeFullClassName    = prop.getProperty("dataTypeFullClassName");
         this.dataTypeShortClassName   = prop.getProperty("dataTypeShortClassName");
         this.dataTypeStringList       = prop.getProperty("dataTypeStringList");
         this.dataTypeNumberList       = prop.getProperty("dataTypeNumberList");
		 
		 // # 4.- Forensic-Ready Logger Internal Parameters #
		 // # Delimiters #
         this.objectOrientedDelimiter2 = prop.getProperty("objectOrientedDelimiter2");
         this.textFileExtName          = prop.getProperty("textFileExtName");
         this.textFileExtDelimiter     = prop.getProperty("textFileExtDelimiter"); 
         this.tabDelimiter1            = prop.getProperty("tabDelimiter1");
         this.whiteSpaceWordsDelimiter1= prop.getProperty("whiteSpaceWordsDelimiter1");
         this.whiteSpaceWordsDelimiter2= prop.getProperty("whiteSpaceWordsDelimiter2");
         this.dataInputMethod          = prop.getProperty("dataInputMethod");
         this.valueDelimiter           = prop.getProperty("valueDelimiter");
         
		 // # Regular Expressions #
         this.findString               = prop.getProperty("findString");
         this.findDot                  = prop.getProperty("findDot");
         this.findWhiteSpaces          = prop.getProperty("findWhiteSpaces");
         this.findWord                 = prop.getProperty("findWord");
         this.startFindWordWhiteSpaces = prop.getProperty("startFindWordWhiteSpaces");
         this.endFindWordWhiteSpaces   = prop.getProperty("endFindWordWhiteSpaces");
         this.alphaNumeric             = prop.getProperty("alphaNumeric");
         this.replacePattern           = prop.getProperty("replacePattern");
         this.findPLPattern2           = prop.getProperty("findPLPattern2");
         this.findClassPattern2        = prop.getProperty("findClassPattern2");
         this.findClassPattern3        = prop.getProperty("findClassPattern3");
         this.findInterfacePattern2    = prop.getProperty("findInterfacePattern2");
         this.findListPattern2         = prop.getProperty("findListPattern2");
         this.findArrayListPattern2    = prop.getProperty("findArrayListPattern2");
         
		 // # 5.- Aspect Information #
		 // # Text Files Information #
         this.textFilePath             = prop.getProperty("textFilePath");
         this.textFilePackageName      = prop.getProperty("textFilePackageName");
         this.textFileNameExt1         = prop.getProperty("textFileNameExt1");
         this.textFileName1            = prop.getProperty("textFileName1");
         this.textFileType1            = prop.getProperty("textFileType1");
         this.pointCutName1a           = prop.getProperty("pointCutName1a");
         this.pointCutName1b           = prop.getProperty("pointCutName1b"); 
         this.textFileNameExt2         = prop.getProperty("textFileNameExt2");
         this.textFileName2            = prop.getProperty("textFileName2");
         this.textFileType2            = prop.getProperty("textFileType2");
         this.pointCutName2a           = prop.getProperty("pointCutName2a");
         this.pointCutName2b           = prop.getProperty("pointCutName2b");
         
         this.textFileNameExt3         = prop.getProperty("textFileNameExt3");
         this.textFileName3            = prop.getProperty("textFileName2");
         this.textFileType3            = prop.getProperty("textFileType3");
         this.pointCutName3a           = prop.getProperty("pointCutName3a");
         
         
         this.textFileLineSearch       = prop.getProperty("textFileLineSearch");
         this.textFileLineReplace      = prop.getProperty("textFileLineReplace");
         this.textFileLineType         = prop.getProperty("textFileLineType");
         this.textFileLinePropId       = prop.getProperty("textFileLinePropId");	 

         // # Aspect Files Information #
         this.fieldType1               = prop.getProperty("fieldType1");
         this.fieldType2               = prop.getProperty("fieldType2");
         this.fieldType3               = prop.getProperty("fieldType3");
         this.fieldType4               = prop.getProperty("fieldType4");
         this.fieldType5               = prop.getProperty("fieldType5");
         this.confPropertyName1        = prop.getProperty("confPropertyName1");
         this.startCallMethod          = prop.getProperty("startCallMethod");
         this.endCallMethod1           = prop.getProperty("endCallMethod1");
         this.endCallMethod2           = prop.getProperty("endCallMethod2");
         this.methodName1              = prop.getProperty("methodName1");
         this.methodName2              = prop.getProperty("methodName2");
         this.methodName3              = prop.getProperty("methodName3");
         this.methodName4              = prop.getProperty("methodName4");
         this.methodName5              = prop.getProperty("methodName5");
         this.methodName6              = prop.getProperty("methodName6");
         this.methodName9              = prop.getProperty("methodName9");
         this.callMethodDelimiter      = prop.getProperty("callMethodDelimiter");
         this.assignValueDelimiter     = prop.getProperty("assignValueDelimiter");
         this.specialDelimiter         = prop.getProperty("specialDelimiter");
         this.guiLibDelimiter          = prop.getProperty("guiLibDelimiter");
		 
		 // # 6.- Sequence Diagram Information #
		 // # Sequence Diagram File Information # 
         this.umlSeqDiagTextFileName1  = prop.getProperty("umlSeqDiagTextFileName1");
         this.umlSeqDiagPngFileName1   = prop.getProperty("umlSeqDiagPngFileName1");
         this.umlSeqDiagTextFileName2  = prop.getProperty("umlSeqDiagTextFileName2");
		 
         this.startSendMessage         = prop.getProperty("startSendMessage");
         this.endSendMessage           = prop.getProperty("endSendMessage");
         this.invalidSpecialCharacter  = prop.getProperty("invalidSpecialCharacter");
         this.validSpecialCharacter    = prop.getProperty("validSpecialCharacter");
         this.colorDelimiter           = prop.getProperty("colorDelimiter");	 
		 
         this.startUMLSeqDiagram       = prop.getProperty("startUMLSeqDiagram");
         this.endUMLSeqDiagram         = prop.getProperty("endUMLSeqDiagram");
         this.member1                  = prop.getProperty("member1");
         this.member2                  = prop.getProperty("member2");
         this.userName                 = prop.getProperty("userName");
         this.regExpSearchWhiteSpace1  = prop.getProperty("regExpSearchWhiteSpace1"); 
         this.startNote                = prop.getProperty("startNote");
         this.endNote                  = prop.getProperty("endNote");
         this.colorNote                = prop.getProperty("colorNote");
         this.position                 = prop.getProperty("position");
         this.space                    = prop.getProperty("space");
         this.startDivision            = prop.getProperty("startDivision");
         this.endDivision              = prop.getProperty("endDivision");
         this.member1Note              = prop.getProperty("member1Note");
         this.member2Note              = prop.getProperty("member2Note");
         this.fullMessage              = prop.getProperty("fullMessage");
         this.methodSpecification      = prop.getProperty("methodSpecification");
         
         this.startDiagram             = prop.getProperty("startDiagram");
         this.endDiagram               = prop.getProperty("endDiagram");
         this.connectUser              = prop.getProperty("connectUser");
         this.startParameter           = prop.getProperty("startParameter");
         this.endParameter             = prop.getProperty("endParameter");
         this.bodyParameter            = prop.getProperty("bodyParameter");
         this.specificationParameter   = prop.getProperty("specificationParameter");
         this.startNote1               = prop.getProperty("startNote1");
         this.endNote1                 = prop.getProperty("endNote1");
         this.bodyNote1                = prop.getProperty("bodyNote1");
         this.endUMLSeqDiagram1        = prop.getProperty("endUMLSeqDiagram1");
         
         this.methodSpecification      = prop.getProperty("methodSpecification");
         this.memberMethod             = prop.getProperty("memberMethod");
         this.member2Method            = prop.getProperty("member2Method");
         this.methodName7              = prop.getProperty("methodName7");
         this.methodName8              = prop.getProperty("methodName8");
         this.umlDirectoryName         = prop.getProperty("umlDirectoryName");
         this.imageDirectoryName       = prop.getProperty("imageDirectoryName");
         this.stepName1                = prop.getProperty("stepName1");
         this.stepName2                = prop.getProperty("stepName2");
         this.originalPrefix           = prop.getProperty("originalPrefix");
         
   	     // # 7.- Generating Logs Information #
   	     // # Generating Logs Information # 
         this.annotationFile           = prop.getProperty("annotationFile");
         this.doNotLogAttributes       = prop.getProperty("doNotLogAttributes");
        
         this.logDirectoryName         = prop.getProperty("logDirectoryName");
         this.annotationFilePrefix     = prop.getProperty("annotationFilePrefix");
         this.colorMethod              = prop.getProperty("colorMethod");
         this.colorNote1               = prop.getProperty("colorNote1");
		  
		 // Display a message that contain the value of the configuration parameter
		 printConfigurationParameters();
		 
	   } 
	   catch (IOException ex) 
	   {
	      errorMessage1 = ex.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while loading the parameters into the Features Configuration File. Error Message: " + errorMessage1;
		  return errorMessage2;
	   } 
	   finally 
	   {
	      if (input != null) 
		  {
		     try 
		     {
		        input.close();
			  } 
			  catch (IOException e) 
			  {
			     errorMessage1 = e.getMessage();
				 errorMessage2 = "Error XXXX: Occurred while closing the Configuration File. Error Message: " + errorMessage1;
				 return errorMessage2;
		     }
	      }
	   }

      return errorMessage2;	  
   }
   
   // Method #5
   public String loadParameters(String featuresConfigFile, String databaseConfigFile) //2
   {
      Properties  prop   = new Properties();
	  InputStream input  = null;
      String errorMessage1 = "", errorMessage2 = "";
	  		 
	  // Load the remaining configuration parameters from the Features Configuration Properties File
	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(featuresConfigFile);

		 // Load the properties file
		 prop.load(input);
		 
		 this.featuresConfigFile       = prop.getProperty("featuresConfigFile");
		 this.databaseConfigFile       = prop.getProperty("databaseConfigFile");
		 this.projectName              = prop.getProperty("projectName");
		 this.jarFileName              = prop.getProperty("jarFileName");
		 this.projectInputDir          = prop.getProperty("projectInputDir");
		 this.projectOutputDir         = prop.getProperty("projectOutputDir");
		 this.jarFullFilePath          = prop.getProperty("jarFullFilePath");
		 this.programmingLanguage      = prop.getProperty("programmingLanguage");
		 this.dbms                     = prop.getProperty("dbms");
		 this.inputMethod              = prop.getProperty("inputMethod");
		 this.startProjectMethod       = prop.getProperty("startProjectMethod");
		 this.endProjectMethod         = prop.getProperty("endProjectMethod");
		 this.connectProjectMethod     = prop.getProperty("connectProjectMethod");
		 this.connectProjectMethodReturnValue = prop.getProperty("connectProjectMethodReturnValue");

		 // # 2.- Programming Language Information #
		 this.newLine1                 = prop.getProperty("newLine1");
		 this.newLine2                 = prop.getProperty("newLine2");
		 this.objectOrientedDelimiter1 = prop.getProperty("objectOrientedDelimiter1");
		 this.startMethod              = prop.getProperty("startMethod");
		 this.endMethod                = prop.getProperty("endMethod");
		 this.startParameters          = prop.getProperty("startParameters");
		 this.endParameters            = prop.getProperty("endParameters");
		 this.singleLineComment        = prop.getProperty("singleLineComment");
		 this.endStatementDelimiter    = prop.getProperty("endStatementDelimiter");
		 this.startClassList           = prop.getProperty("startClassList");
		 this.endClassList             = prop.getProperty("endClassList");
		 this.bluePrintObject1         = prop.getProperty("bluePrintObject1");
		 this.bluePrintObject2         = prop.getProperty("bluePrintObject2");	 
		 this.initializeObjectName     = prop.getProperty("initializeObjectName");
		 this.initializeObjectMethod   = prop.getProperty("initializeObjectMethod");
		 this.subProgramObject         = prop.getProperty("subProgramObject");
		 this.methodNameException      = prop.getProperty("methodNameException");
		 this.printMessage             = prop.getProperty("printMessage");
		 this.startLineNum             = prop.getProperty("startLineNum");
		 this.startArray               = prop.getProperty("startArray");
		 this.endArray                 = prop.getProperty("endArray");
		 this.classDataType            = prop.getProperty("classDataType");
		 
		 this.returnValueMethod        = prop.getProperty("returnValueMethod");
		 this.nonPrimitiveValue1       = prop.getProperty("nonPrimitiveValue1");
		 this.nonPrimitiveValue2       = prop.getProperty("nonPrimitiveValue2");
		 this.nonPrimitiveValue3       = prop.getProperty("nonPrimitiveValue3");
		 this.nonPrimitiveValue4       = prop.getProperty("nonPrimitiveValue4");
		 this.startClassDef            = prop.getProperty("startClassDef");
		 this.endClassDef              = prop.getProperty("endClassDef");
		 
         this.dataTypeFullClassName    = prop.getProperty("dataTypeFullClassName");
         this.dataTypeShortClassName   = prop.getProperty("dataTypeShortClassName");
         this.dataTypeStringList       = prop.getProperty("dataTypeStringList");
         this.dataTypeNumberList       = prop.getProperty("dataTypeNumberList");
         			 
		 // # 3.- Operating System Information #
		 this.operatingSystem          = prop.getProperty("operatingSystem");
         this.specialPropertyOS        = prop.getProperty("specialPropertyOS");
		 
		 // # 4.- Forensic-Ready Logger Internal Parameters #
		 // # Delimiters #
         this.objectOrientedDelimiter2 = prop.getProperty("objectOrientedDelimiter2");
         this.textFileExtName          = prop.getProperty("textFileExtName");
         this.textFileExtDelimiter     = prop.getProperty("textFileExtDelimiter"); 
         this.tabDelimiter1            = prop.getProperty("tabDelimiter1");
         this.whiteSpaceWordsDelimiter1= prop.getProperty("whiteSpaceWordsDelimiter1");
         this.whiteSpaceWordsDelimiter2= prop.getProperty("whiteSpaceWordsDelimiter2");
         this.dataInputMethod          = prop.getProperty("dataInputMethod");
         this.valueDelimiter           = prop.getProperty("valueDelimiter");
         
		 // # Regular Expressions #
         this.findString               = prop.getProperty("findString");
         this.findDot                  = prop.getProperty("findDot");
         this.findWhiteSpaces          = prop.getProperty("findWhiteSpaces");
         this.findWord                 = prop.getProperty("findWord");
         this.startFindWordWhiteSpaces = prop.getProperty("startFindWordWhiteSpaces");
         this.endFindWordWhiteSpaces   = prop.getProperty("endFindWordWhiteSpaces");
         this.alphaNumeric             = prop.getProperty("alphaNumeric");
         this.replacePattern           = prop.getProperty("replacePattern");
         this.findPLPattern2           = prop.getProperty("findPLPattern2");
         this.findClassPattern2        = prop.getProperty("findClassPattern2");
         this.findClassPattern3        = prop.getProperty("findClassPattern3");
         this.findInterfacePattern2    = prop.getProperty("findInterfacePattern2");
         this.findListPattern2         = prop.getProperty("findListPattern2");
         this.findArrayListPattern2    = prop.getProperty("findArrayListPattern2");
		 
		 // # 5.- Aspect Information #
		 // # Text Files Information #
         this.textFilePath             = prop.getProperty("textFilePath");
         this.textFilePackageName      = prop.getProperty("textFilePackageName");
         this.textFileNameExt1         = prop.getProperty("textFileNameExt1");
         this.textFileName1            = prop.getProperty("textFileName1");
         this.textFileType1            = prop.getProperty("textFileType1");
         this.pointCutName1a           = prop.getProperty("pointCutName1a");
         this.pointCutName1b           = prop.getProperty("pointCutName1b"); 
         this.textFileNameExt2         = prop.getProperty("textFileNameExt2");
         this.textFileName2            = prop.getProperty("textFileName2");
         this.textFileType2            = prop.getProperty("textFileType2");
         this.pointCutName2a           = prop.getProperty("pointCutName2a");
         this.pointCutName2b           = prop.getProperty("pointCutName2b");
         this.textFileNameExt3         = prop.getProperty("textFileNameExt3");
         this.textFileName3            = prop.getProperty("textFileName2");
         this.textFileType3            = prop.getProperty("textFileType3");
         this.pointCutName3a           = prop.getProperty("pointCutName3a");
         
         this.textFileLineSearch       = prop.getProperty("textFileLineSearch");
         this.textFileLineReplace      = prop.getProperty("textFileLineReplace");
         this.textFileLineType         = prop.getProperty("textFileLineType");
         this.textFileLinePropId       = prop.getProperty("textFileLinePropId");	 

         // # Aspect Files Information #
         this.fieldType1               = prop.getProperty("fieldType1");
         this.fieldType2               = prop.getProperty("fieldType2");
         this.fieldType3               = prop.getProperty("fieldType3");
         this.fieldType4               = prop.getProperty("fieldType4");
         this.fieldType5               = prop.getProperty("fieldType5");
         this.confPropertyName1        = prop.getProperty("confPropertyName1");
         this.startCallMethod          = prop.getProperty("startCallMethod");
         this.endCallMethod1           = prop.getProperty("endCallMethod1");
         this.endCallMethod2           = prop.getProperty("endCallMethod2");
         this.methodName1              = prop.getProperty("methodName1");
         this.methodName2              = prop.getProperty("methodName2");
         this.methodName3              = prop.getProperty("methodName3");
         this.methodName4              = prop.getProperty("methodName4");
         this.methodName5              = prop.getProperty("methodName5");
         this.methodName6              = prop.getProperty("methodName6");	
         this.methodName9              = prop.getProperty("methodName9");	
         this.callMethodDelimiter      = prop.getProperty("callMethodDelimiter");
         this.assignValueDelimiter     = prop.getProperty("assignValueDelimiter");
         this.specialDelimiter         = prop.getProperty("specialDelimiter");
         this.guiLibDelimiter          = prop.getProperty("guiLibDelimiter");
		 
		 // # 6.- Sequence Diagram Information #
		 // # Sequence Diagram File Information # 
         this.umlSeqDiagTextFileName1  = prop.getProperty("umlSeqDiagTextFileName1");
         this.umlSeqDiagPngFileName1   = prop.getProperty("umlSeqDiagPngFileName1");
         this.umlSeqDiagTextFileName2  = prop.getProperty("umlSeqDiagTextFileName2");
		 
         this.startSendMessage         = prop.getProperty("startSendMessage");
         this.endSendMessage           = prop.getProperty("endSendMessage");
         this.invalidSpecialCharacter  = prop.getProperty("invalidSpecialCharacter");
         this.validSpecialCharacter    = prop.getProperty("validSpecialCharacter");
         this.colorDelimiter           = prop.getProperty("colorDelimiter");	 
		 
         this.startUMLSeqDiagram       = prop.getProperty("startUMLSeqDiagram");
         this.endUMLSeqDiagram         = prop.getProperty("endUMLSeqDiagram");
         this.member1                  = prop.getProperty("member1");
         this.member2                  = prop.getProperty("member2");
         this.userName                 = prop.getProperty("userName");
         this.regExpSearchWhiteSpace1  = prop.getProperty("regExpSearchWhiteSpace1"); 
         this.startNote                = prop.getProperty("startNote");
         this.endNote                  = prop.getProperty("endNote");
         this.colorNote                = prop.getProperty("colorNote");
         this.position                 = prop.getProperty("position");
         this.space                    = prop.getProperty("space");
         this.startDivision            = prop.getProperty("startDivision");
         this.endDivision              = prop.getProperty("endDivision");
         this.member1Note              = prop.getProperty("member1Note");
         this.member2Note              = prop.getProperty("member2Note");
         this.fullMessage              = prop.getProperty("fullMessage");
         this.methodSpecification      = prop.getProperty("methodSpecification");
         
         this.startDiagram             = prop.getProperty("startDiagram");
         this.endDiagram               = prop.getProperty("endDiagram");
         this.connectUser              = prop.getProperty("connectUser");
         this.startParameter           = prop.getProperty("startParameter");
         this.endParameter             = prop.getProperty("endParameter");
         this.bodyParameter            = prop.getProperty("bodyParameter");
         this.specificationParameter   = prop.getProperty("specificationParameter");
         this.startNote1               = prop.getProperty("startNote1");
         this.endNote1                 = prop.getProperty("endNote1");
         this.bodyNote1                = prop.getProperty("bodyNote1");
         this.endUMLSeqDiagram1        = prop.getProperty("endUMLSeqDiagram1");
         
         this.methodSpecification      = prop.getProperty("methodSpecification");
         this.memberMethod             = prop.getProperty("memberMethod");
         this.member2Method            = prop.getProperty("member2Method");
         this.methodName7              = prop.getProperty("methodName7");
         this.methodName8              = prop.getProperty("methodName8");
         this.umlDirectoryName         = prop.getProperty("umlDirectoryName");
         this.imageDirectoryName       = prop.getProperty("imageDirectoryName");
         this.stepName1                = prop.getProperty("stepName1");
         this.stepName2                = prop.getProperty("stepName2");
         this.originalPrefix           = prop.getProperty("originalPrefix");
         
   	     // # 7.- Generating Logs Information #
   	     // # Generating Logs Information # 
         this.annotationFile           = prop.getProperty("annotationFile");
         this.doNotLogAttributes       = prop.getProperty("doNotLogAttributes");
         
         this.logDirectoryName         = prop.getProperty("logDirectoryName");
         this.annotationFilePrefix     = prop.getProperty("annotationFilePrefix");
         this.colorMethod              = prop.getProperty("colorMethod");
         this.colorNote1               = prop.getProperty("colorNote1");
         
	   } 
	   catch (IOException ex) 
	   {
	      errorMessage1 = ex.getMessage();
		  errorMessage2 = "Error XXXX: Occurred while loading the parameters into the Features Configuration File. Error Message: " + errorMessage1;
		  return errorMessage2;
	   } 
	   finally 
	   {
	      if (input != null) 
		  {
		     try 
		     {
		        input.close();
			  } 
			  catch (IOException e) 
			  {
			     errorMessage1 = e.getMessage();
				 errorMessage2 = "Error XXXX: Occurred while closing the Configuration File. Error Message: " + errorMessage1;
				 return errorMessage2;
		     }
	      }
	   }

      return errorMessage2;	  
   }

	      
}
