package model;

import java.io.Serializable;
import java.util.Date;


// This Class is to store the data, it has the same atributes as the TravelRequestFormEvent Class
public class TravelRequest implements Serializable 
{

	private static final long serialVersionUID = -542727119035768593L;

	// Counter for the Travel Requests, start with number 1
	private static int count = 1;
	
	private int id;
	private String number;	
	private String employee;
	private Transportation transportation;
	private String purpose;
	private String travelFrom;
	private String travelTo;
	private Date travelDate;
	private Date returnDate;
	private String notes;
	private Currency currency;
	private float totalFunding;
	private Status status;
	private String statusNotes;
   
	
    // Constructor #1 of the TravelRequest Class that assigns an ID
	public TravelRequest(String number, String employee, Transportation transportation, 
			String purpose, String travelFrom, String travelTo, Date travelDate, Date returnDate, 
			String notes, Currency currency, float totalFunding, Status status, String statusNotes)
	{
		
		this.number = number;
		this.employee = employee;
		this.transportation = transportation;
		this.purpose = purpose;
		this.travelFrom = travelFrom;
		this.travelTo = travelTo;
		this.travelDate = travelDate;
		this.returnDate = returnDate;
		this.notes = notes;
		this.currency = currency;
		this.totalFunding = totalFunding;
		this.status = status;
		this.statusNotes = statusNotes;
		
	    // Everytime that I create a new travel request, it will increase the count counter
	    this.id = count;
	    count++;
	    
	}
	
    // Constructor #2 of the Travel Request Class that assigns an ID
	public TravelRequest(int id, String number, String employee, Transportation transportation, 
			String purpose, String travelFrom, String travelTo, Date travelDate, Date returnDate, 
			String notes, Currency currency, float totalFunding, Status status, String statusNotes)
	{
		// Calls the Constructor #1 of the Travel Request Class
        this(number, employee, transportation, purpose, travelFrom, travelTo, travelDate,
        	returnDate, notes, currency, totalFunding, status, statusNotes);
		
		this.id = id;

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

	public String getEmployee() 
	{
		return employee;
	}

	public void setEmployee(String employee) 
	{
		this.employee = employee;
	}

	public Transportation getTransportation() 
	{
		return transportation;
	}

	public void setTransportation(Transportation transportation) 
	{
		this.transportation = transportation;
	}

	public String getPurpose() 
	{
		return purpose;
	}

	public void setPurpose(String purpose) 
	{
		this.purpose = purpose;
	}

	public String getTravelFrom() 
	{
		return travelFrom;
	}

	public void setTravelFrom(String travelFrom) 
	{
		this.travelFrom = travelFrom;
	}

	public String getTravelTo() 
	{
		return travelTo;
	}

	public void setTravelTo(String travelTo) 
	{
		this.travelTo = travelTo;
	}

	public Date getTravelDate() 
	{
		return travelDate;
	}

	public void setTravelDate(Date travelDate) 
	{
		this.travelDate = travelDate;
	}

	public Date getReturnDate() 
	{
		return returnDate;
	}

	public void setReturnDate(Date returnDate) 
	{
		this.returnDate = returnDate;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes = notes;
	}


	public Currency getCurrency() 
	{
		return currency;
	}

	public void setCurrency(Currency currency) 
	{
		this.currency = currency;
	}

	public float getTotalFunding() 
	{
		return totalFunding;
	}

	public void setTotalFunding(float totalFunding) 
	{
		this.totalFunding = totalFunding;
	}


	public Status getStatus() 
	{
		return status;
	}

	public void setStatus(Status status) 
	{
		this.status = status;
	}

	public String getStatusNotes() 
	{
		return statusNotes;
	}

	public void setStatusNotes(String statusNotes) 
	{
		this.statusNotes = statusNotes;
	}

	@Override
	public String toString() 
	{
		return id + ": " + number + " "+ employee + " "+ status;
	}
	
}
