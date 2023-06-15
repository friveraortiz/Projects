package frl.gui.configFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.*;

import frl.model.generateAspectOrientedFiles.ProgrammingLanguageDetail;

//Package #6
//Class #8
public class ProgLangDetTablePanel extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private JTable progLangDetTable;
	private ProgLangDetTableModel progLangDetTableModel;
	
	// Create the constructor of the class
	// Method #1
	public ProgLangDetTablePanel() 
	{
		
		// Define the Object progLangDetTable
		progLangDetTableModel = new ProgLangDetTableModel();
		progLangDetTable      = new JTable(progLangDetTableModel);
		
		progLangDetTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		// add the object into the Panel
		setLayout(new BorderLayout());
        setBackground(Color.lightGray);
		add(new JScrollPane(progLangDetTable), BorderLayout.CENTER);
	}
	
	// This method: setDataprogLangDet was defined first in the ProgLangDetModel Class
	// Method #2
	public void setData(List<ProgrammingLanguageDetail> progLangDetDb) 
	{
		progLangDetTableModel.setDataProgLangDet(progLangDetDb);
	}
	
	// This method is to display the data in the UserTablePanel when the data changes
	// in the UserFormPanel object
	// Method #3
	public void refresh()
	{
		progLangDetTableModel.fireTableDataChanged(); 
	}
	
	// Method #5
	public int getIdSelected()
	{
		int rowSel = progLangDetTable.getSelectedRow();
		int idSel  =(int)progLangDetTable.getModel().getValueAt(rowSel, 0);
		
		return idSel;
	}
	
	// Method #6
	public String getColSel(int col) 
	{
	   // declare the variables
	   int rowSel = progLangDetTable.getSelectedRow();
	   int colSel = col, idSel;
	   String nameSel="", valueSel="", stringSel="";

	   switch(colSel) 
	   { 
	       // Id
           case 0 :
              idSel     = (int)progLangDetTable.getModel().getValueAt(rowSel, 0);
              stringSel = Integer.toString(idSel);
           break;
          
	       // Name
	       case 1 :
	          nameSel   = (String)progLangDetTable.getModel().getValueAt(rowSel, colSel);
	          stringSel = nameSel;
	          break;
	           
	       // Value
	       case 2 :
	          valueSel  = (String)progLangDetTable.getModel().getValueAt(rowSel, colSel);
	          stringSel = valueSel;
		      break;  
	    }

		return stringSel;	
	   
	}
	
	// Method #6
	public String getRowColSel(int row, int col) 
	{
	   // declare the variables
	   int rowSel = row;
	   int colSel = col;
	   int idSel;
	   String nameSel;	
	   String valueSel; 
	   String stringSel="";

	   switch(colSel) 
	   { 
	       // Id
           case 0 :
              idSel     = (int)progLangDetTable.getModel().getValueAt(rowSel, 0);
              stringSel = Integer.toString(idSel);
           break;
          
	       // Name
	       case 1 :
	          nameSel   = (String)progLangDetTable.getModel().getValueAt(rowSel, colSel);
	          stringSel = nameSel;
	          break;
	           
	       // Value
	       case 2 :
	          valueSel  = (String)progLangDetTable.getModel().getValueAt(rowSel, colSel);
	          stringSel = valueSel;
		      break;  
	    }

		return stringSel;	
	   
	}	
	
	public int getRowCount() 
	{
	   int rowCount=0;
	   rowCount = progLangDetTable.getModel().getRowCount();
	   
	   return rowCount;
	}
				
}
