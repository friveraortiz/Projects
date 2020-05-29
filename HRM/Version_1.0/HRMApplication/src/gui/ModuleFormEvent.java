package gui;
import java.util.EventObject;

public class ModuleFormEvent extends EventObject 
{

    private String moduleName;	
    private String subModuleName;
    private String userLevel;
	
	public ModuleFormEvent(Object source) 
	{
		super(source);
	}
	
	public ModuleFormEvent(Object source, String moduleName, String subModuleName, String userLevel) 
	{
		
		super(source);
		this.moduleName = moduleName;
		this.subModuleName = subModuleName;
		this.userLevel = userLevel;

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

	public String getUserLevel() 
	{
		return userLevel;
	}

	public void setUserLevel(String userLevel) 
	{
		this.userLevel = userLevel;
	}

}