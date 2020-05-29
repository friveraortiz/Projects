package frl.model;


// This Class is to store the data
public class TextFilePlain
{

    private int headerId;
    private int lineNumber;
    private String text;	
    
    // Constructor #1 of the TextFile Plain Class
	public TextFilePlain(int headerId, int lineNumber, String text)
	{
	   this.headerId        = headerId;
	   this.lineNumber      = lineNumber;
	   this.text            = text;
	}
	
    // Constructor #2 of the TextFile Plain Class
	public TextFilePlain(int lineNumber, String text)
	{
	   this.lineNumber      = lineNumber;
	   this.text            = text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}

	@Override
	public String toString() 
	{
		return "TextFilePlain [headerId=" + headerId + ", lineNumber=" + lineNumber + ", text=" + text + "]";
	}
	

	
	
}
