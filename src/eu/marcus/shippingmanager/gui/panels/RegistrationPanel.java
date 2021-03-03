package eu.marcus.shippingmanager.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;

/**
 * 
 * @author Marcus
 *
 * The class is for registering an user in the ShippingManager application.
 */

public class RegistrationPanel extends LoginPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The label with the "Registration" inscription
	 */
	protected JLabel registrationLabel;
	/**
	 * The label with the "Address" inscription
	 */
	protected JLabel addressLabel;
	/**
	 * The editable text to enter the address
	 */
	protected JTextField addressText;
	/**
	 * The button to create a new {@link User}
	 */
	protected JButton createButton;
	/**
	 * The log in panel for {@link User} in ShippingManager application
	 */
	protected UserPanel userPanel;
	/**
	 * A new {@link User}
	 */
	protected User user;
	/**
	 * A static variable that tells if the user has registered successfully or not
	 */
	protected static boolean error;
	
	/**
	 * The class contains three parameters to register a new user in ShippingManager application
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	public RegistrationPanel(ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		super(userList,shipList,insuredShipList);
		
		remove(loginLabel);
		remove(signInButton);
		revalidate();
		repaint();
		
		registrationLabel=new JLabel("REGISTRATION");
		registrationLabel.setFont(new Font("Arial",Font.PLAIN,50));
		registrationLabel.setBounds(250, 180, 400, 50);
		
		addressLabel=new JLabel("ADDRESS");
		addressLabel.setFont(new Font("Arial",Font.PLAIN,20));
		addressLabel.setBounds(150,480,200,50);
		
		addressText=new JTextField("",40);
		addressText.setFont(new Font("Arial",Font.PLAIN,20));
		addressText.setBounds(300,480,400,50);
		
		createButton=new JButton("CREATE");
		createButton.setFont(new Font("Arial",Font.PLAIN,12));
		createButton.setBounds(600,570,100,30);
		
		errorLabel=new JLabel();
		errorLabel.setFont(new Font("Arial",Font.PLAIN,12));
		
		add(registrationLabel);
		add(addressLabel);
		add(addressText);
		add(createButton);
		add(errorLabel);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		
		for (ActionListener al : super.returnButton.getActionListeners())
		    super.returnButton.removeActionListener(al);
		
		returnButton.addActionListener(e->
				{

					removeAll();
					userPanel=new UserPanel(userList,shipList,insuredShipList);
					userPanel.setSize(900,1000);
					add(userPanel);
					
					revalidate();
					repaint();
					
					
				});
		
		createButton.addActionListener(e->
		{
						
			errorLabel.setText("");
			user=new User(usernameText.getText(),passwordText.getText(),addressText.getText());
			
			error=true;
			
			for (int i=0;i<userList.size();i++) {
				if(usernameText.getText().length()==0||passwordText.getText().length()==0||addressText.getText().length()==0)
				{
					error=false;
	            	errorLabel.setText("One or more incomplete fields");
	            	errorLabel.setForeground(Color.RED);
	            	errorLabel.setBounds(350,520,200,100);
	            	repaint();
				}
				else if(userList.get(i).getName().equalsIgnoreCase(usernameText.getText()))
	            	{
	            	error=false;
	            	errorLabel.setText("User already registered, please try again");
	            	errorLabel.setForeground(Color.RED);
	            	errorLabel.setBounds(350,520,500,100);
	            	repaint();
	            	}
				
	        }
			
			if(error)
			{
			userList.add(user);
			try
	        {
	            FileOutputStream fos = new FileOutputStream("userFile");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(userList);
	            oos.close();
	            fos.close();
	        }
	        catch (IOException ioe) 
	        {
	            ioe.printStackTrace();
	        }
			
			removeAll();
			
			userPanel=new UserPanel(userList,shipList,insuredShipList);
			userPanel.setSize(900,1000);
			add(userPanel);
			
			revalidate();
			repaint(); 
			
			}			
									
		});
	
	}

}