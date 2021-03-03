package eu.marcus.shippingmanager.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.ArrayList;

import eu.marcus.shippingmanager.gui.panels.EnterPanel;
import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;

/**
 * @author Marcus
 * 
 * The class contains the {@link EnterPanel enterPanel} enter panel of {@link GenericFrame} 
 */

public class GenericFrame extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The ShippingManager application enter panel
	 */
	protected EnterPanel enterPanel;
	
	/**
	 * {@link GenericFrame} takes for input three parameters  and set the generic frame of
	 * the application adding the enter panel.
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments
	 * @param insuredShipList contains the insured shipments
	 */

	public GenericFrame(ArrayList<User> userList, ArrayList<Shipment> shipList, ArrayList<InsuredShipment> insuredShipList)
	{
		super();
		
		setTitle("ShippingManager");
		setSize(900,1000);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		enterPanel=new EnterPanel(userList,shipList,insuredShipList);
		add(enterPanel);
		
		
		
		
	}
}


