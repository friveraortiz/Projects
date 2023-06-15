package frl.process.loadUMLSequenceDiagram;

import java.io.*;


import org.apache.commons.io.FileUtils;

public class WorkFiles 
{
    
   public class GenericExtFilter implements FilenameFilter 
   {
	  private String word;	     
      private String ext;

	  public GenericExtFilter(String word, String ext) 
	  {
	     this.word = word;
	     this.ext  = ext;   
	  }
	           
	  public boolean accept(File dir, String name) 
	  {  
	     if (word == null || word.isEmpty()) 
	        return (name.endsWith(ext));
	     else 
	        if (ext == null || ext.isEmpty()) 
		       return (name.endsWith(word));
	     else
	        if ((word != null && !word.isEmpty()) && (ext == null && !ext.isEmpty())) 
	           return (name.contains(word) && name.endsWith(ext));
	        else 
	           return false;
	  }
   }
   
   public void deleteFilesDirectory(String directory)
   {
      File dir, deleteFile;
      String[] list;
      String fileName="";
      boolean isdeleted;
	   
      dir = new File(directory);
    
      // List out all the files present in the directory
      list = dir.list();
         
      if (list.length == 0) 
         return;
        
      for (String file : list)
      {
   	     fileName   = new StringBuffer(directory).append(File.separator).append(file).toString();
         deleteFile = new File(fileName);
    	 isdeleted  = deleteFile.delete();
    	 //System.out.println("The File: " + fileName + " has been deleted: " + isdeleted);
      }
   }
   
   public void deleteWordFilesDirectory(String directory, String word)
   {
      GenericExtFilter filter;  
      File dir, deleteFile;
      String[] list;
      String fileName="";
      boolean isdeleted;
	   
      filter = new GenericExtFilter(word, null);
      dir    = new File(directory);
    
      // List out all the file name with .png extension
      list = dir.list(filter);
         
      if (list.length == 0) 
         return;
        
      for (String file : list)
      {
   	     fileName   = new StringBuffer(directory).append(File.separator).append(file).toString();
         deleteFile = new File(fileName);
    	 isdeleted  = deleteFile.delete();
    	 //System.out.println("The File: " + fileName + " has been deleted: " + isdeleted);
      }
   }
	 
   public void deletePreviousFileDirectory(String directory, String file1)
   {
      File deleteFile, file2;
      String fileName="";
      boolean isdeleted;
	   
      fileName = new StringBuffer(directory).append(File.separator).append(file1).toString();
      file2    = new File(fileName);
      
      if(file2.exists() && !file2.isDirectory()) 
      { 
         deleteFile = new File(fileName);
         isdeleted  = deleteFile.delete();
         //System.out.println("The Previous File: " + fileName + " has been deleted: " + isdeleted);
      }

   }
   
   public void copyFileDirectory(String sourcePathName, String destPathName) throws Exception
   { 
      String errorMessage1="", errorMessage2="";	   
      File source, dest;
      
      // Build the Source File Information
      source = new File(sourcePathName);
      
      // Build the Destination File Information
      dest   = new File(destPathName);	  
    		  
      // Copying the file from the Source until the Destination
      try 
      {
         FileUtils.copyFile(source, dest);
      } 
      catch (IOException e1) 
      {
	     errorMessage1 = e1.getMessage();
		 errorMessage2 = "Error XXXX: Occurred while copying the Source FileName: " + source + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "to the Destination FileName: " + dest + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 throw new Exception(errorMessage2);	
      }
	   
   }
    
}
