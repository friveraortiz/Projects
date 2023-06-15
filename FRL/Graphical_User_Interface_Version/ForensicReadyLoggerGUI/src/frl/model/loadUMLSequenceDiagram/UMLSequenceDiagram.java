package frl.model.loadUMLSequenceDiagram;


public class UMLSequenceDiagram
{
    private int projectId;
    private int lineNumber;
    private String lineText;
    private LineType1 lineType1;
    private String lineType2;
    private DatumCategory1 datumCategory1;
    private String datumCategory2;
    private String datumValue;
    private String userName;
    private String methodName1; 
    private String methodName2;
    private String methodOwner;
    private int lineNumber1;
    private int lineNumber2;
    private int methodIdentifier;
    private int classMethodIdentifier;
	
    // Constructor #1 of the User Class that assigns an ID
    // Method #1
	public UMLSequenceDiagram(int projectId, int lineNumber, String lineText,
                              LineType1 lineType1, String lineType2, DatumCategory1 datumCategory1,
                              String datumCategory2, String datumValue, String userName)
	{
		this.projectId      = projectId;
		this.lineNumber     = lineNumber;
		this.lineText       = lineText;
		this.lineType1      = lineType1;
		this.lineType2      = lineType2;
		this.datumCategory1 = datumCategory1;
		this.datumCategory2 = datumCategory2;
		this.datumValue     = datumValue;
		this.userName       = userName;
	}

    // Constructor #2 of the User Class that assigns an ID
    // Method #2
	public UMLSequenceDiagram(int projectId, int lineNumber, String lineText,
                              LineType1 lineType1, String lineType2, DatumCategory1 datumCategory1,
                              String datumCategory2, String datumValue, String userName, 
                              String methodName1)
	{
		this.projectId      = projectId;
		this.lineNumber     = lineNumber;
		this.lineText       = lineText;
		this.lineType1      = lineType1;
		this.lineType2      = lineType2;
		this.datumCategory1 = datumCategory1;
		this.datumCategory2 = datumCategory2;
		this.datumValue     = datumValue;
		this.userName       = userName;
		this.methodName1    = methodName1;
	}	
	

    // Constructor #2 of the User Class that assigns an ID
    // Method #2
	public UMLSequenceDiagram(int projectId, int lineNumber1, int lineNumber2, 
			                  String methodName1, String methodName2, String methodOwner,
			                  int methodIdentifier, int classMethodIdentifier)
	{
		this.projectId      = projectId;
		this.lineNumber1    = lineNumber1;
		this.lineNumber2    = lineNumber2;
		this.methodName1    = methodName1;
		this.methodName2    = methodName2;
		this.methodOwner    = methodOwner;
		this.methodIdentifier = methodIdentifier;
		this.classMethodIdentifier = classMethodIdentifier;
	}	
	

	public int getProjectId() 
	{
		return projectId;
	}


	public void setProjectId(int projectId) 
	{
		this.projectId = projectId;
	}


	public int getLineNumber() 
	{
		return lineNumber;
	}


	public void setLineNumber(int lineNumber) 
	{
		this.lineNumber = lineNumber;
	}


	public String getLineText() 
	{
		return lineText;
	}


	public void setLineText(String lineText) 
	{
		this.lineText = lineText;
	}


	public LineType1 getLineType1() 
	{
		return lineType1;
	}


	public void setLineType1(LineType1 lineType1) 
	{
		this.lineType1 = lineType1;
	}


	public String getLineType2() 
	{
		return lineType2;
	}


	public void setLineType2(String lineType2) 
	{
		this.lineType2 = lineType2;
	}


	public DatumCategory1 getDatumCategory1() 
	{
		return datumCategory1;
	}


	public void setDatumCategory1(DatumCategory1 datumCategory1) 
	{
		this.datumCategory1 = datumCategory1;
	}


	public String getDatumCategory2() 
	{
		return datumCategory2;
	}


	public void setDatumCategory2(String datumCategory2) 
	{
		this.datumCategory2 = datumCategory2;
	}


	public String getDatumValue() 
	{
		return datumValue;
	}


	public void setDatumValue(String datumValue) 
	{
		this.datumValue = datumValue;
	}


	public String getUserName() 
	{
		return userName;
	}


	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	
	public String getMethodName1() 
	{
		return methodName1;
	}

	public void setMethodName1(String methodName1) 
	{
		this.methodName1 = methodName1;
	}
	
	public String getMethodName2() 
	{
		return methodName2;
	}

	public void setMethodName2(String methodName2) 
	{
		this.methodName2 = methodName2;
	}

	public String getMethodOwner() 
	{
		return methodOwner;
	}

	public void setMethodOwner(String methodOwner) 
	{
		this.methodOwner = methodOwner;
	}

	public int getLineNumber1() 
	{
		return lineNumber1;
	}

	public void setLineNumber1(int lineNumber1) 
	{
		this.lineNumber1 = lineNumber1;
	}

	public int getLineNumber2() 
	{
		return lineNumber2;
	}

	public void setLineNumber2(int lineNumber2) 
	{
		this.lineNumber2 = lineNumber2;
	}


	public int getMethodIdentifier() 
	{
		return methodIdentifier;
	}

	public void setMethodIdentifier(int methodIdentifier) 
	{
		this.methodIdentifier = methodIdentifier;
	}

	public int getClassMethodIdentifier() 
	{
		return classMethodIdentifier;
	}

	public void setClassMethodIdentifier(int classMethodIdentifier) 
	{
		this.classMethodIdentifier = classMethodIdentifier;
	}

	@Override
	public String toString() 
	{
		return "UMLSequenceDiagram [projectId=" + projectId + ", lineNumber=" + lineNumber + ", lineText=" + lineText
				+ ", lineType1=" + lineType1 + ", lineType2=" + lineType2 + ", datumCategory1=" + datumCategory1
				+ ", datumCategory2=" + datumCategory2 + ", datumValue=" + datumValue + ", userName=" + userName
				+ ", methodName1=" + methodName1 + ", methodName2=" + methodName2 + ", methodOwner=" + methodOwner
				+ ", lineNumber1=" + lineNumber1 + ", lineNumber2=" + lineNumber2 + ", methodIdentifier="
				+ methodIdentifier + ", classMethodIdentifier=" + classMethodIdentifier + "]";
	}

}