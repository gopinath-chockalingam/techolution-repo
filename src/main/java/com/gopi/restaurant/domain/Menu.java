package com.gopi.restaurant.domain;

/**
 * This class will have list of items/dishes
 * 
 * @author gopic
 *
 */
public class Menu {

	public Item[] items = null;
    
	public Menu() {
		
	}

	public Menu(int totalItems){
		items=new Item[totalItems];
		populateItems();
	}
	
	
	public void setItems(Item[] items) {
		this.items = items;
	}

	public Item[] getItems() {
		return items;
	}

	public void populateItems() {
		Item item;
        // populate all the given items in array 
		for (int i = 0; i <items.length; i++) {
			item = new Item(i, "Dish No-" + i);
			items[i] = item;
		}

	}

}
