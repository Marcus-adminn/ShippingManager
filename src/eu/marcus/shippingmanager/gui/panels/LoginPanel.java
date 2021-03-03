package eu.marcus.shippingmanager.gui.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

/**
 * @author Marcus
 *
 * The class contains the main structure of a login panel for Admin and User
 * of the ShippingManager application.
 */

public abstract class LoginPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The label with the "Login" inscription
	 */
	protected JLabel loginLabel;
	/**
	 * The label with the "Username" inscription
	 */
	protected JLabel usernameLabel;
	/**
	 * The label with the "Password" inscription
	 */
	protected JLabel passwordLabel;
	/**
	 * The label that shows error messages
	 */
	protected JLabel errorLabel;
	/**
	 * The editable text to enter the username
	 */
	protected JTextField usernameText;
	/**
	 * The editable text to enter the password
	 */
	protected JTextField passwordText;
	/**
	 * The sign in button for the shipment's access
	 */
	protected JButton signInButton;
	/**
	 * The button per the return to the {@link EnterPanel}
	 */
	protected JButton returnButton;
	/**
	 * The enter panel of ShippingManager applications
	 */
	protected EnterPanel enterPanel;
	
	/**
	 * The class contains three parameters and passes them to the specific
	 * class {@link AdminPanel} for the Admin login
	 * and {@link UserPanel} for the user login
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	
	public LoginPanel(ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		super(null);
		
		loginLabel=new JLabel("LOGIN");
		loginLabel.setFont(new Font("Arial",Font.PLAIN,50));
		loginLabel.setBounds(360, 180, 200, 50);
		
		usernameLabel=new JLabel("USERNAME");
		usernameLabel.setFont(new Font("Arial",Font.PLAIN,20));
		usernameLabel.setBounds(150,300,200,50);
		
		passwordLabel=new JLabel("PASSWORD");
		passwordLabel.setFont(new Font("Arial",Font.PLAIN,20));
		passwordLabel.setBounds(150,390,200,50);
		
		usernameText=new JTextField("",40);
		usernameText.setFont(new Font("Arial",Font.PLAIN,20));
		usernameText.setBounds(300,300,400,50);
		
		passwordText=new JTextField("",40);
		passwordText.setFont(new Font("Arial",Font.PLAIN,20));
		passwordText.setBounds(300,390,400,50);
		
		signInButton=new JButton("SIGN IN");
		signInButton.setFont(new Font("Arial",Font.PLAIN,12));
		signInButton.setBounds(600,490,100,30);
		
		returnButton=new JButton("RETURN");
		returnButton.setFont(new Font("Arial",Font.PLAIN,12));
		returnButton.setBounds(600, 850, 100, 30);
		
		errorLabel=new JLabel();
		errorLabel.setFont(new Font("Arial",Font.PLAIN,12));
		errorLabel.setForeground(Color.RED);
		
		add(loginLabel);
		add(usernameLabel);
		add(passwordLabel);
		add(usernameText);
		add(passwordText);
		add(signInButton);
		add(returnButton);
		add(errorLabel);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		
		returnButton.addActionListener(e->
		{

			removeAll();
			
			enterPanel=new EnterPanel(userList,shipList,insuredShipList);
			enterPanel.setSize(900,1000);
			add(enterPanel);
			
			revalidate();
			repaint();
				
		});
		
		}
	
}

