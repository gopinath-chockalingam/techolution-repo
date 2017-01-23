package com.gopi.restaurant.utils;

import java.util.Comparator;

import com.gopi.restaurant.domain.Item;


/**
 * Custom comparator to sort based on SatisfactoryRatio in descending order.
 * @author gopic
 *
 */
public class CustomerSatisfactoryRatioComparator implements Comparator<Item> {

	 /**
     * This method compares two items and sorts the item based on 
     * satisfactoryRatio of Item in descending order
     * @param customer
     * @return
     */
	@Override
	public int compare(Item o1, Item o2) {
		
		// Sorting satisfactoryRatio in descending order to get the max/highest satisfactory ratio for fractional knapsack
		return (int) (o2.getItemRatio() - o1.getItemRatio());
	}

}
