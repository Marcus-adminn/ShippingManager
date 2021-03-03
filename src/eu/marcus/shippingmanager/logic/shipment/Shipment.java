package eu.marcus.shippingmanager.logic.shipment;

import java.io.Serializable;

/**
 * @author Marcus
 *
 * The class creates a new not insured {@link Shipment} for a User
   in the ShippingManager application.
 */
public class Shipment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The username that creates the new shipment
	 */
	protected String username;
	/**
	 * The shipment's code
	 */
	protected String code;
	/**
	 * The shipment's date
	 */
	protected String date;
	/**
	 * The shipment's address
	 */
	protected String address;
	/**
	 * The shipment's weight
	 */
	protected String weight;
	/**
	 * The shipment's state
	 */
	protected String state;
	/**
	 * The string that represent if a {@link Shipment} is insured or not
	 */
	protected String insured;
	/**
	 *  A static variable who decides at what percentage a shipment will fail
	 */
	protected static double failedNumber;
	
	
	/**
	 * The class contains seven parameters to create a new not insured {@link Shipment}
	 * @param username the username of the user that creates this order
	 * @param code the code of the insured shipment
	 * @param date the date of the insured shipment
	 * @param address the address of the insured shipment
	 * @param weight the weight of the insured shipment
	 * @param state the state of the insured shipment
	 * @param insured the String to represent if a {@link Shipment} in insured or not
	 */
	public Shipment(String username, String code, String date, String address, String weight, String state,String insured)
	{
		this.username=username;
		this.code=code;
		this.date=date;
		this.address=address;
		this.weight=weight;
		this.state=state;
		this.insured=insured;
	}
	
	/**
	 * Get the username that creates the new shipment
	 * @return the username of the user that creates this order
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * Get the shipment's code
	 * @return code
	 */
	public String getCode()
	{
		return code;
	}
	
	/**
	 * Get the shipment's date
	 * @return date the date of the insured shipment
	 */
	public String getDate()
	{
		return date;
	}
	
	/**
	 * Get the shipment's address
	 * @return address the address of the insured shipment
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * Get the shipment's weight
	 * @return weight the weight of the insured shipment
	 */
	public String getWeight()
	{
		return weight;
	}
	
	/**
	 * Get the shipment's state
	 * @return state the state of the insured shipment
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * Get the shipment's value of Insured, that identify if a shipment is insured or not
	 * @return insured that identify if a shipment is insured or not
	 */
	public String getInsured()
	{
		return insured;
	}
	
	/**
	 * Set the shipment's state
	 * @param state the state of the insured shipment
	 */
	public void setState(String state)
	{
		this.state=state;
	}
	
	/**
	 * The method changes the not insured shipments state
	 */
	public void changeState()
	{
		failedNumber=0;
		if(state.equalsIgnoreCase("IN PREPARATION"))
			setState("IN TRANSIT");
		else if(state.equalsIgnoreCase("IN TRANSIT"))
		{
			failedNumber=Math.random();
			if(failedNumber<=0.2) setState("FAILED");
			else setState("RECEIVED");
		}
	}
	
}
