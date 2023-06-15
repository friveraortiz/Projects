package frl.model.inputMethods;

import java.util.ArrayList;

public class DatabaseData
{
	boolean dbClassFlag;
	ArrayList <String> methodDBList;
	
	public DatabaseData(boolean dbClassFlag, ArrayList <String> methodDBList) 
	{
	   this.dbClassFlag  = dbClassFlag;
	   this.methodDBList = methodDBList;
	}

	public boolean isDbClassFlag() 
	{
		return dbClassFlag;
	}

	public void setDbClassFlag(boolean dbClassFlag) 
	{
		this.dbClassFlag = dbClassFlag;
	}

	public ArrayList<String> getMethodDBList() 
	{
		return methodDBList;
	}

	public void setMethodDBList(ArrayList<String> methodDBList) 
	{
		this.methodDBList = methodDBList;
	}

	@Override
	public String toString() 
	{
		return "DatabaseData [dbClassFlag=" + dbClassFlag + ", methodDBList=" + methodDBList + "]";
	}
	
	
}
