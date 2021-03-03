package eu.marcus.shippingmanager.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;

/**
 * @author Marcus
 *
 * The class where a user can create his order in the ShippingManager application.
 */

public class CreateSpeditionsPanel extends UserSpeditionsPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The label with "Create" inscription
	 */
	protected JLabel createNewOrderLabel;
	/**
	 * The label with "Code" inscription
	 */
	protected JLabel codeLabel;
	/**
	 * The label with "Date" inscription
	 */
	protected JLabel dateLabel;
	/**
	 * The label with "Address" inscription
	 */
	protected JLabel addressLabel;
	/**
	 * The label with "Weight" inscription
	 */
	protected JLabel weightLabel;
	/**
	 * The label with "State" inscription
	 */
	protected JLabel statusLabel;
	/**
	 * The label with "Insured Value" inscription
	 */
	protected JLabel insuredValueLabel;
	/**
	 * The label that shows error messages
	 */
	protected JLabel errorLabel;
	/**
	 * The non-editable text that contains the Code
	 */
	protected JTextField codeText;
	/**
	 * The non-editable text that contains the Date
	 */
	protected JTextField dateText;
	/**
	 * The editable text to enter the Address
	 */
	protected JTextField addressText;
	/**
	 * The editable text to enter the Weight
	 */
	protected JTextField weightText;
	/**
	 * The non-editable text that contains the State
	 */
	protected JTextField stateText;
	/**
	 * It contains the Insured Value if a shipment is insured
	 */
	protected JTextField insuredValueText;
	/**
	 * The checkbox if a user want to insured his shipment
	 */
	protected JCheckBox assicurateCheckBox;
	/**
	 * The button for create a new order
	 */
	protected JButton createSpeditionButton;
	/**
	 * A new Shipment
	 */
	protected Shipment ship;
	/**
	 * A new InsuredShipments
	 */
	protected InsuredShipment insuredShip;
	/**
	 * The panel witch a user can manage his shipments
	 */
	protected UserSpeditionsPanel userSpeditionsPanel;
	/**
	 * The Date of the shipment
	 */
	protected Date date;
	
	/**
	 * The class has four parameters to create a new order in ShippingManager application
	 * @param name represents the name of the user that is creating  the new shipment
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	public CreateSpeditionsPanel(String name,ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		
		
		super(name,userList,shipList,insuredShipList);
		
		removeAll();
		
		allSpeditionsButton.setEnabled(true);
		newSpeditionsButton.setEnabled(false);
		
		add(allSpeditionsButton);
		add(newSpeditionsButton);
		
		revalidate();
		repaint();
		
		errorLabel=new JLabel("");
		errorLabel.setFont(new Font("Arial",Font.PLAIN,12));
		errorLabel.setForeground(Color.RED);
		
		createNewOrderLabel=new JLabel("Create your new order");
		createNewOrderLabel.setBounds(190,100,500,100);
		createNewOrderLabel.setFont(new Font("Arial",Font.PLAIN,50));
		
		codeLabel=new JLabel("CODE: ");
		codeLabel.setBounds(128,300,500,100);
		codeLabel.setFont(new Font("Arial",Font.PLAIN,20));
		
		codeText=new JTextField(createCode(name),20);
		codeText.setBounds(328,330,400,30);
		codeText.setFont(new Font("Arial",Font.PLAIN,20));
		codeText.setEditable(false);
		
		dateLabel=new JLabel("DATE: ");
		dateLabel.setBounds(128,360,500,100);
		dateLabel.setFont(new Font("Arial",Font.PLAIN,20));
		
		dateText=new JTextField(toStringDate(),20);
		dateText.setBounds(328,390,400,30);
		dateText.setFont(new Font("Arial",Font.PLAIN,20));
		dateText.setEditable(false);
		
		addressLabel=new JLabel("ADDRESS: ");
		addressLabel.setBounds(128,420,500,100);
		addressLabel.setFont(new Font("Arial",Font.PLAIN,20));
		
		addressText=new JTextField();
		addressText.setBounds(328,450,400,30);
		addressText.setFont(new Font("Arial",Font.PLAIN,20));
		
		weightLabel=new JLabel("WEIGHT: ");
		weightLabel.setBounds(128,480,500,100);
		weightLabel.setFont(new Font("Arial",Font.PLAIN,20));
		
		weightText=new JTextField();
		weightText.setBounds(328,510,400,30);
		weightText.setFont(new Font("Arial",Font.PLAIN,20));
		
		
		stateLabel=new JLabel("STATUS: ");
		stateLabel.setBounds(128,540,500,100);
		stateLabel.setFont(new Font("Arial",Font.PLAIN,20));
		
		stateText=new JTextField("IN PREPARATION");
		stateText.setBounds(328,570,400,30);
		stateText.setFont(new Font("Arial",Font.PLAIN,20));
		stateText.setEditable(false);
		
		assicurateCheckBox=new JCheckBox("Insure");
		assicurateCheckBox.setBounds(390,650,200, 30);
		assicurateCheckBox.setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		assicurateCheckBox.setFont(new Font("Arial",Font.PLAIN,20));
		
		insuredValueLabel=new JLabel("INSURED VALUE: ");
		insuredValueLabel.setBounds(128,700,500,100);
		insuredValueLabel.setFont(new Font("Arial",Font.PLAIN,20));
		
		insuredValueText=new JTextField();
		insuredValueText.setBounds(328,730,400,30);
		insuredValueText.setFont(new Font("Arial",Font.PLAIN,20));
		
		insuredValueText.setEditable(false);
		
		logoutButton.setBounds(632,850,100,30);
		
		createSpeditionButton=new JButton("Create");
		createSpeditionButton.setFont(new Font("Arial",Font.PLAIN,12));
		createSpeditionButton.setBounds(128,850,100,30);
		
		add(errorLabel);
		add(createNewOrderLabel);
		add(codeLabel);
		add(codeText);
		add(dateLabel);
		add(dateText);
		add(addressLabel);
		add(addressText);
		add(weightLabel);
		add(weightText);
		add(stateLabel);
		add(stateText);
		add(assicurateCheckBox);
		add(insuredValueLabel);
		add(insuredValueText);
		add(logoutButton);
		add(createSpeditionButton);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		
		assicurateCheckBox.addActionListener(e->
		{
			if(assicurateCheckBox.isSelected()) insuredValueText.setEditable(true);
			else insuredValueText.setEditable(false);
		});
		
		logoutButton.addActionListener(e->
		{
			removeAll();
			UserPanel userPanel=new UserPanel(userList,shipList,insuredShipList);
			userPanel.setSize(900,1000);
			add(userPanel);
			
			revalidate();
			repaint();
		});
		
		allSpeditionsButton.addActionListener(e->
		{
			removeAll();
			UserSpeditionsPanel userSpeditionsPanel=new UserSpeditionsPanel(name,userList,shipList,insuredShipList);
			userSpeditionsPanel.setSize(900,1000);
			add(userSpeditionsPanel);
			
			revalidate();
			repaint();
		});
		
		createSpeditionButton.addActionListener(e->
		{
			errorLabel.setText("");
			if(addressText.getText().length()==0||weightText.getText().length()==0)
			{
				errorLabel.setText("There are one or more empty parameters.");
				errorLabel.setBounds(335,750,300,50);
				repaint();
			}
			else if(isNumber(weightText.getText())==false)
			{
				errorLabel.setText("Weight must be a number.");
				errorLabel.setBounds(375,750,300,50);
				repaint();
			}
			else
			{
				if(assicurateCheckBox.isSelected()&&!(insuredValueText.getText().length()>0)||!isNumber(insuredValueText.getText()))
				{
					errorLabel.setText("Incorrect insured value.");
					errorLabel.setBounds(375,750,300,50);
					repaint();
				}
				else if(assicurateCheckBox.isSelected()&&insuredValueText.getText().length()>0&&isNumber(insuredValueText.getText()))
				{
					insuredShip=new InsuredShipment(name,codeText.getText(),dateText.getText(),addressText.getText(),weightText.getText(),stateText.getText(),insuredValueText.getText(),"INSURED");
					insuredShipList.add(insuredShip);
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
				else if(assicurateCheckBox.isSelected()==false)
				{
					ship=new Shipment(name,codeText.getText(),dateText.getText(),addressText.getText(),weightText.getText(),stateText.getText(),"NOT INSURED");
					shipList.add(ship);
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
					
					removeAll();
					
					userSpeditionsPanel=new UserSpeditionsPanel(name,userList,shipList,insuredShipList);
					userSpeditionsPanel.setSize(900,1000);
					add(userSpeditionsPanel);
					
					revalidate();
					repaint();
					
				}
				
			}
			
		});
		
	}
	
	/**
	 * The method creates the shipment's code
	 * @param name username
	 * @return code the shipment's code
	 */
	
	public String createCode(String name)
	{
		date=new Date();
		if(name.length()<4)
			return name.toUpperCase()+giveNumbers()+date.toString().toUpperCase().substring(0,2);
		else return name.toUpperCase().substring(0,3)+giveNumbers()+date.toString().toUpperCase().substring(0,1);
		
	}
	
	public int giveNumbers()
	{
		int n=0;
		
		File numberFile=new File("numberFile");
		if (numberFile.length() != 0) { 
			try
	        {
	            FileInputStream fileInputStream = new FileInputStream(numberFile);
	            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	 
	            n= (int) objectInputStream.readObject();
	 
	            objectInputStream.close();
	            fileInputStream.close();
	        } 
	        catch (IOException exception) 
	        {
	        	System.out.println("I/O exception of some sort has occurred.");
	            exception.printStackTrace();
	        } 
	        catch (ClassNotFoundException exception) 
	        {
	            System.out.println("Class not found");
	            exception.printStackTrace();
	        }
			
		}
		
		n++;
		
		try
        {
            FileOutputStream fos = new FileOutputStream("numberFile");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(n);
            oos.close();
            fos.close();
        }
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
		
		return n;
		
	}
	
	
	/**
	 * The method transform the {@link Date} object to a String date
	 * @return date date in String form
	 */
	public String toStringDate()
	{
		date=new Date();
		String stringDate=date.toString();
		int index=0;
		int count=0;
		for(int i=0;i<stringDate.length();i++)
		{
			if(stringDate.charAt(i)==' ') count++;
			if(count==3)
				{
					index=i;
					break;
				}
		}
			
		return stringDate.substring(0,index);

	}
	
	/**
	 * The method check the weight
	 * @param weight the shipment's weight
	 * @return the check success
	 */
	
	public boolean isNumber(String weight)
	{
		for(int i=0;i<weight.length();i++)
		{
			if(weight.charAt(i)<'0'||weight.charAt(i)>'9') return false;
		}
		return true;
	}
	
	
	
}


