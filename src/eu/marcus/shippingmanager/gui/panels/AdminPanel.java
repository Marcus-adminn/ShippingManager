package eu.marcus.shippingmanager.gui.panels;

import java.awt.Color;
import java.util.ArrayList;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.Admin;
import eu.marcus.shippingmanager.logic.user.User;

/**
 * @author Marcus
 * 
 * The class is created to log {@link Admin} to ShippingManager application.
 * {@link Admin} manages the shipments. 
 */

public class AdminPanel extends LoginPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The {@link Admin} of the application
	 */
	protected Admin adminUser;
	/**
	 * The shipments's panel for the {@link Admin}
	 */
	protected AdminSpeditionsPanel adminSpeditionsPanel;
	
	/**
	 * {@link AdminPanel} takes in input three parameters to log {@link Admin} to
	 * ShippingManager application
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	
	public AdminPanel(ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		super(userList,shipList,insuredShipList);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		
		signInButton.addActionListener(e->
				{
					
					adminUser=new Admin();
					errorLabel.setText("");
					if(usernameText.getText().equals(adminUser.getName())&&passwordText.getText().equals(adminUser.getPassword()))
						{
							removeAll();
							adminSpeditionsPanel=new AdminSpeditionsPanel(userList,shipList,insuredShipList);
							adminSpeditionsPanel.setSize(900,1000);
							add(adminSpeditionsPanel);
							
							revalidate();
							repaint();
						}
					else if(usernameText.getText().equals(adminUser.getName())&&!passwordText.getText().equals(adminUser.getPassword()))
					{
						
						errorLabel.setText("Wrong Password! Please try again.");
						errorLabel.setBounds(344,410,400,100);
						repaint();
					}
					else if(!usernameText.getText().equals(adminUser.getName())&&passwordText.getText().equals(adminUser.getPassword()))
					{
						errorLabel.setText("Wrong Username! Please try again.");
						errorLabel.setBounds(344,410,400,100);
						repaint();
					}
					else
					{
						errorLabel.setText("Wrong Username and Password! Please try again.");
						errorLabel.setBounds(300,410,400,100);
						repaint();
					}
					
				});
		
	}
	
}
