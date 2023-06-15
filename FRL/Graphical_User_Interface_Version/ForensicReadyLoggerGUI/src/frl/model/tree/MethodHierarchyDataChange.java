package frl.model.tree;

import java.io.Serializable;

// This Class is to store the data
public class MethodHierarchyDataChange implements Serializable 
{
	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the MethodDataChange, start with number 1
	private static int count = 1;
    private int projectId;
    private int methodId;
    private String packageName;
    private String className;
    private String shortMethodName;
    private String longMethodName;
    private NodeType nodeType;
    private int parentId;
    private int level;
    
    // Constructor #1 of the MethodHierachyDataChange Class that assigns an ID
	public MethodHierarchyDataChange(int projectId, int methodId, String packageName, 
			                        String className, String shortMethodName, String longMethodName, 
			                        NodeType nodeType, int parentId, int level)
	{
       this.projectId       = projectId;
	   this.methodId        = methodId;	
	   this.packageName     = packageName;
	   this.className       = className;
	   this.shortMethodName = shortMethodName;
	   this.longMethodName  = longMethodName;
	   this.nodeType        = nodeType;
	   this.parentId        = parentId;
	   this.level           = level;
	}
	
    // Constructor #2 of the MethodDataChange Class that assigns an ID
	public MethodHierarchyDataChange(int projectId, String packageName, String className, 
			                        String shortMethodName, String longMethodName, NodeType nodeType, 
			                        int parentId, int level)
	{
	   this.projectId       = projectId;
	   this.packageName     = packageName;
	   this.className       = className;
	   this.shortMethodName = shortMethodName;
	   this.longMethodName  = longMethodName;
	   this.nodeType        = nodeType;
	   this.parentId        = parentId;
	   this.level           = level;
	   
	   this.methodId       = count;
	   count++;
	}

	public int getProjectId() 
	{
	   return projectId;
	}

	public int getMethodId() 
	{
	   return methodId;
	}

	public String getPackageName() 
	{
	   return packageName;
	}

	public String getClassName() 
	{
	   return className;
	}

	public String getShortMethodName() 
	{
	   return shortMethodName;
	}

	public String getLongMethodName() 
	{
	   return longMethodName;
	}

	public NodeType getNodeType() 
	{
	   return nodeType;
	}

	public int getParentId() 
	{
	   return parentId;
	}

	public int getLevel() 
	{
	   return level;
	}

	public void setProjectId(int projectId) 
	{
	   this.projectId = projectId;
	}

	public void setMethodId(int methodId) 
	{
	   this.methodId = methodId;
	}

	public void setPackageName(String packageName) 
	{
	   this.packageName = packageName;
	}

	public void setClassName(String className) 
	{
	   this.className = className;
	}

	public void setShortMethodName(String shortMethodName) 
	{
	   this.shortMethodName = shortMethodName;
	}

	public void setLongMethodName(String longMethodName) 
	{
	   this.longMethodName = longMethodName;
	}

	public void setNodeType(NodeType nodeType) 
	{
	   this.nodeType = nodeType;
	}

	public void setParentId(int parentId) 
	{
	   this.parentId = parentId;
	}

	public void setLevel(int level) 
	{
	   this.level = level;
	}

	@Override
	public String toString() 
	{
	   return "MethodHierachyDataChange [projectId=" + projectId + ", methodId=" + methodId + ", packageName="
	   		+ packageName + ", className=" + className + ", shortMethodName=" + shortMethodName
	   		+ ", longMethodName=" + longMethodName + ", nodeType=" + nodeType + ", parentId=" + parentId
	   		+ ", level=" + level + "]";
	}

}