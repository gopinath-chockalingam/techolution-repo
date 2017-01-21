package com.gopi.restaurant.domain;

/**
 * Item is a POJO.
 * 
 * @author gopinath
 *
 */
public class Item {

	private int id;

	private String name;

	private int satisfactoryLevel;

	private int timeTaken;

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
