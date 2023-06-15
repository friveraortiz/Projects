package frl.gui.generateAspectOrientedFiles;
import java.util.EventObject;

//Package #6
//Class #2
public class AOPFileFormEvent extends EventObject 
{

   private static final long serialVersionUID = 1L;
   private int id;
   private String fileName;	
   private String path;
   private String name;
   private String fileType;
   private String packageName;
   
   // Constructor # 1
   // Method #1	
   public AOPFileFormEvent(Object source) 
   {
		super(source);
   }
	
   // Constructor # 2
   // Method #2
   public AOPFileFormEvent(Object source, int id, String fileName, String path, String name, 
			            String fileType, String packageName) 
   {
		super(source);
		this.id = id;
		this.fileName = fileName;
		this.path = path;
		this.name = name;
		this.fileType = fileType;
		this.packageName = packageName;
   }
	
   // Constructor # 3
   // Method #3
   public AOPFileFormEvent(Object source, String fileName, String path, String name, 
                        String fileType, String packageName) 
   {
		super(source);
		this.fileName = fileName;
		this.path = path;
		this.name = name;
		this.fileType = fileType;
		this.packageName = packageName;
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

   public String getFileType() 
   {
      return fileType;
   }

   public void setFileType(String fileType) 
   {
      this.fileType = fileType;
   }

   public String getPackageName() 
   {
      return packageName;
   }

   public void setPackageName(String packageName) 
   {
      this.packageName = packageName;
   }

}	

