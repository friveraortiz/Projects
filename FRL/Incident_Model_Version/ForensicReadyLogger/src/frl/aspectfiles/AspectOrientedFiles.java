package frl.aspectfiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import frl.configuration.FRLConfiguration;
import frl.controller.ClassMethodController;
import frl.controller.GraphicalUserInterfaceController;
import frl.controller.TextFileController;
import frl.controller.TreeStructureController;
import frl.model.ClassMethod;
import frl.model.DatabaseMethod2;
import frl.model.FieldType;
import frl.model.PropertyType;
import frl.model.TextFileDetails;
import frl.model.TextFileProperties;

public class AspectOrientedFiles
{
  	
   public AspectOrientedFiles(FRLConfiguration frlCon) //1
   {
	   
   }
			
   public String generateMethodName2(FRLConfiguration frlCon, String text, String method)
   {
      String endApp1="", endApp2="";
  

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
	     System.out.println("Error XXXX: The End Project Method Configuration Parameter is empty.");
		 endApp2 = "";
	  }
	  
      return endApp2;
	   
   }
   
   public String generateMethodName1(FRLConfiguration frlCon, String text, String method)
   {
	   String packageName1="", className1="", shortMethodName1="";
	   String shortMethodName2="", returnType2="";
	   String errorMessage, methodName1="", methodName2=""; 
	   String [] parts;
	   ClassMethod cm;
	   ClassMethodController classMethodCon = new ClassMethodController();
	      
	   // Validate if the input parameter has a value
	   if(method != null && !method.isEmpty())
	   {
	   
		  // Replace the delimiter "." for "~" 
	      method = method.replace(frlCon.objectOrientedDelimiter1, frlCon.objectOrientedDelimiter2);

	      // Get the different parts of the Start Application Method
	      parts = method.split(frlCon.objectOrientedDelimiter2);
	      packageName1     = parts[0];
	      className1       = parts[1]; 
	      shortMethodName1 = parts[2];
	      
	   	  // Validate if the methodName is equal to a Constructor
	   	  if(shortMethodName1.equals(frlCon.initializeObjectMethod))
	   	     shortMethodName1 = className1;

	   	  try 
	      {
	         classMethodCon.connect(frlCon.propertiesFilePath);
	      } 
	      catch (Exception e) 
	      {
	         errorMessage = e.getMessage();
		     System.out.println("Error XXXX: Occurred while connecting to the Database. Error Message: " + errorMessage);
	      }
 	  
	      try 
	      {
	    	 // Get the shortMethodName and returnType from the Forensic-Ready Logger Database 
	         cm = classMethodCon.loadShortMethodName1(packageName1, className1, shortMethodName1);
	         
	         shortMethodName2 = cm.getShortMethodName();
	         returnType2      = cm.getReturnType1();
	         
	         // Validate if the returnType2 is equals to "Constructor"
	         if(returnType2.equals(frlCon.initializeObjectName))
		        // Build the methodName1
		        methodName1 = packageName1 + frlCon.objectOrientedDelimiter1 +
		        		      className1   + frlCon.objectOrientedDelimiter1 + 
		        		      frlCon.initializeObjectMethod + frlCon.endCallMethod2;	 
	         
	         else
	        	// returnType2 is equal to a method data type such as boolean, void, string, etc 
	            // Build the methodName1
	            methodName1 = returnType2 +  " " + packageName1 + frlCon.objectOrientedDelimiter1 + 
	            		      className1  + frlCon.objectOrientedDelimiter1 + shortMethodName2 + 
	            		      frlCon.endCallMethod2;
	         
	         // Build the methodName2
	         methodName2 = text.replace(frlCon.confPropertyName1, methodName1);
	         
	      } 
	      catch (SQLException e) 
	      {
	         errorMessage = e.getMessage();
		     System.out.println("Error XXXX: Occurred while loading the Short Method Name. Error Message: " + errorMessage);
	      }
	      
	      classMethodCon.disconnect();
      }
	  else
	  {
	     System.out.println("Error XXXX: The Configuration Parameter for the Method Name is empty.");
		 methodName2 = "";
	  }
	   
      return methodName2;

   }
   
   public String generateMethodName(FRLConfiguration frlCon, String text, String inputMethod)
   {
      String methodName = "";
      
      switch(inputMethod)
      {
         case "startApplication":
        	 methodName = generateMethodName1(frlCon, text, frlCon.startProjectMethod); 	 
         break;
         
         case "endApplication":
            methodName = generateMethodName2(frlCon, text, frlCon.endProjectMethod);
         break;
         
         case "connectProjectMethod":
            methodName = generateMethodName1(frlCon, text, frlCon.connectProjectMethod); 	 	 
         break;
         
      }
      return methodName;  		  
   }
   
 
   public String getGuiLibList(FRLConfiguration frlCon, String text)
   {
      String errorMessage, guiLibList1="", guiLibList2="", newText;
	  int c;
	  GraphicalUserInterfaceController guiCon = new GraphicalUserInterfaceController();
	  ArrayList<String> guiList = new ArrayList<String>();

      // Connect to the Database
      try 
      {
 	     guiCon.connect(frlCon.propertiesFilePath);
	  } 
      catch (Exception e) 
      {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
   
      // Get the Graphical User Interface from the Forensic-Ready Logger Database
      try 
      {
	     guiList = guiCon.loadAllGui(frlCon.programmingLanguage);
	  } 
      catch (Exception e) 
      {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while loading all the Graphical User Interface from the Database . Error Message: " + errorMessage);
	  }
   
      // Disconnect from the Forensic-Ready Logger Database
      guiCon.disconnect();
      
      // Loop into the GuiList ArrayList
      guiLibList1 = "";
      for (c=0; c < guiList.size(); c++) 
      { 		      
         guiLibList1 = guiLibList1 + guiList.get(c) + frlCon.guiLibDelimiter;
      }
      
      // Building the line to assing a variable the value of the
      // List of the graphical user interfaces
      newText = frlCon.methodName3 + frlCon.assignValueDelimiter;
      guiLibList2 = text.replace(frlCon.confPropertyName1, newText);
      
      newText = frlCon.whiteSpaceWordsDelimiter2 + guiLibList1 + frlCon.whiteSpaceWordsDelimiter2;
      guiLibList2 = guiLibList2.replace(frlCon.guiLibDelimiter, newText) + frlCon.endStatementDelimiter;
      
      return guiLibList2;   	   
   }
   
   public String getDatabaseMethodsList(FRLConfiguration frlCon, String text)
   { 

      int c, pipeLineEnd;
	  String packageName, className, shortMethodName, errorMessage;
	  String returnType1, returnType2, line, dbMethodList ="";
      TreeStructureController treeStructCon;
      ArrayList<DatabaseMethod2> dbMetDet = new ArrayList<DatabaseMethod2>();
	   
      // Create a new object of Tree StructureController class
      treeStructCon = new TreeStructureController();

	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     treeStructCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
	     System.out.println("Error XXXX: Occurred while conecting to the Database." + "Error Message: " + errorMessage);
	  } 

   	  // Get Build the Database Method List      
      try 
	  {
	     dbMetDet = treeStructCon.loadDBMethodsDetails();
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
	  	 System.out.println("Error XXXX: Occurred while loading the Database Methods Details." + "Error Message: " + errorMessage);
	  }
      
      dbMethodList ="";
      pipeLineEnd = dbMetDet.size() - 1;
      
      // Loop through the DatabaseMethodDetails ArrayList
      for (c = 0; c < dbMetDet.size(); c++) 
	  {
	     // Get the fields from the textFileDetails ArrayList
		 packageName     = dbMetDet.get(c).getPackageName();
		 className       = dbMetDet.get(c).getClassName();
		 shortMethodName = dbMetDet.get(c).getShortMethodName();
		 returnType1     = dbMetDet.get(c).getReturnType1();
		 returnType2     = dbMetDet.get(c).getReturnType2();
		 
		 // Validate if the returnType1 is equal to "Class"
		 if(returnType1.equals(frlCon.bluePrintObject2))
		    returnType1 = "";	
		 
		 // Build the line that contains the call method for the Aspect
	     line = "                            "+
	    		frlCon.startCallMethod + returnType1 + returnType2 + " " + 
	    		packageName     + frlCon.objectOrientedDelimiter1 + 
	            className       + frlCon.objectOrientedDelimiter1 + 
	            shortMethodName + frlCon.endCallMethod1;
	     
	     // Validate if the counter is less or equal to pipeLineFlag end value
	     if(c < pipeLineEnd)
	        line = line + " " + frlCon.callMethodDelimiter + " " +
	               System.getProperty("line.separator");
	     else
	    	 line = line + frlCon.endStatementDelimiter;
	     
	     dbMethodList = dbMethodList + line;
		 
	  }  
      
   	  // Disconnect from the Forensic Ready Logger Database
   	  treeStructCon.disconnect();
   	  
      return dbMethodList;   
   }
   
   public String getNonPrimitiveDataTypeList(FRLConfiguration frlCon)
   { 
      String nonPrimDataList = "", errorMessage = "", library="";
      int c;
      TreeStructureController treeStrucCon;
      ArrayList<String> nonPrimLib = new ArrayList<>();
      
      treeStrucCon = new TreeStructureController();
      
      // Connect to the Forensic Ready Logger Database
      try 
      {
	     treeStrucCon.connect(frlCon.propertiesFilePath);
	  } 
      catch (Exception e) 
      {
  	     errorMessage = e.getMessage();
  		 System.out.println("Error XXXX: Occurred while conecting to the Database." + "Error Message: " + errorMessage);
	  }
      
      // Get all the libraries from the Non-Primitive Return Types
      try 
      {
	     nonPrimLib = treeStrucCon.getLibNonPrimRetType(frlCon.programmingLanguage);
	  } 
      catch (SQLException e) 
      {
   	     errorMessage = e.getMessage();
   		 System.out.println("Error XXXX: Occurred while getting the Non Primitive Return Types." + "Error Message: " + errorMessage);
	  }
      
      // Loop through the Non Primitives Libraries ArrayList
	  for (c = 0; c < nonPrimLib.size(); c++) 
	  {
		 library = nonPrimLib.get(c);
		 nonPrimDataList = nonPrimDataList + library;
		  
	  }
      
      // Disconnect to the Forensic Ready Logger Database
      treeStrucCon.disconnect();
      
      return nonPrimDataList;
   }
   
   public String getConfigPropValues(String text,
		                             int headerId,
		                             int propertyId,
		                             FRLConfiguration frlCon,
		                             TextFileController textFileCon)   
   {
      String name, value, line = "", errorMessage, newValue;
      String propTypeStr;
      TextFileProperties tfp = null;
      PropertyType propType;
      
      // Get all the text file properties for this header id
      try 
      {
         tfp = textFileCon.getPropertyValue(headerId, propertyId);
	  } 
      catch (SQLException e) 
      {
 	     errorMessage = e.getMessage();
 	     System.out.println("Error XXXX: Occurred while getting the Property Value. " + "Error Message: " + errorMessage);
	  }
      
      // Obtain each of the text file properties
      
      name     = tfp.getName();
      value    = tfp.getValue();	
	  propType = tfp.getType();
	  
	  // Convert Enum to String
	  propTypeStr = propType.name();
	  
	  // Validate if the property Type is equals to "Component"
	  if(propTypeStr.equals(frlCon.fieldType3))
	     line  = text.replace(frlCon.confPropertyName1, value);
	  else
		 // Validate if the property Type is equals to "Attribute"
	     if(propTypeStr.equals(frlCon.fieldType4))
	     {
	    	if(name.equals(frlCon.specialDelimiter)) 
		       newValue = name + frlCon.assignValueDelimiter + value;
	    	else
	           newValue = name + frlCon.assignValueDelimiter + 
	        	   	      frlCon.whiteSpaceWordsDelimiter2 + value + 
	        		      frlCon.whiteSpaceWordsDelimiter2;
	    	
	    	line  = text.replace(frlCon.confPropertyName1, newValue); 
	    	
	     }
	     // Validate if the property Type is equals to "Method"
	     else if(propTypeStr.equals(frlCon.fieldType5))
	     {
	    	// Validate if the value is equal to "nonPrimitiveDataType"
	        if(value.equals(frlCon.methodName1))	 
	    	   // Generate the Non Primitive Data Type List
	    	   line = getNonPrimitiveDataTypeList(frlCon);
	        else
	           // Validate if the value is equal to "databaseMethods"	
	           if(value.equals(frlCon.methodName2))
	           {	   
	        	  // Generate the Database Methods List 
	              line = getDatabaseMethodsList(frlCon, text);
	           }
	           else
		           // Validate if the value is equal to "guiLibs"	
		           if(value.equals(frlCon.methodName3))
		           {	   
		        	  // Generate the Graphical User Interface Library List 
		              line = getGuiLibList(frlCon, text);

		           } 
		           else  
		              // Generate the Starting Application Method Name   
		              if(value.equals(frlCon.methodName4))
			             line = generateMethodName(frlCon, text, frlCon.methodName4);
		              else
		            	  // Generate the Ending Application Method Name 
		            	  if(value.equals(frlCon.methodName5))
		            	     line = generateMethodName(frlCon, text, frlCon.methodName5);  
		            	  else
			            	  // Generate the Connect Application Method Name 
			            	  if(value.equals(frlCon.methodName6))
			            	     // Generate the Connect Application Method Name 
			            	     line = generateMethodName(frlCon, text, frlCon.methodName6); 
	              
	     }
  			
      return line; 	
   }
   
   public String getPropertyValue(String propertiesFilePath, String name)
   {
      String errorMessage, value = "";
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
	   catch (IOException e) 
	   {
	      errorMessage = e.getMessage();
		  System.out.println("Error XXXX: Occurred while loading the Configuration File. Error Message: " + errorMessage);
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
			     System.out.println("Error XXXX: Occurred while closing the Configuration File. Error Message: " + errorMessage);
			  }
	      }
	   }

      return value;   
   }
   
   public void updateTextFileProperties(int headerId, TextFileController textFileCon, FRLConfiguration frlCon)
   {
      ArrayList<TextFileProperties> textFileProps = new ArrayList<>();
	  String errorMessage, name, value;
	  int c, propId;	  
	   
	  try 
	  {
	     textFileProps = textFileCon.loadPropertyNames(headerId);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
	 	 System.out.println("Error XXXX: Occurred while loading the Properties Names. " + "Error Message: " + errorMessage);
      }
	  
      // Loop through the TextFileProps ArrayList
	  for (c = 0; c < textFileProps.size(); c++) 
	  {
         propId = textFileProps.get(c).getPropId();
	     name   = textFileProps.get(c).getName();
	     
	     // Obtain the Property Value
	     value = getPropertyValue(frlCon.propertiesFilePath, name);
	     
	     // Update the Property Name in the textFileProperties table
	     try 
	     {
	        textFileCon.updatePropertyNames(headerId, propId, value);
		 } 
	     catch (SQLException e) 
	     {
		    errorMessage = e.getMessage();
		 	System.out.println("Error XXXX: Occurred while loading the Properties Names. " + "Error Message: " + errorMessage);

	     }

	  }	 
	   
	   
   }
   
   public void generateAspectFile(FRLConfiguration frlCon, String fileName)
   {
	   
	  int i, headerId = 0, propertyId=0;
	  String text;
	  String filePath = null, line = "", errorMessage;
	  
	  FieldType type;
      FileWriter fw = null;
	  BufferedWriter bw = null;	  
	  TextFileController textFileCon;
      ArrayList<TextFileDetails> textFileDets = new ArrayList<>();	
      

	  // Create a new object of TextFileController class
	  textFileCon = new TextFileController();
			  
      // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     textFileCon.connect(frlCon.propertiesFilePath);
	  } 
	  catch (Exception e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while conecting to the Database." + "Error Message: " + errorMessage);
	  }
			  
	  // Get the current Header Id
	  try 
	  {
	     headerId = textFileCon.getHeaderId(fileName, 
		    		                        frlCon.programmingLanguage);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXX: Occurred while loading the Header Id for the File: "+fileName + " Error Message: " + errorMessage);
	  }
		 
	  String format = frlCon.tabDelimiter1;
      System.out.format(format, fileName, 1);
      System.out.println(fileName);
	  
	  // Update the textFileProperties
	  updateTextFileProperties(headerId, textFileCon, frlCon);
	
	  // Get the textFileDetails ArrayList from the Database
	  try 
	  {
	     textFileDets = textFileCon.getTextFile(headerId);
	  } 
	  catch (SQLException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error XXXX: Occurred while loading the Text File Details from the Database into Database Class. " + "Error Message: " + errorMessage);
	  }
	  
	  // Build the Path for the new Text File that will be created
	  // in the Project Output Directory
	  filePath = frlCon.projectOutputDir + fileName;
		  
	  // Create a new Text File
	  File file = new File(filePath);
	
	  // If file doesn't exists, then create it
	  try
	  {
	     if (!file.exists()) 
	     {
	        file.createNewFile();
	        fw = new FileWriter(file.getAbsoluteFile());
	     }
	     else
	     { 	 
	        file.delete();
	     	fw = new FileWriter(file,true); 
	      }	 
	
	      bw = new BufferedWriter(fw);
	   }
	   catch(Exception e)
	   {
	      errorMessage = e.getMessage();
		  System.out.println("Error XXXX: Occurred while creating a new Aspect File: "+filePath + "Error Message: " + errorMessage);
	   }
	   
	   
	   // Loop through the textFileDetails ArrayList
	   for (i = 0; i < textFileDets.size(); i++) 
	   {
			  
	      // Get the fields from the textFileDetails ArrayList
		  text       = textFileDets.get(i).getText();
		  type       = textFileDets.get(i).getType();
		  propertyId = textFileDets.get(i).getPropertyId();
		   
		  // Validate if the field type is equal to "Personalized"
		  if (type.name().equals(frlCon.fieldType1))
		     // Get the final value of the line from the Text File Properties
		     // or from some methods 
			 line = getConfigPropValues(text, headerId, propertyId, frlCon, textFileCon);
		  else 
		     // Validate if the field type is equal to "Fixed"	
		     if (type.name().equals(frlCon.fieldType2))
		        line = text;
	
	         // Write in the text file
	         try 
	         {
		        fw.append(line);
		        fw.append(frlCon.newLine2);
			 } 
	         catch (IOException e) 
	         {
	    	    errorMessage = e.getMessage();
	    	    System.out.println("Error XXXX: Occurred while writing the content to the new Aspect File: "+filePath + "Error Message: " + errorMessage);
	
			 }
	
	    } 
	   
	    // Close text file
	    try 
	    {
		   bw.close();
		} 
	    catch (IOException e) 
	    {
	 	   errorMessage = e.getMessage();
	 	   System.out.println("Error XXXX: Occurred while closing the buffer writter. " + "Error Message: " + errorMessage);
		}
	    
	    textFileCon.disconnect();
   
   } 
   
   public void generateAspectFiles(FRLConfiguration frlCon)
   {
      String folder, format;
      
      System.out.println("");
      System.out.println("For the Project: "+frlCon.projectName);
      System.out.println("Generating the Aspect Oriented Programming Files ...");
      
      folder = frlCon.projectOutputDir + frlCon.textFilePath;
	  format = frlCon.tabDelimiter1;
      System.out.println("Folder: ");
	  System.out.format(format, folder, 1);
	  System.out.println(folder);
      
	  System.out.println("Files: ");
      
      // Generate the Aspect File #1
      generateAspectFile(frlCon, frlCon.textFileNameExt1);
      
      // Generate the Aspect File #2
      generateAspectFile(frlCon, frlCon.textFileNameExt2);      
	   
   }
      

}