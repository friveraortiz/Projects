package frl.model.loadUMLSequenceDiagram;

public class ParameterSequenceDiagram 
{
   private int projectIdentifier;	
   private int methodIdentifier;	
   private int parameterIdentifier;
   private int programmingLanguageIdentifier;
   private String parameterName;

   private int dataTypeIdentifier;
   private String dataTypeName;
   private String packageName;
   private String className;
   private String returnType;
   private int classMethodIdentifier;
   
   private String parameterFullName;
   
   // Constructor #1
   public ParameterSequenceDiagram(int projectIdentifier, int methodIdentifier,
		                          int parameterIdentifier, int programmingLanguageIdentifier, 
		                          String parameterName, int dataTypeIdentifier, String dataTypeName, 
		                          String packageName, String className, String returnType, 
		                          int classMethodIdentifier) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.parameterIdentifier = parameterIdentifier;
	  this.programmingLanguageIdentifier = programmingLanguageIdentifier;
	  this.parameterName       = parameterName;
	  this.dataTypeIdentifier  = dataTypeIdentifier;
	  this.dataTypeName        = dataTypeName;
	  this.packageName         = packageName;
	  this.className           = className;
	  this.returnType          = returnType;
	  this.classMethodIdentifier = classMethodIdentifier;
   }

   // Constructor #2
   public ParameterSequenceDiagram(int projectIdentifier, int methodIdentifier,
                                   int parameterIdentifier, String parameterName, 
                                   int dataTypeIdentifier, String dataTypeName,
                                   String packageName) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.parameterIdentifier = parameterIdentifier;
	  this.parameterName       = parameterName;
	  this.dataTypeIdentifier  = dataTypeIdentifier;
	  this.dataTypeName        = dataTypeName;
	  this.packageName         = packageName;
   }
   
   // Constructor #3
   public ParameterSequenceDiagram(int projectIdentifier, int methodIdentifier,
                                   int parameterIdentifier, String parameterName, 
                                   int dataTypeIdentifier, String dataTypeName,
                                   String packageName, String className) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.parameterIdentifier = parameterIdentifier;
	  this.parameterName       = parameterName;
	  this.dataTypeIdentifier  = dataTypeIdentifier;
	  this.dataTypeName        = dataTypeName;
	  this.packageName         = packageName;
	  this.className           = className;
   }
   
   // Constructor #3
   public ParameterSequenceDiagram(int projectIdentifier, int methodIdentifier,
                                   int parameterIdentifier, String parameterName, 
                                   int dataTypeIdentifier, String dataTypeName,
                                   String packageName, String className, 
                                   String parameterFullName) 
   {
      this.projectIdentifier   = projectIdentifier;
	  this.methodIdentifier    = methodIdentifier;
	  this.parameterIdentifier = parameterIdentifier;
	  this.parameterName       = parameterName;
	  this.dataTypeIdentifier  = dataTypeIdentifier;
	  this.dataTypeName        = dataTypeName;
	  this.packageName         = packageName;
	  this.className           = className;
	  this.parameterFullName   = parameterFullName;
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

   public int getParameterIdentifier() 
   {
      return  parameterIdentifier;
   }

   public void setParameterIdentifier(int parameterIdentifier) 
   {
      this.parameterIdentifier = parameterIdentifier;
   }

   public int getProgrammingLanguageIdentifier() 
   {
      return  programmingLanguageIdentifier;
   }

   public void setProgrammingLanguageIdentifier(int programmingLanguageIdentifier) 
   {
      this.programmingLanguageIdentifier = programmingLanguageIdentifier;
   }

   public String getParameterName() 
   {
      return  parameterName;
   }

   public void setParameterName(String parameterName) 
   {
      this.parameterName = parameterName;
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
   
   public String getParameterFullName() 
   {
      return parameterFullName;
   }

   public void setParameterFullName(String parameterFullName) 
   {
      this.parameterFullName = parameterFullName;
   }

   @Override
   public String toString() 
   {
	   return "ParameterSequenceDiagram [projectIdentifier=" + projectIdentifier + ", methodIdentifier=" + methodIdentifier
			+ ", parameterIdentifier=" + parameterIdentifier + ", programmingLanguageIdentifier="
			+ programmingLanguageIdentifier + ", parameterName=" + parameterName + ", dataTypeIdentifier="
			+ dataTypeIdentifier + ", dataTypeName=" + dataTypeName + ", packageName=" + packageName + ", className="
			+ className + ", returnType=" + returnType + ", classMethodIdentifier=" + classMethodIdentifier
			+ ", parameterFullName=" + parameterFullName + "]";
   }

}
