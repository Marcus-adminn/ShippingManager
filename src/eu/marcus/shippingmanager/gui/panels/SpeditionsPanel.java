package eu.marcus.shippingmanager.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import eu.marcus.shippingmanager.logic.shipment.InsuredShipment;
import eu.marcus.shippingmanager.logic.shipment.Shipment;
import eu.marcus.shippingmanager.logic.user.User;

/**
 * @author Marcus
 *
 * The generic shipment's panel of ShippingManager application used by Admin
   and {@link User}
 */
public class SpeditionsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The model of the shipment's table
	 */
	protected DefaultTableModel defaultTableModel;
	/**
	 * The shipment's table
	 */
	protected JTable speditionsTable;
	/**
	 * The shipment's table scrool
	 */
	protected JScrollPane tableScrollPanel;
	/**
	 * The generic logout button
	 */
	protected JButton logoutButton;
	
	/**
	 * The class contains three parameters to manage the shipments by Admin and {@link User}
	 * @param userList contains the users list
	 * @param shipList contains the not insured shipments list
	 * @param insuredShipList contains the insured shipments list
	 */
	
	public SpeditionsPanel(ArrayList<User> userList,ArrayList<Shipment> shipList,ArrayList<InsuredShipment> insuredShipList)
	{
		super(null);
		
		defaultTableModel=new DefaultTableModel()
		{

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		speditionsTable=new JTable(defaultTableModel);
		defaultTableModel.addColumn("CODE");
		defaultTableModel.addColumn("DATE");
		defaultTableModel.addColumn("DESTINATION");
		defaultTableModel.addColumn("WEIGHT");
		defaultTableModel.addColumn("STATUS");
		defaultTableModel.addColumn("INSURANCE");
		
		speditionsTable.setRowHeight(40);
		
		speditionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if(table.getValueAt(row, 4).equals("FAILED")) c.setBackground(Color.RED);
		        else if(table.getValueAt(row, 4).equals("REFUND REQUIRED")) c.setBackground(Color.CYAN);
		        else c.setBackground(Color.GREEN);
		        return c;
		    }
		});
		
		revalidate();
		repaint();
		
		tableScrollPanel=new JScrollPane(speditionsTable);
		tableScrollPanel.setVisible(true);
		tableScrollPanel.setBounds(40,320,802,500);
        
        logoutButton=new JButton("Logout");
        logoutButton.setFont(new Font("Arial",Font.PLAIN,12));
        logoutButton.setBounds(742,850,100,30);
        
        add(tableScrollPanel);
		add(logoutButton);
		
		setBackground(Color.getHSBColor((float)0.6, (float)0.2, (float)1));
		
	}
	
}