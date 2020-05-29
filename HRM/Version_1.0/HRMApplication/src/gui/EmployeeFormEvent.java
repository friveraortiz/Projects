package gui;
import java.util.EventObject;
import java.util.Date;

public class EmployeeFormEvent extends EventObject 
{
   private int id;
   private String number;	
   private String firstName;
   private String middleName;
   private String lastName;
   private Date dob;
   private String gender;
   private String maritalStatus;
   private String mobilePhone;
   private Date joinedDate;
   private Date terminatedDate;
   private String jobTitle;
   private String department;
   private String supervisor;
	   
	
	public EmployeeFormEvent(Object source) 
	{
		super(source);
	}


	public EmployeeFormEvent(Object source, int id, String number, String firstName, String middleName, String lastName, Date dob,
			String gender, String maritalStatus,String mobilePhone, Date joinedDate, Date terminatedDate, 
			String jobTitle, String department,
			String supervisor) 
	{
		super(source);
		this.id = id;
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
		
	}
	
	public EmployeeFormEvent(Object source, String number, String firstName, String middleName, String lastName, Date dob,
			String gender, String maritalStatus,String mobilePhone, Date joinedDate, Date terminatedDate, 
			String jobTitle, String department,
			String supervisor) 
	{
		super(source);
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
		
	}

	public int getId() 
	{
		return id;
	}


	public void setId(int id) 
	{
		this.id = id;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public Date getJoinedDate() {
		return joinedDate;
	}


	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}


	public Date getTerminatedDate() {
		return terminatedDate;
	}


	public void setTerminatedDate(Date terminatedDate) {
		this.terminatedDate = terminatedDate;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}


}	

