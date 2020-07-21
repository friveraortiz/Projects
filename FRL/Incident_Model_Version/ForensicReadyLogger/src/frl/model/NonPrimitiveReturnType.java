package frl.model;

import java.io.Serializable;


// This Class is to store the data
public class NonPrimitiveReturnType implements Serializable 
{


	private static final long serialVersionUID = 1L;
	private String returnType1;	
    private String returnType2;	
    private String library;

    
    // Constructor of the TextFile Details Class
	public NonPrimitiveReturnType(String returnType1, String returnType2, String library)
	{
	   this.returnType1 = returnType1;
	   this.returnType2 = returnType2;
	   this.library     = library;

	}


	public String getReturnType1() 
	{
		return returnType1;
	}


	public void setReturnType1(String returnType1) 
	{
		this.returnType1 = returnType1;
	}


	public String getReturnType2() 
	{
		return returnType2;
	}


	public void setReturnType2(String returnType2) 
	{
		this.returnType2 = returnType2;
	}


	public String getLibrary() 
	{
		return library;
	}


	public void setLibrary(String library) 
	{
		this.library = library;
	}


	@Override
	public String toString() 
	{
		return "NonPrimitiveReturnType [returnType1=" + returnType1 + ", returnType2=" + returnType2 + ", library="
				+ library + "]";
	}
	

}
