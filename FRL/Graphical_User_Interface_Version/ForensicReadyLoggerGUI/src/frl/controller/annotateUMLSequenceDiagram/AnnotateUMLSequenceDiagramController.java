package frl.controller.annotateUMLSequenceDiagram;

import java.util.ArrayList;

import frl.model.annotateUMLSequenceDiagram.AnnotateUMLSequenceDiagramDatabase;
import frl.model.annotateUMLSequenceDiagram.MethodStepData;
import frl.model.annotateUMLSequenceDiagram.ReturnTypeData;
import frl.model.annotateUMLSequenceDiagram.UserData;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.MethodSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ReturnTypeValueSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.UMLSequenceDiagramFinal;
import frl.model.loadUMLSequenceDiagram.ValueSequenceDiagram;

public class AnnotateUMLSequenceDiagramController 
{
	
   // Declare a new object for the ClassMethods Database class
   AnnotateUMLSequenceDiagramDatabase annotateUMLSeqDiagramDb = new AnnotateUMLSequenceDiagramDatabase();
   
   public void connect(String dbConfigFilePath) throws Exception //1
   {
      String errorMessage="";
      
      try 
      {	   
         annotateUMLSeqDiagramDb.connect(dbConfigFilePath);
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
   }
   
   public void disconnect() throws Exception  //2
   {
      String errorMessage="";
	      
	  try 
	  {	  
	     annotateUMLSeqDiagramDb.disconnect();
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      }
	   
   }
   
   public ArrayList<UserData> loadUsers(int projectId) throws Exception
   {
	  String errorMessage="";  
      ArrayList<UserData> userList;
      
	  try 
	  {	  
		  userList = annotateUMLSeqDiagramDb.loadUsers(projectId);
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      } 
	  
	  return userList;
   }
   
   public ArrayList<MethodStepData> loadMethodsSteps(int projectId, int userId) throws Exception
   {
	  String errorMessage="";  
      ArrayList<MethodStepData> methodStepList;
      
	  try 
	  {	  
		  methodStepList = annotateUMLSeqDiagramDb.loadMethodsSteps(projectId, userId);
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      } 
	  
	  return methodStepList;
   }
   
   public UserData getUserDetails(int projectId, String userName) throws Exception
   {
	  String errorMessage="";  
	  UserData userData = null;
      
	  try 
	  {	  
	     userData = annotateUMLSeqDiagramDb.getUserDetails(projectId, userName);
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      } 
	  
	  return userData;
   }
   
   public MethodStepData getMethodDetails(int projectId, int userId, String methodName) throws Exception
   {
      String errorMessage="";
      MethodStepData methodStep=null;
      
	  try 
	  {	  
	     methodStep = annotateUMLSeqDiagramDb.getMethodDetails1(projectId, userId, methodName);
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      } 
	   
      return methodStep;    
   }
   
   public ArrayList<ParameterSequenceDiagram> loadParameters(int projectId, int methodId) throws Exception
   {
      String errorMessage="";   
      ArrayList<ParameterSequenceDiagram> parameterList;
      
	  try 
	  {	  
	     parameterList = annotateUMLSeqDiagramDb.loadParameters(projectId, methodId);
	  }   
      catch(Exception e)
      {
         errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
      } 
      
      return parameterList;
   }
   
   public ParameterSequenceDiagram getParametersIds(int projectId, int methodId, 
                                                    String parameterName, 
                                                    String parameterDataTypeName) throws Exception
   {
      String errorMessage="";   
	  ParameterSequenceDiagram parameterRecord=null;
	      
	  try 
	  {	  
	     parameterRecord = annotateUMLSeqDiagramDb.getParametersIds(projectId, methodId, 
	    		                                             parameterName, parameterDataTypeName);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
	      
	  return parameterRecord;
   }
   
   public ArrayList<AttributeSequenceDiagram> loadAttributes(int projectId, int methodId,
                                                             int parameterId, 
                                                             int parameterDataTypeId) throws Exception
   {
      String errorMessage="";   
      ArrayList<AttributeSequenceDiagram> attributeList;
		      
	  try 
	  {	  
	     attributeList = annotateUMLSeqDiagramDb.loadAttributes(projectId, methodId, 
	    		                                                parameterId, parameterDataTypeId);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
		      
	  return attributeList;	   
	   
   }
   
   public AttributeSequenceDiagram getAttributeInfo(int projectId, int methodId, int parameterId, int parameterDataTypeId,
                                                    String attributeName, String attributeValue) throws Exception
   {
      String errorMessage="";
      AttributeSequenceDiagram attributeSeqDiagramRecord = null;
	      
	  try 
	  {	  
		  attributeSeqDiagramRecord = annotateUMLSeqDiagramDb.getAttributeInfo(projectId, methodId, 
	    		                                                               parameterId, parameterDataTypeId, 
	    		                                                               attributeName, attributeValue);
	  }   
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage);
	  } 
		   
	  return attributeSeqDiagramRecord;    
   }
   
   public ArrayList<ValueSequenceDiagram> loadValues(int projectId, int methodId, 
		                                             int parameterId, int parameterDataTypeId,
                                                     int progrLangId, int attributeId) throws Exception
   {
      String errorMessage="";   
      ArrayList<ValueSequenceDiagram> valueList;
		      
	  try 
	  {	  
	     valueList = annotateUMLSeqDiagramDb.loadValues(projectId, methodId, 
	    		                                        parameterId, parameterDataTypeId,
	    		                                        progrLangId, attributeId);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
		      
	  return valueList;	   
	   
   }
   
   public int getValueId(int projectId, int methodId, int parameterId, int parameterDataTypeId,
                         int progLangId, int attributeId, String valueEquivalence) throws Exception
   {
      String errorMessage="";
	  int valueId=0;
	      
	  try 
	  {	  
	     valueId = annotateUMLSeqDiagramDb.getValueId(projectId, methodId, 
	    		                                      parameterId, parameterDataTypeId, 
	    		                                      progLangId, attributeId, valueEquivalence);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
		   
	  return valueId;    
   } 
   
   public ReturnTypeData loadReturnType(int projectId, int methodId) throws Exception
   {
      String errorMessage="";
      ReturnTypeData returnTypeData= null;
		      
	  try 
	  {	  
		  returnTypeData = annotateUMLSeqDiagramDb.loadReturnType(projectId, methodId);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
			   
	  return returnTypeData;    
	   
   }
   
   public ArrayList<ReturnTypeValueSequenceDiagram> loadReturnTypeValues(int projectId, int methodId,
                                                                         int returnTypeId) throws Exception
   {
      String errorMessage="";   
      ArrayList<ReturnTypeValueSequenceDiagram> returnTypeValueList;
		      
	  try 
	  {	  
	     returnTypeValueList = annotateUMLSeqDiagramDb.loadReturnTypeValues(projectId, methodId, returnTypeId);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
		      
	  return returnTypeValueList;	   
	   
   }
   
   public ArrayList<UMLSequenceDiagramFinal> loadUMLSequenceDiagramMethod(int projectId, int userId, 
                                                                          int methodId, int methodStepId) throws Exception
   {
      String errorMessage="";   
      ArrayList<UMLSequenceDiagramFinal> umlSeqDiagramFinalList = new ArrayList<UMLSequenceDiagramFinal>();
		      
	  try 
	  {	  
	     umlSeqDiagramFinalList = annotateUMLSeqDiagramDb.loadUMLSequenceDiagramMethod(projectId, userId, 
				                                                                       methodId, methodStepId);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
		      
	  return umlSeqDiagramFinalList;	   
	   
   } 
   
   public int getReturnTypeValueId(int projectId, int methodId, String returnTypeValueName) throws Exception
   {
      String errorMessage="";   
      int returnTypeValueId=0;
		      
	  try 
	  {	  
	     returnTypeValueId = annotateUMLSeqDiagramDb.getReturnTypeValueId(projectId, methodId, returnTypeValueName);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
		      
	  return returnTypeValueId;	   
	   
   }
   
   public void saveAnnotationUMLSequenceDiagram(ArrayList<UMLSequenceDiagramFinal> umlSequenceDiagram) throws Exception //6
   {
      String errorMessage="";   
			      
	  try 
	  {	  
	     annotateUMLSeqDiagramDb.saveAnnotationUMLSequenceDiagram(umlSequenceDiagram);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
	   
   }
   
   public void updateMethodLineNumbersSequenceDiagram(ArrayList<UMLSequenceDiagramFinal> umlSequenceDiagramList) throws Exception
   {
      String errorMessage="";   
			      
	  try 
	  {	  
	     annotateUMLSeqDiagramDb.updateMethodLineNumbersSequenceDiagram(umlSequenceDiagramList);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
	   
   }
   
   public void updateUserLineNumbersSequenceDiagram(int projectId, int userId) throws Exception
   {
      String errorMessage="";   
	      
	  try 
	  {	  
	     annotateUMLSeqDiagramDb.updateUserLineNumbersSequenceDiagram(projectId);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
		   
   }
   
   public void updateEndSequenceDiagram(int projectId) throws Exception
   {
      String errorMessage="";   
	      
	  try 
	  {	  
	     annotateUMLSeqDiagramDb.updateEndSequenceDiagram(projectId);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
	   
   }
   
   public void updateDiagramLineNumbersSequenceDiagram(int projectId) throws Exception
   {
      String errorMessage="";   
	      
	  try 
	  {	  
	     annotateUMLSeqDiagramDb.updateDiagramLineNumbersSequenceDiagram(projectId);
	  }   
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  } 
		   
   }

   public ArrayList<UMLSequenceDiagramFinal> loadSequenceDiagramUser(int projectId, int userId) throws Exception
   {
      String errorMessage="";
      ArrayList<UMLSequenceDiagramFinal> umlSeqDiag = new ArrayList<UMLSequenceDiagramFinal>();
      
	  try 
	  {	 	
	     umlSeqDiag = annotateUMLSeqDiagramDb.loadSequenceDiagramUser(projectId, userId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
	  
      return umlSeqDiag; 
      
   }  
   
   public ArrayList<UMLSequenceDiagramFinal> loadSequenceDiagramMethod(UMLSequenceDiagramFinal seqDiagramFinal) throws Exception
   {
      String errorMessage="";
	  ArrayList<UMLSequenceDiagramFinal> umlSeqDiag = new ArrayList<UMLSequenceDiagramFinal>();
	      
	  try 
	  {	 	
	     umlSeqDiag = annotateUMLSeqDiagramDb.loadSequenceDiagramMethod(seqDiagramFinal);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		  
	  return umlSeqDiag; 

   }  
   
   public void updateMethodSequenceDiagram(UMLSequenceDiagramFinal umlSequenceDiagramRecord) throws Exception
   {
      String errorMessage="";
      
	  try 
	  {	 	
	     annotateUMLSeqDiagramDb.updateMethodSequenceDiagram(umlSequenceDiagramRecord);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
      
   }   
   
   public void updateAnnotationUMLSequenceDiagram(ArrayList<UMLSequenceDiagramFinal> umlSequenceDiagram) throws Exception
   {
      String errorMessage="";
      
	  try 
	  {	 	
	     annotateUMLSeqDiagramDb.updateAnnotationUMLSequenceDiagram(umlSequenceDiagram);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
      
   }   
   
   public void updateAnnotationType(UMLSequenceDiagramFinal umlSequenceDiagramRecord) throws Exception
   {
      String errorMessage="";
	      
	  try 
	  {	 	
	     annotateUMLSeqDiagramDb.updateAnnotationType(umlSequenceDiagramRecord);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
	  }
	      
   }
   
   public String getReturnTypePackageName(int projectId, int methodId, int returnTypeId) throws Exception
   {
      String errorMessage="", returnTypePackageName="";
	      
	  try 
	  {	 	
	     returnTypePackageName = annotateUMLSeqDiagramDb.getReturnTypePackageName(projectId, methodId, returnTypeId);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
	     throw new Exception(errorMessage);
	  }
	  
	  return returnTypePackageName;
	      
   }   
   
   public MethodSequenceDiagram getMethodDetails2(int projectId, int methodId) throws Exception
   {
      String errorMessage="";
      MethodSequenceDiagram methodSeqDiagramRecord = null;
	      
	  try 
	  {	 	
	     methodSeqDiagramRecord = annotateUMLSeqDiagramDb.getMethodDetails2(projectId, methodId);
	  }  
	  catch(Exception e1)
	  {
	     errorMessage = e1.getMessage();
	     throw new Exception(errorMessage);
	  }
	  
	  return methodSeqDiagramRecord;
	      
   }  
   
   public void updateUserFileSequenceDiagram(UserData userData) throws Exception
   {
      String errorMessage="";
	      
	  try 
	  {	 	
         annotateUMLSeqDiagramDb.updateUserFileSequenceDiagram(userData);
	  }  
	  catch(Exception e)
	  {
	     errorMessage = e.getMessage();
		 throw new Exception(errorMessage);
	  }
		     	    
   }
   
}
