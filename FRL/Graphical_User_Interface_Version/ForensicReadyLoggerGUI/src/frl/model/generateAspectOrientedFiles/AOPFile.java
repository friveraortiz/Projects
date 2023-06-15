package frl.model.generateAspectOrientedFiles;

import java.io.Serializable;

// This Class is to store the data, it has the same atributes as the AOPFileFormEvent Class
//Package #8
//Class #1
public class AOPFile implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the Employees, start with number 1
	private static int count = 1;
	
    private int id;	
    private String fileName;	
    private String path;
    private String name;
    private AOPFileType type;
    private String packageName;
	
    // Constructor #1 of the AOPFile Class that assigns an ID
    // Method #1
	public AOPFile(String fileName, String path, String name, AOPFileType type, String packageName)
	{
		this.fileName = fileName;
		this.path     = path;
	    this.name     = name;
	    this.type     = type;
	    this.packageName = packageName;
		
	    // Everytime that I create a new AOPFile, it will increase the count counter
	    this.id = count;
	    count++;
	    
	}
	
    // Constructor #2 of the AOPFile Class that assigns an ID
    // Method #2
	public AOPFile(int id, String fileName, String path, String name, AOPFileType type, String packageName)
	{
		// Calls the Constructor #1 of the AOPFile Class
        this(fileName, path, name, type, packageName);
		
		this.id = id;
		
	}
	
    // Constructor #3 of the AOPFile Class that assigns an ID
    // Method #3
	public AOPFile(String fileName, String path)
	{
       this.fileName = fileName;
	   this.path = path;
		
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getFileName() 
	{
		return fileName;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getPath() 
	{
		return path;
	}

	public void setPath(String path) 
	{
		this.path = path;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public AOPFileType getType() 
	{
		return type;
	}

	public void setType(AOPFileType type) 
	{
		this.type = type;
	}

	public String getPackageName() 
	{
		return packageName;
	}

	public void setPackageName(String packageName) 
	{
		this.packageName = packageName;
	}

	@Override
	public String toString() 
	{
		return "AOPFile [id=" + id + ", fileName=" + fileName + ", path=" + path + ", name=" + name + ", type=" + type
				+ ", packageName=" + packageName + "]";
	}
	
	
}
