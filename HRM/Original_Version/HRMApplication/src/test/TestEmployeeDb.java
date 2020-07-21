package test;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import controller.EmployeeController;
import model.Employee;
import model.EmployeeDatabase;
import model.Gender;
import model.MaritalStatus;


public class TestEmployeeDb 
{

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException 
	{
		System.out.println("Running HRM Employee Database Test ...");

		
		EmployeeDatabase db = new EmployeeDatabase();
		EmployeeController ec = new EmployeeController();
		
		/*
		try 
		{
			db.connect();
			System.out.println("CONNECTED to the HRM Database and Employee Table");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}*/
		
		System.out.println("BEFORE load employees");
		ec.load();
		System.out.println("AFTER load employees");
		
		
		
		// Convert a String to a Date
/*
		try 
		{
		    // Deleting one existing employee in the database
		    //db.addEmployee(new Employee(6, "71", "Maria", "Juana", "Sanchez1", date1, Gender.Female, MaritalStatus.Widowed, "8113304013", date1, date1, "System Manager1", "Management1", "Benito Perez"));
		    //db.delete(new Employee(6,"71", "Maria", "Juana", "Sanchez1", date1, Gender.Female, MaritalStatus.Widowed, "8113304013", date1, date1, "System Manager1", "Management1", "Benito Perez"));
			
			// Add two new employees into the database
			//db.addEmployee(new Employee("123", "German", "Pedro", "Gutierrez", date1, Gender.Female, MaritalStatus.Single, "8113304013", date1, date1, "PhD Student", "Computer Science", "Juana"));
			//db.addEmployee(new Employee("456", "Benito", "Rafael", "Palacios", date1, Gender.Male, MaritalStatus.Single, "8111283713", date1, date1, "Software Engineer", "Development", "Paul"));

			
		} 
		catch (ParseException e1) 
		{
			e1.printStackTrace();
		}  */
		
		
		String sDate1="13/02/2018";  
	    Date date1=null;
		try 
		{
		   date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} 
		catch (ParseException e1) 
		{
		   e1.printStackTrace();
		}
	    System.out.println(sDate1+"\t"+date1);  
	    
	    
	    System.out.println("BEFORE saving a new Employee");
	    
		try 
		{
		   db.save(new Employee("E000000001", "Henry", "", "Smith", date1, Gender.Male, MaritalStatus.Single, "8113304013", date1, date1, "Systems Administrator", "Development", "Henry Smith"));
		   db.save(new Employee("E000000002", "Arthur", "", "Jones", date1, Gender.Male, MaritalStatus.Single, "8113304013", date1, date1, "Manager", "Administation", "Henry Smith"));
		} 
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}
		
		System.out.println("AFTER saving a new Employee");
		
		/*
		db.load();
		
		db.loadEmployee(1);
		*/
		
		/*
		try 
		{
			db.load();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		*/
		
		/*
		try 
		{
			db.loadEmployee(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		*/
		/*
		boolean validDeleteEmp = false;
		
		*/
		
		/*
        boolean validFullName = false;
		
		validFullName=db.validateFullName(new Employee(9, "12", "Benito", "Casimiro", "Guzman", date1, Gender.Female, MaritalStatus.Widowed, "8113304013", date1, date1, "Software Engineer", "Computer Science", "Juana"));
		System.out.println("validFullName : "+validFullName);
		*/
		
		/*
	    ArrayList<Employee> eB = db.loadSupervisors();
	    //System.out.println(Arrays.toString(eB.toArray()));
	    for (int i = 0; i < eB.size(); i++) 
	    {
			System.out.println(eB.get(i));
		} */
		
		
		/*
		int maxEmp = 0;
		maxEmp = db.maxNumEmployee();
		System.out.println("maxEmp: "+maxEmp);
	
	    
		boolean validDeleteEmp = false;
		
		
				*/
		
		System.out.println("DISCONNECTED to the HRM Database and Employee Table");
		db.disconnect();
		
	
	}

}
