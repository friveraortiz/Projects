package model;

import java.io.Serializable;


// This Class is to store the data, it has the same atributes as the ModuleFormEvent Class
public class Module implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;
	
	// Counter for the Modules, start with number 1
	private static int count = 1;
	
    private int id;	
    private String moduleName;	
    private String subModuleName;
    private UserLevel userLevel;
	
    // Constructor #1 of the Module Class that assigns an ID
	public Module(String moduleName, String subModuleName, UserLevel userLevel)
	{
		this.moduleName = moduleName;
		this.subModuleName = subModuleName;
		this.userLevel = userLevel;
		
	    // Everytime that I create a new module, it will increase the count counter
	    this.id = count;
	    count++;
	    
	}
	
    // Constructor #2 of the Module Class that assigns an ID
	public Module(int id, String moduleName, String subModuleName, UserLevel userLevel)
	{
		// Calls the Constructor #1 of the User Class
        this(moduleName, subModuleName, userLevel);
		
		this.id = id;
		
	}
	

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getModuleName() 
	{
		return moduleName;
	}

	public void setModuleName(String moduleName) 
	{
		this.moduleName = moduleName;
	}

	public String getSubModuleName() 
	{
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) 
	{
		this.subModuleName = subModuleName;
	}

	public UserLevel getUserLevel() 
	{
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) 
	{
		this.userLevel = userLevel;
	}

	@Override
	public String toString() 
	{
		return id + ": " + moduleName + " "+ subModuleName + " "+ userLevel;
	}
	
}
