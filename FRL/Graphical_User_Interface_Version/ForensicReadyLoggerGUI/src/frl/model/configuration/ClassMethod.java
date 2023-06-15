package frl.model.configuration;

import java.io.Serializable;


// This Class is to store the data
public class ClassMethod implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the ClassMethod, start with number 1
	private static int count = 1;
	
	private int projectId;	
    private int id;	
    private String packageName;
    private String className;
    private String fullMethodName;
    private String shortMethodName;
    private String returnType1;
    private String returnType2;
    private String fileName;	
    private int startLineNumber;
    private int endLineNumber;
    private String textFileName;
    private boolean databaseMethod;
    private String parameterMethodName;
    private int ciId;
    private int databaseClass;
    private String databaseClassType;
    private int sdId;
    private int guiClass;
    private int interfaceClass;
	
    // Constructor #1 of the ClassMethod Class
	public ClassMethod(int projectId, String packageName, String className, String fullMethodName,
			           String shortMethodName, String returnType1, String returnType2,
			           String fileName, int startLineNumber, String paramMethodName, int sdId) //1
	{
	   this.projectId           = projectId;
       this.packageName         = packageName;
       this.className           = className;
       this.fullMethodName      = fullMethodName;
       this.shortMethodName     = shortMethodName;
       this.returnType1         = returnType1;
       this.returnType2         = returnType2;
       this.fileName            = fileName;
       this.startLineNumber     = startLineNumber;
       this.parameterMethodName = paramMethodName;
       this.sdId                = sdId;
		
	   // Everytime that I create a new ClassMethod, it will increase the count counter
	   this.id = count;
	   count++;
	}
	
    // Constructor #2 of the ClassMethod Class
	public ClassMethod(int projectId, int id, String packageName, String className, String fullMethodName,
                       String shortMethodName, String returnType1, String returnType2, 
                       String fileName, int startLineNumber, String parameterMethodName, int sdId) //2
	{
       // Calls the Constructor #1 of the ClassMethod Class
       this(projectId, packageName, className, fullMethodName, shortMethodName, returnType1, 
    		returnType2, fileName, startLineNumber, parameterMethodName, sdId);
	   this.id = id;

	}
	
    // Constructor #3 of the ClassMethod Class
	public ClassMethod(int projectId, String packageName, String className,
			           String shortMethodName, String returnType1, 
			           String returnType2, String fileName, 
			           int startLineNumber) //3
	{
	   this.projectId       = projectId;
       this.packageName     = packageName;
       this.className       = className;
       this.shortMethodName = shortMethodName;
       this.returnType1     = returnType1;
       this.returnType2     = returnType2;
       this.fileName        = fileName;
       this.startLineNumber = startLineNumber;
		
	   // Everytime that I create a new ClassMethod, it will increase the count counter
	   this.id = count;
	   count++;
	}
	
	// Constructor #4 of the ClassMethod Class
    public ClassMethod(int projectId, int id, String packageName, String className, String fullMethodName,
			           String shortMethodName, String returnType1, String returnType2,
			           String fileName, int startLineNumber, int endLineNumber, String parameterMethodName, int sdId) //4
    {
	   
       // Calls the Constructor #1 of the ClassMethod Class
       this(projectId, packageName, className, fullMethodName, shortMethodName, returnType1, 
    		returnType2, fileName, startLineNumber, parameterMethodName, sdId);
	   this.endLineNumber   = endLineNumber;
	   this.id = id;	    
	}
    
    // Constructor #5 of the ClassMethod Class
    public ClassMethod(int projectId, int id, String shortMethodName)
    {
       this.projectId       = projectId;
       this.id              = id;
       this.shortMethodName = shortMethodName;
    }
    
    // Constructor #6 of the ClassMethod Class
    public ClassMethod(int projectId, String shortMethodName, String returnType1)
    {
       this.projectId       = projectId;
       this.shortMethodName = shortMethodName;
       this.returnType1     = returnType1;
    }
    
    // Constructor #7 of the ClassMethod Class
    public ClassMethod(int projectId, int id, String packageName, String className, 
    		           String shortMethodName, String fullMethodName,
    		           String textFileName, boolean databaseMethod)
    {
       this.projectId       = projectId;
       this.id              = id;
       this.packageName     = packageName;
       this.className       = className;
       this.shortMethodName = shortMethodName;
       this.fullMethodName  = fullMethodName;
       this.textFileName    = textFileName;
       this.databaseMethod  = databaseMethod;
    }
    
    // Constructor #8 of the ClassMethod Class
	public ClassMethod(int projectId, String packageName, String className, String returnType1) //1
	{
	   this.projectId    = projectId;
	   this.packageName  = packageName;
       this.className    = className;
       this.returnType1  = returnType1;
	}   
	
	// Constructor #9 of the ClassMethod Class
	public ClassMethod(int projectId, int id, String textFileName, String parameterMethodName)
	{
       this.projectId           = projectId;
       this.id                  = id;
       this.textFileName        = textFileName;
       this.parameterMethodName = parameterMethodName;
	}
	
    // Constructor #10 of the ClassMethod Class
	public ClassMethod(int projectId, String packageName, String className, 
			           int id, String returnType1, String returnType2, String textFileName) //1
	{
	   this.projectId    = projectId;
	   this.packageName  = packageName;
       this.className    = className;
       this.id           = id;
       this.returnType1  = returnType1;
       this.returnType2  = returnType2;
       this.textFileName = textFileName;
	}  
	
    // Constructor #11 of the ClassMethod Class
    public ClassMethod(int projectId, int id, String packageName, String className, 
    		           String shortMethodName, String textFileName)
    {
       this.projectId       = projectId;
       this.id              = id;
       this.packageName     = packageName;
       this.className       = className;
       this.shortMethodName = shortMethodName;
       this.textFileName    = textFileName;
    }
    
    // Constructor #12 of the ClassMethod Class
	public ClassMethod(int projectId, int id, String packageName, String className,
			           String shortMethodName, String fullMethodName, 
			           String returnType1, String returnType2) //1
	{
	   this.projectId       = projectId;
	   this.id              = id;
       this.packageName     = packageName;
       this.className       = className;
       this.shortMethodName = shortMethodName;
       this.fullMethodName  = fullMethodName;
       this.returnType1     = returnType1;
       this.returnType2     = returnType2;
	}
	
    // Constructor #13 of the ClassMethod Class
	public ClassMethod(int projectId, int id, String packageName, String className, 
			           String shortMethodName, String fullMethodName, String fileName, 
			           int startLineNumber, int endLineNumber) //1
	{
	   this.projectId       = projectId;
	   this.id              = id;
       this.packageName     = packageName;
       this.className       = className;
       this.shortMethodName = shortMethodName;
       this.fullMethodName  = fullMethodName;
       this.fileName        = fileName;
       this.startLineNumber = startLineNumber;
       this.endLineNumber   = endLineNumber;
	}
		
    // Constructor #14 of the ClassMethod Class
	public ClassMethod(int projectId, int id, String packageName, String className,
			           String shortMethodName, String textFileName, int ciId)
	{
       this.projectId         = projectId;
	   this.id                = id;
	   this.packageName       = packageName;
	   this.className         = className;
	   this.shortMethodName   = shortMethodName;
	   this.textFileName      = textFileName;
	   this.ciId              = ciId;
	}
	
	
    // Constructor #15 of the ClassMethod Class
	public ClassMethod(int projectId, String packageName, String className, String fileName, int ciId) //1
	{
	   this.projectId     = projectId;
	   this.packageName   = packageName;
       this.className     = className;
       this.fileName      = fileName;
       this.ciId          = ciId;
	}  
	
	
    // Constructor #16 of the ClassMethod Class
	public ClassMethod(int projectId, String fullMethodName, String parameterMethodName, int startLineNumber)
	{
       this.projectId           = projectId;
       this.fullMethodName      = fullMethodName;
       this.parameterMethodName = parameterMethodName;
       this.startLineNumber     = startLineNumber;
	}
	
	// Constructor #17 of the ClassMethod Class
	public ClassMethod(int projectId, int id, String fullMethodName, String parameterMethodName, String fileName,
			           int startLineNumber)
	{
	   this.projectId           = projectId;
	   this.id                  = id;
	   this.fullMethodName      = fullMethodName;
	   this.parameterMethodName = parameterMethodName;
	   this.fileName            = fileName;
	   this.startLineNumber     = startLineNumber;
	}
	
    // Constructor #18 of the ClassMethod Class
	public ClassMethod(int projectId, int id, String packageName, String className,
			           String shortMethodName, String fullMethodName, String parameterMethodName,
			           String returnType1, String returnType2, String textFileName, int ciId, 
			           int databaseClass, String databaseClassType, int guiClass, int interfaceClass)
	{
	   this.projectId           = projectId;
	   this.id                  = id;
       this.packageName         = packageName;
       this.className           = className;
       this.shortMethodName     = shortMethodName;
       this.fullMethodName      = fullMethodName;
       this.parameterMethodName = parameterMethodName;
       this.returnType1         = returnType1;
       this.returnType2         = returnType2;
       this.textFileName        = textFileName;
       this.ciId                = ciId;
       this.databaseClass       = databaseClass;
       this.databaseClassType   = databaseClassType;
       this.guiClass            = guiClass;
       this.interfaceClass      = interfaceClass;
	}
	
	// Constructor #19
	public ClassMethod(int projectId, int methodId, String packageName, String className, 
                       String shortMethodName, String returnType1, String returnType2,
                       String parameterMethodName, String textFileName)
    {
	   this.projectId       = projectId;
	   this.id              = methodId;
	   this.packageName     = packageName;
	   this.className       = className;
	   this.shortMethodName = shortMethodName;	
       this.returnType1     = returnType1;
       this.returnType2     = returnType2;
       this.parameterMethodName = parameterMethodName;
       this.textFileName    = textFileName;
    }
	
	// Constructor #20
	public ClassMethod(int projectId, String packageName, String className, 
                       String shortMethodName, String fullMethodName)
	{
		   this.projectId       = projectId;
		   this.packageName     = packageName;
		   this.className       = className;
		   this.shortMethodName = shortMethodName;	
	       this.fullMethodName  = fullMethodName;		
	}

    // Constructor #21 of the ClassMethod Class
    public ClassMethod(int projectId, String packageName, String className, 
    		           String shortMethodName, String returnType1, 
    		           String returnType2)
    {
       this.projectId       = projectId;
       this.packageName     = packageName;
	   this.className       = className;
       this.shortMethodName = shortMethodName;
       this.returnType1     = returnType1;
       this.returnType2     = returnType2; 
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

	public String getFullMethodName() {
		return fullMethodName;
	}

	public void setFullMethodName(String fullMethodName) {
		this.fullMethodName = fullMethodName;
	}

	public String getShortMethodName() {
		return shortMethodName;
	}

	public void setShortMethodName(String shortMethodName) 
	{
	   this.shortMethodName = shortMethodName;
	}

	public String getReturnType1() 
	{
	   return returnType1;
	}

	public void setReturnType1(String returnType1) 
	{
	   this.returnType1 = returnType1;
	}
	
	public String getReturnType2() 
	{
	   return returnType2;
	}

	public void setReturnType2(String returnType2) 
	{
	   this.returnType2 = returnType2;
	}

	public String getFileName() 
	{
	   return fileName;
	}

	public void setFileName(String fileName) 
	{
	   this.fileName = fileName;
	}

	public int getStartLineNumber() 
	{
	   return startLineNumber;
	}

	public void setStartLineNumber(int startLineNumber) 
	{
	   this.startLineNumber = startLineNumber;
	}

	public int getEndLineNumber() 
	{
	   return endLineNumber;
	}

	public void setEndLineNumber(int endLineNumber) 
	{
	   this.endLineNumber = endLineNumber;
	}
	

	public String getTextFileName() 
	{
		return textFileName;
	}

	public void setTextFileName(String textFileName) 
	{
		this.textFileName = textFileName;
	}

	public boolean getDatabaseMethod() 
	{
		return databaseMethod;
	}

	public void setDatabaseMethod(boolean databaseMethod) 
	{
		this.databaseMethod = databaseMethod;
	}

	public String getParameterMethodName() 
	{
		return parameterMethodName;
	}

	public void setParameterMethodName(String parameterMethodName) 
	{
		this.parameterMethodName = parameterMethodName;
	}

	public int getCiId() {
		return ciId;
	}

	public void setCiId(int ciId) {
		this.ciId = ciId;
	}
	

	public int getDatabaseClass() {
		return databaseClass;
	}

	public void setDatabaseClass(int databaseClass) {
		this.databaseClass = databaseClass;
	}

	public String getDatabaseClassType() {
		return databaseClassType;
	}

	public void setDatabaseClassType(String databaseClassType) {
		this.databaseClassType = databaseClassType;
	}

	public int getSdId() 
	{
		return sdId;
	}

	public void setSdId(int sdId) 
	{
		this.sdId = sdId;
	}
	

	public int getGuiClass() 
	{
		return guiClass;
	}

	public void setGuiClass(int guiClass) 
	{
		this.guiClass = guiClass;
	}

	public int getInterfaceClass() 
	{
		return interfaceClass;
	}

	public void setInterfaceClass(int interfaceClass) 
	{
		this.interfaceClass = interfaceClass;
	}

	@Override
	public String toString() 
	{
		return "ClassMethod [projectId=" + projectId + ", id=" + id + ", packageName=" + packageName + ", className="
				+ className + ", fullMethodName=" + fullMethodName + ", shortMethodName=" + shortMethodName
				+ ", returnType1=" + returnType1 + ", returnType2=" + returnType2 + ", fileName=" + fileName
				+ ", startLineNumber=" + startLineNumber + ", endLineNumber=" + endLineNumber + ", textFileName="
				+ textFileName + ", databaseMethod=" + databaseMethod + ", parameterMethodName=" + parameterMethodName
				+ ", ciId=" + ciId + ", databaseClass=" + databaseClass + ", databaseClassType=" + databaseClassType
				+ ", sdId=" + sdId + ", guiClass=" + guiClass + ", interfaceClass=" + interfaceClass + "]";
	}

}