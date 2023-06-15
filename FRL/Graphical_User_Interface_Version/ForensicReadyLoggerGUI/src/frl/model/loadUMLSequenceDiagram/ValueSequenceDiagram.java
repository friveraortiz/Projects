package frl.model.loadUMLSequenceDiagram;

public class ValueSequenceDiagram
{
	
   private int projectIdentifier;
   private int methodIdentifier;
   private int parameterIdentifier;
   private int dataTypeIdentifier1;
   private int programmingLanguageIdentifier1;
   private int attributeIdentifier;
   private int valueIdentifier;
   private String valueName;
   private String valueEquivalence;
   private String valuePackageName;
   private String valueClassName;
   private int dataTypeIdentifier2;
   private int programmingLanguageIdentifier2;
   private int classInfoIdentifier2;
   private int classAttributeIdentifier2;
   private int classMethodIdentifier1;
   
   // Constructor #1
   public ValueSequenceDiagram(int projectIdentifier,    int methodIdentifier, int parameterIdentifier,
		                       int dataTypeIdentifier1,  int programmingLanguageIdentifier1, 
		                       int attributeIdentifier,  int valueIdentifier, String valueName, 
		                       String valueEquivalence,  String valuePackageName, 
		                       String valueClassName,    
		                       int dataTypeIdentifier2,  int programmingLanguageIdentifier2,
		                       int classInfoIdentifier2, int classAttributeIdentifier2,
		                       int classMethodIdentifier1) 
   {
      this.projectIdentifier              = projectIdentifier;
	  this.methodIdentifier               = methodIdentifier;
	  this.parameterIdentifier            = parameterIdentifier;
	  this.dataTypeIdentifier1            = dataTypeIdentifier1;
	  this.programmingLanguageIdentifier1 = programmingLanguageIdentifier1;
	  this.attributeIdentifier            = attributeIdentifier;
	  this.valueIdentifier                = valueIdentifier;
	  this.valueName                      = valueName;
	  this.valueEquivalence               = valueEquivalence;
	  this.valuePackageName               = valuePackageName;
	  this.valueClassName                 = valueClassName;
	  this.dataTypeIdentifier2            = dataTypeIdentifier2;
	  this.programmingLanguageIdentifier2 = programmingLanguageIdentifier2;
	  this.classInfoIdentifier2           = classInfoIdentifier2;
	  this.classAttributeIdentifier2      = classAttributeIdentifier2;
	  this.classMethodIdentifier1         = classMethodIdentifier1;
   }
   
   // Constructor #2
   public ValueSequenceDiagram(int projectIdentifier, int methodIdentifier, int parameterIdentifier,
		                       int dataTypeIdentifier1, int programmingLanguageIdentifier1, 
		                       int attributeIdentifier, int valueIdentifier, String valueName, 
		                       String valueEquivalence) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.parameterIdentifier = parameterIdentifier;
	  this.dataTypeIdentifier1 = dataTypeIdentifier1;
	  this.programmingLanguageIdentifier1 = programmingLanguageIdentifier1;
	  this.attributeIdentifier = attributeIdentifier;
	  this.valueIdentifier     = valueIdentifier;
	  this.valueName           = valueName;
	  this.valueEquivalence    = valueEquivalence;
   }

   public int getProjectIdentifier() 
   {
	  return projectIdentifier;
   }

   public void setProjectIdentifier(int projectIdentifier) 
   {
	  this.projectIdentifier = projectIdentifier;
   }

   public int getMethodIdentifier() 
   {
	  return methodIdentifier;
   }

   public void setMethodIdentifier(int methodIdentifier) 
   {
	  this.methodIdentifier = methodIdentifier;
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

   public int getProgrammingLanguageIdentifier1() 
   {
	  return programmingLanguageIdentifier1;
   }

   public void setProgrammingLanguageIdentifier1(int programmingLanguageIdentifier1) 
   {
	  this.programmingLanguageIdentifier1 = programmingLanguageIdentifier1;
   }

   public int getAttributeIdentifier() 
   {
	  return attributeIdentifier;
   }

   public void setAttributeIdentifier(int attributeIdentifier) 
   {
	  this.attributeIdentifier = attributeIdentifier;
   }

   public int getValueIdentifier() 
   {
	  return valueIdentifier;
   }

   public void setValueIdentifier(int valueIdentifier) 
   {
	  this.valueIdentifier = valueIdentifier;
   }

   public String getValueName() 
   {
	  return valueName;
   }

   public void setValueName(String valueName) 
   {
	  this.valueName = valueName;
   }

   public String getValueEquivalence() 
   {
	  return valueEquivalence;
   }

   public void setValueEquivalence(String valueEquivalence) 
   {
	  this.valueEquivalence = valueEquivalence;
   }

   public String getValuePackageName() 
   {
	  return valuePackageName;
   }

   public void setValuePackageName(String attributePackageName) 
   {
	  this.valuePackageName = attributePackageName;
   }

   public String getValueClassName() 
   {
	  return valueClassName;
   }

   public void setValueClassName(String valueClassName) 
   {
	  this.valueClassName = valueClassName;
   }

   public int getClassMethodIdentifier1() 
   {
	  return classMethodIdentifier1;
   }

   public void setClassMethodIdentifier1(int classMethodIdentifier1) 
   {
	  this.classMethodIdentifier1 = classMethodIdentifier1;
   }

   public int getDataTypeIdentifier2() 
   {
      return dataTypeIdentifier2;
   }

   public void setDataTypeIdentifier2(int dataTypeIdentifier2) 
   {
      this.dataTypeIdentifier2 = dataTypeIdentifier2;
   }

   public int getProgrammingLanguageIdentifier2() 
   {
      return programmingLanguageIdentifier2;
   }

   public void setProgrammingLanguageIdentifier2(int programmingLanguageIdentifier2) 
   {
      this.programmingLanguageIdentifier2 = programmingLanguageIdentifier2;
   }

   public int getClassInfoIdentifier2() 
   {
      return classInfoIdentifier2;
   }

   public void setClassInfoIdentifier2(int classInfoIdentifier2) 
   {
      this.classInfoIdentifier2 = classInfoIdentifier2;
   }

   public int getClassAttributeIdentifier2() 
   {
      return classAttributeIdentifier2;
   }

   public void setClassAttributeIdentifier2(int classAttributeIdentifier2) 
   {
      this.classAttributeIdentifier2 = classAttributeIdentifier2;
   }

   @Override
   public String toString() 
   {
      return "ValueSequenceDiagram [projectIdentifier=" + projectIdentifier + ", methodIdentifier=" + methodIdentifier
			 + ", parameterIdentifier=" + parameterIdentifier + ", dataTypeIdentifier1=" + dataTypeIdentifier1
			 + ", programmingLanguageIdentifier1=" + programmingLanguageIdentifier1 + ", attributeIdentifier="
			 + attributeIdentifier + ", valueIdentifier=" + valueIdentifier + ", valueName=" + valueName
			 + ", valueEquivalence=" + valueEquivalence + ", valuePackageName=" + valuePackageName + ", valueClassName="
			 + valueClassName + ", classMethodIdentifier1=" + classMethodIdentifier1 + ", dataTypeIdentifier2="
			 + dataTypeIdentifier2 + ", programmingLanguageIdentifier2=" + programmingLanguageIdentifier2
			 + ", classInfoIdentifier2=" + classInfoIdentifier2 + ", classAttributeIdentifier2="
			 + classAttributeIdentifier2 + "]";
   }

   
}