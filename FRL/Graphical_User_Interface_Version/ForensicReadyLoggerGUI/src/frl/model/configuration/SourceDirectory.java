package frl.model.configuration;

import java.io.Serializable;

// This Class is to store the data
public class SourceDirectory implements Serializable
{
	private static final long serialVersionUID = -3527169040243745742L;
	
	// Counter for the ClassMethod, start with number 1
	private static int count = 1;
	
	private int projectId;	
    private int idDir;	
    private String nameDir;
    private String pathDir;
    private int dbMetCount;
	
    // Constructor #1 of the Source Directory Class
 	public SourceDirectory(int projectId, String nameDir, String pathDir) //2
 	{
       this.projectId = projectId;
 	   this.nameDir   = nameDir;
 	   this.pathDir   = pathDir;	
 	   
	   // Everytime that I create a new Source Directory , it will increase the count counter
	   this.idDir = count;
	   count++;
 	}   
	   
    // Constructor #2 of the Source Directory Class
	public SourceDirectory(int projectId, int id, String name, String path) //2
	{
       this.projectId = projectId;
       this.idDir     = id;
	   this.nameDir   = name;
	   this.pathDir   = path;	
	}
	
	public SourceDirectory(int projectId, int id, int dbMetCount)
	{
       this.projectId  = projectId;
	   this.idDir      = id;
	   this.dbMetCount = dbMetCount;
	}

	public int getProjectId() 
	{
		return projectId;
	}

	public void setProjectId(int projectId) 
	{
		this.projectId = projectId;
	}

	public int getIdDir() 
	{
		return idDir;
	}

	public void setIdDir(int idDir) 
	{
		this.idDir = idDir;
	}

	public String getNameDir() 
	{
		return nameDir;
	}

	public void setNameDir(String nameDir) 
	{
		this.nameDir = nameDir;
	}

	public String getPathDir() 
	{
		return pathDir;
	}

	public void setPathDir(String pathDir) 
	{
		this.pathDir = pathDir;
	}

	public int getDbMetCount() 
	{
		return dbMetCount;
	}

	public void setDbMetCount(int dbMetCount) 
	{
		this.dbMetCount = dbMetCount;
	}

	@Override
	public String toString() 
	{
		return "SourceDirectory [projectId=" + projectId + ", idDir=" + idDir + ", nameDir=" + nameDir + ", pathDir="
				+ pathDir + ", dbMetCount=" + dbMetCount + "]";
	}
	
}
