package frl.model.configureAspectOrientedFiles;

// This Class is to store the data
public class TextFileHeader
{
    private int headerId;
    private String fileName;
    private String path;
    private String name;
    private FileType type;
    private String packageName;
    private String programmingLanguage;
    private String pointCut1Name;
    private String pointCut2Name;
    
    // Constructor #1 of the TextFile Header Class
	public TextFileHeader(int headerId, String fileName, String path, String name,
			             FileType type, String packageName, String programmingLanguage)
	{
	   this.headerId = headerId;
	   this.fileName = fileName;
	   this.path = path;
	   this.name = name;
	   this.type = type;
	   this.packageName = packageName;
	   this.programmingLanguage = programmingLanguage;

	}
	
    // Constructor #2 of the TextFile Header Class
	public TextFileHeader(String fileName, String path, String name,
			             FileType type, String packageName, String programmingLanguage)
	{
	   this.fileName = fileName;
	   this.path = path;
	   this.name = name;
	   this.type = type;
	   this.packageName = packageName;
	   this.programmingLanguage = programmingLanguage;

	}
	
    // Constructor #3 of the TextFile Header Class
	public TextFileHeader(int headerId, String fileName, String path, String name,
			             FileType type, String packageName, String programmingLanguage,
			             String pointCut1Name, String pointCut2Name)
	{
	   this.headerId = headerId;
	   this.fileName = fileName;
	   this.path = path;
	   this.name = name;
	   this.type = type;
	   this.packageName = packageName;
	   this.programmingLanguage = programmingLanguage;
	   this.pointCut1Name = pointCut1Name;
	   this.pointCut2Name = pointCut2Name;

	}

	public int getHeaderId() 
	{
		return headerId;
	}


	public void setHeaderId(int headerId) 
	{
		this.headerId = headerId;
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


	public FileType getType() 
	{
		return type;
	}


	public void setType(FileType type) 
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


	public String getProgrammingLanguage() 
	{
		return programmingLanguage;
	}


	public void setProgrammingLanguage(String programmingLanguage) 
	{
		this.programmingLanguage = programmingLanguage;
	}
	
	public String getPointCut1Name() 
	{
		return pointCut1Name;
	}

	public void setPointCut1Name(String pointCut1Name) 
	{
		this.pointCut1Name = pointCut1Name;
	}

	public String getPointCut2Name() 
	{
		return pointCut2Name;
	}

	public void setPointCut2Name(String pointCut2Name) 
	{
		this.pointCut2Name = pointCut2Name;
	}

	@Override
	public String toString() 
	{
		return "TextFileHeader [headerId=" + headerId + ", fileName=" + fileName + ", path=" + path + ", name=" + name
				+ ", type=" + type + ", packageName=" + packageName + ", programmingLanguage=" + programmingLanguage
				+ "]";
	}
	
}
