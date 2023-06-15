package frl.gui.aopTemplateFile;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import frl.model.aopTemplateFile.AOPTemplateFile;

// This class is a Wrapper for my Data
//Package #6
//Class #7
public class AOPTemplateFileTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;

	// Define an atribute
    private List<AOPTemplateFile> aOPTemplateFileDb;
    
    // Define the name of the Columns
    private String[] aOPTemplateFileColNames = { "Id", "File Name", "Path", "Name", "Type File", "Package Name", "Programming Language Id", "Programming Language Name", "PointCut 1", "PointCut 2" };
	
 	// Define the Constructor of the Class empty, without any code inside
    // Method #1
	public AOPTemplateFileTableModel() 
	{
		
	}
	
	@Override
	// Method #2
	public String getColumnName(int column) 
	{
		return aOPTemplateFileColNames[column];
	}

	// Define a Method for Class
	// Method #3
	public void setData(List<AOPTemplateFile> aOPTemplateFileDb) 
	{
       this.aOPTemplateFileDb = aOPTemplateFileDb;
	}
	
	// Define the Methods of the Class
	@Override
	// Method #4
	public int getRowCount() 
	{
	   return aOPTemplateFileDb.size();	
	}

	@Override
	// Method #5
	public int getColumnCount() 
	{
	   return 4;
	}

	@Override
	// Method #6
	public Object getValueAt(int row, int col) 
	{
			   
	   AOPTemplateFile aOPTemplateFile = aOPTemplateFileDb.get(row);
       
       switch(col) 
       {
          case 0:
    	     return aOPTemplateFile.getId();
          case 1:
    	     return aOPTemplateFile.getFileName();
          case 2:
    	     return aOPTemplateFile.getPath();
          case 3:
    	     return aOPTemplateFile.getName();
          case 4:
    	     return aOPTemplateFile.getTypeFile();
          case 5:
    	     return aOPTemplateFile.getPackageName();
          case 6:
    	     return aOPTemplateFile.getProgrammingLanguageId();
          case 7:
    	     return aOPTemplateFile.getProgrammingLanguageName();
          case 8:
    	     return aOPTemplateFile.getPointCutName1();
          case 9:
    	     return aOPTemplateFile.getPointCutName2();
       }

       return null;
	}

}
