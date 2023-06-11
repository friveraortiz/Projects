package main;

public class Activity 
{
	private int id;	
    private String module;
    private String description;
    
	public Activity(int id, String module, String description) 
	{
       this.id          = id;
       this.module      = module;
       this.description = description;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getModule() 
	{
		return module;
	}

	public void setModule(String module) 
	{
		this.module = module;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	@Override
	public String toString() 
	{
		return "Activity [id=" + id + ", module=" + module + ", description=" + description + "]";
	}	

}
