package frl.model.loadUMLSequenceDiagram;

public class ParameterDataSequenceDiagram 
{
   int parameterId;
   int parameterDataTypeId;
   String parameterFullName;
   
   public ParameterDataSequenceDiagram(int parameterId, int parameterDataTypeId, String parameterFullName) 
   {
      this.parameterId         = parameterId;
	  this.parameterDataTypeId = parameterDataTypeId;
	  this.parameterFullName   = parameterFullName;
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

   @Override
   public String toString() 
   {
	   return "ParameterDataSequenceDiagram [parameterId=" + parameterId + ", parameterDataTypeId=" + parameterDataTypeId
			  + ", parameterFullName=" + parameterFullName + "]";
   }
   
}
