package frl.gui.generateAspectOrientedFiles;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

import frl.model.user.UserLevel;

//Package #6
//Class #9
public class AOPFileToolBar extends JPanel implements ActionListener 
{

   private static final long serialVersionUID = 1L;
   
   // Define the attributes of the class //	
   private JLabel userLabel;
   private JTextField userField;
   private JLabel userLevelLabel;
   private JTextField userLevelField;
   private AOPFileToolBarListener aOPFileToolBarListener;
   public static JButton addNewButton;

   // Method #1
   public AOPFileToolBar(String user, String menu, UserLevel userLevel) 
   {
        // Creates a border for the toolbar
	    setBorder(BorderFactory.createEtchedBorder());	
	    
		// The Objects for this Panel
		userLabel = new JLabel("User: ");
		userField = new JTextField(10);
		// Set the tooltip text in the JTextField Object 
		userField.setToolTipText("User Name connected to the Forensic-Ready Logger System"); 
		
		userLevelLabel = new JLabel("User Level: ");
		userLevelField = new JTextField(10);
		// Set the tooltip text in the JTextField Object 
		userLevelField.setToolTipText("User Role associated to the User Name");
		
		addNewButton = new JButton("Add New");

		
		// Asign the value for the User field coming for a variable: public UserToolBar (String user) 
		userField.setText(user);
		userField.setEnabled(false);
		
		// Convert from UserLevel to String
		String userLevelStr = userLevel.toString();
				
		userLevelField.setText(userLevelStr);
		userLevelField.setEnabled(false);
		addNewButton.addActionListener(this);
		
		if ((menu.equals("AOP File")))
		{	
			addNewButton.setVisible(true);
			// Set the tooltip text in the JButton Object 
			addNewButton.setToolTipText("Creates New Aspect Oriented Programming Files");
		}	
		else
			addNewButton.setVisible(false);
		
		// Adding the objects to the Layout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(userLabel);
		add(userField);
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(userLevelLabel);
		add(userLevelField);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(addNewButton);
		
   }

   // Method #2
   public void setUserToolBarListener(AOPFileToolBarListener listener)
   {
	   this.aOPFileToolBarListener = listener;	
   }
	
	
   @Override
   // Method #3
   public void actionPerformed(ActionEvent e) 
   {
		//Get which button has been clicked
		JButton clicked = (JButton)e.getSource();
		
		// Add New Button
		if(clicked == addNewButton) 
		{
			if(aOPFileToolBarListener != null) 
			{
		       aOPFileToolBarListener.addNewEventOccurred();
			}
	    }
		
   }	
		
}