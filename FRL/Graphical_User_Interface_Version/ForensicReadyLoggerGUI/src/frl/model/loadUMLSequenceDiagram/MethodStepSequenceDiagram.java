package frl.model.loadUMLSequenceDiagram;

public class MethodStepSequenceDiagram 
{

   private int projectIdentifier;	
   private int userIdentifier;
   private int methodIdentifier;	
   private int stepIdentifier;
   private int orderIdentifier;
   private String methodName;
   
   public MethodStepSequenceDiagram(int projectIdentifier, int userIdentifier, 
		                            int methodIdentifier, int stepIdentifier,
		                            int orderIdentifier, String methodName) 
   {
      this.projectIdentifier = projectIdentifier;
	  this.userIdentifier    = userIdentifier;
	  this.methodIdentifier  = methodIdentifier;
	  this.stepIdentifier    = stepIdentifier;
	  this.orderIdentifier   = orderIdentifier;
	  this.methodName        = methodName;
   }

   public int getProjectIdentifier() 
   {
	  return projectIdentifier;
   }

   public void setProjectIdentifier(int projectIdentifier) 
   {
	  this.projectIdentifier = projectIdentifier;
   }

   public int getUserIdentifier() 
   {
	  return userIdentifier;
   }

   public void setUserIdentifier(int userIdentifier) 
   {
	   this.userIdentifier = userIdentifier;
   }

   public int getMethodIdentifier() 
   {
	  return methodIdentifier;
   }

   public void setMethodIdentifier(int methodIdentifier) 
   {
	  this.methodIdentifier = methodIdentifier;
   }

   public int getStepIdentifier() 
   {
	  return stepIdentifier;
   }

   public void setStepIdentifier(int stepIdentifier) 
   {
     this.stepIdentifier = stepIdentifier;
   }
   
   public int getOrderIdentifier() 
   {
      return orderIdentifier;
   }

   public void setOrderIdentifier(int orderIdentifier) 
   {
      this.orderIdentifier = orderIdentifier;
   }

   public String getMethodName() 
   {
	  return methodName;
   }

   public void setMethodName(String methodName) 
   {
	  this.methodName = methodName;
   }

   @Override
   public String toString() 
   {
	  return "MethodStepSequenceDiagram [projectIdentifier=" + projectIdentifier + ", userIdentifier=" + userIdentifier
			 + ", methodIdentifier=" + methodIdentifier + ", stepIdentifier=" + stepIdentifier + ", orderIdentifier="
			 + orderIdentifier + ", methodName=" + methodName + "]";
   }

}
