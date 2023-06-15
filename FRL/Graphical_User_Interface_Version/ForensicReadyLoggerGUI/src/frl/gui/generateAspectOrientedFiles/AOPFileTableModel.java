package frl.gui.generateAspectOrientedFiles;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import frl.model.generateAspectOrientedFiles.AOPFile;

// This class is a Wrapper for my Data
//Package #6
//Class #7
public class AOPFileTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;

	// Define an atribute
    private List<AOPFile> aopFileDb;
    
    // Define the name of the six columns
    private String[] aopFileColNames = { "Id", "File Name", "Path", "Name", "Type", "Package" };
	
 	// Define the Constructor of the Class empty, without any code inside
    // Method #1
	public AOPFileTableModel() 
	{
		
	}
	
	@Override
	// Method #2
	public String getColumnName(int column) 
	{
		return aopFileColNames[column];
	}

	// Define a Method for Class
	// Method #3
	public void setDataAOPFile(List<AOPFile> aopFileDb) 
	{
       this.aopFileDb = aopFileDb;
	}
	
	// Define the Methods of the Class
	@Override
	// Method #4
	public int getRowCount() 
	{
	   return aopFileDb.size();	
	}

	@Override
	// Method #5
	// There are 6 columns defined at this tableModel
	public int getColumnCount() 
	{
	   return 6;
	}

	@Override
	// Method #6
	public Object getValueAt(int row, int col) 
	{
       AOPFile aopFile = aopFileDb.get(row);
       
       switch(col) 
       {
          case 0:
    	     return aopFile.getId();
          case 1:
    	     return aopFile.getFileName();
          case 2:
    	     return aopFile.getPath();
          case 3:
    	     return aopFile.getName();
          case 4:
    	     return aopFile.getType();
          case 5:
    	     return aopFile.getPackageName();
       }
       return null;
	}

}
