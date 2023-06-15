package frl.model.configuration;

import java.io.Serializable;


// This Class is to store the data
public class ClassInformation implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the ClassDB, start with number 1
	private static int count = 1;
	
	private int projectId;	
    private int id;	
    private String packageName;
    private String className;
    private String fileName;
    private int databaseClass;
    private String databaseClassType;
    private int interfaceFlag;
    private int guiClass;
    private int enumClass;
    
    // Constructor #1 of the ClassInformation Class
	public ClassInformation(int projectId, String packageName, String className, String fileName,
			                int databaseClass, String databaseClassType, int interfaceFlag, int guiClass, 
			                int enumClass) 
	{
	   this.projectId         = projectId;
	   this.packageName       = packageName;
	   this.className         = className;
	   this.fileName          = fileName;
	   this.databaseClass     = databaseClass;
	   this.databaseClassType = databaseClassType;
	   this.interfaceFlag     = interfaceFlag;
	   this.guiClass          = guiClass;
	   this.enumClass         = enumClass;
		
	   // Everytime that I create a new Class Attribute, it will increase the count counter
	   this.id = count;
	   count++;
	}
	
    // Constructor #2 of the ClassInformation Class
	public ClassInformation(int projectId, int id, String packageName, String className, String fileName,
                            int databaseClass, String databaseClassType, int interfaceFlag, int guiClass, 
                            int enumClass) 
	{
	   this.projectId         = projectId;
	   this.id                = id;
	   this.packageName       = packageName;
	   this.className         = className;
	   this.fileName          = fileName;
	   this.databaseClass     = databaseClass;
	   this.databaseClassType = databaseClassType;
	   this.interfaceFlag     = interfaceFlag;
	   this.guiClass          = guiClass;
	   this.enumClass         = enumClass;
	}
	
	// Constructor #3 of the ClassInformation Class
	public ClassInformation(int projectId, String packageName, String className, 
			                String fileName, int databaseClass)
	{
       this.projectId         = projectId;
	   this.packageName       = packageName;
	   this.className         = className;
	   this.fileName          = fileName;
	   this.databaseClass     = databaseClass;		
	}

	// Constructor #4 of the ClassInformation Class
	public ClassInformation(int projectId, int id, String packageName, String className, 
			                String fileName, String databaseClassType, int interfaceFlag)
	{
       this.projectId         = projectId;
       this.id                = id;
	   this.packageName       = packageName;
	   this.className         = className;
	   this.fileName          = fileName;
	   this.databaseClassType = databaseClassType;
	   this.interfaceFlag     = interfaceFlag;		
	}
	
	// Constructor #5 of the ClassInformation Class
	public ClassInformation(int projectId, int id, int databaseClass, String databaseClassType)
	{
       this.projectId         = projectId;
       this.id                = id;
       this.databaseClass     = databaseClass;
       this.databaseClassType = databaseClassType;
	}
	
	public int getProjectId() 
	{
		return projectId;
	}

	public void setProjectId(int projectId) 
	{
		this.projectId = projectId;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getPackageName() 
	{
		return packageName;
	}

	public void setPackageName(String packageName) 
	{
		this.packageName = packageName;
	}

	public String getClassName() 
	{
		return className;
	}

	public void setClassName(String className) 
	{
		this.className = className;
	}

	public String getFileName() 
	{
		return fileName;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public int getDatabaseClass() 
	{
		return databaseClass;
	}

	public void setDatabaseClass(int databaseClass) 
	{
		this.databaseClass = databaseClass;
	}

	public String getDatabaseClassType() 
	{
		return databaseClassType;
	}

	public void setDatabaseClassType(String databaseClassType) 
	{
		this.databaseClassType = databaseClassType;
	}

	public int getInterfaceFlag() 
	{
		return interfaceFlag;
	}

	public void setInterfaceFlag(int interfaceFlag) 
	{
		this.interfaceFlag = interfaceFlag;
	}
	
	
	public int getGuiClass() 
	{
		return guiClass;
	}

	public void setGuiClass(int guiClass) 
	{
		this.guiClass = guiClass;
	}

	public int getEnumClass() 
	{
		return enumClass;
	}

	public void setEnumClass(int enumClass) 
	{
		this.enumClass = enumClass;
	}

	@Override
	public String toString() 
	{
		return "ClassInformation [projectId=" + projectId + ", id=" + id + ", packageName=" + packageName
				+ ", className=" + className + ", fileName=" + fileName + ", databaseClass=" + databaseClass
				+ ", databaseClassType=" + databaseClassType + ", interfaceFlag=" + interfaceFlag + ", guiClass="
				+ guiClass + ", enumClass=" + enumClass + "]";
	}
	
}
