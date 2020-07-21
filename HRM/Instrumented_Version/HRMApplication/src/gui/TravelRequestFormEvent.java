package gui;
import java.util.EventObject;
import java.util.Date;

public class TravelRequestFormEvent extends EventObject 
{

   private int id;
   private String number;	
   private String employee;
   private String transportation;
   private String purpose;
   private String travelFrom;
   private String travelTo;
   private Date travelDate;
   private Date returnDate;
   private String notes;
   private String currency;
   private float totalFunding;
   private String status;
   private String statusNotes;
	

   public TravelRequestFormEvent(Object source) 
	{
		super(source);
	}


	public TravelRequestFormEvent(Object source, int id, String number, String employee, String transportation, 
			String purpose, String travelFrom, String travelTo, Date travelDate, Date returnDate, 
			String notes, String currency, float totalFunding, String status, String statusNotes) 
	{
		super(source);
		this.id = id;
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

	}
	
	public TravelRequestFormEvent(Object source, String number, String employee, String transportation, 
			String purpose, String travelFrom, String travelTo, Date travelDate, Date returnDate, 
			String notes, String currency, float totalFunding, String status, String statusNotes) 
	{
		super(source);
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


	public String getTransportation() 
	{
		return transportation;
	}


	public void setTransportation(String transportation) 
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


	public String getCurrency() 
	{
		return currency;
	}


	public void setCurrency(String currency) 
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


	public String getStatus() 
	{
		return status;
	}


	public void setStatus(String status) 
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


}	

