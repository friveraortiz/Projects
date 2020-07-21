package controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import gui.TravelRequestFormEvent;
import model.Currency;
import model.Employee;
import model.Status;
import model.Transportation;
import model.TravelRequest;
import model.TravelRequestDatabase;
import model.UserLevel;

// This class is an intermediary between the GUI and the Model Classes
// Transform the data from the GUI in a way that the Model can understand it, because 
// Model is a Data Model to store data into a Database
public class TravelRequestController
{
	// Declare a new object for the TravelRequestDatabase class
	TravelRequestDatabase trDb = new TravelRequestDatabase();

	// Define the method: List getTravelRequests which was defined in the TravelRequestDatabase Class
	public List<TravelRequest> getTravelRequests()
	{
		return trDb.getTravelRequests();
	}
	
	// Define the method: addTravelRequest which was defined in the TravelRequestDatabase Class
	public void addTravelRequest(TravelRequestFormEvent ev) 
	{
	     TravelRequest travelRequest;
		
		 // Define the variables for this method
		 int id = ev.getId();
	     String number = ev.getNumber();	
	     String employee = ev.getEmployee();
	     String transportation = ev.getTransportation();
	     String purpose = ev.getPurpose();
	     String travelFrom = ev.getTravelFrom();
	     String travelTo = ev.getTravelTo();
		 Date travelDate = ev.getTravelDate();
		 Date returnDate = ev.getReturnDate();
		 String notes = ev.getNotes();
		 String currency = ev.getCurrency();
		 float totalFunding = ev.getTotalFunding();
		 String status = ev.getStatus();
		 String statusNotes = ev.getStatusNotes();
		 
	     Transportation transportationCat = null;
	     
	     // Define the possible values for TransportationCat
	     if(transportation.equals("Airplane")) 
	     {
	        transportationCat = Transportation.Airplane;
	     }
	     else if(transportation.equals("Train")) 
		 {
		    transportationCat = Transportation.Train;
		 }
		 else if(transportation.equals("Taxi")) 
		 {
	       transportationCat = Transportation.Taxi;
		 }
		 else if(transportation.equals("Own Vehicle")) 
		 {
		   transportationCat = Transportation.OwnVehicle;
		 }
		 else if(transportation.equals("Rented Vehicle")) 
		 {
		   transportationCat = Transportation.RentedVehicle;
		 }
	     
	     Currency currencyCat = null;
	     
	     // Define the possible values for CurrencyCat
	     if(currency.equals("Euros")) 
	     {
	        currencyCat = Currency.Euros;
	     }
	     else if(currency.equals("Pound Sterling")) 
		 {
		    currencyCat = Currency.PoundSterling;
		 }
		 else if(currency.equals("American Dollars")) 
		 {
	       currencyCat = Currency.AmericanDollars;
		 }
		 else if(currency.equals("Canadian Dollars")) 
		 {
		   currencyCat = Currency.CanadianDollars;
		 }
		 else if(currency.equals("Mexican Pesos")) 
		 {
		   currencyCat = Currency.MexicanPesos;
		 }
	    	
	     Status statusCat = null;
	     
	     // Define the possible values for StatusCat
	     if(status.equals("Pending")) 
	     {
	        statusCat = Status.Pending;
	     }
	     else if(status.equals("Approved")) 
		 {
		    statusCat = Status.Approved;
		 }
		 else if (status.equals("Rejected")) 
		 {
	       statusCat = Status.Rejected;
		 }
	    
	     if (id == 0)
	     {
	    	 travelRequest = new TravelRequest (number, employee, transportationCat, 
	    				purpose, travelFrom, travelTo, travelDate, returnDate, 
	    				notes, currencyCat, totalFunding, statusCat, statusNotes
			    	  );
	     }
	     else
	     {
	         travelRequest = new TravelRequest (id, number, employee, transportationCat, 
	    				purpose, travelFrom, travelTo, travelDate, returnDate, 
	    				notes, currencyCat, totalFunding, statusCat, statusNotes
		    	  );
	     }
	    	     
	     trDb.addTravelRequest(travelRequest);
	     
	}
	
	// Define the method: delete a Travel Request
	public void deleteTravelRequest(int index) 
	{
		trDb.deleteTravelRequest(index);
	}
	
   public void save(TravelRequestFormEvent ev) throws SQLException 
   {
	     TravelRequest travelRequest;
			
		 // Define the variables for this method
		 int id = ev.getId();
	     String number = ev.getNumber();	
	     String employee = ev.getEmployee();
	     String transportation = ev.getTransportation();
	     String purpose = ev.getPurpose();
	     String travelFrom = ev.getTravelFrom();
	     String travelTo = ev.getTravelTo();
		 Date travelDate = ev.getTravelDate();
		 Date returnDate = ev.getReturnDate();
		 String notes = ev.getNotes();
		 String currency = ev.getCurrency();
		 float totalFunding = ev.getTotalFunding();
		 String status = ev.getStatus();
		 String statusNotes = ev.getStatusNotes();
		 
	     Transportation transportationCat = null;
	     
	     // Define the possible values for TransportationCat
	     if(transportation.equals("Airplane")) 
	     {
	        transportationCat = Transportation.Airplane;
	     }
	     else if(transportation.equals("Train")) 
		 {
		    transportationCat = Transportation.Train;
		 }
		 else if(transportation.equals("Taxi")) 
		 {
	       transportationCat = Transportation.Taxi;
		 }
		 else if(transportation.equals("Own Vehicle")) 
		 {
		   transportationCat = Transportation.OwnVehicle;
		 }
		 else if(transportation.equals("Rented Vehicle")) 
		 {
		   transportationCat = Transportation.RentedVehicle;
		 }
	     
	     Currency currencyCat = null;
	     
	     // Define the possible values for CurrencyCat
	     if(currency.equals("Euros")) 
	     {
	        currencyCat = Currency.Euros;
	     }
	     else if(currency.equals("Pound Sterling")) 
		 {
		    currencyCat = Currency.PoundSterling;
		 }
		 else if(currency.equals("American Dollars")) 
		 {
	       currencyCat = Currency.AmericanDollars;
		 }
		 else if(currency.equals("Canadian Dollars")) 
		 {
		   currencyCat = Currency.CanadianDollars;
		 }
		 else if(currency.equals("Mexican Pesos")) 
		 {
		   currencyCat = Currency.MexicanPesos;
		 }
	    	
	     Status statusCat = null;
	     
	     // Define the possible values for StatusCat
	     if(status.equals("Pending")) 
	     {
	        statusCat = Status.Pending;
	     }
	     else if(status.equals("Approved")) 
		 {
		    statusCat = Status.Approved;
		 }
		 else if (status.equals("Rejected")) 
		 {
	       statusCat = Status.Rejected;
		 }
	    
	     if (id == 0)
	     {
	    	 travelRequest = new TravelRequest (number, employee, transportationCat, 
	    				purpose, travelFrom, travelTo, travelDate, returnDate, 
	    				notes, currencyCat, totalFunding, statusCat, statusNotes
			    	  );
	     }
	     else
	     {
	         travelRequest = new TravelRequest (id, number, employee, transportationCat, 
	    				purpose, travelFrom, travelTo, travelDate, returnDate, 
	    				notes, currencyCat, totalFunding, statusCat, statusNotes
		    	  );
	     }
	  
	  try 
      {
	     trDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
		 System.out.println("Error 54: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
	      
      trDb.save(travelRequest); 
      
      trDb.disconnect();
      
   }
   
   public void delete(TravelRequestFormEvent ev) throws SQLException 
   {
	   
	     TravelRequest travelRequest;
		 int id = ev.getId();
	     String number = ev.getNumber();	
	     String employee = ev.getEmployee();
	     String transportation = ev.getTransportation();
	     String purpose = ev.getPurpose();
	     String travelFrom = ev.getTravelFrom();
	     String travelTo = ev.getTravelTo();
		 Date travelDate = ev.getTravelDate();
		 Date returnDate = ev.getReturnDate();
		 String notes = ev.getNotes();
		 String currency = ev.getCurrency();
		 float totalFunding = ev.getTotalFunding();
		 String status = ev.getStatus();
		 String statusNotes = ev.getStatusNotes();
		 
	     Transportation transportationCat = null;
	     
	     // Define the possible values for TransportationCat
	     if(transportation.equals("Airplane")) 
	     {
	        transportationCat = Transportation.Airplane;
	     }
	     else if(transportation.equals("Train")) 
		 {
		    transportationCat = Transportation.Train;
		 }
		 else if(transportation.equals("Taxi")) 
		 {
	       transportationCat = Transportation.Taxi;
		 }
		 else if(transportation.equals("Own Vehicle")) 
		 {
		   transportationCat = Transportation.OwnVehicle;
		 }
		 else if(transportation.equals("Rented Vehicle")) 
		 {
		   transportationCat = Transportation.RentedVehicle;
		 }
	     
	     Currency currencyCat = null;
	     
	     // Define the possible values for CurrencyCat
	     if(currency.equals("Euros")) 
	     {
	        currencyCat = Currency.Euros;
	     }
	     else if(currency.equals("Pound Sterling")) 
		 {
		    currencyCat = Currency.PoundSterling;
		 }
		 else if(currency.equals("American Dollars")) 
		 {
	       currencyCat = Currency.AmericanDollars;
		 }
		 else if(currency.equals("Canadian Dollars")) 
		 {
		   currencyCat = Currency.CanadianDollars;
		 }
		 else if(currency.equals("Mexican Pesos")) 
		 {
		   currencyCat = Currency.MexicanPesos;
		 }
	    	
	     Status statusCat = null;
	     
	     // Define the possible values for StatusCat
	     if(status.equals("Pending")) 
	     {
	        statusCat = Status.Pending;
	     }
	     else if(status.equals("Approved")) 
		 {
		    statusCat = Status.Approved;
		 }
		 else if (status.equals("Rejected")) 
		 {
	       statusCat = Status.Rejected;
		 }
	    
	     if (id == 0)
	     {
	    	 travelRequest = new TravelRequest (number, employee, transportationCat, 
	    				purpose, travelFrom, travelTo, travelDate, returnDate, 
	    				notes, currencyCat, totalFunding, statusCat, statusNotes
			    	  );
	     }
	     else
	     {
	         travelRequest = new TravelRequest (id, number, employee, transportationCat, 
	    				purpose, travelFrom, travelTo, travelDate, returnDate, 
	    				notes, currencyCat, totalFunding, statusCat, statusNotes
		    	  );
	     }

      try 
	  {
	     trDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 54: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
		      
	  trDb.delete(travelRequest); 	      
	  trDb.disconnect();	     
      
   }

	   
   public void load(String userP, UserLevel userLevelP) throws SQLException 
   {
      try 
	  {
	     trDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
		 System.out.println("Error 54: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      trDb.load(userP, userLevelP); 
      trDb.disconnect();
   }
   
   public void loadTravelRequest(int id) throws SQLException 
   {
      trDb.loadTravelRequest(id); 
   }
   
   public ArrayList<Employee> loadFullNameEmployees(String userP, UserLevel userLevelP) throws SQLException
   {
	   
      try 
	  {
	     trDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
		 System.out.println("Error 45: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
 
	  ArrayList<Employee> eB = trDb.loadFullNameEmployees(userP, userLevelP);
      trDb.disconnect();

	  return eB;
   }
   
   public boolean validateTravelRequestFields(TravelRequestFormEvent ev)
   {
	  boolean validTravelRequest = false; 
	  int v = 0;
	  String number = ev.getNumber();	
	  String employee = ev.getEmployee();
	  String transportation = ev.getTransportation();
	  String purpose = ev.getPurpose();
	  String travelFrom = ev.getTravelFrom();
	  String travelTo = ev.getTravelTo();
	  Date travelDate = ev.getTravelDate();
	  Date returnDate = ev.getReturnDate();
	  String currency = ev.getCurrency();
	  float totalFunding = ev.getTotalFunding();
	  
	  // Validation: #1
	  // Validate that all the Travel Request Fields are not empty
	  
      // Define the format of the date
	  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	  // Make the conversion from Date to String
	  String travelDateStr = formatter.format(travelDate);  
	  String returnDateStr = formatter.format(returnDate);  
	  
	  // Convert from Float to String
	  String totalFundingStr = Float.toString(totalFunding);
	  
	  if(number != null && !number.isEmpty())
	     v++;
	
	  if(employee != null && !employee.isEmpty() && !employee.trim().isEmpty())
	     v++;
	  
	  if(transportation != null && !transportation.isEmpty())
	     v++;

	  if(purpose != null && !purpose.isEmpty())
	     v++;
	  
	  if(travelFrom != null && !travelFrom.isEmpty())
	     v++;

	  if(travelTo != null && !travelTo.isEmpty())
	     v++;
	  
	  if(travelDateStr != null && !travelDateStr.isEmpty())
	     v++;
	  
	  if(returnDateStr != null && !returnDateStr.isEmpty())
	     v++;
	  
	  if(currency != null && !currency.isEmpty())
	     v++;

	  if(totalFundingStr != null && !totalFundingStr.isEmpty())
	     v++;
	  
	  
	  // Validate that all the travel request fields have a value						     
	  if (v == 10) 
	     validTravelRequest = true;
	  else 
		  validTravelRequest = false;
	  
      return validTravelRequest;	     
   }   

   public boolean validateTravelReturnDates(TravelRequestFormEvent ev)
   {
	  boolean validTravelRequest = false; 
	     
	  Date travelDate = ev.getTravelDate();
	  Date returnDate = ev.getReturnDate();
	  
	  // Validation: #2
	  // Validate that the date of birth and joined date are not the same
	  if(returnDate.equals(travelDate))
	  {
		  validTravelRequest = true;
      }
	  else
		  // Validate that the return date is greater than the travel Date
		  if (returnDate.after(travelDate))
		     validTravelRequest = true;
		  else
			  validTravelRequest = false; 
		  

      return validTravelRequest;	     
   } 
   
   public boolean validateTotalFunding(TravelRequestFormEvent ev)
   {
	  boolean validTravelRequest = false; 
	  float totalFunding = ev.getTotalFunding();
	    	
	  // Validation: #3
	  // The total funding must be greater than 0
	  
	  // Validate that all the employee fields have a value						     
	  if (totalFunding > 0) 
	     validTravelRequest = true;
	  else 
		  validTravelRequest = false;
	  
      return validTravelRequest;	     
   }
   
   public int maxNumTravelRequest() throws SQLException
   {
      int maxTR = 0;
	  
      
      try 
	  {
	     trDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
		 System.out.println("Error 86: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
 
      maxTR = trDb.maxNumTravelRequest();
      trDb.disconnect();

      return maxTR;
   }
      

}
