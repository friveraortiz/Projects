package frl.model.loadUMLSequenceDiagram;

public class ReturnTypeSequenceDiagram 
{
   private int projectIdentifier;	
   private int methodIdentifier;	
   private int returnTypeIdentifier;
   private int programmingLanguageIdentifier;
   private String returnTypeName;

   private int dataTypeIdentifier;
   private String dataTypeName;
   private String packageName;
   private String className;
   private String returnType;
   private int classMethodIdentifier;
   private int returnTypeIdentifier2;
   
   private String returnTypeFullName;
   
   // Constructor #1
   public ReturnTypeSequenceDiagram(int projectIdentifier, int methodIdentifier,
		                          int returnTypeIdentifier, int programmingLanguageIdentifier, 
		                          String returnTypeName, int dataTypeIdentifier, String dataTypeName, 
		                          String packageName, String className, String returnType, 
		                          int classMethodIdentifier, int returnTypeIdentifier2) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.returnTypeIdentifier = returnTypeIdentifier;
	  this.programmingLanguageIdentifier = programmingLanguageIdentifier;
	  this.returnTypeName       = returnTypeName;
	  this.dataTypeIdentifier  = dataTypeIdentifier;
	  this.dataTypeName        = dataTypeName;
	  this.packageName         = packageName;
	  this.className           = className;
	  this.returnType          = returnType;
	  this.classMethodIdentifier = classMethodIdentifier;
	  this.returnTypeIdentifier2 = returnTypeIdentifier2;
   }

   // Constructor #2
   public ReturnTypeSequenceDiagram(int projectIdentifier, int methodIdentifier,
                                   int returnTypeIdentifier, String returnTypeName, 
                                   int dataTypeIdentifier, String dataTypeName,
                                   String packageName) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.returnTypeIdentifier = returnTypeIdentifier;
	  this.returnTypeName       = returnTypeName;
	  this.dataTypeIdentifier  = dataTypeIdentifier;
	  this.dataTypeName        = dataTypeName;
	  this.packageName         = packageName;
   }
   
   // Constructor #3
   public ReturnTypeSequenceDiagram(int projectIdentifier, int methodIdentifier,
                                   int returnTypeIdentifier, String returnTypeName, 
                                   int dataTypeIdentifier, String dataTypeName,
                                   String packageName, String className) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.returnTypeIdentifier = returnTypeIdentifier;
	  this.returnTypeName       = returnTypeName;
	  this.dataTypeIdentifier  = dataTypeIdentifier;
	  this.dataTypeName        = dataTypeName;
	  this.packageName         = packageName;
	  this.className           = className;
   }
   
   // Constructor #3
   public ReturnTypeSequenceDiagram(int projectIdentifier, int methodIdentifier,
                                   int returnTypeIdentifier, String returnTypeName, 
                                   int dataTypeIdentifier, String dataTypeName,
                                   String packageName, String className, 
                                   String returnTypeFullName) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.returnTypeIdentifier = returnTypeIdentifier;
	  this.returnTypeName       = returnTypeName;
	  this.dataTypeIdentifier  = dataTypeIdentifier;
	  this.dataTypeName        = dataTypeName;
	  this.packageName         = packageName;
	  this.className           = className;
	  this.returnTypeFullName   = returnTypeFullName;
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
      return  methodIdentifier;
   }

   public void setMethodIdentifier(int methodIdentifier) 
   {
      this.methodIdentifier = methodIdentifier;
   }

   public int getReturnTypeIdentifier() 
   {
      return  returnTypeIdentifier;
   }

   public void setReturnTypeIdentifier(int returnTypeIdentifier) 
   {
      this.returnTypeIdentifier = returnTypeIdentifier;
   }

   public int getProgrammingLanguageIdentifier() 
   {
      return  programmingLanguageIdentifier;
   }

   public void setProgrammingLanguageIdentifier(int programmingLanguageIdentifier) 
   {
      this.programmingLanguageIdentifier = programmingLanguageIdentifier;
   }

   public String getReturnTypeName() 
   {
      return  returnTypeName;
   }

   public void setReturnTypeName(String returnTypeName) 
   {
      this.returnTypeName = returnTypeName;
   }

   public int getDataTypeIdentifier() 
   {
      return  dataTypeIdentifier;
   }

   public void setDataTypeIdentifier(int dataTypeIdentifier) 
   {
      this.dataTypeIdentifier = dataTypeIdentifier;
   }

   public String getDataTypeName() 
   {
      return  dataTypeName;
   }

   public void setDataTypeName(String dataTypeName) 
   {
      this.dataTypeName = dataTypeName;
   }

   public String getPackageName() 
   {
      return  packageName;
   }

   public void setPackageName(String packageName) 
   {
      this.packageName = packageName;
   }

   public String getClassName() 
   {
      return  className;
   }

   public void setClassName(String className) 
   {
      this.className = className;
   }

   public String getReturnType() 
   {
      return  returnType;
   }

   public void setReturnType(String returnType) 
   {
      this.returnType = returnType;
   }
   
   public int getClassMethodIdentifier() 
   {
	  return classMethodIdentifier;
   }

   public void setClassMethodIdentifier(int classMethodIdentifier) 
   {
	  this.classMethodIdentifier = classMethodIdentifier;
   }
   
   public String getReturnTypeFullName() 
   {
      return returnTypeFullName;
   }

   public void setReturnTypeFullName(String returnTypeFullName) 
   {
      this.returnTypeFullName = returnTypeFullName;
   }
      
   public int getReturnTypeIdentifier2() 
   {
      return returnTypeIdentifier2;
   }

   public void setReturnTypeIdentifier2(int returnTypeIdentifier2) 
   {
      this.returnTypeIdentifier2 = returnTypeIdentifier2;
   }

   @Override
   public String toString() 
   {
      return "ReturnTypeSequenceDiagram [projectIdentifier=" + projectIdentifier + ", methodIdentifier="
			 + methodIdentifier + ", returnTypeIdentifier=" + returnTypeIdentifier + ", programmingLanguageIdentifier="
			 + programmingLanguageIdentifier + ", returnTypeName=" + returnTypeName + ", dataTypeIdentifier="
			 + dataTypeIdentifier + ", dataTypeName=" + dataTypeName + ", packageName=" + packageName + ", className="
			 + className + ", returnType=" + returnType + ", classMethodIdentifier=" + classMethodIdentifier
			 + ", returnTypeIdentifier2=" + returnTypeIdentifier2 + ", returnTypeFullName=" + returnTypeFullName + "]";
   }

}
