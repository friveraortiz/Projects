package frl.gui.generateLoggingInstructions;
import java.util.EventObject;


//Package #6
//Class #2
public class AOPFinalFileFormEvent extends EventObject 
{
   private static final long serialVersionUID = 1L;
   private int projectId;
   private String featuresConfigFile;
   private String databaseConfigFile;
   private String projectName;
   private String jarFileName;
   private String projectInputDir;
   private String projectOutputDir;

   private String dbms;
   private String inputMethod;
   private String startProjectMethod;
   private String endProjectMethod;
   private String connectProjectMethod;
   private String operatingSystem;
   
   private String newLine1;
   private String newLine2;
   private String objectOrientedDelimiter1;
   private String startMethod;
   private String endMethod;
   private String startParameters;
   private String endParameters;
   private String singleLineComment;
   private String endStatementDelimiter;
   private String startClassList;
   private String endClassList;
   private String bluePrintObject1;
   private String bluePrintObject2;
   private String initializeObjectName;
   private String initializeObjectMethod;
   private String subProgramObject;
   private String methodNameException;
   private String printMessage;
   private String startLineNum;
   private String startArray;
   private String endArray;
   
   private String umlSeqDiagramImage;
   private String umlSeqDiagramText;
   
   private int userId;
   private String userName;
   
   private int methodId;
   private String methodName;
   
   private int programmingLanguageId;
   private String programmingLanguageName;
   
   private int parameterId;
   private int parameterDataTypeId;
   private String parameterFullName;
   
   private int attributeId;
   private String attributeName;
   
   private String logDirectory;
   private String aOPDirectory;
   
   private String annotationFile;
   private String annotationFilePrefix;
   private String findWhiteSpaces;
   private String validSpecialCharacter;
   private String valueDelimiter;
   
   private String dataTypeFullClassName;
   private String dataTypeShortClassName;
   private String dataTypeStringList;
   private String dataTypeNumberList;
   
   private String confPropertyName1;
   private String startCallMethod;
   private String endCallMethod1;
   private String callMethodDelimiter;
   private String assignValueDelimiter;
   private String guiLibDelimiter;
   private String findString;
   private String textFileNameExt3;
   
   // Constructor # 1
   /*
   public AOPFinalFileFormEvent(Object source, int projectId2, int methodId, int parameterId, int parameterDataTypeId, int attributeId, String attributeName) 
   {
		super(source);
   }
	*/
   // Constructor # 1
   public AOPFinalFileFormEvent(Object source, int projectId, String featuresConfigFile, String databaseConfigFile,
		                              String projectName, String jarFileName, String projectInputDir, 
		                              String projectOutputDir, String programmingLanguageName, String dbms, 
		                              String inputMethod, String startProjectMethod, String endProjectMethod, 
		                              String connectProjectMethod, String operatingSystem, String newLine1,
                                      String newLine2, String objectOrientedDelimiter1, String startMethod,
                                      String endMethod, String startParameters, String endParameters,
                                      String singleLineComment, String endStatementDelimiter, 
                                      String startClassList, String endClassList, String bluePrintObject1,
                                      String bluePrintObject2, String initializeObjectName, 
                                      String initializeObjectMethod, String subProgramObject,
                                      String methodNameException, String printMessage, String startLineNum, 
                                      String startArray, String endArray, 
                                      String umlSeqDiagramImage, String umlSeqDiagramText) 
   {
		super(source);
		this.projectId = projectId;
        this.featuresConfigFile   = featuresConfigFile;
        this.databaseConfigFile   = databaseConfigFile;
        this.projectName          = projectName;
        this.jarFileName          = jarFileName;
        this.projectInputDir      = projectInputDir; 
        this.projectOutputDir     = projectOutputDir;
        this.programmingLanguageName  = programmingLanguageName; 
        this.dbms                 = dbms; 
        this.inputMethod          = inputMethod; 
        this.startProjectMethod   = startProjectMethod; 
        this.endProjectMethod     = endProjectMethod; 
        this.connectProjectMethod = connectProjectMethod;
        this.operatingSystem      = operatingSystem;
        
        this.newLine1                 =  newLine1;
        this.newLine2                 = newLine2;
        this.objectOrientedDelimiter1 = objectOrientedDelimiter1;
        this.startMethod              = startMethod;
        this.endMethod                = endMethod;
        this.startParameters          = startParameters;
        this.endParameters            = endParameters;
        this.singleLineComment        = singleLineComment;
        this.endStatementDelimiter    = endStatementDelimiter;
        this.startClassList           = endStatementDelimiter;
        this.endClassList             = endClassList;
        this.bluePrintObject1         = bluePrintObject1;
        this.bluePrintObject2         = bluePrintObject2;
        this.initializeObjectName     = initializeObjectName;
        this.initializeObjectMethod   = initializeObjectMethod;
        this.subProgramObject         = subProgramObject;
        this.methodNameException      = methodNameException;
        this.printMessage             = printMessage;
        this.startLineNum             = startLineNum;
        this.startArray               = startArray;
        this.endArray                 = endArray;
        
        this.umlSeqDiagramImage       = umlSeqDiagramImage;
        this.umlSeqDiagramText        = umlSeqDiagramText;
   }
	
   // Constructor # 2
   public AOPFinalFileFormEvent(Object source, String featuresConfigFile, String databaseConfigFile, String projectName,
                              String jarFileName, String projectInputDir, String projectOutputDir,
                              String programmingLanguageName, String dbms, String inputMethod, 
                              String startProjectMethod, String endProjectMethod, String connectProjectMethod,
                              String operatingSystem, String newLine1,
                              String newLine2, String objectOrientedDelimiter1, String startMethod,
                              String endMethod, String startParameters, String endParameters,
                              String singleLineComment, String endStatementDelimiter, 
                              String startClassList, String endClassList, String bluePrintObject1,
                              String bluePrintObject2, String initializeObjectName, 
                              String initializeObjectMethod, String subProgramObject,
                              String methodNameException, String printMessage, String startLineNum, 
                              String startArray, String endArray, 
                              String umlSeqDiagramImage, String umlSeqDiagramText) 
   {
		super(source);
        this.featuresConfigFile   = featuresConfigFile;
        this.databaseConfigFile   = databaseConfigFile;
        this.projectName          = projectName;
        this.jarFileName          = jarFileName;
        this.projectInputDir      = projectInputDir; 
        this.projectOutputDir     = projectOutputDir;
        this.programmingLanguageName  = programmingLanguageName; 
        this.dbms                 = dbms; 
        this.inputMethod          = inputMethod; 
        this.startProjectMethod   = startProjectMethod; 
        this.endProjectMethod     = endProjectMethod; 
        this.connectProjectMethod = connectProjectMethod;
        this.operatingSystem      = operatingSystem;
        
        this.newLine1                 =  newLine1;
        this.newLine2                 = newLine2;
        this.objectOrientedDelimiter1 = objectOrientedDelimiter1;
        this.startMethod              = startMethod;
        this.endMethod                = endMethod;
        this.startParameters          = startParameters;
        this.endParameters            = endParameters;
        this.singleLineComment        = singleLineComment;
        this.endStatementDelimiter    = endStatementDelimiter;
        this.startClassList           = endStatementDelimiter;
        this.endClassList             = endClassList;
        this.bluePrintObject1         = bluePrintObject1;
        this.bluePrintObject2         = bluePrintObject2;
        this.initializeObjectName     = initializeObjectName;
        this.initializeObjectMethod   = initializeObjectMethod;
        this.subProgramObject         = subProgramObject;
        this.methodNameException      = methodNameException;
        this.printMessage             = printMessage;
        this.startLineNum             = startLineNum;
        this.startArray               = startArray;
        this.endArray                 = endArray;
        
        this.umlSeqDiagramImage       = umlSeqDiagramImage;
        this.umlSeqDiagramText        = umlSeqDiagramText;
   }
   
   // Constructor # 3
   // Method #3
   public AOPFinalFileFormEvent(Object source, int projectId, String featuresConfigFile, String databaseConfigFile,
                              String projectName, String jarFileName, String projectInputDir, 
                              String projectOutputDir, String programmingLanguageName, String dbms, 
                              String inputMethod, String startProjectMethod, String endProjectMethod, 
                              String connectProjectMethod, String operatingSystem, String methodsFiles, String aspectOrientedFile1,
                              String aspectOrientedFile2, String umlSeqDiagramImage, String umlSeqDiagramText) 
   {
      super(source);
      this.projectId = projectId;
      this.featuresConfigFile   = featuresConfigFile;
      this.databaseConfigFile   = databaseConfigFile;
      this.projectName          = projectName;
      this.jarFileName          = jarFileName;
      this.projectInputDir      = projectInputDir; 
      this.projectOutputDir     = projectOutputDir;
      this.programmingLanguageName  = programmingLanguageName; 
      this.dbms                 = dbms; 
      this.inputMethod          = inputMethod; 
      this.startProjectMethod   = startProjectMethod; 
      this.endProjectMethod     = endProjectMethod; 
      this.connectProjectMethod = connectProjectMethod;
      this.operatingSystem      = operatingSystem;
      
      this.umlSeqDiagramImage       = umlSeqDiagramImage;
      this.umlSeqDiagramText        = umlSeqDiagramText;
   }

   // Constructor # 4
   public AOPFinalFileFormEvent(Object source, String featuresConfigFile, String databaseConfigFile, String projectName,
            				  String jarFileName, String projectInputDir, String projectOutputDir,
           				      String programmingLanguageName, String dbms, String inputMethod, 
           				      String startProjectMethod, String endProjectMethod, String connectProjectMethod,
           				      String operatingSystem, String methodsFiles, String aspectOrientedFile1,
                              String aspectOrientedFile2, String umlSeqDiagramImage, String umlSeqDiagramText) 
   {
      super(source);
      this.featuresConfigFile  = featuresConfigFile;
      this.databaseConfigFile  = databaseConfigFile;
      this.projectName         = projectName;
      this.jarFileName         = jarFileName;
      this.projectInputDir     = projectInputDir; 
      this.projectOutputDir    = projectOutputDir;
      this.programmingLanguageName = programmingLanguageName; 
      this.dbms                 = dbms; 
      this.inputMethod          = inputMethod; 
      this.startProjectMethod   = startProjectMethod; 
      this.endProjectMethod     = endProjectMethod; 
      this.connectProjectMethod = connectProjectMethod;
      this.operatingSystem      = operatingSystem;
      
      this.umlSeqDiagramImage       = umlSeqDiagramImage;
      this.umlSeqDiagramText        = umlSeqDiagramText;
   }

   // Constructor # 5
   public AOPFinalFileFormEvent(Object source, int projectId2, int methodId, 
		                        int projectId, int parameterDataTypeId, String projectOutputDir) 
   {
      super(source);
      //this.featuresConfigFile  = projectId2;
      //this.databaseConfigFile  = methodId;
      this.projectId           = projectId;
      //this.projectName         = parameterDataTypeId;
      this.projectOutputDir    = projectOutputDir;
   }
   
   // Constructor # 6
   /*
   public AOPFinalFileFormEvent(Object source, int projectId, String field, String value) 
   {
		super(source);
		this.projectId = projectId;
		
		switch(field)
		{
		   case "featuresConfigFile": 
			   this.featuresConfigFile = value;
		   break;
		   
		   case "databaseConfigFile": 
			   this.databaseConfigFile = value;
		   break;
		   
		   case "projectName": 
			   this.projectName = value;
		   break;
		   
		   case "jarFileName": 
			   this.jarFileName = value;
		   break;
		   
		   case "projectInputDir": 
			   this.projectInputDir = value;
		   break;
		   
		   case "projectOutputDir": 
			   this.projectOutputDir = value;
		   break;
		   
		   case "programmingLanguage": 
			   this.programmingLanguage = value;
		   break;
		   
		   case "UMLSeqDiagramImage": 
			   this.umlSeqDiagramImage = value;
		   break;
		   
		   case "UMLSeqDiagramFile": 
			   this.umlSeqDiagramText = value;
		   break;
		   
		   case "methodName": 
			   this.methodName = value;
		   break;
		   
		}
		
   }
   */
   
   // Constructor # 7
   /*
   public AOPFinalFileFormEvent(Object source, String field, String value) 
   {
		super(source);
		
		switch(field)
		{
		   case "featuresConfigFile": this.featuresConfigFile = value;
		   break;
		   
		   case "databaseConfigFile": this.databaseConfigFile = value;
		   break;
		   
		   case "projectName": this.projectName = value;
		   break;
		   
		   case "jarFileName": this.jarFileName = value;
		   break;
		   
		   case "projectInputDir": this.projectInputDir = value;
		   break;
		   
		   case "projectOutputDir": this.projectOutputDir = value;
		   break;
		   
		   case "programmingLanguage": this.programmingLanguage = value;
		   break;
		   
		   case "UMLSeqDiagramImage": 
			   this.umlSeqDiagramImage = value;
		   break;
		   
		   case "UMLSeqDiagramFile": 
			   this.umlSeqDiagramText = value;
		   break;
		}
		
   }*/
  
   
   // Constructor # 6
   public AOPFinalFileFormEvent(Object source, int projectId, int userId, String userName, int programmingLanguageId) 
   {
      super(source); 
	  this.projectId             = projectId;
	  this.userId                = userId;
	  this.userName              = userName;
	  this.programmingLanguageId = programmingLanguageId;
   }
   
   // Constructor # 7
   public AOPFinalFileFormEvent(Object source, int projectId, int userId, int methodId, String methodName, int programmingLanguageId) 
   {
      super(source); 
	  this.projectId  = projectId;
	  this.userId     = userId;
      this.methodId   = methodId;
      this.methodName = methodName;
      this.programmingLanguageId = programmingLanguageId;
   }  
   
   // Constructor # 8
   public AOPFinalFileFormEvent(Object source, int projectId, int methodId, 
		                        int parameterId, int parameterDataTypeId, String parameterFullName, int programmingLanguageId) 
   {
      super(source); 
	  this.projectId             = projectId;
      this.methodId              = methodId;
      this.parameterId           = parameterId;
      this.parameterDataTypeId   = parameterDataTypeId;
      this.parameterFullName     = parameterFullName;
      this.programmingLanguageId = programmingLanguageId;
   } 
   
   // Constructor # 9
   public AOPFinalFileFormEvent(Object source, int projectId, int methodId, 
		                        int parameterId, int parameterDataTypeId, String parameterFullName, 
		                        int attributeId, String attributeName, int programmingLanguageId) 
   {
      super(source); 
	  this.projectId             = projectId;
      this.methodId              = methodId;
      this.parameterId           = parameterId;
      this.parameterDataTypeId   = parameterDataTypeId;
      this.parameterFullName     = parameterFullName;
      this.attributeId           = attributeId;
      this.attributeName         = attributeName;
      this.programmingLanguageId = programmingLanguageId;
   } 
   
   // Constructor #10
   public AOPFinalFileFormEvent(Object source, int projectId, String logDirectory)
   {
      super(source); 
	  this.projectId    = projectId;
	  this.logDirectory = logDirectory;
   }
   
   // Constructor #11
   public AOPFinalFileFormEvent(Object source, int projectId, String projectName, String projectInputDir, String projectOutputDir, int programmingLanguageId,
                                String programmingLanguageName, String objectOrientedDelimiter1, String dataTypeFullClassName, String dataTypeShortClassName, 
                                String dataTypeStringList, String dataTypeNumberList, String logDirectory, String aOPDirectory, String annotationFile, String annotationFilePrefix,
                                String findWhiteSpaces, String validSpecialCharacter, String valueDelimiter, String endStatementDelimiter, String confPropertyName1,
                                String startCallMethod, String endCallMethod1, String callMethodDelimiter, String assignValueDelimiter, String guiLibDelimiter,
                                String findString, String textFileNameExt3)
   {
      super(source);
      this.projectId                = projectId;
      this.projectName              = projectName;
      this.projectInputDir          = projectInputDir;
      this.projectOutputDir         = projectOutputDir;
      this.programmingLanguageId    = programmingLanguageId;
      this.programmingLanguageName  = programmingLanguageName;
      this.objectOrientedDelimiter1 = objectOrientedDelimiter1;
      this.dataTypeFullClassName    = dataTypeFullClassName;
      this.dataTypeShortClassName   = dataTypeShortClassName;
      this.dataTypeStringList       = dataTypeStringList;
      this.dataTypeNumberList       = dataTypeNumberList;
      this.logDirectory             = logDirectory;
      this.aOPDirectory             = aOPDirectory;
      this.annotationFile           = annotationFile;
      this.annotationFilePrefix     = annotationFilePrefix;
      this.findWhiteSpaces          = findWhiteSpaces;
      this.validSpecialCharacter    = validSpecialCharacter;
      this.valueDelimiter           = valueDelimiter;
      this.endStatementDelimiter    = endStatementDelimiter;
      this.confPropertyName1        = confPropertyName1;
      this.startCallMethod          = startCallMethod;
      this.endCallMethod1           = endCallMethod1;
      this.callMethodDelimiter      = callMethodDelimiter;
      this.assignValueDelimiter     = assignValueDelimiter;
      this.guiLibDelimiter          = guiLibDelimiter;
      this.findString               = findString;
      this.textFileNameExt3         = textFileNameExt3;
   }

   public int getProjectId() 
   {
      return projectId;
   }

   public void setProjectId(int projectId) 
   {
	  this.projectId = projectId;
   }

   public String getfeaturesConfigFile() 
   {
      return featuresConfigFile;
   }

   public void setfeaturesConfigFile(String featuresConfigFile) 
   {
      this.featuresConfigFile = featuresConfigFile;
   }
   
   public String getDatabaseConfigFile() 
   {
      return databaseConfigFile;
   }

   public void setDatabaseConfigFile(String databaseConfigFile) 
   {
      this.databaseConfigFile = databaseConfigFile;
   }

   public String getProjectName() 
   {
      return projectName;
   }

   public void setProjectName(String projectName) 
   {
      this.projectName = projectName;
   }

   public String getJarFileName() 
   {
      return jarFileName;
   }

   public void setJarFileName(String jarFileName) 
   {
      this.jarFileName = jarFileName;
   }

   public String getProjInputDir() 
   {
      return projectInputDir;
   }

   public void setProjInputDir(String projectInputDir) 
   {
      this.projectInputDir = projectInputDir;
   }

   public String getProjOutputDir() 
   {
      return projectOutputDir;
   }

   public void setProjOutputDir(String projectOutputDir) 
   {
      this.projectOutputDir = projectOutputDir;
   }

   public String getDbms() 
   {
      return dbms;
   }

   public void setDbms(String dbms) 
   {
      this.dbms = dbms;
   }

   public String getInputMethod() 
   {
      return inputMethod;
   }

   public void setInputMethod(String inputMethod) 
   {
      this.inputMethod = inputMethod;
   }

   public String getStartProjMethod() 
   {
      return startProjectMethod;
   }

   public void setStartProjMethod(String startProjectMethod) 
   {
      this.startProjectMethod = startProjectMethod;
   }

   public String getEndProjMethod() 
   {
      return endProjectMethod;
   }

   public void setEndProjMethod(String endProjectMethod) 
   {
      this.endProjectMethod = endProjectMethod;
   }

   public String getConnectProjMethod() 
   {
      return connectProjectMethod;
   }

   public void setConnectProjMethod(String connectProjectMethod) 
   {
      this.connectProjectMethod = connectProjectMethod;
   }

   public String getOperatingSystem() 
   {
      return operatingSystem;
   }

   public void setOperatingSystem(String operatingSystem) 
   {
      this.operatingSystem = operatingSystem;
   }

   public String getNewLine1() 
   {
	return newLine1;
   }

   public void setNewLine1(String newLine1) 
   {
      this.newLine1 = newLine1;
   }

   public String getNewLine2() 
   {
      return newLine2;
   }

   public void setNewLine2(String newLine2) 
   {
      this.newLine2 = newLine2;
   }

   public String getObjectOrientedDelimiter1() 
   {
      return objectOrientedDelimiter1;
   }

   public void setObjectOrientedDelimiter1(String objectOrientedDelimiter1) 
   {
      this.objectOrientedDelimiter1 = objectOrientedDelimiter1;
   }

   public String getStartMethod() 
   {
      return startMethod;
   }

   public void setStartMethod(String startMethod) 
   {
      this.startMethod = startMethod;
   }

   public String getEndMethod() 
   {
      return endMethod;
   }

   public void setEndMethod(String endMethod) 
   {
      this.endMethod = endMethod;
   }

   public String getStartParameters() 
   {
      return startParameters;
   }

   public void setStartParameters(String startParameters) 
   {
      this.startParameters = startParameters;
   }

   public String getEndParameters() 
   {
      return endParameters;
   }

   public void setEndParameters(String endParameters) 
   {
      this.endParameters = endParameters;
   }

   public String getSingleLineComment() 
   {
      return singleLineComment;
   }

   public void setSingleLineComment(String singleLineComment) 
   {
      this.singleLineComment = singleLineComment;
   }

   public String getEndStatementDelimiter() 
   {
      return endStatementDelimiter;
   }

   public void setEndStatementDelimiter(String endStatementDelimiter) 
   {
      this.endStatementDelimiter = endStatementDelimiter;
   }

   public String getStartClassList() 
   {
      return startClassList;
   }

   public void setStartClassList(String startClassList) 
   {
      this.startClassList = startClassList;
   }

   public String getEndClassList() 
   {
      return endClassList;
   }

   public void setEndClassList(String endClassList) 
   {
      this.endClassList = endClassList;
   }

   public String getBluePrintObject1() 
   {
      return bluePrintObject1;
   }

   public void setBluePrintObject1(String bluePrintObject1) 
   {
      this.bluePrintObject1 = bluePrintObject1;
   }

   public String getBluePrintObject2() 
   {
      return bluePrintObject2;
   }

   public void setBluePrintObject2(String bluePrintObject2) 
   {
      this.bluePrintObject2 = bluePrintObject2;
   }

   public String getInitializeObjectName() 
   {
      return initializeObjectName;
   }

   public void setInitializeObjectName(String initializeObjectName) 
   {
      this.initializeObjectName = initializeObjectName;
   }

   public String getInitializeObjectMethod() 
   {
      return initializeObjectMethod;
   }

   public void setInitializeObjectMethod(String initializeObjectMethod) 
   {
      this.initializeObjectMethod = initializeObjectMethod;
   }

   public String getSubProgramObject() 
   {
      return subProgramObject;
   }

   public void setSubProgramObject(String subProgramObject) 
   {
      this.subProgramObject = subProgramObject;
   }

   public String getMethodNameException() 
   {
      return methodNameException;
   }

   public void setMethodNameException(String methodNameException) 
   {
      this.methodNameException = methodNameException;
   }

   public String getPrintMessage() 
   {
      return printMessage;
   }

   public void setPrintMessage(String printMessage) 
   {
      this.printMessage = printMessage;
   }

   public String getStartLineNum() 
   {
      return startLineNum;
   }

   public void setStartLineNum(String startLineNum) 
   {
      this.startLineNum = startLineNum;
   }

   public String getStartArray() 
   {
      return startArray;
   }

   public void setStartArray(String startArray) 
   {
      this.startArray = startArray;
   }

   public String getEndArray() 
   {
      return endArray;
   }

   public void setEndArray(String endArray) 
   {
      this.endArray = endArray;
   }

   public String getUmlSeqDiagramImage() 
   {
	   return umlSeqDiagramImage;
   }

   public String getUmlSeqDiagramText() 
   {
	   return umlSeqDiagramText;
   }

   public void setUmlSeqDiagramImage(String umlSeqDiagramImage) 
   {
      this.umlSeqDiagramImage = umlSeqDiagramImage;
   }

   public void setUmlSeqDiagramText(String umlSeqDiagramText) 
   {
      this.umlSeqDiagramText = umlSeqDiagramText;
   }

   public String getFeaturesConfigFile() 
   {
	   return featuresConfigFile;
   }

   public void setFeaturesConfigFile(String featuresConfigFile) 
   {
	   this.featuresConfigFile = featuresConfigFile;
   }

   public String getProjectInputDir() 
   {
	  return projectInputDir;
   }

   public void setProjectInputDir(String projectInputDir) 
   {
	  this.projectInputDir = projectInputDir;
   }

   public String getProjectOutputDir() 
   {
	  return projectOutputDir;
   }

   public void setProjectOutputDir(String projectOutputDir) 
   {
	   this.projectOutputDir = projectOutputDir;
   }

   public String getStartProjectMethod() 
   {
	  return startProjectMethod;
   }

   public void setStartProjectMethod(String startProjectMethod) 
   {
      this.startProjectMethod = startProjectMethod;
   }

   public String getEndProjectMethod() 
   {
      return endProjectMethod;
   }

   public void setEndProjectMethod(String endProjectMethod) 
   {
	  this.endProjectMethod = endProjectMethod;
   }

   public String getConnectProjectMethod() 
   {
	  return connectProjectMethod;
   }

   public void setConnectProjectMethod(String connectProjectMethod) 
   {
	  this.connectProjectMethod = connectProjectMethod;
   }

   public int getUserId() 
   {
      return userId;
   }

   public void setUserId(int userId) 
   {
      this.userId = userId;
   }

   public String getUserName() 
   {
      return userName;
   }

   public void setUserName(String userName) 
   {
      this.userName = userName;
   }   
 
   
   public int getMethodId() 
   {
	   return methodId;
   }

   public void setMethodId(int methodId) 
   {
	  this.methodId = methodId;
   }

   public String getMethodName() 
   {
      return methodName;
   }

   public void setMethodName(String methodName) 
   {
      this.methodName = methodName;
   }

   public String getProgrammingLanguageName() 
   {
	  return programmingLanguageName;
   }

   public void setProgrammingLanguageName(String programmingLanguageName) 
   {
	  this.programmingLanguageName = programmingLanguageName;
   }

   public int getProgrammingLanguageId() 
   {
      return programmingLanguageId;
   }

   public void setProgrammingLanguageId(int programmingLanguageId) 
   {
	  this.programmingLanguageId = programmingLanguageId;
   }

   public int getParameterId() 
   {
	  return parameterId;
   }

   public void setParameterId(int parameterId) 
   {
	  this.parameterId = parameterId;
   }

   public int getParameterDataTypeId() 
   {
	  return parameterDataTypeId;
   }

   public void setParameterDataTypeId(int parameterDataTypeId) 
   {
	  this.parameterDataTypeId = parameterDataTypeId;
   }

   public String getParameterFullName() 
   {
	  return parameterFullName;
   }

   public void setParameterFullName(String parameterFullName) 
   {
	  this.parameterFullName = parameterFullName;
   }

   public int getAttributeId() 
   {
	  return attributeId;
   }

   public void setAttributeId(int attributeId) 
   {
	  this.attributeId = attributeId;
   }

   public String getAttributeName() 
   {
	  return attributeName;
   }

   public void setAttributeName(String attributeName) 
   {
	  this.attributeName = attributeName;
   }

   public String getLogDirectory() 
   {
	  return logDirectory;
   }

   public void setLogDirectory(String logDirectory) 
   {
	  this.logDirectory = logDirectory;
   }

   public String getAnnotationFile() 
   {
	  return annotationFile;
   }

   public void setAnnotationFile(String annotationFile) 
   {
	  this.annotationFile = annotationFile;
   }

   public String getAnnotationFilePrefix() 
   {
	  return annotationFilePrefix;
   }

   public void setAnnotationFilePrefix(String annotationFilePrefix) 
   {
	  this.annotationFilePrefix = annotationFilePrefix;
   }

   public String getFindWhiteSpaces() 
   {
	  return findWhiteSpaces;
   }

   public void setFindWhiteSpaces(String findWhiteSpaces) 
   {
	  this.findWhiteSpaces = findWhiteSpaces;
   }

   public String getDataTypeFullClassName() 
   {
	  return dataTypeFullClassName;
   }

   public void setDataTypeFullClassName(String dataTypeFullClassName) 
   {
	   this.dataTypeFullClassName = dataTypeFullClassName;
   }

   public String getDataTypeShortClassName() 
   {
	  return dataTypeShortClassName;
   }

   public void setDataTypeShortClassName(String dataTypeShortClassName) 
   {
	  this.dataTypeShortClassName = dataTypeShortClassName;
   }

   public String getDataTypeStringList() 
   {
	  return dataTypeStringList;
   }

   public void setDataTypeStringList(String dataTypeStringList) 
   {
	  this.dataTypeStringList = dataTypeStringList;
   }

   public String getDataTypeNumberList() 
   {
	  return dataTypeNumberList;
   }

   public void setDataTypeNumberList(String dataTypeNumberList) 
   {
	  this.dataTypeNumberList = dataTypeNumberList;
   }

   public String getValidSpecialCharacter() 
   {
      return validSpecialCharacter;
   }

   public void setValidSpecialCharacter(String validSpecialCharacter) 
   {
	  this.validSpecialCharacter = validSpecialCharacter;
   }

   public String getValueDelimiter() 
   {
	  return valueDelimiter;
   }

   public void setValueDelimiter(String valueDelimiter) 
   {
	  this.valueDelimiter = valueDelimiter;
   }

   public String getConfPropertyName1() 
   {
      return confPropertyName1;
   }

   public void setConfPropertyName1(String confPropertyName1) 
   {
	  this.confPropertyName1 = confPropertyName1;
   }

   public String getStartCallMethod() 
   {
	  return startCallMethod;
   }

   public void setStartCallMethod(String startCallMethod) 
   {
	  this.startCallMethod = startCallMethod;
   }

   public String getEndCallMethod1() 
   {
	  return endCallMethod1;
   }

   public void setEndCallMethod1(String endCallMethod1) 
   {
	  this.endCallMethod1 = endCallMethod1;
   }

   public String getCallMethodDelimiter() 
   {
	  return callMethodDelimiter;
   }

   public void setCallMethodDelimiter(String callMethodDelimiter) 
   {
	  this.callMethodDelimiter = callMethodDelimiter;
   }

   public String getAssignValueDelimiter() 
   {
	  return assignValueDelimiter;
   }

   public void setAssignValueDelimiter(String assignValueDelimiter) 
   {
	  this.assignValueDelimiter = assignValueDelimiter;
   }

   public String getGuiLibDelimiter() 
   {
	  return guiLibDelimiter;
  }

  public void setGuiLibDelimiter(String guiLibDelimiter) 
  {
	   this.guiLibDelimiter = guiLibDelimiter;
  }

   public String getFindString() 
   {
	  return findString;
   }

   public void setFindString(String findString) 
   {
	  this.findString = findString;
   }

   public String getTextFileNameExt3() 
   {
	  return textFileNameExt3;
   }

   public void setTextFileNameExt3(String textFileNameExt3) 
   {
      this.textFileNameExt3 = textFileNameExt3;
   }

   public String getAOPDirectory() 
   {
      return aOPDirectory;
   }

   public void setAOPDirectory(String aOPDirectory) 
   {
      this.aOPDirectory = aOPDirectory;
   }
   
   
   
 
}	