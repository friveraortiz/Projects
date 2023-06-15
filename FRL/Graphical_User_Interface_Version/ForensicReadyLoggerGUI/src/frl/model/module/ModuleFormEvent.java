package frl.model.module;
import java.util.EventObject;

//Package #5
//Class #1
public class ModuleFormEvent extends EventObject 
{

	private static final long serialVersionUID = 1L;
	private String moduleName;	
    private String subModuleName;
    private String userLevel;
	
    // Method #1
	public ModuleFormEvent(Object source) 
	{
		super(source);
	}
	
	// Method #2
	public ModuleFormEvent(Object source, String moduleName, String subModuleName, String userLevel) 
	{
		
		super(source);
		this.moduleName = moduleName;
		this.subModuleName = subModuleName;
		this.userLevel = userLevel;

	}
	
	// Method #3
	public String getModuleName() 
	{
		return moduleName;
	}
	
	// Method #4
	public void setModuleName(String moduleName) 
	{
		this.moduleName = moduleName;
	}
	
	// Method #5
	public String getSubModuleName() 
	{
		return subModuleName;
	}
	
	// Method #6
	public void setSubModuleName(String subModuleName) 
	{
		this.subModuleName = subModuleName;
	}
	
	// Method #7
	public String getUserLevel() 
	{
		return userLevel;
	}
	
	// Method #8
	public void setUserLevel(String userLevel) 
	{
		this.userLevel = userLevel;
	}

}