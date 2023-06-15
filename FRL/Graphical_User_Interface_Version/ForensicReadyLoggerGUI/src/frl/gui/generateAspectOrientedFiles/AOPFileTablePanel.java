package frl.gui.generateAspectOrientedFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import frl.model.generateAspectOrientedFiles.AOPFile;
import frl.model.generateAspectOrientedFiles.AOPFileType;


//Package #6
//Class #8
public class AOPFileTablePanel extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private JTable aopFileTable;
	private AOPFileTableModel aopFileTableModel;
	
	// Defined for a PopupMenu
	private JPopupMenu aopFilePopup;
	
	// Listener for the AOPFile Table
	AOPFileTableListener aOPFileTableListener;
	
	// PopupMenu selected
	private String popupMenu="";
	
	// Create the constructor of the class
	// Method #1
	public AOPFileTablePanel() 
	{
		
		// Define the Object aopFileTable
		aopFileTableModel = new AOPFileTableModel();
		aopFileTable = new JTable(aopFileTableModel);
		
		// Defined for a PopupMenu
		aopFilePopup = new JPopupMenu();
		JMenuItem view = new JMenuItem("View");
		view.setToolTipText("Consult the details of this Aspect Oriented Programming File"); 
		
		JMenuItem modify = new JMenuItem("Modify");
		modify.setToolTipText("Modify the details of this Aspect Oriented Programming File"); 
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.setToolTipText("Eliminates this Aspect Oriented Programming File"); 
		
		aopFilePopup.add(view);
		aopFilePopup.add(modify);
		aopFilePopup.add(delete);
		
		// Listener for the Mouse to display a Popup Menu
		aopFileTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				
			    // If we do right click with the mouse, then display a Popup Menu
				if(e.getButton() == MouseEvent.BUTTON3) 
				{
					
				   aopFilePopup.show(aopFileTable, e.getX(), e.getY());
				}
				
			}
		});
		
		
		// Action Listener for the View AOP File Popup Menu
		view.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// Get from the current row selected, the Employee Id
				int idSel = getAOPFileIdSelected();
				
				if(aOPFileTableListener != null) 
				{
				   // The View Popup Menu was selected	
				   popupMenu="View";	
				   			      
				   // Call the interface which is implemented in the EmployeeFrame Class
				   aOPFileTableListener.aopFileTableEventOccurred(idSel, popupMenu);
				}
			}
	    });
		
		// Action Listener for the Modify AOP File Popup Menu
		modify.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			   // Get from the current row selected, the AOP File Id
			   int idSel = getAOPFileIdSelected();
				
			   if(aOPFileTableListener != null) 
			   {
				  // The Modify Popup Menu was selected	
			      popupMenu="Modify";
			      
			      // Call the interface which is implemented in the AOPFileFrame Class
				  aOPFileTableListener.aopFileTableEventOccurred(idSel, popupMenu);
			   }
				
			}
	    });
		
		// Action Listener for the Delete AOPFile Popup Menu
		delete.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				// Get from the current row selected, the AOPFile Id
				int idSel = getAOPFileIdSelected();
				
				if(aOPFileTableListener != null) 
				{
				   // The Delete Popup Menu was selected	
				   popupMenu="Delete";
				   
				   // Call the interface which is implemented in the AOPFileFrame Class
				   aOPFileTableListener.aopFileTableEventOccurred(idSel, popupMenu);
				}
				
			}
	    });
		
		
		// add the object into the Panel
		setLayout(new BorderLayout());
        setBackground(Color.lightGray);
		add(new JScrollPane(aopFileTable), BorderLayout.CENTER);
		
		
	}
	
	// This method: setDataAOPFile was defined first in the AOPFileTableModel Class
	// Method #2
	public void setDataAOPFile(List<AOPFile> aopFileDb) 
	{
		aopFileTableModel.setDataAOPFile(aopFileDb);
	}
	
	// This method is to display the data in the AOPFileTablePanel when the data changes
	// in the AOPFileFormPanel object
	// Method #3
	public void refresh()
	{
		aopFileTableModel.fireTableDataChanged(); 
	}
	
	// Method #4
	public void setAOPFileTableListener(AOPFileTableListener listener) 
	{
		this.aOPFileTableListener = listener;
	}
	
	// Method #5
	public int getAOPFileIdSelected()
	{
		int rowSel = aopFileTable.getSelectedRow();
		int idSel = (int)aopFileTable.getModel().getValueAt(rowSel, 0);
		
		return idSel;
	}
	
	// Method #6
	public String getAOPFileStringColSel(int col) 
	{
	   // declare the variables
	   int rowSel = aopFileTable.getSelectedRow();
	   int colSel = col, idSel;
	   String fileNameSel, pathSel, nameSel;
	   String packageSel, stringSel="";
	   AOPFileType typeSel;

	   switch(colSel) 
	   { 
	       // AOPFile Id Number
           case 0 :
              idSel     = (int)aopFileTable.getModel().getValueAt(rowSel, 0);
              stringSel = Integer.toString(idSel);
           break;
          
	       // File Name
	       case 1 :
	          fileNameSel = (String)aopFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel   = fileNameSel;
	          break;
	           
	       // Path
	       case 2 :
	          pathSel   = (String)aopFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel = pathSel;
		      break;  
		      
	       // Name
	       case 3 :
	          nameSel   = (String)aopFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel = nameSel;
		      break;  
		      
		   // Type
	       case 4 :
	          typeSel   = (AOPFileType)aopFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel = typeSel.toString();
	          break;
		      
	       // Package
	       case 5 :
	          packageSel = (String)aopFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel  = packageSel;
		      break;
	          
	    }

		return stringSel;	
	   
	}
				
}
