package frl.model.loadUMLSequenceDiagram;


public class UserFileSequenceDiagram
{

   private int projectIdentifier;
   private int userIdentifier;	
   private int fileIdentifier;	
   private String textFilename;		
   private String pngFilename;
   
   // Constructor of the Class #1
   public UserFileSequenceDiagram(int projectIdentifier, int userIdentifier, int fileIdentifier, 
		                          String textFilename, String pngFilename) 
   {
	  this.projectIdentifier = projectIdentifier; 
      this.userIdentifier    = userIdentifier;
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
	   return "UserFilesSequenceDiagram [projectIdentifier=" + projectIdentifier + ", userIdentifier=" + userIdentifier
			  + ", fileIdentifier=" + fileIdentifier + ", textFilename=" + textFilename + ", pngFilename=" + pngFilename
			  + "]";
   }
   
}
