package papillon.models;

import java.io.Serializable;
import java.util.UUID;

public class CheckItem implements Serializable{

	private static final long serialVersionUID = 257542109751750802L;
	private UUID id;
	private MenuItem item;
	private int quantity; 
	
	public CheckItem(MenuItem item, int quantity) {
		this.id = UUID.randomUUID(); //random id and unlikely to repeat. Uniquely identifies the item
		this.item = item; 
		this.quantity = quantity; 
	}
	
	public double getSubtotal() {
		return (item.getPrice() * quantity); 
	}
	
	public MenuItem getMenuItem() {
		return item; 
		
	}

	public UUID getId() {
		return id; 
	}

	public int getQuantity() {
		return quantity; 
	}

}
