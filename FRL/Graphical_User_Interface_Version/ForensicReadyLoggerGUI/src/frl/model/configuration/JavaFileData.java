package frl.model.configuration;

import java.io.Serializable;

public class JavaFileData implements Serializable 
{
	private static final long serialVersionUID = 2069130055260121464L;
	
	// Counter for the JavaFile class, it starts with the number 1
	private static int count = 1;
	
	private int classId;
	private String className;
	private String packageName;
	private String fullClassName;
	private String classFileName;
	
	public JavaFileData(String className, String packageName, 
			            String fullClassName, String classFileName) 
	{
       super();
	   this.className     = className;
	   this.packageName   = packageName;
	   this.fullClassName = fullClassName;
	   this.classFileName = classFileName;
	   
	   // Everytime that I create a new JavaFile, it will increase the count counter
	   this.classId = count;
	   count++;
	}
	
	public JavaFileData(String packageName, String className)
	{
       this.packageName = packageName;
	   this.className   = className;
	}

	public int getClassId() 
	{
		return classId;
	}

	public void setClassId(int classId) 
	{
		this.classId = classId;
	}

	public String getClassName() 
	{
		return className;
	}

	public void setClassName(String className) 
	{
		this.className = className;
	}

	public String getPackageName() 
	{
		return packageName;
	}

	public void setPackageName(String packageName) 
	{
		this.packageName = packageName;
	}

	public String getFullClassName() 
	{
		return fullClassName;
	}

	public void setFullClassName(String fullClassName) 
	{
		this.fullClassName = fullClassName;
	}

	public String getClassFileName() 
	{
		return classFileName;
	}

	public void setClassFileName(String classFileName) 
	{
		this.classFileName = classFileName;
	}

	@Override
	public String toString() 
	{
		return "JavaFile [classId=" + classId + ", className=" + className + ", packageName=" + packageName
				+ ", fullClassName=" + fullClassName + ", classFileName=" + classFileName + "]";
	}
	
	
}
