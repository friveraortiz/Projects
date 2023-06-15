package frl.gui.aopTemplateFile;
import java.util.EventObject;

//Package #6
//Class #2
public class AOPTemplateFileFormEvent extends EventObject 
{

   private static final long serialVersionUID = 1L;
   private int id;
   private String fileName;	
   private String path;
   private String name;
   private String typeFile;
   private String packageName;
   private int programmingLanguageId;
   private String programmingLanguageName;
   private String pointCut1Name;
   private String pointCut2Name;
   
   // Constructor # 1
   // Method #1	
   public AOPTemplateFileFormEvent(Object source) 
   {
      super(source);
   }

   // Constructor # 2
   // Method #1	
   public AOPTemplateFileFormEvent(Object source, int id, String fileName, String path, 
		                           String name, String typeFile, String packageName, int programmingLanguageId,
		                           String programmingLanguageName, String pointCut1Name, String pointCut2Name) 
   {
      super(source);
	  this.id       = id;
	  this.fileName = fileName;
	  this.path     = path;
	  this.name     = name;
	  this.typeFile = typeFile;
	  this.packageName = packageName;
	  this.programmingLanguageId = programmingLanguageId;
	  this.programmingLanguageName = programmingLanguageName;
	  this.pointCut1Name = pointCut1Name;
	  this.pointCut2Name = pointCut2Name;
   }
   
   // Constructor # 3
   // Method #1	
   public AOPTemplateFileFormEvent(Object source, String fileName, String path, 
		                           String name, String typeFile, String packageName, int programmingLanguageId,
		                           String programmingLanguageName, String pointCut1Name, String pointCut2Name) 
   {
      super(source);
	  this.fileName = fileName;
	  this.path     = path;
	  this.name     = name;
	  this.typeFile = typeFile;
	  this.packageName = packageName;
	  this.programmingLanguageId = programmingLanguageId;
	  this.programmingLanguageName = programmingLanguageName;
	  this.pointCut1Name = pointCut1Name;
	  this.pointCut2Name = pointCut2Name;
   }
   
   // Constructor # 4
   public AOPTemplateFileFormEvent(Object source, int programmingLanguageId, String programmingLanguageName)
   {
      super(source);
	  this.programmingLanguageId   = programmingLanguageId;
	  this.programmingLanguageName = programmingLanguageName;
   }
   
   // Constructor # 5
   public AOPTemplateFileFormEvent(Object source, String path)
   {
      super(source);
	  this.path = path;
   }   

   public int getId() 
   {
      return id;
   }

   public void setId(int id) 
   {
      this.id = id;
   }

   public String getFileName() 
   {
      return fileName;
   }

   public void setFileName(String fileName) 
   {
      this.fileName = fileName;
   }

   public String getPath() 
   {
      return path;
   }

   public void setPath(String path) 
   {
      this.path = path;
   }

   public String getName() 
   {
      return name;
   }

   public void setName(String name) 
   {
      this.name = name;
   }

   public String getTypeFile() 
   {
      return typeFile;
   }

   public void setTypeFile(String typeFile) 
   {
      this.typeFile = typeFile;
   }

   public String getPackageName() 
   {
      return packageName;
   }

   public void setPackageName(String packageName) 
   {
      this.packageName = packageName;
   }

   public int getProgrammingLanguageId() 
   {
      return programmingLanguageId;
   }

   public void setProgrammingLanguageId(int programmingLanguageId) 
   {
      this.programmingLanguageId = programmingLanguageId;
   }

   public String getProgrammingLanguageName() 
   {
      return programmingLanguageName;
   }

   public void setProgrammingLanguageName(String programmingLanguageName) 
   {
      this.programmingLanguageName = programmingLanguageName;
   }

   public String getPointCut1Name() 
   {
      return pointCut1Name;
   }

   public void setPointCut1Name(String pointCut1Name) 
   {
      this.pointCut1Name = pointCut1Name;
   }

   public String getPointCut2Name() 
   {
      return pointCut2Name;
   }

   public void setPointCut2Name(String pointCut2Name) 
   {
      this.pointCut2Name = pointCut2Name;
   }

   @Override
   public String toString() 
   {
      return "AOPTemplateFileFormEvent [id=" + id + ", fileName=" + fileName + ", path=" + path + ", name=" + name
			+ ", typeFile=" + typeFile + ", packageName=" + packageName + ", programmingLanguageId="
			+ programmingLanguageId + ", programmingLanguageName=" + programmingLanguageName + ", pointCut1Name="
			+ pointCut1Name + ", pointCut2Name=" + pointCut2Name + "]";
   }
}	