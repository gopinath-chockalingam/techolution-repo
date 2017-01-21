package com.gopi.restaurant.domain;

/**
 * Customer will have food/dish items from the menu.
 * If T minutes given as input, customer will start to consume items/food and this will happen
 * until the summation of the time he takes to consume N dishes is less than the
 * given T minutes.
 * 
 * @author gopinath
 *
 */
public class Customer {

	private String customerName;
	// Time in minutes
	private int totalTimeGiven;
	
	private Menu menu = null;

	public Customer(String customerName, int totalTimeToEat, Menu menu) {
		this.customerName = customerName;
		this.totalTimeGiven = totalTimeToEat;
		this.menu = menu;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getTotalTimeGiven() {
		return totalTimeGiven;
	}

	public void setTotalTimeGiven(int totalTimeGiven) {
		this.totalTimeGiven = totalTimeGiven;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}


}
