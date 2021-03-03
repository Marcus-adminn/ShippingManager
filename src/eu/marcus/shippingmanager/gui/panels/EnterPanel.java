package eu.marcus.shippingmanager.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;

/**
 * @author Marcus
 *
 * The class contains the enter panel of the ShippingManager application.
 * Here you can choose whether to verify the credentials as Admin or User.
 */
public class EnterPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The label with the "ShippingManager" title inscription
	 */
	protected JLabel title;
	/**
	 * The label with the "Enter" inscription
	 */
	protected JLabel enterLabel;
	/**
	 *  The label that contains the ShippingManager logo
	 */
	protected JLabel logo;
	/**
	 * The button for the Admin credentials verify
	 */
	protected JButton adminButton;
	/**
	 * The button for the user credentials verify
	 */
	protected JButton userButton;
	/**
	 * The image of the ShippingManager application group.
	 */
	protected BufferedImage completeLogo;
	/**
	 * The Admin log in panel
	 */
	protected AdminPanel adminPanel;
	/**
	 * The User login panel
	 */
	protected UserPanel userPanel;
	
	/**
	 * The class contains three parameters to enter at the log in panel
	 * as Admin or User
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	public EnterPanel(ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		super(null);
		
		title=new JLabel("SHIPPINGMANAGER");
		title.setFont(new Font("Bauhaus 93",Font.PLAIN,64));
		title.setBounds(170,100, 900, 100);
		
		enterLabel=new JLabel("PLEASE ENTER");
		enterLabel.setFont(new Font("Arial",Font.PLAIN,35));
		enterLabel.setBounds(320,280,300,100);
		
		adminButton=new JButton("Admin");
		adminButton.setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		adminButton.setFont(new Font("Arial",Font.PLAIN,35));
		adminButton.setBounds(350,380,200,100);
		
		userButton=new JButton("User");
		userButton.setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		userButton.setFont(new Font("Arial",Font.PLAIN,35));
		userButton.setBounds(350,500,200,100);
		
		try
		{
			completeLogo=ImageIO.read(new File("completeLogo.png"));
		}
		catch (IOException e)
		{
			System.out.println("Error during loading of completeLogo");
		}
		
		logo = new JLabel(new ImageIcon(completeLogo));
		logo.setBounds(60,600,800,400);
		
		add(title);
		add(enterLabel);
		add(adminButton);
		add(userButton);
		add(logo);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		
		adminButton.addActionListener(e->
				{
					
					removeAll();
					
					adminPanel=new AdminPanel(userList,shipList,insuredShipList);
					adminPanel.setSize(900,1000);
					add(adminPanel);
					
					revalidate();
					repaint();
			
				});
		
		userButton.addActionListener(e->
				{
			
					removeAll();
					
					userPanel=new UserPanel(userList,shipList,insuredShipList);
					userPanel.setSize(900,1000);
					add(userPanel);
					
					revalidate();
					repaint();
					
				});
		
	}

}
