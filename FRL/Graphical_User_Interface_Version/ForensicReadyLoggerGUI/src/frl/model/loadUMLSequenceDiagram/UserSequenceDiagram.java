package frl.model.loadUMLSequenceDiagram;

public class UserSequenceDiagram 
{

   private int projectIdentifier;
   private int userIdentifier;
   private String userName;
   private String textFileName;
   private String pngFileName;
   
   // Constructor #1
   public UserSequenceDiagram(int projectIdentifier, int userIdentifier, String userName) 
   {
      this.projectIdentifier = projectIdentifier;
	  this.userIdentifier    = userIdentifier;
	  this.userName          = userName;
   }
   
   // Constructor #2
   public UserSequenceDiagram(int projectIdentifier, int userIdentifier, String textFileName, String pngFileName) 
   {
      this.projectIdentifier = projectIdentifier;
	  this.userIdentifier    = userIdentifier;
	  this.textFileName      = textFileName;
	  this.pngFileName       = pngFileName;
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

   public String getUserName() 
   {
	  return userName;
   }

   public void setUserName(String userName) 
   {
	  this.userName = userName;
   }

   public String getTextFileName() 
   {
     return textFileName;
   }

   public void setTextFileName(String textFileName) 
   {
	  this.textFileName = textFileName;
   }

   public String getPngFileName() 
   {
	  return pngFileName;
   }

   public void setPngFileName(String pngFileName) 
   {
	  this.pngFileName = pngFileName;
   }

   @Override
   public String toString() 
   {
	   return "UserSequenceDiagram [projectIdentifier=" + projectIdentifier + ", userIdentifier=" + userIdentifier
			   + ", userName=" + userName + ", textFileName=" + textFileName + ", pngFileName=" + pngFileName + "]";
   }
   
}
