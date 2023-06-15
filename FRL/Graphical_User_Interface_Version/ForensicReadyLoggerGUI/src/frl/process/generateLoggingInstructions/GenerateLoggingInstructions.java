package frl.process.generateLoggingInstructions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import frl.controller.generateLoggingInstructions.AOPFinalFileController;
import frl.gui.generateLoggingInstructions.AOPFinalFileFormEvent;
import frl.model.configuration.ClassMethod;
import frl.model.configureAspectOrientedFiles.FieldType;
import frl.model.configureAspectOrientedFiles.PropertyType;
import frl.model.configureAspectOrientedFiles.TextFileDetails;
import frl.model.configureAspectOrientedFiles.TextFileProperties;
import frl.model.loadUMLSequenceDiagram.AnnotationType;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramFinal;

public class GenerateLoggingInstructions 
{

   public String generateAnnotationFile(String databaseConfigFile, AOPFinalFileFormEvent ev, 
                                        AOPFinalFileController aopFinalFileController) throws Exception
   {
      String errorMessage1="", errorMessage2="", projectName="", projectInputDir="", projectOutputDir="", logDirectory="", 
	         annotationFile="", annotationFilePrefix="", findWhiteSpaces="", validSpecialCharacter ="",
	     	 valueDelimiter = "", endStatementDelimiter="", progLanguageName="", objectOrientedDelimiter1="", 
	     	 dataTypeFullClassName="", dataTypeShortClassName="", dataTypeStringList="", dataTypeNumberList="", 
	     	 fullMethodName="", methodOwner="",  methodShortName="", fullAnnotationCondition="", 
	     	 annotationElement1="", annotationOperator="", annotationElement2="", InitialElement1="", FinalElement1="", 
	     	 annotationFilePathName="", line="";
	  int c=0, projectId=0, progrLanguageId=0, methodId=0, length=0, methodCounter=0;
	  AnnotationType annotationType;
	  String[] parts = new String[]{""};
	 	  
      File fout = null;
	  FileOutputStream fos = null;
	  BufferedWriter bw = null;
	 	  
	  ArrayList<UMLSequenceDiagramFinal> umlSeqDiagramFinalList = new ArrayList<UMLSequenceDiagramFinal>();

      // Get the values from the GUI
	  projectId                = ev.getProjectId();
	  projectName              = ev.getProjectName();
	  projectInputDir          = ev.getProjInputDir();
	  projectOutputDir         = ev.getProjectOutputDir();
	  progrLanguageId          = ev.getProgrammingLanguageId();
	  progLanguageName         = ev.getProgrammingLanguageName(); 
	  objectOrientedDelimiter1 = ev.getObjectOrientedDelimiter1();
	  dataTypeFullClassName    = ev.getDataTypeFullClassName();
	  dataTypeShortClassName   = ev.getDataTypeShortClassName();
	  dataTypeStringList       = ev.getDataTypeStringList();
	  dataTypeNumberList       = ev.getDataTypeNumberList();
	  logDirectory             = ev.getLogDirectory();
	  annotationFile           = ev.getAnnotationFile();
	  annotationFilePrefix     = ev.getAnnotationFilePrefix();
	  findWhiteSpaces          = ev.getFindWhiteSpaces();
	  validSpecialCharacter    = ev.getValidSpecialCharacter();
	  valueDelimiter           = ev.getValueDelimiter();
	  endStatementDelimiter    = ev.getEndStatementDelimiter();
	  
	  /*
	  System.out.println("Project Id                 : " + projectId);
	  System.out.println("Project Name               : " + projectName);
	  System.out.println("Project Input Dir          : " + projectInputDir);
	  System.out.println("Project Output Dir         : " + projectOutputDir);
	  System.out.println("Programming Language Id    : " + progrLanguageId);
	  System.out.println("Programming Language Name  : " + progLanguageName);
	  System.out.println("Object Oriented Delimiter1 : " + objectOrientedDelimiter1);
	  System.out.println("Data Type Full ClassName   : " + dataTypeFullClassName);
	  System.out.println("Data Type Short ClassName  : " + dataTypeShortClassName);
	  System.out.println("Data Type String List      : " + dataTypeStringList);
	  System.out.println("Data Type Number List      : " + dataTypeNumberList);
	  System.out.println("Log Directory              : " + logDirectory);
	  System.out.println("Annotation File            : " + annotationFile);
	  System.out.println("Annotation File Prefix     : " + annotationFilePrefix);
	  System.out.println("Find White Spaces          : " + findWhiteSpaces);
	  System.out.println("Valid Special Character    : " + validSpecialCharacter);
	  System.out.println("Value Delimiter            : " + valueDelimiter);
	  System.out.println("End Statement Delimiter    : " + endStatementDelimiter);
	  */
	  
	  // Process 
	  try 
	  {
	     // Load the Annotations
	  	 umlSeqDiagramFinalList = aopFinalFileController.loadAnnotations(databaseConfigFile, projectId);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
         throw new Exception(errorMessage1);
	  }
	  	  
	  // Build the annotation File Path Name
	  annotationFilePathName = projectOutputDir + File.separator + projectName + validSpecialCharacter + annotationFilePrefix;
	  	  
	  
	  //System.out.println("Annotation File Path Name: " + annotationFilePathName);
	  	  
	  FileUtils.deleteQuietly(new File(annotationFilePathName));
	  	  
	  try 
	  {
	     fout = new File(annotationFilePathName);
	 	 fos  = new FileOutputStream(fout);
	 	 bw   = new BufferedWriter(new OutputStreamWriter(fos));
	  } 
	  catch (Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
	     errorMessage2 = "Error XXXX while opening the Annotation File: " + annotationFilePathName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + errorMessage1;
 	     bw.close();
 	     fos.close();
	     throw new Exception(errorMessage2);
	  } 
	 	  
	  //System.out.println("Loading the Annotations ...");
	       
	  for (c = 0; c < umlSeqDiagramFinalList.size(); c++) 
	  {
	     annotationType          = umlSeqDiagramFinalList.get(c).getAnnotationType();
	     methodId                = umlSeqDiagramFinalList.get(c).getMethodIdentifier();
	     fullMethodName          = umlSeqDiagramFinalList.get(c).getLineType2(); 
	     methodOwner             = umlSeqDiagramFinalList.get(c).getDatumCategory2();
	     methodShortName         = umlSeqDiagramFinalList.get(c).getDatumValue();
	     fullAnnotationCondition = umlSeqDiagramFinalList.get(c).getAnnotationDetails();
	 	 annotationElement1      = umlSeqDiagramFinalList.get(c).getAnnotationElement1();
	 	 annotationOperator      = umlSeqDiagramFinalList.get(c).getAnnotationOperator();
	 	 annotationElement2      = umlSeqDiagramFinalList.get(c).getAnnotationElement2();
	 	 
	 	 methodCounter++;
	 	   	 
	 	 /*
	 	 System.out.println("Annotation Type           : " + annotationType);
	 	 System.out.println("Method Id                 : " + methodId);
	 	 System.out.println("Full Method Name          : " + fullMethodName);
	 	 System.out.println("Method Owner              : " + methodOwner);
	 	 System.out.println("Method Short Name         : " + methodShortName);
	 	 System.out.println("Full Annotation Condition : " + fullAnnotationCondition);
	 	   	 
	 	 System.out.println("Annotation Element 1      : " + annotationElement1);
	 	 System.out.println("Annotation Operator       : " + annotationOperator);
	 	 System.out.println("Annotation Element 2      : " + annotationElement2);
	 	 */
	 	   	 
	 	 parts  = annotationElement1.split("\\s+");
	 	 length = parts.length;
	 	 //System.out.println("Parts: " + Arrays.toString(parts));
	 	   		   	 
	 	 if(length > 1)
	 	    InitialElement1 = parts[1];
	 	 else
	 	    InitialElement1 = parts[0];
	 	   	 
	 	 //System.out.println("Initial Element 1: " + InitialElement1);
	 	   	 
	 	 parts  = InitialElement1.split("\\.");
	 	 length = parts.length;
	 	 //System.out.println("Parts: " + Arrays.toString(parts));
	 	   	 
	 	 if(length > 1)
	        FinalElement1 = parts[1];
	     else
	 	    FinalElement1 = parts[0];
	 	   	 
	 	 //ystem.out.println("Final Element 1: " + FinalElement1);
	 	   	 
	 	 line = annotationType          + valueDelimiter + 
	 	   	    //methodId                + valueDelimiter + 
	 			methodCounter           + valueDelimiter + 
	 	   	    fullMethodName          + valueDelimiter + 
	 	   	    methodOwner             + valueDelimiter + 
	 	   	    methodShortName         + valueDelimiter +
	 	   	    fullAnnotationCondition + valueDelimiter +
	 	   	    FinalElement1           + valueDelimiter + 
	 	   	    annotationOperator      + valueDelimiter +
	 	   	    annotationElement2      + endStatementDelimiter + 
	 	   		System.lineSeparator();
	 	   	 
	 	  //System.out.println("Line: " + line);
	 	   	 
	 	  // Write the line into the Annotations File
	 	  try 
	 	  {
	 	     bw.write(line);
	 	  } 
	 	  catch (Exception e3)
	 	  {
	 	     errorMessage1 = e3.getMessage();
		     errorMessage2 = "Error XXXX while creating the Annotation File: "+ annotationFilePathName + System.lineSeparator();
		     errorMessage2 = errorMessage2 + errorMessage1;
	 	     bw.close();
	 	     fos.close();
		     throw new Exception(errorMessage2);
	 	  } 

	   }
	       
	   try 
	   {
	      bw.close();
	      fos.close();
	   } 
	   catch (Exception e4) 
	   {
	      errorMessage1 = e4.getMessage();
		  errorMessage2 = "Error XXXX while closing the Annotation File: "+ annotationFilePathName + System.lineSeparator();
		  errorMessage2 = errorMessage2 + errorMessage1;
		  throw new Exception(errorMessage2);	
	   }
	   
	   bw.close();
	   
	   //System.out.println("The Following File was generated");
	   //System.out.println("Location: " + annotationFilePathName);
		  
	   return annotationFilePathName;
   }
   
   
   public String generateFile(AOPFinalFileFormEvent ev, String fileName, String content) throws Exception
   {
      String errorMessage1="", errorMessage2="", projectOutputDir="", 
	 	     filePathName="";
	  File fout = null;
	  FileOutputStream fos = null;
	  BufferedWriter bw = null;	  
	  
	  projectOutputDir = ev.getProjectOutputDir();
	  
	  //System.out.println("Project Output Dir : " + projectOutputDir);
 
  	  filePathName = projectOutputDir + File.separator + fileName;
  	  
  	  //System.out.println("Location: " + filePathName);
  	  
  	  FileUtils.deleteQuietly(new File(filePathName));
  	  
 	  try 
 	  {
 	     fout = new File(filePathName);
 	   	 fos  = new FileOutputStream(fout);
 	   	 bw   = new BufferedWriter(new OutputStreamWriter(fos));
 	  } 
 	  catch (Exception e1)
 	  {
	     errorMessage1 = e1.getMessage();	  
		 errorMessage2 = "Error XXXX while opening the Log4J Properties File: " + filePathName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + errorMessage1;
		 fos.close();
		 bw.close();
		 throw new Exception(errorMessage2);	
 	  } 

 	  // Write the content of the Log4J Properties File
 	  try 
 	  {
 	     bw.write(content);
 	  } 
 	  catch (Exception e2)
 	  {
 	     errorMessage1 = e2.getMessage();		  
 		 errorMessage2 = "Error XXXX Ocurred while creating the File: " + filePathName + System.lineSeparator();
 		 errorMessage2 = errorMessage2 + errorMessage1;
 	     bw.close();
 	     fos.close();
 		 throw new Exception(errorMessage2);	
 	  } 
 	   	 
 	  try 
 	  {
 	     bw.close();
 	     fos.close();
 	  } 
 	  catch (Exception e3) 
 	  {
  	     errorMessage1 = e3.getMessage();		  
  		 errorMessage2 = "Error XXXX Ocurred while closing the File: " + filePathName + System.lineSeparator();
  		 errorMessage2 = errorMessage2 + errorMessage1;
  		 throw new Exception(errorMessage2);	
 	  } 
 	  
      return filePathName;	   
   }
   
   public String generateLog4JPropertesFile(AOPFinalFileFormEvent ev) throws Exception
   {
      String errorMessage1="", fileName1="", content="", logDirectory="", fileName2="";
	  
	  // Get the Data from the GUI
	  logDirectory = ev.getLogDirectory();
	  
	  fileName1="log4j.properties";
	  
 	  content = "";
      content = "# Define the root logger with appender file" + System.lineSeparator();
      content = content + "log = " + logDirectory + System.lineSeparator();
      content = content + "log4j.rootLogger = ALL, file" + System.lineSeparator();
      content = content + "# Create a Daily Log File" + System.lineSeparator();
      content = content + "log4j.appender.file=org.apache.log4j.DailyRollingFileAppender" + System.lineSeparator();
      content = content + "log4j.appender.file.File=${log}/forensicReadyLogger_${current.date.time}.log" + System.lineSeparator();
      content = content + "log4j.appender.file.Append=true" + System.lineSeparator();
      content = content + "log4j.appender.file.layout=org.apache.log4j.PatternLayout" + System.lineSeparator();
      content = content + "log4j.appender.file.layout.ConversionPattern=%d{dd/MM/yyyy hh:mm:ss aa z} %-5p %c{1}:%L - %m%n";
	  
	  try
	  {
	     fileName2 = generateFile(ev, fileName1, content);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	  
	  	 throw new Exception(errorMessage1);
	  }   
	   
      return fileName2;  
   }
   
   public void updateTextFileProperties(String databaseConfigFile, AOPFinalFileFormEvent ev, 
                                        AOPFinalFileController aopFinalFileController, String annotationFile) throws Exception
   {
      String errorMessage1="";
      int projectId=0, headerId=0;
      
      projectId = ev.getProjectId();
      headerId  = 3;
	  
	  try 
	  {
	    aopFinalFileController.updateTextFileProperties(databaseConfigFile, projectId, headerId, annotationFile);
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();	  
	  	 throw new Exception(errorMessage1);
	  }  	   
	   
   }
   
   public String createAnnotatedMethodList(int projectId, String databaseConfigFile, 
		                                   AOPFinalFileFormEvent ev, 
		                                   AOPFinalFileController aopFinalFileController) throws Exception
   {
      String methodList="", errorMessage1="", methodFullName="", line="", startCallMethod="", endCallMethod1="", 
    		 callMethodDelimiter="", guiLibDelimiter="", returnType1="", returnType2="", methodPrefixName="",
    		 value1="class", value2="ArrayList", value3="List", value4="<", value5=">";
      int c=0, length=0;
	  ArrayList<ClassMethod> classMethodList = new ArrayList<ClassMethod>();
      
	  //System.out.println("Inside createAnnotatedMethodList method ...");
	  
	  // Get the data from the GUI
	  startCallMethod     = ev.getStartCallMethod();
	  endCallMethod1      = ev.getEndCallMethod1();
	  callMethodDelimiter = ev.getCallMethodDelimiter();
	  guiLibDelimiter     = ev.getGuiLibDelimiter();
	  
	  //System.out.println("INSIDE THE Create Annotated Method List Method ...");
	  
	  // Load the Method Details from the Annotations
	  try 
	  {
	     classMethodList = aopFinalFileController.loadAnnotatedMethodsDet(databaseConfigFile, projectId);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	  
	  	 throw new Exception(errorMessage1);
	  }
	  
	  length = classMethodList.size() - 1;
	  //System.out.println("Class Method List Size: " + length);
	  
	  if(classMethodList != null ) 
	  { 
	     for(c = 0; c < classMethodList.size(); c++)
		 {
	        
	    	//methodId       = classMethodList.get(c).getId();
	        methodFullName = classMethodList.get(c).getFullMethodName();
	        returnType1    = classMethodList.get(c).getReturnType1();
	        returnType2    = classMethodList.get(c).getReturnType2();
		    line           = "";	
		    methodPrefixName="";
	        
		    /*
		    System.out.println("Method Id        : " + methodId);
		    
		    
		    
		    System.out.println("INITIAL Method Full Name : " + methodFullName);
		    System.out.println("Return Type 1            : " + returnType1);
		    System.out.println("Return Type 2            : " + returnType2);
		    */
		    
		    if(methodFullName.contains(value1))
		    {
		       //System.out.println("The method contains a class data type");
		       methodFullName  = methodFullName.replace(value1, returnType2);
		    }
		    
		    /*nonPrimitiveValue1=ArrayList 
		    nonPrimitiveValue2=List
		    startClassDef=<
		    endClassDef=>
		    */
		    if(returnType1.contains(value2))
		    {
		       methodPrefixName  = returnType1 + value4 + returnType2  + value5;
		       
		       methodFullName  = methodFullName.replace(value2, methodPrefixName);
		    }
		    else
		    	 if( returnType1.contains(value3) )
		    	 {
		    	    methodPrefixName  = returnType1 + value4 + returnType2  + value5;
				       
				    methodFullName  = methodFullName.replace(value3, methodPrefixName);
		    	 }
		    
		    
		    //System.out.println("Method Prefix Name       : " + methodPrefixName);
		    
		    //System.out.println("FINAL Method Full Name   : " + methodFullName);
	        
	        if(c == 0)
	        {	
		       //line = "      call(" + methodFullName + "(..)) ||" + System.lineSeparator();
	           line = "      " + startCallMethod + methodFullName + endCallMethod1 + callMethodDelimiter + System.lineSeparator();
	        }   
		    else
		       if(c < length)
		       {	   
	              //line = "            call(" + methodFullName + "(..)) ||" + System.lineSeparator();
	              line = "      " + startCallMethod + methodFullName + endCallMethod1 + callMethodDelimiter + System.lineSeparator();
		       }
	        else
	        	if(c == length)
	        	{	
	 	           //line = "            call(" + methodFullName + "(..));";
	 	           line = "      " + startCallMethod + methodFullName + endCallMethod1 + guiLibDelimiter;
	        	}   
	        
	        /*
	        System.out.println("Counter : "+ c);
	        System.out.println("Line    : "+ line);
	        */
	        
	        methodList = methodList + line;
	        
		 }
	  }   
      
	  //System.out.println("Method List: "+ methodList);
	  
      return methodList;
	   
   }
   
   public String generateAOPFile(String databaseConfigFile, AOPFinalFileFormEvent ev, AOPFinalFileController aopFinalFileController) throws Exception
   {
      String errorMessage1="", errorMessage2="", lineText="", propertyName="", propertyValue="", propertyDataType="", line="",
    		 confPropertyName1="", content="", property="", propertyValueFirstChar="", findString="", assignValueDelimiter="",
    		 fileName1="", fileName2="", aOPDirectory="", projectOutputDir="";
      
      int headerId=0, linePropertyId=0, c=0, projectId=0;
	  FieldType lineType = null;
	  PropertyType propertyType = null;
	  //TextFileHeader textFileHeaderRecord = null;
	  TextFileProperties textFilePropertiesRecord = null;
	  ArrayList<TextFileDetails> textFileDetailsList = new ArrayList<TextFileDetails>();
	
	  // Get the data from the GUI
	  headerId             = 3;
	  projectId            = ev.getProjectId();
	  confPropertyName1    = ev.getConfPropertyName1();
	  findString           = ev.getFindString();
	  assignValueDelimiter = ev.getAssignValueDelimiter();
	  fileName1            = ev.getTextFileNameExt3();
	  aOPDirectory         = ev.getAOPDirectory();
	  
	  projectOutputDir     = ev.getProjectOutputDir();
	 
	  
	  //System.out.println("AOP Directory            : " + aOPDirectory);
	  //System.out.println("Project Output Directory : " + projectOutputDir);
	  
	  /*
	  // Load the AOP Header
	  try 
	  {
	     textFileHeaderRecord = aopFinalFileController.loadAOPFileHeader(databaseConfigFile, headerId);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();	  
	  	 throw new Exception(errorMessage1);
	  }
	  
	  if(textFileHeaderRecord != null ) 
	  {
	     longFileName    = textFileHeaderRecord.getFileName();
	     pathName        = textFileHeaderRecord.getPath();
	     shortFileName   = textFileHeaderRecord.getName();
	     fileType        = textFileHeaderRecord.getType();
	     packageName     = textFileHeaderRecord.getPackageName();
	     programmingLanguageName = textFileHeaderRecord.getProgrammingLanguage();
	     pointCut1Name   = textFileHeaderRecord.getPointCut1Name();
	     pointCut2Name   = textFileHeaderRecord.getPointCut2Name();
	     
	     
	     
	     System.out.println("");
	     System.out.println("AOP Header");
	     
	     System.out.println("Long File Name            : " + longFileName);
	     System.out.println("Path Name                 : " + pathName);
	     System.out.println("Short File Name           : " + shortFileName);
	     System.out.println("File Type                 : " + fileType);
	     System.out.println("Package Name              : " + packageName);
	     System.out.println("Programming Language Name : " + programmingLanguageName);
	     System.out.println("PointCut 1 Name           : " + pointCut1Name);
	     System.out.println("PointCut 2 Name           : " + pointCut2Name);
	     
	  }
	  
	  */
	  
	  // Load the AOP Details
	  try 
	  {
	     textFileDetailsList = aopFinalFileController.loadAOPFileDetails(databaseConfigFile, headerId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();	  
	  	 throw new Exception(errorMessage1);
	  }
	  
	  if(textFileDetailsList != null ) 
	  {
	     for(c = 0; c < textFileDetailsList.size(); c++)
		 {
	        //lineNumber = textFileDetailsList.get(c).getLineNumber();
	        lineText   = textFileDetailsList.get(c).getText();
	        lineType   = textFileDetailsList.get(c).getType();
	        linePropertyId  = textFileDetailsList.get(c).getPropertyId();
	        
	        /*
		    System.out.println("");
			System.out.println("AOP Details"); 
	        System.out.println("Line Text     : " + lineText);
	        System.out.println("Line Type     : " + lineType);
	        
	        System.out.println("Line Property Id   : " + linePropertyId);
	        
	        */
	        
	        if(linePropertyId > 0)
	        {
	      	   // Load the AOP Properties
	      	   try 
	      	   {
	      	      textFilePropertiesRecord = aopFinalFileController.loadAOPFileProperties(databaseConfigFile, projectId, headerId, linePropertyId);
	      	   } 
	      	   catch (Exception e3) 
	      	   {
	      	      errorMessage1 = e3.getMessage();	  
	      	  	  throw new Exception(errorMessage1);
	      	   }
	      	   	      	 
	     	   if(textFilePropertiesRecord != null ) 
	    	   {
	     		  //System.out.println("Text File Properties Record is not null"); 
	     		  
	    	      propertyName     = textFilePropertiesRecord.getName();
	    	      propertyValue    = textFilePropertiesRecord.getValue(); 
	    	      propertyDataType = textFilePropertiesRecord.getDataType();
	    	      propertyType     = textFilePropertiesRecord.getType();
	    	     
	    	      /*
	    	      System.out.println("");
	    	      System.out.println("AOP Properties");
	    	     
	    	      System.out.println("Property Name      : " + propertyName);
	    	      System.out.println("Property Value     : " + propertyValue);
	    	      System.out.println("Property Data Type : " + propertyDataType);
	    	      System.out.println("Property Type      : " + propertyType);
	    	      */
	    	      
	    	   }
	     	   else
	     	   {
			      errorMessage1 = "Error XXXX: The Property of the Text File is null for the Project Identifier: " + projectId + System.lineSeparator();
			 	  errorMessage2 = errorMessage1 + "Header Identifier: " + headerId + System.lineSeparator();
			 	  errorMessage2 = errorMessage2 + "Line Property Identifier: " + linePropertyId + System.lineSeparator();
			 	  throw new Exception(errorMessage2);	
	     	   }
	     	   
	        }
	        
	        line = "";
	        
	        // Build the line for the text
	        if(lineType.equals(FieldType.Fixed))
	           line = lineText;
	        else
	        {  
	           if(propertyType.equals(PropertyType.Attribute) )
	           {	
	              property = "";
	              
	              // Validate that property Value is not null or empty
	              if(propertyValue != null && !propertyValue.trim().isEmpty()) 
	              {	  
		             propertyValueFirstChar = propertyValue.substring(0, 1);
		             
		             if(propertyValue.substring(0, 1).equals(findString)) 
			            propertyValue = propertyValue.replace(findString, findString+findString);
	              }
	              
	        	  if(propertyDataType.equals("String"))
	                 property = propertyName + assignValueDelimiter + "\"" + propertyValue + "\"";
	        	  else
	        		  property = propertyName + assignValueDelimiter + propertyValue;
	        	  
	              line = lineText.replace(confPropertyName1, property);
	              
	              //System.out.println("ORIGINAL Line: " + line);
	              
	              
	              if(propertyName.equals("annotationFile"))
	              {
	            	  line = line.replace(projectOutputDir + System.getProperty("file.separator"), aOPDirectory);
	            	  //System.out.println("MODIFIED Line =>:" + line);
	              }
	              
	              
	           }
	           else
	        	   if (propertyType.equals(PropertyType.Component))
	        	      line = lineText.replace(confPropertyName1, propertyValue);
	        	   else
	        		   if (propertyType.equals(PropertyType.Method))
	        		   {	 
	        		      line = createAnnotatedMethodList(projectId, databaseConfigFile, ev, aopFinalFileController);
	        		      //line = lineText.replace(confPropertyName1, line);
	        		   }   
	        }   
	        
	        //System.out.println("Line: " + line);
	        content = content + line + System.lineSeparator();
	        
	        	
	     }
	  }	
	  
	  try
	  {
	     fileName2 = generateFile(ev, fileName1, content);
	  } 
	  catch (Exception e4) 
	  {
	     errorMessage1 = e4.getMessage();	  
	  	 throw new Exception(errorMessage1);
	  }
	  
	  return fileName2;
 
   }
   
   public String generateLoggingInstructions(String databaseConfigFile, String featuresConfigFile, 
		                                     AOPFinalFileFormEvent ev, 
                                             AOPFinalFileController aopFinalFileController)
   {
      String errorMessage="", fileName="";
      
      System.out.println("Creating the Required Files to assist to generate the Logging Instructions ...");
	  
	   
      // Generate the Annotation File
      try 
	  {
    	  fileName = generateAnnotationFile(databaseConfigFile, ev, aopFinalFileController);
	  } 
      catch (Exception e1) 
      {
   	     errorMessage = e1.getMessage();
   	     return errorMessage;
	  }
      
      System.out.println("The Annotation File was generated in:" + System.lineSeparator() + fileName);
      
      // Update the text_file_property table
      try 
	  {
         updateTextFileProperties(databaseConfigFile, ev, aopFinalFileController, fileName);
	  } 
      catch (Exception e2) 
      {
   	     errorMessage = e2.getMessage();
   	     return errorMessage;
	  }
      
      
      // Generate the Log4J Properties File
      try 
	  {
    	  fileName = generateLog4JPropertesFile(ev);
	  } 
      catch (Exception e3) 
      {
   	     errorMessage = e3.getMessage();
   	     return errorMessage;
	  }
      
      System.out.println("The Log4J Properties File was generated in: " + System.lineSeparator() + fileName);
      
      // Generate the AOP Final File
      try 
	  {
         fileName = generateAOPFile(databaseConfigFile, ev, aopFinalFileController);
	  } 
      catch (Exception e4) 
      {
   	     errorMessage = e4.getMessage();
   	     return errorMessage;
	  }
      
      System.out.println("The AOP File was generated in: " + System.lineSeparator() + fileName);

      return errorMessage;
   }
}
