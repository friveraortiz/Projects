package frl.gui.generateLoggingInstructions;
import java.util.EventListener;

//Package #6
//Class #3
public interface AOPFinalFileFormListener extends EventListener 
{   
   // Method #1
   public boolean progLanguageComboEventOccurred(AOPFinalFileFormEvent ev);
      
   // Method #2
   public boolean userNameComboEventOccurred(AOPFinalFileFormEvent ev);
   
   // Method #3
   public boolean methodNameComboEventOccurred(AOPFinalFileFormEvent ev);
   
   // Method #4
   public boolean parameterNameComboEventOccurred(AOPFinalFileFormEvent ev);
   
   // Method #5
   public boolean attributeNameComboEventOccurred(AOPFinalFileFormEvent ev);
   
   // Method #6
   public boolean openLogDirectoryEventOccurred(AOPFinalFileFormEvent ev); 
   
   // Method #7
   public boolean openAOPDirectoryEventOccurred(AOPFinalFileFormEvent ev); 
   
   // Method #8
   public boolean generateEventOccurred(AOPFinalFileFormEvent ev); 
   
   // Method #9
   public boolean cancelEventOccurred(); 
   
}