package frl.gui.configFile;
import java.util.EventListener;

//Package #6
//Class #3
public interface ConfigFileFormListener extends EventListener 
{

   // Method #1
   public boolean configFileFormOpenProjectNameEventOccurred(ConfigFileFormEvent e); 
   
   // Method #2
   public boolean configFileFormOpenJarFileEventOccurred(ConfigFileFormEvent e);
   
   // Method #3
   public boolean configFileFormOpenProjInputDirEventOccurred(ConfigFileFormEvent e); 
   
   // Method #4
   public boolean configFileFormOpenProjOutputDirEventOccurred(ConfigFileFormEvent e); 
   
   // Method #5
   public boolean configFileFormProgLanguageComboEventOccurred(ConfigFileFormEvent e);
   
   // Method #6
   public boolean configFileFormSaveEventOccurred(ConfigFileFormEvent e); 
   
   // Method #7
   public boolean configFileFormDeleteEventOccurred(ConfigFileFormEvent e); 
   
   // Method #8
   public boolean configFileFormCancelEventOccurred(); 
   
}