package frl.gui.loadUMLSequenceDiagram;
import java.util.EventObject;


//Package #6
//Class #2
public class UMLSequenceDiagramFormEvent extends EventObject 
{
   private static final long serialVersionUID = 1L;
   private int projectId;
   private String featuresConfigFile;
   private String databaseConfigFile;
   private String projectName;
   private String jarFileName;
   private String projectInputDir;
   private String projectOutputDir;
   private String programmingLanguage;
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
   
   /*private String methodsFiles;
   private String aspectOrientedFile1;
   private String aspectOrientedFile2;*/
   private String umlSeqDiagramImage;
   private String umlSeqDiagramText;
   
   // Constructor # 1
   public UMLSequenceDiagramFormEvent(Object source) 
   {
		super(source);
   }
	
   // Constructor # 2
   public UMLSequenceDiagramFormEvent(Object source, int projectId, String featuresConfigFile, String databaseConfigFile,
		                              String projectName, String jarFileName, String projectInputDir, 
		                              String projectOutputDir, String programmingLanguage, String dbms, 
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
        this.programmingLanguage  = programmingLanguage; 
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
   public UMLSequenceDiagramFormEvent(Object source, String featuresConfigFile, String databaseConfigFile, String projectName,
                              String jarFileName, String projectInputDir, String projectOutputDir,
                              String programmingLanguage, String dbms, String inputMethod, 
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
        this.programmingLanguage  = programmingLanguage; 
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
   public UMLSequenceDiagramFormEvent(Object source, int projectId, String featuresConfigFile, String databaseConfigFile,
                              String projectName, String jarFileName, String projectInputDir, 
                              String projectOutputDir, String programmingLanguage, String dbms, 
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
      this.programmingLanguage  = programmingLanguage; 
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
   public UMLSequenceDiagramFormEvent(Object source, String featuresConfigFile, String databaseConfigFile, String projectName,
            				  String jarFileName, String projectInputDir, String projectOutputDir,
           				      String programmingLanguage, String dbms, String inputMethod, 
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
      this.programmingLanguage = programmingLanguage; 
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
   public UMLSequenceDiagramFormEvent(Object source, String featuresConfigFile, String databaseConfigFile, 
		                      int projectId, String projectName, String projectOutputDir) 
   {
      super(source);
      this.featuresConfigFile  = featuresConfigFile;
      this.databaseConfigFile  = databaseConfigFile;
      this.projectId                  = projectId;
      this.projectName         = projectName;
      this.projectOutputDir    = projectOutputDir;
   }
   
   // Constructor # 6
   public UMLSequenceDiagramFormEvent(Object source, int projectId, String field, String value) 
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
		   
		}
		
   }
   
   // Constructor # 7
   public UMLSequenceDiagramFormEvent(Object source, String field, String value) 
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

   public String getProgLanguage() 
   {
      return programmingLanguage;
   }

   public void setProgLanguage(String programmingLanguage) 
   {
      this.programmingLanguage = programmingLanguage;
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

}	

