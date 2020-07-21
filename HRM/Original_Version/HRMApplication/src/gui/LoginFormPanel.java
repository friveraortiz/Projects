package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginFormPanel extends JPanel 
{
   // Define the attributes of the class //	
   private JLabel userLabel;
   private JLabel passwordLabel;
   private JTextField userField;
   private JPasswordField passwordField;
   private JButton signInBtn;
   private LoginFormPanelListener loginFormPanelListener;
      
   // Constructor of the LoginFormPanel Class
   public LoginFormPanel() 
   {

		Dimension dim = getPreferredSize();
		
	    // Define the size of the Frame of the class //
		
		// This is the size of the Connection Frame
		dim.width = 400;
		setPreferredSize(dim);
		
		// Define the objects of the class //
		
		// The JLabel object
		userLabel = new JLabel("User: ");
		passwordLabel = new JLabel("Password: ");
		
		// The JTextField object
		userField = new JTextField(10);
		passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');
				
		// The JButton Object
		signInBtn = new JButton("Sign in ->");
		
		// Listener for the signInBtn button
		signInBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				// Obtain the value of the Objects
				String user = userField.getText();
				char[] passwordC = passwordField.getPassword();
				// Convert from Char to String
				String password = String.valueOf(passwordC); 
				
  				// It calls the LoginEvent class and there
				// it launches the HRM Main Menu
				UserFormEvent ev = new UserFormEvent(this, user, new String(password));
				
				if(loginFormPanelListener != null) 
				{
					loginFormPanelListener.loginFormEventOccurred(ev);
				}
				
			}
		});
		
		// Create inner and outer borders for the loginPanel
		Border innerBorder = BorderFactory.createTitledBorder("Connection to the HRM System");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		// Creates all the visual components for the loginPanel
		layoutComponents();
	
   }
   
   public void layoutComponents() 
   {
	    // Define the positions of the objects in the Panel //
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
			
		// First row //
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;

	
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(userLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(userField, gc);
		
	    // Second row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
	    add(passwordLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
	    add(passwordField, gc);
		
		
		// Last row //
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2.0;
	    
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(signInBtn, gc);
	   
   }
   
   public void setLoginFormPanelListener(LoginFormPanelListener listener) 
   {
	   this.loginFormPanelListener = listener;
   }
   
   
}