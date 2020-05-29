package test;


import java.sql.SQLException;
import model.ModuleDatabase;
import controller.ModuleController;

public class TestModuleDb 
{
	

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Running HRM Module Database Test ...");

        ModuleController moduleController = new ModuleController();
		ModuleDatabase db = new ModuleDatabase();
		
		try 
		{
			db.connect();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		
		try 
		{
			//db.saveModules();
			moduleController.saveModules();
			System.out.println("Modules saved in the DB using the Controller ...");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			String errorMessage = e.getMessage();
			System.out.println("Error Message: "+errorMessage);
		}
		
		
		/*
		try 
		{
		   String[][] A = new String[3][4];
		   
	       A = db.getSubModules(new Module("Employees", "Authorize", UserLevel.Manager));
	       System.out.println(Arrays.deepToString(A));
	       
		   for (String[] a : A) 
		   { 
			   for (String k : a) 
		       { 
			      System.out.print(k + "\t"); 
		       } 
		       System.out.println("\n"); 
		   }
		   
		} 
		catch (SQLException e) 
		{
		   e.printStackTrace();
		   String errorMessage = e.getMessage();
		   System.out.println("Error Message: "+errorMessage);
		}*/
		
		
		/*
		String[] A = new String[6];
	    try 
	    {
			A = moduleController.getModules(new ModuleFormEvent("TestModuleDb","Employees", "Authorize", UserLevel.Admin.toString()));
		    System.out.println(Arrays.toString(A));
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
			String errorMessage = e.getMessage();
			System.out.println("Error Message: "+errorMessage);
	    }*/
		
	/*
		String[][] A = new String[3][4];
	    try 
	    {
			A = moduleController.getSubModules(new ModuleFormEvent("TestModuleDb","Employees", "Authorize", UserLevel.Employee.toString()));
		    System.out.println(Arrays.deepToString(A));
		    
			   // now let's print a two dimensional array in Java 
			   for (String[] a : A) 
			   { 
				   for (String k : a) 
			       { 
				      System.out.print(k + "\t"); 
			       } 
			       System.out.println("\n"); 
			   }
		} 
	    catch (SQLException e) 
	    {
			e.printStackTrace();
			String errorMessage = e.getMessage();
			System.out.println("Error Message: "+errorMessage);
	    }
       */
		
		//moduleController.disconnect();
	 	db.disconnect();
	
	}

}
