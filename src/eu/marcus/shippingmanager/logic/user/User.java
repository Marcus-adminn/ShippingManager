package eu.marcus.shippingmanager.logic.user;

import java.io.Serializable;

/**
 * @author Marcus
 *
 * The class creates a new User from {@link GenericUser} in the ShippingManager application
 */
public class User extends GenericUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The new username
	 */
	protected String name;
	/**
	 * The new user's password
	 */
	protected String password;
	/**
	 * The new user's address
	 */
	protected String address;
	
	/**
	 * The class contains three parameters and create a new {@link User}
	 * @param name the new username
	 * @param password the new user's password
	 * @param address the new user's address
	 */
	public User(String name, String password, String address)
	{
		this.name=name;
		this.password=password;
		this.address=address;
	}
	
	/**
	 * Get the new username
	 * @return name the new username
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Get the new user's password
	 * @return the new user's password
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * The method compares two users and says if they are equal or not
	 * @param user a user
	 * @return true if the two users are equal in name, password and address. Else return false
	 */
	public boolean equals(User user)
	{
		return (this.name.equals(user.name)  &&
				this.password.equals(user.password)  &&
				this.address.equals(user.address));
	}
}