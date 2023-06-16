package frl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;


import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;

public aspect FrlStart
{
   String projectOutputDir="/Users/fannyriveraortiz/eclipse-workspace/Directories/Output/";
   String umlSeqDiagTextFileName1="IncidentSequenceDiagram.txt";
   String umlSeqDiagPngFileName1="IncidentSequenceDiagram.png";
   
   String newLine1="%n";
   String startUMLSeqDiagram="@startuml";
   String endUMLSeqDiagram="@enduml";

   String umlSeqDiagTextFile=projectOutputDir + umlSeqDiagTextFileName1;
   String umlSeqDiagPngFile=projectOutputDir  + umlSeqDiagPngFileName1;
   String method, errorMessage, content="";
   
   int lines=0;
   String umlSeqDiagTextFileName2="IncidentSequenceDiagramTEMPORAL.txt";
   String umlSeqDiagTextFile2=projectOutputDir + umlSeqDiagTextFileName2;
   File inputFile  = new File(umlSeqDiagTextFile);
   File outputFile = new File(umlSeqDiagTextFile2);
   
   /*Evaluation Start */
   long time1, time2, duration;
   /*Evaluation End */
   
   pointcut start(): 
   	                      execution(void gui.HRMApp.main(..));

   after(): start()
   {
	  System.out.println("Message FRL: Welcome to the Forensic-Ready Logger ...");
	  	   
	  // Delete UML Sequence Diagram previous Files
	  deleteFiles();
	  
	  /* Evaluation Start */
	  time1 = System.currentTimeMillis();
	  /* Evaluation End */

	  // Create a new UML Sequence Diagram Text File
	  createSeqDiagramTextFile(umlSeqDiagTextFile);
	  
	  /* Evaluation Start */
	  time2 = System.currentTimeMillis();
	  duration = time2 - time1;
	  System.out.println("Message FRL: The UML Sequence Diagram Files were FIRST Created in : " + duration + " milliseconds");      
	  /* Evaluation End */
   }
   
   pointcut end(): 
   	                      call(void exit(..));

   before(): end()
   {
	
      /* Evaluation Start */
	  time1 = System.currentTimeMillis(); 
	  /* Evaluation End */ 
	   
      // Create and update the UML Sequence Diagram Files 
      updateFiles();
      
	  /* Evaluation Start */
	  time2 = System.currentTimeMillis();
	  duration = time2 - time1;
	  System.out.println("Message FRL: The UML Sequence Diagram Files were FINALLY Created in : " + duration + " milliseconds");      
	  /* Evaluation End */
	  
      System.out.println("Message FRL: Good Bye to the Forensic-Ready Logger.");
      
   }
      
   public void deleteSeqDiagramFile(String inputFile)
   {
      File file = new File(inputFile);
	   
      // Delete an existing UML Sequence Diagram File
	  if(file.exists() && !file.isDirectory()) 
	     file.delete();
	  
   }
   
   public void deleteFiles()
   {
      // Delete UML Sequence Diagram Text File
	  deleteSeqDiagramFile(umlSeqDiagTextFile);
		  
	  // Delete UML Sequence Diagram Png File
      deleteSeqDiagramFile(umlSeqDiagPngFile); 
      
   }
   
   public void writeTextFile(String textFile, String content)
   {
      FileWriter fw = null;
      BufferedWriter bw;
      File file;

      try
      {
         // Create a new textFile
         file = new File(textFile);

         // Validate if the textFile exists
         if (!file.exists()) 
         {
            file.createNewFile();
            fw = new FileWriter(file.getAbsoluteFile());
         }
         else
            fw = new FileWriter(file, true);
         
         bw = new BufferedWriter(fw);

         // Write in the textFile
         fw.append(content);

         // Close the textFile
         bw.close();
         
      }
      catch(Exception e)
      {
         errorMessage = e.getMessage();
         System.out.println("Error FRL: Occurred while opening the UML Sequence Diagram Text File: "+textFile + "Error Message: " + errorMessage);
      }
            
   }  
   
   public void createSeqDiagramTextFile(String textFile)
   {
 
      content = "";

      // Plant UML Instructions
      content = startUMLSeqDiagram + newLine1;
      content = content + "skinparam backgroundColor #EEEBDC"   + newLine1;
      content = content + "skinparam sequence {"                + newLine1;
      content = content + "ArrowColor DarkBlue"                 + newLine1;
      content = content + "ActorBorderColor DarkBlue"           + newLine1;
      content = content + "LifeLineBorderColor blue"            + newLine1;
      content = content + "LifeLineBackgroundColor DarkBlue"    + newLine1;
      content = content + "ParticipantBorderColor DarkBlue"     + newLine1;
      content = content + "ParticipantBackgroundColor DarkBlue" + newLine1;
      content = content + "ParticipantFontName Impact"          + newLine1;
      content = content + "ParticipantFontSize 17"              + newLine1;
      content = content + "ParticipantFontColor #A9DCDF"        + newLine1;
      content = content + "ActorBackgroundColor DarkBlue"       + newLine1;
      content = content + "ActorFontColor DarkBlue"             + newLine1;
      content = content + "ActorFontSize 17"                    + newLine1;
      content = content + "ActorFontName Aapex"                 + newLine1;
      content = content + "}"                                   + newLine1;
         
      content = String.format(content);
      
      // Write the Plant Uml Lines into the UML Sequence Diagram Text File
	  writeTextFile(textFile, content);
 
   }

   public void createSeqDiagramPngFile(String textFile)
   {
      File file1 = new File(textFile); 
	  SourceFileReader reader = null;
	  List<GeneratedImage> list = null;
	  
	  // Create the Reader of the UML Sequence Diagram Text File
	  try 
	  {
	     reader = new SourceFileReader(file1);
	  } 
	  catch (IOException e) 
	  {
	     errorMessage = e.getMessage();
	     System.out.println("Error FRL: Occurred while reading the UML Sequence Diagram Text File: "+textFile + "Error Message: " + errorMessage);
	  }
	   
	  // Generate the list of images using the Sequence Diagram Text File
	  try 
	  {
	     list = reader.getGeneratedImages();
	  } 
	  catch (IOException e) 
	  {
	     errorMessage = e.getMessage();
		 System.out.println("Error FRL: Occurred while generating the UML Sequence Diagram Png File" + "Error Message: " + errorMessage);
	  }
	   
	  // Generated the UML Sequence Diagram Png File
	  file1 = list.get(0).getPngFile();
 	   
   }
   
   public void countLinesTextFile()
   {
		try 
		{
			LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(umlSeqDiagTextFile));
			lineNumberReader.skip(Long.MAX_VALUE);
			lines = lineNumberReader.getLineNumber();
			lineNumberReader.close();
		} 
		catch (Exception e1) 
		{
		   errorMessage = e1.getMessage();
		   System.out.println("Error FRL: Occurred while counting the lines of the UML Sequence Diagram Text File" + "Error Message: " + errorMessage);
		}		

   }
   
   public  void deleteLineTextFile()
   {
	  String line="";
	  int c=0;
	  
	  try 
	  {
	     try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		    		
		    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) 
		 {
		
		    while ((line = reader.readLine()) != null) 
		    {
		       c++;
		       
		       if(c != lines)
		       {	
		          if (!line.equals(endUMLSeqDiagram)) 
		          {
		             writer.write(line);
		             writer.newLine();
		          }
		       }
		       else
		       {		
		          writer.write(line);
	              writer.newLine();
		       }   
		    }
		 }
		 catch (Exception e1) 
		 {
		    errorMessage = e1.getMessage();
			System.out.println("Error FRL: Occurred while writing a new UML Sequence Diagram Text File without double @enduml instructions." + "Error Message: " + errorMessage);
		 }

		 if (inputFile.delete()) 
		 {
		    // Rename the output file to the input file
		    if (!outputFile.renameTo(inputFile)) 
		    {
		       errorMessage = "Error FRL: Ocurred while renaming the File: " + umlSeqDiagTextFile2 + " to " + umlSeqDiagTextFile;	
		       System.out.println(errorMessage);
		       throw new Exception(errorMessage);
		    }
		 } 
		 else
		 {
	        errorMessage = "Error FRL: Ocurred while deleting the File: " + umlSeqDiagTextFile;
		    System.out.println(errorMessage);
		    throw new Exception(errorMessage);
		 }
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
	     System.out.println("Error FRL: Occurred while deleting or renaming the new UML Sequence Diagram Text File without double @enduml instructions." + "Error Message: " + errorMessage);
      }
   }
   
   public void updateFiles()
   {
	   
	  // Update the UML Sequence Diagram Text File with the @endUml Instruction  
      content = endUMLSeqDiagram + newLine1;
      content = String.format(content);
      
      // Write the contents into the UML Sequence Diagram Text File
      writeTextFile(umlSeqDiagTextFile, content);
      
	  // Conting the number of lines of the UML Sequence Diagram Text File
	  countLinesTextFile();
	  
	  // Deleting the repeated @enduml instructions
	  deleteLineTextFile();
      
      // Create the UML Sequence Diagram Png File
   	  createSeqDiagramPngFile(umlSeqDiagTextFile);
	      
  	  // Print on the screen Informative Messages
	  System.out.println("Message FRL: UML Sequence Diagram Text File created: "+umlSeqDiagTextFile);
	  System.out.println("Message FRL: UML Sequence Diagram PNG File created : "+umlSeqDiagPngFile);
      //System.out.println("Message FRL: Good Bye to the Forensic-Ready Logger.");
	  
   }  
   
}
