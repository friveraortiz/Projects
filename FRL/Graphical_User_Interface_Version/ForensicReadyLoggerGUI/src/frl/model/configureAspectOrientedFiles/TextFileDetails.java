package frl.model.configureAspectOrientedFiles;

// This Class is to store the data
public class TextFileDetails
{

    private int headerId;
    private int lineNumber;
    private String text;	
    private FieldType type;
    private int propertyId;

    // Constructor #1 of the TextFile Details Class
	public TextFileDetails(int headerId, int lineNumber, String text, FieldType type)
	{
	   this.headerId        = headerId;
	   this.lineNumber      = lineNumber;
	   this.text            = text;
	   this.type            = type;
	}

    // Constructor #2 of the TextFile Details Class
	public TextFileDetails(int headerId, int lineNumber, String text, FieldType type, int propertyId)
	{
	   this.headerId        = headerId;
	   this.lineNumber      = lineNumber;
	   this.text            = text;
	   this.type            = type;
       this.propertyId      = propertyId;
	}	
	

	public int getHeaderId() 
	{
		return headerId;
	}

	public void setHeaderId(int headerId) 
	{
		this.headerId = headerId;
	}

	public int getLineNumber() 
	{
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) 
	{
		this.lineNumber = lineNumber;
	}

	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}

	public FieldType getType() 
	{
		return type;
	}

	public void setType(FieldType type) 
	{
		this.type = type;
	}

	public int getPropertyId() 
	{
		return propertyId;
	}

	public void setPropertyId(int propertyId) 
	{
		this.propertyId = propertyId;
	}

	@Override
	public String toString() 
	{
		return "TextFileDetails [headerId=" + headerId + ", lineNumber=" + lineNumber + ", text=" + text + ", type="
				+ type + ", propertyId=" + propertyId + "]";
	}

}
