package model;

import java.io.Serializable;
import java.util.Date;


// This Class is to store the data, it has the same atributes as the EmployeeFormEvent Class
public class Employee implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the Employees, start with number 1
	private static int count = 1;
	
    private int id;	
    private String number;	
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String mobilePhone;
    private Date joinedDate;
    private Date terminatedDate;
    private String jobTitle;
    private String department;
    private String supervisor;
    private String fullName;
   
	
    // Constructor #1 of the Employee Class that assigns an ID
	public Employee(String number, String firstName, String middleName,
     String lastName, Date dob, Gender gender, MaritalStatus maritalStatus, 
     String mobilePhone, Date joinedDate, Date terminatedDate, String jobTitle,
     String department, String supervisor)
	{
		this.number = number;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.mobilePhone = mobilePhone;
		this.joinedDate = joinedDate;
		this.terminatedDate = terminatedDate;
		this.jobTitle = jobTitle;
	    this.department = department;
	    this.supervisor = supervisor; 
		
	    // Everytime that I create a new employee, it will increase the count counter
	    this.id = count;
	    count++;
	    
	}
	
    // Constructor #2 of the Employee Class that assigns an ID
	public Employee(int id, String number, String firstName, String middleName,
     String lastName, Date dob, Gender gender, MaritalStatus maritalStatus, 
     String mobilePhone, Date joinedDate, Date terminatedDate, String jobTitle,
     String department, String supervisor)
	{
		// Calls the Constructor #1 of the Employee Class
        this(number, firstName, middleName, lastName, dob, gender, maritalStatus, mobilePhone, joinedDate, terminatedDate, jobTitle, department, supervisor);
		this.id = id;

	}
	
    // Constructor #3 of the EmployeeBasics Class that assigns an ID
	public Employee(int id, String number, String fullName)
	{
		this.id = id;
		this.number = number;
		this.fullName = fullName;	    
	}
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getNumber() 
	{
		return number;
	}
	public void setNumber(String number) 
	{
		this.number = number;
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getMiddleName() 
	{
		return middleName;
	}
	public void setMiddleName(String middleName) 
	{
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public Date getDob() 
	{
		return dob;
	}
	public void setDob(Date dob) 
	{
		this.dob = dob;
	}
	public Gender getGender() 
	{
		return gender;
	}
	public void setGender(Gender gender) 
	{
		this.gender = gender;
	}
	public MaritalStatus getMaritalStatus() 
	{
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatus maritalStatus) 
	{
		this.maritalStatus = maritalStatus;
	}
	public String getMobilePhone() 
	{
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) 
	{
		this.mobilePhone = mobilePhone;
	}
	public Date getJoinedDate() 
	{
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) 
	{
		this.joinedDate = joinedDate;
	}
	public Date getTerminatedDate() 
	{
		return terminatedDate;
	}
	public void setTerminatedDate(Date terminatedDate) 
	{
		this.terminatedDate = terminatedDate;
	}
	public String getJobTitle() 
	{
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) 
	{
		this.jobTitle = jobTitle;
	}
	public String getDepartment() 
	{
		return department;
	}
	public void setDepartment(String department) 
	{
		this.department = department;
	}
	public String getSupervisor() 
	{
		return supervisor;
	}
	public void setSupervisor(String supervisor) 
	{
		this.supervisor = supervisor;
	}
	
	public String getFullName() 
	{
		return fullName;
	}

	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}

	@Override
	public String toString() 
	{
		return id + ": " + firstName + " "+ middleName + " "+ lastName;
	}
	
}
