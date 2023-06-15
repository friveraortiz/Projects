package frl.model.loadUMLSequenceDiagram;

public class MethodSequenceDiagram 
{
	private int projectIdentifier;	
	private int methodIdentifier;	
	private String ownerName;			
	private String packageName;			
	private String className;			
	private String shortName;
	private String fullName;			
	private int classMethodIdentifier;
	
	private String textFileName1;
	private String textFileName2;
	private String pngFileName2;
	
    // Constructor #1 of the Method Sequence Diagram Class
	public MethodSequenceDiagram(int projectIdentifier,	int methodIdentifier,	
                                 String ownerName, String packageName, String className,			
                                 String shortName, int classMethodIdentifier, 
                                 String fullName, String textFileName1)
	{
	   this.projectIdentifier = projectIdentifier;
	   this.methodIdentifier  = methodIdentifier;
	   this.ownerName         = ownerName;
	   this.packageName       = packageName;
	   this.className         = className;
	   this.shortName         = shortName;
	   this.classMethodIdentifier = classMethodIdentifier;
	   this.fullName          = fullName;
	   this.textFileName1     = textFileName1;
	}
	
    // Constructor #2 of the Method Sequence Diagram Class
	public MethodSequenceDiagram(int projectIdentifier,	int methodIdentifier, String fullName)
	{
	   this.projectIdentifier = projectIdentifier;
	   this.methodIdentifier  = methodIdentifier;
	   this.fullName          = fullName;
	}
	
    // Constructor #3 of the Method Sequence Diagram Class
	public MethodSequenceDiagram(int projectIdentifier,	int methodIdentifier, String ownerName, 
			                     String packageName, String className, String shortName)
	{
	   this.projectIdentifier = projectIdentifier;
	   this.methodIdentifier  = methodIdentifier;
	   this.ownerName         = ownerName;
	   this.packageName       = packageName;
	   this.className         = className;
	   this.shortName         = shortName;
	}
	
    // Constructor #4 of the Method Sequence Diagram Class
	public MethodSequenceDiagram(int projectIdentifier,	int methodIdentifier, String ownerName, 
			                     String packageName, String className, String shortName, 
			                     String fullName)
	{
	   this.projectIdentifier = projectIdentifier;
	   this.methodIdentifier  = methodIdentifier;
	   this.ownerName         = ownerName;
	   this.packageName       = packageName;
	   this.className         = className;
	   this.shortName         = shortName;
	   this.fullName          = fullName;
	}	
	
	public int getProjectIdentifier() 
	{
		return projectIdentifier;
	}

	public void setProjectIdentifier(int projectIdentifier) 
	{
		this.projectIdentifier = projectIdentifier;
	}

	public int getMethodIdentifier() 
	{
		return methodIdentifier;
	}

	public void setMethodIdentifier(int methodIdentifier) 
	{
		this.methodIdentifier = methodIdentifier;
	}

	public String getOwnerName() 
	{
		return ownerName;
	}

	public void setOwnerName(String ownerName) 
	{
		this.ownerName = ownerName;
	}

	public String getPackageName() 
	{
		return packageName;
	}

	public void setPackageName(String packageName) 
	{
		this.packageName = packageName;
	}

	public String getClassName() 
	{
		return className;
	}

	public void setClassName(String className) 
	{
		this.className = className;
	}

	public String getShortName() 
	{
		return shortName;
	}

	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public int getClassMethodIdentifier() 
	{
		return classMethodIdentifier;
	}

	public void setClassMethodIdentifier(int classMethodIdentifier) 
	{
		this.classMethodIdentifier = classMethodIdentifier;
	}
	
	
	public String getFullName() 
	{
		return fullName;
	}

	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}

	public String getTextFileName1() 
	{
		return textFileName1;
	}

	public void setTextFileName1(String textFileName1) 
	{
		this.textFileName1 = textFileName1;
	}
	
	public String getTextFileName2() 
	{
		return textFileName2;
	}

	public void setTextFileName2(String textFileName2) 
	{
		this.textFileName2 = textFileName2;
	}

	public String getPngFileName2() 
	{
		return pngFileName2;
	}

	public void setPngFileName2(String pngFileName2) 
	{
		this.pngFileName2 = pngFileName2;
	}

	@Override
	public String toString() 
	{
		return "MethodSequenceDiagram [projectIdentifier=" + projectIdentifier + ", methodIdentifier="
				+ methodIdentifier + ", ownerName=" + ownerName + ", packageName=" + packageName + ", className="
				+ className + ", shortName=" + shortName + ", fullName=" + fullName + ", classMethodIdentifier="
				+ classMethodIdentifier + ", textFileName1=" + textFileName1 + ", textFileName2=" + textFileName2
				+ ", pngFileName2=" + pngFileName2 + "]";
	}

}
