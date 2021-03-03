package eu.marcus.shippingmanager.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;


/**
 * @author Marcus
 * 
 * The class is being used to manage the user's order in the ShippingManager application.
   A user can click on the failed insured shipment row to ask for a refund.
 */
public class UserSpeditionsPanel extends SpeditionsPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The button that shows all shipments
	 */
	protected JButton allSpeditionsButton;
	/**
	 * The button to create a new shipments
	 */
	protected JButton newSpeditionsButton;
	/**
	 * The label with the "Welcome" inscription
	 */
	protected JLabel welcomeLabel;
	/**
	 * The label with the guidelines for modifying shipments
	 */
	protected JLabel manageYourOrderLabel;
	/**
	 * The log in {@link User} panel
	 */
	protected UserPanel userPanel;
	/**
	 * The users order panel
	 */
	protected UserSpeditionsPanel userSpeditionsPanel;
	/**
	 * The panel where a user can create his order
	 */
	protected CreateSpeditionsPanel createSpeditionsPanel;
	
	/**
	 * The class contains four parameters to manage the {@link User}'s shipments.
	 * @param name the username
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	
	public UserSpeditionsPanel(String name,ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		
		super(userList,shipList,insuredShipList);
		
		welcomeLabel=new JLabel("Welcome");
		welcomeLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,70));
		welcomeLabel.setBounds(290,50,300,100);
		
		manageYourOrderLabel=new JLabel("Click on the failed insured shipment row to ask for a refund");
		manageYourOrderLabel.setFont(new Font("Arial",Font.PLAIN,20));
		manageYourOrderLabel.setBounds(174,150,600,50);
		
		allSpeditionsButton=new JButton("All Speditions");
		allSpeditionsButton.setFont(new Font("Arial",Font.PLAIN,15));
		allSpeditionsButton.setBounds(200,240,150,40);
		
		newSpeditionsButton=new JButton("New Speditions");
		newSpeditionsButton.setFont(new Font("Arial",Font.PLAIN,15));
		newSpeditionsButton.setBounds(550,240,150,40);
		
		add(allSpeditionsButton);
		add(newSpeditionsButton);
		add(welcomeLabel);
		add(manageYourOrderLabel);
		
		allSpeditionsButton.setEnabled(false);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		

		for(int i=0;i<shipList.size();i++)
		{
			if(name.equals(shipList.get(i).getUsername()))
			{
				String col[] = {shipList.get(i).getCode(),shipList.get(i).getDate(),shipList.get(i).getAddress(),shipList.get(i).getWeight(),shipList.get(i).getState(),"NOT INSURED"};

				defaultTableModel.addRow(col);
			}
			
		}
		
		for(int i=0;i<insuredShipList.size();i++)
		{
			
			if(name.equals(insuredShipList.get(i).getUsername()))
			{
				String col[] = {insuredShipList.get(i).getCode(),insuredShipList.get(i).getDate(),insuredShipList.get(i).getAddress(),insuredShipList.get(i).getWeight(),insuredShipList.get(i).getState(),"INSURED"};

				defaultTableModel.addRow(col);
			}
			
		}
		
		speditionsTable.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				int row=speditionsTable.rowAtPoint(e.getPoint());

				int col= 4;
				
				int index=0;
				
				if(speditionsTable.getValueAt(row, col).equals("FAILED"))
				{
					for(int i=0;i<insuredShipList.size();i++)
						if(insuredShipList.get(i).getCode().equalsIgnoreCase((String) speditionsTable.getValueAt(row, 0)))
							index=i;
					if(insuredShipList.get(index).getState().equalsIgnoreCase("FAILED"))
					{
						insuredShipList.get(index).changeState();
						try
				        {
				            FileOutputStream fos = new FileOutputStream("insuredShipFile");
				            ObjectOutputStream oos = new ObjectOutputStream(fos);
				            oos.writeObject(insuredShipList);
				            oos.close();
				            fos.close();
				        }
				        catch (IOException ioe) 
				        {
				            ioe.printStackTrace();
				        }
						removeAll();
						userSpeditionsPanel=new UserSpeditionsPanel(name,userList,shipList,insuredShipList);
						userSpeditionsPanel.setSize(900,1000);
						add(userSpeditionsPanel);
						
						revalidate();
						repaint();
				}
				
			}
			}

			@Override
			public void mouseEntered(MouseEvent args0) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent args0) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent args0) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent args0) {
				
				
			}
	
		});
		
		newSpeditionsButton.addActionListener(e->
		{
			removeAll();
			createSpeditionsPanel=new CreateSpeditionsPanel(name,userList,shipList,insuredShipList);
			createSpeditionsPanel.setSize(900,1000);
			add(createSpeditionsPanel);
			
			revalidate();
			repaint();
		}
		
	);
		
		logoutButton.addActionListener(e->
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