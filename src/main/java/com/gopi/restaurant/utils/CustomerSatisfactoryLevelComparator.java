package com.gopi.restaurant.utils;

import java.util.Comparator;

import com.gopi.restaurant.domain.Item;


/**
 * Custom comparator to sort based on Satisfactory level in descending order.
 * @author gopinath
 *
 */
public class CustomerSatisfactoryLevelComparator implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		// Sorting in descending order to get the max/highest satisfactory level at first index/position in items array
		return o2.getSatisfactoryLevel() - o1.getSatisfactoryLevel();
	}

}
