package frl.model;

import java.io.Serializable;


// This Class is to store the data
public class DatabaseMethod1 implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the DatabaseMethod, start with number 1
	private static int count = 1;
    private int id;	
    private String name;	

    // Constructor #1 of the DatabaseMethod Class that assigns an ID
	public DatabaseMethod1(String name)
	{
       this.name        = name;

	   // Everytime that I create a new DatabaseMethod, it will increase the count counter
	   this.id = count;
	   count++;
	    
	}
	
    // Constructor #2 of the DatabaseMethod Class that assigns an ID
	public DatabaseMethod1(int id, String name)
	{
       // Calls the Constructor #1 of the ClassMethodDetails Class
       this(name);
	   this.id = id;

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
		return "DatabaseMethod [id=" + id + ", name=" + name + "]";
	}
	
	
    
}

