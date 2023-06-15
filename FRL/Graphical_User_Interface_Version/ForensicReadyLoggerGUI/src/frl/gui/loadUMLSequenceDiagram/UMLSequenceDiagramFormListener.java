package frl.gui.loadUMLSequenceDiagram;
import java.util.EventListener;


//Package #6
//Class #3
public interface UMLSequenceDiagramFormListener extends EventListener 
{   
   // Method #7
   public boolean openUmlSeqDiagramImageEventOccurred(UMLSequenceDiagramFormEvent e); 
   
   // Method #8
   public boolean openUmlSeqDiagramTextEventOccurred(UMLSequenceDiagramFormEvent e); 
   
   // Method #9
   public boolean progLanguageComboEventOccurred(UMLSequenceDiagramFormEvent e);
   
   // Method #10
   public boolean saveEventOccurred(UMLSequenceDiagramFormEvent e); 
   
   // Method #11
   public boolean deleteEventOccurred(UMLSequenceDiagramFormEvent e); 
   
   // Method #12
   public boolean cancelEventOccurred(); 
   
}