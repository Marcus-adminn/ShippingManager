package eu.marcus.shippingmanager.logic.shipment;

import java.io.Serializable;

/**
 * @author Marcus
 *
 * The class creates a new insured {@link Shipment} for a User
   in the ShippingManager application.
 */
public class InsuredShipment extends Shipment implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The Assicured Value
	 */
	protected String assicuredValue;
	
	/**
	 * The class has seven parameters to create a new insured {@link Shipment}
	 * @param username the username of the user that creates this order
	 * @param code the code of the insured shipment
	 * @param date the date of the insured shipment
	 * @param address the address of the insured shipment
	 * @param weight the weight of the insured shipment
	 * @param state the state of the insured shipment
	 * @param assicuredValue the insured value of the insured shipment
	 * @param insured the String to represent if a {@link Shipment} in insured or not
	 */
	public InsuredShipment(String username, String code, String date, String address, String weight, String state,String assicuredValue,String insured)
	{
		super(username,code,date,address,weight,state,insured);
		this.assicuredValue=assicuredValue;
	}
	
	/**
	 * The method changes the insured shipments state
	 */
	@Override
	public void changeState()
	{
		failedNumber=0;
		if(this.state.equalsIgnoreCase("IN PREPARATION"))
			setState("IN TRANSIT");
		else if(this.state.equalsIgnoreCase("IN TRANSIT"))
		{
			failedNumber=Math.random();
			if(failedNumber<=0.2) setState("FAILED");
			else setState("RECEIVED");
		}
		else if(this.state.equalsIgnoreCase("FAILED"))
			setState("REFUND REQUIRED");
		else if(this.state.equalsIgnoreCase("REFUND REQUIRED"))
			setState("REFUND PAID");
	}
	
}
