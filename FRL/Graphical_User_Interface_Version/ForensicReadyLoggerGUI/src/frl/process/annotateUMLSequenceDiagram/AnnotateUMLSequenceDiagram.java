package frl.process.annotateUMLSequenceDiagram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import frl.controller.annotateUMLSequenceDiagram.AnnotateUMLSequenceDiagramController;
import frl.gui.annotateUMLSequenceDiagram.AnnotateUMLSequenceDiagramFormEvent;
import frl.model.annotateUMLSequenceDiagram.FileData;
import frl.model.annotateUMLSequenceDiagram.UserData;
import frl.model.configuration.FRLConfiguration;
import frl.model.loadUMLSequenceDiagram.AnnotationType;
import frl.model.loadUMLSequenceDiagram.DatumCategory1;
import frl.model.loadUMLSequenceDiagram.LineType1;
import frl.model.loadUMLSequenceDiagram.MethodSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramFinal;
import frl.process.loadUMLSequenceDiagram.WorkFiles;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;

public class AnnotateUMLSequenceDiagram 
{
	   
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

	   public String createSeqDiagramImageFile(String textFileName1, String textPathFileName, FRLConfiguration frlCon) throws Exception
	   {
	      File file1 = new File(textPathFileName); 
		  SourceFileReader reader = null;
		  List<GeneratedImage> list = null;
		  String fileName="", textFileName2="", imagePathFileName="", errorMessage1="", errorMessage2="";
		  
		  // Create the Reader of the UML Sequence Diagram Text File
		  try 
		  {
		     reader = new SourceFileReader(file1);
		  } 
		  catch (IOException e1) 
		  {
		     errorMessage1 = e1.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while reading the UML Sequence Diagram Text File: " + textPathFileName + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			 throw new Exception(errorMessage2);	     
		  }
		   
		  // Generate the list of images using the Sequence Diagram Text File
		  try 
		  {
		     list = reader.getGeneratedImages();
		  } 
		  catch (IOException e2) 
		  {
		     errorMessage1 = e2.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while generating the UML Sequence Diagram Png File from the text File: " + textPathFileName + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			 throw new Exception(errorMessage2);	
		  }
		   
		  // Generated the UML Sequence Diagram Image File
		  file1 = list.get(0).getPngFile();
		  
		  // Convert from file to string type
		  fileName = file1.toString();
		  
          // Remove the .txt extension from the textFileName1
		  textFileName2 = textFileName1.substring(0, textFileName1.lastIndexOf("."));
		  
		  /*
		  // Build the Image FileName to copy into the Image Directory
		  imagePathFileName = frlCon.imageDirectoryName + File.separator + textFileName2 + ".png";
		  
	      // Copying the file into the Image Directory
	      try
	      {
	         copyFilesDirectory(fileName, imagePathFileName);
	      }
	      catch (Exception e3) 
	      {
	         errorMessage1 = e3.getMessage();
	       	 throw new Exception(errorMessage1);
	      }
	      */
	      
		  return fileName;
	 	   
	   } 
	   
	   public FileData loadUpdatedSeqDiagrams(AnnotateUMLSequenceDiagramController annotateUMLSequenceDiagramController, 
	                                          FRLConfiguration frlCon,
	                                          String databaseConfigFile,
	                                          int projectId, 
	                                          int userId, String userName,
	                                          int methodId, int methodStepId) throws Exception
	   {
	      String errorMessage1="", errorMessage2="", dirPathName="", textFileName="", imageFileName="", 
	    		 prefixFile="_Modified", extFile=".png", 
	    		 userPathTextFileName="", userPathImageFileName="", userTextFileName="", userImageFileName="",
	    		 methodFileName="", methodPathTextFileName="", methodPathImageFileName="", 
	    		 methodTextFileName="", methodImageFileName="";
	      //imageDirectory="/Users/fannyriveraortiz/eclipse-workspace/ForensicReadyLoggerGUI/src/images/";
	      ArrayList<UMLSequenceDiagramFinal> umlSeqDiagramUserList, umlSeqDiagramMethodList;
		  UMLSequenceDiagramFinal umlSeqDiagramMethodRecord;
		  FileData fileDataRecord;
		  WorkFiles workFiles = new WorkFiles();
	      
		  /*
	      System.out.println("*** Loading the Updated UML Sequence Diagrams Method ***");
	      
	      System.out.println("Loading the Updated UML Sequence Diagram at the User Level");
	      System.out.println("User Id   : " + userId);
	      System.out.println("User Name :"  + userName);
	      */
	      
		  // Connect to the Database
		  try 
		  {
		     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
		  } 
		  catch (Exception e1) 
		  {
		     errorMessage1 = e1.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while Connecting to the Database";
			 errorMessage2 = errorMessage2 + errorMessage1;
			 throw new Exception(errorMessage2);
	      }
		  
	      // Loading the updated UML Sequence Diagram at the User Level
		  try 
		  {
		     umlSeqDiagramUserList = annotateUMLSequenceDiagramController.loadSequenceDiagramUser(projectId, userId);
		       
		     //System.out.println("UML Sequence Diagram List for User ...");
		     //System.out.println("Number of Elements: " + umlSeqDiagramUserList.size());
		     //System.out.println("Elements: " + Arrays.toString(umlSeqDiagramUserList.toArray()));
		   } 
		   catch (Exception e2) 
		   {
		      errorMessage1 = e2.getMessage();
			  throw new Exception(errorMessage1);
		   }

		   // Build the directory name for this User
		   dirPathName = frlCon.projectOutputDir + File.separator + 
				         frlCon.umlDirectoryName + File.separator + 
				         frlCon.projectName      + File.separator + 
				         userName                + File.separator;
		   		   
		   // Generate the Text FileName
	 	   textFileName = frlCon.umlSeqDiagTextFileName1.substring(0, frlCon.umlSeqDiagTextFileName1.length()-4);
	 	   textFileName = textFileName + frlCon.validSpecialCharacter + userName + prefixFile + frlCon.textFileExtDelimiter;
	 	   	 	     
	 	   // Generate the Image FileName
	       imageFileName = textFileName.substring(0, textFileName.length()-4) + extFile;
	       
	  	   // Store the FileNames of the Text and Image User Files without any path included
	  	   userTextFileName  = textFileName;
	  	   userImageFileName = imageFileName;
	  	   
	  	   /*
	 	   System.out.println("Directories Information: ");
		   System.out.println("User Directory            : " + dirPathName);
		   System.out.println("Image Directory           : " + imageDirectory);
		   
	 	   System.out.println("Creating the UML Sequence Diagram Files at the Method Level");
	  	   System.out.println("Text File Name            : " + textFileName);
	  	   System.out.println("Image File Name           : " + imageFileName);
	 	   System.out.println("User Path Text File Name  : " + userPathTextFileName);
	       System.out.println("User Path Image File Name : " + userPathImageFileName);
	       */
	       
	       // Delete Previous Text File in the User Directory
	       try 
	       {
		      workFiles.deletePreviousFileDirectory(dirPathName, userTextFileName);
 	       }
   	       catch (Exception e3) 
           {
              errorMessage1 = e3.getMessage();
    	      throw new Exception(errorMessage1);
           } 
	       
	       // Delete Previous Image File in the User Directory
	       try 
	       {
		      workFiles.deletePreviousFileDirectory(dirPathName, userImageFileName);
 	       }
   	       catch (Exception e4) 
           {
              errorMessage1 = e4.getMessage();
    	      throw new Exception(errorMessage1);
           } 

	       // Delete Previous Image file in the User Directory
	       /*try 
	       {
	          workFiles.deletePreviousFileDirectory(imageDirectory, userImageFileName);
 	       }
   	       catch (Exception e5) 
           {
              errorMessage1 = e5.getMessage();
    	      throw new Exception(errorMessage1);
           } 
           */
	       
		   // Generate the Path Text File for the UML Sequence Diagram for this User 
	 	   try 
	 	   {
	 	      userPathTextFileName = createTextFile(frlCon, dirPathName, textFileName, umlSeqDiagramUserList); 
	 	      //System.out.println("User Text File Name CREATED: " + userTextFileName);
	  	   } 
	       catch (Exception e5) 
	       {
	          errorMessage1 = e5.getMessage();
	    	  throw new Exception(errorMessage1);
	       }   
	 	     
	       // Generate the Image File for the UML Sequence Diagram for this User 
	 	   try
	 	   {
	 	      userPathImageFileName = createSeqDiagramImageFile(textFileName, userPathTextFileName, frlCon);
	 	      //System.out.println("User Image File Name CREATED: " + userImageFileName);
	 	   }
	   	   catch (Exception e6) 
	       {
	          errorMessage1 = e6.getMessage();
	    	  throw new Exception(errorMessage1);
	       } 
	 	   
	 	   // Copy the User Image File into the ImageDirectory
	 	   /*try
	 	   {
	 	      workFiles.copyFileDirectory(userPathImageFileName, imageDirectory+userImageFileName); 
	 	   }
	   	   catch (Exception e8) 
	       {
	          errorMessage1 = e8.getMessage();
	    	  throw new Exception(errorMessage1);
	       } 
	       */
	         
	       // Build the UML Sequence Diagram Method Record
	       umlSeqDiagramMethodRecord = new UMLSequenceDiagramFinal(projectId, userId, methodId, methodStepId);
	          
	       // Load the UML Sequence Diagram for this Method Id and Method Step Id
	       try 
	       {
	          umlSeqDiagramMethodList =  annotateUMLSequenceDiagramController.loadSequenceDiagramMethod(umlSeqDiagramMethodRecord);
	    	        
	    	  //System.out.println("UML Sequence Diagram List for Method ...");
	    	  //System.out.println("Number of Elements: " + umlSeqDiagramMethodList.size());    
	   	   }  
	       catch (Exception e7) 
	       {
	          errorMessage1 = e7.getMessage();
	     	  throw new Exception(errorMessage1);
	       }  
	       
	       // Build the filenames for the Method Level
	       methodFileName = frlCon.methodName8 + Integer.toString(methodId) + frlCon.stepName2 + Integer.toString(methodStepId) + prefixFile;
	    	    
	       // Generate the Text FileName
	       textFileName = frlCon.umlSeqDiagTextFileName1.substring(0, frlCon.umlSeqDiagTextFileName1.length()-4);
	       textFileName = textFileName + "_" + userName + "_" + methodFileName + frlCon.textFileExtDelimiter;
	    	    
	       // Generate the Image FileName
	       imageFileName = textFileName.substring(0, textFileName.length()-4) + ".png";
	    	  
	  	   // Store the FileNames of the Text and Image Method Files without any path included
	  	   methodTextFileName  = textFileName;
	  	   methodImageFileName = imageFileName;
	  	   
	  	   /*
	       System.out.println("Creating the UML Sequence Diagram Files at the Method Level");
	       System.out.println("Method Id                   : " + methodId);
	       System.out.println("Method Step Id              : " + methodStepId);
	       System.out.println("Method Step File Name       : " + methodFileName);
	  	   System.out.println("Text File Name              : " + textFileName);
	  	   System.out.println("Image File Name             : " + imageFileName);
	 	   System.out.println("Method Path Text File Name  : " + methodPathTextFileName);
	       System.out.println("Method Path Image File Name : " + methodPathImageFileName);
	       */
	  	   
	       // Delete Previous Text File in the User Directory
	       try 
	       {
		      workFiles.deletePreviousFileDirectory(dirPathName, methodTextFileName);
 	       }
   	       catch (Exception e8) 
           {
              errorMessage1 = e8.getMessage();
    	      throw new Exception(errorMessage1);
           } 
	       
	       // Delete Previous Image File in the User Directory
	       try 
	       {
		      workFiles.deletePreviousFileDirectory(dirPathName, methodImageFileName);
 	       }
   	       catch (Exception e9) 
           {
              errorMessage1 = e9.getMessage();
    	      throw new Exception(errorMessage1);
           } 

	       // Delete Previous Image file in the User Directory
	       /*try 
	       {
	          workFiles.deletePreviousFileDirectory(imageDirectory, methodImageFileName);
 	       }
   	       catch (Exception e12) 
           {
              errorMessage1 = e12.getMessage();
    	      throw new Exception(errorMessage1);
           } 
           */
	    	      
	 	   // Generate the Path Text File for the UML Sequence Diagram for Method  
	 	   try 
	 	   {
	 	      methodPathTextFileName = createTextFile(frlCon, dirPathName, textFileName, umlSeqDiagramMethodList); 	
	  	   } 
	       catch (Exception e10) 
	       {
	          errorMessage1 = e10.getMessage();
	    	  throw new Exception(errorMessage1);
	       }
	 	   
	       // Generate the Image File for the UML Sequence Diagram for this Method
	       try
	       {
	          methodPathImageFileName = createSeqDiagramImageFile(textFileName, methodPathTextFileName, frlCon);
	       }
	       catch (Exception e11) 
	       {
	          errorMessage1 = e11.getMessage();
	       	  throw new Exception(errorMessage1);
	       }
	       
	 	   // Copy the User Image File into the ImageDirectory
	 	   /*try
	 	   {
	 	      workFiles.copyFileDirectory(methodPathImageFileName, imageDirectory+methodImageFileName); 
	 	   }
	   	   catch (Exception e15) 
	       {
	          errorMessage1 = e15.getMessage();
	    	  throw new Exception(errorMessage1);
	       } 
	       */
	       
	       fileDataRecord = new FileData(projectId, userId, userName, 
			                             userTextFileName, userImageFileName, methodId, 
			                             methodStepId, methodTextFileName, methodImageFileName);
	       
		   // Disconnect from the Database 
		   try 
		   {
		      annotateUMLSequenceDiagramController.disconnect();
		   } 
		   catch (Exception e12) 
		   {
		      errorMessage1 = e12.getMessage();
			  errorMessage2 = "Error XXXX: Occurred while disconnecting from the Database";
			  errorMessage2 = errorMessage2 + errorMessage1;
			  throw new Exception(errorMessage2);
		   }    
	       
	       return fileDataRecord;
		   
	   }
       
	   public void createAnnotation(FRLConfiguration frlCon,
			                        AnnotateUMLSequenceDiagramController annotateUMLSequenceDiagramController,
			                        int projectIdGUI, int userIdGUI, String userNameGUI,
			                        int methodIdGUI, int methodStepIdGUI, String methodNameGUI,
			                        int diagramLineNumberSD2, int diagramLineNumberSD1,
			                        int methodLineNumberSD2, int cmIdMD1,
			                 		int diagramLineNumberMD1, int methodLineNumberMD1, String methodLineTextMD1,  
			                 		int diagramLineNumberME1, int methodLineNumberME1,
			       		 		    int diagramLineNumberNS1, int methodLineNumberNS1, int cmIdNS1,
		        		 		    int diagramLineNumberNE1, int methodLineNumberNE1, int cmIdNE1,
			                        String annotationTypeGUI, String parameterNameGUI,
			    		            String attributeNameGUI, String attributeDataTypeNameGUI, 
			    		            String valueNameGUI,String valueText1GUI, 
			    		            String comparisonName1GUI, 
			    		            String returnTypeNameGUI, String comparisonName2GUI, String returnTypeValueNameGUI
			    		            ) throws Exception
	   {
	      String lineTextNewAnnotation="", errorMessage1="", errorMessage2="", methodPackageName="", methodClassName="", 
	    		 methodShortName="", methodFullName="", methodAnnotation="", annotationDetails="N",
	             annotationElement1="", annotationElement2="", annotationOperator1="",
	             parameterDataType="", parameterName="", attributeDataType="", attributeName="";
	      String[] parts1, parts2;
	      UMLSequenceDiagramFinal umlSeqDiagramRecord;
		  ArrayList<UMLSequenceDiagramFinal> umlSeqDiagram2List = new ArrayList<UMLSequenceDiagramFinal>();
		  AnnotationType annotationType = null;
	      MethodSequenceDiagram methodSeqDiagramRecord = null;
	      
		  /***********************************************************************************************/
		  /* Collecting all the information about the new annotation to insert it in the Database */
		  /***********************************************************************************************/
		  
	      // Initialize the variables	      
		  diagramLineNumberSD2 = diagramLineNumberSD1; 
		    
		  //System.out.println("Annotation Type GUI: " + annotationTypeGUI);
		  
		  if(annotationTypeGUI.equals("Method") || annotationTypeGUI.equals("Parameter") || annotationTypeGUI.equals("Return Type"))
		  {	  
			  
		     // Assign the correspondent Annotation Enum Type 
			 if(annotationTypeGUI.equals("Parameter"))
			    annotationType = AnnotationType.Parameter;
			 else 
			    if(annotationTypeGUI.equals("Return Type"))
				   annotationType = AnnotationType.ReturnType;
				else 
				   if(annotationTypeGUI.equals("Method"))
				      annotationType = AnnotationType.Method;		  
	          
			 
			 //System.out.println("Annotation Type: " + annotationType);
			 
		     // Build each of the 3 records that contains each of the lines of the new annotation
			    
			 // Record #1  
		     lineTextNewAnnotation = "note left of " + userNameGUI + " #lightpink";
		     
		     if(diagramLineNumberNS1 == 0)
		     {
		        diagramLineNumberSD2++;
		        methodLineNumberSD2++;
		     }
		     else
		     {
		        diagramLineNumberSD2 = diagramLineNumberNS1;
		        methodLineNumberSD2  = methodLineNumberNS1;
		     }
		  
		     /*
		     System.out.println("*** Record #1 ***");
		     System.out.println("Line Text New Annotation : " + lineTextNewAnnotation);
		     System.out.println("Diagram Line Number      : " + diagramLineNumberSD2);
		     System.out.println("Method Line Number       : " + methodLineNumberSD2);
		     System.out.println("User Name                : " + userNameGUI);
		     */
		     
		     umlSeqDiagramRecord = new UMLSequenceDiagramFinal(projectIdGUI, diagramLineNumberSD2, userIdGUI, 
		    		                                           methodIdGUI, methodStepIdGUI, methodLineNumberSD2, 
		    		                                           lineTextNewAnnotation, LineType1.MethodBody, "Note Starts", 
		    		                                           DatumCategory1.NoteMember, "Owner", userNameGUI, cmIdMD1);
		    
		     umlSeqDiagram2List.add(umlSeqDiagramRecord);
		     
			 // Record #2		     
		     if(annotationTypeGUI.equals("Method"))
		     {	 
			    try 
				{	  
			       methodSeqDiagramRecord = annotateUMLSequenceDiagramController.getMethodDetails2(projectIdGUI, methodIdGUI);
			       methodPackageName = methodSeqDiagramRecord.getPackageName();
			       methodClassName = methodSeqDiagramRecord.getClassName(); 
			       methodShortName = methodSeqDiagramRecord.getShortName();
			       
			       methodFullName = methodPackageName + "." + methodClassName + "." + methodShortName;
			       
				}   
				catch(Exception e1)
				{
				   errorMessage1 = e1.getMessage();
				   errorMessage2 = "Error XXXX: Occurred while loading the Method Details from the Database" + System.lineSeparator();
				   errorMessage2 = errorMessage2 + errorMessage1;
				   throw new Exception(errorMessage2);
			    }
			    
			    annotationDetails    = "Y";
		        annotationElement1   = "Method";
		        annotationOperator1  = "=";
			    annotationElement2   = methodFullName;
			    
		        lineTextNewAnnotation = annotationElement1 + " " + annotationOperator1 + " " + annotationElement2; 
		        
		        /*
		        System.out.println("Annotation Details     : " + annotationDetails);
		        System.out.println("Annotation Element 1   : " + annotationElement1);
		        System.out.println("Annotation Operation 1 : " + annotationOperator1);
		        System.out.println("Annotation Element 2   : " + annotationElement2);

		        
		        System.out.println("Method Package Name : " + methodPackageName);
		        System.out.println("Method Class Name   : " + methodClassName);
		        System.out.println("Method Short Name   : " + methodShortName);
		        System.out.println("Method Full Name    : " + methodFullName);
		        
		        System.out.println("Line Text New Annotation : " + lineTextNewAnnotation);
		        */

		     }   
		     else 
		        if(annotationTypeGUI.equals("Return Type"))
		        {	
				   annotationDetails     = "Y";		        	
		           annotationElement1    = returnTypeNameGUI;
		           annotationOperator1   = comparisonName1GUI;
		           
		           if(returnTypeValueNameGUI != null && returnTypeValueNameGUI.trim().isEmpty() == false)
		              annotationElement2 = returnTypeValueNameGUI;
		           else
		              annotationElement2  = valueText1GUI;
		           
		           lineTextNewAnnotation = annotationElement1 + " " + annotationOperator1 + " " + annotationElement2;
		        }   
		     else
			    if(annotationTypeGUI.equals("Parameter"))
			    {	
				   annotationDetails = "Y";
				   
				   
				   /*
				   System.out.println("*** ADDING A PARAMETER ANNOTATION ***");
				   
				   System.out.println("Annotation Details           : " + annotationDetails);
				   System.out.println("Parameter Name GUI           : " + parameterNameGUI);
				   
				   System.out.println("Attribute Name GUI           : " + attributeNameGUI);
				   System.out.println("Attribute Data Type Name GUI : " + attributeDataTypeNameGUI);
				   
				   System.out.println("Value Name GUI               : " + valueNameGUI);
				   System.out.println("Value Text 1 GUI             : " + valueText1GUI);
				   
				   System.out.println("Comparison Name 1 GUI        : " + comparisonName1GUI);
				   System.out.println("Comparison Name 2 GUI        : " + comparisonName2GUI);
				   */
 
				   
				   if(parameterNameGUI.contains(" "))
				   {	   
				      parts1 = parameterNameGUI.split(frlCon.findWhiteSpaces);
				      
				      parameterDataType = parts1[0];
				      parameterName     = parts1[1];
				      

				   }
				   
				   /* 
				   System.out.println("Parameter Data Type : " + parameterDataType);
				   System.out.println("Parameter Name      : " + parameterName);
				     */
				   
				   // Build the Annotation Element 1
				   if(attributeNameGUI != null && attributeNameGUI.trim().isEmpty() == false)	
				   {	   
					  /* 
				      if(attributeDataTypeNameGUI.equals(frlCon.classDataType))	 
				      {	  
	                     parts2 =  attributeNameGUI.split(frlCon.findDot);
					     attributeDataType = parts2[0];
					     attributeName     = parts2[1];
					      
					     
					     System.out.println("Attribute Data Type : " + attributeDataType);
						 System.out.println("Attribute Name      : " + attributeName);
						 
						  
				         annotationElement1 = parameterName + frlCon.objectOrientedDelimiter1 + attributeName;
				      }   
				      else
				      */	  
				      annotationElement1 = parameterName + frlCon.objectOrientedDelimiter1 + attributeNameGUI;
				   }   
				   else
				      annotationElement1 = parameterName;
				   
				   /*
				   if(valueNameGUI != null && valueNameGUI.trim().isEmpty() == false)
				   {	   
				    	   
			          annotationElement1 = annotationElement1 + frlCon.objectOrientedDelimiter1 + valueNameGUI;
			          
			          annotationElement2 = valueText1GUI;
			          annotationOperator1 = comparisonName1GUI;
				   }
				   else
				   {	   
					   annotationElement2 = valueText1GUI;
					   annotationOperator1 = comparisonName1GUI;
				   }	
				   */   
				   
				   if(valueNameGUI != null && valueNameGUI.trim().isEmpty() == false)
				   {
				      //System.out.println("CASE VALUE IS NOT EMPTY");
					   
				      if(attributeDataTypeNameGUI.equals(frlCon.classDataType))
				      {	  
				         annotationElement2  = valueNameGUI; 
				         annotationOperator1 = comparisonName2GUI;
				         
				         //System.out.println("CASE ATTRIBUTE IS AN ENUM");
				      }  
				      else
				      {
				         annotationElement1  = annotationElement1 + frlCon.objectOrientedDelimiter1 + valueNameGUI;
				          
				         annotationElement2  = valueText1GUI;
				         annotationOperator1 = comparisonName1GUI;
				         
				         //System.out.println("CASE ATTRIBUTE IS NOT AN ENUM");
				      }
				    	  
				   }
				   else
				   {	   
				      annotationElement2 = valueText1GUI;
					  annotationOperator1 = comparisonName2GUI;
					  
					  //System.out.println("CASE VALUE IS EMPTY");
				   }

				   // Build the Line for the Parameter Data Type Annotation
			       lineTextNewAnnotation = annotationElement1 + " " + annotationOperator1 + " " + annotationElement2;
			       
			       /*
				   System.out.println("Annotation Element 1     : " + annotationElement1);
				   System.out.println("Annotation Element 2     : " + annotationElement2);
				   System.out.println("Annotation Operator 1    : " + annotationOperator1);
				   
				   System.out.println("Line Text New Annotation : " + lineTextNewAnnotation);
				   */
				   

			    }   
		     
		     if(diagramLineNumberNS1 == 0)
		     {
		        diagramLineNumberSD2++;
		        methodLineNumberSD2++;
		     }
		     else
		     {
		        diagramLineNumberSD2 = diagramLineNumberNS1 + 1;
		        methodLineNumberSD2  = methodLineNumberNS1 + 1;
		     }
			  
		     /*
			  System.out.println("*** Record #2 ***");
			  System.out.println("Annotation Type          : " + annotationTypeGUI);
			  System.out.println("Line Text New Annotation : " + lineTextNewAnnotation);
			  System.out.println("Diagram Line Number      : " + diagramLineNumberSD2);
			  System.out.println("Method Line Number       : " + methodLineNumberSD2);
			  System.out.println("User Name                : " + userNameGUI);
			   */
		     
			 // Record # 2    
			 umlSeqDiagramRecord = new UMLSequenceDiagramFinal(projectIdGUI, diagramLineNumberSD2, userIdGUI, 
			    		                                       methodIdGUI, methodStepIdGUI, methodLineNumberSD2, 
			    		                                       lineTextNewAnnotation, LineType1.MethodBody, "Note Body", 
			    		                                       DatumCategory1.NoteMember, "Member", lineTextNewAnnotation, 
			    		                                       cmIdMD1, annotationType, annotationDetails, 
			    		    			                       annotationElement1, annotationOperator1, annotationElement2);
			    
			 umlSeqDiagram2List.add(umlSeqDiagramRecord);
			    
			 // Record #3
			 lineTextNewAnnotation = "end note";
			 
		     if(diagramLineNumberNS1 == 0)
		     {
		        diagramLineNumberSD2++;
		        methodLineNumberSD2++;
		     }
		     else
		     {
		        diagramLineNumberSD2 = diagramLineNumberNE1;
		        methodLineNumberSD2  = methodLineNumberNE1;
		     }
			    
		     /*
			  System.out.println("*** Record #3 ***");
			  System.out.println("Line Text New Annotation : " + lineTextNewAnnotation);
			  System.out.println("Diagram Line Number      : " + diagramLineNumberSD2);
			  System.out.println("Method Line Number       : " + methodLineNumberSD2);
			  System.out.println("User Name                : " + userNameGUI);
			   */
		     
		     umlSeqDiagramRecord = new UMLSequenceDiagramFinal(projectIdGUI, diagramLineNumberSD2, userIdGUI, 
			    		                                       methodIdGUI, methodStepIdGUI, methodLineNumberSD2, 
			    		                                       lineTextNewAnnotation, LineType1.MethodBody, "Note Ends", 
			    		                                       DatumCategory1.NoteMember, "", "", cmIdMD1);
			    
			 umlSeqDiagram2List.add(umlSeqDiagramRecord);
			        
			 // Save the annotation into the Database 
		     if(diagramLineNumberNS1 == 0)
		     {
			    try 
			    {	  
			       annotateUMLSequenceDiagramController.saveAnnotationUMLSequenceDiagram(umlSeqDiagram2List);
			    }   
			    catch(Exception e2)
			    {
			       errorMessage1 = e2.getMessage();
				   errorMessage2 = "Error XXXX: Occurred while saving a new annotation into the Database" + System.lineSeparator();
				   errorMessage2 = errorMessage2 + errorMessage1;
				   throw new Exception(errorMessage2);
		       }
		     }
		     else
		     {
			    try 
			    {	  
			       annotateUMLSequenceDiagramController.updateAnnotationUMLSequenceDiagram(umlSeqDiagram2List);
			    }   
			    catch(Exception e3)
			    {
			       errorMessage1 = e3.getMessage();
				   errorMessage2 = "Error XXXX: Occurred while updating an existing annotation into the Database"+ System.lineSeparator();
				   errorMessage2 = errorMessage2 + errorMessage1;
				   throw new Exception(errorMessage2);
		       }
		     }
		     
			 /*******************************************/
			 /* Get the missing information 
			  * Line Position where is located the 
			     Method Definition and also where the Method Ends */
			 /******************************************/

			 //System.out.println("*** 4.- Information about the Method Definition and Method Ending ***");
			    
			 // Record #4
	 
			 diagramLineNumberSD2  = diagramLineNumberMD1;
			 methodLineNumberSD2   = methodLineNumberMD1;    
			 lineTextNewAnnotation = "Method Definition";
			 methodAnnotation      = methodLineTextMD1.replaceAll("->", "-[#purple]>");	   
			    
			 /*
			 System.out.println("*** Record #4 ***");
			 System.out.println("Line Text New Annotation : " + lineTextNewAnnotation);
			 System.out.println("Diagram Line Number      : " + diagramLineNumberSD2);
			 System.out.println("Method Line Number       : " + methodLineNumberSD2);
			 System.out.println("User Name                : " + userNameGUI);
             */
			 
			 umlSeqDiagramRecord = new UMLSequenceDiagramFinal(projectIdGUI, diagramLineNumberSD2, userIdGUI, 
			    		                                       methodIdGUI, methodStepIdGUI, methodLineNumberSD2, 
			    		                                       methodAnnotation, LineType1.MethodBody, 
			    		                                       lineTextNewAnnotation, DatumCategory1.MethodName, 
			    		                                       "", methodAnnotation, cmIdMD1);
			    
			 umlSeqDiagram2List.add(umlSeqDiagramRecord);
			    
			 // Record #5
			 diagramLineNumberSD2  = diagramLineNumberME1;
			 methodLineNumberSD2   = methodLineNumberME1;
			 lineTextNewAnnotation = "None";
			   
			 /*
			  System.out.println("*** Record #5 ***");
			  System.out.println("Line Text New Annotation : " + lineTextNewAnnotation);
			  System.out.println("Diagram Line Number      : " + diagramLineNumberSD2);
			  System.out.println("Method Line Number       : " + methodLineNumberSD2);
			  System.out.println("User Name                : " + userNameGUI);
			 */ 
			 
			 umlSeqDiagramRecord = new UMLSequenceDiagramFinal(projectIdGUI, diagramLineNumberSD2, userIdGUI, 
			    		                                       methodIdGUI, methodStepIdGUI, methodLineNumberSD2, 
			    		                                       lineTextNewAnnotation, LineType1.MethodEnds, "End", 
			    		                                       DatumCategory1.None, "", "", cmIdMD1);
			    
			 umlSeqDiagram2List.add(umlSeqDiagramRecord);
			    
			 // Update the Method Line Numbers
			 //System.out.println("*** Updating Method Line Numbers ***");
			 try 
			 {	  
			    annotateUMLSequenceDiagramController.updateMethodLineNumbersSequenceDiagram(umlSeqDiagram2List);
			 }   
			 catch(Exception e4)
			 {
			    errorMessage1 = e4.getMessage();
				errorMessage2 = "Error XXXX: Occurred while Udating the Method Line Numbers" + System.lineSeparator();
				errorMessage2 = errorMessage2 + errorMessage1;
				throw new Exception(errorMessage2);
			 } 
			 
		  }
	      
		  // Update User Line Numbers
		  //System.out.println("*** Updating User Line Numbers ***");
		  try 
		  {	  
		     annotateUMLSequenceDiagramController.updateUserLineNumbersSequenceDiagram(projectIdGUI, userIdGUI);
		  }   
		  catch(Exception e5)
		  {
		     errorMessage1 = e5.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while updating the User Line Numbers" + System.lineSeparator();
			 errorMessage2 = errorMessage2 + errorMessage1;
			 throw new Exception(errorMessage2);
		  } 
			 
		  // Update Diagram Line Numbers
		  //System.out.println("*** Updating Diagram Line Numbers ***");
		  try 
		  {	  
		     annotateUMLSequenceDiagramController.updateDiagramLineNumbersSequenceDiagram(projectIdGUI);
		  }   
		  catch(Exception e6)
		  {
		     errorMessage1 = e6.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while updating the Diagram Line Numbers" + System.lineSeparator();
			 errorMessage2 = errorMessage2 + errorMessage1;
			throw new Exception(errorMessage2);
		  } 
		  
		  // Update the End of the Sequence Diagram
		  //System.out.println("*** Updating the End of the UML Sequence Diagram ***");
			 
		  //System.out.println("Project Id: "+ projectIdGUI);
		 try 
		 {	  
		    annotateUMLSequenceDiagramController.updateEndSequenceDiagram(projectIdGUI);
		 }   
		 catch(Exception e7)
		 {
		    errorMessage1 = e7.getMessage();
			errorMessage2 = "Error XXXX: Occurred while updating the End of the UML Sequence Diagram" + System.lineSeparator();
			errorMessage2 = errorMessage2 + errorMessage1;
			throw new Exception(errorMessage2);
		 }
			 			
		 // Build the UML Sequence Diagram Record 
		 umlSeqDiagramRecord = new UMLSequenceDiagramFinal(projectIdGUI, userIdGUI, 
                                                          methodIdGUI, methodStepIdGUI,
                                                          annotationType);
			
	     // Update the Annotation Type
		try 
		{	  
	       annotateUMLSequenceDiagramController.updateAnnotationType(umlSeqDiagramRecord);
		}   
		catch(Exception e8)
		{
	       errorMessage1 = e8.getMessage();
		   errorMessage2 = "Error XXXX: Occurred while updating the Annotation Type in the UML Sequence Diagram" + System.lineSeparator();
		   errorMessage2 = errorMessage2 + errorMessage1;
		   throw new Exception(errorMessage2);
		}

	   }
	   
	   public void addAnnotationUMLSeqDiagram(String databaseConfigFile, 
			                                  FRLConfiguration frlCon, 
			                                  AnnotateUMLSequenceDiagramController annotateUMLSequenceDiagramController,
			                                  AnnotateUMLSequenceDiagramFormEvent ev) throws Exception
	   {
	      int c=0, diagramLineNumberSD1=0, methodLineNumberSD1=0, 
		      projectIdGUI=0, userIdGUI=0, methodIdGUI=0, methodStepIdGUI=0, parameterIdGUI=0, 
		      parameterDataTypeIdGUI=0, attributeIdGUI=0, valueIdGUI=0, returnTypeIdGUI=0,
		      returnTypeValueIdGUI=0, cmIdSD1=0, 
		      diagramLineNumberSD2=0, methodLineNumberSD2=0,
		      diagramLineNumberMD1=0, methodLineNumberMD1=0, cmIdMD1=0,
		      diagramLineNumberME1=0, methodLineNumberME1=0, cmIdME1=0,
		      diagramLineNumberNS1=0, methodLineNumberNS1=0,
		      diagramLineNumberNE1=0, methodLineNumberNE1=0, cmIdNS1=0, cmIdNE1=0;
		        
		  String errorMessage1="", errorMessage2="", lineTextSD1="", lineType2SD1="", datumCategory2SD1="", datumValueSD1="",
		         userNameGUI="", userTextFileNameGUI="", userImageFileNameGUI="", whQuestionNameGUI="", 
		    	 methodNameGUI="", methodTextFileNameGUI="", methodImageFileNameGUI="", parameterNameGUI="",
		         attributeNameGUI="", attributeDataTypeNameGUI="", comparisonName1GUI="", valueNameGUI="", valueText1GUI="",
		    	 returnTypeNameGUI="", comparisonName2GUI="", returnTypeValueNameGUI="", 
		    	 lineTextNewAnnotation="", /*comparisonName3GUI ="",*/ annotationTypeGUI="", methodLineTextMD1="", value1="", value2="";
		  ArrayList<UMLSequenceDiagramFinal> umlSeqDiagram1List = null, umlSeqDiagram2List = new ArrayList<UMLSequenceDiagramFinal>();
		  LineType1 lineType1SD1;
		  DatumCategory1 datumCategory1SD1;

	   
	      // System.out.println("*** INSIDE ADD ANNOTATION AT THE METHOD LEVEL ***");
		    
		  /**********************************/
		  // 1.- Get the data from the GUI
		  /**********************************/
		    
		  // User Information
		  projectIdGUI         = ev.getProjectId();
		  userIdGUI            = ev.getUserId();		   
		  userNameGUI          = ev.getUserName();
	      userTextFileNameGUI  = ev.getUserTextFileName();
		  userImageFileNameGUI = ev.getUserImageFileName();
				   
		  // Wh-Question Information 
		  whQuestionNameGUI     = ev.getWhQuestionName();

		  // Method Information 
		  methodIdGUI            = ev.getMethodId();
	      methodStepIdGUI        = ev.getMethodStepId();
		  methodNameGUI          = ev.getMethodName();
		  methodTextFileNameGUI  = ev.getMethodTextFileName();
		  methodImageFileNameGUI = ev.getMethodImageFileName();
				   
		  // Parameter Information
		  parameterIdGUI         = ev.getParameterId();
		  parameterDataTypeIdGUI = ev.getParameterDataTypeId();
		  parameterNameGUI       = ev.getParameterName();
				   
		  // Attribute Information 
		  attributeIdGUI           = ev.getAttributeId();
		  attributeNameGUI         = ev.getAttributeName();
		  attributeDataTypeNameGUI = ev.getAttributeDataTypeName();
				   
		  // Comparison Operator Information 
		  comparisonName1GUI     = ev.getComparisonName1();
		  comparisonName2GUI     = ev.getComparisonName2();
				   
		  // Value Information 
		  valueIdGUI              = ev.getValueId();
		  valueNameGUI            = ev.getValueName();
		  valueText1GUI           = ev.getValueText1();

		  // Return Type Information 
		  returnTypeIdGUI        = ev.getReturnTypeId();
		  returnTypeNameGUI      = ev.getReturnTypeName();
			
		  returnTypeValueIdGUI   = ev.getReturnTypeValueId();
		  returnTypeValueNameGUI = ev.getReturnTypeValueName();
		  
		  // Comparison Operator Information 
		  //logicalNameGUI     = ev.getLogicalName();
		  //comparisonName3GUI   = ev.getComparisonName3();
		  
		  // Annotation Type Information 
		  annotationTypeGUI  = ev.getAnnotationType();
		  
			/*
			System.out.println("*** 1.- Information about the GUI fields ***");
			
			System.out.println("Project Id GUI               : " + projectIdGUI);
			
			System.out.println("User Id GUI                  : " + userIdGUI);
		    System.out.println("User Id GUI                  : " + userIdGUI);
		    System.out.println("User Name GUI                : " + userNameGUI);
		    System.out.println("User TextFileName GUI        : " + userTextFileNameGUI);  
		    System.out.println("User ImageFileName GUI       : " + userImageFileNameGUI);
		    
		    System.out.println("Wh Question                  : " + whQuestionNameGUI);

		    System.out.println("Method Id GUI                : " + methodIdGUI);
		    System.out.println("Method Step Id GUI           : " + methodStepIdGUI);        
		    System.out.println("Method Name GUI              : " + methodNameGUI);      
		    System.out.println("Method TextFileName GUI      : " + methodTextFileNameGUI);  
		    System.out.println("Method ImageFileName GUI     : " + methodImageFileNameGUI); 
				   
		    System.out.println("Parameter Id GUI             : " + parameterIdGUI);
		    System.out.println("Parameter DataType Id GUI    : " + parameterDataTypeIdGUI); 
		    System.out.println("Parameter Name GUI           : " + parameterNameGUI);    

		    System.out.println("Attribute Id GUI             : " + attributeIdGUI);         
		    System.out.println("Attribute Name GUI           : " + attributeNameGUI);    
		    System.out.println("Attribute Data Type Name GUI : " + attributeDataTypeNameGUI);   
		    				   
		    System.out.println("Comparision Name 1 GUI       : " + comparisonName1GUI);    
		    System.out.println("Comparison Name 2 GUI        : " + comparisonName2GUI);    
				
		    System.out.println("Value Id GUI                 : " + valueIdGUI);  
		    System.out.println("Value Name GUI               : " + valueNameGUI);  
		    System.out.println("Value Text 1 GUI             : " + valueText1GUI);  
		    
				   
		    System.out.println("Return Type Id GUI           : " + returnTypeIdGUI);       
		    System.out.println("Return Type Name GUI         : " + returnTypeNameGUI);      

				   
		    System.out.println("Return Type Value Id GUI     : " + returnTypeValueIdGUI);   
		    System.out.println("Return Type Value Name GUI   : " + returnTypeValueNameGUI); 
		    */
		    
		    /****************************************************************************/
		    // 2.- Get the data from the current UML Sequence Diagram to the Method Level
		    /****************************************************************************/
					
		  // Connect to the Database
		  try 
		  {
		     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
		  } 
		  catch (Exception e1) 
		  {
		     errorMessage1 = e1.getMessage();
			 errorMessage2 = "Error XXXX: Ocurred while Connecting to the Database";
			 errorMessage2 = errorMessage2 + errorMessage1;
			 throw new Exception(errorMessage2);
	      }
		  		  
		  // Get the initial UML Sequence Diagram from the Method Level
		  try 
		  {
		     umlSeqDiagram1List = annotateUMLSequenceDiagramController.loadUMLSequenceDiagramMethod(projectIdGUI, userIdGUI, 
		                                                                                            methodIdGUI, methodStepIdGUI);
		  }
		  catch (Exception e4) 
		  {
		     errorMessage1 = e4.getMessage();
			 errorMessage2 = "Error XXXX: Ocurred while getting the Initial UML Sequence Diagram at the Method Level";
			 errorMessage2 = errorMessage2 + errorMessage1;
			 throw new Exception(errorMessage2);
		  }
		  
		 //System.out.println("*** 2.- Information about the Initial UML Sequence Diagram Method Level ***");
			
		  // Loop to load the current UML Sequence Diagram at the Method Level
		 for (c = 0; c < umlSeqDiagram1List.size(); c++) 
		 {
		     // Get the fields	
		     diagramLineNumberSD1 = umlSeqDiagram1List.get(c).getDiagramLineNumber();
		     methodLineNumberSD1  = umlSeqDiagram1List.get(c).getMethodLineNumber();
		     lineTextSD1          = umlSeqDiagram1List.get(c).getLineText();
		     lineType1SD1         = umlSeqDiagram1List.get(c).getLineType1();
		     lineType2SD1         = umlSeqDiagram1List.get(c).getLineType2();
		     datumCategory1SD1    = umlSeqDiagram1List.get(c).getDatumCategory1();
		     datumCategory2SD1    = umlSeqDiagram1List.get(c).getDatumCategory2();
		     datumValueSD1        = umlSeqDiagram1List.get(c).getDatumValue();
		     cmIdSD1              = umlSeqDiagram1List.get(c).getClassMethodIdentifier();
		       
		     /*
		     System.out.println("Diagram Line Number SD1     : " + diagramLineNumberSD1);
		     System.out.println("Method Line Number SD1      : " + methodLineNumberSD1);
		     System.out.println("Line Text SD1               : " + lineTextSD1);
		     System.out.println("Line Type 1 SD1             : " + lineType1SD1);
		     System.out.println("Line Type 2 SD1             : " + lineType2SD1);
		     System.out.println("Datum Category 1 SD1        : " + datumCategory1SD1);
		     System.out.println("Datum Category 2 SD1        : " + datumCategory2SD1);
		     System.out.println("Datum Value SD1             : " + datumValueSD1);
		     System.out.println("Class Method Identifier SD1 : " + cmIdSD1);
		     */
		       
		     if(lineType2SD1.equals("Method Definition"))
		     {
		        diagramLineNumberMD1 = diagramLineNumberSD1;
		        methodLineNumberMD1  = methodLineNumberSD1;
		    	cmIdMD1              = cmIdSD1;
		    	methodLineTextMD1    = lineTextSD1;
		    	
		    	/*
		    	System.out.println("*** Method Definition ***");
		    	
			    System.out.println("Diagram Line Number NS1     : " + diagramLineNumberNS1);
			    System.out.println("Method Line Number  NS1     : " + methodLineNumberNS1);
			    System.out.println("Diagram Line Number NE1     : " + diagramLineNumberNE1);
			    System.out.println("Method Line Number  NE1     : " + methodLineNumberNE1);
			    
			    */
		     }
		       
		     if(lineType1SD1.equals(LineType1.MethodEnds))
		     {
		        diagramLineNumberME1 = diagramLineNumberSD1;
		        methodLineNumberME1  = methodLineNumberSD1;
		     	cmIdME1              = cmIdSD1;
		    	methodLineNumberSD2 = methodLineNumberSD1;
		     }
		     
		     if(lineType2SD1.equals("Note Starts") && lineTextSD1.contains("#lightpink"))
		     {
		        diagramLineNumberNS1 = diagramLineNumberSD1;
		        methodLineNumberNS1  = methodLineNumberSD1;
		        cmIdNS1              = cmIdSD1;
		        
		        diagramLineNumberNE1 = diagramLineNumberNS1 + 2;
		        methodLineNumberNE1  = methodLineNumberNS1  + 2;
		        cmIdNE1              = cmIdNS1;
		        
		        /*
		        System.out.println("*** Annotation ALREADY PRESENT ***");
			    System.out.println("Diagram Line Number NS1     : " + diagramLineNumberNS1);
			    System.out.println("Method Line Number  NS1     : " + methodLineNumberNS1);
			    System.out.println("Diagram Line Number NE1     : " + diagramLineNumberNE1);
			    System.out.println("Method Line Number  NE1     : " + methodLineNumberNE1);
			    */
			    
			    
		     }
	      }
		  
		  /*
		  System.out.println("Inside ADD Annotation UML SEQUENCE DIAGRAM");
		  
		  System.out.println("Parameter Name GUI    : " + parameterNameGUI);
		  System.out.println("Attribute Name GUI    : " + attributeNameGUI);		  		  
		  System.out.println("Value Name GUI        : " + valueNameGUI);
		  
		  System.out.println("Value Text 1 GUI      : " + valueText1GUI);
		  System.out.println("Value Text 2 GUI      : " + valueText2GUI);
		  
		  System.out.println("Value 1               : " + value1);
		  System.out.println("Value 2               : " + value2);
		  
		  System.out.println("Comparison Name 1 GUI : " + comparisonName1GUI);
		  System.out.println("Comparison Name 3 GUI : " + comparisonName3GUI);
		  */
		  		  
          // Build the annotation into the UML Sequence Diagram
    	  try 
		  {
             createAnnotation(frlCon, annotateUMLSequenceDiagramController,
                              projectIdGUI, userIdGUI, userNameGUI,
                              methodIdGUI, methodStepIdGUI, methodNameGUI,
                              diagramLineNumberSD2, diagramLineNumberSD1,
                              methodLineNumberSD2, cmIdMD1,
        		              diagramLineNumberMD1, methodLineNumberMD1, methodLineTextMD1, 
        		              diagramLineNumberME1, methodLineNumberME1,
        		 		      diagramLineNumberNS1, methodLineNumberNS1, cmIdNS1,
        		 		      diagramLineNumberNE1, methodLineNumberNE1, cmIdNE1,
                              annotationTypeGUI, parameterNameGUI, attributeNameGUI, 
                              attributeDataTypeNameGUI,
                              valueNameGUI, valueText1GUI,
                              comparisonName1GUI, 
	                          returnTypeNameGUI, comparisonName2GUI, 
	                          returnTypeValueNameGUI
	                          );
		  }
		  catch (Exception e5) 
		  {
		     errorMessage1 = e5.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while disconnecting from the Database";
			 errorMessage2 = errorMessage2 + errorMessage1;
			 throw new Exception(errorMessage2);
			 
		  } 
		  

		  // Disconnect from the Database 
		  try 
		  {
		     annotateUMLSequenceDiagramController.disconnect();
		  } 
		  catch (Exception e6) 
		  {
		     errorMessage1 = e6.getMessage();
			 errorMessage2 = "Error XXXX: Occurred while disconnecting from the Database";
			 errorMessage2 = errorMessage2 + errorMessage1;
			 throw new Exception(errorMessage2);
			 
		  }    
   }
	   
   public void save (String databaseConfigFile, 
                     FRLConfiguration frlCon, 
                     AnnotateUMLSequenceDiagramController annotateUMLSequenceDiagramController,
                     AnnotateUMLSequenceDiagramFormEvent ev) throws Exception
   {
      int projectId=0, userId=0, c=0;
	  String userName="", userTextFileName="", userImageFileName="", dirPathName1="", dirPathName2="", 
			 umlTextFileName="IncidentSeqDiagram_Modified", errorMessage1="", errorMessage2="";
	  ArrayList<UserData> userList = new ArrayList<UserData>();
	  
	  // Obtaining the information from the GUI
	  projectId         = ev.getProjectId();
	  userId            = ev.getUserId();
	  userName          = ev.getUserName();
	  userTextFileName  = ev.getUserTextFileName();
	  userImageFileName = ev.getUserImageFileName();

	  // Connect to the Database
	  try 
	  {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while Connecting to the Database";
		 errorMessage2 = errorMessage2 + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
	  try 
	  {
	     userList = annotateUMLSequenceDiagramController.loadUsers(projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error XXXX: Ocurred while loading the Users of the UML Sequence Diagrams";
		 errorMessage2 = errorMessage2 + errorMessage1;
		 throw new Exception(errorMessage2);
      }
	  
	  dirPathName1 = frlCon.projectOutputDir + File.separator + 
		             frlCon.umlDirectoryName + File.separator + 
		             frlCon.projectName      + File.separator;
	  
	  // Loop for the ArrayList
	  for (c = 0; c < userList.size(); c++) 
	  {
	     userId            = userList.get(c).getUserIdentifier();
		 userName          = userList.get(c).getUserName();
		 userTextFileName  = userList.get(c).getUserTextFileName();
		 userImageFileName = userList.get(c).getUserImageFileName();
		 
		 // Build the directory name for this User
		 dirPathName2 = dirPathName1 + userName + File.separator;
		 
		 System.out.println("User Id             : " + userId);
		 System.out.println("User Name           : " + userName);
		 System.out.println("User Text FileName  : " + userTextFileName);
		 System.out.println("User Image FileName : " + userImageFileName);
		 System.out.println("Dir Path Name       : " + dirPathName2);
		 
	  }
	  
	  
	  // Disconnect from the Database 
	  try 
	  {
	     annotateUMLSequenceDiagramController.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while disconnecting from the Database";
		 errorMessage2 = errorMessage2 + errorMessage1;
		 throw new Exception(errorMessage2);
	  }    
	   
   }

}
