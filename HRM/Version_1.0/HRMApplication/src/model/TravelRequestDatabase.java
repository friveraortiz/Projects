package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class TravelRequestDatabase 
{
   // Atributes for the Travel Request Database Class		
   private List<TravelRequest> travelRequests;
   private String dbUser;
   private String dbPassword;
   private String dbPort;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   public TravelRequestDatabase()
   {
      travelRequests = new LinkedList<TravelRequest>();
   }
   
   // Methods of the Class
   
   public void configureDBParameters()
   {
      Properties prop = new Properties();
	  InputStream input = null;

	  try 
	  {
         // Specify the path of the configuration file
	     input = new FileInputStream("/Users/fannyriveraortiz/eclipse-workspace/HRMApplication/resources/config.properties");

	     // Load the properties file
		 prop.load(input);

		 // Get the values from the properties file
		 this.dbUser = prop.getProperty("dbUser");
		 this.dbPassword = prop.getProperty("dbPassword");
		 this.dbPort = prop.getProperty("dbPort");
		 this.dbUrl = prop.getProperty("dbUrl");
		 this.dbDriver = prop.getProperty("dbDriver");

	   } 
	   catch (IOException ex) 
	   {
	      String errorMessage = ex.getMessage();
		  System.out.println("Error 74: Occurred while loading the Config File. Error Message: " + errorMessage);
	   } 
	   finally 
	   {
	      if (input != null) 
	      {
		     try 
		     {
			    input.close();
			 } 
		     catch (IOException e) 
		     {
			    String errorMessage = e.getMessage();
				System.out.println("Error 75: Occurred while closing the Config File. Error Message: " + errorMessage);
			 }
		  }
	   }

   }  
   
   // Connect to the HRM Database
   public void connect() throws Exception 
   {

      if (dbCon != null)
	  return;

      // Get the database parameters from the config.properties file
      configureDBParameters();
      
	  try 
	  {
	     Class.forName(dbDriver);
	  } 
	  catch (ClassNotFoundException e) 
	  {
		 String errorMessage = e.getMessage();
	     throw new Exception("Error 76: The Database driver was not found. Error Message: "+errorMessage);
	  }
		
	  dbCon = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
	  //System.out.println("HRM Database Connected");
	}
	
    // Disconnect to the HRM Database
	public void disconnect() 
	{
	   if (dbCon != null) 
	   {
	      try 
		  {
		     dbCon.close();
		  } 
	      catch (SQLException e) 
		  {
	    	 String errorMessage = e.getMessage();
		     System.out.println("Error 77: The HRM Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("HRM Database Disconnected");
	 }
   
	public void save(TravelRequest travelRequest) throws SQLException 
	{
		// Get the values from the GUI
		int travel_request_id = travelRequest.getId();
		String number = travelRequest.getNumber();	
		String employee = travelRequest.getEmployee();
		Transportation transportation = travelRequest.getTransportation();
		String purpose = travelRequest.getPurpose();
		String travelFrom = travelRequest.getTravelFrom();
		String travelTo = travelRequest.getTravelTo();
		Date travelDate = travelRequest.getTravelDate();
		Date returnDate = travelRequest.getReturnDate();
		String notes = travelRequest.getNotes();
		Currency currency1 = travelRequest.getCurrency();
		float totalFunding = travelRequest.getTotalFunding();
		Status status = travelRequest.getStatus();
		String statusNotes = travelRequest.getStatusNotes();
		
		// SQL Statements
		String selectSql1 = "select count(*) as count from travel_requests where travel_request_id=?";
		PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
		
		String selectSql2 = "select employee_id as employee_id from employees where full_name=?";
		PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
		
		String insertSql = "insert into travel_requests (travel_request_id, number, employee_id, transportation, purpose, ";
		insertSql = insertSql + "travel_from, travel_to, travel_date, return_date, notes, currency, total_funding, status, ";
		insertSql = insertSql + "status_notes) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = dbCon.prepareStatement(insertSql);

		String updateSql = "update travel_requests set number=?, employee_id=?, transportation=?, purpose=?, ";
		       updateSql = updateSql + "travel_from=?, travel_to=?, travel_date=?, return_date=?, notes=?, currency=?, ";
			   updateSql = updateSql + "total_funding=?, status=?, status_notes=? where travel_request_id=?";
		PreparedStatement updateStatement = dbCon.prepareStatement(updateSql);
		
		String deleteSql = "delete from travel_requests where travel_request_id=?";
		PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
		
	    selectStatement1.setInt(1, travel_request_id);
	    selectStatement2.setString(1, employee);
				
	    ResultSet selectResult1 = selectStatement1.executeQuery();
	    selectResult1.next();
	    int count = selectResult1.getInt(1);
	    
	    ResultSet selectResult2 = selectStatement2.executeQuery();
	    selectResult2.next();
	    int employee_id = selectResult2.getInt(1);
	    
	    // Validate that the employee_Id field is not empty
	    if (employee_id != 0)
	    {
           if (count == 0) 
           {
              System.out.println("Inserting a Travel Request with ID: "+travel_request_id);
            	
				int col = 1;
				insertStatement.setInt(col++, travel_request_id);
				insertStatement.setString(col++, number);
				insertStatement.setInt(col++, employee_id);
				insertStatement.setString(col++, transportation.name());
				insertStatement.setString(col++, purpose);
				insertStatement.setString(col++, travelFrom);
				insertStatement.setString(col++, travelTo);
				
				// Define the format of the date
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

				try 
				{
				    // Convert terminated Date from Date to String
					String travelDateStr = formatter.format(travelDate);  
					Date travelDate1 = formatter.parse(travelDateStr);
						 
					// Create a calendar
					Calendar cal = Calendar.getInstance();
					// Set an specific date into the calendar
					cal.setTime(travelDate1);
					     
					// Convert date from java.util.Calendar to java.sql.Date
				    java.sql.Date travelDate2 = new java.sql.Date(cal.getTimeInMillis());
						 
				    insertStatement.setDate(col++, travelDate2);
				} 
				catch (ParseException e) 
				{
					String errorMessage = e.getMessage();
					System.out.println("Error 78: Ocurred while converting the Travel Date. Error Message: " + errorMessage);
				}

				try 
				{
				    // Convert terminated Date from Date to String
					String returnDateStr = formatter.format(returnDate);  
					Date returnDate1 = formatter.parse(returnDateStr);
						 
					// Create a calendar
					Calendar cal = Calendar.getInstance();
					// Set an specific date into the calendar
					cal.setTime(returnDate1);
					     
					// Convert date from java.util.Calendar to java.sql.Date
				    java.sql.Date returnDate2 = new java.sql.Date(cal.getTimeInMillis());
						 
				    insertStatement.setDate(col++, returnDate2);
					
				} 
				catch (ParseException e) 
				{
					String errorMessage = e.getMessage();
					System.out.println("Error 79: Ocurred while converting the Return Date. Error Message: " + errorMessage);
				}

				insertStatement.setString(col++, notes);
				insertStatement.setString(col++, currency1.name());
				insertStatement.setFloat(col++, totalFunding);
				insertStatement.setString(col++, status.name());
				insertStatement.setString(col++, statusNotes);
				
				insertStatement.executeUpdate();
			} 
            else 
			{
            	
				int col = 1;
				
				System.out.println("Updating a Travel Request with ID: "+travel_request_id);
				
				updateStatement.setString(col++, number);
				updateStatement.setInt(col++, employee_id);
				updateStatement.setString(col++, transportation.name());
				updateStatement.setString(col++, purpose);
				updateStatement.setString(col++, travelFrom);
				updateStatement.setString(col++, travelTo);
				
				// Define the format of the date
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

				try 
				{

				    // Convert terminated Date from Date to String
					String travelDateStr = formatter.format(travelDate);  
					Date travelDate1 = formatter.parse(travelDateStr);
							 
					// Create a calendar
					Calendar cal = Calendar.getInstance();
					// Set an specific date into the calendar
					cal.setTime(travelDate1);
						     
					// Convert date from java.util.Calendar to java.sql.Date
					java.sql.Date travelDate2 = new java.sql.Date(cal.getTimeInMillis());
							 
					updateStatement.setDate(col++, travelDate2);
				} 
				catch (ParseException e) 
				{
					String errorMessage = e.getMessage();
					System.out.println("Error 80: Ocurred while converting the Travel Date. Error Message: " + errorMessage);
				}

				try 
				{
				    // Convert terminated Date from Date to String
					String returnDateStr = formatter.format(returnDate);  
					Date returnDate1 = formatter.parse(returnDateStr);
						 
					// Create a calendar
					Calendar cal = Calendar.getInstance();
					// Set an specific date into the calendar
					cal.setTime(returnDate1);
					     
					// Convert date from java.util.Calendar to java.sql.Date
				    java.sql.Date returnDate2 = new java.sql.Date(cal.getTimeInMillis());
						 
				    updateStatement.setDate(col++, returnDate2);
				} 
				catch (ParseException e) 
				{
					String errorMessage = e.getMessage();
					System.out.println("Error 81: Ocurred while converting the Return Date. Error Message: " + errorMessage);
				}

				updateStatement.setString(col++, notes);
				updateStatement.setString(col++, currency1.name());
				updateStatement.setFloat(col++, totalFunding);
				updateStatement.setString(col++, status.name());
				updateStatement.setString(col++, statusNotes);
				updateStatement.setInt(col++, travel_request_id);

				updateStatement.executeUpdate();
				
		   }
		}
		
		insertStatement.close();
		updateStatement.close();
		deleteStatement.close();
		selectStatement1.close();
		selectStatement2.close();
	} 

	public void delete(TravelRequest travelRequest) throws SQLException 
	{

		// Get the values from the GUI
		int travel_request_id = travelRequest.getId();
		
		// SQL Statements
		String selectSql = "select count(*) as count from travel_requests where travel_request_id=?";
		PreparedStatement selectStatement = dbCon.prepareStatement(selectSql);
		
		String deleteSql = "delete from travel_requests where travel_request_id=?";
		PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
		
		selectStatement.setInt(1, travel_request_id);

		ResultSet selectResult = selectStatement.executeQuery();
		selectResult.next();

        int count = selectResult.getInt(1);
                        
        if (count != 0) 
        {
           int col = 1;
		   System.out.println("Deleting a Travel Request with ID: "+travel_request_id);
		   deleteStatement.setInt(col, travel_request_id);
		   deleteStatement.executeUpdate();

	    }
            
 		deleteStatement.close();
		selectStatement.close();
	} 
	

   public void load(String userP, UserLevel userLevelP) throws SQLException 
   {
      travelRequests.clear();
      
      System.out.println("Loading Travel Requests ... ");
      
      // Validate if the User Level is equal to Employee
      // It will bring just the travel request created for this employee
      if(userLevelP == UserLevel.Employee)
      {	  
      
         String selectSql1 = "select employee_id from users where username=?";
	     PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	     selectStatement1.setString(1, userP);

	     ResultSet selectResults1 = selectStatement1.executeQuery();
	     selectResults1.next();
         int employeeId = selectResults1.getInt(1);
      
         // SQL Statements
         String selectSql2 = "select t.travel_request_id, t.number, e.full_name as employee, t.transportation, t.purpose, ";
	     selectSql2 = selectSql2 + "t.travel_from, t.travel_to, t.travel_date, t.return_date, t.notes, t.currency, ";
	     selectSql2 = selectSql2 + "t.total_funding, t.status, t.status_notes ";
	     selectSql2 = selectSql2 + "from travel_requests t, employees e ";
	     selectSql2 = selectSql2 + "where t.employee_id =? ";
	     selectSql2 = selectSql2 + "and t.employee_id = e.employee_id ";
	     selectSql2 = selectSql2 + "order by t.travel_request_id";
	  
	     PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
	     selectStatement2.setInt(1, employeeId);
	  
	     ResultSet selectResults2 = selectStatement2.executeQuery();

	     while(selectResults2.next()) 
	     {
 
	        int travelRequestId = selectResults2.getInt("travel_request_id");
		    String number = selectResults2.getString("number");
		    String employee = selectResults2.getString("employee");
		    String transportation = selectResults2.getString("transportation");
		    String purpose = selectResults2.getString("purpose");
		    String travelFrom = selectResults2.getString("travel_from");
		    String travelTo = selectResults2.getString("travel_to");
		    Date travelDate = selectResults2.getDate("travel_date");	
		    Date returnDate = selectResults2.getDate("return_date");	
		    String notes = selectResults2.getString("notes");
		    String currency = selectResults2.getString("currency");
		    float totalFunding = selectResults2.getFloat("total_funding");
		    String status = selectResults2.getString("status");
		    String statusNotes = selectResults2.getString("status_notes");
			
		    TravelRequest travelRequest = new TravelRequest(travelRequestId, number, employee,  
			  	      Transportation.valueOf(transportation), purpose, travelFrom, travelTo, travelDate, returnDate, 
					  notes, Currency.valueOf(currency), totalFunding, Status.valueOf(status), statusNotes);
			
		    travelRequests.add(travelRequest);		 
	     }    
		 selectResults1.close();
		 selectStatement1.close();
		   
		 selectResults2.close();
		 selectStatement2.close();
		 
      }
      else 
    	  if(userLevelP == UserLevel.Admin || userLevelP == UserLevel.Manager)
          {	  
      
             // SQL Statements
             String selectSql3 = "select t.travel_request_id, t.number, e.full_name as employee, t.transportation, t.purpose, ";
	         selectSql3 = selectSql3 + "t.travel_from, t.travel_to, t.travel_date, t.return_date, t.notes, t.currency, ";
	         selectSql3 = selectSql3 + "t.total_funding, t.status, t.status_notes ";
	         selectSql3 = selectSql3 + "from travel_requests t, employees e ";
	         selectSql3 = selectSql3 + "where t.employee_id = e.employee_id order by t.travel_request_id";
	  
	         Statement selectStatement3 = dbCon.createStatement();
	         ResultSet selectResults3 = selectStatement3.executeQuery(selectSql3);
		
	         while(selectResults3.next()) 
	         {
	            int travelRequestId = selectResults3.getInt("travel_request_id");
		        String number = selectResults3.getString("number");
		        String employee = selectResults3.getString("employee");
		        String transportation = selectResults3.getString("transportation");
		        String purpose = selectResults3.getString("purpose");
		        String travelFrom = selectResults3.getString("travel_from");
		        String travelTo = selectResults3.getString("travel_to");
		        Date travelDate = selectResults3.getDate("travel_date");	
		        Date returnDate = selectResults3.getDate("return_date");	
		        String notes = selectResults3.getString("notes");
		        String currency = selectResults3.getString("currency");
		        float totalFunding = selectResults3.getFloat("total_funding");
		        String status = selectResults3.getString("status");
		        String statusNotes = selectResults3.getString("status_notes");
			
		        TravelRequest travelRequest = new TravelRequest(travelRequestId, number, employee,  
				       Transportation.valueOf(transportation), purpose, travelFrom, travelTo, travelDate, returnDate, 
					   notes, Currency.valueOf(currency), totalFunding, Status.valueOf(status), statusNotes);
			
		        travelRequests.add(travelRequest);
						
	         }
	         selectResults3.close();
	         selectStatement3.close();
       } 

   }

   	  
   public void loadTravelRequest(int id) throws SQLException
   {

	  System.out.println("Loading an specif Travel Request: "+id);
	  
	  // SQL Statements
	  String selectSql = "select t.travel_request_id, t.number, e.full_name as employee, t.transportation, t.purpose, ";
			 selectSql = selectSql+"t.travel_from, t.travel_to, t.travel_date, t.return_date, t.notes, t.currency, t.total_funding, ";
			 selectSql = selectSql+"t.status, t.status_notes from travel_requests t, employees e ";
	  		 selectSql = selectSql+"where t.employee_id = e.employee_id and t.travel_request_id=?";
	  PreparedStatement selectStatement = dbCon.prepareStatement(selectSql);
	  selectStatement.setInt(1, id);

	  ResultSet selectResults = selectStatement.executeQuery();
	  selectResults.next();

	  int travelRequestId = selectResults.getInt("travel_request_id");
			
	  String number = selectResults.getString("number");
	  String employee = selectResults.getString("employee");
	  String transportation = selectResults.getString("transportation");
	  String purpose = selectResults.getString("purpose");
	  String travelFrom = selectResults.getString("travel_from");
	  String travelTo = selectResults.getString("travel_to");
	  Date travelDate = selectResults.getDate("travel_date");	
	  Date returnDate = selectResults.getDate("return_date");	
	  String notes = selectResults.getString("notes");
	  String currency = selectResults.getString("currency");
	  float totalFunding = selectResults.getFloat("total_funding");
	  String status = selectResults.getString("status");
	  String statusNotes = selectResults.getString("status_notes");	  			
				
	  TravelRequest travelRequest = new TravelRequest(travelRequestId, number, employee,  
				    Transportation.valueOf(transportation), purpose, travelFrom, travelTo, travelDate, returnDate, 
					notes, Currency.valueOf(currency), totalFunding, Status.valueOf(status), statusNotes);
			
	  travelRequests.add(travelRequest);
				
	  selectResults.close();
	  selectStatement.close();
	
   }
   
   // Add a new travel request
   public void addTravelRequest(TravelRequest travelRequest) 
   {
        travelRequests.add(travelRequest); 
   }
   
   // Get a travel request
   public List<TravelRequest> getTravelRequests()
   {
	   return Collections.unmodifiableList(travelRequests);
   }
   
   // Delete a travel request
   public void deleteTravelRequest(int id)
   {
	   travelRequests.remove(id);
   }
   
   public ArrayList<Employee> loadFullNameEmployees(String userP, UserLevel userLevelP) throws SQLException 
   {
      // Declare ArrayList of EmployeeBasics
	  ArrayList<Employee> employeeBasics = new ArrayList<Employee>();	 
	  
	  // Depending on the UserLevel, it will display a different list of available employees
	  if(userLevelP.equals(UserLevel.Admin) || userLevelP.equals(UserLevel.Manager))
	  {	  
	     String sql1 = "select e.employee_id, e.number, e.full_name from employees e order by e.full_name asc";
	     Statement selectStatement1 = dbCon.createStatement();
		
	     ResultSet results1 = selectStatement1.executeQuery(sql1);
		
	     while(results1.next()) 
	     {
		  
	        int employeeId = results1.getInt("employee_id");
	        String number = results1.getString("number");
		    String fullName = results1.getString("full_name");
		 
	        Employee eB = new Employee(employeeId, number, fullName); 
	     
	        // Add elements to the ArrayList.
	        employeeBasics.add(eB); 
	     }
		
	     results1.close();
	     selectStatement1.close();
	  }
	  else 
	     if(userLevelP.equals(UserLevel.Employee))
		 {
	    	 String sql1 = "select e.employee_id, e.number, e.full_name from users u, employees e ";
	    	        sql1 = sql1 + "where u.employee_id = e.employee_id ";
	    	        sql1 = sql1 + "and u.username = ?";
	    	 PreparedStatement selectStatement1 = dbCon.prepareStatement(sql1);
		     selectStatement1.setString(1, userP);
			
		     ResultSet results1 = selectStatement1.executeQuery();
			
		     while(results1.next()) 
		     {
			  
		        int employeeId = results1.getInt("employee_id");
		        String number = results1.getString("number");
			    String fullName = results1.getString("full_name");
			 
		        Employee eB = new Employee(employeeId, number, fullName); 
		     
		        // Add elements to the ArrayList.
		        employeeBasics.add(eB); 
		     }
			
		     results1.close();
		     selectStatement1.close();
	    	 
		 }
	  
      return employeeBasics;
      
   }
   
   public int maxNumTravelRequest() throws SQLException
   {
      int maxTR = 0;	   
	   
      // SQL Statements
      String selectSql = "SELECT MAX(CAST(SUBSTRING(t.number, LOCATE('T', t.number) + 1) AS UNSIGNED)) AS max_value ";
	         selectSql = selectSql + "FROM travel_requests t";
	 
	  PreparedStatement selectStatement = dbCon.prepareStatement(selectSql);
	  ResultSet selectResults = selectStatement.executeQuery();
	  selectResults.next();
	  maxTR = selectResults.getInt("max_value");
	   
	  selectResults.close();
	  selectStatement.close(); 
	  
      return maxTR;	  
   }
   
}
