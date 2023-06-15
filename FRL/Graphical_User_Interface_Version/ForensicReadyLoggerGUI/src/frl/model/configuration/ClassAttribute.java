package frl.model.configuration;

import java.io.Serializable;


// This Class is to store the data
public class ClassAttribute implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the ClassMethod, start with number 1
	private static int count = 1;
	
	private int projectId;	
    private int id;	
    private String packageName;
    private String className;
    private String fullName;
    private String shortName;
    private int classInfoId;
    private String dataType;
    
    private int dbClassFlag;
    private String dbClassType;
    private int interfaceFlag;
    
    
    // Constructor #1 of the ClassAttribute Class
	public ClassAttribute(int projectId, String packageName, String className, String fullName,
			              String shortName, int classInfoId, String dataType) //1
	{
	   this.projectId   = projectId;
       this.packageName = packageName;
       this.className   = className;
       this.fullName    = fullName;
       this.shortName   = shortName;
       this.classInfoId = classInfoId;
       this.dataType    = dataType;
		
	   // Everytime that I create a new Class Attribute, it will increase the count counter
	   this.id = count;
	   count++;
	}
	
    // Constructor #2 of the ClassAttribute Class
	public ClassAttribute(int projectId, int id, String packageName, String className, String fullName,
                          String shortName, int classInfoId, String dataType) //1
	{
	   this.projectId   = projectId;
	   this.id          = id;	
	   this.packageName = packageName;
       this.className   = className;
       this.fullName    = fullName;
       this.shortName   = shortName;
       this.classInfoId = classInfoId;
       this.dataType    = dataType;
	}
	
	// Constructor #3 of the ClassAttribute Class
	public ClassAttribute(int projectId, int id, String shortName, String packageName, String className, String dataType)
    {
       this.projectId   = projectId;
	   this.id          = id;	
       this.shortName   = shortName;
       this.packageName = packageName;
       this.className   = className;
       this.dataType    = dataType;
    }
	
	// Constructor #4 of the ClassAttribute Class
	public ClassAttribute(int projectId, int attributeId, String packageName, String className, 
			              String shortName, String dataType, int classInfoId, int dbClassFlag, 
			              String dbClassType, int interfaceFlag)
	{
       this.projectId   = projectId;
	   this.id          = attributeId;		
	   this.packageName = packageName;
	   this.className   = className;
       this.shortName   = shortName;
       this.dataType    = dataType;
       this.classInfoId = classInfoId;
       this.dbClassFlag = dbClassFlag;
       this.dbClassType = dbClassType;
       this.interfaceFlag = interfaceFlag;
       
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

	public String getFullName() 
	{
		return fullName;
	}

	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}

	public String getShortName() 
	{
		return shortName;
	}

	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public int getClassInfoId() 
	{
		return classInfoId;
	}

	public void setClassInfoId(int classInfoId) 
	{
		this.classInfoId = classInfoId;
	}

	public String getDataType() 
	{
		return dataType;
	}

	public void setDataType(String dataType) 
	{
		this.dataType = dataType;
	}

	public int getDbClassFlag() {
		return dbClassFlag;
	}

	public void setDbClassFlag(int dbClassFlag) 
	{
		this.dbClassFlag = dbClassFlag;
	}

	public String getDbClassType() 
	{
		return dbClassType;
	}

	public void setDbClassType(String dbClassType) 
	{
		this.dbClassType = dbClassType;
	}

	public int getInterfaceFlag() 
	{
		return interfaceFlag;
	}

	public void setInterfaceFlag(int interfaceFlag) 
	{
		this.interfaceFlag = interfaceFlag;
	}

	@Override
	public String toString() 
	{
		return "ClassAttribute [projectId=" + projectId + ", id=" + id + ", packageName=" + packageName + ", className="
				+ className + ", fullName=" + fullName + ", shortName=" + shortName + ", classInfoId=" + classInfoId
				+ ", dataType=" + dataType + ", dbClassFlag=" + dbClassFlag + ", dbClassType=" + dbClassType
				+ ", interfaceFlag=" + interfaceFlag + "]";
	}
    


}
