package test;
import java.sql.SQLException;
import model.TravelRequestDatabase;

public class TestTravelRequestDb 
{

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException 
	{
		System.out.println("Running HRM Travel Requests Database Test ...");

		TravelRequestDatabase db = new TravelRequestDatabase();
		try 
		{
			db.connect();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
        /*
		String sDate1="18/01/2019";  
		String sDate2="24/01/2019";  
	    Date date1=null;
	    Date date2=null;
	    
		try 
		{
		   date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		   date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
		   System.out.println(sDate1+"\t"+date1); 
		}
		catch (ParseException e1) 
		{
			e1.printStackTrace();
		}
		
		float f1=2000.0000f; 
		
		try 
		{
		   	
		   db.save(new TravelRequest("7", "Andres Rafael Perez", Transportation.OwnVehicle, 
					"Research Conference Trip", "Dublin, Ireland", "Brussells, Belgium", date1, date2, 
					"Notes 123", Currency.CanadianDollars, f1, Status.Rejected, "Rejected"));
		   
		   db.save(new TravelRequest("2", "Maria Velazquez", Transportation.Airplane, 
					"Research Conference Trip", "Moscow, Russia", "Tokyo, Japan", date1, date2, 
					"Notes 1", Currency.Euros, f1, Status.Pending, "Pending"));
		   
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		
		db.delete(new TravelRequest("1", "Gerald Jones", Transportation.Airplane, 
				"Business Trip", "Paris, France", "Madrid, Spain", date1, date2, 
				"Notes", Currency.Euros, f1, Status.Pending, "Not Approved yet"));
				
		
		
		try 
		{
			db.load();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		try 
		{
			db.loadTravelRequest(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}*/
		/*
		ArrayList<EmployeeBasics> eB = new ArrayList<EmployeeBasics>();
		try 
		{
	       eB=db.loadFullNameEmployees("jperez", UserLevel.Employee);
	       System.out.println("This is the ArrayList ... ");
	       for (int i = 0; i < eB.size(); i++) 
	       {
		      System.out.println(eB.get(i));
		   }
	       
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}*/
		
		/*
		try 
		{
		   boolean validEmployee = false;
		   
		   //validEmployee=db.validateEmployee(new TravelRequest(4, "jalvarez", "123", "Japan", "Juan Rafael Alvarez", "jalvarez@hotmail.com", TravelRequestLevel.Employee));
		   //System.out.println("Valid Employee 1: "+validEmployee);
		   
		   validEmployee=db.validateEmployee(new TravelRequest(4, "jalvarez", "123", "vane", "Juan Rafael Alvarez", "jalvarez@hotmail.com", TravelRequestLevel.Employee));
		   System.out.println("Valid Employee 2: "+validEmployee);
		} 
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}*/
		
		
		//db.load("jmaperez", UserLevel.Employee); 
		//db.load("friverao", UserLevel.Admin); 
		
		int maxTR = 0;
		maxTR = db.maxNumTravelRequest();
		System.out.println("maxTR: "+maxTR);
		
		
		db.disconnect();
	
	}

}
