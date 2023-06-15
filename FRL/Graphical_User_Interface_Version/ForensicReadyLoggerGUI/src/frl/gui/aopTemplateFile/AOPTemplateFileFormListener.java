package frl.gui.aopTemplateFile;
import java.util.EventListener;

public interface AOPTemplateFileFormListener extends EventListener 
{
   // Method #1	
   public boolean aOPTemplateFileSaveEventOccurred(AOPTemplateFileFormEvent e); 
   
   // Method #2
   public boolean aOPTemplateFileDeleteEventOccurred(AOPTemplateFileFormEvent e); 
   
   // Method #3
   public boolean programmingLanguageComboEventOccurred(String databaseConfigFile, AOPTemplateFileFormEvent e); 
   
   // Method #4
   public boolean openPathButtonEventOccurred(AOPTemplateFileFormEvent e); 
   
}