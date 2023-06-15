package frl.process.configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import frl.controller.configuration.ClassMethodController;
import frl.model.configuration.ClassAttribute;
import frl.model.configuration.ClassInformation;
import frl.model.configuration.ClassMethod;
import frl.model.configuration.FRLConfiguration;
import frl.model.configuration.JavaFileData;
import frl.model.configuration.SourceDirectory;


public class JavaClasses 
{

   // Method #1
   // Constructor	
   public JavaClasses() //1
   {
		   
   }
	 
   // Method #3
   public String cleanOutputDirectory(String projectOutputDir) //5
   {   
	  File projectFolder=null;
	  String errorMessage1="", errorMessage2="";
		  

	  /* Delete the Previous Project Directory */
	  try 
	  {
	     // Convert from String DataType to File DataType
		 projectFolder = new File(projectOutputDir);
		 
	     // Remove all the files from the Project Output Directory
		 FileUtils.cleanDirectory(projectFolder);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while deleting the Project Output Directory:";
	     errorMessage2 = errorMessage2 + System.lineSeparator() + projectFolder;
	     errorMessage2 = errorMessage2 + System.lineSeparator() + "Error Message: " + errorMessage1;
	     return errorMessage2;
	  }
		  
      return errorMessage2;   
   }
 
   public String getFinalConstructorShortName(String packageName, String shortConsName, String objectOrientedDelimiter1) //14
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
   
   public boolean verifyProjectException(String projectException, String shortMethodName) //12
   {
      boolean found = false;
      
	  if(projectException.equals(shortMethodName))
	     found = true;

      return found;	   
   }  

   public static String validateClassType_1(String inputReturnType, long times, FRLConfiguration frlCon)
   {
      String outputReturnType1="", outputReturnType2="", element="", tok1="", tok2="";
      int c;
      boolean libFound1=false;
      StringTokenizer stokenizer1, stokenizer2;

      stokenizer1 = new StringTokenizer(inputReturnType, frlCon.whiteSpaceWordsDelimiter1);	
      
      while(stokenizer1.hasMoreElements()) 
      {
         // returns the next token
         tok1 = (String) stokenizer1.nextElement();
         
         element = tok1;
         
         // Validate if the "[" delimiter is present in the token
         libFound1 = tok1.contains(frlCon.startArray);
         
         if(libFound1 == true)
         { 
        	 
        	stokenizer2 = new StringTokenizer(inputReturnType, frlCon.objectOrientedDelimiter1);
        	
            while(stokenizer2.hasMoreElements()) 
            {
               // returns the next token
               tok2 = (String) stokenizer2.nextElement();
                
               element = tok2;
               
               if(element.contains(frlCon.endStatementDelimiter))
               {	   
                  outputReturnType1  = element.replace(frlCon.endStatementDelimiter, "");
               }  
               
            }  
        	 
         }

      } 
      
      outputReturnType2 = outputReturnType1;
      
      // Add the start and end brackets [] of the Array
      for (c = 0; c < times; c++) 
      { 
          outputReturnType2 = outputReturnType2 + frlCon.startArray + frlCon.endArray;
      } 
      

      return outputReturnType2;

   }


   public static String validateInterfacePLType(String inputReturnType, FRLConfiguration frlCon)
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
   

   public String validateClassType_2(String inputReturnType, FRLConfiguration frlCon)
   {
      String outputReturnType="", element="", tok1="", tok2="";
	  boolean libFound1=false;
	  StringTokenizer stokenizer1, stokenizer2;

	  stokenizer1 = new StringTokenizer(inputReturnType, frlCon.whiteSpaceWordsDelimiter1);	
	      
	  while(stokenizer1.hasMoreElements()) 
	  {
	     // returns the next token
	     tok1 = (String) stokenizer1.nextElement();
	         
	     element = tok1;
	         
	     // Validate if the "." delimiter is present in the token
	     libFound1 = tok1.contains(frlCon.objectOrientedDelimiter1);
	         
	     if(libFound1 == true)
	     { 
	        	 
	        stokenizer2 = new StringTokenizer(inputReturnType, frlCon.objectOrientedDelimiter1);
	        	
	        while(stokenizer2.hasMoreElements()) 
	        {
	           // returns the next token
	           tok2 = (String) stokenizer2.nextElement();
	                
	           element = tok2;
	               
	        }  
	        	 
	     }

	  } 
	  
	  outputReturnType = element;

      return outputReturnType;

   }
 
   public String validateArrayListType_1(String inputReturnType, FRLConfiguration frlCon)
   {
      String outputReturnType="", part2="", specialCase="RowFilter";
      String[] parts;

      /*
      if(inputReturnType.equals("class org.isf.priceslist.model.PriceList"))
      {	  
      System.out.println("Inside the validateArrayListType_1 method ...");
      
      System.out.println("Input Return Type: " + inputReturnType);
      }*/
      
      //System.out.println("Inside the validateArrayListType_1 method...");
      
      // nonPrimitiveValue1=ArrayList 
      if(inputReturnType.contains(frlCon.nonPrimitiveValue1))
      {
         //System.out.println("PRUEBA 1 ..."); 
    	  
    	 /* if(inputReturnType.equals("class org.isf.priceslist.model.PriceList")) 
     	 System.out.println("CASO A");
    	  */
    	  
         parts = inputReturnType.split(frlCon.nonPrimitiveValue1);
         
         if(parts != null && parts.length > 1) 
	     {
  	        part2 = parts[1];
  	        part2 = part2.replaceAll(frlCon.startClassDef, "");
  	        part2 = part2.replaceAll(frlCon.endClassDef, "");
    	    outputReturnType = frlCon.nonPrimitiveValue1 + " " + part2;
	     }
	     else 
 	        outputReturnType = "";
         
    	 
 	     //System.out.println("Input Return Type   : " + inputReturnType);
    	 //System.out.println("Part1               : " + part1);
         /*
         if(inputReturnType.equals("class org.isf.priceslist.model.PriceList"))
         {	 
    	 System.out.println("Part2               : " + part2);
    	 System.out.println("Output Return Type  : " + outputReturnType);
         }
         */
    	 
      }
      else
    	  // nonPrimitiveValue2=List
    	  if(inputReturnType.contains(frlCon.nonPrimitiveValue2))
    	  {
     	     //System.out.println("CASO B");
     	     
    	     parts = inputReturnType.split(frlCon.nonPrimitiveValue2);
    	     
    	     /*
    	     System.out.println("Parts        : " + Arrays.toString(parts));
    	     System.out.println("Parts Lenght : " + parts.length);
    	     */
    	     /*
    	     if(inputReturnType.equals("class org.isf.priceslist.model.PriceList"))
             {	
    	     
    	     
    	     System.out.println("Parts [0]    : " + parts[0]);
             }
             */
    	     
    	     if(parts!= null && parts.length > 1) 
    	     {
    	    	 /*if(inputReturnType.equals("class org.isf.priceslist.model.PriceList"))
    	         
    	    	 System.out.println("1"); 
    	    	 */
        	    part2 = parts[1];
        	    
        	    /*
        	    System.out.println("Part1       : " + parts[0]);
        	    System.out.println("Part2       : " + part2);
        	    */
        	    
                if(part2.contains(specialCase) == false)
                { 	
        	    
        	       part2 = part2.replaceAll(frlCon.startClassDef, "");
        	  	
        	       part2 = part2.replaceAll(frlCon.endClassDef, "");
        	    
        	    //System.out.println("Part2       : " + part2);
        	  	
        	  	
        	  	   
                }
                else
                {
                	part2 = part2.replaceFirst(frlCon.startClassDef, "");
                	
                	if (part2.endsWith(frlCon.endClassDef)) 
                	{
                       part2 = part2.substring(0, part2.length()-1) + "";
                    }
                	
                	
                }
                
       	  	   outputReturnType = frlCon.nonPrimitiveValue2 + " " + part2;
       	    
        	    
    	     } 
    	     else 
    	        outputReturnType = "";
    	     
    	  }
    	  else
    		  outputReturnType = "";
      /*
      System.out.println("part 2                 : " + part2);
      System.out.println("outputReturnType       : " + outputReturnType);
      */
      return outputReturnType;

   } 
  
  
   public String getFinalReturnType_1(String returnType, FRLConfiguration frlCon) //13
   {
      String finalReturnType="";
      char startArray;
      long times = 0;
      boolean libFound0=false, libFound1=false, libFound2=false, libFound3=false, 
    		  libFound4=false, libFound5=false, libFound6=false;
      Pattern 
         libPattern0 = Pattern.compile("(javax.*.<*>)"),
         // JAVA library (java)
         libPattern1 = Pattern.compile(frlCon.findPLPattern2), 
         // Java Developer Class (class)
         libPattern2 = Pattern.compile(frlCon.findClassPattern2),
         // ArrayList Class (ArrayList)
         libPattern3 = Pattern.compile(frlCon.findArrayListPattern2),
         // List Class (List)
	     libPattern4 = Pattern.compile(frlCon.findListPattern2);

      libFound0 = returnType.startsWith("class");
      
      if(libFound0 == false)
      {		  
    	 // findClassPattern3=class [ 
         libFound1 = returnType.contains(frlCon.findClassPattern3);

         if(libFound1 == false)
         {	 
        	libFound6 =  libPattern0.matcher(returnType).find();
        	
        	if(libFound6 == false)
        	{	
        	 
               libFound4 = libPattern3.matcher(returnType).find();
            
               if (libFound4 == false)
               {	
                  libFound5 = libPattern4.matcher(returnType).find();
               
                  if (libFound5 == false)
                  {  	
                     libFound3 = libPattern2.matcher(returnType).find();
                  }   
               }
        	}
            
         }
         else
         {
            startArray = frlCon.startArray.charAt(0);  
            
            times = returnType.chars().filter(c -> c == startArray).count(); 
            
         }
      
      }

      /*
      if(returnType.contains("javax.swing.JComboBox") )
      {
    	  
      System.out.println("");
      System.out.println("*** Inside the getFinalReturnType_1 Method ***");
      
      System.out.println("Return Type: " + returnType);
      
      System.out.println("LibFound 0 : " + libFound0);
      System.out.println("LibFound 1 : " + libFound1);
      System.out.println("LibFound 2 : " + libFound2);
      System.out.println("LibFound 3 : " + libFound3);
      System.out.println("LibFound 4 : " + libFound4);
      System.out.println("LibFound 5 : " + libFound5);
      System.out.println("LibFound 6 : " + libFound6);
      
      System.out.println("Times     : " + times);
      }
      */
      
      if(libFound0 == true)
      {	  
    	  finalReturnType = "class";
      }	  
      else
         if (libFound1 == true) 
         {	  
            finalReturnType = validateClassType_1(returnType, times, frlCon);	
         }   
         else 
            if (libFound2 == true) 
            {	 
               finalReturnType = validateInterfacePLType(returnType, frlCon);
            }   
            else
    	       if(libFound3 == true)
    	       { 	
    	          finalReturnType = validateClassType_2(returnType, frlCon);
    	       }   
    	       else
                  if (libFound4 == true || libFound5 == true) 
                  {	   
	                 finalReturnType = validateArrayListType_1(returnType, frlCon);
                  }   
                  else 
                  { 	
                	 if(libFound6 == true ) 
                        finalReturnType = "Java&Object";
                	 else
                		 finalReturnType = returnType;
                  }   

      /*
      if(returnType.contains("javax.swing.JComboBox") )
      {
         System.out.println("Final Return Type: " + finalReturnType);
      }
      
      if(finalReturnType.equals("Java&List"))
    	  System.out.println("Return Type Different: " + returnType);
      */
      
      return finalReturnType;
	   
   }
  
   public String getMethodReturnTypePackage(String shortMethodName, 
			                                String returnTypeMethod, 
			                                String OODelimiter) throws Exception
   {
	   String value="", methodPackage1="", methodClass2="",
			  errorMessage1="", errorMessage2="",
			  specialCase1="Object[]", specialCase2="RowFilter", specialCase3="Object";
	   Class<?> methodClass1;
	   int caseRT=0;
	   Pattern specialChar;
	   Matcher hasSpecialChar; 
	   
	   // Special Characters 
	   specialChar = Pattern.compile ("[?!@#$%&*()_+=|<>?{}\\[\\]~-]");
	   
	   // Remove the "class" word from the return type method
	   if(returnTypeMethod.contains("class"))
	   {	   
	      methodClass2 = returnTypeMethod.replace("class ", "");
	      caseRT = 1;
	      
		  if(methodClass2.contains(specialCase1))
          {	   
             methodClass2 = methodClass2.replace(specialCase1, "Object");
             
             //System.out.println("Method Class2 : " + methodClass2); 
          }
	   }   
	   else
		   if(returnTypeMethod.contains("interface"))
		   {	   
		      methodClass2 = returnTypeMethod.replace("interface ", "");
		      caseRT = 2;
		   }  
		   else 
		   {	
			  if(returnTypeMethod.contains(specialCase2)== true) 
			  {
			     methodClass2 = returnTypeMethod; 	  
			     methodClass2 = methodClass2.replace("javax.swing.RowFilter", specialCase2);
			     
			     methodClass2 = methodClass2.replace("java.lang.Object", specialCase3);
			     
			     caseRT = 3;
			     
			     /*
			     System.out.println("TRES");
			     System.out.println("Return Type    : " + returnTypeMethod);
			     System.out.println("Method Class 2 : " + methodClass2);
			     */
			     //System.out.println("Case RT        : " + caseRT);
			  }
		      else 
		      {	  
		         methodClass2 = returnTypeMethod;
			       
		         hasSpecialChar = specialChar.matcher(returnTypeMethod);	
		           
		         if (hasSpecialChar.find() == true)
		            caseRT = 4;
		         
		         //System.out.println("CUATRO");
		         //System.out.println("Case RT        : " + caseRT);
		      }

		   }	 
	   

       if(caseRT >= 1 && caseRT <=2)
       {	   
	      // Create a new Class for the Method Class
	      try 
	      {
	         methodClass1   = Class.forName(methodClass2);
	         methodPackage1 = methodClass1.getPackageName();
	         
	      /*
	      System.out.println("Method Class   1 : " + methodClass1);
	      System.out.println("Method Package 1 : " + methodPackage1);
	      System.out.println("Value            : " + value);
	      */
	          
	      } 
	      catch(NoClassDefFoundError e1) 
	      {
	         errorMessage1 = e1.getMessage(); 
	         errorMessage2 = "Error XXXX: Occurred while trying to create a New Method Class " + returnTypeMethod + System.lineSeparator();
	         errorMessage2 = errorMessage2 + "For the Method Name: " + shortMethodName + System.lineSeparator();
	         errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	         
	         throw new Exception(errorMessage2); 
	      }
	      catch (Exception e2) 
	      {
	         errorMessage1 = e2.getMessage(); 
	         errorMessage2 = "Error XXXX: Occurred while trying to create a New Method Class " + returnTypeMethod + System.lineSeparator();
	         errorMessage2 = errorMessage2 + "For the Method Name: " + shortMethodName + System.lineSeparator();
	         errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;

	         throw new Exception(errorMessage2);
	       }
        }
	      
	    switch(caseRT)
	    {    
	       case 1:    
	          value = methodPackage1 + OODelimiter;  
	          break;  
	       case 2:    
	          value = methodPackage1;  
	          break;  
	       case 3:    
		      value = methodClass2;  
		      break;   
	       case 4:    
	          value = methodClass2;
		      break;  
	       default:     
	          value = methodClass2;; 
	    }     
	       
	    /*
	   System.out.println("Case RT : " + caseRT);
	   System.out.println("Value   : " + value);
	    */
	    
	   return value;
   }   

   public String getComponentsClass(String class1, String oODelimiter1, String oODelimiter2) throws Exception
   {
      String package1="", class3="", class4="", finalClass="", 
    		 errorMessage1="", errorMessage2="";
      Class<?> class2;
      
	  // Create a new Class for the Method Class
	  try 
	  {
	     class2   = Class.forName(class1);
	     class3   = class2.getName();
	     package1 = class2.getPackageName();
	  } 
	  catch(NoClassDefFoundError e1) 
	  {
	     errorMessage1 = e1.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + class1 + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	         
	     throw new Exception(errorMessage2); 
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + class1 + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;

	     throw new Exception(errorMessage2);
      }
	  
	  class4 = class3.replace(package1 + oODelimiter1, "");
			  
	  finalClass = package1 + oODelimiter2 + class4;
	  
	  //finalClass = class4;
	  
	  return finalClass;
   }
     
   public ArrayList<String> getClassConstructorsMethods 
                           (String packageName, 
                            String className, 
                            ArrayList<String> projExceptionList, 
                            FRLConfiguration frlCon,
                            String fileName) throws Exception
   {
      // Get the Full Name of the Constructors and the Methods of the Class
      String inputClassName="", modifierStr="", shortMethodName="", fullMethodName="", finalMethodName="", 
    		 returnTypeMethod1="", returnTypeMethod2="", shortConsName="", fullConsName="", initialConsName="", 
    		 finalConsName1="", finalConsName2="", replaceStr="", str="", substr="", before="", modifierEnum1="", 
    		 modifierEnum2="", part0="", part1="", part2="", 
    		 classTypeMethod="", value="", value1="", specialCase="RowFilter",
    		 errorMessage1="", errorMessage2="";

      int modifierInt=0, i=0, j=0, startArray=0;
      boolean exceptionFound=false, isEnum=false, flagClass=false;

      Method[] classMethods;
      String[] parts, array, components;
      Constructor<?>[] classCons = null;
      Class<?> newClass = null;
      ArrayList<String> classConsMethods = new ArrayList<String>();
      
      // Assign a value to the inputClassName variable
      inputClassName = packageName + frlCon.objectOrientedDelimiter1 + className;
    		  
      // Initialize the ArrayList
      classConsMethods.clear();
      
      /*
      if( packageName.contains("org.isf.accounting.gui") && 
     		 className.contains("PatientBillEdit"))
      {	  
    	  System.out.println("");
         System.out.println("Inside the Get Class Constructors Methods Procedure ...");
      }   
      */
      
      // Create a new class 
      try 
      {
         newClass = Class.forName(inputClassName);
      } 
      catch(NoClassDefFoundError e1) 
      {
         errorMessage1 = e1.getMessage(); 
    	 errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + packageName + "." + className + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "Error Message: " + "The JAVA Library: "+ errorMessage1 + " was NOT found.";
    	 
    	 throw new Exception(errorMessage2); 
      }
      catch (Exception e2) 
      {
         errorMessage1 = e2.getMessage(); 
    	 errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + packageName + "." + className + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
         errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         
    	 throw new Exception(errorMessage2);
      }

      // Verify if the class is an Enum
      try
      {
         isEnum = newClass.isEnum();
         
         // Get the modifier
         if(isEnum == true)
         {	  
            modifierInt = newClass.getModifiers();
            modifierStr = Modifier.toString(modifierInt);
            
            parts = modifierStr.split(frlCon.findWhiteSpaces);
            
            if (parts != null && parts.length > 0)
            {
           	   modifierEnum1 = parts[0];
           	 
           	   modifierEnum1 = modifierEnum1 + " " + frlCon.classDataType.toLowerCase();  
           	   
            }
            
         }
      }
      catch(NoClassDefFoundError e3) 
      {
         errorMessage1 = e3.getMessage(); 
    	 errorMessage2 = "Error XXXX: Occurred while trying to validate if it is an Enum of the New Class: " + packageName + "." + className + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "Error Message: " + "The JAVA Library: "+ errorMessage1 + " was NOT found.";

    	 throw new Exception(errorMessage2); 
      }
      catch (Exception e4) 
      {
         errorMessage1 = e4.getMessage(); 
    	 errorMessage2 = "Error XXXX: Occurred while trying to validate if it is an Enum of the New Class: " + packageName + "." + className + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
         errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         
    	 throw new Exception(errorMessage2);
      }
      
            
      /**************************************/
      // Get the Constructors of the Class
      /**************************************/
      try
      {
         classCons = newClass.getDeclaredConstructors();
      }
      catch(NoClassDefFoundError e5) 
      {
         errorMessage1 = e5.getMessage(); 
    	 errorMessage2 = "Error XXXX: Occurred while trying to get the Constructos of the New Class: " + packageName + "." + className + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "Error Message: " + "The JAVA Library: "+ errorMessage1 + " was NOT found.";
    	 
    	 throw new Exception(errorMessage2); 
      }
      catch (Exception e6) 
      {
         errorMessage1 = e6.getMessage(); 
    	 errorMessage2 = "Error XXXX: Occurred while trying to get the Constructors of the New Class: " + packageName + "." + className + System.lineSeparator();
    	 errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
         errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         
    	 throw new Exception(errorMessage2);
      }
      
      /*
      // See the Constructors of this Specific Class
      if( packageName.contains("org.isf.accounting.gui") && 
  		  className.contains("PatientBillEdit"))
      {
  	     System.out.println("Initial Constructor List Size: " + classCons.length);	       	
         System.out.println("Initial Constructor List: ");
         System.out.println(Arrays.toString(classCons));
      }
      */
      
      // Build the constructor name that can be found in the Java File
      for(i = 0; i < classCons.length; i++)
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
       	 
       	 if(isEnum == true)
       	 {	  
       	    returnTypeMethod1 = frlCon.classDataType;
       	     
       	    // Make a change in the finalConsName1
            parts = finalConsName1.split(frlCon.findWhiteSpaces);
                
            if (parts != null && parts.length > 0)
               modifierEnum2 = parts[0];
                
            if(modifierEnum1.equals(modifierEnum2) == false)
            {	 
               finalConsName1 = finalConsName1.replaceAll(modifierEnum2, modifierEnum1);
            }   
                
       	  }   
       	  else
       	     returnTypeMethod1 = frlCon.initializeObjectName; 
  
       	 
          // Validate if the returnTypeMethod1 is empty or null
   	      if (returnTypeMethod1 == null || returnTypeMethod1.isEmpty() || 
   	    	  returnTypeMethod1.trim().isEmpty()) 
          {	 
   	         returnTypeMethod1 = "None";
          }   
   	    
       	  // Build the full Method Name
       	  fullMethodName = finalConsName1 + frlCon.objectOrientedDelimiter2 + 
       			           finalConsName2 + frlCon.objectOrientedDelimiter2 + 
       			           returnTypeMethod1;
       	  
       	  //System.out.println("Asignando return type method en constructor");
       	  //System.out.println("Return Type Method 1: "+ returnTypeMethod1);
       	  
          // Add the new Method to the ArrayList 
       	  classConsMethods.add(fullMethodName);

         }
      
      /*
      
         // See the Constructors of this Specific Class
         if( packageName.contains("org.isf.accounting.gui") && 
     		 className.contains("PatientBillEdit"))
         {
     	    System.out.println("Final Constructor List Size: " + classConsMethods.size());	       	
            System.out.println("Final Constructor List: ");
    	    System.out.println(Arrays.deepToString(classConsMethods.toArray()));
         }
         */

         /**********************************/
         // Get the Methods of the Class
         /**********************************/   
         try
         {
            classMethods = newClass.getDeclaredMethods();   
         }
         catch(NoClassDefFoundError e7) 
         {
            errorMessage1 = e7.getMessage(); 
            errorMessage2 = "Error XXXX: Occurred while trying to get the Methods of the New Class: " + packageName + "." + className + System.lineSeparator();
            errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
            errorMessage2 = errorMessage2 + "Error Message: " + "The JAVA Library: "+ errorMessage1 + " was NOT found.";

            throw new Exception(errorMessage2); 
         }
         catch (Exception e8) 
         {
            errorMessage1 = e8.getMessage(); 
            errorMessage2 = "Error XXXX: Occurred while trying to get the Methods of the New Class: " + packageName + "." + className + System.lineSeparator();
            errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
            errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
               
           throw new Exception(errorMessage2);
         }
 
         /*
         // See the Constructors of this Specific Class
         if( packageName.contains("org.isf.accounting.gui") && 
     		  className.contains("PatientBillEdit"))
         {
     	     System.out.println("Initial Method List Size: " + classMethods.length);	       	
            System.out.println("Initial Method List: ");
            System.out.println(Arrays.toString(classMethods));
         } 
         */       

         // Build the method name that can be found in the Java File
         for(i = 0; i < classMethods.length; i++)
         {
            // Initialize some variables
         	shortMethodName   = "";
         	finalMethodName   = "";
         	fullMethodName    = "";
         	classTypeMethod   = "";
         	modifierStr       = "";
            returnTypeMethod1 = "";
         	returnTypeMethod2 = ""; 
         	value             = "";
            exceptionFound    = false;
         	flagClass         = false;
         	
         	startArray        = 0;
         	modifierInt       = 0;

            // Get the short method name 
       	    shortMethodName  = classMethods[i].getName();
       	 
       	    // Get the Declaring Class Type of this Method: Class or Interface
       	    classTypeMethod = classMethods[i].getDeclaringClass().toString();
       	    
       	    //System.out.println("Class Type Method: " +classTypeMethod);
       	    
       	    // Get the Initial Return Type of the Method
            returnTypeMethod1 = classMethods[i].getGenericReturnType().toString();
            
            //System.out.println("Inside Method Loop");        	  
            //System.out.println("Return Type Method 1: "  + returnTypeMethod1);

            // Get the modifier
            modifierInt = classMethods[i].getModifiers();
            modifierStr = Modifier.toString(modifierInt).toLowerCase();
            
            /*
            if( packageName.contains("org.isf.accounting.gui") && 
               	  className.contains("PatientBillEdit") &&
               	  shortMethodName.contains("getJComboBoxPriceList"))
            {
               System.out.println("Short Method Name    : " + shortMethodName);	
               System.out.println("Class Type Method    : " + classTypeMethod);
               System.out.println("Return Type Method 1 : " + returnTypeMethod1);
               System.out.println("Modifier Int         : " + modifierInt);
               System.out.println("Modifier String      : " + modifierStr);
            }*/
            
            //System.out.println("Modifier: "+ modifierStr);
            
            // Validate if the Return Type of the Method contains an Array[]
            startArray = StringUtils.countMatches(returnTypeMethod1, frlCon.startArray);
            
            // Verify if the shortMethodName corresponds to an Exception
            exceptionFound = verifyProjectException(frlCon.methodNameException, shortMethodName);
            
            // If the shortMethodName is not in the Project Exception List then
            // Add to the list of the Class Methods
            if(exceptionFound == false)
            {	  
            	
               // Gets the Second Return Type for the Method
               returnTypeMethod2 = getFinalReturnType_1(returnTypeMethod1, frlCon);
               
               /*
               if( packageName.contains("org.isf.accounting.gui") && 
                   className.contains("PatientBillEdit") &&
                   shortMethodName.contains("getJComboBoxPriceList")
                   )
               {
                  System.out.println("*** Obtain the FINAL RETURN TYPE 1 ***");
                  System.out.println("Return Type Method 2: "  + returnTypeMethod2);
               }*/
               
               /***********************************************/
               /* Obtain the final return type of the method 
                * stored in the returnTypeMethod2 variable */
               /***********************************************/
               
               
               // Added this special Return Type Method Case: Java Class + <> 
      		   if(returnTypeMethod2.contains("Java&Object") == true) 
      		   {
      			  
      			  /* 
      			  System.out.println("*****************************"); 
      		      System.out.println("*** Caso RARO ***");   
      		      System.out.println("Return Type Method 1 : " + returnTypeMethod1);
      		      System.out.println("Return Type Method 2 : " + returnTypeMethod2);
      		      System.out.println("Short Method Name    : " + shortMethodName);
      		      */
      		      
      		      parts = returnTypeMethod1.split(frlCon.startClassDef);
      		      
      		      part0 = parts[0];
      		      part1 = parts[1];
      		      part1 = part1.replace(frlCon.endClassDef, "");
      		      
      		      /*
      		      System.out.println("Part 0 : " + part0);
      		      System.out.println("Part 1 : " + part1);
      		      */
      		      
      		      try
      		      {
      		         value = getComponentsClass(part0, frlCon.objectOrientedDelimiter1, 
      		        		                           frlCon.objectOrientedDelimiter2);
      		      }
      		      catch (Exception e9) 
      	          {
      	             errorMessage1 = e9.getMessage(); 
   	                 throw new Exception(errorMessage1);
      	          }
      		      
      		      components = value.split(frlCon.objectOrientedDelimiter2);
      		         
      		      returnTypeMethod1 = "class " + 
      		                          components[0] + 
      		    		              frlCon.objectOrientedDelimiter1 +
      		                          components[1] + 
      		                          frlCon.startClassDef;
      		      
      		      returnTypeMethod2 = components[1] + frlCon.startClassDef;
      		      
      		      /*
      		      System.out.println("Value (PART O) : " + value);
      		      
      		      System.out.println("Return Type Method 1 : " + returnTypeMethod1);
    		      System.out.println("Return Type Method 2 : " + returnTypeMethod2);
    		      */
      		      
      		      try
    		      {
    		         value = getComponentsClass(part1, frlCon.objectOrientedDelimiter1,
    		        		                           frlCon.objectOrientedDelimiter2);
    		      }
    		      catch (Exception e9) 
    	          {
    	             errorMessage1 = e9.getMessage(); 
 	                 throw new Exception(errorMessage1);
    	          }
      		      
      		      
      		      components = value.split(frlCon.objectOrientedDelimiter2);
 		         
    		      returnTypeMethod1 = returnTypeMethod1 +
    		    		              components[0] + 
    		    		              frlCon.objectOrientedDelimiter1 +
    		                          components[1] + 
    		                          frlCon.endClassDef;
    		      
    		      returnTypeMethod2 = returnTypeMethod2 + components[1] + frlCon.endClassDef;
    		      
    		      /*
    		      System.out.println("Value (PART 1) : " + value);
    		      
    		      System.out.println("Return Type Method 1 : " + returnTypeMethod1);
  		          System.out.println("Return Type Method 2 : " + returnTypeMethod2);
  		          */
    		      
      		      finalMethodName = modifierStr + " " + returnTypeMethod2 + " " + shortMethodName;
      		      
      		      /*
      		      System.out.println("Return Type Method 1 : " + returnTypeMethod1);
      		      System.out.println("Return Type Method 2 : " + returnTypeMethod2);
    		      System.out.println("Final Method Name    : " + finalMethodName);
      		      
      		      System.out.println("*****************************");
      		      */
      		      
      		   }
      		   else	 
      		   {
                  // Validates if the returnTypeMethod2 contains a Return Data Type: ArrayList or List
                  if(returnTypeMethod2.contains(frlCon.nonPrimitiveValue1) || 
            	     returnTypeMethod2.contains(frlCon.nonPrimitiveValue2)  )
                  {
                  
                     returnTypeMethod1 = returnTypeMethod2;
                  
                     parts = returnTypeMethod2.split(frlCon.findWhiteSpaces);
                  
                  //System.out.println("Inside ArrayList or List Part 1");        	  
                  //System.out.println("Return Type Method 1: "  + returnTypeMethod1);
                  
                  /*
                  System.out.println("Short Method Name    : " + shortMethodName);
                  System.out.println("Return Type Method 1 : " + returnTypeMethod1);
                  System.out.println("Return Type Method 2 : " + returnTypeMethod2);
                  
                  System.out.println("Parts                : " + Arrays.toString(parts));
                  */
                  

                  
                     if(ArrayUtils.isEmpty(parts) == false && 
                	    parts.length > 1)
                     {	  
                	 
                	    part0 = parts[0];
                        part1 = parts[1];
                     
                        // Remove whitespaces from the beginning and at the end
                        part0 = part0.trim();
                        part1 = part1.trim();
                     

                        // Validate if the Class is a Java Class or
               	        // a Developer-Defined Class
                     
               	        // Initialize the variables 
           	            value1     = "";
           	            part2      = "";

                     /*
           	         System.out.println("Part 0              : " + part0);
           	         System.out.println("Part 1              : " + part1);
           	         */
           	         
           	            if(returnTypeMethod1.contains(specialCase) == true)
           	               part1 = returnTypeMethod1;
           	            else
           	               if(part1.contains(frlCon.objectOrientedDelimiter1) == true)
           	                  part1 = "class " + part1; 
           	           

           	            try 
           	            {
           	               value1 = getMethodReturnTypePackage(shortMethodName, 
                                                               part1, 
                                                               frlCon.objectOrientedDelimiter1);
           	            } 
           	            catch (Exception e9) 
           	            {
           	               errorMessage1 = e9.getMessage(); 
                           value1     = "";
        	               throw new Exception(errorMessage1);
           	            }
           	         
           	         //System.out.println("AFTER getMethodReturnTypePackage METHOD");
           	         //System.out.println("Value 1: " + value1);
           	         
           	      
        	            if(value1.isEmpty() == false)
        	            {
        	        	 
        	        	   if(returnTypeMethod1.contains(specialCase) == false) 
        	        	   {	
        	                  part2             = value1;
        	                  returnTypeMethod2 = returnTypeMethod1;
        	            
        	            
        	            /*
        	            System.out.println("Part 0             : "+ part0);
        	            System.out.println("Part 2             : "+ part2);
        	            System.out.println("Return Type Method2: "+ returnTypeMethod2);
        	            
        	            */
        	            
        	                  returnTypeMethod2 = returnTypeMethod2.replace(part2, "");
        	                  returnTypeMethod2 = returnTypeMethod2.replace(part0, "");
        	           
        	        	      returnTypeMethod1 = returnTypeMethod1.trim();        	        	
        	        	      returnTypeMethod2 = returnTypeMethod2.trim();
        	        	   
        	        	   //System.out.println("Inside ArrayList or List Part 2");        	  
        	               //System.out.println("Return Type Method 1: "  + returnTypeMethod1);
        	        	
        	        	/*
        	        	System.out.println("Return Type Method1: "+ returnTypeMethod1);
        	        	System.out.println("Return Type Method2: "+ returnTypeMethod2);
        	        	*/
        	        	
                              finalMethodName = modifierStr + " " + part0 + 
                                                frlCon.startClassDef + 
                                                returnTypeMethod2 + 
                                                frlCon.endClassDef + " " + 
                                                shortMethodName;
        	        	   }
        	        	   else
        	        	   {	
        	        		   returnTypeMethod2 = value1;
        	        		
        	        		   finalMethodName = modifierStr       + " "  + 
                                                 returnTypeMethod2 +  " " + 
                                                 shortMethodName;
        	        	   }
                       
        	            }
        	            else
        	        	   returnTypeMethod2 = "";

                     }
                     else
                     {	  
                        returnTypeMethod2 = "";
                     
                     /*
                     if( packageName.contains("org.isf.accounting.gui") && 
                        	  className.contains("PatientBillEdit") &&
                        	  shortMethodName.contains("getJComboBoxPriceList"))
                         {
                    	 System.out.println("Return Type Method 2: "+returnTypeMethod2);
                         }
                         */

                     }
                  
                  /*
                  System.out.println("Final method name    : " + finalMethodName);
                  System.out.println("Return Type Method 2 : " + returnTypeMethod2);
                    */
                  }
                  else
                  {	   
            	     // Validates if the returnTypeMethod2 contains a Return Data Type: Class
            	     if(returnTypeMethod2.contains(frlCon.bluePrintObject1))
            	     {
            	        returnTypeMethod2 = returnTypeMethod1;
            	     
            		    parts = returnTypeMethod2.split(frlCon.findWhiteSpaces);  
            		 
                        if(ArrayUtils.isEmpty(parts) == false && parts.length > 1)
                        {	  
                   	       part0 = parts[0];
                           part1 = parts[1];
                        
                           // Remove whitespaces from the beginning and at the end
                           part0 = part0.trim();
                           part1 = part1.trim();
                        
                           // Validate if the Part O contains the Data Type: Class 
                           if(part0.contains(frlCon.bluePrintObject1) == true)
                           {	
                        	
                              // Validate if the Class is an Enum
                              if(isEnum == true)
                              {
                                 value             = frlCon.bluePrintObject1;
                        	     part1             = returnTypeMethod2;
                                 part1             = part1.replace("[L", "");
                                 part1             = part1.replace(frlCon.endStatementDelimiter, "");
                                      
                                 returnTypeMethod1 = returnTypeMethod1.replace("[L", "");
                                 returnTypeMethod1 = returnTypeMethod1.replace(frlCon.endStatementDelimiter, "");
                        	     flagClass         = true;
                        	  
                        	  //System.out.println("Inside Class");        	  
                              //System.out.println("Return Type Method 1: "  + returnTypeMethod1);
                        		      
                              }
                              else
                        	     // Validate if the Class is an Array
                                 if(startArray >=1 )
                        	     {
                        	     
                                    part1 = returnTypeMethod2;
                                    part1 = part1.replace("[L", "");
                                    part1 = part1.replace(frlCon.startArray, "");
                                    part1 = part1.replace(frlCon.endStatementDelimiter, "");
                                 
                                    for (j = 0; j < startArray; j++) 
                                    {
                                       part1 = part1 + frlCon.startArray + frlCon.endArray;
                                    }
                                       
                                    part1 = part1.replace(frlCon.bluePrintObject1 + " ", "");
                             		      
                                    array = part1.split("\\."); 
                                 
                                    if(ArrayUtils.isEmpty(array) == false && array.length > 1)
                                    {	  
                           	           value = array[0] + frlCon.objectOrientedDelimiter1 + 
                           	    	           array[1] + frlCon.objectOrientedDelimiter1;
                                    }
                                    else
                             	       value = part1;
                             	 
                                    flagClass = true;
                                 
                                 
                          	        returnTypeMethod2 = part1;
                          	        returnTypeMethod1 = "class " + returnTypeMethod2;
                          	     
                          	     //System.out.println("Inside Start Array");        	  
                                 //System.out.println("Return Type Method 1: "  + returnTypeMethod1);

                        		 }
                                 else
                                 {
                                    // Validate if the Class is a Java Class or
                                	// a Developer-Defined Class
 
                                	// Initialize the variables 
                            	    value     = "";
                            	    part1     = "";
                            	    flagClass = false;
                            		  
                            	    try 
                            	    {
                            		     value = getMethodReturnTypePackage(shortMethodName, 
                            		    		                            returnTypeMethod2, 
	                                                                        frlCon.objectOrientedDelimiter1);
                            		     
                            	     } 
                            	     catch (Exception e10) 
                            	     {
                            	        errorMessage1 = e10.getMessage(); 
                            	        throw new Exception(errorMessage1);
                            	     }
                            	   
                            	     if(value.isEmpty() == false)
                            	     {	  
                            		    flagClass = true;
                            		    part1     = returnTypeMethod2;
                            	     }   
                            	     else
                            	     {	  
                            		    flagClass = false;
                            	     } 
                            	   
                                  }
                           
                        		     
                              // For the Java or Developer-Defined Class 
                              // Divide the value contained in Part1
                              // to obtain the Final Return Type Method
                              if( flagClass == true)
                              {
                                 // Validate that part1 and value are different
                                 if(part1.equals(value) == false)
                                 {	  
                              
                                    parts = part1.split(value);                
                               
                                    if(ArrayUtils.isEmpty(parts) == false && parts.length > 1)
                                    {
                                       part1 = parts[0];
                                       part2 = parts[1];
                                   
                                       returnTypeMethod2 = part2;
                                    }

                                 }
                              
                                 // if it is an enum it should have another value
                                 if(isEnum == true ) 
                                 {	  
                                    finalMethodName = modifierStr + returnTypeMethod2 + " " + shortMethodName;
                                    returnTypeMethod1 = frlCon.classDataType;
                                 
                                 //System.out.println("Inside Enum");        	  
                                 //System.out.println("Return Type Method 1: "  + returnTypeMethod1);
                                 }
                                 else
                                 {
                                    finalMethodName = modifierStr + " " + returnTypeMethod2 + " " + shortMethodName;
                                 } 
                               
                              
                              }
                              else
                              {
                              
                                 returnTypeMethod2 = returnTypeMethod1;
                     	     
                     		     parts = returnTypeMethod2.split(frlCon.findWhiteSpaces);  
                     		 
                                 if(ArrayUtils.isEmpty(parts) == false && parts.length > 1)
                                 {	  
                                    part0 = parts[0];
                                    part1 = parts[1];
                                 
                                    // Remove whitespaces from the beginning and at the end
                                    part0 = part0.trim();
                                    part1 = part1.trim();
                                 
                                 
                                    returnTypeMethod2 = part1;
                                 
                                    finalMethodName = modifierStr + " " +
                                                      returnTypeMethod2 + " " +
                                                      shortMethodName;
                                 
                                 } 
                              
                              }
                           
                           }
                           else
                           { 	
                              // Build the final method name 
                              finalMethodName = modifierStr + " " + returnTypeMethod2 + " " + shortMethodName;
                           }
                    
                        }   
                     }
            	     else
            	     {
            	         finalMethodName   = modifierStr + " " + returnTypeMethod2 + " " + shortMethodName; 
               	         returnTypeMethod1 = returnTypeMethod2;
            	     }
 
   	              }
      		   }
               
               // Validate if the modifier contains the word: "abstract"
               //if(modifierStr.contains("abstract") == true)
               //  finalMethodName = finalMethodName.replace("abstract ","");
               
               if(classTypeMethod.contains("interface"))
               {   
            	   //System.out.println("modifier str: " + modifierStr);
                  finalMethodName = finalMethodName.replace(modifierStr + " ","");
                  
                  //System.out.println("final method name: " + finalMethodName);
                  
               }

               // Validate if the returnTypeMethod1 is empty or null
        	   if (returnTypeMethod1 == null || 
        		   returnTypeMethod1.isEmpty() || 
        	       returnTypeMethod1.trim().isEmpty()) 
               {	 
        	      returnTypeMethod1 = "None";
        	      
        	      //System.out.println("Inside Empty");        	  
                  //System.out.println("Return Type Method 1: "  + returnTypeMethod1);
               }  
        	   
        	   /* Attention: Here we are saving the full name of the java.lang. classes
        	    * 
        	    * */

        	   // Validate if the  returnTypeMethod1 contains a Java Lang Class
        	   // Then assign the value of the returnTypeMethod 2
        	   /*if(returnTypeMethod1.toLowerCase().contains("java"))
        	   {	   
        	      returnTypeMethod1 = returnTypeMethod2; 
        	   } 
        	   */
        	     
        	      
               // Build the full Method Name
               fullMethodName = finalMethodName + frlCon.objectOrientedDelimiter2 + 
            		            shortMethodName + frlCon.objectOrientedDelimiter2 + 
            		            returnTypeMethod1;
               
               /*
               if( packageName.contains("org.isf.accounting.gui") && 
                 	  className.contains("PatientBillEdit") &&
                 	  shortMethodName.contains("getJComboBoxPriceList"))
               {
            	   System.out.println("=============>>>> DATOS YA PARA INSERTAR ...");
            	   System.out.println("Final Method Name   : " + finalMethodName);
                   System.out.println("Short Method Name   : " + shortMethodName);
                   System.out.println("Return Type Method1 : " + returnTypeMethod1);
                   System.out.println("Return Type Method2 : " + returnTypeMethod2);
                   System.out.println("Full Method Name    : " + fullMethodName);
                   
               }*/
               
               // Add the new Method to the ArrayList 
               classConsMethods.add(fullMethodName);
               
            }
         }

         /*
         // See the Constructors of this Specific Class
         if( packageName.contains("org.isf.accounting.gui") && 
     		 className.contains("PatientBillEdit"))
         {
     	    System.out.println("Final Method List Size: " + classConsMethods.size());	       	
            System.out.println("Final Method List: ");
    	    System.out.println(Arrays.deepToString(classConsMethods.toArray()));
         }
         */
         
        
         /*
      System.out.println("Class Name: " + className);
       
      if( packageName.contains("org.isf.accounting.gui") && 
          className.contains("PatientBillEdit")
         )
      {  	 
         System.out.println("Methods and Constructors FINAL LIST: ");
      
      for(i = 0; i < classConsMethods.size(); i++) 
      {
         System.out.println("Method: "+ i +" "+(classConsMethods.get(i)).toString());
      }
      
      }*/
      
      
      
      return classConsMethods;

   }

   public String validateClassType_3(String inputReturnType, FRLConfiguration frlCon)
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
 
   public ArrayList<ClassMethod> getMethodStartLineNumbers(int projectId, 
		                                                   String fileName, 
		                                                   String fullMethodName, 
		                                                   String returnTypeName, 
		                                                   FRLConfiguration frlCon) throws Exception //11
   {

	  String lineText1="", lineText2="", methodParamName="", errorMessage1="", errorMessage2="";
	  int lineNum=0;
	  Path path;
      final Charset ENCODING = StandardCharsets.UTF_8;
      ClassMethod lineNumbersRecord;
      ArrayList<ClassMethod> lineNumbersList = new ArrayList<ClassMethod>();
      
      // Initialize Variables
      path = Paths.get(fileName);
      
      // Initialize the ArrayList
      lineNumbersList.clear();
      
      // Convert all these string variables into Lower Case letters
      fullMethodName = fullMethodName.toLowerCase();
      returnTypeName = returnTypeName.toLowerCase();
      
      /*
      if( path.toString().contains("PatientBillEdit"))
      {
     	 System.out.println(""); 
         System.out.println("Inside the Get Method Start Line Numbers Method ..."); 	
         
         System.out.println("Full Method Name INITIAL : " + fullMethodName);
         System.out.println("Return Type Name         : " + returnTypeName);
         System.out.println("Path                     : " + path);
      }
      */
      
      if(fullMethodName != null && !fullMethodName.trim().isEmpty()) 
      {
         // Validate if the returnTypeName is NOT equal to: Enum
         // if it is, assign to the full Method Name = full Method Name + "("
         if(returnTypeName.equals(frlCon.classDataType.toLowerCase()) == false)
         {
            fullMethodName = fullMethodName + frlCon.startParameters;	
         }
          
    	 try ( BufferedReader reader = Files.newBufferedReader(path, ENCODING))
    	 {
    		  
    	    lineText1 = null;
    	    lineText2 = "";
    	    lineNum   = 0;
    	     
    	    while ((lineText1 = reader.readLine()) != null) 
    	    {
    	       lineNum++;	  
    	    	
    		   lineText2 = lineText1;
    		   lineText2 = lineText2.toLowerCase();
    		   lineText2 = lineText2.trim();
    			
    		   methodParamName = lineText1.trim();
    			
    		   // Validate if the method is present
    		   if ( lineText2.contains(fullMethodName) ) 
    		   {
    				
    			   // Create a new record
    			   lineNumbersRecord = new ClassMethod(projectId, fullMethodName, methodParamName, lineNum);
    			   lineNumbersList.add(lineNumbersRecord);
    	    	}   
    	    }      
         }
    	 catch (Exception e1) 
    	 {
     	    errorMessage1 = e1.getMessage(); 
     		errorMessage2 = "Error XXXX: Occurred while loading the Java File: " + fileName + System.lineSeparator();
     		errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
     		errorMessage2 = errorMessage2 + "Full Method Name: " + fullMethodName + System.lineSeparator();
     		errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
     		throw new Exception(errorMessage2); 	
    	 }   	  
    	  
      }
      else
      {
         errorMessage2 = "Error XXXX: The Java File: " + fileName + System.lineSeparator();
         errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
      	 errorMessage2 = errorMessage2 + "has an Empty Full Method Name: " + fullMethodName + System.lineSeparator();
      	 errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
      	 throw new Exception(errorMessage2); 	
      }

      
      /*
      if( path.toString().contains("PatientBillEdit"))
      {
     	 System.out.println(""); 
         System.out.println("Full Method Name FINAL: " + fullMethodName);
      }
      */
       
      return lineNumbersList;
   
   }
   
  
   public String getPackageNameJavaFile(String fileName) throws Exception
   {
      String packageName="", line="", errorMessage1="", errorMessage2="";     
	  String [] lineSplit;
	  FileReader fr = null;
	  BufferedReader br = null;

  	  try 
  	  {
   	     fr = new FileReader(fileName);
  	  } 
  	  catch (FileNotFoundException e1) 
  	  {
  	     errorMessage1 = e1.getMessage(); 
  		 errorMessage2 = "Error XXXX: Occurred while loading the FileName: " + fileName + System.lineSeparator();
  		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
  		 return packageName; 	
  	  }
	   
  	  br = new BufferedReader(fr);
		   
      try 
      {
	     while((line =  br.readLine()) != null)
		 {  
		    if (line.toLowerCase().contains("package"))
		    {
		       lineSplit = line.split("\\s+");

		       packageName = lineSplit[1];
		       packageName = packageName.replace(";","");
		       
		       break;
		    }
		 } 
	  } 
      catch (IOException e2) 
      {
   	     errorMessage1 = e2.getMessage(); 
   		 errorMessage2 = "Error XXXX: Occurred while getting the Package Name from the File: " + fileName + System.lineSeparator();
   		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
   		 return packageName; 	
	  }
      
      br.close();
      fr.close();
 	   
      return packageName;
   }
   
   public int obtainSourceDirectory(int projectId, String projectInputDir, List<SourceDirectory> dirList, String fileName1) throws Exception
   {
      String pathDir="", fileName2="", errorMessage1="", errorMessage2="";	   
      int i=0, idDir1=0, idDir2=0;
      
      try
      {
         fileName2 = fileName1.replace(projectInputDir,"");
         fileName2 = fileName2.strip();
         
         //System.out.println("File Name 2: " + fileName2);
      
         // Loop for the source directories
	     for(i = 0; i < dirList.size(); i++) 
	     {
	        idDir1   = dirList.get(i).getIdDir();
		    pathDir  = dirList.get(i).getPathDir();
		    
		    //System.out.println("Id Dir 1 : " + idDir1);
		 
		    if(fileName2.startsWith(pathDir) == true)
		    {	 
		       idDir2 = idDir1;	
		       //System.out.println("Entre");
		       break;
		    }

         }  
      }
      catch (Exception e1) 
      {
   	     errorMessage1 = e1.getMessage(); 
   		 errorMessage2 = "Error XXXX: Occurred while getting the Source Directory from the File: " + fileName1 + System.lineSeparator();
   		 errorMessage2 = errorMessage2 + "Project Identifier: " + projectId + System.lineSeparator();
   		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
   		 idDir2=0;
   		 return idDir2=0; 	
	  }
	  
      return idDir2;	  
   }
   
   public String getClassMethodsStartLines(int projectId, FRLConfiguration frlCon, ArrayList<JavaFileData> javaClasses, 
		                                   List<SourceDirectory> dirList) //16
   {
	   
      String className="", packageName="", fullMethodName="", line="", shortMethodName="", 
    		 returnType="", fileName="", finalReturnType1="", finalReturnType2="", 
    		 paramMethodName="", errorMessage1="", errorMessage2="";
      int lineNumber=0, i=0, j=0, k=0, s=0, idDir=0;
      String[] method = null, parts;
      
      ArrayList<String> initialMethodsNames = new ArrayList<String>();
      ArrayList<String> finalMethodsNames = new ArrayList<String>();
      ArrayList<ClassMethod> methodStartLineNumList = new ArrayList<ClassMethod>();
      ArrayList<String> projExcepList = new ArrayList<String>(); 
      ClassMethodController classMetCon = new ClassMethodController();
      ClassMethod classMethod;
      		
      // Initialize the ArrayLists
      initialMethodsNames.clear();
      finalMethodsNames.clear();
      methodStartLineNumList.clear();
      projExcepList.clear();
      
      //System.out.println("Inside the Get Class Methods Start Lines Method ...");
      
 	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage2 = e1.getMessage();
		 return errorMessage2;
	  }	
	  
	  try 
	  {	 	   
	     classMetCon.deleteClassInformation(projectId);  
      }
      catch(Exception e2)
      {
         errorMessage2 = e2.getMessage();
         return errorMessage2;
      }
	  
	  // Delete the existing Attributes for the Java Files in the Database
	  try 
	  {
	     classMetCon.deleteClassAttribute(projectId);
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage2 = e3.getMessage();
		 return errorMessage2;
	  }
	  
      // Delete the existing Methods for the Java Files in the Database
	  try 
	  {
	     classMetCon.deleteClassMethod(projectId);
	  } 
	  catch (Exception e4) 
	  {
	     errorMessage2 = e4.getMessage();
		 return errorMessage2;
	  }
	  
 	  //System.out.println("This Project Name " + frlCon.projectName + " has " + javaClasses.size() + " JAVA Files");
	  //System.out.println("");
	  
      // For every Input Class Loop 
      for(i = 0; i < javaClasses.size(); i++) 
	  {
	    	  
         // Obtain the filename
         fileName = javaClasses.get(i).getClassFileName();
         
    	 // Get the package name of the file
    	 packageName = javaClasses.get(i).getPackageName();

         // Get the class name of the file
         className = javaClasses.get(i).getClassName();
    	 
    	 //System.out.println("Obtaining the Methods of the Java File Number : " + i);
    	 s = i + 1;
    	 
    	 System.out.println("Getting the Start Lines from the Java File: " + s + " of " + javaClasses.size());
    	 
    	 /*
    	 if( packageName.contains("org.isf.accounting.gui") && 
    		 className.contains("PatientBillEdit"))
         {  
    	 System.out.println("Java File Name   : " + fileName);
    	 System.out.println("Package Name     : " + packageName);
    	 System.out.println("Class Name       : " + className);
         }
         */
    	 
    	 // Obtain the source directory of the current Java File
    	 try
    	 {
    	    idDir = obtainSourceDirectory(projectId, frlCon.projectInputDir, dirList, fileName); 
    	 }
    	 catch(Exception e5)
    	 {
     	    errorMessage2 = e5.getMessage(); 
     		return errorMessage2;   		 
    	 }
    	 
		 if(errorMessage2.isEmpty() == false)
		    return errorMessage2;
    	 
		 /*
    	 System.out.println("FileName : " + fileName);
    	 System.out.println("Id Dir   : " + idDir);
    	 */
         
         // Get the Initial List of the Methods and Constructors of the Java Files
		 try 
		 {
		    initialMethodsNames = getClassConstructorsMethods(packageName, className, projExcepList, frlCon, fileName);
		   
		 } 
		 catch (Exception e6) 
		 {
    	    errorMessage2 = e6.getMessage(); 
    		return errorMessage2;
		 }
		 
		 if(errorMessage2.isEmpty() == false)
		    return errorMessage2;

		 /*
		 if( packageName.contains("org.isf.accounting.gui") && 
	    	 className.contains("PatientBillEdit"))
		 {	 
		    System.out.println("Initially, this Java File has: " + initialMethodsNames.size() + " methods and constructors.");
		    System.out.println(Arrays.deepToString(initialMethodsNames.toArray()));
		 }
		 */
		
		 
		 // Get the Final List of the Methods and Constructors of the Java Files
    	 finalMethodsNames = (ArrayList<String>) initialMethodsNames.stream().distinct().collect(Collectors.toList());
    	 
         
    	 /*
    	 if( packageName.contains("org.isf.accounting.gui") && 
    	     className.contains("PatientBillEdit"))
    	 {	 
    	    System.out.println("Finally, this Java File has: " + finalMethodsNames.size() + " methods and constructors.");
    		System.out.println(Arrays.deepToString(finalMethodsNames.toArray()));
    	 }
    	 */
    	  
    	 // Initializing the variables
    	 fullMethodName   = "";
    	 shortMethodName  = "";
    	 
    	 /*
    	 System.out.println("**************************");
    	 System.out.println("Towards inserting into the Class Method table");
    	 */
         
    	 // For every Method and Constructor of this Java File
         for(j = 0; j < finalMethodsNames.size(); j++) 
   	     {
        	// Current Method 
            line       = finalMethodsNames.get(j);
       	    returnType = "";
            
        	// Getting the current method in this Java File
        	try
        	{
        	   method          = line.split(frlCon.objectOrientedDelimiter2);
        	   fullMethodName  = method[0];
        	   shortMethodName = method[1];
        	   returnType      = method[2];
        	   
        	   // Remove White Spaces at the beginning and at the end
        	   returnType     = returnType.trim();
        	   
          	   finalReturnType1 = "";
        	   finalReturnType2 = "";
        	   
        	   /*
        	   if(shortMethodName.contains("getJComboBoxPriceList"))
        	   {	   
        	      System.out.println("Method            : " + method);    
        	      System.out.println("Full Method Name  : " + fullMethodName);   
        	      System.out.println("Short Method Name : " + shortMethodName);   
        	      System.out.println("Return Type       : " + returnType); 
        	   }
        	   */
        	   
        	}
    	    catch (Exception e7) 
    	    {
       	       errorMessage1 = e7.getMessage(); 
       	       errorMessage2 = "Error XXXX: Occurred while getting the Sections of the Method: " + method + System.lineSeparator();
       		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
       		   return errorMessage2; 	
   		    }
   		    
        	
        	if(errorMessage2.isEmpty() == false)
     		   return errorMessage2;
        	   
        	
        	// Get the finalReturnType divided in two fields by white space
        	if(returnType.indexOf(' ') >= 0)
        	{		
        		
        	   try
        	   {
        	      parts = returnType.split("\\s+");
        	   
        	      finalReturnType1 = parts[0];
        	      finalReturnType2 = parts[1];
        	   }
    	       catch (Exception e8) 
    	       {
       	          errorMessage1 = e8.getMessage(); 
       		      errorMessage2 = "Error XXXX: Occurred while dividing in Sections the Return Type: " + returnType + System.lineSeparator();
       		      errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
       		      return errorMessage2; 	
   		       }
        	   
        	   /*
        	   if(shortMethodName.contains("getJComboBoxPriceList"))
           	   {
        		   System.out.println("Inside the Get Class Methods Start Lines method ...");
        		   System.out.println("=> Case 1 - PART A");
        		   System.out.println("Final Return Type 1 : " + finalReturnType1);
                   System.out.println("Final Return Type 2 : " + finalReturnType2);
           	   }
           	   */
        	
        	   // Validate the value of finalReturnType2
        	   if(finalReturnType2.equals(" "))
        	      finalReturnType2 = "";
        	   
        	   /*
        	   if(shortMethodName.contains("getJComboBoxPriceList"))
           	   {
        		   System.out.println("=> Case 1 - PART B");
       			   System.out.println("Return Type         : " + returnType);
          		   System.out.println("Final Return Type 1 : " + finalReturnType1);
                   System.out.println("Final Return Type 2 : " + finalReturnType2);
       			
           	   }
           	   */
        	   
        	}
        	else
        	{	
        	   //System.out.println("THE RETURN TYPE: " + returnType + " NO TIENE WHITE SPACES!");	
        	   finalReturnType1 = returnType; 
        	   finalReturnType2 = "";
        	   
        	   /*
        	   if(shortMethodName.contains("getJComboBoxPriceList"))
           	   {
        		System.out.println("=> Case 2 - PART B");
       			System.out.println("Return Type         : " + returnType);
       			System.out.println("Final Return Type 1 : " + finalReturnType1);
                System.out.println("Final Return Type 2 : " + finalReturnType2);
           	   }
           	   */
        	}
        	
        	/*
        	if(shortMethodName.contains("getJComboBoxPriceList"))
        	{
        	   System.out.println("=> AFTER THE CASES");
        		
        	   System.out.println("Final Return Type 1: " + finalReturnType1);
               System.out.println("Final Return Type 2: " + finalReturnType2);
               System.out.println("**************************");
        	}
        	*/
        	
    	    /************************************/
        	// Get the Method Start Line Numbers
        	/************************************/
            
        	try 
    	    {
    	       // Obtain the Method Start Line
    	       methodStartLineNumList = getMethodStartLineNumbers(projectId,	
	        		                                              fileName, 
	        		                                              fullMethodName, 
	        		                                              finalReturnType1,
	        		                                              frlCon);
		    } 
    	    catch (Exception e9) 
    	    {
       	       errorMessage1 = e9.getMessage(); 
       		   errorMessage2 = "Error XXXX: Occurred while getting the existing Start Line Numbers of the Methods from the Project." + System.lineSeparator();
       		   errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
       		   return errorMessage2; 	
		    }
        	
        	/*
        	if(shortMethodName.contains("getJComboBoxPriceList"))
        	{
          	   System.out.println("YA PARA INSERTAR EN LA BASE DE DATOS");
        	}
        	*/
		    
    	    if( methodStartLineNumList.isEmpty() == false )
    	    {	
	           // For every Start Line Number Loop 
	           for(k = 0; k < methodStartLineNumList.size(); k++) 	   
	     	   {
	              //lineNumber = methodStartLineNumbers.get(k);
	              lineNumber      = methodStartLineNumList.get(k).getStartLineNumber();
	              paramMethodName = methodStartLineNumList.get(k).getParameterMethodName();
	              
	              /*
	              if(shortMethodName.contains("getJComboBoxPriceList"))
	          	  {
	            	  System.out.println("Line Number           : " + lineNumber);
	            	  System.out.println("Parameter Method Name : " + paramMethodName);
	            	  System.out.println("Package Name          : " + packageName);
	            	  System.out.println("Class Name            : " + className);
	            	  System.out.println("Full Method Name      : " + fullMethodName);
	            	  System.out.println("Short Method Name     : " + shortMethodName);
	            	  System.out.println("Final Return Type 1   : " + finalReturnType1);
	            	  System.out.println("Final Return Type 2   : " + finalReturnType2);
	          	  }
	          	  */
	              
	              // Insert a record into the class_method table
	        	  try 
	        	  {

	        		 classMethod = new ClassMethod(projectId, packageName, className, 
	        				                       fullMethodName, shortMethodName, 
	        				                       finalReturnType1, finalReturnType2, 
	        				                       fileName, lineNumber, 
	        				                       paramMethodName, idDir); 
	        		 
	        	     classMetCon.saveClassMethod(classMethod);   
	        	       		 
	        	  } 
	        	  catch (Exception e10) 
	        	  {
	        	     errorMessage2 = e10.getMessage(); 
	        		 return errorMessage2;
	        	  }
	        	  
	     	   }
    	    }
    	    
    	    
   	     }  
   	     
           
      } 
      
      
      // Disconnect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.disconnect();
	  } 
      catch (Exception e11) 
      {
  	     errorMessage2 = e11.getMessage(); 
  		 return errorMessage2; 
	  }
      
      return errorMessage2;
        
   }

   public String getClassMethodsEndLines(ArrayList<JavaFileData> javaFileNames, 
		                                 String databaseConfigFile, 
		                                 String replacePattern, 
		                                 int projectId)
   {
	   
      String javaFileName="", className="", errorMessage1="", errorMessage2="";
      String fileNameSec[];
      int id1=0, endLine1=0, size=0, k=0, i=0, j=0;
      
      ClassMethodController classMetCon = new ClassMethodController();
      ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
      
      // Connect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
 	     errorMessage2 = e1.getMessage(); 
 		 return errorMessage2; 
	  }
      
      // For every Input Class Loop 
      for(i = 0; i < javaFileNames.size(); i++) 
	  {
	    	  
         javaFileName = javaFileNames.get(i).getClassFileName();
         
    	 // Split the filename into sections
         try 
         {
    	    fileNameSec = javaFileName.split(Pattern.quote(File.separator));
         }
 	     catch (Exception e2) 
 	     {
    	    errorMessage1 = e2.getMessage(); 
    		errorMessage2 = "Error XXXX: Occurred while dividing in Sections the FileName: " + javaFileName + System.lineSeparator();
    		errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
    		return errorMessage2; 	
		 }
    	  
    	 // Get the class name of the file
    	 className = fileNameSec[fileNameSec.length - 1];
    	 className = className.replaceFirst(replacePattern, "");
    	 
         
    	 /*
     	 if(fileName.equals("UserLevel.java") || fileName.equals("Tranportation.java") || 
     		fileName.contains("Currency.java")  || fileName.equals("Status.java"))
     	 {	 
     		System.out.println("Inside getClassMethodsEndLines ...");
     		System.out.println("Input Class Name : " + inputClassName);
     		System.out.println("File Name        : " + fileName);
     		System.out.println("File Name Sec    : " + fileNameSec);
     		//System.out.println("Class Name       : " + className);
     	 }	
     	 */
    	 
         try 
         {
   		    classMetList = classMetCon.load(className, projectId);
   		    size         = classMetList.size() - 1;
               		
   		    // For every Method Loop 
            for(j = 0; j < classMetList.size(); j++) 
      	    {
               id1       = classMetList.get(j).getId();
               className = classMetList.get(j).getClassName();

               if(size > 1 && j < size)
           	   {	   
           	      k = j + 1;
           	   
           	      try
           	      {
                     endLine1 = classMetList.get(k).getStartLineNumber() - 1;
           	      }   
          	      catch (Exception e3) 
         	      {
          	    	  /*
                     System.out.println("Id 1      : " + id1);
                     System.out.println("ClassName : " + className);
                     System.out.println("Size      : " + size);
                     System.out.println("J         : " + j);
               	     System.out.println("K         : " + k); 
               	     System.out.println("EndLine1  : " + endLine1); 
               	     */
               	     
            	     errorMessage1 = e3.getMessage(); 
            		 errorMessage2 = "Error XXXX: Occurred while calculating the endLine " + endLine1 + " for the ClassName: " + className + System.lineSeparator();
            		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
            		 return errorMessage2; 	
        	      }
           	   }
           	   else   
           	      endLine1 = 0;
           	   
               /*
          	 if(className.equals("UserLevel.java") || className.equals("Tranportation.java") || 
             		className.contains("Currency.java")  || className.equals("Status.java"))
             	 {	 
             		 System.out.println("Class Name : " + className);
             		 System.out.println("Size       : " + classMetList.size());
                     System.out.println("Id 1       : " + id1);
                     System.out.println("K          : " + k);
                     System.out.println("J          : " + j);
                	 System.out.println("End Line   : " + endLine1);
             	 }	
             	 
             	 */
 
           	   // Update the EndLinesNumber field in the ClassMethod Table
           	   try 
           	   {
           	      classMetCon.updateEndLineNumbers(id1, endLine1, projectId);
           	   }
           	   catch (Exception e4)
           	   {
           		//System.out.println("ERROR OCURRED HERE!"); 
           	      errorMessage2 = e4.getMessage(); 
        		  return errorMessage2;  
           	   }
           	   
      	    }
   		    
   	     } 
         catch (Exception e5) 
         {
    	    errorMessage2 = e5.getMessage(); 
    		return errorMessage2; 
   	     }
             
      } 
      
      // Disconnect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.disconnect();
	  } 
      catch (Exception e6) 
      {
  	     errorMessage2 = e6.getMessage(); 
  		 return errorMessage2; 
	  }
      
      return errorMessage1;     
   }
   
   public int getMethodLastLineNumber(String fileName, String inputCharacter) throws IOException //8
   {
	   
      Path path = Paths.get(fileName);
      final Charset ENCODING = StandardCharsets.UTF_8;
      String lineStr = null;
      int lineNum=0, prevEnd=0, lastEnd=0;
      
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
	           
	           /*
		    	if(fileName.contains("TravelRequestController.java") || fileName.contains("EmployeeController.java")|| 
		 	       fileName.contains("UserController.java") || fileName.contains("TravelRequestFormEvent.java") ||
		 	       fileName.contains("UserDatabase.java") || fileName.contains("User.java") || fileName.contains("TravelRequest.java") || 
		 	       fileName.contains("TravelRequestDatabase.java") || fileName.contains("EmployeeDatabase.java")) 
		    	{
		    		System.out.println("Inside getMethodLastLineNumber ...");
		    		System.out.println("FileName : " + fileName);
		    		System.out.println("Line Str : " + lineStr);
		    		System.out.println("Line Num : " + lineNum);
		    		System.out.println("Prev End : " + prevEnd);
		    		System.out.println("Last End : " + lastEnd);
		    	}
		    	*/
	    	}
	    	 
	    	lineNum ++;
	    		 
	     }      
      }
	  
	  /*
	  if(prevEnd > 0)
	     prevEnd ++;
	  else
		  prevEnd = lastEnd + 1;
		  
	 */
	 lastEnd ++;
	  
      //return prevEnd;
	 return lastEnd;
   
   }
  
   public String getClassMethodsZeroEndLines(String propertiesFilePath,
		                                     String endMethod, int projectId) //9
   {
	   
      String fileName="", errorMessage1="";
      int id=0, endLineNum=0;    
      ClassMethodController classMetCon = new ClassMethodController();
      ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
      
      // Connect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.connect(propertiesFilePath);
	  } 
      catch (Exception e1) 
      {
  	    errorMessage1 = e1.getMessage(); 
  		return errorMessage1; 
	  }
      
      //System.out.println("Get Class Methods Zero End Lines Method ...");
      
      // Load the Class Methods  that have End Line Numbers Zero
      try 
      {
    	 // For every Input Class Loop  
   	     classMetList = classMetCon.loadZeroEndLineNumbers(projectId);
   		    
   	     for(int i = 0; i < classMetList.size(); i++) 
 	     {
   	    	// Get the information from the Database 
   	        id           = classMetList.get(i).getId();
   	        fileName     = classMetList.get(i).getFileName();
   	        //startLineNum = classMetList.get(i).getStartLineNumber();
   	        endLineNum   = getMethodLastLineNumber(fileName, endMethod); 
   	        
   	        // Update the EndLinesNumber field in the ClassMethod Table
        	classMetCon.updateEndLineNumbers(id, endLineNum, projectId);
   	        	        
   	     }
   	     
      }   
      catch (Exception e2) 
      {
 	     errorMessage1 = e2.getMessage(); 
 		 return errorMessage1; 
   	  }
             
      // Disconnect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.disconnect();
	  } 
      catch (Exception e3) 
      {
  	     errorMessage1 = e3.getMessage(); 
  		 return errorMessage1; 
	  }
      
      return errorMessage1;  
        
   }

   public String createDirectory(String projectOutputDir, String projectName) throws Exception //4
   {   
	  String fullFolderName="", errorMessage1="", errorMessage2=""; 
	  File newFolder;
	  
	  //Path path;
	  
	  // System.out.println("Inside Create Directory method ...");
	  
	  try
	  {
	     fullFolderName = projectOutputDir + File.separator + projectName; 
	     //fullFolderName = projectOutputDir + projectName; 
	     newFolder      = new File(fullFolderName);
	     
	     
	     //path = Paths.get(fullFolderName);
	     //Files.createDirectory(path);
	     
	     //System.out.println("Project Output Dir : " + projectOutputDir);
	     //System.out.println("Project Name       : " + projectName);
	     
	     //System.out.println("Full Folder Name   : " + fullFolderName);
	     //System.out.println("New Folder         : " + newFolder);
	  
	     if (!newFolder.exists() ) 
	     {	 
	        newFolder.mkdir();
	        //System.out.println("CREATING NEW DIRECTORY        : " + newFolder);
	     }   
	     //else
	    	 //System.out.println("NOT CREATING A NEW DIRECTORY : " + newFolder);
	    	 
	  }
	  catch (Exception e1) 
      {
	     errorMessage1 = e1.getMessage(); 
 		 errorMessage2 = "Error XXXX: Occurred while creating the folder: " + fullFolderName + System.lineSeparator();
 		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
 		 
 		 throw new Exception(errorMessage2); 
	  }
	   
      return fullFolderName;	        
   }

   public String getMethodFileName(String fileName, String textFileExtDelimiter) //3
   {
      String methodFileName = null, cStr = null;
      int c = 0;
      File file;
      
      file = new File(fileName + textFileExtDelimiter);

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
   
   public String getFileMethodBody(String projectName, String outputDir,
		                           String packageName, String className, 
		                           String shortMethodName, String fileName, 
		                           int lineStart, int lineEnd, 
		                           FRLConfiguration frlCon) throws Exception
   {
		String subDirName="", createdDir="", fileTxtName="", fileMethodName1="",
			   fileMethodName2="", fileMethodName3="", line="", methodBody="", specialCase1="service",
			   specialCase2="help", errorMessage1="", errorMessage2="";
		
		int c=0;
		
		Writer fileWriter;
		BufferedReader fileReader;
		
		//System.out.println("Inside Get File Method Body Method...");
		
		
		// Create the Project Directory
		subDirName = outputDir;
		
		try
		{
		   createdDir = createDirectory(subDirName, projectName);
		}
		catch (Exception e1) 
		{
	 	   errorMessage1 = e1.getMessage(); 
	 	   throw new Exception(errorMessage1); 
		}
		
		/*
	    System.out.println("Sub Directory Name 1 : " + subDirName);
		System.out.println("Created Directory  1 : " + createdDir);
		*/
			
		
		// Create the Package Directory
		subDirName = createdDir;
		packageName = packageName.trim();
		
		// Validate if the package Name ends with the reserved words service or help
		// add a number one at the end to be able to create the Folder
		if(packageName.endsWith(specialCase1) || packageName.endsWith(specialCase2) ) 
	       packageName = packageName + "1";
		
		try
		{
		   createdDir = createDirectory(subDirName, packageName);
		}
		catch (Exception e2) 
		{
	 	   errorMessage1 = e2.getMessage(); 
	 	   throw new Exception(errorMessage1); 
		}
		
		/*
		System.out.println("Package Name         : " + packageName);
	    System.out.println("Sub Directory Name 2 : " + subDirName);
		System.out.println("Created Directory  2 : " + createdDir);
		*/
		
		// Create the Class Directory
		subDirName = createdDir;
		className  = className.trim();
		
		try
		{
		   createdDir = createDirectory(subDirName, className);
		}
		catch (Exception e3) 
		{
	 	   errorMessage1 = e3.getMessage(); 
	 	   throw new Exception(errorMessage1); 
		}
		
		/*
		System.out.println("Class Name           : " + className);
	    System.out.println("Sub Directory Name 3 : " + subDirName);
		System.out.println("Created Directory  3 : " + createdDir);
		*/
		
		subDirName      = createdDir;		
		fileTxtName     = shortMethodName;
		
		fileMethodName1 = subDirName +  File.separator + fileTxtName;
		
		//fileMethodName1 = getMethodFile(subDirName + fileTxtName, frlCon.textFileExtDelimiter);
		fileMethodName2 = getMethodFileName(fileMethodName1, frlCon.textFileExtDelimiter);
		
		fileMethodName3 = subDirName +  File.separator + fileMethodName2;
		
		/*
		System.out.println("File Txt Name       : " + fileTxtName);
		
		System.out.println("SubDirectory Name   : " + subDirName);
		System.out.println("File Method Name  1 : " + fileMethodName1);
		System.out.println("File Method Name  2 : " + fileMethodName2);
		System.out.println("File Method Name  3 : " + fileMethodName3);
		*/

		try 
		{
		   // Appends to a new text file 
		   //fileWriter = new FileWriter(subDirName + fileMethodName1, false);
		   fileWriter = new FileWriter(fileMethodName3, false);
		
		   try 
		   {
			   fileReader = new BufferedReader(new FileReader(fileName));
				
			   // Initialize variables
			   line       = "";
			   methodBody = "";
			   c          = 1;
				
			   while ((line = fileReader.readLine()) != null) 
			   {	 
			      if(c >= lineStart && c <=lineEnd)  
			         methodBody += line + System.lineSeparator();
			      
				  c ++;
			   }   
					
			   //System.out.println("Method Body : " + methodBody);
			   
			   // Write the content of the Method Body into a Text File
			   fileReader.close();
			   fileWriter.write(methodBody);
			   fileWriter.flush();
			   fileWriter.close();
				
		    } 
			catch (Exception e1) 
			{
		 	   errorMessage1 = e1.getMessage(); 
		 	   errorMessage2 = "Error XXXX: Occurred while loading the Java File: " + fileName + System.lineSeparator();
		 	   errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
		 	   
		 	   throw new Exception(errorMessage2); 
			}
		
		} 
		catch (IOException e2) 
		{
	 	   errorMessage1 = e2.getMessage(); 
	 	   errorMessage2 = "Error XXXX: Occurred while writing the Method File: " + fileMethodName2 + System.lineSeparator();
	 	   errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
	 	   
	 	   throw new Exception(errorMessage2); 
		} 

		
      return fileMethodName3;	
   }
   
   public String getClassMethodsBody(ArrayList<JavaFileData> javaFileNames, 
		                             FRLConfiguration frlCon, int projectId) //7
   {
	   
      String javaFileName=null, className="";
      String fileName="", packageName="", shortMethodName="";
      String fileMethodBody="", errorMessage1="", errorMessage2="";
      String fileNameSec[];
      int i=0, j=0, k=0, id=0, lineStart1=0, lineEnd1=0;
      ClassMethodController classMetCon = new ClassMethodController();
      ArrayList<ClassMethod> classMetList = new ArrayList<ClassMethod>(); 
      
      // Connect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.connect(frlCon.databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
 	     errorMessage2 = e1.getMessage(); 
 		 return errorMessage2; 
	  }
      
      // For every Input Class Loop 
      for(i = 0; i < javaFileNames.size(); i++) 
	  {
	    	  
         javaFileName = javaFileNames.get(i).getClassFileName();
         
         k = i + 1;
         
         System.out.println("Creating the Method Text Files from the Java File: " + k + " of " + javaFileNames.size());
         
    	 // Split the filename into sections
    	 fileNameSec = javaFileName.split(Pattern.quote(File.separator));
    	  
    	 // Get the class name of the file
    	 className = fileNameSec[fileNameSec.length - 1];
    	 className = className.replaceFirst(frlCon.replacePattern, "");
    	 
    	 /*
    	 System.out.println("Java File Name : " + javaFileName);
    	 System.out.println("File Name Sec  : " + fileNameSec);
    	 System.out.println("Class Name     : " + className);
    	 */
    	 
         try 
         {
   		    classMetList = classMetCon.load(className, projectId);
   		              
   		    // For every Method Loop 
            for(j = 0; j < classMetList.size(); j++) 
      	    {
               
               id   	        = classMetList.get(j).getId();
               packageName      = classMetList.get(j).getPackageName();
               shortMethodName  = classMetList.get(j).getShortMethodName();
               fileName         = classMetList.get(j).getFileName();
               lineStart1       = classMetList.get(j).getStartLineNumber();
               lineEnd1         = classMetList.get(j).getEndLineNumber();
               
               /*
               System.out.println("Id1                 : " + id1);
               System.out.println("Package Name 1      : " + packageName1);
               System.out.println("Short Method Name 1 : " + shortMethodName1);
               
               System.out.println("FileName 1          : " + fileName1);
               System.out.println("Line Start 1        : " + lineStart1);
               System.out.println("Line End 1          : " + lineEnd1);
               */
               
               try 
               {
                  fileMethodBody = getFileMethodBody(frlCon.projectName, frlCon.projectOutputDir, packageName, 
                                                     className, shortMethodName, fileName, 
                                                     lineStart1, lineEnd1, frlCon);
               }
               catch(Exception e2)
               {
           	     errorMessage1 = e2.getMessage(); 
         		 errorMessage2 = "Error XXXX: Occurred while loading the File Names of the Methods for the Project. ";
         		 errorMessage2 = errorMessage2 + "Error Message: "+ System.lineSeparator() + errorMessage1;
         		 return errorMessage2; 
               }
               
               //System.out.println("fileMethodBody1     : " + fileMethodBody1);
               
               try
               {
                  // Update the FileMethodBody into the Class Methods table
                  classMetCon.updateTextFileName(id, fileMethodBody, projectId);
               }
               catch (Exception e3) 
               {
         	      errorMessage2 = e3.getMessage();
                  return errorMessage2; 
         	   }
               
      	    }
   		    
   	     } 
         catch (Exception e4) 
         {
   		    errorMessage2 = e4.getMessage();
            return errorMessage2; 
   	     }
             
      } 
      
      // Disconnect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.disconnect();
	  } 
      catch (Exception e5) 
      {
  	     errorMessage2 = e5.getMessage(); 
  		 return errorMessage2; 
	  }
      
      return errorMessage2;
        
   }  
   
   public ArrayList<JavaFileData> getMissingJavaLibraries(ArrayList<String> initialJavaClasses, 
		                                                  FRLConfiguration frlCon,
		                                                  String missingLibFileName,
		                                                  String specialClassName) throws Exception
   {
      String fullClassName="", fileName="", className="", packageName="", line="",
    		 errorMessage1="", errorMessage2="";

	  String[] fileClassNameSec;
	  ArrayList<JavaFileData> finalJavaClasses; 
	  JavaFileData javaFileRecord;
	  FileWriter fstream;
	  BufferedWriter info;
	  
	  Class<?> newClass = null;
	  Method[] methodsNewClass;
	  Constructor<?>[] consNewClass = null;

	  boolean errorFlag=false;
      int i=0;
      
      finalJavaClasses = new ArrayList<JavaFileData>();

      // Create a new Text File for the missing Java Libraries

	  try 
	  {
		 fstream = new FileWriter(missingLibFileName);
		 info    = new BufferedWriter(fstream);
	  } 
      catch(Exception e1)
	  {
	     errorMessage1 = e1.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while writing in the FileName: " + missingLibFileName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "The Java Missing Libraries. Error Message: " + errorMessage1 + System.lineSeparator();

	     throw new Exception(errorMessage2);
      }
	  
	  
      // Loop for all the Java Files
      for(i = 0; i < initialJavaClasses.size(); i++) 
	  {
	    
         // Obtain the JAVA Filename
    	 fileName = initialJavaClasses.get(i);
    	  
    	 // Split the filename into sections
    	 try
    	 {
    	    fileClassNameSec = fileName.split(Pattern.quote(File.separator));
    	 }
 	     catch (Exception e2) 
 	     {
    	    errorMessage1 = e2.getMessage(); 
    		errorMessage2 = "Error XXXX: Occurred while dividing in Sections the FileName: " + fileName + System.lineSeparator();
    		errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
    		throw new Exception(errorMessage2);
		 }
    	  
    	 // Get the class name of the file
    	 className = fileClassNameSec[fileClassNameSec.length - 1];
    	 className = className.replaceFirst(frlCon.replacePattern, "");
    	  
    	 // Get the package name of the file
    	 try 
    	 {
		    packageName = getPackageNameJavaFile(fileName);
		 } 
    	 catch (Exception e3) 
    	 {
    	    errorMessage2 = e3.getMessage();
    	    throw new Exception(errorMessage2);
		 }
    	 
         // Assign a value to the fileClassName variable
         fullClassName = packageName + frlCon.objectOrientedDelimiter1 + className;
         
    	 if(fullClassName.equals(specialClassName) == false)
    	 {	 
            // Create a new class 
            try 
            {
               newClass = Class.forName(fullClassName);
               errorFlag = false;
            }
            catch(NoClassDefFoundError e4) 
            {
               errorMessage1 = e4.getMessage(); 
       	       errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + packageName + "." + className + System.lineSeparator();
       	       errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
       	       errorMessage2 = errorMessage2 + "Error Message: " + "The JAVA Library: "+ errorMessage1 + " was NOT found.";
       	       
       	       errorFlag = true;

            }
            catch (Exception e5) 
            {
               errorMessage1 = e5.getMessage(); 
       	       errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + packageName + "." + className + System.lineSeparator();
       	       errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
               errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
               
               errorFlag = true;
               
            }
            
            if(errorFlag == false)
            { 	
               // Obtain the methods of the New Class
               try 
               {
                  methodsNewClass = newClass.getDeclaredMethods();
                  errorFlag       = false;
                                  
               }
               catch(NoClassDefFoundError e6) 
               {
                  errorMessage1 = e6.getMessage(); 
       	          errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + packageName + "." + className + System.lineSeparator();
       	          errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
       	          errorMessage2 = errorMessage2 + "Error Message: " + "The JAVA Library: "+ errorMessage1 + " was NOT found.";
       	       
       	          errorFlag = true;

               }
               catch (Exception e7) 
               {
                  errorMessage1 = e7.getMessage(); 
       	          errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + packageName + "." + className + System.lineSeparator();
       	          errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
                  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
               
                  errorFlag = true;
               
               }
               
               // Obtain the constructors of the New Class
               if(errorFlag == false)
               {
                  // Obtain the methods of the new class
                  try 
                  {
                     consNewClass = newClass.getDeclaredConstructors();
                     errorFlag    = false; 
                       
                   }
                   catch(NoClassDefFoundError e8) 
                   {
                      errorMessage1 = e8.getMessage(); 
           	          errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + packageName + "." + className + System.lineSeparator();
           	          errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
           	          errorMessage2 = errorMessage2 + "Error Message: " + "The JAVA Library: "+ errorMessage1 + " was NOT found.";
           	       
           	          errorFlag = true;

                   }
                   catch (Exception e9) 
                   {
                      errorMessage1 = e9.getMessage(); 
           	          errorMessage2 = "Error XXXX: Occurred while trying to create a New Class " + packageName + "." + className + System.lineSeparator();
           	          errorMessage2 = errorMessage2 + "For the Java File: " + fileName + System.lineSeparator();
                      errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
                   
                      errorFlag = true;
                   
                   }
               }
               
            }
            
    	    // Validate if an error occurred while trying to create a new Class for this Java File
       	    if (errorFlag == false)
       	    {	 
           	    // Create a new Java File record
        	    javaFileRecord = new JavaFileData(className, packageName, fullClassName, fileName);
       	    	finalJavaClasses.add(javaFileRecord);
       	    } 
       	    else
       	    {
       	    	
       	       // Store the information of the Class that needs a library into a TextFile 
       		   try 
       		   {
       		      line = errorMessage2;
       		      info.write(line);
       		      info.newLine();
       		      
       		   } 
               catch(Exception e8)
       		   {
       	          errorMessage1 = e8.getMessage(); 
       	    	  errorMessage2 = "Error XXXX: Occurred while writing in the FileName: " + missingLibFileName + System.lineSeparator();
       	    	  errorMessage2 = errorMessage2 + "The Java Missing Libraries. Error Message: " + errorMessage1 + System.lineSeparator();

       	    	  throw new Exception(errorMessage2);
               }
            }
         }

     }

	 info.close(); 
	 fstream.close();
	 
     return finalJavaClasses;
	   
   }
   
   public int getNewMethodEndLine(String javaFile, int methodNamePos, String endMethod) throws Exception
   {
      String lineText="", errorMessage1="", errorMessage2="";
      int newMethodEndLine=0, lineNumber=0;
      boolean flag=false;
	  FileReader fileReader = null;
      
      try 
      {
         fileReader = new FileReader(javaFile);

         try (BufferedReader bufferedReader = new BufferedReader(fileReader)) 
         {
  		    while( ((lineText = bufferedReader.readLine()) != null) && flag == false ) 
  			{
  			   lineNumber++;
  			    
  			   /*
  			   if(lineNumber == methodNamePos)
  			   {
  				   
  				  System.out.println("Line where the METHOD NAME is present!");
  			      
  			      System.out.println("Line Text   : " + lineText);
			      System.out.println("Line Number : " + lineNumber);
			      
  			   }
  			   */
  			    
  			   if(lineNumber > methodNamePos)
  			   {	
  			      // Validate if the current line is empty or contains the symbol: "}"
  			      // In case it is affirmative then assign the new Method End Line Number
  			      if (lineText.isEmpty() || lineText.contains(endMethod))
  			      {	   
  			         newMethodEndLine = lineNumber;
  			         
  			         flag = true;
  			          
  			         /*System.out.println("Line AFTER the METHOD NAME is present!");
  			         
  			         System.out.println("New Method End Line Text   : " + lineText);
  			         System.out.println("New Method End Line Number : " + lineNumber);
  			         System.out.println("Flag                       : " + flag);
  			         */
  			         
  			      }
  			    	
  			   } 	
  			    	
  			}
  			
  			bufferedReader.close();
  		 }  
         catch(Exception e1) 
         {
            errorMessage1 = e1.getMessage(); 
 	    	errorMessage2 = "Error XXXX: Occurred while loading the LINES in the JavaFile: " + javaFile + System.lineSeparator();
 	    	errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();

 	    	throw new Exception(errorMessage2);
         }
          
         fileReader.close();

      }
      catch(Exception e2) 
      {
         errorMessage1 = e2.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while OPENING the JavaFile: " + javaFile + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();

	     throw new Exception(errorMessage2);            
      }      
      
      return newMethodEndLine;
   }
   
   public int obtainNewMethodStartEndLines(String javaFileName, 
		                                   String packageName,
		                                   String className1, 
		                                   String methodName1,
		                                   int methodStartLine) throws Exception
   {
      ArrayList<Integer> lineNumbers1 = new ArrayList<Integer>();
      String errorMessage1="", errorMessage2="", 
    		 lineText1=null, lineText2="", methodName2="",
    		 inst1="@modifying", inst2="@transactional", inst3="eventlistener",
    		 inst4="autowired", inst5="@override", inst6="deprecated", 
    		 inst7="@query";
      int blankPos=0, queryPos=0,
    	  newMethodStartLine=0, lineNumber=0,
    	  methodMinRange=3, methodMaxRange=6, methodCurrentRange=0;
      
      boolean methodFound=false, queryFound1=false, queryFound2=false, 
    		  otherFound=false;
	  Path path;
  	  final Charset ENCODING = StandardCharsets.UTF_8;   
  	  
	  path = Paths.get(javaFileName);
	  
	  // Initialize variables 

	 methodName2     = methodName1.toLowerCase();

	 blankPos          = 0;
	 queryPos          = 0;
		 
	 methodFound       = false;
	 queryFound1       = false;
	 queryFound2       = false;
	 otherFound        = false;
		 
	 newMethodStartLine = 0;
		 
	 lineNumber         = 0; 
		 
  	  try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
  	  {
  		    		     
  		 try 
  		 {
  			lineText1=null;
 			while ( ((lineText1 = reader.readLine()) != null && 
 					  methodFound == false) ) 
			{

			   lineText2 = lineText1;
			   lineText2 = lineText2.toLowerCase();
			   lineText2 = lineText2.trim();
			   
			   lineNumber++;

			   methodCurrentRange = methodStartLine - lineNumber;
			   

			   if (lineText2.contains(methodName2) && 
			       lineNumber == methodStartLine) 
			   {		
			            //methodNamePos = lineNumber;
			            methodFound   = true;
			            
			            /*
			            System.out.println("Line Text    : " + lineText2);
			            System.out.println("Line Number  : " + lineNumber);
			            System.out.println("METHOD FOUND : " + methodNamePos);
			            */
			   }	
			   else
			      // Instructions: @Modifying, @Transactional, 
			     // @EventListener @AutoWired @Override @Deprecated
			     if ( (lineText2.contains(inst1)  || lineText2.contains(inst2) || 
			    	   lineText2.contains(inst3)  || lineText2.contains(inst4) ||
			    	   lineText2.contains(inst5)  || lineText2.contains(inst6)) &&   
			    	   (methodCurrentRange <= methodMinRange))
			     {		
			               lineNumbers1.add(lineNumber);
			    		   otherFound = true;
				 }
			     else
			        // Instruction: @Query
			        if ( lineText2.contains(inst7))
			        {
			           //System.out.println("LINE CONTAINS @QUERY: "+lineNumber);
			          if( methodCurrentRange <= methodMinRange ) 
			          {
			             queryPos    = lineNumber;
				         queryFound1 = true;
				        	     //System.out.println("QUERY POS CASO 1: "+queryPos);
			          }
			          else 
			             if( (methodCurrentRange >=  methodMinRange) && 
			        		 (methodCurrentRange <=  methodMaxRange) )   
			        	 {   		
			        		        queryPos    = lineNumber;
			        		        queryFound2 = true;
			        		        //System.out.println("QUERY POS CASO 2: "+queryPos);
						 }
			        	 else
			        	 {
			        	    queryPos    = 0;
			        		queryFound1 = false;
			        		queryFound2 = false;
			        			    //System.out.println("QUERY POS CASO 3: "+queryPos);
			        	 }			        		   
			         } 
					 else 
					    // Blank Line
					    if (lineText2.isEmpty())
					    {
					       blankPos = lineNumber;				    	     
					    }
					            
		    }
		 } 
  		 catch (Exception e1) 
  		 {
  		    errorMessage1 = e1.getMessage(); 
 	    	errorMessage2 = "Error XXXX: Occurred while loading the LINES of the Java File: " + javaFileName + System.lineSeparator();
 	    	errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
            throw new Exception(errorMessage2);
	     }      
  	  } 
  	  catch (Exception e2) 
  	  {
  	     errorMessage1 = e2.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while opening the Java File: " + javaFileName + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
	     throw new Exception(errorMessage2);
	  }
  	  
  	  /*********************************/
	  // Obtain the new method start line
	  /*********************************/
  	  if(methodFound == true)
  	  {	   
  		 // If the special instruction @query was present
  		 // Validate the Query Position and the Blank Position 
  		 // to insert  
	     if( (queryFound1 == true || queryFound2 == true) )
	        if( queryPos == (methodStartLine - 1) )
	  	       lineNumbers1.add(queryPos);	
	        else 
	           if(blankPos <= (queryPos-1) )
	              lineNumbers1.add(queryPos);	
	            else
	        	   if(otherFound == true)
	                  lineNumbers1.add(queryPos);	
	     
	     //System.out.println("Line Numbers 1: "+lineNumbers1);
	       
	     // Calculate the lineNumbers1 ArrayList is not null
  	     if( lineNumbers1 != null && lineNumbers1.size() > 0 )
  	     {	   
  	        // Order the List of Line Numbers 
  	        Collections.sort(lineNumbers1);
  	        
  	        // Obtain the minimum element 
  	        newMethodStartLine = Collections.min(lineNumbers1);
  	     }
  	     else
  	        //newMethodStartLine = 0;
  	        newMethodStartLine = methodStartLine;
  	     
  	   //System.out.println("New Method Start Line: "+newMethodStartLine);

  	   }
  	  
  	   /*
	   if( 
	      packageName.contains("org.isf.accounting.service") &&
	      newMethodStartLine > 0
		   )
	   {
		  System.out.println("");
	      System.out.println("Java File Name        : " + javaFileName);
		  System.out.println("Package Name          : " + packageName);
		  System.out.println("Class Name            : " + className1);
		  System.out.println("Method Name           : " + methodName1);
		  System.out.println("Java File Lines       : " + javaFileLines);
		  System.out.println("Method Start Line     : " + methodNamePos);
		  System.out.println("Method Found Flag     : " + methodFound);
		  System.out.println("Query Found  Flag 1   : " + queryFound1);
		  System.out.println("Query Found  Flag 2   : " + queryFound2);
		 
		  System.out.println("Query Pos             : " + queryPos);
		  System.out.println("Blank Pos             : " + blankPos);
		  
		  System.out.println("Line Numbers 1        : " + lineNumbers1);
		  System.out.println("Line Numbers 2        : " + lineNumbers2);
		  System.out.println("New Start Line Number : " + newMethodStartLine);
		  System.out.println("New End Line Number   : " + newMethodEndLine);
		  

		} 	
		*/ 
          
  	  return newMethodStartLine;
   }
   
   public String getSpecialClassMethodsStartLines(FRLConfiguration frlCon, int projectId)
   {
      String errorMessage1="", fileName="", className="", 
    		 packageName="", fullMethodName="";
      int i=0, classMethodId=0, startLineNum1=0, startLineNum2=0, 
    		  startLineNum3=0;
      
      ClassMethodController classMetCon = new ClassMethodController();
      ArrayList<ClassMethod> javaFiles = new ArrayList<ClassMethod>(); 
      		
 	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 return errorMessage1;
	  }	
      
      // Read the Java Files from the Database
	  try 
	  {
         javaFiles = classMetCon.loadJavaFiles(projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 return errorMessage1;
	  }	
	  
	  // Loop to read the Java Files from the FRL Database
      for (i = 0; i < javaFiles.size(); i++) 
      { 		      
          fileName        = javaFiles.get(i).getFileName();
          classMethodId   = javaFiles.get(i).getId();
          
          packageName     = javaFiles.get(i).getPackageName(); 	
          className       = javaFiles.get(i).getClassName();
          fullMethodName = javaFiles.get(i).getFullMethodName();
          
          startLineNum1   = javaFiles.get(i).getStartLineNumber();
          
          // For the Short Method Name
          // Obtain the new Start Line Number and the new End Line Number
          
          /* Call a method that loads the Java File
             and changes the startLineNumber
             depending if there are special instructions */
          
          try
          {
        	  startLineNum2 = obtainNewMethodStartEndLines(fileName, 
            		                                       packageName,
            		                                       className, 
                                                           fullMethodName, 
                                                           startLineNum1);
          }
          catch(Exception e3)
          {
             errorMessage1 = e3.getMessage();
     	     return errorMessage1;
          }
          
          if(startLineNum2 > 0)
             startLineNum3 = startLineNum2;
          else
        	  startLineNum3 = startLineNum1;
                
        	 /*
        	 if(packageName.contains("org.isf.dicom.service") && className.contains("DicomIoOperationRepository"))
        	 {
        		 System.out.println("Method Name: " + shortMethodName);
        		 System.out.println("Current Start Line Number     : " + currentStartLineNumber);
        		 System.out.println("New     Start Line Number     : " + newStartLineNumber);
        		 System.out.println("Def     Start Line Number     : " + defStartLineNumber);
        	 }	
        	 */
        	 
         try
         {
            classMetCon.updateStartLineNumbers(projectId, classMethodId, startLineNum3);
         }
         catch(Exception e4)
         {
            errorMessage1 = e4.getMessage();
        	return errorMessage1;
         }
             
      } 
      
      // Disconnect to the Forensic Ready Logger Database
      try 
      {
	     classMetCon.disconnect();
	  } 
      catch (Exception e5) 
      {
  	     errorMessage1 = e5.getMessage(); 
  		 return errorMessage1; 
	  }
      
      return errorMessage1; 
   }  
   
   public JavaFileData getClassDataTypeInfo(int projectId, String fileName, String packageName, 
                                            String className, String fieldType1) throws Exception
   {
	  String errorMessage1="", errorMessage2="", fieldPackageName="", fieldClassName=""; 
	  Class<?> class1=null;
      JavaFileData jFDRecord = null;

      /*
      System.out.println("Inside the getClassDataTypeInfo Method ...");
      
      System.out.println("Field Type 1 : " + fieldType1);
      System.out.println("Package Name : " + packageName);
      System.out.println("Class Name   : " + className);
      System.out.println("1");
      */
      
      // Convert a String data type to a class data type 
	  try 
	  {
	     class1 = Class.forName(fieldType1);
	  }
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage(); 
		 errorMessage2 = "Error XXXX: Occurred while obtaining the Class for the Field: " + fieldType1 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Project Id: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Java File: " + fileName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Package Name: " + packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
		   		         
		 throw new Exception(errorMessage2);
	  }
	  
	  //System.out.println("1");
			          
	  // Get the Package Name
	  try
	  {
         fieldPackageName = class1.getPackageName();
	  }
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage(); 
		 errorMessage2 = "Error XXXX: Occurred while obtaining the Field Package Name from the Class: " + class1 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Field Package Name: " + fieldPackageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
		 throw new Exception(errorMessage2);
	  } 
			              
	  // Get the Class Name
	  try
	  {
         fieldClassName = class1.getSimpleName();
	  }
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage(); 
		 errorMessage2 = "Error XXXX: Occurred while obtaining the Field Class Name from the Class: " + class1 + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Field Class Name: " + fieldClassName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
		 throw new Exception(errorMessage2);
	  } 
		       
	  jFDRecord = new JavaFileData(fieldPackageName, fieldClassName);	   
	   
	  return jFDRecord;
	   
   }
   
   public JavaFileData getPrimClassDataTypeInfo(int projectId, String fileName, String packageName, 
		                                        String className, String fieldType, String defaultPackage,
		                                        boolean fieldPrimDataType, boolean fieldClassDataType,
		                                        boolean fieldArrayDataType) throws Exception
   {
      String primDataType="", fieldPackageName="", fieldClassName="", errorMessage1="";
      JavaFileData jFDRecord=null;
      
      // Initialize variables
      primDataType = fieldType.toLowerCase();
      
      /*
      System.out.println("Inside the getPrimClassDataTypeInfo Method ...");
      
      System.out.println("Field Type     : " + fieldType);
      System.out.println("Prim Data Type : " + primDataType);
      */
	     
      if(fieldPrimDataType == true || fieldArrayDataType == true)
      {	  

	     switch(primDataType) 
	     {
	        case "int":
  		       fieldPackageName = defaultPackage; 
	           fieldClassName = "Integer";
	           break;
	        case "char":
  		       fieldPackageName = defaultPackage; 
	           fieldClassName = "Character";
	           break;
	        case "float":
	           fieldPackageName = defaultPackage; 
		       fieldClassName = StringUtils.capitalize(primDataType);
		       break; 
	        case "boolean":
	           fieldPackageName = defaultPackage; 
		       fieldClassName = StringUtils.capitalize(primDataType);
		       break; 	
	        case "double":
	           fieldPackageName = defaultPackage; 
		       fieldClassName = StringUtils.capitalize(primDataType);
		       break; 
	        case "long":
               fieldPackageName = defaultPackage; 
		       fieldClassName = StringUtils.capitalize(primDataType);
		       break;  
	        case "byte":
	           fieldPackageName = defaultPackage; 
			   fieldClassName = StringUtils.capitalize(primDataType);
			   break;   
	        case "short":
		       fieldPackageName = defaultPackage; 
			   fieldClassName = StringUtils.capitalize(primDataType);
			   break; 
	        default: 
	           fieldPackageName = "";
	           fieldClassName   = "";
	           break;
         }
      }
      
      /*
      System.out.println("*** Flags *** ");
      System.out.println("Field Prim Data Type  : " + fieldPrimDataType);
      System.out.println("Field Array Data Type : " + fieldArrayDataType);
      System.out.println("Field Class Data Type : " + fieldClassDataType);
      System.out.println("Field Package Name    : " + fieldPackageName);
      System.out.println("Field Class Name      : " + fieldClassName);
      */
	  
      if( (fieldPrimDataType == true && fieldClassName.trim().isEmpty() == true)  ||
    	  (fieldArrayDataType == true && fieldClassName.trim().isEmpty() == true) ||
		  (fieldClassDataType == true) )
	  {
    	  //System.out.println("ENTRE A VALIDAR LA CLASE");
    	  
    	  try
 	      {
    	     jFDRecord = getClassDataTypeInfo(projectId, fileName, packageName, 
                                              className, fieldType);
 	         
 	         fieldPackageName = jFDRecord.getPackageName();
       	     fieldClassName   = jFDRecord.getClassName();
 	      }
 	      catch(Exception e1)
 	      {
 	         errorMessage1 = e1.getMessage(); 
 	         throw new Exception(errorMessage1);
 	      }
       
      }
      else
	     jFDRecord = new JavaFileData(fieldPackageName, fieldClassName);
	  
	  return jFDRecord;
   }
   
   public void getAttributesDataClass(int projectId, String packageName, String className, 
		                              String fileName, int ciId, ClassMethodController classMetCon,
		                              FRLConfiguration frlCon) throws Exception
   {
      String fieldName="", fieldType1="", fieldType2="", fieldType3="", fieldType4="", fieldPackageName="", fieldClassName="", 
    		 fieldDataType="", defaultPackage="java.lang", collectionPackage="java.util.Collection",
    		 exceptionField1="portId", exceptionField2="selectedDay", exceptionField3="days",
    		 arrayString="", fieldDefClassStr="", errorMessage1="", errorMessage2="";
	  Type fieldGenericType;

      int i=0, j=0, counterArrays=0, classInterfaceInt=0;
      boolean fieldPrimDataType=false, fieldArrayDataType=false, fieldEnumDataType=false, fieldClassDataType=false,
    		  classInterface=false, fieldCollection=false;
      String[] parts;
      Class<?> class1=null, fieldClassType1=null, fieldDefClass=null;
      List<Field> classFields;
      Field field = null;
      ClassAttribute classAttributeRecord;
      JavaFileData jFDRecord;
      
      // Get the Attributes of the Class
	  try 
	  {
	     class1         = Class.forName(packageName + "." + className);
	  	 classFields    = Lists.newArrayList(class1.getDeclaredFields());
	  	 classInterface = class1.isInterface();
	  	 
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage(); 
		 errorMessage2 = "Error XXXX: Occurred while obtaining a New Class from the Package Name: " + packageName + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
		 throw new Exception(errorMessage2);
	  }
	  
	  /*
	  if( packageName.equals("org.isf.patient.model") &&
		  className.equals("PatientProfilePhoto"))
	  {	  
	     System.out.println("*** INSIDE THE Get Attributes Data Class METHOD ***"); 
	     System.out.println("Package Name   : " + packageName);
	     System.out.println("Class Name     : " + className);
	     System.out.println("File Name      : " + fileName);
	     System.out.println("Class Info Id  : " + ciId);
	     
	     System.out.println("Interface        : " + classInterface);
	  
	     System.out.println("Attributes         : " + Arrays.deepToString(classFields.toArray()));
	     System.out.println("Count of Attributes: " + classFields.size());
	  }
	  */
	  
	  
	  if(classInterface == true)
	     classInterfaceInt = 1;
	  else
	     classInterfaceInt = 0;
	  
	  //System.out.println("Interface : " + classInterfaceInt);
	  
	  // Update the Interface Flag field in the Class Information table
	  try 
	  {	 	   
	     classMetCon.updateInterfaceFlag(projectId, ciId, classInterfaceInt);
      }
      catch(Exception e)
      {
         errorMessage1 = e.getMessage();
	     throw new Exception(errorMessage1);
      }
	  
      // Loop for all the fields
      for (i=0; i < classFields.size(); i++) 
      {
    	  
         try
         {
            // Current Field 
            field     = classFields.get(i); 
            fieldName = field.getName();
         }
         catch (Exception e2) 
 	     {
 	        errorMessage1 = e2.getMessage(); 
 		    errorMessage2 = "Error XXXX: Occurred while obtaining the information about the Field: " + field + System.lineSeparator();
 		    errorMessage2 = errorMessage2 + "Project Id: " + projectId + System.lineSeparator();
 		    errorMessage2 = errorMessage2 + "Java File: " + fileName + System.lineSeparator();
 		    errorMessage2 = errorMessage2 + "Package Name: " + packageName + System.lineSeparator();
 		    errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
 		    errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
 		    throw new Exception(errorMessage2);
 	     }

         //System.out.println("Field: " + field + " " + i + " of " + classFields.size());         
         
         // Initialize variables
         fieldPrimDataType  = false;
         fieldArrayDataType = false;
         fieldEnumDataType  = false;
         fieldClassDataType = false;
         fieldDefClass      = null;
         
         if( fieldName.equals(exceptionField1) == false && 
        	 fieldName.equals(exceptionField2) == false &&
        	 fieldName.equals(exceptionField3) == false)
         {
            // Obtain all the information about field
            try
            {
        	   // Obtain the general information about the field 

               fieldClassType1  = field.getType();
               fieldType1       = fieldClassType1.getCanonicalName();
               fieldType2       = field.getDeclaringClass().toString();
               fieldType2       = fieldType2.replace("class ","");
               fieldDefClass    = field.getType().getDeclaringClass();
               fieldGenericType = field.getGenericType();
               
               // Obtain the Flags about the field
               fieldPrimDataType  = fieldClassType1.isPrimitive();
               fieldArrayDataType = fieldClassType1.isArray();
               fieldEnumDataType  = fieldClassType1.isEnum();
               
               
               if(fieldType1.contains(collectionPackage) == true)
               {	   
                  fieldCollection = true;
                  fieldType3      = fieldGenericType.toString();
                  fieldType3      = fieldType3.replace(collectionPackage + frlCon.startClassDef, "");
                  fieldType3      = fieldType3.replace(frlCon.endClassDef, "");
               }
               else
               {	   
            	   fieldCollection = false;
            	   fieldType3 = "";
               }

               /*
               if(packageName.equals("org.isf.patient.model") &&
     		    className.equals("PatientProfilePhoto"))
               {	   
               System.out.println("*******************************");
           	   System.out.println("*** INFO ABOUT THE FIELD ***");
           	   System.out.println("*******************************");
               System.out.println("Field                     : " + field);
               System.out.println("Field Name                : " + fieldName);
               
               System.out.println("Field Class Type 1        : " + fieldClassType1);
               System.out.println("Field Type 1              : " + fieldType1);
               System.out.println("Field Type 2              : " + fieldType2);
               System.out.println("Field Type 3              : " + fieldType3);
               
               System.out.println("Field Declaring Class     : " + fieldDefClass);
               
               System.out.println("Field Generic Type        : " + fieldGenericType);
               
               System.out.println("Field Primitive Data Type : " + fieldPrimDataType);
               System.out.println("Field Array Data  Type    : " + fieldArrayDataType);
               System.out.println("Field Enum Data Type      : " + fieldEnumDataType);
               System.out.println("Field Collection          : " + fieldCollection);


               System.out.println("Field Declaring String : " + fieldDefClassStr);
               
               
               System.out.println("Field Actual Package          : " + field.getType().getPackageName());
               System.out.println("Field Actual Simple Name      : " + field.getType().getSimpleName());
               System.out.println("Field Actual Declaring Class  : " + field.getType().getDeclaringClass());
               
               System.out.println("Field Actual Assignalble Form : " + field.getType().isAssignableFrom(fieldClassType1));
               

               
               System.out.println("*******************************");
               
               System.out.println("File                   : " + fileName);
               System.out.println("Package Name           : " + packageName);
               System.out.println("Class Name             : " + className);
               System.out.println("*******************************");
               }
               */
               
         

            }
            catch (Exception e3) 
 	        {
 	           errorMessage1 = e3.getMessage(); 
 		       errorMessage2 = "Error XXXX: Occurred while obtaining the information about the Field: " + field + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Project Id: " + projectId + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Java File: " + fileName + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Package Name: " + packageName + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Class Name: " + className + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
 		       throw new Exception(errorMessage2);
 	        }
            
            if(fieldDefClass != null )
            {	   
               fieldDefClassStr = fieldDefClass.toString();
               fieldDefClassStr = fieldDefClassStr.replace("class ","");
               
               fieldType1 = fieldType2;
               fieldType1 = fieldType1.replace("class ","");
               
               //System.out.println("ENTRE A CORREGIR ESTE CASO: " + exceptionField2);
            }   
            
         
            // Validate if the Class has an Array Data Type
            if(fieldArrayDataType == false)
            {	
               // Validate the Primitive and Class Data Type which are mutually exclusive
               if(fieldPrimDataType == false)
               {	   
                  if(fieldClassType1.toString().contains("class") == true)	   
                     fieldClassDataType = true;
                  else
                     fieldClassDataType = false;    
               }
               else
                  fieldClassDataType = false;   
               
            } 
            else
            {	
               fieldClassDataType = false;
               fieldPrimDataType  = false;
            }
            

            /*
            if( packageName.equals("org.isf.patient.model") &&
          		  className.equals("PatientProfilePhoto"))
            { 	
            System.out.println("*** INITIAL RETRIEVING INFORMATION ***");
            System.out.println("Field Declaring String : " + fieldDefClassStr);
      		System.out.println("Field       Type 1     : " + fieldType1);
      		System.out.println("Field Class Data Type  : " + fieldClassDataType);    
      		
      		System.out.println("Field       Type 2     : " + fieldType2);
      		System.out.println("Field Prim Data Type   : " + fieldPrimDataType);
      		System.out.println("Field Array Data Type  : " + fieldArrayDataType);
      		System.out.println("Field Enum Data Type   : " + fieldEnumDataType);
      		System.out.println("Field Collection       : " + fieldCollection);
      		
            }   
            */   
            
            
            /***********************************/
            // Get the Attributes of the Class
            // Package Name and Class Name
            /***********************************/
       		 
       		// Initialize Values
       	    fieldPackageName = "";
       	    fieldClassName   = "";
         
            // Validate if the Attribute has a Array Data Type (CASE 1)
            if(fieldArrayDataType == false)
            {
        	   // Validate if the Attribute has an Primary or a Class Data Type (CASE 2)
         	   if(fieldPrimDataType == true || fieldClassDataType == true) 
         	   {
         	      try
         	      {
         	         jFDRecord = getPrimClassDataTypeInfo(projectId, fileName, packageName, 
                                                          className, fieldType1, defaultPackage,
                                                          fieldPrimDataType, fieldClassDataType, 
                                                          fieldArrayDataType); 
         	         
         	         fieldPackageName = jFDRecord.getPackageName();
               	     fieldClassName   = jFDRecord.getClassName();
         	      }
         	      catch(Exception e4)
         	      {
         	         errorMessage1 = e4.getMessage(); 
         	         throw new Exception(errorMessage1);
         	      }
         	      
         	      if(fieldPrimDataType == true || 
         	    	 fieldPackageName.contains(defaultPackage) == true)
         	         fieldDataType = "Primitive";
         	      else
         	      {	  
         	    	 if(fieldPackageName.contains("java") == false)
                        fieldDataType = "PersonalizedClass";
                        else   
                        	fieldDataType = "JavaClass";
         	      }
                        

         	   }  
         	   else
         	   {

         		  if(fieldCollection == true)
         		  {
         		     fieldType4 = fieldType3; 
         		  }
         		  else
         			 fieldType4 = fieldType1; 
         		  
            	  try
         	      {
            	     
            	     jFDRecord = getClassDataTypeInfo(projectId, fileName, packageName, 
                                                      className, fieldType4);
         	         
         	         fieldPackageName = jFDRecord.getPackageName();
               	     fieldClassName   = jFDRecord.getClassName();
         	      }
         	      catch(Exception e5)
         	      {
         	         errorMessage1 = e5.getMessage(); 
         	         throw new Exception(errorMessage1);
         	      }
            	  
            	  
            	  if(fieldCollection == true)
         		  {
       	             fieldDataType = "Collection";
      	             
              		 fieldClassName = fieldDataType + frlCon.startClassDef + fieldClassName + frlCon.endClassDef;            		  
         		  }
            	  else
            	  {	  
            	  
            	    if(fieldPackageName.contains(defaultPackage) == true)
                       fieldDataType = "Primitive";
            	    else
            	    {
            	       if(fieldPackageName.contains("java") == false)
                          fieldDataType = "PersonalizedClass";
            	       else
            	          fieldDataType = "JavaClass";
            	    }
            	    
            	  }
            	  
         	   }
         	
            }
            else
            {
               // Enum Data Type
               if(fieldEnumDataType == false)
               {	

            	  // Validate if the Attribute has an Primary or a Class Data Type (CASE 1) 
                  if(fieldArrayDataType == true && fieldType1.contains("[]"))
                  {
                     //System.out.println("ARRAY CASE!");
                     //System.out.println("CASE 1");
                    	 
                     parts         = fieldType1.split("\\[]"); 
                     counterArrays = fieldType1.split("\\[]", -1).length-1;
                     //counterParts  = parts.length;
                     fieldType1    = parts[0];
                     
                     
                     /*
                     if( packageName.equals("org.isf.patient.model") &&
                     		  className.equals("PatientProfilePhoto"))
                     { 	 
                     System.out.println("Counter Arrays : " + counterArrays);
                     //System.out.println("Counter Parts  : " + counterParts);
                     System.out.println("Field Type 2   : " + fieldType1);
                     }
                     */
                     
                     
                     // Getting the Data Type of the Field Type 2
                     try
            	     {
            	        jFDRecord = getPrimClassDataTypeInfo(projectId, fileName, packageName, 
                                                             className, fieldType1, defaultPackage,
                                                             fieldPrimDataType, fieldClassDataType,
                                                             fieldArrayDataType); 
            	         
            	         fieldPackageName = jFDRecord.getPackageName();
                  	     fieldClassName   = jFDRecord.getClassName();
            	     }
            	     catch(Exception e6)
            	     {
            	        errorMessage1 = e6.getMessage(); 
            	        throw new Exception(errorMessage1);
            	     }
                     
     	             arrayString = "";
   	               
             	     // Add the [] to the Class
     	             for (j = 0; j < counterArrays; j++) 
     	             {
     	                arrayString  = arrayString + "[]";
     	                //System.out.println("ENTRE AL CICLO");
     	             }

    	             fieldDataType = "Array";
    	             
     	             fieldClassName = fieldClassName + arrayString;
     	             
 
     	             
     	            /* 
     	            if( packageName.equals("org.isf.patient.model") &&
     	           		  className.equals("PatientProfilePhoto"))
     	            {	
               	      System.out.println("CASE 4");
               	      
     	             
     	             
                     System.out.println("Field Package Name : "  + fieldPackageName);
                     System.out.println("Field Class Name   : "  + fieldClassName);
                     System.out.println("Array String       : "  + arrayString);
                     System.out.println("Counter Array      s: " + counterArrays);
     	            }
     	            */
     	              
                  }	
                  
               }
               else
               { 
                  fieldDataType = "Enum";
                  
                  /*
                  if( packageName.equals("org.isf.patient.model") &&
                  		  className.equals("PatientProfilePhoto"))
             	      System.out.println("CASE 3");
             	      */
             	      
               }
            
            }
         
            
            /*if( packageName.equals("org.isf.accounting.gui.totals") &&
            		  className.equals("BalanceTotal"))
            if( packageName.equals("org.isf.patient.model") &&
            		  className.equals("PatientProfilePhoto"))
            {	
            System.out.println("PREVIO TO INSERT");
            
	        System.out.println("Field Package Name    : " + fieldPackageName);
	        System.out.println("Field Class Name      : " + fieldClassName);
	        System.out.println("Field To String       : " + field.toString());
	        System.out.println("Field       Name      : " + fieldName);
	        System.out.println("Field       Data Type : " + fieldDataType);
            }
            */
            
            
            // Create a new record
	        try
	        {
	           classAttributeRecord = new ClassAttribute(projectId, fieldPackageName, fieldClassName, 
	        		                                     field.toString(), fieldName, ciId, fieldDataType)  ;
	        }
	        catch (Exception e7) 
	        {
 	           errorMessage1 = e7.getMessage(); 
 		       errorMessage2 = "Error XXXX: Occurred while creating a new Attribute for the Project Id: " + projectId + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Package Name: " + fieldPackageName + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Class Name: " + fieldClassName + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Short Name: " + fieldName + System.lineSeparator();
 		       errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
 		       throw new Exception(errorMessage2);
            }
	     

	        // Insert the Attributes into the Database
	        try
	        {
	           classMetCon.saveClassAttribute(classAttributeRecord);
	        }
	        catch (Exception e8) 
	        {
	           errorMessage2 = e8.getMessage(); 
		       throw new Exception(errorMessage2);
            }
	     
         }  
         
      }	   
   }
   
   public int validateGuiClass(int projectId, ArrayList<String> guiList, String line1) throws Exception
   {
      String errorMessage1="", errorMessage2="", line2="", name="", reservedWord="import " , comparison="";
      int i=0, guiFlag=0;
      
      try
      {
    	 
    	 // Initialize Variables
    	 guiFlag=0;
    	 i=0;
    	 line2 = line1.toLowerCase();
    	 
    	 // Loop around all the GUI Libraries of this Project
         for(i = 0; i < guiList.size(); i++) 
	     {  
	        // Get the Name of the GUI
	        name = guiList.get(i);
	     
	        comparison = reservedWord + name;
	     
	        if(line2.contains(comparison) == true)
	        {	 
	           guiFlag = 1;
	           break;
	        }   
	     
	     }
      }
      catch (Exception e1) 
      {
         errorMessage1 = e1.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while validating the GUI libraries for the Project Id: " + projectId + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Line: " + line1 + System.lineSeparator();
	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
	     throw new Exception(errorMessage2);
      }
      
      return guiFlag;
	   
   }
      
   public String getClassDataTypeAttributes(ArrayList<JavaFileData> javaFiles, 
		                                    int projectId,
		                                    FRLConfiguration frlCon)
   {
      String errorMessage1="", errorMessage2="",
    		 value1="@service", value2="@repository", value3="@gui", value4="enum", value5="@none",
    		 fileName="", packageName="", className="", 
    		 classDataType="", currentLine="";
      
      /*value1="@table", */ /*value4="@transactional",*/
      /*value5="@component",value6="@embeddable",value7="@mappedsuperclass",*/
    		  ;     
	  boolean dbClassFlag=false, loopFlag=false, guiClassFlag=false, enumClassFlag=false;
	  int i=0,s=0, dbClassFlagInt=0, ciId=0, guiClassFlagInt=0, enumClassFlagInt=0;
	  FileReader fr = null;
	  BufferedReader br = null;	 
	  ClassMethodController classMetCon = new ClassMethodController();
	  ClassInformation classInformation;
	  ArrayList<String> guiList = new ArrayList<String>();
	  
	  //System.out.println("Inside the Get Class Data Type Attributes Method ... ");
	  
	  // Initialize Variables
	  guiList.clear();
	  
 	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage2 = e1.getMessage();
		 return errorMessage2;
	  }	
	  
 	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     guiList = classMetCon.loadGUIProject(projectId, frlCon.programmingLanguage);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage2 = e2.getMessage();
		 return errorMessage2;
	  }	
	  
	  // Disconnect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.disconnect();
      } 
	  catch (Exception e3) 
	  {
	     errorMessage2 = e3.getMessage(); 
	     return errorMessage2; 
	  } 	  
	  
	  // Looping around the array list

	  // For every Input Class Loop 
	  for(i = 0; i < javaFiles.size(); i++) 
	  {  

	     // Get the package name of the file
	     packageName = javaFiles.get(i).getPackageName();

	     // Get the class name of the file
	     className = javaFiles.get(i).getClassName();
	     
	     // Obtain the Filename
	     fileName = javaFiles.get(i).getClassFileName();
	    	 
	     // System.out.println("Obtaining the Methods of the Java File Number : " + i);
	     s = i + 1;
	    	 
	     System.out.println("");
	     System.out.println("Getting the Attributes from the Java File: " + s + " of " + javaFiles.size());

	  	 try 
	  	 {
	   	    fr = new FileReader(fileName);
	  	 } 
	  	 catch (Exception e4) 
	  	 {
	  	    errorMessage1 = e4.getMessage(); 
	  		errorMessage2 = "Error XXXX: Occurred while opening the Java File: " + fileName + System.lineSeparator();
	  		errorMessage2 = errorMessage2 + "In the Project Id: " + projectId + System.lineSeparator();
	  		errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
	  		return errorMessage2;
	  	 }
		 
	  	 // Initialize Variables
	  	 br = new BufferedReader(fr);

	  	 classDataType = "";
	  	 dbClassFlag   = false;
	  	 guiClassFlag  = false;
	  	 enumClassFlag = false;
	  	 loopFlag      = false;
	  	 
	  	 // Determine whether or not a Class performs Data Operations
	     try 
	     {
		    while( ((currentLine =  br.readLine() ) != null) && 
		    	   (loopFlag == false) )
			{  
			   
			   // Convert to Lowercase the Current Line
		       currentLine = currentLine.toLowerCase(); 
		       currentLine = currentLine.trim();
		       
		       //System.out.println("Current Line: " + currentLine);

		       // Validate if the Class performs Graphical User Operations
               if(guiClassFlag == false)
               {	   
		          try
		          {
		             guiClassFlagInt = validateGuiClass(projectId, guiList, currentLine);
		          }
		          catch(Exception e5)
		          {
		             errorMessage2 = e5.getMessage();
		 		     return errorMessage2;
		          }
		          
		          if(guiClassFlagInt == 1)
		             guiClassFlag = true;
		        	  
               }
		          		       
		       // Validate if this Class performs Data Operations
		       if(dbClassFlag == false)
		       {	   
			      if (currentLine.startsWith(value1))
				  {	  
				     classDataType = value1; 
					 dbClassFlag   = true;
				  }   
			      else
				     if (currentLine.startsWith(value2))
					 {	  
				        classDataType = value2; 
						dbClassFlag   = true;
					 }
		       }
		       
		       // Validate if this class is an Enum
		       if (currentLine.contains(value4))
		       {
		          enumClassFlag = true; 
		       }
			      
				      /*if (currentLine.startsWith(value1))
				      {	  
				         classDataType = value1; 
				         dbClassFlag   = true;
				      }   
				      else*/
			         
				        /*else
					       if (currentLine.startsWith(value4))
						   {	  
						      classDataType = value4; 
						      dbClassFlag   = true;
						   }   
					       else
						      /*if (currentLine.startsWith(value5))
						      {	  
						         classDataType = value5; 
							     dbClassFlag   = true;
						      }
						      else
							      if (currentLine.startsWith(value6))
							      {	  
							         classDataType = value6; 
								     dbClassFlag   = true;
							      } 
							      else
							    	  if (currentLine.startsWith(value7))
								      {	  
								         classDataType = value7; 
									     dbClassFlag   = true;
								      }*/
		       

               /*
		       System.out.println("GUI Class Flag      : " + guiClassFlag);	
		       System.out.println("GUI Class Flag Int  : " + guiClassFlagInt);
		       System.out.println("DB Class Flag       : " + dbClassFlag);	
		       System.out.println("Class Data Type     : " + classDataType);
		       */
		         
			      
		       // Validate if we reach the point where the class or the interface is declared
		       // to break the loop
			   if ( (currentLine.contains("public") || currentLine.contains("private") ||
			         currentLine.contains("protected")) &&
			        (currentLine.contains("class") || currentLine.contains("interface")) 
			       )
			   {
			      loopFlag   = true;

			   }
			   
			 }
		    
			 br.close();
			 fr.close();
			 
			 
		  } 
	      catch (Exception e6) 
	      {
	         errorMessage1 = e6.getMessage(); 
		  	 errorMessage2 = "Error XXXX: Occurred while loading the Java File: " + fileName + System.lineSeparator();
		  	 errorMessage2 = errorMessage2 + "In the Project Id: " + projectId + System.lineSeparator();
		  	 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1 + System.lineSeparator();
		  	 return errorMessage2;
		  }
	     
		  // Assign the Integer Class Data Type depending on whether or not
		  // it is a class that performs data operations
		  /*if(dbClassFlag == true)
		     dbClassFlagInt = 1;
		  else
		     dbClassFlagInt = 0;
		     */
		  
		  // Check the flags
	      /*System.out.println("*** VALUES AFTER THE LOOOP ***");
		  
	      System.out.println("DB Class : " + dbClassFlag);
		  System.out.println("Gui Class: " + guiClassFlag);
		  */
			 
		  if(dbClassFlag == false)
		  {
		     dbClassFlagInt = 0;
			 
		     if(guiClassFlag == true)
		     {	  
		        guiClassFlagInt = 1; 
		        classDataType   = value3;
		     }   
			 else	
			 {	 
		        guiClassFlagInt = 0;
			    classDataType   = value5;
			 }   
				
		  }
		  else
		  {	 
		     dbClassFlagInt  = 1;
		     guiClassFlagInt = 0;
		     guiClassFlag    = false;
		  }
		  
		  if(enumClassFlag == true)
		     enumClassFlagInt = 1;
		  else
		     enumClassFlagInt = 0;
		  
		  /*
		  System.out.println("*** VALUES BEFORE INSERTING ***");
		  System.out.println("DB Class        : " + dbClassFlag);
		  System.out.println("Gui Class       : " + guiClassFlag);
		  System.out.println("Class Data Type : " + classDataType);
		  */
		  
		  // Create a new record
	      classInformation = new ClassInformation(projectId, packageName, className, fileName,
	    		                                  dbClassFlagInt, classDataType, 0, 
	    		                                  guiClassFlagInt, enumClassFlagInt);
	      
	 	  // Connect to the Forensic Ready Logger Database
		  try 
		  {
		     classMetCon.connect(frlCon.databaseConfigFile);
		  } 
		  catch (Exception e7) 
		  {
		     errorMessage2 = e7.getMessage();
			 return errorMessage2;
		  }	
	      
		  // Insert the new Class Information record
		  try 
		  {
		     ciId = classMetCon.saveClassInformation(classInformation);
		  } 
		  catch (Exception e8) 
		  {
		     errorMessage2 = e8.getMessage();
			 return errorMessage2;
		  }
				  
	      // Update the Class Identifier in the Class_Method table
		  try 
		  {
		     classMetCon.updateClassInformation(projectId, packageName, className, ciId);
		  } 
		  catch (Exception e9) 
		  {
		     errorMessage2 = e9.getMessage();
			 return errorMessage2;
		  }
		  
	      // Verify if the class performs data operations
	      // get its attributes 
	      try
	      {
	         getAttributesDataClass(projectId,
	            		            packageName, 
	            		            className, 
	        		                fileName, 
	        		                ciId,
	        		                classMetCon,
	        		                frlCon);
	      } 
	      catch (Exception e10) 
		  {
		     errorMessage2 = e10.getMessage(); 
		     return errorMessage2;
		  }
	      
	     // Disconnect to the Forensic Ready Logger Database
	     try 
	     {
		    classMetCon.disconnect();
		 } 
	     catch (Exception e11) 
	     {
	  	    errorMessage2 = e11.getMessage(); 
	  		return errorMessage2; 
		 } 
	         
	  }

      return errorMessage2;   
   }
   
   public ArrayList<ClassMethod> loadMissingFullMethodParameterNames(int projectId, FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="";
	  ArrayList<ClassMethod> paramMethodList = new ArrayList<ClassMethod>();
	  ClassMethodController classMetCon = new ClassMethodController();
	  
	  // Initialize ArrayList
	  paramMethodList.clear();
	  
	   
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }	


	  // Read the Method Parameter Names that are not complete
	  try 
	  {
	     paramMethodList = classMetCon.loadIncompleteParameterMethods(projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception(errorMessage1);
	  }	

	  // Disconnect to the Forensic Ready Logger Database
	  try 
	  {
		     classMetCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 throw new Exception(errorMessage1);
	  }	

	  return paramMethodList;
	  
	   
   }
   
   public String getFinalParameterMethodName(int projectId, 
                                             String javaFileName, 
		                                     String initialMethodParamName,
		                                     int startLineNumber,
		                                     FRLConfiguration frlCon) throws Exception
   {
      
	  String lineText1="", lineText2="", endMethod1="", endMethod2="", finalMethodParamName="", 
			 errorMessage1="", errorMessage2="";
      int lineNum=0;
      boolean paramMethodFound=false, paramMethodFlag=false;
	  Path path;
      final Charset ENCODING = StandardCharsets.UTF_8;
      
	  // Initialize Variables
	  path = Paths.get(javaFileName);
	  initialMethodParamName = initialMethodParamName.toLowerCase();
	  endMethod1 = frlCon.startMethod;
	  endMethod2 = frlCon.endParameters + frlCon.endStatementDelimiter;
	   
	  /*
	  System.out.println("INSIDE THE Get Final Parameter MethodName METHOD ...");
	  System.out.println("Java File Name                : " + javaFileName);
	  System.out.println("Initial Method Parameter Name : " + initialMethodParamName);
	  System.out.println("Start Line Number             : " + startLineNumber);
	  */
	  
	  try ( BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	  {  
	     lineText1 = null;
		 lineText2 = "";
		 lineNum = 0;
		 finalMethodParamName = "";
		 
		 paramMethodFound = false;
		 paramMethodFlag = false;
		     
		  while ( (lineText1 = reader.readLine()) != null &&
				  paramMethodFlag == false
				) 
		  {
		     lineNum++;	  
		    	
			 lineText2 = lineText1;
			 lineText2 = lineText2.trim();
			 lineText2 = lineText2.toLowerCase();

				
			 // Validate if the method is present
			 if(lineNum == startLineNumber)
			 {	 
				/*System.out.println("BEGINS"); 
			    System.out.println("Line Numb               : " + lineNum);
				System.out.println("Line Text 2             : " + lineText2);
				*/
				    
			    if ( lineText2.contains(initialMethodParamName) ) 
			    {
			       paramMethodFound = true;
			       //finalMethodParamName = finalMethodParamName + lineText2;
		        }  
			    
                /*
			    System.out.println("Parameter Method Found  : " + paramMethodFound);
			    System.out.println("Final MethodParamName   : " + finalMethodParamName);
			    */
			 }   
			 
			 if(paramMethodFound == true)
			 {

			    finalMethodParamName = finalMethodParamName + lineText1.trim();
			    			    
				if ( lineText2.contains(endMethod1) ) 
				{
			       paramMethodFlag = true;
				}
				else
			       if ( lineText2.contains(endMethod2) ) 
				   {
			          paramMethodFlag = true;
				   }
				
			 }

		  }      
	  }
	  catch (Exception e1) 
	  {
		     errorMessage1 = e1.getMessage(); 
			 errorMessage2 = "Error XXXX: Occurred while loading the Java File: " + javaFileName + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "Method Parameter Name: " + initialMethodParamName + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "Start Line Number : " + startLineNumber + System.lineSeparator();
			 errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
			 throw new Exception(errorMessage2); 	
      }
	  
	  //System.out.println("FINAL METHOD PARAMETER NAME   : " + finalMethodParamName);
	  
	  return finalMethodParamName;
	  
   }
 

   public void updateMethodParameterName(int projectId, int classMethodId, String parameterMethodName, FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="";
	  ClassMethodController classMetCon = new ClassMethodController();
	   
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception(errorMessage1);
	  }	


	  // Update the Method Parameter Name
	  try 
	  {
	     classMetCon.updateParameterMethodName(projectId, classMethodId, parameterMethodName);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception(errorMessage1);
	  }	

	  // Disconnect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.disconnect();
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage();
		 throw new Exception(errorMessage1);
	  }	
  
   }  
   
   public String getFinalParameterMethodNames(int projectId, FRLConfiguration frlCon) 
   {

	  String javaFileName="", initialParamMethodName="", finalParamMethodName="", 
			 errorMessage1="", errorMessage2="";
	  int i=0, id=0, startLineNumber=0;
	  ArrayList<ClassMethod> paramMethodList = new ArrayList<ClassMethod>();
	  
	  // Initialize the ArrayLists
	  paramMethodList.clear();
      
      // Load the List of Methods that does not have a Complete Full parameter Name
	  try 
	  {
	     paramMethodList = loadMissingFullMethodParameterNames(projectId, frlCon);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     return errorMessage1;
	  }
	  
	  //System.out.println("Parameter Method List Size: " + paramMethodList.size());
	  
	  // Validate the Method Parameter List
	  if(paramMethodList.isEmpty() == false)
	  {
		  for (i = 0; i < paramMethodList.size(); i++) 
		  {
		     id                     = paramMethodList.get(i).getId();
			 //fullMethodName         = paramMethodList.get(i).getFullMethodName();
			 initialParamMethodName = paramMethodList.get(i).getParameterMethodName();
			 startLineNumber        = paramMethodList.get(i).getStartLineNumber();
			 javaFileName           = paramMethodList.get(i).getFileName();
			 
			 /*
			 System.out.println("");
			 System.out.println("Counter i: " + i);
			 System.out.println("Java File Name : " + System.lineSeparator() + javaFileName);
			 System.out.println("Id                            : " + id);
			 System.out.println("Full Method Name              : " + fullMethodName);
			 System.out.println("Initial Parameter Method Name : " + initialParamMethodName);
			 System.out.println("Start Line Number             : " + startLineNumber);
			 */

			 try
			 {
		        finalParamMethodName = getFinalParameterMethodName(projectId, javaFileName, 
		        		                                           initialParamMethodName, startLineNumber, frlCon);
			 }
			 catch (Exception e2) 
			 {
			    errorMessage1 = e2.getMessage();
			    return errorMessage1;
			 }
			 
			 //System.out.println("Final Parameter Method Name : " + System.lineSeparator() + finalParamMethodName);
			 
			 try
			 {
			    updateMethodParameterName(projectId, id, finalParamMethodName, frlCon);
			 }
			 catch (Exception e3) 
			 {
			    errorMessage1 = e3.getMessage();
			    return errorMessage1;
			 }
			    			 
		  }

	  }

      return errorMessage2;

   }   
   
   public List<SourceDirectory> getListSourceDirectories(int projectId, String projectDir, 
		                                                 String kindDir, String databaseConfigFile) throws Exception 
   {
      File folder = new File(projectDir);
      String fileName="", nameDir="", pathDir="", errorMessage1="", errorMessage2="";
	  SourceDirectory sourceDirRecord; 
	  List<SourceDirectory> dirList = new ArrayList<SourceDirectory>();
	  
      try
      {
         for (File file : folder.listFiles()) 
         {
            if (file.isDirectory()) 
            {
               fileName = file.getAbsolutePath().toString();

    	       if (fileName.contains(kindDir))
               {

                  nameDir = file.getName().toString();
                  pathDir = file.getAbsolutePath().toString();
                  
                  nameDir = nameDir.trim();
                  pathDir = pathDir.trim();
                  
                  sourceDirRecord = new SourceDirectory(projectId, nameDir, pathDir);
                  
                  dirList.add(sourceDirRecord);
               }
               else
               {
                  dirList.addAll(getListSourceDirectories(projectId, file.getAbsolutePath(), kindDir, databaseConfigFile));
               }
            }
         }
      }   
      catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage(); 
	     errorMessage2 = "Error XXXX: Occurred while getting the Source Directories from: " + projectDir + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "For the Project Identifier: " + projectId + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
		 throw new Exception(errorMessage2); 	
      }
      
      return dirList;
   }
   
   public List<SourceDirectory> getSourceDirectories(FRLConfiguration frlCon, int projectId) throws Exception
   {
      String errorMessage1="", nameDir="", pathDir1="", pathDir2="";
      int i=0, idDir=0;
      SourceDirectory sourceDirRecord; 
      List<SourceDirectory> dirList1 = new ArrayList<SourceDirectory>();
      List<SourceDirectory> dirList2 = new ArrayList<SourceDirectory>();
	  ClassMethodController classMetCon = new ClassMethodController();
	  
	  // Connect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.connect(frlCon.databaseConfigFile);
	  } 
	  catch (Exception e1) 
	  {
	     errorMessage1 = e1.getMessage();
		 throw new Exception (errorMessage1);
	  }	
	  
	  // Delete the previous source directories for this project
	  try 
	  {
	     classMetCon.deleteSourceDirectories(projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 throw new Exception (errorMessage1);
	  }	
      
	  try 
	  {
	     dirList1 = getListSourceDirectories(projectId, frlCon.projectInputDir, "/src/", frlCon.databaseConfigFile);
	  } 
	  catch (Exception e3) 
	  {
	     errorMessage1 = e3.getMessage(); 
	     throw new Exception();
	  }
   
	  // Insert the records into the Database
	  for(i = 0; i < dirList1.size(); i++) 
	  {
	     
	     idDir    = dirList1.get(i).getIdDir();
	     nameDir  = dirList1.get(i).getNameDir();
	     pathDir1 = dirList1.get(i).getPathDir();
	     
	     pathDir2 = pathDir1.replace(frlCon.projectInputDir ,"");
	     
	     sourceDirRecord = new SourceDirectory(projectId, idDir, nameDir, pathDir2);
	     
	     dirList2.add(sourceDirRecord);
	     
         // Insert this source Directory in the Database
   	     try 
   	     {
   	        classMetCon.saveSourceDirectory(sourceDirRecord);
   	     } 
   	     catch (Exception e4) 
   	     {
   	        errorMessage1 = e4.getMessage();
   		    throw new Exception (errorMessage1);
   	     }	

	  }
	  
	  // Disconnect to the Forensic Ready Logger Database
	  try 
	  {
	     classMetCon.disconnect();
	  } 
	  catch (Exception  e5) 
	  {
	     errorMessage1 = e5.getMessage();
		 throw new Exception (errorMessage1);
	  }	
	  
      return dirList2;
   
   }
   
   // Method #
   public String getComponentsMethods(ArrayList<String> initialJavaClasses, FRLConfiguration frlCon, int projectId) //17
   {
      String errorMessage1="", missingLibFileName="", specialClassName="";
      int initialSize=0, finalSize=0;
      ArrayList<JavaFileData> finalJavaClasses; 
      List<SourceDirectory> dirList = new ArrayList<SourceDirectory>();
		   
      /************************************************
       * For every Java Class File
       * Get all its methods contained in Text Files 
       ************************************************/
	  System.out.println("");
	  System.out.println("Starting to obtain all the Components from the Java Files ...");
	  	  
	  // Delete all the files from the Output Project Directory
	  errorMessage1 = cleanOutputDirectory(frlCon.projectOutputDir);
	  
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;
	  
	  // Validate if the Forensic-Ready Logger has attached all the Libraries from the Software System
	  // to read the Java Files(*.java) and execute the instruction: newClass = Class.forName(fullClassName);
	  
	  // Build the fileName
	  missingLibFileName = frlCon.projectOutputDir + File.separator + "MissingJavaLibraries.txt";
	  
	  // Add the name of a special case class name 
	  specialClassName = "org.isf.utils.db.DbJpaUtil";
	  
	  
	  System.out.println("");
	  System.out.println("Verifying the Java Libraries that are MISSING in the Forensic-Ready Logger ...");
	  
	  try 
	  {
	     finalJavaClasses = getMissingJavaLibraries(initialJavaClasses, frlCon, missingLibFileName, specialClassName);
	  } 
	  catch (Exception e1) 
	  {
         errorMessage1 = e1.getMessage(); 
         return errorMessage1;
	  }
	  
	  // Compare the initial Java Classes and the final Java Classes
	  initialSize = initialJavaClasses.size();
	  finalSize   = finalJavaClasses.size();
	  
	  
	  if( (initialSize  == finalSize))
	     System.out.println("There are 0 Java Libraries that are MISSING.");
	  else
		  if( ( initialSize - 1) == finalSize)
		     System.out.println("There are 0 Java Libraries that are MISSING.");
		  else
		  {	  
	         errorMessage1 = "There are some Java Libraries that are MISSING AND need to be ATTACHED to the Forensic-Ready Logger." + 
		     System.lineSeparator();
	         errorMessage1 = errorMessage1 + "Check the Details in the File Name: " + 
	         System.lineSeparator() + missingLibFileName;
	       }
	  
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;
	  
      // Get the Source Directories for the Project
	  System.out.println("");
	  System.out.println("Getting the Source Directories from the Project ...");
	  System.out.println("");
	  
	  try 
	  {
	     dirList = getSourceDirectories(frlCon, projectId);
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage(); 
	     return errorMessage1;
	  }
	  
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;
	  
	  
      // Get the Start Lines Numbers for every Method in the Project
	  System.out.println("");
	  System.out.println("Getting the Methods Start Lines ...");
	  System.out.println("");
	  errorMessage1 = getClassMethodsStartLines(projectId, frlCon, finalJavaClasses, dirList);
      
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;
	  

	  // Obtain the full method parameter name for the methods that have an incomplete method parameter name
	  System.out.println("");
	  System.out.println("Obtaining the Final Full Parameter Method Names ...");
	  System.out.println("");
	  errorMessage1 = getFinalParameterMethodNames(projectId, frlCon);
	  
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;

	  
	  // Modify the Method Start Line and Method End Lines 
	  // if the Method Name contains in the previous 3 lines:
	  // Special instructions such as @Query, @Transactional @Modifying
	  System.out.println("");
	  System.out.println("Changing the Methods Start Lines in some Special Methods ...");
	  errorMessage1 = getSpecialClassMethodsStartLines(frlCon, projectId);
				  
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;
  
	  
      // Get the End Lines Numbers for every Method in the Project  
	  System.out.println("");
	  System.out.println("Getting the Methods End Lines ...");
      errorMessage1 = getClassMethodsEndLines(finalJavaClasses, frlCon.databaseConfigFile, 
                                              frlCon.replacePattern, projectId);
      
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;
	  
	  
   	  // Get the value for the End Lines Numbers that have a Zero value for every Method in the Project
	  System.out.println("");
	  System.out.println("Getting the Methods with Zero End Lines ...");
	  errorMessage1 = getClassMethodsZeroEndLines(frlCon.databaseConfigFile, frlCon.endMethod, projectId);
   	  
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;

	  
   	  // Generate the Method Text Files for all the Java Files in the Project
	  System.out.println("");
	  System.out.println("Creating the Method Text Files ...");
	  System.out.println("");
   	  errorMessage1 = getClassMethodsBody(finalJavaClasses, frlCon, projectId);

	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;
	     
      /**************************************
        For every Java Class File
        Get the class type 
        Its fields stored in the Database
       **************************************/
	  

	  // Get the Data Type of the Class which this Java File belongs
	  // And Obtain its attributes
      System.out.println("");
	  System.out.println("Getting the Data Type of the Classes and the Attributes ...");
	  System.out.println("");
	  errorMessage1 = getClassDataTypeAttributes(finalJavaClasses, projectId, frlCon);
		      
	  if(errorMessage1.isEmpty() == false)
	     return errorMessage1;
	  
      System.out.println("");
      System.out.println("Finishing to obtain all the Components from the Java Files.");
	 
	  return errorMessage1;	
	    
	  
   }
	 
   // Method #2
   public ArrayList<String> readJavaFiles(String inputDir, String projectName, 
			                              String tabDelimiter, String programmingLanguage) throws Exception
   {
		   
	  String pl="", format="", fileName="", fileNamePath="", ext="", 
			 errorMessage1="", errorMessage2="", hiddenDir=".mvn";
	  String[] extensions;
	  ArrayList<String> javaFiles = new ArrayList<String>();
      File dir;
	  List<File> files = null;
	  File[] visibleFiles;
	  boolean hiddenDirFlag=false;
	  int jF=0;
	  char c;
	 
	  
	  pl = programmingLanguage.toLowerCase();
	  extensions = new String[]{pl};
	  File directory = new File(inputDir);
	  format = tabDelimiter;  
	  
	  // Initialize the javaFiles ArrayList
	  javaFiles.clear();

      System.out.println("");
	  System.out.println("For the Project Name  : " + projectName ); 
	  System.out.println("Located in the Folder : "+ inputDir);

	  System.out.println("These are " + programmingLanguage + " Files which are the Source Code: ");
	  
	  try
	  {
	     visibleFiles = directory.listFiles((FileFilter) HiddenFileFilter.VISIBLE);

	     for (File visibleFile : visibleFiles) 
	     {
	        dir = new File(visibleFile.getCanonicalPath());
	        
		    if(dir.isDirectory() == true)
		    {	
				
	           files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		    	
	    	   for (File file : files) 
		       {
		          // Get the filename
			      fileName = file.getName();
			      
			      // Get the first character of the filename
			      c = fileName.charAt(0);
			      
			      // Get the full path of this file
			      fileNamePath = file.getAbsolutePath().toString();
			   
                  hiddenDirFlag = file.getAbsolutePath().toString().contains(hiddenDir);
                  
			      if (hiddenDirFlag == false && Character.isLetter(c))
			      {	  
	                 jF ++;
				     System.out.format(format, fileNamePath, 1);
				     System.out.println(jF +".- " + fileNamePath);
				  
			         javaFiles.add(fileNamePath);
			      }   
			         
	           }
		   }
		   else
		   {
		      // Get the filename
			  fileName = visibleFile.getName();
			  
			  ext = FilenameUtils.getExtension(visibleFile.toString()); 
			  
			  // Get the first character of the filename
			  c = fileName.charAt(0);
			  
			  // Get the full path of this file
			  fileNamePath = visibleFile.getAbsolutePath().toString();
			   
              hiddenDirFlag = visibleFile.getAbsolutePath().toString().contains(hiddenDir);
			  
              // Validate if the first character is a letter, to be sure is a Java Class File 
		      if (hiddenDirFlag == false && Character.isLetter(c) && ext.equals(pl))
		      {	  
                 jF ++;
				 System.out.format(format, fileNamePath, 1);
				 System.out.println(jF +".- " + fileNamePath);
				  
			     javaFiles.add(fileNamePath);
		      }
		      
		     }      
	      }
	   }
	   catch(Exception e)
	   {
 	      errorMessage1 = e.getMessage(); 
  		  errorMessage2 = "Error XXXX: Occurred while loading the Source Code for the Project Name: " + projectName + System.lineSeparator();
  		  errorMessage2 = errorMessage2 + "Located in the Folder: " + inputDir + System.lineSeparator() ;
  		  errorMessage2 = errorMessage2 + "Error Message: " + System.lineSeparator() + errorMessage1;
  		  throw new Exception(errorMessage2); 
	   }
	 
	   System.out.println("");
	   System.out.println("The Project Name: " + projectName + " has " + jF + " Java Files.");
	   
	   return javaFiles;
		    
	}
	   
   public String getSourceCode(FRLConfiguration frlCon, int projectId)
   {
      String errorMessage="";
	  ArrayList<String> javaClasses = new ArrayList<String>(); 
      
	  // Read the Java Files from the Application Input Directory 
	  try 
	  {
	     javaClasses = readJavaFiles(frlCon.projectInputDir, frlCon.projectName,                                                           
		   			                 frlCon.tabDelimiter1, frlCon.programmingLanguage);
	  } 
	  catch (Exception e) 
	  {
         errorMessage = e.getMessage();
         return errorMessage;
	  }
	  

	  // Validate whether or not they are any Java Files (*.java) in the Application Project 
	  //format = frlCon.tabDelimiter1;
	  
	  if(javaClasses.isEmpty() == false)
	  {                                                                                                                                    
		 // For this Project, from the Java Files:
		 // Get all Methods separated in Text Files
		 // Get the class type
		 // Get the attributes 
		 errorMessage = getComponentsMethods(javaClasses, frlCon, projectId);
	  }
	  else
	  {	  
	     errorMessage = "For the Project Name: " + frlCon.projectName + System.lineSeparator();
	     errorMessage = errorMessage + "There are NOT any Java Files (*.java) " + System.lineSeparator();
	     errorMessage = errorMessage + "In the Input Folder: " + frlCon.projectInputDir;
	  } 
	  
      return errorMessage;	   
   }
   
}
