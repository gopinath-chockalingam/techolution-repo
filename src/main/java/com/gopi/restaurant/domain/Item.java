package com.gopi.restaurant.domain;

/**
 * Item has id,name and satisfactorylevel and timeTaken for the item.
 * 
 * @author gopic
 *
 */
public class Item {

	private int id;

	private String name;

	private int satisfactoryLevel;

	private int timeTaken;
	
	private double itemRatio;

	public double getItemRatio() {
		return itemRatio;
	}

	public void setItemRatio(double itemRatio) {
		this.itemRatio = itemRatio;
	}

	public Item(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public int getSatisfactoryLevel() {
		return satisfactoryLevel;
	}

	public void setSatisfactoryLevel(int satisfactoryLevel) {
		this.satisfactoryLevel = satisfactoryLevel;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
