package papillon.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import papillon.controllers.CheckController;
import papillon.models.CheckItem;

/**
 * Contains the check information
 */

public class CheckPanel extends JPanel {
 
	private static SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yy hh:mm:ss a");
	
    private JTextArea txtInfo = new JTextArea();
    private JButton buttonLook = new JButton("Invoice Lookup");
    
    private String serverName; 
    private Date date; 
    private String invoiceNum; 
    private ArrayList<CheckItem> checkItems; 
    private double subtotal; 
    private double tax; 
    private double total; 
    
    
    private CheckController checkCtrl;
    
    public CheckPanel(CheckController checkCtrl) {
    	this.checkCtrl = checkCtrl;
    	initialize();
    }
    

    private void initialize() {
    	setBorder(BorderFactory.createLineBorder(new Color(205, 205, 240)));
        setLayout(new BorderLayout());
        JScrollPane scr = new JScrollPane(txtInfo);
        scr.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        scr.setBackground(Color.white);
        add(scr, BorderLayout.CENTER);
        Font txtFont1 = new Font ("monospaced", 0, 11);
        txtInfo.setFont(txtFont1);
        txtInfo.setEditable(false);
        
        JPanel pnbtn = new JPanel(new BorderLayout());
        pnbtn.setBackground(Color.white);
        JPanel pnbtn1 = new JPanel(new GridLayout(1, 2));
        pnbtn1.setBackground(Color.white);
        JPanel tmp = new JPanel();
        tmp.setBackground(Color.white);
        pnbtn1.add(tmp);  // left empty
        pnbtn1.add(buttonLook);
        pnbtn.add(pnbtn1, BorderLayout.NORTH);        
        pnbtn1.setPreferredSize(new Dimension(200, 35));
        buttonLook.setFont(new Font("SansSerif", Font.BOLD, 10));
        buttonLook.setBackground(Color.blue);
        buttonLook.setForeground(Color.white);
        buttonLook.setMargin(new Insets(0,0,0,0));
       
        ArrowsPanel arrowsPanel = new ArrowsPanel(checkCtrl);
        pnbtn.add(arrowsPanel, BorderLayout.CENTER);
        pnbtn.setPreferredSize(new Dimension(200, 90));
        
        add(pnbtn, BorderLayout.SOUTH);
        this.setBackground(Color.white);
    }
    
    
    /**
     * Update the view
     */
    public void renderCheck() {
    	//TODO: Add the subtotal, tax and total. 
    	//check must also change the quantity of the same item and not add it n times. 
    	
    	
    	String result = "KYOTO SUSHI HOUSE\n"; 
    	result += "1 Sushi Way Ste 345\n"; 
    	result += "San Antonio, TX, 78260\n"; 
    	result += "210-555-6789\n"; 
    	result += "\n\n";  
    	
    	result += "Server: " + serverName + "\n";
		
		result += fmt.format(date) + "\n";
		result += "Invoice number: " + invoiceNum + "\n\n";
		result += " ---------------------------  \n\n";
		
		for (CheckItem item: checkItems) {
			result += convertCheckItemToString(item); 
			result += "\n";  
		}
		
		result += "\n\n"; 
		result += "Subtotal:\t\t" + formatCurrency(subtotal) + "\n"; 
		result += "Tax:\t\t\t" + formatCurrency(tax) + "\n"; 
		result += "Total:\t\t\t" + formatCurrency(total) + "\n"; 
		
		txtInfo.setText(result);
    	
    }
    
    public String formatCurrency(double d) {
    	return String.format("$%.2f", d); 
    }
    
    
	public void setServerName(String name) {
		serverName = name; 
	}
	
	public void setDate(Date date) {
		this.date = date; 
	}
	
	public void setInvoice(int invoiceNum) {
		this.invoiceNum = Integer.toString(invoiceNum);  
	}
	
	public void setCheckItems(ArrayList<CheckItem> items) {
		checkItems = items; 
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public void setTax(double tax) {
		this.tax = tax; 
	}
	
	public void setTotal(double total) {
		this.total = total; 
	}
	
	public String convertCheckItemToString(CheckItem item) {
		String word = "             "; 
		String shortName = item.getMenuItem().getName() + word;   
		if (shortName.length() >= 13) {
			shortName = shortName.substring(0, 13);
		} 
		String price = formatCurrency(item.getMenuItem().getPrice() * item.getQuantity()); 
		String checkItemString = shortName + "\t" + item.getQuantity() + "\t" + price;
		return checkItemString; 
	}
	
	
	
}