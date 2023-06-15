package frl.model.tree;

import java.io.Serializable;


// This Class is to store the data
public class DatabaseMethod2 implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

    private int id;	
    private String packageName;	
    private String className;
    private String textFileName;
    private String shortMethodName;
    private String paramMethodName;
    private String returnType1;
    private String returnType2;
    
    // Constructor #1 of the DatabaseMethod2 Class that assigns an ID
	public DatabaseMethod2(int id, String packageName, String className, 
			               String textFileName, String shortMethodName, 
			               String returnType1, String returnType2)
	{
       this.id              = id;
       this.packageName     = packageName;
       this.className       = className;
       this.textFileName    = textFileName;
       this.shortMethodName = shortMethodName;
       this.returnType1     = returnType1;
       this.returnType2     = returnType2;
	    
	}
	
    // Constructor #2 of the DatabaseMethod2 Class that assigns an ID
	public DatabaseMethod2(String packageName, String className, 
			               String shortMethodName,
			               String returnType1, String returnType2)
	{
       this.packageName     = packageName;
       this.className       = className;
       this.shortMethodName = shortMethodName;
       this.returnType1     = returnType1;
       this.returnType2     = returnType2;
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

	public String getTextFileName() 
	{
		return textFileName;
	}

	public void setTextFileName(String textFileName) 
	{
		this.textFileName = textFileName;
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
	

	public String getParamMethodName() 
	{
		return paramMethodName;
	}

	public void setParamMethodName(String paramMethodName) 
	{
		this.paramMethodName = paramMethodName;
	}

	@Override
	public String toString() 
	{
		return "DatabaseMethod2 [id=" + id + ", packageName=" + packageName + ", className=" + className
				+ ", textFileName=" + textFileName + ", shortMethodName=" + shortMethodName + ", returnType1="
				+ returnType1 + ", returnType2=" + returnType2 + "]";
	}

    
}