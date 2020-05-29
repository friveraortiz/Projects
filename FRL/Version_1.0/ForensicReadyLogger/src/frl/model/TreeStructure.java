package frl.model;

import java.io.Serializable;


// This Class is to store the data
public class TreeStructure implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

    private int id;	
    private String name;	
    private NodeType nodeType;
    private int parentId;
    private int level;
    private String name2;

    // Constructor #1 of the Tree Structure Class
	public TreeStructure(int id, String name, NodeType nodeType, 
			             int parentId, int level)
	{
	   this.id             = id;	
       this.name           = name;
       this.nodeType       = nodeType;
       this.parentId       = parentId;	
       this.level          = level;
       
	}
	
    // Constructor #2 of the Tree Structure Class
	public TreeStructure(int id, String name, NodeType nodeType, 
			             int parentId, int level, String name2)
	{
	   this.id        = id;	
       this.name      = name;
       this.nodeType  = nodeType;
       this.parentId  = parentId;	
       this.level     = level;
       this.name2     = name2;
       
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public NodeType getNodeType() 
	{
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) 
	{
		this.nodeType = nodeType;
	}

	public int getParentId() 
	{
		return parentId;
	}

	public void setParentId(int parentId) 
	{
		this.parentId = parentId;
	}

	public int getLevel() 
	{
		return level;
	}

	public void setLevel(int level) 
	{
		this.level = level;
	}
	
	public String getName2() 
	{
		return name2;
	}

	public void setName2(String name2) 
	{
		this.name2 = name2;
	}

	@Override
	public String toString() 
	{
		return "TreeStructure [id=" + id + ", name=" + name + ", nodeType=" + nodeType + ", parentId=" + parentId
				+ ", level=" + level + ", name2=" + name2 + "]";
	}

	
}
