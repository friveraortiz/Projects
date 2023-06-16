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


import model.UserLevel;

public aspect FrlLoggingMethods
{
   String annotationFile="/Users/fannyriveraortiz/eclipse-workspace/Directories/Output/HRMApplication_Annotations.txt";
   String doNotLogAttributes="";
   String findWhiteSpaces="\\s+";
   String objectOrientedDelimiter1=".";
   String programmingLanguage="java";
   String dataTypeFullClassName="java.lang.Class";
   String dataTypeShortClassName="java.lang.";
   ArrayList<String> dataTypeStringList=new ArrayList<>(Arrays.asList("java.lang.Character", "java.lang.String", "java.lang.Class", "java.lang.Boolean"));
   ArrayList<String> dataTypeNumberList=new ArrayList<>(Arrays.asList("java.lang.Integer", "java.lang.Float", "java.lang.Double", "java.lang.Long", "java.lang.Short"));

   ArrayList<String> annotationList = new ArrayList<String>();

   String errorMessage1="",errorMessage2="", methodPackageName="", methodClassName="", methodFullClassName1="", methodShortName="", 
		  methodFullName1="", methodFullName2="", methodReturnType="", methodPLReturnType="", methodCondition="", 
		  methodReturnTypeValueStr="", parameterTypeShortName="", parameterTypePkgName="", parameterTypeClassName1="", 
		  parameterTypeClassName2="", parameterName="", attributeShortName="",
		  annotationLine="", annotationType="", annotationMethodShortName="", annotationMethodFullName="", 
		  annotationCondition="", annotationMethodOwner="", annotationElement1="", annotationElement2="", annotationOperator="", 
		  annotationAttributeType="", annotationAttributePLType="", oSName="", oSUserName="", oSHostname="";
  
   String[] parameterNames;
   Class<?> parameterTypeFullName=null, attributeType=null, methodFullClassName2=null;
   Class<?>[] parameterTypes;
   Object[] methodArgs, parameterValues;
   Object parameterValue=null, attributeValue=null;
   Field attributeFullName=null;
   Signature method;
   
   int i=0, a=0, len=0, parameterCount=0, parameterNumber=0, attributeNumber=0, atttributeCount=0, annotationMethodId=0;
      
   boolean addLog=false;

   Date date;
   DateFormat df1, df2;
   SimpleDateFormat dateFormat;
   
   InetAddress oSIP;

   Logger log;
   
   /*Evaluation Start */
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
      
      // Compare if the Data Type Programming Language is a String, boolean or a Class
      if(dataTypeStringList.contains(dataTypeProgrLanguage1))
	  {
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
	  }
      
      else
    	 // Compare if the Data Type Programming Language is a Numeric Data Type
         if(dataTypeNumberList.contains(dataTypeProgrLanguage1))
    	 {
        	 dataTypeProgrLanguage2 = dataTypeProgrLanguage1.replace(dataTypeShortClassName, "");
            
            switch (dataTypeProgrLanguage2) 
        	{
        	   case "Integer":
        	      integerValue1 = Integer.parseInt(value1);
        		  integerValue2 = Integer.parseInt(value2);
        	   break;
        	   
        	   case "Float":
         	      floatValue1 = Float.parseFloat(value1);
         		  floatValue2 = Float.parseFloat(value2);
         	   break;
         	   
           	   case "Double":
          	      doubleValue1 = Double.parseDouble(value1);
          		  doubleValue2 = Double.parseDouble(value2);
          	   break;
          	   
           	   case "Long":
           	      longValue1 = Long.parseLong(value1);
           		  longValue2 = Long.parseLong(value2);
           	   break;
           	   
          	   case "Short":
                  shortValue1 = Short.valueOf(value1);
            	  shortValue2 = Short.valueOf(value2);
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
    	 
      //System.out.println("Log Flag                : " + logFlag);	
      //System.out.println("End ...");
      
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
	           //System.out.println("Annotation Type            : "+annotationType);
	        break;
	        
	        case 2:
	           annotationMethodId = Integer.parseInt(tokenText);
	           //System.out.println("Annotation Method Id       : "+annotationMethodId);
	        break;
	        
	        case 3:
	           annotationMethodFullName = tokenText;
	           //System.out.println("Annotation Method Full Name : "+annotationMethodFullName);
	        break;
	        
	        case 4:
	           annotationMethodOwner = tokenText;
	           //System.out.println("Annotation Method Owner     : "+annotationMethodOwner);
	        break;
	        
	        case 5:
	           annotationMethodShortName = tokenText;
	           //System.out.println("Annotation Method Short Name : "+annotationMethodShortName);
	        break;
	        
	        case 6:
	           annotationCondition = tokenText;
	           //System.out.println("Annotation Condition         : "+annotationCondition);
	        break;
	        
	        case 7:
		       annotationElement1 = tokenText;
		       //System.out.println("Annotation Element 1         : "+annotationElement1);
		    break;
	        
	        case 8:
		       annotationOperator = tokenText;
		       //System.out.println("Annotation Operator          : "+annotationOperator);
		    break;
		    
	        case 9:
		       annotationElement2 = tokenText;
		       //System.out.println("Annotation Element 2         : "+annotationElement2);
		    break;
	        
	     }

      }
	  
	  //System.out.println("Ending getAnnotationFields Method ...");
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
   
   public boolean parameterAnnotation(String[] parameterNames1, Class<?>[] parameterTypes1, int parameterCount1, Object[] parameterValues1, String annotationType)
   {
      boolean addLog1=false;
      String objectString="";
      Pattern pattern;
      Matcher matcher;
      long parameterValuesCount=0;
      String parameterValue2="", parameterType="", parameterPLType="";
	  
	  //System.out.println("Starting Parameter Annotation Method ...");
	  
	  for (parameterNumber=0; parameterNumber < parameterCount; parameterNumber++) 
	  {
	     parameterTypeShortName  = parameterTypes[parameterNumber].getSimpleName();
		 parameterTypePkgName    = parameterTypes[parameterNumber].getPackageName();
		 parameterTypeClassName1 = parameterTypes[parameterNumber].getClass().toString();
		 parameterTypeClassName2 = parameterTypePkgName + objectOrientedDelimiter1 + parameterTypeShortName;
		 parameterName           = parameterNames[parameterNumber];
		 parameterValue          = parameterValues[parameterNumber];
		 
		 objectString = parameterValue.toString(); 
		 pattern      = Pattern.compile("\\s", Pattern.CASE_INSENSITIVE);        
		 matcher      = pattern.matcher(objectString);        
		 parameterValuesCount = matcher.results().count();  
		 
		 /*
		 if (annotationType.equals("Method"))
		 {  
			System.out.println("PARAMETER VALUES");
	        System.out.println("Parameter Type ShortName    : "+parameterTypeShortName);  
	        System.out.println("Parameter Type Package Name : "+parameterTypePkgName); 
	        System.out.println("Parameter Type Class Name 1 : "+parameterTypeClassName1); 
	        System.out.println("Parameter Type Class Name 2 : "+parameterTypeClassName2); 
	        System.out.println("Parameter Name              : "+parameterName); 
	        System.out.println("Parameter Values            : "+parameterValue); 
	        System.out.println("Parameter Values Count      : "+parameterValuesCount); 
		 }
		 */
		       
		 if(parameterTypeClassName2.contains(programmingLanguage) == false)
		 { 
		    try 
		    {
			   parameterTypeFullName = Class.forName(parameterTypeClassName2); 
			   //System.out.println("Parameter parameter Type Full Name : "+parameterTypeFullName); 
			} 
		    catch (Exception e1) 
		    {
		       errorMessage1 = e1.getMessage(); 
		       errorMessage2 = "Error XXXX: Occurred while getting the Class Name for the Parameter Type Class: " + System.lineSeparator();
		       errorMessage2 = errorMessage2 + parameterTypeClassName2 + System.lineSeparator();
		       errorMessage1 = errorMessage2 + errorMessage1;
		       System.out.println(errorMessage1);
			}
		    
            // Counting the parameter values
		    if(annotationType.equals("Parameter") && parameterValuesCount	== 0)
		    {
		    	//System.out.println("ENTRE UNO");
		    	
		    	parameterValue2    = parameterValue.toString();
		    	parameterValue2    = parameterValue2.replaceAll("\\s+"," ");
		    	annotationElement2 = annotationElement2.replaceAll("\\s+"," ");
		    	
		    	//System.out.println("COMPARING " + parameterName + " WITH " + annotationElement1 + " " + parameterName.equals(annotationElement1));
		    	
		    	//System.out.println("COMPARING " + parameterValue2 + " WITH " + annotationElement2 + " " + parameterValue2.equals(annotationElement2));
		    	
		    	if (parameterName.equals(annotationElement1) == true)
		    	{	
		    	   //System.out.println("ENTRE DOS");
		    	   
		    	   /*if(parameterValue2.equals(annotationElement2) == true)
		    	      System.out.println("THEY ARE EQUAL");
		    	      */
		    	   
		    	   //System.out.println("annotationAttributePLType : "+annotationAttributePLType);
		    	   
		    	   parameterType = parameterValue2.getClass().getName();
		    	   
		    	   if(parameterType.contains(dataTypeShortClassName) == false)
		    	      parameterPLType = dataTypeFullClassName;
		    	   else
		    		   parameterPLType = parameterType;
		    	   
		    	   /*
		    	   System.out.println("*** CHECK THIS OUT ***");	 
		    	   
		    	   System.out.println("Parameter Value2   : " + parameterValue2);
		    	   System.out.println("Parameter Type     : " + parameterType);
		    	   System.out.println("Parameter PL Type  : " + parameterPLType);
		    	   */
		    	   
		    	   addLog1 = addNewLog(parameterPLType, parameterValue2, annotationElement2, annotationOperator);
		    	   
		    	   //System.out.println("Add Log 1: "+addLog1);
		    	   
				   if(addLog1 ==true)
				   {	
					   		
				    	 //System.out.println("Before the Security Log Header PARAMETER");
				    		 
				         addLogHeader();
				    	 
				         //System.out.println("After the Security Log Header PARAMETER");
				         
						 /* Evaluation Start */
						 time1 = System.currentTimeMillis();
						 /* Evaluation End */
				         
						 //System.out.println("Before the Security Log Details PARAMETER");
				    		 
				         // Logging Instructions for the value of the Attributes
				         log.info("Attribute Name      : " + parameterName);
				         log.info("Attribute Type      : " + parameterTypeClassName2);
				         log.info("Attribute Value     : " + parameterValue2);
				         
				         //System.out.println("After the Security Log Details PARAMETER");
				         
					  	 /* Evaluation Start */
					  	 time2 = System.currentTimeMillis();
					  	 duration = time2 - time1;
					  	 /* Evaluation End */
					  	 
					  	 System.out.println("Message FRL: The Security Log for a Parameter was generated in : " + duration + " milliseconds");
					        
					  	 /* Evaluation End */

				   }
		    	}
		    }
		    else
		    {	
		    	
		       atttributeCount = parameterTypeFullName.getDeclaredFields().length;
		    
		    //System.out.println("Parameter Atributes            : "+atttributeCount); 
		        
		       for (attributeNumber=0; attributeNumber < atttributeCount; attributeNumber++)
		       {
		    	
		          attributeShortName = parameterTypeFullName.getDeclaredFields()[attributeNumber].getName();
		          attributeType      = parameterTypeFullName.getDeclaredFields()[attributeNumber].getType();
		           
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
			      
			   
			      if(attributeValue != null && 
				     attributeValue.toString().equals("0") == false && 
				     attributeValue.toString().equals("0.0") == false)
			      {	   
			         annotationAttributeType = attributeValue.getClass().getName();
			          
				     if(annotationAttributeType.contains(dataTypeShortClassName) == false)
				        annotationAttributePLType = dataTypeFullClassName;
				     else
				        annotationAttributePLType = annotationAttributeType;
				  
				  /*
				  if (annotationType.equals("Method"))
				  {
					  System.out.println("ATTRIBUTES VALUES");
					  System.out.println("Attribute Short Name   : " + attributeShortName);
					  System.out.println("Attribute Type         : " + attributeType);
					  System.out.println("Attribute Full Name    : " + attributeFullName);
					  System.out.println("Attribute Value        : " + attributeFullName);
				  }
				  */
				  
				  
				     if(annotationType.equals("Method") == true || annotationType.equals("ReturnType") == true)
				     {	  
				        if(doNotLogAttributes.contains(attributeShortName) == false)
				        {	 

				  	       /* Evaluation Start */
				  	       time1 = System.currentTimeMillis();
				  	       /* Evaluation End */
				  	    
				   	       //System.out.println("Message FRL: The Security Log a Method or a ReturnType Started at: " + str1);
				   	  
				           // Logging Instructions for the value of the Attributes
			               log.info("Attribute Name      : " + attributeShortName);
			               log.info("Attribute Type      : " + attributeType);
				           log.info("Attribute Value     : " + attributeValue);
				        
				        
				   	    //System.out.println("Message FRL: The Security Log for a Method or a ReturnType Ended at: " + str2);
				   	    
				  	       // Calculate the time between the start and the end of the generation of the Security Log
				  	       /* Evaluation Start */
				  	       time2 = System.currentTimeMillis();
				  	       duration = time2 - time1;
				  	       /* Evaluation End */
				  	       System.out.println("Message FRL: The Security Log for a Method or a ReturnType was generated in : " + duration + " milliseconds");
				        
				  	       /* Evaluation End */
				        
				        }
				     }
				  
			      }
			     else
			        annotationAttributeType = "";
			   
			   
			   /*
			    if (annotationType.equals("Parameter"))
				{
				
			    	System.out.println("Annotation Element1            : " + annotationElement1);
			    	System.out.println("Annotation Element2            : " + annotationElement2);	
			    	System.out.println("Do Not Log Attributes          : " + doNotLogAttributes);	
			    	
				}
			       
			    System.out.println("COMPARING " + attributeShortName + " WITH " + annotationElement1);
			    */
			    
		    	   if (attributeShortName.equals(annotationElement1))
		    	   {	
		    	   /*System.out.println("THEY ARE EQUAL");
		    	   	
		    	   System.out.println("2.- Before Add New Log 1 PARAMETER Annotation DETAILS ");
		    	   */
		    	   
			          addLog1 = addNewLog(annotationAttributePLType, attributeValue.toString(), annotationElement2, annotationOperator);

			       //System.out.println("2.- After Add New Log 1 RETURN TYPE Annotation DETAILS: "+addLog1);
			       
				      if(annotationType.equals("Parameter") == true && addLog1 == true)
				      {	
					   		
				         if(doNotLogAttributes.contains(attributeShortName) == false)
				         {	  

				    	 //System.out.println("Before the Security Log Header PARAMETER");
				    		 
				            addLogHeader();
				    	 
				         //System.out.println("After the Security Log Header PARAMETER");
				         
						    /* Evaluation Start */
						    time1 = System.currentTimeMillis();
						    /* Evaluation End */
				         
						 //System.out.println("Before the Security Log Details PARAMETER");
				    		 
				            // Logging Instructions for the value of the Attributes
				            log.info("Attribute Name      : " + attributeShortName);
				            log.info("Attribute Type      : " + attributeType);
				            log.info("Attribute Value     : " + attributeValue);
				         
				         //System.out.println("After the Security Log Details PARAMETER");
				         
					  	    /* Evaluation Start */
					  	    time2 = System.currentTimeMillis();
					  	    duration = time2 - time1;
					  	    /* Evaluation End */
					  	 
					  	    System.out.println("Message FRL: The Security Log for a Parameter was generated in : " + duration + " milliseconds");
					        
					  	    /* Evaluation End */
				         }
				      }
		    	   }

		        }
		    
		      }    
		  }
      }
	  

      //System.out.println("Ending Parameter Annotation Method ...");
      
      return addLog1;
      

   }
   
   public void addLogHeader()
   {

	  //System.out.println("Message FRL: The Security Log for the Header Started at: " + str1);
	   
	  /* Evaluation Start */
	  time1 = System.currentTimeMillis();
	  /* Evaluation End */
	   	 
      log = Logger.getLogger(methodFullClassName2);
	  log.info("OS Name             : " + oSName);
	  log.info("OS Current Time     : " + df1.format(date));
	  log.info("OS IP Address       : " + oSIP);
	  log.info("OS Hostname         : " + oSHostname);
	  log.info("OS UserName         : " + oSUserName);
	  log.info("Annotation Type     : " + annotationType);
	  log.info("Method Name         : " + methodFullName2);
	  log.info("Method Return Type  : " + methodReturnType);
	  log.info("Method Return Value : " + methodReturnTypeValueStr);
	  
	   /* Evaluation Start */
	   time2 = System.currentTimeMillis();
	   duration = time2 - time1;
	   /* Evaluation End */
	   System.out.println("Message FRL: The Security Log for the Header was generated in : " + duration + " milliseconds");
	        
	   /* Evaluation End */
   }
   
 
   // These are ALL the Methods that can be Annotated for the Attack Scenario
   pointcut loggingMethods(): 
	      call(boolean controller.UserController.validateUser(..))||
	      call(boolean model.UserDatabase.validateUser(..));
   
   /*
   // These are the Data Operations Methods that can be Annotated for the Attack Scenario
   pointcut loggingMethods(): 
      call(boolean model.UserDatabase.validateUser(..));
  
   // These are the Annotated the Methods for the Attack Scenario
   pointcut loggingMethods(): 
	      call(boolean controller.UserController.validateUser(..));
	      */

   
   /* These are the Data Operations Methods that can be Annotated for the Attack Scenario */
   
   after() returning(Object methodReturnTypeValue): loggingMethods() && args(..)
	   {

		  // Get the General Information about the Method
		  method               = thisJoinPoint.getSignature();
		  methodPackageName    = method.getDeclaringType().getPackageName();
		  methodClassName      = method.getDeclaringType().getSimpleName();
		  methodFullClassName1 = methodPackageName + objectOrientedDelimiter1 + methodClassName; 
		  
		  methodShortName      = method.getName();
		  methodFullName1      = method.toString();
		  methodArgs           = thisJoinPoint.getArgs();
		  methodFullName2      = methodPackageName + objectOrientedDelimiter1 + methodClassName + objectOrientedDelimiter1 + methodShortName;

		  if (methodReturnTypeValue != null)	  
		     methodReturnType = methodReturnTypeValue.getClass().getName();
		  else
		     methodReturnType = "";
		  
		  if(methodReturnType.contains(dataTypeShortClassName) == false)
		     methodPLReturnType = dataTypeFullClassName;
		  else
			  methodPLReturnType = methodReturnType;

		  // Getting information about the Operating System
		  oSUserName = System.getProperty("user.name");
		  oSName     = System.getProperty("os.name");
		  
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

		  // Get the annotations related to this Method from an Input TextFile
		  annotationList = getAnnotationLines(annotationFile, methodFullName2);

		  System.out.println("Current executed Method: " + methodFullName2);
		  		
		  // Loop into the annotationList ArrayList
		  for (a = 0; a < annotationList.size(); a++) 
		  {
		     // Get the Annotation Line 	  
		     annotationLine = annotationList.get(a);
		     
		     //System.out.println("Annotation Line: " + annotationLine);
		     
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
		     if (annotationType.equals("Parameter"))
		     {
		     System.out.println("Annotation Method ShortName : " + annotationMethodShortName);
		     System.out.println("Annotation Type             : " + annotationType);
		     }
		     */
			 
			 // Create a new log Entry
			 if(annotationMethodShortName.contains(methodShortName))
			 {
			    if(annotationType.equals("Method"))
				{  
				   methodReturnTypeValueStr = "";
					    
				   if (methodReturnTypeValue != null)	
				      methodReturnTypeValueStr = methodReturnTypeValue.toString();
				   
				   addLogHeader();
				   				   
		    	   // Get the Information about the Parameters: Names and Types
		    	   parameterNames  = ((CodeSignature) thisJoinPoint.getSignature()).getParameterNames();
		    	   parameterTypes  = ((CodeSignature) thisJoinPoint.getSignature()).getParameterTypes();
		    	   parameterCount  = parameterNames.length;		  
		    	   parameterValues = thisJoinPoint.getArgs();
		    			
		    	   addLog = parameterAnnotation(parameterNames, parameterTypes, parameterCount, parameterValues, "Method");

				}
			    else
			       if(annotationType.equals("ReturnType"))
				   {
			    	  //System.out.println("RETURN TYPE BEGINS"); 
			    	  //System.out.println("1.- Before Add New Log RETURN TYPE Annotation HEADER");
			    	   
				      addLog = addNewLog(methodPLReturnType, methodReturnTypeValue.toString(), annotationElement2, annotationOperator);
						
				      //System.out.println("1.- After Add New Log RETURN TYPE Annotation HEADER: "+addLog);
				      
					  if(addLog == true)
					  {	 
						 methodReturnTypeValueStr = "";
						    
					     if (methodReturnTypeValue != null)	
						    methodReturnTypeValueStr = methodReturnTypeValue.toString();
		   
					     //System.out.println("Before the Security Log Header RETURN TYPE");
					     addLogHeader();
					     //System.out.println("After the Security Log Header RETURN TYPE");
					   				   
			    	     // Get the Information about the Parameters: Names and Types
			    	     parameterNames  = ((CodeSignature) thisJoinPoint.getSignature()).getParameterNames();
			    	     parameterTypes  = ((CodeSignature) thisJoinPoint.getSignature()).getParameterTypes();
			    	     parameterCount  = parameterNames.length;		  
			    	     parameterValues = thisJoinPoint.getArgs();
			    			
			    	     //System.out.println("2.- Before Add New Log RETURN TYPE Annotation DETAILS ");
			    	     
			    	     addLog = parameterAnnotation(parameterNames, parameterTypes, parameterCount, parameterValues, "ReturnType");
			    	     
			    	     /*System.out.println("2.- After Add New Log RETURN TYPE Annotation DETAILS: "+addLog);
			    	     System.out.println("RETURN TYPE ENDS"); 
			    	     */
					  }
				   }
			       else
			          if(annotationType.equals("Parameter"))
			    	  {
			        	 //System.out.println("*** ANNOTATION TYPE PARAMETER BEGINS ***");  
			        	  
			    		 // Get the Information about the Parameters: Names and Types
			    		 parameterNames  = ((CodeSignature) thisJoinPoint.getSignature()).getParameterNames();
			    		 parameterTypes  = ((CodeSignature) thisJoinPoint.getSignature()).getParameterTypes();
			    		 parameterCount  = parameterNames.length;		  
			    		 parameterValues = thisJoinPoint.getArgs();
			    			
						 log = Logger.getLogger(methodFullClassName2);
						 
						 //System.out.println("1.- BEFORE Add New Log PARAMETER Annotation HEADER");
			    		 
			    		 addLog = parameterAnnotation(parameterNames, parameterTypes, parameterCount, parameterValues, "Parameter");
			    		 
			    		 //System.out.println("1.- AFTER Add New Log PARAMETER Annotation: HEADER "+addLog);
			    			
			    		 if(addLog == true)
						 {	
							
							methodReturnTypeValueStr = "";
							    
						    if (methodReturnTypeValue != null)	
							   methodReturnTypeValueStr = methodReturnTypeValue.toString();
						 }
			    		 
			    		 //System.out.println("*** ANNOTATION TYPE PARAMETER ENDS ***"); 
			    	  }
		       }
	     }
		  
      }	   
}
