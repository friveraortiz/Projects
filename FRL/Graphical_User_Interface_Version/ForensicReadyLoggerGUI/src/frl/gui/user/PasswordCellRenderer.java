package frl.gui.user;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

//Package #6
//Class #1
public class PasswordCellRenderer extends JPasswordField implements TableCellRenderer 
{

	private static final long serialVersionUID = 1L;

	// Method #1
	public PasswordCellRenderer() 
    {
        super();
        // This displays astericks in fields since it is a password.
        // It does not affect the actual value of the cell.
        this.setText("filler123");
    }
	
	// Method #2
    public Component getTableCellRendererComponent
    (
       JTable arg0,
       Object arg1,
       boolean arg2,
       boolean arg3,
       int arg4,
       int arg5
    ) 
    {
        return this;
    }
    
}

