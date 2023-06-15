package frl.model.annotateUMLSequenceDiagram;

public class ReturnTypeData
{
	
   private int projectIdentifier;
   private int methodIdentifier;
   private int returnTypeIdentifier;
   private String returnTypeName;
   
   public ReturnTypeData(int projectIdentifier, int methodIdentifier, 
		                 int returnTypeIdentifier, String returnTypeName) 
   {
	  this.projectIdentifier    = projectIdentifier;
	  this.methodIdentifier     = methodIdentifier;
	  this.returnTypeIdentifier = returnTypeIdentifier;
	  this.returnTypeName       = returnTypeName;
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

   public int getReturnTypeIdentifier() 
   {
	  return returnTypeIdentifier;
   }

   public void setReturnTypeIdentifier(int returnTypeIdentifier) 
   {
	  this.returnTypeIdentifier = returnTypeIdentifier;
   }

   public String getReturnTypeName() 
   {
	  return returnTypeName;
   }

   public void setReturnTypeName(String returnTypeName) 
   {
	   this.returnTypeName = returnTypeName;
   }

   @Override
   public String toString() 
   {
	   return "ReturnTypeData [projectIdentifier=" + projectIdentifier + ", methodIdentifier=" + methodIdentifier
			   + ", returnTypeIdentifier=" + returnTypeIdentifier + ", returnTypeName=" + returnTypeName + "]";
   }
 
}