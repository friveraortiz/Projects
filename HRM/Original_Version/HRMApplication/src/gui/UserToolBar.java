package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.UserLevel;

public class UserToolBar extends JPanel implements ActionListener 
{

   // Define the attributes of the class //	
   private JLabel userLabel;
   private JTextField userField;
   private JLabel userLevelLabel;
   private JTextField userLevelField;
   private UserToolBarListener userToolBarListener;
   public static JButton addNewButton;
	
   public UserToolBar (String user, String menu, UserLevel userLevel) 
   {
        // Creates a border for the toolbar
	    setBorder(BorderFactory.createEtchedBorder());	
	    
		// The Objects for this Panel
		userLabel = new JLabel("User: ");
		userField = new JTextField(10);
		userLevelLabel = new JLabel("User Level: ");
		userLevelField = new JTextField(10);
		addNewButton = new JButton("Add New");
		
		// Asign the value for the User field coming for a variable: public UserToolBar (String user) 
		userField.setText(user);
		userField.setEnabled(false);
		
		// Convert from UserLevel to String
		String userLevelStr = userLevel.toString();
				
		userLevelField.setText(userLevelStr);
		userLevelField.setEnabled(false);
		addNewButton.addActionListener(this);
		
		if ((menu.equals("Employees") | menu.equals("Users")) | menu.equals("Travel Requests"))
			addNewButton.setVisible(true);
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
	
	public void setUserToolBarListener(UserToolBarListener listener)
	{
	   this.userToolBarListener = listener;	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//Get which button has been clicked
		JButton clicked = (JButton)e.getSource();
		
		// Add New Button
		if(clicked == addNewButton) 
		{
			if(userToolBarListener != null) 
			{
		       userToolBarListener.addNewEventOccurred();
			}
	    }
		
	}	
		
}