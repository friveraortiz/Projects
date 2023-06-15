package frl.gui.projectTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import frl.model.generateAspectOrientedFiles.ConfigFile;

//Package #6
//Class #8
public class ProjectTablePanel extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private JTable projectTable;
	private ProjectTableModel projectTableModel;
	
	// Defined for a PopupMenu
	private JPopupMenu projectPopup;
	
	// Listener for the AOPFile Table
	ProjectTableListener projectTableListener;
	
	// PopupMenu selected
	private String popupMenu="";
	
	// Create the constructor of the class
	// Method #1
	public ProjectTablePanel() 
	{
		// Define the Object projectTable
		projectTableModel = new ProjectTableModel();
		projectTable      = new JTable(projectTableModel);
		
		// Defined for a PopupMenu
		projectPopup   = new JPopupMenu();
		JMenuItem view = new JMenuItem("View");
		view.setToolTipText("Consult the details of this Project Application"); 
		
		JMenuItem modify = new JMenuItem("Modify");
		modify.setToolTipText("Modify the details of this Project Application"); 
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.setToolTipText("Eliminates this Project Application"); 
		
		projectPopup.add(view);
		projectPopup.add(modify);
		projectPopup.add(delete);
		
		// Listener for the Mouse to display a Popup Menu
		projectTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
			    // If we do right click with the mouse, then display a Popup Menu
				if(e.getButton() == MouseEvent.BUTTON3) 
				{
				   projectPopup.show(projectTable, e.getX(), e.getY());
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
				int idSel = getProjectIdSelected();
				
				if(projectTableListener != null) 
				{
				   // The View Popup Menu was selected	
				   popupMenu="View";	
				   			      
				   // Call the interface which is implemented in the EmployeeFrame Class
				   projectTableListener.projectEventOccurred(idSel, popupMenu);
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
			   int idSel = getProjectIdSelected();
				
			   if(projectTableListener != null) 
			   {
				  // The Modify Popup Menu was selected	
			      popupMenu="Modify";
			      
			      // Call the interface which is implemented in the AOPFileFrame Class
				  projectTableListener.projectEventOccurred(idSel, popupMenu);
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
				int idSel = getProjectIdSelected();
				
				if(projectTableListener != null) 
				{
				   // The Delete Popup Menu was selected	
				   popupMenu="Delete";
				   
				   // Call the interface which is implemented in the AOPFileFrame Class
				   projectTableListener.projectEventOccurred(idSel, popupMenu);
				}
				
			}
	    });
		
		// add the object into the Panel
		setLayout(new BorderLayout());
        setBackground(Color.lightGray);
		add(new JScrollPane(projectTable), BorderLayout.CENTER);
		
	}
	
	// This method: setDataAOPFile was defined first in the ProjectTableModel Class
	// Method #2
	public void setData(List<ConfigFile> configFileDb) 
	{
		projectTableModel.setData(configFileDb);
	}
	
	
	// This method is to display the data in the projectTablePanel when the data changes
	// in the AOPFileFormPanel object
	// Method #3
	public void refresh()
	{
		projectTableModel.fireTableDataChanged(); 
	}
	
	// Method #4
	public void setProjectTableListener(ProjectTableListener projectTableListener) 
	{
		this.projectTableListener = projectTableListener;
	}
	
	// Method #5
	public int getProjectIdSelected()
	{
		int rowSel = projectTable.getSelectedRow();
		int idSel = (int)projectTable.getModel().getValueAt(rowSel, 0);
		
		return idSel;
	}
	
	// Method #6
	public String getProjectStringColSel(int col) 
	{
	   // declare the variables
	   int rowSel, colSel, idSel;
	   String stringSel="";
	   
	   rowSel = projectTable.getSelectedRow();
	   colSel = col;

	   switch(colSel) 
	   { 
	      // Id
          case 0 :
             idSel      = (int)projectTable.getModel().getValueAt(rowSel, 0);
             stringSel  = Integer.toString(idSel);
             break;
          
	      /*Project Name, JAR File Name, Project Input Directory, Project Output Directory
	          Programming Language, DBMS, Input Method, Start Method Name, End Method Name,
	          Connect Method Name, Operating System
	      */   
	      case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 :
	         stringSel = (String)projectTable.getModel().getValueAt(rowSel, colSel);
		     break;  
	    }

		return stringSel;	
	   
	}
				
}
