package frl.model;

import java.io.Serializable;


// This Class is to read the data
public class GraphicalUserInterface implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the GraphicalUserInterface, start with number 1
	private static int count = 1;
    private int id;	
    private String name;	
    private int plId;

    // Constructor #1 of the GraphicalUserInterface Class that assigns an ID
	public GraphicalUserInterface(String name, int plId)
	{
       this.name       = name;
       this.plId       = plId;

	   // Everytime that I create a new GraphicalUserInterface Class, it will increase the count counter
	   this.id = count;
	   count++;
	    
	}
	
    // Constructor #2 of the GraphicalUserInterface Class that assigns an ID
	public GraphicalUserInterface(int id, String name, int plId)
	{
       // Calls the Constructor #1 of the GraphicalUserInterface Class
       this(name, plId);
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

	public int getPlId() 
	{
	   return plId;
	}

	public void setPlId(int plId) 
	{
	   this.plId = plId;
	}

	@Override
	public String toString() 
	{
		return "GraphicalUserInterface [id=" + id + ", name=" + name + ", plId=" + plId + "]";
	}


    
}
