package model;

import java.io.Serializable;


// This Class is to store the data
public class JavaDBOperations implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the JavaDBOperations, start with number 1
	private static int count = 1;
	
    private int id;	
    private String name;	
    private String validation1;
    private String validation2;
   
    // Constructor #1 of the JavaDBOperations Class that assigns an ID
	public JavaDBOperations(String name, String validation1, String validation2)
	{
		this.name = name;
		this.validation1 = validation1;
		this.validation2 = validation2;
		
	    // Everytime that I create a new JavaDBOperations, it will increase the count counter
	    this.id = count;
	    count++;
	    
	}
	
    // Constructor #2 of the JavaDBOperations Class that assigns an ID
	public JavaDBOperations(int id, String name, String validation1, String validation2)
	{
		// Calls the Constructor #1 of the JavaDBOperations Class
        this(name, validation1, validation2);
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

	public String getValidation1() 
	{
		return validation1;
	}

	public void setValidation1(String validation1) 
	{
		this.validation1 = validation1;
	}

	public String getValidation2() 
	{
		return validation2;
	}

	public void setValidation2(String validation2) 
	{
		this.validation2 = validation2;
	}

	@Override
	public String toString() 
	{
		return "JavaDBOperations [name=" + name + ", validation1=" + validation1 + ", validation2=" + validation2 + "]";
	}
	
	

	
}
