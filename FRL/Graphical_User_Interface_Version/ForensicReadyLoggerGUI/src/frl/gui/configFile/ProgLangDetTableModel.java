package frl.gui.configFile;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import frl.model.generateAspectOrientedFiles.ProgrammingLanguageDetail;

// This class is a Wrapper for my Data
//Package #6
//Class #7
public class ProgLangDetTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;

	// Define an attribute
    private List<ProgrammingLanguageDetail> progLangDetDb;
    
    // Define the name of the Columns
    private String[] progLangDetColNames = { "Id", "Name", "Value" };
    	
 	// Define the Constructor of the Class empty, without any code inside
    // Method #1
	public ProgLangDetTableModel() 
	{
		
	}
	
	@Override
	// Method #2
	public String getColumnName(int column) 
	{
		return progLangDetColNames[column];
	}

	// Define a Method for Class
	// Method #3
	public void setDataProgLangDet(List<ProgrammingLanguageDetail> progLangDetDb) 
	{
       this.progLangDetDb = progLangDetDb;
	}
	
	// Define the Methods of the Class
	@Override
	// Method #4
	public int getRowCount() 
	{
	   return progLangDetDb.size();	
	}

	@Override
	// Method #5
	public int getColumnCount() 
	{
	   return 3;
	}

	@Override
	// Method #6
	public Object getValueAt(int row, int col) 
	{
       ProgrammingLanguageDetail progLangDet = progLangDetDb.get(row);
       
       switch(col) 
       {
       case 0:
    	   return progLangDet.getIdDet();
       case 1:
    	   return progLangDet.getElementName();
       case 2:
    	   return progLangDet.getElementValue();
       }

       return null;
	}

}
