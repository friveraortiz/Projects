package frl.model.annotateUMLSequenceDiagram;

public class FileData 
{
	private int projectIdentifier;
	private int userIdentifier;
	private String userName;
	private String userTextFileName;
	private String userImageFileName;	
	private int methodId;
	private int methodStepId;
	private String methodTextFileName;
	private String methodImageFileName;
	
	public FileData(int projectIdentifier, int userIdentifier, String userName, 
			        String userTextFileName, String userImageFileName, int methodId, 
			        int methodStepId, String methodTextFileName, String methodImageFileName) 
	{
	   this.projectIdentifier   = projectIdentifier;
	   this.userIdentifier      = userIdentifier;
	   this.userName            = userName;
	   this.userTextFileName    = userTextFileName;
	   this.userImageFileName   = userImageFileName;
	   this.methodId            = methodId;
	   this.methodStepId        = methodStepId;
	   this.methodTextFileName  = methodTextFileName;
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

	public String getUserName() 
	{
	   return userName;
	}

	public void setUserName(String userName) 
	{
	   this.userName = userName;
	}

	public String getUserTextFileName() 
	{
	   return userTextFileName;
	}

	public void setUserTextFileName(String userTextFileName) 
	{
	   this.userTextFileName = userTextFileName;
	}

	public String getUserImageFileName() 
	{
	   return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) 
	{
	   this.userImageFileName = userImageFileName;
	}

	public int getMethodId() 
	{
       return methodId;
	}

	public void setMethodId(int methodId) 
	{
       this.methodId = methodId;
	}

	public int getMethodStepId() 
	{
       return methodStepId;
	}

	public void setMethodStepId(int methodStepId) 
	{
	   this.methodStepId = methodStepId;
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
       return "FileData [projectIdentifier=" + projectIdentifier + ", userIdentifier=" + userIdentifier + ", userName="
			  + userName + ", userTextFileName=" + userTextFileName + ", userImageFileName=" + userImageFileName
			  + ", methodId=" + methodId + ", methodStepId=" + methodStepId + ", methodTextFileName="
			  + methodTextFileName + ", methodImageFileName=" + methodImageFileName + "]";
	}
}
