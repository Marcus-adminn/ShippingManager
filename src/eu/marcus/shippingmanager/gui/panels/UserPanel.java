package eu.marcus.shippingmanager.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;

/**
 * @author Marcus
 *
 * The class is created to log {@link User} to ShippingManager application.
   {@link User} can make new shipments or ask for a refund in the event of failed insured shipments. 
 */
public class UserPanel extends LoginPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The label with the "Don't have an account?" inscription
	 */
	protected JLabel dontHaveAnAccountLabel;
	/**
	 * The button that can register a new user
	 */
	protected JButton registerButton;
	/**
	 * The shipments panel for {@link User} of ShippingManager application
	 */
	protected UserSpeditionsPanel userSpeditionsPanel;
	/**
	 * The registration panel for a new {@link User} of ShippingManager application
	 */
	protected RegistrationPanel registrationPanel;
	/**
	 * 
	 */
	protected static boolean wrongParameter;
	
	/**
	 * The class contains three parameters to log as {@link User} and have access to {@link UserSpeditionsPanel}
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	
	public UserPanel(ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		super(userList,shipList,insuredShipList);
		
		dontHaveAnAccountLabel=new JLabel("Don't have an Account?");
		dontHaveAnAccountLabel.setFont(new Font("Arial",Font.PLAIN,20));
		dontHaveAnAccountLabel.setBounds(345,620,300,50);
		
		registerButton=new JButton("REGISTER");
		registerButton.setFont(new Font("Arial",Font.PLAIN,12));
		registerButton.setBounds(400,680,100,30);
		
		add(dontHaveAnAccountLabel);
		add(registerButton);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		
		signInButton.addActionListener(e->
		{
			errorLabel.setText("");
			wrongParameter=false;
			for(int i=0;i<userList.size();i++)
			{
				if(userList.get(i).getName().equals(usernameText.getText())&&userList.get(i).getPassword().equals(passwordText.getText()))
				{
					wrongParameter=true;
					removeAll();
					
					userSpeditionsPanel=new UserSpeditionsPanel(userList.get(i).getName(),userList,shipList,insuredShipList);
					userSpeditionsPanel.setSize(900,1000);
					add(userSpeditionsPanel);
					
					revalidate();
					repaint();
				}
			}
			if(wrongParameter==false)
			{
				errorLabel.setText("Wrong Username or Password! Please try again.");
				errorLabel.setBounds(300,410,400,100);
				repaint();
			}
			
		});
		
		registerButton.addActionListener(e->
		{
			removeAll();
			
			registrationPanel=new RegistrationPanel(userList,shipList,insuredShipList);
			registrationPanel.setSize(900,1000);
			add(registrationPanel);
			
			revalidate();
			repaint();		
					
		});
		
		
	}
	

}
