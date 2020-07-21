package frl.model;

import java.io.Serializable;


// This Class is to store the data
public class ClassMethod implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the ClassMethod, start with number 1
	private static int count = 1;
	
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
    
	
    // Constructor #1 of the ClassMethod Class
	public ClassMethod(String packageName, String className, String fullMethodName,
			           String shortMethodName, String returnType1, String returnType2,
			           String fileName, int startLineNumber) //1
	{
       this.packageName     = packageName;
       this.className       = className;
       this.fullMethodName  = fullMethodName;
       this.shortMethodName = shortMethodName;
       this.returnType1     = returnType1;
       this.returnType2     = returnType2;
       this.fileName        = fileName;
       this.startLineNumber = startLineNumber;
		
	   // Everytime that I create a new ClassMethod, it will increase the count counter
	   this.id = count;
	   count++;
	    
	}
	
    // Constructor #2 of the ClassMethod Class
	public ClassMethod(int id, String packageName, String className, String fullMethodName,
            String shortMethodName, String returnType1, String returnType2, 
            String fileName, int startLineNumber) //2
	{
       // Calls the Constructor #1 of the ClassMethod Class
       this(packageName, className, fullMethodName, shortMethodName, returnType1, 
    		returnType2, fileName, startLineNumber);
	   this.id = id;

	}
	
    // Constructor #3 of the ClassMethod Class
	public ClassMethod(String packageName, String className,
			           String shortMethodName, String returnType1, 
			           String returnType2, String fileName, 
			           int startLineNumber) //3
	{
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
    public ClassMethod(int id, String packageName, String className, String fullMethodName,
			           String shortMethodName, String returnType1, String returnType2,
			           String fileName, int startLineNumber, int endLineNumber) //4
    {
	   
       // Calls the Constructor #1 of the ClassMethod Class
       this(packageName, className, fullMethodName, shortMethodName, returnType1, 
    		returnType2, fileName, startLineNumber);
	   this.endLineNumber   = endLineNumber;
	   this.id = id;
					    
	}
    
    // Constructor #5 of the ClassMethod Class
    public ClassMethod(String shortMethodName, String returnType1)
    {
       this.shortMethodName = shortMethodName;
       this.returnType1 = returnType1;
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

	public String getFullMethodName() 
	{
		return fullMethodName;
	}

	public void setFullMethodName(String fullMethodName) 
	{
		this.fullMethodName = fullMethodName;
	}

	public String getShortMethodName() 
	{
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

	@Override
	public String toString() 
	{
		return "ClassMethod [id=" + id + ", packageName=" + packageName + ", className=" + className
				+ ", fullMethodName=" + fullMethodName + ", shortMethodName=" + shortMethodName + ", returnType="
				+ returnType1 + returnType2 + ", fileName=" + fileName + ", startLineNumber=" + startLineNumber + ", endLineNumber="
				+ endLineNumber + "]";
	}

}
