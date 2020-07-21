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
import model.Currency;
import model.Status;
import model.Transportation;
import model.TravelRequest;

public class TravelRequestTablePanel extends JPanel 
{
	private JTable travelRequestTable;
	private TravelRequestTableModel travelRequestTableModel;
	
	// Defined for a PopupMenu
	private JPopupMenu travelRequestPopup;
	
	// Listener for the TravelRequestTable
	TravelRequestTableListener travelRequestTableListener;
	
	// PopupMenu selected
	private String popupMenu="";
	
	
	// Create the constructor of the class
	@SuppressWarnings("null")
	public TravelRequestTablePanel(String [][] subModules) 
	{
		
		// Define the Object travelRequestTable
		travelRequestTableModel = new TravelRequestTableModel();
		travelRequestTable = new JTable(travelRequestTableModel);
		
		// Defined for a PopupMenu
		travelRequestPopup = new JPopupMenu();
		JMenuItem viewTra = new JMenuItem("View");
		JMenuItem modifyTra = new JMenuItem("Modify");
		JMenuItem deleteTra = new JMenuItem("Delete");
		
		travelRequestPopup.add(viewTra);
		travelRequestPopup.add(modifyTra);
		travelRequestPopup.add(deleteTra);
		
        ////////////////////////////////////////////////////////////////
		// If the user connected has a user_level admin or a manager, 
		// add the change status submenu
		////////////////////////////////////////////////////////////////
        
		String module = null;
		String value = null;
		String subModule = null;
		
        for(int row=0; row<subModules.length; row++)
        {
           
            for(int col=0; col <subModules[row].length;col++)
            {
               value = subModules[row][col];
               
         	  if(col == 0)
              {
        	     if(value != null && !value.isEmpty() && !value.trim().isEmpty())	 
        		    if (value.equals("Travel Requests"))
                       module = value;  
              }
              else 
                 if(col == 1)
                 {
            	    if(value != null && !value.isEmpty() && !value.trim().isEmpty())	 
            		   if (value.equals("Change Status"))
                          subModule = value;  
                 }
            }
        }
        
        // Validate whether or not to add the Change Status Popup Menu
        if(subModule != null && !subModule.isEmpty() && !subModule.trim().isEmpty())
        {
		   JMenuItem changeStatusTra = new JMenuItem("Change Status");
		   travelRequestPopup.add(changeStatusTra);
        
		   // Action Listener for the Change Status Popup Menu
		   changeStatusTra.addActionListener(new ActionListener() 
		   {
		      @Override
			   public void actionPerformed(ActionEvent arg0) 
			   {
				
			      // Get from the current row selected, the Employee Id
			      int idSel=getTravelRequestIdSelected();
				
			      if(travelRequestTableListener != null) 
			      {
				     // The Change Status Popup Menu was selected	
			         popupMenu="ChangeStatus";
			      
			         // Call the interface which is implemented in the TravelRequestFrame Class
				     travelRequestTableListener.travelRequestTableEventOccurred(idSel, popupMenu);
			      }
			   }
	       });
        }
		
		
		// Listener for the Mouse to display a Popup Menu
		travelRequestTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{	
			    // If we do right click with the mouse, then display a Popup Menu
				if(e.getButton() == MouseEvent.BUTTON3) 
				{
					
				   travelRequestPopup.show(travelRequestTable, e.getX(), e.getY());
				}
				
			}
		});
		
		
		// Action Listener for the View Travel Request Popup Menu
		viewTra.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// Get from the current row selected, the Travel Request Id
				int idSel=getTravelRequestIdSelected();
				
				if(travelRequestTableListener != null) 
				{
				   // The View Popup Menu was selected	
				   popupMenu="View";
				   			      
				   // Call the interface which is implemented in the TravelRequestFrame Class
				   travelRequestTableListener.travelRequestTableEventOccurred(idSel, popupMenu);
				}
			}
	    });
		
		// Action Listener for the Modify Travel Request Popup Menu
		modifyTra.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			   // Get from the current row selected, the Travel Request Id
			   int idSel=getTravelRequestIdSelected();
				
			   if(travelRequestTableListener != null) 
			   {
				  // The Modify Popup Menu was selected	
			      popupMenu="Modify";
			      
			      // Call the interface which is implemented in the TravelRequestFrame Class
				  travelRequestTableListener.travelRequestTableEventOccurred(idSel, popupMenu);
			   }
				
			}
	    });
		
		// Action Listener for the Delete Travel Request Popup Menu
		deleteTra.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int row = travelRequestTable.getSelectedRow();
				
				// Get from the current row selected, the Travel Request Id
				int idSel=getTravelRequestIdSelected();
				
				if(travelRequestTableListener != null) 
				{
				   // The Delete Popup Menu was selected	
				   popupMenu="Delete";
				   
				   // Call the interface which is implemented in the TravelRequestFrame Class
				   travelRequestTableListener.travelRequestTableEventOccurred(idSel, popupMenu);
					  
				}
				
			}
	    });
		
		// add the object into the Panel
		setLayout(new BorderLayout());
		add(new JScrollPane(travelRequestTable), BorderLayout.CENTER);
		
	}
	
	// This method: setDataEmployee was defined first in the travelRequestTableModel Class
	public void setDataTravelRequest(List<TravelRequest> travelRequestDb) 
	{
		travelRequestTableModel.setDataTravelRequest(travelRequestDb);
	}
	
	// This method is to display the data in the TravelRequestTablePanel when the data changes
	// in the TravelRequestFormPanel object
	public void refresh()
	{
		travelRequestTableModel.fireTableDataChanged(); 
	}
	
	public void setTravelRequestTableListener(TravelRequestTableListener listener) 
	{
		this.travelRequestTableListener = listener;
	}
	
	public int getTravelRequestIdSelected()
	{
		int rowSel = travelRequestTable.getSelectedRow();
		int idSel=(int)travelRequestTable.getModel().getValueAt(rowSel, 0);
		
		return idSel;
	}
	
	public String getTravelRequestStringColSel(int col) 
	{
	   // declare the variables
	   int rowSel = travelRequestTable.getSelectedRow();
	   int colSel = col;
	
	   int idSel;
	   String numberSel;	
	   String employeeSel; 
	   Transportation transportationSel;
	   String purposeSel;
	   String travelFromSel;
	   String travelToSel;
	   Date travelDateSel1;
	   Date returnDateSel1;
	   String notesSel;
	   Currency currencySel;
	   float totalFundingSel;
	   Status statusSel;
	   String statusNotesSel;
	   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
	   String stringSel="";

	   switch(colSel) 
	   { 
	   
	       // Id Number
           case 0 :
              idSel=(int)travelRequestTable.getModel().getValueAt(rowSel, 0);
              stringSel=Integer.toString(idSel);
           break;
          
	       // Travel Request Number
	       case 1 :
	          numberSel=(String)travelRequestTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=numberSel;
	          break;
	           
	       // Employee
	       case 2 :
	          employeeSel=(String)travelRequestTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=employeeSel;
		      break;
		      
		   // Transportation
	       case 3 :
	          transportationSel=(Transportation)travelRequestTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=transportationSel.toString();
		      break;   
		      
		   // Purpose
	       case 4 :
	          purposeSel=(String)travelRequestTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=purposeSel;
		      break;
		   
		   // Travel From
	       case 5 :
		      travelFromSel=(String)travelRequestTable.getModel().getValueAt(rowSel, colSel);
		      stringSel=travelFromSel;
			  break;
			     
		   // Travel To
	       case 6 :
		      travelToSel=(String)travelRequestTable.getModel().getValueAt(rowSel, colSel);
		      stringSel=travelToSel;
			  break;
		      		   
		   // Travel Date
	       case 7 :
		       travelDateSel1=(Date)travelRequestTable.getModel().getValueAt(rowSel, colSel);
		       String travelDateSel2 = dateFormat.format(travelDateSel1);
		       stringSel=travelDateSel2;
			   break; 
		      
		   // Return Date
	       case 8 :
		       returnDateSel1=(Date)travelRequestTable.getModel().getValueAt(rowSel, colSel);
		       String returnDateSel2 = dateFormat.format(returnDateSel1);
		       stringSel=returnDateSel2;
			   break; 
		      
		   // Notes
	       case 9 :
		       notesSel=(String)travelRequestTable.getModel().getValueAt(rowSel, colSel);
		       stringSel=notesSel;
			   break; 
		
		   // Currency
	       case 10 :
		       currencySel=(Currency)travelRequestTable.getModel().getValueAt(rowSel, colSel);
		       stringSel=currencySel.toString();
			   break;  
		      
		   // Total Funding
	       case 11 :
	          totalFundingSel=(float)travelRequestTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=Float.toString(totalFundingSel);
		      break; 
		      
 		   // Status
	       case 12 :
	          statusSel=(Status)travelRequestTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=statusSel.toString();
		      break;      
		  
		   // Status Notes
	       case 13 :
	          statusNotesSel=(String)travelRequestTable.getModel().getValueAt(rowSel, colSel);
	          stringSel=statusNotesSel;
		      break;    
	    }

		return stringSel;	
	   
	}
	
			
}
