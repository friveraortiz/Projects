package frl.gui.annotateUMLSequenceDiagram;
import java.util.EventListener;

public interface AnnotateUMLSequenceDiagramFormListener extends EventListener 
{   
   public boolean userNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev);
   
   public boolean methodNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev);
   
   public boolean annotationTypeEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev);
   
   public boolean parameterNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev);
   
   public boolean attributeNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev);
   
   public boolean valueNameComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev);
   
   public boolean returnTypeValueComboEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev);
   
   public boolean addAnnotationEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev); 
   
   public boolean deleteAnnotationEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev); 
	
   public boolean saveEventOccurred(AnnotateUMLSequenceDiagramFormEvent ev); 

   public boolean cancelEventOccurred(); 
   
}