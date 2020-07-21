package controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gui.EmployeeFormEvent;
import model.Employee;
import model.Gender;
import model.MaritalStatus;
import model.EmployeeDatabase;

// This class is an intermediary between the GUI and the Model Classes
// Transform the data from the GUI in a way that the Model can understand it, because 
// Model is a Data Model to store data into a Database
public class EmployeeController 
{
	// Declare a new object for the EmployeeDatabase class
	EmployeeDatabase empDb = new EmployeeDatabase();

	// Define the method: List getEmployees which was defined in the EmployeeDatabase Class
	public List<Employee> getEmployees()
	{
		return empDb.getEmployees();
	}
	
	// Define the method: addEmployee which was defined in the EmployeeDatabase Class
	public void addEmployee(EmployeeFormEvent ev) 
	{
	     Employee employee;
		
		 // Define the variables for this method
		 int id = ev.getId();
	     String number = ev.getNumber();	
	     String firstName = ev.getFirstName();
	     String middleName = ev.getMiddleName();
	     String lastName = ev.getLastName();
	     Date dob = ev.getDob();
	     String gender = ev.getGender();
	     String maritalStatus = ev.getMaritalStatus();
	     String mobilePhone = ev.getMobilePhone();
	     Date joinedDate = ev.getJoinedDate();
	     Date terminatedDate = ev.getTerminatedDate();
	     String jobTitle = ev.getJobTitle();
	     String department = ev.getDepartment();
	     String supervisor = ev.getSupervisor();
		
	     MaritalStatus maritalStatusCat = null;
	     
	     // Define the possible values for MaritalStatusCat
	     if(maritalStatus.equals("Single")) 
	     {
	        maritalStatusCat = MaritalStatus.Single;
	     }
	     else if(maritalStatus.equals("Married")) 
		 {
		    maritalStatusCat = MaritalStatus.Married;
		 }
		 else if(maritalStatus.equals("Divorced")) 
		 {
	       maritalStatusCat = MaritalStatus.Divorced;
		 }
		 else if(maritalStatus.equals("Widowed")) 
		 {
		   maritalStatusCat = MaritalStatus.Widowed;
		 }
	    	 
	     Gender genderCat = null;
	    
	     // Define the possible values for genderCat 
	     if(gender.equals("Male")) 
	     {
	        genderCat = Gender.Male;
	     }
	     else if(gender.equals("Female")) 
		 {
		    genderCat = Gender.Female;
		 }
	    
	     if (id == 0)
	     {
	    	 employee = new Employee (number, firstName, middleName, lastName,  
			    	  dob, genderCat, maritalStatusCat, mobilePhone,  
			    	  joinedDate,  terminatedDate,  jobTitle, department,  
			    	  supervisor
			    	  );
	     }
	     else
	     {
	         employee = new Employee (id, number, firstName, middleName, lastName,  
		    	  dob, genderCat, maritalStatusCat, mobilePhone,  
		    	  joinedDate,  terminatedDate,  jobTitle, department,  
		    	  supervisor
		    	  );
	     }
	    	     
	     empDb.addEmployee(employee);
	     
	}
	
	// Define the method: delete an Employee
	public void deleteEmployee(int index) 
	{
		empDb.deleteEmployee(index);
	}
	
   public void save(EmployeeFormEvent ev) throws SQLException 
   {
	  Employee employee;
	   
      // Define the variables for this method
	  int id = ev.getId();
	  String number = ev.getNumber();	
	  String firstName = ev.getFirstName();
	  String middleName = ev.getMiddleName();
	  String lastName = ev.getLastName();
	  Date dob = ev.getDob();
	  String gender = ev.getGender();
	  String maritalStatus = ev.getMaritalStatus();
	  String mobilePhone = ev.getMobilePhone();
	  Date joinedDate = ev.getJoinedDate();
	  Date terminatedDate = ev.getTerminatedDate();
	  String jobTitle = ev.getJobTitle();
	  String department = ev.getDepartment();
	  String supervisor = ev.getSupervisor();
			
	  MaritalStatus maritalStatusCat = null;
		     
	  // Define the possible values for MaritalStatusCat
	  if(maritalStatus.equals("Single")) 
	  {
	     maritalStatusCat = MaritalStatus.Single;
	  }
	  else if(maritalStatus.equals("Married")) 
	  {
	     maritalStatusCat = MaritalStatus.Married;
	  }
	  else if(maritalStatus.equals("Divorced")) 
	  {
	     maritalStatusCat = MaritalStatus.Divorced;
	  }
	  else if(maritalStatus.equals("Widowed")) 
	  {
         maritalStatusCat = MaritalStatus.Widowed;
	  }
		    	 
	  Gender genderCat = null;
		    
	  // Define the possible values for genderCat 
	  if(gender.equals("Male")) 
	  {
	     genderCat = Gender.Male;
	  }
	  else if(gender.equals("Female")) 
	  {
         genderCat = Gender.Female;
	  }
	  
	  
	  if (id == 0)
	  {
	     employee = new Employee (number, firstName, middleName, lastName,  
			    	  dob, genderCat, maritalStatusCat, mobilePhone,  
			    	  joinedDate,  terminatedDate,  jobTitle, department,  
			    	  supervisor
			    	  );
	  }
	  else
	  {
	     employee = new Employee (id, number, firstName, middleName, lastName,  
		    	  dob, genderCat, maritalStatusCat, mobilePhone,  
		    	  joinedDate,  terminatedDate,  jobTitle, department,  
		    	  supervisor
		    	  );
	  }
	  
      try 
	  {
	     empDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 25: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
      empDb.save(employee); 
      empDb.disconnect(); 
      
   }
   
   public void delete(EmployeeFormEvent ev) throws SQLException 
   {
	  Employee employee;
	   
      // Define the variables for this method
	  int id = ev.getId();
	  String number = ev.getNumber();	
	  String firstName = ev.getFirstName();
	  String middleName = ev.getMiddleName();
	  String lastName = ev.getLastName();
	  Date dob = ev.getDob();
	  String gender = ev.getGender();
	  String maritalStatus = ev.getMaritalStatus();
	  String mobilePhone = ev.getMobilePhone();
	  Date joinedDate = ev.getJoinedDate();
	  Date terminatedDate = ev.getTerminatedDate();
	  String jobTitle = ev.getJobTitle();
	  String department = ev.getDepartment();
	  String supervisor = ev.getSupervisor();
	  
	  MaritalStatus maritalStatusCat = null;
		     
	  // Define the possible values for MaritalStatusCat
	  if(maritalStatus.equals("Single")) 
	  {
	     maritalStatusCat = MaritalStatus.Single;
	  }
	  else if(maritalStatus.equals("Married")) 
	  {
	     maritalStatusCat = MaritalStatus.Married;
	  }
	  else if(maritalStatus.equals("Divorced")) 
	  {
	     maritalStatusCat = MaritalStatus.Divorced;
	  }
	  else if(maritalStatus.equals("Widowed")) 
	  {
         maritalStatusCat = MaritalStatus.Widowed;
	  }
		    	 
	  Gender genderCat = null;
		    
	  // Define the possible values for genderCat 
	  if(gender.equals("Male")) 
	  {
	     genderCat = Gender.Male;
	  }
	  else if(gender.equals("Female")) 
	  {
         genderCat = Gender.Female;
	  }
	  
	  
	  if (id == 0)
	  {
	     employee = new Employee (number, firstName, middleName, lastName,  
			    	  dob, genderCat, maritalStatusCat, mobilePhone,  
			    	  joinedDate,  terminatedDate,  jobTitle, department,  
			    	  supervisor
			    	  );
	  }
	  else
	  {
	     employee = new Employee (id, number, firstName, middleName, lastName,  
		    	  dob, genderCat, maritalStatusCat, mobilePhone,  
		    	  joinedDate,  terminatedDate,  jobTitle, department,  
		    	  supervisor
		    	  );
	  }
	  
      try 
	  {
	     empDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 25: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

      empDb.delete(employee); 
      empDb.disconnect();
      
   }

	   
   public void load() throws SQLException 
   {
      try 
	  {
	     empDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
		 System.out.println("Error 25: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }  
      empDb.load(); 
      empDb.disconnect();
      
   }
   
   public void loadEmployee(int id) throws SQLException 
   {
      empDb.loadEmployee(id); 
   }
   
   public ArrayList<Employee> loadSupervisors() throws SQLException
   {
      try 
	  {
	     empDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 14: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }  
	      
	  ArrayList<Employee> eB = empDb.loadSupervisors();
	  empDb.disconnect();
      return eB;
   }
   
   public boolean validateEmployeeFields(EmployeeFormEvent ev)
   {
	  boolean validEmployee = false; 
	  int v = 0;
	   
      // Define the variables for this method
	  String number = ev.getNumber();	
	  String firstName = ev.getFirstName();
	  String lastName = ev.getLastName();
	  Date dob = ev.getDob();
	  String mobilePhone = ev.getMobilePhone();
	  Date joinedDate = ev.getJoinedDate();
	  Date terminatedDate = ev.getTerminatedDate();
	  String jobTitle = ev.getJobTitle();
	  String department = ev.getDepartment();
	  String supervisor = ev.getSupervisor();
			
	  // Validation: #1
	  // Validate that all the Employee Fields are not empty
	  
      // Convert from Date to String
	  
      // Define the format of the date
	  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	  // Make the conversion from Date to String
	  String dobStr = formatter.format(dob);  
	  String joinedDateStr = formatter.format(joinedDate);  
	  String terminatedDateStr = formatter.format(terminatedDate);  
	  
	  if(number != null && !number.isEmpty())
	     v++;
	
	  if(firstName != null && !firstName.isEmpty())
	     v++;

	  if(lastName != null && !lastName.isEmpty())
	     v++;
	  
	  if(mobilePhone != null && !mobilePhone.isEmpty())
	     v++;

	  if(jobTitle != null && !jobTitle.isEmpty())
	     v++;
	  
	  if(department != null && !department.isEmpty())
	     v++;
	  
	  if(dobStr != null && !dobStr.isEmpty())
	     v++;
	  
	  if(joinedDateStr != null && !joinedDateStr.isEmpty())
	     v++;

	  if(terminatedDateStr != null && !terminatedDateStr.isEmpty())
	     v++;
	  
	  if(supervisor != null && !supervisor.isEmpty() && !supervisor.trim().isEmpty())
	     v++;
	  
	  // Validate that all the employee fields have a value						     
	  if (v == 10) 
	     validEmployee = true;
	  else 
		  validEmployee = false;
	  
      return validEmployee;	     
   }   

   public boolean validateSupervisor(EmployeeFormEvent ev)
   {
	  boolean validEmployee = false; 
	  String fullName = null;
	   
      // Define the variables for this method
	  String firstName = ev.getFirstName();
	  String middleName = ev.getMiddleName();
	  String lastName = ev.getLastName();
	  
	  // Build the Full Name of the Employee
	  if(middleName != null && !middleName.isEmpty() && !middleName.trim().isEmpty())
	  {
	      fullName = firstName + " " + middleName + " " + lastName;
	  }
	  else
	     fullName = firstName + " " + lastName;
	  
	  // Get the Supervisor
	  String supervisor = ev.getSupervisor();
	  
	  // Validation: #2	
	  // Validate that the employee full name is different from the supervisor
	  if (fullName.equals(supervisor))
	  {
	     validEmployee = false;	  
	  }
	  else
	     validEmployee = true;
	  
      return validEmployee;	     
   }   

   public boolean validateDobJdDates(EmployeeFormEvent ev)
   {
	  boolean validEmployee = false; 
	   
      // Define the variables for this method	  
	  Date dob = ev.getDob();
	  Date joinedDate = ev.getJoinedDate();
	  
	  // Validation: #3
	  // Validate that the date of birth and joined date are not the same
	  if(dob.equals(joinedDate))
	  {
		  validEmployee = false;
      }
	  else
		  // Validate that the date of birth is greater than the joined Date
		  if (dob.before(joinedDate))
		     validEmployee = true;
		  else
			  validEmployee = false; 
		  

      return validEmployee;	     
   }   
   
   public boolean validateJdTdDates(EmployeeFormEvent ev)
   {
	  boolean validEmployee = false; 
	   
      // Define the variables for this method
	  Date joinedDate = ev.getJoinedDate();
	  Date terminatedDate = ev.getTerminatedDate();
	  
	  // Validation: #4
	  // Validate that the joined date and terminated date are not the same
	  if(joinedDate.equals(terminatedDate))
	  {
		  validEmployee = false;
      }
	  else
		  // Validate that the terminated Date is greater than the joined Date
		  if (joinedDate.before(terminatedDate))
		     validEmployee = true;
		  else
			  validEmployee = false; 
		  

      return validEmployee;	     
   }  
   
   public boolean validateDeleteEmployee1(EmployeeFormEvent ev) throws SQLException
   {
	  Employee employee; 
	  boolean validDeleteEmp = false; 
	   
      // Define the variables for this method
	  int id = ev.getId();
	  String number = ev.getNumber();	
	  String firstName = ev.getFirstName();
	  String middleName = ev.getMiddleName();
	  String lastName = ev.getLastName();
	  Date dob = ev.getDob();
	  String gender = ev.getGender();
	  String maritalStatus = ev.getMaritalStatus();
	  String mobilePhone = ev.getMobilePhone();
	  Date joinedDate = ev.getJoinedDate();
	  Date terminatedDate = ev.getTerminatedDate();
	  String jobTitle = ev.getJobTitle();
	  String department = ev.getDepartment();
	  String supervisor = ev.getSupervisor();
			
	  MaritalStatus maritalStatusCat = null;
		     
	  // Define the possible values for MaritalStatusCat
	  if(maritalStatus.equals("Single")) 
	  {
	     maritalStatusCat = MaritalStatus.Single;
	  }
	  else if(maritalStatus.equals("Married")) 
	  {
	     maritalStatusCat = MaritalStatus.Married;
	  }
	  else if(maritalStatus.equals("Divorced")) 
	  {
	     maritalStatusCat = MaritalStatus.Divorced;
	  }
	  else if(maritalStatus.equals("Widowed")) 
	  {
         maritalStatusCat = MaritalStatus.Widowed;
	  }
		    	 
	  Gender genderCat = null;
		    
	  // Define the possible values for genderCat 
	  if(gender.equals("Male")) 
	  {
	     genderCat = Gender.Male;
	  }
	  else if(gender.equals("Female")) 
	  {
         genderCat = Gender.Female;
	  }
	  
	  if (id == 0)
	  {
	     employee = new Employee (number, firstName, middleName, lastName,  
			    	  dob, genderCat, maritalStatusCat, mobilePhone,  
			    	  joinedDate,  terminatedDate,  jobTitle, department,  
			    	  supervisor
			    	  );
	  }
	  else
	  {
	     employee = new Employee (id, number, firstName, middleName, lastName,  
		    	  dob, genderCat, maritalStatusCat, mobilePhone,  
		    	  joinedDate,  terminatedDate,  jobTitle, department,  
		    	  supervisor
		    	  );
	  }
	  
	  
      try 
	  {
	     empDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 25: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }
	  validDeleteEmp =  empDb.validateDeleteEmployee1(employee); 

      empDb.disconnect();

	  return validDeleteEmp;
      
   }   
   
   public boolean validateFullName(EmployeeFormEvent ev) throws SQLException
   {
      Employee employee; 
	  boolean validFullName = false; 
		   
	  // Define the variables for this method
	  int id = ev.getId();
	  String number = ev.getNumber();	
	  String firstName = ev.getFirstName();
	  String middleName = ev.getMiddleName();
	  String lastName = ev.getLastName();
	  Date dob = ev.getDob();
	  String gender = ev.getGender();
	  String maritalStatus = ev.getMaritalStatus();
	  String mobilePhone = ev.getMobilePhone();
	  Date joinedDate = ev.getJoinedDate();
	  Date terminatedDate = ev.getTerminatedDate();
	  String jobTitle = ev.getJobTitle();
	  String department = ev.getDepartment();
	  String supervisor = ev.getSupervisor();
				
	  MaritalStatus maritalStatusCat = null;
			     
	  // Define the possible values for MaritalStatusCat
	  if(maritalStatus.equals("Single")) 
	  {
	     maritalStatusCat = MaritalStatus.Single;
	  }
	  else if(maritalStatus.equals("Married")) 
	  {
	     maritalStatusCat = MaritalStatus.Married;
	  }
	  else if(maritalStatus.equals("Divorced")) 
	  {
         maritalStatusCat = MaritalStatus.Divorced;
	  }
	  else if(maritalStatus.equals("Widowed")) 
	  {
	     maritalStatusCat = MaritalStatus.Widowed;
	  }
			    	 
	  Gender genderCat = null;
			    
      // Define the possible values for genderCat 
	  if(gender.equals("Male")) 
	  {
	     genderCat = Gender.Male;
	  }
	  else if(gender.equals("Female")) 
	  {
	     genderCat = Gender.Female;
	  }
		  
	  if (id == 0)
	  {
	     employee = new Employee (number, firstName, middleName, lastName,  
				    	  dob, genderCat, maritalStatusCat, mobilePhone,  
				    	  joinedDate,  terminatedDate,  jobTitle, department,  
				    	  supervisor
				    	  );
	  }
	  else
	  {
	     employee = new Employee (id, number, firstName, middleName, lastName,  
			    	  dob, genderCat, maritalStatusCat, mobilePhone,  
			    	  joinedDate,  terminatedDate,  jobTitle, department,  
			    	  supervisor
			    	  );
	  }


      try 
	  {
	     empDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 25: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

      // Validation: #5
	  // Validate that the FullName doesn't exist already for an existing Employee
	  validFullName = empDb.validateFullName(employee); 
      empDb.disconnect();

	  
	  return validFullName;
      
   } 
   
   public int maxNumEmployee() throws SQLException
   {
      int maxEmp = 0;
	  
      try 
	  {
	     empDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 88: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

      maxEmp = empDb.maxNumEmployee();
      
      empDb.disconnect();
      
      return maxEmp;
   }
   
   
   public boolean validateDeleteEmployee2(EmployeeFormEvent ev) throws SQLException
   {
	  Employee employee; 
	  boolean validDeleteEmp = false; 
	   
      // Define the variables for this method
	  int id = ev.getId();
	  String number = ev.getNumber();	
	  String firstName = ev.getFirstName();
	  String middleName = ev.getMiddleName();
	  String lastName = ev.getLastName();
	  Date dob = ev.getDob();
	  String gender = ev.getGender();
	  String maritalStatus = ev.getMaritalStatus();
	  String mobilePhone = ev.getMobilePhone();
	  Date joinedDate = ev.getJoinedDate();
	  Date terminatedDate = ev.getTerminatedDate();
	  String jobTitle = ev.getJobTitle();
	  String department = ev.getDepartment();
	  String supervisor = ev.getSupervisor();
			
	  MaritalStatus maritalStatusCat = null;
		     
	  // Define the possible values for MaritalStatusCat
	  if(maritalStatus.equals("Single")) 
	  {
	     maritalStatusCat = MaritalStatus.Single;
	  }
	  else if(maritalStatus.equals("Married")) 
	  {
	     maritalStatusCat = MaritalStatus.Married;
	  }
	  else if(maritalStatus.equals("Divorced")) 
	  {
	     maritalStatusCat = MaritalStatus.Divorced;
	  }
	  else if(maritalStatus.equals("Widowed")) 
	  {
         maritalStatusCat = MaritalStatus.Widowed;
	  }
		    	 
	  Gender genderCat = null;
		    
	  // Define the possible values for genderCat 
	  if(gender.equals("Male")) 
	  {
	     genderCat = Gender.Male;
	  }
	  else if(gender.equals("Female")) 
	  {
         genderCat = Gender.Female;
	  }
	  
	  if (id == 0)
	  {
	     employee = new Employee (number, firstName, middleName, lastName,  
			    	  dob, genderCat, maritalStatusCat, mobilePhone,  
			    	  joinedDate,  terminatedDate,  jobTitle, department,  
			    	  supervisor
			    	  );
	  }
	  else
	  {
	     employee = new Employee (id, number, firstName, middleName, lastName,  
		    	  dob, genderCat, maritalStatusCat, mobilePhone,  
		    	  joinedDate,  terminatedDate,  jobTitle, department,  
		    	  supervisor
		    	  );
	  }
	  
      try 
	  {
	     empDb.connect();
	  } 
	  catch (Exception e) 
	  {
	     String errorMessage = e.getMessage();	
	     System.out.println("Error 25: Occurred while connecting to the Database. Error Message: " + errorMessage);
	  }

	  validDeleteEmp =  empDb.validateDeleteEmployee2(employee); 
	  empDb.disconnect();
	  
	  return validDeleteEmp;
      
   }   
  

}
