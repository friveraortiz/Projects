package frl.gui.annotateUMLSequenceDiagram;
import java.util.EventObject;

public class AnnotateUMLSequenceDiagramFormEvent extends EventObject 
{
   private static final long serialVersionUID = 1L;

   
   private int projectId;
   private int userId;
   private String userName; 
   private String userTextFileName; 
   private String userImageFileName; 
   
   private String whQuestionName; 
   
   private int methodId; 
   private int methodStepId; 
   private String methodName;       
   private String methodTextFileName; 
   private String methodImageFileName; 
   
   private int parameterId; 
   private int parameterDataTypeId; 
   private String parameterName;
   
   private int attributeId; 
   private String attributeName; 
   private String attributeDataTypeName;
   
   private String comparisonName1;  
   
   private int valueId;   
   private String valueName; 
   private String valueText1; 

   private int returnTypeId; 
   private String returnTypeName; 
   
   private String comparisonName2;  
   
   private int returnTypeValueId; 
   private String returnTypeValueName;
   
   //private String comparisonName3;
   private String annotationType;
      
   // Constructor #1
   public AnnotateUMLSequenceDiagramFormEvent(Object source) 
   {
		super(source);
   }
   	
   // Constructor #2
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, int userId, 
		                                      String userName, int methodId, String methodName)
   {
	  super(source);
      this.projectId  = projectId;	   
	  this.userId     = userId;
	  this.userName   = userName;
	  this.methodId   = methodId;
	  this.methodName = methodName;
   }
   
   // Constructor #3
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, String userName)
   {
	  super(source);
      this.projectId  = projectId;	   
	  this.userName   = userName;
   }
   
   // Constructor #4
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, int userId, String userName, String methodName)
   {
	  super(source);
      this.projectId  = projectId;	   
	  this.userId     = userId;
	  this.userName   = userName;
	  this.methodName = methodName;
   }
   
   // Constructor #5
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, int userId, 
		                                      String userName, int methodId,
		                                      String methodName, String annotationType)
   {
	  super(source);
      this.projectId      = projectId;	   
	  this.userId         = userId;
	  this.userName       = userName;
	  this.methodId       = methodId;
	  this.methodName     = methodName;
	  this.annotationType = annotationType;
   }
   
   
   // Constructor #6
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, 
		                                      int methodId, int parameterId, 
		                                      int parameterDataTypeId, 
		                                      String parameterName)
   {
	  super(source);
      this.projectId           = projectId;	
      this.methodId            = methodId;
      this.parameterId         = parameterId;
      this.parameterDataTypeId = parameterDataTypeId;
	  this.parameterName       = parameterName;
   }
   
   // Constructor #7
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, 
		                                      int methodId, int parameterId, 
		                                      int parameterDataTypeId, 
		                                      int attributeId,
		                                      String attributeName)
   {
	  super(source);
      this.projectId           = projectId;	
      this.methodId            = methodId;
      this.parameterId         = parameterId;
      this.parameterDataTypeId = parameterDataTypeId;
      this.attributeId         = attributeId;
      this.attributeName       = attributeName;
   }
   
   // Constructor #8
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, 
		                                      int methodId, int parameterId, 
		                                      int parameterDataTypeId, 
		                                      int attributeId,
		                                      String attributeName,
		                                      int valueId, 
		                                      String valueName)
   {
	  super(source);
      this.projectId            = projectId;	
      this.methodId             = methodId;
      this.parameterId          = parameterId;
      this.parameterDataTypeId  = parameterDataTypeId;
      this.attributeId          = attributeId;
      this.attributeName        = attributeName;
      this.valueId              = valueId;
      this.valueName            = valueName;
   }
   
   // Constructor #9
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, int userId,
	                                          String userName, String userTextFileName, 
	                                          String userImageFileName, String whQuestionName, 
                                              int methodId, int methodStepId, String methodName,       
                                              String methodTextFileName, String methodImageFileName, 
                                              int parameterId, int parameterDataTypeId, String parameterName,
                                              int attributeId, String attributeName, 
                                              String attributeDataTypeName,
                                              String comparisonName1,     
                                              int valueId, String valueName, String valueText1,
                                              int returnTypeId, String returnTypeName, String comparisonName2,
                                              int returnTypeValueId, 
                                              String returnTypeValueName,
                                              /*String comparison3,*/
                                              String annotationType)
   
   {
      super(source);
      this.projectId            = projectId;
      this.userId               = userId;
      this.userName             = userName;
      this.userTextFileName     = userTextFileName;
      this.userImageFileName    = userImageFileName;
      this.whQuestionName       = whQuestionName;
      this.methodId             = methodId; 
      this.methodStepId         = methodStepId;
      this.methodName           = methodName;  
      this.methodTextFileName   = methodTextFileName;
      this.methodImageFileName  = methodTextFileName;
      this.parameterId          = parameterId;
      this.parameterDataTypeId  = parameterDataTypeId; 
      this.parameterName        = parameterName;
      this.attributeId          = attributeId;
      this.attributeName        = attributeName;
      this.attributeDataTypeName = attributeDataTypeName;
      this.comparisonName1      = comparisonName1;
      this.valueName            = valueName;
      this.valueText1           = valueText1;
      this.valueId              = valueId;
      this.returnTypeId         = returnTypeId;
      this.returnTypeName       = returnTypeName;
      
      this.comparisonName2      = comparisonName2;
      
      this.returnTypeValueId    = returnTypeValueId;
      this.returnTypeValueName  = returnTypeValueName;
      
      //this.comparisonName3      = comparison3;
      this.annotationType       = annotationType;
   }
   
   // Constructor #10
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, int methodId, 
		                                      int returnTypeValueId, String returnTypeValueName)
   {
      super(source);
      this.projectId           = projectId;
      this.methodId            = methodId;
      this.returnTypeValueId   = returnTypeValueId;
      this.returnTypeValueName = returnTypeValueName;

   }
   
   // Constructor #11
   public AnnotateUMLSequenceDiagramFormEvent(Object source, int projectId, int userId, 
		                                      String userName, String userTextFileName, String userImageFileName)
   {
      super(source);
	  this.projectId         = projectId;
	  this.userId            = userId;
	  this.userName          = userName;
	  this.userTextFileName  = userTextFileName;
	  this.userImageFileName = userImageFileName; 
   }

   public int getProjectId() 
   {
      return projectId;
   }

   public void setProjectId(int projectId) 
   {
      this.projectId = projectId;
   }

   public int getUserId() 
   {
	  return userId;
   }

   public void setUserId(int userId) 
   {
	   this.userId = userId;
   }

   public String getUserName() 
   {
	   return userName;
   }

   public void setUserName(String userName) 
   {
	   this.userName = userName;
   }

   public String getUserTextFileName() 
   {
	   return userTextFileName;
   }

   public void setUserTextFileName(String userTextFileName) 
   {
	   this.userTextFileName = userTextFileName;
   }

   public String getUserImageFileName() 
   {
	   return userImageFileName;
   }

   public void setUserImageFileName(String userImageFileName) 
   {
	  this.userImageFileName = userImageFileName;
   }

   public String getWhQuestionName() 
   {
	  return whQuestionName;
   }

   public void setWhQuestionName(String whQuestionName) 
   {
	   this.whQuestionName = whQuestionName;
   }

   public int getMethodId() 
   {
	   return methodId;
   }

   public void setMethodId(int methodId) 
   {
	   this.methodId = methodId;
   }

   public int getMethodStepId() 
   {
	   return methodStepId;
   }

   public void setMethodStepId(int methodStepId) 
   {
	   this.methodStepId = methodStepId;
   }

   public String getMethodName() 
   {
	   return methodName;
   }

   public void setMethodName(String methodName) 
   {
	   this.methodName = methodName;
   }

   public String getMethodTextFileName() 
   {
	   return methodTextFileName;
   }

   public void setMethodTextFileName(String methodTextFileName) 
   {
	  this.methodTextFileName = methodTextFileName;
   }

   public String getMethodImageFileName() 
   {
	  return methodImageFileName;
   }

   public void setMethodImageFileName(String methodImageFileName) 
   {
	  this.methodImageFileName = methodImageFileName;
   }

   public int getParameterId() 
   {
	  return parameterId;
   }

   public void setParameterId(int parameterId) 
   {
	  this.parameterId = parameterId;
   }

   public int getParameterDataTypeId() 
   {
	  return parameterDataTypeId;
   }

   public void setParameterDataTypeId(int parameterDataTypeId) 
   {
	  this.parameterDataTypeId = parameterDataTypeId;
   }

   public String getParameterName() 
   {
	  return parameterName;
   }

   public void setParameterName(String parameterName) 
   {
	  this.parameterName = parameterName;
   }

   public int getAttributeId() 
   {
	  return attributeId;
   }

   public void setAttributeId(int attributeId) 
   {
	  this.attributeId = attributeId;
   }

   public String getAttributeName() 
   {
	   return attributeName;
   }

   public void setAttributeName(String attributeName) 
   {
	  this.attributeName = attributeName;
   }   

   public String getAttributeDataTypeName() 
   {
      return attributeDataTypeName;
   }

   public void setAttributeDataTypeName(String attributeDataTypeName) 
   {
      this.attributeDataTypeName = attributeDataTypeName;
   }

   public String getComparisonName1() 
   {
	  return comparisonName1;
   }

   public void setComparisonName1(String comparisonName1) 
   {
	  this.comparisonName1 = comparisonName1;
   }

   public int getValueId() 
   {
	  return valueId;
   }

   public void setValueId(int valueId) 
   {
	  this.valueId = valueId;
   }

   public String getValueName() 
   {
      return valueName;
   }

   public void setValueName(String valueName) 
   {
      this.valueName = valueName;
   }

   public String getValueText1() 
   {
      return valueText1;
   }

   public void setValueText1(String valueText1) 
   {
      this.valueText1 = valueText1;
   }

   public int getReturnTypeId() 
   {
	  return returnTypeId;
   }

   public void setReturnTypeId(int returnTypeId) 
   {
	  this.returnTypeId = returnTypeId;
   }

   public String getReturnTypeName() 
   {
	  return returnTypeName;
   }

   public void setReturnTypeName(String returnTypeName) 
   {
	  this.returnTypeName = returnTypeName;
   }

   public int getReturnTypeValueId() 
   {
	  return returnTypeValueId;
   }

   public void setReturnTypeValueId(int returnTypeValueId) 
   {
	  this.returnTypeValueId = returnTypeValueId;
   }
   
   public String getComparisonName2() 
   {
      return comparisonName2;
   }

   public void setComparisonName2(String comparisonName2) 
   {
      this.comparisonName2 = comparisonName2;
   }

   public String getReturnTypeValueName() 
   {
	  return returnTypeValueName;
   }

   public void setReturnTypeValueName(String returnTypeValueName) 
   {
      this.returnTypeValueName = returnTypeValueName;
   }
   
/*
   public String getComparisonName3() 
   {
      return comparisonName3;
   }

   public void setComparisonName3(String comparisonName3) 
   {
      this.comparisonName3 = comparisonName3;
   }
   */

   public String getAnnotationType() 
   {
      return annotationType;
   }

   public void setAnnotationType(String annotationType) 
   {
      this.annotationType = annotationType;
   }

   @Override
   public String toString() 
   {
      return "AnnotateUMLSequenceDiagramFormEvent [projectId=" + projectId + ", userId=" + userId + ", userName="
			 + userName + ", userTextFileName=" + userTextFileName + ", userImageFileName=" + userImageFileName
			 + ", whQuestionName=" + whQuestionName + ", methodId=" + methodId + ", methodStepId=" + methodStepId
			 + ", methodName=" + methodName + ", methodTextFileName=" + methodTextFileName + ", methodImageFileName="
			 + methodImageFileName + ", parameterId=" + parameterId + ", parameterDataTypeId=" + parameterDataTypeId
			 + ", parameterName=" + parameterName + ", attributeId=" + attributeId + ", attributeName=" + attributeName
			 + ", attributeDataTypeName=" + attributeDataTypeName + ", comparisonName1=" + comparisonName1 + ", valueId="
			 + valueId + ", valueName=" + valueName + ", valueText1=" + valueText1 + ", returnTypeId=" + returnTypeId
			 + ", returnTypeName=" + returnTypeName + ", comparisonName2=" + comparisonName2 + ", returnTypeValueId="
			 + returnTypeValueId + ", returnTypeValueName=" + returnTypeValueName + ", annotationType=" + annotationType
			 + "]";
   }


}	