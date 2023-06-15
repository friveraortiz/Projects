package frl.model.generateAspectOrientedFiles;

import java.io.Serializable;

// This Class is to store the data, it has the same attributes as the DBMS Class
public class DBMS implements Serializable 
{
	private static final long serialVersionUID = -542727119035768593L;

    private int id;	
    private String name;
	
    // Constructor #1 of the DBMS Class
	public DBMS(int id, String name)
	{
		this.id = id;
		this.name = name;	    
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	@Override
	public String toString() 
	{
		return "DBMS [id=" + id + ", name=" + name + "]";
	}


}
