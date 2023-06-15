package frl.model.configureAspectOrientedFiles;

// This Class is to store the data
public class TextFilePropertyName
{
    private int headerId;
    private int propId;
    private String name;
    private String dataType;
    private PropertyType type;
    
    // Constructor #1 of the TextFilePropertyName Class
	public TextFilePropertyName(int headerId, int propId, String name, String dataType, PropertyType type)
	{
	   this.headerId  = headerId;
       this.propId    = propId;
       this.name      = name;
       this.dataType  = dataType;
       this.type      = type;
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
		return "TextFilePropertyName [headerId=" + headerId + ", propId=" + propId + ", name=" + name + ", dataType="
				+ dataType + ", type=" + type + "]";
	}

	
}
