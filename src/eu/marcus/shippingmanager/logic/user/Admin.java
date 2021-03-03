package eu.marcus.shippingmanager.logic.user;

/**
 * @author Marcus
 *
 * The class creates the {@link Admin} who can manage all shipments of ShippingManager application
 */
public class Admin extends GenericUser{
	
	/**
	 * The admin's name
	 */
	protected String name;
	/**
	 * The admin's password
	 */
	protected String password;
	
	/**
	 * The class creates the {@link Admin}
	 */
	public Admin()
	{
		super();
		this.name="ADMIN_USER";
		this.password="ADMIN_PASSWORD";
		this.admin=true;
	}
	
	/**
	 * Get the admin's name
	 * @return name the admin name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Get the admin's password
	 * @return password the password
	 */
	public String getPassword()
	{
		return password;
	}
	
	
}