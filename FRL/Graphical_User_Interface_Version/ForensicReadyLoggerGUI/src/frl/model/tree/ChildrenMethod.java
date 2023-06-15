package frl.model.tree;

public class ChildrenMethod
{

    private final int parentId;
    private final String methodName;
	private NodeType nodeType;


    public ChildrenMethod(int parentId, String methodName, NodeType nodeType)
    {
        this.parentId   = parentId;
        this.methodName = methodName;
        this.nodeType   = nodeType;
    }


	public int getParentId() 
	{
		return parentId;
	}


	public String getMethodName() 
	{
		return methodName;
	}
	

	public NodeType getNodeType() 
	{
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) 
	{
		this.nodeType = nodeType;
	}


	@Override
	public String toString() 
	{
		return "ChildrenMethod [parentId=" + parentId + ", methodName=" + methodName + ", nodeType=" + nodeType + "]";
	}


}