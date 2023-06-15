package frl.model.module;

import java.io.Serializable;
import frl.model.user.UserLevel;

// This Class is to store the data, it has the same atributes as the ModuleFormEvent Class
//Package #7
//Class #1
public class Module implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;
	
	// Counter for the Modules, start with number 1
	private static int count = 1;
	
    private int id;	
    private String moduleName;	
    private UserLevel userLevel;
	
    // Constructor #1 of the Module Class that assigns an ID
    // Method #1
	public Module(String moduleName, UserLevel userLevel)
	{
		this.moduleName = moduleName;
		this.userLevel = userLevel;
		
	    // Everytime that I create a new module, it will increase the count counter
	    this.id = count;
	    count++;
	    
	}
	
    // Constructor #2 of the Module Class that assigns an ID
	// Method #2
	public Module(int id, String moduleName, UserLevel userLevel)
	{
		// Calls the Constructor #1 of the User Class
        this(moduleName, userLevel);
		
		this.id = id;
		
	}
	
	// Method #3
	public int getId() 
	{
		return id;
	}
	
	// Method #4
	public void setId(int id) 
	{
		this.id = id;
	}
	
	// Method #5
	public String getModuleName() 
	{
		return moduleName;
	}
	
	// Method #6
	public void setModuleName(String moduleName) 
	{
		this.moduleName = moduleName;
	}
	
	// Method #7
	public UserLevel getUserLevel() 
	{
		return userLevel;
	}
	
	// Method #8
	public void setUserLevel(UserLevel userLevel) 
	{
		this.userLevel = userLevel;
	}

	// Method #9
	public String toString() 
	{
		return id + ": " + moduleName + " "+ userLevel;
	}
	
}
