package frl.model.loadUMLSequenceDiagram;


public class DataType 
{
   private int progLanguageIdentifier;
   private String parameterName;
   
   private int dataTypeIdentifier1;
   private String dataTypeName1;
   private DataCategory dataCategory1;
   private String packageName1;
   private String className1;
   private String returnType1;
   
   private int dataTypeIdentifier2;
   private String dataTypeName2;
   private DataCategory dataCategory2;
   private String packageName2;
   private String className2;
   private String returnType2;

   // Constructor #1
   public DataType (int progLanguageIdentifier, String parameterName, 
		            int dataTypeidentifier1, DataCategory dataCategory1)
   {
      this.progLanguageIdentifier = progLanguageIdentifier;
      this.parameterName          = parameterName;
      this.dataTypeIdentifier1    = dataTypeidentifier1;
      this.dataCategory1          = dataCategory1;
   }
   
   // Constructor #2
   public DataType (String parameterName, String dataTypeName1, String packageName1, 
		            String className1, String returnType1)
   {
	  this.parameterName = parameterName;
	  this.dataTypeName1 = dataTypeName1;
	  this.packageName1  = packageName1;
	  this.className1    = className1; 
      this.returnType1   = returnType1;
   }
   
   // Constructor #3
   public DataType(int progLanguageIdentifier, String parameterName, int dataTypeIdentifier1, 
		           String dataTypeName1, DataCategory dataCategory1, String packageName1, 
		           String className1, String returnType1, int dataTypeIdentifier2,
		           String dataTypeName2, DataCategory dataCategory2, String packageName2, 
		           String className2, String returnType2) 
   {
      this.progLanguageIdentifier = progLanguageIdentifier;
	  this.parameterName = parameterName;
	  this.dataTypeIdentifier1 = dataTypeIdentifier1;
	  this.dataTypeName1 = dataTypeName1;
	  this.dataCategory1 = dataCategory1;
	  this.packageName1  = packageName1;
	  this.className1    = className1;
	  this.returnType1   = returnType1;
	  this.dataTypeIdentifier2 = dataTypeIdentifier2;
	  this.dataTypeName2 = dataTypeName2;
	  this.dataCategory2 = dataCategory2;
	  this.packageName2  = packageName2;
	  this.className2    = className2;
	  this.returnType2   = returnType2;
   }
   
   // Constructor #4
   public DataType (String packageName1, int dataTypeIdentifier1, String dataTypeName1, String returnType1)
   {
      this.packageName1        = packageName1;
      this.dataTypeIdentifier1 = dataTypeIdentifier1;
      this.dataTypeName1       = dataTypeName1;
      this.returnType1         = returnType1;
   }
   
   // Constructor #5
   public DataType (String packageName1, int dataTypeIdentifier1, String dataTypeName1, String dataTypeName2, String returnType1)
   {
      this.packageName1 = packageName1;
      this.dataTypeIdentifier1 = dataTypeIdentifier1;
      this.dataTypeName1       = dataTypeName1;
      this.dataTypeName2       = dataTypeName2;
      this.returnType1         = returnType1;
   }  

   // Constructor #6
   public DataType(int progLanguageIdentifier, String parameterName, int dataTypeIdentifier1, 
		           String dataTypeName1, DataCategory dataCategory1, String packageName1, 
		           String className1, String returnType1) 
   {
      this.progLanguageIdentifier = progLanguageIdentifier;
	  this.parameterName = parameterName;
	  this.dataTypeIdentifier1 = dataTypeIdentifier1;
	  this.dataTypeName1 = dataTypeName1;
	  this.dataCategory1 = dataCategory1;
	  this.packageName1  = packageName1;
	  this.className1    = className1;
	  this.returnType1   = returnType1;
   }   
   
   public int getProgLanguageIdentifier() 
   {
      return progLanguageIdentifier;
   }

   public void setProgLanguageIdentifier(int progLanguageIdentifier) 
   {
      this.progLanguageIdentifier = progLanguageIdentifier;
   }

   public int getDataTypeIdentifier1() 
   {
	  return dataTypeIdentifier1;
   }

   public void setDataTypeIdentifier1(int dataTypeIdentifier1) 
   {
	   this.dataTypeIdentifier1 = dataTypeIdentifier1;
   }

   public String getParameterName() 
   {
	   return parameterName;
   }

   public void setParameterName(String parameterName) 
   {
	   this.parameterName = parameterName;
   }

   public DataCategory getDataCategory1() 
   {
	   return dataCategory1;
   }

   public void setDataCategory1(DataCategory dataCategory1) 
   {
	   this.dataCategory1 = dataCategory1;
   }

   public String getPackageName1() 
   {
      return packageName1;
   }

   public void setPackageName(String packageName1) 
   {
	  this.packageName1 = packageName1;
   }

   public String getClassName1() 
   {
      return className1;
   }

   public void setClassName1(String className1) 
   {
	  this.className1 = className1;
   }

   public String getReturnType1() 
   {
	   return returnType1;
   }

   public void setReturnType1(String returnType1) 
   {
	   this.returnType1 = returnType1;
   }
   
   public String getDataTypeName1() 
   {
      return dataTypeName1;
   }

   public void setDataTypeName1(String dataTypeName1) 
   {
	   this.dataTypeName1 = dataTypeName1;
   }

   public int getDataTypeIdentifier2() 
   {
	  return dataTypeIdentifier2;
   }

   public void setDataTypeIdentifier2(int dataTypeIdentifier2) 
   {
	  this.dataTypeIdentifier2 = dataTypeIdentifier2;
   }

   public String getDataTypeName2() 
   {
	   return dataTypeName2;
   }

   public void setDataTypeName2(String dataTypeName2) 
   {
	  this.dataTypeName2 = dataTypeName2;
   }

   public DataCategory getDataCategory2() 
   {
      return dataCategory2;
   }

   public void setDataCategory2(DataCategory dataCategory2) 
   {
	  this.dataCategory2 = dataCategory2;
   }

   public String getPackageName2() 
   {
	  return packageName2;
   }

   public void setPackageName2(String packageName2) 
   {
	  this.packageName2 = packageName2;
   }

   public String getClassName2() 
   {
	  return className2;
   }

   public void setClassName2(String className2) 
   {
	  this.className2 = className2;
   }

   public String getReturnType2() 
   {
	  return returnType2;
   }

   public void setReturnType2(String returnType2) 
   {
	  this.returnType2 = returnType2;
   }

   @Override
   public String toString() 
   {
	  return "DataType [progLanguageIdentifier=" + progLanguageIdentifier + ", parameterName=" + parameterName
			 + ", dataTypeIdentifier1=" + dataTypeIdentifier1 + ", dataTypeName1=" + dataTypeName1 + ", dataCategory1="
			 + dataCategory1 + ", packageName1=" + packageName1 + ", className1=" + className1 + ", returnType1="
			 + returnType1 + ", dataTypeIdentifier2=" + dataTypeIdentifier2 + ", dataTypeName2=" + dataTypeName2
			 + ", dataCategory2=" + dataCategory2 + ", packageName2=" + packageName2 + ", className2=" + className2
			 + ", returnType2=" + returnType2 + "]";
   }
   
}
