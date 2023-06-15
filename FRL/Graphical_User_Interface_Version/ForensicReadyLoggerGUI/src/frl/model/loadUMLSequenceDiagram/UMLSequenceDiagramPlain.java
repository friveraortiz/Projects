package frl.model.loadUMLSequenceDiagram;


public class UMLSequenceDiagramPlain
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
    private String methodPackageName;
    private String methodClassName;
    private int userIdentifier;
    private String textFileName;
    private String methodName3;
    private int stepIdentifier;
	
    // Constructor #1 of the User Class that assigns an ID
	public UMLSequenceDiagramPlain(int projectId, int lineNumber, String lineText,
                                   LineType1 lineType1, String lineType2, DatumCategory1 datumCategory1,
                                   String datumCategory2, String datumValue, String userName, int userIdentifier)
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
		this.userIdentifier = userIdentifier;
	}
	
    // Constructor #2 of the User Class that assigns an ID
	public UMLSequenceDiagramPlain(int projectId, int lineNumber, String lineText,
                              LineType1 lineType1, String lineType2, DatumCategory1 datumCategory1,
                              String datumCategory2, String datumValue)
	{
		this.projectId      = projectId;
		this.lineNumber     = lineNumber;
		this.lineText       = lineText;
		this.lineType1      = lineType1;
		this.lineType2      = lineType2;
		this.datumCategory1 = datumCategory1;
		this.datumCategory2 = datumCategory2;
		this.datumValue     = datumValue;
	}

    // Constructor #3 of the User Class that assigns an ID
	public UMLSequenceDiagramPlain(int projectId, int lineNumber, String lineText,
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
	
    // Constructor #4 of the User Class that assigns an ID
	public UMLSequenceDiagramPlain(int projectId, int lineNumber1, int lineNumber2, 
			                       String methodName1, String methodName2, String methodOwner,
			                       int classMethodIdentifier, 
			                       String methodPackageName, String methodClassName, String methodName3)
	{
		this.projectId      = projectId;
		this.lineNumber1    = lineNumber1;
		this.lineNumber2    = lineNumber2;
		this.methodName1    = methodName1;
		this.methodName2    = methodName2;
		this.methodOwner    = methodOwner;
		this.classMethodIdentifier = classMethodIdentifier;
		this.methodPackageName     = methodPackageName;
		this.methodClassName       = methodClassName;
		this.methodName3           = methodName3;
	}	
	
    // Constructor #5 of the User Class that assigns an ID
	public UMLSequenceDiagramPlain(int projectId, int lineNumber, LineType1 lineType1, 
			                       String lineType2, DatumCategory1 datumCategory1,
                                   String datumCategory2, String datumValue)
	{
		this.projectId      = projectId;
		this.lineNumber     = lineNumber;
		this.lineType1      = lineType1;
		this.lineType2      = lineType2;
		this.datumCategory1 = datumCategory1;
		this.datumCategory2 = datumCategory2;
		this.datumValue     = datumValue;
	}
	
    // Constructor #6 of the User Class that assigns an ID
	public UMLSequenceDiagramPlain(int projectId, int userIdentifier, int methodIdentifier, int lineNumber,
			                       String lineText, LineType1 lineType1, String lineType2, 
			                       DatumCategory1 datumCategory1, String datumCategory2, String datumValue, 
			                       int classMethodIdentifier, int stepId)
	{
		this.projectId             = projectId;
        this.userIdentifier        = userIdentifier;
        this.methodIdentifier      = methodIdentifier;
        this.lineNumber            = lineNumber;
		this.lineText              = lineText;
		this.lineType1             = lineType1;
		this.lineType2             = lineType2;
		this.datumCategory1        = datumCategory1;
		this.datumCategory2        = datumCategory2;
		this.datumValue            = datumValue;
		this.classMethodIdentifier = classMethodIdentifier;
		this.stepIdentifier        = stepId;
		
	}
	
	// Constructor #7 of the Class
	public UMLSequenceDiagramPlain(int projectId, int methodIdentifier, String methodPackageName,
			                       String methodClassName, String methodName2, 
			                       int classMethodIdentifier)
	{
	   this.projectId         = projectId;
	   this.methodIdentifier  = methodIdentifier;
	   this.methodPackageName = methodPackageName;
	   this.methodClassName   = methodClassName;
	   this.methodName2       = methodName2;
	   this.classMethodIdentifier = classMethodIdentifier;
	}
	
	// Constructor #8 of the Class
	public UMLSequenceDiagramPlain(int projectId, int methodIdentifier, String methodPackageName,
			                       String methodClassName, String methodName2)
	{
	   this.projectId         = projectId;
	   this.methodIdentifier  = methodIdentifier;
	   this.methodPackageName = methodPackageName;
	   this.methodClassName   = methodClassName;
	   this.methodName2       = methodName2;
	}
	
	// Constructor #9 of the Class 
	public UMLSequenceDiagramPlain(int projectId, int userIdentifier, int methodIdentifier,
                                   int lineNumber, String methodPackageName, String methodClassName,
                                   String methodName1)
	{
		
	   this.projectId         = projectId;
	   this.userIdentifier    = userIdentifier;
	   this.methodIdentifier  = methodIdentifier;
	   this.lineNumber        = lineNumber;
	   this.methodPackageName = methodPackageName;
	   this.methodClassName   = methodClassName;
	   this.methodName1       = methodName1;
		
	}
	
	// Constructor #10 of the Class
	public UMLSequenceDiagramPlain(int projectId, int userId, int methodId,
                                   String packageName, String className,
                                   String name1, int stepId)
	{
	   this.projectId         = projectId;
	   this.userIdentifier    = userId;
	   this.methodIdentifier  = methodId;
	   this.methodPackageName = packageName;
	   this.methodClassName   = className;
       this.methodName1       = name1;
       this.stepIdentifier    = stepId;
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
	
	public String getMethodPackageName() 
	{
		return methodPackageName;
	}

	public void setMethodPackageName(String methodPackageName) 
	{
		this.methodPackageName = methodPackageName;
	}

	public String getMethodClassName() 
	{
		return methodClassName;
	}

	public void setMethodClassName(String methodClassName) 
	{
		this.methodClassName = methodClassName;
	}

	public int getUserIdentifier() 
	{
		return userIdentifier;
	}

	public void setUserIdentifier(int userIdentifier) 
	{
		this.userIdentifier = userIdentifier;
	}
	
	public String getTextFileName() 
	{
		return textFileName;
	}

	public void setTextFileName(String textFileName) 
	{
		this.textFileName = textFileName;
	}

	public String getMethodName3() 
	{
		return methodName3;
	}

	public void setMethodName3(String methodName3) 
	{
		this.methodName3 = methodName3;
	}
	
	public int getStepIdentifier() 
	{
		return stepIdentifier;
	}

	public void setStepIdentifier(int stepIdentifier) 
	{
		this.stepIdentifier = stepIdentifier;
	}

	@Override
	public String toString() 
	{
		return "UMLSequenceDiagramPlain [projectId=" + projectId + ", lineNumber=" + lineNumber + ", lineText="
				+ lineText + ", lineType1=" + lineType1 + ", lineType2=" + lineType2 + ", datumCategory1="
				+ datumCategory1 + ", datumCategory2=" + datumCategory2 + ", datumValue=" + datumValue + ", userName="
				+ userName + ", methodName1=" + methodName1 + ", methodName2=" + methodName2 + ", methodOwner="
				+ methodOwner + ", lineNumber1=" + lineNumber1 + ", lineNumber2=" + lineNumber2 + ", methodIdentifier="
				+ methodIdentifier + ", classMethodIdentifier=" + classMethodIdentifier + ", methodPackageName="
				+ methodPackageName + ", methodClassName=" + methodClassName + ", userIdentifier=" + userIdentifier
				+ ", textFileName=" + textFileName + ", methodName3=" + methodName3 + ", stepIdentifier="
				+ stepIdentifier + "]";
	}

}