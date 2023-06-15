package frl.model.loadUMLSequenceDiagram;

import java.util.Arrays;

public class MethodParameterData 
{
	private int projectIdentifier;
	
	private int parameterIdentifier; 
	private String parameterName;
	private int parameterDataTypeIdentifier;
	private String parameterDataTypeName;
	private String parameterPackageName;
	private String parameterClassName;
	private int parameterClassMethodIdentifier;
	private int parameterProgrammingLanguageIdentifier;
	private String parameterTextFileName;
	private String parameterReturnType;
	private int parameterClassInfoIdentifier;
	private int parameterEnumClassFlag;
	
	private int methodIdentifier;
	private String methodPackageName;
	private String methodClassName;
	private String methodShortName;
	private String methodFullName;
	private int methodClassMethodIdentifier;
	private String methodTextFileName;
	private boolean methodDbFlag;
	

	String[] attributeValues;
	
	// Constructor #1
	public MethodParameterData(int projectIdentifier, int parameterIdentifier, String parameterName,
			                   int parameterDataTypeId, String parameterDataTypeName, String parameterPackageName, 
			                   String parameterClassName, int parameterClassMethodIdentifier, 
			                   int parameterProgrammingLanguageIdentifier, String parameterTextFileName,
			                   String parameterReturnType, int parameterClassInfoIdentifier,
			                   int parameterEnumClassFlag,
			                   int methodIdentifier, String methodPackageName, String methodClassName, 
			                   String methodShortName, 
			                   int methodClassMethodIdentifier, 
			                   String methodTextFileName) 
	{
		this.projectIdentifier            = projectIdentifier;
		this.parameterIdentifier          = parameterIdentifier;
		this.parameterName                = parameterName;
		this.parameterDataTypeIdentifier  = parameterDataTypeId;
		this.parameterDataTypeName        = parameterDataTypeName;
		this.parameterPackageName         = parameterPackageName;
		this.parameterClassName           = parameterClassName;
		this.parameterClassMethodIdentifier = parameterClassMethodIdentifier;
		this.parameterProgrammingLanguageIdentifier = parameterProgrammingLanguageIdentifier;
		
		this.parameterTextFileName        = parameterTextFileName;
		this.parameterReturnType          = parameterReturnType;
		this.parameterClassInfoIdentifier = parameterClassInfoIdentifier;
		this.parameterEnumClassFlag       = parameterEnumClassFlag;
		
		this.methodIdentifier             = methodIdentifier;
		this.methodPackageName            = methodPackageName;
		this.methodClassName              = methodClassName;
		this.methodShortName              = methodShortName;

		this.methodClassMethodIdentifier  = methodClassMethodIdentifier;
		this.methodTextFileName           = methodTextFileName;

	}
	
	// Constructor #2
	public MethodParameterData(int projectIdentifier, int parameterIdentifier, String parameterName,
			                   int parameterDataTypeId, String parameterDataTypeName, String parameterPackageName, 
			                   String parameterClassName, int parameterClassMethodIdentifier, 
			                   int parameterProgrammingLanguageIdentifier,
			                   String parameterTextFileName, String parameterReturnType,
			                   int methodIdentifier, String methodPackageName, String methodClassName, 
			                   String methodShortName, 
			                   int methodClassMethodIdentifier, 
			                   String methodTextFileName,
			                   String [] attributeValues) 
	{
		this.projectIdentifier           = projectIdentifier;
		this.parameterIdentifier         = parameterIdentifier;
		this.parameterName               = parameterName;
		this.parameterDataTypeIdentifier = parameterDataTypeId;
		this.parameterDataTypeName       = parameterDataTypeName;
		this.parameterPackageName        = parameterPackageName;
		this.parameterClassName          = parameterClassName;
		this.parameterClassMethodIdentifier = parameterClassMethodIdentifier;
		this.parameterProgrammingLanguageIdentifier = parameterProgrammingLanguageIdentifier;
		this.parameterTextFileName       = parameterTextFileName;
		this.parameterReturnType         = parameterReturnType;
		
		this.methodIdentifier            = methodIdentifier;
		this.methodPackageName           = methodPackageName;
		this.methodClassName             = methodClassName;
		this.methodShortName             = methodShortName;

		this.methodClassMethodIdentifier = methodClassMethodIdentifier;
		this.methodTextFileName          = methodTextFileName;
		this.attributeValues             = attributeValues;
	}

	public int getProjectIdentifier() 
	{
		return projectIdentifier;
	}

	public void setProjectIdentifier(int projectIdentifier) 
	{
		this.projectIdentifier = projectIdentifier;
	}

	public int getParameterIdentifier() 
	{
		return parameterIdentifier;
	}

	public void setParameterIdentifier(int parameterIdentifier) 
	{
		this.parameterIdentifier = parameterIdentifier;
	}

	public String getParameterName() 
	{
		return parameterName;
	}

	public void setParameterName(String parameterName) 
	{
		this.parameterName = parameterName;
	}

	public int getParameterDataTypeIdentifier() 
	{
		return parameterDataTypeIdentifier;
	}

	public void setParameterDataTypeIdentifier(int parameterDataTypeIdentifier) 
	{
		this.parameterDataTypeIdentifier = parameterDataTypeIdentifier;
	}

	public String getParameterDataTypeName() 
	{
		return parameterDataTypeName;
	}

	public void setParameterDataTypeName(String parameterDataTypeName) 
	{
		this.parameterDataTypeName = parameterDataTypeName;
	}

	public String getParameterPackageName() 
	{
		return parameterPackageName;
	}

	public void setParameterPackageName(String parameterPackageName) 
	{
		this.parameterPackageName = parameterPackageName;
	}

	public String getParameterClassName() 
	{
		return parameterClassName;
	}

	public void setParameterClassName(String parameterClassName) 
	{
		this.parameterClassName = parameterClassName;
	}

	public int getParameterClassMethodIdentifier() 
	{
		return parameterClassMethodIdentifier;
	}

	public void setParameterClassMethodIdentifier(int parameterClassMethodIdentifier) 
	{
		this.parameterClassMethodIdentifier = parameterClassMethodIdentifier;
	}

	public int getParameterProgrammingLanguageIdentifier() 
	{
		return parameterProgrammingLanguageIdentifier;
	}

	public void setParameterProgrammingLanguageIdentifier(int parameterProgrammingLanguageIdentifier) 
	{
		this.parameterProgrammingLanguageIdentifier = parameterProgrammingLanguageIdentifier;
	}

	public String getParameterTextFileName() 
	{
		return parameterTextFileName;
	}

	public void setParameterTextFileName(String parameterTextFileName) 
	{
		this.parameterTextFileName = parameterTextFileName;
	}

	public String getParameterReturnType() 
	{
		return parameterReturnType;
	}

	public void setParameterReturnType(String parameterReturnType) 
	{
		this.parameterReturnType = parameterReturnType;
	}

	public int getParameterClassInfoIdentifier() 
	{
		return parameterClassInfoIdentifier;
	}

	public void setParameterClassInfoIdentifier(int parameterClassInfoIdentifier) 
	{
		this.parameterClassInfoIdentifier = parameterClassInfoIdentifier;
	}

	public int getParameterEnumClassFlag() 
	{
		return parameterEnumClassFlag;
	}

	public void setParameterEnumClassFlag(int parameterEnumClassFlag) 
	{
		this.parameterEnumClassFlag = parameterEnumClassFlag;
	}

	public int getMethodIdentifier() 
	{
		return methodIdentifier;
	}

	public void setMethodIdentifier(int methodIdentifier) 
	{
		this.methodIdentifier = methodIdentifier;
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

	public String getMethodShortName() 
	{
		return methodShortName;
	}

	public void setMethodShortName(String methodShortName) 
	{
		this.methodShortName = methodShortName;
	}

	public String getMethodFullName() 
	{
		return methodFullName;
	}

	public void setMethodFullName(String methodFullName) 
	{
		this.methodFullName = methodFullName;
	}

	public int getMethodClassMethodIdentifier() 
	{
		return methodClassMethodIdentifier;
	}

	public void setMethodClassMethodIdentifier(int methodClassMethodIdentifier) 
	{
		this.methodClassMethodIdentifier = methodClassMethodIdentifier;
	}

	public String getMethodTextFileName() 
	{
		return methodTextFileName;
	}

	public void setMethodTextFileName(String methodTextFileName) 
	{
		this.methodTextFileName = methodTextFileName;
	}

	public boolean isMethodDbFlag() 
	{
		return methodDbFlag;
	}

	public void setMethodDbFlag(boolean methodDbFlag) 
	{
		this.methodDbFlag = methodDbFlag;
	}

	public String[] getAttributeValues() 
	{
		return attributeValues;
	}

	public void setAttributeValues(String[] attributeValues) 
	{
		this.attributeValues = attributeValues;
	}

	@Override
	public String toString() 
	{
		return "MethodParameterData [projectIdentifier=" + projectIdentifier + ", parameterIdentifier="
				+ parameterIdentifier + ", parameterName=" + parameterName + ", parameterDataTypeIdentifier="
				+ parameterDataTypeIdentifier + ", parameterDataTypeName=" + parameterDataTypeName
				+ ", parameterPackageName=" + parameterPackageName + ", parameterClassName=" + parameterClassName
				+ ", parameterClassMethodIdentifier=" + parameterClassMethodIdentifier
				+ ", parameterProgrammingLanguageIdentifier=" + parameterProgrammingLanguageIdentifier
				+ ", parameterTextFileName=" + parameterTextFileName + ", parameterReturnType=" + parameterReturnType
				+ ", parameterClassInfoIdentifier=" + parameterClassInfoIdentifier + ", parameterEnumClassFlag="
				+ parameterEnumClassFlag + ", methodIdentifier=" + methodIdentifier + ", methodPackageName="
				+ methodPackageName + ", methodClassName=" + methodClassName + ", methodShortName=" + methodShortName
				+ ", methodFullName=" + methodFullName + ", methodClassMethodIdentifier=" + methodClassMethodIdentifier
				+ ", methodTextFileName=" + methodTextFileName + ", methodDbFlag=" + methodDbFlag + ", attributeValues="
				+ Arrays.toString(attributeValues) + "]";
	} 
    
}
