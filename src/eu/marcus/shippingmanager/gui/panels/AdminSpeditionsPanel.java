package eu.marcus.shippingmanager.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JLabel;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;


/**
 * @author Marcus
 *
 * The class is being used to manage the user's order in the ShippingManager application.
 * The Admin can change the shipment's state of all order and delete orders when
 * the shipment's state has ended
 */
public class AdminSpeditionsPanel extends SpeditionsPanel{

	/**
	 * 
	 */  
	private static final long serialVersionUID = 1L;
	
	/**
	 * The label with "Welcome" inscription
	 */
	protected JLabel welcomeAdminLabel;
	/**
	 * The label with "Create" inscription
	 */
	protected JLabel manageYourShipmentsLabel;
	/**
	 * The Admin log in panel
	 */
	protected AdminPanel admin;
	/**
	 * The panel in witch Admin can manage all shipments
	 */
	protected AdminSpeditionsPanel adminSpeditionsPanel;
	
	/**
	 * The class contains three parameters to manage all orders by
	 * Admin in this current application
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	public AdminSpeditionsPanel(ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		
		super(userList,shipList,insuredShipList);
		
		welcomeAdminLabel=new JLabel("Welcome Admin");
		welcomeAdminLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,60));
		welcomeAdminLabel.setBounds(220,100,500,100);
		
		manageYourShipmentsLabel=new JLabel("Here you can change state with a click");
		manageYourShipmentsLabel.setFont(new Font("Arial",Font.PLAIN,30));
		manageYourShipmentsLabel.setBounds(180,200,800,50);
		
		add(welcomeAdminLabel);
		add(manageYourShipmentsLabel);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		
		for(int i=0;i<shipList.size();i++)
		{
			String col[] = {shipList.get(i).getCode(),shipList.get(i).getDate(),shipList.get(i).getAddress(),shipList.get(i).getWeight(),shipList.get(i).getState(),shipList.get(i).getInsured()};

			defaultTableModel.addRow(col);
			
		}
		
		for(int i=0;i<insuredShipList.size();i++)
		{
			
			
			String col[] = {insuredShipList.get(i).getCode(),insuredShipList.get(i).getDate(),insuredShipList.get(i).getAddress(),insuredShipList.get(i).getWeight(),insuredShipList.get(i).getState(),insuredShipList.get(i).getInsured()};

			defaultTableModel.addRow(col);
			
		}
		
		logoutButton.addActionListener(e->
		{
			
			removeAll();
			
			admin=new AdminPanel(userList,shipList,insuredShipList);
			admin.setSize(900,1000);
			add(admin);
			
			revalidate();
			repaint();
				
		});
		
		speditionsTable.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				int row=speditionsTable.rowAtPoint(e.getPoint());

				int col= 4;
				
				int index=0;
				
				if(speditionsTable.getValueAt(row, col+1).equals("NOT INSURED"))
				{
					for(int i=0;i<shipList.size();i++)
						if(shipList.get(i).getCode().equalsIgnoreCase((String) speditionsTable.getValueAt(row, 0)))
								index=i;
							
					shipList.get(index).changeState();
					try
			        {
			            FileOutputStream fos = new FileOutputStream("shipFile");
			            ObjectOutputStream oos = new ObjectOutputStream(fos);
			            oos.writeObject(shipList);
			            oos.close();
			            fos.close();
			        }
			        catch (IOException ioe) 
			        {
			            ioe.printStackTrace();
			        }
					
					if(speditionsTable.getValueAt(row, col).equals("RECEIVED")||speditionsTable.getValueAt(row, col).equals("FAILED"))
					{
						for(int i=0;i<shipList.size();i++)
							if(shipList.get(i).getCode().equalsIgnoreCase((String) speditionsTable.getValueAt(row, 0)))
									index=i;
						shipList.remove(index);
						
						try
				        {
							
							PrintWriter writers = new PrintWriter("shipFile");
							writers.print("");
							writers.close();
				            FileOutputStream fos = new FileOutputStream("shipFile");
				            ObjectOutputStream oos = new ObjectOutputStream(fos);
				            oos.writeObject(shipList);
				            oos.close();
				            fos.close();
				            
				        }
				        catch (IOException ioe) 
				        {
				            ioe.printStackTrace();
				        }
						
					}
					
					removeAll();
					
					adminSpeditionsPanel=new AdminSpeditionsPanel(userList,shipList,insuredShipList);
					adminSpeditionsPanel.setSize(900,1000);
					add(adminSpeditionsPanel);
					
					revalidate();
					repaint();
				}
				else
				{
					for(int i=0;i<insuredShipList.size();i++)
						if(insuredShipList.get(i).getCode().equalsIgnoreCase((String) speditionsTable.getValueAt(row, 0)))
							index=i;
						
					if(!insuredShipList.get(index).getState().equalsIgnoreCase("FAILED"))
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
						
						if(speditionsTable.getValueAt(row, col).equals("RECEIVED")||speditionsTable.getValueAt(row, col).equals("REFUND PAID"))
						{
							for(int i=0;i<insuredShipList.size();i++)
								if(insuredShipList.get(i).getCode().equalsIgnoreCase((String) speditionsTable.getValueAt(row, 0)))
									index=i;
							insuredShipList.remove(index);
							try
					        {
								PrintWriter writers = new PrintWriter("insuredShipFile");
								writers.print("");
								writers.close();
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
						}
						
						removeAll();
						
						adminSpeditionsPanel=new AdminSpeditionsPanel(userList,shipList,insuredShipList);
						adminSpeditionsPanel.setSize(900,1000);
						add(adminSpeditionsPanel);
						
						revalidate();
						repaint();
					}
					
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
			}
			
		});
			
	}	
	
}


