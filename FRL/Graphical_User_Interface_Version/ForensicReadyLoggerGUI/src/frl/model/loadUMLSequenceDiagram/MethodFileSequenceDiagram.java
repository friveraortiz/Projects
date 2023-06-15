package frl.model.loadUMLSequenceDiagram;


public class MethodFileSequenceDiagram
{
   private int projectIdentifier;
   private int userIdentifier;	
   private int methodIdentifier;	
   private int stepIdentifier;	
   private int fileIdentifier;	
   private String textFilename;		
   private String pngFilename;
   
   // Constructor of the Class #1
   public MethodFileSequenceDiagram(int projectIdentifier, int userIdentifier, int methodIdentifier, 
		                            int stepIdentifier, int fileIdentifier, String textFilename, 
		                            String pngFilename) 
   {
	  this.projectIdentifier = projectIdentifier;
	  this.userIdentifier    = userIdentifier;
	  this.methodIdentifier  = methodIdentifier;
	  this.stepIdentifier    = stepIdentifier;
	  this.fileIdentifier    = fileIdentifier;
	  this.textFilename      = textFilename;
	  this.pngFilename       = pngFilename;
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

   public int getFileIdentifier() 
   {
      return fileIdentifier;
   }

   public void setFileIdentifier(int fileIdentifier) 
   {
	  this.fileIdentifier = fileIdentifier;
   }

   public String getTextFilename() 
   {
	  return textFilename;
   }

   public void setTextFilename(String textFilename) 
   {
	  this.textFilename = textFilename;
   }

   public String getPngFilename() 
   {
	  return pngFilename;
   }

   public void setPngFilename(String pngFilename) 
   {
	  this.pngFilename = pngFilename;
   }

   @Override
   public String toString() 
   {
	   return "MethodFileSequenceDiagram [projectIdentifier=" + projectIdentifier + ", userIdentifier=" + userIdentifier
			  + ", methodIdentifier=" + methodIdentifier + ", stepIdentifier=" + stepIdentifier + ", fileIdentifier="
			  + fileIdentifier + ", textFilename=" + textFilename + ", pngFilename=" + pngFilename + "]";
   }

}
