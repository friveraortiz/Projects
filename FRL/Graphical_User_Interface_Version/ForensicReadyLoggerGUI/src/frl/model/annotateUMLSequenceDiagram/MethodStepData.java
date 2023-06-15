package frl.model.annotateUMLSequenceDiagram;

public class MethodStepData 
{
	private int projectIdentifier;
	private int userIdentifier;
	private int methodIdentifier;
	private int methodStepIdentifier;
	private String methodName;
	private String methodTextFileName;
	private String methodImageFileName;
	
	// Constructor # 1
	public MethodStepData(int projectIdentifier, int userIdentifier, int methodIdentifier, 
			              int methodStepIdentifier, String methodName, 
			              String methodTextFileName, String methodImageFileName) 
	{
       this.projectIdentifier = projectIdentifier;
	   this.userIdentifier = userIdentifier;
	   this.methodIdentifier = methodIdentifier;
	   this.methodStepIdentifier = methodStepIdentifier;
	   this.methodName = methodName;
	   this.methodTextFileName = methodTextFileName;
	   this.methodImageFileName = methodImageFileName;
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

	public int getMethodIdentifier() 
	{
		return methodIdentifier;
	}

	public void setMethodIdentifier(int methodIdentifier) 
	{
		this.methodIdentifier = methodIdentifier;
	}

	public int getMethodStepIdentifier() 
	{
		return methodStepIdentifier;
	}

	public void setMethodStepIdentifier(int methodStepIdentifier) 
	{
		this.methodStepIdentifier = methodStepIdentifier;
	}

	public String getMethodName() 
	{
		return methodName;
	}

	public void setMethodName(String methodName) 
	{
		this.methodName = methodName;
	}

	public String getMethodTextFileName() 
	{
		return methodTextFileName;
	}

	public void setMethodTextFileName(String methodTextFileName) 
	{
		this.methodTextFileName = methodTextFileName;
	}

	public String getMethodImageFileName() 
	{
		return methodImageFileName;
	}

	public void setMethodImageFileName(String methodImageFileName) 
	{
		this.methodImageFileName = methodImageFileName;
	}

	@Override
	public String toString() 
	{
		return "MethodStepData [projectIdentifier=" + projectIdentifier + ", userIdentifier=" + userIdentifier
				+ ", methodIdentifier=" + methodIdentifier + ", methodStepIdentifier=" + methodStepIdentifier
				+ ", methodName=" + methodName + ", methodTextFileName=" + methodTextFileName + ", methodImageFileName="
				+ methodImageFileName + "]";
	}

}
