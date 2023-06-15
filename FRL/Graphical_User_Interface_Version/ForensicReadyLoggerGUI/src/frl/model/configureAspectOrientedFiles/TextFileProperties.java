package frl.model.configureAspectOrientedFiles;


// This Class is to store the data
public class TextFileProperties
{
    private int projectId;
    private int headerId;
    private int propId;
    private String name;
    private String value;
    private String dataType;
    private PropertyType type;
    
    // Constructor #1 of the TextFile Properties Class
	public TextFileProperties(int projectId, int headerId, int propId, String name, String value,
			                  String dataType, PropertyType type)
	{
	   this.projectId = projectId;	
	   this.headerId  = headerId;
       this.propId    = propId;
       this.name      = name;
       this.value     = value;
       this.dataType  = dataType;
       this.type      = type;
	}

    // Constructor #2 of the TextFile Properties Class
	public TextFileProperties(int projectId, String name, String value,
			                  String dataType, PropertyType type)
	{
	   this.projectId = projectId;		
       this.name      = name;
       this.value     = value;
       this.dataType  = dataType;
       this.type      = type;
	}
	
    // Constructor #3 of the TextFile Properties Class
	public TextFileProperties(int projectId, String value, String dataType, PropertyType type)
	{
	   this.projectId = projectId;	
       this.value     = value;
       this.dataType  = dataType;
       this.type      = type;
	}
	
    // Constructor #4 of the TextFile Properties Class
	public TextFileProperties(int projectId, int headerId, int propId, String value, 
			                  String dataType, PropertyType type)
	{
	   this.projectId = projectId;	
       this.headerId  = headerId;
	   this.propId    = propId;	
       this.value     = value;
       this.dataType  = dataType;
       this.type      = type;
	}
	
    // Constructor #5 of the TextFile Properties Class
	public TextFileProperties(int headerId, int propId, String name, String dataType, PropertyType type)
	{	
       this.headerId  = headerId;
	   this.propId    = propId;	
       this.name      = name;
       this.dataType  = dataType;
       this.type      = type;
	}
	
	public int getProjectId() 
	{
       return projectId;
	}

	public void setProjectId(int projectId) 
	{
	   this.projectId = projectId;
	}

	public int getHeaderId() 
	{
		return headerId;
	}

	public void setHeaderId(int headerId) 
	{
		this.headerId = headerId;
	}

	public int getPropId() 
	{
		return propId;
	}

	public void setPropId(int propId) 
	{
		this.propId = propId;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getValue() 
	{
		return value;
	}

	public void setValue(String value) 
	{
		this.value = value;
	}

	public String getDataType() 
	{
		return dataType;
	}

	public void setDataType(String dataType) 
	{
		this.dataType = dataType;
	}

	public PropertyType getType() 
	{
		return type;
	}

	public void setType(PropertyType type) 
	{
		this.type = type;
	}

	@Override
	public String toString() 
	{
		return "TextFileProperties [headerId=" + headerId + ", propId=" + propId + ", name=" + name + ", value=" + value
				+ ", dataType=" + dataType + ", type=" + type + "]";
	}

	
}
