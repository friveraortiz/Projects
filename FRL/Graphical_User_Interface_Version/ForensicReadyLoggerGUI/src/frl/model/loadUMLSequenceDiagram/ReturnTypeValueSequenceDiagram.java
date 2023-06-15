package frl.model.loadUMLSequenceDiagram;

public class ReturnTypeValueSequenceDiagram
{
	
   private int projectIdentifier;
   private int methodIdentifier;
   private int returnTypeIdentifier;
   private int returnTypeValueIdentifier;
   private String returnTypeValueName;
   private String returnTypeValueEquivalence;
   private String returnTypeValuePackageName;
   private String returnTypeValueClassName;
   private int classMethodIdentifier;
   private int classInformationIdentifier;
   private int attributeIdentifier;
   
   // Constructor #1
   public ReturnTypeValueSequenceDiagram(int projectIdentifier, 
		                                  int methodIdentifier, 
		                                  int returnTypeIdentifier,
		                                  int returnTypeValueIdentifier, 
		                                  String returnTypeValueName, 
		                                  String returnTypeValueEquivalence, 
		                                  String returnTypeValuePackageName,
		                                  String returnTypeValueClassName, 
		                                  int classInformationIdentifier,
		                                  int atributeIdentifier) 
   {
      this.projectIdentifier          = projectIdentifier;
	  this.methodIdentifier           = methodIdentifier;
	  this.returnTypeIdentifier       = returnTypeIdentifier;
	  this.returnTypeValueIdentifier  = returnTypeValueIdentifier;
	  this.returnTypeValueName        = returnTypeValueName;
	  this.returnTypeValueEquivalence = returnTypeValueEquivalence;
	  this.returnTypeValuePackageName = returnTypeValuePackageName;
	  this.returnTypeValueClassName   = returnTypeValueClassName;
	  this.classInformationIdentifier = classInformationIdentifier;
	  this.attributeIdentifier        = atributeIdentifier;
   }
   
   // Constructor #2
   public ReturnTypeValueSequenceDiagram(int projectIdentifier, 
		                                 int methodIdentifier, 
		                                 int returnTypeIdentifier,
		                                 int returnTypeValueIdentifier, 
		                                 String returnTypeValueName, 
		                                 String returnTypeValueEquivalence,
		                                 String returnTypeValuePackageName
		                                 ) 
   {
      this.projectIdentifier          = projectIdentifier;
	  this.methodIdentifier           = methodIdentifier;
	  this.returnTypeIdentifier       = returnTypeIdentifier;
	  this.returnTypeValueIdentifier  = returnTypeValueIdentifier;
	  this.returnTypeValueName        = returnTypeValueName;
	  this.returnTypeValueEquivalence = returnTypeValueEquivalence;
	  this.returnTypeValuePackageName = returnTypeValuePackageName;
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

   public int getReturnTypeValueIdentifier() 
   {
	   return returnTypeValueIdentifier;
   }

   public void setReturnTypeValueIdentifier(int returnTypeValueIdentifier) 
   {
	  this.returnTypeValueIdentifier = returnTypeValueIdentifier;
   }

   public String getReturnTypeValueName() 
   {
	   return returnTypeValueName;
   }

   public void setReturnTypeValueName(String returnTypeValueName) 
   {
	  this.returnTypeValueName = returnTypeValueName;
   }

   public String getReturnTypeValueEquivalence() 
   {
	   return returnTypeValueEquivalence;
   }

   public void setReturnTypeValueEquivalence(String returnTypeValueEquivalence) 
   {
	  this.returnTypeValueEquivalence = returnTypeValueEquivalence;
   }

   public String getReturnTypeValuePackageName() 
   {
	   return returnTypeValuePackageName;
   }

   public void setReturnTypeValuePackageName(String returnTypeValuePackageName) 
   {
	  this.returnTypeValuePackageName = returnTypeValuePackageName;
   }

   public String getReturnTypeValueClassName() 
   {
	   return returnTypeValueClassName;
   }

   public void setReturnTypeValueClassName(String returnTypeValueClassName) 
   {
	  this.returnTypeValueClassName = returnTypeValueClassName;
   }

   public int getClassMethodIdentifier() 
   {
	   return classMethodIdentifier;
   }

   public void setClassMethodIdentifier(int classMethodIdentifier) 
   {
	  this.classMethodIdentifier = classMethodIdentifier;
   }

   public int getReturnTypeIdentifier() 
   {
	   return returnTypeIdentifier;
   }

   public void setReturnTypeIdentifier(int returnTypeIdentifier) 
   {
	  this.returnTypeIdentifier = returnTypeIdentifier;
   }
   
   public int getClassInformationIdentifier() 
   {
      return classInformationIdentifier;
   }

   public void setClassInformationIdentifier(int classInformationIdentifier) 
   {
      this.classInformationIdentifier = classInformationIdentifier;
   }
   
   public int getAttributeIdentifier() 
   {
      return attributeIdentifier;
   }

   public void setAttributeIdentifier(int attributeIdentifier) 
   {
      this.attributeIdentifier = attributeIdentifier;
   }

   @Override
   public String toString() 
   {
      return "ReturnTypeValueSequenceDiagram [projectIdentifier=" + projectIdentifier + ", methodIdentifier="
			 + methodIdentifier + ", returnTypeIdentifier=" + returnTypeIdentifier + ", returnTypeValueIdentifier="
			 + returnTypeValueIdentifier + ", returnTypeValueName=" + returnTypeValueName
			 + ", returnTypeValueEquivalence=" + returnTypeValueEquivalence + ", returnTypeValuePackageName="
			 + returnTypeValuePackageName + ", returnTypeValueClassName=" + returnTypeValueClassName
			 + ", classMethodIdentifier=" + classMethodIdentifier + ", classInformationIdentifier="
			 + classInformationIdentifier + ", attributeIdentifier=" + attributeIdentifier + "]";
   }

}