package frl.process.loadUMLSequenceDiagram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import frl.controller.loadUMLSequenceDiagram.LoadUMLSequenceDiagramController;
import frl.model.configuration.ClassAttribute;
import frl.model.configuration.ClassMethod;
import frl.model.configuration.FRLConfiguration;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.DataCategory;
import frl.model.loadUMLSequenceDiagram.DataType;
import frl.model.loadUMLSequenceDiagram.DatumCategory1;
import frl.model.loadUMLSequenceDiagram.LineType1;
import frl.model.loadUMLSequenceDiagram.MemberType;
import frl.model.loadUMLSequenceDiagram.MethodDataSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodFileSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodParameterData;
import frl.model.loadUMLSequenceDiagram.MethodStepSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.NoteType;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ReturnTypeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ReturnTypeValueSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramFinal;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramPlain;
import frl.model.loadUMLSequenceDiagram.UserFileSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UserSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ValueSequenceDiagram;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;

public class UMLSeqDiagram
{

   public String deleteUMLSequenceDiagramPlain(String databaseConfigFile, int projectId)
   {
      String errorMessage1="";	   
      LoadUMLSequenceDiagramController umlSeqDiagramCon = new LoadUMLSequenceDiagramController();
      
	  // Connecting to the database
	  try 
	  {
	     umlSeqDiagramCon.connect(databaseConfigFile);
      } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 return errorMessage1;
	  }
	  
	  // Deleting the previous UML Sequence Diagram from the UML Sequence Diagram Plain Table
	  // uml_sequence_diagram_plain
	  try 
	  {
	     umlSeqDiagramCon.deleteUMLSequenceDiagramPlain(projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
	 	 return errorMessage1;
	  }
	  
	  
	  // Disconnect from the database
	  try 
	  {
	     umlSeqDiagramCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
	     return errorMessage1;
	  }
	  
	  
      return errorMessage1;	      
   }
	   
   public void saveSeqTextFileDb(String databaseConfigFile, Path fileName, 
		                         FRLConfiguration frlCon, int projectId) throws Exception
   {
      String lineText="", lineType2="", memberName="", datumCategory2="", datumValue="";
      String methodName = "", methodMessage="", noteMember1="", noteMember2="", 
    		 errorMessage1="", errorMessage2="";
      
      int lineNumber=0, memberCounter=0, noteCounter=0, parameterCounter=0;
	  boolean noteFlag=false, parameterFlag=false;
	  
	  NoteType noteType1=NoteType.None, noteType2=NoteType.None;
	  MemberType memberType;
	  DatumCategory1 datumCategory1=DatumCategory1.None;
	  LineType1 lineType1=LineType1.None;
      
      final Charset ENCODING = StandardCharsets.UTF_8;
      LoadUMLSequenceDiagramController umlSeqDiagramCon = new LoadUMLSequenceDiagramController();
      UMLSequenceDiagramPlain umlSequenceDiagram;
            
      // Deleting the previous UML Sequence Diagram from the UML Sequence Diagram Plain Table
	  errorMessage1 = deleteUMLSequenceDiagramPlain(databaseConfigFile, projectId);
	  
	  if(errorMessage1 != null && !errorMessage1.trim().isEmpty())
	     throw new Exception(errorMessage1);
      
      // Connecting to the database
      try 
      {
	     umlSeqDiagramCon.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
	     errorMessage1 = e1.getMessage();
	     throw new Exception(errorMessage1);
	  }
     
	  try (BufferedReader reader = Files.newBufferedReader(fileName, ENCODING))
	  {
		     
		 while ((lineText = reader.readLine()) != null) 
		 {
		    lineNumber++;	
		    memberName     = methodName  = methodMessage="";
		    noteMember1    = noteMember2 = "";
		    noteType1      = noteType2   = NoteType.None;
		    memberType     = MemberType.None;
		    datumCategory1 = DatumCategory1.None;
		    datumCategory2 = datumValue = "";
		    
		    // Assign the lineType1 and lineType2
		    
		    // @startuml
		    if(lineText.equals(frlCon.startUMLSeqDiagram))
		    {	
		       lineType1 = LineType1.DiagramStarts; 
		       lineType2 = frlCon.startDiagram;
		    }   
		    // @enduml
		    else 
		       if(lineText.equals(frlCon.endUMLSeqDiagram))
		       {	
		          lineType1 = LineType1.DiagramEnds; 
		          lineType2 = frlCon.endDiagram;
		       }   
		       else
		    	   // skinparam backgroundColor
		    	   if(lineText.contains(frlCon.specificationParameter))
			       {	
				      lineType1        = LineType1.ConfigurationStarts; 
				      lineType2        = frlCon.startParameter;
				      parameterFlag    = true;
				      parameterCounter = lineNumber;
				   } 
		    	   else
			    	   // }
			    	   if(lineText.equals(frlCon.endMethod))
				       {	
					      lineType1        = LineType1.ConfigurationEnds; 
					      lineType2        = frlCon.endParameter;
					      parameterFlag    = false;
					      parameterCounter = 0;
					   } 
		               // == Connects to the
		               else 
		                  if(lineText.contains(frlCon.startDivision))
		                  {
                             lineType1  = LineType1.ConnectionStarts;
                             lineType2  = frlCon.connectUser;
		                     datumValue = "";
		                  }   
		                  else 
		    	             // actor 
		                     if(lineText.contains(frlCon.member1))
		                     {
			                    memberCounter++;
			                    lineType2  = frlCon.memberMethod + " " + memberCounter;
		                        memberType = MemberType.Actor;
		                        memberName = StringUtils.substringBetween(lineText, frlCon.member1, frlCon.position);
		                        memberName = memberName.replaceAll(frlCon.findWhiteSpaces,"");
		                        
		                        datumCategory1 = DatumCategory1.DiagramMember;
		                        datumCategory2 = memberType.name();
		                        datumValue     = memberName;
		                     }
		                     else 
		        	            // participant 
		    	               if (lineText.contains(frlCon.member2))
		                       {
				                  memberCounter++;
				                  lineType2  = frlCon.memberMethod + " " + memberCounter;
			                      memberType = MemberType.Participant;
			                      memberName = StringUtils.substringBetween(lineText, frlCon.member2, frlCon.position);
			                      memberName = memberName.replaceAll(frlCon.findWhiteSpaces,"");
			                      
			                      datumCategory1 = DatumCategory1.DiagramMember;
			                      datumCategory2 = memberType.name();
			                      datumValue     = memberName;
			                   }
		    	               else
		    	                  // note left of 
				    	         if(lineText.contains(frlCon.startNote))
				    	         {	   
				    	            lineType2   = frlCon.startNote1;	
				    	      
				    	            noteFlag    = true;
					    	        noteCounter = lineNumber;
					    	        noteMember1 = StringUtils.substringBetween(lineText, frlCon.startNote, (frlCon.colorDelimiter + frlCon.colorNote) );
					    	        noteMember1 = noteMember1.replaceAll(frlCon.findWhiteSpaces,"");
								    		
					    	        noteType1 = NoteType.Owner;
					    	        
					    	        datumCategory1 = DatumCategory1.NoteMember;
					    	        datumCategory2 = noteType1.name();
			                        datumValue     = noteMember1;
				    	         }   
				    	         else
			    	                // end note
					    	        if(lineText.contains(frlCon.endNote))
					    	        {	   
					    	           lineType2 = frlCon.endNote1;						    	      
					    	           noteFlag  = false;
					                } 
				    	            else 
				    	               // ->
				    	               if(lineText.contains(frlCon.startSendMessage))
				    	               {
				    		              lineType2     = frlCon.methodSpecification;  
				    	                  methodName    = lineText.substring(lineText.indexOf(frlCon.endSendMessage) + 1, lineText.length());
				    		              methodName    = methodName.replaceAll(frlCon.findWhiteSpaces,"");
				    		              methodMessage = lineText;
				    		                 
								    	  datumCategory1 = DatumCategory1.MethodName;
								    	  datumCategory2 = methodName;
						                  datumValue     = methodMessage;
	
				    	               }
				    	               else
				    	                  //||20||
				    	                  if(lineText.contains(frlCon.space))
				    		              {
				    	    	             lineType1 = LineType1.MethodEnds;      
				    	                     lineType2 = frlCon.endUMLSeqDiagram1;       
				    		              }
		    
		    if(memberCounter == 1)
		    	lineType1 =  LineType1.MethodStarts;  
		    else
		    	if(memberCounter > 1 && !lineType2.equals(frlCon.endUMLSeqDiagram1))
		    	   lineType1 =  LineType1.MethodBody;
		    
		    if(noteFlag == true && lineNumber == (noteCounter + 1)) 
		    {		
		       lineType2   = frlCon.bodyNote1;	
		       noteMember2 = lineText;
		       noteMember2 = noteMember2.replaceAll(frlCon.findWhiteSpaces,"");
		       noteType2 = NoteType.Member;
		       
   	           datumCategory1 = DatumCategory1.NoteMember;
		       datumCategory2 = noteType2.name();
               datumValue     = noteMember2;
		    }
		    
		    if(parameterFlag == true && lineNumber >= (parameterCounter + 1))
		    {	
		       lineType1 = LineType1.ConfigurationBody;	
		       lineType2 = frlCon.bodyParameter;
		    }
		    
		     // Save the current line of the UML Sequence Diagram into the Database
			 umlSequenceDiagram = new UMLSequenceDiagramPlain(projectId, lineNumber, lineText,
					                                          lineType1, lineType2, datumCategory1,
					                                          datumCategory2, datumValue);
		     try
			 {
		        umlSeqDiagramCon.saveUMLSequenceDiagramPlain(umlSequenceDiagram);
			 }
			 catch (Exception e2) 
			 {
			    errorMessage1 = e2.getMessage();
			    throw new Exception(errorMessage1);
			 } 
			 
			 // Initialize Values
			 if(lineType2.equals(frlCon.endUMLSeqDiagram1))
			 {	
	   		    // Initialize the values 
	   	    	lineType1 = LineType1.None;
	   	    	lineType2 = "";
	   		    memberCounter = noteCounter = 0;
			 }
		    
	     }      
      }
	  catch(IOException e3)
	  {
 	     errorMessage1 = e3.getMessage();
 		 errorMessage2 = "Error XXXX: Occurred while loading the UML Sequence Diagram Text File: " + fileName + System.lineSeparator();
 		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
 		 throw new Exception(errorMessage2);
	  }
	  
	  // Disconnect from the database
	  try 
	  {
	     umlSeqDiagramCon.disconnect();
	  } 
	  catch (Exception e4) 
	  {
	     errorMessage1 = e4.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
	   
   }
   
   public void updateUserNames(int projectId, String databaseConfigFile) throws Exception
   {
      String errorMessage1="", lineType1Str="", value="",
    		  currentUserName="", previousUserName="";
      int lineNumber1=0, lineNumber2=0, c=0, userId=0; 
      LineType1 lineType1;
     
	  LoadUMLSequenceDiagramController umlSeqDiagramCon = new LoadUMLSequenceDiagramController();
	  ArrayList<UMLSequenceDiagramPlain> umlSeqDiag = new ArrayList<UMLSequenceDiagramPlain>();
	  
	  value = LineType1.ConnectionStarts.name();
	   
	  // Connecting to the database
	  try 
	  {
	     umlSeqDiagramCon.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     throw new Exception(errorMessage1);
	  }  
	  
	  // Load the records that need to be updated
      try 
	  {	 	
         umlSeqDiag = umlSeqDiagramCon.loadUserRecordsPlain(projectId);
	  }  
	  catch(Exception e2)
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception(errorMessage1);
	  } 
      
	  // Calculate the line Numbers values where is going to be updated the UserName field
	  for (c = 0; c < umlSeqDiag.size(); c++)
	  {	  	
		  
		 // Get the fields from the ArrayList 
		 lineNumber1 = umlSeqDiag.get(c).getLineNumber();
		 lineType1   = umlSeqDiag.get(c).getLineType1();
		 
		 // Convert from Enum to String Type
		 lineType1Str   = lineType1.name();
		 
	     if(lineType1Str.equals(value))
	     {
		    // Calculate the Line Number 2 value
		    lineNumber2 = lineNumber1 + 1;
		    
	        // Get the UserName value
	        try
	        {
	           previousUserName = currentUserName;
	           currentUserName  = umlSeqDiagramCon.getUserNamePlain(projectId, lineNumber2);
	           	           
	           if(previousUserName.equals(currentUserName) == false)
	              userId++;
	        }   
	  	    catch(Exception e3)
		    {
		       errorMessage1 = e3.getMessage();
		       throw new Exception(errorMessage1);
		    }
	        
	        // Update the Datum Category 2 Value
	        try
	        {
	           umlSeqDiagramCon.updateConnectionRecordsPlain(projectId, lineNumber1, currentUserName);
	        }   
	  	    catch(Exception e4)
		    {
		       errorMessage1 = e4.getMessage();
		       throw new Exception(errorMessage1);
		    }
	     }
	     
	     // Update the record with the UserName and UserId
	     try
	     {
	        umlSeqDiagramCon.updateUserRecordsPlain(projectId, lineNumber1, currentUserName, userId);

	     }   
	  	 catch(Exception e5)
		 {
		    errorMessage1 = e5.getMessage();
		    throw new Exception(errorMessage1);
		 }
	     	        
	  }
	  
	  // Disconnect from the database
	  try 
	  {
	     umlSeqDiagramCon.disconnect();
	  } 
	  catch (Exception e6) 
	  {
         errorMessage1 = e6.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
   }
   
   public String getFirstLineFile(String textFile) throws Exception
   {
      BufferedReader Buff;
	  String firstLine="", errorMessage="";
	  
	  try
	  {
	     Buff = new BufferedReader(new FileReader(textFile)); 
	     firstLine = Buff.readLine(); 
	  }
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  Buff.close();
	  
	  return firstLine;
	  
   }
 
   public ClassMethod getComponentsClass(int projectId, String className1, FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="", errorMessage2="", finalPackageName="", className3="", finalClassName="";
      Class<?> className2;
      ClassMethod classMetRecord = null;
      
	  // Create a new Class for the Method Class
	  try 
	  {
	     className2       = Class.forName(className1);
	     className3       = className2.getName();
	     finalPackageName = className2.getPackageName();
	  } 
	  catch(NoClassDefFoundError e1) 
	  {
	     errorMessage1 = e1.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + className1 + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	         
	     throw new Exception(errorMessage2); 
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + className1 + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;

	     throw new Exception(errorMessage2);
      }
	  
	  finalClassName = className3.replace(finalPackageName + frlCon.objectOrientedDelimiter1, "");
	  
	  classMetRecord = new ClassMethod(projectId, finalPackageName, finalClassName, null);
	  
	  return classMetRecord;
   }  
   
   public void updateMethodsDetails(FRLConfiguration frlCon, String databaseConfigFile, int projectId) throws Exception
   {
      String errorMessage1="", lineType2="", datumCategory2="", datumValue="",
             methodName1="", methodName2="", methodName3="", methodOwner="", methodPackageName="",
             methodClassName=""/*, textFileName=""*/;
      int c=0, lineNumber=0, lineNumber1=0, lineNumber2=0, startPos=0, endPos=0, cmId=0;
      boolean startFlag=false;
      
      LineType1 lineType1=LineType1.None, value0=LineType1.ConnectionStarts, value1=LineType1.MethodStarts, 
		        value2=LineType1.MethodEnds, value3=LineType1.MethodBody;
      
      LoadUMLSequenceDiagramController umlSeqDiagramCon = new LoadUMLSequenceDiagramController();
	  UMLSequenceDiagramPlain umlSeqDiagramRecord;
	  ClassMethod classMethodRecord1, classMethodRecord2;
	  ArrayList<UMLSequenceDiagramPlain> umlSeqDiagram = new ArrayList<UMLSequenceDiagramPlain>();
	    	  
	  // Connecting to the database
	  try 
	  {
	     umlSeqDiagramCon.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  		
	  // Load all the sequence diagrams from the Project
	  try 
	  {
	     umlSeqDiagram = umlSeqDiagramCon.loadMethodRecordsPlain(projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception(errorMessage1);	  
	  }
	  	  
	  // Loop to iterate all the records from the umlSeqDiagram ArrayList
      for (c = 0; c < umlSeqDiagram.size(); c++) 
      { 
         lineNumber      = umlSeqDiagram.get(c).getLineNumber(); 
         lineType1       = umlSeqDiagram.get(c).getLineType1();
         lineType2       = umlSeqDiagram.get(c).getLineType2();
         datumCategory2  = umlSeqDiagram.get(c).getDatumCategory2();
         datumValue      = umlSeqDiagram.get(c).getDatumValue();

         // Connection Starts
         if((lineType1).equals(value0))
         {	 
            lineNumber1 = lineNumber;	
            startFlag   = true;
            
         }   
	     // Method Starts
         else 
            if((lineType1).equals(value1) && startFlag == false)
        	{	 
               lineNumber1 = lineNumber;	
        	}  
            else 
               // Method Ends	
               if((lineType1).equals(value2))
               {	   
                  lineNumber2 = lineNumber;	
               }   
               else 
         	      // Method Body
                   if((lineType1).equals(value3) )
                   {	   
                	   if((lineType2).equals(frlCon.methodSpecification))
                	   {	
                		   
                          methodName1 = datumCategory2;
                          startPos    = methodName1.indexOf(frlCon.objectOrientedDelimiter1); 
                          endPos      = methodName1.indexOf(frlCon.startParameters);
                          startPos++;
                          methodName2 = methodName1.substring(startPos, endPos);
                          
                	   }
                       else
                          if(lineType2.equals(frlCon.member2Method))
                          {	   
                             methodOwner       = datumValue;
                             methodPackageName = "";
                             methodClassName   = "";
                             /*
                             // Split the Method Owner in two elements
                             methodOwnerArray = methodOwner.split(frlCon.findDot);
                             
                             if(methodOwnerArray.length > 0)
                             {	  
                                methodPackageName = methodOwnerArray[0];
                                methodClassName   = methodOwnerArray[1];
                             }
                             */
                             
                             try
                             {
                                classMethodRecord1 = getComponentsClass(projectId, methodOwner, frlCon);
                                
                                methodPackageName  = classMethodRecord1.getPackageName();
                                methodClassName    = classMethodRecord1.getClassName();
                             }
                             catch(Exception e3)
                             {
                 			    errorMessage1 = e3.getMessage();
                 			    throw new Exception(errorMessage1);
                 			 } 
                                                       
                          }   
                      } 
         
         // Method Ends	
         if((lineType1).equals(value2))
         {	
        	// Increase the method Id Counter
            startFlag = false;
            
            cmId = 0;
            //textFileName = "";
            methodName3 = "";
            
            // Get the ClassMethod Id for this MethodName2 and MethodOwner   
	        try
	        {
	           classMethodRecord2 = umlSeqDiagramCon.getClassMethodDetails1(projectId, methodName2, methodPackageName, methodClassName); 
	           
		       // Get the fields from the ClassMethodRecord
		       cmId         = classMethodRecord2.getId();
		       methodName3  = classMethodRecord2.getParameterMethodName();
		       //textFileName = classMethodRecord2.getTextFileName();
	        }
	  	    catch(Exception e3)
			{
			   errorMessage1 = e3.getMessage();
			   throw new Exception(errorMessage1);
			} 
 	        
            /*
	        // Build the complete Path Full Name for the TextFile
			pathTextFileName = frlCon.projectOutputDir + File.separator + frlCon.projectName + File.separator +
					           methodPackageName + File.separator +  methodClassName + File.separator + 
					           textFileName;
					           
			
			System.out.println("Path Text File Name  : " + pathTextFileName);
			*/
			
			// Get the first line of the textFileName
			//methodName3 = getFirstLineFile(pathTextFileName);
			//methodName3 = getFirstLineFile(textFileName);
			
			// Remove the white spaces from the beginning of the line
			//methodName3 = methodName3.replaceFirst(frlCon.findWhiteSpaces, "");
			
			// Build the record to save this Method
            umlSeqDiagramRecord = new UMLSequenceDiagramPlain(projectId, lineNumber1, lineNumber2, 
	                                                          methodName1, methodName2, methodOwner,
	                                                          cmId, methodPackageName, 
	                                                          methodClassName, methodName3);
           
	        // Update the missing details of the Method
	        try
	        {
	           umlSeqDiagramCon.updateMethodDetailsPlain(projectId, umlSeqDiagramRecord); 
	        }
	  	    catch(Exception e4)
		    {
		       errorMessage1 = e4.getMessage();
		       throw new Exception(errorMessage1);
		    } 
	        
         }
         
      }
	  
	  // Disconnect from the database
	  try 
	  {
	     umlSeqDiagramCon.disconnect();
	  } 
	  catch (Exception e5) 
	  {
         errorMessage1 = e5.getMessage();
		 throw new Exception(errorMessage1);
	  }

   }
   
   public void cleanDirectory(String projectOutputDir) throws Exception
   {   
	  File folder;
	  String errorMessage1="", errorMessage2="";
		  
	  // Convert the path from STring to File
	  folder = new File(projectOutputDir);
	  
	  try 
	  {
	     // Remove all the files from the Project Output Directory
		 FileUtils.cleanDirectory(folder);
	  } 
	  catch (IOException | IllegalArgumentException e) 
	  {
	     errorMessage1 = e.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the files from the Directory: "+ projectOutputDir + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     throw new Exception(errorMessage2);
	  }
 
   }
   
   public String createTextFile(FRLConfiguration frlCon, String dirPathName, String fileName, 
		                        ArrayList<UMLSequenceDiagramFinal> umlSeqDiagram) throws Exception
   {
      FileWriter fw = null;
      BufferedWriter bw = null;
      File file;
      String errorMessage1="", errorMessage2="", filePath="", lineText="", line="";
      int c=0, lines=0;
      
      
      // Create the complete filename including the path
      filePath = dirPathName + fileName; 
      file     = new File(filePath);
      lines    = umlSeqDiagram.size() - 1;
      
      /*
      System.out.println("INSIDE the Create Text File Method in the UMLSeqDiagram class...");
      //System.out.println("FileName   : " + fileName);
	  System.out.println("FilePath   : " + filePath);
	  System.out.println("File       : " + file);	  
      System.out.println("Lines      : " + lines);
      */
      
      /*
      System.out.println("INSIDE the Create Text File Method in the UMLSeqDiagram class...");
      
      if(fileName.contains("IncidentSequenceDiagram_User_1_Original.txt"))
      {	    
         System.out.println("THE USER FILE GENERATED HERE");
      }  
      
	  System.out.println("FilePath   : " + filePath);
	  System.out.println("File       : " + file);	  
      System.out.println("Lines      : " + lines);
      */
      
      // For every UML Sequence Diagram Element Loop 	  
  	  for(c = 0; c < umlSeqDiagram.size(); c++) 
	  {
	     lineText = umlSeqDiagram.get(c).getLineText();	  
	     
	     /*
		 System.out.println("C         : " + c);
		 System.out.println("Line Text : " + lineText);
         */
	     
		 // Verify if there is any instruction related to terminate the UML Sequence Diagram: @enduml
		 // before ending the UML Diagram Text File
		 if(c < lines)
		 {
			if(lineText.equals(frlCon.endUMLSeqDiagram) == false)
			{
			   //System.out.println("ENTRE AQUI ...");	
	 
			   line = lineText + System.getProperty("line.separator");
			}
			else
		       line = "";

		 }
		 else
		 {	 
			line = lineText + System.getProperty("line.separator");
		 }
		 
		 //System.out.println("Line      : " + line);

		 if(line != null && !line.trim().isEmpty()) 
		 {	 
	        try
	        {
	           fw = new FileWriter(file, true);
	           bw = new BufferedWriter(fw);

	           // Write in the textFile
	           fw.append(line);
	        
	           // Close the textFile
	           bw.close();
	         
	         }
	         catch(Exception e1)
	         {
	 	        errorMessage1 = e1.getMessage();
	 		    errorMessage2 = "Error XXXX: Occurred while opening the UML Sequence Diagram Text File: " + file + System.lineSeparator();
	 		    errorMessage2 = errorMessage2 + "In the Directory: " + dirPathName + System.lineSeparator();
	 		    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	 		    throw new Exception(errorMessage2);	
	         }
		 }
	     
	  }
  	  	  
      return filePath;         
   } 
   
   public String createSeqDiagramPngFile(String textFile) throws Exception
   {
      File file1 = new File(textFile); 
	  SourceFileReader reader = null;
	  List<GeneratedImage> list = null;
	  String errorMessage1="", errorMessage2="", fileName="";
	  
	  /*
	  System.out.println("Inside the Create Seq Diagram Png File Method ...");
	  
	  System.out.println("Textfile : " + textFile);
	  System.out.println("File 1   : " + file1);
	  
	  System.out.println("CREATE IMAGE-STEP 1");
	  */
	  
	  // Create the Reader of the UML Sequence Diagram Text File
	  try 
	  {
	     reader = new SourceFileReader(file1);
	  } 
	  catch (IOException e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while reading the UML Sequence Diagram Text File: " + textFile + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);	     
	  }
	  
	  /*
	  System.out.println("Reader: " + reader);
	   
	  System.out.println("CREATE IMAGE-STEP 2");
	  */
	  
	  // Generate the list of images using the Sequence Diagram Text File
	  try 
	  {
	     list = reader.getGeneratedImages();
	  } 
	  catch (IOException e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while generating the UML Sequence Diagram Png File from the text File: " + textFile + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);	
	  }
	  
	  /*
	  System.out.println("List: " + list);
	   
	  System.out.println("CREATE IMAGE-STEP 3");
	  */
	  
	  // Generated the UML Sequence Diagram Png File
	  file1 = list.get(0).getPngFile();
	  
	  //System.out.println("CREATE IMAGE-STEP 4");
	  
	  // Convert from file to string type
	  fileName = file1.toString();
	  
	  /*
	  System.out.println("File 1   : " + file1);
	  System.out.println("FileName : " + fileName);
	  
	  System.out.println("CREATE IMAGE-STEP 5");
	  */
	  
	  return fileName;
 	   
   }
   
   public void saveFinalSequenceDiagram(LoadUMLSequenceDiagramController umlSeqDiagramCon, int projectId) throws Exception
   {
      String errorMessage="", lineText="", lineType2="", datumCategory2="", datumValue = "";
      int userId=0, methodId=0, cmId=0, c=0, diagramLineNumber=0, methodLineNumber=0, 
    	  config=0, method=0, pos=0, stepId=0;
	  DatumCategory1 datumCategory1=DatumCategory1.None; 
      LineType1 lineType1=LineType1.None, 
    		    value0 = LineType1.DiagramStarts, 
    		    value1 = LineType1.DiagramEnds,
    		    value2 = LineType1.ConfigurationStarts, 
    		    value3 = LineType1.ConfigurationBody,
    		    value4 = LineType1.ConfigurationEnds,
    		    value5 = LineType1.ConnectionStarts, 
    		    value6 = LineType1.MethodStarts, 
    		    value7 = LineType1.MethodBody,
    		    value8 = LineType1.MethodEnds;
      
      UMLSequenceDiagramFinal umlSeqDiagRecord;
      ArrayList<UMLSequenceDiagramPlain> umlSeqDiagList = new ArrayList<UMLSequenceDiagramPlain>();
	      
      //System.out.println("Saving Data into the Sequence Diagram Final table ...");
      
	  // Load all the sequence diagram stored from this project Id
	  try 
	  {	 	
	     umlSeqDiagList = umlSeqDiagramCon.loadSequenceDiagramsPlain(projectId);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
		  
	  // For every element in the umlSeqDiagList ArrayList Loop 	  
	  for(c = 0; c < umlSeqDiagList.size(); c++) 
	  {  
	     userId            = umlSeqDiagList.get(c).getUserIdentifier(); 
		 methodId          = umlSeqDiagList.get(c).getMethodIdentifier(); 
		 //diagramLineNumber = umlSeqDiagList.get(c).getLineNumber();
		 lineText          = umlSeqDiagList.get(c).getLineText();
		 lineType1         = umlSeqDiagList.get(c).getLineType1();
		 lineType2         = umlSeqDiagList.get(c).getLineType2();
		 datumCategory1    = umlSeqDiagList.get(c).getDatumCategory1();
		 datumCategory2    = umlSeqDiagList.get(c).getDatumCategory2();
		 datumValue        = umlSeqDiagList.get(c).getDatumValue();
		 cmId              = umlSeqDiagList.get(c).getClassMethodIdentifier();
		 stepId            = umlSeqDiagList.get(c).getStepIdentifier();
		 
		 diagramLineNumber ++;
		 
         // Calculate the lineNumber value depending on the category of the lineType1 field

	     // DiagramStarts, DiagramEnds, ConfigurationStarts, ConfigurationBody, ConfigurationEnds                 
	     if( (lineType1).equals(value0) || (lineType1).equals(value1) ||
	    	 (lineType1).equals(value2) || (lineType1).equals(value3) ||
	    	 (lineType1).equals(value4) )
	     {	 
	        config++;
	        methodLineNumber = config;
	     }   
		 // ConnectionStarts
	     else 
	        if((lineType1).equals(value5))
	        {	
	           method = 0;
	           method++;
	           methodLineNumber = method;
	           pos = diagramLineNumber;
	        }   
	        else 
	           // MethodStarts
	           if((lineType1).equals(value6) )
	           {	   
	              if(diagramLineNumber == (pos + 1) )
	        	  {	  
	                 method++;
	                 methodLineNumber = method;
	        	  }
	        	  else
	        	  {
		             method = 0;  
		             method++;
		             methodLineNumber = method;
	        	  }
	           }  
	           else
	              // MethodBody // MethodEnds
	        	  if ((lineType1).equals(value7) || (lineType1).equals(value8)) 
	              {	    
	 	             method++;
	 	             methodLineNumber = method;
	 	          } 	 
	     
	     /*
	     System.out.println("Primary Key         : " + projectId + " " + userId + " " + methodId + " " + diagramLineNumber + methodLineNumber);
	     System.out.println("Project id          : " + projectId);
	     System.out.println("User id             : " + userId);
		 System.out.println("Method id           : " + methodId);
		 System.out.println("Diagram Line Number : " + diagramLineNumber);
		 System.out.println("Method Line Number  : " + methodLineNumber);
		 System.out.println("Line Text           : " + lineText);
		 System.out.println("Line Type 1         : " + lineType1);
		 System.out.println("Line Type 2         : " + lineType2);
		 System.out.println("Datum Category 1    : " + datumCategory1);
		 System.out.println("Datum Category 2    : " + datumCategory2);
		 System.out.println("Datum Value         : " + datumValue);
		 System.out.println("Class Method Id     : " + cmId);
		 System.out.println("Step Id: "+stepId);
		 */
	     
		 umlSeqDiagRecord = new UMLSequenceDiagramFinal(projectId, diagramLineNumber, userId,  
				                                        methodId, stepId, methodLineNumber, lineText, 
				                                        lineType1, lineType2, datumCategory1, 
				                                        datumCategory2, datumValue, cmId);
	     
		 try 
		 {	 	
	        umlSeqDiagramCon.saveUMLSequenceDiagramsFinal(umlSeqDiagRecord);
		 }  
		 catch(Exception e2)
		 {
		    errorMessage = e2.getMessage();
			throw new Exception(errorMessage);
	     }
	         
      }
	   
   }
   
   public DataType validateClassType(LoadUMLSequenceDiagramController umlSeqDiagramCon, FRLConfiguration frlCon,
                                     int projectId, String name, String type1) throws Exception
   {
      String errorMessage = "", packageName="", returnType1="", type2="";/*, specialCase="LocalDateTime"*/
      int dataTypeId=0;
      ClassMethod classMethod = null;
      DataType dataType=null;
      
      /*
      System.out.println("*** Inside Validate Class Type Method ***");
      
      System.out.println("Project Id : " + projectId);
      System.out.println("Name       : " + name);
      System.out.println("Type 1     : " + type1);
      
      System.out.println("Initialize Object Name : " + frlCon.initializeObjectName);
      System.out.println("Class Data Type        : " + frlCon.classDataType);
      */
      
      // Determine the Data Type of the Parameter
      try 
      {	 	
    	 //System.out.println("VALIDATING IF THE CLASS BELONGS TO A PERSONALIZED CLASS");
    	 
         classMethod = umlSeqDiagramCon.getClassDetails(projectId, type1, frlCon.initializeObjectName, frlCon.classDataType);

         //System.out.println("Class Method: " + classMethod);
         
         // The Parameter is a Personalized Class
         if(classMethod != null)
         {	
            packageName = classMethod.getPackageName();	
            returnType1 = classMethod.getReturnType1();
            dataTypeId  = 0;

            //System.out.println("CASE ONE-Validate Class Type");
         }
         else
         {
            // Verify if it a Primitive Data Type or a Java Data Type	 
            /*if(type1.contains(specialCase))
            {
               packageName = "java.time";
               returnType1 = frlCon.initializeObjectName;
               
               System.out.println("CASE TWO");
            }*/
        	 
        	/* 
        	System.out.println("VALIDATING IF THE CLASS BELONGS TO A PRIMIVITE OR NON PRIMITIVE CLASS");
        	
        	System.out.println("Project Id : " + projectId);
        	System.out.println("Type 1     : " + type1);
        	*/
        	 
        	// The Verify if the Parameter is a Primitive or Non Primitive Class 
       	    try 
    	    {	 	
    	       dataType = umlSeqDiagramCon.getPrimitiveClassDetails(projectId, type1);
    	    }  
    	    catch(Exception e1)
    	    { 
    	       errorMessage = e1.getMessage();
    		   throw new Exception(errorMessage);
    	    } 
       	    
       	    //System.out.println("Data Type: " + dataType);
       	    
            // Validate if classMethod is not equal to null
            if(dataType != null)
            {	
               packageName = dataType.getPackageName1();
               returnType1 = dataType.getReturnType1();
               dataTypeId  = dataType.getDataTypeIdentifier1();
               type2       = dataType.getDataTypeName1();

               //System.out.println("CASE TWO-Validate Class Type");
            }
         }
         
      }  
      catch(Exception e2)
      {
         errorMessage = e2.getMessage();
         throw new Exception(errorMessage);
      }
      
      // initializeObjectName=Constructor
      if(returnType1.equals(frlCon.initializeObjectName))
     	 //bluePrintObject2=Class
          type2 = frlCon.bluePrintObject2;
       else 
     	 // classDataType=Enum 
          if(returnType1.equals(frlCon.classDataType) )
             type2 = frlCon.classDataType;
          /*else
             type2 = returnType1;*/
   	    
      if(dataTypeId == 0)
      {	  
   	     try 
   	     {	 	
   	        dataTypeId = umlSeqDiagramCon.getDataTypeId(projectId, type2);
   	     }  
   	     catch(Exception e3)
   	     {
   	        errorMessage = e3.getMessage();
   		    throw new Exception(errorMessage);
   	     }
      }
      
      /*
      if( type2.equals(frlCon.bluePrintObject2) || 
    	  type2.equals(frlCon.classDataType) )
         dataType = new DataType(name, type2, packageName, type1, returnType1);
      */  

      /*      
      System.out.println("**********************************************");
      System.out.println("*** INFORMATION Validate Class Type Method ***");
       
      System.out.println("Name          : " + name);
      System.out.println("Data Type Id  : " + dataTypeId);
      System.out.println("Type 1        : " + type1);
      System.out.println("Type 2        : " + type2);
      System.out.println("Package Name  : " + packageName);
      System.out.println("Return Type 1 : " + returnType1);
      System.out.println("Data Type     : " + dataType);
      System.out.println("**********************************************");
      */
 
      dataType = new DataType (packageName, dataTypeId, type1, type2, returnType1);
      
      return dataType;

   }      
   
   public DataType getDataTypeDetails(LoadUMLSequenceDiagramController umlSeqDiagramCon, FRLConfiguration frlCon, 
		                              int projectId, String name1, String type1) throws Exception
   {
      String errorMessage="", type2="", type3="",type4,
    		 returnType1="", returnType2="", packageName1="", packageName2="";
      
      int progrLangId1=0, dataTypeId1=0, dataTypeId2=0;
      String[] parts;
      DataType dataType0=null, dataType1=null, dataType2=null, dataType4=null;
      DataCategory dataCategory1=null;
      
      /*
      String type4="";
      DataType dataType3=null;
      DataCategory dataCategory2=null;
      */
    		  
      /*
      System.out.println("INSIDE THE Get Data Type Details Method ... ");
      
      System.out.println("Project Id       : " + projectId);
      System.out.println("Parameter Name 1 : " + name1);
      System.out.println("Parameter Type 1 : " + type1);
      
      System.out.println("Get Data Type Details TYPE 1 -STEP 1");
      */
      
      // Validate if the type is an ArrayList
      if(type1.contains(frlCon.nonPrimitiveValue1))
      {	  
         type2 = frlCon.nonPrimitiveValue1;  
         type3 = type1.substring(type1.indexOf(frlCon.startClassDef) + 1, type1.indexOf(frlCon.endClassDef)); 
         
         //System.out.println("Get Data Type Details - CASE ONE");
      }   
      else
    	  // Validate if the type is an List
    	  if(type1.contains(frlCon.nonPrimitiveValue2))
    	  {	 
             //System.out.println("CASE ONE");
              
    	     type2 = frlCon.nonPrimitiveValue2;
             type3 = type1.substring(type1.indexOf(frlCon.startClassDef) + 1, type1.indexOf(frlCon.endClassDef));
             
             //System.out.println("Get Data Type Details - CASE TWO");

    	  }   
    	  else
    	     // Validate if the type is an Array 
      	     if ((type1.contains(frlCon.startArray) && type1.contains(frlCon.endArray)))
      	     {	
      	    	//System.out.println("Get Data Type Details - CASE THREE");
      	    	
     	        type2 = frlCon.nonPrimitiveValue3;
     	    	parts = type1.split(frlCon.findWhiteSpaces);
     	    	   
     	    	if(parts[0].contains(frlCon.startArray)) 
     	    	   type3 = parts[0].substring(0, parts[0].indexOf(frlCon.startArray));	
     	    	else
     	           type3 = parts[0];
     	    	
      	     } 
      	     else
      	       if (name1.contains(frlCon.startArray) && name1.contains(frlCon.endArray))	
      	       {
      	          //System.out.println("Get Data Type Details - CASE FOUR");
        	    	
         	      type2 = frlCon.nonPrimitiveValue3;
         	      //type3 = type1;
         	      parts = type1.split(frlCon.findWhiteSpaces);
   	    	   
      	    	  if(parts[0].contains(frlCon.startArray)) 
      	    	     type3 = parts[0].substring(0, parts[0].indexOf(frlCon.startArray));	
      	    	  else
      	             type3 = parts[0];

      	       }
      	       else
      	           // Validate if the type is an Interface
      	    	   if(type1.contains(frlCon.nonPrimitiveValue4))
      	    	   {	   
      	    	      type2 = frlCon.nonPrimitiveValue4;
      	    	      type3 = "";
      	    	      //System.out.println("Get Data Type Details - CASE FIVE");

      	    	   }    
      	    	   else
      	    	   {	

       	    	      //System.out.println("Get Data Type Details - CASE SIX");
      	    		  
       	    	      // Validate if the type1 is a Class or an Enum
      	    	      try
      	    	      {
      	    	         dataType0 = validateClassType(umlSeqDiagramCon, frlCon,
                                                       projectId, name1, type1);
      	    	      }
      	   	          catch(Exception e1)
      	   	          {
      	   	             errorMessage = e1.getMessage();
      	   	             throw new Exception(errorMessage);
      	   	          }
      	    	      
      	    	      //System.out.println("Data Type 0: "+ dataType0);
      	    	      
      	    	      // Class or an Enum Type
      	    	      if(dataType0 != null)
      	    	      {	  
          	    	     type2        = dataType0.getDataTypeName2();  
      	    	         packageName1 = dataType0.getPackageName1();
      	    	         returnType1  = dataType0.getReturnType1();
      	    	      }
      	    	      else
      	    	         // Primitive Type 
      	    	         type2 = type1;
      	    	      
      	    	      type3 = "";

      	    	   }
      
     /* 
     System.out.println("DETERMINED THE DATA TYPE OF THE TYPE 1 OF THE PARAMETER");
     System.out.println("AND DIVIDED DATA TYPE 1 IN DATA TYPE 2 AND DATA TYPE 3");
     
	 System.out.println("Package Name 1 : " + packageName1);
	 System.out.println("Return Type 1  : " + returnType1);
     System.out.println("Type 1         : " + type1);
	 System.out.println("Type 2         : " + type2);
	 System.out.println("Type 3         : " + type3);
     */
     
     // Get the Data Type Identifier for the Type 2 from the Database
	 try 
	 {	 	
	     /*System.out.println("Data Type Details Method -STEP 2");
	     
		 System.out.println("*** => BEFORE Get Data Type Details 1 ***");
		 
		 System.out.println("Programming Language : " + frlCon.programmingLanguage);
		 System.out.println("Type 2               : " + type2);
		 */
		 
		 //System.out.println("Get Data Type Details TYPE 2 -STEP 2");
		 
		 // Remove all the Whitespace from type 2
		 type2 = type2.replaceAll("\\s+","");
		 
		 try 
		 {
	        dataType1 = umlSeqDiagramCon.getDataTypeDetails1(frlCon.programmingLanguage, type2);
		 }
		 catch(Exception e2)
		 {
		    errorMessage = e2.getMessage();
		    
		    //System.out.println("AN OCCURRED ERROR IN Data Type Details : "+ errorMessage);
		    
		    throw new Exception(errorMessage);
		 }
	     
		 /*System.out.println("*** => AFTER Get Data Type Details 1 ***");
	     System.out.println("DATA TYPE 1: " + dataType1);
	     */
	     //System.out.println("BEFORE Get Prog Language Identifier ...");
	     
	     
	     if(dataType1 != null)
	     {	 
	        progrLangId1  = dataType1.getProgLanguageIdentifier();
	        dataTypeId1   = dataType1.getDataTypeIdentifier1();
	        dataCategory1 = dataType1.getDataCategory1();
	     }
         else
         {
    	     errorMessage = "Error XXXX: Occurred while getting the Data Type of " + System.lineSeparator();
    	     errorMessage = errorMessage + "Programming Language : " + frlCon.programmingLanguage + System.lineSeparator();
    	     errorMessage = errorMessage + "Parameter Name : " + name1 + System.lineSeparator();
    	     errorMessage = errorMessage + "Type 1: " + type1 + System.lineSeparator();
    	     errorMessage = errorMessage + "Type 2: " + type2 + System.lineSeparator();
    	     errorMessage = errorMessage + "Error: The Data Type is NULL";
    		 throw new Exception(errorMessage);	
         }
	     
	     /*
	     System.out.println("DETERMINED THE DATA TYPE OF THE TYPE 2 OF THE PARAMETER");
	     System.out.println("Name                 1 :" + name1);
	     System.out.println("Type                 1 :" + type1);
	     System.out.println("Type                 2 :" + type2);
	     
	     System.out.println("Programming Language 1 :" + progrLangId1);
	     System.out.println("Data Type Id         1 :" + dataTypeId1);
	     System.out.println("Data Type Category   1 :" + dataCategory1);
	     */
	     
	  }  
	  catch(Exception e3)
	  {
	     errorMessage = e3.getMessage();
	     throw new Exception(errorMessage);
	  }
	 
      //if( StringUtils.isBlank(type3) == false || StringUtils.isEmpty(type3) == false)
	  if(type3 != null && !type3.trim().isEmpty()) 
      {
		 /* 
		 System.out.println("Get Data Type Details TYPE 3 -STEP 3");
    	  
		 System.out.println("Project Id : " + projectId);
		 System.out.println("Name 1     : " + name1);
		 System.out.println("Type 3     : " + type3);
		 */
		 
         // Validate if the type3 is a Class or an Enum
	     try
	     {
	        dataType2 = validateClassType(umlSeqDiagramCon, frlCon,
                                          projectId, name1, type3);
	    	      
	    	// Class or an Enum Type
	    	if(dataType2 != null)
	    	{	  
 	    	   type4        = dataType2.getDataTypeName2();  
 	    	   //type5        = dataType2.getDataTypeName2();  
	    	   packageName2 = dataType2.getPackageName1();
	    	   returnType2  = dataType2.getReturnType1();
	    	   dataTypeId2  = dataType2.getDataTypeIdentifier1();
	    	}
	    	else
	    	   // Primitive Type 
	    	   type4 = type1;

	    	/*
	    	System.out.println("Type 4         : " + type4);
	    	System.out.println("Package Name 2 : " + packageName2);
	    	System.out.println("Return Type  2 : " + returnType2);    	
	    	System.out.println("Data Type Id 2 : " + dataTypeId2);   
	    	*/ 	
	    	
	      }
	   	  catch(Exception e4)
	   	  {
	   	     errorMessage = e4.getMessage();
	   	     throw new Exception(errorMessage);
	   	  }
	      
	      /*
	      // Get the Data Type Identifier from the database
		  try 
		  {	 	
			 System.out.println("Get Data Type Details TYPE 3 -STEP 4");
			     
			 System.out.println("BEFORE GetDataTypeDetails1 ");
			  
		     dataType3 = umlSeqDiagramCon.getDataTypeDetails1(frlCon.programmingLanguage, type4);
		     
		     System.out.println("AFTER GetDataTypeDetails1 ");
		     
		     if(dataType3 != null)
		     {	
		        dataTypeId2   = dataType3.getDataTypeIdentifier1();
		        dataCategory2 = dataType3.getDataCategory1();
		     		        
		        System.out.println("Data Type Id 2       : " + dataTypeId2);
		        System.out.println("Data Type Category 2 : " + dataCategory2);
		        
		        
		      //System.out.println("Name                 1 :" + name1);
		      //System.out.println("Type                 3 :" + type3);
		      //System.out.println("Type                 4 :" + type4);
		        
		        //System.out.println("Package Name         2 :" + packageName2);
		        //System.out.println("Return Type          2 :" + returnType2);
		        
		        
		     }
		  }  
		  catch(Exception e5)
		  {
		     errorMessage = e5.getMessage();
		     throw new Exception(errorMessage);
		  }
		  */

      }
      
      /*
      System.out.println("**************************************************");
      System.out.println("FINAL INFORMATION IN GetDataTypeDetails METHOD ");
      System.out.println("**************************************************");
      
      System.out.println("Programming Language 1 : " + progrLangId1);
	  System.out.println("Name                 1 : " + name1);
	  System.out.println("Data Type Id         1 : " + dataTypeId1);
	  System.out.println("Type                 1 : " + type1);
	  System.out.println("Data Type Category   1 : " + dataCategory1);
	  System.out.println("Package Name         1 : " + packageName1);
	  System.out.println("Type                 1 : " + type1);
	  System.out.println("Return Type          1 : " + returnType1);
	  System.out.println("Data Type Id         2 : " + dataTypeId2);
	  System.out.println("Type                 3 : " + type3);
	  System.out.println("Data Type Category   2 : " + dataCategory2);
	  System.out.println("Package Name         2 : " + packageName2);
	  System.out.println("Type                 3 : " + type3);
	  System.out.println("Return Type          2 : " + returnType2);
	  System.out.println("**************************************************");
      */
      
      // Build the DataType4 with all the information about the parameter
      /*dataType4 = new DataType(progrLangId1, name1, dataTypeId1, type1, dataCategory1, packageName1, 
	                           type1, returnType1, dataTypeId2, type3, dataCategory2, packageName2, 
	                           type3, returnType2);
	                           */
      
      if(dataTypeId2 == 0)
      {
         dataType4 = new DataType(progrLangId1, name1, dataTypeId1, type1, dataCategory1, packageName1, 
                                  type1, returnType1);	  
      }
      else
      {
         dataType4 = new DataType(progrLangId1, name1, dataTypeId2, type1, dataCategory1, packageName2, 
                                  type1, returnType2);	 
      }
      
      //System.out.println("DATA TYPE 4: " + dataType4);
      
      return dataType4;
      
   }
   
   public void saveParametersSequenceDiagram(FRLConfiguration frlCon, LoadUMLSequenceDiagramController umlSeqDiagramCon, int projectId) throws Exception
   {
      String errorMessage="", methodFullName="", parameters1="", element="", type0="", parameterName0="",
    		 dataTypeName1="", packageName1="",  
    		 className1="", returnType1="";
            
      int c=0, i=0, methodId=0, startPos=0, endPos=0, parameterId=0, 
    	  dataTypeId1=0, progLangId=0, cmId1=0;
      String[] parameters2 = new String[50], parts = new String[2];
     
	  ArrayList<MethodDataSequenceDiagram> methodSeqDiagram = new ArrayList<MethodDataSequenceDiagram>(); 
	  ParameterSequenceDiagram parameterSeqDiagram1;/*, parameterSeqDiagram2;*/
	  ClassMethod classMethod;
	  DataType dataType;
	  
      /*packageName2="", dataTypeName2="", className2="", , returnType2=""
       * int dataTypeId2=0, cmId2=0*/
	  
	  // Initialize the Arrays
	  Arrays.fill(parameters2,"");
	  Arrays.fill(parts,"");
	  
	  /*
	  System.out.println("*** INSIDE SAVE PARAMETERS Sequence Diagram METHOD ***");
	  System.out.println("Project Id: " + projectId);
	  */
	  
	  // Get the Class Method Id that is related to this methodName2 and Owner
	  try 
	  {
	     methodSeqDiagram = umlSeqDiagramCon.getMethodSequenceDiagrams(projectId);
	  } 
	  catch (Exception e2) 
	  { 
		errorMessage = e2.getMessage();
	    throw new Exception(errorMessage);
	  }
	  
	  //System.out.println("List of Methods: " + methodSeqDiagram.size());
      	
	  // Loop to iterate all the records from the methodSeqDiagram ArrayList
	  for (c = 0; c < methodSeqDiagram.size(); c++) 
	  { 
	     // Getting all the fields from the methodSeqDiagram ArrayList
		 methodId       = methodSeqDiagram.get(c).getMethodIdentifier();
		 methodFullName = methodSeqDiagram.get(c).getMethodFullName();
		 
		 //methodFullName = methodSeqDiagram.get(c).getFullName();
		 		  
		 // Initialize the Arrays
		 Arrays.fill(parameters2, "");
		 Arrays.fill(parts, "");
		 
		 // Get the parameters of the method that are located between ()
		 startPos    = methodFullName.indexOf(frlCon.startParameters) + 1; 
         endPos      = methodFullName.indexOf(frlCon.endParameters);
         parameters1 = methodFullName.substring(startPos, endPos);
         
		 if(parameters1.contains(frlCon.valueDelimiter))
		    parameters2 = parameters1.split(frlCon.valueDelimiter); 
		 else
		    parameters2[0] = parameters1;
		 
		 /*
		 System.out.println("SAVE PARAMETERS METHOD - STEP 1");
		 System.out.println("Counter           : " + c);
		 System.out.println("Method Id         : " + methodId);
		 System.out.println("Method Full Name  : " + methodFullName);
		 
		 System.out.println("Parameters        : " + parameters1);
		 System.out.println("Parameter 2 Array : " + Arrays.toString(parameters2));
		 System.out.println("Length            : " + parameters2.length);
		 */
		 
		 // Validate that the method has parameters
		 if (parameters2.length > 0) 
		 {	 
		    // Initialize the parameter Counter
		    parameterId = 0;
		 
		    // Iterating over the Parameter2 Array
	        for (i = 0; i < parameters2.length; i++) 
	        { 
	    	   element = parameters2[i].trim();
	    	   
	    	   /*
	    	   if(element.isEmpty() == false)
	    	      System.out.println("Element : " + element);
	    	      */
	    	
	    	   //element = "UserLevel[] classArray";
	    	   
	    	   /*
	    	   System.out.println("SAVE PARAMETERS METHOD - STEP 2");
	    	   
	    	   System.out.println("Counter i : " + i);
	    	   System.out.println("Element   : " + element);
	    	   */
	           
	           if(element != null && !element.trim().isEmpty())
	           {	
	              parts           = element.split(frlCon.findWhiteSpaces);
	              type0           = parts[0];
	              parameterName0  = parts[1];
	           
	              //System.out.println("SAVE PARAMETERS METHOD - STEP 3");
	              
	              
	              // Increase the Parameter Counter
	              parameterId++;

	              /*
	              System.out.println("Parts             : " + Arrays.toString(parts));
	              System.out.println("Parameter Id      : " + parameterId);
	              System.out.println("Parameter Type 0  : " + type0); 
	              System.out.println("Parameter Name 0  : " + parameterName0); 

	              System.out.println("SAVE PARAMETERS METHOD - STEP 4");
	              
	              System.out.println("BEFORE Get Data Type Details METHOD");
	              */
	              
	              // Get the Data Type Identifier of the Parameter Type
	              try
	              {
	                 dataType = getDataTypeDetails(umlSeqDiagramCon, frlCon, projectId, parameterName0, type0);
	              }
	        	  catch(Exception e3)
	        	  {
	        	     errorMessage = e3.getMessage();
	        	 	 throw new Exception(errorMessage);
	        	  }
	              
	              /*
	              System.out.println("AFTER Get Data Type Details METHOD");
	              System.out.println("Data Type : " + dataType);
	              */
	              
	              
	              if(dataType != null)
	              {	  
	                 // Get the Information from the dataType Record
	                 progLangId    = dataType.getProgLanguageIdentifier();
	                 dataTypeId1   = dataType.getDataTypeIdentifier1(); 
	                 dataTypeName1 = dataType.getDataTypeName1();
	                 packageName1  = dataType.getPackageName1();
	                 className1    = dataType.getClassName1();
	                 returnType1   = dataType.getReturnType1();
			   
	                 /*
	                 dataTypeId2   = dataType.getDataTypeIdentifier2(); 
	                 dataTypeName2 = dataType.getDataTypeName2();
	                 packageName2  = dataType.getPackageName2();
	                 className2    = dataType.getClassName2();
	                 returnType2   = dataType.getReturnType2();
	                 */
			   
	              /*   
	              System.out.println("SAVE PARAMETERS METHOD - STEP 5");
	              System.out.println("INFORMATION");
	              System.out.println("Programming Language Id : " + progLangId);
			   
	              System.out.println("Data Type Id 1    : " + dataTypeId1);
	              System.out.println("Data Type Name 1  : " + dataTypeName1);
	              System.out.println("Package Name 1    : " + packageName1);
	              System.out.println("Class Name 1      : " + className1);
	              System.out.println("Return Type 1     : " + returnType1);
	              */
	           
	              /*
	              System.out.println("Data Type Id 2    : " + dataTypeId2);
	              System.out.println("Data Type Name 2  : " + dataTypeName2);
	              System.out.println("Package Name 2    : " + packageName2);
	              System.out.println("Class Name 2      : " + className2);
	              System.out.println("Return Type 2     : " + returnType2);
	              */
	              }
	              else
	              {
	         	     errorMessage = "Error XXXX: Occurred while getting the Method Data Type of " + System.lineSeparator();
	         	     
	         	     errorMessage = errorMessage + "Project Id : " + projectId + System.lineSeparator();
	         	     errorMessage = errorMessage + "Method Id : " + methodId + System.lineSeparator();
	         	     errorMessage = errorMessage + "Method Name : " + methodFullName + System.lineSeparator();
	         	     errorMessage = errorMessage + "Parameter Name : " + parameterName0 + System.lineSeparator();
	         	     errorMessage = errorMessage + "Type: " + type0 + System.lineSeparator();
	         	     errorMessage = errorMessage + "Error: The Data Type is NULL";
	         		 throw new Exception(errorMessage);	
	              }
	              
	              
	              cmId1=0; //cmId2=0;
	              
	              // Validate if returnType1 is an Enum or a Class
	              if(returnType1.equals(frlCon.initializeObjectName) || returnType1.equals(frlCon.classDataType))
	              {	  
	                 // Build a new classMethod Record
	            	  
	            	 classMethod = new ClassMethod(projectId, packageName1, className1, returnType1); 
	            	  
	                 // Get the cmid value
	        	     try 
	        	     {
	        	        cmId1 = umlSeqDiagramCon.getClassMethodId(classMethod);
	        	     }
	        	     catch(Exception e4)
	        	     {
	        	        errorMessage = e4.getMessage();
	        	 	    throw new Exception(errorMessage);
	        	     }
	        	     
	        	     /*
	        	     System.out.println("SAVE PARAMETERS METHOD - STEP 6");
	        	     System.out.println("Cm Id 1 : " + cmId1);
	        	     */
	        	     
	              }
	              /*else
		              // Validate if returnType2 is an Enum or a Class
		              if(returnType2.equals(frlCon.initializeObjectName) || returnType2.equals(frlCon.classDataType))
		              {	  
		                 // Build a new classMethod Record
		            	  
		            	 classMethod = new ClassMethod(projectId, packageName2, className2, returnType2); 
		            	  
		                 // Get the cmid value
		        	     try 
		        	     {
		        	        cmId2 = umlSeqDiagramCon.getClassMethodId(classMethod);
		        	     }
		        	     catch(Exception e5)
		        	     {
		        	        errorMessage = e5.getMessage();
		        	 	    throw new Exception(errorMessage);
		        	     }
		        	     
		        	     System.out.println("SAVE PARAMETERS METHOD - STEP 7");
		        	     System.out.println("Cm Id 1 : " + cmId1);
		        	     
		              }
		              */
	              
	              /*
	              System.out.println("SAVE PARAMETERS METHOD - STEP 8");
	              System.out.println("INFORMATION");
	              
	              System.out.println("*** Information before inserting the parameter: DATA TYPE ID 1 ***");
	              System.out.println("Project Id         : " + projectId);
	              System.out.println("Method Id          : " + methodId);
	              System.out.println("Parameter Id       : " + parameterId);
	              System.out.println("Programing Lang Id : " + progLangId);
	              System.out.println("Parameter Name 0   : " + parameterName0);
	              System.out.println("Data Type Id 1     : " + dataTypeId1);
	              System.out.println("Data Type Name 1   : " + dataTypeName1);
	              System.out.println("Package Name 1     : " + packageName1);
	              System.out.println("Class Name 1       : " + className1);
	              System.out.println("Return Type 1      : " + returnType1);
	              System.out.println("CM ID 1            : " + cmId1);
	              */
	              
	              
	              // Buid a new Parameter Sequence Diagram Record
	              parameterSeqDiagram1 = new ParameterSequenceDiagram(projectId, methodId, parameterId, 
	            		                                              progLangId, parameterName0, dataTypeId1, 
	            		                                              dataTypeName1, packageName1, className1, 
	            		                                              returnType1, cmId1);
	        
	              /*
	              System.out.println("SAVE PARAMETERS METHOD - STEP 9");
	              System.out.println("BEFORE SAVING A PARAMETER CASE 1");
	              */
	             
	              
	              // Save the parameters of this method into the Database
	              try 
	              {	 	
	                 umlSeqDiagramCon.saveParameterSequenceDiagram(parameterSeqDiagram1);
	                 
	                 //System.out.println("SAVING A PARAMETER - STEP 1");
	              }  
	              catch(Exception e6)
	              {
	                 errorMessage = e6.getMessage();
	            	 throw new Exception(errorMessage);
	              }
	              
	              //System.out.println("AFTER SAVING A PARAMETER CASE 1");
	              
	              /*
	              if(dataTypeId2 > 0)
	              {
		              // Buid a new Parameter Sequence Diagram Record
		              parameterSeqDiagram2 = new ParameterSequenceDiagram(projectId, methodId, parameterId, 
		            		                                             progLangId, parameterName0, dataTypeId2, 
		            		                                             dataTypeName2, packageName2, className2, 
		            		                                             returnType2, cmId2);
		              
		              System.out.println("SAVE PARAMETERS METHOD - STEP 10");
		              System.out.println("INFORMATION");
		              
		              System.out.println("*** Information before inserting the parameter: DATA TYPE ID 2 ***");
		              System.out.println("Project Id         : " + projectId);
		              System.out.println("Method Id          : " + methodId);
		              System.out.println("Parameter Id       : " + parameterId);
		              System.out.println("Programing Lang Id : " + progLangId);
		              System.out.println("Parameter Name 0   : " + parameterName0);
		              System.out.println("Data Type Id   2   : " + dataTypeId2);
		              System.out.println("Data Type Name 2   : " + dataTypeName2);
		              System.out.println("Package Name 2     : " + packageName2);
		              System.out.println("Class Name 2       : " + className2);
		              System.out.println("Return Type 2      : " + returnType2);
		              System.out.println("CM ID 2            : " + cmId2);
		              
		              
		              System.out.println("BEFORE SAVING A PARAMETER CASE 2");
		              
		              // Save the parameters of this method into the Database
		              try 
		              {	 	
		                 umlSeqDiagramCon.saveParameterSequenceDiagram(parameterSeqDiagram2);
		                 
		                 //System.out.println("SAVING A PARAMETER - STEP 2");
		              }  
		              catch(Exception e7)
		              {
		                 errorMessage = e7.getMessage();
		            	 throw new Exception(errorMessage);
		              }	 
		              
		              System.out.println("AFTER SAVING A PARAMETER CASE 2");
		              
		              
	              }
	              */
	      
	              
	           } 
	        } 
		 }  
		 
		 
	  }		 
   }
   
   
   public void saveReturnTypesSequenceDiagram(FRLConfiguration frlCon, LoadUMLSequenceDiagramController umlSeqDiagramCon, int projectId) throws Exception
   {
      String errorMessage="", methodShortName="", methodFullName="", methodReturnTypeName="",
    		 dataTypeName1="", dataTypeName2 = "", packageName1="", packageName2="", packageName3="",
    		 className1="", className2="", className3="", returnType1="", returnType2="", returnType3="",
    		 methodReturnTypeName1="", methodReturnTypeName2="", methodReturnTypeName3="", 
    		 returnTypeName="", returnTypeName1="", rest="";
      
      int c=0, methodId=0, returnTypeId=0, 
    	  dataTypeId1=0, dataTypeId2=0, progLangId=0, cmId1=0, cmId2=0, 
    	  methodCmId=0, methodReturnTypeId=0;
      
      boolean arrayFlag=false;
     
	  ArrayList<MethodDataSequenceDiagram> methodSeqDiagram = new ArrayList<MethodDataSequenceDiagram>(); 
	  ReturnTypeSequenceDiagram returnTypeSeqDiagram;
	  ClassMethod classMethod1, classMethod2, classMethod3;
	  DataType dataType;
	  
	  //System.out.println("*** Inside the Save Return Type Sequence Diagram Method ***");
	  
	  // Get the Class Method Id that is related to this methodName2 and Owner
	  try 
	  {
	     methodSeqDiagram = umlSeqDiagramCon.getMethodSequenceDiagrams(projectId);
	  } 
	  catch (Exception e1) 
	  { 
		errorMessage = e1.getMessage();
	    throw new Exception(errorMessage);
	  }
      
	  //System.out.println("STEP 1");
	  
	  // Loop to iterate all the records from the methodSeqDiagram ArrayList
	  for (c = 0; c < methodSeqDiagram.size(); c++) 
	  { 
	     // Getting all the fields from the methodSeqDiagram ArrayList
		 methodId             = methodSeqDiagram.get(c).getMethodIdentifier();
		 methodShortName      = methodSeqDiagram.get(c).getMethodShortName();
		 methodFullName       = methodSeqDiagram.get(c).getMethodFullName();
		 methodCmId            = methodSeqDiagram.get(c).getClassMethodIdentifier();
		 methodReturnTypeName  = methodSeqDiagram.get(c).getReturnTypeName();
		 methodReturnTypeId    = methodSeqDiagram.get(c).getReturnTypeIdentifier();
		 methodReturnTypeName1 = methodSeqDiagram.get(c).getClassMethodReturnType1();
		 methodReturnTypeName2 = methodSeqDiagram.get(c).getClassMethodReturnType2();
		 
		 /*
		 System.out.println("************************************************");
		 System.out.println("Method Id               : " + methodId);
		 System.out.println("Method Short Name       : " + methodShortName);
		 System.out.println("Method Full Name        : " + methodFullName);
		 
		 System.out.println("Method Class Method Id  : " + methodCmId);
		 

		 System.out.println("Method Return Type Id   : " + methodReturnTypeId);
		 
		 System.out.println("Method Return Type Name 1 : " + methodReturnTypeName1);
		 System.out.println("Method Return Type Name 2 : " + methodReturnTypeName2);
		 */
		 
		 methodReturnTypeName = methodReturnTypeName.trim();
		 
		 /*
		 System.out.println("Method Return Type Name =>:" + methodReturnTypeName);
		 
		 System.out.println("************************************************");
		 
		 System.out.println("STEP 2");
		 */
		 
		 
		 //String value = frlCon.nonPrimitiveValue1;
		 /*
		 if(methodReturnTypeName.equals("ArrayList") )
		 {
			 System.out.println("HERE I AM");
		 }
		 */
		 
		 // Initialize variables
		 methodReturnTypeName3 = "";
		 className3 = "";
		 packageName3 = "";
		 returnTypeName = "";
		 returnTypeName1 = "";
		 arrayFlag = false;
		 
		 if( methodReturnTypeName.equals("Class")   || 
			 methodReturnTypeName.equals("ArrayList") ||
			 methodReturnTypeName.equals("List") ||
			 methodReturnTypeName.equals("Array")
			)
		 {	 
             // Remove the []
         	
			 //System.out.println("ENTRE A LA VALIDACION");
			 
         	if(methodReturnTypeName2.contains(frlCon.startArray)) 
         	{	
    	       methodReturnTypeName3 = methodReturnTypeName2.substring(0, methodReturnTypeName2.indexOf(frlCon.startArray));
    	       
    	       /*System.out.println("CASE ARRAY");
    	       System.out.println("Method Return Type Name 3 : " + methodReturnTypeName3);
    	       */
    	       
    	       arrayFlag = true;
    	       rest = methodReturnTypeName2.substring(methodReturnTypeName2.indexOf(frlCon.startArray), methodReturnTypeName2.length());
    	       
    	       //System.out.println("Rest: "+rest);
         	}	   
    	    else
    	    {	
    	       methodReturnTypeName3 = methodReturnTypeName2;
    	       //System.out.println("CASE NO ARRAY");
    	    }
         	
         	/*
         	System.out.println("Method Return Type Name 3: " + methodReturnTypeName3);
         	System.out.println("STEP 3");
         	*/
         	
		    try
		    {
		       classMethod1 = getComponentsClass(projectId, methodReturnTypeName3, frlCon);
	        
		       className3   = classMethod1.getClassName();
	           packageName3 = classMethod1.getPackageName();
		    }
		    catch (Exception e2) 
		    { 
	           errorMessage = e2.getMessage();
		       throw new Exception(errorMessage);
		    }
		    
		    returnTypeName = className3;
		    
		    /*
	        System.out.println("Package Name 3    : " + packageName3);
	        System.out.println("Class Name 3      : " + className3);
		    
		    System.out.println("RETURN TYPE NAME: "+ returnTypeName);
		    
		    System.out.println("STEP 4");
		    */
		    
		    if( methodReturnTypeName.equals("Class") )
		    {	
		    	returnTypeName1 = returnTypeName;
		    	//System.out.println("RETURN TYPE NAME 1 - CASE 1");
		    }	
		    else
		       if(methodReturnTypeName.equals("ArrayList") || 
		          methodReturnTypeName.equals("List"))
		       {	
		          returnTypeName1 = methodReturnTypeName + frlCon.startClassDef + returnTypeName + frlCon.endClassDef;
		          //System.out.println("RETURN TYPE NAME 1 - CASE 2");
		       }
		       else
		          if(methodReturnTypeName.equals("Array")) 
		          {	  
		             returnTypeName1 = methodReturnTypeName + frlCon.startArray + returnTypeName + frlCon.endArray;
		             //System.out.println("RETURN TYPE NAME 1 - CASE 3");
		          }  
		    
		    //System.out.println("STEP 5");
		    
		 }
		 else
		 {
			 className3   = "";
			 packageName3 = "";
			 
			 returnTypeName = methodReturnTypeName;
			 
			 returnTypeName1 = returnTypeName;
			 
			 /*
			 System.out.println("RETURN TYPE NAME 1 - CASE 4");
			 
			 System.out.println("STEP 6");
			 */
		 }
		 
		 /*
		 System.out.println("RETURN TYPE NAME 1: " + returnTypeName1);

         
         System.out.println("Method Return Type Name : " + methodReturnTypeName);
         System.out.println("Return Type Name        : " + returnTypeName);
         System.out.println("Return Type Name   1    : " + returnTypeName1);
         */
         
         
         // Initialize Variables
         progLangId     = 0;
         dataTypeId1    = 0; 
         dataTypeName1  = "";
         packageName1   = "";
         className1     = "";
         returnType1    = "";
	   
         dataTypeId2    = 0; 
         dataTypeName2  = "";
         packageName2   = "";
         className2     = "";
         returnType2    = "";
         returnType3    = "";
         
         
         //System.out.println("STEP 7");
         
         // Get the Data Type Identifier of the Parameter Type
         try
         {
            dataType = getDataTypeDetails(umlSeqDiagramCon, frlCon, projectId, returnTypeName1, returnTypeName1);
         }
         catch (Exception e1) 
   	     { 
   		    errorMessage = e1.getMessage();
   	        throw new Exception(errorMessage);
   	     }
      
         //System.out.println("STEP 8");
         
         if(dataType != null)
         {	 
            // Get the Information from the dataType Record
            progLangId     = dataType.getProgLanguageIdentifier();
            dataTypeId1    = dataType.getDataTypeIdentifier1(); 
            dataTypeName1  = dataType.getDataTypeName1();
            packageName1   = dataType.getPackageName1();
            className1     = dataType.getClassName1();
            returnType1    = dataType.getReturnType1();
	   
            dataTypeId2    = dataType.getDataTypeIdentifier2(); 
            dataTypeName2  = dataType.getDataTypeName2();
            packageName2   = dataType.getPackageName2();
            className2     = dataType.getClassName2();
            returnType2    = dataType.getReturnType2();
         }

         /*
         System.out.println("STEP 9");
         
         System.out.println("*** DATA TYPE DETAILS RESULTS ***");
         System.out.println("Programming Language Id : " + progLangId);
	   
         System.out.println("Data Type Id 1    : " + dataTypeId1);
         System.out.println("Data Type Name 1  : " + dataTypeName1);
         System.out.println("Package Name 1    : " + packageName1);
         System.out.println("Class Name 1      : " + className1);
         System.out.println("Return Type 1     : " + returnType1);
      
         System.out.println("Data Type Id 2    : " + dataTypeId2);
         System.out.println("Data Type Name 2  : " + dataTypeName2);
         System.out.println("Package Name 2    : " + packageName2);
         System.out.println("Class Name 2      : " + className2);
         System.out.println("Return Type 2     : " + returnType2);
         
         System.out.println("STEP 10");
         */
         
         cmId1=0; cmId2=0;
         
         // Validate if returnType1 is an Enum or a Class
         if(returnType1.equals(frlCon.initializeObjectName) || returnType1.equals(frlCon.classDataType))
         {	  
            //System.out.println("STEP 11");
        	 
            // Build a new classMethod Record
       	    classMethod2 = new ClassMethod(projectId, packageName1, className1, returnType1); 
       	  
            // Get the cmid value
   	        try 
   	        {
   	           cmId1 = umlSeqDiagramCon.getClassMethodId(classMethod2);
   	        }
   	        catch(Exception e)
   	        {
   	           errorMessage = e.getMessage();
   	 	       throw new Exception(errorMessage);
   	        }
   	     
   	        //System.out.println("Cm Id 1 : " + cmId1);
   	     
   	        
         }
         /*else
             // Validate if returnType1 is an Enum or a Class
             if(returnType2.equals(frlCon.initializeObjectName) || returnType2.equals(frlCon.classDataType))
             {	  
                System.out.println("STEP 12");
            	 
                // Remove the []
            	
            	if(returnType2.contains(frlCon.startArray)) 
       	    	   returnType3 = returnType2.substring(0, returnType2.indexOf(frlCon.startArray));	
       	    	else
       	    		returnType3 = returnType2;
            	
            	System.out.println("Return Type 3 : " + returnType3);
            	 
                // Build a new classMethod Record
           	    classMethod3 = new ClassMethod(projectId, packageName2, className2, returnType3); 
           	    
           	    System.out.println("STEP 13");
           	 
                // Get the cmid value
       	        try 
       	        {
       	           cmId2 = umlSeqDiagramCon.getClassMethodId(classMethod3);
       	        }
       	        catch(Exception e)
       	        {
       	           errorMessage = e.getMessage();
       	 	       throw new Exception(errorMessage);
       	        }
       	     
       	        System.out.println("Cm Id 2 : " + cmId2);
       	       
       	     
             }
          */
 
         returnTypeId++;
         
         if(arrayFlag == true)
         {	 
            returnTypeName1 = returnTypeName1 + rest;
            
            //System.out.println("STEP 14");
            
            try
            {
            	methodReturnTypeId  = umlSeqDiagramCon.getReturnType("Array");
            }
            catch(Exception e)
   	        {
   	           errorMessage = e.getMessage();
   	 	       throw new Exception(errorMessage);
   	        }
            
            //System.out.println("Method Return type id array: " + methodReturnTypeId);
            
         } 
         
         if(returnType1.equals("Enum"))
         {
             returnTypeName1 = returnTypeName1 + rest;
             
             //System.out.println("STEP 15");
             
             try
             {
                methodReturnTypeId  = umlSeqDiagramCon.getReturnType("Enum");
             }
             catch(Exception e)
    	     {
    	        errorMessage = e.getMessage();
    	 	    throw new Exception(errorMessage);
    	     } 
 
         }
         
         //System.out.println("STEP 16");
         
         // Buid a new Return Type Sequence Diagram Record
         /*if(dataTypeId2 > 0)
         {	 
            returnTypeSeqDiagram = new ReturnTypeSequenceDiagram(projectId, methodId, returnTypeId, 
       		                                                     progLangId, returnTypeName1, dataTypeId2, 
       		                                                     dataTypeName2, packageName2, className2, 
       		                                                     returnType2, cmId2, methodReturnTypeId);
         }
         else
         {*/
         returnTypeSeqDiagram = new ReturnTypeSequenceDiagram(projectId, methodId, returnTypeId, 
           		                                              progLangId, returnTypeName1, dataTypeId1, 
           		                                              dataTypeName1, packageName1, className1, 
           		                                              returnType1, cmId1, methodReturnTypeId);
         //}
   
         //System.out.println("STEP 17");
         
         // Save the parameters of this method into the Database
         try 
         {	 	
            umlSeqDiagramCon.saveReturnTypeSequenceDiagram(returnTypeSeqDiagram);
         }  
         catch(Exception e4)
         {
            errorMessage = e4.getMessage();
       	    throw new Exception(errorMessage);
         }
         
	  }		 
   }   
   
   
   /*
   public String getAttributeNames(String textFile, String value) throws Exception
   {
      String errorMessage1="", errorMessage2="", line="", attribute="", attributeStr="";
    		  
      String[] parts;
	  FileInputStream fstream;
	  BufferedReader br;
	  File file;
	  
	  attributeStr = value + " ";
	  
	  System.out.println("Inside the Get Attribute Names Method ...");
	  System.out.println("Text File     : " + textFile);
	  System.out.println("Value         : " + value);
	  System.out.println("Attribute Str : " + attributeStr);
	  
	  // Validate if the Parameter Path Text File exists
	  file = new File(textFile);
	  
	  if (!file.exists())
	  {
		 errorMessage1 = "Error XXXX: The Text File Name: " + textFile + System.lineSeparator();
		 errorMessage1 = errorMessage1 + "does not exists.";
		 throw new Exception(errorMessage1);
	  }

	  try
	  {
	     fstream = new FileInputStream(textFile);
	     br      = new BufferedReader(new InputStreamReader(fstream));
	     line    = br.readLine();
	     
	     // Read File Line By Line
	     while (line != null)   
	     {      
		    // Get the name of the method
		    if(line.contains(attributeStr))
		    {
		    	//System.out.println ("Return Found: " + line);
		    	parts     = line.split(attributeStr);		    	
		    	attribute = parts[1];
		    	
		    	attribute = attribute.substring(0, attribute.length() - 1);  
		    	
		    	System.out.println ("Part 1    : " + part1);
		    	System.out.println ("Part 2    : " + part2);
		        System.out.println ("Attribute : " + attribute);
		        
		    }
		        
		    line = br.readLine();
	     }

	     // Close the input stream
	     fstream.close();
	     br.close();
	  }
	  catch(Exception e1)
	  {

	     errorMessage2 = "Error XXXX: Occurred while getting the Attribute Names from the Text File: " + System.lineSeparator() + textFile + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Line: " + line + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error: "+ errorMessage1;
     	 throw new Exception(errorMessage2);
	  }
	  
	  return attribute;

   }
   */
   
   /*
   public String[] getEnumValues(String parameterPackageName, String parameterClassName, 
		                         String parameterTextFileName, FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="", errorMessage2="", lineText="";
	  int lineNumber=1, startPos=0, c=0;
	  boolean flag=false;
	  String[] attributeValues=new String[10];
	  
	  FileInputStream fstream;
	  BufferedReader br;
	  File file;

      // Initialize the array
	  Arrays.fill(attributeValues,"");
	  
	  // Build the full Path Name 
	  parameterPathTextFileName = frlCon.projectOutputDir + File.separator + frlCon.projectName + File.separator +
                                  parameterPackageName    + File.separator + parameterClassName + File.separator +
                                  parameterTextFileName; 
                                  
	  
	  //System.out.println("Parameter Path Text File: " + parameterPathTextFileName);
	  
	  // Validate if the Parameter Path Text File exists
	  //file = new File(parameterPathTextFileName);
	  
	  file = new File(parameterTextFileName);
	  
	  if (!file.exists())
	  {
		 errorMessage1 = "Error 7777: The Text File Name: " + parameterTextFileName + System.lineSeparator();
		 errorMessage1 = errorMessage1 + "does not exists.";
		 throw new Exception(errorMessage1);
	  }

	  // Get all the possible values that the Enum can have
	  try
	  {
	     fstream  = new FileInputStream(parameterTextFileName);
	     br       = new BufferedReader(new InputStreamReader(fstream));
	     lineText = br.readLine();
	     
	     // Read File Line By Line
	     while (lineText != null)   
	     {      
		    // Get the name of the method
		    if(lineText.equals(frlCon.startMethod))
		    { 	
		       startPos = lineNumber + 1;	
		       flag = true;
		    }   
		    
		    if(lineText.equals(frlCon.endMethod))
		    {	
			   startPos = 0;	
			   flag = false;
		    }   
		    
	    	System.out.println("Line Text   :" +lineText);	 
	    	System.out.println("Line Number :" +lineNumber);	 
	    	System.out.println("StartPos    :" +startPos);	
	    	
		    
		    if(lineNumber >= startPos && flag == true)
		    {
		      lineText           = lineText.replaceAll(frlCon.findWhiteSpaces,"");
		      lineText           = lineText.replace(frlCon.valueDelimiter,"");
		      attributeValues[c] = lineText;
		     
		      //System.out.println("C         :" + c);
		      //System.out.println("Enum Value:" + enumValues[c]);
		      c++;
		    }
		        
		    lineText = br.readLine();
		    lineNumber++;
	     }

	     // Close the input stream
	     fstream.close();
	     br.close();
	     
	  }
	  catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error XXXX: Occurred while getting the Enum Values from the Text File: " + parameterTextFileName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "and Line: " + lineText + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error: " + errorMessage1;
     	 throw new Exception(errorMessage2);
	  }
	  
	  return attributeValues;
	   
   }
   */
   
   /*
   public AttributeSequenceDiagram getAttributesClass(int projectId, int methodId, int parameterId, 
		                                              String parameterName, String parameterPackageName, 
		                                              String parameterClassName, String line,  
		                                              LoadUMLSequenceDiagramController umlSeqDiagramCon, 
                                                      FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="", paramStr="", attributeMethod="", textFile1="", textFileFullPath1="", 
    		 textFile2="", attributeName="", attributeDataTypeName1="", attributeDataTypeName2="", 
    		 returnType1="", returnType2="", attributePackageName="", 
    		 attributeClassName="", returnType3="", attributeValue="";
      int length=0, startPos=0, attributeDataTypeId2=0, cmId1=0, cmId2=0, plId2=0;
      ClassMethod classMethod1=null, classMethod2;
      DataType dataType = null;
      String[] parts;
	  String[] attributeValues = new String[10];
      AttributeSequenceDiagram attributeSeqDiagramRecord=null;
      
      // Initialize the array
	  Arrays.fill(attributeValues,"");

      paramStr = parameterName + frlCon.objectOrientedDelimiter1;
      

      // Get the name of the method
      if(line.contains(paramStr))
      {
         length          = line.length();
         attributeMethod = line.substring(line.indexOf(frlCon.objectOrientedDelimiter1) + 1, length);
         startPos        = attributeMethod.indexOf(frlCon.startParameters);
         attributeMethod = attributeMethod.substring(0, startPos);
         
         
         System.out.println("Get Attributes Class Method - ONE");
         System.out.println("Parameter Package Name : " + parameterPackageName);
         System.out.println("Parameter Class Name   : " + parameterClassName);
         
         System.out.println("Line                   : " + line);
         System.out.println("Length                 : " + length);
         System.out.println("Start Pos              : " + startPos);
         System.out.println("Attribute Method       : " + attributeMethod);
         
         

         // Get the Class Method Id and Specific Text File for this Attribute Method
         try 
         {
            classMethod1 = umlSeqDiagramCon.getClassMethodDetails2(projectId, parameterPackageName, parameterClassName, attributeMethod);
         }
         catch(Exception e1)
         {
            errorMessage1 = e1.getMessage();
        	throw new Exception(errorMessage1);
         }

         // Get the Data from the ClassMethod Record
         cmId1       = classMethod1.getId();
         textFile1   = classMethod1.getTextFileName();
         returnType1 = classMethod1.getReturnType1();
         returnType2 = classMethod1.getReturnType2();
 
         if(classMethod1 !=null)
         {	 
        	 
            // Build the full path of the textFile3
            textFileFullPath1 = frlCon.projectOutputDir + File.separator + frlCon.projectName  + File.separator +
        		 			    parameterPackageName    + File.separator + parameterClassName + File.separator +
        		 			    textFile1;
        		 			    
         

         System.out.println("Get Attributes Class Method - TWO");
         System.out.println("Cm Id 1                : " + cmId1);
         System.out.println("Text File 1            : " + textFile1);
         System.out.println("Return Type 1          : " + returnType1);
         System.out.println("Return Type 2          : " + returnType2);
         System.out.println("Text File 1            : " + textFile1);
         System.out.println("Text File Full Path 1  : " + textFileFullPath1);
         
         
    	    //attributeName = getAttributeNames(textFileFullPath1, frlCon.returnValueMethod);
    	    attributeName = getAttributeNames(textFile1, frlCon.returnValueMethod);

    	    if(returnType1.equals(frlCon.bluePrintObject2))
    	    {	 
    	       attributeDataTypeName1 = returnType2;
    	    
    	       if(attributeDataTypeName1.contains(frlCon.objectOrientedDelimiter1))
    	          attributeDataTypeName2 = returnType1;
    	       else
    	          attributeDataTypeName2 = returnType2;
    	    }	 
    	    else
    	    {	 
    	       attributeDataTypeName1 = returnType1; 
    		   attributeDataTypeName2 = attributeDataTypeName1;
    	    }
    	 
    	 System.out.println("Get Attributes Class Method - THREE");
    	 System.out.println("Attribute Data Type Name 1 : " + attributeDataTypeName1);
    	 System.out.println("Attribute Data Type Name 2 : " + attributeDataTypeName2);
    	 
    	    
            // Get the attribute name and type for this method
            try
            {
               // Get the data type id for the return Type1
               dataType = umlSeqDiagramCon.getDataTypeDetails1(frlCon.programmingLanguage, attributeDataTypeName2);
                
               attributeDataTypeId2 = dataType.getDataTypeIdentifier1();  
               plId2                = dataType.getProgLanguageIdentifier();
            }
            catch(Exception e2)
            {
               errorMessage1 = e2.getMessage();
        	   throw new Exception(errorMessage1);
            }
         
            
         System.out.println("Get Attributes Class Method - FOUR");
         System.out.println("Data Type                : " + dataType);
         System.out.println("Attribute Data Type Id 2 : " + attributeDataTypeId2);
         System.out.println("Programing Languague 2   : " + plId2);
         
         
            // Validate if the attribute Type contains a "."
            if(attributeDataTypeName1.contains(frlCon.objectOrientedDelimiter1))
    	    {	 
               parts = attributeDataTypeName1.split(frlCon.findDot);
        	   attributePackageName = parts[0]; 
        	   attributeClassName   = parts[1];
        	 
        	   
        	System.out.println("Get Attributes Class Method - FIVE");
        	System.out.println("Attribute Package Name  : " + attributePackageName);
        	System.out.println("Attribute Class Name    : " + attributeClassName);
        	
        	
               // Get the class Method Details and specific text file for this method
               try 
               {
                  classMethod2 = umlSeqDiagramCon.getClassMethodDetails2(projectId, attributePackageName, attributeClassName, attributeClassName); 
               }
               catch(Exception e3)
               {
                  errorMessage1 = e3.getMessage();
                  throw new Exception(errorMessage1);
               }
             
               cmId2       = classMethod2.getId();
               textFile2   = classMethod2.getTextFileName();
               returnType3 = classMethod2.getReturnType1();
             
            
            System.out.println("Get Attributes Class Method - SIX");
            System.out.println("Cm Id 2           : " + cmId2);
            System.out.println("Text File Name 2  : " + textFile2);
            System.out.println("Return Type 3     : " + returnType3);
            
            
            
               // Get the data Type of this return type 3
             
     		   // Get all the possible values that can have 
    		   if(returnType3.equals(frlCon.classDataType))
    		   {	 
    	          // Get the Data Type Id for the Return Type 3
    	          try
    	          {
    	             dataType = umlSeqDiagramCon.getDataTypeDetails1(frlCon.programmingLanguage, returnType3);
    	            
    	          
    	          attributeDataTypeId3 = dataType.getDataTypeIdentifier1();
    	          plId3                = dataType.getProgLanguageIdentifier();
    	          
    	             attributeDataTypeId2 = dataType.getDataTypeIdentifier1();
    	             plId2                = dataType.getProgLanguageIdentifier();
    	          
    	             
    	          System.out.println("Get Attributes Class Method - SEVEN");
    	          System.out.println("Attribute Data Type Id 2   : " + attributeDataTypeId2);
    	          System.out.println("PL Id 2                    : " + plId2);
    	          
    	           }
    	           catch(Exception e4)
    	           {
    	              errorMessage1 = e4.getMessage();
    	              throw new Exception(errorMessage1);
    	           }
    	         
    	           // Get the Attributes Values
    		       try
    		       {
    		          attributeValues = getEnumValues(attributePackageName, attributeClassName, textFile2, frlCon);
    		       
    		          
    		       System.out.println("Get Attributes Class Method - EIGHT");
    		       System.out.println("Attribute Values ARRAY : " + Arrays.toString(attributeValues));
    		       System.out.println("Attribute Package Name : " + attributePackageName);
    		       System.out.println("Attribute Class Name   : " + attributeClassName);
    		       System.out.println("Text File Name 2       : " + textFile2);
    		       
    		       
    		       }
    		       catch(Exception e5)
    		       {
    		          errorMessage1 = e5.getMessage();
    			      throw new Exception(errorMessage1);
    		       }
    		   }
    		    
            }
         
            // Build a new record     
            attributeSeqDiagramRecord = new AttributeSequenceDiagram(projectId, methodId, parameterId,
                                 									 attributeMethod, cmId1, attributeName, 
                                 									 attributeValue, attributeDataTypeId2, plId2, 
                                 									 attributePackageName, attributeClassName, cmId2, 
                                 									 attributeValues); 
         
         }   
         else
         {
    	    errorMessage1 = "Error XXXX: The Parameter with Package Name: " + parameterPackageName + System.lineSeparator();
    	    errorMessage1 = errorMessage1 + "Class Name: " + parameterClassName + System.lineSeparator();
    	    errorMessage1 = errorMessage1 + "Attribute Method: " + attributeMethod + System.lineSeparator();
    	    errorMessage1 = errorMessage1 + "Project Identifier: " + projectId + System.lineSeparator();
    	    errorMessage1 = errorMessage1 + "Does not exist in the Forensic-Ready Logger Database";
         }
         
         
         System.out.println("Inside the Get Attributes Class Method  ...");
         
         System.out.println("Parameter Str : " + paramStr);
         System.out.println("Line          : " + line);
         
         
         System.out.println("*** INFORMATION ABOUT THE ATTRIBUTE ***");
         System.out.println("Attribute Method          : " + attributeMethod);
         System.out.println("CmId1                     : " + cmId1);
         System.out.println("Attribute Name            : " + attributeName);
         System.out.println("Attribute Value           : " + attributeValue);
         System.out.println("Attribute Data Type Id 2  : " + attributeDataTypeId2);
         System.out.println("plId2                     : " + plId2);
         System.out.println("Attribute Package Name    : " + attributePackageName);
         System.out.println("Attribute Class Name      : " + attributeClassName);
         System.out.println("CmId2                     : " + cmId2);
         System.out.println("Attribute Values          : " + attributeValues);
         
      }

      return attributeSeqDiagramRecord;   
   }
   */
   /*
   public void getMissingParameterAttributes(int projectId, int methodId1,
		                                     String methodPackageName1, String methodClassName1,
		                                     String methodShortName1, String methodPathTextFileName1, 
		                                     int methodClassMethodId1,
		                                     int parameterId, String parameterName,
		                                     int parameterDataTypeId, String parameterDataTypeName,
		                                     String parameterPackageName, String parameterClassName,
		                                     String parameterTextFileName, int parameterClassMethodId,
		                                     int parameterProgLangId, String parameterReturnType,
		                                     LoadUMLSequenceDiagramController umlSeqDiagramCon,
                                             FRLConfiguration frlCon) throws Exception
   {
      String errorMessage="", lineText="", callingMethod="", part1="", part2="", fullMethodName1="",
    		 methodPackageName2="", methodClassName2="", methodShortName2="", methodTextFileName2=""; 
      int lineNumber=1, startPos=0, methodCmId2=0;
      String[] parts;
      String[] attributeValues = new String[10];
      
      FileInputStream fstream = null;
	  BufferedReader br = null;
      File file;
      ClassMethod classMethodRecord = null;
      MethodParameterData methodParameterRecord = null;
      
	  file = new File(methodPathTextFileName1);

	  if (!file.exists())
	  {
		 errorMessage = "Error 8888: The Text File Name: " + methodPathTextFileName1 + System.lineSeparator();
		 errorMessage = errorMessage + "does not exists.";
		 throw new Exception(errorMessage);
	  }
	   
	  fstream  = new FileInputStream(methodPathTextFileName1);
	  br       = new BufferedReader(new InputStreamReader(fstream));
	  lineText = br.readLine();  
	  
	  try
	  {
	     // Read File Line By Line the methodPathTextFileName File
	     while (lineText != null)   
	     {
		    //System.out.println("Line Text   : " + lineText);
		    //System.out.println("Line Number : " + lineNumber);
		    
		    if(lineText.contains(parameterName) && lineNumber > 1)
    	    {
		       //enumFlag      = true;
		       lineText      = lineText.replaceAll(frlCon.findWhiteSpaces,"");
		       startPos      = lineText.indexOf(frlCon.startParameters);
		       callingMethod = lineText.substring(0, startPos);
		       
		       
		       System.out.println("*** Parameter Name Found ***");
		       System.out.println("Line Text   : " + lineText);
			   System.out.println("Line Number : " + lineNumber);
		       
		       System.out.println("Start Position : " + startPos);
		       System.out.println("Calling Method : " + callingMethod);
		       
		       
		       if(callingMethod.contains(frlCon.objectOrientedDelimiter1))
		       {
		          parts = callingMethod.split(frlCon.findDot);	 
		    	  part1 = parts[0];
		    	  part2 = parts[1];
		    	  
		    	  part1 = part1.replaceAll(frlCon.findWhiteSpaces,"");
		    	  part2 = part2.replaceAll(frlCon.findWhiteSpaces,"");
		    	   
		    	  //System.out.println("Part 1 :" + part1);
		    	  //System.out.println("Part 2 :" + part2);
		    	  
		       }
    	    }
		    
		    lineText = br.readLine();  
		    lineNumber++;
	     }  

	  }
	  finally 
	  {
	     br.close();	  
	     fstream.close();
	  }
	  
	  // Build the fullMethodName
	  fullMethodName1 = methodPackageName1 + frlCon.objectOrientedDelimiter1 + methodClassName1 +
	    		        frlCon.objectOrientedDelimiter1 + methodShortName1 + frlCon.textFileExtDelimiter;
	     
	  
	  System.out.println("Method              Id : " + methodId1);
	  System.out.println("Method Class Method Id : " + methodClassMethodId1);
	  System.out.println("Full Method Name       : " + fullMethodName1);
	  
	     
	  try 
	  {	 	
	     classMethodRecord = umlSeqDiagramCon.getParentMethodDetails(projectId, methodClassMethodId1, fullMethodName1);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
	  }
		 
	  if(classMethodRecord != null)
	  {
	     methodCmId2          = classMethodRecord.getId();
	     methodPackageName2   = classMethodRecord.getPackageName();
	     methodClassName2     = classMethodRecord.getClassName();
	     methodShortName2     = classMethodRecord.getShortMethodName();
	     methodTextFileName2  = classMethodRecord.getTextFileName();
	     
		 // Build the complete path for the methodTextFileName
		 methodPathTextFileName2 = frlCon.projectOutputDir + File.separator + frlCon.projectName + File.separator +
		                           methodPackageName2 + File.separator + methodClassName2 + File.separator +
		                           methodTextFileName2;
			
		 
		 System.out.println("Method Cm Id               :" + methodCmId2);
		 System.out.println("Method Package Name        :" + methodPackageName2);
		 System.out.println("Method Class Name          :" + methodClassName2);
		 System.out.println("Method Short Name          :" + methodShortName2);
		 System.out.println("Method Text File Name      :" + methodTextFileName2);
		 //System.out.println("Method Path Text File Name :" + methodPathTextFileName2);

		 System.out.println("Parameter Package Name     :" + parameterPackageName);
		 System.out.println("Parameter Class   Name     :" + parameterClassName);		
		 System.out.println("Parameter Name             :" + parameterName);
		 System.out.println("Parameter Data Type Name   :" + parameterDataTypeName);
		 System.out.println("Parameter Data Type Id     :" + parameterDataTypeId);
		 System.out.println("Parameter Text File Name   :" + parameterTextFileName);
		 


		 if(part2.equals(methodShortName2))
		 {
			// if parameterDatatype = Enum
			// Get all the possible values that can have 
			if(parameterDataTypeName.equals(frlCon.classDataType))
			{	 
			    try
			    {
			       attributeValues = getEnumValues(parameterPackageName, parameterClassName, 
			    		                           parameterTextFileName, frlCon);
			       
			       //System.out.println("Attribute Values         : " + Arrays.toString(attributeValues));
			    }
			    catch(Exception e2)
			    {
			       errorMessage = e2.getMessage();
				   throw new Exception(errorMessage);
			    }
			    
				methodParameterRecord = new MethodParameterData
			                     (projectId, parameterId, parameterName,
			                      parameterDataTypeId, parameterDataTypeName, parameterPackageName, 
			                      parameterClassName, parameterClassMethodId, parameterProgLangId,
			                      parameterTextFileName, parameterReturnType,
                                  methodId1, methodPackageName2, methodClassName2, 
                                  methodShortName2, methodCmId2, methodTextFileName2, 
                                  attributeValues);
	 
			   	
			   //System.out.println("*** Calling the getParameterAttributes method for SECOND TIME ***");	
	           // Get all the lines from the Class Method file
	           // that contain the parameter Name
	           try
	           {
	              getParameterAttributes(methodParameterRecord, umlSeqDiagramCon, frlCon);
	           } 
	           catch(Exception e3)
	           {
	             errorMessage = e3.getMessage();
		         throw new Exception(errorMessage);
	           }
				
		    }
		 }
			
      }
	   
   }
   */
   
   /*
   public void getParameterAttributes(MethodParameterData methodParameterRecord, 
		                              LoadUMLSequenceDiagramController umlSeqDiagramCon,
		                              FRLConfiguration frlCon) throws Exception
   {
      FileInputStream fstream;
	  BufferedReader br;
	  String line = "", errorMessage1="", methodPackageName="", methodClassName="",
		     parameterName="", parameterDataTypeName="", parameterPackageName="", parameterClassName="", 
		     methodShortName="", parameterTextFileName="", methodTextFileName="", methodPathTextFileName, 
		     attributeMethod="", attributeName="", attributePackageName="", attributeClassName="", 
		     value1="", value2="", attributeDataTypeName="", attributeValue="", 
		     valueName="", valueEquivalence="", parameterReturnType="";
	  
	  int projectId=0, methodId=0, parameterId=0, parameterClassMethodId=0,
		  parameterDataTypeId=0, cmId1=0, cmId2=0, attributeDataTypeId2=0, parameterProgLangId=0, 
		  plId2=0, attributeId=0, c=0, v=0, methodClassMethodId=0, valueId=0;
	  
	  boolean enumFlag=false;
	  AttributeSequenceDiagram attributeSeqDiagramRecord1=null, attributeSeqDiagramRecord2 = null;
	  ValueSequenceDiagram valueSeqDiagram;
	  
	  String[] attributeValues1, attributeValues2 = new String[10];
	  DataType dataType;
	  File file;

	  System.out.println("Inside the Get Parameter Attributes Method ...");
	  
	  
	  // Initialize the array
	  Arrays.fill(attributeValues2,"");
		  
	  // Get the data from the methodParameterRecord Parameter
	  projectId              = methodParameterRecord.getProjectIdentifier();
	  parameterId            = methodParameterRecord.getParameterIdentifier();
	  parameterName          = methodParameterRecord.getParameterName();
	  parameterDataTypeId    = methodParameterRecord.getParameterDataTypeIdentifier();
	  parameterDataTypeName  = methodParameterRecord.getParameterDataTypeName();
	  parameterPackageName   = methodParameterRecord.getParameterPackageName();
	  parameterClassName     = methodParameterRecord.getParameterClassName();
	  parameterClassMethodId = methodParameterRecord.getParameterClassMethodIdentifier();
	  parameterProgLangId    = methodParameterRecord.getParameterProgrammingLanguageIdentifier();
	  parameterReturnType    = methodParameterRecord.getParameterReturnType();
	  parameterTextFileName  = methodParameterRecord.getParameterTextFileName();

	  methodId               = methodParameterRecord.getMethodIdentifier();
	  methodPackageName      = methodParameterRecord.getMethodPackageName();
	  methodClassName        = methodParameterRecord.getMethodClassName();
	  methodTextFileName     = methodParameterRecord.getMethodTextFileName();
	  methodShortName        = methodParameterRecord.getMethodShortName();
	  methodClassMethodId    = methodParameterRecord.getMethodClassMethodIdentifier();
	  
	  attributeValues1       = methodParameterRecord.getAttributeValues();
	  

	  //methodFullName         = methodParameterRecord.getMethodFullName();
	  //methodDbFlag           = methodParameterRecord.getMethodDbFlag();

	  
	  if(parameterDataTypeName.equals("Enum") || 
		 parameterDataTypeName.equals(frlCon.bluePrintObject2) )
      {
	  
		 System.out.println("STEP ONE");  
	     System.out.println("*** INFORMATION ABOUT THE CURRENT METHOD: *** ");
	     System.out.println("Method Id                 : " + methodId);
	     System.out.println("Method Package Name       : " + methodPackageName);
	     System.out.println("Method Class Name         : " + methodClassName);
		 System.out.println("Method Short Name         : " + methodShortName);
	     System.out.println("Method Class Method Id    : " + methodClassMethodId);
	     System.out.println("Method    Text FileName   : " + methodTextFileName);

	     
		 System.out.println("*** INFORMATION ABOUT THE CURRENT PARAMETER: *** ");
	     System.out.println("Project   Id              : " + projectId);
		 System.out.println("Parameter Id              : " + parameterId);
	     System.out.println("Parameter Name            : " + parameterName);
	     System.out.println("Parameter Data Type Id    : " + parameterDataTypeId);
	     System.out.println("Parameter Data Type Name  : " + parameterDataTypeName);
	     System.out.println("Parameter Package Name    : " + parameterPackageName);
	     System.out.println("Parameter Class Name      : " + parameterClassName);
	     System.out.println("Parameter Class Method Id : " + parameterClassMethodId);
	     System.out.println("Parameter Return Type     : " + parameterReturnType);
	     System.out.println("Parameter Text FileName   : " + parameterTextFileName);     
	      

      //}
      
      
	  // Build the complete path for the methodTextFileName
	  methodPathTextFileName = frlCon.projectOutputDir + File.separator + frlCon.projectName + File.separator +
	                           methodPackageName + File.separator + methodClassName + File.separator +
	                           methodTextFileName;
	                           
	  methodPathTextFileName = methodTextFileName;
	  

	  file = new File(methodPathTextFileName);

	  if (!file.exists())
	  {
		 errorMessage1 = "Error 9999: The Text File Name: " + methodPathTextFileName + System.lineSeparator();
		 errorMessage1 = errorMessage1 + "does not exists.";
		 throw new Exception(errorMessage1);
	  }
	   
	  
	  fstream  = new FileInputStream(methodPathTextFileName);
	  br       = new BufferedReader(new InputStreamReader(fstream));
	  line     = br.readLine();   
	  enumFlag = false;
	  
	  
	  try
	  {
	     // Read File Line By Line the methodPathTextFileName File
	     while (line != null)   
	     {
	    	//System.out.println("Get Parameter Attributes METHOD - TWO");   
	        //System.out.println ("Line: " + line);

	        // Validate the Data Type of the Parameter	    
	    	 
	        // Validate if the Parameter DataType = "Class"
	        if(parameterDataTypeName.equals(frlCon.bluePrintObject2))
	        {
	           // Get the attributes of the Class	
	           try
	           {
	              attributeSeqDiagramRecord1 = getAttributesClass(projectId, methodId, parameterId, parameterName, 
	        			                                          parameterPackageName, parameterClassName, line, 
	        			                                          umlSeqDiagramCon, frlCon);
	           }
	           catch(Exception e1)
	           {
	              errorMessage1 = e1.getMessage();
	              throw new Exception(errorMessage1);
	           }
	           
	           
	           if(attributeSeqDiagramRecord1 !=null)
	           {
	              // Get the information from the attributeSeqDiagramRecord1
	              attributeMethod      = attributeSeqDiagramRecord1.getMethodName();
	              cmId1                = attributeSeqDiagramRecord1.getClassMethodIdentifier1();
	              attributeName        = attributeSeqDiagramRecord1.getAttributeName();
	              attributeDataTypeId2 = attributeSeqDiagramRecord1.getDataTypeIdentifier2();
	              plId2                = attributeSeqDiagramRecord1.getProgrammingLanguageIdentifier2();
	              attributePackageName = attributeSeqDiagramRecord1.getAttributePackageName();
	              attributeClassName   = attributeSeqDiagramRecord1.getAttributeClassName();
                  cmId2                = attributeSeqDiagramRecord1.getClassMethodIdentifier2();
                  attributeValues2     = attributeSeqDiagramRecord1.getAttributeValues();
                  
                  // Increase the attribute Counter
                  attributeId++;  
                  
                  if(attributeValues2[0] != null && !attributeValues2[0].trim().isEmpty())
                  {
                     attributeValue = attributePackageName + frlCon.objectOrientedDelimiter1 + 
                    		          attributeClassName;
                  }
                  
                  // Verify if the Parameter Return Type is equal to Enum
                  // Assign to the Attribute Name the name of Parameter
                  if(parameterReturnType.equals(frlCon.classDataType))
                     attributeName = parameterName;
                	  
                  System.out.println("*** BEFORE INSERTING ATTRIBUTES - CLASS ***");  
                  
                  System.out.println("Method Id                 : " + methodId);
                  System.out.println("Parameter Id              : " + parameterId);
                  System.out.println("Parameter Data Type Id 1  : " + parameterDataTypeId);
                  System.out.println("Parameter Prog Lang Id 1  : " + parameterProgLangId);
                  System.out.println("Attribute Id              : " + attributeId);
                  System.out.println("Attribute Method          : " + attributeMethod);
                  System.out.println("Cm Id 1                   : " + cmId1);
                  System.out.println("Attribute Name            : " + attributeName);
                  System.out.println("Attribute Value           : " + attributeValue);
                  System.out.println("Attribute Data Type Id 2  : " + attributeDataTypeId2);  
                  System.out.println("Programming Language Id 2 : " + plId2);
        	      System.out.println("Attribute Package Name    : " + attributePackageName);
        	      System.out.println("Attribute Class Name      : " + attributeClassName);
                  System.out.println("Cm Id 2                   : " + cmId2);
                  
                  System.out.println("Parameter Name            : " + parameterName);
                  System.out.println("Parameter Return Type     : " + parameterReturnType);
                  System.out.println("Attribute Values  Array   : " + Arrays.toString(attributeValues2));

                  
                  // Build a new Record attributeSeqDiagramRecord2
                  attributeSeqDiagramRecord2 = new AttributeSequenceDiagram(projectId, methodId, parameterId,
              		     													parameterDataTypeId, parameterProgLangId, attributeId,
              		     													attributeMethod, cmId1, attributeName, attributeValue,
              		     													attributeDataTypeId2, plId2, attributePackageName, 
              		     													attributeClassName, cmId2);
              		     													
                   
                  // Save the attribute in the Database
                  try
                  {
                     umlSeqDiagramCon.saveAttributeSequenceDiagram(attributeSeqDiagramRecord2);
                  }
                  catch(Exception e2)
                  {
                     errorMessage1 = e2.getMessage();
               	    throw new Exception(errorMessage1);
                  }  
                  
                  
                  // Validate if the array contains values
                  if(attributeValues2[0] != null && !attributeValues2[0].trim().isEmpty())
                  {
            	      valueName = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
            	      
            	      valueId = 0;
            	      
            	      for(c=0; c<10; c++)
            		  { 
            	         valueEquivalence = attributeValues2[c];
            	         
            	         if(valueEquivalence != null && !valueEquivalence.trim().isEmpty()) 
            	         { 
            	        	
            	        	valueId++;
            	        	
            	        	System.out.println("Get Parameter Attributes METHOD - FOUR"); 
            	        	
            	        	System.out.println("Project Id             : " + projectId);
            	        	System.out.println("Method Id              : " + methodId);
            	        	System.out.println("Parameter Id           : " + parameterId);
            	        	System.out.println("Parameter Data Type Id : " + parameterDataTypeId);
            	        	System.out.println("Parameter Prog Lang Id : " + parameterProgLangId);
            	        	System.out.println("Attribute Id           : " + attributeId);
            	        	System.out.println("Value Id               : " + valueId);
            	        	System.out.println("Value Name             : " + valueName);
            	        	System.out.println("Value Equivalence      : " + valueEquivalence);
            	        	System.out.println("Attribute Package Name : " + attributePackageName);
            	        	System.out.println("Attribute Class Name   : " + attributeClassName);
            	        	System.out.println("Cm Id 2                : " + cmId2);
            	        	
            	            
                            // Build a new Record valueSeqDiagram 
            	        	valueSeqDiagram = new ValueSequenceDiagram(projectId, methodId, parameterId,
            	        			                                   parameterDataTypeId, parameterProgLangId, 
            	        			                                   attributeId, valueId, valueName, 
		                                                               valueEquivalence, attributePackageName, 
		                                                               attributeClassName, cmId2);
            	        	
                            // Save the value in the Database
                            try
                            {
                               umlSeqDiagramCon.saveValueSequenceDiagram(valueSeqDiagram);
                            }
                            catch(Exception e3)
                            {
                               errorMessage1 = e3.getMessage();
                        	     throw new Exception(errorMessage1);
                            }                             
            	         }
            		  }   
            	   
                  }  
                  
                  
	           }
	           
	        } 
	        else
	           // DataType = "Enum"	
	           if(parameterDataTypeName.equals(frlCon.classDataType))
	           {
	              for(c=0; c<10; c++)
	      	      { 
	      	         value1 = attributeValues1[c];
	      	         value2 = parameterClassName + frlCon.objectOrientedDelimiter1 + value1; 
	      	         
	      	         
	      	         System.out.println("Get Parameter Attributes METHOD - FIVE"); 
	      	         System.out.println("Value 1: " + value1);
	      	         System.out.println("Value 2: " + value2);
	      	         
	      	        
	      	         if((value1 != null && !value1.trim().isEmpty()) && line.contains(value2))
	      	         {
	                    attributeValues2[v] = value1;
	                    v++;
	                    enumFlag = true;
	                     
	                 
	                 //System.out.println("Value 1     : " + value1);
	                 //System.out.println("Value 2     : " + value2);
	                    
	                    
	                 System.out.println("Get Parameter Attributes METHOD - SIX");    
	                 System.out.println("Line            : " + line);
	                 System.out.println("Attribute Value : " + attributeValues2[v]);
	                 System.out.println("Enum Flag       : " + enumFlag);
	                 

	      	         }
	      	      }	  
	           }
	           
	           line = br.readLine();
	        }
	     }
	     finally 
	     {
	        br.close();
	        fstream.close();
	     } 
	  
	     
	     //System.out.println("Get Parameter Attributes METHOD - SEVEN");   
	     
	     // Validate if the Parameter Data Type is an Enum  
	     if(parameterDataTypeName.equals(frlCon.classDataType))
	        if(enumFlag == true)	 
	        {	 
	           attributeMethod        = parameterClassName; 
	           attributeName          = parameterClassName;
	           attributeDataTypeName  = parameterDataTypeName;
	           attributePackageName   = parameterPackageName;
	           attributeClassName	  = parameterClassName;
	           cmId2                  = parameterClassMethodId;
	     
               // Get the attribute name and type for this method
               try
               {
                  // Get the data type id for the return Type1
                  dataType = umlSeqDiagramCon.getDataTypeDetails1(frlCon.programmingLanguage, attributeDataTypeName);
                 
                  attributeDataTypeId2 = dataType.getDataTypeIdentifier1();  
                  plId2                = dataType.getProgLanguageIdentifier();
               }
               catch(Exception e3)
               {
                  errorMessage1 = e3.getMessage();
                  throw new Exception(errorMessage1);
               }
            
               // Validate if the array contains values
               if(attributeValues2[0] != null && !attributeValues2[0].trim().isEmpty())
               {
         	      attributeMethod = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
         	      attributeName   = attributeClassName;
         	      attributeValue  = attributePackageName + frlCon.objectOrientedDelimiter1 + 
       		                        attributeClassName;
         	      
    	          attributeId++;
    	          
                  // Verify if the parameterReturnType is equal to Enum
                  // Assign to the Attribute Name the name of Parameter
                  if(parameterReturnType.equals(frlCon.classDataType))
                     attributeName = parameterName;
    	          
    	          
    	            System.out.println("*** DATOS DE LOS ENUMS ***");
    	            System.out.println("Attribute Values         : " + Arrays.toString(attributeValues2));
    	            
    	            System.out.println("Method Id                : " + methodId);
    	            System.out.println("Parameter Id             : " + parameterId);
    	            System.out.println("Parameter Data Type Id   : " + parameterDataTypeId);
    	            
    	            System.out.println("Attribute Id             : " + attributeId);
    	            System.out.println("Attribute Method         : " + attributeMethod);
    	            System.out.println("Cm Id 2                  : " + cmId2);
    	            System.out.println("Attribute Name           : " + attributeName);
    	            System.out.println("Attribute Value          : " + attributeValue);
    	            System.out.println("Attribute Data Type Id 2 : " + attributeDataTypeId2);
    	  	        System.out.println("Attribute Package Name   : " + attributePackageName);
    	  	        System.out.println("Attribute Class Name     : " + attributeClassName);
    	            
    	  	        System.out.println("*** EXTRA INFORMATION ***");
    	            System.out.println("Attribute Data Type Name : " + attributeDataTypeName);
    	            System.out.println("Programming Language   2 : " + plId2);
    	            System.out.println("Cm Id 1                  : " + cmId1);
    	            
    	          
                  
                  System.out.println("*** DATOS DE LOS ATTRIBUTOS ***");
                  System.out.println("CHECK POINT 2");
                  
                  System.out.println("Attribute Values         : " + Arrays.toString(attributeValues2));

                  System.out.println("Method Id                : " + methodId);
                  System.out.println("Parameter Id             : " + parameterId);
                  System.out.println("Parameter Name           : " + parameterName);
                  System.out.println("Parameter Data Type Id 1 : " + parameterDataTypeId);
                  System.out.println("Parameter Return Type    : " + parameterReturnType);
                  
                  System.out.println("Attribute Id             : " + attributeId);
                  System.out.println("Attribute Method         : " + attributeMethod);
                  System.out.println("Cm Id 1                  : " + cmId1);
                  System.out.println("Attribute Name           : " + attributeName);
                  System.out.println("Attribute Value          : " + attributeValue);
                  
                  System.out.println("Attribute Data Type Id 2 : " + attributeDataTypeId2);
        	      System.out.println("Attribute Package Name   : " + attributePackageName);
        	      System.out.println("Attribute Class Name     : " + attributeClassName);
                  System.out.println("Cm Id 2                  : " + cmId2);
                  
                  
                  System.out.println("*** BEFORE INSERTING ATTRIBUTES - ENUM ***");  
                  
                  System.out.println("Method Id                 : " + methodId);
                  System.out.println("Parameter Id              : " + parameterId);
                  System.out.println("Parameter Data Type Id 1  : " + parameterDataTypeId);
                  System.out.println("Parameter Prog Lang Id 1  : " + parameterProgLangId);
                  System.out.println("Attribute Id              : " + attributeId);
                  System.out.println("Attribute Method          : " + attributeMethod);
                  System.out.println("Cm Id 1                   : " + cmId1);
                  System.out.println("Attribute Name            : " + attributeName);
                  System.out.println("Attribute Value           : " + attributeValue);
                  System.out.println("Attribute Data Type Id 2  : " + attributeDataTypeId2);  
                  System.out.println("Programming Language Id 2 : " + plId2);
        	      System.out.println("Attribute Package Name    : " + attributePackageName);
        	      System.out.println("Attribute Class Name      : " + attributeClassName);
                  System.out.println("Cm Id 2                   : " + cmId2);
                  
                  System.out.println("Parameter Name            : " + parameterName);
                  System.out.println("Parameter Return Type     : " + parameterReturnType);
                  System.out.println("Attribute Values  Array   : " + Arrays.toString(attributeValues2));

    	          
                  // Build a new Record attributeSeqDiagramRecord2
                  attributeSeqDiagramRecord2 = new AttributeSequenceDiagram(projectId, methodId, parameterId,
               		     													parameterDataTypeId, parameterProgLangId, attributeId,
               		     													attributeMethod, cmId2, attributeName, attributeValue,
               		     													attributeDataTypeId2, plId2,
               		     													attributePackageName, attributeClassName, cmId2, 0, 0);
                    
                   // Save the attribute in the Database
                   try
                   {
                      umlSeqDiagramCon.saveAttributeSequenceDiagram(attributeSeqDiagramRecord2);
                   }
                   catch(Exception e2)
                   {
                      errorMessage1 = e2.getMessage();
                	  throw new Exception(errorMessage1);
                   } 
                   
                   
                   // Validate if the array contains values
                   if(attributeValues2[0] != null && !attributeValues2[0].trim().isEmpty())
                   {
             	      valueName = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
             	      valueId = 0;
             	      
             	      for(c=0; c<10; c++)
             		  { 
             	         valueEquivalence = attributeValues2[c];
             	         
             	         if(valueEquivalence != null && !valueEquivalence.trim().isEmpty()) 
             	         { 
             	        	
             	        	valueId++;
             	            
                             // Build a new Record valueSeqDiagram 

             	        	valueSeqDiagram = new ValueSequenceDiagram(projectId, methodId, parameterId,
             	        			                                   parameterDataTypeId, parameterProgLangId, 
             	        			                                   attributeId, valueId, valueName, 
 		                                                               valueEquivalence, attributePackageName, 
 		                                                               attributeClassName, cmId2);
             	        	
                             // Save the value in the Database
                             try
                             {
                                umlSeqDiagramCon.saveValueSequenceDiagram(valueSeqDiagram);
                             }
                             catch(Exception e2)
                             {
                                errorMessage1 = e2.getMessage();
                         	     throw new Exception(errorMessage1);
                             }                             
             	         }
             		  }   
             	   
                   }  

               }  
            
	        }
	     
	        else
	        {
	           //System.out.println("Parameter Data Type : " + parameterDataTypeName);
	    	   //System.out.println("Enum Flag           : " + enumFlag); 
	    	   
	    	   getMissingParameterAttributes(projectId, methodId, methodPackageName,
                                             methodClassName, methodShortName, methodPathTextFileName, 
                                             methodClassMethodId,
                                             parameterId, parameterName, parameterDataTypeId, parameterDataTypeName, 
                                             parameterPackageName, parameterClassName, parameterTextFileName, 
                                             parameterClassMethodId, parameterProgLangId, parameterReturnType,
                                             umlSeqDiagramCon, frlCon);
	        }
	     
	  
   }
   }
   */
   
   /*
   public void saveParametersAttributes(int projectId, LoadUMLSequenceDiagramController umlSeqDiagramCon,
		                                FRLConfiguration frlCon) throws Exception
   {
	  String errorMessage = "", parameterName="", parameterDataTypeName="", parameterPackageName="", 
			 parameterClassName="", methodShortName="", parameterTextFileName="", 
			 methodTextFileName="", methodPackageName="", methodClassName="", parameterReturnType="";
	  int c, parameterId=0, methodId=0, parameterDataTypeId=0, parameterClassMethodId=0, 
		  parameterProgLangId=0, methodClassMethodId=0;
      ArrayList<MethodParameterData> methodParameterList;
      MethodParameterData methodParameterRecord;
      String[] attributeValues = null;
      
      System.out.println("Inside the Save Parameters Attributes Method ...");
      
      // Get the Parameters and the Methods Information from the Database 
	  try 
	  {
         methodParameterList = umlSeqDiagramCon.loadMethodsParametersData(projectId, frlCon.bluePrintObject2, frlCon.classDataType);
         
         
         System.out.println("Method Parameters : " + methodParameterList.size());
	     System.out.println("Elements          : " + Arrays.toString(methodParameterList.toArray()));
	     
	     
	  }
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  // Loop to iterate all the methods that contains parameters 
	  // from the methodParameterList ArrayList
	  
	  for (c = 0; c < methodParameterList.size(); c++) 
	  { 
	     // Getting all data  from the  methodParameterList ArrayList
	     parameterId            = methodParameterList.get(c).getParameterIdentifier(); 
		 parameterName          = methodParameterList.get(c).getParameterName(); 
		 parameterDataTypeId    = methodParameterList.get(c).getParameterDataTypeIdentifier();
		 parameterDataTypeName  = methodParameterList.get(c).getParameterDataTypeName();
		 parameterPackageName   = methodParameterList.get(c).getParameterPackageName();
		 parameterClassName     = methodParameterList.get(c).getParameterClassName();
		 parameterClassMethodId = methodParameterList.get(c).getParameterClassMethodIdentifier();	
		 parameterTextFileName  = methodParameterList.get(c).getParameterTextFileName();
		 parameterProgLangId    = methodParameterList.get(c).getParameterProgrammingLanguageIdentifier();
		 parameterReturnType    = methodParameterList.get(c).getParameterReturnType();
		 
		 methodId               = methodParameterList.get(c).getMethodIdentifier();
		 methodPackageName      = methodParameterList.get(c).getMethodPackageName();
		 methodClassName        = methodParameterList.get(c).getMethodClassName();
		 methodShortName        = methodParameterList.get(c).getMethodShortName();
		 methodClassMethodId    = methodParameterList.get(c).getMethodClassMethodIdentifier();
		 methodTextFileName     = methodParameterList.get(c).getMethodTextFileName();
 
		 System.out.println("**************************************************"); 
		 System.out.println("*** METHOD INFORMATION ***");
		 System.out.println("Method ID                 : " + methodId);
		 System.out.println("Method Package Name       : " + methodPackageName);
		 System.out.println("Method Class Name         : " + methodClassName);
		 System.out.println("Method Short Name         : " + methodShortName);
		 System.out.println("Method Class Method Id    : " + methodClassMethodId);
		 System.out.println("Method Text File          : " + methodTextFileName);
		 System.out.println("**************************************************"); 
		 System.out.println("*** PARAMETER INFORMATION ***");
		 System.out.println("Parameter Id              : " + parameterId);
		 System.out.println("Parameter Name            : " + parameterName);
		 System.out.println("Parameter Data Type Id    : " + parameterDataTypeId);
		 System.out.println("Parameter Data Type Name  : " + parameterDataTypeName);
		 System.out.println("Parameter Package Name    : " + parameterPackageName);
		 System.out.println("Parameter Class Name      : " + parameterClassName);
		 System.out.println("Parameter Class Method Id : " + parameterClassMethodId);
		 System.out.println("Parameter Text File Name  : " + parameterTextFileName);
		 System.out.println("Parameter Prog Lang Id    : " + parameterProgLangId);
		 System.out.println("Parameter Return Type     : " + parameterReturnType);
		 System.out.println("**************************************************"); 

		 // WE ARE REMOVING THIS TO OBTAIN THE VALUES OF AN ENUM LATER
		 
		 // if Parameter Data Type = Enum
		 // Get the values that can have 
		 if(parameterDataTypeName.equals(frlCon.classDataType))
		 {	 
		    try
		    {
		       attributeValues = getEnumValues(parameterPackageName, parameterClassName, 
		    		                           parameterTextFileName, frlCon);
		    }
		    catch(Exception e2)
		    {
		       errorMessage = e2.getMessage();
			   throw new Exception(errorMessage);
		    }
		    
		    //System.out.println("ENTRE AQUI A LO DE ENUM");
		 }
		 
		 // Create a new Method Parameter Record
		 methodParameterRecord = new MethodParameterData(projectId, parameterId, parameterName,
				               							 parameterDataTypeId, parameterDataTypeName, parameterPackageName, 
				               							 parameterClassName, parameterClassMethodId, parameterProgLangId,
				               							 parameterTextFileName, parameterReturnType,
				               							 methodId, methodPackageName, methodClassName, 
				               							 methodShortName, methodClassMethodId, methodTextFileName, 
				               							 attributeValues);
		 
		 
		 
		 // Save the Attributes and the Values for this Parameter
		 try
		 {
		    getParameterAttributes(methodParameterRecord, umlSeqDiagramCon, frlCon);
		 } 
		 catch(Exception e3)
		 {
		    errorMessage = e3.getMessage();
			throw new Exception(errorMessage);
		 }
		 
		 
	  }
	  
	  	   
   }
   */
   
   public DataType obtainAttributeDataType(int projectId,
		                                   String attributePackageName, String attributeClassName, 
		                                   String attributeDataType, FRLConfiguration frlCon,
		                                   LoadUMLSequenceDiagramController umlSeqDiagramCon) throws Exception
   {
      String attributeClassName1="", attributeDataType1="", errorMessage="";
      int enumFlag=0;
      DataType dataType = null;

      
      /*
	  System.out.println("Inside the Obtain Attribute Data Type Method ...");

	  System.out.println("Attribute Package Name : " + attributePackageName);
      System.out.println("Attribute Class Name   : " + attributeClassName);
      System.out.println("Attribute Data Type    : " + attributeDataType);
      */
      

	  // Validate if the Attribute Class Name contains an Array Symbol "[" 
	  if(attributeClassName.contains(frlCon.startArray))
	  {	 
	     attributeClassName1 = attributeClassName.substring(0, attributeClassName.indexOf(frlCon.startArray));
	     //System.out.println("CASE ONE");
	  } 
	  else
	  {	  
	     attributeClassName1 = attributeClassName;
	     //System.out.println("CASE TWO");
	  }   

	  
      //System.out.println("Attribute Class Name 1: " + attributeClassName1);
	  
      if(attributeDataType.equals("Primitive") || attributeDataType.equals("JavaClass") || 
    	 attributeDataType.equals("Array") )
      {	  
         attributeDataType1 = attributeClassName1;
         //System.out.println("CASE THREE");
      }   
      else
         if(attributeDataType.equals("PersonalizedClass"))
         {	   
              
        	//System.out.println("CASE FOUR");
        	 
      	    try 
    	    {
    	       enumFlag = umlSeqDiagramCon.getClassEnumFlag(projectId, attributePackageName, attributeClassName);
    	    }
    	    catch(Exception e1)
    	    {
    	       errorMessage = e1.getMessage();
    	 	   throw new Exception(errorMessage);
    	    }
      	    
      	    //System.out.println("ENUM FLAG: " + enumFlag);
      	    
      	    if(enumFlag == 1)
      	    	attributeDataType1 = "Enum";
      	    else
      	       attributeDataType1 = "Class";
         }  
        	 
      /*
      System.out.println("Inside Obtain Attribute Data Type Method ...");
      System.out.println("Attribute Data Type 1: " + attributeDataType1);
      
      System.out.println("BEFORE getDataTypeDetails1 method ");
      */
    	
      // Get the attribute name and type for this method
      try
      {               
         // Get the data type id for the return Type1
         dataType = umlSeqDiagramCon.getDataTypeDetails1(frlCon.programmingLanguage, attributeDataType1);
      }
      catch(Exception e2)
      {
         errorMessage = e2.getMessage();
	     throw new Exception(errorMessage);
      }
      
      /*
      System.out.println("AFTER getDataTypeDetails1 method ");
      
      System.out.println("Data Type : "+ dataType);
      */
      
      return dataType;
      
   }
     
   public void saveAttributesSequenceDiagram(int projectId, LoadUMLSequenceDiagramController umlSeqDiagramCon,
		                                     FRLConfiguration frlCon) throws Exception
   {
	  String errorMessage = "", parameterName="", parameterDataTypeName="", parameterPackageName="", 
			 parameterClassName="", methodShortName="", parameterTextFileName="", 
			 methodTextFileName="", methodPackageName="", methodClassName="", parameterReturnType="",
			 attributePackageName="", attributeClassName="", attributeShortName="", attributeDataType="",
			 attributeMethod="", attributeName="", attributeValue="", attributeDataTypeName2="", valueCompare="";
	  int i=0, j=0, parameterId=0, methodId=0, parameterDataTypeId=0, parameterClassMethodId=0, 
		  parameterProgLangId=0, parameterClassInfoId=0, parameterEnumClassFlag=0, methodClassMethodId=0, attributeId=0,
		  attributeDataTypeId2=0, plId2=0, attributeCounter=0, attributeClassInfoId=0;
      ArrayList<MethodParameterData> methodParameterList;
      ArrayList<ClassAttribute> classAttributeList = new ArrayList<ClassAttribute>(); 
      AttributeSequenceDiagram attributeSeqDiagramRecord = null;
      DataType dataType = null;
      
      //MethodParameterData methodParameterRecord;
      //String[] attributeValues = null;
      /*cmId1=0, cmId2=0,*/ 
      //attributeDataType1=""
      
      /*
      System.out.println("Inside the Save Parameters Attributes Method ...");
      System.out.println("1");
      */
      
      // Get the Parameters and the Methods Information from the Database 
	  try 
	  {
         methodParameterList = umlSeqDiagramCon.loadMethodsParametersData(projectId, frlCon.bluePrintObject2, frlCon.classDataType);
         
         
         /*
         System.out.println("Method Parameters : " + methodParameterList.size());
	     System.out.println("Elements          : " + Arrays.toString(methodParameterList.toArray()));
	     */
	     
	  }
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  //System.out.println("2");
	  
	  // Loop to iterate all the methods that contains parameters 
	  // from the methodParameterList ArrayList
	  
	  for (i = 0; i < methodParameterList.size(); i++) 
	  { 
	     // Getting all data  from the  methodParameterList ArrayList
	     parameterId            = methodParameterList.get(i).getParameterIdentifier(); 
		 parameterName          = methodParameterList.get(i).getParameterName(); 
		 parameterDataTypeId    = methodParameterList.get(i).getParameterDataTypeIdentifier();
		 parameterDataTypeName  = methodParameterList.get(i).getParameterDataTypeName();
		 parameterPackageName   = methodParameterList.get(i).getParameterPackageName();
		 parameterClassName     = methodParameterList.get(i).getParameterClassName();
		 parameterClassMethodId = methodParameterList.get(i).getParameterClassMethodIdentifier();	
		 parameterTextFileName  = methodParameterList.get(i).getParameterTextFileName();
		 parameterProgLangId    = methodParameterList.get(i).getParameterProgrammingLanguageIdentifier();
		 parameterReturnType    = methodParameterList.get(i).getParameterReturnType();
		 parameterClassInfoId   = methodParameterList.get(i).getParameterClassInfoIdentifier();	
		 parameterEnumClassFlag = methodParameterList.get(i).getParameterEnumClassFlag();
		 
		 methodId               = methodParameterList.get(i).getMethodIdentifier();
		 methodPackageName      = methodParameterList.get(i).getMethodPackageName();
		 methodClassName        = methodParameterList.get(i).getMethodClassName();
		 methodShortName        = methodParameterList.get(i).getMethodShortName();
		 methodClassMethodId    = methodParameterList.get(i).getMethodClassMethodIdentifier();
		 methodTextFileName     = methodParameterList.get(i).getMethodTextFileName();
		 
		 //System.out.println("3");
 
         /*		 
		 if(methodId == 18)
		 {	
		 System.out.println("**************************************************"); 
		 System.out.println("*** METHOD INFORMATION ***");
		 System.out.println("**************************************************"); 
		 System.out.println("Method ID                 : " + methodId);
		 System.out.println("Method Package Name       : " + methodPackageName);
		 System.out.println("Method Class Name         : " + methodClassName);
		 System.out.println("Method Short Name         : " + methodShortName);
		 System.out.println("Method Class Method Id    : " + methodClassMethodId);
		 System.out.println("Method Text File          : " + methodTextFileName);
		 System.out.println("**************************************************"); 
		 System.out.println("*** PARAMETER INFORMATION ***");
		 System.out.println("**************************************************"); 
		 System.out.println("Parameter Id              : " + parameterId);
		 System.out.println("Parameter Name            : " + parameterName);
		 System.out.println("Parameter Data Type Id    : " + parameterDataTypeId);
		 System.out.println("Parameter Data Type Name  : " + parameterDataTypeName);
		 System.out.println("Parameter Package Name    : " + parameterPackageName);
		 System.out.println("Parameter Class Name      : " + parameterClassName);
		 System.out.println("Parameter Class Method Id : " + parameterClassMethodId);
		 System.out.println("Parameter Text File Name  : " + parameterTextFileName);
		 System.out.println("Parameter Prog Lang Id    : " + parameterProgLangId);
		 System.out.println("Parameter Return Type     : " + parameterReturnType);
		 System.out.println("Parameter Class Info Id   : " + parameterClassInfoId);
		 System.out.println("Parameter Enum Class      : " + parameterEnumClassFlag);
		 System.out.println("**************************************************"); 
		 }
		 */
		 
		 // Get the Maximum Counter for this Attribute
   	     try 
 	     {	 	
 	        attributeCounter = umlSeqDiagramCon.getAttributeId(projectId, methodId, parameterId, parameterDataTypeId, parameterProgLangId);
 	     }  
 	     catch(Exception e2)
 	     {
 	        errorMessage = e2.getMessage();
 		    throw new Exception(errorMessage);
 	     }
   	     
   	     //System.out.println("4");
		 
		 if(parameterEnumClassFlag == 0)
		 {	 
		    // The Class is not an Enum Data Type	 
			 
		    //System.out.println("NO ES UN ENUM");
		 
		    // Get the Attributes of this Class
            try
		    {
		       classAttributeList = umlSeqDiagramCon.loadClassAttributes(projectId, parameterClassInfoId);			      	   
		    }
		    catch(Exception e3)
		    {
		       errorMessage = e3.getMessage();
			   throw new Exception(errorMessage);
		    }
            
            //System.out.println("5");
         
		    //System.out.println(Arrays.deepToString(classAttributeList.toArray()));
            		    
		    for (j = 0; j < classAttributeList.size(); j++) 
		    {
		       attributeId          = classAttributeList.get(j).getId();
		       attributePackageName = classAttributeList.get(j).getPackageName();
		       attributeClassName   = classAttributeList.get(j).getClassName();
		       attributeShortName   = classAttributeList.get(j).getShortName();
		       attributeDataType    = classAttributeList.get(j).getDataType();
		    
		       attributeCounter++;
		       
		       //System.out.println("6");
		    
		    /*   
		    if(methodId == 18)
			{
		    System.out.println("ENTRE AL CASO DE CLASS ATTRIBUTE");	
	        System.out.println("**************************************************"); 
			System.out.println("*** ATTRIBUTES INFORMATION ***");
			System.out.println("**************************************************"); 
			 
		    System.out.println("Attribute Id              : " + attributeId);
		    System.out.println("Attribute Package Name    : " + attributePackageName);
		    System.out.println("Attribute Class Name      : " + attributeClassName);
		    System.out.println("Attribute Short Name      : " + attributeShortName);
		    System.out.println("Attribute Data Type       : " + attributeDataType);
		    System.out.println("Programming Language      : " + frlCon.programmingLanguage);
		    }
		    */
		    
		       // Initialize variables
		       //attributeDataType1   = "";
		       attributeDataTypeId2 = 0;
		       plId2                = 0;
		       attributeValue       = "";
		       attributeClassInfoId = 0;
		    
		       attributeMethod = "CLASS_ATTRIBUTE";
		       
		       //System.out.println("7");
		       
		       try
		       {
		          dataType = obtainAttributeDataType(projectId,
		        		                             attributePackageName, attributeClassName, 
                                                  	 attributeDataType, frlCon,
                                                  	 umlSeqDiagramCon);
		       }
		       catch(Exception e4)
		       {
		    	   errorMessage = e4.getMessage();
		    	   throw new Exception(errorMessage);
		       }
		       
		       //System.out.println("8");
            
		       // Obtain the Attribute Data Type
		       if(dataType != null ) 
		       {
		    	   attributeDataTypeId2   = dataType.getDataTypeIdentifier1();  
		    	   attributeDataTypeName2 = dataType.getParameterName();
		    	   plId2                  = dataType.getProgLanguageIdentifier();
		    	   
		       }
		       
		       /*
		       System.out.println("9");
		       
		       System.out.println("Attribute Data Type 2   : " + attributeDataTypeId2);
		       System.out.println("Programming Language 2  : " + plId2);
               */
		       
		       if(attributeDataType.equals("PersonalizedClass"))
		       {	
		    	   attributeValue = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
		       
		    	   // Get the Class Information Id from this Package Name and Class Name
		    	   try
		    	   {
		    		   attributeClassInfoId = umlSeqDiagramCon.getClassInformation(projectId, attributePackageName, attributeClassName);
		    	   }
		    	   catch(Exception e5)
		    	   {
		    		   errorMessage = e5.getMessage();
		    		   throw new Exception(errorMessage);
		    	   }
		       }
		       
		       valueCompare = frlCon.classDataType.toLowerCase();
		       
		       //System.out.println("10");
		    		   
		       if(attributeDataTypeName2.equals(valueCompare))
		       {	    
		          //attributeName  = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
                  //attributeValue = attributeShortName;
		          attributeName = attributeShortName;
		          attributeValue = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
		          
                  
                  //System.out.println("ENTRE AQUI");
		       }
		       else
		       {	   
		          attributeName = attributeShortName;  
		          //System.out.println("ENTRE ACA");
		       }   
		       
		       /*
		       System.out.println("11");

		       
		       System.out.println("Attribute Data Type Id 2   : " + attributeDataTypeId2);
		       
		       System.out.println("Attribute Data Type Name 2 : " + attributeDataTypeName2);
		       
		       System.out.println("Attribute Name             : " + attributeName);
		       System.out.println("Attribute Value            : " + attributeValue);
		       */
		       
		       // Build a new Record attributeSeqDiagramRecord2
		       attributeSeqDiagramRecord = new AttributeSequenceDiagram(projectId, methodId, parameterId,
        		     												 	parameterDataTypeId, parameterProgLangId, attributeCounter,
        		     												 	attributeMethod, parameterClassMethodId, 
        		     												 	attributeName, attributeValue,
        		     												 	attributeDataTypeId2, plId2, attributePackageName, 
        		     												 	attributeClassName, 0, parameterClassInfoId, attributeId, 
        		     												 	attributeClassInfoId);
            
		       //System.out.println("12");
		       
		       // Save the Attribute in the Database
		       try
		       {
		    	   umlSeqDiagramCon.saveAttributeSequenceDiagram(attributeSeqDiagramRecord);
		       }
		       catch(Exception e6)
		       {
		    	   errorMessage = e6.getMessage();
		    	   throw new Exception(errorMessage);
		       } 
		       
		       //System.out.println("13");
            
		    
		    }
	     }
		 else
		 {
			
			//System.out.println("ENTRE AL CASO DE ENUM");
			
		    // The Class is an Enum Data Type
			 
			attributeCounter++; 
			attributeMethod = "ENUM";
			

			
			//attributeValue     = parameterPackageName + frlCon.objectOrientedDelimiter1 + parameterClassName;
			
			attributeDataTypeId2 = parameterDataTypeId;
			plId2                = parameterProgLangId;
		    
			attributePackageName = parameterPackageName;
			attributeClassName   = parameterClassName;
			attributeId = 0;
			attributeClassInfoId = parameterClassInfoId;
			
			attributeShortName = parameterName;
		    attributeName  = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
            attributeValue = attributeShortName;
			
            /*
            System.out.println("14");
 		    
			System.out.println("Attribute Data Type Id 2   : " + attributeDataTypeId2);
			System.out.println("Attribute Name             : " + attributeName);
		    System.out.println("Attribute Value            : " + attributeValue);
            

			
			System.out.println("Parameter Class Method Id : " + parameterClassMethodId);
			System.out.println("Parameter Class Info Id   : " + parameterClassInfoId);
			System.out.println("Attribute Class Info Id   : " + attributeClassInfoId);
			*/
			
		    // Build a new Record attributeSeqDiagramRecord2
		    attributeSeqDiagramRecord = new AttributeSequenceDiagram(projectId, methodId, parameterId,
     		     												 	 parameterDataTypeId, parameterProgLangId, attributeCounter,
     		     												 	 attributeMethod, parameterClassMethodId, 
     		     												 	 attributeName, attributeValue,
     		     												 	 attributeDataTypeId2, plId2, attributePackageName, 
     		     												 	 attributeClassName, 0, parameterClassInfoId, attributeId, 
     		     												 	 attributeClassInfoId);
          
		    //System.out.println("15");
		    
		    // Save the Attribute in the Database
		    try
		    {
		       umlSeqDiagramCon.saveAttributeSequenceDiagram(attributeSeqDiagramRecord);
		    }
		    catch(Exception e7)
		    {
		       errorMessage = e7.getMessage();
		       throw new Exception(errorMessage);
		    }
		    
		    //System.out.println("16");
			 
		 }

		 
	  }
	  
	  //System.out.println("17");	   
	  
   }
   
   public void saveValuesSequenceDiagram(int projectId, LoadUMLSequenceDiagramController umlSeqDiagramCon,
                                         FRLConfiguration frlCon) throws Exception
   {
      String errorMessage="", attributePackageName="", attributeClassName="", attributeShortName="", 
    		 attributeDataType="", valueName="", valueEquivalence=""; 
      int i=0, j=0, attributeId1=0, attributeId2=0, attributeDataTypeId=0, plId=0, valueId=0, methodId=0, parameterId=0, 
    	  parameterDataTypeId=0, parameterProgLangId=0, attributeClassInfoId=0, cmId=0;
      
      ArrayList<AttributeSequenceDiagram> attributeSeqDiagramList = new ArrayList<AttributeSequenceDiagram>(); 
  	  ArrayList<ClassAttribute> attributeList = new ArrayList<ClassAttribute>();
  	  DataType dataType = null;
  	  ValueSequenceDiagram valueSeqDiagram;
      
  	  // Initialize the ArrayLists
  	  attributeSeqDiagramList.clear();
  	  attributeList.clear();
	        
      // Load the information of the Attributes that have a Class Data Type
      try
      {
         attributeSeqDiagramList = umlSeqDiagramCon.loadAttributeInformation(projectId);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
    	 throw new Exception(errorMessage);
      } 
      
      //System.out.println("Check if the ArrayList is EMPTY: "+ attributeSeqDiagramList.isEmpty());
      		
      // Loop of the Class Information Identifiers List
      for (i = 0; i < attributeSeqDiagramList.size(); i++) 
	  {
         methodId             = attributeSeqDiagramList.get(i).getMethodIdentifier();
         parameterId          = attributeSeqDiagramList.get(i).getParameterIdentifier();
         parameterDataTypeId  = attributeSeqDiagramList.get(i).getDataTypeIdentifier1();
         parameterProgLangId  = attributeSeqDiagramList.get(i).getProgrammingLanguageIdentifier1();
         attributeId1         = attributeSeqDiagramList.get(i).getAttributeIdentifier();
         attributeClassInfoId = attributeSeqDiagramList.get(i).getClassInfoIdentifier2();
         
         /*if( methodId == 10 || methodId == 11 || methodId == 12 || methodId == 15 || methodId == 16  )
         {
         	 
         System.out.println("*** INSIDE ATTRIBUTE LOOP ***");
         System.out.println("Method Id              : " + methodId);
         System.out.println("Parameter Id           : " + parameterId);
         System.out.println("Parameter Data Type Id : " + parameterDataTypeId);
         System.out.println("Parameter Prog Lang Id : " + parameterProgLangId);
         System.out.println("Attibute Class Info Id : " + attributeClassInfoId);
         }
         */
         
         // For each of the Class Information Identifier
         // Load the attributes
         try
         {
            attributeList = umlSeqDiagramCon.loadClassAttributes(projectId, attributeClassInfoId);
         }
         catch(Exception e2)
         {
            errorMessage = e2.getMessage();
       	    throw new Exception(errorMessage);
         } 
         
         /*if( methodId == 10 || methodId == 11 || methodId == 12 || methodId == 15 || methodId == 16  )
         {
        	 System.out.println("Attribute List: " + attributeList.size()); 
         }
         */
         
         // Loop of the Attributes List
         for (j = 0; j < attributeList.size(); j++) 
   	     {  
            attributeId2         = attributeList.get(j).getId();
            attributePackageName = attributeList.get(j).getPackageName();
            attributeClassName   = attributeList.get(j).getClassName();
            attributeShortName   = attributeList.get(j).getShortName();
            attributeDataType    = attributeList.get(j).getDataType();
            
            
            /*
            System.out.println("Attribute Id           : " + attributeId2);
            System.out.println("Attribute Package Name : " + attributePackageName);
            System.out.println("Attribute Class Name   : " + attributeClassName);
            System.out.println("Attribute Short Name   : " + attributeShortName);
            System.out.println("Attribute Data Type    : " + attributeDataType);
            */
            
            // Initialize Variables
            attributeDataTypeId = 0;
            plId = 0;
            
            try
            {
               dataType = obtainAttributeDataType(projectId, 
            		                              attributePackageName, attributeClassName, 
                                                  attributeDataType, frlCon,
                                                  umlSeqDiagramCon);
            }
            catch(Exception e3)
            {
               errorMessage = e3.getMessage();
           	   throw new Exception(errorMessage);
            }
            
            // Obtain the Attribute Data Type
            if(dataType != null ) 
            {
               attributeDataTypeId = dataType.getDataTypeIdentifier1();  
               plId                = dataType.getProgLanguageIdentifier();
            }
            
            /*
            System.out.println("Attribute Data Type  ID   : " + attributeDataTypeId);
            System.out.println("Attribute Data Type  Name : " + attributeClassName);
            System.out.println("Programming Language Id   : " + plId);
            */
            
            // 1.- Build a new Value Record
        	valueId++;
        	valueName = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
        	valueEquivalence = "";
        	
        	/*
        	System.out.println("Value Id          : " + valueId);
        	System.out.println("Value Name        : " + valueName);
        	System.out.println("Value Equivalence : " + valueEquivalence);
        	*/
        	
            // Build a new Record valueSeqDiagram         	
        	valueSeqDiagram = new ValueSequenceDiagram(projectId, methodId, parameterId,
        			                               	   parameterDataTypeId, parameterProgLangId, 
        			                               	   attributeId1, valueId, attributeShortName, 
        			                               	   valueEquivalence,  attributePackageName, 
        			                               	   attributeClassName,    
        			                               	   attributeDataTypeId, plId,
        			                               	   attributeClassInfoId, attributeId2, cmId);         
                    
            // 2.- Insert the record into the Value Sequence Diagram Table
            try
            {
               umlSeqDiagramCon.saveValueSequenceDiagram(valueSeqDiagram);
            }
            catch(Exception e4)
            {
               errorMessage= e4.getMessage();
               throw new Exception(errorMessage);
            }                             
	         
	     }	
         
	  }      
       
   }
   
   
   
  public void saveReturnTypeAttributesSequenceDiagram(int projectId, LoadUMLSequenceDiagramController umlSeqDiagramCon,
		                                              FRLConfiguration frlCon) throws Exception
  {
	  String errorMessage = "", parameterName="", parameterDataTypeName="", parameterPackageName="", 
			 parameterClassName="", methodShortName="", parameterTextFileName="", 
			 methodTextFileName="", methodPackageName="", methodClassName="", parameterReturnType="",
			 attributePackageName="", attributeClassName="", attributeShortName="", attributeDataType="",
			 attributeMethod="", attributeName="", attributeValue="", attributeDataTypeName2="", valueCompare="";
	  int i=0, j=0, parameterId=0, methodId=0, parameterDataTypeId=0, parameterClassMethodId=0, 
		  parameterProgLangId=0, parameterClassInfoId=0, parameterEnumClassFlag=0, methodClassMethodId=0, attributeId=0,
		  attributeDataTypeId2=0, plId2=0, attributeCounter=0, attributeClassInfoId=0;
     ArrayList<MethodParameterData> methodReturnTypeList;
     ArrayList<ClassAttribute> classAttributeList = new ArrayList<ClassAttribute>(); 
     AttributeSequenceDiagram attributeSeqDiagramRecord = null;
     DataType dataType = null;
     
     //MethodParameterData methodParameterRecord;
     //String[] attributeValues = null;
     /*cmId1=0, cmId2=0,*/ 
     //attributeDataType1=""
     
     //System.out.println("Inside the Save Parameters Attributes Method ...");
     
     // Get the Return Type and the Methods Information from the Database 
	  try 
	  {
        methodReturnTypeList = umlSeqDiagramCon.loadMethodsParametersData(projectId, frlCon.bluePrintObject2, frlCon.classDataType);
        
        /*
        System.out.println("Method Parameters : " + methodParameterList.size());
	     System.out.println("Elements          : " + Arrays.toString(methodParameterList.toArray()));
	     */
	     
	  }
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  // Loop to iterate all the methods that contains parameters 
	  // from the methodParameterList ArrayList
	  
	  for (i = 0; i < methodReturnTypeList.size(); i++) 
	  { 
	     // Getting all data  from the  methodReturnTypeList ArrayList
	     parameterId            = methodReturnTypeList.get(i).getParameterIdentifier(); 
		 parameterName          = methodReturnTypeList.get(i).getParameterName(); 
		 parameterDataTypeId    = methodReturnTypeList.get(i).getParameterDataTypeIdentifier();
		 parameterDataTypeName  = methodReturnTypeList.get(i).getParameterDataTypeName();
		 parameterPackageName   = methodReturnTypeList.get(i).getParameterPackageName();
		 parameterClassName     = methodReturnTypeList.get(i).getParameterClassName();
		 parameterClassMethodId = methodReturnTypeList.get(i).getParameterClassMethodIdentifier();	
		 parameterTextFileName  = methodReturnTypeList.get(i).getParameterTextFileName();
		 parameterProgLangId    = methodReturnTypeList.get(i).getParameterProgrammingLanguageIdentifier();
		 parameterReturnType    = methodReturnTypeList.get(i).getParameterReturnType();
		 parameterClassInfoId   = methodReturnTypeList.get(i).getParameterClassInfoIdentifier();	
		 parameterEnumClassFlag = methodReturnTypeList.get(i).getParameterEnumClassFlag();
		 
		 methodId               = methodReturnTypeList.get(i).getMethodIdentifier();
		 methodPackageName      = methodReturnTypeList.get(i).getMethodPackageName();
		 methodClassName        = methodReturnTypeList.get(i).getMethodClassName();
		 methodShortName        = methodReturnTypeList.get(i).getMethodShortName();
		 methodClassMethodId    = methodReturnTypeList.get(i).getMethodClassMethodIdentifier();
		 methodTextFileName     = methodReturnTypeList.get(i).getMethodTextFileName();

		 /*
		 if(methodId == 18)
		 {	 
		 System.out.println("**************************************************"); 
		 System.out.println("*** METHOD INFORMATION ***");
		 System.out.println("**************************************************"); 
		 System.out.println("Method ID                 : " + methodId);
		 System.out.println("Method Package Name       : " + methodPackageName);
		 System.out.println("Method Class Name         : " + methodClassName);
		 System.out.println("Method Short Name         : " + methodShortName);
		 System.out.println("Method Class Method Id    : " + methodClassMethodId);
		 System.out.println("Method Text File          : " + methodTextFileName);
		 System.out.println("**************************************************"); 
		 System.out.println("*** PARAMETER INFORMATION ***");
		 System.out.println("**************************************************"); 
		 System.out.println("Parameter Id              : " + parameterId);
		 System.out.println("Parameter Name            : " + parameterName);
		 System.out.println("Parameter Data Type Id    : " + parameterDataTypeId);
		 System.out.println("Parameter Data Type Name  : " + parameterDataTypeName);
		 System.out.println("Parameter Package Name    : " + parameterPackageName);
		 System.out.println("Parameter Class Name      : " + parameterClassName);
		 System.out.println("Parameter Class Method Id : " + parameterClassMethodId);
		 System.out.println("Parameter Text File Name  : " + parameterTextFileName);
		 System.out.println("Parameter Prog Lang Id    : " + parameterProgLangId);
		 System.out.println("Parameter Return Type     : " + parameterReturnType);
		 System.out.println("Parameter Class Info Id   : " + parameterClassInfoId);
		 System.out.println("Parameter Enum Class      : " + parameterEnumClassFlag);
		 System.out.println("**************************************************"); 
		 }
		 */
		 
		 // Get the Maximum Counter for this Attribute
  	     try 
	     {	 	
	        attributeCounter = umlSeqDiagramCon.getAttributeId(projectId, methodId, parameterId, parameterDataTypeId, parameterProgLangId);
	     }  
	     catch(Exception e2)
	     {
	        errorMessage = e2.getMessage();
		    throw new Exception(errorMessage);
	     }
		 
		 if(parameterEnumClassFlag == 0)
		 {	 
		    // The Class is not an Enum Data Type	 
		 
		    // Get the Attributes of this Class
           try
		    {
		       classAttributeList = umlSeqDiagramCon.loadClassAttributes(projectId, parameterClassInfoId);			      	   
		    }
		    catch(Exception e3)
		    {
		       errorMessage = e3.getMessage();
			   throw new Exception(errorMessage);
		    }
        
		    //System.out.println(Arrays.deepToString(classAttributeList.toArray()));
           		    
		    for (j = 0; j < classAttributeList.size(); j++) 
		    {
		       attributeId          = classAttributeList.get(j).getId();
		       attributePackageName = classAttributeList.get(j).getPackageName();
		       attributeClassName   = classAttributeList.get(j).getClassName();
		       attributeShortName   = classAttributeList.get(j).getShortName();
		       attributeDataType    = classAttributeList.get(j).getDataType();
		    
		       attributeCounter++;
		    
		       /*
		    if(methodId == 18)
			{
		    System.out.println("ENTRE AL CASO DE CLASS ATTRIBUTE");	
	        System.out.println("**************************************************"); 
			System.out.println("*** ATTRIBUTES INFORMATION ***");
			System.out.println("**************************************************"); 
			 
		    System.out.println("Attribute Id              : " + attributeId);
		    System.out.println("Attribute Package Name    : " + attributePackageName);
		    System.out.println("Attribute Class Name      : " + attributeClassName);
		    System.out.println("Attribute Short Name      : " + attributeShortName);
		    System.out.println("Attribute Data Type       : " + attributeDataType);
		    System.out.println("Programming Language      : " + frlCon.programmingLanguage);
		    }
		    */
		    
		       // Initialize variables
		       //attributeDataType1   = "";
		       attributeDataTypeId2 = 0;
		       plId2                = 0;
		       attributeValue       = "";
		       attributeClassInfoId = 0;
		    
		       attributeMethod = "CLASS_ATTRIBUTE";
		       
		       try
		       {
		          dataType = obtainAttributeDataType(projectId,
		        		                             attributePackageName, attributeClassName, 
                                                 	 attributeDataType, frlCon,
                                                 	 umlSeqDiagramCon);
		       }
		       catch(Exception e4)
		       {
		    	   errorMessage = e4.getMessage();
		    	   throw new Exception(errorMessage);
		       }
           
		       // Obtain the Attribute Data Type
		       if(dataType != null ) 
		       {
		    	   attributeDataTypeId2   = dataType.getDataTypeIdentifier1();  
		    	   attributeDataTypeName2 = dataType.getParameterName();
		    	   plId2                  = dataType.getProgLanguageIdentifier();
		    	   
		       }
		       
		       /*
		       System.out.println("Attribute Data Type 2   : " + attributeDataTypeId2);
		       System.out.println("Programming Language 2  : " + plId2);
             */
		       
		       if(attributeDataType.equals("PersonalizedClass"))
		       {	
		    	   attributeValue = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
		       
		    	   // Get the Class Information Id from this Package Name and Class Name
		    	   try
		    	   {
		    		   attributeClassInfoId = umlSeqDiagramCon.getClassInformation(projectId, attributePackageName, attributeClassName);
		    	   }
		    	   catch(Exception e5)
		    	   {
		    		   errorMessage = e5.getMessage();
		    		   throw new Exception(errorMessage);
		    	   }
		       }
		       
		       valueCompare = frlCon.classDataType.toLowerCase();
		    		   
		       if(attributeDataTypeName2.equals(valueCompare))
		       {	    
		          attributeName  = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
                 attributeValue = attributeShortName;
                 //System.out.println("ENTRE AQUI");
		       }
		       else
		       {	   
		          attributeName = attributeShortName;  
		          //System.out.println("ENTRE ACA");
		       }   

		       /*
		       System.out.println("Attribute Data Type Id 2   : " + attributeDataTypeId2);
		       
		       System.out.println("Attribute Data Type Name 2 : " + attributeDataTypeName2);
		       
		       System.out.println("Attribute Name             : " + attributeName);
		       System.out.println("Attribute Value            : " + attributeValue);
		       */
		       
		       // Build a new Record attributeSeqDiagramRecord2
		       attributeSeqDiagramRecord = new AttributeSequenceDiagram(projectId, methodId, parameterId,
       		     												 	parameterDataTypeId, parameterProgLangId, attributeCounter,
       		     												 	attributeMethod, parameterClassMethodId, 
       		     												 	attributeName, attributeValue,
       		     												 	attributeDataTypeId2, plId2, attributePackageName, 
       		     												 	attributeClassName, 0, parameterClassInfoId, attributeId, 
       		     												 	attributeClassInfoId);
           
		       // Save the Attribute in the Database
		       try
		       {
		    	   umlSeqDiagramCon.saveAttributeSequenceDiagram(attributeSeqDiagramRecord);
		       }
		       catch(Exception e6)
		       {
		    	   errorMessage = e6.getMessage();
		    	   throw new Exception(errorMessage);
		       } 
           
		    
		    }
	     }
		 else
		 {
			
			//System.out.println("ENTRE AL CASO DE ENUM");
			
		    // The Class is an Enum Data Type
			 
			attributeCounter++; 
			attributeMethod = "ENUM";
			

			
			//attributeValue     = parameterPackageName + frlCon.objectOrientedDelimiter1 + parameterClassName;
			
			attributeDataTypeId2 = parameterDataTypeId;
			plId2                = parameterProgLangId;
		    
			attributePackageName = parameterPackageName;
			attributeClassName   = parameterClassName;
			attributeId = 0;
			attributeClassInfoId = parameterClassInfoId;
			
			attributeShortName = parameterName;
		    attributeName  = attributePackageName + frlCon.objectOrientedDelimiter1 + attributeClassName;
            attributeValue = attributeShortName;
			
		    /*
			System.out.println("Attribute Data Type Id 2   : " + attributeDataTypeId2);
			System.out.println("Attribute Name             : " + attributeName);
		    System.out.println("Attribute Value            : " + attributeValue);
           */ 

			/*
			System.out.println("Parameter Class Method Id : " + parameterClassMethodId);
			System.out.println("Parameter Class Info Id   : " + parameterClassInfoId);
			System.out.println("Attribute Class Info Id   : " + attributeClassInfoId);
			*/
			
		    // Build a new Record attributeSeqDiagramRecord2
		    attributeSeqDiagramRecord = new AttributeSequenceDiagram(projectId, methodId, parameterId,
    		     												 	 parameterDataTypeId, parameterProgLangId, attributeCounter,
    		     												 	 attributeMethod, parameterClassMethodId, 
    		     												 	 attributeName, attributeValue,
    		     												 	 attributeDataTypeId2, plId2, attributePackageName, 
    		     												 	 attributeClassName, 0, parameterClassInfoId, attributeId, 
    		     												 	 attributeClassInfoId);
         
		    // Save the Attribute in the Database
		    try
		    {
		       umlSeqDiagramCon.saveAttributeSequenceDiagram(attributeSeqDiagramRecord);
		    }
		    catch(Exception e7)
		    {
		       errorMessage = e7.getMessage();
		       throw new Exception(errorMessage);
		    } 
			 
		 }

		 
	  }
	  
	  	   
  }  
 
   public void deletePreviousSeqDiagramsData(int projectId, String databaseConfigFile) throws Exception
   {
      String errorMessage="";
      LoadUMLSequenceDiagramController umlSeqDiagramCon = new LoadUMLSequenceDiagramController();

	  // Connecting to the Database
	  try 
	  {
	     umlSeqDiagramCon.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }	
      
      // Delete the previous Method Files Sequence Diagram for this projectId
	  // method_file_sequence_diagram
      try 
      {
    	  umlSeqDiagramCon.deleteMethodFilesSequenceDiagram(projectId);
      }
      catch(Exception e2)
      {
         errorMessage = e2.getMessage();
 		 throw new Exception(errorMessage);
      }
      
      // Delete the previous User Files Sequence Diagram for this projectId
      // user_file_sequence_diagram
      try 
      {
    	  umlSeqDiagramCon.deleteUserFilesSequenceDiagram(projectId);
      }
      catch(Exception e3)
      {
         errorMessage = e3.getMessage();
 		 throw new Exception(errorMessage);
      }
      
	  // Delete all the existing Sequence Diagrams Final for this Project Id
      // uml_sequence_diagram_final
	  try 
	  {
	     umlSeqDiagramCon.deleteSequenceDiagramsFinal(projectId);
	  } 
	  catch (Exception e4) 
	  {
	     errorMessage = e4.getMessage();
		 throw new Exception(errorMessage);
	  }	
	  
      // Delete all the existing Values for this Project Id
	  // value_sequence_diagram
	  try 
	  {
	     umlSeqDiagramCon.deleteValuesSequenceDiagram(projectId);
	  } 
	  catch (Exception e5) 
	  {
	     errorMessage = e5.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  	  
	  
      // Delete all the existing Return Types Attributes for this Project Id
	  // return_type_attribute_sequence_diagram
	  try 
	  {
	     umlSeqDiagramCon.deleteAttributesSequenceDiagram(projectId);
	  } 
	  catch (Exception e6) 
	  {
	     errorMessage = e6.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  
	  	  	  
      // Delete all the existing Parameters for this Project Id
	  // parameter_sequence_diagram
	  try 
	  {
	     umlSeqDiagramCon.deleteParametersSequenceDiagram(projectId);
	  } 
	  catch (Exception e7) 
	  {
	     errorMessage = e7.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  
	  // Delete all the existing Return Type Values for this Project Id
	  // return_type_value_sequence_diagram
	  try 
	  {
	     umlSeqDiagramCon.deleteReturnTypeValueSequenceDiagram(projectId);
	  } 
	  catch (Exception e8) 
	  {
	     errorMessage = e8.getMessage();
		 throw new Exception(errorMessage);
	  }	 
		  
	  /*
	  // Delete all the existing Return Type Attributes for this Project Id
      // return_type_attribute_sequence_diagram 
	  try 
	  {
	     umlSeqDiagramCon.deleteReturnTypeAttributesSequenceDiagram(projectId);
	  } 
	  catch (Exception e9) 
	  {
	     errorMessage = e9.getMessage();
		 throw new Exception(errorMessage);
	  }	 
		  
	  // Delete all the existing Return Types Values for this Project Id
	  // return_type_value_sequence_diagram 
	  try 
	  {
	     umlSeqDiagramCon.deleteReturnTypeValuesSequenceDiagram_1(projectId);
	  } 
	  catch (Exception e10) 
	  {
	     errorMessage = e10.getMessage();
		 throw new Exception(errorMessage);
	  }	 	  
	  */
	  
	  // Delete all the existing Method Steps for this Project Id
	  // method_step_sequence_diagram
	  try
	  {
	     umlSeqDiagramCon.deleteMethodStepsSequenceDiagram(projectId);
	  }
	  catch(Exception e11)
	  {
	     errorMessage = e11.getMessage();
		 throw new Exception(errorMessage);
	  }
      
	  // Delete all the Existing Methods for this Project Id
	  // method_sequence_diagram 
	  try 
	  {
	     umlSeqDiagramCon.deleteMethodsSequenceDiagram(projectId);
	  } 
	  catch (Exception e12) 
	  {
	     errorMessage = e12.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  
	  // Delete All the existing Users for this Project Id
	  // user_sequence_diagram 
	  try 
	  {
	     umlSeqDiagramCon.deleteUsersSequenceDiagram(projectId);
	  } 
	  catch (Exception e13) 
	  {
	     errorMessage = e13.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  
	  
      // Delete all the existing Parameters for this Project Id
	  // parameter_sequence_diagram
	  try 
	  {
	     umlSeqDiagramCon.deleteReturnTypeSequenceDiagram(projectId);
	  } 
	  catch (Exception e9) 
	  {
	     errorMessage = e9.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  
	  

	  // Disconnect from the database
	  try 
	  {
	     umlSeqDiagramCon.disconnect();
	  } 
	  catch (Exception e14) 
	  {
	     errorMessage = e14.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
   }
   
   public void updateMethodStepNumberPlain(LoadUMLSequenceDiagramController umlSeqDiagramCon, 
		                                   int projectId, String value1, String value2) throws Exception
   {
      String errorMessage="", packageName="", className="", methodName1="", orderIdStr="";
      int u=0, m=0, userId=0, methodId=0, stepId=0, s=0, orderId=0;
      
      ArrayList<UserSequenceDiagram> users;
      ArrayList<Integer> methods;
      ArrayList<UMLSequenceDiagramPlain> methodSteps1;
      MethodStepSequenceDiagram methodStep2;
      UMLSequenceDiagramPlain seqDiagramPlain;
      
      // Get all the distinct Users from the Database
	  try 
	  {
	     users = umlSeqDiagramCon.loadUsersSequenceDiagram(projectId);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  
	  // Loop to iterate all the records from the userList ArrayList
	  for (u = 0; u < users.size(); u++) 
	  {
         userId = users.get(u).getUserIdentifier();
         
         /*
         System.out.println("INSIDE THE USERS LOOP");
         System.out.println("User Id: "+ userId);
         */
	      
         // Get all the methods for this User Id
	     try
	     {
	        methods = umlSeqDiagramCon.loadMethodIdsPlain(projectId, userId, value1);  
	     }
		 catch(Exception e2)
		 {
		    errorMessage = e2.getMessage();
			throw new Exception(errorMessage);
		 }
	     
	     // Loop to iterate all the records from the methodList ArrayList
		 for (m = 0; m < methods.size(); m++) 
		 {
		    methodId = methods.get(m);
		    
		    /*
		    System.out.println("INSIDE THE METHODS LOOP");
		    System.out.println("Method Id: "+ methodId);
		    System.out.println("Value    : "+ value1);
		    */
		    
		    // Get the Steps that contain this userId and methodId
		    try
		    {
		       methodSteps1 = umlSeqDiagramCon.getMethodStepsPlain(projectId, userId, methodId, value1);
		    }
			catch(Exception e3)
			{
			   errorMessage = e3.getMessage();
			   throw new Exception(errorMessage);
			}
		    
		    stepId = 0;
		    
		    // Loop to iterate all the records from the methodStepsList ArrayList
			for (s = 0; s < methodSteps1.size(); s++) 
			{
			   //lineNumber  = methodSteps1.get(s).getLineNumber();
			   packageName = methodSteps1.get(s).getMethodPackageName();
			   className   = methodSteps1.get(s).getMethodClassName();
			   methodName1 = methodSteps1.get(s).getMethodName1();
			   stepId ++;
			   
			   /*
			   System.out.println("INSIDE THE METHOD STEPS LOOP");

			   
			   System.out.println("Project Id      : " + projectId);
			   System.out.println("User    Id      : " + userId);
			   System.out.println("Method  Id      : " + methodId);
			   System.out.println("Package Name    : " + packageName);
			   System.out.println("Class Name      : " + className);
			   System.out.println("Method Name 1   : " + methodName1);
			   System.out.println("Step       Id   : " + stepId);
			   */
			   
			   // Get the order Id of the Method
			   orderIdStr = methodName1.substring(0, methodName1.indexOf(value2));
			   orderId = Integer.parseInt(orderIdStr);
			   
			   /*
			   System.out.println("Order Id Str    : " + orderIdStr);
			   System.out.println("Order Id Number : " + orderId);
			   
			   
			   System.out.println("Saving a new Method Step Sequence Diagram");
			   */
			   // Build a new record
			   methodStep2 = new MethodStepSequenceDiagram(projectId, userId, 
                                                           methodId, stepId, 
                                                           orderId, methodName1);
			   
			   // Save this Record in the Method Step Numbers Table
			   try
			   {
			      umlSeqDiagramCon.saveMethodStepSequenceDiagram(methodStep2);
			   }
			   catch(Exception e4)
			   {
			      errorMessage = e4.getMessage();
				  throw new Exception(errorMessage);
			   }
			   
			   // Build a new record
			   seqDiagramPlain = new UMLSequenceDiagramPlain(projectId, userId, methodId,
					                                         packageName, className, methodName1,
					                                         stepId);
			   
			   // Update the Method Step Numbers in the UML Sequence Diagram Plain Table
			   try
			   {
			      umlSeqDiagramCon.updateMethodStepPlain(seqDiagramPlain);
			   }
			   catch(Exception e5)
			   {
			      errorMessage = e5.getMessage();
				  throw new Exception(errorMessage);
			   }	
			}
		    
		 }
		  
	  }

   }
   
   public void saveReturnTypeValuesSequenceDiagram(int projectId, LoadUMLSequenceDiagramController umlSeqDiagramCon, 
		                                           FRLConfiguration frlCon) throws Exception
   {	
	   String errorMessage="", returnType2="", returnTypeName="", packageName="", 
			  className="", owner="", methodFullName="", attributePackageName="", attributeClassName="", 
			  attributeShortName="", attributeDataType="";

	   int m=0, methodId=0, returnTypeId=0, i=0, returnTypeValueId=0, ciId=0, attributeId=0;
	   
	   //int cmId=0;
	   //String textFileName="", methodShortName="", returnType1="", ;
	   //String[] returnTypeValues=new String[10];
	   //MethodDataSequenceDiagram methodDetSeqDiagramRecord2;
	   //ClassMethod classMethodRecord=null;
	   
	   MethodDataSequenceDiagram methodDetSeqDiagramRecord;
	   ArrayList<MethodDataSequenceDiagram> methodDetSeqDiagramList = new ArrayList<MethodDataSequenceDiagram>();
	   ReturnTypeValueSequenceDiagram returnTypeValueSeqDiagramRecord;
       ClassMethod classMethodRecord=null;
	   ArrayList<ClassAttribute> classAttributeList = new ArrayList<ClassAttribute>(); 

	   // Getting the Method Details for this Project
	   try 
	   {
	      methodDetSeqDiagramList = umlSeqDiagramCon.getMethodDetailsSequenceDiagram(projectId);
	   } 
	   catch (Exception e1) 
	   {
	      errorMessage = e1.getMessage();
		  throw new Exception(errorMessage);
	   }
	   
	   /*
	   System.out.println("Inside the Save Method Return Type Values Method ");
	   System.out.println("STARTS");
	   System.out.println("Method List: " + methodDetSeqDiagramList);
	   */
	   
	   // Loop to iterate all the records from the methodDetSeqDiagramList ArrayList
	   for (m = 0; m < methodDetSeqDiagramList.size(); m++) 
	   {
	      methodId        = methodDetSeqDiagramList.get(m).getMethodIdentifier();
	      methodFullName  = methodDetSeqDiagramList.get(m).getMethodFullName();
		  returnTypeId    = methodDetSeqDiagramList.get(m).getReturnTypeIdentifier();
		  returnTypeName  = methodDetSeqDiagramList.get(m).getReturnTypeName();
	      returnType2     = methodDetSeqDiagramList.get(m).getClassMethodReturnType2();
	      
	      //methodShortName = methodDetSeqDiagramList.get(m).getMethodShortName();
		  //returnType1     = methodDetSeqDiagramList.get(m).getClassMethodReturnType1();
		  

	      /*
		  System.out.println("");
		  System.out.println("STEP - ONE - GET THE METHODS DETAILS");
		  System.out.println("Method Id         : " + methodId);
		  System.out.println("Method Full Name  : " + methodFullName);
		  System.out.println("Return Type 2     : " + returnType2);
		  System.out.println("Return Type Id    : " + returnTypeId);
		  System.out.println("Return Type Name  : " + returnTypeName);
		  */
		  
	      /*
		  System.out.println("Method Id     : " + methodId);
		  System.out.println("Return Type 1 : " + returnTypeId);
		  */
		  
			
		  // Buid a new methodDetSeqDiagram Record 
		  methodDetSeqDiagramRecord = new MethodDataSequenceDiagram(projectId, methodId, returnTypeId); 
		  
		  
			
		  // Update the Return Type in the Method Sequence Diagram Table
		  try
		  {
		     umlSeqDiagramCon.updateReturnTypeMethodSequenceDiagram(methodDetSeqDiagramRecord);
		  }
		  catch(Exception e2)
		  {
		     errorMessage = e2.getMessage();
			 throw new Exception(errorMessage);
		  }
		  
		  // Validate if the returnType Name is equals to Class or Enum
		  if(returnTypeName.equals(frlCon.bluePrintObject2) || 
			 returnTypeName.equals(frlCon.classDataType))
			  
		  {	  
			 // packageName     = classMethodReturnType.split(frlCon.findDot)[0];
			 // className       = classMethodReturnType.split(frlCon.findDot)[1];
			 // methodShortName = className;
			 
			 // Validate if the returnType2 contains an Array Symbol "[" 
			 if(returnType2.contains(frlCon.startArray))
			 {	 
			    owner = returnType2.substring(0, returnType2.indexOf(frlCon.startArray));
				//System.out.println("Owner: " + owner); 
			 } 
			 else
				 owner = returnType2;
				 
			 // Initialize the variables
			 packageName = "";
			 className   = "";
			 
			 // Obtain the information of the Class: Package Name and Class Name 
			 try
             {
                classMethodRecord = getComponentsClass(projectId, owner, frlCon);
                
                packageName = classMethodRecord.getPackageName();
                className   = classMethodRecord.getClassName();
             }
             catch(Exception e3)
             {
 			    errorMessage = e3.getMessage();
 			    throw new Exception(errorMessage);
 			 } 
 			 
			 /*
			 System.out.println("");
			 System.out.println("STEP - TWO - GET THE CLASS INFORMATION DETAILS");
			 System.out.println("Return Type 2     : " + returnType2);
			 System.out.println("Package Name      : " + packageName);
			 System.out.println("Class Name        : " + className);
			 */
			 //System.out.println("Method Short Name : " + methodShortName);
			 
			 // Get the Class Information Id from this Package Name and Class Name
			 try
			 {
			    ciId = umlSeqDiagramCon.getClassInformation(projectId, packageName, className);
			 }
			 catch(Exception e4)
			 {
			    errorMessage = e4.getMessage();
				throw new Exception(errorMessage);
			 }
			 
			 //System.out.println("Class Information ID: " + ciId);
			 
			 if(ciId != 0)
			 {	 
			    // Get the Attributes of this Class
                try
			    {
			       classAttributeList = umlSeqDiagramCon.loadClassAttributes(projectId, ciId);			      	   
			    }
			    catch(Exception e5)
			    {
			       errorMessage = e5.getMessage();
				   throw new Exception(errorMessage);
			    }
             
                /*
                System.out.println("");
			    System.out.println("STEP - THREE - GET THE ATTRIBUTE INFORMATION DETAILS");
			    System.out.println(Arrays.deepToString(classAttributeList.toArray()));
			    */
			    
			    returnTypeValueId = 0;
			    
			    // Save the Method Return Values in the Database
			    for(i=0; i < classAttributeList.size(); i++) 
			    {
			       attributeId          = classAttributeList.get(i).getId();
			       attributePackageName = classAttributeList.get(i).getPackageName();
			   	   attributeClassName   = classAttributeList.get(i).getClassName();
			   	   attributeShortName   = classAttributeList.get(i).getShortName();
			   	   attributeDataType    = classAttributeList.get(i).getDataType();
			   	   
			   	   /*
			   	   System.out.println("Attribute Id           : " + attributeId);
			   	   System.out.println("Attribute Package Name : " + attributePackageName);
			   	   System.out.println("Attribute Class Name   : " + attributeClassName);
			   	   System.out.println("Attribute Short Name   : " + attributeShortName);
			   	   System.out.println("Attribute Data Type    : " + attributeDataType);
			   	   */
			   	   
			   	   returnTypeValueId ++;
			   	
			   	   // Build a New Record to insert into the  ReturnTypeValueSequenceDiagram table
		           returnTypeValueSeqDiagramRecord = new ReturnTypeValueSequenceDiagram(projectId, 
		        		                                                 				methodId, 
		        		                                                 				returnTypeId,      // Return Type Id of the Method
		        		                                                 				returnTypeValueId, // Counter of the Table
		        		                                                 				className,         // Class Information Class Name
		        		                                                 				attributeShortName, // Name/Value of the Attribute:  returnTypeValues[i], 
		        		                                                 				packageName,       // Class Information Package Name
		        		                                                 				className, 
		        		                                                 				ciId,
		        		                                                 				attributeId); 
		           
		           // Save the record into the database
			       try
			       {
			          umlSeqDiagramCon.saveReturnTypeValueSequenceDiagram(returnTypeValueSeqDiagramRecord);
			       }
			       catch(Exception e6)
			       {
			          errorMessage = e6.getMessage();
			    	  throw new Exception(errorMessage);
			       }
			    } 
			    
			 }   
			 
			 /*
		     // Buld the Method Sequence Diagram Record 2
		     methodDetSeqDiagramRecord2 = new MethodDataSequenceDiagram(projectId,
				                                                        packageName,
				                                                        className, 
				                                                        methodShortName);
		     // Get the data
		     try
		     {
		        classMethodRecord = umlSeqDiagramCon.getClassMethodDetails3(methodDetSeqDiagramRecord2);
		     }
		     catch(Exception e4)
		     { 
		        errorMessage = e4.getMessage();
			    throw new Exception(errorMessage);
		     }
		     
		     
		     // Validate there is data in the classMethodRecord
		     if(classMethodRecord != null)
		     {
		        cmId         = classMethodRecord.getId();
		        textFileName = classMethodRecord.getTextFileName();
		        
		        System.out.println("");
		        System.out.println("Return Type Values - THREE");
		        
		        System.out.println("Class Method Id          : " + cmId);
		        System.out.println("Text File Name           : " + textFileName);
		        
		        
		     }
		     
		     returnTypeValues = getEnumValues(packageName, className, textFileName, frlCon);
		     
		     System.out.println("");
		     System.out.println("Return Type Values - FOUR");
		     System.out.println("Return Type Values: " + Arrays.toString(returnTypeValues));
		     
		     returnTypeValueId = 0;
		     
		     for(i=0; i < returnTypeValues.length; i++) 
		     {
		        // Validate that the element is not null or empty
		        if(returnTypeValues[i] != null && !returnTypeValues[i].trim().isEmpty()) 
		        {
		        	
                   returnTypeValueId++;
                   
		           // Build a new record
		           returnTypeValueSeqDiagramRecord = new ReturnTypeValueSequenceDiagram(projectId, 
		        		                                                 				methodId, 
		        		                                                 				returnTypeId,
		        		                                                 				returnTypeValueId, 
		        		                                                 				className, 
		        		                                                 				returnTypeValues[i], 
		        		                                                 				packageName, 
		        		                                                 				className, 
		        		                                                 				cmId); 
		          System.out.println(""); 
		          System.out.println("Return Type Values - FIVE"); 
		          System.out.println("*** BEFORE INSERTING ***");
		          
		          System.out.println("Project Id            : " + projectId);
		          System.out.println("Method Id             : " + methodId);
		          System.out.println("Return Type Id        : " + returnTypeId);
		          System.out.println("Return Type Value Id  : " + returnTypeValueId);
		          System.out.println("Class Name            : " + className);  
		          System.out.println("Return Type Values    : " + Arrays.toString(returnTypeValues));
		          System.out.println("Package Name          : " + packageName);
		          System.out.println("Class Name            : " + className);
		          System.out.println("CM Id                 : " + cmId);
		          
		          // Save the record into the database
		     	  try
		    	  {
		     	     umlSeqDiagramCon.saveReturnTypeValueSequenceDiagram(returnTypeValueSeqDiagramRecord);
		    	  }
		    	  catch(Exception e5)
		    	  {
		    	     errorMessage = e5.getMessage();
		    		 throw new Exception(errorMessage);
		    	  }
		       }
		    }*/
		    
		     
		  }

	   }
	   
	   //System.out.println("ENDS");
 
   }
	   
   public void saveFinalSequenceDiagramData(FRLConfiguration frlCon, String databaseConfigFile, int projectId) throws Exception
   {
      String errorMessage="";
      LoadUMLSequenceDiagramController umlSeqDiagramCon = new LoadUMLSequenceDiagramController();

	  // Connecting to the Database
	  try 
	  {
	     umlSeqDiagramCon.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }	 
	  
	  System.out.println("Saving the Users from the UML Sequence Diagram into the Database ...");
	  // Save the Users into the User_Sequence_Diagram Table
	  try 
	  {	 	
	     umlSeqDiagramCon.saveUsersSequenceDiagram(projectId);
	  }  
	  catch(Exception e2)
	  {
	     errorMessage = e2.getMessage();
		 throw new Exception(errorMessage);
	  }
	    
	  System.out.println("Saving the Methods from the UML Sequence Diagram into the Database ...");
	  // Save the Methods into the Method_Sequence_Diagram Table
	  try 
	  {	 	
	     umlSeqDiagramCon.saveMethodsSequenceDiagram(projectId);
	  }  
	  catch(Exception e3)
	  {
	     errorMessage = e3.getMessage();
		 throw new Exception(errorMessage);
	  }	
	  
	  System.out.println("Updating the Method Step Numbers into the Database ...");
	  // Update the Step Numbers in the UML_Sequence_Diagram_Plain Table
	  try 
	  {	 	
	     updateMethodStepNumberPlain(umlSeqDiagramCon, projectId, frlCon.methodSpecification, frlCon.objectOrientedDelimiter1);
	  }  
	  catch(Exception e4)
	  {
	     errorMessage = e4.getMessage();
		 throw new Exception(errorMessage);
	  }	
	  	
	  
	  System.out.println("Saving the Return Type Values from the UML Sequence Diagram into the Database ...");
	  // If the Method contain a Return Type that is a Class or an Enum
	  // Get all the values that can return 
	  try 
	  {
	     saveReturnTypeValuesSequenceDiagram(projectId, umlSeqDiagramCon, frlCon);
	  } 
	  catch (Exception e5) 
	  {
	     errorMessage = e5.getMessage();	
	     System.out.println("Error Message: "+errorMessage);
	     throw new Exception(errorMessage);
	  }
	  
	  
	  //System.out.println("BEFORE SAVING PARAMETERS");
	  
	  System.out.println("Saving the Parameters from the UML Sequence Diagram into the Database ...");
	  // Save the Parameters in the case the Method has them into the Parameters_Sequence_Diagram table
	  try 
	  {
	     saveParametersSequenceDiagram(frlCon, umlSeqDiagramCon, projectId);
	  } 
	  catch (Exception e6) 
	  {
	     errorMessage = e6.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  //System.out.println("AFTER SAVING PARAMETERS");
	  	  
	  
	  //System.out.println("BEFORE SAVING ATTRIBUTES");
	  
	  System.out.println("Saving the Attributes from the UML Sequence Diagram into the Database ...");
	  // If the Parameters Methods are either a Class or an Enum
	  // Get the attributes that can be used
	  try 
	  {
	     saveAttributesSequenceDiagram(projectId, umlSeqDiagramCon, frlCon);
	  } 
	  catch (Exception e7) 
	  {
	     errorMessage = e7.getMessage();
		 throw new Exception(errorMessage);
	  } 
	  
	  //System.out.println("AFTER SAVING ATTRIBUTES");
  	  
	  //System.out.println("BEFORE SAVING VALUES");
	  
      System.out.println("Saving the Values from the UML Sequence Diagram into the Database ...");
      // If we have Attributes that are either a Class or an Enum
      // Get the values that can be used
      try 
      {
	     saveValuesSequenceDiagram(projectId, umlSeqDiagramCon, frlCon);
      } 
      catch (Exception e8) 
      {
        errorMessage = e8.getMessage();
	    throw new Exception(errorMessage);
      } 
      
      //System.out.println("AFTER SAVING VALUES");
      
      //System.out.println("BEFORE SAVING RETURN TYPES");
      
	  System.out.println("Saving the Return Types from the UML Sequence Diagram into the Database ...");
	  // Save the Return Types into the Return_Type_Sequence_Diagram table
	  try 
	  {
	     saveReturnTypesSequenceDiagram(frlCon, umlSeqDiagramCon, projectId);
	  } 
	  catch (Exception e9) 
	  {
	     errorMessage = e9.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  //System.out.println("AFTER SAVING RETURN TYPES");
	  
	  /*
	   * YA ESTABA COMENTADO
	  System.out.println("Saving the Return Type Attributes from the UML Sequence Diagram into the Database ...");
	  // If the Return Type is either a Class or an Enum
	  // Get the attributes that can be used
	  try 
	  {
	     saveReturnTypeAttributesSequenceDiagram(projectId, umlSeqDiagramCon, frlCon);
	  } 
	  catch (Exception e9) 
	  {
	     errorMessage = e9.getMessage();
		 throw new Exception(errorMessage);
	  } 
  	  */
      
      
	  System.out.println("Saving the Final UML Sequence Diagram into the Database ...");
	  // Save the Records into the UML_Sequence_Diagram_Final table
	  try 
	  {
	     saveFinalSequenceDiagram(umlSeqDiagramCon, projectId);
	  } 
	  catch (Exception e10) 
	  {
	     errorMessage = e10.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
       
	  // Disconnect from the database
	  try 
	  {
	     umlSeqDiagramCon.disconnect();
	  } 
	  catch (Exception e11) 
	  {
	     errorMessage = e11.getMessage();
		 throw new Exception(errorMessage);
	  }
	   
   }
   
   public String createUMLDirectory(FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="", errorMessage2="", dirPathName="";
      File theDir;

      // Create a new Directory inside the Input Directory for the Project Application
      try
      {
         // Build the directory name
         dirPathName = frlCon.projectOutputDir + File.separator + 
        		       frlCon.umlDirectoryName + File.separator + 
        		       frlCon.projectName; 
         
         //System.out.println("Dir Path Name: "+ dirPathName);
         theDir = new File(dirPathName);
         
         // Create a New Directory
         if (!theDir.exists())
            theDir.mkdirs();
         else
         {
        	// If the Directory exists
        	// Delete all the files that are there
            try
            {
               cleanDirectory(dirPathName); 
            }
            catch(Exception e2)
            {
               errorMessage1 = e2.getMessage();
               throw new Exception(errorMessage1);
            }
         }
         
      }
      catch(Exception e1)
      {
 	     errorMessage1 = e1.getMessage();
 		 errorMessage2 = "Error XXXX: Occurred while creating a new Directory for the UML Sequence Diagram Files: " + dirPathName + System.lineSeparator();
 		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
 		 throw new Exception(errorMessage2);	
      }
      
      return dirPathName;	   
   }
   
   public String createDirectory(String dirPathName1, String name) throws Exception
   {
      String errorMessage1="", errorMessage2="", dirPathName2=""; 
      File theDir;

      // Create a new Directory inside the previous directory to store the UML Sequence Diagram Files
      try
      {
         // Build the Directory Path Name
	     dirPathName2 = dirPathName1 + File.separator + name + File.separator;
         theDir = new File(dirPathName2);
         
         // Create a New Directory
         if (!theDir.exists())
            theDir.mkdirs();

      }
      catch(Exception e1)
      {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while creating a new Directory: " + dirPathName2 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "For the: " + name + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);	
      }
      
      return dirPathName2;	   
   }

   public void generateSeqDiagramsFiles(int projectId, String databaseConfigFile, FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1=""; 
      int u=0, m=0, userId=0, methodId=0, userFileId=0, methodStepId=0, methodFileId=0;
      String userName="", dirPathName1="", dirPathName2="", userTextFileName="",
    		 methodFileName2="", methodTextFileName="", textFileName="", imageFileName="",
    	     userImageFileName="", methodImageFileName="";

      LoadUMLSequenceDiagramController umlSeqDiagramCon = new LoadUMLSequenceDiagramController();
	  ArrayList<UserSequenceDiagram> userList = new ArrayList<UserSequenceDiagram>();
      ArrayList<UMLSequenceDiagramFinal> umlSeqDiagramUserList, umlSeqDiagramMethodList;
      ArrayList<MethodStepSequenceDiagram> methodStepList; 
     
      UserFileSequenceDiagram userFileSeqDiagramRecord;
      MethodFileSequenceDiagram methodFileSeqDiagramRecord;
      UMLSequenceDiagramFinal umlSeqDiagramMethodRecord;
	  WorkFiles workFiles = new WorkFiles(); 
	  
      /*
	  System.out.println("STEP 1");
	  System.out.println("Inside the Generate Seq Diagrams Files Method");
	  */
	  
      // Connecting to the database
	  try 
	  {
	     umlSeqDiagramCon.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
	  //System.out.println("2");
      // Create a Directory to Store the UML Sequence Diagram Files
      try 
      {
         dirPathName1 = createUMLDirectory(frlCon);
         //System.out.println("Dir Path Name 1: " + dirPathName1);
	  } 
      catch (Exception e2) 
      {
  	     errorMessage1 = e2.getMessage();
  	     throw new Exception(errorMessage1);
	  }
      
      //System.out.println("STEP 3");
	  // Obtain all the different Users for the Project Identifier
	  try 
	  {	 	
	     userList = umlSeqDiagramCon.loadUsersSequenceDiagram(projectId);
	     /*System.out.println("Number of Elements: " + userList.size());
	     System.out.println("Elements: " + Arrays.toString(userList.toArray()));
	     */
	  }  
	  catch(Exception e3)
	  {
	     errorMessage1 = e3.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
	  //System.out.println("STEP 4");
	  // Loop to iterate in the User ArrayList
      for (u = 0; u < userList.size(); u++) 
      { 
    	 // Getting the Data from the ArrayList
    	 userId   = userList.get(u).getUserIdentifier();
         userName = userList.get(u).getUserName(); 
         userFileId = 1;
         
         /*
         System.out.println("User Id   : "+ userId);
		 System.out.println("User Name : "+ userName);
		 System.out.println("User File Id   : "+ userFileId);
		 
		  
         
         System.out.println("INSIDE THE FIRST LOOP");
         System.out.println("STEP 5");
         */
         
         // Obtain the UML Sequence Diagram ArrayList for this User
   	     try 
   	     {
   	        umlSeqDiagramUserList = umlSeqDiagramCon.loadSequenceDiagramUser(projectId, userId);
   	        
   	        /*
   	        System.out.println("UML Sequence Diagram List for User ...");
   	        System.out.println("Number of Elements: " + umlSeqDiagramUserList.size());
   	        System.out.println("Elements: " + Arrays.toString(umlSeqDiagramUserList.toArray()));
   	        */
 	     } 
   	     catch (Exception e4) 
   	     {
   		    errorMessage1 = e4.getMessage();
   			throw new Exception(errorMessage1);
   	     }
   	      
   	     //System.out.println("STEP 6");
   	     // Create a Directory to store the UML Sequence Diagrams for this User
   	     try
   	     {
   	        dirPathName2 = createDirectory(dirPathName1, userName);
   	        
   	        //System.out.println("Dir Path Name 2: "+dirPathName2);
   	        
   	  	 } 
      	 catch (Exception e5) 
      	 {
      	    errorMessage1 = e5.getMessage();
      		throw new Exception(errorMessage1);
      	 } 
   	     
   	     //System.out.println("STEP 7");
   	     // Delete all the previous text and image files for this User
	     try
	     {
   	        workFiles.deleteFilesDirectory(dirPathName2);
   	        //System.out.println("Deleting previous text and image files from the Directory: " + dirPathName2);
	     }
 	     catch (Exception e6) 
         {
            errorMessage1 = e6.getMessage();
  	        throw new Exception(errorMessage1);
         } 
   	     
	     //System.out.println("STEP 8");
	     
   	     // Generate the Text FileName
   	     textFileName = frlCon.umlSeqDiagTextFileName1.substring(0, frlCon.umlSeqDiagTextFileName1.length()-4);
   	     textFileName = textFileName + "_" + userName + frlCon.originalPrefix + frlCon.textFileExtDelimiter;
   	     
   	     // Generate the Image FileName
         imageFileName = textFileName.substring(0, textFileName.length()-4) + ".png";
         
         
         //System.out.println("STEP 9");
		 // Generate the Text File for the UML Sequence Diagram for this User 
   	     try 
   	     {
   	    	userTextFileName = createTextFile(frlCon, dirPathName2, textFileName, umlSeqDiagramUserList); 
   	        //System.out.println("User Text File Name: " + userTextFileName);
    	 } 
      	 catch (Exception e7) 
      	 {
      	    errorMessage1 = e7.getMessage();
      		throw new Exception(errorMessage1);
      	 }   
   	       
   	     /*
   	     System.out.println("STEP 10");
   	     
   	     System.out.println("User Text File Name  : " + userTextFileName);
   	     */
   	  
         // Generate the PNG File for the UML Sequence Diagram for this User 
   	     try
   	     {
   	        userImageFileName = createSeqDiagramPngFile(userTextFileName);
   	        //System.out.println("User Image File Name: " + userImageFileName);
   	     }
     	 catch (Exception e8) 
      	 {
            errorMessage1 = e8.getMessage();
      		throw new Exception(errorMessage1);
      	 } 
      	 
   	     
         
         /*
         System.out.println("User Image File Name : " + userImageFileName);
         
         
         
	     System.out.println("Dir Path Name 2 : " + dirPathName2);
   	     System.out.println("Text File Name  : " + textFileName);
   	     System.out.println("Png  File Name  : " + imageFileName);
   	     
         System.out.println("Image Directory Name: " + frlCon.imageDirectoryName);
         */
         
         /*
         System.out.println("Source File     : " + source);
         System.out.println("Destiny File    : " + dest);
         */
         
   	     //System.out.println("STEP 11");
   	  
   	     // Build a new userFilesSequenceDiagram Record
   	     userFileSeqDiagramRecord = new UserFileSequenceDiagram(projectId, userId, userFileId, textFileName, imageFileName);
   	  
   	     // Saving the name of the TextFile and the PngFile for this user into the database
   	     try 
   	     {	 	
   	    	umlSeqDiagramCon.saveUserFileSequenceDiagram(userFileSeqDiagramRecord);
   	     }  
   	     catch(Exception e9)
   	     {
   	        errorMessage1 = e9.getMessage();
   		    throw new Exception(errorMessage1);
   	     }
   	     
   	     //System.out.println("STEP 12");
   	  
	     // Getting the Method Step List for this User
		 try 
		 {	 	
		    methodStepList = umlSeqDiagramCon.loadMethodsSequenceDiagram(projectId, userId);
		    
		    
		    /*
		    System.out.println("Method List: ");
		    System.out.println("Number of Elements : " + methodStepList.size());
		    System.out.println("Elements           : " + Arrays.deepToString(methodStepList.toArray()));
		    */
		    
		 }  
		 catch(Exception e10)
		 {
		    errorMessage1 = e10.getMessage();
			throw new Exception(errorMessage1);
		 }
		  
		 // For every method Names Element Loop 	  
     	 for(m = 0; m < methodStepList.size(); m++) 
	     {
     		methodId     = methodStepList.get(m).getMethodIdentifier();
     		methodStepId = methodStepList.get(m).getStepIdentifier();
     	      
     		/*
     		System.out.println("INSIDE THE SECOND LOOP");
     		System.out.println("STEP 13");
     		*/
     		
            umlSeqDiagramMethodRecord = new UMLSequenceDiagramFinal(projectId, userId, methodId, methodStepId);
            
            //System.out.println("STEP 14");
            // Getting the UML Sequence Diagram for this User 
      	    try 
      	    {
      	       umlSeqDiagramMethodList =  umlSeqDiagramCon.loadSequenceDiagramMethod(umlSeqDiagramMethodRecord);      	       
     	    }  
        	catch (Exception e11) 
         	{
       	       errorMessage1 = e11.getMessage();
       		   throw new Exception(errorMessage1);
         	}  
         	
      	    /*
     		if(methodId == 6)	
     		{	
     		*/
      	    
      	    /*
            System.out.println("Method Id          : " + methodId);
            System.out.println("Method Step Id     : " + methodStepId);
   	        System.out.println("UML Sequence Diagram List for Method ...");
   	        System.out.println("Number of Elements : " + umlSeqDiagramMethodList.size());
      	    System.out.println("Dir Path Name 1    : " + dirPathName1);
      	    System.out.println("Dir Path Name 2    : " + dirPathName2);
      	    */

      	    //System.out.println("STEP 15");
      	  
      	    methodFileName2 = frlCon.methodName8 + Integer.toString(methodId) + frlCon.stepName2 + Integer.toString(methodStepId) + frlCon.originalPrefix;
      	    
      	    // Generate the Text FileName
      	    textFileName = frlCon.umlSeqDiagTextFileName1.substring(0, frlCon.umlSeqDiagTextFileName1.length()-4);
      	    textFileName = textFileName + "_" + userName + "_" + methodFileName2 + frlCon.textFileExtDelimiter;
      	    
      	    // Generate the Image FileName
      	    imageFileName = textFileName.substring(0, textFileName.length()-4) + ".png";
      	  
      	    
      	    /*
      	    if(textFileName.contains("Method_6"))
      	    {	
      	    */
      	    /*
      	    System.out.println("Method Step File Name : " + methodFileName2);
    	    System.out.println("Text File Name        : " + textFileName);
    	    System.out.println("Image File Name       : " + imageFileName);
    	    */
      	    //}
      	    
      	    //System.out.println("STEP 16");
   		    // Generate the Text File for the UML Sequence Diagram for Method  
   	        try 
   	        {
   	           methodTextFileName = createTextFile(frlCon, dirPathName2, textFileName, umlSeqDiagramMethodList); 	
   	           //System.out.println("Method Text File Name : " + methodTextFileName);
    	    } 
      	    catch (Exception e12) 
      	    {
      		   errorMessage1 = e12.getMessage();
      		   throw new Exception(errorMessage1);
      	    } 
   	        
   	        //System.out.println("STEP 17");
   	     
            // Generate the PNG File for the UML Sequence Diagram for this Method
   	        try
      	    {
      	       methodImageFileName = createSeqDiagramPngFile(methodTextFileName);
      	       //System.out.println("Method Image File Name : " + methodTextFileName);
      	    }
        	catch (Exception e13) 
         	{
               errorMessage1 = e13.getMessage();
         	   throw new Exception(errorMessage1);
         	}
      	    
            /*
            if(textFileName.contains("Method_6"))
      	    {	
      	    */
            	
            /*
    	    System.out.println("Method Text File Name   : " + methodTextFileName);
            System.out.println("Method Image File Name  : " + methodImageFileName);
            */

            /*
            System.out.println("Image Directory Name  : " + frlCon.imageDirectoryName);
            
            System.out.println("Source File           : " + source);
            System.out.println("Destiny File          : " + dest);
            */
      	    //}
      	    
   	        //System.out.println("STEP 18");
   	     
      	    methodFileId = 1;
      	    
      	    // Build a new method Record
      	    methodFileSeqDiagramRecord = new MethodFileSequenceDiagram(projectId, userId, methodId, 
      	    		                                                   methodStepId, methodFileId, 
      	    		                                                   textFileName, imageFileName);
      	    
      	    //System.out.println("STEP 19");
      	  
      	    // Saving the name of the TextFile and the PngFile for this user into the database
      	    try 
      	    {	 	
      	       umlSeqDiagramCon.saveMethodFileSequenceDiagram(methodFileSeqDiagramRecord); 	
      	    }  
      	    catch(Exception e14)
      	    {
      	       errorMessage1 = e14.getMessage();
      		   throw new Exception(errorMessage1);
      	    }

	      }
	      
      }
      
      //System.out.println("STEP 20");
	  // Disconnect from the database
	  try 
	  {
	     umlSeqDiagramCon.disconnect();
	  } 
	  catch (Exception e15) 
	  {
	     errorMessage1 = e15.getMessage();
		 throw new Exception(errorMessage1);
	  }
	  
   }
   
   public void updateMethodsIds(FRLConfiguration frlCon, String databaseConfigFile, int projectId) throws Exception
   {
      String errorMessage="", packageName="", className="", methodName2="";
	  int m, methodId;
	  LoadUMLSequenceDiagramController umlSeqDiagramCon = new LoadUMLSequenceDiagramController();
	  ArrayList<UMLSequenceDiagramPlain> umlSeqDiagramPlainList;
	  UMLSequenceDiagramPlain umlSeqDiagramPlainRecord;
	   
      // Connecting to the database
	  try 
	  {
	     umlSeqDiagramCon.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  // Get all the distinct methods from the UML_Sequence_Diagram_Plain Table
	  try 
	  {
	     umlSeqDiagramPlainList = umlSeqDiagramCon.loadUniqueMethodsPlain(projectId);
	  }
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
	  // Loop of all the elements in the umlSeqDiagramPlain ArrayList
	  for(m = 0; m < umlSeqDiagramPlainList.size(); m++) 
	  {
  	     methodId    = umlSeqDiagramPlainList.get(m).getMethodIdentifier();
  	     packageName = umlSeqDiagramPlainList.get(m).getMethodPackageName();
  	     className   = umlSeqDiagramPlainList.get(m).getMethodClassName();
  	     methodName2 = umlSeqDiagramPlainList.get(m).getMethodName2();
  	     
  	     // Build the record
  	     umlSeqDiagramPlainRecord = new UMLSequenceDiagramPlain(projectId, methodId, packageName,
  	    		                                                className, methodName2);
  	     
  	     // Update the Method Id in the UMLSequenceDiagramPlain table
  	     umlSeqDiagramCon.updateIdentifierPlain(umlSeqDiagramPlainRecord);
	  }
	  
	  // Disconnect from the database
	  try 
	  {
	     umlSeqDiagramCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage = e3.getMessage();
		 throw new Exception(errorMessage);
	  }	   
   }
   
   public void printMessagesFile(String message) throws Exception
   {
      String outputFileName="", errorMessage1="";
	  PrintStream ps;
      File file;
      FileOutputStream fos;

      //ps_console = System.out;
      
      // Validate if the file exists
      outputFileName="/Users/fannyriveraortiz/eclipse-workspace/OutputFile.txt";
      file = new File(outputFileName);

	  if (!file.exists())
	  {
		 errorMessage1 = "Error 9999: The Output File Name: " + outputFileName + System.lineSeparator();
		 errorMessage1 = errorMessage1 + "does not exists.";
		 throw new Exception(errorMessage1);
	  }

      fos = new FileOutputStream(file);

      // Create new print stream for file.
      ps = new PrintStream(fos);

      // Set file print stream.
      System.setOut(ps);
      System.out.println(message);
   }
      
   public String loadUMLSeqDiagramElements(String databaseConfigFile1, String fileName1, FRLConfiguration frlCon, int projectId)
   {
      String errorMessage="", databaseConfigFile2="";
      Path fileName2;

      // Get the real file paths for the following files
      fileName2 = Paths.get(fileName1);    
      databaseConfigFile2 = databaseConfigFile1;
      
      if(frlCon.operatingSystem.contains("Windows"))
    	  databaseConfigFile2 = databaseConfigFile1.replace("\\", "\\\\");
      
      System.out.println("Saving the UML Sequence Diagram Text File into the Database ...");

      // Save every line of the UML Sequence Diagram Text File into the Database
      try 
      {
	     saveSeqTextFileDb(databaseConfigFile2, fileName2, frlCon, projectId);
	  } 
      catch (Exception e1) 
      {
  	     errorMessage = e1.getMessage();
  	     return errorMessage;
	  }
      
      System.out.println("Updating the User Names into the Database ...");
      // Update the additional details related to the Methods
      try 
      {
         updateUserNames(projectId, databaseConfigFile2);
	  } 
      catch (Exception e2) 
      {
  	     errorMessage = e2.getMessage();
  	     return errorMessage;
	  }
      
      System.out.println("Updating the Method Details into the Database ...");
      // Update the additional details related to the Methods
      try 
      {
         updateMethodsDetails(frlCon, databaseConfigFile1, projectId);
	  } 
      catch (Exception e3) 
      {
  	     errorMessage = e3.getMessage();
  	     return errorMessage;
	  }
      
      System.out.println("Updating the Methods Identifiers into the Database ...");
      // Update the additional details related to the Methods
      try 
      {
         updateMethodsIds(frlCon, databaseConfigFile1, projectId);
	  } 
      catch (Exception e4) 
      {
  	     errorMessage = e4.getMessage();
  	     return errorMessage;
	  }
      
      System.out.println("Delete the Previous Information about the UML Sequence Diagram from the Database ...");
	  // Delete all the previous data in the Sequence Diagram Tables
	  try 
	  {
	     deletePreviousSeqDiagramsData(projectId, databaseConfigFile1);
	  } 
	  catch (Exception e5) 
	  {
	     errorMessage = e5.getMessage();
	     return errorMessage;
	  }	 
      
	  
      System.out.println("Save the Final Information about the UML Sequence Diagram into the Database ...");
      // Store the data into the final sequence diagram tables
      try 
      {
         saveFinalSequenceDiagramData(frlCon, databaseConfigFile1, projectId);
	  } 
      catch (Exception e6) 
      {
  	     errorMessage = e6.getMessage();
  	     return errorMessage;
	  }
        
      //System.out.println("BEFORE Generate Seq Diagrams Files METHOD");
      
      System.out.println("Generating the UML Sequence Diagram Files ...");
      // Generate the New Text and Images Files corresponding to the Diagrams for each User and Method
      try
      {
         generateSeqDiagramsFiles(projectId, databaseConfigFile2, frlCon);
	  } 
      catch (Exception e7) 
      {
	     errorMessage = e7.getMessage();
	     return errorMessage;
	  }
	  
	  
      //System.out.println("AFTER Generate Seq Diagrams Files METHOD");
      
      return errorMessage;
	   
   }
}