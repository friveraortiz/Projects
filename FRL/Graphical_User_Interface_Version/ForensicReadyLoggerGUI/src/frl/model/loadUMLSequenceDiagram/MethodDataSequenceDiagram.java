package frl.model.loadUMLSequenceDiagram;

public class MethodDataSequenceDiagram
{
	private int projectIdentifier;	
	private int methodIdentifier;	
	private String methodOwnerName;			
	private String methodPackageName;			
	private String methodClassName;			
	private String methodShortName;
	private String methodFullName;		
	
	private int classMethodIdentifier;
	private String classMethodPackageName;			
	private String classMethodClassName;			
	private String classMethodShortName;
	private String classMethodFullName;		
	private String classMethodReturnType1;		
	private String classMethodReturnType2;		
	private String classMethodTextFileName;
	
	private int returnTypeIdentifier;
	private String returnTypeName;
	
	// Constructor #1
	public MethodDataSequenceDiagram(int projectIdentifier, int methodIdentifier, String methodOwnerName,
			String methodPackageName, String methodClassName, String methodShortName, String methodFullName,
			int classMethodIdentifier, String classMethodPackageName, String classMethodClassName,
			String classMethodShortName, String classMethodFullName, String classMethodReturnType1,
			String classMethodReturnType2, String classMethodTextFileName, int returnTypeIdentifier,
			String returnTypeName) 
	{
		this.projectIdentifier       = projectIdentifier;
		this.methodIdentifier        = methodIdentifier;
		this.methodOwnerName         = methodOwnerName;
		this.methodPackageName       = methodPackageName;
		this.methodClassName         = methodClassName;
		this.methodShortName         = methodShortName;
		this.methodFullName          = methodFullName;
		this.classMethodIdentifier   = classMethodIdentifier;
		this.classMethodPackageName  = classMethodPackageName;
		this.classMethodClassName    = classMethodClassName;
		this.classMethodShortName    = classMethodShortName;
		this.classMethodFullName     = classMethodFullName;
		this.classMethodReturnType1  = classMethodReturnType1;
		this.classMethodReturnType2  = classMethodReturnType2;
		this.classMethodTextFileName = classMethodTextFileName;
		this.returnTypeIdentifier    = returnTypeIdentifier;
		this.returnTypeName          = returnTypeName;
		
	}
	
	// Constructor #2
	public MethodDataSequenceDiagram(int projectIdentifier, int methodIdentifier, int returnTypeIdentifier) 
	{
       this.projectIdentifier    = projectIdentifier;
	   this.methodIdentifier     = methodIdentifier;
	   this.returnTypeIdentifier = returnTypeIdentifier;
	}
	
	// Constructor #3
	public MethodDataSequenceDiagram(int projectIdentifier, String methodPackageName, 
			                         String methodClassName, String methodShortName) 
	{
		this.projectIdentifier       = projectIdentifier;
		this.methodPackageName       = methodPackageName;
		this.methodClassName         = methodClassName;
		this.methodShortName         = methodShortName;
	}
	
	// Constructor #4
	public MethodDataSequenceDiagram(int projectIdentifier, int methodIdentifier, String methodShortName, 
			                         String methodFullName, int classMethodIdentifier,
			                         int returnTypeIdentifier, String returnTypeName,
			                         String classMethodReturnType1, String classMethodReturnType2)
	{
       this.projectIdentifier = projectIdentifier;
       this.methodIdentifier  = methodIdentifier;
       this.methodShortName   = methodShortName;
       this.methodFullName    = methodFullName;
       this.classMethodIdentifier  = classMethodIdentifier;
       this.returnTypeIdentifier   = returnTypeIdentifier;
       this.returnTypeName         = returnTypeName;
       this.classMethodReturnType1 = classMethodReturnType1;
       this.classMethodReturnType2 = classMethodReturnType2;
	};

	public int getProjectIdentifier() 
	{
		return projectIdentifier;
	}

	public void setProjectIdentifier(int projectIdentifier) 
	{
		this.projectIdentifier = projectIdentifier;
	}

	public int getMethodIdentifier() 
	{
		return methodIdentifier;
	}

	public void setMethodIdentifier(int methodIdentifier) 
	{
		this.methodIdentifier = methodIdentifier;
	}

	public String getMethodOwnerName() 
	{
		return methodOwnerName;
	}

	public void setMethodOwnerName(String methodOwnerName) 
	{
		this.methodOwnerName = methodOwnerName;
	}

	public String getMethodPackageName() 
	{
		return methodPackageName;
	}

	public void setMethodPackageName(String methodPackageName) 
	{
		this.methodPackageName = methodPackageName;
	}

	public String getMethodClassName() 
	{
		return methodClassName;
	}

	public void setMethodClassName(String methodClassName) 
	{
		this.methodClassName = methodClassName;
	}

	public String getMethodShortName() 
	{
		return methodShortName;
	}

	public void setMethodShortName(String methodShortName) 
	{
		this.methodShortName = methodShortName;
	}

	public String getMethodFullName() 
	{
		return methodFullName;
	}

	public void setMethodFullName(String methodFullName) 
	{
		this.methodFullName = methodFullName;
	}

	public int getClassMethodIdentifier() 
	{
		return classMethodIdentifier;
	}

	public void setClassMethodIdentifier(int classMethodIdentifier) 
	{
		this.classMethodIdentifier = classMethodIdentifier;
	}

	public String getClassMethodPackageName() 
	{
		return classMethodPackageName;
	}

	public void setClassMethodPackageName(String classMethodPackageName) 
	{
		this.classMethodPackageName = classMethodPackageName;
	}

	public String getClassMethodClassName() 
	{
		return classMethodClassName;
	}

	public void setClassMethodClassName(String classMethodClassName) 
	{
		this.classMethodClassName = classMethodClassName;
	}

	public String getClassMethodShortName() 
	{
		return classMethodShortName;
	}

	public void setClassMethodShortName(String classMethodShortName) 
	{
		this.classMethodShortName = classMethodShortName;
	}

	public String getClassMethodFullName() 
	{
		return classMethodFullName;
	}

	public void setClassMethodFullName(String classMethodFullName) 
	{
		this.classMethodFullName = classMethodFullName;
	}

	public String getClassMethodReturnType1() 
	{
		return classMethodReturnType1;
	}

	public void setClassMethodReturnType1(String classMethodReturnType1) 
	{
		this.classMethodReturnType1 = classMethodReturnType1;
	}

	public String getClassMethodReturnType2() 
	{
		return classMethodReturnType2;
	}

	public void setClassMethodReturnType2(String classMethodReturnType2) 
	{
		this.classMethodReturnType2 = classMethodReturnType2;
	}

	public String getClassMethodTextFileName() 
	{
		return classMethodTextFileName;
	}

	public void setClassMethodTextFileName(String classMethodTextFileName) 
	{
		this.classMethodTextFileName = classMethodTextFileName;
	}

	public int getReturnTypeIdentifier() 
	{
		return returnTypeIdentifier;
	}

	public void setReturnTypeIdentifier(int returnTypeIdentifier) 
	{
		this.returnTypeIdentifier = returnTypeIdentifier;
	}

	public String getReturnTypeName() 
	{
		return returnTypeName;
	}

	public void setReturnTypeName(String returnTypeName) 
	{
		this.returnTypeName = returnTypeName;
	}

	@Override
	public String toString() 
	{
		return "MethodDetailsDataSequenceDiagram [projectIdentifier=" + projectIdentifier + ", methodIdentifier="
				+ methodIdentifier + ", methodOwnerName=" + methodOwnerName + ", methodPackageName=" + methodPackageName
				+ ", methodClassName=" + methodClassName + ", methodShortName=" + methodShortName + ", methodFullName="
				+ methodFullName + ", classMethodIdentifier=" + classMethodIdentifier + ", classMethodPackageName="
				+ classMethodPackageName + ", classMethodClassName=" + classMethodClassName + ", classMethodShortName="
				+ classMethodShortName + ", classMethodFullName=" + classMethodFullName + ", classMethodReturnType1="
				+ classMethodReturnType1 + ", classMethodReturnType2=" + classMethodReturnType2
				+ ", classMethodTextFileName=" + classMethodTextFileName + ", returnTypeIdentifier="
				+ returnTypeIdentifier + ", returnTypeName=" + returnTypeName + "]";
	}

}
