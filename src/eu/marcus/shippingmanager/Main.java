package eu.marcus.shippingmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import eu.marcus.shippingmanager.gui.GenericFrame;
import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;

/**
 * @author Marcus
 * @since 22/12/2020
 * @version 1.0
 * 
 * ShippingManager is a shipment's management application.
   A user is able to order goods which will then be managed by the Admin.
 *
 * The class contains the Main Method.
 */

public class Main {
	
	/**
	 * The Main Method contains userList, shipList and insuredShipList that will
	 * be manipulated to manage users and shipments in ShippingManager application.
	 * @param args arguments entered
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args){
		
		/**
		 * @param userList contains the user list
		 */
		ArrayList<User> userList=new ArrayList<>();
		/**
		 * @param shipList contains the not insured shipping list
		 */
		ArrayList<Shipment> shipList=new ArrayList<>();
		/**
		 * @param insuredShipList contains the not insured shipping list
		 */
		ArrayList<InsuredShipment> insuredShipList=new ArrayList<>();
		
		File userFile=new File("userFile");
		
		if (userFile.length() != 0) { 
			try
	        {
	            FileInputStream fileInputStream = new FileInputStream(userFile);
	            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	 
	            userList = (ArrayList) objectInputStream.readObject();
	 
	            objectInputStream.close();
	            fileInputStream.close();
	        } 
	        catch (IOException exception) 
	        {
	        	System.out.println("I/O exception of some sort has occurred.");
	            exception.printStackTrace();
	            return;
	        } 
	        catch (ClassNotFoundException exception) 
	        {
	            System.out.println("Class not found");
	            exception.printStackTrace();
	            return;
	        }
		}
		
		File shipFile=new File("shipFile");
		
		if (shipFile.length() != 0) { 
			try
	        {
	            FileInputStream fileInputStream = new FileInputStream(shipFile);
	            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	 
	            shipList = (ArrayList) objectInputStream.readObject();
	 
	            objectInputStream.close();
	            fileInputStream.close();
	        } 
	        catch (IOException exception) 
	        {
	        	System.out.println("I/O exception of some sort has occurred.");
	        	exception.printStackTrace();
	            return;
	        } 
	        catch (ClassNotFoundException exception) 
	        {
	            System.out.println("Class not found");
	            exception.printStackTrace();
	            return;
	        }
		}
		
		File insuredShipFile=new File("insuredShipFile");
		
		if (insuredShipFile.length() != 0) { 
			try
	        {
	            FileInputStream fileInputStream = new FileInputStream("insuredShipFile");
	            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	 
	            insuredShipList = (ArrayList) objectInputStream.readObject();
	 
	            objectInputStream.close();
	            fileInputStream.close();
	        } 
	        catch (IOException exception) 
	        {
	        	System.out.println("I/O exception of some sort has occurred.");
	        	exception.printStackTrace();
	            return;
	        } 
	        catch (ClassNotFoundException exception) 
	        {
	            System.out.println("Class not found");
	            exception.printStackTrace();
	            return;
	        }
	         
		}
		
		GenericFrame mainFrame=new GenericFrame(userList,shipList,insuredShipList);
		mainFrame.setVisible(true);

	}
	
}
