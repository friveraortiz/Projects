package frl.model.annotateUMLSequenceDiagram;

public class UserData 
{
	private int projectIdentifier;
	private int userIdentifier;
	private String userName;
	private String userTextFileName;
	private String userImageFileName;
	
	// Constructor #1
	public UserData(int projectIdentifier, int userIdentifier, String userName, 
			        String userTextFileName, String userImageFileName) 
	{
	   this.projectIdentifier = projectIdentifier;
	   this.userIdentifier    = userIdentifier;
	   this.userName          = userName;
	   this.userTextFileName  = userTextFileName;
	   this.userImageFileName = userImageFileName;
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

	@Override
	public String toString() 
	{
		return "UserData [projectIdentifier=" + projectIdentifier + ", userIdentifier=" + userIdentifier + ", userName="
				+ userName + ", userTextFileName=" + userTextFileName + ", userImageFileName=" + userImageFileName
				+ "]";
	}

}
