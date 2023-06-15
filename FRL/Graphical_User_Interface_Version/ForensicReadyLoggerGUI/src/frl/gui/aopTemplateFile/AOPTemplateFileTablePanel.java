package frl.gui.aopTemplateFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import frl.model.aopTemplateFile.AOPTemplateFile;


//Package #6
//Class #8
public class AOPTemplateFileTablePanel extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private JTable aOPTemplateFileTable;
	private AOPTemplateFileTableModel aOPTemplateFileTableModel;
	
	// Defined for a PopupMenu
	private JPopupMenu aOPTemplateFilePopup;
	
	// Listener for the User Table
	AOPTemplateFileTableListener aOPTemplateFileTableListener;
	
	// PopupMenu selected
	private String popupMenu="";
	
	// Create the constructor of the class
	// Method #1
	public AOPTemplateFileTablePanel() 
	{	
       // Define the Object aOPTemplateFileTable
	   aOPTemplateFileTableModel = new AOPTemplateFileTableModel();
	   aOPTemplateFileTable      = new JTable(aOPTemplateFileTableModel);
		
	   // Defined for a PopupMenu
	   aOPTemplateFilePopup = new JPopupMenu();
	   JMenuItem view= new JMenuItem("View");
	   // Set the tooltip text in the JMenuItem Object 
	   view.setToolTipText("Consults the details of this AOP Template File");
		
	   JMenuItem modify = new JMenuItem("Modify");
	   // Set the tooltip text in the JMenuItem Object 
	   modify.setToolTipText("Changes the details of this AOP Template File"); 
		
	   JMenuItem delete = new JMenuItem("Delete");
	   // Set the tooltip text in the JMenuItem Object 
	   delete.setToolTipText("Eliminates this AOP Template File"); 
	   aOPTemplateFilePopup.add(view);
	   aOPTemplateFilePopup.add(modify);
	   aOPTemplateFilePopup.add(delete);
		
	   // Listener for the Mouse to display a Popup Menu
	   aOPTemplateFileTable.addMouseListener(new MouseAdapter() 
	   {
	      @Override
		  public void mousePressed(MouseEvent e) 
		  {
		     // If we do right click with the mouse, then display a Popup Menu
			 if(e.getButton() == MouseEvent.BUTTON3) 
			 {
			    aOPTemplateFilePopup.show(aOPTemplateFileTable, e.getX(), e.getY());
			 }
				
		   }
		});
		
		
		// Action Listener for the View User Popup Menu
		view.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// Get from the current row selected, the Employee Id
				int idSel=getIdSelected();
				
				if(aOPTemplateFileTable != null) 
				{
				   // The View Popup Menu was selected	
				   popupMenu="View";	
				   			      
				   // Call the interface which is implemented in the EmployeeFrame Class
				   aOPTemplateFileTableListener.aOPTemplateFileEventOccurred(idSel, popupMenu);
				}
			}
	    });
		
		// Action Listener for the Modify User Popup Menu
		modify.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			   // Get from the current row selected, the User Id
			   int idSel = getIdSelected();
				
			   if(aOPTemplateFileTableListener != null) 
			   {
				  // The Modify Popup Menu was selected	
			      popupMenu="Modify";
			      
			      // Call the interface which is implemented in the UserFrame Class
			      aOPTemplateFileTableListener.aOPTemplateFileEventOccurred(idSel, popupMenu);
			   }
				
			}
	    });
		
		// Action Listener for the Delete User Popup Menu
		delete.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				// Get from the current row selected, the User Id
				int idSel = getIdSelected();
				
				if(aOPTemplateFileTableListener != null) 
				{
				   // The Delete Popup Menu was selected	
				   popupMenu="Delete";
				   
				   // Call the interface which is implemented in the UserFrame Class
				   aOPTemplateFileTableListener.aOPTemplateFileEventOccurred(idSel, popupMenu);
					  
				}
				
			}
	    });
		
		// add the object into the Panel
		setLayout(new BorderLayout());
        setBackground(Color.lightGray);
		add(new JScrollPane(aOPTemplateFileTable), BorderLayout.CENTER);
		
	}
	
	// This method: setDataUser was defined first in the UserTableModel Class
	// Method #2
	public void setData(List<AOPTemplateFile> aOPTemplateFile) 
	{
		aOPTemplateFileTableModel.setData(aOPTemplateFile);
	}
	
	// This method is to display the data in the UserTablePanel when the data changes
	// in the UserFormPanel object
	// Method #3
	public void refresh()
	{
		aOPTemplateFileTableModel.fireTableDataChanged(); 
	}
	
	// Method #4
	public void setTableListener(AOPTemplateFileTableListener listener) 
	{
		this.aOPTemplateFileTableListener = listener;
	}
	
	// Method #5
	public int getIdSelected()
	{
	   int rowSel=0, idSel=0;	
	   
	   rowSel = aOPTemplateFileTable.getSelectedRow();
	   idSel  = (int)aOPTemplateFileTable.getModel().getValueAt(rowSel, 0);
		
	   return idSel;
	}
	
	// Method #6
	public String getStringColSel(int col) 
	{
	   // declare the variables
	   int rowSel=0, colSel=0, idSel=0, programmingLanguageIdSel=0;
	   String fileNameSel="", pathSel="", nameSel="", typeFileSel="", packageNameSel="", programmingLanguageNameSel="", 
			  pointCutName1Sel="", pointCutName2Sel="", stringSel="";
	   
	   rowSel = aOPTemplateFileTable.getSelectedRow();
	   colSel = col;

	   switch(colSel) 
	   { 
	       // AOPFileTemplate Id Number
           case 0 :
              idSel=(int)aOPTemplateFileTable.getModel().getValueAt(rowSel, 0);
              stringSel=Integer.toString(idSel);
           break;
          
	       // File Name
	       case 1 :
	          fileNameSel=(String)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=fileNameSel;
	       break;
	           
	       // Path
	       case 2 :
	          pathSel=(String)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=pathSel;
	       break;
	       
	       // Name
	       case 3 :
	          nameSel=(String)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=nameSel;
	       break;
	       
	       // Type File
	       case 4 :
	          typeFileSel=(String)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=typeFileSel;
	       break;
	       
	       // Package Name
	       case 5 :
	          packageNameSel=(String)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=packageNameSel;
	       break;
	       
	       // Programming Language Id
	       case 6 :
	          programmingLanguageIdSel=(int)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=Integer.toString(programmingLanguageIdSel);
	       break;
	       
	       // Programming Language Name
	       case 7 :
	          programmingLanguageNameSel=(String)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=programmingLanguageNameSel;
	       break;
	       
	       // PointCutName 1
	       case 8 :
	          pointCutName1Sel=(String)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=pointCutName1Sel;
	       break;
	       
	       // PointCutName 2
	       case 9 :
		      pointCutName2Sel=(String)aOPTemplateFileTable.getModel().getValueAt(rowSel, colSel);
		      stringSel=pointCutName2Sel;
	       break;
	       
	    }

		return stringSel;	
	   
	}
				
}
