package frl.model.generateAspectOrientedFiles;

import java.io.Serializable;

// This Class is to store the data, it has the same attributes as the Programming Language Detail Class
public class ProgrammingLanguageDetail implements Serializable 
{
	private static final long serialVersionUID = -542727119035768593L;

    private int idHdr;
    private String name;
    private int idDet;
    private String elementName;
    private String elementValue;
    
    // Constructor #1 of the Programming Language Detail Class
	public ProgrammingLanguageDetail(int idHdr, int idDet, String elementName, String elementValue)
	{
		this.idHdr        = idHdr;
		this.idDet        = idDet;
		this.elementName  = elementName;
		this.elementValue = elementValue;
	}
	
    // Constructor #2 of the Programming Language Detail Class
	public ProgrammingLanguageDetail(int idHdr, String name, int idDet, String elementName, String elementValue)
	{
		this.idHdr        = idHdr;
		this.name         = name;
		this.idDet        = idDet;
		this.elementName  = elementName;
		this.elementValue = elementValue;
	}
	
    // Constructor #3 of the Programming Language Detail Class
	public ProgrammingLanguageDetail(int idDet, String elementName, String elementValue)
	{
		this.idDet        = idDet;
		this.elementName  = elementName;
		this.elementValue = elementValue;
	}

	public int getIdHdr() 
	{
		return idHdr;
	}

	public void setIdHdr(int idHdr) 
	{
		this.idHdr = idHdr;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getIdDet() 
	{
		return idDet;
	}

	public void setIdDet(int idDet) 
	{
		this.idDet = idDet;
	}

	public String getElementName() 
	{
		return elementName;
	}

	public void setElementName(String elementName) 
	{
		this.elementName = elementName;
	}

	public String getElementValue() 
	{
		return elementValue;
	}

	public void setElementValue(String elementValue) 
	{
		this.elementValue = elementValue;
	}

	@Override
	public String toString() 
	{
		return "ProgrammingLanguageDetail [idHdr=" + idHdr + ", name=" + name + ", idDet=" + idDet + ", elementName="
				+ elementName + ", elementValue=" + elementValue + "]";
	}
    

}
