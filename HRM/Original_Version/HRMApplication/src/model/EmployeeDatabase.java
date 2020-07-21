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

public class EmployeeDatabase 
{
   // Atributes for the Employee Database Class
   private List<Employee> employees;
   private String dbUser;
   private String dbPassword;
   private String dbUrl;
   private String dbDriver;
   private Connection dbCon;
   
   // Constructor of the Class
   public EmployeeDatabase()
   {
      employees = new LinkedList<Employee>();
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
		 prop.getProperty("dbPort");
		 this.dbUrl = prop.getProperty("dbUrl");
		 this.dbDriver = prop.getProperty("dbDriver");

	   } 
	   catch (IOException ex) 
	   {
	      String errorMessage = ex.getMessage();
		  System.out.println("Error 56: Occurred while loading the Config File. Error Message: " + errorMessage);
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
				System.out.println("Error 57: Occurred while closing the Config File. Error Message: " + errorMessage);
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
	     throw new Exception("Error 58: The Database driver was not found. Error Message: "+errorMessage);
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
		     System.out.println("Error 59: The HRM Database Connection cannot be closed. Error Message: "+errorMessage);
		  }
	   }
		
	   dbCon = null;
	   //System.out.println("HRM Database Disconnected");
	 }
   
	public void save(Employee employee) throws SQLException 
	{
		
	   // Get the values from the GUI
	   int employeeId = employee.getId();
	   String number = employee.getNumber();
	   String firstName = employee.getFirstName();
	   String middleName = employee.getMiddleName();
	   String lastName = employee.getLastName(); 
	   String fullName;
	   int supervisorId=0;
	    
	   if(middleName != null && !middleName.isEmpty() && !middleName.trim().isEmpty())
	   {
	      fullName = firstName + " " + middleName + " " + lastName;
	   }
	   else
	      fullName = firstName + " " + lastName;
	    
	   Date dobDate = employee.getDob();
	   Gender gender = employee.getGender();
	   MaritalStatus maritalStatus = employee.getMaritalStatus();
	   String mobilePhone = employee.getMobilePhone();
	   Date joinedDate = employee.getJoinedDate();
	   Date terminatedDate = employee.getTerminatedDate();
	   
	   String jobTitle = employee.getJobTitle();
	   String department = employee.getDepartment();
	   String supervisor = employee.getSupervisor();
	   
	   // SQL Statements
	   
	   // Verify if the Employee already exists in the employees table
	   String selectSql1 = "select count(*) as count from employees where employee_id=?";
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   
	   selectStatement1.setInt(1, employeeId);
	   ResultSet selectResult = selectStatement1.executeQuery();
	   selectResult.next();
       int count = selectResult.getInt(1);
		
	   String insertSql = "insert into employees (employee_id, number, first_name, middle_name, last_name, full_name, dob, gender, marital_status, mobile_phone, joined_date, terminated_date, job_title, department, supervisor_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	   PreparedStatement insertStatement = dbCon.prepareStatement(insertSql);
   
	   String updateSql = "update employees set number=?, first_name=?, middle_name=?, last_name=?, full_name=?, dob=?, gender=?, marital_status=?, mobile_phone=?, joined_date=?, terminated_date=?, job_title=?, department=?, supervisor_id=? where employee_id=?";
	   PreparedStatement updateStatement = dbCon.prepareStatement(updateSql);
		
	   String deleteSql = "delete from employees where employee_id=?";
	   PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
	   
	   // Get the employee_id from the supervisor name
	   String selectSql2 = "select employee_id from employees where full_name =?";
	   PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
       
       // Get the Supervisor Id
       selectStatement2.setString(1, supervisor);
	   ResultSet selectResult2 = selectStatement2.executeQuery();
	   
	   // Validate that the Supervisor Id is not null
	   if (selectResult2.next())
	   {	   
          supervisorId = selectResult2.getInt(1);
	   }   
	   else
	   {
	      String errorMessage = "The Supervisor: "+ supervisor + " does not exist in the Employees table";
	      System.out.println("Error 60: Occurred while getting the Supervisor Id. Error Message: " + errorMessage);
	   }   
       
       if (count == 0) 
       {
          System.out.println("Inserting an Employee with ID: "+employeeId);
          
		  int col = 1;
		  insertStatement.setInt(col++, employeeId);
		  insertStatement.setString(col++, number);
		  insertStatement.setString(col++, firstName);
		  insertStatement.setString(col++, middleName);
		  insertStatement.setString(col++, lastName);
		  insertStatement.setString(col++, fullName);
				
		  // Define the format of the date
		  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		  try 
		  {
		     // Convert terminated Date from Date to String
			 String dobDateStr = formatter.format(dobDate);  
			 Date dobDate1 = formatter.parse(dobDateStr);
					 
			 // Create a calendar
			 Calendar cal = Calendar.getInstance();
			 // Set an specific date into the calendar
			 cal.setTime(dobDate1);
				     
			 // Convert date from java.util.Calendar to java.sql.Date
			 java.sql.Date dobDate2 = new java.sql.Date(cal.getTimeInMillis());
					 
			 insertStatement.setDate(col++, dobDate2);
		  } 
		  catch (ParseException e) 
		  {
			 String errorMessage = e.getMessage();
			 System.out.println("Error 61: Occurred while converting the Birth Date. Error Message: " + errorMessage);
		  }

		  insertStatement.setString(col++, gender.name());
		  insertStatement.setString(col++, maritalStatus.name());
		  insertStatement.setString(col++, mobilePhone);
				
		  try 
		  {
		     // Convert terminated Date from Date to String
			 String joinedDateStr = formatter.format(joinedDate);  
			 Date joinedDate1 = formatter.parse(joinedDateStr);
				 
			 // Create a calendar
			 Calendar cal = Calendar.getInstance();
			 // Set an specific date into the calendar
			 cal.setTime(joinedDate1);
			     
			 // Convert date from java.util.Calendar to java.sql.Date
		     java.sql.Date joinedDate2 = new java.sql.Date(cal.getTimeInMillis());
				 
		     insertStatement.setDate(col++, joinedDate2);
		  } 
		  catch (ParseException e) 
		  {
			 String errorMessage = e.getMessage();
			 System.out.println("Error 62: Occurred while converting the Joined Date. Error Message: " + errorMessage);
		  }

		  try 
		  {
		     // Convert terminated Date from Date to String
			 String terminatedDateStr = formatter.format(terminatedDate);  
			 Date terminatedDate1 = formatter.parse(terminatedDateStr);
			 
			 // Create a calendar
		     Calendar cal = Calendar.getInstance();
		     // Set an specific date into the calendar
		     cal.setTime(terminatedDate1);
		     
		     // Convert date from java.util.Calendar to java.sql.Date
			 java.sql.Date terminatedDate2 = new java.sql.Date(cal.getTimeInMillis());
			 
			 insertStatement.setDate(col++, terminatedDate2);
		   } 
		   catch (ParseException e) 
		   {
			  String errorMessage = e.getMessage();
			  System.out.println("Error 63: Occurred while converting the Terminated Date. Error Message: " + errorMessage);
		   }

		   insertStatement.setString(col++, jobTitle);
		   insertStatement.setString(col++, department);
		   insertStatement.setInt(col++, supervisorId);
		   
		   insertStatement.executeUpdate();
		} 
        else 
	    {
            	
		   int col = 1;
				
		   System.out.println("Updating an Employee with ID: "+employeeId);
				
		   updateStatement.setString(col++, number);
		   updateStatement.setString(col++, firstName);
		   updateStatement.setString(col++, middleName);
		   updateStatement.setString(col++, lastName);
		   updateStatement.setString(col++, fullName);
				
		   // Define the format of the date
		   DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				
		   try 
		   {
		      // Convert terminated Date from Date to String
			  String dobDateStr = formatter.format(dobDate);  
			  Date dobDate1 = formatter.parse(dobDateStr);
			  
			  // Create a calendar
			  Calendar cal = Calendar.getInstance();
			  // Set an specific date into the calendar
			  cal.setTime(dobDate1);
					     
			  // Convert date from java.util.Calendar to java.sql.Date
			  java.sql.Date dobDate2 = new java.sql.Date(cal.getTimeInMillis());
			
			  updateStatement.setDate(col++, dobDate2);
					
		    } 
		   catch (ParseException e) 
		   {
			  String errorMessage = e.getMessage();
			  System.out.println("Error 64: Occurred while converting the Birth Date. Error Message: " + errorMessage);
		   }

		   updateStatement.setString(col++, gender.name());
		   updateStatement.setString(col++, maritalStatus.name());
		   updateStatement.setString(col++, mobilePhone);
				
		   try 
		   {
		      // Convert terminated Date from Date to String
			  String joinedDateStr = formatter.format(joinedDate);  
			  Date joinedDate1 = formatter.parse(joinedDateStr);
			  
			  // Create a calendar
			  Calendar cal = Calendar.getInstance();
			  // Set an specific date into the calendar
			  cal.setTime(joinedDate1);
				     
			  // Convert date from java.util.Calendar to java.sql.Date
			  java.sql.Date joinedDate2 = new java.sql.Date(cal.getTimeInMillis());
					 
			  updateStatement.setDate(col++, joinedDate2);
		    } 
		   catch (ParseException e) 
		   {
			  String errorMessage = e.getMessage();
			  System.out.println("Error 65: Occurred while converting the Joined Date. Error Message: " + errorMessage);
		   }

		   try 
		   {
		      // Convert terminated Date from Date to String
			  String terminatedDateStr = formatter.format(terminatedDate);  
			  Date terminatedDate1 = formatter.parse(terminatedDateStr);
			  
			  // Create a calendar
			  Calendar cal = Calendar.getInstance();
			  // Set an specific date into the calendar
			  cal.setTime(terminatedDate1);
			     
			  // Convert date from java.util.Calendar to java.sql.Date
			  java.sql.Date terminatedDate2 = new java.sql.Date(cal.getTimeInMillis());
				 
			  updateStatement.setDate(col++, terminatedDate2);
		   } 
		   catch (ParseException e) 
		   {
			  String errorMessage = e.getMessage();
			  System.out.println("Error 66: Occurred while converting the Terminated Date. Error Message: " + errorMessage);
		   }

		   updateStatement.setString(col++, jobTitle);
		   updateStatement.setString(col++, department);
		   updateStatement.setInt(col++, supervisorId);
		   updateStatement.setInt(col++, employeeId);

		   updateStatement.executeUpdate();
				
		}
		
		insertStatement.close();
		updateStatement.close();
		deleteStatement.close();
		selectStatement1.close();
		selectStatement2.close();
		
	} 

	public void delete(Employee employee) throws SQLException 
	{
	    // Get the values from the GUI
		int employeeId = employee.getId();

		// SQL Statements
		String selectSql = "select count(*) as count from employees where employee_id=?";
		PreparedStatement selectStatement = dbCon.prepareStatement(selectSql);
		
		String deleteSql = "delete from employees where employee_id=?";
		PreparedStatement deleteStatement = dbCon.prepareStatement(deleteSql);
		
		selectStatement.setInt(1, employeeId);

		ResultSet selectResult = selectStatement.executeQuery();
		selectResult.next();

        int count = selectResult.getInt(1);
                        
        if (count != 0) 
        {
           int col = 1;
		   System.out.println("Deleting an Employee with ID: "+employeeId);
		   deleteStatement.setInt(col, employeeId);
		   deleteStatement.executeUpdate();
		}
            
 		deleteStatement.close();
		selectStatement.close();
	} 
	

   public void load() throws SQLException 
   {
	  String supervisor =null;
      employees.clear();

      // SQL Statements      
      String sql1 = "select employee_id, number, first_name, middle_name, last_name, ";
	  sql1 = sql1 + "dob, gender, marital_status, mobile_phone, ";
	  sql1 = sql1 + "joined_date, terminated_date, job_title, department, supervisor_id ";
	  sql1 = sql1 + "from employees order by employee_id";
	  
	  Statement selectStatement1 = dbCon.createStatement();
	  ResultSet selectResults1 = selectStatement1.executeQuery(sql1);
		
	  while(selectResults1.next()) 
	  {
	     int employeeId = selectResults1.getInt("employee_id");
			
		 String number = selectResults1.getString("number");
		 String firstName = selectResults1.getString("first_name");
		 String middleName = selectResults1.getString("middle_name");
		 String lastName = selectResults1.getString("last_name");
		 Date dob = selectResults1.getDate("dob");		 
		 String gender = selectResults1.getString("gender");
		 String maritalStatus = selectResults1.getString("marital_status");
		 String mobilePhone = selectResults1.getString("mobile_phone");
		 Date joinedDate = selectResults1.getDate("joined_date");
		 Date terminatedDate = selectResults1.getDate("terminated_date");
		 String jobTitle = selectResults1.getString("job_title");
		 String department = selectResults1.getString("department");
		 int supervisorId = selectResults1.getInt("supervisor_id");
		
	     // Get the Supervisor Name
		 String selectSql2 = "select full_name from employees where employee_id =?";
		 PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
		 
	     selectStatement2.setInt(1, supervisorId);
		 ResultSet selectResults2 = selectStatement2.executeQuery();
		 
		 // Validate that the full name is not null
		 if(selectResults2.next())
	        supervisor = selectResults2.getString(1); 
			 
		 Employee employee = new Employee(employeeId, number, firstName, middleName, lastName, dob, Gender.valueOf(gender), 
			                                 MaritalStatus.valueOf(maritalStatus), mobilePhone, joinedDate, terminatedDate, 
			                                 jobTitle, department, supervisor);
			
	     employees.add(employee);
		 selectResults2.close();
		 selectStatement2.close();
						
	   }
		
		selectResults1.close();
		selectStatement1.close();

   }

   	  
   public void loadEmployee(int id) throws SQLException
   {
	  
	 // SQL Statements
	  String selectSql1 = "select employee_id, number, first_name, middle_name, last_name, dob, gender, marital_status, mobile_phone, joined_date, terminated_date, job_title, department, supervisor_id from employees where employee_id=?";
	  PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	  selectStatement1.setInt(1, id);

	  ResultSet selectResults1 = selectStatement1.executeQuery();
	  selectResults1.next();
	  
	  int employeeId = selectResults1.getInt("employee_id");
	  String number = selectResults1.getString("number");
	  String firstName = selectResults1.getString("first_name");
	  String middleName = selectResults1.getString("middle_name");
	  String lastName = selectResults1.getString("last_name");
	  Date dob = selectResults1.getDate("dob");
	  String gender = selectResults1.getString("gender");
	  String maritalStatus = selectResults1.getString("marital_status");
	  String mobilePhone = selectResults1.getString("mobile_phone");
	  Date joinedDate = selectResults1.getDate("joined_date");
	  Date terminatedDate = selectResults1.getDate("terminated_date");
	  String jobTitle = selectResults1.getString("job_title");
	  String department = selectResults1.getString("department");
	  int supervisorId = selectResults1.getInt("supervisor_id");
				
	  // Get the Supervisor Name
	  String selectSql2 = "select full_name from employees where employee_id =?";
	  PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
		 
	  selectStatement2.setInt(1, supervisorId);
	  ResultSet selectResults2 = selectStatement2.executeQuery();
	  selectResults2.next();
	  String supervisor = selectResults2.getString(1); 
	  
	  Employee employee = new Employee(id, number, firstName, middleName, lastName, dob, Gender.valueOf(gender), 
				                       MaritalStatus.valueOf(maritalStatus), mobilePhone, joinedDate, terminatedDate, 
				                       jobTitle, department, supervisor);
				
	  employees.add(employee);
				
	  selectResults1.close();
	  selectStatement1.close();
	  selectResults2.close();
	  selectStatement2.close();
	
   }
   
   // Add a new employee
   public void addEmployee(Employee employee) 
   {
        employees.add(employee); 
   }
   
   // Get an employee
   public List<Employee> getEmployees()
   {
	   return Collections.unmodifiableList(employees);
   }
   
   // Delete an employee
   public void deleteEmployee(int id)
   {
	   employees.remove(id);
   }
   
   public ArrayList<Employee> loadSupervisors() throws SQLException 
   {
      // Declare ArrayList of EmployeeBasics
	  ArrayList<Employee> employeeBasics = new ArrayList<Employee>();	 
	    
	  // Select the employees that have the rol of Manager or Admin in the HRM Software System
	  String sql = "select e.employee_id, e.number, e.full_name from employees e, users u ";
	         sql = sql + "where e.employee_id = u.employee_id and u.user_level in (";
	         sql = sql + "'Manager'" + "," + "'Admin'" + ") " ;
	         sql = sql + "order by full_name asc";
	         
	  Statement selectStatement = dbCon.createStatement();
		
	  ResultSet results = selectStatement.executeQuery(sql);
		
	  while(results.next()) 
	  {
	     int employeeId  = results.getInt("employee_id");
	     String number   = results.getString("number");
		 String fullName = results.getString("full_name");
		 
	     Employee eB = new Employee(employeeId, number, fullName); 
	     
	     // Add fields to the ArrayList.
	     employeeBasics.add(eB); 
	     
	  }
		
	  results.close();
	  selectStatement.close();
	  
      return employeeBasics;
      
   }
   
	public boolean validateDeleteEmployee1(Employee employee) throws SQLException 
	{
		boolean validDeleteEmp = false;

	    // Get the values from the GUI
		int employeeId = employee.getId();

		// SQL Statements
		String selectSql1 = "select count(*) as count_users from users where employee_id=?";
		PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
		String selectSql2 = "select count(*) as count_tr from travel_requests where employee_id=?";
		PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
		
		selectStatement1.setInt(1, employeeId);
		selectStatement2.setInt(1, employeeId);

		ResultSet selectResult1 = selectStatement1.executeQuery();
		selectResult1.next();
        int count_users = selectResult1.getInt(1);
        
        ResultSet selectResult2 = selectStatement2.executeQuery();
		selectResult2.next();
        int count_tr = selectResult2.getInt(1);
                       
       // Validate if the employee has a user or travel requests
       // it cannot be deleted 
       if (count_users >= 1 | count_tr >=1) 
    	   validDeleteEmp = false;
       else
    	   validDeleteEmp = true;

        
		selectStatement1.close();
		selectStatement2.close();
		
		return validDeleteEmp;
	} 

	public boolean validateFullName(Employee employee) throws SQLException
	{
	  
	   boolean validFullName = false;
	   
	   // Get the values from the GUI
	   int employeeId = employee.getId();
	   String firstName = employee.getFirstName();
	   String middleName = employee.getMiddleName();
	   String lastName = employee.getLastName(); 
	   String fullName;
	    
	   if(middleName != null && !middleName.isEmpty() && !middleName.trim().isEmpty())
	   {
	      fullName = firstName + " " + middleName + " " + lastName;
	   }
	   else
	      fullName = firstName + " " + lastName;
	   
	   // SQL Statements
	   String selectSql1 = "select count(employee_id) as count from employees where full_name=?";
	   PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);
	   
	   // Set the FullName from the GUI
	   selectStatement1.setString(1, fullName);
	   ResultSet selectResults1 = selectStatement1.executeQuery();
	   selectResults1.next();
	   int fullNameExists = selectResults1.getInt("count");
	   
	   // Validate if the FullName already exist for an existing Employee
	   if (fullNameExists == 1)
	   {
		   String selectSql2 = "select employee_id from employees where full_name=?";
		   PreparedStatement selectStatement2 = dbCon.prepareStatement(selectSql2);
		   
		   selectStatement2.setString(1, fullName);
		   ResultSet selectResults2 = selectStatement2.executeQuery();
		   selectResults2.next();
		   int employee_Id = selectResults2.getInt("employee_id");
		   	   
		   // Validate if the employee_id already has an existing employee in the HRM database
		   if (employeeId == employee_Id)
		      validFullName = true;	 
		   else
			   validFullName = false;
		   
		   selectResults2.close();
		   selectStatement2.close();
	   
	   }
	   else
		   validFullName = true;	 
		   
	   	   
       selectResults1.close();
	   selectStatement1.close();
	  
	   
	   return validFullName;
	} 	
	
   public int maxNumEmployee() throws SQLException
   {
      int maxEmp = 0;	   
		   
	  // SQL Statements
	  String selectSql = "SELECT MAX(CAST(SUBSTRING(e.number, LOCATE('E', e.number) + 1) AS UNSIGNED)) AS max_value ";
		     selectSql = selectSql + "FROM employees e";
		 
	  PreparedStatement selectStatement = dbCon.prepareStatement(selectSql);
	  ResultSet selectResults = selectStatement.executeQuery();
	  selectResults.next();
	  maxEmp = selectResults.getInt("max_value");
		   
	  selectResults.close();
	  selectStatement.close(); 
		  
	  return maxEmp;	  
   }
   
   public boolean validateDeleteEmployee2(Employee employee) throws SQLException 
   {
      boolean validDeleteEmp = false;

	  // Get the values from the GUI
	  int employeeId = employee.getId();

	  // SQL Statements
	  String selectSql1 = "select count(*) as count_supervisor from employees where supervisor_id=?";
	  PreparedStatement selectStatement1 = dbCon.prepareStatement(selectSql1);

	  selectStatement1.setInt(1, employeeId);

	  ResultSet selectResult1 = selectStatement1.executeQuery();
	  selectResult1.next();
      int countSupervisor = selectResult1.getInt(1);
            
      // Validate if the employee was assigned as a supervisor of other employees
      // it cannot be deleted 
      if (countSupervisor >= 1) 
   	     validDeleteEmp = false;
      else
   	     validDeleteEmp = true;

      
	  selectStatement1.close();
		
	  return validDeleteEmp;
   }   
   
}
