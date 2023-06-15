package frl.model.loadUMLSequenceDiagram;

public class AttributeSequenceDiagram
{
	
   private int projectIdentifier;
   private int methodIdentifier;
   private int parameterIdentifier;
   private int dataTypeIdentifier1;
   private int programmingLanguageIdentifier1;
   
   private int attributeIdentifier;
   private int dataTypeIdentifier2;
   private int programmingLanguageIdentifier2;   
   private String attributeName;
   private String attributeValue;
   private String attributePackageName;
   private String attributeClassName;

   private int classInfoIdentifier1;
   private int classAttributeIdentifier1;
   private int classMethodIdentifier1;

   private int classInfoIdentifier2;
   

   
   private String methodName;
   private int classMethodIdentifier2;
   //private String[] attributeValues;
   private String attributeDataTypeName;

	   
   // Constructor #1
   public AttributeSequenceDiagram(int projectIdentifier, int methodIdentifier, int parameterIdentifier,
		   						   int dataTypeIdentifier1, int programmingLanguageIdentifier1, int attributeIdentifier,  
		   						   String methodName, int classMethodIdentifier1, String attributeName, String attributeValue, 
		   						   int dataTypeIdentifier2, int programmingLanguageIdentifier2, String attributePackageName, 
		   						   String attributeClassName, int classMethodIdentifier2, int classInfoIdentifier1, 
		   						   int classAttributeIdentifier1, int classInfoIdentifier2) 
   {
      this.projectIdentifier              = projectIdentifier;
	  this.methodIdentifier               = methodIdentifier;
	  this.parameterIdentifier            = parameterIdentifier;
	  this.dataTypeIdentifier1            = dataTypeIdentifier1;
	  this.programmingLanguageIdentifier1 = programmingLanguageIdentifier1;
	  this.attributeIdentifier            = attributeIdentifier;
	  
	  this.methodName                     = methodName;
	  this.classMethodIdentifier1         = classMethodIdentifier1;
	  this.attributeName                  = attributeName;
	  this.attributeValue                 = attributeValue;
	  this.dataTypeIdentifier2            = dataTypeIdentifier2;
	  this.programmingLanguageIdentifier2 = programmingLanguageIdentifier2;
	  
	  this.attributePackageName           = attributePackageName;
	  this.attributeClassName             = attributeClassName;
	  this.classMethodIdentifier2         = classMethodIdentifier2;
	  
	  this.classInfoIdentifier1           = classInfoIdentifier1;
	  this.classAttributeIdentifier1      = classAttributeIdentifier1;
	  
	  this.classInfoIdentifier2           = classInfoIdentifier2;

   }
   
   /*
   // Constructor #2
   public AttributeSequenceDiagram(int projectIdentifier, int methodIdentifier, int parameterIdentifier,
		                           String methodName, int classMethodIdentifier1, String attributeName, 
		                           String attributeValue, int dataTypeIdentifier2, 
		                           int programmingLanguageIdentifier2, String attributePackageName, 
		                           String attributeClassName, int classMethodIdentifier2, 
		                           String[] attributeValues) 
   {
      this.projectIdentifier              = projectIdentifier;
	  this.methodIdentifier               = methodIdentifier;
	  this.parameterIdentifier            = parameterIdentifier;
	  this.methodName                     = methodName;
	  this.classMethodIdentifier1         = classMethodIdentifier1;
	  this.attributeName                  = attributeName;
	  this.attributeValue                 = attributeValue;
	  this.dataTypeIdentifier2            = dataTypeIdentifier2;
	  this.programmingLanguageIdentifier2 = programmingLanguageIdentifier2;
	  this.attributePackageName           = attributePackageName;
	  this.attributeClassName             = attributeClassName;
	  this.classMethodIdentifier2         = classMethodIdentifier2;
	  this.attributeValues                = attributeValues;
   }
   */
   
   // Constructor #2
   public AttributeSequenceDiagram(int projectIdentifier, int methodIdentifier, 
		                           int parameterIdentifier, int dataTypeIdentifier1, 
		                           int attributeIdentifier, int dataTypeIdentifier2,
		                           String attributeDataTypeName,
		                           String attributeName,
		                           String attributeValue) 
   {
      this.projectIdentifier     = projectIdentifier;
	  this.methodIdentifier      = methodIdentifier;
	  this.parameterIdentifier   = parameterIdentifier;
	  this.dataTypeIdentifier1   = dataTypeIdentifier1;
	  this.attributeIdentifier   = attributeIdentifier;
	  this.dataTypeIdentifier2   = dataTypeIdentifier2;
	  this.attributeDataTypeName = attributeDataTypeName;
	  this.attributeName         = attributeName;
	  this.attributeValue        = attributeValue;
   }
   
   /*
   // Constructor #4
   public AttributeSequenceDiagram(int projectIdentifier, int methodIdentifier, 
		                           int parameterIdentifier, int dataTypeIdentifier1, 
		                           int attributeIdentifier, String attributeName) 
   {
      this.projectIdentifier    = projectIdentifier;
	  this.methodIdentifier     = methodIdentifier;
	  this.parameterIdentifier  = parameterIdentifier;
	  this.dataTypeIdentifier1  = dataTypeIdentifier1;
	  this.attributeIdentifier  = attributeIdentifier;
	  this.attributeName        = attributeName;
   }  
   */
   
   // Constructor #3
   public AttributeSequenceDiagram(int projectIdentifier, int methodIdentifier, 
		                           int parameterIdentifier, int dataTypeIdentifier1, 
		                           int attributeIdentifier, String attributeName,
		                           String attributeDataTypeName, String attributeValue) 
   {
      this.projectIdentifier     = projectIdentifier;
	  this.methodIdentifier      = methodIdentifier;
	  this.parameterIdentifier   = parameterIdentifier;
	  this.dataTypeIdentifier1   = dataTypeIdentifier1;
	  this.attributeIdentifier   = attributeIdentifier;
	  this.attributeName         = attributeName;
	  this.attributeDataTypeName = attributeDataTypeName;
	  this.attributeValue        = attributeValue;
   }  
   
   
   // Constructor #4
   public AttributeSequenceDiagram(int projectIdentifier, int methodIdentifier, 
           						   int parameterIdentifier, int dataTypeIdentifier1,
           						   int programmingLanguageIdentifier1, int attributeIdentifier,
           						   int classInfoIdentifier2)
   {
      this.projectIdentifier              = projectIdentifier;	
      this.methodIdentifier               = methodIdentifier;
      this.parameterIdentifier            = parameterIdentifier;
      this.dataTypeIdentifier1            = dataTypeIdentifier1;
      this.programmingLanguageIdentifier1 = programmingLanguageIdentifier1;
      this.attributeIdentifier            = attributeIdentifier;
      this.classInfoIdentifier2           = classInfoIdentifier2;
   }
   
   // Constructor #5
   public AttributeSequenceDiagram(int projectIdentifier, int methodIdentifier, 
		                           int parameterIdentifier, 
		                           int attributeIdentifier, int dataTypeIdentifier2,
		                           String attributeDataTypeName) 
   {
      this.projectIdentifier     = projectIdentifier;
	  this.methodIdentifier      = methodIdentifier;
	  this.parameterIdentifier   = parameterIdentifier;
	  this.attributeIdentifier   = attributeIdentifier;
	  this.dataTypeIdentifier2   = dataTypeIdentifier2;
	  this.attributeDataTypeName = attributeDataTypeName;
   }   
   
   
   public int getProjectIdentifier() 
   {
	  return projectIdentifier;
   }

   public void setProjectIdentifier(int projectIdentifier) 
   {
	  this.projectIdentifier = projectIdentifier;
   }

   public int getParameterIdentifier() 
   {
	  return parameterIdentifier;
   }

   public void setParameterIdentifier(int parameterIdentifier) 
   {
	  this.parameterIdentifier = parameterIdentifier;
   }

   public int getDataTypeIdentifier1() 
   {
	  return dataTypeIdentifier1;
   }

   public void setDataTypeIdentifier1(int dataTypeIdentifier1) 
   {
	  this.dataTypeIdentifier1 = dataTypeIdentifier1;
   }

   public int getAttributeIdentifier() 
   {
	  return attributeIdentifier;
   }

   public void setAttributeIdentifier(int attributeIdentifier) 
   {
	  this.attributeIdentifier = attributeIdentifier;
   }

   
   public String getMethodName() 
   {
	  return methodName;
   }

   public void setMethodName(String methodName) 
   {
	  this.methodName = methodName;
   }
   

   public int getClassMethodIdentifier1() 
   {
	  return classMethodIdentifier1;
   }

   public void setClassMethodIdentifier1(int classMethodIdentifier1) 
   {
	  this.classMethodIdentifier1 = classMethodIdentifier1;
   }

   public String getAttributeName() 
   {
	  return attributeName;
   }

   public void setAttributeName(String attributeName) 
   {
	  this.attributeName = attributeName;
   }

   public int getDataTypeIdentifier2() 
   {
	  return dataTypeIdentifier2;
   }

   public void setDataTypeIdentifier2(int dataTypeIdentifier2) 
   {
	  this.dataTypeIdentifier2 = dataTypeIdentifier2;
   }

   public String getAttributePackageName() 
   {
	  return attributePackageName;
   }

   public void setAttributePackageName(String attributePackageName) 
   {
	  this.attributePackageName = attributePackageName;
   }

   public String getAttributeClassName() 
   {
	  return attributeClassName;
   }

   public void setAttributeClassName(String attributeClassName) 
   {
	  this.attributeClassName = attributeClassName;
   }

   
   public int getClassMethodIdentifier2() 
   {
	  return classMethodIdentifier2;
   }

   public void setClassMethodIdentifier2(int classMethodIdentifier2) 
   {
	  this.classMethodIdentifier2 = classMethodIdentifier2;
   }
   

   public int getMethodIdentifier() 
   {
	  return methodIdentifier;
   }

   public void setMethodIdentifier(int methodIdentifier) 
   {
	  this.methodIdentifier = methodIdentifier;
   }

   public int getProgrammingLanguageIdentifier1() 
   {
	  return programmingLanguageIdentifier1;
   }

   public void setProgrammingLanguageIdentifier1(int programmingLanguageIdentifier1) 
   {
	  this.programmingLanguageIdentifier1 = programmingLanguageIdentifier1;
   }

   public int getProgrammingLanguageIdentifier2() 
   {
	  return programmingLanguageIdentifier2;
   }

   public void setProgrammingLanguageIdentifier2(int programmingLanguageIdentifier2) 
   {
	  this.programmingLanguageIdentifier2 = programmingLanguageIdentifier2;
   }

   /*
   public String[] getAttributeValues() 
   {
      return attributeValues;
   }

   public void setAttributeValues(String[] attributeValues) 
   {
	  this.attributeValues = attributeValues;
   }
   */
   
   public String getAttributeValue() 
   {
      return attributeValue;
   }

   public void setAttributeValue(String attributeValue) 
   {
      this.attributeValue = attributeValue;
   }
   
   
   public String getAttributeDataTypeName() 
   {
      return attributeDataTypeName;
   }

   public void setAttributeDataTypeName(String dataTypeName) 
   {
	   this.attributeDataTypeName = dataTypeName;
   }
   

   public int getClassInfoIdentifier1() 
   {
      return classInfoIdentifier1;
   }

   public void setClassInfoIdentifier1(int classInfoIdentifier1) 
   {
      this.classInfoIdentifier1 = classInfoIdentifier1;
   }

   public int getClassAttributeIdentifier1() 
   {
      return classAttributeIdentifier1;
  }

   public void setClassAttributeIdentifier1(int classAttributeIdentifier1) 
   {
      this.classAttributeIdentifier1 = classAttributeIdentifier1;
   }

   public int getClassInfoIdentifier2() 
   {
     return classInfoIdentifier2;
   }

   public void setClassInfoIdentifier2(int classInfoIdentifier2) 
   {
      this.classInfoIdentifier2 = classInfoIdentifier2;
   }



}