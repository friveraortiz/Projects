package frl.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FRLConfiguration 
{
   // # 1.- Software System General Information #	
   public String propertiesFilePath = "/Users/fanny/eclipse-workspace/ForensicReadyLogger/resources/frlconfig.properties";
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
   
   // # 2.- Programming Language Information #
   // # Operating System Information #
   public String elementsFileName;
   
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
   
   //# 3.- Forensic-Ready Logger Database Information
   public String database;
   
   // # 4.- Forensic-Ready Logger Internal Parameters #
   // # Delimiters #
   public String objectOrientedDelimiter2;
   public String textFileExtName;
   public String textFileExtDelimiter;
   public String tabDelimiter1;
   public String whiteSpaceWordsDelimiter1;
   public String whiteSpaceWordsDelimiter2;
   public String dataInputMethod;
   
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
   public String callMethodDelimiter;
   public String assignValueDelimiter;
   public String specialDelimiter;
   public String guiLibDelimiter;
   
   // # 6.- Sequence Diagram Information #
   // # Sequence Diagram File Information # 
   public String umlSeqDiagTextFileName1;
   public String umlSeqDiagPngFileName1; 
   
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
   
   public FRLConfiguration() //1
   {

	   
   }

   public boolean frlConfigureParameters() //2
   {
      Properties  prop   = new Properties();
	  InputStream input  = null;
	  boolean     result = true;
	  String      errorMessage = null, format;
		  
	  try 
	  {
	     // Specify the path of the configuration file
		 input = new FileInputStream(propertiesFilePath);

		 // Load the properties file
	     prop.load(input);

		 // Get the values from the configuration file
		 
	     //# 1.- Software System General Information #
		 this.projectName              = prop.getProperty("projectName");
		 this.jarFileName              = prop.getProperty("jarFileName");
		 this.jarFullFilePath          = this.projectInputDir + this.jarFileName;
		 this.projectInputDir          = prop.getProperty("projectInputDir");
		 this.projectOutputDir         = prop.getProperty("projectOutputDir");
		 this.programmingLanguage      = prop.getProperty("programmingLanguage");
		 this.dbms                     = prop.getProperty("dbms");
		 this.inputMethod              = prop.getProperty("inputMethod");
		 this.startProjectMethod       = prop.getProperty("startProjectMethod");
		 this.endProjectMethod         = prop.getProperty("endProjectMethod");		 
		 this.connectProjectMethod     = prop.getProperty("connectProjectMethod");
		 
		 // # 2.- Programming Language Information #
		 // # Operating System Information #
		 this.elementsFileName         = prop.getProperty("elementsFileName");
		 
		 // # Source Code Information#
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
		 
		 // # 3.- Forensic-Ready Logger Database Information
		 this.database                 = prop.getProperty("database");
	
		 // # 4.- Forensic-Ready Logger Internal Parameters #
		 // # Delimiters #
		 this.objectOrientedDelimiter2 = prop.getProperty("objectOrientedDelimiter2");
		 this.textFileExtName          = prop.getProperty("textFileExtName");
		 this.textFileExtDelimiter     = prop.getProperty("textFileExtDelimiter"); 
		 this.tabDelimiter1            = prop.getProperty("tabDelimiter1");
		 this.whiteSpaceWordsDelimiter1= prop.getProperty("whiteSpaceWordsDelimiter1");
		 this.whiteSpaceWordsDelimiter2= prop.getProperty("whiteSpaceWordsDelimiter2");
		 this.dataInputMethod          = prop.getProperty("dataInputMethod");
		 
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
		 this.callMethodDelimiter      = prop.getProperty("callMethodDelimiter");
		 this.assignValueDelimiter     = prop.getProperty("assignValueDelimiter");
		 this.specialDelimiter         = prop.getProperty("specialDelimiter");
		 this.guiLibDelimiter          = prop.getProperty("guiLibDelimiter");		 

		 // # 6.- Sequence Diagram Information #
		 // # Sequence Diagram File Information # 
		 this.umlSeqDiagTextFileName1  = prop.getProperty("umlSeqDiagTextFileName1");
		 this.umlSeqDiagPngFileName1   = prop.getProperty("umlSeqDiagPngFileName1");
		 
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
		 
		 // Printing the values that contains the configuration file
		 format = this.tabDelimiter1;
		 System.out.println("");
		 System.out.println("For the Project: "+this.projectName ); 
		 System.out.println("Loading the Configuration Parameters ..."); 
		 System.out.println("File: ");
		 System.out.format(format, this.propertiesFilePath, 1);
		 System.out.println(this.propertiesFilePath);
		 
		 // # 1.- Software System General Information #
		 System.out.println("");
		 System.out.println("**********************************************************");
		 System.out.println("Section 1: Software System General Information.");
		 System.out.println("**********************************************************");
		 System.out.println("Project Name                        :"+this.projectName);
		 System.out.println("Project Jar File                    :"+this.jarFileName);
		 System.out.println("Project Input  Directory            :"+this.projectInputDir);
		 System.out.println("Project Output Directory            :"+this.projectOutputDir);
		 System.out.println("Programming Language                :"+this.programmingLanguage);
		 System.out.println("Dbms                                :"+this.dbms);
		 System.out.println("Input Method                        :"+this.inputMethod);
		 System.out.println("Start Project Method                :"+this.startProjectMethod);
		 System.out.println("End Project Method                  :"+this.endProjectMethod);
		 System.out.println("Connect Project Method              :"+this.connectProjectMethod);
		 
		 // # 2.- Programming Language Information #
		 // # Operating System Information #
		 System.out.println("");
		 System.out.println("**********************************************************");
		 System.out.println("Section 2: Programming Language Information.");
		 System.out.println("**********************************************************");
		 System.out.println("Elements File Name                  :"+this.elementsFileName);

		 // # Source Code Information#
		 System.out.println("New Line 1                          :"+this.newLine1);
		 System.out.println("New Line 2                          :"+this.newLine2);
		 System.out.println("Object Oriented Delimiter 1         :"+this.objectOrientedDelimiter1);
		 System.out.println("Start Method                        :"+this.startMethod);
		 System.out.println("End Method                          :"+this.endMethod);	 
		 System.out.println("Start Parameters                    :"+this.startParameters);
		 System.out.println("End Parameters                      :"+this.endParameters);
		 System.out.println("Single Line Comment                 :"+this.singleLineComment);
		 System.out.println("End Statement Delimiter             :"+this.endStatementDelimiter);
		 System.out.println("Starts Declaring Class for a List   :"+this.startClassList);
		 System.out.println("Ends Declaring Class for a List     :"+this.endClassList);
		 System.out.println("Blue Print Object 1                 :"+this.bluePrintObject1);
		 System.out.println("Blue Print Object 2                 :"+this.bluePrintObject2);
		 System.out.println("Initializes Object Name             :"+this.initializeObjectName);
		 System.out.println("Initializes Object Method           :"+this.initializeObjectMethod);
		 System.out.println("SubProgram Object                   :"+this.subProgramObject);
		 System.out.println("Method Name Exception               :"+this.methodNameException);
		 System.out.println("Print Message Instruction           :"+this.printMessage);
		 System.out.println("Start Line Number                   :"+this.startLineNum);
		 System.out.println("Start Array                         :"+this.startArray);
		 System.out.println("End Array                           :"+this.endArray);

		 // # 3.- Forensic-Ready Logger Database Information
		 System.out.println("");
		 System.out.println("**********************************************************");
		 System.out.println("Section 3: Forensic-Ready Logger Database Information.");
		 System.out.println("**********************************************************");
		 System.out.println("Database                            :"+this.database);

		 // # 4.- Forensic-Ready Logger Internal Parameters #
		 // # Delimiters #
		 System.out.println("");
		 System.out.println("**********************************************************");
		 System.out.println("Section 4: Forensic-Ready Logger Database Information.");
		 System.out.println("**********************************************************");
		 System.out.println("Object Oriented Delimiter 2         :"+this.objectOrientedDelimiter2); 
		 System.out.println("Text File Extension Name            :"+this.textFileExtName);
		 System.out.println("Text File Extension Deliminter      :"+this.textFileExtDelimiter);
		 System.out.println("Tab Delimiter1                      :"+this.tabDelimiter1);
		 System.out.println("White Space Words Delimiter 1       :"+this.whiteSpaceWordsDelimiter1);
		 System.out.println("White Space Words Delimiter 2       :"+this.whiteSpaceWordsDelimiter2);
		 System.out.println("Data Input Method                   :"+this.dataInputMethod);
         
		 // # Regular Expressions #
		 System.out.println("Finds String                        :"+this.findString);
		 System.out.println("Finds Dot                           :"+this.findDot);
		 System.out.println("Finds White Spaces                  :"+this.findWhiteSpaces);
		 System.out.println("Finds Word                          :"+this.findWord);
		 System.out.println("Starts Find Word White Spaces       :"+this.startFindWordWhiteSpaces);
		 System.out.println("Ends Find Word White Spaces         :"+this.endFindWordWhiteSpaces); 
		 System.out.println("Alpha Numeric                       :"+this.alphaNumeric);
		 System.out.println("Replaces Pattern                    :"+this.replacePattern);
		 System.out.println("Finds PL Pattern 2                  :"+this.findPLPattern2);
		 System.out.println("Finds Class Pattern 2               :"+this.findClassPattern2);
		 System.out.println("Finds Class Pattern 3               :"+this.findClassPattern3);
		 System.out.println("Finds Interface Pattern 2           :"+this.findInterfacePattern2);
		 System.out.println("Finds List Pattern 2                :"+this.findListPattern2);
		 System.out.println("Finds ArrayList Pattern 2           :"+this.findArrayListPattern2);
		 
		 // # 5.- Aspect Information #
	     // # Text Files Information #
		 System.out.println("");
		 System.out.println("**********************************************************");
		 System.out.println("Section 5: Aspect Information.");
		 System.out.println("**********************************************************");
		 System.out.println("Text FilePath                       :"+this.textFilePath);
		 System.out.println("Text File Package Name              :"+this.textFilePackageName);
		 System.out.println("Text File Full Name 1               :"+this.textFileNameExt1);
		 System.out.println("Text FileName 1                     :"+this.textFileName1);
		 System.out.println("Text File Type 1                    :"+this.textFileType1);
		 System.out.println("Point Cut Name 1a                   :"+this.pointCutName1a);
		 System.out.println("Point Cut Name 1b                   :"+this.pointCutName1b);
		 
		 System.out.println("Text File Full Name 2               :"+this.textFileNameExt2);
		 System.out.println("Text FileName 2                     :"+this.textFileName2);
		 System.out.println("Text File Type 2                    :"+this.textFileType2);
		 System.out.println("Point Cut Name 2a                   :"+this.pointCutName2a);
		 System.out.println("Point Cut Name 2b                   :"+this.pointCutName2b);
		 System.out.println("Text File Line Search               :"+this.textFileLineSearch);
		 System.out.println("Text File Line Replace              :"+this.textFileLineReplace);
		 System.out.println("Text File Line Type                 :"+this.textFileLineType);
		 System.out.println("Text File Line Property Identifier  :"+this.textFileLinePropId);
		 
		 //# Aspect Files Information #
		 System.out.println("Field Type 1                        :"+this.fieldType1);
		 System.out.println("Field Type 2                        :"+this.fieldType2);
		 System.out.println("Field Type 3                        :"+this.fieldType3);
		 System.out.println("Field Type 4                        :"+this.fieldType4);
		 System.out.println("Field Type 5                        :"+this.fieldType5);
		 
		 System.out.println("Configuration Property 1            :"+this.confPropertyName1);
         System.out.println("Start Call Method                   :"+this.startCallMethod);
         System.out.println("End Call Method 1                   :"+this.endCallMethod1);
         System.out.println("End Call Method 2                   :"+this.endCallMethod2);
		 System.out.println("Method Name 1                       :"+this.methodName1);
		 System.out.println("Method Name 2                       :"+this.methodName2);
		 System.out.println("Method Name 3                       :"+this.methodName3);
		 System.out.println("Method Name 4                       :"+this.methodName4);
		 System.out.println("Method Name 5                       :"+this.methodName5);
		 System.out.println("Method Name 6                       :"+this.methodName6);
		 System.out.println("Call Method Delimiter               :"+this.callMethodDelimiter);
		 System.out.println("Assign Value Delimiter              :"+this.assignValueDelimiter);
		 System.out.println("Special Character Delimiter         :"+this.specialDelimiter);
		 System.out.println("Gui Library Delimiter               :"+this.guiLibDelimiter);

		 // # 6.- Sequence Diagram Information #
		 // # Sequence Diagram File Information # 
		 System.out.println("");
		 System.out.println("**********************************************************");
		 System.out.println("Section 6: Sequence Diagram Information.");
		 System.out.println("**********************************************************");
		 System.out.println("UML Sequence Diagram Text FileName 1:"+this.umlSeqDiagTextFileName1);
		 System.out.println("UML Sequence Diagram Png  FileName 1:"+this.umlSeqDiagPngFileName1);
		 
		 // # Delimiters #
		 System.out.println("Start Send Message                  :"+this.startSendMessage);
		 System.out.println("End Send Message                    :"+this.endSendMessage);
		 System.out.println("Invalid Special Character           :"+this.invalidSpecialCharacter);
		 System.out.println("Valid Special Character             :"+this.validSpecialCharacter);
		 System.out.println("Color Delimiter                     :"+this.colorDelimiter);
		 
		 // # Plant UML Reserved Words #
		 System.out.println("Start UML Sequence Diagram          :"+this.startUMLSeqDiagram);
		 System.out.println("End UML Sequence Diagram            :"+this.endUMLSeqDiagram);
		 System.out.println("Member 1                            :"+this.member1);
		 System.out.println("Member 2                            :"+this.member2);
		 System.out.println("UserName                            :"+this.userName);
		 System.out.println("Reg Expression Search White Spaces 1:"+this.regExpSearchWhiteSpace1);
		 System.out.println("Start Note Delimiter                 :"+this.startNote);
		 System.out.println("End Note Delimiter                   :"+this.endNote);
		 System.out.println("Color Note                           :"+this.colorNote);
		 System.out.println("Position Delimiter                   :"+this.position);
		 System.out.println("Space between Lines                  :"+this.space);
		 System.out.println("Start Division Delimiter             :"+this.startDivision);
		 System.out.println("End Division Delimiter               :"+this.endDivision);

   } 
   catch (IOException ex) 
   {
      errorMessage = ex.getMessage();
	  System.out.println("Error 1121: Occurred while loading the Configuration File. Error Message: " + errorMessage);
	  result = false;
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
		    errorMessage = e.getMessage();
		    System.out.println("Error 1122: Occurred while closing the Configuration File. Error Message: " + errorMessage);
		    result = false;
		 }
      }
   }
      return result;	  
   }
	      
}
