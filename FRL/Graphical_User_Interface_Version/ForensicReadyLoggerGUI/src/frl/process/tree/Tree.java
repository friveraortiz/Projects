package frl.process.tree;

import java.util.ArrayList;

import frl.model.tree.NodeType;
import frl.model.tree.TreeStructure;

public class Tree 
{
   public  Node<String> createTree(ArrayList<TreeStructure> treeStructures) 
   {
      String name2;
	  NodeType nodeType;
	  int i;
	   
	  Node<String> root = null;
	  Node<String> node = null;
	  Node<String> subNode;
	   
	  for (i=0; i < treeStructures.size(); i++) 
	  {
	     nodeType = treeStructures.get(i).getNodeType();
	     name2    = treeStructures.get(i).getName2();
	     
	     switch(nodeType)
	     {
	        case Root:
	           root = new Node<>(name2);
	           break;
	           
	        case Node:
	           node = root.addChild(new Node<String>(name2));
	           break;
	           
	        case Internal:
		       subNode = node.addChild(new Node<String>(name2));
		       break;  
	     }   
	     
	  }
	  
	  return root;
	}
		
	public <T> void printTree(Node<T> node, String appender) 
	{
       System.out.println(appender + node.getData());
	   node.getChildren().forEach(each ->  printTree(each, appender + appender));
	}

}
