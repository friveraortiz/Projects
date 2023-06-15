package frl.model.loadUMLSequenceDiagram;


public class UMLSequenceDiagramFinal
{
    private int projectId;
    private int diagramLineNumber;
    private int userIdentifier;
    private int methodIdentifier;
    private int methodStepIdentifier;
    private int methodLineNumber;
    private String lineText;
    private LineType1 lineType1;
    private String lineType2;
    private DatumCategory1 datumCategory1;
    private String datumCategory2;
    private String datumValue;
    private int classMethodIdentifier;
    private AnnotationType annotationType;
    private String annotationDetails;
    private String annotationElement1;
    private String annotationOperator;
    private String annotationElement2;

    // Constructor #1
	public UMLSequenceDiagramFinal(int projectId, int diagramLineNumber, int userIdentifier, 
			                       int methodIdentifier, int methodStepIdentifier, int methodLineNumber, 
			                       String lineText, LineType1 lineType1, String lineType2, 
			                       DatumCategory1 datumCategory1, String datumCategory2, String datumValue, 
			                       int classMethodIdentifier)
	{
		this.projectId             = projectId;
		this.diagramLineNumber     = diagramLineNumber;
		this.userIdentifier        = userIdentifier;
		this.methodIdentifier      = methodIdentifier;
		this.methodStepIdentifier  = methodStepIdentifier;
		this.methodLineNumber      = methodLineNumber;
		this.lineText              = lineText;
		this.lineType1      	   = lineType1;
		this.lineType2             = lineType2;
		this.datumCategory1        = datumCategory1;
		this.datumCategory2        = datumCategory2;
		this.datumValue            = datumValue;
		this.classMethodIdentifier = classMethodIdentifier;
	}
	
    // Constructor #2
	public UMLSequenceDiagramFinal(int projectId, int userIdentifier, int methodIdentifier, 
			                       int methodLineNumber, String lineText, LineType1 lineType1, 
			                       String lineType2, DatumCategory1 datumCategory1,
                                   String datumCategory2, String datumValue)
	{
		this.projectId      = projectId;
		this.userIdentifier = userIdentifier;
		this.methodIdentifier = methodIdentifier;
		this.methodLineNumber  = methodLineNumber;
		this.lineText       = lineText;
		this.lineType1      = lineType1;
		this.lineType2      = lineType2;
		this.datumCategory1 = datumCategory1;
		this.datumCategory2 = datumCategory2;
		this.datumValue     = datumValue;
	}
	
	// Constructor #3
	public UMLSequenceDiagramFinal(int projectId, int userIdentifier, 
			                       int diagramLineNumber, String lineText)
	{
		this.projectId         = projectId;
		this.userIdentifier    = userIdentifier;
		this.diagramLineNumber = diagramLineNumber;
		this.lineText          = lineText;
	}	
	
	// Constructor #4
	public UMLSequenceDiagramFinal(int projectId, int userIdentifier, int methodIdentifier,
			                       int stepIdentifier, int diagramLineNumber, String lineText)
	{
		this.projectId            = projectId;
		this.userIdentifier       = userIdentifier;
		this.methodIdentifier     = methodIdentifier;
		this.methodStepIdentifier = stepIdentifier;
		this.diagramLineNumber    = diagramLineNumber;
		this.lineText             = lineText;
	}	
	
	// Constructor #5
	public UMLSequenceDiagramFinal(int projectId, int userIdentifier, int methodIdentifier,
			                       int stepIdentifier)
	{
		this.projectId            = projectId;
		this.userIdentifier       = userIdentifier;
		this.methodIdentifier     = methodIdentifier;
		this.methodStepIdentifier = stepIdentifier;
	}	
	
    // Constructor #6
	public UMLSequenceDiagramFinal(int projectId, int diagramLineNumber, int userIdentifier, 
			                       int methodIdentifier, int methodStepIdentifier,
			                       int methodLineNumber, String lineText, LineType1 lineType1, 
			                       String lineType2, DatumCategory1 datumCategory1,
                                   String datumCategory2, String datumValue)
	{
		this.projectId             = projectId;
		this.diagramLineNumber     = diagramLineNumber;
		this.userIdentifier        = userIdentifier;
		this.methodIdentifier      = methodIdentifier;
		this.methodStepIdentifier  = methodStepIdentifier;
		this.methodLineNumber      = methodLineNumber;
		this.lineText              = lineText;
		this.lineType1      	   = lineType1;
		this.lineType2             = lineType2;
		this.datumCategory1        = datumCategory1;
		this.datumCategory2        = datumCategory2;
		this.datumValue            = datumValue;
	}
	
    // Constructor #7
	public UMLSequenceDiagramFinal(int projectId, int userIdentifier, 
			                       int methodIdentifier, int methodStepIdentifier,
                                   AnnotationType annotationType)
	{
		this.projectId             = projectId;
		this.userIdentifier        = userIdentifier;
		this.methodIdentifier      = methodIdentifier;
		this.methodStepIdentifier  = methodStepIdentifier;
		this.annotationType        = annotationType;
	}	
	
    // Constructor #8
	public UMLSequenceDiagramFinal(int projectId, int diagramLineNumber, int userIdentifier, 
			                       int methodIdentifier, int methodStepIdentifier, int methodLineNumber, 
			                       String lineText, LineType1 lineType1, String lineType2, 
			                       DatumCategory1 datumCategory1, String datumCategory2, String datumValue, 
			                       int classMethodIdentifier, AnnotationType annotationType, String annotationDetails, 
			                       String annotationElement1, String annotationOperator, String annotationElement2)
	{
		this.projectId             = projectId;
		this.diagramLineNumber     = diagramLineNumber;
		this.userIdentifier        = userIdentifier;
		this.methodIdentifier      = methodIdentifier;
		this.methodStepIdentifier  = methodStepIdentifier;
		this.methodLineNumber      = methodLineNumber;
		this.lineText              = lineText;
		this.lineType1      	   = lineType1;
		this.lineType2             = lineType2;
		this.datumCategory1        = datumCategory1;
		this.datumCategory2        = datumCategory2;
		this.datumValue            = datumValue;
		this.classMethodIdentifier = classMethodIdentifier;
		this.annotationType        = annotationType;
		this.annotationDetails     = annotationDetails;
		this.annotationElement1    = annotationElement1;
		this.annotationOperator    = annotationOperator;
		this.annotationElement2    = annotationElement2;
	}	
	
	// Constructor #9
	public UMLSequenceDiagramFinal(int projectId, AnnotationType annotationType, int methodIdentifier, 
			                       String lineType2, String datumCategory2, String datumValue, 
			                       String annotationDetails, String annotationElement1, String annotationOperator, 
			                       String annotationElement2)
	{
	   this.projectId          = projectId;
       this.annotationType     = annotationType;
       this.methodIdentifier   = methodIdentifier;
       this.lineType2          = lineType2;
       this.datumCategory2     = datumCategory2;
       this.datumValue         = datumValue; 
       this.annotationDetails  = annotationDetails;
       this.annotationElement1 = annotationElement1;
       this.annotationOperator = annotationOperator;
       this.annotationElement2 = annotationElement2;
	}
	
	public int getProjectId() 
	{
		return projectId;
	}

	public void setProjectId(int projectId) 
	{
		this.projectId = projectId;
	}

	public int getUserIdentifier() 
	{
		return userIdentifier;
	}

	public void setUserIdentifier(int userIdentifier) 
	{
		this.userIdentifier = userIdentifier;
	}

	public int getMethodIdentifier() 
	{
		return methodIdentifier;
	}

	public void setMethodIdentifier(int methodIdentifier) 
	{
		this.methodIdentifier = methodIdentifier;
	}
	
	public int getMethodStepIdentifier() 
	{
		return methodStepIdentifier;
	}

	public void setMethodStepIdentifier(int methodStepIdentifier) 
	{
		this.methodStepIdentifier = methodStepIdentifier;
	}

	public int getDiagramLineNumber() 
	{
		return diagramLineNumber;
	}

	public void setDiagramLineNumber(int diagramLineNumber) 
	{
		this.diagramLineNumber = diagramLineNumber;
	}

	public int getMethodLineNumber() 
	{
		return methodLineNumber;
	}

	public void setMethodLineNumber(int methodLineNumber) 
	{
		this.methodLineNumber = methodLineNumber;
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

	public int getClassMethodIdentifier() 
	{
		return classMethodIdentifier;
	}

	public void setClassMethodIdentifier(int classMethodIdentifier) 
	{
		this.classMethodIdentifier = classMethodIdentifier;
	}

	public AnnotationType getAnnotationType() 
	{
       return annotationType;
	}

	public void setAnnotationType(AnnotationType annotationType) 
	{
	   this.annotationType = annotationType;
	}
	
	public String getAnnotationDetails() 
	{
		return annotationDetails;
	}

	public void setAnnotationDetails(String annotationDetails) 
	{
		this.annotationDetails = annotationDetails;
	}

	public String getAnnotationElement1() 
	{
		return annotationElement1;
	}

	public void setAnnotationElement1(String annotationElement1) 
	{
		this.annotationElement1 = annotationElement1;
	}

	public String getAnnotationOperator() 
	{
		return annotationOperator;
	}

	public void setAnnotationOperator(String annotationOperator) 
	{
		this.annotationOperator = annotationOperator;
	}

	public String getAnnotationElement2() 
	{
		return annotationElement2;
	}

	public void setAnnotationElement2(String annotationElement2) 
	{
		this.annotationElement2 = annotationElement2;
	}

	@Override
	public String toString() 
	{
		return "UMLSequenceDiagramFinal [projectId=" + projectId + ", diagramLineNumber=" + diagramLineNumber
				+ ", userIdentifier=" + userIdentifier + ", methodIdentifier=" + methodIdentifier
				+ ", methodStepIdentifier=" + methodStepIdentifier + ", methodLineNumber=" + methodLineNumber
				+ ", lineText=" + lineText + ", lineType1=" + lineType1 + ", lineType2=" + lineType2
				+ ", datumCategory1=" + datumCategory1 + ", datumCategory2=" + datumCategory2 + ", datumValue="
				+ datumValue + ", classMethodIdentifier=" + classMethodIdentifier + ", annotationType=" + annotationType
				+ ", annotationDetails=" + annotationDetails + ", annotationElement1=" + annotationElement1
				+ ", annotationOperator=" + annotationOperator + ", annotationElement2=" + annotationElement2 + "]";
	}

}