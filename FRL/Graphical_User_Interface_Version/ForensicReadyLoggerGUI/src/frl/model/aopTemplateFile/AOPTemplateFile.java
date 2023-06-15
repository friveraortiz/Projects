package frl.model.aopTemplateFile;

import java.io.Serializable;


public class AOPTemplateFile implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the Employees, start with number 1
	private static int count = 1;
	
    private int id;	
    private String fileName;	
    private String path;
    private String name;
    private String typeFile;
    private String packageName;
    private int programmingLanguageId;
    private String programmingLanguageName;
    private String pointCutName1;
    private String pointCutName2;
	
    // Constructor #1 of the AOPTemplateFile Class that assigns an ID
    // Method #1
	public AOPTemplateFile(String fileName, String path, String name, String typeFile, String packageName, 
			               int programmingLanguageId, String programmingLanguageName, String pointCutName1, String pointCutName2)
	{
	   this.fileName      = fileName;
	   this.path          = path;
	   this.name          = name;
	   this.typeFile      = typeFile;
	   this.packageName   = packageName;
	   this.programmingLanguageId = programmingLanguageId;
	   this.programmingLanguageName = programmingLanguageName;
	   this.pointCutName1 = pointCutName1;
	   this.pointCutName2 = pointCutName2;

	   // Everytime that I create a new AOPTemplateFile, it will increase the count counter
	   this.id = count;
	   count++;
	}
	
    // Constructor #2 of the AOPTemplateFile Class that assigns an ID
    // Method #1
	public AOPTemplateFile(int id, String fileName, String path, String name, String typeFile, String packageName, 
			               int programmingLanguageId, String programmingLanguageName, String pointCutName1, String pointCutName2)
	{
	   this.id            = id;
	   this.fileName      = fileName;
	   this.path          = path;
	   this.name          = name;
	   this.typeFile      = typeFile;
	   this.packageName   = packageName;
	   this.programmingLanguageId = programmingLanguageId;
	   this.programmingLanguageName = programmingLanguageName;
	   this.pointCutName1 = pointCutName1;
	   this.pointCutName2 = pointCutName2;

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

	public String getTypeFile() 
	{
		return typeFile;
	}

	public void setTypeFile(String typeFile) 
	{
		this.typeFile = typeFile;
	}

	public String getPackageName() 
	{
		return packageName;
	}

	public void setPackageName(String packageName) 
	{
		this.packageName = packageName;
	}

	public int getProgrammingLanguageId() 
	{
		return programmingLanguageId;
	}

	public void setProgrammingLanguageId(int programmingLanguageId) 
	{
		this.programmingLanguageId = programmingLanguageId;
	}

	public String getProgrammingLanguageName() 
	{
		return programmingLanguageName;
	}

	public void setProgrammingLanguageName(String programmingLanguageName) {
		this.programmingLanguageName = programmingLanguageName;
	}

	public String getPointCutName1() 
	{
		return pointCutName1;
	}

	public void setPointCutName1(String pointCutName1) 
	{
		this.pointCutName1 = pointCutName1;
	}

	public String getPointCutName2() 
	{
		return pointCutName2;
	}

	public void setPointCutName2(String pointCutName2) 
	{
		this.pointCutName2 = pointCutName2;
	}

	@Override
	public String toString() {
		return "AOPTemplateFile [id=" + id + ", fileName=" + fileName + ", path=" + path + ", name=" + name
				+ ", typeFile=" + typeFile + ", packageName=" + packageName + ", programmingLanguageId="
				+ programmingLanguageId + ", programmingLanguageName=" + programmingLanguageName + ", pointCutName1="
				+ pointCutName1 + ", pointCutName2=" + pointCutName2 + "]";
	}

}