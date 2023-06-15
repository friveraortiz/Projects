package frl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;

import org.apache.log4j.PropertyConfigurator;
import java.util.List;

public aspect FrlLoggingMethods
{
   String annotationFile="C:\\Users\\friverao\\eclipse-workspace\\Directories\\Output\\OpenHospital_Annotations.txt";
   String doNotLogAttributes="";
   String findWhiteSpaces="\\s+";
   String objectOrientedDelimiter1=".";
   String programmingLanguage="java";
   String dataTypeFullClassName="java.lang.Class";
   String dataTypeShortClassName="java.lang.";
   ArrayList<String> dataTypeStringList=new ArrayList<>(Arrays.asList("java.lang.Character", "java.lang.String", "java.lang.Class", "java.lang.Boolean"));
   ArrayList<String> dataTypeNumberList=new ArrayList<>(Arrays.asList("java.lang.Integer", "java.lang.Float", "java.lang.Double", "java.lang.Long", "java.lang.Short"));

   ArrayList<String> annotationList = new ArrayList<String>();

   String errorMessage1="", errorMessage2="", methodPackageName="", methodClassName="", methodFullClassName1="", methodShortName="", 
		  methodFullName1="", methodFullName2="", methodReturnType="", methodPLReturnType="", methodCondition="", 
		  methodReturnTypeValueStr="",
		  annotationLine="", annotationType="", annotationMethodShortName="", annotationMethodFullName="", 
		  annotationCondition="", annotationMethodOwner="", annotationElement1="", annotationElement2="", annotationOperator="", 
		  annotationAttributeType="", annotationAttributePLType="", oSName="", oSUserName="", oSHostname="";
   
   String[] parameterNames;
   Class<?> methodFullClassName2=null;
   Class<?>[] parameterTypes;
   Object[] methodArgs, parameterValues;
   Signature method;
   
   /*String parameterTypeShortName="",parameterTypePkgName="",parameterTypeClassName1="", 
	  parameterTypeClassName2="", parameterName="", attributeShortName="",*/
   //Class<?> parameterTypeFullName=null,attributeType=null
   //Object parameterValue=null,attributeValue=null;
   //Field attributeFullName=null;

   
   int i=0, a=0, len=0, parameterCount=0,annotationMethodId=0;
   /*int parameterNumber=0,attributeNumber=0, /*atttributeCount=0,*/ ;
      
   boolean addLog=false;

   Date date;
   DateFormat df1, df2;
   SimpleDateFormat dateFormat;
   
   InetAddress oSIP;

   Logger log;
   
   String log4jConfPath = "C:\\Users\\friverao\\git\\openhospital-gui\\src\\main\\java\\log4j.properties";
   String methodReturnTypeStr="";
   /*, String attributeTypeStr="";*/
   
   /* Evaluation Start (DECLARE VARIABLES) */
   long time1, time2, duration;
   /* Evaluation End */   
   
   public boolean addNewLog(String dataTypeProgrLanguage1, String value1, String value2, String operator)
   {   
      int integerValue1=0, integerValue2=0;
      float floatValue1=0, floatValue2=0;
      double doubleValue1=0, doubleValue2=0;
      long longValue1=0, longValue2=0;
      short shortValue1=0, shortValue2=0;
      String dataTypeProgrLanguage2="";
      Boolean logFlag = false;
      
      /*
      System.out.println("*** Inside the ADD NEW LOG Method ***");
      
      System.out.println("INPUT PARAMETERS");

      System.out.println("Data Type Prog Language 1 : " + dataTypeProgrLanguage1);
      System.out.println("Value 1  : " + value1);
      System.out.println("Value 2  : " + value2);
      System.out.println("Operator : " + operator);
      
      System.out.println("Data Type String List     : " + dataTypeStringList);
      */
      
      // Compare if the Data Type Programming Language is a String, boolean or a Class
      if(dataTypeStringList.contains(dataTypeProgrLanguage1))
	  {
	     //System.out.println("Case 1 - String ");
	     
	     if(operator.equals("="))
		 {	   
		    if (value1.equals(value2) == true)
		       logFlag = true;
	    	else
	    	   logFlag = false;
		  }   
		  else
		     if(operator.equals("!="))
		     {
	    	    if (value1.equals(value2) == false)
	    	       logFlag = true;
	    	    else
	    	       logFlag = false;
		     }
		   
		   //System.out.println("Log Flag: "+logFlag);  
		     
	  }
      
      else
    	 // Compare if the Data Type Programming Language is a Numeric Data Type
         if(dataTypeNumberList.contains(dataTypeProgrLanguage1))
    	 {
    	     //System.out.println("Case 2 - Number ");
    	 
        	 dataTypeProgrLanguage2 = dataTypeProgrLanguage1.replace(dataTypeShortClassName, "");
        	 
        	 //System.out.println("Data Type Prog Language 2 : " + dataTypeProgrLanguage2);
            
            switch (dataTypeProgrLanguage2) 
        	{
        	   case "Integer":
        	      integerValue1 = Integer.parseInt(value1);
        		  integerValue2 = Integer.parseInt(value2);
        		   //System.out.println("Case Integer");
        	   break;
        	   
        	   case "Float":
         	      floatValue1 = Float.parseFloat(value1);
         		  floatValue2 = Float.parseFloat(value2);
         		  //System.out.println("Case Float");
         	   break;
         	   
           	   case "Double":
          	      doubleValue1 = Double.parseDouble(value1);
          		  doubleValue2 = Double.parseDouble(value2);
          		  //System.out.println("Case Double");
          	   break;
          	   
           	   case "Long":
           	      longValue1 = Long.parseLong(value1);
           		  longValue2 = Long.parseLong(value2);
           		  //System.out.println("Case Long");
           	   break;
           	   
          	   case "Short":
                  shortValue1 = Short.valueOf(value1);
            	  shortValue2 = Short.valueOf(value2);
            	  //System.out.println("Case Short Value");
               break;
        	}
            
            if(operator.equals("="))
            {
               switch (dataTypeProgrLanguage2) 
               {
                  case "Integer":
            	     if(integerValue1 == integerValue2)
            	        logFlag = true;
            	     else
            	        logFlag = false;
            	  break;
            	   
            	  case "Float":
             	     if(floatValue1 == floatValue2)
             	        logFlag = true;
             	     else
             	        logFlag = false;
             	  break;
             	   
               	   case "Double":
               	     if(doubleValue1 == doubleValue2)
              	        logFlag = true;
              	     else
              	        logFlag = false;
              	   break;
              	   
               	   case "Long":
                      if(longValue1 == longValue2)
                   	     logFlag = true;
                   	  else
                   	     logFlag = false;
               	   break;
               	   
              	   case "Short":
                      if(shortValue1 == shortValue2)
                         logFlag = true;
                      else
                         logFlag = false;
                   break;
            	}
            	
            }
			else 
			   if(operator.equals("!="))
	               switch (dataTypeProgrLanguage2) 
	               {
	                  case "Integer":
	            	     if(integerValue1 != integerValue2)
	            	        logFlag = true;
	            	     else
	            	        logFlag = false;
	            	  break;
	            	   
	            	  case "Float":
	             	     if(floatValue1 != floatValue2)
	             	        logFlag = true;
	             	     else
	             	        logFlag = false;
	             	  break;
	             	   
	               	  case "Double":
	               	     if(doubleValue1 != doubleValue2)
	              	        logFlag = true;
	              	     else
	              	        logFlag = false;
	              	  break;
	              	   
	               	  case "Long":
	                     if(longValue1 != longValue2)
	                   	    logFlag = true;
	                   	 else
	                   	    logFlag = false;
	               	  break;
	               	   
	              	  case "Short":
	                     if(shortValue1 != shortValue2)
	                        logFlag = true;
	                     else
	                        logFlag = false;
	                  break;
	            	}
			   else 
			      if(operator.equals(">"))
		             switch (dataTypeProgrLanguage2) 
		             {
		                case "Integer":
		            	   if(integerValue1 > integerValue2)
		            	      logFlag = true;
		            	   else
		            	      logFlag = false;
		            	break;
		            	   
		            	case "Float":
		             	   if(floatValue1 > floatValue2)
		             	      logFlag = true;
		             	   else
		             	      logFlag = false;
		             	break;
		             	   
		               	case "Double":
		               	   if(doubleValue1 > doubleValue2)
		              	      logFlag = true;
		              	   else
		              	      logFlag = false;
		              	break;
		              	   
		               	case "Long":
		                   if(longValue1 > longValue2)
		                      logFlag = true;
		                   else
		                      logFlag = false;
		               	break;
		               	   
		              	case "Short":
		                   if(shortValue1 > shortValue2)
		                      logFlag = true;
		                   else
		                      logFlag = false;
		                break;
		             }
			      else
			         if(operator.equals("<"))
			            switch (dataTypeProgrLanguage2) 
			            {
			               case "Integer":
			                  if(integerValue1 < integerValue2)
			            	     logFlag = true;
			            	  else
			            	     logFlag = false;
			               break;
			            	   
			               case "Float":
			                  if(floatValue1 < floatValue2)
			             	     logFlag = true;
			             	  else
			             	     logFlag = false;
			               break;
			             	   
			               case "Double":
			                  if(doubleValue1 < doubleValue2)
			              	     logFlag = true;
			              	  else
			              	     logFlag = false;
			               break;
			              	   
			               case "Long":
			                  if(longValue1 < longValue2)
			                     logFlag = true;
			                  else
			                     logFlag = false;
			               break;
			               	   
			               case "Short":
			                  if(shortValue1 < shortValue2)
			                     logFlag = true;
			                  else
			                     logFlag = false;
			               break;
			            }
			    	 else
			    	    if(operator.equals(">="))
				           switch (dataTypeProgrLanguage2) 
				           {
				              case "Integer":
				                 if(integerValue1 >= integerValue2)
				            	    logFlag = true;
				            	 else
				            	    logFlag = false;
				              break;
				            	   
				              case "Float":
				                 if(floatValue1 >= floatValue2)
				             	    logFlag = true;
				             	 else
				             	    logFlag = false;
				              break;
				             	   
				              case "Double":
				                 if(doubleValue1 >= doubleValue2)
				              	    logFlag = true;
				              	 else
				              	    logFlag = false;
				              break;
				              	   
				              case "Long":
				                 if(longValue1 >= longValue2)
				                    logFlag = true;
				                 else
				                    logFlag = false;
				              break;
				               	   
				              case "Short":
				                 if(shortValue1 >= shortValue2)
				                    logFlag = true;
				                 else
				                    logFlag = false;
				              break;
				           }
			    	    else 
			    	       if(operator.equals("<="))
					           switch (dataTypeProgrLanguage2) 
					           {
					              case "Integer":
					                 if(integerValue1 <= integerValue2)
					            	    logFlag = true;
					            	 else
					            	    logFlag = false;
					              break;
					            	   
					              case "Float":
					                 if(floatValue1 <= floatValue2)
					             	    logFlag = true;
					             	 else
					             	    logFlag = false;
					              break;
					             	   
					              case "Double":
					                 if(doubleValue1 <= doubleValue2)
					              	    logFlag = true;
					              	 else
					              	    logFlag = false;
					              break;
					              	   
					              case "Long":
					                 if(longValue1 <= longValue2)
					                    logFlag = true;
					                 else
					                    logFlag = false;
					              break;
					               	   
					              case "Short":
					                 if(shortValue1 <= shortValue2)
					                    logFlag = true;
					                 else
					                    logFlag = false;
					              break;
					           }
    		  
    	 }
    	       
      //System.out.println("Log Flag: " + logFlag);
      
      return logFlag;
	   
   }
   
   public void getAnnotationFields(String line)
   {
      String tokenText="";
      StringTokenizer tokens;
      int tokenNumber=0;

	  // Constructor of the StringTokenizer class  
	  tokens = new StringTokenizer(line, ",;");
	  
	  // Checks if the tokens String has more tokens or not  
	  while (tokens.hasMoreTokens())   
	  {  
	     // Get the information from the Token
	     tokenText = tokens.nextToken();
	     tokenText = tokenText.replaceAll(findWhiteSpaces,"");
	     tokenNumber++;
	     
	     // Depending on the tokenNumber, it stores the tokenText into the appropriate field
	     switch(tokenNumber) 
	     {
	        case 1:
	           annotationType = tokenText;
	        break;
	        
	        case 2:
	           annotationMethodId = Integer.parseInt(tokenText);
	        break;
	        
	        case 3:
	           annotationMethodFullName = tokenText;
	        break;
	        
	        case 4:
	           annotationMethodOwner = tokenText;
	        break;
	        
	        case 5:
	           annotationMethodShortName = tokenText;
	        break;
	        
	        case 6:
	           annotationCondition = tokenText;
	        break;
	        
	        case 7:
		       annotationElement1 = tokenText;
		    break;
	        
	        case 8:
		       annotationOperator = tokenText;
		    break;
		    
	        case 9:
		       annotationElement2 = tokenText;
		    break;
	        
	     }

      }

   }
   
   public ArrayList<String> getAnnotationLines(String textFile, String method)
   {
      String line="";
      ArrayList<String> annotationList = new ArrayList<String>();
      BufferedReader br = null;
      File file;
      FileReader fr;
      
      try 
      {
         file = new File(textFile);
         fr   = new FileReader(file);
         br   = new BufferedReader(fr);
         
         while ((line = br.readLine()) != null) 
         {
            if(line.contains(method))
            {
               annotationList.add(line);
            }
         }
       }
       catch(IOException e1) 
       { 
          errorMessage1 = e1.getMessage(); 
          errorMessage2 = "Error XXXX: Occurred while loading the TextFile: " + System.lineSeparator();
          errorMessage2 = errorMessage2 + textFile + System.lineSeparator();
          errorMessage1 = errorMessage2 + errorMessage1;
          System.out.println(errorMessage1);
       }
       finally
       {
          try
          { 
             if (br != null)
                br.close(); 
          }
          catch(IOException e2)
          {
             errorMessage1 = e2.getMessage(); 
             errorMessage2 = "Error XXXX: Occurred while closing the TextFile: " + System.lineSeparator();
             errorMessage2 = errorMessage2 + textFile + System.lineSeparator();
             errorMessage1 = errorMessage2 + errorMessage1;
             System.out.println(errorMessage1);
          }
      }
      
      return annotationList;
   }
   
   public boolean attributeAnnotation(int attributeCount, 
		                              String parameterName,
                                      Class<?> parameterTypeFullName, 
		                              Object parameterValue, String annotationType1, String annotationElementA, 
		                              Object methodReturnTypeValue1,
		                              String label)
   {
      String attributeShortName="", attributeTypeStr="", subAttributeName="", parameterType="";
      Object attributeValue=null;
      Class<?> attributeType=null, attributeTypeFullName=null;
      Field attributeFullName=null;
      int attributeNumber=0, subAttributeCount=0; 
      boolean addLog1=false, subAttributeFlag=false, attributePrimitiveType=false;
      
      //System.out.println("Inside the Attribute Annotation Method ...");
      
      for (attributeNumber=0; attributeNumber < attributeCount; attributeNumber++)
      {
   	
         attributeShortName     = parameterTypeFullName.getDeclaredFields()[attributeNumber].getName();
         attributeType          = parameterTypeFullName.getDeclaredFields()[attributeNumber].getType();
         attributeTypeStr       = attributeType.toString(); 
         attributePrimitiveType = attributeType.isPrimitive();
         
         subAttributeName = "";
         subAttributeFlag = false;
         
         if(attributeTypeStr.contains("class"))
         {
            attributeTypeStr = attributeTypeStr.replace("class ","");
         }
          
	      try 
	      {
	         attributeFullName = parameterValue.getClass().getDeclaredField(attributeShortName);
		     attributeFullName.setAccessible(true);
	      } 
	      catch (Exception e2) 
	      {
	         errorMessage1 = e2.getMessage(); 
	         errorMessage2 = "Error XXXX: Occurred while getting the Attribute Full Name for the Attribute Short Name: " + System.lineSeparator();
	         errorMessage2 = errorMessage2 + attributeShortName + System.lineSeparator();
	         errorMessage1 = errorMessage2 + errorMessage1;
	         System.out.println(errorMessage1);
	      }  
	      
	      /*
	      System.out.println("*** ATRIBUTE INFORMATION ***");
	      System.out.println("Attribute Number     : " + attributeNumber);
	      System.out.println("Attribute Short Name : " + attributeShortName);
	      System.out.println("Attribute Type       : " + attributeType);
	      System.out.println("Attribute Type  Str  : " + attributeTypeStr);
	      System.out.println("Attribute Full Name  : " + attributeFullName);
	      System.out.println("Attribute Primitive Data Type: " + attributePrimitiveType);
	      */

	      try 
	      {
	         attributeValue = attributeFullName.get(parameterValue);
	      } 
	      catch (Exception e3) 
	      {
	         errorMessage1 = e3.getMessage(); 
	         errorMessage2 = "Error XXXX: Occurred while getting the Attribute Value for the Parameter Value: " + System.lineSeparator();
	         errorMessage2 = errorMessage2 + parameterValue + System.lineSeparator();
	         errorMessage1 = errorMessage2 + errorMessage1;
	         System.out.println(errorMessage1);
	      } 
	      
	      
	      //System.out.println("Attribute Value      : " + attributeValue);
	   
	      if(attributeValue != null && attributeValue.toString().equals("0") == false && 
		     attributeValue.toString().equals("0.0") == false)
	      {	   
	         annotationAttributeType = attributeValue.getClass().getName();
	          
		     if(annotationAttributeType.contains(dataTypeShortClassName) == false)
		        annotationAttributePLType = dataTypeFullClassName;
		     else
		        annotationAttributePLType = annotationAttributeType;
		     
		    
		     /*
		    System.out.println("Annotation Attribute Type    : " + annotationAttributeType);    
		    System.out.println("Annotation Attribute PL Type : " + annotationAttributePLType);  
		    System.out.println("Data Type Full Class Name    : " + dataTypeFullClassName); 
		    */
		    
		    
		    if(annotationType1.equals("Method") == true || annotationType1.equals("ReturnType") == true)
		    {	  
		    	/*
		    	System.out.println("METHOD/RETURN TYPE ATTRIBUTE ANNOTATION TYPE");
		    	System.out.println("Annotation Type 1: " + annotationType1);
		    	*/
		    	
		        if(doNotLogAttributes.contains(attributeShortName) == false)
		        {	 
		           
		           // Validate if the Attribute Data Type is a Personalized Class
		           if( attributeTypeStr.contains(programmingLanguage) == false &&
		               attributePrimitiveType == false)
		  		   {
		  		      
		  		      //System.out.println("The Attribute has a Personalized Class");
		  		   
		  		      /* Evaluation Start (TIME 1) */
	                  time1 = System.currentTimeMillis();
	                  /* Evaluation End */
	  
				      // Logging Instructions for the value of the Attributes
			          log.info(label + " Name : " + attributeShortName);
			          log.info(label + " Type : " + attributeTypeStr);
			          
			          /* Evaluation Start (TIME 2) */
	                  time2 = System.currentTimeMillis();
	                  duration = time2 - time1;
	                  System.out.println("Message FRL: The Security Log for a METHOD or a RETURN TYPE Annotation Type was generated in: " + duration + " milliseconds"); 
	                  /* Evaluation End */
			          
			          addLog1 = true;
			          
			          /*
			          System.out.println("Add Log 1         : " + addLog1);
			          System.out.println("Add an Attribute Log");
			          System.out.println("Attribute  Name     : " + attributeShortName);
			          System.out.println("Attribute  Type     : " + attributeType);
			          System.out.println("Attribute  Type Str : " + attributeTypeStr);
			          System.out.println("FAIL ONE");
			          */
			              
		              // Obtain the Full Name of the Personalized Class 
		   		      try 
		   		      {
		   			     attributeTypeFullName = Class.forName(attributeTypeStr); 
		   			  } 
		   		      catch (Exception e1) 
		   		      {
		   		         errorMessage1 = e1.getMessage(); 
		   		         errorMessage2 = "Error XXXX: Occurred while getting the Class Name for the Attribute Type Class: " + System.lineSeparator();
		   		         errorMessage2 = errorMessage2 + attributeTypeStr + System.lineSeparator();
		   		         errorMessage1 = errorMessage2 + errorMessage1;
		   		         System.out.println(errorMessage1);
		   			  }
		   			
		   			  //System.out.println("Attribute Type Full Name: " + attributeTypeFullName);
		   			  
		   			  //System.out.println("*** Verifiying if the Attribute has sub-attributes ***");
				      subAttributeCount = attributeTypeFullName.getDeclaredFields().length;
				      
				      if(subAttributeCount > 0)
				      {	 
				       
				         /* Evaluation Start (TIME 1) */
	                     time1 = System.currentTimeMillis();
	                     /* Evaluation End */
	                     
				    	 log.info("The Attribute: " + attributeShortName + " has " + attributeCount + " SubAttributes");
				    	 
				    	 /* Evaluation Start (TIME 2) */
	                     time2 = System.currentTimeMillis();
	                     duration = time2 - time1;
	                     System.out.println("Message FRL: The Security Log for a METHOD or a RETURN TYPE Annotation Type was generated in: " + duration + " milliseconds"); 
	                     /* Evaluation End */  
				         
				    	 //System.out.println(label + "  has: " + subAttributeCount + " SubAttributes");
				         
				    	 addLog1 = attributeAnnotation(subAttributeCount, attributeShortName, attributeTypeFullName, 
				    		                           attributeValue, annotationType1, annotationElement1,
		                                               methodReturnTypeValue1, "SubAttribute");
				       
				         //System.out.println("Add Log 1 - SubAttributes : " + addLog1);
				      }
				      
		        	   
		  		   }
		           else
		           {
		              //System.out.println("The Attribute has a Primitive or a Java Class");
		           
		              /* Evaluation Start (TIME 1) */
	                  time1 = System.currentTimeMillis();
	                  /* Evaluation End */
	                  
			          // Logging Instructions for the value of the Attributes
		              log.info(label + " Name  : " + attributeShortName);
		              log.info(label + " Type  : " + attributeTypeStr);
			          log.info(label + " Value : " + attributeValue);
			          
			          /* Evaluation Start (TIME 2) */
	                  time2 = System.currentTimeMillis();
	                  duration = time2 - time1;
	                  System.out.println("Message FRL: The Security Log for a METHOD or a RETURN TYPE Annotation Type was generated in: " + duration + " milliseconds"); 
	                  /* Evaluation End */ 
			           
			          addLog1 = true;
			          
			          /*System.out.println("Add Log 1         : " + addLog1);
			           
			          System.out.println("Add an Attribute Log");
			           
			          System.out.println("Attribute Name  : " + attributeShortName);
			          System.out.println("Attribute Type  : " + attributeType);
			          System.out.println("Attribute Value : " + attributeValue);  
			          */
		           }
		        
		        }
		    }
		    else
		    {
		    	if(annotationType1.equals("Parameter") == true)
		    	{	
		    	
		    	   /*	
		    	   System.out.println("PARAMETER ATTRIBUTE ANNOTATION TYPE");
			       System.out.println("Annotation Type 1: " + annotationType1);
			    	
		    	   	
		    	   System.out.println("INSIDE THE ANOTATION TYPE PARAMETER: ATTRIBUTE");
		    	   
		           System.out.println("Attribute Short Name         : " + attributeShortName);
		           System.out.println("Attribute Value              : " + attributeValue.toString());
		           System.out.println("Annotation Element A         : " + annotationElementA);
		           System.out.println("Annotation Attribute PL Type : " + annotationAttributePLType);
		           System.out.println("Annotation Element 2         : " + annotationElement2);
		           System.out.println("Annotation Operator          : " + annotationOperator);
		           
		           System.out.println("Annotation Condition         : " + annotationCondition);
		           
		           System.out.println("Attribute Type Str: " + attributeTypeStr);
		           
		           System.out.println("Comparing: " + attributeShortName + " and " + annotationElementA);
		           */
		           
		           
		           //if(doNotLogAttributes.contains(attributeShortName) == false)
		        	   
		           if(attributeShortName.equals(annotationElementA))
			       {	 
			           
 			           // Validate if the Attribute Data Type is a Personalized Class
			           if( attributeTypeStr.contains(programmingLanguage) == false &&
			        	   attributePrimitiveType == false)
			  		   {
			  		      
			  		      //System.out.println("The Attribute has a Personalized Class");
			  		   
			  		      /*
					      // Logging Instructions for the value of the Attributes
				          log.info(label + " Name      : " + attributeShortName);
				          log.info(label + " Type      : " + attributeTypeStr);
				          
				          addLog1 = true;
				          System.out.println("Add Log 1         : " + addLog1);
				           
				          System.out.println("Add an Attribute Log");
				           
				          System.out.println("Attribute  Name  : " + attributeShortName);
				          System.out.println("Attribute  Type  : " + attributeType);
				          */
				          
			        	  /* 
			              System.out.println("Add Log 1         : " + addLog1);
			              System.out.println("Add an Attribute Log");
			              System.out.println("Attribute  Name     : " + attributeShortName);
			              System.out.println("Attribute  Type     : " + attributeType);
			              System.out.println("Attribute  Type Str : " + attributeTypeStr);
			              System.out.println("FAIL TWO");
			              */
				              
			              // Obtain the Full Name of the Personalized Class 
			   		      try 
			   		      {
			   			     attributeTypeFullName = Class.forName(attributeTypeStr); 
			   			  } 
			   		      catch (Exception e1) 
			   		      {
			   		         errorMessage1 = e1.getMessage(); 
			   		         errorMessage2 = "Error XXXX: Occurred while getting the Class Name for the Attribute Type Class: " + System.lineSeparator();
			   		         errorMessage2 = errorMessage2 + attributeTypeStr + System.lineSeparator();
			   		         errorMessage1 = errorMessage2 + errorMessage1;
			   		         System.out.println(errorMessage1);
			   			  }
			   			
			   			  /*System.out.println("Attribute Type Full Name: " + attributeTypeFullName);
			   			  
			   			  System.out.println("*** Verifiying if the Attribute has sub-attributes ***");
			   			  */
					      subAttributeCount = attributeTypeFullName.getDeclaredFields().length;
					      
					      // Validate if the condition includes a subAttribute
					      subAttributeName = annotationCondition.substring(annotationCondition.indexOf(annotationElementA));
					      
					      //System.out.println("Sub Attribute Name ORIGINAL: "  + subAttributeName);
					      
					      if(subAttributeName.contains("."))
					      {
					         subAttributeName = subAttributeName.substring(subAttributeName.indexOf(".") + 1, subAttributeName.indexOf("="));
					         subAttributeFlag = true;
					      }   
					      
					      /*
					      System.out.println("Sub Attribute Count         : " + subAttributeCount);
					      System.out.println("Sub Attribute Name MODIFIED : "  + subAttributeName);
					      System.out.println("SubAttribute Flag           : " + subAttributeFlag);
					      */
					      
					      if(subAttributeCount > 0 && subAttributeFlag == true)
					      {	  
					         /* Evaluation Start (TIME 1) */
	                         time1 = System.currentTimeMillis();
	                         /* Evaluation End */
	                  
					    	 log.info("The Attribute: " + attributeShortName + " has " + attributeCount + " SubAttributes"); 
					    	 
					    	 /* Evaluation Start (TIME 2) */
	                         time2 = System.currentTimeMillis();
	                         duration = time2 - time1;
	                         System.out.println("Message FRL: The Security Log for a PARAMETER Annotation Type was generated in: " + duration + " milliseconds"); 
	                         /* Evaluation End */ 
					         
					    	 //System.out.println(label + "  has: " + subAttributeCount + " SubAttributes");
					         
					    	 //System.out.println("BEFORE LOGGING SUBATTRIBUTES - PARAMETER DATA TYPE");
					    	 addLog1 = attributeAnnotation(subAttributeCount, attributeShortName, attributeTypeFullName, 
					    		                           attributeValue, annotationType1, subAttributeName,
			                                               methodReturnTypeValue1, "SubAttribute");
					       
					         //System.out.println("Add Log 1 - SubAttributes : " + addLog1);
					      }
					      
			  		   }
			           else
			           {
			              //System.out.println("The Attribute has a Primitive or a Java Class");
			        	   
			        	  /*if(attributeShortName.equals(annotationElementA))
			        	  {*/
			              if(doNotLogAttributes.contains(attributeShortName) == false)
			              {	  
			        	     addLog1 = addNewLog(annotationAttributePLType, attributeValue.toString(), annotationElement2, annotationOperator);

		                     //System.out.println("Validating adding a Log for a Parameter (INSIDE ATTRIBUTE CASE 1): " + addLog1); 
		                     
		                     if(addLog1 == true)
		                     {	 
		                        //System.out.println("Adding a Header Log");
	                            addLogHeader();
	    	 
	                            //System.out.println("Add an Attribute Log");
	                            
	                            parameterType = parameterTypeFullName.toString();
	                            
	                            parameterType = parameterType.replace("class "," ");
	                            
	                            /*
	                            System.out.println("WE NEED TO ADD HERE A LOG FOR THE PARAMETER");
	                            System.out.println("Parameter Name           : " + parameterName);
	                            
	                            System.out.println("Parameter Value          :" + parameterValue);
	                            System.out.println("Parameter Type Full Name : " + parameterTypeFullName);
	                            */
	                            
	                            /* Evaluation Start (TIME 1) */
	                            time1 = System.currentTimeMillis();
	                            /* Evaluation End */
	                            
	                            // Logging Instructions for the value of the Parameter
	       					    log.info("Parameter Name  : " + parameterName);
	       					    log.info("Parameter Type  : " + parameterType);
	       				        log.info("Parameter Value : " + parameterValue);
	       				     
	       					    //log.info("Parameter Type Full Name : " + parameterTypeFullName);
	                         
					            // Logging Instructions for the value of the Attributes
				                log.info(label + " Name  : " + attributeShortName);
				                log.info(label + " Type  : " + attributeTypeStr);
					            log.info(label + " Value : " + attributeValue);
					            
					            /* Evaluation Start (TIME 2) */
	                            time2 = System.currentTimeMillis();
	                            duration = time2 - time1;
	                            System.out.println("Message FRL: The Security Log for PARAMETER Annotation Type was generated in: " + duration + " milliseconds"); 
	                            /* Evaluation End */ 
	  			          
	  			                addLog1 = true;
	  			                /*
	  			                System.out.println("Add Log 1         : " + addLog1);
					            System.out.println("Add an Attribute Log");
					           
					            System.out.println("Attribute Name  : " + attributeShortName);
					            System.out.println("Attribute Type  : " + attributeType);
					            System.out.println("Attribute Value : " + attributeValue);
					            */  
		                     }

			        	  }
 
			           }
			        
			       }

		        }
		    }   
	     }
	     else
	        annotationAttributeType = "";
	   
       }      
      
      
      return addLog1;
   }
   
   public boolean parameterAnnotation(String[] parameterNames1, Class<?>[] parameterTypes1, int parameterCount1, 
		                              Object[] parameterValues1, String annotationType1, Object methodReturnTypeValue1)
   {

      int parameterNumber=0, attributeCount=0;
      String parameterTypeShortName="", parameterTypePkgName="", parameterName="", parameterValue2="", parameterType="", 
    		 parameterPLType="", parameterTypeClassName1="", parameterTypeClassName2="", objectString="";
      // String attributeShortName="";
      Object parameterValue=null;
      //Object attributeValue=null;
      long parameterValuesCount=0;
      boolean addLog1=false, parameterPrimitiveType=false;
      Pattern pattern=null;
      Matcher matcher=null;
      Class<?> parameterTypeFullName=null;
      //Class<?> attributeType=null;
      //Field attributeFullName=null;
	  
      
      /*
	  System.out.println("*** Inside the Parameter Annotation Method ***");
	  System.out.println("Parameter Count: " + parameterCount1);	
	  */	 
	  	
	  // Loop for the Parameters
	  for (parameterNumber=0; parameterNumber < parameterCount1; parameterNumber++) 
	  {
	     
	     /*System.out.println("INSIDE THE LOOP");
	  
	     System.out.println("*** INFORMATION ABOUT THE PARAMETER ***");
         System.out.println("Parameter Number            : " + parameterNumber);
         */
         
	     parameterTypeShortName  = parameterTypes[parameterNumber].getSimpleName();
	     
	     /*
		 System.out.println("Parameter Type Short Name   : " + parameterTypeShortName);
		 System.out.println("ONE");
		 */
		 
		 parameterTypeClassName1 = parameterTypes[parameterNumber].getClass().toString();
		 
		 /*System.out.println("Parameter Type Class Name 1 : " + parameterTypeClassName1);
		 System.out.println("TWO");
		 */
		 
		 parameterPrimitiveType = parameterTypes[parameterNumber].isPrimitive();
		 
		 /*System.out.println("Parameter Primitive Type : "+parameterPrimitiveType);
		 System.out.println("THREE");
		 

         System.out.println("Parameter Type : "+ parameterTypes[parameterNumber]);
         System.out.println("FOUR");
         */
          
         if(parameterPrimitiveType == false)
         {
		    parameterTypePkgName = parameterTypes[parameterNumber].getPackage().toString();
		    parameterTypePkgName = parameterTypePkgName.replace("package ","");
		    //System.out.println("CASE ONE");  
		 }
		 else
		 {
		    parameterTypePkgName = parameterTypeClassName1.replace("class ","");
		    parameterTypePkgName = parameterTypePkgName.replace(".Class","");
		    //System.out.println("CASE TWO");
		 }
		 
		 /*
		 System.out.println("Parameter Type Pkg Name     : " + parameterTypePkgName);
		 System.out.println("FIVE");
		 */
		 
		 parameterTypeClassName2 = parameterTypePkgName + objectOrientedDelimiter1 + parameterTypeShortName;
		 
		 /*
		 System.out.println("Parameter Type Class Name 2 : " + parameterTypeClassName2);
		 System.out.println("SIX");
		 */
		 
		 parameterName           = parameterNames1[parameterNumber];
		 
		 /*
		 System.out.println("Parameter Name              : " + parameterName);
		 System.out.println("SEVEN");
		 */
		 
		 parameterValue          = parameterValues1[parameterNumber];
		 
		 /*
		 System.out.println("Parameter Value             : " + parameterValue);
		 System.out.println("EIGHT");
		 */
		 
		 if(parameterValue != null)
		 {
		 objectString = parameterValue.toString(); 
		 
		 /*
		 System.out.println("Object String               : " + objectString);
         System.out.println("NINE");
         */
         
		 pattern      = Pattern.compile("\\s", Pattern.CASE_INSENSITIVE);  
		 
		 /*
		 System.out.println("Pattern                     : " + pattern);
         System.out.println("TEN");
         */
               
		 matcher      = pattern.matcher(objectString); 
		 
		 /*
		 System.out.println("Matcher                     : " + matcher);
		 System.out.println("ELEVEN");	
		 */
		   
		 }
		 else
		 {
		    objectString = null;
		    pattern = null;
		    matcher=null;
		 }     
         
         
         //System.out.println("Annotation Type 1           : " + annotationType1);
         
		 
		 //parameterValuesCount = matcher.results().count(); 
		 //parameterValuesCount = matcher.toMatchResult.groupCount();
		 //parameterValuesCount = 0;
		 
		 if(objectString != null && objectString.isEmpty() == false)
		    parameterValuesCount = 1;
		 else
		    parameterValuesCount = 0;
		 
		 
		 /*
		 System.out.println("Parameter Values Count: " + parameterValuesCount);
		 
		 System.out.println("Parameter Type Class Name 2 : " + parameterTypeClassName2);
		 
		 System.out.println("Validating the KIND of Data Type of the Parameter : " + parameterTypeClassName2);
		 */
		 
		 
		 // Validate if the Parameter (parameterTypeClassName2 field) has a Personalized Class and it is not a Java Class
		 // The Parameter has a Personalized Class
		 if(parameterTypeClassName2.contains(programmingLanguage) == false)
		 { 
			 
		    //System.out.println("*** THE PARAMETER HAS A PERSONALIZED CLASS DATA TYPE AND MIGHT HAVE ATTRIBUTES ***");
			 
			// Obtain the Full Name of the Personalized Class 
		    try 
		    {
			   parameterTypeFullName = Class.forName(parameterTypeClassName2); 
			   //System.out.println("Parameter parameter Type Full Name : " + parameterTypeFullName); 
			} 
		    catch (Exception e1) 
		    {
		       errorMessage1 = e1.getMessage(); 
		       errorMessage2 = "Error XXXX: Occurred while getting the Class Name for the Parameter Type Class: " + System.lineSeparator();
		       errorMessage2 = errorMessage2 + parameterTypeClassName2 + System.lineSeparator();
		       errorMessage1 = errorMessage2 + errorMessage1;
		       System.out.println(errorMessage1);
			}
			
			//System.out.println("Parameter Type Full Name: " + parameterTypeFullName);
		    
		    if(annotationType1.equals("Parameter"))
		    {
		       parameterValue2    = parameterValue.toString();
			   parameterValue2    = parameterValue2.replaceAll("\\s+"," ");
			   annotationElement2 = annotationElement2.replaceAll("\\s+"," ");
				   
			   /*
			   System.out.println("PARAMETER TYPE Annotation-POINT ONE");
				     
			   System.out.println("Parameter Value 2    : " + parameterValue2);
			   System.out.println("Annotation Element 1 : " + annotationElement1);
			   System.out.println("Annotation Element 2 : " + annotationElement2);
			   */
			   
			   // Validate if the current parameter matches with the Annotation Element1	     
			   if (parameterName.equals(annotationElement1) == true)
			   {	
			     
			      parameterType = parameterValue2.getClass().getName();
				    	   
				  if(parameterType.contains(dataTypeShortClassName) == false)
				     parameterPLType = dataTypeFullClassName;
				  else
				     parameterPLType = parameterType;
				    	   
				  addLog1 = addNewLog(parameterPLType, parameterValue2, annotationElement2, annotationOperator);
				    	
				  /*
				  System.out.println("PARAMETER NAME: " + parameterName +" EQUALS TO " + annotationElement1);
				  System.out.println("Parameter Type    : " + parameterType);
				  System.out.println("Parameter PL Type : " + parameterPLType);
				  System.out.println("Parameter Value   : " + parameterValue2);
				  
				    	
				  System.out.println("Validating Adding a Log for a Parameter (CASE ONE): " + addLog1);
				  */
				  
				    	   
				  if(addLog1 == true)
				  {	
				     methodReturnTypeValueStr = "";
							    
					 if (methodReturnTypeValue1 != null)	
					    methodReturnTypeValueStr = methodReturnTypeValue1.toString();
						   
					 /*
				     System.out.println("Method Return Type Value Str: " + methodReturnTypeValueStr);
								
					 System.out.println("VALIDATE HERE IF THE PARAMETER HAS AN ATTRIBUTE IN THE CONDITION");
					 System.out.println("Annotation Condition: " + annotationCondition);			
					 			
					 System.out.println("Add a Header Log");
					 */
					 
						   
					 addLogHeader();
						   
					 parameterType = parameterTypeClassName2;
		             parameterType = parameterType.replace("class "," ");
		               
		             /*   
		             System.out.println("Parameter Value 2 : " + parameterValue2);
		             
		             System.out.println("Parameter Type    : " + parameterType);
		                
				     System.out.println("Add a Parameter Log");
				     */
						         
				     /* Evaluation Start (TIME 1) */
	                 time1 = System.currentTimeMillis();
	                 /* Evaluation End */
	                            		         
				     // Logging Instructions for the value of the Parameter
					 log.info("Parameter Name : " + parameterName);
					 log.info("Parameter Type : " + parameterType);
				     //log.info("Parameter Value     : " + parameterValue2);
				     
				     /* Evaluation Start (TIME 2) */
	                 time2 = System.currentTimeMillis();
	                 duration = time2 - time1;
	                 System.out.println("Message FRL: The Security Log for a PARAMETER Annotation Type was generated in: " + duration + " milliseconds"); 
	                 /* Evaluation End */
				     
						         
			      }
			   }
				     
		       }
		       else
		          if(annotationType1.equals("Method") == true || annotationType1.equals("ReturnType") ==true )
		          {
		           
		             // Get the Parameter Type
		             //parameterValue2 = parameterValue.toString();
		             //parameterType   = parameterValue2.getClass().getName();
		             parameterType = parameterTypeClassName2;
		             parameterType = parameterType.replace("class "," ");
		                
		             //System.out.println("Parameter Value 2 : " + parameterValue2);
		             
		             /*
		             System.out.println("METHOD OR RETURN TYPE Annotation-POINT ONE");
		             System.out.println(annotationType1 + " Type Annotation");
		             System.out.println("Parameter Name    : " + parameterName);
		             System.out.println("Parameter Type    : " + parameterType);
		             System.out.println("Parameter Value   : " + parameterValue2);
		             
		             
		             
		             System.out.println("Add a Parameter Log");
		             */
		                
		             /* Evaluation Start (TIME 1) */
	                 time1 = System.currentTimeMillis();
	                 /* Evaluation End */
	                               
		             // Add a Log for the Parameter
			         log.info("Parameter Name : " + parameterName);
			         log.info("Parameter Type : " + parameterType);
			         //log.info("Parameter Value     : " + parameterValue2);
			         
			         /* Evaluation Start (TIME 2) */
	                 time2 = System.currentTimeMillis();
	                 duration = time2 - time1;
	                 System.out.println("Message FRL: The Security Log for a METHOD OR RETURN TYPE Annotation Type was generated in: " + duration + " milliseconds"); 
	                 /* Evaluation End */
				           
				     addLog1 = true;
				        
				     System.out.println("Add Log 1         : " + addLog1);
		           }
		       
		       //System.out.println("*** Verifiying if the Parameter has attributes ***");
		       
		       attributeCount = parameterTypeFullName.getDeclaredFields().length;
		       
		       //System.out.println("Attribute Count: " + attributeCount);
		       
		       if(attributeCount > 0)
		       {	  
		          //System.out.println("REVIEW TIMING OF THE PARAMETER"); 
	              if(annotationType1.equals("Method") == true || annotationType1.equals("ReturnType") ==true )  
	              {	  
			         /* Evaluation Start (TIME 1) */
		             time1 = System.currentTimeMillis();
		             /* Evaluation End */
		              
		             log.info("The Parameter: " + parameterName + " has " + attributeCount + " Attributes");
		          
		             /* Evaluation Start (TIME 2) */
	                 time2 = System.currentTimeMillis();
	                 duration = time2 - time1;
	                 System.out.println("Message FRL: The Security Log for a METHOD, PARAMETER OR RETURN TYPE Annotation Type was generated in: " + duration + " milliseconds");
	                 /* Evaluation End */
	              }
		          
		          /*
		          System.out.println("The Parameter has: " + attributeCount + " Attributes");
		          System.out.println("TWO"); 
		          
	              
	              
	              System.out.println("BEFORE LOGGING THE ATTRIBUTES OF THE PARAMETER : " + parameterName + " " + parameterType);
	              */
	              
		          addLog1 = attributeAnnotation(attributeCount, parameterName, parameterTypeFullName, 
                                                parameterValue, annotationType1, annotationElement1, 
                                                methodReturnTypeValue1, "Attribute");
		       
		          //System.out.println("AFTER LOGGING THE ATTRIBUTES OF THE PARAMETER - Add Log 1 - Attributes : " + addLog1);
		       }
		       		    
		  }
		  else
		  {
		     // The Parameter has a PRIMITIVE DATA TYPE(int, char, String, etc)
			 // and does not have any Attributes 
			 /*System.out.println("Parameter Values Count: " + parameterValuesCount );
			 
			 System.out.println("Validating the Parameter Values Count: " + parameterValuesCount);
			 */
			  
		     if(parameterValuesCount == 1)
		     {
		        //System.out.println("*** THE PARAMETER HAS A PRIMITIVE DATA TYPE AND DOES NOT HAVE ATTRIBUTES ***");
		        
		        if(annotationType1.equals("Method") == true || annotationType1.equals("ReturnType") ==true)
		        {
		           //System.out.println("Adding a Log for a Parameter");
		           
		           // Get the Parameter Type
		           parameterValue2 = parameterValue.toString();
		           parameterType   = parameterValue2.getClass().getName();
	               
	               /*
	               System.out.println("METHOD OR RETURN TYPE Annotation-POINT TWO");
		           System.out.println(annotationType1 + " Type Annotation");
		           System.out.println("Parameter Name    : " + parameterName);
		           System.out.println("Parameter Type    : " + parameterType);
		           System.out.println("Parameter Value   : " + parameterValue2);
		           */
		           
		           /* Evaluation Start (TIME 1) */
	               time1 = System.currentTimeMillis();
	               /* Evaluation End */
	                            
		           // Logging Instructions for the value of the Attributes
				   log.info("Parameter Name  : " + parameterName);
			       log.info("Parameter Type  : " + parameterType);
				   log.info("Parameter Value : " + parameterValue2);
				   
				   /* Evaluation Start (TIME 2) */
	               time2 = System.currentTimeMillis();
	               duration = time2 - time1;
	               System.out.println("Message FRL: The Security Log for a METHOD OR RETURN TYPE Annotation Type was generated in: " + duration + " milliseconds"); 
	               /* Evaluation End */
				           
				   addLog1 = true;
				   
				   //System.out.println("Add a Parameter Log: " + addLog1); 
		        }
		        else
		        {
		           if(annotationType1.equals("Parameter"))
			       {
			          parameterValue2    = parameterValue.toString();
					  parameterValue2    = parameterValue2.replaceAll("\\s+"," ");
					  annotationElement2 = annotationElement2.replaceAll("\\s+"," ");
					    
					  /*
					  System.out.println("Parameter Type Annotation");
					  System.out.println("Parameter Value 2    : " + parameterValue2);
					  System.out.println("Annotation Element 1 : " + annotationElement1);
					  System.out.println("Annotation Element 2 : " + annotationElement2);
					  
					  
					  System.out.println("PARAMETER TYPE Annotation-POINT TWO");
			          System.out.println(annotationType1 + " Type Annotation");
			          System.out.println("Parameter Name    : " + parameterName);
			          System.out.println("Parameter Type    : " + parameterType);
			          System.out.println("Parameter Value   : " + parameterValue2);
			          */
					     
					  if (parameterName.equals(annotationElement1) == true)
					  {	
					     parameterType = parameterValue2.getClass().getName();
					    	   
					     if(parameterType.contains(dataTypeShortClassName) == false)
					        parameterPLType = dataTypeFullClassName;
					     else
					        parameterPLType = parameterType;
					    	   
					     addLog1 = addNewLog(parameterPLType, parameterValue2, annotationElement2, annotationOperator);
					    	
					     /*
					     System.out.println("Parameter Type    : " + parameterType);
					     System.out.println("Parameter PL Type : " + parameterPLType);
					    	
					     System.out.println("Validating adding a Log for a Parameter (CASE TWO): " + addLog1);
					     */
					     
					    	   
						 if(addLog1 ==true)
						 {	
						    methodReturnTypeValueStr = "";
								    
							if (methodReturnTypeValue1 != null)	
							   methodReturnTypeValueStr = methodReturnTypeValue1.toString();
							 
							/*
							System.out.println("Method Return Type Value Str: " + methodReturnTypeValueStr);
									
							System.out.println("Add a Header Log");
							*/
							
							   
							addLogHeader();
							   
							//System.out.println("Add a Parameter Log");
							       
							/* Evaluation Start (TIME 1) */
	                        time1 = System.currentTimeMillis();
	                        /* Evaluation End */
	                                     
					        // Logging Instructions for the value of the Parameter
						    log.info("Parameter Name  : " + parameterName);
						    log.info("Parameter Type  : " + parameterType);
							log.info("Parameter Value : " + parameterValue2);
							
							/* Evaluation Start (TIME 2) */
	                        time2 = System.currentTimeMillis();
	                        duration = time2 - time1;
	                        System.out.println("Message FRL: The Security Log for a PARAMETER Annotation Type was generated in: " + duration + " milliseconds"); 
	                        /* Evaluation End */
							         
						 }
					  }
					     
			        }
		        }
		     }
		  }
      }
	  
      return addLog1;
      

   }
   
   public void addLogHeader()
   {
      
      //System.out.println("*** CREATING THE LOG HEADER WITH THE OS and METHOD INFO ***");
      
      PropertyConfigurator.configure(log4jConfPath); 
      log = Logger.getLogger(methodFullClassName2);
      
      /* Evaluation Start (TIME 1) */
	  time1 = System.currentTimeMillis();
	  /* Evaluation End */
	                            
	  log.info("OS Name             : " + oSName);
	  log.info("OS Current Time     : " + df1.format(date));
	  log.info("OS IP Address       : " + oSIP);
	  log.info("OS Hostname         : " + oSHostname);
	  log.info("OS UserName         : " + oSUserName);
	  log.info("Annotation Type     : " + annotationType);
	  log.info("Method Name         : " + methodFullName2);
	  log.info("Method Return Type  : " + methodReturnType);
	  log.info("Method Return Value : " + methodReturnTypeValueStr);
	  
	  /* Evaluation Start (TIME 2) */
	  time2 = System.currentTimeMillis();
	  duration = time2 - time1;
	  System.out.println("Message FRL: The Security Log for the HEADER was generated in: " + duration + " milliseconds"); 
	  /* Evaluation End */
	  	  
   }
   
   pointcut loggingMethods(): 
      call(void org.isf.menu.gui.Login.acceptPwd(..))||
      call(List<org.isf.menu.model.UserMenuItem> org.isf.menu.manager.UserBrowsingManager.getMenu(..))||
      call(List<org.isf.exa.model.Exam> org.isf.exa.manager.ExamBrowsingManager.getExams(..))||
      call(List<org.isf.lab.model.Laboratory> org.isf.lab.manager.LabManager.getLaboratory(..))||
      call(List<org.isf.patient.model.Patient> org.isf.patient.manager.PatientBrowserManager.getPatientsByOneOfFieldsLike(..))||
      call(org.isf.patient.model.Patient org.isf.patient.manager.PatientBrowserManager.getPatientById(..))||
      call(org.isf.admission.model.Admission org.isf.admission.manager.AdmissionBrowserManager.getCurrentAdmission(..))||
      call(List<org.isf.exa.model.ExamRow> org.isf.exa.manager.ExamRowBrowsingManager.getExamRowByExamCode(..))||
      call(boolean org.isf.lab.manager.LabManager.newLaboratory2(..))||
      call(org.isf.patient.model.Patient org.isf.patient.manager.PatientBrowserManager.getPatientAll(..))||
      call(boolean org.isf.lab.manager.LabManager.updateLaboratory(..))||
      call(boolean org.isf.lab.manager.LabManager.deleteLaboratory(..));                  
   
   after() returning(Object methodReturnTypeValue): loggingMethods() && args(..)
   {
       
	      //System.out.println("*** Inside the AFTER Logging Methods Pointcut ***");
	   
		  // Get the General Information about the Method
		  method               = thisJoinPoint.getSignature();
		  
		  methodPackageName = method.getDeclaringType().getPackage().toString();
          methodPackageName = methodPackageName.replace("package ","");
		  
		  methodClassName      = method.getDeclaringType().getSimpleName();
		  methodFullClassName1 = methodPackageName + objectOrientedDelimiter1 + methodClassName; 
		  
		  methodShortName      = method.getName();
		  methodFullName1      = method.toString();
		  methodArgs           = thisJoinPoint.getArgs();
		  methodFullName2      = methodPackageName + objectOrientedDelimiter1 + methodClassName + objectOrientedDelimiter1 + methodShortName;

          
          /*
		  System.out.println("*** CURRENT METHOD INFORMATION ***");
          System.out.println("Method                   : " + method);
          System.out.println("Method Package Name      : " + methodPackageName);
          System.out.println("Method Class Name        : " + methodClassName);
          System.out.println("Method Full Class Name 1 : " + methodFullClassName1);
          System.out.println("Method Short Name        : " + methodShortName);
          System.out.println("Method Full Name 1       : " + methodFullName1);
          System.out.println("Method Args              : " + methodArgs);
          System.out.println("Method Full Name 2       : " + methodFullName2);
          System.out.println("Method Return Type Value : " + methodReturnTypeValue);    
          */
               
	       
		  if (methodReturnTypeValue != null)
		  {	  
		     methodReturnType = methodReturnTypeValue.getClass().getName();
		  }   
		  else
		  {	  
		      if( methodFullName1.contains("void"))
		      {
		         methodReturnType      = "void";
		         methodReturnTypeValue = "";
		      } 
		      else
		      {
		         methodReturnType    = "";
		         methodReturnTypeValue = "";
		      }  
		  }   
		  
		  /*
		  System.out.println("Method Return Type       : " + methodReturnType);
		  System.out.println("Method Return Type Value : " + methodReturnTypeValue);
		  */
		  
		  
		  if(methodReturnType.contains(dataTypeShortClassName) == false)
		     methodPLReturnType = dataTypeFullClassName;
		  else
			  methodPLReturnType = methodReturnType;

		  // Getting information about the Operating System
		  oSUserName = System.getProperty("user.name");
		  oSName     = System.getProperty("os.name");
		  
		  /*
		  System.out.println("Data Type Short Class Name : " + dataTypeShortClassName);
		  System.out.println("Data Type Full Class Name  : " + dataTypeFullClassName);
		  System.out.println("Method PL Return Type      : " + methodPLReturnType);
		  
		  System.out.println("OS User Name               : " + oSUserName);
		  System.out.println("OS Name                    : " + oSName);
		  */
		  
		  // Getting the IP Address
		  try
		  {
		     oSIP = InetAddress.getLocalHost();
		     oSHostname = oSIP.getHostName();
		  }
		  catch (UnknownHostException e1) 
		  {
		     errorMessage1  = e1.getMessage(); 
	         errorMessage2  = "Error XXXX: Occurred while getting the IP Address" + System.lineSeparator();
	         errorMessage1  = errorMessage2 + errorMessage1;
	         System.out.println(errorMessage1);
		  }
		  
		  /*
		  System.out.println("O SIP                    : " + oSIP);
		  System.out.println("OS HOst Name             : " + oSHostname);
		  */

		  // Getting the Class Name for the Method
		  try 
		  {
		     methodFullClassName2 = Class.forName(methodFullClassName1);
		  } 
		  catch (ClassNotFoundException e2) 
		  {
		     errorMessage1 = e2.getMessage(); 
		     errorMessage2 = "Error XXXX: Occurred while getting the Class Name for the Method: " + System.lineSeparator();
		     errorMessage2 = errorMessage2 + methodFullClassName1 + System.lineSeparator();
		     errorMessage1 = errorMessage2 + errorMessage1;
		     System.out.println(errorMessage1);
		  }
		  
		  //System.out.println("Method Full Class Name 2 : " + methodFullClassName2);

		  // Get the annotations related to this Method from an Input TextFile
		  annotationList = getAnnotationLines(annotationFile, methodFullName2);
		  
		  
		  /*
		  System.out.println("*** ANNOTATION FILE INFORMATION ***");
		  System.out.println("Annotation List          : " + annotationList);
		  */

		  // Loop into the annotationList ArrayList
		  for (a = 0; a < annotationList.size(); a++) 
		  {
		     // Get the Annotation Line 	  
		     annotationLine = annotationList.get(a);
		     
		     // Get the Annotation Fields 
		     getAnnotationFields(annotationLine);
		     
		     // Get the Current Date, Time and TimeZone
			 date = new Date();
			 df1 = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss aa z");
			 df1.setTimeZone(TimeZone.getDefault());
			 
			 df2 = new SimpleDateFormat("dd_MM_YYYY");
			 df2.setTimeZone(TimeZone.getDefault());
		     System.setProperty("current.date.time", df2.format(date));
		     	
		     /*
		     System.out.println("*** ANNOTATION INFORMATION ***");	
			 System.out.println("Annotation Line              : " + annotationLine);	
			 System.out.println("Annotation Type              : " + annotationType);   
			 System.out.println("Annotation Method Owner      : " + annotationMethodOwner);
			 System.out.println("Annotation Method Full Name  : " + annotationMethodFullName);
		     System.out.println("Annotation Condition         : " + annotationCondition);
		     System.out.println("Annotation Element 1         : " + annotationElement1);
		     System.out.println("Annotation Element 2         : " + annotationElement2);
		     System.out.println("Annotation Condition         : " + annotationCondition);

		     System.out.println("Annotation Method Short Name : " + annotationMethodShortName);		     
		     System.out.println("Method Short Name            : " + methodShortName);
		     
             */
		     		     
			 // Validate if the current executed method is one of the annotated method
		     // Then create a new log Entry
			 if(annotationMethodShortName.contains(methodShortName))
			 {
				//System.out.println("CURRENT METHOD: " + methodShortName + " EQUALS " + "ANNOTATION Method: " + annotationMethodShortName);
				 
		    	// Get the Information about the Parameters: Names and Types
		    	parameterNames  = ((CodeSignature) thisJoinPoint.getSignature()).getParameterNames();
		    	parameterTypes  = ((CodeSignature) thisJoinPoint.getSignature()).getParameterTypes();
		    	parameterCount  = parameterNames.length;		  
		    	parameterValues = thisJoinPoint.getArgs();
		    	
		    	/*
		    	System.out.println("*** PARAMETERS INFORMATION ***");
		    	System.out.println("Parameter Names  : " + Arrays.toString(parameterNames));
		    	System.out.println("Parameter Types  : " + Arrays.toString(parameterTypes));
		    	System.out.println("Parameter Count  : " + parameterCount);
		    	System.out.println("Parameter Values : " + Arrays.toString(parameterValues));
		    	*/
		    	   
			    if(annotationType.equals("Method"))
				{  

				   methodReturnTypeValueStr = "";
	
				   if (methodReturnTypeValue != null)	
				      methodReturnTypeValueStr = methodReturnTypeValue.toString();
				   else   
				      methodReturnTypeValueStr = "";
				   
                   
				   /*
				   System.out.println("*** METHOD TYPE ANNOTATION ***");
                   System.out.println("Method Return Type Value Str: " + methodReturnTypeValueStr);
                   
                   System.out.println("Add a Header Log-Method");
                   */
                   
				   addLogHeader();
				   
				   //System.out.println("BEFORE PARAMETER ANNOTATION");
		    	   addLog = parameterAnnotation(parameterNames, parameterTypes, parameterCount, parameterValues, "Method", methodReturnTypeValue);
                   
                   /* 
                   System.out.println("AFTER PARAMETER ANNOTATION");

		    	   System.out.println("Add a Parameter Log-Method: " + addLog);
		    	   */
		    	   
                   if(addLog == false)
                   {
                      /* Evaluation Start (TIME 1) */
	                  time1 = System.currentTimeMillis();
	                  /* Evaluation End */
	                            
				      // Add a line indicating there is NO INPUT Parameters
				      log.info("The Method: " + methodShortName + " DOEST NOT have any INPUT Parameters");
				      
				      /* Evaluation Start (TIME 2) */
	                  time2 = System.currentTimeMillis();
	                  duration = time2 - time1;
	                  System.out.println("Message FRL: The Security Log for a METHOD Annotation Type was generated in: " + duration + " milliseconds"); 
	                  /* Evaluation End */
                   }
                   
				}
			    else
			       if(annotationType.equals("ReturnType"))
				   {

			    	  //System.out.println("*** RETURN TYPE ANNOTATION ***");
				      
			    	  // Validate if we are going to create a new log depending on the Return Type
			    	  addLog = addNewLog(methodPLReturnType, methodReturnTypeValue.toString(), annotationElement2, annotationOperator);
						
					  	
			    	  /*
					  System.out.println("Method PL Return Type    : " + methodPLReturnType);	
					  System.out.println("Method Return Type Value : " + methodReturnTypeValue);	
					  System.out.println("Annotation Element 2     : " + annotationElement2);
					  System.out.println("Annotation Operator      : " + annotationOperator);
					  */
					  
			    	  //System.out.println("Validation to add a Log for the Return Type Annotation: " + addLog);
						
					  if(addLog == true)
					  {	 
						 methodReturnTypeValueStr = "";
						    
					     if (methodReturnTypeValue != null)	
						    methodReturnTypeValueStr = methodReturnTypeValue.toString();

                         //System.out.println("Method Return Type Value Str: " + methodReturnTypeValueStr);
                   
                         //System.out.println("Add a Log Header-Method");
					     
					     addLogHeader();
					   		
			    	     addLog = parameterAnnotation(parameterNames, parameterTypes, parameterCount, parameterValues, "ReturnType", methodReturnTypeValue);
			    	     
			    	     //System.out.println("Add a Parameter Log-Method : " + addLog);

					  }
				   }
			       else
			          if(annotationType.equals("Parameter"))
			    	  {
			        	 //System.out.println("*** PARAMETER TYPE ANNOTATION ***");
			    	  			    			
						 log = Logger.getLogger(methodFullClassName2);
						 
			    		 addLog = parameterAnnotation(parameterNames, parameterTypes, parameterCount, parameterValues, "Parameter", methodReturnTypeValue);
			    		 
						 //System.out.println("Validation to add a Log for the Parameter Annotation: " + addLog);
			    		
			    		 if(addLog == true)
						 {	
							
							methodReturnTypeValueStr = "";
							    
						    if (methodReturnTypeValue != null)	
							   methodReturnTypeValueStr = methodReturnTypeValue.toString();
							   
							//System.out.println("Method Return Type Value Str: " + methodReturnTypeValueStr);   
						 }
			    		 
			    	  }
		           }
	           }
		  
           }	   
   }
