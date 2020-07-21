package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Employee;
import model.Gender;
import model.MaritalStatus;

public class EmployeeTablePanel extends JPanel 
{
	private JTable employeeTable;
	private EmployeeTableModel employeeTableModel;
	
	// Defined for a PopupMenu
	private JPopupMenu employeePopup;
	
	// Listener for the Employee Table
	EmployeeTableListener employeeTableListener;
	
	// PopupMenu selected
	private String popupMenu="";
	
	
	// Create the constructor of the class
	public EmployeeTablePanel() 
	{
		
		// Define the Object employeeTable
		employeeTableModel = new EmployeeTableModel();
		employeeTable = new JTable(employeeTableModel);
		
		// Defined for a PopupMenu
		employeePopup = new JPopupMenu();
		JMenuItem viewEmp = new JMenuItem("View");
		JMenuItem modifyEmp = new JMenuItem("Modify");
		JMenuItem deleteEmp = new JMenuItem("Delete");
		employeePopup.add(viewEmp);
		employeePopup.add(modifyEmp);
		employeePopup.add(deleteEmp);
		
		// Listener for the Mouse to display a Popup Menu
		employeeTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				
			    // If we do right click with the mouse, then display a Popup Menu
				if(e.getButton() == MouseEvent.BUTTON3) 
				{
					
				   employeePopup.show(employeeTable, e.getX(), e.getY());
				}
				
			}
		});
		
		
		// Action Listener for the View Employee Popup Menu
		viewEmp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// Get from the current row selected, the Employee Id
				int idSel=getEmployeeIdSelected();
				
				if(employeeTableListener != null) 
				{
				   // The View Popup Menu was selected	
				   popupMenu="View";
				   			      
				   // Call the interface which is implemented in the EmployeeFrame Class
				   employeeTableListener.employeeTableEventOccurred(idSel, popupMenu);
				}
			}
	    });
		
		// Action Listener for the Modify Employee Popup Menu
		modifyEmp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			   // Get from the current row selected, the Employee Id
			   int idSel=getEmployeeIdSelected();
				
			   if(employeeTableListener != null) 
			   {
				  // The Modify Popup Menu was selected	
			      popupMenu="Modify";
			      
			      // Call the interface which is implemented in the EmployeeFrame Class
				  employeeTableListener.employeeTableEventOccurred(idSel, popupMenu);
			   }
				
			}
	    });
		
		// Action Listener for the Delete Employee Popup Menu
		deleteEmp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//int row = employeeTable.getSelectedRow();
				
				// Get from the current row selected, the Employee Id
				int idSel=getEmployeeIdSelected();
				
				if(employeeTableListener != null) 
				{
				   // The Delete Popup Menu was selected	
				   popupMenu="Delete";
				   
				   // Call the interface which is implemented in the EmployeeFrame Class
				   employeeTableListener.employeeTableEventOccurred(idSel, popupMenu);
					  
				}
				
			}
	    });
		
		
		// add the object into the Panel
		setLayout(new BorderLayout());
		add(new JScrollPane(employeeTable), BorderLayout.CENTER);
		
	}
	
	// This method: setDataEmployee was defined first in the EmployeeTableModel Class
	public void setDataEmployee(List<Employee> employeeDb) 
	{
		employeeTableModel.setDataEmployee(employeeDb);
	}
	
	// This method is to display the data in the EmployeeTablePanel when the data changes
	// in the EmployeeFormPanel object
	public void refresh()
	{
		employeeTableModel.fireTableDataChanged(); 
	}
	
	public void setEmployeeTableListener(EmployeeTableListener listener) 
	{
		this.employeeTableListener = listener;
	}
	
	public int getEmployeeIdSelected()
	{
		int rowSel = employeeTable.getSelectedRow();
		int idSel=(int)employeeTable.getModel().getValueAt(rowSel, 0);
		
		return idSel;
	}
	
	public String getEmployeeStringColSel(int col) 
	{
	   // declare the variables
	   int rowSel = employeeTable.getSelectedRow();
	   int colSel = col;
	   int idSel;
	   String numberSel;	
	   String firstNameSel; 
	   String middleNameSel;
	   String lastNameSel;
	   String mobilePhoneSel;
	   String jobTitleSel;
	   String departmentSel;
	   String supervisorSel;
	   Gender genderSel;
	   MaritalStatus maritalStaSel;
	   Date dobSel1;
	   Date joinedDateSel1;
	   Date termDateSel1;
	   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
	   String stringSel="";

	   switch(colSel) 
	   { 
	   
	       // Id Number
           case 0 :
              idSel=(int)employeeTable.getModel().getValueAt(rowSel, 0);
              stringSel=Integer.toString(idSel);
           break;
          
	       // Employee Number
	       case 1 :
	          numberSel=(String)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=numberSel;
	          break;
	           
	       // Employee First Name
	       case 2 :
	          firstNameSel=(String)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=firstNameSel;
		      break;
		      
		   // Employee Middle Name
	       case 3 :
	          middleNameSel=(String)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=middleNameSel;
		      break;  
		      
		   // Employee Last Name
	       case 4 :
	          lastNameSel=(String)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=lastNameSel;
		      break;
		   
		   // Employee DOB
	       case 5 :
	          dobSel1=(Date)employeeTable.getModel().getValueAt(rowSel, colSel);
	          String dobSel2 = dateFormat.format(dobSel1);
	          stringSel=dobSel2;
			  break; 
			     
		   // Employee Gender
	       case 6 :
	          genderSel=(Gender)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=genderSel.toString();
		      break; 
		      		   
		   // Marital Status
	       case 7 :
	          maritalStaSel=(MaritalStatus)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=maritalStaSel.toString();
	          break;
		      
		   // Employee Mobile Phone
	       case 8 :
	          mobilePhoneSel=(String)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=mobilePhoneSel;
		      break; 
		      
		   // Employee Joined Date
	       case 9 :
	          joinedDateSel1=(Date)employeeTable.getModel().getValueAt(rowSel, colSel);
	          String joinedDateSel2 = dateFormat.format(joinedDateSel1);
	          stringSel=joinedDateSel2;
			  break; 
		
		   // Employee Terminated Date
	       case 10 :
	          termDateSel1=(Date)employeeTable.getModel().getValueAt(rowSel, colSel);
	          String termDateSel2 = dateFormat.format(termDateSel1);
	          stringSel=termDateSel2;
			  break; 
		      
		   // Employee Job Title
	       case 11 :
	          jobTitleSel=(String)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=jobTitleSel;
		      break; 
		      
		   // Employee Department
	       case 12 :
	          departmentSel=(String)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=departmentSel;
		      break;      
		  
		   // Employee Supervisor
	       case 13 :
	          supervisorSel=(String)employeeTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=supervisorSel;
		      break;    
	    }

		return stringSel;	
	   
	}
	
			
}
