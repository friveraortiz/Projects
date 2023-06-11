package main;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomLinesFile 
{
    static ArrayList<Activity> activityList = new ArrayList<Activity>();
    static ArrayList<Integer> idList = new ArrayList<Integer>();
	static Activity activityRecord;
	static String module="", description="";
	static int id=0, minAct=0, maxAct=0;
	   
	public static void readTextFile() 
	{
	   String errorMessage1="", errorMessage2="", textLine="", textFileName="", idStr="";

	   String[] fields;

	   File file;	
	   BufferedReader br = null;
	   
	   // Initialize Variables
	   textFileName="/Users/fannyriveraortiz/eclipse-workspace/OH_Activities.txt";
	   file = new File(textFileName);
	   activityList.clear();
	   
       try 
       {
	      br = new BufferedReader(new FileReader(file));
	   } 
       catch (FileNotFoundException e1) 
       {
          errorMessage1 = e1.getMessage();
    	  errorMessage2 = "Error XXXX: Occurred while opening the Text File : " + System.lineSeparator();
    	  errorMessage2 = errorMessage2 + textFileName + System.lineSeparator();
    	  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	   }
       
       try 
       {
	      while((textLine=br.readLine()) != null)
		  {
		     //System.out.println("Line: " + textLine);
		     
		     // Separate each of the 3 fields by a comma delimiter
		     fields = textLine.split(",");
		     
		     idStr       = fields[0];
		     module      = fields[1];
		     description = fields[2];
		     
		     // Convert from String DataType to Integer DataType
		     id = Integer.parseInt(idStr);
		    		
		     /*
		     System.out.println("Activity Id : " + id);
		     System.out.println("Module      : " + module);
		     System.out.println("Description : " + description);
		     */
		     
		     // Store the 3 fields into a Activity Record
		     activityRecord = new Activity(id, module, description); 
		     
		     // Store the record into an ArrayList
		     activityList.add(activityRecord);
		     idList.add(id);
		     
		     //System.out.println("Array List Line:" + activityList);
		  }
	      
	      System.out.println("The Text File was loaded and stored: " + textFileName);
	   } 
       catch (IOException e2) 
       {
          errorMessage1 = e2.getMessage();
     	  errorMessage2 = "Error XXXX: Occurred while reading the lines of the Text File : " + System.lineSeparator();
     	  errorMessage2 = errorMessage2 + textFileName + System.lineSeparator();
     	  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	   }
       
       try 
       {
	      br.close();
	   } 
       catch (IOException e3) 
       {
          errorMessage1 = e3.getMessage();
     	  errorMessage2 = "Error XXXX: Occurred while closing the Text File : " + System.lineSeparator();
     	  errorMessage2 = errorMessage2 + textFileName + System.lineSeparator();
     	  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	   }

	}
	
	public static void printArrayList()
	{
	   int i;	
	   
	   for(i = 0; i < activityList.size(); i++) 
	   {
	      id          = activityList.get(i).getId();
	      module      = activityList.get(i).getModule();
	      description = activityList.get(i).getDescription();

	      
	      System.out.println("Counter     : " + i);
		  System.out.println("Id          : " + id);
		  System.out.println("Module      : " + module);
		  System.out.println("Description : " + description);
		  
	   }
	}
	
	public static void obtainMinMaxId()
	{
	   
	   minAct = Collections.min(idList);
	   maxAct = Collections.max(idList);
	   
	   System.out.println("From the Text File");
	   System.out.println("This is the Minimum Number Activity: " + minAct);
	   System.out.println("This is the Maximum Number Activity: " + maxAct);
	   
	}
	
	public static int generateRandomNumber(int minNumber, int maxNumber)
	{
	   Random rand;
	   int randomNumber=0;
		
	   rand = new Random();

	   // Generate a Random Number
	   randomNumber = rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
	   
	   return randomNumber;
	   
	}
	
	public static void getRandomActivities(int randomTotalAct)
	{
	   int i=1, j=0, randomActNum=0;
	   String line="";
	   
	   while (i <= randomTotalAct) 
	   {
          // Generate a random Activity Number 
	      randomActNum = generateRandomNumber(minAct, maxAct);
		  System.out.println("Random Activity Number: " + randomActNum);
		  
		  j = randomActNum - 1;
		  
		  // Obtain the data from this Random Activity Number
	      id          = activityList.get(j).getId();
	      module      = activityList.get(j).getModule();
	      description = activityList.get(j).getDescription();
	      
		  line = id + "," + module + "," + description;
		  
	      //System.out.println("Random Activity : " + i);
		  System.out.println("Id          : " + id);
		  System.out.println("Module      : " + module);
		  System.out.println("Description : " + description);
		  System.out.println("Line        : " + line);
	      
		  i++;
	   }
	   
	}

	public static void main(String[] args) 
	{
	   int randomTotalAct=0;	
    
	   // Read a Text File and store it into an ArrayList
       readTextFile();
       
       // Print the ArrayList
       //printArrayList();
       
       // Obtain the Minimum and Maximum Values related to the Activity Id from the ArrayList
       obtainMinMaxId();
       
       // Get a random number of total activities
       randomTotalAct = generateRandomNumber(1, 6);
       
	   System.out.println("We are going to get: " + randomTotalAct + " Total Random Activities");
	   
	   getRandomActivities(randomTotalAct);
	   
	   //randomActNum = generateRandomNumber(minAct, maxAct);
       
	   //System.out.println("Random Activity Number: " + randomActNum);
	}

}
